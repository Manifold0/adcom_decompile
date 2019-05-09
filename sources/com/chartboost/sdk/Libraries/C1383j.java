package com.chartboost.sdk.Libraries;

import android.app.Activity;
import com.chartboost.sdk.impl.aq;
import java.lang.ref.WeakReference;

/* renamed from: com.chartboost.sdk.Libraries.j */
public final class C1383j extends WeakReference<Activity> {
    /* renamed from: a */
    public final int f2714a;

    public C1383j(Activity activity) {
        super(activity);
        aq.m3408a("WeakActivity.WeakActivity", (Object) activity);
        this.f2714a = activity.hashCode();
    }

    /* renamed from: a */
    public boolean m3160a(Activity activity) {
        return activity != null && activity.hashCode() == this.f2714a;
    }

    public int hashCode() {
        return this.f2714a;
    }
}
