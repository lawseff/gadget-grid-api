package io.github.lawseff.gadgets.service.search;

import java.util.List;

public record SearchResponse<T>(

    List<T> data,

    PaginationDto pagination

) {}
