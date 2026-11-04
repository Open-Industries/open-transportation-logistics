# Standards Traceability Matrix

Every standard or framework claimed by this platform and where its evidence lives.
"Aligned to" throughout — no certification or regulatory-compliance claim is made.

| Standard / framework | Area | Evidence |
|---|---|---|
| ISO/IEC/IEEE 42010 | Architecture description | `docs/architecture/architecture-description.md`; `docs/adr/` |
| ISO/IEC 25010 | Quality model | `docs/architecture/quality-attributes.md` (QS-01…QS-08); tests; SLOs |
| ISO/IEC/IEEE 15288 / 12207 | Life-cycle processes | `docs/lifecycle/software-lifecycle.md` |
| ISO/IEC 27001 / 27002 | ISMS controls | `docs/security/` |
| Twelve-Factor App | Cloud methodology | `docs/architecture/twelve-factor.md` |
| OpenAPI 3.1 / AsyncAPI 2.6 / CloudEvents 1.0 | Contracts & events | `contracts/` |
| RFC 9457 | Error format | `Problem.java` in every service |
| GS1 (SSCC) | Logistic unit identification | `docs/domain/domain-standards.md` mapping; masked SSCC handling |
| UN/EDIFACT | EDI for transport & customs | Boundary: EDI translation at the edge |
| IATA Cargo-XML | Air cargo messaging | Boundary for air mode at the edge |
| ISO 28000 | Supply-chain security management | Security posture in `docs/security/` |
| GTFS / NeTEx | Public-transit data | Posture for passenger-mode data sharing |
