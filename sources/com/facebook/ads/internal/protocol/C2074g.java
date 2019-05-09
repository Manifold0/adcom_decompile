package com.facebook.ads.internal.protocol;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.ads.internal.p025w.p044h.C2625a;
import com.facebook.ads.internal.p025w.p044h.C2626b;
import com.facebook.ads.internal.settings.AdSdkVersion;
import com.facebook.share.internal.MessengerShareContentUtility;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import org.json.JSONObject;

/* renamed from: com.facebook.ads.internal.protocol.g */
public final class C2074g {
    /* renamed from: a */
    private final C2073a f4684a;
    @Nullable
    /* renamed from: b */
    private final Long f4685b;
    @Nullable
    /* renamed from: c */
    private final String f4686c;
    @Nullable
    /* renamed from: d */
    private final String f4687d;

    /* renamed from: com.facebook.ads.internal.protocol.g$a */
    private enum C2073a {
        ID,
        CREATIVE,
        NONE
    }

    public C2074g(Context context, String str, String str2, C2070e c2070e) {
        if (TextUtils.isEmpty(str)) {
            this.f4684a = C2073a.NONE;
            this.f4685b = null;
            this.f4687d = null;
            this.f4686c = null;
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            switch (C2073a.valueOf(jSONObject.getString("type").toUpperCase())) {
                case ID:
                    this.f4684a = C2073a.ID;
                    this.f4685b = Long.valueOf(jSONObject.getString("bid_id"));
                    this.f4687d = jSONObject.getString("device_id");
                    this.f4686c = null;
                    break;
                case CREATIVE:
                    this.f4684a = C2073a.CREATIVE;
                    this.f4685b = Long.valueOf(jSONObject.getString("bid_id"));
                    this.f4687d = jSONObject.getString("device_id");
                    this.f4686c = new JSONObject(jSONObject.getString(MessengerShareContentUtility.ATTACHMENT_PAYLOAD)).toString();
                    break;
                default:
                    throw new C2066b(AdErrorType.BID_PAYLOAD_ERROR, "Unsupported BidPayload type " + jSONObject.getString("type"));
            }
            if (!jSONObject.getString("sdk_version").equals(AdSdkVersion.BUILD)) {
                throw new C2066b(AdErrorType.BID_IMPRESSION_MISMATCH, String.format(Locale.US, "Bid %d for SDK version %s being used on SDK version %s", new Object[]{this.f4685b, jSONObject.getString("sdk_version"), AdSdkVersion.BUILD}));
            } else if (jSONObject.getString("resolved_placement_id").equals(str2)) {
                Set hashSet = new HashSet(Arrays.asList(new Integer[]{Integer.valueOf(C2070e.WEBVIEW_INTERSTITIAL_HORIZONTAL.m5047a()), Integer.valueOf(C2070e.WEBVIEW_INTERSTITIAL_VERTICAL.m5047a()), Integer.valueOf(C2070e.WEBVIEW_INTERSTITIAL_TABLET.m5047a()), Integer.valueOf(C2070e.WEBVIEW_INTERSTITIAL_UNKNOWN.m5047a())}));
                if (jSONObject.getInt("template") == c2070e.m5047a()) {
                    return;
                }
                if (!hashSet.contains(Integer.valueOf(jSONObject.getInt("template"))) || !hashSet.contains(Integer.valueOf(c2070e.m5047a()))) {
                    throw new C2066b(AdErrorType.BID_IMPRESSION_MISMATCH, String.format(Locale.US, "Bid %d for template %s being used on template %s", new Object[]{this.f4685b, Integer.valueOf(jSONObject.getInt("template")), c2070e}));
                }
            } else {
                throw new C2066b(AdErrorType.BID_IMPRESSION_MISMATCH, String.format(Locale.US, "Bid %d for placement %s being used on placement %s", new Object[]{this.f4685b, jSONObject.getString("resolved_placement_id"), str2}));
            }
        } catch (Throwable e) {
            C2625a.m6741b(context, "api", C2626b.f6539d, e);
            throw new C2066b(AdErrorType.BID_PAYLOAD_ERROR, "Invalid BidPayload", e);
        }
    }

    /* renamed from: a */
    public void m5051a(String str) {
        if (!this.f4687d.equals(str)) {
            throw new C2066b(AdErrorType.BID_IMPRESSION_MISMATCH, String.format(Locale.US, "Bid %d for IDFA %s being used on IDFA %s", new Object[]{this.f4685b, this.f4687d, str}));
        }
    }

    /* renamed from: a */
    public boolean m5052a() {
        return this.f4684a == C2073a.CREATIVE;
    }

    @Nullable
    /* renamed from: b */
    public String m5053b() {
        return this.f4686c;
    }

    /* renamed from: c */
    public boolean m5054c() {
        return this.f4684a != C2073a.NONE;
    }

    @Nullable
    /* renamed from: d */
    public String m5055d() {
        return this.f4685b == null ? null : this.f4685b.toString();
    }
}
