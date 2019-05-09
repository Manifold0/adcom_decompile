// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.Model;

import com.chartboost.sdk.impl.s;
import org.json.JSONArray;
import com.chartboost.sdk.impl.o;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.i;
import java.util.concurrent.TimeUnit;
import android.os.Build$VERSION;
import android.os.Build;
import com.chartboost.sdk.Libraries.b;
import java.util.Collections;
import java.util.ArrayList;
import org.json.JSONObject;
import java.util.List;

public class e
{
    public final boolean A;
    public final int B;
    public final boolean C;
    public final int D;
    public final boolean E;
    public final String F;
    public final String G;
    public final String H;
    public final String I;
    public final boolean J;
    public final boolean K;
    public final boolean L;
    public final String a;
    public final boolean b;
    public final boolean c;
    public final List<String> d;
    public final boolean e;
    public final boolean f;
    public final boolean g;
    public final boolean h;
    public final int i;
    public final boolean j;
    public final boolean k;
    public final boolean l;
    public final boolean m;
    public final boolean n;
    public final boolean o;
    public final boolean p;
    public final boolean q;
    public final boolean r;
    public final long s;
    public final int t;
    public final int u;
    public final int v;
    public final int w;
    public final List<String> x;
    public final boolean y;
    public final boolean z;
    
