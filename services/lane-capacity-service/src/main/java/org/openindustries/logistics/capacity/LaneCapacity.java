package org.openindustries.logistics.capacity;

import java.util.UUID;

/**
 * LaneCapacity aggregate — the guarded resource account of this core: a balance/capacity that
 * consumption operations draw from. Core invariant (ADR-0004): it NEVER goes negative;
 * an operation that would overdraw it is rejected and records nothing.
 */
public class LaneCapacity {
    public UUID id;
        public String laneCode;
    public long capacityKg;
    public String status;

    public static LaneCapacity open(String laneCode, long capacityKg) {
        LaneCapacity a = new LaneCapacity();
        a.id = UUID.randomUUID();
        a.laneCode = laneCode;
        a.capacityKg = capacityKg;
        a.status = "ACTIVE";
        return a;
    }
}
