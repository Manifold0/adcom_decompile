// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import java.util.HashMap;

public final class zzeo
{
    private int statusCode;
    private HashMap<String, Integer> zzmu;
    
    public zzeo() {
        this.zzmu = new HashMap<String, Integer>();
        this.statusCode = 0;
    }
    
    public final zzem zzca() {
        return new zzem(this.statusCode, this.zzmu, null);
    }
    
    public final zzeo zzh(final String s, final int n) {
        int n2 = 0;
        switch (n) {
            default: {
                n2 = 0;
                break;
            }
            case 0:
            case 1:
            case 2:
            case 3: {
                n2 = 1;
                break;
            }
        }
        if (n2 != 0) {
            this.zzmu.put(s, n);
        }
        return this;
    }
    
    public final zzeo zzo(final int statusCode) {
        this.statusCode = statusCode;
        return this;
    }
}
