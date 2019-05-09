package com.applovin.impl.sdk;

import java.util.HashSet;

public class ef<T> {
    /* renamed from: a */
    static final ef<String> f2440a = new ef("com.applovin.sdk.impl.isFirstRun", String.class);
    /* renamed from: b */
    static final ef<HashSet> f2441b = new ef("com.applovin.sdk.impl.postbackQueue.key", HashSet.class);
    /* renamed from: c */
    static final ef<Integer> f2442c = new ef("com.applovin.sdk.last_version_code", Integer.class);
    /* renamed from: d */
    static final ef<String> f2443d = new ef("com.applovin.sdk.device_data", String.class);
    /* renamed from: e */
    static final ef<String> f2444e = new ef("com.applovin.sdk.zones", String.class);
    /* renamed from: f */
    static final ef<String> f2445f = new ef("com.applovin.sdk.loaded_mediation_adapters", String.class);
    /* renamed from: g */
    static final ef<Boolean> f2446g = new ef("com.applovin.sdk.compliance.has_user_consent", Boolean.class);
    /* renamed from: h */
    static final ef<Boolean> f2447h = new ef("com.applovin.sdk.compliance.is_age_restricted_user", Boolean.class);
    /* renamed from: i */
    static final ef<String> f2448i = new ef("com.applovin.sdk.stats", String.class);
    /* renamed from: j */
    static final ef<HashSet> f2449j = new ef("com.applovin.sdk.ad.stats", HashSet.class);
    /* renamed from: k */
    public static final ef<Integer> f2450k = new ef("com.applovin.sdk.last_video_position", Integer.class);
    /* renamed from: l */
    public static final ef<Boolean> f2451l = new ef("com.applovin.sdk.should_resume_video", Boolean.class);
    /* renamed from: m */
    private final String f2452m;
    /* renamed from: n */
    private final Class<T> f2453n;

    ef(String str, Class<T> cls) {
        this.f2452m = str;
        this.f2453n = cls;
    }

    /* renamed from: a */
    String m2726a() {
        return this.f2452m;
    }

    /* renamed from: b */
    Class<T> m2727b() {
        return this.f2453n;
    }

    public String toString() {
        return "Key{name='" + this.f2452m + '\'' + "type='" + this.f2453n + '\'' + '}';
    }
}
