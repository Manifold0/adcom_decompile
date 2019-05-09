package com.tapjoy.mraid.util;

import com.ironsource.sdk.constants.Constants.ParametersKeys;

public enum TransitionStringEnum {
    DEFAULT("default"),
    DISSOLVE("dissolve"),
    FADE("fade"),
    ROLL("roll"),
    SLIDE("slide"),
    ZOOM("zoom"),
    NONE(ParametersKeys.ORIENTATION_NONE);
    
    /* renamed from: a */
    private String f8313a;

    private TransitionStringEnum(String text) {
        this.f8313a = text;
    }

    public final String getText() {
        return this.f8313a;
    }

    public static TransitionStringEnum fromString(String text) {
        if (text != null) {
            for (TransitionStringEnum transitionStringEnum : values()) {
                if (text.equalsIgnoreCase(transitionStringEnum.f8313a)) {
                    return transitionStringEnum;
                }
            }
        }
        return null;
    }
}
