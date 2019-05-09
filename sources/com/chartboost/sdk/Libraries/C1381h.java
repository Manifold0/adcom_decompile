package com.chartboost.sdk.Libraries;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import com.chartboost.sdk.C1403e;
import com.chartboost.sdk.Tracking.C1391a;
import java.io.File;
import org.json.JSONObject;

/* renamed from: com.chartboost.sdk.Libraries.h */
public class C1381h {
    /* renamed from: a */
    private C1380a f2710a;
    /* renamed from: b */
    private final C1403e f2711b;
    /* renamed from: c */
    private String f2712c;
    /* renamed from: d */
    private float f2713d = 1.0f;

    /* renamed from: com.chartboost.sdk.Libraries.h$a */
    public static class C1380a {
        /* renamed from: a */
        private int f2703a;
        /* renamed from: b */
        private final String f2704b;
        /* renamed from: c */
        private final File f2705c;
        /* renamed from: d */
        private Bitmap f2706d;
        /* renamed from: e */
        private final C1378f f2707e;
        /* renamed from: f */
        private int f2708f = -1;
        /* renamed from: g */
        private int f2709g = -1;

        public C1380a(String str, File file, C1378f c1378f) {
            this.f2705c = file;
            this.f2704b = str;
            this.f2706d = null;
            this.f2703a = 1;
            this.f2707e = c1378f;
        }

        /* renamed from: a */
        public Bitmap m3144a() {
            if (this.f2706d == null) {
                m3145b();
            }
            return this.f2706d;
        }

        /* renamed from: b */
        public void m3145b() {
            if (this.f2706d == null) {
                CBLogging.m3097a("MemoryBitmap", "Loading image '" + this.f2704b + "' from cache");
                byte[] a = this.f2707e.m3134a(this.f2705c);
                if (a == null) {
                    CBLogging.m3099b("MemoryBitmap", "decode() - bitmap not found");
                    return;
                }
                Options options = new Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeByteArray(a, 0, a.length, options);
                Options options2 = new Options();
                options2.inJustDecodeBounds = false;
                options2.inDither = false;
                options2.inPurgeable = true;
                options2.inInputShareable = true;
                options2.inTempStorage = new byte[32768];
                options2.inSampleSize = 1;
                while (options2.inSampleSize < 32) {
                    try {
                        this.f2706d = BitmapFactory.decodeByteArray(a, 0, a.length, options2);
                    } catch (Throwable e) {
                        CBLogging.m3098a("MemoryBitmap", "OutOfMemoryError suppressed - trying larger sample size", e);
                        options2.inSampleSize *= 2;
                    } catch (Exception e2) {
                        CBLogging.m3098a("MemoryBitmap", "Exception raised decoding bitmap", e2);
                        C1391a.m3206a(getClass(), "decodeByteArray", e2);
                    }
                    if (this.f2706d == null) {
                        this.f2705c.delete();
                        throw new RuntimeException("Unable to decode " + this.f2704b);
                    }
                    this.f2703a = options2.inSampleSize;
                }
                this.f2703a = options2.inSampleSize;
            }
        }

        /* renamed from: c */
        public int m3146c() {
            return this.f2703a;
        }

        /* renamed from: d */
        public void m3147d() {
            try {
                if (!(this.f2706d == null || this.f2706d.isRecycled())) {
                    this.f2706d.recycle();
                }
            } catch (Exception e) {
                C1391a.m3206a(getClass(), "recycle", e);
            }
            this.f2706d = null;
        }

        /* renamed from: e */
        public int m3148e() {
            if (this.f2706d != null) {
                return this.f2706d.getWidth();
            }
            if (this.f2708f >= 0) {
                return this.f2708f;
            }
            m3143g();
            return this.f2708f;
        }

        /* renamed from: f */
        public int m3149f() {
            if (this.f2706d != null) {
                return this.f2706d.getHeight();
            }
            if (this.f2709g >= 0) {
                return this.f2709g;
            }
            m3143g();
            return this.f2709g;
        }

        /* renamed from: g */
        private void m3143g() {
            try {
                Options options = new Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(this.f2705c.getAbsolutePath(), options);
                this.f2708f = options.outWidth;
                this.f2709g = options.outHeight;
            } catch (Exception e) {
                CBLogging.m3098a("MemoryBitmap", "Error decoding file size", e);
                C1391a.m3206a(getClass(), "decodeSize", e);
            }
        }
    }

    public C1381h(C1403e c1403e) {
        this.f2711b = c1403e;
    }

    /* renamed from: a */
    public int m3150a() {
        return this.f2710a.m3148e() * this.f2710a.m3146c();
    }

    /* renamed from: b */
    public int m3153b() {
        return this.f2710a.m3149f() * this.f2710a.m3146c();
    }

    /* renamed from: a */
    public boolean m3151a(String str) {
        return m3152a(this.f2711b.m3309g(), str);
    }

    /* renamed from: a */
    public boolean m3152a(JSONObject jSONObject, String str) {
        JSONObject a = C1377e.m3129a(jSONObject, str);
        this.f2712c = str;
        if (a == null) {
            return true;
        }
        String optString = a.optString("url");
        this.f2713d = (float) a.optDouble("scale", 1.0d);
        if (optString.isEmpty()) {
            return true;
        }
        String optString2 = a.optString("checksum");
        if (optString2.isEmpty()) {
            return false;
        }
        this.f2710a = this.f2711b.f2875e.f2764j.m3399a(optString2);
        if (this.f2710a == null) {
            return false;
        }
        return true;
    }

    /* renamed from: c */
    public void m3154c() {
        if (this.f2710a != null) {
            this.f2710a.m3147d();
        }
    }

    /* renamed from: d */
    public boolean m3155d() {
        return this.f2710a != null;
    }

    /* renamed from: e */
    public Bitmap m3156e() {
        return this.f2710a != null ? this.f2710a.m3144a() : null;
    }

    /* renamed from: f */
    public float m3157f() {
        return this.f2713d;
    }
}
