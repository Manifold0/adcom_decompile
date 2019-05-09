package com.tapjoy;

import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class TapjoyDisplayMetricsUtil {
    /* renamed from: a */
    private Context f7084a;
    /* renamed from: b */
    private Configuration f7085b;
    /* renamed from: c */
    private DisplayMetrics f7086c = new DisplayMetrics();

    public TapjoyDisplayMetricsUtil(Context theContext) {
        this.f7084a = theContext;
        ((WindowManager) this.f7084a.getSystemService("window")).getDefaultDisplay().getMetrics(this.f7086c);
        this.f7085b = this.f7084a.getResources().getConfiguration();
    }

    public int getScreenDensityDPI() {
        return this.f7086c.densityDpi;
    }

    public float getScreenDensityScale() {
        return this.f7086c.density;
    }

    public int getScreenLayoutSize() {
        return this.f7085b.screenLayout & 15;
    }
}
