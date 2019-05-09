// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.internal.LifecycleFragment;
import android.content.Intent;

final class zae extends DialogRedirect
{
    private final /* synthetic */ int val$requestCode;
    private final /* synthetic */ Intent zaoh;
    private final /* synthetic */ LifecycleFragment zaoi;
    
    zae(final Intent zaoh, final LifecycleFragment zaoi, final int val$requestCode) {
        this.zaoh = zaoh;
        this.zaoi = zaoi;
        this.val$requestCode = val$requestCode;
    }
    
    public final void redirect() {
        if (this.zaoh != null) {
            this.zaoi.startActivityForResult(this.zaoh, this.val$requestCode);
        }
    }
}
