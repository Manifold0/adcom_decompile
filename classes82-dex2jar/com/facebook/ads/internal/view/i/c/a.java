// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.i.c;

import android.os.Handler;
import android.view.animation.Animation$AnimationListener;
import android.view.animation.Transformation;
import android.view.animation.Animation;
import android.graphics.Paint;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import com.facebook.ads.internal.w.c.b;
import android.net.Uri;
import com.facebook.ads.internal.w.e.g;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View$OnTouchListener;
import android.graphics.drawable.Drawable;
import com.facebook.ads.internal.w.b.x;
import android.graphics.drawable.GradientDrawable;
import android.widget.TextView;
import android.widget.ImageView;
import android.util.DisplayMetrics;
import android.widget.RelativeLayout;
import android.view.View;
import android.content.Context;
import com.facebook.ads.internal.view.i.a.c;

public class a extends c
{
    private final a a;
    
    public a(final Context context, final String s, final String s2, final float[] array) {
        super(context);
        this.addView((View)(this.a = new a(context, "AdChoices", s, array, s2)));
    }
    
    public static class a extends RelativeLayout
    {
        private final String a;
        private final String b;
        private final String c;
        private final DisplayMetrics d;
        private ImageView e;
        private TextView f;
        private boolean g;
        
        public a(final Context context, final String a, final String b, final float[] array, final String c) {
            super(context);
            this.g = false;
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = context.getResources().getDisplayMetrics();
            final GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(-16777216);
            gradientDrawable.setAlpha(178);
            gradientDrawable.setCornerRadii(new float[] { array[0] * this.d.density, array[0] * this.d.density, array[1] * this.d.density, array[1] * this.d.density, array[2] * this.d.density, array[2] * this.d.density, array[3] * this.d.density, array[3] * this.d.density });
            x.a((View)this, (Drawable)gradientDrawable);
            this.setOnTouchListener((View$OnTouchListener)new View$OnTouchListener() {
                public boolean onTouch(final View view, final MotionEvent motionEvent) {
                    if (motionEvent.getAction() == 0) {
                        if (com.facebook.ads.internal.view.i.c.a.a.this.g) {
                            if (!TextUtils.isEmpty((CharSequence)com.facebook.ads.internal.view.i.c.a.a.this.b)) {
                                com.facebook.ads.internal.w.e.g.a(new g(), com.facebook.ads.internal.view.i.c.a.a.this.getContext(), Uri.parse(com.facebook.ads.internal.view.i.c.a.a.this.b), com.facebook.ads.internal.view.i.c.a.a.this.c);
                            }
                        }
                        else {
                            com.facebook.ads.internal.view.i.c.a.a.d(com.facebook.ads.internal.view.i.c.a.a.this);
                        }
                        return true;
                    }
                    return false;
                }
            });
            (this.e = new ImageView(this.getContext())).setImageBitmap(com.facebook.ads.internal.w.c.c.a(com.facebook.ads.internal.w.c.b.e));
            this.addView((View)this.e);
            final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(Math.round(16.0f * this.d.density), Math.round(16.0f * this.d.density));
            layoutParams.addRule(9);
            layoutParams.addRule(15, -1);
            layoutParams.setMargins(Math.round(4.0f * this.d.density), Math.round(this.d.density * 2.0f), Math.round(this.d.density * 2.0f), Math.round(this.d.density * 2.0f));
            this.e.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
            this.addView((View)(this.f = new TextView(this.getContext())));
            final RelativeLayout$LayoutParams layoutParams2 = new RelativeLayout$LayoutParams(-2, -2);
            layoutParams2.width = 0;
            layoutParams2.leftMargin = (int)(20.0f * this.d.density);
            layoutParams2.addRule(9);
            layoutParams2.addRule(15, -1);
            this.f.setLayoutParams((ViewGroup$LayoutParams)layoutParams2);
            this.f.setSingleLine();
            this.f.setText((CharSequence)this.a);
            this.f.setTextSize(10.0f);
            this.f.setTextColor(-4341303);
            this.setMinimumWidth(Math.round(20.0f * this.d.density));
            this.setMinimumHeight(Math.round(18.0f * this.d.density));
        }
        
        static /* synthetic */ void d(final a a) {
            final Paint paint = new Paint();
            paint.setTextSize(a.f.getTextSize());
            final int round = Math.round(paint.measureText(a.a) + 4.0f * a.d.density);
            final int width = a.getWidth();
            a.g = true;
            final Animation animation = new Animation() {
                final /* synthetic */ int b = round + width;
                
                protected void applyTransformation(final float n, final Transformation transformation) {
                    final int width = (int)(width + (this.b - width) * n);
                    com.facebook.ads.internal.view.i.c.a.a.this.getLayoutParams().width = width;
                    com.facebook.ads.internal.view.i.c.a.a.this.requestLayout();
                    com.facebook.ads.internal.view.i.c.a.a.this.f.getLayoutParams().width = width - width;
                    com.facebook.ads.internal.view.i.c.a.a.this.f.requestLayout();
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
                            if (!com.facebook.ads.internal.view.i.c.a.a.this.g) {
                                return;
                            }
                            com.facebook.ads.internal.view.i.c.a.a.f(com.facebook.ads.internal.view.i.c.a.a.this);
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
            a.startAnimation((Animation)animation);
        }
        
        static /* synthetic */ void f(final a a) {
            final Paint paint = new Paint();
            paint.setTextSize(a.f.getTextSize());
            final int round = Math.round(paint.measureText(a.a) + 4.0f * a.d.density);
            final int width = a.getWidth();
            final Animation animation = new Animation() {
                final /* synthetic */ int b = width - round;
                
                protected void applyTransformation(final float n, final Transformation transformation) {
                    final int width = (int)(width + (this.b - width) * n);
                    com.facebook.ads.internal.view.i.c.a.a.this.getLayoutParams().width = width;
                    com.facebook.ads.internal.view.i.c.a.a.this.requestLayout();
                    com.facebook.ads.internal.view.i.c.a.a.this.f.getLayoutParams().width = width - this.b;
                    com.facebook.ads.internal.view.i.c.a.a.this.f.requestLayout();
                }
                
                public boolean willChangeBounds() {
                    return true;
                }
            };
            animation.setAnimationListener((Animation$AnimationListener)new Animation$AnimationListener() {
                public void onAnimationEnd(final Animation animation) {
                    com.facebook.ads.internal.view.i.c.a.a.this.g = false;
                }
                
                public void onAnimationRepeat(final Animation animation) {
                }
                
                public void onAnimationStart(final Animation animation) {
                }
            });
            animation.setDuration(300L);
            animation.setFillAfter(true);
            a.startAnimation((Animation)animation);
        }
    }
}
