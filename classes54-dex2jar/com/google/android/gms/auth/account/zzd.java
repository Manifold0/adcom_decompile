// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.account;

import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.internal.auth.zzb;

public abstract class zzd extends zzb implements zzc
{
    public static zzc zzc(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.auth.account.IWorkAccountService");
        if (queryLocalInterface instanceof zzc) {
            return (zzc)queryLocalInterface;
        }
        return new zze(binder);
    }
}
