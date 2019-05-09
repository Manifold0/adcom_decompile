// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.vng;

import java.util.HashMap;
import java.util.Map;

public class MoatAdEvent
{
    public static final Double VOLUME_MUTED;
    public static final Double VOLUME_UNMUTED;
    static final Integer a;
    private static final Double e;
    Integer b;
    Double c;
    MoatAdEventType d;
    private final Long f;
    
    static {
        a = Integer.MIN_VALUE;
        e = Double.NaN;
        VOLUME_MUTED = 0.0;
        VOLUME_UNMUTED = 1.0;
    }
    
    public MoatAdEvent(final MoatAdEventType moatAdEventType) {
        this(moatAdEventType, MoatAdEvent.a, MoatAdEvent.e);
    }
    
    public MoatAdEvent(final MoatAdEventType moatAdEventType, final Integer n) {
        this(moatAdEventType, n, MoatAdEvent.e);
    }
    
    public MoatAdEvent(final MoatAdEventType d, final Integer b, final Double c) {
        this.f = System.currentTimeMillis();
        this.d = d;
        this.c = c;
        this.b = b;
    }
    
    Map<String, Object> a() {
        final HashMap<String, Double> hashMap = (HashMap<String, Double>)new HashMap<String, String>();
        hashMap.put("adVolume", (String)this.c);
        hashMap.put("playhead", (String)this.b);
        hashMap.put("aTimeStamp", (String)this.f);
        hashMap.put("type", this.d.toString());
        return (Map<String, Object>)hashMap;
    }
}
