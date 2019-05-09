package com.tapjoy;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build.VERSION;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.widget.ImageButton;
import android.widget.RelativeLayout.LayoutParams;
import com.tonyodev.fetch.FetchConst;

public class TJCloseButton extends ImageButton {
    /* renamed from: a */
    private static final String f6891a = TJCloseButton.class.getSimpleName();
    /* renamed from: b */
    private ClosePosition f6892b;
    /* renamed from: c */
    private boolean f6893c;
    /* renamed from: d */
    private boolean f6894d;

    /* renamed from: com.tapjoy.TJCloseButton$1 */
    class C28011 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ TJCloseButton f6888a;

        /* renamed from: com.tapjoy.TJCloseButton$1$1 */
        class C28001 implements AnimatorListener {
            /* renamed from: a */
            final /* synthetic */ C28011 f6887a;

            C28001(C28011 c28011) {
                this.f6887a = c28011;
            }

            public final void onAnimationCancel(Animator arg0) {
                this.f6887a.f6888a.setClickable(this.f6887a.f6888a.f6893c);
                this.f6887a.f6888a.f6894d = false;
            }

            public final void onAnimationRepeat(Animator arg0) {
            }

            public final void onAnimationStart(Animator arg0) {
            }

            public final void onAnimationEnd(Animator arg0) {
                this.f6887a.f6888a.setClickable(this.f6887a.f6888a.f6893c);
                this.f6887a.f6888a.f6894d = false;
            }
        }

        C28011(TJCloseButton tJCloseButton) {
            this.f6888a = tJCloseButton;
        }

        @SuppressLint({"NewApi"})
        public final void run() {
            this.f6888a.animate().alpha(1.0f).setDuration(500).setListener(new C28001(this));
        }
    }

    public enum ClosePosition {
        TOP_LEFT(new int[]{10, 9}),
        TOP_CENTER(new int[]{10, 14}),
        TOP_RIGHT(new int[]{10, 11}),
        CENTER(new int[]{13}),
        BOTTOM_LEFT(new int[]{12, 9}),
        BOTTOM_CENTER(new int[]{12, 14}),
        BOTTOM_RIGHT(new int[]{12, 11});
        
        /* renamed from: a */
        final LayoutParams f6890a;

        private ClosePosition(int[] layoutParams) {
            this.f6890a = new LayoutParams(-2, -2);
            for (int addRule : layoutParams) {
                this.f6890a.addRule(addRule);
            }
            int deviceScreenDensityScale = (int) (-10.0f * TapjoyConnectCore.getDeviceScreenDensityScale());
            this.f6890a.setMargins(0, deviceScreenDensityScale, deviceScreenDensityScale, 0);
        }
    }

    public TJCloseButton(Context context) {
        this(context, ClosePosition.TOP_RIGHT);
    }

    public TJCloseButton(Context context, ClosePosition closePosition) {
        super(context);
        this.f6893c = true;
        this.f6892b = closePosition;
        Bitmap loadBitmapFromJar = TapjoyUtil.loadBitmapFromJar("tj_close_button.png", context);
        if (loadBitmapFromJar == null) {
            try {
                loadBitmapFromJar = BitmapFactory.decodeResource(context.getResources(), context.getResources().getIdentifier("tj_close_button", "drawable", context.getPackageName()));
            } catch (Exception e) {
                TapjoyLog.m7131w(f6891a, "Could not find close button asset");
            }
        }
        setImageBitmap(loadBitmapFromJar);
        setBackgroundColor(ViewCompat.MEASURED_SIZE_MASK);
        setLayoutParams(this.f6892b.f6890a);
    }

    @TargetApi(11)
    protected void onAttachedToWindow() {
        if (VERSION.SDK_INT >= 12) {
            setAlpha(0.0f);
            setVisibility(0);
            this.f6894d = true;
            setClickable(false);
            new Handler().postDelayed(new C28011(this), FetchConst.DEFAULT_ON_UPDATE_INTERVAL);
        }
    }

    void setClickableRequested(boolean clickable) {
        this.f6893c = clickable;
        if (!this.f6894d) {
            setClickable(clickable);
        }
    }
}
