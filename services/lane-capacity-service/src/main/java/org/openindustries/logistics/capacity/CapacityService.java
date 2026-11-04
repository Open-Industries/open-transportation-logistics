package org.openindustries.logistics.capacity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class CapacityService {

    @Inject
    CapacityRepository repository;

    public LaneCapacity open(OpenLaneCapacityRequest request) {
        return repository.saveAccount(LaneCapacity.open(request.laneCode(), request.capacityKg()));
    }

    public LaneCapacity get(UUID id) {
        return repository.findAccount(id)
                .orElseThrow(() -> new DomainException(404, "LaneCapacity not found: " + id));
    }

    /**
     * Draws from the account. Invariant: the balance/capacity never goes negative —
     * an over-drawing operation is rejected (422) and nothing is recorded.
     */
    public CapacityBooking consume(UUID accountId, CreateCapacityBookingRequest request) {
        LaneCapacity account = get(accountId);
        if (!"ACTIVE".equals(account.status)) {
            throw new DomainException(409, "LaneCapacity is not ACTIVE");
        }
        if (request.kg() > account.capacityKg) {
            throw new DomainException(422, "Insufficient capacity: requested " + request.kg()
                    + " exceeds available " + account.capacityKg);
        }
        account.capacityKg -= request.kg();
        repository.saveAccount(account);
        return repository.saveRecord(CapacityBooking.of(accountId, request.bookingType(), request.kg(), account.capacityKg));
    }

    public List<CapacityBooking> recordsOf(UUID accountId) {
        get(accountId);
        return repository.recordsOf(accountId);
    }
}
