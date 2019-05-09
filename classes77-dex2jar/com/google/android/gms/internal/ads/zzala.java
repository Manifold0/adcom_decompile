// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;
import android.webkit.CookieManager;
import android.content.Context;
import android.webkit.WebResourceResponse;
import java.io.InputStream;
import java.util.Map;
import android.annotation.TargetApi;

@TargetApi(21)
public final class zzala extends zzakz
{
    @Override
    public final WebResourceResponse zza(final String s, final String s2, final int n, final String s3, final Map<String, String> map, final InputStream inputStream) {
        return new WebResourceResponse(s, s2, n, s3, (Map)map, inputStream);
    }
    
    @Override
    public final zzaqx zza(final zzaqw zzaqw, final boolean b) {
        return new zzarv(zzaqw, b);
    }
    
    @Override
    public final CookieManager zzax(final Context context) {
        if (zzrp()) {
            return null;
        }
        try {
            return CookieManager.getInstance();
        }
        catch (Throwable t) {
            zzakb.zzb("Failed to obtain CookieManager.", t);
            zzbv.zzeo().zza(t, "ApiLevelUtil.getCookieManager");
            return null;
        }
    }
    
    @Override
    public final int zzrq() {
        return 16974374;
    }
}
