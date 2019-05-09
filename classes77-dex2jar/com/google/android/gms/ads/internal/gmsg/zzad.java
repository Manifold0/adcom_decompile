// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal.gmsg;

import java.util.Map;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzcj;
import com.google.android.gms.ads.internal.zzbv;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.app.Activity;
import android.view.View;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.ads.internal.overlay.zzn;
import com.google.android.gms.ads.internal.overlay.zzt;
import com.google.android.gms.internal.ads.zzci;
import com.google.android.gms.internal.ads.zzaab;
import com.google.android.gms.ads.internal.zzx;
import com.google.android.gms.internal.ads.zzjd;
import android.content.Context;
import com.google.android.gms.internal.ads.zzadh;

@zzadh
public final class zzad<T extends zzarr> implements zzv<T>
{
    private final Context mContext;
    private final zzjd zzapt;
    private final zzb zzbll;
    private final zzd zzblm;
    private final zzx zzbmw;
    private final zzaab zzbmx;
    private final zzci zzbna;
    private final zzt zzbnb;
    private final zzn zzbnc;
    private final zzaqw zzbnd;
    private final zzang zzzw;
    
    public zzad(final Context mContext, final zzang zzzw, final zzci zzbna, final zzt zzbnb, final zzjd zzapt, final zzb zzbll, final zzd zzblm, final zzn zzbnc, final zzx zzbmw, final zzaab zzbmx) {
        this.zzbnd = null;
        this.mContext = mContext;
        this.zzzw = zzzw;
        this.zzbna = zzbna;
        this.zzbnb = zzbnb;
        this.zzapt = zzapt;
        this.zzbll = zzbll;
        this.zzblm = zzblm;
        this.zzbmw = zzbmw;
        this.zzbmx = zzbmx;
        this.zzbnc = zzbnc;
    }
    
    @VisibleForTesting
    static String zza(final Context context, final zzci zzci, final String s, final View view, @Nullable final Activity activity) {
        if (zzci == null) {
            return s;
        }
        try {
            Uri uri2;
            final Uri uri = uri2 = Uri.parse(s);
            if (zzci.zzc(uri)) {
                uri2 = zzci.zza(uri, context, view, activity);
            }
            return uri2.toString();
        }
        catch (Exception ex) {
            zzbv.zzeo().zza(ex, "OpenGmsgHandler.maybeAddClickSignalsToUrl");
            return s;
        }
        catch (zzcj zzcj) {
            return s;
        }
    }
    
    private static boolean zzg(final Map<String, String> map) {
        return "1".equals(map.get("custom_close"));
    }
    
    private static int zzh(final Map<String, String> map) {
        final String s = map.get("o");
        if (s != null) {
            if ("p".equalsIgnoreCase(s)) {
                return zzbv.zzem().zzrm();
            }
            if ("l".equalsIgnoreCase(s)) {
                return zzbv.zzem().zzrl();
            }
            if ("c".equalsIgnoreCase(s)) {
                return zzbv.zzem().zzrn();
            }
        }
        return -1;
    }
    
    private final void zzl(final boolean b) {
        if (this.zzbmx != null) {
            this.zzbmx.zzm(b);
        }
    }
}
