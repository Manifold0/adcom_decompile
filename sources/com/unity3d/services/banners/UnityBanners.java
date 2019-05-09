package com.unity3d.services.banners;

import android.app.Activity;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.ads.placement.Placement;
import com.unity3d.services.ads.properties.AdsProperties;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.misc.Utilities;
import com.unity3d.services.core.properties.ClientProperties;

public final class UnityBanners {

    /* renamed from: com.unity3d.services.banners.UnityBanners$2 */
    static class C15962 implements Runnable {
        C15962() {
        }

        public void run() {
            try {
                if (!BannerHide.hide()) {
                    UnityBanners.sendError("Could not hide banner in time");
                }
            } catch (Exception e) {
                UnityBanners.sendError(e.getMessage());
            }
        }
    }

    public static void loadBanner(Activity activity) {
        loadBanner(activity, Placement.getDefaultBannerPlacement());
    }

    public static void loadBanner(final Activity activity, final String placementId) {
        DeviceLog.entered();
        if (!UnityAds.isSupported()) {
            sendError("Unity Ads is not supported on this device.");
        }
        if (!UnityAds.isInitialized()) {
            sendError("UnityAds is not initialized.");
        } else if (UnityAds.isReady(placementId)) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        ClientProperties.setActivity(activity);
                        if (!BannerShow.show(placementId)) {
                            UnityBanners.sendError("Could not show banner in time");
                        }
                    } catch (Exception e) {
                        UnityBanners.sendError(e.getMessage());
                    }
                }
            }).start();
        } else {
            sendError("Banner placement " + placementId + " is not ready");
        }
    }

    public static void destroy() {
        new Thread(new C15962()).start();
    }

    public static void setBannerListener(IUnityBannerListener listener) {
        AdsProperties.setBannerListener(listener);
    }

    public static IUnityBannerListener getBannerListener() {
        return AdsProperties.getBannerListener();
    }

    private static void sendError(final String message) {
        Utilities.runOnUiThread(new Runnable() {
            public void run() {
                IUnityBannerListener listener = UnityBanners.getBannerListener();
                if (listener != null) {
                    listener.onUnityBannerError(message);
                }
            }
        });
    }
}
