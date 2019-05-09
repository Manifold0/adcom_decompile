package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class zaj extends zal {
    private final SparseArray<zaa> zacw = new SparseArray();

    private class zaa implements OnConnectionFailedListener {
        public final int zacx;
        public final GoogleApiClient zacy;
        public final OnConnectionFailedListener zacz;
        private final /* synthetic */ zaj zada;

        public zaa(zaj zaj, int i, GoogleApiClient googleApiClient, OnConnectionFailedListener onConnectionFailedListener) {
            this.zada = zaj;
            this.zacx = i;
            this.zacy = googleApiClient;
            this.zacz = onConnectionFailedListener;
            googleApiClient.registerConnectionFailedListener(this);
        }

        public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            String valueOf = String.valueOf(connectionResult);
            Log.d("AutoManageHelper", new StringBuilder(String.valueOf(valueOf).length() + 27).append("beginFailureResolution for ").append(valueOf).toString());
            this.zada.zab(connectionResult, this.zacx);
        }
    }

    public static zaj zaa(LifecycleActivity lifecycleActivity) {
        LifecycleFragment fragment = getFragment(lifecycleActivity);
        zaj zaj = (zaj) fragment.getCallbackOrNull("AutoManageHelper", zaj.class);
        return zaj != null ? zaj : new zaj(fragment);
    }

    private zaj(LifecycleFragment lifecycleFragment) {
        super(lifecycleFragment);
        this.mLifecycleFragment.addCallback("AutoManageHelper", this);
    }

    public final void zaa(int i, GoogleApiClient googleApiClient, OnConnectionFailedListener onConnectionFailedListener) {
        Preconditions.checkNotNull(googleApiClient, "GoogleApiClient instance cannot be null");
        Preconditions.checkState(this.zacw.indexOfKey(i) < 0, "Already managing a GoogleApiClient with id " + i);
        zam zam = (zam) this.zadf.get();
        boolean z = this.mStarted;
        String valueOf = String.valueOf(zam);
        Log.d("AutoManageHelper", new StringBuilder(String.valueOf(valueOf).length() + 49).append("starting AutoManage for client ").append(i).append(" ").append(z).append(" ").append(valueOf).toString());
        this.zacw.put(i, new zaa(this, i, googleApiClient, onConnectionFailedListener));
        if (this.mStarted && zam == null) {
            String valueOf2 = String.valueOf(googleApiClient);
            Log.d("AutoManageHelper", new StringBuilder(String.valueOf(valueOf2).length() + 11).append("connecting ").append(valueOf2).toString());
            googleApiClient.connect();
        }
    }

    public final void zaa(int i) {
        zaa zaa = (zaa) this.zacw.get(i);
        this.zacw.remove(i);
        if (zaa != null) {
            zaa.zacy.unregisterConnectionFailedListener(zaa);
            zaa.zacy.disconnect();
        }
    }

    public void onStart() {
        super.onStart();
        boolean z = this.mStarted;
        String valueOf = String.valueOf(this.zacw);
        Log.d("AutoManageHelper", new StringBuilder(String.valueOf(valueOf).length() + 14).append("onStart ").append(z).append(" ").append(valueOf).toString());
        if (this.zadf.get() == null) {
            for (int i = 0; i < this.zacw.size(); i++) {
                zaa zab = zab(i);
                if (zab != null) {
                    zab.zacy.connect();
                }
            }
        }
    }

    public void onStop() {
        super.onStop();
        for (int i = 0; i < this.zacw.size(); i++) {
            zaa zab = zab(i);
            if (zab != null) {
                zab.zacy.disconnect();
            }
        }
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        for (int i = 0; i < this.zacw.size(); i++) {
            zaa zab = zab(i);
            if (zab != null) {
                printWriter.append(str).append("GoogleApiClient #").print(zab.zacx);
                printWriter.println(":");
                zab.zacy.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
            }
        }
    }

    protected final void zaa(ConnectionResult connectionResult, int i) {
        Log.w("AutoManageHelper", "Unresolved error while connecting client. Stopping auto-manage.");
        if (i < 0) {
            Log.wtf("AutoManageHelper", "AutoManageLifecycleHelper received onErrorResolutionFailed callback but no failing client ID is set", new Exception());
            return;
        }
        zaa zaa = (zaa) this.zacw.get(i);
        if (zaa != null) {
            zaa(i);
            OnConnectionFailedListener onConnectionFailedListener = zaa.zacz;
            if (onConnectionFailedListener != null) {
                onConnectionFailedListener.onConnectionFailed(connectionResult);
            }
        }
    }

    protected final void zao() {
        for (int i = 0; i < this.zacw.size(); i++) {
            zaa zab = zab(i);
            if (zab != null) {
                zab.zacy.connect();
            }
        }
    }

    @Nullable
    private final zaa zab(int i) {
        if (this.zacw.size() <= i) {
            return null;
        }
        return (zaa) this.zacw.get(this.zacw.keyAt(i));
    }
}
