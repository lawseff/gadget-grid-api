package io.github.lawseff.gadgets.service.dimensions;

public record DimensionsDto(

    LengthUnit unit,

    double length,

    double width,

    double height

) {}
