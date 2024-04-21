package io.github.lawseff.gadgets.service.gadget;

import io.github.lawseff.gadgets.service.gadget.dimensions.DimensionsDto;
import java.util.UUID;

public record GadgetDto(

    UUID id,

    String name,

    String imageUrl,

    DimensionsDto dimensions

) {}
