package com.bumptech.glide.request;

import com.bumptech.glide.request.RequestCoordinator;

public final class ErrorRequestCoordinator implements RequestCoordinator, Request {
    private volatile Request error;
    private RequestCoordinator.RequestState errorState = RequestCoordinator.RequestState.CLEARED;
    private final RequestCoordinator parent;
    private volatile Request primary;
    private RequestCoordinator.RequestState primaryState = RequestCoordinator.RequestState.CLEARED;
    private final Object requestLock;

    public ErrorRequestCoordinator(Object requestLock2, RequestCoordinator parent2) {
        this.requestLock = requestLock2;
        this.parent = parent2;
    }

    public void setRequests(Request primary2, Request error2) {
        this.primary = primary2;
        this.error = error2;
    }

    public void begin() {
        synchronized (this.requestLock) {
            if (this.primaryState != RequestCoordinator.RequestState.RUNNING) {
                this.primaryState = RequestCoordinator.RequestState.RUNNING;
                this.primary.begin();
            }
        }
    }

    public void clear() {
        synchronized (this.requestLock) {
            this.primaryState = RequestCoordinator.RequestState.CLEARED;
            this.primary.clear();
            if (this.errorState != RequestCoordinator.RequestState.CLEARED) {
                this.errorState = RequestCoordinator.RequestState.CLEARED;
                this.error.clear();
            }
        }
    }

    public void pause() {
        synchronized (this.requestLock) {
            if (this.primaryState == RequestCoordinator.RequestState.RUNNING) {
                this.primaryState = RequestCoordinator.RequestState.PAUSED;
                this.primary.pause();
            }
            if (this.errorState == RequestCoordinator.RequestState.RUNNING) {
                this.errorState = RequestCoordinator.RequestState.PAUSED;
                this.error.pause();
            }
        }
    }

    public boolean isRunning() {
        boolean z;
        synchronized (this.requestLock) {
            if (this.primaryState != RequestCoordinator.RequestState.RUNNING) {
                if (this.errorState != RequestCoordinator.RequestState.RUNNING) {
                    z = false;
                }
            }
            z = true;
        }
        return z;
    }

    public boolean isComplete() {
        boolean z;
        synchronized (this.requestLock) {
            if (this.primaryState != RequestCoordinator.RequestState.SUCCESS) {
                if (this.errorState != RequestCoordinator.RequestState.SUCCESS) {
                    z = false;
                }
            }
            z = true;
        }
        return z;
    }

    public boolean isCleared() {
        boolean z;
        synchronized (this.requestLock) {
            z = this.primaryState == RequestCoordinator.RequestState.CLEARED && this.errorState == RequestCoordinator.RequestState.CLEARED;
        }
        return z;
    }

    public boolean isEquivalentTo(Request o) {
        if (!(o instanceof ErrorRequestCoordinator)) {
            return false;
        }
        ErrorRequestCoordinator other = (ErrorRequestCoordinator) o;
        if (!this.primary.isEquivalentTo(other.primary) || !this.error.isEquivalentTo(other.error)) {
            return false;
        }
        return true;
    }

    public boolean canSetImage(Request request) {
        boolean parentCanSetImage;
        synchronized (this.requestLock) {
            parentCanSetImage = parentCanSetImage();
        }
        return parentCanSetImage;
    }

    private boolean parentCanSetImage() {
        RequestCoordinator requestCoordinator = this.parent;
        return requestCoordinator == null || requestCoordinator.canSetImage(this);
    }

    public boolean canNotifyStatusChanged(Request request) {
        boolean z;
        synchronized (this.requestLock) {
            z = parentCanNotifyStatusChanged() && isValidRequestForStatusChanged(request);
        }
        return z;
    }

    public boolean canNotifyCleared(Request request) {
        boolean z;
        synchronized (this.requestLock) {
            z = parentCanNotifyCleared() && request.equals(this.primary);
        }
        return z;
    }

