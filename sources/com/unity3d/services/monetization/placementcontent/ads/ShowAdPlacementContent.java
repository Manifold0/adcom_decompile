package com.unity3d.services.monetization.placementcontent.ads;

import android.app.Activity;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.UnityAds.FinishState;
import com.unity3d.services.monetization.placementcontent.core.RewardablePlacementContent;
import java.util.HashMap;
import java.util.Map;

public class ShowAdPlacementContent extends RewardablePlacementContent {
    private static Map<String, IShowAdListener> listenerMap = new HashMap();

    public ShowAdPlacementContent(String placementId, Map<String, Object> params) {
        super(placementId, params);
    }

    public void show(Activity activity, IShowAdListener listener) {
        listenerMap.put(this.placementId, listener);
        if (UnityAds.isReady(this.placementId)) {
            UnityAds.show(activity, this.placementId);
        } else {
            sendAdFinished(this.placementId, FinishState.ERROR);
        }
    }

    public static void sendAdFinished(String placementId, FinishState finishState) {
        IShowAdListener listener = (IShowAdListener) listenerMap.remove(placementId);
        if (listener != null) {
            listener.onAdFinished(placementId, finishState);
        }
    }

    public static void sendAdStarted(String placementId) {
        IShowAdListener listener = (IShowAdListener) listenerMap.get(placementId);
        if (listener != null) {
            listener.onAdStarted(placementId);
        }
    }
}
