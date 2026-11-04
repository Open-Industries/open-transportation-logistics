package org.openindustries.logistics.capacity;

import java.time.Instant;
import java.util.UUID;

/** One guarded consumption record — the auditable evidence of a draw on a LaneCapacity. */
public class CapacityBooking {
    public UUID id;
    public UUID accountId;
    public String bookingType;
    public long kg;
    public long remainingAfter;
    public Instant recordedAt;

    public static CapacityBooking of(UUID accountId, String bookingType, long kg, long remainingAfter) {
        CapacityBooking r = new CapacityBooking();
        r.id = UUID.randomUUID();
        r.accountId = accountId;
        r.bookingType = bookingType;
        r.kg = kg;
        r.remainingAfter = remainingAfter;
        r.recordedAt = Instant.now();
        return r;
    }
}
