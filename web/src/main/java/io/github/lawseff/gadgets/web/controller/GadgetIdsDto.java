package io.github.lawseff.gadgets.web.controller;

import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

public record GadgetIdsDto(

    @Size(max = 500)
    List<UUID> gadgetIds

) {}
