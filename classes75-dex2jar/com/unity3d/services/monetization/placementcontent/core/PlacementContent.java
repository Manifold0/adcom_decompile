// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.monetization.placementcontent.core;

import com.unity3d.services.monetization.core.placementcontent.PlacementContentEvent;
import com.unity3d.services.monetization.core.webview.WebViewEventCategory;
import com.unity3d.services.core.webview.WebViewApp;
import org.json.JSONException;
import com.unity3d.services.core.log.DeviceLog;
import org.json.JSONObject;
import java.util.HashMap;
import com.unity3d.services.monetization.UnityMonetization;
import java.util.Map;

public class PlacementContent
{
    private Map<String, Object> extras;
    protected String placementId;
    private UnityMonetization.PlacementContentState state;
    private String type;
    
    public PlacementContent(final String placementId, final Map<String, Object> map) {
        this.extras = new HashMap<String, Object>();
        this.placementId = placementId;
        this.type = map.get("type");
        this.extras.putAll(map);
    }
    
    private JSONObject getJsonForCustomEvent(final CustomEvent customEvent) {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("category", (Object)customEvent.getCategory());
            jsonObject.put("type", (Object)customEvent.getType());
            jsonObject.put("data", (Object)customEvent.getData());
            return jsonObject;
        }
        catch (JSONException ex) {
            DeviceLog.warning("Error creating json for custom event: ", ex.getMessage());
            return jsonObject;
        }
    }
    
    protected String getDefaultEventCategory() {
        return "PLACEMENT_CONTENT";
    }
    
    public Object getExtra(final String s) {
        return this.extras.get(s);
    }
    
    public Map<String, Object> getExtras() {
        return this.extras;
    }
    
    public UnityMonetization.PlacementContentState getState() {
        return this.state;
    }
    
    public String getType() {
        return this.type;
    }
    
    public boolean isReady() {
        return this.state == UnityMonetization.PlacementContentState.READY;
    }
    
    public void sendCustomEvent(final CustomEvent customEvent) {
        if (customEvent.getCategory() == null) {
            customEvent.setCategory(this.getDefaultEventCategory());
        }
        if (WebViewApp.getCurrentApp() == null) {
            DeviceLog.warning("Could not send custom event due to app being null");
            return;
        }
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.PLACEMENT_CONTENT, PlacementContentEvent.CUSTOM, this.placementId, this.getJsonForCustomEvent(customEvent));
    }
    
    public void sendCustomEvent(final String s, final String s2, final Map<String, Object> map) {
        this.sendCustomEvent(new CustomEvent(s, s2, map));
    }
    
    public void sendCustomEvent(final String s, final Map<String, Object> map) {
        this.sendCustomEvent(new CustomEvent(s, map));
    }
    
    public void setState(final UnityMonetization.PlacementContentState state) {
        this.state = state;
    }
}
