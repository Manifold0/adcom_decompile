// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.api.GoogleApiClient;
import android.util.Log;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import android.support.annotation.Nullable;
import android.util.SparseArray;

public class zaj extends zal
{
    private final SparseArray<zaa> zacw;
    
    private zaj(final LifecycleFragment lifecycleFragment) {
        super(lifecycleFragment);
        this.zacw = (SparseArray<zaa>)new SparseArray();
        this.mLifecycleFragment.addCallback("AutoManageHelper", (LifecycleCallback)this);
    }
    
    public static zaj zaa(final LifecycleActivity lifecycleActivity) {
        final LifecycleFragment fragment = getFragment(lifecycleActivity);
        final zaj zaj = (zaj)fragment.getCallbackOrNull("AutoManageHelper", (Class)zaj.class);
        if (zaj != null) {
            return zaj;
        }
        return new zaj(fragment);
    }
    
    @Nullable
    private final zaa zab(final int n) {
        if (this.zacw.size() <= n) {
            return null;
        }
        return (zaa)this.zacw.get(this.zacw.keyAt(n));
    }
    
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        for (int i = 0; i < this.zacw.size(); ++i) {
            final zaa zab = this.zab(i);
            if (zab != null) {
                printWriter.append(s).append("GoogleApiClient #").print(zab.zacx);
                printWriter.println(":");
                zab.zacy.dump(String.valueOf(s).concat("  "), fileDescriptor, printWriter, array);
            }
        }
    }
    
    @Override
    public void onStart() {
        super.onStart();
        final boolean mStarted = this.mStarted;
        final String value = String.valueOf(this.zacw);
        Log.d("AutoManageHelper", new StringBuilder(String.valueOf(value).length() + 14).append("onStart ").append(mStarted).append(" ").append(value).toString());
        if (this.zadf.get() == null) {
            for (int i = 0; i < this.zacw.size(); ++i) {
                final zaa zab = this.zab(i);
                if (zab != null) {
                    zab.zacy.connect();
                }
            }
        }
    }
    
    @Override
    public void onStop() {
        super.onStop();
        for (int i = 0; i < this.zacw.size(); ++i) {
            final zaa zab = this.zab(i);
            if (zab != null) {
                zab.zacy.disconnect();
            }
        }
    }
    
    public final void zaa(final int n) {
        final zaa zaa = (zaa)this.zacw.get(n);
        this.zacw.remove(n);
        if (zaa != null) {
            zaa.zacy.unregisterConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener)zaa);
            zaa.zacy.disconnect();
        }
    }
    
    public final void zaa(final int n, final GoogleApiClient googleApiClient, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        Preconditions.checkNotNull((Object)googleApiClient, (Object)"GoogleApiClient instance cannot be null");
        Preconditions.checkState(this.zacw.indexOfKey(n) < 0, (Object)new StringBuilder(54).append("Already managing a GoogleApiClient with id ").append(n).toString());
        final zam zam = this.zadf.get();
        final boolean mStarted = this.mStarted;
        final String value = String.valueOf(zam);
        Log.d("AutoManageHelper", new StringBuilder(String.valueOf(value).length() + 49).append("starting AutoManage for client ").append(n).append(" ").append(mStarted).append(" ").append(value).toString());
        this.zacw.put(n, (Object)new zaa(n, googleApiClient, onConnectionFailedListener));
        if (this.mStarted && zam == null) {
            final String value2 = String.valueOf(googleApiClient);
            Log.d("AutoManageHelper", new StringBuilder(String.valueOf(value2).length() + 11).append("connecting ").append(value2).toString());
            googleApiClient.connect();
        }
    }
    
    @Override
    protected final void zaa(final ConnectionResult connectionResult, final int n) {
        Log.w("AutoManageHelper", "Unresolved error while connecting client. Stopping auto-manage.");
        if (n < 0) {
            Log.wtf("AutoManageHelper", "AutoManageLifecycleHelper received onErrorResolutionFailed callback but no failing client ID is set", (Throwable)new Exception());
        }
        else {
            final zaa zaa = (zaa)this.zacw.get(n);
            if (zaa != null) {
                this.zaa(n);
                final GoogleApiClient.OnConnectionFailedListener zacz = zaa.zacz;
                if (zacz != null) {
                    zacz.onConnectionFailed(connectionResult);
                }
            }
        }
    }
    
    @Override
    protected final void zao() {
        for (int i = 0; i < this.zacw.size(); ++i) {
            final zaa zab = this.zab(i);
            if (zab != null) {
                zab.zacy.connect();
            }
        }
    }
    
    private final class zaa implements OnConnectionFailedListener
    {
        public final int zacx;
        public final GoogleApiClient zacy;
        public final OnConnectionFailedListener zacz;
        
        public zaa(final int zacx, final GoogleApiClient zacy, final OnConnectionFailedListener zacz) {
            this.zacx = zacx;
            this.zacy = zacy;
            this.zacz = zacz;
            zacy.registerConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener)this);
        }
        
        @Override
        public final void onConnectionFailed(@NonNull final ConnectionResult connectionResult) {
            final String value = String.valueOf(connectionResult);
            Log.d("AutoManageHelper", new StringBuilder(String.valueOf(value).length() + 27).append("beginFailureResolution for ").append(value).toString());
            zaj.this.zab(connectionResult, this.zacx);
        }
    }
}
