package com.chartboost.sdk.impl;

import com.chartboost.sdk.C1397c;
import com.chartboost.sdk.C1397c.C1396c;
import com.chartboost.sdk.C1409h;
import com.chartboost.sdk.Model.C1388c;
import com.chartboost.sdk.Model.C1389d;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.impl.C1440e.C1439a;

/* renamed from: com.chartboost.sdk.impl.d */
class C1435d implements C1389d {
    /* renamed from: a */
    private final C1440e f3217a;
    /* renamed from: b */
    private final C1441f f3218b;

    C1435d(C1440e c1440e, C1441f c1441f) {
        this.f3217a = c1440e;
        this.f3218b = c1441f;
    }

    /* renamed from: a */
    public void mo4307a(C1388c c1388c) {
        c1388c.f2766l = 1;
        if (this.f3217a.f3238f.m3545f(this.f3218b.f3260b)) {
            this.f3217a.f3237e.m3253a(c1388c);
            if (this.f3217a.f3238f.f3209a == 0 && c1388c.f2768n != 1 && c1388c.f2770p.f2730b != 1) {
                mo4310c(c1388c);
                return;
            }
            return;
        }
        C1440e c1440e = this.f3217a;
        c1440e.getClass();
        this.f3217a.f3233a.execute(new C1439a(c1440e, 7, this.f3218b.f3260b, this.f3218b, null));
    }

    /* renamed from: b */
    public void mo4309b(C1388c c1388c) {
        C1440e c1440e = this.f3217a;
        c1440e.getClass();
        this.f3217a.f3233a.execute(new C1439a(c1440e, 7, this.f3218b.f3260b, this.f3218b, null));
    }

    /* renamed from: c */
    public void mo4310c(C1388c c1388c) {
        c1388c.f2772r = true;
        C1440e c1440e = this.f3217a;
        c1440e.getClass();
        this.f3217a.f3233a.execute(new C1439a(c1440e, 5, this.f3218b.f3260b, this.f3218b, null));
    }

    /* renamed from: a */
    public void mo4308a(C1388c c1388c, CBImpressionError cBImpressionError) {
        C1397c c1397c = this.f3217a.f3237e;
        c1397c.getClass();
        Runnable c1396c = new C1396c(c1397c, 11);
        c1396c.f2836d = c1388c;
        C1409h.m3328b(c1396c);
        C1440e c1440e = this.f3217a;
        c1440e.getClass();
        this.f3217a.f3233a.execute(new C1439a(c1440e, 6, this.f3218b.f3260b, this.f3218b, cBImpressionError));
    }
}
