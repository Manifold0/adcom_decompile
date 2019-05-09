// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.ads.configuration;

import java.net.MalformedURLException;
import android.os.ConditionVariable;
import java.net.URL;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.misc.Utilities;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.core.configuration.Configuration;
import com.unity3d.services.ads.api.AdsProperties;
import com.unity3d.services.ads.api.Purchasing;
import com.unity3d.services.ads.api.WebPlayer;
import com.unity3d.services.ads.api.Placement;
import com.unity3d.services.ads.api.VideoPlayer;
import com.unity3d.services.ads.api.Listener;
import com.unity3d.services.ads.api.AdUnit;
import com.unity3d.services.ads.adunit.WebPlayerHandler;
import com.unity3d.services.ads.adunit.VideoPlayerHandler;
import com.unity3d.services.ads.adunit.WebViewHandler;
import java.util.HashMap;
import java.util.Map;
import java.net.InetAddress;

public class AdsModuleConfiguration implements IAdsModuleConfiguration
{
    private InetAddress _address;
    
    @Override
    public Map<String, Class> getAdUnitViewHandlers() {
        final HashMap<String, Class<VideoPlayerHandler>> hashMap = (HashMap<String, Class<VideoPlayerHandler>>)new HashMap<String, Class<WebViewHandler>>();
        hashMap.put("videoplayer", (Class<WebViewHandler>)VideoPlayerHandler.class);
        hashMap.put("webplayer", (Class<WebViewHandler>)WebPlayerHandler.class);
        hashMap.put("webview", WebViewHandler.class);
        return (Map<String, Class>)hashMap;
    }
    
    @Override
    public Class[] getWebAppApiClassList() {
        return new Class[] { AdUnit.class, Listener.class, VideoPlayer.class, Placement.class, WebPlayer.class, Purchasing.class, AdsProperties.class };
    }
    
    @Override
    public boolean initCompleteState(final Configuration configuration) {
        return true;
    }
    
    @Override
    public boolean initErrorState(final Configuration configuration, String string, final String s) {
        final IUnityAdsListener listener = UnityAds.getListener();
        string = "Init failed in " + string;
        if (com.unity3d.services.ads.properties.AdsProperties.getListener() != null) {
            Utilities.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    listener.onUnityAdsError(UnityAds.UnityAdsError.INITIALIZE_FAILED, string);
                }
            });
        }
        return true;
    }
    
    @Override
    public boolean initModuleState(final Configuration configuration) {
        final boolean b = true;
        DeviceLog.debug("Unity Ads init: checking for ad blockers");
        try {
            final String host = new URL(configuration.getConfigUrl()).getHost();
            final ConditionVariable conditionVariable = new ConditionVariable();
            new Thread() {
                @Override
                public void run() {
                    try {
                        AdsModuleConfiguration.this._address = InetAddress.getByName(host);
                        conditionVariable.open();
                    }
                    catch (Exception ex) {
                        DeviceLog.exception("Couldn't get address. Host: " + host, ex);
                        conditionVariable.open();
                    }
                }
            }.start();
            boolean b2 = b;
            if (conditionVariable.block(2000L)) {
                b2 = b;
                if (this._address != null) {
                    b2 = b;
                    if (this._address.isLoopbackAddress()) {
                        DeviceLog.error("Unity Ads init: halting init because Unity Ads config resolves to loopback address (due to ad blocker?)");
                        final IUnityAdsListener listener = com.unity3d.services.ads.properties.AdsProperties.getListener();
                        if (listener != null) {
                            Utilities.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    listener.onUnityAdsError(UnityAds.UnityAdsError.AD_BLOCKER_DETECTED, "Unity Ads config server resolves to loopback address (due to ad blocker?)");
                                }
                            });
                        }
                        b2 = false;
                    }
                }
            }
            return b2;
        }
        catch (MalformedURLException ex) {
            return true;
        }
    }
    
    @Override
    public boolean resetState(final Configuration configuration) {
        com.unity3d.services.ads.placement.Placement.reset();
        return true;
    }
}
