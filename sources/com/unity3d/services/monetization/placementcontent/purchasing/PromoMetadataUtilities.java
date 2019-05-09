package com.unity3d.services.monetization.placementcontent.purchasing;

import com.applovin.sdk.AppLovinEventTypes;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import com.kongregate.android.api.AnalyticsServices;
import com.unity3d.services.monetization.placementcontent.purchasing.PromoMetadata.Builder;
import com.unity3d.services.purchasing.core.Product;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class PromoMetadataUtilities {
    public static PromoMetadata createPromoMetadataFromParamsMap(Map<String, Object> params) {
        Builder builder = PromoMetadata.newBuilder();
        if (params.containsKey("impressionDate")) {
            builder.withImpressionDate(new Date(((Long) params.get("impressionDate")).longValue()));
        }
        if (params.containsKey("offerDuration")) {
            builder.withOfferDuration(((Long) params.get("offerDuration")).longValue());
        }
        if (params.containsKey("costs")) {
            builder.withCosts(getItemListFromList((List) params.get("costs")));
        }
        if (params.containsKey("payouts")) {
            builder.withPayouts(getItemListFromList((List) params.get("payouts")));
        }
        if (params.containsKey(AppLovinEventTypes.USER_VIEWED_PRODUCT)) {
            builder.withPremiumProduct(createProductFromMap((Map) params.get(AppLovinEventTypes.USER_VIEWED_PRODUCT)));
        }
        if (params.containsKey("userInfo")) {
            builder.withCustomInfo((Map) params.get("userInfo"));
        }
        return builder.build();
    }

    private static List<Item> getItemListFromList(List<Map<String, Object>> itemsList) {
        List<Item> items = new ArrayList(itemsList.size());
        for (Map<String, Object> itemMap : itemsList) {
            items.add(createItemFromMap(itemMap));
        }
        return items;
    }

    private static Item createItemFromMap(Map<String, Object> itemMap) {
        Item.Builder itemBuilder = Item.newBuilder();
        if (itemMap.containsKey("itemId")) {
            itemBuilder.withItemId((String) itemMap.get("itemId"));
        }
        if (itemMap.containsKey(AnalyticsServices.SWRVE_VIRTUAL_ECONOMY_PARAM_QUANTITY)) {
            itemBuilder.withQuantity(((Number) itemMap.get(AnalyticsServices.SWRVE_VIRTUAL_ECONOMY_PARAM_QUANTITY)).longValue());
        }
        if (itemMap.containsKey("type")) {
            itemBuilder.withType((String) itemMap.get("type"));
        }
        return itemBuilder.build();
    }

    private static Product createProductFromMap(Map<String, Object> productParams) {
        Product.Builder productBuilder = Product.newBuilder();
        if (productParams.containsKey("productId")) {
            productBuilder.withProductId((String) productParams.get("productId"));
        }
        if (productParams.containsKey("isoCurrencyCode")) {
            productBuilder.withIsoCurrencyCode((String) productParams.get("isoCurrencyCode"));
        }
        if (productParams.containsKey("localizedPriceString")) {
            productBuilder.withLocalizedPriceString((String) productParams.get("localizedPriceString"));
        }
        if (productParams.containsKey("localizedDescription")) {
            productBuilder.withLocalizedDescription((String) productParams.get("localizedDescription"));
        }
        if (productParams.containsKey("localizedTitle")) {
            productBuilder.withLocalizedTitle((String) productParams.get("localizedTitle"));
        }
        if (productParams.containsKey("localizedPrice")) {
            productBuilder.withLocalizedPrice(((Double) productParams.get("localizedPrice")).doubleValue());
        }
        if (productParams.containsKey(ParametersKeys.PRODUCT_TYPE)) {
            productBuilder.withProductType((String) productParams.get(ParametersKeys.PRODUCT_TYPE));
        }
        return productBuilder.build();
    }
}
