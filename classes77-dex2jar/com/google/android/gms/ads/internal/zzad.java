// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzwf;
import com.google.android.gms.internal.ads.zzanm;
import com.google.android.gms.internal.ads.zzanj;
import com.google.android.gms.internal.ads.zzanz;
import com.google.android.gms.internal.ads.zzano;
import com.google.android.gms.internal.ads.zzaoe;
import com.google.android.gms.internal.ads.zzwh;
import org.json.JSONObject;
import com.google.android.gms.internal.ads.zzwi;
import com.google.android.gms.internal.ads.zzwk;
import android.text.TextUtils;
import com.google.android.gms.internal.ads.zznk;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzajl;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.ads.zzang;
import android.content.Context;
import javax.annotation.ParametersAreNonnullByDefault;
import com.google.android.gms.internal.ads.zzadh;

@zzadh
@ParametersAreNonnullByDefault
public final class zzad
{
    private Context mContext;
    private final Object mLock;
    private long zzxm;
    
    public zzad() {
        this.mLock = new Object();
        this.zzxm = 0L;
    }
    
    public final void zza(final Context context, final zzang zzang, final String s, @Nullable final Runnable runnable) {
        this.zza(context, zzang, true, null, s, null, runnable);
    }
    
    @VisibleForTesting
    final void zza(final Context context, final zzang zzang, final boolean b, @Nullable final zzajl zzajl, final String s, @Nullable final String s2, @Nullable final Runnable runnable) {
        if (zzbv.zzer().elapsedRealtime() - this.zzxm < 5000L) {
            zzakb.zzdk("Not retrying to fetch app settings");
        }
        else {
            this.zzxm = zzbv.zzer().elapsedRealtime();
            int n;
            if (zzajl == null) {
                n = 1;
            }
            else {
                boolean b2;
                if (zzbv.zzer().currentTimeMillis() - zzajl.zzps() > (long)zzkb.zzik().zzd(zznk.zzbcu)) {
                    b2 = true;
                }
                else {
                    b2 = false;
                }
                if (b2 || !zzajl.zzpt()) {
                    n = 1;
                }
                else {
                    n = 0;
                }
            }
            if (n != 0) {
                if (context == null) {
                    zzakb.zzdk("Context not provided to fetch application settings");
                    return;
                }
                if (TextUtils.isEmpty((CharSequence)s) && TextUtils.isEmpty((CharSequence)s2)) {
                    zzakb.zzdk("App settings could not be fetched. Required parameters missing");
                    return;
                }
                Context applicationContext = context.getApplicationContext();
                while (true) {
                    Label_0301: {
                        if (applicationContext == null) {
                            break Label_0301;
                        }
                        while (true) {
                            this.mContext = applicationContext;
                            final zzwf<JSONObject, JSONObject> zza = zzbv.zzey().zzb(this.mContext, zzang).zza("google.afma.config.fetchAppSettings", zzwk.zzbrc, zzwk.zzbrc);
                            while (true) {
                                JSONObject jsonObject = null;
                                Label_0307: {
                                    try {
                                        jsonObject = new JSONObject();
                                        if (!TextUtils.isEmpty((CharSequence)s)) {
                                            jsonObject.put("app_id", (Object)s);
                                            jsonObject.put("is_init", b);
                                            jsonObject.put("pn", (Object)context.getPackageName());
                                            final zzanz<JSONObject> zzf = zza.zzf(jsonObject);
                                            final zzanz<Object> zza2 = zzano.zza((zzanz<Object>)zzf, (zzanj<? super Object, ?>)zzae.zzxn, zzaoe.zzcvz);
                                            if (runnable != null) {
                                                zzf.zza(runnable, zzaoe.zzcvz);
                                            }
                                            zzanm.zza(zza2, "ConfigLoader.maybeFetchNewAppSettings");
                                            return;
                                        }
                                        break Label_0307;
                                    }
                                    catch (Exception ex) {
                                        zzakb.zzb("Error requesting application settings", (Throwable)ex);
                                        return;
                                    }
                                    break;
                                }
                                if (!TextUtils.isEmpty((CharSequence)s2)) {
                                    jsonObject.put("ad_unit_id", (Object)s2);
                                    continue;
                                }
                                continue;
                            }
                        }
                    }
                    applicationContext = context;
                    continue;
                }
            }
        }
    }
}
