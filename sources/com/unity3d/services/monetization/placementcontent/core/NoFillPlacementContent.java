package com.unity3d.services.monetization.placementcontent.core;

import com.unity3d.services.monetization.UnityMonetization.PlacementContentState;
import java.util.Map;

public class NoFillPlacementContent extends PlacementContent {
    public NoFillPlacementContent(String placementId, Map<String, Object> params) {
        super(placementId, params);
    }

    public boolean isReady() {
        return false;
    }

    public PlacementContentState getState() {
        return PlacementContentState.NO_FILL;
    }

    public void sendCustomEvent(CustomEvent customEvent) {
    }

    protected String getDefaultEventCategory() {
        return "NO_FILL";
    }
}
