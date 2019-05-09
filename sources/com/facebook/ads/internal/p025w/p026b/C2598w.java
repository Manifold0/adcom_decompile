package com.facebook.ads.internal.p025w.p026b;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.view.InputDevice;
import android.view.InputDevice.MotionRange;
import android.view.MotionEvent;
import android.view.View;
import com.facebook.ads.internal.p050r.C2078a;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.w.b.w */
public class C2598w {
    /* renamed from: a */
    private static final String f6396a = C2598w.class.getSimpleName();
    /* renamed from: b */
    private boolean f6397b;
    /* renamed from: c */
    private int f6398c = -1;
    /* renamed from: d */
    private int f6399d = -1;
    /* renamed from: e */
    private int f6400e = -1;
    /* renamed from: f */
    private int f6401f = -1;
    /* renamed from: g */
    private long f6402g = -1;
    /* renamed from: h */
    private int f6403h = -1;
    /* renamed from: i */
    private long f6404i = -1;
    /* renamed from: j */
    private long f6405j = -1;
    /* renamed from: k */
    private int f6406k = -1;
    /* renamed from: l */
    private int f6407l = -1;
    /* renamed from: m */
    private int f6408m = -1;
    /* renamed from: n */
    private int f6409n = -1;
    /* renamed from: o */
    private float f6410o = -1.0f;
    /* renamed from: p */
    private float f6411p = -1.0f;
    /* renamed from: q */
    private float f6412q = -1.0f;
    @Nullable
    /* renamed from: r */
    private View f6413r;
    @Nullable
    /* renamed from: s */
    private View f6414s;

    /* renamed from: a */
    public void m6670a() {
        this.f6402g = System.currentTimeMillis();
    }

    /* renamed from: a */
    public void m6671a(MotionEvent motionEvent, View view, View view2) {
        if (!this.f6397b) {
            this.f6397b = true;
            InputDevice device = motionEvent.getDevice();
            if (device != null) {
                MotionRange motionRange = device.getMotionRange(0);
                MotionRange motionRange2 = device.getMotionRange(1);
                if (!(motionRange == null || motionRange2 == null)) {
                    this.f6412q = Math.min(motionRange.getRange(), motionRange2.getRange());
                }
            }
            if (this.f6412q <= 0.0f) {
                this.f6412q = (float) Math.min(view.getMeasuredWidth(), view.getMeasuredHeight());
            }
        }
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        int[] iArr2 = new int[2];
        view2.getLocationInWindow(iArr2);
        switch (motionEvent.getAction()) {
            case 0:
                this.f6398c = (int) (((float) iArr[0]) / C2600x.f6420b);
                this.f6399d = (int) (((float) iArr[1]) / C2600x.f6420b);
                this.f6400e = (int) (((float) view.getWidth()) / C2600x.f6420b);
                this.f6401f = (int) (((float) view.getHeight()) / C2600x.f6420b);
                this.f6403h = 1;
                this.f6404i = System.currentTimeMillis();
                this.f6406k = (int) (((float) ((((int) (motionEvent.getX() + 0.5f)) + iArr2[0]) - iArr[0])) / C2600x.f6420b);
                this.f6407l = (int) (((float) ((iArr2[1] + ((int) (motionEvent.getY() + 0.5f))) - iArr[1])) / C2600x.f6420b);
                this.f6410o = motionEvent.getPressure();
                this.f6411p = motionEvent.getSize();
                this.f6413r = view2;
                return;
            case 1:
            case 3:
                this.f6405j = System.currentTimeMillis();
                this.f6408m = (int) (((float) ((((int) (motionEvent.getX() + 0.5f)) + iArr2[0]) - iArr[0])) / C2600x.f6420b);
                this.f6409n = (int) (((float) ((iArr2[1] + ((int) (motionEvent.getY() + 0.5f))) - iArr[1])) / C2600x.f6420b);
                this.f6414s = view2;
                return;
            case 2:
                this.f6410o -= this.f6410o / ((float) this.f6403h);
                this.f6410o += motionEvent.getPressure() / ((float) this.f6403h);
                this.f6411p -= this.f6411p / ((float) this.f6403h);
                this.f6411p += motionEvent.getSize() / ((float) this.f6403h);
                this.f6403h++;
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public boolean m6672a(Context context) {
        int d = C2078a.m5094d(context);
        return d >= 0 && m6674c() < ((long) d);
    }

    /* renamed from: b */
    public boolean m6673b() {
        return this.f6402g != -1;
    }

    /* renamed from: c */
    public long m6674c() {
        return m6673b() ? System.currentTimeMillis() - this.f6402g : -1;
    }

    /* renamed from: d */
    public boolean m6675d() {
        return this.f6397b;
    }

    /* renamed from: e */
    public Map<String, String> m6676e() {
        if (!this.f6397b) {
            return null;
        }
        C2580j c2580j;
        String valueOf = String.valueOf((this.f6411p * this.f6412q) / 2.0f);
        long j = (this.f6402g <= 0 || this.f6405j <= this.f6402g) ? -1 : this.f6405j - this.f6402g;
        Map<String, String> hashMap = new HashMap();
        hashMap.put("adPositionX", String.valueOf(this.f6398c));
        hashMap.put("adPositionY", String.valueOf(this.f6399d));
        hashMap.put("width", String.valueOf(this.f6400e));
        hashMap.put("height", String.valueOf(this.f6401f));
        hashMap.put("clickDelayTime", String.valueOf(j));
        hashMap.put("startTime", String.valueOf(this.f6404i));
        hashMap.put("endTime", String.valueOf(this.f6405j));
        hashMap.put("startX", String.valueOf(this.f6406k));
        hashMap.put("startY", String.valueOf(this.f6407l));
        hashMap.put("clickX", String.valueOf(this.f6408m));
        hashMap.put("clickY", String.valueOf(this.f6409n));
        hashMap.put("endX", String.valueOf(this.f6408m));
        hashMap.put("endY", String.valueOf(this.f6409n));
        hashMap.put("force", String.valueOf(this.f6410o));
        hashMap.put("radiusX", valueOf);
        hashMap.put("radiusY", valueOf);
        String str = "clickedViewTag";
        if (this.f6413r == null || this.f6414s == null) {
            c2580j = C2580j.INTERNAL_NULL_VIEW;
        } else if (this.f6413r != this.f6414s) {
            c2580j = C2580j.INTERNAL_NO_CLICK;
        } else if (VERSION.SDK_INT < 4) {
            c2580j = C2580j.INTERNAL_API_TOO_LOW;
        } else {
            Object tag = this.f6413r.getTag(C2580j.f6358p);
            c2580j = tag == null ? C2580j.INTERNAL_NO_TAG : !(tag instanceof C2580j) ? C2580j.INTERNAL_WRONG_TAG_CLASS : (C2580j) tag;
        }
        hashMap.put(str, String.valueOf(c2580j.m6644a()));
        return hashMap;
    }
}
