package com.moat.analytics.mobile.cha;

import com.ironsource.sdk.constants.Constants.RequestParameters;
import java.util.HashMap;
import java.util.Map;

public class MoatAdEvent {
    public static final Double VOLUME_MUTED = Double.valueOf(0.0d);
    public static final Double VOLUME_UNMUTED = Double.valueOf(1.0d);
    /* renamed from: ˋ */
    static final Integer f3413 = Integer.valueOf(Integer.MIN_VALUE);
    /* renamed from: ˎ */
    private static final Double f3414 = Double.valueOf(Double.NaN);
    /* renamed from: ʽ */
    private final Long f3415;
    /* renamed from: ˊ */
    Double f3416;
    /* renamed from: ˏ */
    Integer f3417;
    /* renamed from: ॱ */
    MoatAdEventType f3418;
    /* renamed from: ᐝ */
    private final Double f3419;

    public MoatAdEvent(MoatAdEventType moatAdEventType, Integer num, Double d) {
        this.f3415 = Long.valueOf(System.currentTimeMillis());
        this.f3418 = moatAdEventType;
        this.f3416 = d;
        this.f3417 = num;
        this.f3419 = Double.valueOf(C1526r.m3867());
    }

    public MoatAdEvent(MoatAdEventType moatAdEventType, Integer num) {
        this(moatAdEventType, num, f3414);
    }

    public MoatAdEvent(MoatAdEventType moatAdEventType) {
        this(moatAdEventType, f3413, f3414);
    }

    /* renamed from: ˏ */
    final Map<String, Object> m3708() {
        Map<String, Object> hashMap = new HashMap();
        hashMap.put(com.moat.analytics.mobile.tjy.MoatAdEvent.EVENT_AD_VOLUME, this.f3416);
        hashMap.put(com.moat.analytics.mobile.tjy.MoatAdEvent.EVENT_PLAY_HEAD, this.f3417);
        hashMap.put(com.moat.analytics.mobile.tjy.MoatAdEvent.EVENT_TS, this.f3415);
        hashMap.put("type", this.f3418.toString());
        hashMap.put(RequestParameters.DEVICE_VOLUME, this.f3419);
        return hashMap;
    }
}
