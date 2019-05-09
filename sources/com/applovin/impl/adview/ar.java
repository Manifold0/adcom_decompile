package com.applovin.impl.adview;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.RelativeLayout;
import com.applovin.impl.sdk.C1280g;
import com.applovin.impl.sdk.C1286m;
import com.applovin.impl.sdk.ee;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;

class ar extends Dialog implements aq {
    /* renamed from: a */
    private final Activity f1777a;
    /* renamed from: b */
    private final AppLovinSdk f1778b;
    /* renamed from: c */
    private final AppLovinLogger f1779c;
    /* renamed from: d */
    private final C1260n f1780d;
    /* renamed from: e */
    private final C1286m f1781e;
    /* renamed from: f */
    private final String f1782f;
    /* renamed from: g */
    private RelativeLayout f1783g;
    /* renamed from: h */
    private ak f1784h;

    ar(C1286m c1286m, String str, C1260n c1260n, Activity activity, AppLovinSdk appLovinSdk) {
        super(activity, 16973840);
        if (c1286m == null) {
            throw new IllegalArgumentException("No ad specified");
        } else if (c1260n == null) {
            throw new IllegalArgumentException("No main view specified");
        } else if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified");
        } else if (activity == null) {
            throw new IllegalArgumentException("No activity specified");
        } else {
            this.f1778b = appLovinSdk;
            this.f1779c = appLovinSdk.getLogger();
            this.f1777a = activity;
            this.f1780d = c1260n;
            this.f1781e = c1286m;
            this.f1782f = str;
            requestWindowFeature(1);
            setCancelable(false);
        }
    }

    /* renamed from: a */
    private int m2025a(int i) {
        return AppLovinSdkUtils.dpToPx(this.f1777a, i);
    }

    /* renamed from: a */
    private void m2027a(al alVar) {
        int i = 9;
        if (this.f1784h != null) {
            this.f1779c.mo4178w("ExpandedAdDialog", "Attempting to create duplicate close button");
            return;
        }
        this.f1784h = ak.m2007a(this.f1778b, getContext(), alVar);
        this.f1784h.setVisibility(8);
        this.f1784h.setOnClickListener(new av(this));
        this.f1784h.setClickable(false);
        ee eeVar = new ee(this.f1778b);
        int a = m2025a(eeVar.m2691R());
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(a, a);
        layoutParams.addRule(10);
        layoutParams.addRule(eeVar.m2694U() ? 9 : 11);
        this.f1784h.mo4047a(a);
        int a2 = m2025a(eeVar.m2693T());
        int a3 = m2025a(eeVar.m2692S());
        layoutParams.setMargins(a3, a2, a3, 0);
        this.f1783g.addView(this.f1784h, layoutParams);
        this.f1784h.bringToFront();
        int a4 = m2025a(eeVar.m2695V());
        View view = new View(this.f1777a);
        view.setBackgroundColor(0);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(a + a4, a + a4);
        layoutParams2.addRule(10);
        if (!eeVar.m2694U()) {
            i = 11;
        }
        layoutParams2.addRule(i);
        layoutParams2.setMargins(a3 - m2025a(5), a2 - m2025a(5), a3 - m2025a(5), 0);
        view.setOnClickListener(new aw(this));
        this.f1783g.addView(view, layoutParams2);
        view.bringToFront();
    }

    /* renamed from: b */
    private void m2029b() {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        this.f1780d.setLayoutParams(layoutParams);
        this.f1783g = new RelativeLayout(this.f1777a);
        this.f1783g.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        this.f1783g.setBackgroundColor(-1157627904);
        this.f1783g.addView(this.f1780d);
        if (!this.f1781e.m3032j()) {
            m2027a(this.f1781e.m3033k());
            m2032d();
        }
        setContentView(this.f1783g);
    }

    /* renamed from: c */
    private void m2030c() {
        this.f1780d.m2116a("javascript:al_onCloseTapped();", new as(this));
    }

    /* renamed from: d */
    private void m2032d() {
        this.f1777a.runOnUiThread(new ax(this));
    }

    /* renamed from: a */
    public C1286m m2037a() {
        return this.f1781e;
    }

    public void dismiss() {
        C1280g c = this.f1780d.m2118c();
        if (c != null) {
            c.m2916e();
        }
        this.f1777a.runOnUiThread(new au(this));
    }

    public void onBackPressed() {
        this.f1780d.m2116a("javascript:al_onBackPressed();", new at(this));
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        m2029b();
    }

    protected void onStart() {
        super.onStart();
        try {
            Window window = getWindow();
            if (window != null) {
                window.setFlags(this.f1777a.getWindow().getAttributes().flags, this.f1777a.getWindow().getAttributes().flags);
                if (this.f1781e.m1793F()) {
                    window.addFlags(16777216);
                    return;
                }
                return;
            }
            this.f1779c.mo4173e("ExpandedAdDialog", "Unable to turn on hardware acceleration - window is null");
        } catch (Throwable th) {
            this.f1779c.mo4174e("ExpandedAdDialog", "Setting window flags failed.", th);
        }
    }
}
