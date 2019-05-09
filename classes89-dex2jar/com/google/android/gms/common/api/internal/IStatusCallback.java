// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.os.Parcelable;
import com.google.android.gms.internal.base.zaa;
import android.os.Parcelable$Creator;
import com.google.android.gms.internal.base.zac;
import android.os.Parcel;
import android.os.IBinder;
import com.google.android.gms.internal.base.zab;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import android.os.IInterface;

public interface IStatusCallback extends IInterface
{
    void onResult(final Status p0) throws RemoteException;
    
    public abstract static class Stub extends zab implements IStatusCallback
    {
        public Stub() {
            super("com.google.android.gms.common.api.internal.IStatusCallback");
        }
        
        public static IStatusCallback asInterface(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.common.api.internal.IStatusCallback");
            if (queryLocalInterface instanceof IStatusCallback) {
                return (IStatusCallback)queryLocalInterface;
            }
            return new zaa(binder);
        }
        
        @Override
        protected boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            if (n == 1) {
                this.onResult(com.google.android.gms.internal.base.zac.zaa(parcel, (android.os.Parcelable$Creator<Status>)Status.CREATOR));
                return true;
            }
            return false;
        }
        
        public static final class zaa extends com.google.android.gms.internal.base.zaa implements IStatusCallback
        {
            zaa(final IBinder binder) {
                super(binder, "com.google.android.gms.common.api.internal.IStatusCallback");
            }
            
            @Override
            public final void onResult(final Status status) throws RemoteException {
                final Parcel zaa = this.zaa();
                com.google.android.gms.internal.base.zac.zaa(zaa, (Parcelable)status);
                this.zac(1, zaa);
            }
        }
    }
}
