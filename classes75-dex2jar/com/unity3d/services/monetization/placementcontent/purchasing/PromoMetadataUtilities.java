// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.monetization.placementcontent.purchasing;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import com.unity3d.services.purchasing.core.Product;
import java.util.Map;

public class PromoMetadataUtilities
{
    private static Item createItemFromMap(final Map<String, Object> map) {
        final Item.Builder builder = Item.newBuilder();
        if (map.containsKey("itemId")) {
            builder.withItemId(map.get("itemId"));
        }
        if (map.containsKey("quantity")) {
            builder.withQuantity(((Number)map.get("quantity")).longValue());
        }
        if (map.containsKey("type")) {
            builder.withType(map.get("type"));
        }
        return builder.build();
    }
    
    private static Product createProductFromMap(final Map<String, Object> map) {
        final Product.Builder builder = Product.newBuilder();
        if (map.containsKey("productId")) {
            builder.withProductId(map.get("productId"));
        }
        if (map.containsKey("isoCurrencyCode")) {
            builder.withIsoCurrencyCode(map.get("isoCurrencyCode"));
        }
        if (map.containsKey("localizedPriceString")) {
            builder.withLocalizedPriceString(map.get("localizedPriceString"));
        }
        if (map.containsKey("localizedDescription")) {
            builder.withLocalizedDescription(map.get("localizedDescription"));
        }
        if (map.containsKey("localizedTitle")) {
            builder.withLocalizedTitle(map.get("localizedTitle"));
        }
        if (map.containsKey("localizedPrice")) {
            builder.withLocalizedPrice((double)map.get("localizedPrice"));
        }
        if (map.containsKey("productType")) {
            builder.withProductType(map.get("productType"));
        }
        return builder.build();
    }
    
    public static PromoMetadata createPromoMetadataFromParamsMap(final Map<String, Object> map) {
        final PromoMetadata.Builder builder = PromoMetadata.newBuilder();
        if (map.containsKey("impressionDate")) {
            builder.withImpressionDate(new Date(map.get("impressionDate")));
        }
        if (map.containsKey("offerDuration")) {
            builder.withOfferDuration(map.get("offerDuration"));
        }
        if (map.containsKey("costs")) {
            builder.withCosts(getItemListFromList((List<Map<String, Object>>)map.get("costs")));
        }
        if (map.containsKey("payouts")) {
            builder.withPayouts(getItemListFromList((List<Map<String, Object>>)map.get("payouts")));
        }
        if (map.containsKey("product")) {
            builder.withPremiumProduct(createProductFromMap((Map<String, Object>)map.get("product")));
        }
        if (map.containsKey("userInfo")) {
            builder.withCustomInfo((Map<String, Object>)map.get("userInfo"));
        }
        return builder.build();
    }
    
    private static List<Item> getItemListFromList(final List<Map<String, Object>> list) {
        final ArrayList<Item> list2 = new ArrayList<Item>(list.size());
        final Iterator<Map<String, Object>> iterator = list.iterator();
        while (iterator.hasNext()) {
            list2.add(createItemFromMap(iterator.next()));
        }
        return list2;
    }
}
