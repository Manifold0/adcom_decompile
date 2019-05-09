package com.ironsource.mediationsdk.utils;

import android.content.Context;
import android.text.TextUtils;
import com.ironsource.mediationsdk.model.BannerPlacement;
import com.ironsource.mediationsdk.model.InterstitialPlacement;
import com.ironsource.mediationsdk.model.Placement;
import com.ironsource.mediationsdk.model.PlacementAvailabilitySettings;
import com.ironsource.mediationsdk.model.PlacementCappingType;
import java.util.Calendar;
import java.util.TimeZone;

public class CappingManager {
    private static final String CAPPING_TIME_THRESHOLD = "CappingManager.CAPPING_TIME_THRESHOLD";
    private static final String CAPPING_TYPE = "CappingManager.CAPPING_TYPE";
    private static final String CURRENT_NUMBER_OF_SHOWS = "CappingManager.CURRENT_NUMBER_OF_SHOWS";
    private static final String IS_CAPPING_ENABLED = "CappingManager.IS_CAPPING_ENABLED";
    private static final String IS_DELIVERY_ENABLED = "CappingManager.IS_DELIVERY_ENABLED";
    private static final String IS_PACING_ENABLED = "CappingManager.IS_PACING_ENABLED";
    private static final String MAX_NUMBER_OF_SHOWS = "CappingManager.MAX_NUMBER_OF_SHOWS";
    private static final String SECONDS_BETWEEN_SHOWS = "CappingManager.SECONDS_BETWEEN_SHOWS";
    private static final String TIME_OF_THE_PREVIOUS_SHOW = "CappingManager.TIME_OF_THE_PREVIOUS_SHOW";

    public enum ECappingStatus {
        CAPPED_PER_DELIVERY,
        CAPPED_PER_COUNT,
        CAPPED_PER_PACE,
        NOT_CAPPED
    }

    public static synchronized void addCappingInfo(Context context, InterstitialPlacement placement) {
        synchronized (CappingManager.class) {
            if (!(context == null || placement == null)) {
                PlacementAvailabilitySettings availabilitySettings = placement.getPlacementAvailabilitySettings();
                if (availabilitySettings != null) {
                    addCappingInfo(context, "Interstitial", placement.getPlacementName(), availabilitySettings);
                }
            }
        }
    }

    public static synchronized void addCappingInfo(Context context, Placement placement) {
        synchronized (CappingManager.class) {
            if (!(context == null || placement == null)) {
                PlacementAvailabilitySettings availabilitySettings = placement.getPlacementAvailabilitySettings();
                if (availabilitySettings != null) {
                    addCappingInfo(context, IronSourceConstants.REWARDED_VIDEO_AD_UNIT, placement.getPlacementName(), availabilitySettings);
                }
            }
        }
    }

    public static synchronized void addCappingInfo(Context context, BannerPlacement placement) {
        synchronized (CappingManager.class) {
            if (!(context == null || placement == null)) {
                PlacementAvailabilitySettings availabilitySettings = placement.getPlacementAvailabilitySettings();
                if (availabilitySettings != null) {
                    addCappingInfo(context, IronSourceConstants.BANNER_AD_UNIT, placement.getPlacementName(), availabilitySettings);
                }
            }
        }
    }

    public static synchronized ECappingStatus isPlacementCapped(Context context, InterstitialPlacement placement) {
        ECappingStatus isPlacementCapped;
        synchronized (CappingManager.class) {
            if (!(context == null || placement == null)) {
                if (placement.getPlacementAvailabilitySettings() != null) {
                    isPlacementCapped = isPlacementCapped(context, "Interstitial", placement.getPlacementName());
                }
            }
            isPlacementCapped = ECappingStatus.NOT_CAPPED;
        }
        return isPlacementCapped;
    }

    public static synchronized boolean isBnPlacementCapped(Context context, String placementName) {
        boolean z;
        synchronized (CappingManager.class) {
            z = isPlacementCapped(context, IronSourceConstants.BANNER_AD_UNIT, placementName) != ECappingStatus.NOT_CAPPED;
        }
        return z;
    }

