// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.support.annotation.WorkerThread;

abstract class zaau implements Runnable
{
    private final /* synthetic */ zaak zagj;
    
    private zaau(final zaak zagj) {
        this.zagj = zagj;
    }
    
    @WorkerThread
    @Override
    public void run() {
        this.zagj.zaeo.lock();
        try {
            if (Thread.interrupted()) {
                return;
            }
            this.zaan();
        }
        catch (RuntimeException ex) {
            this.zagj.zaft.zab(ex);
        }
        finally {
            this.zagj.zaeo.unlock();
        }
    }
    
    @WorkerThread
    protected abstract void zaan();
}
