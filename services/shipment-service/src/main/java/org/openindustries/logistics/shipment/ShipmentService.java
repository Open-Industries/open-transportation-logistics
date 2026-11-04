package org.openindustries.logistics.shipment;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ShipmentService {

    @Inject
    ShipmentRepository repository;

    public Shipment create(CreateShipmentRequest request) {
        return repository.save(Shipment.create(request.mode(), request.ssccCode(), request.laneCode()));
    }

    public Shipment get(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new DomainException(404, "Shipment not found: " + id));
    }

    public List<Shipment> list() {
        return repository.findAll();
    }

    public Shipment dispatch(UUID id) {
        Shipment entity = get(id);
        if ("DELIVERED".equals(entity.status)) {
            throw new DomainException(409, "A delivered shipment is terminal");
        }
        if ("IN_TRANSIT".equals(entity.status)) {
            throw new DomainException(409, "Shipment already IN_TRANSIT");
        }
        entity.status = "IN_TRANSIT";
        return repository.save(entity);
    }

    public Shipment deliver(UUID id) {
        Shipment entity = get(id);
        if ("DELIVERED".equals(entity.status)) {
            throw new DomainException(409, "A delivered shipment is terminal");
        }
        if ("DELIVERED".equals(entity.status)) {
            throw new DomainException(409, "Shipment already DELIVERED");
        }
        entity.status = "DELIVERED";
        return repository.save(entity);
    }
}
