package io.github.lawseff.gadgets.service.inventory.volume.calculator;

import io.github.lawseff.gadgets.persistence.gadget.Gadget;
import io.github.lawseff.gadgets.service.inventory.volume.VolumeDto;
import io.github.lawseff.gadgets.service.inventory.volume.VolumeUnit;
import java.util.List;

public interface VolumeCalculator {

  VolumeDto getVolume(List<Gadget> gadgets, VolumeUnit unit);

}
