package com.unity3d.services.monetization.core.placementcontent;

import com.unity3d.services.monetization.placementcontent.ads.ShowAdPlacementContent;
import com.unity3d.services.monetization.placementcontent.core.NoFillPlacementContent;
import com.unity3d.services.monetization.placementcontent.core.PlacementContent;
import com.unity3d.services.monetization.placementcontent.purchasing.PromoAdPlacementContent;
import java.util.Map;

public class PlacementContentResultFactory {

    public enum PlacementContentResultType {
        SHOW_AD,
        PROMO_AD,
        NO_FILL,
        CUSTOM;

        static PlacementContentResultType parse(String type) {
            if (type == null) {
                return CUSTOM;
            }
            try {
                return valueOf(type);
            } catch (IllegalArgumentException e) {
                return CUSTOM;
            }
        }
    }

    public static PlacementContent create(String placementId, Map<String, Object> params) {
        switch (PlacementContentResultType.parse((String) params.get("type"))) {
            case SHOW_AD:
                return new ShowAdPlacementContent(placementId, params);
            case PROMO_AD:
                return new PromoAdPlacementContent(placementId, params);
            case NO_FILL:
                return new NoFillPlacementContent(placementId, params);
            default:
                return new PlacementContent(placementId, params);
        }
    }
}
