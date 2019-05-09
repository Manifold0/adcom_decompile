// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.Libraries;

import com.chartboost.sdk.impl.aq;
import android.app.Activity;
import java.lang.ref.WeakReference;

public final class j extends WeakReference<Activity>
{
    public final int a;
    
    public j(final Activity activity) {
        super(activity);
        aq.a("WeakActivity.WeakActivity", activity);
        this.a = activity.hashCode();
    }
    
    public boolean a(final Activity activity) {
        return activity != null && activity.hashCode() == this.a;
    }
    
    @Override
    public int hashCode() {
        return this.a;
    }
}
