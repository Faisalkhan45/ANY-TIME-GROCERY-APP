package com.google.common.collect;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Queue;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@Deprecated
public abstract class TreeTraverser<T> {
    public abstract Iterable<T> children(T t);

    @Deprecated
    public static <T> TreeTraverser<T> using(final Function<T, ? extends Iterable<T>> nodeToChildrenFunction) {
        Preconditions.checkNotNull(nodeToChildrenFunction);
        return new TreeTraverser<T>() {
            public Iterable<T> children(T root) {
                return (Iterable) Function.this.apply(root);
            }
        };
    }

    @Deprecated
    public final FluentIterable<T> preOrderTraversal(final T root) {
        Preconditions.checkNotNull(root);
        return new FluentIterable<T>() {
            public UnmodifiableIterator<T> iterator() {
                return TreeTraverser.this.preOrderIterator(root);
            }
        };
    }

    /* access modifiers changed from: package-private */
    public UnmodifiableIterator<T> preOrderIterator(T root) {
        return new PreOrderIterator(root);
    }

    private final class PreOrderIterator extends UnmodifiableIterator<T> {
        private final Deque<Iterator<T>> stack;

        PreOrderIterator(T root) {
            ArrayDeque arrayDeque = new ArrayDeque();
            this.stack = arrayDeque;
            arrayDeque.addLast(Iterators.singletonIterator(Preconditions.checkNotNull(root)));
        }

        public boolean hasNext() {
            return !this.stack.isEmpty();
        }

        public T next() {
            Iterator<T> itr = this.stack.getLast();
            T result = Preconditions.checkNotNull(itr.next());
            if (!itr.hasNext()) {
                this.stack.removeLast();
            }
            Iterator<T> childItr = TreeTraverser.this.children(result).iterator();
            if (childItr.hasNext()) {
                this.stack.addLast(childItr);
            }
            return result;
        }
    }

    @Deprecated
    public final FluentIterable<T> postOrderTraversal(final T root) {
        Preconditions.checkNotNull(root);
        return new FluentIterable<T>() {
            public UnmodifiableIterator<T> iterator() {
                return TreeTraverser.this.postOrderIterator(root);
            }
        };
    }

    /* access modifiers changed from: package-private */
    public UnmodifiableIterator<T> postOrderIterator(T root) {
        return new PostOrderIterator(root);
    }

    private static final class PostOrderNode<T> {
        final Iterator<T> childIterator;
        final T root;

        PostOrderNode(T root2, Iterator<T> childIterator2) {
            this.root = Preconditions.checkNotNull(root2);
            this.childIterator = (Iterator) Preconditions.checkNotNull(childIterator2);
        }
    }

    private final class PostOrderIterator extends AbstractIterator<T> {
        private final ArrayDeque<PostOrderNode<T>> stack;

        PostOrderIterator(T root) {
            ArrayDeque<PostOrderNode<T>> arrayDeque = new ArrayDeque<>();
            this.stack = arrayDeque;
            arrayDeque.addLast(expand(root));
        }

        /* access modifiers changed from: protected */
        @CheckForNull
        public T computeNext() {
            while (!this.stack.isEmpty()) {
                PostOrderNode<T> top = this.stack.getLast();
                if (top.childIterator.hasNext()) {
                    this.stack.addLast(expand(top.childIterator.next()));
                } else {
                    this.stack.removeLast();
                    return top.root;
                }
            }
            return endOfData();
        }

        private PostOrderNode<T> expand(T t) {
            return new PostOrderNode<>(t, TreeTraverser.this.children(t).iterator());
        }
    }

    @Deprecated
    public final FluentIterable<T> breadthFirstTraversal(final T root) {
        Preconditions.checkNotNull(root);
        return new FluentIterable<T>() {
            public UnmodifiableIterator<T> iterator() {
                return new BreadthFirstIterator(root);
            }
        };
    }

    private final class BreadthFirstIterator extends UnmodifiableIterator<T> implements PeekingIterator<T> {
        private final Queue<T> queue;

        BreadthFirstIterator(T root) {
            ArrayDeque arrayDeque = new ArrayDeque();
            this.queue = arrayDeque;
            arrayDeque.add(root);
        }

        public boolean hasNext() {
            return !this.queue.isEmpty();
        }

        public T peek() {
            return this.queue.element();
        }

        public T next() {
            T result = this.queue.remove();
            Iterables.addAll(this.queue, TreeTraverser.this.children(result));
            return result;
        }
    }
}
