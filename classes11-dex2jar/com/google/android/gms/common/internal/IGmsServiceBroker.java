// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import com.google.android.gms.common.annotation.KeepForSdk;
import android.os.RemoteException;
import android.os.IInterface;

public interface IGmsServiceBroker extends IInterface
{
    @KeepForSdk
    void getService(final IGmsCallbacks p0, final GetServiceRequest p1) throws RemoteException;
    
    public abstract static class Stub extends Binder implements IGmsServiceBroker
    {
        public Stub() {
            this.attachInterface((IInterface)this, "com.google.android.gms.common.internal.IGmsServiceBroker");
        }
        
        @KeepForSdk
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            if (n > 16777215) {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
            final IBinder strongBinder = parcel.readStrongBinder();
            IGmsCallbacks gmsCallbacks;
            if (strongBinder == null) {
                gmsCallbacks = null;
            }
            else {
                final IInterface queryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsCallbacks");
                if (queryLocalInterface instanceof IGmsCallbacks) {
                    gmsCallbacks = (IGmsCallbacks)queryLocalInterface;
                }
                else {
                    gmsCallbacks = new zzl(strongBinder);
                }
            }
            if (n == 46) {
                GetServiceRequest getServiceRequest;
                if (parcel.readInt() != 0) {
                    getServiceRequest = (GetServiceRequest)GetServiceRequest.CREATOR.createFromParcel(parcel);
                }
                else {
                    getServiceRequest = null;
                }
                this.getService(gmsCallbacks, getServiceRequest);
                parcel2.writeNoException();
                return true;
            }
            if (n == 47) {
                if (parcel.readInt() != 0) {
                    zzr.CREATOR.createFromParcel(parcel);
                }
                throw new UnsupportedOperationException();
            }
            parcel.readInt();
            if (n != 4) {
                parcel.readString();
            }
            switch (n) {
                case 19: {
                    parcel.readStrongBinder();
                    if (parcel.readInt() != 0) {
                        Bundle.CREATOR.createFromParcel(parcel);
                        break;
                    }
                    break;
                }
                case 1: {
                    parcel.readString();
                    parcel.createStringArray();
                    parcel.readString();
                    if (parcel.readInt() != 0) {
                        Bundle.CREATOR.createFromParcel(parcel);
                        break;
                    }
                    break;
                }
                case 9: {
                    parcel.readString();
                    parcel.createStringArray();
                    parcel.readString();
                    parcel.readStrongBinder();
                    parcel.readString();
                    if (parcel.readInt() != 0) {
                        Bundle.CREATOR.createFromParcel(parcel);
                        break;
                    }
                    break;
                }
                case 20:
                case 30: {
                    parcel.createStringArray();
                    parcel.readString();
                    if (parcel.readInt() != 0) {
                        Bundle.CREATOR.createFromParcel(parcel);
                        break;
                    }
                    break;
                }
                case 10: {
                    parcel.readString();
                    parcel.createStringArray();
                    break;
                }
                case 34: {
                    parcel.readString();
                    break;
                }
                case 2:
                case 5:
                case 6:
                case 7:
                case 8:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                case 23:
                case 25:
                case 27:
                case 37:
                case 38:
                case 41:
                case 43: {
                    if (parcel.readInt() != 0) {
                        Bundle.CREATOR.createFromParcel(parcel);
                        break;
                    }
                    break;
                }
            }
            throw new UnsupportedOperationException();
        }
        
        private static final class zza implements IGmsServiceBroker
        {
            private final IBinder zza;
            
            zza(final IBinder zza) {
                this.zza = zza;
            }
            
            public final IBinder asBinder() {
                return this.zza;
            }
            
            @Override
            public final void getService(final IGmsCallbacks gmsCallbacks, final GetServiceRequest getServiceRequest) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (gmsCallbacks != null) {
                        binder = gmsCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    if (getServiceRequest != null) {
                        obtain.writeInt(1);
                        getServiceRequest.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.zza.transact(46, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
