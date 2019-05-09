// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import android.support.annotation.Nullable;
import android.os.Bundle;

final class zau implements zabt
{
    private final /* synthetic */ zas zaeq;
    
    private zau(final zas zaeq) {
        this.zaeq = zaeq;
    }
    
    @Override
    public final void zab(final int n, final boolean b) {
        this.zaeq.zaeo.lock();
        try {
            if (this.zaeq.zaen || this.zaeq.zaem == null || !this.zaeq.zaem.isSuccess()) {
                this.zaeq.zaen = false;
                this.zaeq.zaa(n, b);
                return;
            }
            this.zaeq.zaen = true;
            this.zaeq.zaeg.onConnectionSuspended(n);
        }
        finally {
            this.zaeq.zaeo.unlock();
        }
    }
    
    @Override
    public final void zab(@Nullable final Bundle bundle) {
        this.zaeq.zaeo.lock();
        try {
            this.zaeq.zaa(bundle);
            this.zaeq.zael = ConnectionResult.RESULT_SUCCESS;
            this.zaeq.zax();
        }
        finally {
            this.zaeq.zaeo.unlock();
        }
    }
    
    @Override
    public final void zac(@NonNull final ConnectionResult connectionResult) {
        this.zaeq.zaeo.lock();
        try {
            this.zaeq.zael = connectionResult;
            this.zaeq.zax();
        }
        finally {
            this.zaeq.zaeo.unlock();
        }
    }
}
