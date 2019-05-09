package com.facebook.ads.internal.adapters.p030b;

import com.facebook.ads.internal.adapters.p030b.C1869d.C1868a;
import com.facebook.ads.internal.adapters.p030b.C1873e.C1872b;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.tapjoy.TapjoyConstants;
import java.io.Serializable;
import org.json.JSONObject;

/* renamed from: com.facebook.ads.internal.adapters.b.l */
public class C1880l implements Serializable {
    private static final long serialVersionUID = 85021702336014823L;
    /* renamed from: a */
    private final C1873e f3978a;
    /* renamed from: b */
    private final C1877i f3979b;
    /* renamed from: c */
    private final C1869d f3980c;
    /* renamed from: d */
    private final boolean f3981d;

    private C1880l(C1873e c1873e, C1877i c1877i, C1869d c1869d, boolean z) {
        this.f3978a = c1873e;
        this.f3979b = c1877i;
        this.f3980c = c1869d;
        this.f3981d = z;
    }

    /* renamed from: a */
    static C1880l m4314a(JSONObject jSONObject) {
        int i = 0;
        C1873e a = new C1872b().m4257a(jSONObject.optString("title")).m4259b(jSONObject.optString(MessengerShareContentUtility.SUBTITLE)).m4260c(jSONObject.optString("body")).m4258a();
        C1877i c1877i = new C1877i(jSONObject.optString("fbad_command"), jSONObject.optString("call_to_action"));
        boolean optBoolean = jSONObject.optBoolean("video_autoplay_enabled");
        boolean optBoolean2 = jSONObject.optBoolean("is_watch_and_browse");
        C1868a b = new C1868a().m4230a(jSONObject.optString(TapjoyConstants.TJC_VIDEO_URL)).m4231a(optBoolean).m4235b(jSONObject.optBoolean("is_audio_muted", true));
        if (optBoolean) {
            i = jSONObject.optInt("unskippable_seconds", 0);
        }
        C1868a a2 = b.m4228a(i);
        JSONObject optJSONObject = jSONObject.optJSONObject(MessengerShareContentUtility.MEDIA_IMAGE);
        if (optJSONObject != null) {
            a2.m4234b(optJSONObject.optString("url")).m4236c(optJSONObject.optInt("width")).m4237d(optJSONObject.optInt("height"));
        }
        a2.m4229a(C1884n.m4332a(jSONObject));
        return new C1880l(a, c1877i, a2.m4232a(), optBoolean2);
    }

    /* renamed from: a */
    public C1873e m4315a() {
        return this.f3978a;
    }

    /* renamed from: b */
    public C1877i m4316b() {
        return this.f3979b;
    }

    /* renamed from: c */
    public C1869d m4317c() {
        return this.f3980c;
    }

    /* renamed from: d */
    public boolean m4318d() {
        return this.f3981d;
    }
}
