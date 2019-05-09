// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.monetization.placementcontent.ads;

import android.app.Activity;
import com.unity3d.ads.UnityAds;
import java.util.HashMap;
import java.util.Map;
import com.unity3d.services.monetization.placementcontent.core.RewardablePlacementContent;

public class ShowAdPlacementContent extends RewardablePlacementContent
{
    private static Map<String, IShowAdListener> listenerMap;
    
    static {
        ShowAdPlacementContent.listenerMap = new HashMap<String, IShowAdListener>();
    }
    
    public ShowAdPlacementContent(final String s, final Map<String, Object> map) {
        super(s, map);
    }
    
    public static void sendAdFinished(final String s, final UnityAds.FinishState finishState) {
        final IShowAdListener showAdListener = ShowAdPlacementContent.listenerMap.remove(s);
        if (showAdListener != null) {
            showAdListener.onAdFinished(s, finishState);
        }
    }
    
    public static void sendAdStarted(final String s) {
        final IShowAdListener showAdListener = ShowAdPlacementContent.listenerMap.get(s);
        if (showAdListener != null) {
            showAdListener.onAdStarted(s);
        }
    }
    
    public void show(final Activity activity, final IShowAdListener showAdListener) {
        ShowAdPlacementContent.listenerMap.put(this.placementId, showAdListener);
        if (UnityAds.isReady(this.placementId)) {
            UnityAds.show(activity, this.placementId);
            return;
        }
        sendAdFinished(this.placementId, UnityAds.FinishState.ERROR);
    }
}
