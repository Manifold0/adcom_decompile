package com.facebook.ads.internal.view.p022i.p067c;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p025w.p057e.C2615g;
import com.facebook.ads.internal.p025w.p069c.C2603b;
import com.facebook.ads.internal.p025w.p069c.C2604c;
import com.facebook.ads.internal.view.p022i.p065a.C2391c;

/* renamed from: com.facebook.ads.internal.view.i.c.a */
public class C2434a extends C2391c {
    /* renamed from: a */
    private final C2433a f5887a;

    /* renamed from: com.facebook.ads.internal.view.i.c.a$a */
    public static class C2433a extends RelativeLayout {
        /* renamed from: a */
        private final String f5880a;
        /* renamed from: b */
        private final String f5881b;
        /* renamed from: c */
        private final String f5882c;
        /* renamed from: d */
        private final DisplayMetrics f5883d;
        /* renamed from: e */
        private ImageView f5884e;
        /* renamed from: f */
        private TextView f5885f;
        /* renamed from: g */
        private boolean f5886g = false;

        /* renamed from: com.facebook.ads.internal.view.i.c.a$a$1 */
        class C24271 implements OnTouchListener {
            /* renamed from: a */
            final /* synthetic */ C2433a f5870a;

            C24271(C2433a c2433a) {
                this.f5870a = c2433a;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() != 0) {
                    return false;
                }
                if (!this.f5870a.f5886g) {
                    C2433a.m6251d(this.f5870a);
                } else if (!TextUtils.isEmpty(this.f5870a.f5881b)) {
                    C2615g.m6721a(new C2615g(), this.f5870a.getContext(), Uri.parse(this.f5870a.f5881b), this.f5870a.f5882c);
                }
                return true;
            }
        }

        /* renamed from: com.facebook.ads.internal.view.i.c.a$a$3 */
        class C24303 implements AnimationListener {
            /* renamed from: a */
            final /* synthetic */ C2433a f5875a;

            /* renamed from: com.facebook.ads.internal.view.i.c.a$a$3$1 */
            class C24291 implements Runnable {
                /* renamed from: a */
                final /* synthetic */ C24303 f5874a;

                C24291(C24303 c24303) {
                    this.f5874a = c24303;
                }

                public void run() {
                    if (this.f5874a.f5875a.f5886g) {
                        C2433a.m6253f(this.f5874a.f5875a);
                    }
                }
            }

            C24303(C2433a c2433a) {
                this.f5875a = c2433a;
            }

            public void onAnimationEnd(Animation animation) {
                new Handler().postDelayed(new C24291(this), 3000);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        }

        /* renamed from: com.facebook.ads.internal.view.i.c.a$a$5 */
        class C24325 implements AnimationListener {
            /* renamed from: a */
            final /* synthetic */ C2433a f5879a;

            C24325(C2433a c2433a) {
                this.f5879a = c2433a;
            }

            public void onAnimationEnd(Animation animation) {
                this.f5879a.f5886g = false;
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        }

        public C2433a(Context context, String str, String str2, float[] fArr, String str3) {
            super(context);
            this.f5880a = str;
            this.f5881b = str2;
            this.f5882c = str3;
            this.f5883d = context.getResources().getDisplayMetrics();
            Drawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(ViewCompat.MEASURED_STATE_MASK);
            gradientDrawable.setAlpha(178);
            gradientDrawable.setCornerRadii(new float[]{fArr[0] * this.f5883d.density, fArr[0] * this.f5883d.density, fArr[1] * this.f5883d.density, fArr[1] * this.f5883d.density, fArr[2] * this.f5883d.density, fArr[2] * this.f5883d.density, fArr[3] * this.f5883d.density, fArr[3] * this.f5883d.density});
            C2600x.m6681a((View) this, gradientDrawable);
            setOnTouchListener(new C24271(this));
            this.f5884e = new ImageView(getContext());
            this.f5884e.setImageBitmap(C2604c.m6697a(C2603b.IC_AD_CHOICES));
            addView(this.f5884e);
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(Math.round(16.0f * this.f5883d.density), Math.round(16.0f * this.f5883d.density));
            layoutParams.addRule(9);
            layoutParams.addRule(15, -1);
            layoutParams.setMargins(Math.round(4.0f * this.f5883d.density), Math.round(this.f5883d.density * 2.0f), Math.round(this.f5883d.density * 2.0f), Math.round(this.f5883d.density * 2.0f));
            this.f5884e.setLayoutParams(layoutParams);
            this.f5885f = new TextView(getContext());
            addView(this.f5885f);
            layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.width = 0;
            layoutParams.leftMargin = (int) (20.0f * this.f5883d.density);
            layoutParams.addRule(9);
            layoutParams.addRule(15, -1);
            this.f5885f.setLayoutParams(layoutParams);
            this.f5885f.setSingleLine();
            this.f5885f.setText(this.f5880a);
            this.f5885f.setTextSize(10.0f);
            this.f5885f.setTextColor(-4341303);
            setMinimumWidth(Math.round(20.0f * this.f5883d.density));
            setMinimumHeight(Math.round(18.0f * this.f5883d.density));
        }

        /* renamed from: d */
        static /* synthetic */ void m6251d(C2433a c2433a) {
            Paint paint = new Paint();
            paint.setTextSize(c2433a.f5885f.getTextSize());
            int round = Math.round(paint.measureText(c2433a.f5880a) + (4.0f * c2433a.f5883d.density));
            final int width = c2433a.getWidth();
            round += width;
            c2433a.f5886g = true;
            Animation c24282 = new Animation(c2433a) {
                /* renamed from: c */
                final /* synthetic */ C2433a f5873c;

                protected void applyTransformation(float f, Transformation transformation) {
                    int i = (int) (((float) width) + (((float) (round - width)) * f));
                    this.f5873c.getLayoutParams().width = i;
                    this.f5873c.requestLayout();
                    this.f5873c.f5885f.getLayoutParams().width = i - width;
                    this.f5873c.f5885f.requestLayout();
                }

                public boolean willChangeBounds() {
                    return true;
                }
            };
            c24282.setAnimationListener(new C24303(c2433a));
            c24282.setDuration(300);
            c24282.setFillAfter(true);
            c2433a.startAnimation(c24282);
        }

        /* renamed from: f */
        static /* synthetic */ void m6253f(C2433a c2433a) {
            Paint paint = new Paint();
            paint.setTextSize(c2433a.f5885f.getTextSize());
            int round = Math.round(paint.measureText(c2433a.f5880a) + (4.0f * c2433a.f5883d.density));
            final int width = c2433a.getWidth();
            round = width - round;
            Animation c24314 = new Animation(c2433a) {
                /* renamed from: c */
                final /* synthetic */ C2433a f5878c;

                protected void applyTransformation(float f, Transformation transformation) {
                    int i = (int) (((float) width) + (((float) (round - width)) * f));
                    this.f5878c.getLayoutParams().width = i;
                    this.f5878c.requestLayout();
                    this.f5878c.f5885f.getLayoutParams().width = i - round;
                    this.f5878c.f5885f.requestLayout();
                }

                public boolean willChangeBounds() {
                    return true;
                }
            };
            c24314.setAnimationListener(new C24325(c2433a));
            c24314.setDuration(300);
            c24314.setFillAfter(true);
            c2433a.startAnimation(c24314);
        }
    }

    public C2434a(Context context, String str, String str2, float[] fArr) {
        super(context);
        this.f5887a = new C2433a(context, "AdChoices", str, fArr, str2);
        addView(this.f5887a);
    }
}
