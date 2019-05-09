// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.concurrent.Future;
import com.google.android.gms.ads.internal.zzbv;
import android.os.Build$VERSION;
import java.util.LinkedHashMap;
import java.util.Map;
import android.content.Context;

@zzadh
public final class zznm
{
    private Context mContext;
    private String zzaej;
    private String zzbfx;
    private Map<String, String> zzbfy;
    
    public zznm(final Context mContext, String zzaej) {
        this.mContext = null;
        this.zzaej = null;
        this.mContext = mContext;
        this.zzaej = zzaej;
        this.zzbfx = (String)zzkb.zzik().zzd(zznk.zzawi);
        (this.zzbfy = new LinkedHashMap<String, String>()).put("s", "gmob_sdk");
        this.zzbfy.put("v", "3");
        this.zzbfy.put("os", Build$VERSION.RELEASE);
        this.zzbfy.put("sdk", Build$VERSION.SDK);
        final Map<String, String> zzbfy = this.zzbfy;
        zzbv.zzek();
        zzbfy.put("device", zzakk.zzri());
        final Map<String, String> zzbfy2 = this.zzbfy;
        while (true) {
            while (true) {
                Label_0152: {
                    if (mContext.getApplicationContext() != null) {
                        zzaej = mContext.getApplicationContext().getPackageName();
                        break Label_0152;
                    }
                    Label_0264: {
                        break Label_0264;
                        while (true) {
                            final Map<String, String> zzbfy3;
                            String s = null;
                            zzbfy3.put("is_lite_sdk", s);
                            final Future<zzaga> zzq = zzbv.zzev().zzq(this.mContext);
                            try {
                                zzq.get();
                                this.zzbfy.put("network_coarse", Integer.toString(zzq.get().zzcjx));
                                this.zzbfy.put("network_fine", Integer.toString(zzq.get().zzcjy));
                                return;
                                zzaej = mContext.getPackageName();
                                break;
                                s = "0";
                            }
                            catch (Exception ex) {
                                zzbv.zzeo().zza(ex, "CsiConfiguration.CsiConfiguration");
                                return;
                            }
                        }
                    }
                }
                zzbfy2.put("app", zzaej);
                final Map<String, String> zzbfy3 = this.zzbfy;
                zzbv.zzek();
                if (zzakk.zzav(mContext)) {
                    final String s = "1";
                    continue;
                }
                break;
            }
            continue;
        }
    }
    
    final Context getContext() {
        return this.mContext;
    }
    
    final String zzfw() {
        return this.zzaej;
    }
    
    final String zzjd() {
        return this.zzbfx;
    }
    
    final Map<String, String> zzje() {
        return this.zzbfy;
    }
}
