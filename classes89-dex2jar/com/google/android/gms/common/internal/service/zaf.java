// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal.service;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;

final class zaf extends zaa
{
    private final BaseImplementation.ResultHolder<Status> mResultHolder;
    
    public zaf(final BaseImplementation.ResultHolder<Status> mResultHolder) {
        this.mResultHolder = mResultHolder;
    }
    
    @Override
    public final void zaj(final int n) throws RemoteException {
        this.mResultHolder.setResult(new Status(n));
    }
}
