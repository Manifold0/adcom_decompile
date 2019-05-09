// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import com.google.android.gms.common.api.Api$ClientKey;
import com.google.android.gms.common.api.Api$ApiOptions$NoOptions;
import com.google.android.gms.common.api.Api$AbstractClientBuilder;

public final class zzg
{
    public static final Api$AbstractClientBuilder<zzf, Api$ApiOptions$NoOptions> CLIENT_BUILDER;
    public static final Api$ClientKey<zzf> CLIENT_KEY;
    
    static {
        CLIENT_KEY = new Api$ClientKey();
        CLIENT_BUILDER = new zzh();
    }
}
