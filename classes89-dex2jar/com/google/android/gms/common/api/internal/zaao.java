// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import javax.annotation.concurrent.GuardedBy;
import com.google.android.gms.common.ConnectionResult;

final class zaao extends zabf
{
    private final /* synthetic */ ConnectionResult zagm;
    private final /* synthetic */ zaan zagn;
    
    zaao(final zaan zagn, final zabd zabd, final ConnectionResult zagm) {
        this.zagn = zagn;
        this.zagm = zagm;
        super(zabd);
    }
    
    @GuardedBy("mLock")
    public final void zaan() {
        this.zagn.zagj.zae(this.zagm);
    }
}
