// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.internal.BaseGmsClient$SignOutCallbacks;

final class zabm implements BaseGmsClient$SignOutCallbacks
{
    final /* synthetic */ GoogleApiManager.zaa zaiy;
    
    zabm(final GoogleApiManager.zaa zaiy) {
        this.zaiy = zaiy;
    }
    
    public final void onSignOutComplete() {
        this.zaiy.zaim.handler.post((Runnable)new zabn(this));
    }
}
