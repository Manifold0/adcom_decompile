package com.chartboost.sdk.Model;

import android.os.Build;
import android.os.Build.VERSION;
import com.chartboost.sdk.C1410i;
import com.chartboost.sdk.Chartboost.CBPIDataUseConsent;
import com.chartboost.sdk.Libraries.C1372b;
import com.chartboost.sdk.impl.C1450o;
import com.chartboost.sdk.impl.C1454s;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import com.kongregate.p000o.p003a.C0578a;
import com.tapjoy.TJAdUnitConstants.String;
import com.vungle.warren.network.VungleApiClient;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.chartboost.sdk.Model.e */
public class C1390e {
    /* renamed from: A */
    public final boolean f2781A;
    /* renamed from: B */
    public final int f2782B;
    /* renamed from: C */
    public final boolean f2783C;
    /* renamed from: D */
    public final int f2784D;
    /* renamed from: E */
    public final boolean f2785E;
    /* renamed from: F */
    public final String f2786F;
    /* renamed from: G */
    public final String f2787G;
    /* renamed from: H */
    public final String f2788H;
    /* renamed from: I */
    public final String f2789I;
    /* renamed from: J */
    public final boolean f2790J;
    /* renamed from: K */
    public final boolean f2791K;
    /* renamed from: L */
    public final boolean f2792L;
    /* renamed from: a */
    public final String f2793a;
    /* renamed from: b */
    public final boolean f2794b;
    /* renamed from: c */
    public final boolean f2795c;
    /* renamed from: d */
    public final List<String> f2796d;
    /* renamed from: e */
    public final boolean f2797e;
    /* renamed from: f */
    public final boolean f2798f;
    /* renamed from: g */
    public final boolean f2799g;
    /* renamed from: h */
    public final boolean f2800h;
    /* renamed from: i */
    public final int f2801i;
    /* renamed from: j */
    public final boolean f2802j;
    /* renamed from: k */
    public final boolean f2803k;
    /* renamed from: l */
    public final boolean f2804l;
    /* renamed from: m */
    public final boolean f2805m;
    /* renamed from: n */
    public final boolean f2806n;
    /* renamed from: o */
    public final boolean f2807o;
    /* renamed from: p */
    public final boolean f2808p;
    /* renamed from: q */
    public final boolean f2809q;
    /* renamed from: r */
    public final boolean f2810r;
    /* renamed from: s */
    public final long f2811s;
    /* renamed from: t */
    public final int f2812t;
    /* renamed from: u */
    public final int f2813u;
    /* renamed from: v */
    public final int f2814v;
    /* renamed from: w */
    public final int f2815w;
    /* renamed from: x */
    public final List<String> f2816x;
    /* renamed from: y */
    public final boolean f2817y;
    /* renamed from: z */
    public final boolean f2818z;

