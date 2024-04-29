package io.github.lawseff.gadgets.service.inventory;

import io.github.lawseff.gadgets.persistence.GadgetRepository;
import io.github.lawseff.gadgets.service.inventory.volume.VolumeCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventoryService {

  private final GadgetRepository repository;

  private final VolumeCalculator metricalVolumeCalculator;

  public InventorySummaryDto getSummary(List<UUID> ids) {
    var gadgets = repository.findAllByIdNonDistinct(ids);
    var volume = metricalVolumeCalculator.getVolume(gadgets);
    return new InventorySummaryDto(ids, volume);
  }

}
