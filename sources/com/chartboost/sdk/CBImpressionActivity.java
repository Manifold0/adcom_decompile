package com.chartboost.sdk;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Model.C1388c;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.Tracking.C1391a;
import com.chartboost.sdk.impl.C1454s;

@SuppressLint({"Registered"})
public class CBImpressionActivity extends Activity {
    /* renamed from: a */
    final C1391a f2656a;
    /* renamed from: b */
    final Handler f2657b;
    /* renamed from: c */
    final C1397c f2658c;
    /* renamed from: d */
    private Activity f2659d;

    /* renamed from: com.chartboost.sdk.CBImpressionActivity$1 */
    class C13691 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ CBImpressionActivity f2655a;

        C13691(CBImpressionActivity cBImpressionActivity) {
            this.f2655a = cBImpressionActivity;
        }

        public void run() {
            try {
                CBLogging.m3104e("VideoInit", "preparing activity for video surface");
                this.f2655a.addContentView(new SurfaceView(this.f2655a), new LayoutParams(0, 0));
            } catch (Exception e) {
                C1391a.m3206a(CBImpressionActivity.class, "postCreateSurfaceView Runnable.run", e);
            }
        }
    }

    public CBImpressionActivity() {
        C1391a c1391a;
        Handler handler;
        C1397c c1397c;
        if (C1409h.m3324a() != null) {
            c1391a = C1409h.m3324a().f2916o;
        } else {
            c1391a = null;
        }
        this.f2656a = c1391a;
        if (C1409h.m3324a() != null) {
            handler = C1409h.m3324a().f2917p;
        } else {
            handler = null;
        }
        this.f2657b = handler;
        if (C1409h.m3324a() != null) {
            c1397c = C1409h.m3324a().f2918q;
        } else {
            c1397c = null;
        }
        this.f2658c = c1397c;
        this.f2659d = null;
    }

    public boolean dispatchTouchEvent(MotionEvent event) {
        if (this.f2659d != null) {
            return this.f2659d.dispatchTouchEvent(event);
        }
        return super.dispatchTouchEvent(event);
    }

    public void forwardTouchEvents(Activity host) {
        this.f2659d = host;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ((getIntent() != null && !getIntent().getBooleanExtra("isChartboost", false)) || this.f2656a == null || this.f2657b == null || this.f2658c == null) {
            CBLogging.m3099b("CBImpressionActivity", "This activity cannot be called from outside chartboost SDK");
            finish();
            return;
        }
        m3095a();
        requestWindowFeature(1);
        getWindow().setWindowAnimations(0);
        this.f2658c.m3251a(this);
        setContentView(new RelativeLayout(this));
        m3096b();
        CBLogging.m3097a("CBImpressionActivity", "Impression Activity onCreate() called");
    }

    @TargetApi(11)
    /* renamed from: a */
    private void m3095a() {
        if (C1454s.m3627a().m3630a(11)) {
            getWindow().setFlags(16777216, 16777216);
        }
    }

    protected void onStart() {
        try {
            super.onStart();
            if (this.f2658c != null && !C1410i.f2942s) {
                this.f2658c.m3264e(this);
            }
        } catch (Exception e) {
            C1391a.m3206a(getClass(), "onStart", e);
        }
    }

    protected void onResume() {
        try {
            super.onResume();
            if (!(this.f2658c == null || C1410i.f2942s)) {
                this.f2658c.m3248a((Activity) this);
                this.f2658c.m3270h();
            }
        } catch (Exception e) {
            C1391a.m3206a(getClass(), "onResume", e);
        }
        Chartboost.setActivityAttrs(this);
    }

    protected void onPause() {
        try {
            super.onPause();
            if (this.f2658c != null && !C1410i.f2942s) {
                this.f2658c.m3248a((Activity) this);
                this.f2658c.m3272i();
            }
        } catch (Exception e) {
            C1391a.m3206a(getClass(), "onPause", e);
        }
    }

    protected void onStop() {
        try {
            super.onStop();
            if (this.f2658c != null && !C1410i.f2942s) {
                this.f2658c.m3273i(this);
            }
        } catch (Exception e) {
            C1391a.m3206a(getClass(), "onStop", e);
        }
    }

    protected void onDestroy() {
        try {
            if (!(this.f2658c == null || C1410i.f2942s)) {
                this.f2658c.m3276k(this);
            }
            super.onDestroy();
        } catch (Exception e) {
            C1391a.m3206a(getClass(), "onDestroy", e);
        } catch (Throwable th) {
            super.onDestroy();
        }
    }

    public void onBackPressed() {
        try {
            if (this.f2658c == null || !this.f2658c.m3277k()) {
                super.onBackPressed();
            }
        } catch (Exception e) {
            C1391a.m3206a(getClass(), "onBackPressed", e);
        }
    }

    public void onAttachedToWindow() {
        try {
            super.onAttachedToWindow();
            if (C1454s.m3627a().m3630a(14) && getWindow() != null && getWindow().getDecorView() != null && !getWindow().getDecorView().isHardwareAccelerated() && this.f2658c != null) {
                CBLogging.m3099b("CBImpressionActivity", "The activity passed down is not hardware accelerated, so Chartboost cannot show ads");
                C1388c d = this.f2658c.m3262d();
                if (d != null) {
                    d.m3171a(CBImpressionError.HARDWARE_ACCELERATION_DISABLED);
                }
                finish();
            }
        } catch (Exception e) {
            C1391a.m3206a(getClass(), "onAttachedToWindow", e);
        }
    }

    /* renamed from: b */
    private void m3096b() {
        if (!C1454s.m3627a().m3630a(14)) {
            this.f2657b.post(new C13691(this));
        }
    }
}
