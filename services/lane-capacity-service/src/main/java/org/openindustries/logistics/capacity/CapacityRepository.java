package org.openindustries.logistics.capacity;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/** Outbound port; default in-memory adapter, production adapter is JPA on this service's own database. */
@ApplicationScoped
public class CapacityRepository {
    private final ConcurrentHashMap<UUID, LaneCapacity> accounts = new ConcurrentHashMap<>();
    private final CopyOnWriteArrayList<CapacityBooking> records = new CopyOnWriteArrayList<>();

    public LaneCapacity saveAccount(LaneCapacity account) {
        accounts.put(account.id, account);
        return account;
    }

    public Optional<LaneCapacity> findAccount(UUID id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public CapacityBooking saveRecord(CapacityBooking record) {
        records.add(record);
        return record;
    }

    public List<CapacityBooking> recordsOf(UUID accountId) {
        return records.stream().filter(r -> r.accountId.equals(accountId)).toList();
    }
}
