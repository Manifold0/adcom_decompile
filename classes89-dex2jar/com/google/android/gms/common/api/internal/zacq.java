// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

final class zacq implements zacs
{
    private final /* synthetic */ zacp zalb;
    
    zacq(final zacp zalb) {
        this.zalb = zalb;
    }
    
    @Override
    public final void zac(final BasePendingResult<?> basePendingResult) {
        this.zalb.zakz.remove(basePendingResult);
    }
}
