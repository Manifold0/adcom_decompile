// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

abstract class zabf
{
    private final zabd zahu;
    
    protected zabf(final zabd zahu) {
        this.zahu = zahu;
    }
    
    protected abstract void zaan();
    
    public final void zac(final zabe zabe) {
        zabe.zaeo.lock();
        try {
            if (zabe.zahq != this.zahu) {
                return;
            }
            this.zaan();
        }
        finally {
            zabe.zaeo.unlock();
        }
    }
}
