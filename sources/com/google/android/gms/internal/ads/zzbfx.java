package com.google.android.gms.internal.ads;

import android.content.ComponentName;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsServiceConnection;
import java.lang.ref.WeakReference;

public final class zzbfx extends CustomTabsServiceConnection {
    private WeakReference<zzbfy> zzedz;

    public zzbfx(zzbfy zzbfy) {
        this.zzedz = new WeakReference(zzbfy);
    }

    public final void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {
        zzbfy zzbfy = (zzbfy) this.zzedz.get();
        if (zzbfy != null) {
            zzbfy.zza(customTabsClient);
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        zzbfy zzbfy = (zzbfy) this.zzedz.get();
        if (zzbfy != null) {
            zzbfy.zzjo();
        }
    }
}
