package com.facebook.ads.internal.view.p060d;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/* renamed from: com.facebook.ads.internal.view.d.c */
public class C2309c extends RecyclerView implements OnTouchListener {
    /* renamed from: a */
    protected final int f5486a = m5940a();
    /* renamed from: b */
    protected int f5487b = 0;
    /* renamed from: c */
    private int f5488c = 0;
    /* renamed from: d */
    private boolean f5489d = true;
    /* renamed from: e */
    private boolean f5490e = false;
    /* renamed from: f */
    private LinearLayoutManager f5491f;
    /* renamed from: g */
    private C2308a f5492g;

    /* renamed from: com.facebook.ads.internal.view.d.c$a */
    public interface C2308a {
        /* renamed from: a */
        int mo5634a(int i);
    }

    public C2309c(Context context) {
        super(context);
        setOnTouchListener(this);
    }

    public C2309c(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnTouchListener(this);
    }

    public C2309c(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setOnTouchListener(this);
    }

    /* renamed from: a */
    private int m5940a() {
        return ((int) getContext().getResources().getDisplayMetrics().density) * 10;
    }

    private int getItemCount() {
        return getAdapter() == null ? 0 : getAdapter().getItemCount();
    }

    /* renamed from: a */
    protected void mo5635a(int i, boolean z) {
        if (getAdapter() != null) {
            this.f5487b = i;
            if (z) {
                smoothScrollToPosition(i);
            } else {
                scrollToPosition(i);
            }
        }
    }

    public int getCurrentPosition() {
        return this.f5487b;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        int rawX = (int) motionEvent.getRawX();
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1 || actionMasked == 6 || actionMasked == 3 || actionMasked == 4) {
            if (this.f5490e) {
                rawX = this.f5488c - rawX;
                actionMasked = this.f5492g.mo5634a(rawX);
                rawX = rawX > this.f5486a ? Math.min(this.f5487b + actionMasked, getItemCount() - 1) : rawX < (-this.f5486a) ? Math.max(this.f5487b - actionMasked, 0) : this.f5487b;
                mo5635a(rawX, true);
            }
            this.f5489d = true;
            this.f5490e = false;
            return true;
        }
        if (actionMasked == 0 || actionMasked == 5 || (this.f5489d && actionMasked == 2)) {
            this.f5488c = rawX;
            if (this.f5489d) {
                this.f5489d = false;
            }
            this.f5490e = true;
        }
        return false;
    }

    public void setLayoutManager(LayoutManager layoutManager) {
        if (layoutManager instanceof LinearLayoutManager) {
            super.setLayoutManager(layoutManager);
            this.f5491f = (LinearLayoutManager) layoutManager;
            return;
        }
        throw new IllegalArgumentException("SnapRecyclerView only supports LinearLayoutManager");
    }

    public void setSnapDelegate(C2308a c2308a) {
        this.f5492g = c2308a;
    }
}
