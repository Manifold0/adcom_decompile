package com.moat.analytics.mobile.vng;

import java.util.HashMap;
import java.util.Map;

public class MoatAdEvent {
    public static final Double VOLUME_MUTED = Double.valueOf(0.0d);
    public static final Double VOLUME_UNMUTED = Double.valueOf(1.0d);
    /* renamed from: a */
    static final Integer f1326a = Integer.valueOf(Integer.MIN_VALUE);
    /* renamed from: e */
    private static final Double f1327e = Double.valueOf(Double.NaN);
    /* renamed from: b */
    Integer f1328b;
    /* renamed from: c */
    Double f1329c;
    /* renamed from: d */
    MoatAdEventType f1330d;
    /* renamed from: f */
    private final Long f1331f;

    public MoatAdEvent(MoatAdEventType moatAdEventType) {
        this(moatAdEventType, f1326a, f1327e);
    }

    public MoatAdEvent(MoatAdEventType moatAdEventType, Integer num) {
        this(moatAdEventType, num, f1327e);
    }

    public MoatAdEvent(MoatAdEventType moatAdEventType, Integer num, Double d) {
        this.f1331f = Long.valueOf(System.currentTimeMillis());
        this.f1330d = moatAdEventType;
        this.f1329c = d;
        this.f1328b = num;
    }

    /* renamed from: a */
    Map<String, Object> m1420a() {
        Map<String, Object> hashMap = new HashMap();
        hashMap.put(com.moat.analytics.mobile.tjy.MoatAdEvent.EVENT_AD_VOLUME, this.f1329c);
        hashMap.put(com.moat.analytics.mobile.tjy.MoatAdEvent.EVENT_PLAY_HEAD, this.f1328b);
        hashMap.put(com.moat.analytics.mobile.tjy.MoatAdEvent.EVENT_TS, this.f1331f);
        hashMap.put("type", this.f1330d.toString());
        return hashMap;
    }
}
