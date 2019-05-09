package com.moat.analytics.mobile.iro;

import com.ironsource.sdk.constants.Constants.RequestParameters;
import java.util.HashMap;
import java.util.Map;

public class MoatAdEvent {
    public static final Double VOLUME_MUTED = Double.valueOf(0.0d);
    public static final Double VOLUME_UNMUTED = Double.valueOf(1.0d);
    /* renamed from: ˋ */
    private static final Double f1107 = Double.valueOf(Double.NaN);
    /* renamed from: ˏ */
    static final Integer f1108 = Integer.valueOf(Integer.MIN_VALUE);
    /* renamed from: ʼ */
    private final Long f1109;
    /* renamed from: ʽ */
    private final Double f1110;
    /* renamed from: ˊ */
    Integer f1111;
    /* renamed from: ˎ */
    Double f1112;
    /* renamed from: ॱ */
    MoatAdEventType f1113;

    public MoatAdEvent(MoatAdEventType moatAdEventType, Integer num, Double d) {
        this.f1109 = Long.valueOf(System.currentTimeMillis());
        this.f1113 = moatAdEventType;
        this.f1112 = d;
        this.f1111 = num;
        this.f1110 = Double.valueOf(C0789p.m1366());
    }

    public MoatAdEvent(MoatAdEventType moatAdEventType, Integer num) {
        this(moatAdEventType, num, f1107);
    }

    public MoatAdEvent(MoatAdEventType moatAdEventType) {
        this(moatAdEventType, f1108, f1107);
    }

    /* renamed from: ॱ */
    final Map<String, Object> m1218() {
        Map<String, Object> hashMap = new HashMap();
        hashMap.put(com.moat.analytics.mobile.tjy.MoatAdEvent.EVENT_AD_VOLUME, this.f1112);
        hashMap.put(com.moat.analytics.mobile.tjy.MoatAdEvent.EVENT_PLAY_HEAD, this.f1111);
        hashMap.put(com.moat.analytics.mobile.tjy.MoatAdEvent.EVENT_TS, this.f1109);
        hashMap.put("type", this.f1113.toString());
        hashMap.put(RequestParameters.DEVICE_VOLUME, this.f1110);
        return hashMap;
    }
}
