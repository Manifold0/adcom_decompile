package com.facebook.ads.internal.view.p022i.p067c;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.applovin.sdk.AppLovinErrorCodes;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.view.p022i.p023b.C1812l;
import com.facebook.ads.internal.view.p022i.p023b.C1814j;
import com.facebook.ads.internal.view.p022i.p023b.C1818d;
import com.facebook.ads.internal.view.p022i.p023b.C2406c;
import com.facebook.ads.internal.view.p022i.p023b.C2410i;
import com.facebook.ads.internal.view.p022i.p023b.C2411k;
import com.facebook.ads.internal.view.p022i.p065a.C2389a;
import com.facebook.ads.internal.view.p022i.p065a.C2391c;

/* renamed from: com.facebook.ads.internal.view.i.c.l */
public class C2481l extends C2391c {
    /* renamed from: a */
    private final C1814j f5988a;
    /* renamed from: b */
    private final C1812l f5989b;
    /* renamed from: c */
    private final C1818d f5990c;
    /* renamed from: d */
    private final C2483m f5991d;
    /* renamed from: e */
    private final Paint f5992e;

    /* renamed from: com.facebook.ads.internal.view.i.c.l$1 */
    class C24761 extends C1814j {
        /* renamed from: a */
        final /* synthetic */ C2481l f5983a;

        C24761(C2481l c2481l) {
            this.f5983a = c2481l;
        }

        /* renamed from: a */
        public void m6372a(C2410i c2410i) {
            this.f5983a.f5991d.setChecked(true);
        }
    }

    /* renamed from: com.facebook.ads.internal.view.i.c.l$2 */
    class C24772 extends C1812l {
        /* renamed from: a */
        final /* synthetic */ C2481l f5984a;

        C24772(C2481l c2481l) {
            this.f5984a = c2481l;
        }

        /* renamed from: a */
        public void m6374a(C2411k c2411k) {
            this.f5984a.f5991d.setChecked(false);
        }
    }

    /* renamed from: com.facebook.ads.internal.view.i.c.l$3 */
    class C24783 extends C1818d {
        /* renamed from: a */
        final /* synthetic */ C2481l f5985a;

        C24783(C2481l c2481l) {
            this.f5985a = c2481l;
        }

        /* renamed from: a */
        public void m6376a(C2406c c2406c) {
            this.f5985a.f5991d.setChecked(true);
        }
    }

    /* renamed from: com.facebook.ads.internal.view.i.c.l$4 */
    class C24794 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C2481l f5986a;

        C24794(C2481l c2481l) {
            this.f5986a = c2481l;
        }

        public void onClick(View view) {
            if (this.f5986a.getVideoView() != null) {
                switch (this.f5986a.getVideoView().getState()) {
                    case PREPARED:
                    case IDLE:
                    case PAUSED:
                    case PLAYBACK_COMPLETED:
                        this.f5986a.getVideoView().m6157a(C2389a.USER_STARTED);
                        return;
                    case STARTED:
                        this.f5986a.getVideoView().m6160a(true);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public C2481l(Context context) {
        this(context, false);
    }

    public C2481l(Context context, boolean z) {
        super(context);
        this.f5988a = new C24761(this);
        this.f5989b = new C24772(this);
        this.f5990c = new C24783(this);
        this.f5991d = new C2483m(context, z);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (((double) displayMetrics.density) * 23.76d), (int) (((double) displayMetrics.density) * 23.76d));
        layoutParams.addRule(13);
        this.f5991d.setLayoutParams(layoutParams);
        this.f5991d.setChecked(true);
        this.f5991d.setClickable(false);
        this.f5992e = new Paint();
        this.f5992e.setStyle(Style.FILL);
        if (z) {
            this.f5992e.setColor(-1728053248);
        } else {
            this.f5992e.setColor(-1);
            this.f5992e.setAlpha(AppLovinErrorCodes.NO_FILL);
        }
        C2600x.m6680a((View) this, 0);
        addView(this.f5991d);
        setGravity(17);
        layoutParams = new RelativeLayout.LayoutParams((int) (((double) displayMetrics.density) * 72.0d), (int) (((double) displayMetrics.density) * 72.0d));
        layoutParams.addRule(13);
        setLayoutParams(layoutParams);
    }

    /* renamed from: a */
    protected void mo5608a() {
        super.mo5608a();
        if (getVideoView() != null) {
            getVideoView().getEventBus().m5029a(this.f5988a, this.f5989b, this.f5990c);
        }
        setOnClickListener(new C24794(this));
    }

    /* renamed from: b */
    protected void mo5609b() {
        setOnClickListener(null);
        if (getVideoView() != null) {
            getVideoView().getEventBus().m5031b(this.f5990c, this.f5989b, this.f5988a);
        }
        super.mo5609b();
    }

    protected void onDraw(Canvas canvas) {
        int min = Math.min((getWidth() - getPaddingLeft()) - getPaddingRight(), (getHeight() - getPaddingTop()) - getPaddingBottom());
        int i = min / 2;
        canvas.drawCircle((float) (getPaddingLeft() + i), (float) ((min / 2) + getPaddingTop()), (float) i, this.f5992e);
        super.onDraw(canvas);
    }
}