    private boolean parentCanNotifyCleared() {
        RequestCoordinator requestCoordinator = this.parent;
        return requestCoordinator == null || requestCoordinator.canNotifyCleared(this);
    }

    private boolean parentCanNotifyStatusChanged() {
        RequestCoordinator requestCoordinator = this.parent;
        return requestCoordinator == null || requestCoordinator.canNotifyStatusChanged(this);
    }

    private boolean isValidRequestForStatusChanged(Request request) {
        if (this.primaryState != RequestCoordinator.RequestState.FAILED) {
            return request.equals(this.primary);
        }
        return request.equals(this.error) && (this.errorState == RequestCoordinator.RequestState.SUCCESS || this.errorState == RequestCoordinator.RequestState.FAILED);
    }

    public boolean isAnyResourceSet() {
        boolean z;
        synchronized (this.requestLock) {
            if (!this.primary.isAnyResourceSet()) {
                if (!this.error.isAnyResourceSet()) {
                    z = false;
                }
            }
            z = true;
        }
        return z;
    }

    public void onRequestSuccess(Request request) {
        synchronized (this.requestLock) {
            if (request.equals(this.primary)) {
                this.primaryState = RequestCoordinator.RequestState.SUCCESS;
            } else if (request.equals(this.error)) {
                this.errorState = RequestCoordinator.RequestState.SUCCESS;
            }
            RequestCoordinator requestCoordinator = this.parent;
            if (requestCoordinator != null) {
                requestCoordinator.onRequestSuccess(this);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002c, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onRequestFailed(com.bumptech.glide.request.Request r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.requestLock
            monitor-enter(r0)
            com.bumptech.glide.request.Request r1 = r3.error     // Catch:{ all -> 0x002d }
            boolean r1 = r4.equals(r1)     // Catch:{ all -> 0x002d }
            if (r1 != 0) goto L_0x0020
            com.bumptech.glide.request.RequestCoordinator$RequestState r1 = com.bumptech.glide.request.RequestCoordinator.RequestState.FAILED     // Catch:{ all -> 0x002d }
            r3.primaryState = r1     // Catch:{ all -> 0x002d }
            com.bumptech.glide.request.RequestCoordinator$RequestState r1 = r3.errorState     // Catch:{ all -> 0x002d }
            com.bumptech.glide.request.RequestCoordinator$RequestState r2 = com.bumptech.glide.request.RequestCoordinator.RequestState.RUNNING     // Catch:{ all -> 0x002d }
            if (r1 == r2) goto L_0x001e
            com.bumptech.glide.request.RequestCoordinator$RequestState r1 = com.bumptech.glide.request.RequestCoordinator.RequestState.RUNNING     // Catch:{ all -> 0x002d }
            r3.errorState = r1     // Catch:{ all -> 0x002d }
            com.bumptech.glide.request.Request r1 = r3.error     // Catch:{ all -> 0x002d }
            r1.begin()     // Catch:{ all -> 0x002d }
        L_0x001e:
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return
        L_0x0020:
            com.bumptech.glide.request.RequestCoordinator$RequestState r1 = com.bumptech.glide.request.RequestCoordinator.RequestState.FAILED     // Catch:{ all -> 0x002d }
            r3.errorState = r1     // Catch:{ all -> 0x002d }
            com.bumptech.glide.request.RequestCoordinator r1 = r3.parent     // Catch:{ all -> 0x002d }
            if (r1 == 0) goto L_0x002b
            r1.onRequestFailed(r3)     // Catch:{ all -> 0x002d }
        L_0x002b:
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return
        L_0x002d:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.ErrorRequestCoordinator.onRequestFailed(com.bumptech.glide.request.Request):void");
    }

    public RequestCoordinator getRoot() {
        RequestCoordinator root;
        synchronized (this.requestLock) {
            RequestCoordinator requestCoordinator = this.parent;
            root = requestCoordinator != null ? requestCoordinator.getRoot() : this;
        }
        return root;
    }
}
