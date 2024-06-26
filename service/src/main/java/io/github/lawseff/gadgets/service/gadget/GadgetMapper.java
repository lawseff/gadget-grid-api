package io.github.lawseff.gadgets.service.gadget;

import io.github.lawseff.gadgets.persistence.gadget.Gadget;
import io.github.lawseff.gadgets.service.gadget.dimensions.mapper.DimensionsMapper;
import io.github.lawseff.gadgets.service.gadget.dimensions.LengthUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GadgetMapper {

  private static final String NO_IMAGE = null;

  private final DimensionsMapper metricDimensionsMapper;

  private final DimensionsMapper imperialDimensionsMapper;

  public GadgetDto mapToDto(Gadget gadget, LengthUnit unit) {
    var mapper = switch (unit) {
      case MM -> metricDimensionsMapper;
      case INCH -> imperialDimensionsMapper;
    };
    var id = gadget.getId();
    var name = gadget.getName();
    var dimensions = mapper.mapToDto(gadget.getDimensions());
    return new GadgetDto(id, name, NO_IMAGE, dimensions);
  }

}
