package com.bumptech.glide;

import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.request.transition.NoTransition;
import com.bumptech.glide.request.transition.TransitionFactory;
import com.bumptech.glide.request.transition.ViewAnimationFactory;
import com.bumptech.glide.request.transition.ViewPropertyAnimationFactory;
import com.bumptech.glide.request.transition.ViewPropertyTransition;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;

public abstract class TransitionOptions<CHILD extends TransitionOptions<CHILD, TranscodeType>, TranscodeType> implements Cloneable {
    private TransitionFactory<? super TranscodeType> transitionFactory = NoTransition.getFactory();

    public final CHILD dontTransition() {
        return transition(NoTransition.getFactory());
    }

    public final CHILD transition(int viewAnimationId) {
        return transition(new ViewAnimationFactory(viewAnimationId));
    }

    public final CHILD transition(ViewPropertyTransition.Animator animator) {
        return transition(new ViewPropertyAnimationFactory(animator));
    }

    public final CHILD transition(TransitionFactory<? super TranscodeType> transitionFactory2) {
        this.transitionFactory = (TransitionFactory) Preconditions.checkNotNull(transitionFactory2);
        return self();
    }

    public final CHILD clone() {
        try {
            return (TransitionOptions) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: package-private */
    public final TransitionFactory<? super TranscodeType> getTransitionFactory() {
        return this.transitionFactory;
    }

    private CHILD self() {
        return this;
    }

    public boolean equals(Object o) {
        if (o instanceof TransitionOptions) {
            return Util.bothNullOrEqual(this.transitionFactory, ((TransitionOptions) o).transitionFactory);
        }
        return false;
    }

    public int hashCode() {
        TransitionFactory<? super TranscodeType> transitionFactory2 = this.transitionFactory;
        if (transitionFactory2 != null) {
            return transitionFactory2.hashCode();
        }
        return 0;
    }
}
