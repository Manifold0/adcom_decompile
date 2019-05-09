package com.unity3d.services.monetization.placementcontent.ads;

import com.unity3d.ads.UnityAds.FinishState;

public interface IShowAdListener {
    void onAdFinished(String str, FinishState finishState);

    void onAdStarted(String str);
}
