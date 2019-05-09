// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Map;
import java.util.HashMap;

final class zzaqi implements Runnable
{
    private final /* synthetic */ String zzcce;
    private final /* synthetic */ String zzdba;
    private final /* synthetic */ int zzdbb;
    private final /* synthetic */ int zzdbc;
    private final /* synthetic */ boolean zzdbd;
    private final /* synthetic */ zzaqh zzdbe;
    
    zzaqi(final zzaqh zzdbe, final String zzcce, final String zzdba, final int zzdbb, final int zzdbc, final boolean b) {
        this.zzdbe = zzdbe;
        this.zzcce = zzcce;
        this.zzdba = zzdba;
        this.zzdbb = zzdbb;
        this.zzdbc = zzdbc;
        this.zzdbd = false;
    }
    
    @Override
    public final void run() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("event", "precacheProgress");
        hashMap.put("src", this.zzcce);
        hashMap.put("cachedSrc", this.zzdba);
        hashMap.put("bytesLoaded", Integer.toString(this.zzdbb));
        hashMap.put("totalBytes", Integer.toString(this.zzdbc));
        String s;
        if (this.zzdbd) {
            s = "1";
        }
        else {
            s = "0";
        }
        hashMap.put("cacheReady", s);
        this.zzdbe.zza("onPrecacheEvent", hashMap);
    }
}
