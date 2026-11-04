# Data Architecture

Data view (VP-5) of the Architecture Description.

## 1. Ownership

| Data | Owner (only writer) | Store (reference → production) |
|---|---|---|
| Shipments records | shipment-service | in-memory → PostgreSQL `shipment` |
| Lane Capacity records | lane-capacity-service | in-memory → PostgreSQL `capacity` |
| Routing Rules records | routing-rules-service | in-memory → PostgreSQL `routing` |
| Fleet Units records | fleet-catalog-service | in-memory → PostgreSQL `fleet` |
| Notifications records | notification-service | in-memory → PostgreSQL `notification` |

In-memory adapters exist so the platform runs anywhere with zero setup; production
persistence is configuration (`DB_KIND`, `DB_URL`) plus the JPA adapter — the domain and
contracts never change (12-Factor IV).

## 2. Privacy and identifiers

Shipment identifiers follow GS1 discipline; the SSCC is validated and never stored or returned in clear beyond its masked form in this reference. Consignee personal data is minimized; all data is synthetic. Internal keys are UUIDs. All values in this repository are synthetic.

## 3. Consistency, evidence, retention

Strong within a context; eventual across contexts via CloudEvents with idempotent
consumers. Consumption and decision records are append-only evidence; corrections are
compensating records, never edits. Retention per the adopter's regulatory schedule;
personal data erasable per data-subject rights while evidential records are retained in
pseudonymized form (UUIDs only). Backups per service, encrypted, restore tested
(ISO 27002 A.8.13).
