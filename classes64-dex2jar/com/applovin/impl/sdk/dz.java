// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import android.os.Bundle;
import android.app.Activity;
import android.app.Application$ActivityLifecycleCallbacks;

class dz implements Application$ActivityLifecycleCallbacks
{
    final /* synthetic */ dy a;
    
    dz(final dy a) {
        this.a = a;
    }
    
    public void onActivityCreated(final Activity activity, final Bundle bundle) {
        this.a.j();
    }
    
    public void onActivityDestroyed(final Activity activity) {
        this.a.j();
    }
    
    public void onActivityPaused(final Activity activity) {
        this.a.e();
    }
    
    public void onActivityResumed(final Activity activity) {
        this.a.i();
    }
    
    public void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
        this.a.f();
    }
    
    public void onActivityStarted(final Activity activity) {
        this.a.h();
    }
    
    public void onActivityStopped(final Activity activity) {
        this.a.g();
    }
}
