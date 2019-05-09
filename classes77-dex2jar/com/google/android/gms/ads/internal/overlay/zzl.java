// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal.overlay;

import com.google.android.gms.internal.ads.zzakk;
import android.app.Activity;
import com.google.android.gms.common.util.PlatformVersion;
import android.content.Intent;
import com.google.android.gms.ads.internal.zzbv;
import android.content.Context;
import com.google.android.gms.internal.ads.zzadh;

@zzadh
public final class zzl
{
    public static void zza(final Context context, final AdOverlayInfoParcel adOverlayInfoParcel, final boolean b) {
        if (adOverlayInfoParcel.zzbyu == 4 && adOverlayInfoParcel.zzbyn == null) {
            if (adOverlayInfoParcel.zzbym != null) {
                adOverlayInfoParcel.zzbym.onAdClicked();
            }
            zzbv.zzeh();
            zza.zza(context, adOverlayInfoParcel.zzbyl, adOverlayInfoParcel.zzbyt);
            return;
        }
        final Intent intent = new Intent();
        intent.setClassName(context, "com.google.android.gms.ads.AdActivity");
        intent.putExtra("com.google.android.gms.ads.internal.overlay.useClientJar", adOverlayInfoParcel.zzacr.zzcvg);
        intent.putExtra("shouldCallOnOverlayOpened", b);
        AdOverlayInfoParcel.zza(intent, adOverlayInfoParcel);
        if (!PlatformVersion.isAtLeastLollipop()) {
            intent.addFlags(524288);
        }
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
        }
        zzbv.zzek();
        zzakk.zza(context, intent);
    }
}
