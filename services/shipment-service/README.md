# Shipment Service

Bounded context: **Shipments** — Shipment registry; the SSCC is masked at creation.

Alignment: GS1 SSCC identification posture. Lifecycle transitions take no body; secret identifiers,
where the domain has them, are masked at creation and never stored or returned in clear.
All data is synthetic.

| Item | Value |
|---|---|
| Port | `8081` (`HTTP_PORT`) |
| Base path | `/api/v1/shipments` |
| Owns data | Shipment records |
| Publishes | `shipment.created`, lifecycle events (see `contracts/asyncapi.yaml`) |

Run locally: `mvn -pl services/shipment-service quarkus:dev`.
