package com.facebook.ads.internal.p025w.p026b;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnSystemUiVisibilityChangeListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import com.tonyodev.fetch.FetchConst;

/* renamed from: com.facebook.ads.internal.w.b.t */
public class C2593t implements OnSystemUiVisibilityChangeListener {
    /* renamed from: a */
    private final View f6382a;
    /* renamed from: b */
    private int f6383b;
    @Nullable
    /* renamed from: c */
    private Window f6384c;
    /* renamed from: d */
    private C2592a f6385d = C2592a.f6379a;
    /* renamed from: e */
    private final Runnable f6386e = new C25901(this);

    /* renamed from: com.facebook.ads.internal.w.b.t$1 */
    class C25901 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C2593t f6377a;

        C25901(C2593t c2593t) {
            this.f6377a = c2593t;
        }

        public void run() {
            this.f6377a.m6660a(false);
        }
    }

    /* renamed from: com.facebook.ads.internal.w.b.t$a */
    public enum C2592a {
        f6379a,
        FULL_SCREEN
    }

    public C2593t(View view) {
        this.f6382a = view;
        this.f6382a.setOnSystemUiVisibilityChangeListener(this);
    }

    /* renamed from: a */
    private void m6658a(int i, boolean z) {
        if (this.f6384c != null) {
            LayoutParams attributes = this.f6384c.getAttributes();
            if (z) {
                attributes.flags |= i;
            } else {
                attributes.flags &= i ^ -1;
            }
            this.f6384c.setAttributes(attributes);
        }
    }

    /* renamed from: a */
    private void m6660a(boolean z) {
        if (!C2592a.f6379a.equals(this.f6385d)) {
            int i = 3840;
            if (!z) {
                i = 3847;
            }
            Handler handler = this.f6382a.getHandler();
            if (handler != null && z) {
                handler.removeCallbacks(this.f6386e);
                handler.postDelayed(this.f6386e, FetchConst.DEFAULT_ON_UPDATE_INTERVAL);
            }
            this.f6382a.setSystemUiVisibility(i);
        }
    }

    /* renamed from: a */
    public void m6661a() {
        this.f6384c = null;
    }

    /* renamed from: a */
    public void m6662a(Window window) {
        this.f6384c = window;
    }

    /* renamed from: a */
    public void m6663a(C2592a c2592a) {
        this.f6385d = c2592a;
        switch (this.f6385d) {
            case FULL_SCREEN:
                m6658a(67108864, true);
                m6658a(134217728, true);
                m6660a(false);
                return;
            default:
                m6658a(67108864, false);
                m6658a(134217728, false);
                this.f6382a.setSystemUiVisibility(0);
                return;
        }
    }

    public void onSystemUiVisibilityChange(int i) {
        int i2 = this.f6383b ^ i;
        this.f6383b = i;
        if ((i2 & 2) != 0 && (i & 2) == 0) {
            m6660a(true);
        }
    }
}
