package io.github.lawseff.gadgets.service.gadget.dimensions;

public record DimensionsDto(

    LengthUnit unit,

    double length,

    double width,

    double height

) {}
