package io.github.lawseff.gadgets.service.search;

import io.github.lawseff.gadgets.service.gadget.dimensions.LengthUnit;

public record SearchRequest(

    int pageNumber,

    int pageSize,

    String searchString,

    LengthUnit unit

) {}