    public static synchronized ECappingStatus isPlacementCapped(Context context, Placement placement) {
        ECappingStatus isPlacementCapped;
        synchronized (CappingManager.class) {
            if (!(context == null || placement == null)) {
                if (placement.getPlacementAvailabilitySettings() != null) {
                    isPlacementCapped = isPlacementCapped(context, IronSourceConstants.REWARDED_VIDEO_AD_UNIT, placement.getPlacementName());
                }
            }
            isPlacementCapped = ECappingStatus.NOT_CAPPED;
        }
        return isPlacementCapped;
    }

    public static synchronized void incrementShowCounter(Context context, InterstitialPlacement placement) {
        synchronized (CappingManager.class) {
            if (placement != null) {
                incrementShowCounter(context, "Interstitial", placement.getPlacementName());
            }
        }
    }

    public static synchronized void incrementShowCounter(Context context, Placement placement) {
        synchronized (CappingManager.class) {
            if (placement != null) {
                incrementShowCounter(context, IronSourceConstants.REWARDED_VIDEO_AD_UNIT, placement.getPlacementName());
            }
        }
    }

    public static synchronized void incrementBnShowCounter(Context context, String placementName) {
        synchronized (CappingManager.class) {
            if (!TextUtils.isEmpty(placementName)) {
                incrementShowCounter(context, IronSourceConstants.BANNER_AD_UNIT, placementName);
            }
        }
    }

    private static String constructSharedPrefsKey(String adUnit, String baseConst, String placementName) {
        return adUnit + "_" + baseConst + "_" + placementName;
    }

    private static ECappingStatus isPlacementCapped(Context context, String adUnit, String placementName) {
        long currentTime = System.currentTimeMillis();
        if (!IronSourceUtils.getBooleanFromSharedPrefs(context, constructSharedPrefsKey(adUnit, IS_DELIVERY_ENABLED, placementName), true)) {
            return ECappingStatus.CAPPED_PER_DELIVERY;
        }
        if (IronSourceUtils.getBooleanFromSharedPrefs(context, constructSharedPrefsKey(adUnit, IS_PACING_ENABLED, placementName), false)) {
            if (currentTime - IronSourceUtils.getLongFromSharedPrefs(context, constructSharedPrefsKey(adUnit, TIME_OF_THE_PREVIOUS_SHOW, placementName), 0) < ((long) (IronSourceUtils.getIntFromSharedPrefs(context, constructSharedPrefsKey(adUnit, SECONDS_BETWEEN_SHOWS, placementName), 0) * 1000))) {
                return ECappingStatus.CAPPED_PER_PACE;
            }
        }
        if (IronSourceUtils.getBooleanFromSharedPrefs(context, constructSharedPrefsKey(adUnit, IS_CAPPING_ENABLED, placementName), false)) {
            int maxNumberOfShows = IronSourceUtils.getIntFromSharedPrefs(context, constructSharedPrefsKey(adUnit, MAX_NUMBER_OF_SHOWS, placementName), 0);
            String currentNumberOfShowsKey = constructSharedPrefsKey(adUnit, CURRENT_NUMBER_OF_SHOWS, placementName);
            int currentNumberOfShows = IronSourceUtils.getIntFromSharedPrefs(context, currentNumberOfShowsKey, 0);
            String timeThresholdKey = constructSharedPrefsKey(adUnit, CAPPING_TIME_THRESHOLD, placementName);
            if (currentTime >= IronSourceUtils.getLongFromSharedPrefs(context, timeThresholdKey, 0)) {
                IronSourceUtils.saveIntToSharedPrefs(context, currentNumberOfShowsKey, 0);
                IronSourceUtils.saveLongToSharedPrefs(context, timeThresholdKey, 0);
            } else if (currentNumberOfShows >= maxNumberOfShows) {
                return ECappingStatus.CAPPED_PER_COUNT;
            }
        }
        return ECappingStatus.NOT_CAPPED;
    }

