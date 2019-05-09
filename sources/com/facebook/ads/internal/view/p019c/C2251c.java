package com.facebook.ads.internal.view.p019c;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import com.facebook.ads.internal.p029x.C2630a;
import com.facebook.ads.internal.settings.AdSdkVersion;
import com.facebook.internal.AnalyticsEvents;
import java.lang.ref.WeakReference;

/* renamed from: com.facebook.ads.internal.view.c.c */
public class C2251c extends Drawable {
    /* renamed from: a */
    private final Paint f5262a = new Paint();
    /* renamed from: b */
    private final Paint f5263b = new Paint();
    /* renamed from: c */
    private final Path f5264c = new Path();
    /* renamed from: d */
    private final TextPaint f5265d = new TextPaint();
    /* renamed from: e */
    private final Paint f5266e = new Paint();
    /* renamed from: f */
    private int f5267f;
    /* renamed from: g */
    private int f5268g;
    /* renamed from: h */
    private String f5269h;
    /* renamed from: i */
    private int f5270i;
    /* renamed from: j */
    private boolean f5271j;
    @Nullable
    /* renamed from: k */
    private String f5272k;
    /* renamed from: l */
    private String f5273l;
    /* renamed from: m */
    private long f5274m;
    /* renamed from: n */
    private final Handler f5275n = new Handler();
    @Nullable
    /* renamed from: o */
    private WeakReference<C2630a> f5276o;
    /* renamed from: p */
    private final Runnable f5277p = new C22501(this);

    /* renamed from: com.facebook.ads.internal.view.c.c$1 */
    class C22501 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C2251c f5261a;

        C22501(C2251c c2251c) {
            this.f5261a = c2251c;
        }

        public void run() {
            this.f5261a.m5761c();
            if (this.f5261a.f5271j) {
                this.f5261a.f5275n.postDelayed(this.f5261a.f5277p, 250);
            }
        }
    }

    public C2251c() {
        this.f5262a.setColor(Color.argb(127, 36, 36, 36));
        this.f5262a.setStyle(Style.FILL_AND_STROKE);
        this.f5263b.setAntiAlias(true);
        this.f5263b.setColor(Color.argb(191, 0, 255, 0));
        this.f5263b.setStrokeWidth(20.0f);
        this.f5263b.setStyle(Style.STROKE);
        this.f5265d.setAntiAlias(true);
        this.f5265d.setColor(-1);
        this.f5265d.setStyle(Style.FILL_AND_STROKE);
        this.f5265d.setTextSize(30.0f);
        this.f5266e.setColor(Color.argb(212, 0, 0, 0));
        this.f5266e.setStyle(Style.FILL_AND_STROKE);
        m5769b();
    }

    /* renamed from: c */
    private void m5761c() {
        int i;
        StringBuilder stringBuilder = new StringBuilder();
        if (this.f5267f <= 0) {
            if (!TextUtils.isEmpty(this.f5272k)) {
                stringBuilder.append(this.f5272k);
                stringBuilder.append("\n");
            }
            if (!TextUtils.isEmpty(this.f5273l)) {
                stringBuilder.append(this.f5273l);
                stringBuilder.append("\n");
            }
            stringBuilder.append("Sdk ");
            stringBuilder.append(AdSdkVersion.BUILD);
            stringBuilder.append(", Loaded ");
            if (this.f5274m > 0) {
                long max = Math.max(0, System.currentTimeMillis() - this.f5274m);
                i = (int) (max / 3600000);
                max %= 3600000;
                int i2 = (int) (max / 60000);
                int i3 = (int) ((max % 60000) / 1000);
                if (i > 0) {
                    stringBuilder.append(i);
                    stringBuilder.append("h ");
                }
                if (i > 0 || i2 > 0) {
                    stringBuilder.append(i2);
                    stringBuilder.append("m ");
                }
                stringBuilder.append(i3);
                stringBuilder.append("s ago");
            } else {
                stringBuilder.append(AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN);
            }
        } else {
            stringBuilder.append("Card ");
            stringBuilder.append(this.f5268g + 1);
            stringBuilder.append(" of ");
            stringBuilder.append(this.f5267f);
        }
        stringBuilder.append("\nView: ");
        if (this.f5276o == null || this.f5276o.get() == null) {
            stringBuilder.append("Viewability Checker not set");
        } else {
            stringBuilder.append(((C2630a) this.f5276o.get()).m6775d());
        }
        this.f5269h = stringBuilder.toString();
        float f = -2.14748365E9f;
        for (String str : this.f5269h.split("\n")) {
            f = Math.max(f, this.f5265d.measureText(str, 0, str.length()));
        }
        this.f5270i = (int) (0.5f + f);
        invalidateSelf();
    }

    /* renamed from: a */
    public void m5763a(int i, int i2) {
        this.f5267f = i;
        this.f5268g = i2;
        m5761c();
    }

    /* renamed from: a */
    public void m5764a(long j) {
        this.f5274m = j;
        m5761c();
    }

    /* renamed from: a */
    public void m5765a(C2630a c2630a) {
        this.f5276o = new WeakReference(c2630a);
        m5761c();
    }

    /* renamed from: a */
    public void m5766a(String str) {
        this.f5272k = str;
        m5761c();
    }

    /* renamed from: a */
    public void m5767a(boolean z) {
        this.f5271j = z;
        if (this.f5271j) {
            this.f5275n.post(this.f5277p);
        } else {
            this.f5275n.removeCallbacks(this.f5277p);
        }
        invalidateSelf();
    }

    /* renamed from: a */
    public boolean m5768a() {
        return this.f5271j;
    }

    /* renamed from: b */
    public void m5769b() {
        this.f5267f = 0;
        this.f5268g = -1;
        this.f5269h = "Initializing...";
        this.f5270i = 100;
        this.f5272k = null;
        this.f5274m = -1;
        this.f5276o = null;
        m5767a(false);
    }

    /* renamed from: b */
    public void m5770b(String str) {
        this.f5273l = str;
        m5761c();
    }

    public void draw(Canvas canvas) {
        if (this.f5271j) {
            int width = canvas.getWidth();
            int height = canvas.getHeight();
            canvas.drawRect(0.0f, 0.0f, (float) width, (float) height, this.f5262a);
            StaticLayout staticLayout = new StaticLayout(this.f5269h, this.f5265d, this.f5270i, Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
            float f = ((float) width) / 2.0f;
            float f2 = ((float) height) / 2.0f;
            float width2 = ((float) staticLayout.getWidth()) / 2.0f;
            float height2 = ((float) staticLayout.getHeight()) / 2.0f;
            canvas.drawRect((f - width2) - 40.0f, (f2 - height2) - 40.0f, 40.0f + (f + width2), 40.0f + (f2 + height2), this.f5266e);
            canvas.save();
            canvas.translate(f - width2, f2 - height2);
            staticLayout.draw(canvas);
            canvas.restore();
            this.f5264c.reset();
            this.f5264c.moveTo(0.0f, 0.0f);
            this.f5264c.lineTo((float) width, 0.0f);
            this.f5264c.lineTo((float) width, (float) height);
            this.f5264c.lineTo(0.0f, (float) height);
            this.f5264c.lineTo(0.0f, 0.0f);
            canvas.drawPath(this.f5264c, this.f5263b);
        }
    }

    public int getOpacity() {
        return -3;
    }

    public void setAlpha(@IntRange(from = 0, to = 255) int i) {
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
    }
}
