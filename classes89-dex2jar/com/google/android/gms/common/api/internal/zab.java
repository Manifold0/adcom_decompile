// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.support.annotation.NonNull;
import android.os.TransactionTooLargeException;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.api.Status;
import android.os.RemoteException;

public abstract class zab
{
    private final int type;
    
    public zab(final int type) {
        this.type = type;
    }
    
    private static Status zaa(final RemoteException ex) {
        final StringBuilder sb = new StringBuilder();
        if (PlatformVersion.isAtLeastIceCreamSandwichMR1() && ex instanceof TransactionTooLargeException) {
            sb.append("TransactionTooLargeException: ");
        }
        sb.append(ex.getLocalizedMessage());
        return new Status(8, sb.toString());
    }
    
    public abstract void zaa(@NonNull final Status p0);
    
    public abstract void zaa(final GoogleApiManager.zaa<?> p0) throws DeadObjectException;
    
    public abstract void zaa(@NonNull final zaab p0, final boolean p1);
    
    public abstract void zaa(@NonNull final RuntimeException p0);
}
