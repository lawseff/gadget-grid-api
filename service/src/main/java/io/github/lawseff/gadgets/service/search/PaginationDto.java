package io.github.lawseff.gadgets.service.search;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PaginationDto(

  long totalCount,

  int totalPages,

  @JsonProperty("page")
  int pageNumber,

  @JsonProperty("size")
  int pageSize

) {}
