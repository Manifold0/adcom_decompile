// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.identifier.AdvertisingIdClient$Info;
import android.content.Context;
import android.content.pm.PackageInfo;

@zzadh
public final class zzaje implements zzajg
{
    @Override
    public final zzanz<String> zza(final String s, final PackageInfo packageInfo) {
        return zzano.zzi(s);
    }
    
    @Override
    public final zzanz<AdvertisingIdClient$Info> zzae(final Context context) {
        final zzaoj<AdvertisingIdClient$Info> zzaoj = new zzaoj<AdvertisingIdClient$Info>();
        zzkb.zzif();
        if (zzamu.zzbh(context)) {
            zzaki.zzb(new zzajf(this, context, zzaoj));
        }
        return zzaoj;
    }
}
