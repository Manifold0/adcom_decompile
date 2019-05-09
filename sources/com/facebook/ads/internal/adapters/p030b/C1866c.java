package com.facebook.ads.internal.adapters.p030b;

import com.google.android.gms.games.GamesStatusCodes;
import java.io.Serializable;
import org.json.JSONObject;

/* renamed from: com.facebook.ads.internal.adapters.b.c */
public class C1866c implements Serializable {
    private static final long serialVersionUID = -1165646029762217510L;
    /* renamed from: a */
    private final int f3901a;
    /* renamed from: b */
    private final int f3902b;
    /* renamed from: c */
    private final int f3903c;
    /* renamed from: d */
    private final boolean f3904d;

    private C1866c(int i, int i2, int i3, boolean z) {
        this.f3901a = i;
        this.f3902b = i2;
        this.f3903c = i3;
        this.f3904d = z;
    }

    /* renamed from: a */
    public static C1866c m4214a(JSONObject jSONObject) {
        return new C1866c(jSONObject.optInt("countdown_time_ms", GamesStatusCodes.STATUS_MULTIPLAYER_ERROR_CREATION_NOT_ALLOWED), jSONObject.optInt("pulse_animation_duration_ms", 600), jSONObject.optInt("default_ad_index"), jSONObject.optBoolean("should_show_rating", false));
    }

    /* renamed from: a */
    public int m4215a() {
        return this.f3901a;
    }

    /* renamed from: b */
    public int m4216b() {
        return this.f3903c;
    }

    /* renamed from: c */
    public int m4217c() {
        return this.f3902b;
    }

    /* renamed from: d */
    public boolean m4218d() {
        return this.f3904d;
    }
}
