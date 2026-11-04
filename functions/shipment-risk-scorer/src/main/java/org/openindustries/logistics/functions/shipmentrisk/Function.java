package org.openindustries.logistics.functions.shipmentrisk;

import io.quarkus.funqy.Funq;

/**
 * Serverless function (Knative / OpenShift Serverless).
 * Scores shipment risk signals (delay, damage, route risk) with an auditable rationale.
 * Transparent, auditable rules: NORMAL (< 35), WARNING (35-69), CRITICAL (>= 70);
 * the rationale is returned with the score for audit (ISO/IEC 27002 A.8.15).
 * Stateless by design (12-Factor VI): all state arrives in the request.
 */
public class Function {

    public record Event(String shipmentId, int delayIndex, int damageIndex, int routeRiskIndex) {
    }

    public record Score(String shipmentId, int score, String decision, String rationale) {
    }

    @Funq
    public Score shipmentRiskScorer(Event event) {
        int score = 0;
        StringBuilder why = new StringBuilder();
        if (event.delayIndex() >= 70) {
            score += 40;
            why.append("delayIndex >= 70; ");
        }
        if (event.damageIndex() >= 60) {
            score += 30;
            why.append("damageIndex >= 60; ");
        }
        if (event.routeRiskIndex() >= 50) {
            score += 30;
            why.append("routeRiskIndex >= 50; ");
        }
        String decision = score >= 70 ? "CRITICAL" : score >= 35 ? "WARNING" : "NORMAL";
        return new Score(event.shipmentId(), score, decision,
                why.length() == 0 ? "no signals" : why.toString().trim());
    }
}
