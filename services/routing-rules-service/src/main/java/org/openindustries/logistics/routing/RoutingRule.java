package org.openindustries.logistics.routing;

import java.util.UUID;

/**
 * RoutingRule aggregate — a decision rule of this core: for one mode, it guarantees a
 * capacity (maxDistanceKm) and a threshold (maxTransitHours), with a precedence priority.
 * Only ACTIVE rules decide; every decision names its rule and its reason (audit).
 */
public class RoutingRule {
    public UUID id;
    public String name;
    public String mode;
    public long maxDistanceKm;
    public int maxTransitHours;
    public int priority;
    public String status;

    public static RoutingRule create(String name, String mode, long maxDistanceKm, int maxTransitHours, int priority) {
        RoutingRule r = new RoutingRule();
        r.id = UUID.randomUUID();
        r.name = name;
        r.mode = mode;
        r.maxDistanceKm = maxDistanceKm;
        r.maxTransitHours = maxTransitHours;
        r.priority = priority;
        r.status = "DRAFT";
        return r;
    }
}
