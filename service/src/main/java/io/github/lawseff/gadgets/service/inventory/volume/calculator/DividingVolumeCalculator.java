package io.github.lawseff.gadgets.service.inventory.volume.calculator;

import io.github.lawseff.gadgets.database.gadget.Gadget;
import io.github.lawseff.gadgets.service.inventory.volume.VolumeDto;
import io.github.lawseff.gadgets.service.inventory.volume.VolumeUnit;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class DividingVolumeCalculator implements VolumeCalculator {

  /**
   * How many cubic millimeters in a unit.
   */
  private static final Map<VolumeUnit, Double> CUBIC_MILLIMETERS_BY_UNIT = Map.of(
      VolumeUnit.LITER, 1000000d,
      VolumeUnit.CUBIC_INCH, 16387.064
  );

  @Override
  public VolumeDto getVolume(List<Gadget> gadgets, VolumeUnit unit) {
    var divisor = Optional.ofNullable(CUBIC_MILLIMETERS_BY_UNIT.get(unit))
        .orElseThrow(() -> new UnsupportedUnitException("Volume calculation not supported for " + unit));
    var cubicMillimeters = gadgets.stream()
        .mapToDouble(this::getCubicMillimetersVolume)
        .sum();
    var convertedVolume = cubicMillimeters / divisor;
    return new VolumeDto(unit, convertedVolume);
  }

  private double getCubicMillimetersVolume(Gadget gadget) {
    var dimensions = gadget.getDimensions();
    return dimensions.getLengthMillimeters() * dimensions.getWidthMillimeters() *
        dimensions.getHeightMillimeters();
  }

}
