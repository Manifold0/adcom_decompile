package com.tapjoy.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.tonyodev.fetch.FetchService;
import java.util.ArrayList;
import java.util.Iterator;

public final class ht extends RelativeLayout implements OnClickListener {
    /* renamed from: a */
    private boolean f8172a;
    /* renamed from: b */
    private float f8173b = 1.0f;
    /* renamed from: c */
    private View f8174c;
    /* renamed from: d */
    private View f8175d;
    /* renamed from: e */
    private FrameLayout f8176e;
    /* renamed from: f */
    private ImageView f8177f;
    /* renamed from: g */
    private hq f8178g;
    /* renamed from: h */
    private gx f8179h;
    /* renamed from: i */
    private C2952a f8180i;

    /* renamed from: com.tapjoy.internal.ht$a */
    public interface C2952a {
        /* renamed from: a */
        void mo6318a();

        /* renamed from: a */
        void mo6319a(gv gvVar);

        /* renamed from: b */
        void mo6320b();
    }

    public ht(Context context, gx gxVar, C2952a c2952a) {
        int i = 1;
        super(context);
        this.f8179h = gxVar;
        this.f8180i = c2952a;
        Context context2 = getContext();
        this.f8174c = new View(context2);
        this.f8174c.setId(1);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(0, 0);
        layoutParams.addRule(13);
        addView(this.f8174c, layoutParams);
        this.f8175d = new View(context2);
        layoutParams = new RelativeLayout.LayoutParams(0, 0);
        layoutParams.addRule(13);
        addView(this.f8175d, layoutParams);
        this.f8176e = new FrameLayout(context2);
        layoutParams = new RelativeLayout.LayoutParams(0, 0);
        layoutParams.addRule(13);
        addView(this.f8176e, layoutParams);
        this.f8177f = new ImageView(context2);
        this.f8177f.setOnClickListener(this);
        layoutParams = new RelativeLayout.LayoutParams(0, 0);
        layoutParams.addRule(7, this.f8174c.getId());
        layoutParams.addRule(6, this.f8174c.getId());
        addView(this.f8177f, layoutParams);
        if (this.f8179h.f8017m != null) {
            gy gyVar = this.f8179h.f8017m;
            if (gyVar.f8019a == null || (gyVar.f8020b == null && gyVar.f8021c == null)) {
                i = 0;
            }
            if (i != 0) {
                this.f8178g = new hq(context2);
                this.f8178g.setOnClickListener(this);
                LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(0, 0);
                layoutParams2.addRule(5, this.f8175d.getId());
                layoutParams2.addRule(8, this.f8175d.getId());
                addView(this.f8178g, layoutParams2);
            }
        }
        this.f8177f.setImageBitmap(gxVar.f8007c.f8035b);
        if (this.f8178g != null && gxVar.f8017m != null && gxVar.f8017m.f8019a != null) {
            this.f8178g.setImageBitmap(gxVar.f8017m.f8019a.f8035b);
        }
    }

