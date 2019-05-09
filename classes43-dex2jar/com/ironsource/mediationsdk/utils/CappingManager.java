// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.utils;

import java.util.Calendar;
import java.util.TimeZone;
import com.ironsource.mediationsdk.model.PlacementCappingType;
import android.text.TextUtils;
import com.ironsource.mediationsdk.model.Placement;
import com.ironsource.mediationsdk.model.InterstitialPlacement;
import com.ironsource.mediationsdk.model.PlacementAvailabilitySettings;
import com.ironsource.mediationsdk.model.BannerPlacement;
import android.content.Context;

public class CappingManager
{
    private static final String CAPPING_TIME_THRESHOLD = "CappingManager.CAPPING_TIME_THRESHOLD";
    private static final String CAPPING_TYPE = "CappingManager.CAPPING_TYPE";
    private static final String CURRENT_NUMBER_OF_SHOWS = "CappingManager.CURRENT_NUMBER_OF_SHOWS";
    private static final String IS_CAPPING_ENABLED = "CappingManager.IS_CAPPING_ENABLED";
    private static final String IS_DELIVERY_ENABLED = "CappingManager.IS_DELIVERY_ENABLED";
    private static final String IS_PACING_ENABLED = "CappingManager.IS_PACING_ENABLED";
    private static final String MAX_NUMBER_OF_SHOWS = "CappingManager.MAX_NUMBER_OF_SHOWS";
    private static final String SECONDS_BETWEEN_SHOWS = "CappingManager.SECONDS_BETWEEN_SHOWS";
    private static final String TIME_OF_THE_PREVIOUS_SHOW = "CappingManager.TIME_OF_THE_PREVIOUS_SHOW";
    
    public static void addCappingInfo(final Context context, final BannerPlacement bannerPlacement) {
        // monitorenter(CappingManager.class)
        if (context != null && bannerPlacement != null) {
            try {
                final PlacementAvailabilitySettings placementAvailabilitySettings = bannerPlacement.getPlacementAvailabilitySettings();
                if (placementAvailabilitySettings != null) {
                    addCappingInfo(context, "Banner", bannerPlacement.getPlacementName(), placementAvailabilitySettings);
                }
            }
            finally {
            }
            // monitorexit(CappingManager.class)
        }
    }
    // monitorexit(CappingManager.class)
    
    public static void addCappingInfo(final Context context, final InterstitialPlacement interstitialPlacement) {
        // monitorenter(CappingManager.class)
        if (context != null && interstitialPlacement != null) {
            try {
                final PlacementAvailabilitySettings placementAvailabilitySettings = interstitialPlacement.getPlacementAvailabilitySettings();
                if (placementAvailabilitySettings != null) {
                    addCappingInfo(context, "Interstitial", interstitialPlacement.getPlacementName(), placementAvailabilitySettings);
                }
            }
            finally {
            }
            // monitorexit(CappingManager.class)
        }
    }
    // monitorexit(CappingManager.class)
    
    public static void addCappingInfo(final Context context, final Placement placement) {
        // monitorenter(CappingManager.class)
        if (context != null && placement != null) {
            try {
                final PlacementAvailabilitySettings placementAvailabilitySettings = placement.getPlacementAvailabilitySettings();
                if (placementAvailabilitySettings != null) {
                    addCappingInfo(context, "Rewarded Video", placement.getPlacementName(), placementAvailabilitySettings);
                }
            }
            finally {
            }
            // monitorexit(CappingManager.class)
        }
    }
    // monitorexit(CappingManager.class)
    
    private static void addCappingInfo(final Context context, final String s, final String s2, final PlacementAvailabilitySettings placementAvailabilitySettings) {
        final boolean deliveryEnabled = placementAvailabilitySettings.isDeliveryEnabled();
        IronSourceUtils.saveBooleanToSharedPrefs(context, constructSharedPrefsKey(s, "CappingManager.IS_DELIVERY_ENABLED", s2), deliveryEnabled);
        if (deliveryEnabled) {
            final boolean cappingEnabled = placementAvailabilitySettings.isCappingEnabled();
            IronSourceUtils.saveBooleanToSharedPrefs(context, constructSharedPrefsKey(s, "CappingManager.IS_CAPPING_ENABLED", s2), cappingEnabled);
            if (cappingEnabled) {
                IronSourceUtils.saveIntToSharedPrefs(context, constructSharedPrefsKey(s, "CappingManager.MAX_NUMBER_OF_SHOWS", s2), placementAvailabilitySettings.getCappingValue());
                IronSourceUtils.saveStringToSharedPrefs(context, constructSharedPrefsKey(s, "CappingManager.CAPPING_TYPE", s2), placementAvailabilitySettings.getCappingType().toString());
            }
            final boolean pacingEnabled = placementAvailabilitySettings.isPacingEnabled();
            IronSourceUtils.saveBooleanToSharedPrefs(context, constructSharedPrefsKey(s, "CappingManager.IS_PACING_ENABLED", s2), pacingEnabled);
            if (pacingEnabled) {
                IronSourceUtils.saveIntToSharedPrefs(context, constructSharedPrefsKey(s, "CappingManager.SECONDS_BETWEEN_SHOWS", s2), placementAvailabilitySettings.getPacingValue());
            }
        }
    }
    
