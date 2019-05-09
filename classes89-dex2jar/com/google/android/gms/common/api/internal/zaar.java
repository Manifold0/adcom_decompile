// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.support.annotation.BinderThread;
import com.google.android.gms.signin.internal.zaj;
import java.lang.ref.WeakReference;
import com.google.android.gms.signin.internal.zac;

final class zaar extends zac
{
    private final WeakReference<zaak> zagk;
    
    zaar(final zaak zaak) {
        this.zagk = new WeakReference<zaak>(zaak);
    }
    
    @BinderThread
    @Override
    public final void zab(final zaj zaj) {
        final zaak zaak = this.zagk.get();
        if (zaak == null) {
            return;
        }
        zaak.zaft.zaa(new zaas(this, zaak, zaak, zaj));
    }
}
