// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

final class zaal implements Runnable
{
    private final /* synthetic */ zaak zagj;
    
    zaal(final zaak zagj) {
        this.zagj = zagj;
    }
    
    @Override
    public final void run() {
        this.zagj.zaey.cancelAvailabilityErrorNotifications(this.zagj.mContext);
    }
}
