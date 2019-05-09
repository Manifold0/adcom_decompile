// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads;

import com.google.android.gms.internal.ads.zzmd;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.internal.ads.zzmb;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import android.content.Context;

public class MobileAds
{
    private MobileAds() {
    }
    
    public static RewardedVideoAd getRewardedVideoAdInstance(final Context context) {
        return zzmb.zziv().getRewardedVideoAdInstance(context);
    }
    
    public static void initialize(final Context context) {
        initialize(context, null, null);
    }
    
    @RequiresPermission("android.permission.INTERNET")
    public static void initialize(final Context context, final String s) {
        initialize(context, s, null);
    }
    
    @Deprecated
    @RequiresPermission("android.permission.INTERNET")
    public static void initialize(final Context context, final String s, final Settings settings) {
        final zzmb zziv = zzmb.zziv();
        zzmd zzbb;
        if (settings == null) {
            zzbb = null;
        }
        else {
            zzbb = settings.zzbb();
        }
        zziv.zza(context, s, zzbb);
    }
    
    public static void openDebugMenu(final Context context, final String s) {
        zzmb.zziv().openDebugMenu(context, s);
    }
    
    public static void setAppMuted(final boolean appMuted) {
        zzmb.zziv().setAppMuted(appMuted);
    }
    
    public static void setAppVolume(final float appVolume) {
        zzmb.zziv().setAppVolume(appVolume);
    }
    
    public static final class Settings
    {
        private final zzmd zzuw;
        
        public Settings() {
            this.zzuw = new zzmd();
        }
        
        @Deprecated
        public final String getTrackingId() {
            return null;
        }
        
        @Deprecated
        public final boolean isGoogleAnalyticsEnabled() {
            return false;
        }
        
        @Deprecated
        public final Settings setGoogleAnalyticsEnabled(final boolean b) {
            return this;
        }
        
        @Deprecated
        public final Settings setTrackingId(final String s) {
            return this;
        }
        
        final zzmd zzbb() {
            return this.zzuw;
        }
    }
}
