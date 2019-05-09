package com.applovin.impl.sdk;

import android.text.TextUtils;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinSdkUtils;
import com.tapjoy.TJAdUnitConstants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

class fy extends dv {
    /* renamed from: a */
    private final an f2542a;
    /* renamed from: b */
    private final AppLovinAdRewardListener f2543b;
    /* renamed from: h */
    private final Object f2544h = new Object();
    /* renamed from: i */
    private volatile boolean f2545i = false;

    public fy(an anVar, AppLovinAdRewardListener appLovinAdRewardListener, AppLovinSdkImpl appLovinSdkImpl) {
        super("TaskValidateReward", appLovinSdkImpl);
        this.f2542a = anVar;
        this.f2543b = appLovinAdRewardListener;
    }

    /* renamed from: a */
    private void m2895a(int i) {
        if (!m2901b()) {
            String str = "network_timeout";
            if (i < 400 || i > TJAdUnitConstants.DEFAULT_VOLUME_CHECK_INTERVAL) {
                this.f2543b.validationRequestFailed(this.f2542a, i);
            } else {
                this.f2543b.userRewardRejected(this.f2542a, new HashMap(0));
                str = "rejected";
            }
            dn.m2591a().m2593a(this.f2542a, str);
        }
    }

    /* renamed from: a */
    private void m2898a(String str, Map<String, String> map) {
        if (!m2901b()) {
            dn a = dn.m2591a();
            a.m2593a(this.f2542a, str);
            a.m2594a(this.f2542a, (Map) map);
            if (str.equals("accepted")) {
                this.f2543b.userRewardVerified(this.f2542a, map);
            } else if (str.equals("quota_exceeded")) {
                this.f2543b.userOverQuota(this.f2542a, map);
            } else if (str.equals("rejected")) {
                this.f2543b.userRewardRejected(this.f2542a, map);
            } else {
                this.f2543b.validationRequestFailed(this.f2542a, AppLovinErrorCodes.INCENTIVIZED_UNKNOWN_SERVER_ERROR);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    private void m2899a(org.json.JSONObject r5) {
        /*
        r4 = this;
        r0 = r4.m2901b();
        if (r0 == 0) goto L_0x0007;
    L_0x0006:
        return;
    L_0x0007:
        r2 = com.applovin.impl.sdk.ag.m2238a(r5);	 Catch:{ JSONException -> 0x0027 }
        r0 = r4.d;	 Catch:{ JSONException -> 0x0027 }
        com.applovin.impl.sdk.ag.m2240a(r2, r0);	 Catch:{ JSONException -> 0x0027 }
        r0 = "params";
        r0 = r2.get(r0);	 Catch:{ Throwable -> 0x0032 }
        r0 = (org.json.JSONObject) r0;	 Catch:{ Throwable -> 0x0032 }
        r0 = com.applovin.impl.sdk.bu.m2391a(r0);	 Catch:{ Throwable -> 0x0032 }
        r1 = r0;
    L_0x001d:
        r0 = "result";
        r0 = r2.getString(r0);	 Catch:{ Throwable -> 0x003b }
    L_0x0023:
        r4.m2898a(r0, r1);	 Catch:{ JSONException -> 0x0027 }
        goto L_0x0006;
    L_0x0027:
        r0 = move-exception;
        r1 = r4.e;
        r2 = r4.c;
        r3 = "Unable to parse API response";
        r1.mo4174e(r2, r3, r0);
        goto L_0x0006;
    L_0x0032:
        r0 = move-exception;
        r0 = new java.util.HashMap;	 Catch:{ JSONException -> 0x0027 }
        r1 = 0;
        r0.<init>(r1);	 Catch:{ JSONException -> 0x0027 }
        r1 = r0;
        goto L_0x001d;
    L_0x003b:
        r0 = move-exception;
        r0 = "network_timeout";
        goto L_0x0023;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.fy.a(org.json.JSONObject):void");
    }

    /* renamed from: a */
    public void m2900a(boolean z) {
        synchronized (this.f2544h) {
            this.f2545i = z;
        }
    }

    /* renamed from: b */
    boolean m2901b() {
        boolean z;
        synchronized (this.f2544h) {
            z = this.f2545i;
        }
        return z;
    }

    public void run() {
        CharSequence userIdentifier = this.d.getUserIdentifier();
        String n = this.f2542a.mo3996n();
        Map hashMap = new HashMap(3);
        hashMap.put("zone_id", this.f2542a.mo3997t().m3051a());
        if (AppLovinSdkUtils.isValidString(n)) {
            hashMap.put("clcode", n);
        } else {
            hashMap.put("clcode", "NO_CLCODE");
        }
        if (!TextUtils.isEmpty(userIdentifier)) {
            hashMap.put("user_id", userIdentifier);
        }
        m2630a("vr", new JSONObject(hashMap), new fz(this));
    }
}
