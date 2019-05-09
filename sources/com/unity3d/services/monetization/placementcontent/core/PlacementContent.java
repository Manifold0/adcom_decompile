package com.unity3d.services.monetization.placementcontent.core;

import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.webview.WebViewApp;
import com.unity3d.services.monetization.UnityMonetization.PlacementContentState;
import com.unity3d.services.monetization.core.placementcontent.PlacementContentEvent;
import com.unity3d.services.monetization.core.webview.WebViewEventCategory;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class PlacementContent {
    private Map<String, Object> extras = new HashMap();
    protected String placementId;
    private PlacementContentState state;
    private String type;

    public PlacementContent(String placementId, Map<String, Object> params) {
        this.placementId = placementId;
        this.type = (String) params.get("type");
        this.extras.putAll(params);
    }

    public Object getExtra(String key) {
        return this.extras.get(key);
    }

    public Map<String, Object> getExtras() {
        return this.extras;
    }

    public String getType() {
        return this.type;
    }

    public void setState(PlacementContentState state) {
        this.state = state;
    }

    public void sendCustomEvent(CustomEvent customEvent) {
        if (customEvent.getCategory() == null) {
            customEvent.setCategory(getDefaultEventCategory());
        }
        if (WebViewApp.getCurrentApp() == null) {
            DeviceLog.warning("Could not send custom event due to app being null");
            return;
        }
        JSONObject eventDataMap = getJsonForCustomEvent(customEvent);
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.PLACEMENT_CONTENT, PlacementContentEvent.CUSTOM, this.placementId, eventDataMap);
    }

    private JSONObject getJsonForCustomEvent(CustomEvent customEvent) {
        JSONObject json = new JSONObject();
        try {
            json.put("category", customEvent.getCategory());
            json.put("type", customEvent.getType());
            json.put("data", customEvent.getData());
        } catch (JSONException e) {
            DeviceLog.warning("Error creating json for custom event: ", e.getMessage());
        }
        return json;
    }

    public boolean isReady() {
        return this.state == PlacementContentState.READY;
    }

    public void sendCustomEvent(String type, Map<String, Object> eventData) {
        sendCustomEvent(new CustomEvent(type, (Map) eventData));
    }

    public void sendCustomEvent(String category, String type, Map<String, Object> eventData) {
        sendCustomEvent(new CustomEvent(category, type, eventData));
    }

    public PlacementContentState getState() {
        return this.state;
    }

    protected String getDefaultEventCategory() {
        return "PLACEMENT_CONTENT";
    }
}
