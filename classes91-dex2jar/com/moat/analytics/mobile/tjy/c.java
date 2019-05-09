// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import android.app.Application$ActivityLifecycleCallbacks;
import android.app.Application;
import android.util.Log;
import android.app.Activity;
import java.lang.ref.WeakReference;

class c implements a
{
    private final WeakReference a;
    private final WeakReference b;
    private boolean c;
    private final ap d;
    private boolean e;
    
    c(final Activity activity, final ap d) {
        com.moat.analytics.mobile.tjy.base.asserts.a.a(activity);
        if (d.b()) {
            final StringBuilder sb = new StringBuilder("Listening to Activity: ");
            String string;
            if (activity != null) {
                string = activity.getClass() + "@" + activity.hashCode();
            }
            else {
                string = "null";
            }
            Log.d("MoatActivityState", sb.append(string).toString());
        }
        this.a = new WeakReference((T)activity.getApplication());
        this.b = new WeakReference((T)activity);
        this.d = d;
        this.c = false;
    }
    
    @Override
    public boolean a() {
        return this.e;
    }
    
    @Override
    public void b() {
        if (!this.c) {
            ((Application)this.a.get()).registerActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)new e(this, null));
        }
    }
    
    @Override
    public Activity c() {
        return (Activity)this.b.get();
    }
}
