package org.openindustries.logistics.fleet;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class VehicleUnitService {

    @Inject
    VehicleUnitRepository repository;

    public VehicleUnit create(CreateVehicleUnitRequest request) {
        return repository.save(VehicleUnit.create(request.name(), request.vehicleType(), request.capacityKg()));
    }

    public VehicleUnit get(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new DomainException(404, "VehicleUnit not found: " + id));
    }

    public List<VehicleUnit> list() {
        return repository.findAll();
    }

    public VehicleUnit deploy(UUID id) {
        VehicleUnit entity = get(id);
        if (!"AVAILABLE".equals(entity.status)) {
            throw new DomainException(409, "Only a AVAILABLE vehicleunit can transition via deploy");
        }
        entity.status = "DEPLOYED";
        return repository.save(entity);
    }
}
