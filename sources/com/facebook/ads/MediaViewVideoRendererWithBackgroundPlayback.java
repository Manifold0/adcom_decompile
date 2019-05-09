package com.facebook.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.facebook.ads.internal.p017t.C2103c;

public final class MediaViewVideoRendererWithBackgroundPlayback extends MediaViewVideoRenderer {
    /* renamed from: d */
    private C2103c f3775d;

    /* renamed from: com.facebook.ads.MediaViewVideoRendererWithBackgroundPlayback$1 */
    class C18241 implements OnTouchListener {
        /* renamed from: a */
        final /* synthetic */ MediaViewVideoRendererWithBackgroundPlayback f3774a;

        C18241(MediaViewVideoRendererWithBackgroundPlayback mediaViewVideoRendererWithBackgroundPlayback) {
            this.f3774a = mediaViewVideoRendererWithBackgroundPlayback;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            return true;
        }
    }

    public MediaViewVideoRendererWithBackgroundPlayback(Context context) {
        super(context);
        this.f3775d = new C2103c(context, this);
        setVolume(1.0f);
    }

    public MediaViewVideoRendererWithBackgroundPlayback(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3775d = new C2103c(context, this);
        setVolume(1.0f);
    }

    public MediaViewVideoRendererWithBackgroundPlayback(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3775d = new C2103c(context, this);
        setVolume(1.0f);
    }

    @TargetApi(21)
    public MediaViewVideoRendererWithBackgroundPlayback(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.f3775d = new C2103c(context, this);
        setVolume(1.0f);
    }

    /* renamed from: a */
    protected void mo5339a() {
        super.mo5339a();
        this.f3775d.m5249a();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f3775d.m5253c();
    }

    protected void onDetachedFromWindow() {
        this.f3775d.m5254d();
        super.onDetachedFromWindow();
    }

    public void onPrepared() {
        super.onPrepared();
        setOnTouchListener(new C18241(this));
        this.f3775d.m5252b();
    }

    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        this.f3775d.m5255e();
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        this.f3775d.m5256f();
    }

    protected void setNativeAd(NativeAd nativeAd) {
        super.setNativeAd(nativeAd);
        this.f3775d.m5250a(nativeAd.m4078f());
    }

    public boolean shouldAllowBackgroundPlayback() {
        return true;
    }
}
