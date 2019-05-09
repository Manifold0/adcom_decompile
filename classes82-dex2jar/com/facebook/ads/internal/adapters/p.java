// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.adapters;

import com.facebook.ads.internal.w.b.x;
import java.util.Map;
import android.util.Base64;
import java.io.OutputStream;
import android.graphics.Bitmap$CompressFormat;
import java.io.ByteArrayOutputStream;
import android.graphics.Canvas;
import android.graphics.Bitmap;
import android.graphics.Bitmap$Config;
import org.json.JSONArray;
import android.view.ViewGroup;
import com.facebook.ads.MediaView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import java.util.Locale;
import org.json.JSONObject;
import org.json.JSONException;
import com.facebook.ads.internal.x.a;
import android.content.Context;
import java.util.List;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.t.f;
import android.view.View;
import com.facebook.ads.internal.t.k;

public class p extends com.facebook.ads.internal.adapters.b
{
    private final i c;
    private k d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;
    private boolean i;
    private View j;
    @Nullable
    private f k;
    private List<View> l;
    private a m;
    private b n;
    @Nullable
    private String o;
    
    public p(final Context context, final c c, final com.facebook.ads.internal.x.a a, final i c2) {
        super(context, c, a);
        this.m = a.a;
        this.n = null;
        this.c = c2;
    }
    
    private String b(final View view) {
        try {
            return this.c(view).toString();
        }
        catch (JSONException ex) {
            return "Json exception";
        }
    }
    
    private JSONObject c(final View view) {
        boolean b = true;
        int i = 0;
        final JSONObject jsonObject = new JSONObject();
        jsonObject.putOpt("id", (Object)view.getId());
        jsonObject.putOpt("class", (Object)view.getClass());
        jsonObject.putOpt("origin", (Object)String.format(Locale.US, "{x:%d, y:%d}", view.getTop(), view.getLeft()));
        jsonObject.putOpt("size", (Object)String.format(Locale.US, "{h:%d, w:%d}", view.getHeight(), view.getWidth()));
        if (this.l == null || !this.l.contains(view)) {
            b = false;
        }
        jsonObject.putOpt("clickable", (Object)b);
        String s = "unknown";
        if (view instanceof Button) {
            s = "button";
        }
        else if (view instanceof TextView) {
            s = "text";
        }
        else if (view instanceof ImageView) {
            s = "image";
        }
        else if (view instanceof MediaView) {
            s = "mediaview";
        }
        else if (view instanceof ViewGroup) {
            s = "viewgroup";
        }
        jsonObject.putOpt("type", (Object)s);
        if (view instanceof ViewGroup) {
            final ViewGroup viewGroup = (ViewGroup)view;
            final JSONArray jsonArray = new JSONArray();
            while (i < viewGroup.getChildCount()) {
                jsonArray.put((Object)this.c(viewGroup.getChildAt(i)));
                ++i;
            }
            jsonObject.putOpt("list", (Object)jsonArray);
        }
        return jsonObject;
    }
    
    private String d(final View view) {
        if (view.getWidth() <= 0 || view.getHeight() <= 0) {
            return "";
        }
        try {
            final Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap$Config.ARGB_8888);
            bitmap.setDensity(view.getResources().getDisplayMetrics().densityDpi);
            view.draw(new Canvas(bitmap));
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap$CompressFormat.JPEG, this.c.i(), (OutputStream)byteArrayOutputStream);
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
        }
        catch (Exception ex) {
            return "";
        }
    }
    
    public void a(final View j) {
        this.j = j;
    }
    
    public void a(final a m) {
        this.m = m;
    }
    
    public void a(final b n) {
        this.n = n;
    }
    
    public void a(final f k) {
        this.k = k;
    }
    
    public void a(final k d) {
        this.d = d;
    }
    
    public void a(@Nullable final String o) {
        this.o = o;
    }
    
    public void a(final List<View> l) {
        this.l = l;
    }
    
    @Override
    protected void a(final Map<String, String> map) {
        if (this.c == null) {
            return;
        }
        if (this.d != null) {
            map.put("nti", String.valueOf(this.d.c()));
        }
        if (this.e) {
            map.put("nhs", Boolean.TRUE.toString());
        }
        if (this.f) {
            map.put("nmv", Boolean.TRUE.toString());
        }
        if (this.g) {
            map.put("nmvap", Boolean.TRUE.toString());
        }
        if (this.j != null && this.c.d()) {
            map.put("view", this.b(this.j));
        }
        if (this.j != null && this.c.h()) {
            map.put("snapshot", this.d(this.j));
        }
        if (this.h) {
            map.put("niv", Boolean.TRUE.toString());
        }
        if (this.m != null) {
            map.put("precache_media", this.m.toString());
        }
        if (this.i) {
            map.put("ucvr", Boolean.TRUE.toString());
        }
        if (this.k != null) {
            map.put("namw", String.valueOf((int)(this.k.getWidth() / x.b)));
            map.put("namh", String.valueOf((int)(this.k.getHeight() / x.b)));
        }
        if (this.n != null) {
            map.put("narar", this.n.toString());
        }
        if (this.o != null) {
            map.put("extra_hints", this.o);
        }
        this.c.a(map);
    }
    
    public void a(final boolean e) {
        this.e = e;
    }
    
    public void b(final boolean f) {
        this.f = f;
    }
    
    public void c(final boolean g) {
        this.g = g;
    }
    
    public void d(final boolean h) {
        this.h = h;
    }
    
    public void e(final boolean i) {
        this.i = i;
    }
    
    public enum a
    {
        a("all"), 
        b("none"), 
        c("manual");
        
        private final String d;
        
        private a(final String d) {
            this.d = d;
        }
        
        @Override
        public String toString() {
            return this.d;
        }
    }
    
    public enum b
    {
        a("available"), 
        b("too_small"), 
        c("no_native_ad_layout");
        
        private final String d;
        
        private b(final String d) {
            this.d = d;
        }
        
        @Override
        public String toString() {
            return this.d;
        }
    }
}
