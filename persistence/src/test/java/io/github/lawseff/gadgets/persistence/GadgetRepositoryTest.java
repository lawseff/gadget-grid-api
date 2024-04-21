package io.github.lawseff.gadgets.persistence;

import io.github.lawseff.gadgets.persistence.entity.Dimensions;
import io.github.lawseff.gadgets.persistence.entity.Gadget;
import io.github.lawseff.gadgets.persistence.exception.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import java.util.List;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DataJpaTest
class GadgetRepositoryTest {

  /**
   * Indicates how close double values should be to the expected result (absolute value).
   */
  private static final Offset<Double> COMPARISON_TOLERANCE = Assertions.within(0.0001);

  @Autowired
  private GadgetRepository repository;

  @BeforeEach
  void setUp() {
    repository.deleteAll();
  }

  @Test
  void findAllWorks() {
    saveGadget("Foo", 100, 150, 200);
    saveGadget("Bar", 250, 300, 350);

    var result = repository.findAll(Sort.by(Sort.Direction.DESC, "name"));

    assertThat(result).hasSize(2);
    assertSoftly(softly -> {
      var foo = result.get(0);
      softly.assertThat(foo.getId()).isNotNull();
      softly.assertThat(foo.getName()).isEqualTo("Foo");
      softly.assertThat(foo.getDimensions().getLengthMillimeters()).isCloseTo(100, COMPARISON_TOLERANCE);
      softly.assertThat(foo.getDimensions().getWidthMillimeters()).isCloseTo(150, COMPARISON_TOLERANCE);
      softly.assertThat(foo.getDimensions().getHeightMillimeters()).isCloseTo(200, COMPARISON_TOLERANCE);
      var bar = result.get(1);
      softly.assertThat(bar.getId()).isNotNull();
      softly.assertThat(bar.getName()).isEqualTo("Bar");
      softly.assertThat(bar.getDimensions().getLengthMillimeters()).isCloseTo(250, COMPARISON_TOLERANCE);
      softly.assertThat(bar.getDimensions().getWidthMillimeters()).isCloseTo(300, COMPARISON_TOLERANCE);
      softly.assertThat(bar.getDimensions().getHeightMillimeters()).isCloseTo(350, COMPARISON_TOLERANCE);
    });
  }

  @Test
  void findByNameWorks() {
    saveGadget("Foo", 300, 450, 500);
    saveGadget("Video Game Console", 100, 150, 200);
    saveGadget("Bar", 250, 300, 350);
    saveGadget("VIDEO GAME CONSOLE 2", 1000, 1100, 1200);

    var result = repository.findByNameContainingIgnoreCase(PageRequest.of(0, 10), "conso");

    assertThat(result.getTotalElements()).isEqualTo(2);
    assertSoftly(softly -> {
      var gadgets = result.get().toList();
      var console1 = gadgets.get(0);
      softly.assertThat(console1.getId()).isNotNull();
      softly.assertThat(console1.getName()).isEqualTo("Video Game Console");
      softly.assertThat(console1.getDimensions().getLengthMillimeters()).isCloseTo(100, COMPARISON_TOLERANCE);
      softly.assertThat(console1.getDimensions().getWidthMillimeters()).isCloseTo(150, COMPARISON_TOLERANCE);
      softly.assertThat(console1.getDimensions().getHeightMillimeters()).isCloseTo(200, COMPARISON_TOLERANCE);
      var console2 = gadgets.get(1);
      softly.assertThat(console2.getId()).isNotNull();
      softly.assertThat(console2.getName()).isEqualTo("VIDEO GAME CONSOLE 2");
      softly.assertThat(console2.getDimensions().getLengthMillimeters()).isCloseTo(1000, COMPARISON_TOLERANCE);
      softly.assertThat(console2.getDimensions().getWidthMillimeters()).isCloseTo(1100, COMPARISON_TOLERANCE);
      softly.assertThat(console2.getDimensions().getHeightMillimeters()).isCloseTo(1200, COMPARISON_TOLERANCE);
    });
  }

