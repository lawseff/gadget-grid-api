package io.github.lawseff.gadgets.web.controller;

import io.github.lawseff.gadgets.service.inventory.InventoryService;
import io.github.lawseff.gadgets.service.inventory.InventorySummaryDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

  private final InventoryService service;

  @PostMapping("/summary")
  public InventorySummaryDto getSummary(@RequestBody @Valid GadgetIdsDto idsDto) {
    var ids = idsDto.gadgetIds();
    return service.getSummary(ids);
  }

}
