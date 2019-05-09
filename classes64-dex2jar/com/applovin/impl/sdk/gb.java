// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.Timer;
import java.util.TimerTask;

class gb extends TimerTask
{
    final /* synthetic */ ga a;
    
    gb(final ga a) {
        this.a = a;
    }
    
    @Override
    public void run() {
        try {
            this.a.e.run();
            synchronized (this.a.g) {
                this.a.b = null;
            }
        }
        catch (Throwable t) {
            if (this.a.a != null) {
                this.a.a.getLogger().e("Timer", "Encountered error while executing timed task", t);
            }
            synchronized (this.a.g) {
                this.a.b = null;
            }
        }
        finally {
            synchronized (this.a.g) {
                this.a.b = null;
            }
            // monitorexit(ga.c(this.a))
        }
    }
}
