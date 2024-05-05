package io.github.lawseff.gadgets.web.controller;

import io.github.lawseff.gadgets.service.gadget.dimensions.LengthUnit;
import io.github.lawseff.gadgets.service.inventory.InventoryService;
import io.github.lawseff.gadgets.service.inventory.InventorySummaryDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

  private final InventoryService service;

  @PostMapping("/summary")
  public InventorySummaryDto getSummary(
      @RequestBody @Valid GadgetIdsDto idsDto,
      @RequestParam(name = "unit", required = false, defaultValue = "MM") LengthUnit lengthUnit
  ) {
    var ids = idsDto.gadgetIds();
    return service.getSummary(ids, lengthUnit);
  }

}
