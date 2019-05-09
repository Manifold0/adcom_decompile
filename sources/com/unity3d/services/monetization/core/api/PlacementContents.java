package com.unity3d.services.monetization.core.api;

import com.unity3d.ads.UnityAds.FinishState;
import com.unity3d.services.core.webview.bridge.WebViewCallback;
import com.unity3d.services.core.webview.bridge.WebViewExposed;
import com.unity3d.services.monetization.UnityMonetization.PlacementContentState;
import com.unity3d.services.monetization.core.placementcontent.PlacementContentResultFactory;
import com.unity3d.services.monetization.core.utilities.JSONUtilities;
import com.unity3d.services.monetization.placementcontent.ads.ShowAdPlacementContent;
import org.json.JSONObject;

public class PlacementContents {
    @WebViewExposed
    public static void createPlacementContent(String placementId, JSONObject params, WebViewCallback callback) {
        com.unity3d.services.monetization.core.placementcontent.PlacementContents.putPlacementContent(placementId, PlacementContentResultFactory.create(placementId, JSONUtilities.jsonObjectToMap(params)));
        callback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void setPlacementContentState(String placementId, String state, WebViewCallback callback) {
        com.unity3d.services.monetization.core.placementcontent.PlacementContents.setPlacementContentState(placementId, PlacementContentState.valueOf(state));
        callback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void sendAdFinished(String placementId, String finishState, WebViewCallback callback) {
        ShowAdPlacementContent.sendAdFinished(placementId, FinishState.valueOf(finishState));
        callback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void sendAdStarted(String placementId, WebViewCallback callback) {
        ShowAdPlacementContent.sendAdStarted(placementId);
        callback.invoke(new Object[0]);
    }
}
