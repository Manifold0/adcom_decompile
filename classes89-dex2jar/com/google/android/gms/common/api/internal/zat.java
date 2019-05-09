// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

final class zat implements Runnable
{
    private final /* synthetic */ zas zaeq;
    
    zat(final zas zaeq) {
        this.zaeq = zaeq;
    }
    
    @Override
    public final void run() {
        this.zaeq.zaeo.lock();
        try {
            this.zaeq.zax();
        }
        finally {
            this.zaeq.zaeo.unlock();
        }
    }
}