  @Test
  void findAllByIdNonDistinctWorks() {
    var fooId = saveGadget("Foo", 300, 450, 500);
    var keyboardId = saveGadget("Keyboard", 12, 150, 25);
    var barId = saveGadget("Bar", 250, 300, 350);

    var gadgets = repository.findAllByIdNonDistinct(
        // Keyboard is requested twice
        List.of(fooId, keyboardId, keyboardId, barId)
    );

    assertThat(gadgets).hasSize(4);
    assertSoftly(softly -> {
      var foo = gadgets.get(0);
      softly.assertThat(foo.getId()).isEqualTo(fooId);
      softly.assertThat(foo.getName()).isEqualTo("Foo");
      softly.assertThat(foo.getDimensions().getLengthMillimeters()).isCloseTo(300, COMPARISON_TOLERANCE);
      softly.assertThat(foo.getDimensions().getWidthMillimeters()).isCloseTo(450, COMPARISON_TOLERANCE);
      softly.assertThat(foo.getDimensions().getHeightMillimeters()).isCloseTo(500, COMPARISON_TOLERANCE);
      var keyboard1 = gadgets.get(1);
      softly.assertThat(keyboard1.getId()).isEqualTo(keyboardId);
      softly.assertThat(keyboard1.getName()).isEqualTo("Keyboard");
      softly.assertThat(keyboard1.getDimensions().getLengthMillimeters()).isCloseTo(12, COMPARISON_TOLERANCE);
      softly.assertThat(keyboard1.getDimensions().getWidthMillimeters()).isCloseTo(150, COMPARISON_TOLERANCE);
      softly.assertThat(keyboard1.getDimensions().getHeightMillimeters()).isCloseTo(25, COMPARISON_TOLERANCE);
      var keyboard2 = gadgets.get(2);
      softly.assertThat(keyboard2.getId()).isEqualTo(keyboardId);
      softly.assertThat(keyboard2.getName()).isEqualTo("Keyboard");
      softly.assertThat(keyboard2.getDimensions().getLengthMillimeters()).isCloseTo(12, COMPARISON_TOLERANCE);
      softly.assertThat(keyboard2.getDimensions().getWidthMillimeters()).isCloseTo(150, COMPARISON_TOLERANCE);
      softly.assertThat(keyboard2.getDimensions().getHeightMillimeters()).isCloseTo(25, COMPARISON_TOLERANCE);
      var bar = gadgets.get(3);
      softly.assertThat(bar.getId()).isEqualTo(barId);
      softly.assertThat(bar.getName()).isEqualTo("Bar");
      softly.assertThat(bar.getDimensions().getLengthMillimeters()).isCloseTo(250, COMPARISON_TOLERANCE);
      softly.assertThat(bar.getDimensions().getWidthMillimeters()).isCloseTo(300, COMPARISON_TOLERANCE);
      softly.assertThat(bar.getDimensions().getHeightMillimeters()).isCloseTo(350, COMPARISON_TOLERANCE);
    });
  }

  @Test
  void findAllByIdNonDistinctExceptionIfNotExists() {
    var fooId = saveGadget("Foo", 300, 450, 500);
    var keyboardId = saveGadget("Keyboard", 12, 150, 25);
    var barId = saveGadget("Bar", 250, 300, 350);
    var ids = List.of(
        fooId, keyboardId, keyboardId, barId,
        // Non-existent ID. Assuming it won't collide with the IDs, generated by Hibernate
        UUID.fromString("30c039f0-784c-477c-a327-873e3484f78e")
    );

    assertThatThrownBy(() -> repository.findAllByIdNonDistinct(ids))
        .isInstanceOf(EntityNotFoundException.class);
  }

  private UUID saveGadget(String name, double length, double width, double height) {
    var gadget = new Gadget();
    gadget.setName(name);
    var dimensions = new Dimensions();
    dimensions.setLengthMillimeters(length);
    dimensions.setWidthMillimeters(width);
    dimensions.setHeightMillimeters(height);
    gadget.setDimensions(dimensions);
    return repository.save(gadget).getId();
  }

}
