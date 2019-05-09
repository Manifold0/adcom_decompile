package com.unity3d.services.monetization.placementcontent.ads;

import com.unity3d.ads.UnityAds.FinishState;

public abstract class ShowAdListenerAdapter implements IShowAdListener {
    public void onAdFinished(String placementId, FinishState withState) {
    }

    public void onAdStarted(String placementId) {
    }
}
