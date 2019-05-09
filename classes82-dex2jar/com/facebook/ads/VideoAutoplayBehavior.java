// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads;

import com.facebook.ads.internal.t.l;

public enum VideoAutoplayBehavior
{
    DEFAULT, 
    OFF, 
    ON;
    
    public static VideoAutoplayBehavior fromInternalAutoplayBehavior(final l l) {
        if (l == null) {
            return VideoAutoplayBehavior.DEFAULT;
        }
        switch (VideoAutoplayBehavior$1.a[l.ordinal()]) {
            default: {
                return VideoAutoplayBehavior.DEFAULT;
            }
            case 1: {
                return VideoAutoplayBehavior.DEFAULT;
            }
            case 2: {
                return VideoAutoplayBehavior.ON;
            }
            case 3: {
                return VideoAutoplayBehavior.OFF;
            }
        }
    }
}
