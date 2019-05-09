// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.monetization.placementcontent.purchasing;

import java.util.Map;
import com.unity3d.services.monetization.placementcontent.ads.ShowAdPlacementContent;

public class PromoAdPlacementContent extends ShowAdPlacementContent
{
    private final PromoMetadata promoMetadata;
    
    public PromoAdPlacementContent(final String s, final Map<String, Object> map) {
        super(s, map);
        this.promoMetadata = PromoMetadataUtilities.createPromoMetadataFromParamsMap(map);
    }
    
    @Override
    protected String getDefaultEventCategory() {
        return "PROMO";
    }
    
    public PromoMetadata getMetadata() {
        return this.promoMetadata;
    }
}
