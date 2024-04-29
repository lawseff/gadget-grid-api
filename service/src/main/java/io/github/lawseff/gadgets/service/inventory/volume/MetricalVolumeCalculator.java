package io.github.lawseff.gadgets.service.inventory.volume;

import io.github.lawseff.gadgets.persistence.entity.Gadget;
import org.springframework.stereotype.Component;
import java.util.List;

@Component("metricalVolumeCalculator")
public class MetricalVolumeCalculator implements VolumeCalculator {

  /**
   * How many liters in one mm^3.
   * I.e. the multiplier to convert cubic millimeters to liters.
   */
  private static final double LITERS_IN_CUBIC_MM = 1e-6;

  @Override
  public VolumeDto getVolume(List<Gadget> gadgets) {
    var volume = gadgets.stream()
        .mapToDouble(this::getVolume)
        .sum();
    return new VolumeDto(VolumeUnit.LITER, volume);
  }

  private double getVolume(Gadget gadget) {
    var dimensions = gadget.getDimensions();
    var cubicMm = dimensions.getLengthMillimeters() * dimensions.getWidthMillimeters() *
        dimensions.getHeightMillimeters();
    return cubicMm * LITERS_IN_CUBIC_MM;
  }

}
