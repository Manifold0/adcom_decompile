// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Map;
import android.text.TextUtils;
import java.util.HashMap;

final class zzaqk implements Runnable
{
    private final /* synthetic */ String val$message;
    private final /* synthetic */ String zzcce;
    private final /* synthetic */ String zzdba;
    private final /* synthetic */ zzaqh zzdbe;
    private final /* synthetic */ String zzdbf;
    
    zzaqk(final zzaqh zzdbe, final String zzcce, final String zzdba, final String zzdbf, final String val$message) {
        this.zzdbe = zzdbe;
        this.zzcce = zzcce;
        this.zzdba = zzdba;
        this.zzdbf = zzdbf;
        this.val$message = val$message;
    }
    
    @Override
    public final void run() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("event", "precacheCanceled");
        hashMap.put("src", this.zzcce);
        if (!TextUtils.isEmpty((CharSequence)this.zzdba)) {
            hashMap.put("cachedSrc", this.zzdba);
        }
        hashMap.put("type", zzaqh.zza(this.zzdbe, this.zzdbf));
        hashMap.put("reason", this.zzdbf);
        if (!TextUtils.isEmpty((CharSequence)this.val$message)) {
            hashMap.put("message", this.val$message);
        }
        this.zzdbe.zza("onPrecacheEvent", hashMap);
    }
}
