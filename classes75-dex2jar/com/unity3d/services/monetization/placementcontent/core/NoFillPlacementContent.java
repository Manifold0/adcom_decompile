// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.monetization.placementcontent.core;

import com.unity3d.services.monetization.UnityMonetization;
import java.util.Map;

public class NoFillPlacementContent extends PlacementContent
{
    public NoFillPlacementContent(final String s, final Map<String, Object> map) {
        super(s, map);
    }
    
    @Override
    protected String getDefaultEventCategory() {
        return "NO_FILL";
    }
    
    @Override
    public UnityMonetization.PlacementContentState getState() {
        return UnityMonetization.PlacementContentState.NO_FILL;
    }
    
    @Override
    public boolean isReady() {
        return false;
    }
    
    @Override
    public void sendCustomEvent(final CustomEvent customEvent) {
    }
}