    private static String constructSharedPrefsKey(final String s, final String s2, final String s3) {
        return s + "_" + s2 + "_" + s3;
    }
    
    public static void incrementBnShowCounter(final Context context, final String s) {
        synchronized (CappingManager.class) {
            if (!TextUtils.isEmpty((CharSequence)s)) {
                incrementShowCounter(context, "Banner", s);
            }
        }
    }
    
    public static void incrementShowCounter(final Context context, final InterstitialPlacement interstitialPlacement) {
        // monitorenter(CappingManager.class)
        if (interstitialPlacement == null) {
            return;
        }
        try {
            incrementShowCounter(context, "Interstitial", interstitialPlacement.getPlacementName());
        }
        finally {
        }
        // monitorexit(CappingManager.class)
    }
    
    public static void incrementShowCounter(final Context context, final Placement placement) {
        // monitorenter(CappingManager.class)
        if (placement == null) {
            return;
        }
        try {
            incrementShowCounter(context, "Rewarded Video", placement.getPlacementName());
        }
        finally {
        }
        // monitorexit(CappingManager.class)
    }
    
    private static void incrementShowCounter(final Context context, final String s, final String s2) {
        if (IronSourceUtils.getBooleanFromSharedPrefs(context, constructSharedPrefsKey(s, "CappingManager.IS_PACING_ENABLED", s2), false)) {
            IronSourceUtils.saveLongToSharedPrefs(context, constructSharedPrefsKey(s, "CappingManager.TIME_OF_THE_PREVIOUS_SHOW", s2), System.currentTimeMillis());
        }
        if (IronSourceUtils.getBooleanFromSharedPrefs(context, constructSharedPrefsKey(s, "CappingManager.IS_CAPPING_ENABLED", s2), false)) {
            IronSourceUtils.getIntFromSharedPrefs(context, constructSharedPrefsKey(s, "CappingManager.MAX_NUMBER_OF_SHOWS", s2), 0);
            final String constructSharedPrefsKey = constructSharedPrefsKey(s, "CappingManager.CURRENT_NUMBER_OF_SHOWS", s2);
            final int intFromSharedPrefs = IronSourceUtils.getIntFromSharedPrefs(context, constructSharedPrefsKey, 0);
            int n;
            if (intFromSharedPrefs == 0) {
                n = 1;
            }
            else {
                n = 0;
            }
            if (n != 0) {
                final String stringFromSharedPrefs = IronSourceUtils.getStringFromSharedPrefs(context, constructSharedPrefsKey(s, "CappingManager.CAPPING_TYPE", s2), PlacementCappingType.PER_DAY.toString());
                final PlacementCappingType placementCappingType = null;
                final PlacementCappingType[] values = PlacementCappingType.values();
                final int length = values.length;
                int n2 = 0;
                PlacementCappingType placementCappingType2;
                while (true) {
                    placementCappingType2 = placementCappingType;
                    if (n2 >= length) {
                        break;
                    }
                    placementCappingType2 = values[n2];
                    if (placementCappingType2.value.equals(stringFromSharedPrefs)) {
                        break;
                    }
                    ++n2;
                }
                IronSourceUtils.saveLongToSharedPrefs(context, constructSharedPrefsKey(s, "CappingManager.CAPPING_TIME_THRESHOLD", s2), initTimeThreshold(placementCappingType2));
            }
            IronSourceUtils.saveIntToSharedPrefs(context, constructSharedPrefsKey, intFromSharedPrefs + 1);
        }
    }
    
    private static long initTimeThreshold(final PlacementCappingType placementCappingType) {
        final Calendar instance = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        switch (placementCappingType) {
            case PER_DAY: {
                instance.set(14, 0);
                instance.set(13, 0);
                instance.set(12, 0);
                instance.set(11, 0);
                instance.add(6, 1);
                break;
            }
            case PER_HOUR: {
                instance.set(14, 0);
                instance.set(13, 0);
                instance.set(12, 0);
                instance.add(11, 1);
                break;
            }
        }
        return instance.getTimeInMillis();
    }
    
