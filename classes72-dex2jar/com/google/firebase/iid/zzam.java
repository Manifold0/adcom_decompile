// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.iid;

import android.os.Bundle;

final class zzam extends zzak<Bundle>
{
    zzam(final int n, final int n2, final Bundle bundle) {
        super(n, 1, bundle);
    }
    
    @Override
    final boolean zzab() {
        return false;
    }
    
    @Override
    final void zzb(Bundle bundle) {
        if ((bundle = bundle.getBundle("data")) == null) {
            bundle = Bundle.EMPTY;
        }
        this.finish(bundle);
    }
}
