package com.tapjoy.mraid.util;

import com.ironsource.sdk.constants.Constants.ParametersKeys;
import com.tapjoy.TJAdUnitConstants.String;

public enum NavigationStringEnum {
    NONE(ParametersKeys.ORIENTATION_NONE),
    CLOSE(String.CLOSE),
    BACK("back"),
    FORWARD("forward"),
    REFRESH("refresh");
    
    /* renamed from: a */
    private String f8310a;

    private NavigationStringEnum(String text) {
        this.f8310a = text;
    }

    public final String getText() {
        return this.f8310a;
    }

    public static NavigationStringEnum fromString(String text) {
        if (text != null) {
            for (NavigationStringEnum navigationStringEnum : values()) {
                if (text.equalsIgnoreCase(navigationStringEnum.f8310a)) {
                    return navigationStringEnum;
                }
            }
        }
        return null;
    }
}
