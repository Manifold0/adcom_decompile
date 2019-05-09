// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Map;
import com.google.android.gms.ads.internal.gmsg.zzv;

final class zzafv implements zzv<Object>
{
    private final /* synthetic */ zzaft zzchv;
    
    zzafv(final zzaft zzchv) {
        this.zzchv = zzchv;
    }
    
    @Override
    public final void zza(final Object o, final Map<String, String> map) {
        final zzafz zzafz;
        synchronized (this.zzchv.mLock) {
            if (this.zzchv.zzchr.isDone()) {
                return;
            }
            zzafz = new zzafz(-2, map);
            if (!this.zzchv.zzchp.equals(zzafz.zzol())) {
                return;
            }
        }
        final String url = zzafz.getUrl();
        if (url == null) {
            zzakb.zzdk("URL missing in loadAdUrl GMSG.");
            // monitorexit(o2)
            return;
        }
        if (url.contains("%40mediation_adapters%40")) {
            final String replaceAll = url.replaceAll("%40mediation_adapters%40", zzajw.zzc(this.zzchv.mContext, map.get("check_adapters"), this.zzchv.zzchq));
            zzafz.setUrl(replaceAll);
            final String value = String.valueOf(replaceAll);
            String concat;
            if (value.length() != 0) {
                concat = "Ad request URL modified to ".concat(value);
            }
            else {
                concat = new String("Ad request URL modified to ");
            }
            zzakb.v(concat);
        }
        this.zzchv.zzchr.set(zzafz);
    }
    // monitorexit(o2)
}
