// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.monetization.core.placementcontent;

import com.unity3d.services.monetization.placementcontent.core.NoFillPlacementContent;
import com.unity3d.services.monetization.placementcontent.purchasing.PromoAdPlacementContent;
import com.unity3d.services.monetization.placementcontent.ads.ShowAdPlacementContent;
import com.unity3d.services.monetization.placementcontent.core.PlacementContent;
import java.util.Map;

public class PlacementContentResultFactory
{
    public static PlacementContent create(final String s, final Map<String, Object> map) {
        switch (PlacementContentResultType.parse(map.get("type"))) {
            default: {
                return new PlacementContent(s, map);
            }
            case SHOW_AD: {
                return new ShowAdPlacementContent(s, map);
            }
            case PROMO_AD: {
                return new PromoAdPlacementContent(s, map);
            }
            case NO_FILL: {
                return new NoFillPlacementContent(s, map);
            }
        }
    }
    
    public enum PlacementContentResultType
    {
        CUSTOM, 
        NO_FILL, 
        PROMO_AD, 
        SHOW_AD;
        
        static PlacementContentResultType parse(final String s) {
            if (s == null) {
                return PlacementContentResultType.CUSTOM;
            }
            try {
                return valueOf(s);
            }
            catch (IllegalArgumentException ex) {
                return PlacementContentResultType.CUSTOM;
            }
        }
    }
}
