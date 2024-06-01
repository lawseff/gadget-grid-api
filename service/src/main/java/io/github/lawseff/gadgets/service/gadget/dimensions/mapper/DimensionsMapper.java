package io.github.lawseff.gadgets.service.gadget.dimensions.mapper;

import io.github.lawseff.gadgets.persistence.gadget.Dimensions;
import io.github.lawseff.gadgets.service.gadget.dimensions.DimensionsDto;

public interface DimensionsMapper {

  DimensionsDto mapToDto(Dimensions dimensions);

}
