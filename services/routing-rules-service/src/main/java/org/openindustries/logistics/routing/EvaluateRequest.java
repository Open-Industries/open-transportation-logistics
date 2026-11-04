package org.openindustries.logistics.routing;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record EvaluateRequest(
        @NotBlank @Pattern(regexp = "ROAD|RAIL|SEA|AIR") String mode,
        @Positive int requiredMaxTransitHours,
        @Positive long requiredMaxDistanceKm) {
}
