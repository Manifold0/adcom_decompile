// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.ads.zzakk;
import org.json.JSONObject;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzue;
import java.util.Map;

final class zzw implements Runnable
{
    private final /* synthetic */ Map zzbmh;
    final /* synthetic */ zzue zzbmi;
    private final /* synthetic */ HttpClient zzbmj;
    
    zzw(final HttpClient zzbmj, final Map zzbmh, final zzue zzbmi) {
        this.zzbmj = zzbmj;
        this.zzbmh = zzbmh;
        this.zzbmi = zzbmi;
    }
    
    @Override
    public final void run() {
        zzakb.zzck("Received Http request.");
        final String s = this.zzbmh.get("http_request");
        JSONObject send;
        try {
            send = this.zzbmj.send(new JSONObject(s));
            if (send == null) {
                zzakb.e("Response should not be null.");
                return;
            }
        }
        catch (Exception ex) {
            zzakb.zzb("Error converting request to json.", (Throwable)ex);
            return;
        }
        zzakk.zzcrm.post((Runnable)new zzx(this, send));
    }
}
