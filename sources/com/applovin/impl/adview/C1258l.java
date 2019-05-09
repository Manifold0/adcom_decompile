package com.applovin.impl.adview;

import android.util.AttributeSet;
import com.applovin.adview.AppLovinAdView;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinSdkUtils;

/* renamed from: com.applovin.impl.adview.l */
class C1258l {
    /* renamed from: a */
    static AppLovinAdSize m2105a(AttributeSet attributeSet) {
        if (attributeSet == null) {
            return null;
        }
        String attributeValue = attributeSet.getAttributeValue(AppLovinAdView.NAMESPACE, "size");
        return AppLovinSdkUtils.isValidString(attributeValue) ? AppLovinAdSize.fromString(attributeValue) : null;
    }

    /* renamed from: b */
    static boolean m2106b(AttributeSet attributeSet) {
        return attributeSet != null && attributeSet.getAttributeBooleanValue(AppLovinAdView.NAMESPACE, "loadAdOnCreate", false);
    }
}
