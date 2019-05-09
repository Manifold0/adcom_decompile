// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.monetization.core.placementcontent;

import com.unity3d.services.monetization.placementcontent.core.CustomEvent;
import com.unity3d.services.monetization.UnityMonetization;
import java.util.HashMap;
import java.util.Map;
import com.unity3d.services.monetization.placementcontent.core.PlacementContent;

public class PlacementContents
{
    private static final PlacementContent NOT_AVAILABLE;
    private static Map<String, PlacementContent> placementContentsMap;
    
    static {
        PlacementContents.placementContentsMap = new HashMap<String, PlacementContent>();
        NOT_AVAILABLE = getNotAvailablePlacementContent();
    }
    
    private static PlacementContent getNotAvailablePlacementContent() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("type", "NOT_AVAILABLE");
        return new NotAvailablePlacementContent("", (Map<String, Object>)hashMap);
    }
    
    public static PlacementContent getPlacementContent(final String s) {
        PlacementContent not_AVAILABLE;
        if ((not_AVAILABLE = PlacementContents.placementContentsMap.get(s)) == null) {
            not_AVAILABLE = PlacementContents.NOT_AVAILABLE;
        }
        return not_AVAILABLE;
    }
    
    public static <T extends PlacementContent> T getPlacementContent(final String s, final Class<T> clazz) {
        final PlacementContent placementContent = getPlacementContent(s);
        if (clazz.isInstance(placementContent)) {
            return clazz.cast(placementContent);
        }
        return null;
    }
    
    public static boolean isReady(final String s) {
        final PlacementContent placementContent = getPlacementContent(s);
        return placementContent != null && placementContent.isReady();
    }
    
    public static PlacementContent putPlacementContent(final String s, final PlacementContent placementContent) {
        return PlacementContents.placementContentsMap.put(s, placementContent);
    }
    
    public static void removePlacementContent(final String s) {
        PlacementContents.placementContentsMap.remove(s);
    }
    
    public static void setPlacementContentState(final String s, final UnityMonetization.PlacementContentState state) {
        final PlacementContent placementContent = getPlacementContent(s);
        if (placementContent != null) {
            placementContent.setState(state);
        }
    }
    
    private static final class NotAvailablePlacementContent extends PlacementContent
    {
        public NotAvailablePlacementContent(final String s, final Map<String, Object> map) {
            super(s, map);
        }
        
        @Override
        protected String getDefaultEventCategory() {
            return "NOT_AVAILABLE";
        }
        
        @Override
        public UnityMonetization.PlacementContentState getState() {
            return UnityMonetization.PlacementContentState.NOT_AVAILABLE;
        }
        
        @Override
        public boolean isReady() {
            return false;
        }
        
        @Override
        public void sendCustomEvent(final CustomEvent customEvent) {
        }
    }
}
