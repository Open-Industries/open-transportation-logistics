package org.openindustries.logistics.capacity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record CreateCapacityBookingRequest(
        @NotBlank @Pattern(regexp = "FTL|LTL|PARCEL|CONTAINER") String bookingType,
        @Positive long kg) {
}
