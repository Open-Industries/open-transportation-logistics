package org.openindustries.logistics.fleet;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/** Outbound port; default in-memory adapter, production adapter is JPA on this service's own database. */
@ApplicationScoped
public class VehicleUnitRepository {
    private final ConcurrentHashMap<UUID, VehicleUnit> store = new ConcurrentHashMap<>();

    public VehicleUnit save(VehicleUnit entity) {
        store.put(entity.id, entity);
        return entity;
    }

    public Optional<VehicleUnit> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<VehicleUnit> findAll() {
        return List.copyOf(store.values());
    }
}
