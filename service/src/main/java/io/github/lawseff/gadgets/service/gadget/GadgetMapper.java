package io.github.lawseff.gadgets.service.gadget;

import io.github.lawseff.gadgets.persistence.entity.Gadget;
import io.github.lawseff.gadgets.service.dimensions.DimensionsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GadgetMapper {

  private static final String NO_IMAGE = null;

  private final DimensionsMapper metricDimensionsMapper;

  public GadgetDto mapToDto(Gadget gadget) {
    var id = gadget.getId();
    var name = gadget.getName();
    var dimensions = metricDimensionsMapper.mapToDto(gadget.getDimensions());
    return new GadgetDto(id, name, NO_IMAGE, dimensions);
  }

}
