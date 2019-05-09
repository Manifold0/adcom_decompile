// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads;

import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzamu;
import com.google.android.gms.internal.ads.zzkb;
import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public final class AdSize
{
    public static final int AUTO_HEIGHT = -2;
    public static final AdSize BANNER;
    public static final AdSize FLUID;
    public static final AdSize FULL_BANNER;
    public static final int FULL_WIDTH = -1;
    public static final AdSize LARGE_BANNER;
    public static final AdSize LEADERBOARD;
    public static final AdSize MEDIUM_RECTANGLE;
    public static final AdSize SEARCH;
    public static final AdSize SMART_BANNER;
    public static final AdSize WIDE_SKYSCRAPER;
    public static final AdSize zzup;
    private final int zzuq;
    private final int zzur;
    private final String zzus;
    
    static {
        BANNER = new AdSize(320, 50, "320x50_mb");
        FULL_BANNER = new AdSize(468, 60, "468x60_as");
        LARGE_BANNER = new AdSize(320, 100, "320x100_as");
        LEADERBOARD = new AdSize(728, 90, "728x90_as");
        MEDIUM_RECTANGLE = new AdSize(300, 250, "300x250_as");
        WIDE_SKYSCRAPER = new AdSize(160, 600, "160x600_as");
        SMART_BANNER = new AdSize(-1, -2, "smart_banner");
        FLUID = new AdSize(-3, -4, "fluid");
        zzup = new AdSize(50, 50, "50x50_mb");
        SEARCH = new AdSize(-3, 0, "search_v2");
    }
    
    public AdSize(final int n, final int n2) {
        String value;
        if (n == -1) {
            value = "FULL";
        }
        else {
            value = String.valueOf(n);
        }
        String value2;
        if (n2 == -2) {
            value2 = "AUTO";
        }
        else {
            value2 = String.valueOf(n2);
        }
        this(n, n2, new StringBuilder(String.valueOf(value).length() + 4 + String.valueOf(value2).length()).append(value).append("x").append(value2).append("_as").toString());
    }
    
    AdSize(final int zzuq, final int zzur, final String zzus) {
        if (zzuq < 0 && zzuq != -1 && zzuq != -3) {
            throw new IllegalArgumentException(new StringBuilder(37).append("Invalid width for AdSize: ").append(zzuq).toString());
        }
        if (zzur < 0 && zzur != -2 && zzur != -4) {
            throw new IllegalArgumentException(new StringBuilder(38).append("Invalid height for AdSize: ").append(zzur).toString());
        }
        this.zzuq = zzuq;
        this.zzur = zzur;
        this.zzus = zzus;
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof AdSize)) {
                return false;
            }
            final AdSize adSize = (AdSize)o;
            if (this.zzuq != adSize.zzuq || this.zzur != adSize.zzur || !this.zzus.equals(adSize.zzus)) {
                return false;
            }
        }
        return true;
    }
    
    public final int getHeight() {
        return this.zzur;
    }
    
    public final int getHeightInPixels(final Context context) {
        switch (this.zzur) {
            default: {
                zzkb.zzif();
                return zzamu.zza(context, this.zzur);
            }
            case -2: {
                return zzjn.zzc(context.getResources().getDisplayMetrics());
            }
            case -4:
            case -3: {
                return -1;
            }
        }
    }
    
    public final int getWidth() {
        return this.zzuq;
    }
    
    public final int getWidthInPixels(final Context context) {
        switch (this.zzuq) {
            default: {
                zzkb.zzif();
                return zzamu.zza(context, this.zzuq);
            }
            case -1: {
                return zzjn.zzb(context.getResources().getDisplayMetrics());
            }
            case -4:
            case -3: {
                return -1;
            }
        }
    }
    
    @Override
    public final int hashCode() {
        return this.zzus.hashCode();
    }
    
    public final boolean isAutoHeight() {
        return this.zzur == -2;
    }
    
    public final boolean isFluid() {
        return this.zzuq == -3 && this.zzur == -4;
    }
    
    public final boolean isFullWidth() {
        return this.zzuq == -1;
    }
    
    @Override
    public final String toString() {
        return this.zzus;
    }
}
