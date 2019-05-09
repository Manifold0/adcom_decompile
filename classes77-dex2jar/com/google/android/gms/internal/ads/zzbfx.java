// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.support.customtabs.CustomTabsClient;
import android.content.ComponentName;
import java.lang.ref.WeakReference;
import android.support.customtabs.CustomTabsServiceConnection;

public final class zzbfx extends CustomTabsServiceConnection
{
    private WeakReference<zzbfy> zzedz;
    
    public zzbfx(final zzbfy zzbfy) {
        this.zzedz = new WeakReference<zzbfy>(zzbfy);
    }
    
    public final void onCustomTabsServiceConnected(final ComponentName componentName, final CustomTabsClient customTabsClient) {
        final zzbfy zzbfy = this.zzedz.get();
        if (zzbfy != null) {
            zzbfy.zza(customTabsClient);
        }
    }
    
    public final void onServiceDisconnected(final ComponentName componentName) {
        final zzbfy zzbfy = this.zzedz.get();
        if (zzbfy != null) {
            zzbfy.zzjo();
        }
    }
}