    private static void incrementShowCounter(Context context, String adUnit, String placementName) {
        if (IronSourceUtils.getBooleanFromSharedPrefs(context, constructSharedPrefsKey(adUnit, IS_PACING_ENABLED, placementName), false)) {
            IronSourceUtils.saveLongToSharedPrefs(context, constructSharedPrefsKey(adUnit, TIME_OF_THE_PREVIOUS_SHOW, placementName), System.currentTimeMillis());
        }
        if (IronSourceUtils.getBooleanFromSharedPrefs(context, constructSharedPrefsKey(adUnit, IS_CAPPING_ENABLED, placementName), false)) {
            int maxNumberOfShows = IronSourceUtils.getIntFromSharedPrefs(context, constructSharedPrefsKey(adUnit, MAX_NUMBER_OF_SHOWS, placementName), 0);
            String currentNumberOfShowsKey = constructSharedPrefsKey(adUnit, CURRENT_NUMBER_OF_SHOWS, placementName);
            int currentNumberOfShows = IronSourceUtils.getIntFromSharedPrefs(context, currentNumberOfShowsKey, 0);
            if (currentNumberOfShows == 0) {
                String cappingTypeString = IronSourceUtils.getStringFromSharedPrefs(context, constructSharedPrefsKey(adUnit, CAPPING_TYPE, placementName), PlacementCappingType.PER_DAY.toString());
                PlacementCappingType cappingType = null;
                for (PlacementCappingType type : PlacementCappingType.values()) {
                    if (type.value.equals(cappingTypeString)) {
                        cappingType = type;
                        break;
                    }
                }
                IronSourceUtils.saveLongToSharedPrefs(context, constructSharedPrefsKey(adUnit, CAPPING_TIME_THRESHOLD, placementName), initTimeThreshold(cappingType));
            }
            IronSourceUtils.saveIntToSharedPrefs(context, currentNumberOfShowsKey, currentNumberOfShows + 1);
        }
    }

    private static long initTimeThreshold(PlacementCappingType cappingType) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        switch (cappingType) {
            case PER_DAY:
                calendar.set(14, 0);
                calendar.set(13, 0);
                calendar.set(12, 0);
                calendar.set(11, 0);
                calendar.add(6, 1);
                break;
            case PER_HOUR:
                calendar.set(14, 0);
                calendar.set(13, 0);
                calendar.set(12, 0);
                calendar.add(11, 1);
                break;
        }
        return calendar.getTimeInMillis();
    }

    private static void addCappingInfo(Context context, String adUnit, String placementName, PlacementAvailabilitySettings availabilitySettings) {
        boolean isDeliveryEnabled = availabilitySettings.isDeliveryEnabled();
        IronSourceUtils.saveBooleanToSharedPrefs(context, constructSharedPrefsKey(adUnit, IS_DELIVERY_ENABLED, placementName), isDeliveryEnabled);
        if (isDeliveryEnabled) {
            boolean isCappingEnabled = availabilitySettings.isCappingEnabled();
            IronSourceUtils.saveBooleanToSharedPrefs(context, constructSharedPrefsKey(adUnit, IS_CAPPING_ENABLED, placementName), isCappingEnabled);
            if (isCappingEnabled) {
                IronSourceUtils.saveIntToSharedPrefs(context, constructSharedPrefsKey(adUnit, MAX_NUMBER_OF_SHOWS, placementName), availabilitySettings.getCappingValue());
                IronSourceUtils.saveStringToSharedPrefs(context, constructSharedPrefsKey(adUnit, CAPPING_TYPE, placementName), availabilitySettings.getCappingType().toString());
            }
            boolean isPacingEnabled = availabilitySettings.isPacingEnabled();
            IronSourceUtils.saveBooleanToSharedPrefs(context, constructSharedPrefsKey(adUnit, IS_PACING_ENABLED, placementName), isPacingEnabled);
            if (isPacingEnabled) {
                IronSourceUtils.saveIntToSharedPrefs(context, constructSharedPrefsKey(adUnit, SECONDS_BETWEEN_SHOWS, placementName), availabilitySettings.getPacingValue());
            }
        }
    }
}
