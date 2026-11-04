# Logistics SLA Evaluator (serverless function)

Quarkus Funqy function on Knative (scale to zero). Compares late hours and on-time percentage against shipment targets; one finding per breach.

Invoke: `POST /logisticsSlaEvaluator` with the KPI payload.
