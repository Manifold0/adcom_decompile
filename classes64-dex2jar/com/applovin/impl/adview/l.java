// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinSdkUtils;
import com.applovin.sdk.AppLovinAdSize;
import android.util.AttributeSet;

class l
{
    static AppLovinAdSize a(final AttributeSet set) {
        if (set != null) {
            final String attributeValue = set.getAttributeValue("http://schemas.applovin.com/android/1.0", "size");
            if (AppLovinSdkUtils.isValidString(attributeValue)) {
                return AppLovinAdSize.fromString(attributeValue);
            }
        }
        return null;
    }
    
    static boolean b(final AttributeSet set) {
        boolean b = false;
        if (set != null) {
            b = b;
            if (set.getAttributeBooleanValue("http://schemas.applovin.com/android/1.0", "loadAdOnCreate", false)) {
                b = true;
            }
        }
        return b;
    }
}
