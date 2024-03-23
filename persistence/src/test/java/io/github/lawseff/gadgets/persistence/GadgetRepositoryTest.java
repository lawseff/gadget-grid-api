package io.github.lawseff.gadgets.persistence;

import io.github.lawseff.gadgets.persistence.entity.Dimensions;
import io.github.lawseff.gadgets.persistence.entity.Gadget;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DataJpaTest
class GadgetRepositoryTest {

  /**
   * Indicates how close double values should be to the expected result (absolute value)
   */
  private static final Offset<Double> COMPARISON_TOLERANCE = Assertions.within(0.0001);

  @Autowired
  private GadgetRepository repository;

  @BeforeEach
  void setUp() {
    repository.deleteAll();
  }

  @Test
  void repositoryWorks() {
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

  private void saveGadget(String name, double length, double width, double height) {
    var gadget = new Gadget();
    gadget.setName(name);
    var dimensions = new Dimensions();
    dimensions.setLengthMillimeters(length);
    dimensions.setWidthMillimeters(width);
    dimensions.setHeightMillimeters(height);
    gadget.setDimensions(dimensions);
    repository.save(gadget);
  }

}
