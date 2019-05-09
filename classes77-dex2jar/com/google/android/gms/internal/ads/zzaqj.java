// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Map;
import java.util.HashMap;

final class zzaqj implements Runnable
{
    private final /* synthetic */ String zzcce;
    private final /* synthetic */ String zzdba;
    private final /* synthetic */ int zzdbc;
    private final /* synthetic */ zzaqh zzdbe;
    
    zzaqj(final zzaqh zzdbe, final String zzcce, final String zzdba, final int zzdbc) {
        this.zzdbe = zzdbe;
        this.zzcce = zzcce;
        this.zzdba = zzdba;
        this.zzdbc = zzdbc;
    }
    
    @Override
    public final void run() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("event", "precacheComplete");
        hashMap.put("src", this.zzcce);
        hashMap.put("cachedSrc", this.zzdba);
        hashMap.put("totalBytes", Integer.toString(this.zzdbc));
        this.zzdbe.zza("onPrecacheEvent", hashMap);
    }
}
