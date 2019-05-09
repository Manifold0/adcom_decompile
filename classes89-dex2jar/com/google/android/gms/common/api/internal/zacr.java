// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import java.util.NoSuchElementException;
import android.os.IBinder;
import com.google.android.gms.common.api.zac;
import java.lang.ref.WeakReference;
import android.os.IBinder$DeathRecipient;

final class zacr implements IBinder$DeathRecipient, zacs
{
    private final WeakReference<BasePendingResult<?>> zalc;
    private final WeakReference<zac> zald;
    private final WeakReference<IBinder> zale;
    
    private zacr(final BasePendingResult<?> basePendingResult, final zac zac, final IBinder binder) {
        this.zald = new WeakReference<zac>(zac);
        this.zalc = new WeakReference<BasePendingResult<?>>(basePendingResult);
        this.zale = new WeakReference<IBinder>(binder);
    }
    
    private final void zaby() {
        final BasePendingResult basePendingResult = this.zalc.get();
        final zac zac = this.zald.get();
        if (zac != null && basePendingResult != null) {
            zac.remove(basePendingResult.zam());
        }
        final IBinder binder = this.zale.get();
        if (binder == null) {
            return;
        }
        try {
            binder.unlinkToDeath((IBinder$DeathRecipient)this, 0);
        }
        catch (NoSuchElementException ex) {}
    }
    
    public final void binderDied() {
        this.zaby();
    }
    
    public final void zac(final BasePendingResult<?> basePendingResult) {
        this.zaby();
    }
}
