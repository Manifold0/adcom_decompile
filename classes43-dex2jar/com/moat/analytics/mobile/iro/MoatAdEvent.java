// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.iro;

import java.util.HashMap;
import java.util.Map;

public class MoatAdEvent
{
    public static final Double VOLUME_MUTED;
    public static final Double VOLUME_UNMUTED;
    private static final Double \u02cb;
    static final Integer \u02cf;
    private final Long \u02bc;
    private final Double \u02bd;
    Integer \u02ca;
    Double \u02ce;
    MoatAdEventType \u0971;
    
    static {
        \u02cf = Integer.MIN_VALUE;
        \u02cb = Double.NaN;
        VOLUME_MUTED = 0.0;
        VOLUME_UNMUTED = 1.0;
    }
    
    public MoatAdEvent(final MoatAdEventType moatAdEventType) {
        this(moatAdEventType, MoatAdEvent.\u02cf, MoatAdEvent.\u02cb);
    }
    
    public MoatAdEvent(final MoatAdEventType moatAdEventType, final Integer n) {
        this(moatAdEventType, n, MoatAdEvent.\u02cb);
    }
    
    public MoatAdEvent(final MoatAdEventType \u0971, final Integer \u02ca, final Double \u02ce) {
        this.\u02bc = System.currentTimeMillis();
        this.\u0971 = \u0971;
        this.\u02ce = \u02ce;
        this.\u02ca = \u02ca;
        this.\u02bd = p.\u0971();
    }
    
    final Map<String, Object> \u0971() {
        final HashMap<String, Double> hashMap = (HashMap<String, Double>)new HashMap<String, String>();
        hashMap.put("adVolume", (String)this.\u02ce);
        hashMap.put("playhead", (String)this.\u02ca);
        hashMap.put("aTimeStamp", (String)this.\u02bc);
        hashMap.put("type", this.\u0971.toString());
        hashMap.put("deviceVolume", this.\u02bd);
        return (Map<String, Object>)hashMap;
    }
}
