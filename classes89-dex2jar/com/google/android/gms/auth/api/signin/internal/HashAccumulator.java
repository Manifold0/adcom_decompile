// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.VisibleForTesting;

public class HashAccumulator
{
    @VisibleForTesting
    private static int zaah;
    private int zaai;
    
    static {
        HashAccumulator.zaah = 31;
    }
    
    public HashAccumulator() {
        this.zaai = 1;
    }
    
    @KeepForSdk
    public HashAccumulator addObject(final Object o) {
        final int zaah = HashAccumulator.zaah;
        final int zaai = this.zaai;
        int hashCode;
        if (o == null) {
            hashCode = 0;
        }
        else {
            hashCode = o.hashCode();
        }
        this.zaai = hashCode + zaai * zaah;
        return this;
    }
    
    @KeepForSdk
    public int hash() {
        return this.zaai;
    }
    
    public final HashAccumulator zaa(final boolean b) {
        final int zaah = HashAccumulator.zaah;
        final int zaai = this.zaai;
        int n;
        if (b) {
            n = 1;
        }
        else {
            n = 0;
        }
        this.zaai = n + zaai * zaah;
        return this;
    }
}
