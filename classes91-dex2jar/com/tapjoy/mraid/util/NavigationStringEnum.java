// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.mraid.util;

public enum NavigationStringEnum
{
    BACK("BACK", 2, "back"), 
    CLOSE("CLOSE", 1, "close"), 
    FORWARD("FORWARD", 3, "forward"), 
    NONE("NONE", 0, "none"), 
    REFRESH("REFRESH", 4, "refresh");
    
    private String a;
    
    private NavigationStringEnum(final String s, final int n, final String a) {
        this.a = a;
    }
    
    public static NavigationStringEnum fromString(final String s) {
        if (s != null) {
            final NavigationStringEnum[] values = values();
            for (int length = values.length, i = 0; i < length; ++i) {
                final NavigationStringEnum navigationStringEnum = values[i];
                if (s.equalsIgnoreCase(navigationStringEnum.a)) {
                    return navigationStringEnum;
                }
            }
        }
        return null;
    }
    
    public final String getText() {
        return this.a;
    }
}
