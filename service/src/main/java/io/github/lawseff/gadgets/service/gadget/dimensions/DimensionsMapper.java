package io.github.lawseff.gadgets.service.gadget.dimensions;

import io.github.lawseff.gadgets.persistence.entity.Dimensions;

public interface DimensionsMapper {

  DimensionsDto mapToDto(Dimensions dimensions);

}
