// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.protocol;

public enum c
{
    a, 
    b, 
    c, 
    d, 
    e;
    
    public static c a(final e e) {
        switch (c$1.a[e.ordinal()]) {
            default: {
                return c.a;
            }
            case 1: {
                return c.d;
            }
            case 2:
            case 3:
            case 4:
            case 5: {
                return c.b;
            }
            case 6:
            case 7:
            case 8:
            case 9: {
                return c.c;
            }
            case 10: {
                return c.e;
            }
        }
    }
    
    public AdPlacementType a() {
        switch (c$1.b[this.ordinal()]) {
            default: {
                return AdPlacementType.UNKNOWN;
            }
            case 1: {
                return AdPlacementType.INTERSTITIAL;
            }
            case 2: {
                return AdPlacementType.BANNER;
            }
            case 3: {
                return AdPlacementType.NATIVE;
            }
            case 4: {
                return AdPlacementType.REWARDED_VIDEO;
            }
        }
    }
}
