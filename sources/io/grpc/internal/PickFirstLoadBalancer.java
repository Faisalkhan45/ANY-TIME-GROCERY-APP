package io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import io.grpc.ConnectivityState;
import io.grpc.ConnectivityStateInfo;
import io.grpc.EquivalentAddressGroup;
import io.grpc.LoadBalancer;
import io.grpc.Status;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

final class PickFirstLoadBalancer extends LoadBalancer {
    /* access modifiers changed from: private */
    public final LoadBalancer.Helper helper;
    private LoadBalancer.Subchannel subchannel;

    PickFirstLoadBalancer(LoadBalancer.Helper helper2) {
        this.helper = (LoadBalancer.Helper) Preconditions.checkNotNull(helper2, "helper");
    }

    public boolean acceptResolvedAddresses(LoadBalancer.ResolvedAddresses resolvedAddresses) {
        List<EquivalentAddressGroup> servers = resolvedAddresses.getAddresses();
        if (servers.isEmpty()) {
            handleNameResolutionError(Status.UNAVAILABLE.withDescription("NameResolver returned no usable address. addrs=" + resolvedAddresses.getAddresses() + ", attrs=" + resolvedAddresses.getAttributes()));
            return false;
        }
        LoadBalancer.Subchannel subchannel2 = this.subchannel;
        if (subchannel2 == null) {
            final LoadBalancer.Subchannel subchannel3 = this.helper.createSubchannel(LoadBalancer.CreateSubchannelArgs.newBuilder().setAddresses(servers).build());
            subchannel3.start(new LoadBalancer.SubchannelStateListener() {
                public void onSubchannelState(ConnectivityStateInfo stateInfo) {
                    PickFirstLoadBalancer.this.processSubchannelState(subchannel3, stateInfo);
                }
            });
            this.subchannel = subchannel3;
            this.helper.updateBalancingState(ConnectivityState.CONNECTING, new Picker(LoadBalancer.PickResult.withSubchannel(subchannel3)));
            subchannel3.requestConnection();
            return true;
        }
        subchannel2.updateAddresses(servers);
        return true;
    }

    public void handleNameResolutionError(Status error) {
        LoadBalancer.Subchannel subchannel2 = this.subchannel;
        if (subchannel2 != null) {
            subchannel2.shutdown();
            this.subchannel = null;
        }
        this.helper.updateBalancingState(ConnectivityState.TRANSIENT_FAILURE, new Picker(LoadBalancer.PickResult.withError(error)));
    }

    /* access modifiers changed from: private */
    public void processSubchannelState(LoadBalancer.Subchannel subchannel2, ConnectivityStateInfo stateInfo) {
        LoadBalancer.SubchannelPicker picker;
        ConnectivityState currentState = stateInfo.getState();
        if (currentState != ConnectivityState.SHUTDOWN) {
            if (stateInfo.getState() == ConnectivityState.TRANSIENT_FAILURE || stateInfo.getState() == ConnectivityState.IDLE) {
                this.helper.refreshNameResolution();
            }
            switch (AnonymousClass2.$SwitchMap$io$grpc$ConnectivityState[currentState.ordinal()]) {
                case 1:
                    picker = new RequestConnectionPicker(subchannel2);
                    break;
                case 2:
                    picker = new Picker(LoadBalancer.PickResult.withNoResult());
                    break;
                case 3:
                    picker = new Picker(LoadBalancer.PickResult.withSubchannel(subchannel2));
                    break;
                case 4:
                    picker = new Picker(LoadBalancer.PickResult.withError(stateInfo.getStatus()));
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported state:" + currentState);
            }
            this.helper.updateBalancingState(currentState, picker);
        }
    }

    /* renamed from: io.grpc.internal.PickFirstLoadBalancer$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$grpc$ConnectivityState;

        static {
            int[] iArr = new int[ConnectivityState.values().length];
            $SwitchMap$io$grpc$ConnectivityState = iArr;
            try {
                iArr[ConnectivityState.IDLE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$io$grpc$ConnectivityState[ConnectivityState.CONNECTING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$io$grpc$ConnectivityState[ConnectivityState.READY.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$io$grpc$ConnectivityState[ConnectivityState.TRANSIENT_FAILURE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public void shutdown() {
        LoadBalancer.Subchannel subchannel2 = this.subchannel;
        if (subchannel2 != null) {
            subchannel2.shutdown();
        }
    }

    public void requestConnection() {
        LoadBalancer.Subchannel subchannel2 = this.subchannel;
        if (subchannel2 != null) {
            subchannel2.requestConnection();
        }
    }

    private static final class Picker extends LoadBalancer.SubchannelPicker {
        private final LoadBalancer.PickResult result;

        Picker(LoadBalancer.PickResult result2) {
            this.result = (LoadBalancer.PickResult) Preconditions.checkNotNull(result2, "result");
        }

        public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs args) {
            return this.result;
        }

        public String toString() {
            return MoreObjects.toStringHelper((Class<?>) Picker.class).add("result", (Object) this.result).toString();
        }
    }

    private final class RequestConnectionPicker extends LoadBalancer.SubchannelPicker {
        private final AtomicBoolean connectionRequested = new AtomicBoolean(false);
        /* access modifiers changed from: private */
        public final LoadBalancer.Subchannel subchannel;

        RequestConnectionPicker(LoadBalancer.Subchannel subchannel2) {
            this.subchannel = (LoadBalancer.Subchannel) Preconditions.checkNotNull(subchannel2, "subchannel");
        }

        public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs args) {
            if (this.connectionRequested.compareAndSet(false, true)) {
                PickFirstLoadBalancer.this.helper.getSynchronizationContext().execute(new Runnable() {
                    public void run() {
                        RequestConnectionPicker.this.subchannel.requestConnection();
                    }
                });
            }
            return LoadBalancer.PickResult.withNoResult();
        }
    }
}