    public static boolean isBnPlacementCapped(final Context context, final String s) {
        synchronized (CappingManager.class) {
            return isPlacementCapped(context, "Banner", s) != ECappingStatus.NOT_CAPPED;
        }
    }
    
    public static ECappingStatus isPlacementCapped(final Context context, final InterstitialPlacement interstitialPlacement) {
        // monitorenter(CappingManager.class)
        Label_0018: {
            if (context == null || interstitialPlacement == null) {
                break Label_0018;
            }
            try {
                ECappingStatus eCappingStatus;
                if (interstitialPlacement.getPlacementAvailabilitySettings() == null) {
                    eCappingStatus = ECappingStatus.NOT_CAPPED;
                }
                else {
                    eCappingStatus = isPlacementCapped(context, "Interstitial", interstitialPlacement.getPlacementName());
                }
                return eCappingStatus;
            }
            finally {
            }
            // monitorexit(CappingManager.class)
        }
    }
    
    public static ECappingStatus isPlacementCapped(final Context context, final Placement placement) {
        // monitorenter(CappingManager.class)
        Label_0018: {
            if (context == null || placement == null) {
                break Label_0018;
            }
            try {
                ECappingStatus eCappingStatus;
                if (placement.getPlacementAvailabilitySettings() == null) {
                    eCappingStatus = ECappingStatus.NOT_CAPPED;
                }
                else {
                    eCappingStatus = isPlacementCapped(context, "Rewarded Video", placement.getPlacementName());
                }
                return eCappingStatus;
            }
            finally {
            }
            // monitorexit(CappingManager.class)
        }
    }
    
    private static ECappingStatus isPlacementCapped(final Context context, String constructSharedPrefsKey, final String s) {
        final long currentTimeMillis = System.currentTimeMillis();
        if (!IronSourceUtils.getBooleanFromSharedPrefs(context, constructSharedPrefsKey(constructSharedPrefsKey, "CappingManager.IS_DELIVERY_ENABLED", s), true)) {
            return ECappingStatus.CAPPED_PER_DELIVERY;
        }
        if (IronSourceUtils.getBooleanFromSharedPrefs(context, constructSharedPrefsKey(constructSharedPrefsKey, "CappingManager.IS_PACING_ENABLED", s), false) && currentTimeMillis - IronSourceUtils.getLongFromSharedPrefs(context, constructSharedPrefsKey(constructSharedPrefsKey, "CappingManager.TIME_OF_THE_PREVIOUS_SHOW", s), 0L) < IronSourceUtils.getIntFromSharedPrefs(context, constructSharedPrefsKey(constructSharedPrefsKey, "CappingManager.SECONDS_BETWEEN_SHOWS", s), 0) * 1000) {
            return ECappingStatus.CAPPED_PER_PACE;
        }
        if (IronSourceUtils.getBooleanFromSharedPrefs(context, constructSharedPrefsKey(constructSharedPrefsKey, "CappingManager.IS_CAPPING_ENABLED", s), false)) {
            final int intFromSharedPrefs = IronSourceUtils.getIntFromSharedPrefs(context, constructSharedPrefsKey(constructSharedPrefsKey, "CappingManager.MAX_NUMBER_OF_SHOWS", s), 0);
            final String constructSharedPrefsKey2 = constructSharedPrefsKey(constructSharedPrefsKey, "CappingManager.CURRENT_NUMBER_OF_SHOWS", s);
            final int intFromSharedPrefs2 = IronSourceUtils.getIntFromSharedPrefs(context, constructSharedPrefsKey2, 0);
            constructSharedPrefsKey = constructSharedPrefsKey(constructSharedPrefsKey, "CappingManager.CAPPING_TIME_THRESHOLD", s);
            if (currentTimeMillis >= IronSourceUtils.getLongFromSharedPrefs(context, constructSharedPrefsKey, 0L)) {
                IronSourceUtils.saveIntToSharedPrefs(context, constructSharedPrefsKey2, 0);
                IronSourceUtils.saveLongToSharedPrefs(context, constructSharedPrefsKey, 0L);
            }
            else if (intFromSharedPrefs2 >= intFromSharedPrefs) {
                return ECappingStatus.CAPPED_PER_COUNT;
            }
        }
        return ECappingStatus.NOT_CAPPED;
    }
    
    public enum ECappingStatus
    {
        CAPPED_PER_COUNT, 
        CAPPED_PER_DELIVERY, 
        CAPPED_PER_PACE, 
        NOT_CAPPED;
    }
}
