package com.facebook.ads;

import android.content.Context;
import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
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
import com.facebook.ads.internal.p017t.C2114e;
import com.facebook.ads.internal.p017t.C2115g;
import com.facebook.ads.internal.p025w.p026b.C2580j;
import com.facebook.ads.internal.p025w.p026b.C2600x;

@Deprecated
public class AdChoicesView extends RelativeLayout {
    /* renamed from: a */
    private final NativeAdBase f3655a;
    /* renamed from: b */
    private final float f3656b;
    /* renamed from: c */
    private boolean f3657c;
    /* renamed from: d */
    private TextView f3658d;
    /* renamed from: e */
    private String f3659e;

    /* renamed from: com.facebook.ads.AdChoicesView$1 */
    class C17771 implements OnTouchListener {
        /* renamed from: a */
        final /* synthetic */ AdChoicesView f3645a;

        C17771(AdChoicesView adChoicesView) {
            this.f3645a = adChoicesView;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() != 0) {
                return false;
            }
            if (this.f3645a.f3657c) {
                this.f3645a.f3655a.m4078f().m5349y();
            } else {
                AdChoicesView.m3919c(this.f3645a);
            }
            return true;
        }
    }

    /* renamed from: com.facebook.ads.AdChoicesView$3 */
    class C17803 implements AnimationListener {
        /* renamed from: a */
        final /* synthetic */ AdChoicesView f3650a;

        /* renamed from: com.facebook.ads.AdChoicesView$3$1 */
        class C17791 implements Runnable {
            /* renamed from: a */
            final /* synthetic */ C17803 f3649a;

            C17791(C17803 c17803) {
                this.f3649a = c17803;
            }

            public void run() {
                if (this.f3649a.f3650a.f3657c) {
                    AdChoicesView.m3921e(this.f3649a.f3650a);
                }
            }
        }

        C17803(AdChoicesView adChoicesView) {
            this.f3650a = adChoicesView;
        }

        public void onAnimationEnd(Animation animation) {
            new Handler().postDelayed(new C17791(this), 3000);
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }
    }

    /* renamed from: com.facebook.ads.AdChoicesView$5 */
    class C17825 implements AnimationListener {
        /* renamed from: a */
        final /* synthetic */ AdChoicesView f3654a;

        C17825(AdChoicesView adChoicesView) {
            this.f3654a = adChoicesView;
        }

        public void onAnimationEnd(Animation animation) {
            this.f3654a.f3657c = false;
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }
    }

    @Deprecated
    public AdChoicesView(Context context, NativeAdBase nativeAdBase) {
        this(context, nativeAdBase, false);
    }

    @Deprecated
    public AdChoicesView(Context context, NativeAdBase nativeAdBase, @Nullable NativeAdLayout nativeAdLayout) {
        this(context, nativeAdBase, false, nativeAdLayout);
    }

    @Deprecated
    public AdChoicesView(Context context, NativeAdBase nativeAdBase, boolean z) {
        this(context, nativeAdBase, z, null);
    }

    @Deprecated
    public AdChoicesView(Context context, NativeAdBase nativeAdBase, boolean z, @Nullable NativeAdLayout nativeAdLayout) {
        super(context);
        this.f3657c = false;
        this.f3655a = nativeAdBase;
        this.f3656b = C2600x.f6420b;
        this.f3655a.m4078f().m5313a(nativeAdLayout);
        if (!this.f3655a.isAdLoaded() || this.f3655a.m4079g().m4507g()) {
            this.f3659e = this.f3655a.getAdChoicesText();
            if (TextUtils.isEmpty(this.f3659e)) {
                this.f3659e = "AdChoices";
            }
            C2115g o = this.f3655a.m4078f().m5339o();
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            setOnTouchListener(new C17771(this));
            this.f3658d = new TextView(getContext());
            addView(this.f3658d);
            LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            if (!z || o == null) {
                this.f3657c = true;
            } else {
                ImageView imageView = new ImageView(getContext());
                addView(imageView);
                LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(Math.round(((float) o.m5353b()) * this.f3656b), Math.round(((float) o.m5354c()) * this.f3656b));
                layoutParams3.addRule(9);
                layoutParams3.addRule(15, -1);
                layoutParams3.setMargins(Math.round(4.0f * this.f3656b), Math.round(2.0f * this.f3656b), Math.round(2.0f * this.f3656b), Math.round(2.0f * this.f3656b));
                imageView.setLayoutParams(layoutParams3);
                C2114e.m5284a(o, imageView);
                layoutParams2.addRule(11, imageView.getId());
                layoutParams2.width = 0;
                layoutParams.width = Math.round(((float) (o.m5353b() + 4)) * this.f3656b);
                layoutParams.height = Math.round(((float) (o.m5354c() + 2)) * this.f3656b);
                this.f3657c = false;
            }
            setLayoutParams(layoutParams);
            layoutParams2.addRule(15, -1);
            this.f3658d.setLayoutParams(layoutParams2);
            this.f3658d.setSingleLine();
            this.f3658d.setText(this.f3659e);
            this.f3658d.setTextSize(10.0f);
            this.f3658d.setTextColor(-4341303);
            C2580j.m6643a(this, C2580j.INTERNAL_AD_CHOICES_ICON);
            C2580j.m6643a(this.f3658d, C2580j.INTERNAL_AD_CHOICES_ICON);
            return;
        }
        setVisibility(8);
    }

    /* renamed from: c */
    static /* synthetic */ void m3919c(AdChoicesView adChoicesView) {
        Paint paint = new Paint();
        paint.setTextSize(adChoicesView.f3658d.getTextSize());
        int round = Math.round(paint.measureText(adChoicesView.f3659e) + (4.0f * adChoicesView.f3656b));
        final int width = adChoicesView.getWidth();
        round += width;
        adChoicesView.f3657c = true;
        Animation c17782 = new Animation(adChoicesView) {
            /* renamed from: c */
            final /* synthetic */ AdChoicesView f3648c;

            protected void applyTransformation(float f, Transformation transformation) {
                int i = (int) (((float) width) + (((float) (round - width)) * f));
                this.f3648c.getLayoutParams().width = i;
                this.f3648c.requestLayout();
                this.f3648c.f3658d.getLayoutParams().width = i - width;
                this.f3648c.f3658d.requestLayout();
            }

            public boolean willChangeBounds() {
                return true;
            }
        };
        c17782.setAnimationListener(new C17803(adChoicesView));
        c17782.setDuration(300);
        c17782.setFillAfter(true);
        adChoicesView.startAnimation(c17782);
    }

    /* renamed from: e */
    static /* synthetic */ void m3921e(AdChoicesView adChoicesView) {
        Paint paint = new Paint();
        paint.setTextSize(adChoicesView.f3658d.getTextSize());
        int round = Math.round(paint.measureText(adChoicesView.f3659e) + (4.0f * adChoicesView.f3656b));
        final int width = adChoicesView.getWidth();
        round = width - round;
        Animation c17814 = new Animation(adChoicesView) {
            /* renamed from: c */
            final /* synthetic */ AdChoicesView f3653c;

            protected void applyTransformation(float f, Transformation transformation) {
                int i = (int) (((float) width) + (((float) (round - width)) * f));
                this.f3653c.getLayoutParams().width = i;
                this.f3653c.requestLayout();
                this.f3653c.f3658d.getLayoutParams().width = i - round;
                this.f3653c.f3658d.requestLayout();
            }

            public boolean willChangeBounds() {
                return true;
            }
        };
        c17814.setAnimationListener(new C17825(adChoicesView));
        c17814.setDuration(300);
        c17814.setFillAfter(true);
        adChoicesView.startAnimation(c17814);
    }
}
