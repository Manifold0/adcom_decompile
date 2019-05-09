package com.facebook.ads.internal.adapters;

import android.content.Context;
import com.facebook.ads.internal.p025w.p026b.C2567c;
import com.facebook.ads.internal.p029x.C2630a;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.adapters.b */
public abstract class C1888b {
    /* renamed from: a */
    protected final C1895c f4023a;
    /* renamed from: b */
    protected final C2630a f4024b;
    /* renamed from: c */
    private final Context f4025c;
    /* renamed from: d */
    private boolean f4026d;

    public C1888b(Context context, C1895c c1895c, C2630a c2630a) {
        this.f4025c = context;
        this.f4023a = c1895c;
        this.f4024b = c2630a;
    }

    /* renamed from: a */
    public final void m4369a() {
        if (!this.f4026d) {
            if (this.f4023a != null) {
                this.f4023a.mo5392a();
            }
            Map hashMap = new HashMap();
            if (this.f4024b != null) {
                this.f4024b.m6771a(hashMap);
            }
            mo5414a(hashMap);
            this.f4026d = true;
            C2567c.m6621a(this.f4025c, "Impression logged");
            if (this.f4023a != null) {
                this.f4023a.mo5503b();
            }
        }
    }

    /* renamed from: a */
    protected abstract void mo5414a(Map<String, String> map);
}
