package org.openindustries.logistics.routing;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record CreateRoutingRuleRequest(
        @NotBlank String name,
        @NotBlank @Pattern(regexp = "ROAD|RAIL|SEA|AIR") String mode,
        @Positive long maxDistanceKm,
        @Positive int maxTransitHours,
        @Min(1) @Max(100) int priority) {
}
