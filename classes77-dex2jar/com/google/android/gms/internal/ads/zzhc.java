// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzhc
{
    private final Object zzajo;
    @GuardedBy("mPoolLock")
    private boolean zzajp;
    
    public zzhc() {
        this.zzajo = new Object();
        this.zzajp = false;
    }
}
