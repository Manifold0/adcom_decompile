// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import android.os.Bundle;
import java.util.Collection;
import com.google.android.gms.common.ConnectionResult;
import android.util.Log;
import android.os.Message;
import com.google.android.gms.internal.base.zap;
import android.os.Looper;
import java.util.concurrent.atomic.AtomicInteger;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.ArrayList;
import android.os.Handler;
import android.os.Handler$Callback;

public final class GmsClientEventManager implements Handler$Callback
{
    private final Handler mHandler;
    private final Object mLock;
    private final GmsClientEventState zaol;
    private final ArrayList<GoogleApiClient.ConnectionCallbacks> zaom;
    @VisibleForTesting
    private final ArrayList<GoogleApiClient.ConnectionCallbacks> zaon;
    private final ArrayList<GoogleApiClient.OnConnectionFailedListener> zaoo;
    private volatile boolean zaop;
    private final AtomicInteger zaoq;
    private boolean zaor;
    
    public GmsClientEventManager(final Looper looper, final GmsClientEventState zaol) {
        this.zaom = new ArrayList<GoogleApiClient.ConnectionCallbacks>();
        this.zaon = new ArrayList<GoogleApiClient.ConnectionCallbacks>();
        this.zaoo = new ArrayList<GoogleApiClient.OnConnectionFailedListener>();
        this.zaop = false;
        this.zaoq = new AtomicInteger(0);
        this.zaor = false;
        this.mLock = new Object();
        this.zaol = zaol;
        this.mHandler = new zap(looper, (Handler$Callback)this);
    }
    
    public final boolean areCallbacksEnabled() {
        return this.zaop;
    }
    
    public final void disableCallbacks() {
        this.zaop = false;
        this.zaoq.incrementAndGet();
    }
    
    public final void enableCallbacks() {
        this.zaop = true;
    }
    
