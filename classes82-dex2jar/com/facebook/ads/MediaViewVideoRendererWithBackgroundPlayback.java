// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads;

import android.view.MotionEvent;
import android.view.View$OnTouchListener;
import android.annotation.TargetApi;
import android.util.AttributeSet;
import android.view.View;
import android.content.Context;
import com.facebook.ads.internal.t.c;

public final class MediaViewVideoRendererWithBackgroundPlayback extends MediaViewVideoRenderer
{
    private c d;
    
    public MediaViewVideoRendererWithBackgroundPlayback(final Context context) {
        super(context);
        this.d = new c(context, (View)this);
        this.setVolume(1.0f);
    }
    
    public MediaViewVideoRendererWithBackgroundPlayback(final Context context, final AttributeSet set) {
        super(context, set);
        this.d = new c(context, (View)this);
        this.setVolume(1.0f);
    }
    
    public MediaViewVideoRendererWithBackgroundPlayback(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.d = new c(context, (View)this);
        this.setVolume(1.0f);
    }
    
    @TargetApi(21)
    public MediaViewVideoRendererWithBackgroundPlayback(final Context context, final AttributeSet set, final int n, final int n2) {
        super(context, set, n, n2);
        this.d = new c(context, (View)this);
        this.setVolume(1.0f);
    }
    
    @Override
    protected void a() {
        super.a();
        this.d.a();
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.d.c();
    }
    
    protected void onDetachedFromWindow() {
        this.d.d();
        super.onDetachedFromWindow();
    }
    
    @Override
    public void onPrepared() {
        super.onPrepared();
        this.setOnTouchListener((View$OnTouchListener)new View$OnTouchListener() {
            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                return true;
            }
        });
        this.d.b();
    }
    
    protected void onVisibilityChanged(final View view, final int n) {
        super.onVisibilityChanged(view, n);
        this.d.e();
    }
    
    public void onWindowFocusChanged(final boolean b) {
        super.onWindowFocusChanged(b);
        this.d.f();
    }
    
    @Override
    protected void setNativeAd(final NativeAd nativeAd) {
        super.setNativeAd(nativeAd);
        this.d.a(nativeAd.f());
    }
    
    @Override
    public boolean shouldAllowBackgroundPlayback() {
        return true;
    }
}
