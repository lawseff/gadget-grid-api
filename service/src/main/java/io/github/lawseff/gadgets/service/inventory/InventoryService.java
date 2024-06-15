package io.github.lawseff.gadgets.service.inventory;

import io.github.lawseff.gadgets.database.gadget.GadgetRepository;
import io.github.lawseff.gadgets.service.gadget.dimensions.LengthUnit;
import io.github.lawseff.gadgets.service.inventory.volume.calculator.VolumeCalculator;
import io.github.lawseff.gadgets.service.inventory.volume.VolumeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventoryService {

  private final GadgetRepository repository;

  private final VolumeCalculator volumeCalculator;

  public InventorySummaryDto getSummary(List<UUID> ids, LengthUnit lengthUnit) {
    var gadgets = repository.findAllByIdNonDistinct(ids);
    var volumeUnit = switch (lengthUnit) {
      case MM -> VolumeUnit.LITER;
      case INCH -> VolumeUnit.CUBIC_INCH;
    };
    var volume = volumeCalculator.getVolume(gadgets, volumeUnit);
    return new InventorySummaryDto(ids, volume);
  }

}
