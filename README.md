# Open Transportation & Logistics — Reference Logistics Core Platform

A **standard, generic transportation and logistics core platform** that any organization in this vertical
can deploy and adapt: built as **microservices and serverless functions** on
**Java 21 + Quarkus**, aligned with **The Twelve-Factor App**, documented under the ISO
architecture and quality standards, and aligned with the domain standards of the
vertical (see the traceability matrix). Everything is *aligned to* the cited standards —
**no certification, conformance or regulatory-compliance claim is made**. All data is
**synthetic**.

> Reference implementation: small, complete and runnable. The rules that matter in this
> domain are enforced in code and covered by tests — start with the invariant:
> booked weight on a lane never exceeds the lane's capacity — a booking that would overrun it is rejected (422) and records nothing; capacity is never silently oversold.

## Standards alignment

| Standard / framework | What it governs here | Where |
|---|---|---|
| ISO/IEC/IEEE **42010** | Architecture description | `docs/architecture/architecture-description.md` |
| ISO/IEC **25010** (SQuaRE) | Product quality model and scenarios | `docs/architecture/quality-attributes.md` |
| ISO/IEC/IEEE **15288 & 12207** | Life-cycle processes | `docs/lifecycle/software-lifecycle.md` |
| ISO/IEC **27001 & 27002** | Information security | `docs/security/` |
| **The Twelve-Factor App** | Cloud-native methodology | `docs/architecture/twelve-factor.md` |
| GS1 (SSCC) | Logistic unit identification | `docs/domain/domain-standards.md` mapping; masked SSCC handling |
| UN/EDIFACT | EDI for transport & customs | Boundary: EDI translation at the edge |
| IATA Cargo-XML | Air cargo messaging | Boundary for air mode at the edge |
| ISO 28000 | Supply-chain security management | Security posture in `docs/security/` |
| GTFS / NeTEx | Public-transit data | Posture for passenger-mode data sharing |

Full traceability: `docs/compliance/standards-traceability.md`.

## The platform at a glance

### Microservices (Quarkus, `services/`)

| Service | Port | Alignment | Responsibility |
|---|---|---|---|
| `shipment-service` | 8081 | GS1 SSCC identification posture | Shipment registry; the SSCC is masked at creation. |
| `lane-capacity-service` | 8082 | Capacity management discipline | Guarded lane-capacity accounts and the freight bookings against them. |
| `routing-rules-service` | 8083 | Network policy as auditable rules | Decision rules binding transport modes to distance and transit-time envelopes. |
| `fleet-catalog-service` | 8084 | Fleet master data | Fleet unit catalogue by type and capacity. |
| `notification-service` | 8085 | Platform support (shared pattern) | Channel-agnostic dispatch of domain notifications from events. |

### Serverless functions (Quarkus Funqy + Knative, `functions/`)

| Function | Trigger | Responsibility |
|---|---|---|
| `shipment-risk-scorer` | Event / HTTP | Scores shipment risk signals (delay, damage, route risk) with an auditable rationale. |
| `logistics-sla-evaluator` | Event / HTTP | Compares late hours and on-time percentage against shipment targets; one finding per breach. |

## Design principles

1. **One bounded context per service**, identical hexagonal layout everywhere.
2. **The domain invariant is code, not prose** — booked weight on a lane never exceeds the lane's capacity — a booking that would overrun it is rejected (422) and records nothing; capacity is never silently oversold.
3. **Privacy by design** — Shipment identifiers follow GS1 discipline; the SSCC is validated and never stored or returned in clear beyond its masked form in this reference. Consignee personal data is minimized; all data is synthetic.
4. **Database per service, API-first (OpenAPI), events as CloudEvents.**
5. **Adapt by configuration** — rules, thresholds, tariffs/rates and providers are data
   and configuration, not code forks (see *Adaptation guide*).

## Quick start

Prerequisites: Java 21, Maven 3.9+, optionally Docker/Podman and Knative/OpenShift.

```bash
mvn verify                                    # build and test everything
mvn -pl services/shipment-service quarkus:dev
docker compose up -d                          # local PostgreSQL, Kafka, Keycloak
```

## Deployment

- **Kubernetes / OpenShift**: `kubectl apply -k deploy/kubernetes/`.
- **Serverless**: Knative Services in `deploy/knative/` (scale to zero).
- **Local**: `docker-compose.yml` for dependencies; services run with `quarkus:dev`.

## Adaptation guide

| You want to… | Change only… |
|---|---|
| Adjust domain rules and thresholds | Rule data in the `routing-rules-service` context and function configuration |
| Change the guarded capacity semantics | The `lane-capacity-service` context (invariant stays: never negative) |
| Plug your IdP / providers | OIDC at the gateway; provider adapters at the boundary |
| Replace in-memory persistence | The repository port's JPA adapter on the service's own database (`DB_KIND`, `DB_URL`) |

## Documentation map

- Architecture (42010): `docs/architecture/` · Decisions: `docs/adr/`
- Domain standards: `docs/domain/domain-standards.md`
- Quality (25010), Security (27001/27002), Life cycle (15288/12207), API, Data, Operations: under `docs/`

## License

Apache-2.0 — see `LICENSE`.
