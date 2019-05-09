// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads;

import com.facebook.ads.internal.t.e;
import java.lang.ref.WeakReference;
import android.view.MotionEvent;
import android.view.View$OnTouchListener;
import android.annotation.TargetApi;
import android.util.AttributeSet;
import android.view.View;
import android.content.Context;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.t.c;

public final class DefaultMediaViewVideoRenderer extends MediaViewVideoRenderer
{
    @Nullable
    private c d;
    
    public DefaultMediaViewVideoRenderer(final Context context) {
        super(context);
        this.d = new c(context, (View)this);
        this.setVolume(0.0f);
    }
    
    public DefaultMediaViewVideoRenderer(final Context context, final AttributeSet set) {
        super(context, set);
        this.d = new c(context, (View)this);
        this.setVolume(0.0f);
    }
    
    public DefaultMediaViewVideoRenderer(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.d = new c(context, (View)this);
        this.setVolume(0.0f);
    }
    
    @TargetApi(21)
    public DefaultMediaViewVideoRenderer(final Context context, final AttributeSet set, final int n, final int n2) {
        super(context, set, n, n2);
        this.d = new c(context, (View)this);
        this.setVolume(0.0f);
    }
    
    @Override
    protected void a() {
        super.a();
        if (this.d != null) {
            this.d.a();
        }
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.d != null) {
            this.d.c();
        }
    }
    
    protected void onDetachedFromWindow() {
        if (this.d != null) {
            this.d.d();
        }
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
        if (this.d != null) {
            this.d.b();
        }
    }
    
    protected void onVisibilityChanged(final View view, final int n) {
        super.onVisibilityChanged(view, n);
        if (this.d != null) {
            this.d.e();
        }
    }
    
    public void onWindowFocusChanged(final boolean b) {
        super.onWindowFocusChanged(b);
        if (this.d != null) {
            this.d.f();
        }
    }
    
    @Override
    protected void setNativeAd(final NativeAd nativeAd) {
        super.setNativeAd(nativeAd);
        if (this.d != null) {
            this.d.a(nativeAd.f(), (c.a)new a(nativeAd.f()));
        }
    }
    
    private static class a implements c.a
    {
        private WeakReference<e> a;
        
        a(final e e) {
            this.a = new WeakReference<e>(e);
        }
        
        @Override
        public void a(final boolean b) {
            if (this.a.get() != null) {
                this.a.get().a(b, false);
            }
        }
    }
}
