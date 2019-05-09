// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.cha;

import java.util.HashMap;
import java.util.Map;

public class MoatAdEvent
{
    public static final Double VOLUME_MUTED;
    public static final Double VOLUME_UNMUTED;
    static final Integer \u02cb;
    private static final Double \u02ce;
    private final Long \u02bd;
    Double \u02ca;
    Integer \u02cf;
    MoatAdEventType \u0971;
    private final Double \u141d;
    
    static {
        \u02cb = Integer.MIN_VALUE;
        \u02ce = Double.NaN;
        VOLUME_MUTED = 0.0;
        VOLUME_UNMUTED = 1.0;
    }
    
    public MoatAdEvent(final MoatAdEventType moatAdEventType) {
        this(moatAdEventType, MoatAdEvent.\u02cb, MoatAdEvent.\u02ce);
    }
    
    public MoatAdEvent(final MoatAdEventType moatAdEventType, final Integer n) {
        this(moatAdEventType, n, MoatAdEvent.\u02ce);
    }
    
    public MoatAdEvent(final MoatAdEventType \u0971, final Integer \u02cf, final Double \u02ca) {
        this.\u02bd = System.currentTimeMillis();
        this.\u0971 = \u0971;
        this.\u02ca = \u02ca;
        this.\u02cf = \u02cf;
        this.\u141d = r.\u0971();
    }
    
    final Map<String, Object> \u02cf() {
        final HashMap<String, Double> hashMap = (HashMap<String, Double>)new HashMap<String, String>();
        hashMap.put("adVolume", (String)this.\u02ca);
        hashMap.put("playhead", (String)this.\u02cf);
        hashMap.put("aTimeStamp", (String)this.\u02bd);
        hashMap.put("type", this.\u0971.toString());
        hashMap.put("deviceVolume", this.\u141d);
        return (Map<String, Object>)hashMap;
    }
}
