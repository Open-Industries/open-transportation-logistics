package org.openindustries.logistics.routing;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class RoutingRuleService {

    @Inject
    RoutingRuleRepository repository;

    public RoutingRule create(CreateRoutingRuleRequest request) {
        return repository.save(RoutingRule.create(request.name(), request.mode(),
                request.maxDistanceKm(), request.maxTransitHours(), request.priority()));
    }

    public RoutingRule get(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new DomainException(404, "RoutingRule not found: " + id));
    }

    public List<RoutingRule> list() {
        return repository.findAll();
    }

    public RoutingRule activate(UUID id) {
        RoutingRule rule = get(id);
        rule.status = "ACTIVE";
        return repository.save(rule);
    }

    public Evaluation evaluate(EvaluateRequest request) {
        var candidates = repository.activeFor(request.mode());
        if (candidates.isEmpty()) {
            return new Evaluation("DENIED", null,
                    "No active rule for " + request.mode());
        }
        for (RoutingRule rule : candidates) {
            if (rule.maxTransitHours <= request.requiredMaxTransitHours()
                    && rule.maxDistanceKm >= request.requiredMaxDistanceKm()) {
                return new Evaluation("ALLOWED", rule.id,
                        "Rule '" + rule.name + "' covers the request");
            }
        }
        return new Evaluation("DEGRADED", candidates.get(0).id,
                "Active rules exist for " + request.mode() + " but none meets the requirement");
    }
}
