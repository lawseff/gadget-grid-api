package io.github.lawseff.gadgets.service.search;

public record SearchRequest(

    int pageNumber,

    int pageSize,

    String searchString

) {}
