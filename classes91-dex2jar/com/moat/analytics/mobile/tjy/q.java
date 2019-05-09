// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import com.moat.analytics.mobile.tjy.base.exception.a;
import android.os.Handler;
import android.os.Looper;

class q implements Runnable
{
    final /* synthetic */ n a;
    
    q(final n a) {
        this.a = a;
    }
    
    @Override
    public void run() {
        try {
            new Handler(Looper.getMainLooper()).post((Runnable)new r(this));
        }
        catch (Exception ex) {
            com.moat.analytics.mobile.tjy.base.exception.a.a(ex);
        }
    }
}
