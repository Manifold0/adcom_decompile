package com.tapjoy.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

public final class hr extends RelativeLayout {
    /* renamed from: a */
    private gu f8161a;
    /* renamed from: b */
    private C2932a f8162b;
    /* renamed from: c */
    private af f8163c = af.UNSPECIFIED;
    /* renamed from: d */
    private int f8164d = 0;
    /* renamed from: e */
    private int f8165e = 0;
    /* renamed from: f */
    private hd f8166f = null;
    /* renamed from: g */
    private ArrayList f8167g = null;
    /* renamed from: h */
    private ArrayList f8168h = null;

    /* renamed from: com.tapjoy.internal.hr$a */
    public interface C2932a {
        /* renamed from: a */
        void mo6288a();

        /* renamed from: a */
        void mo6289a(hc hcVar);
    }

    public hr(Context context, gu guVar, C2932a c2932a) {
        super(context);
        this.f8161a = guVar;
        this.f8162b = c2932a;
    }

    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f8162b.mo6288a();
    }

    protected final void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int size2 = MeasureSpec.getSize(heightMeasureSpec);
        if (size >= size2) {
            if (this.f8163c != af.LANDSCAPE) {
                this.f8163c = af.LANDSCAPE;
                m8085a();
            }
        } else if (this.f8163c != af.PORTRAIT) {
            this.f8163c = af.PORTRAIT;
            m8085a();
        }
        if (!(this.f8164d == size && this.f8165e == size2)) {
            float f;
            float f2;
            float f3;
            LayoutParams layoutParams;
            hc hcVar;
            float a;
            float a2;
            float a3;
            float a4;
            int i;
            this.f8164d = size;
            this.f8165e = size2;
            float f4 = (float) size;
            float f5 = (float) size2;
            if (!(this.f8166f == null || this.f8166f.f8059b == null)) {
                float f6 = ((this.f8166f.f8059b.y * f4) / this.f8166f.f8059b.x) / f5;
                if (f6 < 1.0f) {
                    f6 = (this.f8166f.f8059b.y * f4) / this.f8166f.f8059b.x;
                    f = f4;
                    f2 = 0.0f;
                    f3 = (f5 - f6) / 2.0f;
                    f4 = f6;
                } else if (f6 > 1.0f) {
                    f6 = (this.f8166f.f8059b.x * f5) / this.f8166f.f8059b.y;
                    f3 = 0.0f;
                    f2 = (f4 - f6) / 2.0f;
                    f4 = f5;
                    f = f6;
                }
                for (View view : ah.m7159a(this)) {
                    layoutParams = (LayoutParams) view.getLayoutParams();
                    hcVar = (hc) view.getTag();
                    a = hcVar.f8044a.m8029a(f, f4);
                    a2 = hcVar.f8045b.m8029a(f, f4);
                    a3 = hcVar.f8046c.m8029a(f, f4);
                    a4 = hcVar.f8047d.m8029a(f, f4);
                    i = hcVar.f8048e;
                    size = hcVar.f8049f;
                    if (i == 14) {
                        i = 9;
                        a += (f - a3) / 2.0f;
                    }
                    if (size == 15) {
                        size = 10;
                        a2 += (f4 - a4) / 2.0f;
                    }
                    layoutParams.addRule(i, -1);
                    layoutParams.addRule(size, -1);
                    layoutParams.width = Math.round(a3);
                    layoutParams.height = Math.round(a4);
                    if (i == 9) {
                        layoutParams.leftMargin = Math.round(f2 + a);
                    } else if (i == 11) {
                        layoutParams.rightMargin = Math.round(f2 + a);
                    }
                    if (size == 10) {
                        layoutParams.topMargin = Math.round(f3 + a2);
                    } else if (size == 12) {
                        layoutParams.bottomMargin = Math.round(f3 + a2);
                    }
                }
            }
            f2 = 0.0f;
            f3 = 0.0f;
            f = f4;
            f4 = f5;
            for (View view2 : ah.m7159a(this)) {
                layoutParams = (LayoutParams) view2.getLayoutParams();
                hcVar = (hc) view2.getTag();
                a = hcVar.f8044a.m8029a(f, f4);
                a2 = hcVar.f8045b.m8029a(f, f4);
                a3 = hcVar.f8046c.m8029a(f, f4);
                a4 = hcVar.f8047d.m8029a(f, f4);
                i = hcVar.f8048e;
                size = hcVar.f8049f;
                if (i == 14) {
                    i = 9;
                    a += (f - a3) / 2.0f;
                }
                if (size == 15) {
                    size = 10;
                    a2 += (f4 - a4) / 2.0f;
                }
                layoutParams.addRule(i, -1);
                layoutParams.addRule(size, -1);
                layoutParams.width = Math.round(a3);
                layoutParams.height = Math.round(a4);
                if (i == 9) {
                    layoutParams.leftMargin = Math.round(f2 + a);
                } else if (i == 11) {
                    layoutParams.rightMargin = Math.round(f2 + a);
                }
                if (size == 10) {
                    layoutParams.topMargin = Math.round(f3 + a2);
                } else if (size == 12) {
                    layoutParams.bottomMargin = Math.round(f3 + a2);
                }
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    protected final void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        Iterator it;
        hj hjVar;
        if (visibility == 0) {
            if (this.f8168h != null) {
                it = this.f8168h.iterator();
                while (it.hasNext()) {
                    hjVar = (hj) ((WeakReference) it.next()).get();
                    if (hjVar != null) {
                        hjVar.setVisibility(4);
                        hjVar.m8063b();
                    }
                }
            }
            if (this.f8167g != null) {
                it = this.f8167g.iterator();
                while (it.hasNext()) {
                    hjVar = (hj) ((WeakReference) it.next()).get();
                    if (hjVar != null) {
                        hjVar.setVisibility(0);
                        hjVar.m8061a();
                    }
                }
                return;
            }
            return;
        }
        if (this.f8167g != null) {
            it = this.f8167g.iterator();
            while (it.hasNext()) {
                hjVar = (hj) ((WeakReference) it.next()).get();
                if (hjVar != null) {
                    hjVar.m8063b();
                }
            }
        }
        if (this.f8168h != null) {
            it = this.f8168h.iterator();
            while (it.hasNext()) {
                hjVar = (hj) ((WeakReference) it.next()).get();
                if (hjVar != null) {
                    hjVar.m8063b();
                }
            }
        }
    }

    /* renamed from: a */
    private void m8085a() {
        hj hjVar;
        Iterator it = this.f8161a.f7981a.iterator();
        hd hdVar = null;
        while (it.hasNext()) {
            hd hdVar2 = (hd) it.next();
            if (hdVar2.f8058a == this.f8163c) {
                hdVar = hdVar2;
                break;
            }
            if (hdVar2.f8058a != af.UNSPECIFIED) {
                hdVar2 = hdVar;
            }
            hdVar = hdVar2;
        }
        removeAllViews();
        if (this.f8167g != null) {
            it = this.f8167g.iterator();
            while (it.hasNext()) {
                hjVar = (hj) ((WeakReference) it.next()).get();
                if (hjVar != null) {
                    hjVar.m8064c();
                }
            }
            this.f8167g.clear();
        }
        if (this.f8168h != null) {
            it = this.f8168h.iterator();
            while (it.hasNext()) {
                hjVar = (hj) ((WeakReference) it.next()).get();
                if (hjVar != null) {
                    hjVar.m8064c();
                }
            }
            this.f8168h.clear();
        }
        if (hdVar != null) {
            this.f8166f = hdVar;
            Context context = getContext();
            Iterator it2 = hdVar.f8060c.iterator();
            while (it2.hasNext()) {
                View hjVar2;
                View view;
                Bitmap bitmap;
                Drawable bitmapDrawable;
                BitmapDrawable bitmapDrawable2;
                hc hcVar = (hc) it2.next();
                View relativeLayout = new RelativeLayout(context);
                if (hcVar.f8055l.f8036c != null) {
                    hjVar2 = new hj(context);
                    hjVar2.setScaleType(ScaleType.FIT_XY);
                    hjVar2.m8062a(hcVar.f8055l.f8037d, hcVar.f8055l.f8036c);
                    if (this.f8167g == null) {
                        this.f8167g = new ArrayList();
                    }
                    this.f8167g.add(new WeakReference(hjVar2));
                } else {
                    hjVar2 = null;
                }
                if (hcVar.f8056m == null || hcVar.f8056m.f8036c == null) {
                    view = null;
                } else {
                    view = new hj(context);
                    view.setScaleType(ScaleType.FIT_XY);
                    view.m8062a(hcVar.f8056m.f8037d, hcVar.f8056m.f8036c);
                    if (this.f8168h == null) {
                        this.f8168h = new ArrayList();
                    }
                    this.f8168h.add(new WeakReference(view));
                }
                ViewGroup.LayoutParams layoutParams = new LayoutParams(0, 0);
                ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-1, -1);
                Bitmap bitmap2 = hcVar.f8055l.f8035b;
                if (hcVar.f8056m != null) {
                    bitmap = hcVar.f8056m.f8035b;
                } else {
                    bitmap = null;
                }
                if (bitmap2 != null) {
                    bitmapDrawable = new BitmapDrawable(null, bitmap2);
                } else {
                    bitmapDrawable = null;
                }
                if (bitmap != null) {
                    bitmapDrawable2 = new BitmapDrawable(null, bitmap);
                } else {
                    bitmapDrawable2 = null;
                }
                if (bitmapDrawable != null) {
                    ag.m7158a(relativeLayout, bitmapDrawable);
                }
                if (hjVar2 != null) {
                    relativeLayout.addView(hjVar2, layoutParams2);
                    hjVar2.m8061a();
                }
                if (view != null) {
                    relativeLayout.addView(view, layoutParams2);
                    view.setVisibility(4);
                }
                relativeLayout.setOnTouchListener(new OnTouchListener(this) {
                    /* renamed from: e */
                    final /* synthetic */ hr f8155e;

                    public final boolean onTouch(View v, MotionEvent event) {
                        boolean z = true;
                        if (event.getAction() == 0) {
                            if (!(view == null && bitmapDrawable2 == null)) {
                                if (hjVar2 != null) {
                                    hjVar2.m8063b();
                                    hjVar2.setVisibility(4);
                                }
                                ag.m7158a(v, null);
                            }
                            if (bitmapDrawable2 != null) {
                                ag.m7158a(v, bitmapDrawable2);
                            } else if (view != null) {
                                view.setVisibility(0);
                                view.m8061a();
                            }
                        } else if (event.getAction() == 1) {
                            float x = event.getX();
                            float y = event.getY();
                            if (x >= 0.0f && x < ((float) v.getWidth()) && y >= 0.0f && y < ((float) v.getHeight())) {
                                z = false;
                            }
                            if (z) {
                                if (bitmapDrawable != null) {
                                    ag.m7158a(v, bitmapDrawable);
                                } else if (bitmapDrawable2 != null) {
                                    ag.m7158a(v, null);
                                }
                            }
                            if (view != null) {
                                view.m8063b();
                                view.setVisibility(4);
                            }
                            if (!((view == null && bitmapDrawable2 == null) || hjVar2 == null || !z)) {
                                hjVar2.setVisibility(0);
                                hjVar2.m8061a();
                            }
                        }
                        return false;
                    }
                });
                final View view2 = relativeLayout;
                final hc hcVar2 = hcVar;
                relativeLayout.setOnClickListener(new OnClickListener(this) {
                    /* renamed from: e */
                    final /* synthetic */ hr f8160e;

                    public final void onClick(View v) {
                        if (view != null) {
                            view.m8063b();
                            view2.removeView(view);
                        }
                        if (hjVar2 != null) {
                            hjVar2.m8063b();
                            view2.removeView(hjVar2);
                        }
                        this.f8160e.f8162b.mo6289a(hcVar2);
                    }
                });
                relativeLayout.setTag(hcVar);
                addView(relativeLayout, layoutParams);
            }
        }
    }
}
