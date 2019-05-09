package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.ads.MediaView;
import com.facebook.ads.internal.p017t.C1783f;
import com.facebook.ads.internal.p017t.C2118k;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p029x.C2630a;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import com.tapjoy.TJAdUnitConstants.String;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.facebook.ads.internal.adapters.p */
public class C1938p extends C1888b {
    /* renamed from: c */
    private final C1924i f4225c;
    /* renamed from: d */
    private C2118k f4226d;
    /* renamed from: e */
    private boolean f4227e;
    /* renamed from: f */
    private boolean f4228f;
    /* renamed from: g */
    private boolean f4229g;
    /* renamed from: h */
    private boolean f4230h;
    /* renamed from: i */
    private boolean f4231i;
    /* renamed from: j */
    private View f4232j;
    @Nullable
    /* renamed from: k */
    private C1783f f4233k;
    /* renamed from: l */
    private List<View> f4234l;
    /* renamed from: m */
    private C1936a f4235m = C1936a.ALL;
    /* renamed from: n */
    private C1937b f4236n = null;
    @Nullable
    /* renamed from: o */
    private String f4237o;

    /* renamed from: com.facebook.ads.internal.adapters.p$a */
    public enum C1936a {
        ALL("all"),
        NONE(ParametersKeys.ORIENTATION_NONE),
        MANUAL("manual");
        
        /* renamed from: d */
        private final String f4219d;

        private C1936a(String str) {
            this.f4219d = str;
        }

        public String toString() {
            return this.f4219d;
        }
    }

    /* renamed from: com.facebook.ads.internal.adapters.p$b */
    public enum C1937b {
        AVAILABLE(ParametersKeys.AVAILABLE),
        TOO_SMALL("too_small"),
        NO_NATIVE_AD_LAYOUT("no_native_ad_layout");
        
        /* renamed from: d */
        private final String f4224d;

        private C1937b(String str) {
            this.f4224d = str;
        }

        public String toString() {
            return this.f4224d;
        }
    }

    public C1938p(Context context, C1895c c1895c, C2630a c2630a, C1924i c1924i) {
        super(context, c1895c, c2630a);
        this.f4225c = c1924i;
    }

    /* renamed from: b */
    private String m4568b(View view) {
        try {
            return m4569c(view).toString();
        } catch (JSONException e) {
            return "Json exception";
        }
    }

    /* renamed from: c */
    private JSONObject m4569c(View view) {
        boolean z = true;
        int i = 0;
        JSONObject jSONObject = new JSONObject();
        jSONObject.putOpt("id", Integer.valueOf(view.getId()));
        jSONObject.putOpt("class", view.getClass());
        jSONObject.putOpt("origin", String.format(Locale.US, "{x:%d, y:%d}", new Object[]{Integer.valueOf(view.getTop()), Integer.valueOf(view.getLeft())}));
        jSONObject.putOpt("size", String.format(Locale.US, "{h:%d, w:%d}", new Object[]{Integer.valueOf(view.getHeight()), Integer.valueOf(view.getWidth())}));
        if (this.f4234l == null || !this.f4234l.contains(view)) {
            z = false;
        }
        jSONObject.putOpt(String.CLICKABLE, Boolean.valueOf(z));
        Object obj = "unknown";
        if (view instanceof Button) {
            obj = "button";
        } else if (view instanceof TextView) {
            obj = "text";
        } else if (view instanceof ImageView) {
            obj = MessengerShareContentUtility.MEDIA_IMAGE;
        } else if (view instanceof MediaView) {
            obj = "mediaview";
        } else if (view instanceof ViewGroup) {
            obj = "viewgroup";
        }
        jSONObject.putOpt("type", obj);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            JSONArray jSONArray = new JSONArray();
            while (i < viewGroup.getChildCount()) {
                jSONArray.put(m4569c(viewGroup.getChildAt(i)));
                i++;
            }
            jSONObject.putOpt("list", jSONArray);
        }
        return jSONObject;
    }

    /* renamed from: d */
    private String m4570d(View view) {
        if (view.getWidth() <= 0 || view.getHeight() <= 0) {
            return "";
        }
        try {
            Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Config.ARGB_8888);
            createBitmap.setDensity(view.getResources().getDisplayMetrics().densityDpi);
            view.draw(new Canvas(createBitmap));
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            createBitmap.compress(CompressFormat.JPEG, this.f4225c.m4509i(), byteArrayOutputStream);
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
        } catch (Exception e) {
            return "";
        }
    }

    /* renamed from: a */
    public void m4571a(View view) {
        this.f4232j = view;
    }

    /* renamed from: a */
    public void m4572a(C1936a c1936a) {
        this.f4235m = c1936a;
    }

    /* renamed from: a */
    public void m4573a(C1937b c1937b) {
        this.f4236n = c1937b;
    }

    /* renamed from: a */
    public void m4574a(C1783f c1783f) {
        this.f4233k = c1783f;
    }

    /* renamed from: a */
    public void m4575a(C2118k c2118k) {
        this.f4226d = c2118k;
    }

    /* renamed from: a */
    public void m4576a(@Nullable String str) {
        this.f4237o = str;
    }

    /* renamed from: a */
    public void m4577a(List<View> list) {
        this.f4234l = list;
    }

    /* renamed from: a */
    protected void mo5414a(Map<String, String> map) {
        if (this.f4225c != null) {
            if (this.f4226d != null) {
                map.put("nti", String.valueOf(this.f4226d.m5383c()));
            }
            if (this.f4227e) {
                map.put("nhs", Boolean.TRUE.toString());
            }
            if (this.f4228f) {
                map.put("nmv", Boolean.TRUE.toString());
            }
            if (this.f4229g) {
                map.put("nmvap", Boolean.TRUE.toString());
            }
            if (this.f4232j != null && this.f4225c.m4503d()) {
                map.put(ParametersKeys.VIEW, m4568b(this.f4232j));
            }
            if (this.f4232j != null && this.f4225c.m4508h()) {
                map.put("snapshot", m4570d(this.f4232j));
            }
            if (this.f4230h) {
                map.put("niv", Boolean.TRUE.toString());
            }
            if (this.f4235m != null) {
                map.put("precache_media", this.f4235m.toString());
            }
            if (this.f4231i) {
                map.put("ucvr", Boolean.TRUE.toString());
            }
            if (this.f4233k != null) {
                map.put("namw", String.valueOf((int) (((float) this.f4233k.getWidth()) / C2600x.f6420b)));
                map.put("namh", String.valueOf((int) (((float) this.f4233k.getHeight()) / C2600x.f6420b)));
            }
            if (this.f4236n != null) {
                map.put("narar", this.f4236n.toString());
            }
            if (this.f4237o != null) {
                map.put("extra_hints", this.f4237o);
            }
            this.f4225c.m4497a((Map) map);
        }
    }

    /* renamed from: a */
    public void m4579a(boolean z) {
        this.f4227e = z;
    }

    /* renamed from: b */
    public void m4580b(boolean z) {
        this.f4228f = z;
    }

    /* renamed from: c */
    public void m4581c(boolean z) {
        this.f4229g = z;
    }

    /* renamed from: d */
    public void m4582d(boolean z) {
        this.f4230h = z;
    }

    /* renamed from: e */
    public void m4583e(boolean z) {
        this.f4231i = z;
    }
}
