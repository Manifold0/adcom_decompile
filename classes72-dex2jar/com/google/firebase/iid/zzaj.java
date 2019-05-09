// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.iid;

import android.os.Bundle;

final class zzaj extends zzak<Void>
{
    zzaj(final int n, final int n2, final Bundle bundle) {
        super(n, 2, bundle);
    }
    
    @Override
    final boolean zzab() {
        return true;
    }
    
    @Override
    final void zzb(final Bundle bundle) {
        if (bundle.getBoolean("ack", false)) {
            this.finish(null);
            return;
        }
        this.zza(new zzal(4, "Invalid response to one way request"));
    }
}
