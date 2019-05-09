// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import com.google.android.gms.signin.internal.zaj;

final class zacg implements Runnable
{
    private final /* synthetic */ zaj zagr;
    private final /* synthetic */ zace zakk;
    
    zacg(final zace zakk, final zaj zagr) {
        this.zakk = zakk;
        this.zagr = zagr;
    }
    
    @Override
    public final void run() {
        this.zakk.zac(this.zagr);
    }
}
