package com.facebook.ads.internal.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.view.component.p058a.C2286l;

/* renamed from: com.facebook.ads.internal.view.u */
public class C2551u extends RelativeLayout {
    /* renamed from: a */
    private C2286l f6268a;
    /* renamed from: b */
    private ViewDragHelper f6269b = ViewDragHelper.create(this, 1.0f, new C2550b());
    @Nullable
    /* renamed from: c */
    private C2511a f6270c;
    /* renamed from: d */
    private boolean f6271d = true;
    /* renamed from: e */
    private int f6272e = 0;
    /* renamed from: f */
    private int f6273f = 0;
    /* renamed from: g */
    private int f6274g;
    /* renamed from: h */
    private int f6275h;
    /* renamed from: i */
    private int f6276i;

    /* renamed from: com.facebook.ads.internal.view.u$a */
    public interface C2511a {
        /* renamed from: a */
        void mo5636a();

        /* renamed from: b */
        void mo5637b();
    }

    /* renamed from: com.facebook.ads.internal.view.u$b */
    private class C2550b extends Callback {
        /* renamed from: a */
        final /* synthetic */ C2551u f6267a;

        private C2550b(C2551u c2551u) {
            this.f6267a = c2551u;
        }

        public int clampViewPositionVertical(View view, int i, int i2) {
            int paddingTop = this.f6267a.getPaddingTop();
            return Math.min(Math.max(i, paddingTop), this.f6267a.f6274g);
        }

        public int getViewVerticalDragRange(View view) {
            return this.f6267a.f6274g;
        }

        public void onViewDragStateChanged(int i) {
            if (i != this.f6267a.f6272e) {
                if (i == 0 && (this.f6267a.f6272e == 1 || this.f6267a.f6272e == 2)) {
                    if (this.f6267a.f6275h == this.f6267a.f6276i) {
                        C2551u.m6589d(this.f6267a);
                    } else if (this.f6267a.f6275h == this.f6267a.f6274g) {
                        this.f6267a.m6588d();
                    }
                }
                this.f6267a.f6272e = i;
            }
        }

        public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
            this.f6267a.f6275h = i2;
        }

        public void onViewReleased(View view, float f, float f2) {
            boolean z = true;
            if (this.f6267a.f6275h == this.f6267a.f6276i) {
                this.f6267a.f6271d = false;
            } else if (this.f6267a.f6275h == this.f6267a.f6274g) {
                this.f6267a.f6271d = true;
            } else {
                if (((double) f2) <= 800.0d) {
                    if (((double) f2) < -800.0d) {
                        z = false;
                    } else if (this.f6267a.f6275h <= this.f6267a.f6274g / 2) {
                        z = this.f6267a.f6275h < this.f6267a.f6274g / 2 ? false : false;
                    }
                }
                if (this.f6267a.f6269b.settleCapturedViewAt(0, z ? this.f6267a.f6274g : this.f6267a.f6276i)) {
                    ViewCompat.postInvalidateOnAnimation(this.f6267a);
                }
            }
        }

        public boolean tryCaptureView(View view, int i) {
            return view == this.f6267a.f6268a;
        }
    }

    public C2551u(Context context, C2286l c2286l, int i, int i2) {
        super(context);
        this.f6268a = c2286l;
        this.f6276i = i2;
        this.f6268a.setLayoutParams(new LayoutParams(-1, -1));
        this.f6274g = i;
        this.f6275h = this.f6274g;
        this.f6268a.offsetTopAndBottom(this.f6274g);
        this.f6273f = this.f6274g;
        addView(this.f6268a);
        setBackgroundColor(0);
    }

    /* renamed from: d */
    private void m6588d() {
        this.f6271d = true;
        if (this.f6270c != null) {
            this.f6270c.mo5636a();
        }
    }

    /* renamed from: d */
    static /* synthetic */ void m6589d(C2551u c2551u) {
        c2551u.f6271d = false;
        if (c2551u.f6270c != null) {
            c2551u.f6270c.mo5637b();
        }
    }

    /* renamed from: a */
    public void m6594a() {
        this.f6268a.offsetTopAndBottom(this.f6274g);
        this.f6273f = this.f6274g;
        m6588d();
    }

    /* renamed from: b */
    public void m6595b() {
        this.f6268a.offsetTopAndBottom(this.f6276i);
        this.f6273f = this.f6276i;
    }

    /* renamed from: c */
    public boolean m6596c() {
        return this.f6271d;
    }

    public void computeScroll() {
        if (this.f6269b.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        } else {
            this.f6273f = this.f6268a.getTop();
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.f6271d && this.f6269b.isViewUnder(this.f6268a, (int) motionEvent.getX(), (int) motionEvent.getY()) && this.f6268a.getScrollY() == 0;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.f6268a.offsetTopAndBottom(this.f6273f);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        this.f6268a.mo5565a(motionEvent);
        if (!this.f6269b.isViewUnder(this.f6268a, x, y)) {
            return super.onTouchEvent(motionEvent);
        }
        this.f6269b.processTouchEvent(motionEvent);
        return true;
    }

    public void setDragListener(C2511a c2511a) {
        this.f6270c = c2511a;
    }

    public void setDragRange(int i) {
        this.f6274g = i;
        this.f6269b.smoothSlideViewTo(this.f6268a, 0, this.f6274g);
    }
}
