package io.github.lawseff.gadgets.service.gadget;

import io.github.lawseff.gadgets.persistence.entity.Gadget;
import io.github.lawseff.gadgets.service.gadget.dimensions.DimensionsMapper;
import io.github.lawseff.gadgets.service.gadget.dimensions.LengthUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GadgetMapper {

  private static final String NO_IMAGE = null;

  private final DimensionsMapper metricDimensionsMapper;

  public GadgetDto mapToDto(Gadget gadget, LengthUnit unit) {
    var mapper = switch (unit) {
      case MM -> metricDimensionsMapper;
      default -> throw new UnsupportedOperationException();
    };
    var id = gadget.getId();
    var name = gadget.getName();
    var dimensions = mapper.mapToDto(gadget.getDimensions());
    return new GadgetDto(id, name, NO_IMAGE, dimensions);
  }

}
