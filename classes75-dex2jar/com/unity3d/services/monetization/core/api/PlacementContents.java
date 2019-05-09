// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.monetization.core.api;

import com.unity3d.services.monetization.UnityMonetization;
import com.unity3d.services.monetization.placementcontent.ads.ShowAdPlacementContent;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.core.webview.bridge.WebViewExposed;
import com.unity3d.services.monetization.core.placementcontent.PlacementContentResultFactory;
import com.unity3d.services.monetization.core.utilities.JSONUtilities;
import com.unity3d.services.core.webview.bridge.WebViewCallback;
import org.json.JSONObject;

public class PlacementContents
{
    @WebViewExposed
    public static void createPlacementContent(final String s, final JSONObject jsonObject, final WebViewCallback webViewCallback) {
        com.unity3d.services.monetization.core.placementcontent.PlacementContents.putPlacementContent(s, PlacementContentResultFactory.create(s, JSONUtilities.jsonObjectToMap(jsonObject)));
        webViewCallback.invoke(new Object[0]);
    }
    
    @WebViewExposed
    public static void sendAdFinished(final String s, final String s2, final WebViewCallback webViewCallback) {
        ShowAdPlacementContent.sendAdFinished(s, UnityAds.FinishState.valueOf(s2));
        webViewCallback.invoke(new Object[0]);
    }
    
    @WebViewExposed
    public static void sendAdStarted(final String s, final WebViewCallback webViewCallback) {
        ShowAdPlacementContent.sendAdStarted(s);
        webViewCallback.invoke(new Object[0]);
    }
    
    @WebViewExposed
    public static void setPlacementContentState(final String s, final String s2, final WebViewCallback webViewCallback) {
        com.unity3d.services.monetization.core.placementcontent.PlacementContents.setPlacementContentState(s, UnityMonetization.PlacementContentState.valueOf(s2));
        webViewCallback.invoke(new Object[0]);
    }
}
