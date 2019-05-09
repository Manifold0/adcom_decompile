// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.Libraries;

import com.chartboost.sdk.Tracking.a;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory$Options;
import java.io.File;
import android.graphics.Bitmap;
import org.json.JSONObject;
import com.chartboost.sdk.e;

public class h
{
    private a a;
    private final e b;
    private String c;
    private float d;
    
    public h(final e b) {
        this.d = 1.0f;
        this.b = b;
    }
    
    public int a() {
        return this.a.e() * this.a.c();
    }
    
    public boolean a(final String s) {
        return this.a(this.b.g(), s);
    }
    
    public boolean a(JSONObject a, String optString) {
        a = com.chartboost.sdk.Libraries.e.a(a, optString);
        this.c = optString;
        if (a != null) {
            optString = a.optString("url");
            this.d = (float)a.optDouble("scale", 1.0);
            if (!optString.isEmpty()) {
                final String optString2 = a.optString("checksum");
                if (optString2.isEmpty()) {
                    return false;
                }
                this.a = this.b.e.j.a(optString2);
                if (this.a == null) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public int b() {
        return this.a.f() * this.a.c();
    }
    
    public void c() {
        if (this.a != null) {
            this.a.d();
        }
    }
    
    public boolean d() {
        return this.a != null;
    }
    
    public Bitmap e() {
        if (this.a != null) {
            return this.a.a();
        }
        return null;
    }
    
    public float f() {
        return this.d;
    }
    
    public static class a
    {
        private int a;
        private final String b;
        private final File c;
        private Bitmap d;
        private final f e;
        private int f;
        private int g;
        
        public a(final String b, final File c, final f e) {
            this.f = -1;
            this.g = -1;
            this.c = c;
            this.b = b;
            this.d = null;
            this.a = 1;
            this.e = e;
        }
        
        private void g() {
            try {
                final BitmapFactory$Options bitmapFactory$Options = new BitmapFactory$Options();
                bitmapFactory$Options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(this.c.getAbsolutePath(), bitmapFactory$Options);
                this.f = bitmapFactory$Options.outWidth;
                this.g = bitmapFactory$Options.outHeight;
            }
            catch (Exception ex) {
                CBLogging.a("MemoryBitmap", "Error decoding file size", ex);
                com.chartboost.sdk.Tracking.a.a(this.getClass(), "decodeSize", ex);
            }
        }
        
        public Bitmap a() {
            if (this.d == null) {
                this.b();
            }
            return this.d;
        }
        
        public void b() {
            if (this.d != null) {
                return;
            }
            CBLogging.a("MemoryBitmap", "Loading image '" + this.b + "' from cache");
            final byte[] a = this.e.a(this.c);
            if (a == null) {
                CBLogging.b("MemoryBitmap", "decode() - bitmap not found");
                return;
            }
            final BitmapFactory$Options bitmapFactory$Options = new BitmapFactory$Options();
            bitmapFactory$Options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(a, 0, a.length, bitmapFactory$Options);
            final BitmapFactory$Options bitmapFactory$Options2 = new BitmapFactory$Options();
            bitmapFactory$Options2.inJustDecodeBounds = false;
            bitmapFactory$Options2.inDither = false;
            bitmapFactory$Options2.inPurgeable = true;
            bitmapFactory$Options2.inInputShareable = true;
            bitmapFactory$Options2.inTempStorage = new byte[32768];
            bitmapFactory$Options2.inSampleSize = 1;
        Label_0148_Outer:
            while (bitmapFactory$Options2.inSampleSize < 32) {
                while (true) {
                    try {
                        this.d = BitmapFactory.decodeByteArray(a, 0, a.length, bitmapFactory$Options2);
                        if (this.d == null) {
                            this.c.delete();
                            throw new RuntimeException("Unable to decode " + this.b);
                        }
                    }
                    catch (OutOfMemoryError outOfMemoryError) {
                        CBLogging.a("MemoryBitmap", "OutOfMemoryError suppressed - trying larger sample size", outOfMemoryError);
                        bitmapFactory$Options2.inSampleSize *= 2;
                        continue Label_0148_Outer;
                    }
                    catch (Exception a) {
                        CBLogging.a("MemoryBitmap", "Exception raised decoding bitmap", (Throwable)(Object)a);
                        com.chartboost.sdk.Tracking.a.a(this.getClass(), "decodeByteArray", (Exception)(Object)a);
                        continue;
                    }
                    break;
                }
                break;
            }
            this.a = bitmapFactory$Options2.inSampleSize;
        }
        
        public int c() {
            return this.a;
        }
        
        public void d() {
            while (true) {
                try {
                    if (this.d != null && !this.d.isRecycled()) {
                        this.d.recycle();
                    }
                    this.d = null;
                }
                catch (Exception ex) {
                    com.chartboost.sdk.Tracking.a.a(this.getClass(), "recycle", ex);
                    continue;
                }
                break;
            }
        }
        
        public int e() {
            if (this.d != null) {
                return this.d.getWidth();
            }
            if (this.f >= 0) {
                return this.f;
            }
            this.g();
            return this.f;
        }
        
        public int f() {
            if (this.d != null) {
                return this.d.getHeight();
            }
            if (this.g >= 0) {
                return this.g;
            }
            this.g();
            return this.g;
        }
    }
}
