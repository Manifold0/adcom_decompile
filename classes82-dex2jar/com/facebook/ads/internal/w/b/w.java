// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.w.b;

import android.os.Build$VERSION;
import java.util.HashMap;
import java.util.Map;
import com.facebook.ads.internal.r.a;
import android.content.Context;
import android.view.InputDevice$MotionRange;
import android.view.InputDevice;
import android.view.MotionEvent;
import android.support.annotation.Nullable;
import android.view.View;

public class w
{
    private static final String a;
    private boolean b;
    private int c;
    private int d;
    private int e;
    private int f;
    private long g;
    private int h;
    private long i;
    private long j;
    private int k;
    private int l;
    private int m;
    private int n;
    private float o;
    private float p;
    private float q;
    @Nullable
    private View r;
    @Nullable
    private View s;
    
    static {
        a = w.class.getSimpleName();
    }
    
    public w() {
        this.c = -1;
        this.d = -1;
        this.e = -1;
        this.f = -1;
        this.g = -1L;
        this.h = -1;
        this.i = -1L;
        this.j = -1L;
        this.k = -1;
        this.l = -1;
        this.m = -1;
        this.n = -1;
        this.o = -1.0f;
        this.p = -1.0f;
        this.q = -1.0f;
    }
    
    public void a() {
        this.g = System.currentTimeMillis();
    }
    
    public void a(final MotionEvent motionEvent, final View view, final View view2) {
        if (!this.b) {
            this.b = true;
            final InputDevice device = motionEvent.getDevice();
            if (device != null) {
                final InputDevice$MotionRange motionRange = device.getMotionRange(0);
                final InputDevice$MotionRange motionRange2 = device.getMotionRange(1);
                if (motionRange != null && motionRange2 != null) {
                    this.q = Math.min(motionRange.getRange(), motionRange2.getRange());
                }
            }
            if (this.q <= 0.0f) {
                this.q = (float)Math.min(view.getMeasuredWidth(), view.getMeasuredHeight());
            }
        }
        final int[] array = new int[2];
        view.getLocationInWindow(array);
        final int[] array2 = new int[2];
        view2.getLocationInWindow(array2);
        switch (motionEvent.getAction()) {
            default: {}
            case 0: {
                this.c = (int)(array[0] / x.b);
                this.d = (int)(array[1] / x.b);
                this.e = (int)(view.getWidth() / x.b);
                this.f = (int)(view.getHeight() / x.b);
                this.h = 1;
                this.i = System.currentTimeMillis();
                this.k = (int)(((int)(motionEvent.getX() + 0.5f) + array2[0] - array[0]) / x.b);
                this.l = (int)((array2[1] + (int)(motionEvent.getY() + 0.5f) - array[1]) / x.b);
                this.o = motionEvent.getPressure();
                this.p = motionEvent.getSize();
                this.r = view2;
            }
            case 2: {
                this.o -= this.o / this.h;
                this.o += motionEvent.getPressure() / this.h;
                this.p -= this.p / this.h;
                this.p += motionEvent.getSize() / this.h;
                ++this.h;
            }
            case 1:
            case 3: {
                this.j = System.currentTimeMillis();
                this.m = (int)(((int)(motionEvent.getX() + 0.5f) + array2[0] - array[0]) / x.b);
                this.n = (int)((array2[1] + (int)(motionEvent.getY() + 0.5f) - array[1]) / x.b);
                this.s = view2;
            }
        }
    }
    
    public boolean a(final Context context) {
        final int d = com.facebook.ads.internal.r.a.d(context);
        return d >= 0 && this.c() < d;
    }
    
    public boolean b() {
        return this.g != -1L;
    }
    
    public long c() {
        if (this.b()) {
            return System.currentTimeMillis() - this.g;
        }
        return -1L;
    }
    
    public boolean d() {
        return this.b;
    }
    
    public Map<String, String> e() {
        if (!this.b) {
            return null;
        }
        final String value = String.valueOf(this.p * this.q / 2.0f);
        long n;
        if (this.g > 0L && this.j > this.g) {
            n = this.j - this.g;
        }
        else {
            n = -1L;
        }
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("adPositionX", String.valueOf(this.c));
        hashMap.put("adPositionY", String.valueOf(this.d));
        hashMap.put("width", String.valueOf(this.e));
        hashMap.put("height", String.valueOf(this.f));
        hashMap.put("clickDelayTime", String.valueOf(n));
        hashMap.put("startTime", String.valueOf(this.i));
        hashMap.put("endTime", String.valueOf(this.j));
        hashMap.put("startX", String.valueOf(this.k));
        hashMap.put("startY", String.valueOf(this.l));
        hashMap.put("clickX", String.valueOf(this.m));
        hashMap.put("clickY", String.valueOf(this.n));
        hashMap.put("endX", String.valueOf(this.m));
        hashMap.put("endY", String.valueOf(this.n));
        hashMap.put("force", String.valueOf(this.o));
        hashMap.put("radiusX", value);
        hashMap.put("radiusY", value);
        j j;
        if (this.r == null || this.s == null) {
            j = com.facebook.ads.internal.w.b.j.e;
        }
        else if (this.r != this.s) {
            j = com.facebook.ads.internal.w.b.j.b;
        }
        else if (Build$VERSION.SDK_INT < 4) {
            j = com.facebook.ads.internal.w.b.j.c;
        }
        else {
            final Object tag = this.r.getTag(com.facebook.ads.internal.w.b.j.p);
            if (tag == null) {
                j = com.facebook.ads.internal.w.b.j.a;
            }
            else if (!(tag instanceof j)) {
                j = com.facebook.ads.internal.w.b.j.d;
            }
            else {
                j = (j)tag;
            }
        }
        hashMap.put("clickedViewTag", String.valueOf(j.a()));
        return hashMap;
    }
}
