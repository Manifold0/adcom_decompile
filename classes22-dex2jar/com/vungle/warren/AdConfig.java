// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren;

import java.lang.annotation.Annotation;
import java.util.Map;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;

public class AdConfig
{
    public static final int APK_DIRECT_DOWNLOAD = 32;
    public static final int AUTO_ROTATE = 16;
    public static final String FLAG_DIRECT_DOWNLOAD = "direct_download";
    public static final int IMMEDIATE_BACK = 2;
    public static final int IMMERSIVE = 4;
    public static final int MUTED = 1;
    public static final int TRANSITION_ANIMATE = 8;
    private int flexviewCloseSec;
    private int ordinal;
    private int settings;
    
    public int getFlexViewCloseTime() {
        return this.flexviewCloseSec;
    }
    
    public int getOrdinal() {
        return this.ordinal;
    }
    
    @RestrictTo({ RestrictTo$Scope.LIBRARY })
    public int getSettings() {
        return this.settings;
    }
    
    public void setAutoRotate(final boolean b) {
        if (b) {
            this.settings |= 0x10;
            return;
        }
        this.settings &= 0xFFFFFFEF;
    }
    
    public void setBackButtonImmediatelyEnabled(final boolean b) {
        if (b) {
            this.settings |= 0x2;
            return;
        }
        this.settings &= 0xFFFFFFFD;
    }
    
    public void setExtraSettings(final Map<String, Object> map) {
        if (map == null || map.isEmpty() || !map.containsKey("direct_download")) {
            return;
        }
        if (map.get("direct_download")) {
            this.settings |= 0x20;
            return;
        }
        this.settings &= 0xFFFFFFDF;
    }
    
    public void setFlexViewCloseTime(final int flexviewCloseSec) {
        this.flexviewCloseSec = flexviewCloseSec;
    }
    
    public void setImmersiveMode(final boolean b) {
        if (b) {
            this.settings |= 0x4;
            return;
        }
        this.settings &= 0xFFFFFFFB;
    }
    
    public void setMuted(final boolean b) {
        if (b) {
            this.settings |= 0x1;
            return;
        }
        this.settings &= 0xFFFFFFFE;
    }
    
    public void setOrdinal(final int ordinal) {
        this.ordinal = ordinal;
    }
    
    public void setTransitionAnimationEnabled(final boolean b) {
        if (b) {
            this.settings |= 0x8;
            return;
        }
        this.settings &= 0xFFFFFFF7;
    }
    
    public @interface Settings {
    }
}
