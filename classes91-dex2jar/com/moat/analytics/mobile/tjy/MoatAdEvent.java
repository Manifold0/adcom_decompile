// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import java.util.HashMap;
import java.util.Map;

public class MoatAdEvent
{
    public static final String EVENT_AD_VOLUME = "adVolume";
    public static final String EVENT_PLAY_HEAD = "playhead";
    public static final String EVENT_TS = "aTimeStamp";
    public static final String EVENT_TYPE = "type";
    public static final Integer TIME_UNAVAILABLE;
    public static final Double VOLUME_UNAVAILABLE;
    private Long a;
    public Integer adPlayhead;
    public Double adVolume;
    public MoatAdEventType eventType;
    
    static {
        TIME_UNAVAILABLE = Integer.MIN_VALUE;
        VOLUME_UNAVAILABLE = Double.NaN;
    }
    
    public MoatAdEvent(final MoatAdEventType moatAdEventType) {
        this(moatAdEventType, MoatAdEvent.TIME_UNAVAILABLE, MoatAdEvent.VOLUME_UNAVAILABLE);
    }
    
    public MoatAdEvent(final MoatAdEventType moatAdEventType, final Integer n) {
        this(moatAdEventType, n, MoatAdEvent.VOLUME_UNAVAILABLE);
    }
    
    public MoatAdEvent(final MoatAdEventType eventType, final Integer adPlayhead, final Double adVolume) {
        this.a = System.currentTimeMillis();
        this.eventType = eventType;
        this.adVolume = adVolume;
        this.adPlayhead = adPlayhead;
    }
    
    public long getTimeStamp() {
        return this.a;
    }
    
    public Map toMap() {
        final HashMap<String, Double> hashMap = new HashMap<String, Double>();
        hashMap.put("adVolume", this.adVolume);
        hashMap.put("playhead", Double.valueOf(this.adPlayhead));
        hashMap.put("aTimeStamp", Double.valueOf(this.a));
        hashMap.put("type", (Double)this.eventType.toString());
        return hashMap;
    }
}
