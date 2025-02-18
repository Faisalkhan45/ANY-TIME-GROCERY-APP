package com.google.common.reflect;

import com.google.common.collect.Sets;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Set;

@ElementTypesAreNonnullByDefault
abstract class TypeVisitor {
    private final Set<Type> visited = Sets.newHashSet();

    TypeVisitor() {
    }

    /* Debug info: failed to restart local var, previous not found, register: 6 */
    public final void visit(Type... types) {
        int length = types.length;
        for (int i = 0; i < length; i++) {
            Type type = types[i];
            if (type != null && this.visited.add(type)) {
                boolean succeeded = false;
                try {
                    if (type instanceof TypeVariable) {
                        visitTypeVariable((TypeVariable) type);
                    } else if (type instanceof WildcardType) {
                        visitWildcardType((WildcardType) type);
                    } else if (type instanceof ParameterizedType) {
                        visitParameterizedType((ParameterizedType) type);
                    } else if (type instanceof Class) {
                        visitClass((Class) type);
                    } else if (type instanceof GenericArrayType) {
                        visitGenericArrayType((GenericArrayType) type);
                    } else {
                        String valueOf = String.valueOf(type);
                        throw new AssertionError(new StringBuilder(String.valueOf(valueOf).length() + 14).append("Unknown type: ").append(valueOf).toString());
                    }
                    succeeded = true;
                } finally {
                    if (!succeeded) {
                        this.visited.remove(type);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void visitClass(Class<?> cls) {
    }

    /* access modifiers changed from: package-private */
    public void visitGenericArrayType(GenericArrayType t) {
    }

    /* access modifiers changed from: package-private */
    public void visitParameterizedType(ParameterizedType t) {
    }

    /* access modifiers changed from: package-private */
    public void visitTypeVariable(TypeVariable<?> typeVariable) {
    }

    /* access modifiers changed from: package-private */
    public void visitWildcardType(WildcardType t) {
    }
}
