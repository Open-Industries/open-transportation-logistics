# Services

One independently deployable Quarkus microservice per bounded context. Every service
follows the same hexagonal layout, exposes `/openapi`, `/q/health` and `/q/metrics`,
returns RFC 9457 problem details, and is configured only through environment variables
(Twelve-Factor). Lifecycle transitions take no body; `@Consumes` appears only on
endpoints that read one.

**Implementation order** (see `../docs/implementation-roadmap.md`):
Shipments → Lane Capacity → Routing Rules → Open APIs.

| Service | Port | Alignment |
|---|---|---|
| `shipment-service` | 8081 | GS1 SSCC identification posture |
| `lane-capacity-service` | 8082 | Capacity management discipline |
| `routing-rules-service` | 8083 | Network policy as auditable rules |
| `fleet-catalog-service` | 8084 | Fleet master data |
| `notification-service` | 8085 | Platform support (shared pattern) |

Run the pilot service:

```bash
mvn -pl services/shipment-service quarkus:dev
# OpenAPI UI: http://localhost:8081/docs — health: http://localhost:8081/q/health
```
