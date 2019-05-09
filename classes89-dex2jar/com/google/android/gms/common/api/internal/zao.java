// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.app.Dialog;

final class zao extends zabr
{
    private final /* synthetic */ Dialog zadl;
    private final /* synthetic */ zan zadm;
    
    zao(final zan zadm, final Dialog zadl) {
        this.zadm = zadm;
        this.zadl = zadl;
    }
    
    @Override
    public final void zas() {
        this.zadm.zadk.zaq();
        if (this.zadl.isShowing()) {
            this.zadl.dismiss();
        }
    }
}
