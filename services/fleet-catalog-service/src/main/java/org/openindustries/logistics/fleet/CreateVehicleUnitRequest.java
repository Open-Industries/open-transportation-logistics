package org.openindustries.logistics.fleet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record CreateVehicleUnitRequest(
        @NotBlank String name,
        @NotBlank @Pattern(regexp = "TRUCK|VAN|TRAIN|VESSEL") String vehicleType,
        @Positive int capacityKg) {
}
