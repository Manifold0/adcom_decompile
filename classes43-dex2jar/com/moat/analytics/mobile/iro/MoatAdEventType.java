// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.iro;

public enum MoatAdEventType
{
    AD_EVT_COMPLETE("AdVideoComplete"), 
    AD_EVT_ENTER_FULLSCREEN("fullScreen"), 
    AD_EVT_EXIT_FULLSCREEN("exitFullscreen"), 
    AD_EVT_FIRST_QUARTILE("AdVideoFirstQuartile"), 
    AD_EVT_MID_POINT("AdVideoMidpoint"), 
    AD_EVT_PAUSED("AdPaused"), 
    AD_EVT_PLAYING("AdPlaying"), 
    AD_EVT_SKIPPED("AdSkipped"), 
    AD_EVT_START("AdVideoStart"), 
    AD_EVT_STOPPED("AdStopped"), 
    AD_EVT_THIRD_QUARTILE("AdVideoThirdQuartile"), 
    AD_EVT_VOLUME_CHANGE("AdVolumeChange");
    
    private final String \u02cb;
    
    private MoatAdEventType(final String \u02cb) {
        this.\u02cb = \u02cb;
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
        return this.\u02cb;
    }
}
