package com.facebook.ads.internal.view.p022i.p067c;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.p021o.C1809f;
import com.facebook.ads.internal.view.p022i.p023b.C2412m;
import com.facebook.ads.internal.view.p022i.p065a.C2391c;

/* renamed from: com.facebook.ads.internal.view.i.c.k */
public class C2475k extends C2391c {
    /* renamed from: a */
    private final C1809f<C2412m> f5982a;

    /* renamed from: com.facebook.ads.internal.view.i.c.k$1 */
    class C24741 extends C1809f<C2412m> {
        /* renamed from: a */
        final /* synthetic */ C2475k f5981a;

        C24741(C2475k c2475k) {
            this.f5981a = c2475k;
        }

        /* renamed from: a */
        public Class<C2412m> mo5359a() {
            return C2412m.class;
        }

        /* renamed from: a */
        public void m6368a(C2412m c2412m) {
            this.f5981a.setVisibility(8);
        }
    }

    public C2475k(Context context) {
        this(context, null);
    }

    public C2475k(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public C2475k(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f5982a = new C24741(this);
        int applyDimension = (int) TypedValue.applyDimension(1, 40.0f, getResources().getDisplayMetrics());
        View progressBar = new ProgressBar(getContext());
        progressBar.setIndeterminate(true);
        progressBar.getIndeterminateDrawable().setColorFilter(-1, Mode.SRC_IN);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(applyDimension, applyDimension);
        layoutParams.addRule(13);
        addView(progressBar, layoutParams);
    }

    /* renamed from: a */
    protected void mo5608a() {
        super.mo5608a();
        setVisibility(0);
        if (getVideoView() != null) {
            getVideoView().getEventBus().m5030a(this.f5982a);
        }
    }

    /* renamed from: b */
    protected void mo5609b() {
        if (getVideoView() != null) {
            getVideoView().getEventBus().m5032b(this.f5982a);
        }
        setVisibility(8);
        super.mo5609b();
    }
}
