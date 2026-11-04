# Lane Capacity Service

Bounded context: **Lane Capacity** — Guarded lane-capacity accounts and the freight bookings against them.

Alignment: Capacity management discipline. Core invariant, enforced in code and tests: **the guarded
balance/capacity never goes negative** — over-drawing operations are rejected with `422`
and nothing is recorded. All data is synthetic.

| Item | Value |
|---|---|
| Port | `8082` (`HTTP_PORT`) |
| Base path | `/api/v1/lane-capacity` |
| Owns data | LaneCapacity accounts, CapacityBooking records |
| Publishes | `capacity.consumed`, `capacity.insufficient` |

Run locally: `mvn -pl services/lane-capacity-service quarkus:dev`.
