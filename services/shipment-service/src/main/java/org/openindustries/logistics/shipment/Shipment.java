package org.openindustries.logistics.shipment;

import java.time.Instant;
import java.util.UUID;

/**
 * Shipment aggregate — Shipment registry; the SSCC is masked at creation.
 *
 * Privacy by design (ISO/IEC 27002 A.5.34): secret identifiers are validated at
 * creation and NEVER persisted or returned in clear — only a masked form is kept.
 * All identifiers in this reference implementation are synthetic.
 */
public class Shipment {
    public UUID id;
        public String mode;
        public String ssccCodeMasked;
        public String laneCode;
    public String status;
    public Instant createdAt;

    public static Shipment create(String mode, String ssccCode, String laneCode) {
        Shipment e = new Shipment();
        e.id = UUID.randomUUID();
        e.mode = mode;
        e.ssccCodeMasked = mask(ssccCode);
        e.laneCode = laneCode;
        e.status = "CREATED";
        e.createdAt = Instant.now();
        return e;
    }

    static String mask(String value) {
        if (value == null || value.length() < 4) {
            return "****";
        }
        return "*".repeat(value.length() - 4) + value.substring(value.length() - 4);
    }
}
