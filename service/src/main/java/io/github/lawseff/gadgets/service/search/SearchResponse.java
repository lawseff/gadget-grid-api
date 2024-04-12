package io.github.lawseff.gadgets.service.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record SearchResponse<T>(

    List<T> data,

    PaginationDto pagination,

    @JsonProperty("search")
    String searchString

) {}
