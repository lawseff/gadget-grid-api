package io.github.lawseff.gadgets.web;

import io.github.lawseff.gadgets.persistence.gadget.GadgetRepository;
import io.github.lawseff.gadgets.persistence.gadget.Dimensions;
import io.github.lawseff.gadgets.persistence.gadget.Gadget;
import lombok.Getter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public abstract class ApiTest {

  @Autowired
  protected MockMvc mockMvc;

  protected final TestData testData = new TestData();

  @Autowired
  private GadgetRepository repository;

  @BeforeAll
  void setUp() {
    repository.deleteAll();
    testData.saveAll();
  }

  @Getter
  protected class TestData {

    private Gadget actionCamera;

    private Gadget bluetoothSpeaker;

    private Gadget clock;

    private Gadget drone;

    private Gadget eReader;

    private Gadget fitnessTracker;

    private Gadget glucoseMeter;

    private Gadget headphones;

    private void saveAll() {
      actionCamera = saveGadget("Action Camera", 60, 40, 30);
      bluetoothSpeaker = saveGadget("Bluetooth Speaker", 150, 80, 25);
      clock = saveGadget("Clock (Digital)", 120, 40, 80);
      drone = saveGadget("Drone", 300, 300, 100);
      eReader = saveGadget("E-reader", 150, 100, 5);
      fitnessTracker = saveGadget("Fitness Tracker", 50, 20, 10);
      glucoseMeter = saveGadget("Glucose Meter", 80, 60, 20);
      headphones = saveGadget("Headphones", 180, 150, 50);
    }
    
    private Gadget saveGadget(String name, double length, double width, double height) {
      var gadget = new Gadget();
      gadget.setName(name);
      var dimensions = new Dimensions();
      dimensions.setLengthMillimeters(length);
      dimensions.setWidthMillimeters(width);
      dimensions.setHeightMillimeters(height);
      gadget.setDimensions(dimensions);
      return ApiTest.this.repository.save(gadget);
    }

  }

}