    public C1390e(JSONObject jSONObject) {
        boolean z;
        boolean z2 = false;
        boolean z3 = true;
        this.f2793a = jSONObject.optString("configVariant");
        this.f2794b = jSONObject.optBoolean("prefetchDisable");
        this.f2795c = jSONObject.optBoolean("publisherDisable");
        List arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("invalidateFolderList");
        if (optJSONArray != null) {
            int length = optJSONArray.length();
            for (int i = 0; i < length; i++) {
                String optString = optJSONArray.optString(i);
                if (!optString.isEmpty()) {
                    arrayList.add(optString);
                }
            }
        }
        this.f2796d = Collections.unmodifiableList(arrayList);
        JSONObject optJSONObject = jSONObject.optJSONObject("native");
        if (optJSONObject == null) {
            optJSONObject = new JSONObject();
        }
        this.f2797e = optJSONObject.optBoolean(String.ENABLED, true);
        this.f2798f = optJSONObject.optBoolean("inplayEnabled", true);
        this.f2799g = optJSONObject.optBoolean("interstitialEnabled", true);
        this.f2800h = optJSONObject.optBoolean("lockOrientation");
        this.f2801i = optJSONObject.optInt("prefetchSession", 3);
        this.f2802j = optJSONObject.optBoolean("rewardVideoEnabled", true);
        optJSONObject = jSONObject.optJSONObject("trackingLevels");
        if (optJSONObject == null) {
            optJSONObject = new JSONObject();
        }
        this.f2803k = optJSONObject.optBoolean("critical", true);
        this.f2810r = optJSONObject.optBoolean("includeStackTrace", true);
        this.f2804l = optJSONObject.optBoolean("error");
        this.f2805m = optJSONObject.optBoolean("debug");
        this.f2806n = optJSONObject.optBoolean(C0578a.f789b);
        this.f2807o = optJSONObject.optBoolean("system");
        this.f2808p = optJSONObject.optBoolean("timing");
        this.f2809q = optJSONObject.optBoolean("user");
        this.f2811s = jSONObject.optLong("getAdRetryBaseMs", C1372b.f2679b);
        this.f2812t = jSONObject.optInt("getAdRetryMaxBackoffExponent", 5);
        optJSONObject = jSONObject.optJSONObject(ParametersKeys.WEB_VIEW);
        if (optJSONObject == null) {
            optJSONObject = new JSONObject();
        }
        if (!VungleApiClient.MANUFACTURER_AMAZON.equalsIgnoreCase(Build.MANUFACTURER) || VERSION.SDK_INT >= 21) {
            z = true;
        } else {
            z = false;
        }
        this.f2813u = optJSONObject.optInt("cacheMaxBytes", 104857600);
        int optInt = optJSONObject.optInt("cacheMaxUnits", 10);
        if (optInt <= 0) {
            optInt = 10;
        }
        this.f2814v = optInt;
        this.f2815w = (int) TimeUnit.SECONDS.toDays((long) optJSONObject.optInt("cacheTTLs", C1372b.f2678a));
        List arrayList2 = new ArrayList();
        JSONArray optJSONArray2 = optJSONObject.optJSONArray("directories");
        if (optJSONArray2 != null) {
            int length2 = optJSONArray2.length();
            for (optInt = 0; optInt < length2; optInt++) {
                String optString2 = optJSONArray2.optString(optInt);
                if (!optString2.isEmpty()) {
                    arrayList2.add(optString2);
                }
            }
        }
        this.f2816x = Collections.unmodifiableList(arrayList2);
        z = z && optJSONObject.optBoolean(String.ENABLED, C1390e.m3202a());
        this.f2817y = z;
        this.f2818z = optJSONObject.optBoolean("inplayEnabled", true);
        this.f2781A = optJSONObject.optBoolean("interstitialEnabled", true);
        int optInt2 = optJSONObject.optInt("invalidatePendingImpression", 3);
        if (optInt2 <= 0) {
            optInt2 = 3;
        }
        this.f2782B = optInt2;
        this.f2783C = optJSONObject.optBoolean("lockOrientation", true);
        this.f2784D = optJSONObject.optInt("prefetchSession", 3);
        this.f2785E = optJSONObject.optBoolean("rewardVideoEnabled", true);
        this.f2786F = optJSONObject.optString("version", "v2");
        this.f2787G = String.format("%s/%s%s", new Object[]{ParametersKeys.WEB_VIEW, this.f2786F, "/interstitial/get"});
        this.f2788H = String.format("%s/%s/%s", new Object[]{ParametersKeys.WEB_VIEW, this.f2786F, "prefetch"});
        this.f2789I = String.format("%s/%s%s", new Object[]{ParametersKeys.WEB_VIEW, this.f2786F, "/reward/get"});
        arrayList = new ArrayList();
        boolean z4 = C1410i.f2947x != CBPIDataUseConsent.NO_BEHAVIORAL;
        if (C1410i.f2947x == CBPIDataUseConsent.NO_BEHAVIORAL) {
            z3 = false;
        }
        JSONObject optJSONObject2 = jSONObject.optJSONObject("certificationProviders");
        if (optJSONObject2 != null) {
            optJSONObject2 = optJSONObject2.optJSONObject("moat");
            if (optJSONObject2 != null) {
                arrayList.add("moat");
                z2 = optJSONObject2.optBoolean("loggingEnabled", false);
                z4 = optJSONObject2.optBoolean("locationEnabled", z4);
                z3 = optJSONObject2.optBoolean("idfaCollectionEnabled", z3);
            }
        }
        this.f2790J = z2;
        this.f2791K = z4;
        this.f2792L = z3;
        C1450o.m3613a(arrayList);
    }

    /* renamed from: a */
    private static boolean m3202a() {
        int[] iArr = new int[]{4, 4, 2};
        String d = C1454s.m3627a().m3634d();
        if (d == null || d.length() <= 0) {
            return false;
        }
        String[] split = d.replaceAll("[^\\d.]", "").split("\\.");
        int i = 0;
        while (i < split.length && i < iArr.length) {
            try {
                if (Integer.valueOf(split[i]).intValue() > iArr[i]) {
                    return true;
                }
                if (Integer.valueOf(split[i]).intValue() < iArr[i]) {
                    return false;
                }
                i++;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }
}
