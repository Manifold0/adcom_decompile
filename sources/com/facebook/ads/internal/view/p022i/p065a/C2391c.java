package com.facebook.ads.internal.view.p022i.p065a;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.view.p022i.C2394a;

/* renamed from: com.facebook.ads.internal.view.i.a.c */
public abstract class C2391c extends RelativeLayout implements C2390b {
    @Nullable
    /* renamed from: a */
    private C2394a f5779a;

    public C2391c(Context context) {
        super(context);
    }

    public C2391c(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setLayoutParams(new LayoutParams(-1, -1));
    }

    /* renamed from: a */
    protected void mo5608a() {
    }

    /* renamed from: a */
    public void mo5597a(C2394a c2394a) {
        this.f5779a = c2394a;
        mo5608a();
    }

    /* renamed from: b */
    protected void mo5609b() {
    }

    /* renamed from: b */
    public void mo5598b(C2394a c2394a) {
        mo5609b();
        this.f5779a = null;
    }

    @Nullable
    protected C2394a getVideoView() {
        return this.f5779a;
    }
}
