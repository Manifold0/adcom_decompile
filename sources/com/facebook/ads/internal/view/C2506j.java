package com.facebook.ads.internal.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p050r.C2078a;
import com.facebook.ads.internal.view.p060d.C2306a;
import com.facebook.ads.internal.view.p060d.C2307b;
import com.facebook.ads.internal.view.p060d.C2309c;
import com.facebook.ads.internal.view.p060d.C2309c.C2308a;

/* renamed from: com.facebook.ads.internal.view.j */
public class C2506j extends C2309c implements C2308a {
    /* renamed from: c */
    private final HScrollLinearLayoutManager f6114c;
    /* renamed from: d */
    private C2505a f6115d;
    /* renamed from: e */
    private int f6116e = -1;
    /* renamed from: f */
    private int f6117f = -1;
    /* renamed from: g */
    private int f6118g = 0;
    /* renamed from: h */
    private int f6119h = 0;

    /* renamed from: com.facebook.ads.internal.view.j$a */
    public interface C2505a {
        /* renamed from: a */
        void m6465a(int i, int i2);
    }

    public C2506j(Context context) {
        super(context);
        this.f6114c = new HScrollLinearLayoutManager(context, new C2307b(), new C2306a());
        m6466a();
    }

    public C2506j(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f6114c = new HScrollLinearLayoutManager(context, new C2307b(), new C2306a());
        m6466a();
    }

    public C2506j(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f6114c = new HScrollLinearLayoutManager(context, new C2307b(), new C2306a());
        m6466a();
    }

    /* renamed from: a */
    private void m6466a() {
        this.f6114c.setOrientation(0);
        setLayoutManager(this.f6114c);
        setSaveEnabled(false);
        setSnapDelegate(this);
    }

    /* renamed from: a */
    public int mo5634a(int i) {
        int abs = Math.abs(i);
        return abs <= this.a ? 0 : this.f6118g == 0 ? 1 : (abs / this.f6118g) + 1;
    }

    /* renamed from: a */
    protected void mo5635a(int i, boolean z) {
        super.mo5635a(i, z);
        if (i != this.f6116e || 0 != this.f6117f) {
            this.f6116e = i;
            this.f6117f = 0;
            if (this.f6115d != null) {
                this.f6115d.m6465a(this.f6116e, this.f6117f);
            }
        }
    }

    public int getChildSpacing() {
        return this.f6119h;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int r = C2078a.m5107q(getContext()) ? (((int) C2600x.f6420b) * C2078a.m5108r(getContext())) + paddingTop : Math.round(((float) getMeasuredWidth()) / 1.91f);
        switch (MeasureSpec.getMode(i2)) {
            case Integer.MIN_VALUE:
                r = Math.min(MeasureSpec.getSize(i2), r);
                break;
            case 1073741824:
                r = MeasureSpec.getSize(i2);
                break;
        }
        r -= paddingTop;
        if (C2078a.m5107q(getContext())) {
            r = Math.min(C2254c.f5293a, r);
        } else {
            int i3 = this.f6119h * 2;
            int measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - i3;
            int itemCount = getAdapter().getItemCount();
            int i4 = 0;
            int i5 = Integer.MAX_VALUE;
            while (i5 > r) {
                i4++;
                if (i4 < itemCount) {
                    i5 = (int) (((float) (measuredWidth - (i4 * i3))) / (((float) i4) + 0.333f));
                }
            }
            r = i5;
        }
        setMeasuredDimension(getMeasuredWidth(), r + paddingTop);
        if (!C2078a.m5107q(getContext())) {
            setChildWidth(r + (this.f6119h * 2));
        }
    }

    public void setAdapter(@Nullable Adapter adapter) {
        this.f6114c.m5602a(adapter == null ? -1 : adapter.hashCode());
        super.setAdapter(adapter);
    }

    public void setChildSpacing(int i) {
        this.f6119h = i;
    }

    public void setChildWidth(int i) {
        this.f6118g = i;
        int measuredWidth = getMeasuredWidth();
        this.f6114c.m5603b((((measuredWidth - getPaddingLeft()) - getPaddingRight()) - this.f6118g) / 2);
        this.f6114c.m5601a(((double) this.f6118g) / ((double) measuredWidth));
    }

    public void setCurrentPosition(int i) {
        mo5635a(i, false);
    }

    public void setOnPageChangedListener(C2505a c2505a) {
        this.f6115d = c2505a;
    }
}
