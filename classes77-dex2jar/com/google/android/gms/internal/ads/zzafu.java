// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Map;
import com.google.android.gms.ads.internal.gmsg.zzv;

final class zzafu implements zzv<Object>
{
    private final /* synthetic */ zzaft zzchv;
    
    zzafu(final zzaft zzchv) {
        this.zzchv = zzchv;
    }
    
    @Override
    public final void zza(final Object o, final Map<String, String> map) {
        synchronized (this.zzchv.mLock) {
            if (this.zzchv.zzchr.isDone()) {
                return;
            }
            if (!this.zzchv.zzchp.equals(map.get("request_id"))) {
                return;
            }
        }
        final Map<String, String> map2;
        final zzafz zzafz = new zzafz(1, map2);
        final String type = zzafz.getType();
        final String value = String.valueOf(zzafz.zzoh());
        zzakb.zzdk(new StringBuilder(String.valueOf(type).length() + 24 + String.valueOf(value).length()).append("Invalid ").append(type).append(" request error: ").append(value).toString());
        this.zzchv.zzchr.set(zzafz);
    }
    // monitorexit(o)
}
