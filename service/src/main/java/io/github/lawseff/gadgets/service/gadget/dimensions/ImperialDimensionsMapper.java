package io.github.lawseff.gadgets.service.gadget.dimensions;

import io.github.lawseff.gadgets.persistence.entity.Dimensions;
import org.springframework.stereotype.Component;

@Component("imperialDimensionsMapper")
public class ImperialDimensionsMapper implements DimensionsMapper {

  /**
   * How many millimeters in an inch.
   */
  private static final double MM_IN_INCH = 25.4;

  @Override
  public DimensionsDto mapToDto(Dimensions dimensions) {
    return new DimensionsDto(
        LengthUnit.INCH,
        convertToInches(dimensions.getLengthMillimeters()),
        convertToInches(dimensions.getWidthMillimeters()),
        convertToInches(dimensions.getHeightMillimeters())
    );
  }

  private double convertToInches(double millimeters) {
    return millimeters / MM_IN_INCH;
  }

}
