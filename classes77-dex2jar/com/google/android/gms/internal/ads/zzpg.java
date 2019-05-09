// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import java.util.Map;
import com.google.android.gms.ads.internal.gmsg.zzv;

final class zzpg implements zzv<Object>
{
    private final /* synthetic */ zzacm zzbji;
    final /* synthetic */ zzpf zzbjj;
    
    zzpg(final zzpf zzbjj, final zzacm zzbji) {
        this.zzbjj = zzbjj;
        this.zzbji = zzbji;
    }
    
    @Override
    public final void zza(final Object o, final Map<String, String> map) {
        final zzaqw zzaqw = (zzaqw)this.zzbjj.zzbjg.get();
        if (zzaqw == null) {
            this.zzbji.zzb("/loadHtml", this);
            return;
        }
        zzaqw.zzuf().zza(new zzph(this, map, this.zzbji));
        final String s = map.get("overlayHtml");
        final String s2 = map.get("baseUrl");
        if (TextUtils.isEmpty((CharSequence)s2)) {
            zzaqw.loadData(s, "text/html", "UTF-8");
            return;
        }
        zzaqw.loadDataWithBaseURL(s2, s, "text/html", "UTF-8", null);
    }
}
