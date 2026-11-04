# Fleet Catalog Service

Bounded context: **Fleet Units** — Fleet unit catalogue by type and capacity.

Alignment: Fleet master data. Lifecycle transitions take no body. All data is synthetic.

| Item | Value |
|---|---|
| Port | `8084` (`HTTP_PORT`) |
| Base path | `/api/v1/fleet-units` |
| Owns data | VehicleUnit records |

Run locally: `mvn -pl services/fleet-catalog-service quarkus:dev`.
