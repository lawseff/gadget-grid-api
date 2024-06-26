package io.github.lawseff.gadgets.service.gadget.dimensions.mapper;

import io.github.lawseff.gadgets.persistence.gadget.Dimensions;
import io.github.lawseff.gadgets.service.gadget.dimensions.DimensionsDto;
import io.github.lawseff.gadgets.service.gadget.dimensions.LengthUnit;
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
