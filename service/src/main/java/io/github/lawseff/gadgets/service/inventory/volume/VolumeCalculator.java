package io.github.lawseff.gadgets.service.inventory.volume;

import io.github.lawseff.gadgets.persistence.entity.Gadget;
import java.util.List;

public interface VolumeCalculator {

  VolumeDto getVolume(List<Gadget> gadgets);

}
