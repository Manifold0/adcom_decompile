package com.facebook.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.facebook.ads.internal.p017t.C2103c;
import com.facebook.ads.internal.p017t.C2103c.C1795a;
import com.facebook.ads.internal.p017t.C2114e;
import java.lang.ref.WeakReference;

public final class DefaultMediaViewVideoRenderer extends MediaViewVideoRenderer {
    @Nullable
    /* renamed from: d */
    private C2103c f3737d;

    /* renamed from: com.facebook.ads.DefaultMediaViewVideoRenderer$1 */
    class C17941 implements OnTouchListener {
        /* renamed from: a */
        final /* synthetic */ DefaultMediaViewVideoRenderer f3722a;

        C17941(DefaultMediaViewVideoRenderer defaultMediaViewVideoRenderer) {
            this.f3722a = defaultMediaViewVideoRenderer;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            return true;
        }
    }

    /* renamed from: com.facebook.ads.DefaultMediaViewVideoRenderer$a */
    private static class C1796a implements C1795a {
        /* renamed from: a */
        private WeakReference<C2114e> f3723a;

        C1796a(C2114e c2114e) {
            this.f3723a = new WeakReference(c2114e);
        }

        /* renamed from: a */
        public void mo5338a(boolean z) {
            if (this.f3723a.get() != null) {
                ((C2114e) this.f3723a.get()).m5321a(z, false);
            }
        }
    }

    public DefaultMediaViewVideoRenderer(Context context) {
        super(context);
        this.f3737d = new C2103c(context, this);
        setVolume(0.0f);
    }

    public DefaultMediaViewVideoRenderer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3737d = new C2103c(context, this);
        setVolume(0.0f);
    }

    public DefaultMediaViewVideoRenderer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3737d = new C2103c(context, this);
        setVolume(0.0f);
    }

    @TargetApi(21)
    public DefaultMediaViewVideoRenderer(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.f3737d = new C2103c(context, this);
        setVolume(0.0f);
    }

    /* renamed from: a */
    protected void mo5339a() {
        super.mo5339a();
        if (this.f3737d != null) {
            this.f3737d.m5249a();
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f3737d != null) {
            this.f3737d.m5253c();
        }
    }

    protected void onDetachedFromWindow() {
        if (this.f3737d != null) {
            this.f3737d.m5254d();
        }
        super.onDetachedFromWindow();
    }

    public void onPrepared() {
        super.onPrepared();
        setOnTouchListener(new C17941(this));
        if (this.f3737d != null) {
            this.f3737d.m5252b();
        }
    }

    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (this.f3737d != null) {
            this.f3737d.m5255e();
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (this.f3737d != null) {
            this.f3737d.m5256f();
        }
    }

    protected void setNativeAd(NativeAd nativeAd) {
        super.setNativeAd(nativeAd);
        if (this.f3737d != null) {
            this.f3737d.m5251a(nativeAd.m4078f(), new C1796a(nativeAd.m4078f()));
        }
    }
}
