package io.grpc.internal;

import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import io.grpc.InternalServiceProviders;
import io.grpc.NameResolver;
import io.grpc.NameResolverProvider;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URI;
import java.util.Collection;
import java.util.Collections;

public final class DnsNameResolverProvider extends NameResolverProvider {
    private static final String SCHEME = "dns";

    public DnsNameResolver newNameResolver(URI targetUri, NameResolver.Args args) {
        if (!SCHEME.equals(targetUri.getScheme())) {
            return null;
        }
        String targetPath = (String) Preconditions.checkNotNull(targetUri.getPath(), "targetPath");
        Preconditions.checkArgument(targetPath.startsWith("/"), "the path component (%s) of the target (%s) must start with '/'", (Object) targetPath, (Object) targetUri);
        return new DnsNameResolver(targetUri.getAuthority(), targetPath.substring(1), args, GrpcUtil.SHARED_CHANNEL_EXECUTOR, Stopwatch.createUnstarted(), InternalServiceProviders.isAndroid(getClass().getClassLoader()));
    }

    public String getDefaultScheme() {
        return SCHEME;
    }

    /* access modifiers changed from: protected */
    public boolean isAvailable() {
        return true;
    }

    public int priority() {
        return 5;
    }

    /* access modifiers changed from: protected */
    public Collection<Class<? extends SocketAddress>> getProducedSocketAddressTypes() {
        return Collections.singleton(InetSocketAddress.class);
    }
}
