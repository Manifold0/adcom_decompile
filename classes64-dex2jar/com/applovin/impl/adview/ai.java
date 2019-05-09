// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.graphics.Canvas;
import android.graphics.Paint$Style;
import android.text.TextPaint;
import android.view.View$MeasureSpec;
import android.graphics.Color;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.RectF;
import android.graphics.Paint;
import android.view.View;

public class ai extends View
{
    private final int A;
    protected Paint a;
    protected Paint b;
    private Paint c;
    private Paint d;
    private RectF e;
    private float f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private float l;
    private int m;
    private String n;
    private String o;
    private float p;
    private String q;
    private float r;
    private final float s;
    private final int t;
    private final int u;
    private final int v;
    private final int w;
    private final int x;
    private final float y;
    private final float z;
    
    public ai(final Context context) {
        this(context, null);
    }
    
    public ai(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public ai(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.e = new RectF();
        this.i = 0;
        this.n = "";
        this.o = "";
        this.q = "";
        this.t = Color.rgb(66, 145, 241);
        this.u = Color.rgb(66, 145, 241);
        this.v = Color.rgb(66, 145, 241);
        this.w = 0;
        this.x = 100;
        this.y = aj.b(this.getResources(), 14.0f);
        this.A = (int)aj.a(this.getResources(), 100.0f);
        this.s = aj.a(this.getResources(), 4.0f);
        this.z = aj.b(this.getResources(), 18.0f);
        this.b();
        this.a();
    }
    
    private int e(int size) {
        final int mode = View$MeasureSpec.getMode(size);
        size = View$MeasureSpec.getSize(size);
        if (mode == 1073741824) {
            return size;
        }
        final int a = this.A;
        if (mode == Integer.MIN_VALUE) {
            return Math.min(a, size);
        }
        return a;
    }
    
    private float o() {
        return this.d() / (float)this.j * 360.0f;
    }
    
    protected void a() {
        (this.a = (Paint)new TextPaint()).setColor(this.g);
        this.a.setTextSize(this.f);
        this.a.setAntiAlias(true);
        (this.b = (Paint)new TextPaint()).setColor(this.h);
        this.b.setTextSize(this.p);
        this.b.setAntiAlias(true);
        (this.c = new Paint()).setColor(this.k);
        this.c.setStyle(Paint$Style.STROKE);
        this.c.setAntiAlias(true);
        this.c.setStrokeWidth(this.l);
        (this.d = new Paint()).setColor(this.m);
        this.d.setAntiAlias(true);
    }
    
    public void a(final float l) {
        this.l = l;
        this.invalidate();
    }
    
    public void a(final int i) {
        this.i = i;
        if (this.i > this.e()) {
            this.i %= this.e();
        }
        this.invalidate();
    }
    
    protected void b() {
        this.k = this.t;
        this.g = this.u;
        this.f = this.y;
        this.b(100);
        this.a(0);
        this.l = this.s;
        this.m = 0;
        this.p = this.z;
        this.h = this.v;
    }
    
    public void b(final float f) {
        this.f = f;
        this.invalidate();
    }
    
    public void b(final int j) {
        if (j > 0) {
            this.j = j;
            this.invalidate();
        }
    }
    
    public float c() {
        return this.l;
    }
    
    public void c(final int g) {
        this.g = g;
        this.invalidate();
    }
    
    public int d() {
        return this.i;
    }
    
    public void d(final int k) {
        this.k = k;
        this.invalidate();
    }
    
    public int e() {
        return this.j;
    }
    
    public float f() {
        return this.f;
    }
    
    public int g() {
        return this.g;
    }
    
    public int h() {
        return this.k;
    }
    
    public String i() {
        return this.o;
    }
    
    public void invalidate() {
        this.a();
        super.invalidate();
    }
    
    public String j() {
        return this.n;
    }
    
    public int k() {
        return this.m;
    }
    
    public String l() {
        return this.q;
    }
    
    public float m() {
        return this.p;
    }
    
    public int n() {
        return this.h;
    }
    
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        final float l = this.l;
        this.e.set(l, l, this.getWidth() - l, this.getHeight() - l);
        canvas.drawCircle(this.getWidth() / 2.0f, this.getHeight() / 2.0f, (this.getWidth() - this.l + this.l) / 2.0f, this.d);
        canvas.drawArc(this.e, 270.0f, -this.o(), false, this.c);
        final String string = this.n + this.i + this.o;
        if (!TextUtils.isEmpty((CharSequence)string)) {
            canvas.drawText(string, (this.getWidth() - this.a.measureText(string)) / 2.0f, (this.getWidth() - (this.a.descent() + this.a.ascent())) / 2.0f, this.a);
        }
        if (!TextUtils.isEmpty((CharSequence)this.l())) {
            this.b.setTextSize(this.p);
            canvas.drawText(this.l(), (this.getWidth() - this.b.measureText(this.l())) / 2.0f, this.getHeight() - this.r - (this.a.descent() + this.a.ascent()) / 2.0f, this.b);
        }
    }
    
    protected void onMeasure(final int n, final int n2) {
        this.setMeasuredDimension(this.e(n), this.e(n2));
        this.r = (float)(this.getHeight() - this.getHeight() * 3 / 4);
    }
    
    protected void onRestoreInstanceState(final Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            final Bundle bundle = (Bundle)parcelable;
            this.g = bundle.getInt("text_color");
            this.f = bundle.getFloat("text_size");
            this.p = bundle.getFloat("inner_bottom_text_size");
            this.q = bundle.getString("inner_bottom_text");
            this.h = bundle.getInt("inner_bottom_text_color");
            this.k = bundle.getInt("finished_stroke_color");
            this.l = bundle.getFloat("finished_stroke_width");
            this.m = bundle.getInt("inner_background_color");
            this.a();
            this.b(bundle.getInt("max"));
            this.a(bundle.getInt("progress"));
            this.n = bundle.getString("prefix");
            this.o = bundle.getString("suffix");
            super.onRestoreInstanceState(bundle.getParcelable("saved_instance"));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }
    
    protected Parcelable onSaveInstanceState() {
        final Bundle bundle = new Bundle();
        bundle.putParcelable("saved_instance", super.onSaveInstanceState());
        bundle.putInt("text_color", this.g());
        bundle.putFloat("text_size", this.f());
        bundle.putFloat("inner_bottom_text_size", this.m());
        bundle.putFloat("inner_bottom_text_color", (float)this.n());
        bundle.putString("inner_bottom_text", this.l());
        bundle.putInt("inner_bottom_text_color", this.n());
        bundle.putInt("finished_stroke_color", this.h());
        bundle.putInt("max", this.e());
        bundle.putInt("progress", this.d());
        bundle.putString("suffix", this.i());
        bundle.putString("prefix", this.j());
        bundle.putFloat("finished_stroke_width", this.c());
        bundle.putInt("inner_background_color", this.k());
        return (Parcelable)bundle;
    }
}
