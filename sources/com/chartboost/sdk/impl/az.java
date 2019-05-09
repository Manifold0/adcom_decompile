package com.chartboost.sdk.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.chartboost.sdk.Libraries.C1381h;

public abstract class az extends RelativeLayout {
    /* renamed from: a */
    private final Rect f2955a;
    /* renamed from: c */
    final C1425a f2956c;
    /* renamed from: d */
    protected Button f2957d;
    /* renamed from: e */
    boolean f2958e;

    /* renamed from: com.chartboost.sdk.impl.az$1 */
    class C14231 implements OnTouchListener {
        /* renamed from: a */
        final /* synthetic */ az f3111a;

        C14231(az azVar) {
            this.f3111a = azVar;
        }

        public boolean onTouch(View v, MotionEvent event) {
            boolean a = this.f3111a.m3345a(v, event);
            switch (event.getActionMasked()) {
                case 0:
                    this.f3111a.f2956c.m3472a(a);
                    return a;
                case 1:
                    if (this.f3111a.getVisibility() == 0 && this.f3111a.isEnabled() && a) {
                        this.f3111a.mo4279a(event);
                    }
                    this.f3111a.f2956c.m3472a(false);
                    break;
                case 2:
                    this.f3111a.f2956c.m3472a(a);
                    break;
                case 3:
                case 4:
                    this.f3111a.f2956c.m3472a(false);
                    break;
            }
            return true;
        }
    }

    /* renamed from: com.chartboost.sdk.impl.az$2 */
    class C14242 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ az f3112a;

        C14242(az azVar) {
            this.f3112a = azVar;
        }

        public void onClick(View v) {
            this.f3112a.mo4279a(null);
        }
    }

    /* renamed from: com.chartboost.sdk.impl.az$a */
    private class C1425a extends ay {
        /* renamed from: b */
        final /* synthetic */ az f3113b;
        /* renamed from: c */
        private boolean f3114c;

        public C1425a(az azVar, Context context) {
            this.f3113b = azVar;
            super(context);
            this.f3114c = false;
            this.f3114c = false;
        }

        public boolean performClick() {
            super.performClick();
            return true;
        }

        /* renamed from: a */
        protected void m3472a(boolean z) {
            if (this.f3113b.f2958e && z) {
                if (!this.f3114c) {
                    if (getDrawable() != null) {
                        getDrawable().setColorFilter(1996488704, Mode.SRC_ATOP);
                    } else if (getBackground() != null) {
                        getBackground().setColorFilter(1996488704, Mode.SRC_ATOP);
                    }
                    invalidate();
                    this.f3114c = true;
                }
            } else if (this.f3114c) {
                if (getDrawable() != null) {
                    getDrawable().clearColorFilter();
                } else if (getBackground() != null) {
                    getBackground().clearColorFilter();
                }
                invalidate();
                this.f3114c = false;
            }
        }
    }

    /* renamed from: a */
    protected abstract void mo4279a(MotionEvent motionEvent);

    public az(Context context) {
        this(context, null);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public az(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2955a = new Rect();
        this.f2957d = null;
        this.f2958e = true;
        this.f2956c = new C1425a(this, getContext());
        this.f2956c.setOnTouchListener(new C14231(this));
        addView(this.f2956c, new LayoutParams(-1, -1));
    }

    /* renamed from: a */
    boolean m3345a(View view, MotionEvent motionEvent) {
        view.getLocalVisibleRect(this.f2955a);
        Rect rect = this.f2955a;
        rect.left += view.getPaddingLeft();
        rect = this.f2955a;
        rect.top += view.getPaddingTop();
        rect = this.f2955a;
        rect.right -= view.getPaddingRight();
        rect = this.f2955a;
        rect.bottom -= view.getPaddingBottom();
        return this.f2955a.contains(Math.round(motionEvent.getX()), Math.round(motionEvent.getY()));
    }

    /* renamed from: a */
    public void m3343a(String str) {
        if (str != null) {
            m3339a().setText(str);
            addView(m3339a(), new LayoutParams(-1, -1));
            this.f2956c.setVisibility(8);
            m3344a(false);
            this.f2957d.setOnClickListener(new C14242(this));
        } else if (this.f2957d != null) {
            removeView(m3339a());
            this.f2957d = null;
            this.f2956c.setVisibility(0);
            m3344a(true);
        }
    }

    /* renamed from: a */
    public TextView m3339a() {
        if (this.f2957d == null) {
            this.f2957d = new Button(getContext());
            this.f2957d.setGravity(17);
        }
        this.f2957d.postInvalidate();
        return this.f2957d;
    }

    /* renamed from: a */
    public void m3342a(C1381h c1381h) {
        if (c1381h != null && c1381h.m3155d()) {
            this.f2956c.m3471a(c1381h);
            m3343a(null);
        }
    }

    /* renamed from: a */
    public void m3341a(ScaleType scaleType) {
        this.f2956c.setScaleType(scaleType);
    }

    /* renamed from: a */
    public void m3344a(boolean z) {
        this.f2958e = z;
    }
}
