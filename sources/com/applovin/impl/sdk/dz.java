package com.applovin.impl.sdk;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

class dz implements ActivityLifecycleCallbacks {
    /* renamed from: a */
    final /* synthetic */ dy f2371a;

    dz(dy dyVar) {
        this.f2371a = dyVar;
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        this.f2371a.m2646j();
    }

    public void onActivityDestroyed(Activity activity) {
        this.f2371a.m2646j();
    }

    public void onActivityPaused(Activity activity) {
        this.f2371a.m2639e();
    }

    public void onActivityResumed(Activity activity) {
        this.f2371a.m2645i();
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        this.f2371a.m2641f();
    }

    public void onActivityStarted(Activity activity) {
        this.f2371a.m2644h();
    }

    public void onActivityStopped(Activity activity) {
        this.f2371a.m2643g();
    }
}
