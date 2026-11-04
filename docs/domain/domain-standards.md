# Domain Standards — Transportation & Logistics

Alignment mapping for this core. *Aligned to* throughout: customs filings, dangerous-
goods declarations and carrier certifications remain with regulated parties and systems.

## Concept mapping

| Platform concept | Domain standard | Mapping |
|---|---|---|
| Logistic units | GS1 SSCC | Shipment identity posture; masked in this reference |
| Freight messaging | UN/EDIFACT, IATA Cargo-XML | Translated at the boundary; core contracts are API-first |
| Capacity & bookings | Carrier practice | Guarded lane-capacity accounts; the no-overrun invariant |
| Supply-chain security | ISO 28000 | Security management posture |
| Transit data | GTFS / NeTEx | Passenger data sharing posture where adopters operate transit |

## Scope boundaries

No customs brokerage, no dangerous-goods certification, no vehicle telematics control:
this is the booking/capacity/rules platform around regulated operations.
