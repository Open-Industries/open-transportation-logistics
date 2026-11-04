package org.openindustries.logistics.functions.logisticssla;

import io.quarkus.funqy.Funq;

import java.util.ArrayList;
import java.util.List;

/**
 * Serverless function (Knative / OpenShift Serverless).
 * Compares late hours and on-time percentage against shipment targets; one finding per breach.
 * Compares measured values with targets and returns a verdict with one finding per
 * breach — machine-checkable assurance evidence.
 */
public class Function {

    public record Kpi(String shipmentId, int measuredLateHours, int targetLateHours,
                      double measuredOnTimePct, double targetOnTimePct) {
    }

    public record Verdict(String shipmentId, boolean compliant, List<String> findings) {
    }

    @Funq
    public Verdict logisticsSlaEvaluator(Kpi kpi) {
        List<String> findings = new ArrayList<>();
        if (kpi.measuredLateHours() > kpi.targetLateHours()) {
            findings.add("lateHours " + kpi.measuredLateHours() + " exceeds target " + kpi.targetLateHours());
        }
        if (kpi.measuredOnTimePct() < kpi.targetOnTimePct()) {
            findings.add("onTimePct " + kpi.measuredOnTimePct() + " below target " + kpi.targetOnTimePct());
        }
        return new Verdict(kpi.shipmentId(), findings.isEmpty(), findings);
    }
}
