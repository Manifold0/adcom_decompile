package com.applovin.impl.adview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.NotificationCompat;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import com.kongregate.p000o.p003a.C0583e;

public class ai extends View {
    /* renamed from: A */
    private final int f1734A;
    /* renamed from: a */
    protected Paint f1735a;
    /* renamed from: b */
    protected Paint f1736b;
    /* renamed from: c */
    private Paint f1737c;
    /* renamed from: d */
    private Paint f1738d;
    /* renamed from: e */
    private RectF f1739e;
    /* renamed from: f */
    private float f1740f;
    /* renamed from: g */
    private int f1741g;
    /* renamed from: h */
    private int f1742h;
    /* renamed from: i */
    private int f1743i;
    /* renamed from: j */
    private int f1744j;
    /* renamed from: k */
    private int f1745k;
    /* renamed from: l */
    private float f1746l;
    /* renamed from: m */
    private int f1747m;
    /* renamed from: n */
    private String f1748n;
    /* renamed from: o */
    private String f1749o;
    /* renamed from: p */
    private float f1750p;
    /* renamed from: q */
    private String f1751q;
    /* renamed from: r */
    private float f1752r;
    /* renamed from: s */
    private final float f1753s;
    /* renamed from: t */
    private final int f1754t;
    /* renamed from: u */
    private final int f1755u;
    /* renamed from: v */
    private final int f1756v;
    /* renamed from: w */
    private final int f1757w;
    /* renamed from: x */
    private final int f1758x;
    /* renamed from: y */
    private final float f1759y;
    /* renamed from: z */
    private final float f1760z;

    public ai(Context context) {
        this(context, null);
    }

