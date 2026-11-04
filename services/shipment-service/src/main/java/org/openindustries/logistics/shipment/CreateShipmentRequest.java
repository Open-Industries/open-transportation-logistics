package org.openindustries.logistics.shipment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record CreateShipmentRequest(
        @NotBlank @Pattern(regexp = "ROAD|RAIL|SEA|AIR") String mode,
        @NotBlank String ssccCode,
        @NotBlank String laneCode) {
}
