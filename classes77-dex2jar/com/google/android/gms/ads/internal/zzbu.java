// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import java.util.Iterator;
import android.os.Bundle;
import com.google.android.gms.internal.ads.zznk;
import com.google.android.gms.internal.ads.zzkb;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzjj;
import java.util.TreeMap;
import java.util.Map;

final class zzbu
{
    private final String zzabb;
    private final Map<String, String> zzabc;
    private String zzabd;
    private String zzabe;
    
    public zzbu(final String zzabb) {
        this.zzabb = zzabb;
        this.zzabc = new TreeMap<String, String>();
    }
    
    public final String getQuery() {
        return this.zzabd;
    }
    
    public final void zza(final zzjj zzjj, final zzang zzang) {
        this.zzabd = zzjj.zzaqd.zzatn;
        Bundle bundle;
        if (zzjj.zzaqg != null) {
            bundle = zzjj.zzaqg.getBundle(AdMobAdapter.class.getName());
        }
        else {
            bundle = null;
        }
        if (bundle == null) {
            return;
        }
        final String s = (String)zzkb.zzik().zzd(zznk.zzbda);
        for (final String s2 : bundle.keySet()) {
            if (s.equals(s2)) {
                this.zzabe = bundle.getString(s2);
            }
            else {
                if (!s2.startsWith("csa_")) {
                    continue;
                }
                this.zzabc.put(s2.substring(4), bundle.getString(s2));
            }
        }
        this.zzabc.put("SDKVersion", zzang.zzcw);
    }
    
    public final String zzec() {
        return this.zzabe;
    }
    
    public final String zzed() {
        return this.zzabb;
    }
    
    public final Map<String, String> zzee() {
        return this.zzabc;
    }
}