    public final boolean handleMessage(final Message message) {
        if (message.what == 1) {
            final GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks)message.obj;
            synchronized (this.mLock) {
                if (this.zaop && this.zaol.isConnected() && this.zaom.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnected(this.zaol.getConnectionHint());
                }
                return true;
            }
        }
        Log.wtf("GmsClientEvents", new StringBuilder(45).append("Don't know how to handle message: ").append(message.what).toString(), (Throwable)new Exception());
        return false;
    }
    
    public final boolean isConnectionCallbacksRegistered(final GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        Preconditions.checkNotNull((Object)connectionCallbacks);
        synchronized (this.mLock) {
            return this.zaom.contains(connectionCallbacks);
        }
    }
    
    public final boolean isConnectionFailedListenerRegistered(final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        Preconditions.checkNotNull((Object)onConnectionFailedListener);
        synchronized (this.mLock) {
            return this.zaoo.contains(onConnectionFailedListener);
        }
    }
    
    @VisibleForTesting
    public final void onConnectionFailure(final ConnectionResult connectionResult) {
        Preconditions.checkHandlerThread(this.mHandler, "onConnectionFailure must only be called on the Handler thread");
        this.mHandler.removeMessages(1);
        synchronized (this.mLock) {
            final ArrayList<Object> list = new ArrayList<Object>(this.zaoo);
            final int value = this.zaoq.get();
            final ArrayList<Object> list2 = list;
            final int size = list2.size();
            int i = 0;
            while (i < size) {
                final GoogleApiClient.OnConnectionFailedListener value2 = list2.get(i);
                final int n = i + 1;
                final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = value2;
                if (!this.zaop || this.zaoq.get() != value) {
                    return;
                }
                i = n;
                if (!this.zaoo.contains(onConnectionFailedListener)) {
                    continue;
                }
                onConnectionFailedListener.onConnectionFailed(connectionResult);
                i = n;
            }
        }
    }
    // monitorexit(o)
    
    @VisibleForTesting
    protected final void onConnectionSuccess() {
        synchronized (this.mLock) {
            this.onConnectionSuccess(this.zaol.getConnectionHint());
        }
    }
    
    @VisibleForTesting
    public final void onConnectionSuccess(final Bundle bundle) {
        boolean b;
        boolean b2;
        boolean b3;
        ArrayList<Object> list;
        int value;
        ArrayList<Object> list2;
        int size;
        int i;
        GoogleApiClient.ConnectionCallbacks value2;
        int n;
        GoogleApiClient.ConnectionCallbacks connectionCallbacks;
        Label_0031_Outer:Label_0063_Outer:
        while (true) {
            b = true;
            Preconditions.checkHandlerThread(this.mHandler, "onConnectionSuccess must only be called on the Handler thread");
            while (true) {
            Label_0201:
                while (true) {
                    synchronized (this.mLock) {
                        if (!this.zaor) {
                            b2 = true;
                            Preconditions.checkState(b2);
                            this.mHandler.removeMessages(1);
                            this.zaor = true;
                            if (this.zaon.size() == 0) {
                                b3 = b;
                                Preconditions.checkState(b3);
                                list = new ArrayList<Object>(this.zaom);
                                value = this.zaoq.get();
                                list2 = list;
                                size = list2.size();
                                i = 0;
                                while (i < size) {
                                    value2 = list2.get(i);
                                    n = i + 1;
                                    connectionCallbacks = value2;
                                    if (!this.zaop || !this.zaol.isConnected() || this.zaoq.get() != value) {
                                        break;
                                    }
                                    i = n;
                                    if (this.zaon.contains(connectionCallbacks)) {
                                        continue Label_0031_Outer;
                                    }
                                    connectionCallbacks.onConnected(bundle);
                                    i = n;
                                }
                                break;
                            }
                            break Label_0201;
                        }
                    }
                    b2 = false;
                    continue Label_0063_Outer;
                }
                b3 = false;
                continue;
            }
        }
        this.zaon.clear();
        this.zaor = false;
    }
    // monitorexit(o)
    
    @VisibleForTesting
    public final void onUnintentionalDisconnection(final int n) {
        Preconditions.checkHandlerThread(this.mHandler, "onUnintentionalDisconnection must only be called on the Handler thread");
        this.mHandler.removeMessages(1);
        synchronized (this.mLock) {
            this.zaor = true;
            final ArrayList<Object> list = new ArrayList<Object>(this.zaom);
            final int value = this.zaoq.get();
            final ArrayList<Object> list2 = list;
            final int size = list2.size();
            int i = 0;
            while (i < size) {
                final GoogleApiClient.ConnectionCallbacks value2 = list2.get(i);
                final int n2 = i + 1;
                final GoogleApiClient.ConnectionCallbacks connectionCallbacks = value2;
                if (!this.zaop || this.zaoq.get() != value) {
                    break;
                }
                i = n2;
                if (!this.zaom.contains(connectionCallbacks)) {
                    continue;
                }
                connectionCallbacks.onConnectionSuspended(n);
                i = n2;
            }
        }
        this.zaon.clear();
        this.zaor = false;
    }
    // monitorexit(o)
    
    public final void registerConnectionCallbacks(final GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        Preconditions.checkNotNull((Object)connectionCallbacks);
        synchronized (this.mLock) {
            if (this.zaom.contains(connectionCallbacks)) {
                final String value = String.valueOf(connectionCallbacks);
                Log.w("GmsClientEvents", new StringBuilder(String.valueOf(value).length() + 62).append("registerConnectionCallbacks(): listener ").append(value).append(" is already registered").toString());
            }
            else {
                this.zaom.add(connectionCallbacks);
            }
            // monitorexit(this.mLock)
            if (this.zaol.isConnected()) {
                this.mHandler.sendMessage(this.mHandler.obtainMessage(1, (Object)connectionCallbacks));
            }
        }
    }
    
    public final void registerConnectionFailedListener(final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        Preconditions.checkNotNull((Object)onConnectionFailedListener);
        synchronized (this.mLock) {
            if (this.zaoo.contains(onConnectionFailedListener)) {
                final String value = String.valueOf(onConnectionFailedListener);
                Log.w("GmsClientEvents", new StringBuilder(String.valueOf(value).length() + 67).append("registerConnectionFailedListener(): listener ").append(value).append(" is already registered").toString());
            }
            else {
                this.zaoo.add(onConnectionFailedListener);
            }
        }
    }
    
    public final void unregisterConnectionCallbacks(final GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        Preconditions.checkNotNull((Object)connectionCallbacks);
        synchronized (this.mLock) {
            if (!this.zaom.remove(connectionCallbacks)) {
                final String value = String.valueOf(connectionCallbacks);
                Log.w("GmsClientEvents", new StringBuilder(String.valueOf(value).length() + 52).append("unregisterConnectionCallbacks(): listener ").append(value).append(" not found").toString());
            }
            else if (this.zaor) {
                this.zaon.add(connectionCallbacks);
            }
        }
    }
    
    public final void unregisterConnectionFailedListener(final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        Preconditions.checkNotNull((Object)onConnectionFailedListener);
        synchronized (this.mLock) {
            if (!this.zaoo.remove(onConnectionFailedListener)) {
                final String value = String.valueOf(onConnectionFailedListener);
                Log.w("GmsClientEvents", new StringBuilder(String.valueOf(value).length() + 57).append("unregisterConnectionFailedListener(): listener ").append(value).append(" not found").toString());
            }
        }
    }
    
    @VisibleForTesting
    public interface GmsClientEventState
    {
        Bundle getConnectionHint();
        
        boolean isConnected();
    }
}