    public ai(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ai(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1739e = new RectF();
        this.f1743i = 0;
        this.f1748n = "";
        this.f1749o = "";
        this.f1751q = "";
        this.f1754t = Color.rgb(66, 145, 241);
        this.f1755u = Color.rgb(66, 145, 241);
        this.f1756v = Color.rgb(66, 145, 241);
        this.f1757w = 0;
        this.f1758x = 100;
        this.f1759y = aj.m2006b(getResources(), 14.0f);
        this.f1734A = (int) aj.m2005a(getResources(), 100.0f);
        this.f1753s = aj.m2005a(getResources(), 4.0f);
        this.f1760z = aj.m2006b(getResources(), 18.0f);
        m1988b();
        m1985a();
    }

    /* renamed from: e */
    private int m1983e(int i) {
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        int i2 = this.f1734A;
        return mode == Integer.MIN_VALUE ? Math.min(i2, size) : i2;
    }

    /* renamed from: o */
    private float m1984o() {
        return (((float) m1993d()) / ((float) this.f1744j)) * 360.0f;
    }

    /* renamed from: a */
    protected void m1985a() {
        this.f1735a = new TextPaint();
        this.f1735a.setColor(this.f1741g);
        this.f1735a.setTextSize(this.f1740f);
        this.f1735a.setAntiAlias(true);
        this.f1736b = new TextPaint();
        this.f1736b.setColor(this.f1742h);
        this.f1736b.setTextSize(this.f1750p);
        this.f1736b.setAntiAlias(true);
        this.f1737c = new Paint();
        this.f1737c.setColor(this.f1745k);
        this.f1737c.setStyle(Style.STROKE);
        this.f1737c.setAntiAlias(true);
        this.f1737c.setStrokeWidth(this.f1746l);
        this.f1738d = new Paint();
        this.f1738d.setColor(this.f1747m);
        this.f1738d.setAntiAlias(true);
    }

    /* renamed from: a */
    public void m1986a(float f) {
        this.f1746l = f;
        invalidate();
    }

    /* renamed from: a */
    public void m1987a(int i) {
        this.f1743i = i;
        if (this.f1743i > m1995e()) {
            this.f1743i %= m1995e();
        }
        invalidate();
    }

    /* renamed from: b */
    protected void m1988b() {
        this.f1745k = this.f1754t;
        this.f1741g = this.f1755u;
        this.f1740f = this.f1759y;
        m1990b(100);
        m1987a(0);
        this.f1746l = this.f1753s;
        this.f1747m = 0;
        this.f1750p = this.f1760z;
        this.f1742h = this.f1756v;
    }

    /* renamed from: b */
    public void m1989b(float f) {
        this.f1740f = f;
        invalidate();
    }

    /* renamed from: b */
    public void m1990b(int i) {
        if (i > 0) {
            this.f1744j = i;
            invalidate();
        }
    }

    /* renamed from: c */
    public float m1991c() {
        return this.f1746l;
    }

    /* renamed from: c */
    public void m1992c(int i) {
        this.f1741g = i;
        invalidate();
    }

    /* renamed from: d */
    public int m1993d() {
        return this.f1743i;
    }

    /* renamed from: d */
    public void m1994d(int i) {
        this.f1745k = i;
        invalidate();
    }

    /* renamed from: e */
    public int m1995e() {
        return this.f1744j;
    }

    /* renamed from: f */
    public float m1996f() {
        return this.f1740f;
    }

    /* renamed from: g */
    public int m1997g() {
        return this.f1741g;
    }

    /* renamed from: h */
    public int m1998h() {
        return this.f1745k;
    }

    /* renamed from: i */
    public String m1999i() {
        return this.f1749o;
    }

    public void invalidate() {
        m1985a();
        super.invalidate();
    }

    /* renamed from: j */
    public String m2000j() {
        return this.f1748n;
    }

    /* renamed from: k */
    public int m2001k() {
        return this.f1747m;
    }

    /* renamed from: l */
    public String m2002l() {
        return this.f1751q;
    }

    /* renamed from: m */
    public float m2003m() {
        return this.f1750p;
    }

    /* renamed from: n */
    public int m2004n() {
        return this.f1742h;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f = this.f1746l;
        this.f1739e.set(f, f, ((float) getWidth()) - f, ((float) getHeight()) - f);
        canvas.drawCircle(((float) getWidth()) / 2.0f, ((float) getHeight()) / 2.0f, ((((float) getWidth()) - this.f1746l) + this.f1746l) / 2.0f, this.f1738d);
        canvas.drawArc(this.f1739e, 270.0f, -m1984o(), false, this.f1737c);
        String str = this.f1748n + this.f1743i + this.f1749o;
        if (!TextUtils.isEmpty(str)) {
            canvas.drawText(str, (((float) getWidth()) - this.f1735a.measureText(str)) / 2.0f, (((float) getWidth()) - (this.f1735a.descent() + this.f1735a.ascent())) / 2.0f, this.f1735a);
        }
        if (!TextUtils.isEmpty(m2002l())) {
            this.f1736b.setTextSize(this.f1750p);
            canvas.drawText(m2002l(), (((float) getWidth()) - this.f1736b.measureText(m2002l())) / 2.0f, (((float) getHeight()) - this.f1752r) - ((this.f1735a.descent() + this.f1735a.ascent()) / 2.0f), this.f1736b);
        }
    }

    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(m1983e(i), m1983e(i2));
        this.f1752r = (float) (getHeight() - ((getHeight() * 3) / 4));
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.f1741g = bundle.getInt("text_color");
            this.f1740f = bundle.getFloat("text_size");
            this.f1750p = bundle.getFloat("inner_bottom_text_size");
            this.f1751q = bundle.getString("inner_bottom_text");
            this.f1742h = bundle.getInt("inner_bottom_text_color");
            this.f1745k = bundle.getInt("finished_stroke_color");
            this.f1746l = bundle.getFloat("finished_stroke_width");
            this.f1747m = bundle.getInt("inner_background_color");
            m1985a();
            m1990b(bundle.getInt(C0583e.f803b));
            m1987a(bundle.getInt(NotificationCompat.CATEGORY_PROGRESS));
            this.f1748n = bundle.getString("prefix");
            this.f1749o = bundle.getString("suffix");
            super.onRestoreInstanceState(bundle.getParcelable("saved_instance"));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable bundle = new Bundle();
        bundle.putParcelable("saved_instance", super.onSaveInstanceState());
        bundle.putInt("text_color", m1997g());
        bundle.putFloat("text_size", m1996f());
        bundle.putFloat("inner_bottom_text_size", m2003m());
        bundle.putFloat("inner_bottom_text_color", (float) m2004n());
        bundle.putString("inner_bottom_text", m2002l());
        bundle.putInt("inner_bottom_text_color", m2004n());
        bundle.putInt("finished_stroke_color", m1998h());
        bundle.putInt(C0583e.f803b, m1995e());
        bundle.putInt(NotificationCompat.CATEGORY_PROGRESS, m1993d());
        bundle.putString("suffix", m1999i());
        bundle.putString("prefix", m2000j());
        bundle.putFloat("finished_stroke_width", m1991c());
        bundle.putInt("inner_background_color", m2001k());
        return bundle;
    }
}
