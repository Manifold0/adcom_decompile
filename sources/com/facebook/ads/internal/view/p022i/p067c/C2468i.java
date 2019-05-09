package com.facebook.ads.internal.view.p022i.p067c;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.internal.p021o.C1809f;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.view.p022i.p023b.C2413o;
import com.facebook.ads.internal.view.p022i.p065a.C2391c;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: com.facebook.ads.internal.view.i.c.i */
public class C2468i extends C2391c {
    /* renamed from: a */
    private final C2467a f5956a;
    /* renamed from: b */
    private final int f5957b;
    /* renamed from: c */
    private final String f5958c;
    /* renamed from: d */
    private final String f5959d;
    /* renamed from: e */
    private final AtomicBoolean f5960e;
    /* renamed from: f */
    private final C1809f<C2413o> f5961f = new C24651(this);

    /* renamed from: com.facebook.ads.internal.view.i.c.i$1 */
    class C24651 extends C1809f<C2413o> {
        /* renamed from: a */
        final /* synthetic */ C2468i f5951a;

        C24651(C2468i c2468i) {
            this.f5951a = c2468i;
        }

        /* renamed from: a */
        public Class<C2413o> mo5359a() {
            return C2413o.class;
        }

        /* renamed from: a */
        public void m6340a(C2413o c2413o) {
            if (!this.f5951a.f5960e.get() && this.f5951a.getVideoView() != null) {
                int c = this.f5951a.f5957b - (this.f5951a.getVideoView().getCurrentPositionInMillis() / 1000);
                if (c > 0) {
                    this.f5951a.f5956a.setText(this.f5951a.f5958c + ' ' + c);
                    return;
                }
                this.f5951a.f5956a.setText(this.f5951a.f5959d);
                this.f5951a.f5960e.set(true);
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.i.c.i$2 */
    class C24662 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C2468i f5952a;

        C24662(C2468i c2468i) {
            this.f5952a = c2468i;
        }

        public void onClick(View view) {
            if (!this.f5952a.f5960e.get()) {
                Log.i("SkipPlugin", "User clicked skip before the ads is allowed to skip.");
            } else if (this.f5952a.getVideoView() != null) {
                this.f5952a.getVideoView().m6164f();
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.i.c.i$a */
    private static class C2467a extends TextView {
        /* renamed from: a */
        private final Paint f5953a = new Paint();
        /* renamed from: b */
        private final Paint f5954b;
        /* renamed from: c */
        private final RectF f5955c;

        public C2467a(Context context) {
            super(context);
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            C2600x.m6680a((View) this, 0);
            setTextColor(-3355444);
            setPadding((int) (displayMetrics.density * 9.0f), (int) (displayMetrics.density * 5.0f), (int) (displayMetrics.density * 9.0f), (int) (displayMetrics.density * 5.0f));
            setTextSize(18.0f);
            this.f5953a.setStyle(Style.STROKE);
            this.f5953a.setColor(-10066330);
            this.f5953a.setStrokeWidth(1.0f);
            this.f5953a.setAntiAlias(true);
            this.f5954b = new Paint();
            this.f5954b.setStyle(Style.FILL);
            this.f5954b.setColor(-1895825408);
            this.f5955c = new RectF();
        }

        protected void onDraw(Canvas canvas) {
            if (getText().length() != 0) {
                int width = getWidth();
                int height = getHeight();
                this.f5955c.set((float) null, (float) null, (float) width, (float) height);
                canvas.drawRoundRect(this.f5955c, 6.0f, 6.0f, this.f5954b);
                this.f5955c.set((float) 2, (float) 2, (float) (width - 2), (float) (height - 2));
                canvas.drawRoundRect(this.f5955c, 6.0f, 6.0f, this.f5953a);
                super.onDraw(canvas);
            }
        }
    }

    public C2468i(Context context, int i, String str, String str2) {
        super(context);
        this.f5957b = i;
        this.f5958c = str;
        this.f5959d = str2;
        this.f5960e = new AtomicBoolean(false);
        this.f5956a = new C2467a(context);
        this.f5956a.setText(this.f5958c + ' ' + i);
        addView(this.f5956a, new LayoutParams(-2, -2));
    }

    /* renamed from: a */
    public void mo5608a() {
        super.mo5608a();
        if (getVideoView() != null) {
            getVideoView().getEventBus().m5030a(this.f5961f);
        }
        this.f5956a.setOnClickListener(new C24662(this));
    }

    /* renamed from: b */
    public void mo5609b() {
        if (getVideoView() != null) {
            this.f5956a.setOnClickListener(null);
            getVideoView().getEventBus().m5032b(this.f5961f);
        }
        super.mo5609b();
    }
}
