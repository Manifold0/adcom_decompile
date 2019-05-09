// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.mraid.util;

public enum TransitionStringEnum
{
    DEFAULT("DEFAULT", 0, "default"), 
    DISSOLVE("DISSOLVE", 1, "dissolve"), 
    FADE("FADE", 2, "fade"), 
    NONE("NONE", 6, "none"), 
    ROLL("ROLL", 3, "roll"), 
    SLIDE("SLIDE", 4, "slide"), 
    ZOOM("ZOOM", 5, "zoom");
    
    private String a;
    
    private TransitionStringEnum(final String s, final int n, final String a) {
        this.a = a;
    }
    
    public static TransitionStringEnum fromString(final String s) {
        if (s != null) {
            final TransitionStringEnum[] values = values();
            for (int length = values.length, i = 0; i < length; ++i) {
                final TransitionStringEnum transitionStringEnum = values[i];
                if (s.equalsIgnoreCase(transitionStringEnum.a)) {
                    return transitionStringEnum;
                }
            }
        }
        return null;
    }
    
    public final String getText() {
        return this.a;
    }
}
