// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

public enum MoatAdEventType
{
    AD_EVT_COMPLETE("AD_EVT_COMPLETE", 3, "AdVideoComplete"), 
    AD_EVT_ENTER_FULLSCREEN("AD_EVT_ENTER_FULLSCREEN", 10, "fullScreen"), 
    AD_EVT_EXIT_FULLSCREEN("AD_EVT_EXIT_FULLSCREEN", 11, "exitFullscreen"), 
    AD_EVT_FIRST_QUARTILE("AD_EVT_FIRST_QUARTILE", 0, "AdVideoFirstQuartile"), 
    AD_EVT_MID_POINT("AD_EVT_MID_POINT", 1, "AdVideoMidpoint"), 
    AD_EVT_PAUSED("AD_EVT_PAUSED", 4, "AdPaused"), 
    AD_EVT_PLAYING("AD_EVT_PLAYING", 5, "AdPlaying"), 
    AD_EVT_SKIPPED("AD_EVT_SKIPPED", 8, "AdSkipped"), 
    AD_EVT_START("AD_EVT_START", 6, "AdVideoStart"), 
    AD_EVT_STOPPED("AD_EVT_STOPPED", 7, "AdStopped"), 
    AD_EVT_THIRD_QUARTILE("AD_EVT_THIRD_QUARTILE", 2, "AdVideoThirdQuartile"), 
    AD_EVT_VOLUME_CHANGE("AD_EVT_VOLUME_CHANGE", 9, "AdVolumeChange");
    
    private final String a;
    
    private MoatAdEventType(final String s, final int n, final String a) {
        this.a = a;
    }
    
    public static MoatAdEventType fromString(final String s) {
        if (s != null) {
            final MoatAdEventType[] values = values();
            for (int length = values.length, i = 0; i < length; ++i) {
                final MoatAdEventType moatAdEventType = values[i];
                if (s.equalsIgnoreCase(moatAdEventType.toString())) {
                    return moatAdEventType;
                }
            }
        }
        return null;
    }
    
    @Override
    public final String toString() {
        return this.a;
    }
}
