// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import android.os.Parcelable;
import com.google.android.gms.internal.base.zaa;
import com.google.android.gms.internal.base.zac;
import android.os.Parcel;
import android.os.IBinder;
import com.google.android.gms.internal.base.zab;
import android.os.RemoteException;
import android.os.IInterface;

public interface IResolveAccountCallbacks extends IInterface
{
    void onAccountResolutionComplete(final ResolveAccountResponse p0) throws RemoteException;
    
    public abstract static class Stub extends zab implements IResolveAccountCallbacks
    {
        public Stub() {
            super("com.google.android.gms.common.internal.IResolveAccountCallbacks");
        }
        
        public static IResolveAccountCallbacks asInterface(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.common.internal.IResolveAccountCallbacks");
            if (queryLocalInterface instanceof IResolveAccountCallbacks) {
                return (IResolveAccountCallbacks)queryLocalInterface;
            }
            return new Proxy(binder);
        }
        
        @Override
        protected boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            if (n == 2) {
                this.onAccountResolutionComplete(com.google.android.gms.internal.base.zac.zaa(parcel, ResolveAccountResponse.CREATOR));
                parcel2.writeNoException();
                return true;
            }
            return false;
        }
        
        public static class Proxy extends zaa implements IResolveAccountCallbacks
        {
            Proxy(final IBinder binder) {
                super(binder, "com.google.android.gms.common.internal.IResolveAccountCallbacks");
            }
            
            @Override
            public void onAccountResolutionComplete(final ResolveAccountResponse resolveAccountResponse) throws RemoteException {
                final Parcel zaa = this.zaa();
                com.google.android.gms.internal.base.zac.zaa(zaa, (Parcelable)resolveAccountResponse);
                this.zab(2, zaa);
            }
        }
    }
}
