// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads;

import android.os.Handler;
import android.view.animation.Animation$AnimationListener;
import android.view.animation.Transformation;
import android.view.animation.Animation;
import android.graphics.Paint;
import com.facebook.ads.internal.t.g;
import com.facebook.ads.internal.w.b.j;
import com.facebook.ads.internal.t.e;
import android.view.ViewGroup$LayoutParams;
import android.widget.ImageView;
import android.view.MotionEvent;
import android.view.View;
import android.view.View$OnTouchListener;
import android.widget.RelativeLayout$LayoutParams;
import android.text.TextUtils;
import com.facebook.ads.internal.w.b.x;
import android.support.annotation.Nullable;
import android.content.Context;
import android.widget.TextView;
import android.widget.RelativeLayout;

@Deprecated
public class AdChoicesView extends RelativeLayout
{
    private final NativeAdBase a;
    private final float b;
    private boolean c;
    private TextView d;
    private String e;
    
    @Deprecated
    public AdChoicesView(final Context context, final NativeAdBase nativeAdBase) {
        this(context, nativeAdBase, false);
    }
    
    @Deprecated
    public AdChoicesView(final Context context, final NativeAdBase nativeAdBase, @Nullable final NativeAdLayout nativeAdLayout) {
        this(context, nativeAdBase, false, nativeAdLayout);
    }
    
    @Deprecated
    public AdChoicesView(final Context context, final NativeAdBase nativeAdBase, final boolean b) {
        this(context, nativeAdBase, b, null);
    }
    
    @Deprecated
    public AdChoicesView(final Context context, final NativeAdBase a, final boolean b, @Nullable final NativeAdLayout nativeAdLayout) {
        super(context);
        this.c = false;
        this.a = a;
        this.b = x.b;
        this.a.f().a(nativeAdLayout);
        if (this.a.isAdLoaded() && !this.a.g().g()) {
            this.setVisibility(8);
            return;
        }
        this.e = this.a.getAdChoicesText();
        if (TextUtils.isEmpty((CharSequence)this.e)) {
            this.e = "AdChoices";
        }
        final g o = this.a.f().o();
        final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(-2, -2);
        this.setOnTouchListener((View$OnTouchListener)new View$OnTouchListener() {
            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    if (AdChoicesView.this.c) {
                        AdChoicesView.this.a.f().y();
                    }
                    else {
                        AdChoicesView.c(AdChoicesView.this);
                    }
                    return true;
                }
                return false;
            }
        });
        this.addView((View)(this.d = new TextView(this.getContext())));
        final RelativeLayout$LayoutParams layoutParams2 = new RelativeLayout$LayoutParams(-2, -2);
        if (b && o != null) {
            final ImageView imageView = new ImageView(this.getContext());
            this.addView((View)imageView);
            final RelativeLayout$LayoutParams layoutParams3 = new RelativeLayout$LayoutParams(Math.round(o.b() * this.b), Math.round(o.c() * this.b));
            layoutParams3.addRule(9);
            layoutParams3.addRule(15, -1);
            layoutParams3.setMargins(Math.round(4.0f * this.b), Math.round(2.0f * this.b), Math.round(2.0f * this.b), Math.round(2.0f * this.b));
            imageView.setLayoutParams((ViewGroup$LayoutParams)layoutParams3);
            com.facebook.ads.internal.t.e.a(o, imageView);
            layoutParams2.addRule(11, imageView.getId());
            layoutParams2.width = 0;
            layoutParams.width = Math.round((o.b() + 4) * this.b);
            layoutParams.height = Math.round((o.c() + 2) * this.b);
            this.c = false;
        }
        else {
            this.c = true;
        }
        this.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        layoutParams2.addRule(15, -1);
        this.d.setLayoutParams((ViewGroup$LayoutParams)layoutParams2);
        this.d.setSingleLine();
        this.d.setText((CharSequence)this.e);
        this.d.setTextSize(10.0f);
        this.d.setTextColor(-4341303);
        j.a((View)this, j.m);
        j.a((View)this.d, j.m);
    }
    
    static /* synthetic */ void c(final AdChoicesView adChoicesView) {
        final Paint paint = new Paint();
        paint.setTextSize(adChoicesView.d.getTextSize());
        final int round = Math.round(paint.measureText(adChoicesView.e) + 4.0f * adChoicesView.b);
        final int width = adChoicesView.getWidth();
        adChoicesView.c = true;
        final Animation animation = new Animation() {
            final /* synthetic */ int b = round + width;
            
            protected void applyTransformation(final float n, final Transformation transformation) {
                final int width = (int)(width + (this.b - width) * n);
                AdChoicesView.this.getLayoutParams().width = width;
                AdChoicesView.this.requestLayout();
                AdChoicesView.this.d.getLayoutParams().width = width - width;
                AdChoicesView.this.d.requestLayout();
            }
            
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation.setAnimationListener((Animation$AnimationListener)new Animation$AnimationListener() {
            public void onAnimationEnd(final Animation animation) {
                new Handler().postDelayed((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        if (!AdChoicesView.this.c) {
                            return;
                        }
                        AdChoicesView.e(AdChoicesView.this);
                    }
                }, 3000L);
            }
            
            public void onAnimationRepeat(final Animation animation) {
            }
            
            public void onAnimationStart(final Animation animation) {
            }
        });
        animation.setDuration(300L);
        animation.setFillAfter(true);
        adChoicesView.startAnimation((Animation)animation);
    }
    
    static /* synthetic */ void e(final AdChoicesView adChoicesView) {
        final Paint paint = new Paint();
        paint.setTextSize(adChoicesView.d.getTextSize());
        final int round = Math.round(paint.measureText(adChoicesView.e) + 4.0f * adChoicesView.b);
        final int width = adChoicesView.getWidth();
        final Animation animation = new Animation() {
            final /* synthetic */ int b = width - round;
            
            protected void applyTransformation(final float n, final Transformation transformation) {
                final int width = (int)(width + (this.b - width) * n);
                AdChoicesView.this.getLayoutParams().width = width;
                AdChoicesView.this.requestLayout();
                AdChoicesView.this.d.getLayoutParams().width = width - this.b;
                AdChoicesView.this.d.requestLayout();
            }
            
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation.setAnimationListener((Animation$AnimationListener)new Animation$AnimationListener() {
            public void onAnimationEnd(final Animation animation) {
                AdChoicesView.this.c = false;
            }
            
            public void onAnimationRepeat(final Animation animation) {
            }
            
            public void onAnimationStart(final Animation animation) {
            }
        });
        animation.setDuration(300L);
        animation.setFillAfter(true);
        adChoicesView.startAnimation((Animation)animation);
    }
}