    public e(JSONObject jsonObject) {
        final boolean b = false;
        int n = 1;
        this.a = jsonObject.optString("configVariant");
        this.b = jsonObject.optBoolean("prefetchDisable");
        this.c = jsonObject.optBoolean("publisherDisable");
        final ArrayList<String> list = new ArrayList<String>();
        final JSONArray optJSONArray = jsonObject.optJSONArray("invalidateFolderList");
        if (optJSONArray != null) {
            for (int length = optJSONArray.length(), i = 0; i < length; ++i) {
                final String optString = optJSONArray.optString(i);
                if (!optString.isEmpty()) {
                    list.add(optString);
                }
            }
        }
        this.d = (List<String>)Collections.unmodifiableList((List<?>)list);
        JSONObject optJSONObject;
        if ((optJSONObject = jsonObject.optJSONObject("native")) == null) {
            optJSONObject = new JSONObject();
        }
        this.e = optJSONObject.optBoolean("enabled", true);
        this.f = optJSONObject.optBoolean("inplayEnabled", true);
        this.g = optJSONObject.optBoolean("interstitialEnabled", true);
        this.h = optJSONObject.optBoolean("lockOrientation");
        this.i = optJSONObject.optInt("prefetchSession", 3);
        this.j = optJSONObject.optBoolean("rewardVideoEnabled", true);
        JSONObject optJSONObject2;
        if ((optJSONObject2 = jsonObject.optJSONObject("trackingLevels")) == null) {
            optJSONObject2 = new JSONObject();
        }
        this.k = optJSONObject2.optBoolean("critical", true);
        this.r = optJSONObject2.optBoolean("includeStackTrace", true);
        this.l = optJSONObject2.optBoolean("error");
        this.m = optJSONObject2.optBoolean("debug");
        this.n = optJSONObject2.optBoolean("session");
        this.o = optJSONObject2.optBoolean("system");
        this.p = optJSONObject2.optBoolean("timing");
        this.q = optJSONObject2.optBoolean("user");
        this.s = jsonObject.optLong("getAdRetryBaseMs", com.chartboost.sdk.Libraries.b.b);
        this.t = jsonObject.optInt("getAdRetryMaxBackoffExponent", 5);
        JSONObject optJSONObject3;
        if ((optJSONObject3 = jsonObject.optJSONObject("webview")) == null) {
            optJSONObject3 = new JSONObject();
        }
        boolean b2;
        if ("Amazon".equalsIgnoreCase(Build.MANUFACTURER) && Build$VERSION.SDK_INT < 21) {
            b2 = false;
        }
        else {
            b2 = true;
        }
        this.u = optJSONObject3.optInt("cacheMaxBytes", 104857600);
        int optInt = optJSONObject3.optInt("cacheMaxUnits", 10);
        if (optInt <= 0) {
            optInt = 10;
        }
        this.v = optInt;
        this.w = (int)TimeUnit.SECONDS.toDays(optJSONObject3.optInt("cacheTTLs", com.chartboost.sdk.Libraries.b.a));
        final ArrayList<String> list2 = new ArrayList<String>();
        final JSONArray optJSONArray2 = optJSONObject3.optJSONArray("directories");
        if (optJSONArray2 != null) {
            for (int length2 = optJSONArray2.length(), j = 0; j < length2; ++j) {
                final String optString2 = optJSONArray2.optString(j);
                if (!optString2.isEmpty()) {
                    list2.add(optString2);
                }
            }
        }
        this.x = (List<String>)Collections.unmodifiableList((List<?>)list2);
        this.y = (b2 && optJSONObject3.optBoolean("enabled", a()));
        this.z = optJSONObject3.optBoolean("inplayEnabled", true);
        this.A = optJSONObject3.optBoolean("interstitialEnabled", true);
        int optInt2 = optJSONObject3.optInt("invalidatePendingImpression", 3);
        if (optInt2 <= 0) {
            optInt2 = 3;
        }
        this.B = optInt2;
        this.C = optJSONObject3.optBoolean("lockOrientation", true);
        this.D = optJSONObject3.optInt("prefetchSession", 3);
        this.E = optJSONObject3.optBoolean("rewardVideoEnabled", true);
        this.F = optJSONObject3.optString("version", "v2");
        this.G = String.format("%s/%s%s", "webview", this.F, "/interstitial/get");
        this.H = String.format("%s/%s/%s", "webview", this.F, "prefetch");
        this.I = String.format("%s/%s%s", "webview", this.F, "/reward/get");
        final ArrayList<String> list3 = new ArrayList<String>();
        final boolean b3 = com.chartboost.sdk.i.x != Chartboost.CBPIDataUseConsent.NO_BEHAVIORAL;
        if (com.chartboost.sdk.i.x == Chartboost.CBPIDataUseConsent.NO_BEHAVIORAL) {
            n = 0;
        }
        jsonObject = jsonObject.optJSONObject("certificationProviders");
        boolean optBoolean = b3;
        boolean optBoolean2 = b;
        boolean optBoolean3 = n != 0;
        if (jsonObject != null) {
            jsonObject = jsonObject.optJSONObject("moat");
            optBoolean = b3;
            optBoolean2 = b;
            optBoolean3 = (n != 0);
            if (jsonObject != null) {
                list3.add("moat");
                optBoolean2 = jsonObject.optBoolean("loggingEnabled", false);
                optBoolean = jsonObject.optBoolean("locationEnabled", b3);
                optBoolean3 = jsonObject.optBoolean("idfaCollectionEnabled", (boolean)(n != 0));
            }
        }
        this.J = optBoolean2;
        this.K = optBoolean;
        this.L = optBoolean3;
        com.chartboost.sdk.impl.o.a(list3);
    }
    
    private static boolean a() {
        final int[] array2;
        final int[] array = array2 = new int[3];
        array2[1] = (array2[0] = 4);
        array2[2] = 2;
        final String d = s.a().d();
        if (d != null && d.length() > 0) {
            final String[] split = d.replaceAll("[^\\d.]", "").split("\\.");
            int n = 0;
            while (n < split.length && n < array.length) {
                try {
                    if (Integer.valueOf(split[n]) > array[n]) {
                        return true;
                    }
                    if (Integer.valueOf(split[n]) < array[n]) {
                        break;
                    }
                    ++n;
                }
                catch (NumberFormatException ex) {
                    return false;
                }
            }
        }
        return false;
    }
}
