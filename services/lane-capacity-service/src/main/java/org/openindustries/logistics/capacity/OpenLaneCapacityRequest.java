package org.openindustries.logistics.capacity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record OpenLaneCapacityRequest(
        @NotBlank String laneCode,
        @PositiveOrZero long capacityKg) {
}
