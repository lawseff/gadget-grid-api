package io.github.lawseff.gadgets.service.gadget;

import io.github.lawseff.gadgets.persistence.GadgetRepository;
import io.github.lawseff.gadgets.persistence.entity.Gadget;
import io.github.lawseff.gadgets.service.gadget.dimensions.LengthUnit;
import io.github.lawseff.gadgets.service.search.PaginationDto;
import io.github.lawseff.gadgets.service.search.SearchRequest;
import io.github.lawseff.gadgets.service.search.SearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GadgetService {

  /**
   * Regex for two or more whitespace characters.
   */
  private static final String MULTIPLE_WHITESPACES = "\\s{2,}";

  /**
   * Single space character.
   */
  private static final String SINGLE_SPACE = " ";

  private final GadgetRepository repository;

  private final GadgetMapper mapper;

  public SearchResponse<GadgetDto> findGadgets(SearchRequest request) {
    Pageable pageable = PageRequest.of(request.pageNumber(), request.pageSize());
    var searchString = prepareSearchString(request);
    var data = searchString.isPresent()
        ? repository.findByNameContainingIgnoreCase(pageable, searchString.get())
        : repository.findAll(pageable);
    return mapToResponse(data, searchString, request.unit());
  }

  private Optional<String> prepareSearchString(SearchRequest request) {
    return Optional.ofNullable(request.searchString())
        // Converts to lowercase for a prettier string in the response DTO
        .map(searchString -> searchString.toLowerCase(Locale.US)
            .trim().replaceAll(MULTIPLE_WHITESPACES, SINGLE_SPACE)
        );
  }

  private SearchResponse<GadgetDto> mapToResponse(
      Page<Gadget> page, Optional<String> searchString, LengthUnit unit
  ) {
    var gadgets = page.get()
        .map(gadget -> mapper.mapToDto(gadget, unit))
        .toList();
    return new SearchResponse<>(
        gadgets,
        new PaginationDto(
            page.getTotalElements(),
            page.getTotalPages(),
            page.getNumber(),
            page.getSize()
        ),
        searchString.orElse(null)
    );
  }

}
