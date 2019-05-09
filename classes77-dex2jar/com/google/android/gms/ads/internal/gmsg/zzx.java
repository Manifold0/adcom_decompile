// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.ads.zzakb;
import org.json.JSONObject;

final class zzx implements Runnable
{
    private final /* synthetic */ JSONObject zzbmk;
    private final /* synthetic */ zzw zzbml;
    
    zzx(final zzw zzbml, final JSONObject zzbmk) {
        this.zzbml = zzbml;
        this.zzbmk = zzbmk;
    }
    
    @Override
    public final void run() {
        this.zzbml.zzbmi.zza("fetchHttpRequestCompleted", this.zzbmk);
        zzakb.zzck("Dispatched http response.");
    }
}
