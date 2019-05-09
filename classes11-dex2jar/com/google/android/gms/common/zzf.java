// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common;

import java.util.Arrays;

final class zzf extends zze
{
    private final byte[] zzu;
    
    zzf(final byte[] zzu) {
        super(Arrays.copyOfRange(zzu, 0, 25));
        this.zzu = zzu;
    }
    
    @Override
    final byte[] getBytes() {
        return this.zzu;
    }
}
