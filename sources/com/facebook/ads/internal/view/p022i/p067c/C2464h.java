package com.facebook.ads.internal.view.p022i.p067c;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.view.p022i.C2394a;
import com.facebook.ads.internal.view.p022i.p023b.C1810n;
import com.facebook.ads.internal.view.p022i.p023b.C1812l;
import com.facebook.ads.internal.view.p022i.p023b.C1814j;
import com.facebook.ads.internal.view.p022i.p023b.C1818d;
import com.facebook.ads.internal.view.p022i.p023b.C2406c;
import com.facebook.ads.internal.view.p022i.p023b.C2410i;
import com.facebook.ads.internal.view.p022i.p023b.C2411k;
import com.facebook.ads.internal.view.p022i.p023b.C2412m;
import com.facebook.ads.internal.view.p022i.p065a.C2389a;
import com.facebook.ads.internal.view.p022i.p065a.C2391c;
import com.facebook.ads.internal.view.p022i.p066d.C2501d;

/* renamed from: com.facebook.ads.internal.view.i.c.h */
public class C2464h extends C2391c implements OnTouchListener {
    /* renamed from: a */
    private final C1810n f5946a;
    /* renamed from: b */
    private final C1814j f5947b;
    /* renamed from: c */
    private final C1812l f5948c;
    /* renamed from: d */
    private final C1818d f5949d;
    /* renamed from: e */
    private final C2483m f5950e;

    /* renamed from: com.facebook.ads.internal.view.i.c.h$1 */
    class C24601 extends C1810n {
        /* renamed from: a */
        final /* synthetic */ C2464h f5942a;

        C24601(C2464h c2464h) {
            this.f5942a = c2464h;
        }

        /* renamed from: a */
        public void m6328a(C2412m c2412m) {
            this.f5942a.setVisibility(0);
        }
    }

    /* renamed from: com.facebook.ads.internal.view.i.c.h$2 */
    class C24612 extends C1814j {
        /* renamed from: a */
        final /* synthetic */ C2464h f5943a;

        C24612(C2464h c2464h) {
            this.f5943a = c2464h;
        }

        /* renamed from: a */
        public void m6330a(C2410i c2410i) {
            this.f5943a.f5950e.setChecked(true);
        }
    }

    /* renamed from: com.facebook.ads.internal.view.i.c.h$3 */
    class C24623 extends C1812l {
        /* renamed from: a */
        final /* synthetic */ C2464h f5944a;

        C24623(C2464h c2464h) {
            this.f5944a = c2464h;
        }

        /* renamed from: a */
        public void m6332a(C2411k c2411k) {
            this.f5944a.f5950e.setChecked(false);
        }
    }

    /* renamed from: com.facebook.ads.internal.view.i.c.h$4 */
    class C24634 extends C1818d {
        /* renamed from: a */
        final /* synthetic */ C2464h f5945a;

        C24634(C2464h c2464h) {
            this.f5945a = c2464h;
        }

        /* renamed from: a */
        public void m6334a(C2406c c2406c) {
            this.f5945a.f5950e.setChecked(true);
        }
    }

    public C2464h(Context context) {
        this(context, null);
    }

    public C2464h(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public C2464h(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f5946a = new C24601(this);
        this.f5947b = new C24612(this);
        this.f5948c = new C24623(this);
        this.f5949d = new C24634(this);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.f5950e = new C2483m(context);
        this.f5950e.setChecked(true);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (displayMetrics.density * 25.0f), (int) (displayMetrics.density * 25.0f));
        setVisibility(8);
        addView(this.f5950e, layoutParams);
        setClickable(true);
        setFocusable(true);
    }

    /* renamed from: a */
    protected void mo5608a() {
        super.mo5608a();
        this.f5950e.setOnTouchListener(this);
        setOnTouchListener(this);
        if (getVideoView() != null) {
            getVideoView().getEventBus().m5029a(this.f5946a, this.f5949d, this.f5947b, this.f5948c);
        }
    }

    /* renamed from: b */
    protected void mo5609b() {
        if (getVideoView() != null) {
            getVideoView().getEventBus().m5031b(this.f5948c, this.f5947b, this.f5949d, this.f5946a);
        }
        setOnTouchListener(null);
        this.f5950e.setOnTouchListener(null);
        super.mo5609b();
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 1) {
            return false;
        }
        C2394a videoView = getVideoView();
        if (videoView == null) {
            return false;
        }
        if (videoView.getState() == C2501d.PREPARED || videoView.getState() == C2501d.PAUSED || videoView.getState() == C2501d.PLAYBACK_COMPLETED) {
            videoView.m6157a(C2389a.USER_STARTED);
            return true;
        } else if (videoView.getState() != C2501d.STARTED) {
            return false;
        } else {
            videoView.m6160a(true);
            return false;
        }
    }
}
