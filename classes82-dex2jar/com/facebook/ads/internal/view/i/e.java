// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.i;

import android.os.Handler;
import android.database.ContentObserver;

class e extends ContentObserver
{
    private final c a;
    
    e(final Handler handler, final c a) {
        super(handler);
        this.a = a;
    }
    
    public boolean deliverSelfNotifications() {
        return false;
    }
    
    public void onChange(final boolean b) {
        this.a.e();
    }
}
