// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import javax.annotation.concurrent.GuardedBy;
import android.app.PendingIntent;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient$ConnectionProgressReportCallbacks;

final class zaap extends zabf
{
    private final /* synthetic */ BaseGmsClient$ConnectionProgressReportCallbacks zago;
    
    zaap(final zaan zaan, final zabd zabd, final BaseGmsClient$ConnectionProgressReportCallbacks zago) {
        this.zago = zago;
        super(zabd);
    }
    
    @GuardedBy("mLock")
    public final void zaan() {
        this.zago.onReportServiceBinding(new ConnectionResult(16, (PendingIntent)null));
    }
}
