// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import android.os.Parcelable$Creator;
import com.google.android.gms.internal.common.zza;
import android.os.Parcelable;
import com.google.android.gms.internal.common.zzc;
import android.os.Parcel;
import android.os.IBinder;
import com.google.android.gms.internal.common.zzb;
import android.os.RemoteException;
import android.accounts.Account;
import android.os.IInterface;

public interface IAccountAccessor extends IInterface
{
    Account getAccount() throws RemoteException;
    
    public abstract static class Stub extends zzb implements IAccountAccessor
    {
        public Stub() {
            super("com.google.android.gms.common.internal.IAccountAccessor");
        }
        
        public static IAccountAccessor asInterface(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.common.internal.IAccountAccessor");
            if (queryLocalInterface instanceof IAccountAccessor) {
                return (IAccountAccessor)queryLocalInterface;
            }
            return new zza(binder);
        }
        
        @Override
        protected final boolean zza(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            if (n == 2) {
                final Account account = this.getAccount();
                parcel2.writeNoException();
                com.google.android.gms.internal.common.zzc.zzb(parcel2, (Parcelable)account);
                return true;
            }
            return false;
        }
        
        public static final class zza extends com.google.android.gms.internal.common.zza implements IAccountAccessor
        {
            zza(final IBinder binder) {
                super(binder, "com.google.android.gms.common.internal.IAccountAccessor");
            }
            
            @Override
            public final Account getAccount() throws RemoteException {
                final Parcel zza = this.zza(2, this.zza());
                final Account account = com.google.android.gms.internal.common.zzc.zza(zza, (android.os.Parcelable$Creator<Account>)Account.CREATOR);
                zza.recycle();
                return account;
            }
        }
    }
}
