package io.github.lawseff.gadgets.service;

import io.github.lawseff.gadgets.persistence.GadgetRepository;
import io.github.lawseff.gadgets.persistence.entity.Gadget;
import io.github.lawseff.gadgets.service.gadget.GadgetDto;
import io.github.lawseff.gadgets.service.gadget.GadgetMapper;
import io.github.lawseff.gadgets.service.search.PaginationDto;
import io.github.lawseff.gadgets.service.search.SearchRequest;
import io.github.lawseff.gadgets.service.search.SearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GadgetService {

  private static final Sort DEFAULT_SORT = Sort.by(Sort.Direction.ASC, "name");

  private final GadgetRepository repository;

  private final GadgetMapper mapper;

  public SearchResponse<GadgetDto> findGadgets(SearchRequest request) {
    var gadgetPage = repository.findAll(mapToPageable(request));
    return mapToResponse(gadgetPage);
  }

  private Pageable mapToPageable(SearchRequest request) {
    return PageRequest.of(request.pageNumber(), request.pageSize(), DEFAULT_SORT);
  }

  private SearchResponse<GadgetDto> mapToResponse(Page<Gadget> page) {
    return new SearchResponse<>(
        page.get().map(mapper::mapToDto).toList(),
        new PaginationDto(
            page.getTotalElements(),
            page.getTotalPages(),
            page.getNumber(),
            page.getSize()
        )
    );
  }

}
