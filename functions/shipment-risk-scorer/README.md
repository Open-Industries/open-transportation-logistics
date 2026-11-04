# Shipment Risk Scorer (serverless function)

Quarkus Funqy function on Knative (scale to zero). Scores shipment risk signals (delay, damage, route risk) with an auditable rationale.

Invoke: `POST /shipmentRiskScorer` with the event payload.
