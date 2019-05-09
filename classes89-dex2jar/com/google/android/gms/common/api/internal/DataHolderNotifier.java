// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public abstract class DataHolderNotifier<L> implements Notifier<L>
{
    private final DataHolder mDataHolder;
    
    @KeepForSdk
    protected DataHolderNotifier(final DataHolder mDataHolder) {
        this.mDataHolder = mDataHolder;
    }
    
    @KeepForSdk
    @Override
    public final void notifyListener(final L l) {
        this.notifyListener(l, this.mDataHolder);
    }
    
    @KeepForSdk
    protected abstract void notifyListener(final L p0, final DataHolder p1);
    
    @KeepForSdk
    @Override
    public void onNotifyListenerFailed() {
        if (this.mDataHolder != null) {
            this.mDataHolder.close();
        }
    }
}
