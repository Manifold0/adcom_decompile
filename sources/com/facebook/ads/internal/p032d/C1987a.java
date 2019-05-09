package com.facebook.ads.internal.p032d;

import android.os.Bundle;
import android.view.View;
import com.facebook.ads.internal.p025w.p026b.C1910r;
import com.facebook.ads.internal.p029x.C2630a;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.facebook.ads.internal.d.a */
public final class C1987a implements C1910r<Bundle> {
    /* renamed from: a */
    private final View f4374a;
    /* renamed from: b */
    private final List<C1990d> f4375b;
    /* renamed from: c */
    private C1989c f4376c;

    public C1987a(View view, List<C1907b> list) {
        this.f4374a = view;
        this.f4375b = new ArrayList(list.size());
        for (C1907b c1990d : list) {
            this.f4375b.add(new C1990d(c1990d));
        }
        this.f4376c = new C1989c();
    }

    public C1987a(View view, List<C1907b> list, Bundle bundle) {
        this.f4374a = view;
        this.f4375b = new ArrayList(list.size());
        ArrayList parcelableArrayList = bundle.getParcelableArrayList("TESTS");
        for (int i = 0; i < list.size(); i++) {
            this.f4375b.add(new C1990d((C1907b) list.get(i), (Bundle) parcelableArrayList.get(i)));
        }
        this.f4376c = (C1989c) bundle.getSerializable("STATISTICS");
    }

    /* renamed from: a */
    public void m4747a() {
        this.f4376c.m4761a();
    }

    /* renamed from: a */
    public void m4748a(double d, double d2) {
        if (d2 >= 0.0d) {
            this.f4376c.m4764b(d, d2);
        }
        double c = (double) C2630a.m6753a(this.f4374a, 0).m6778c();
        this.f4376c.m4762a(d, c);
        for (C1990d a : this.f4375b) {
            a.m4769a(d, c);
        }
    }

    /* renamed from: b */
    public void m4749b() {
        this.f4376c.m4763b();
        for (C1990d a : this.f4375b) {
            a.m4768a();
        }
    }

    /* renamed from: c */
    public C1989c m4750c() {
        return this.f4376c;
    }

    /* renamed from: g */
    public Bundle mo5398g() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("STATISTICS", this.f4376c);
        ArrayList arrayList = new ArrayList(this.f4375b.size());
        for (C1990d g : this.f4375b) {
            arrayList.add(g.mo5398g());
        }
        bundle.putParcelableArrayList("TESTS", arrayList);
        return bundle;
    }
}
