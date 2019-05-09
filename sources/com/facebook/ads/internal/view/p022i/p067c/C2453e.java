package com.facebook.ads.internal.view.p022i.p067c;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.net.Uri;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p027a.C1842b;
import com.facebook.ads.internal.p027a.C1843c;
import com.facebook.ads.internal.p051s.C2085c;
import com.facebook.ads.internal.view.p022i.p023b.C2405b;
import com.facebook.ads.internal.view.p022i.p065a.C2391c;
import java.util.HashMap;

/* renamed from: com.facebook.ads.internal.view.i.c.e */
public class C2453e extends C2391c {
    /* renamed from: a */
    private final String f5925a;
    /* renamed from: b */
    private final TextView f5926b = new TextView(getContext());
    /* renamed from: c */
    private final C2085c f5927c;
    /* renamed from: d */
    private final String f5928d;
    /* renamed from: e */
    private final Paint f5929e;
    /* renamed from: f */
    private final RectF f5930f;

    /* renamed from: com.facebook.ads.internal.view.i.c.e$1 */
    class C24521 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C2453e f5924a;

        C24521(C2453e c2453e) {
            this.f5924a = c2453e;
        }

        public void onClick(View view) {
            if (this.f5924a.getVideoView() != null) {
                Uri parse = Uri.parse(this.f5924a.f5925a);
                this.f5924a.getVideoView().getEventBus().m5028a(new C2405b(parse));
                C1842b a = C1843c.m4142a(this.f5924a.getContext(), this.f5924a.f5927c, this.f5924a.f5928d, parse, new HashMap());
                if (a != null) {
                    a.mo5376a();
                }
            }
        }
    }

    public C2453e(Context context, String str, C2085c c2085c, String str2, String str3) {
        super(context);
        this.f5925a = str;
        this.f5927c = c2085c;
        this.f5928d = str2;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.f5926b.setTextColor(-3355444);
        this.f5926b.setTextSize(16.0f);
        this.f5926b.setPadding((int) (displayMetrics.density * 6.0f), (int) (displayMetrics.density * 4.0f), (int) (displayMetrics.density * 6.0f), (int) (displayMetrics.density * 4.0f));
        this.f5929e = new Paint();
        this.f5929e.setStyle(Style.FILL);
        this.f5929e.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.f5929e.setAlpha(178);
        this.f5930f = new RectF();
        C2600x.m6680a((View) this, 0);
        this.f5926b.setText(str3);
        addView(this.f5926b, new LayoutParams(-2, -2));
    }

    /* renamed from: a */
    protected void mo5608a() {
        super.mo5608a();
        this.f5926b.setOnClickListener(new C24521(this));
    }

    /* renamed from: b */
    protected void mo5609b() {
        this.f5926b.setOnClickListener(null);
        super.mo5609b();
    }

    protected void onDraw(Canvas canvas) {
        this.f5930f.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
        canvas.drawRoundRect(this.f5930f, 0.0f, 0.0f, this.f5929e);
        super.onDraw(canvas);
    }
}