    public final void setLandscape(boolean landscape) {
        Bitmap bitmap;
        Bitmap bitmap2;
        ArrayList arrayList;
        this.f8172a = landscape;
        if (landscape) {
            bitmap = this.f8179h.f8006b.f8035b;
            bitmap2 = this.f8179h.f8010f.f8035b;
            arrayList = this.f8179h.f8014j;
        } else {
            bitmap = this.f8179h.f8005a.f8035b;
            bitmap2 = this.f8179h.f8009e.f8035b;
            arrayList = this.f8179h.f8013i;
        }
        ag.m7158a(this.f8174c, new BitmapDrawable(null, bitmap));
        ag.m7158a(this.f8175d, new BitmapDrawable(null, bitmap2));
        if (this.f8176e.getChildCount() > 0) {
            this.f8176e.removeAllViews();
        }
        Context context = getContext();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            gv gvVar = (gv) it.next();
            View view = new View(context);
            view.setTag(gvVar);
            view.setOnClickListener(this);
            this.f8176e.addView(view, new FrameLayout.LayoutParams(0, 0, 51));
        }
    }

    protected final void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int i = 15;
        int i2 = 0;
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int size2 = MeasureSpec.getSize(heightMeasureSpec);
        if (this.f8172a) {
            this.f8173b = Math.min(((float) size) / 480.0f, ((float) size2) / 320.0f);
        } else {
            this.f8173b = Math.min(((float) size) / 320.0f, ((float) size2) / 480.0f);
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f8174c.getLayoutParams();
        layoutParams.width = m8086a(this.f8172a ? FetchService.QUERY_SINGLE : FetchService.ACTION_LOGGING);
        layoutParams.height = m8086a(this.f8172a ? FetchService.ACTION_LOGGING : FetchService.QUERY_SINGLE);
        layoutParams = (RelativeLayout.LayoutParams) this.f8175d.getLayoutParams();
        layoutParams.width = m8086a(this.f8172a ? 448 : IronSourceConstants.INTERSTITIAL_AD_REWARDED);
        layoutParams.height = m8086a(this.f8172a ? IronSourceConstants.INTERSTITIAL_AD_REWARDED : 448);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.f8176e.getLayoutParams();
        layoutParams2.width = layoutParams.width;
        layoutParams2.height = layoutParams.height;
        for (View view : ah.m7159a(this.f8176e)) {
            FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) view.getLayoutParams();
            Rect rect = ((gv) view.getTag()).f7985a;
            layoutParams3.width = m8086a(rect.width());
            layoutParams3.height = m8086a(rect.height());
            layoutParams3.leftMargin = m8086a(rect.left);
            layoutParams3.topMargin = m8086a(rect.top);
        }
        size2 = m8086a(0);
        this.f8177f.setPadding(size2, size2, size2, size2);
        layoutParams = (RelativeLayout.LayoutParams) this.f8177f.getLayoutParams();
        layoutParams.width = m8086a(30);
        layoutParams.height = layoutParams.width;
        layoutParams.rightMargin = (-size2) + m8086a(this.f8179h.f8008d.x);
        layoutParams.topMargin = (-size2) + m8086a(this.f8179h.f8008d.y);
        if (this.f8178g != null) {
            int a = m8086a(this.f8172a ? 16 : 15);
            if (!this.f8172a) {
                i = 16;
            }
            int a2 = m8086a(i);
            this.f8178g.setPadding(size2, size2, size2, size2);
            layoutParams = (RelativeLayout.LayoutParams) this.f8178g.getLayoutParams();
            layoutParams.width = m8086a(26);
            layoutParams.height = layoutParams.width;
            if (this.f8179h.f8017m != null) {
                Point point;
                gy gyVar;
                if (this.f8172a) {
                    gyVar = this.f8179h.f8017m;
                    if (gyVar.f8020b == null) {
                        point = gyVar.f8021c;
                    } else {
                        point = gyVar.f8020b;
                    }
                } else {
                    gyVar = this.f8179h.f8017m;
                    if (gyVar.f8021c == null) {
                        point = gyVar.f8020b;
                    } else {
                        point = gyVar.f8021c;
                    }
                }
                if (point != null) {
                    i2 = point.x;
                    size2 = point.y;
                    layoutParams.leftMargin = m8086a(i2) + a;
                    layoutParams.topMargin = m8086a(size2) + a2;
                }
            }
            size2 = 0;
            layoutParams.leftMargin = m8086a(i2) + a;
            layoutParams.topMargin = m8086a(size2) + a2;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /* renamed from: a */
    private int m8086a(int i) {
        return (int) (((float) i) * this.f8173b);
    }

    protected final void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    public final void onClick(View v) {
        if (v == this.f8177f) {
            this.f8180i.mo6318a();
        } else if (v != null && v == this.f8178g) {
            hq hqVar = this.f8178g;
            hqVar.f8145a = !hqVar.f8145a;
            hqVar.m8083a();
            hqVar.invalidate();
            this.f8180i.mo6320b();
        } else if (v.getTag() instanceof gv) {
            this.f8180i.mo6319a((gv) v.getTag());
        }
    }
}
