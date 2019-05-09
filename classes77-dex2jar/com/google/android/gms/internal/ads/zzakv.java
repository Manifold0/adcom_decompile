// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.net.http.SslError;
import android.annotation.TargetApi;

@TargetApi(14)
public class zzakv extends zzakt
{
    @Override
    public final String zza(final SslError sslError) {
        return sslError.getUrl();
    }
    
    @Override
    public int zzrq() {
        return 1;
    }
}
