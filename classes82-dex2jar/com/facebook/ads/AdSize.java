// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads;

import com.facebook.ads.internal.protocol.d;
import java.io.Serializable;

public class AdSize implements Serializable
{
    @Deprecated
    public static final AdSize BANNER_320_50;
    public static final AdSize BANNER_HEIGHT_50;
    public static final AdSize BANNER_HEIGHT_90;
    public static final AdSize INTERSTITIAL;
    public static final AdSize RECTANGLE_HEIGHT_250;
    private final int a;
    private final int b;
    
    static {
        BANNER_320_50 = new AdSize(d.a);
        INTERSTITIAL = new AdSize(d.b);
        BANNER_HEIGHT_50 = new AdSize(d.c);
        BANNER_HEIGHT_90 = new AdSize(d.d);
        RECTANGLE_HEIGHT_250 = new AdSize(d.e);
    }
    
    public AdSize(final int a, final int b) {
        this.a = a;
        this.b = b;
    }
    
    private AdSize(final d d) {
        this.a = d.a();
        this.b = d.b();
    }
    
    public static AdSize fromWidthAndHeight(final int n, final int n2) {
        if (AdSize.INTERSTITIAL.b == n2 && AdSize.INTERSTITIAL.a == n) {
            return AdSize.INTERSTITIAL;
        }
        if (AdSize.BANNER_320_50.b == n2 && AdSize.BANNER_320_50.a == n) {
            return AdSize.BANNER_320_50;
        }
        if (AdSize.BANNER_HEIGHT_50.b == n2 && AdSize.BANNER_HEIGHT_50.a == n) {
            return AdSize.BANNER_HEIGHT_50;
        }
        if (AdSize.BANNER_HEIGHT_90.b == n2 && AdSize.BANNER_HEIGHT_90.a == n) {
            return AdSize.BANNER_HEIGHT_90;
        }
        if (AdSize.RECTANGLE_HEIGHT_250.b == n2 && AdSize.RECTANGLE_HEIGHT_250.a == n) {
            return AdSize.RECTANGLE_HEIGHT_250;
        }
        return null;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final AdSize adSize = (AdSize)o;
            if (this.a != adSize.a) {
                return false;
            }
            if (this.b != adSize.b) {
                return false;
            }
        }
        return true;
    }
    
    public int getHeight() {
        return this.b;
    }
    
    public int getWidth() {
        return this.a;
    }
    
    @Override
    public int hashCode() {
        return this.a * 31 + this.b;
    }
    
    public d toInternalAdSize() {
        return d.a(this.a, this.b);
    }
}
