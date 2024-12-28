package io.grpc.internal;

import io.grpc.LoadBalancer;
import io.grpc.LoadBalancerProvider;
import io.grpc.NameResolver;
import java.util.Map;

public final class PickFirstLoadBalancerProvider extends LoadBalancerProvider {
    private static final String NO_CONFIG = "no service config";

    public boolean isAvailable() {
        return true;
    }

    public int getPriority() {
        return 5;
    }

    public String getPolicyName() {
        return GrpcUtil.DEFAULT_LB_POLICY;
    }

    public LoadBalancer newLoadBalancer(LoadBalancer.Helper helper) {
        return new PickFirstLoadBalancer(helper);
    }

    public NameResolver.ConfigOrError parseLoadBalancingPolicyConfig(Map<String, ?> map) {
        return NameResolver.ConfigOrError.fromConfig(NO_CONFIG);
    }
}
