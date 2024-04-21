package io.github.lawseff.gadgets.service.gadget.dimensions;

import io.github.lawseff.gadgets.persistence.entity.Dimensions;
import org.springframework.stereotype.Component;

@Component("metricDimensionsMapper")
public class MetricDimensionsMapper implements DimensionsMapper {

  @Override
  public DimensionsDto mapToDto(Dimensions dimensions) {
    return new DimensionsDto(
        LengthUnit.MM,
        dimensions.getLengthMillimeters(), dimensions.getWidthMillimeters(), dimensions.getHeightMillimeters()
    );
  }

}
