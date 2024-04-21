package io.github.lawseff.gadgets.web.controller;

import io.github.lawseff.gadgets.service.gadget.GadgetService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

@RestController
@RequestMapping("/gadgets")
@RequiredArgsConstructor
public class GadgetController {

  private static final CacheControl CACHE_CONTROL = CacheControl.maxAge(Duration.ofHours(1));

  private final GadgetService service;

  @GetMapping
  public ResponseEntity<SearchResponse<GadgetDto>> findGadgets(
      @PageableDefault(size = 12, sort = "name") Pageable pageable,
      @RequestParam(name = "search", required = false) String searchString
  ) {
    // For some reason, Spring doesn't decode the query param ('%20' and '+' aren't decoded to space)
    var decodedSearchString = searchString != null ? URLDecoder.decode(searchString, StandardCharsets.UTF_8) : null;
    var search = new SearchRequest(pageable.getPageNumber(), pageable.getPageSize(), decodedSearchString);
    var result = service.findGadgets(search);
    var body = new SearchResponse<>(
        result.data(),
        // The logic uses 0-based pagination, but for the client it starts from 1. The page number is decremented by 1
        // during mapping, because 'spring.data.web.pageable.one-indexed-parameters' is enabled. To include the correct
        // pagination to the response, incrementing it back by 1
        incrementPageNumber(result.pagination()),
        result.searchString()
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
