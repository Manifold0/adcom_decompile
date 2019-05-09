// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.monetization.placementcontent.purchasing;

import java.util.Map;
import java.util.HashMap;
import com.unity3d.services.monetization.placementcontent.core.CustomEvent;

public class NativePromoAdapter
{
    private static final String EVENT_SHOWN_SHOW_TYPE = "showType";
    private static final String EVENT_TYPE_CLICKED = "clicked";
    private static final String EVENT_TYPE_CLOSED = "closed";
    private static final String EVENT_TYPE_SHOWN = "shown";
    private PromoMetadata promoMetadata;
    private PromoAdPlacementContent promoPlacementContent;
    
    public NativePromoAdapter(final PromoAdPlacementContent promoPlacementContent) {
        this.promoPlacementContent = promoPlacementContent;
        this.promoMetadata = promoPlacementContent.getMetadata();
    }
    
    public PromoMetadata getPromoMetadata() {
        return this.promoMetadata;
    }
    
    public void onClicked() {
        this.promoPlacementContent.sendCustomEvent(new CustomEvent("clicked"));
    }
    
    public void onClosed() {
        this.promoPlacementContent.sendCustomEvent(new CustomEvent("closed"));
    }
    
    public void onShown() {
        this.onShown(NativePromoShowType.FULL);
    }
    
    public void onShown(final NativePromoShowType nativePromoShowType) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("showType", nativePromoShowType.toString());
        this.promoPlacementContent.sendCustomEvent(new CustomEvent("shown", (Map<String, Object>)hashMap));
    }
}
