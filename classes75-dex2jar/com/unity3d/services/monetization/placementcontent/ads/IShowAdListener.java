// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.monetization.placementcontent.ads;

import com.unity3d.ads.UnityAds;

public interface IShowAdListener
{
    void onAdFinished(final String p0, final UnityAds.FinishState p1);
    
    void onAdStarted(final String p0);
}
