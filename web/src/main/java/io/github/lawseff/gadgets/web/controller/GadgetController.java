package io.github.lawseff.gadgets.web.controller;

import io.github.lawseff.gadgets.service.GadgetService;
import io.github.lawseff.gadgets.service.gadget.GadgetDto;
import io.github.lawseff.gadgets.service.search.PaginationDto;
import io.github.lawseff.gadgets.service.search.SearchRequest;
import io.github.lawseff.gadgets.service.search.SearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.Duration;

@RestController
@RequiredArgsConstructor
public class GadgetController {

  private static final CacheControl CACHE_CONTROL = CacheControl.maxAge(Duration.ofHours(1));

  private final GadgetService service;

  @GetMapping("/gadgets")
  public ResponseEntity<SearchResponse<GadgetDto>> findGadgets(
      @PageableDefault(size = 12) Pageable pageable
  ) {
    var search = new SearchRequest(pageable.getPageNumber(), pageable.getPageSize());
    var result = service.findGadgets(search);
    var body = new SearchResponse<>(
        result.data(),
        // The logic uses 0-based pagination, but for the client it starts from 1. The page number is decremented by 1
        // during mapping, because 'spring.data.web.pageable.one-indexed-parameters' is enabled. To include the correct
        // pagination to the response, incrementing it back by 1
        incrementPageNumber(result.pagination())
    );
    return ResponseEntity.ok()
        .cacheControl(CACHE_CONTROL)
        .body(body);
  }

  private PaginationDto incrementPageNumber(PaginationDto pagination) {
    return new PaginationDto(
        pagination.totalElements(),
        pagination.totalPages(),
        pagination.pageNumber() + 1,
        pagination.pageSize()
    );
  }

}
