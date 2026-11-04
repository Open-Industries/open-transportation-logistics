package org.openindustries.logistics.fleet;

import java.util.UUID;

/** VehicleUnit aggregate — Fleet unit catalogue by type and capacity. */
public class VehicleUnit {
    public UUID id;
        public String name;
        public String vehicleType;
        public int capacityKg;
    public String status;

    public static VehicleUnit create(String name, String vehicleType, int capacityKg) {
        VehicleUnit e = new VehicleUnit();
        e.id = UUID.randomUUID();
        e.name = name;
        e.vehicleType = vehicleType;
        e.capacityKg = capacityKg;
        e.status = "AVAILABLE";
        return e;
    }
}
