package com.google.android.gms.dependencies;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0000HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0004HÆ\u0003J\u001f\u0010\f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0004HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0000¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0014"}, d2 = {"Lcom/google/android/gms/dependencies/Node;", "", "child", "dependency", "Lcom/google/android/gms/dependencies/Dependency;", "(Lcom/google/android/gms/dependencies/Node;Lcom/google/android/gms/dependencies/Dependency;)V", "getChild", "()Lcom/google/android/gms/dependencies/Node;", "getDependency", "()Lcom/google/android/gms/dependencies/Dependency;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "strict-version-matcher-plugin"}, k = 1, mv = {1, 4, 0})
/* compiled from: DataObjects.kt */
public final class Node {
    private final Node child;
    private final Dependency dependency;

    public static /* synthetic */ Node copy$default(Node node, Node node2, Dependency dependency2, int i, Object obj) {
        if ((i & 1) != 0) {
            node2 = node.child;
        }
        if ((i & 2) != 0) {
            dependency2 = node.dependency;
        }
        return node.copy(node2, dependency2);
    }

    public final Node component1() {
        return this.child;
    }

    public final Dependency component2() {
        return this.dependency;
    }

    public final Node copy(Node node, Dependency dependency2) {
        Intrinsics.checkNotNullParameter(dependency2, "dependency");
        return new Node(node, dependency2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Node)) {
            return false;
        }
        Node node = (Node) obj;
        return Intrinsics.areEqual((Object) this.child, (Object) node.child) && Intrinsics.areEqual((Object) this.dependency, (Object) node.dependency);
    }

    public int hashCode() {
        Node node = this.child;
        int i = 0;
        int hashCode = (node != null ? node.hashCode() : 0) * 31;
        Dependency dependency2 = this.dependency;
        if (dependency2 != null) {
            i = dependency2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "Node(child=" + this.child + ", dependency=" + this.dependency + ")";
    }

    public Node(Node child2, Dependency dependency2) {
        Intrinsics.checkNotNullParameter(dependency2, "dependency");
        this.child = child2;
        this.dependency = dependency2;
    }

    public final Node getChild() {
        return this.child;
    }

    public final Dependency getDependency() {
        return this.dependency;
    }
}
