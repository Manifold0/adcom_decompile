package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.base.zap;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public final class GmsClientEventManager implements Callback {
    private final Handler mHandler;
    private final Object mLock = new Object();
    private final GmsClientEventState zaol;
    private final ArrayList<ConnectionCallbacks> zaom = new ArrayList();
    @VisibleForTesting
    private final ArrayList<ConnectionCallbacks> zaon = new ArrayList();
    private final ArrayList<OnConnectionFailedListener> zaoo = new ArrayList();
    private volatile boolean zaop = false;
    private final AtomicInteger zaoq = new AtomicInteger(0);
    private boolean zaor = false;

    @VisibleForTesting
    public interface GmsClientEventState {
        Bundle getConnectionHint();

        boolean isConnected();
    }

    public GmsClientEventManager(Looper looper, GmsClientEventState gmsClientEventState) {
        this.zaol = gmsClientEventState;
        this.mHandler = new zap(looper, this);
    }

    public final void disableCallbacks() {
        this.zaop = false;
        this.zaoq.incrementAndGet();
    }

    public final void enableCallbacks() {
        this.zaop = true;
    }

    @VisibleForTesting
    protected final void onConnectionSuccess() {
        synchronized (this.mLock) {
            onConnectionSuccess(this.zaol.getConnectionHint());
        }
    }

    @VisibleForTesting
    public final void onConnectionSuccess(Bundle bundle) {
        boolean z = true;
        Preconditions.checkHandlerThread(this.mHandler, "onConnectionSuccess must only be called on the Handler thread");
        synchronized (this.mLock) {
            Preconditions.checkState(!this.zaor);
            this.mHandler.removeMessages(1);
            this.zaor = true;
            if (this.zaon.size() != 0) {
                z = false;
            }
            Preconditions.checkState(z);
            ArrayList arrayList = new ArrayList(this.zaom);
            int i = this.zaoq.get();
            arrayList = arrayList;
            int size = arrayList.size();
            int i2 = 0;
            while (i2 < size) {
                Object obj = arrayList.get(i2);
                i2++;
                ConnectionCallbacks connectionCallbacks = (ConnectionCallbacks) obj;
                if (this.zaop && this.zaol.isConnected() && this.zaoq.get() == i) {
                    if (!this.zaon.contains(connectionCallbacks)) {
                        connectionCallbacks.onConnected(bundle);
                    }
                }
            }
            this.zaon.clear();
            this.zaor = false;
        }
    }

    @VisibleForTesting
    public final void onUnintentionalDisconnection(int i) {
        Preconditions.checkHandlerThread(this.mHandler, "onUnintentionalDisconnection must only be called on the Handler thread");
        this.mHandler.removeMessages(1);
        synchronized (this.mLock) {
            this.zaor = true;
            ArrayList arrayList = new ArrayList(this.zaom);
            int i2 = this.zaoq.get();
            arrayList = arrayList;
            int size = arrayList.size();
            int i3 = 0;
            while (i3 < size) {
                Object obj = arrayList.get(i3);
                i3++;
                ConnectionCallbacks connectionCallbacks = (ConnectionCallbacks) obj;
                if (this.zaop && this.zaoq.get() == i2) {
                    if (this.zaom.contains(connectionCallbacks)) {
                        connectionCallbacks.onConnectionSuspended(i);
                    }
                }
            }
            this.zaon.clear();
            this.zaor = false;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @com.google.android.gms.common.util.VisibleForTesting
    public final void onConnectionFailure(com.google.android.gms.common.ConnectionResult r8) {
        /*
        r7 = this;
        r0 = r7.mHandler;
        r1 = "onConnectionFailure must only be called on the Handler thread";
        com.google.android.gms.common.internal.Preconditions.checkHandlerThread(r0, r1);
        r0 = r7.mHandler;
        r1 = 1;
        r0.removeMessages(r1);
        r3 = r7.mLock;
        monitor-enter(r3);
        r0 = new java.util.ArrayList;	 Catch:{ all -> 0x0049 }
        r1 = r7.zaoo;	 Catch:{ all -> 0x0049 }
        r0.<init>(r1);	 Catch:{ all -> 0x0049 }
        r1 = r7.zaoq;	 Catch:{ all -> 0x0049 }
        r4 = r1.get();	 Catch:{ all -> 0x0049 }
        r0 = (java.util.ArrayList) r0;	 Catch:{ all -> 0x0049 }
        r5 = r0.size();	 Catch:{ all -> 0x0049 }
        r1 = 0;
        r2 = r1;
    L_0x0025:
        if (r2 >= r5) goto L_0x004c;
    L_0x0027:
        r1 = r0.get(r2);	 Catch:{ all -> 0x0049 }
        r2 = r2 + 1;
        r1 = (com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener) r1;	 Catch:{ all -> 0x0049 }
        r6 = r7.zaop;	 Catch:{ all -> 0x0049 }
        if (r6 == 0) goto L_0x003b;
    L_0x0033:
        r6 = r7.zaoq;	 Catch:{ all -> 0x0049 }
        r6 = r6.get();	 Catch:{ all -> 0x0049 }
        if (r6 == r4) goto L_0x003d;
    L_0x003b:
        monitor-exit(r3);	 Catch:{ all -> 0x0049 }
    L_0x003c:
        return;
    L_0x003d:
        r6 = r7.zaoo;	 Catch:{ all -> 0x0049 }
        r6 = r6.contains(r1);	 Catch:{ all -> 0x0049 }
        if (r6 == 0) goto L_0x0025;
    L_0x0045:
        r1.onConnectionFailed(r8);	 Catch:{ all -> 0x0049 }
        goto L_0x0025;
    L_0x0049:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0049 }
        throw r0;
    L_0x004c:
        monitor-exit(r3);	 Catch:{ all -> 0x0049 }
        goto L_0x003c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.GmsClientEventManager.onConnectionFailure(com.google.android.gms.common.ConnectionResult):void");
    }

    public final void registerConnectionCallbacks(ConnectionCallbacks connectionCallbacks) {
        Preconditions.checkNotNull(connectionCallbacks);
        synchronized (this.mLock) {
            if (this.zaom.contains(connectionCallbacks)) {
                String valueOf = String.valueOf(connectionCallbacks);
                Log.w("GmsClientEvents", new StringBuilder(String.valueOf(valueOf).length() + 62).append("registerConnectionCallbacks(): listener ").append(valueOf).append(" is already registered").toString());
            } else {
                this.zaom.add(connectionCallbacks);
            }
        }
        if (this.zaol.isConnected()) {
            this.mHandler.sendMessage(this.mHandler.obtainMessage(1, connectionCallbacks));
        }
    }

    public final boolean isConnectionCallbacksRegistered(ConnectionCallbacks connectionCallbacks) {
        boolean contains;
        Preconditions.checkNotNull(connectionCallbacks);
        synchronized (this.mLock) {
            contains = this.zaom.contains(connectionCallbacks);
        }
        return contains;
    }

    public final void unregisterConnectionCallbacks(ConnectionCallbacks connectionCallbacks) {
        Preconditions.checkNotNull(connectionCallbacks);
        synchronized (this.mLock) {
            if (!this.zaom.remove(connectionCallbacks)) {
                String valueOf = String.valueOf(connectionCallbacks);
                Log.w("GmsClientEvents", new StringBuilder(String.valueOf(valueOf).length() + 52).append("unregisterConnectionCallbacks(): listener ").append(valueOf).append(" not found").toString());
            } else if (this.zaor) {
                this.zaon.add(connectionCallbacks);
            }
        }
    }

    public final void registerConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener) {
        Preconditions.checkNotNull(onConnectionFailedListener);
        synchronized (this.mLock) {
            if (this.zaoo.contains(onConnectionFailedListener)) {
                String valueOf = String.valueOf(onConnectionFailedListener);
                Log.w("GmsClientEvents", new StringBuilder(String.valueOf(valueOf).length() + 67).append("registerConnectionFailedListener(): listener ").append(valueOf).append(" is already registered").toString());
            } else {
                this.zaoo.add(onConnectionFailedListener);
            }
        }
    }

    public final boolean isConnectionFailedListenerRegistered(OnConnectionFailedListener onConnectionFailedListener) {
        boolean contains;
        Preconditions.checkNotNull(onConnectionFailedListener);
        synchronized (this.mLock) {
            contains = this.zaoo.contains(onConnectionFailedListener);
        }
        return contains;
    }

    public final void unregisterConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener) {
        Preconditions.checkNotNull(onConnectionFailedListener);
        synchronized (this.mLock) {
            if (!this.zaoo.remove(onConnectionFailedListener)) {
                String valueOf = String.valueOf(onConnectionFailedListener);
                Log.w("GmsClientEvents", new StringBuilder(String.valueOf(valueOf).length() + 57).append("unregisterConnectionFailedListener(): listener ").append(valueOf).append(" not found").toString());
            }
        }
    }

    public final boolean handleMessage(Message message) {
        if (message.what == 1) {
            ConnectionCallbacks connectionCallbacks = (ConnectionCallbacks) message.obj;
            synchronized (this.mLock) {
                if (this.zaop && this.zaol.isConnected() && this.zaom.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnected(this.zaol.getConnectionHint());
                }
            }
            return true;
        }
        Log.wtf("GmsClientEvents", "Don't know how to handle message: " + message.what, new Exception());
        return false;
    }

    public final boolean areCallbacksEnabled() {
        return this.zaop;
    }
}
