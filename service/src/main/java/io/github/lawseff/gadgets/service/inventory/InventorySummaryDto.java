package io.github.lawseff.gadgets.service.inventory;

import io.github.lawseff.gadgets.service.inventory.volume.VolumeDto;
import java.util.List;
import java.util.UUID;

public record InventorySummaryDto(

    List<UUID> gadgetIds,

    VolumeDto volume

) {}
