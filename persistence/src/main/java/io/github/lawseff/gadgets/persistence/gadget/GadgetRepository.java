package io.github.lawseff.gadgets.persistence.gadget;

import io.github.lawseff.gadgets.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public interface GadgetRepository extends JpaRepository<Gadget, UUID> {

  Page<Gadget> findByNameContainingIgnoreCase(Pageable pageable, String namePart);

  default List<Gadget> findAllByIdNonDistinct(List<UUID> ids) {
    var gadgetsById = findAllById(ids)
        .stream()
        .collect(Collectors.toMap(Gadget::getId, Function.identity()));
    return ids.stream()
        .map(id -> Optional.ofNullable(gadgetsById.get(id))
            .orElseThrow(() -> new EntityNotFoundException("Gadget %s not found".formatted(id)))
        )
        .toList();
  }

}
