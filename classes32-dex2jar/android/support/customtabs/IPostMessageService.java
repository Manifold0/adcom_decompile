// 
// Decompiled by Procyon v0.5.34
// 

package android.support.customtabs;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.os.Bundle;
import android.os.IInterface;

public interface IPostMessageService extends IInterface
{
    void onMessageChannelReady(final ICustomTabsCallback p0, final Bundle p1) throws RemoteException;
    
    void onPostMessage(final ICustomTabsCallback p0, final String p1, final Bundle p2) throws RemoteException;
    
    public abstract static class Stub extends Binder implements IPostMessageService
    {
        private static final String DESCRIPTOR = "android.support.customtabs.IPostMessageService";
        static final int TRANSACTION_onMessageChannelReady = 2;
        static final int TRANSACTION_onPostMessage = 3;
        
        public Stub() {
            this.attachInterface((IInterface)this, "android.support.customtabs.IPostMessageService");
        }
        
        public static IPostMessageService asInterface(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("android.support.customtabs.IPostMessageService");
            if (queryLocalInterface != null && queryLocalInterface instanceof IPostMessageService) {
                return (IPostMessageService)queryLocalInterface;
            }
            return new Proxy(binder);
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("android.support.customtabs.IPostMessageService");
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("android.support.customtabs.IPostMessageService");
                    final ICustomTabsCallback interface1 = ICustomTabsCallback.Stub.asInterface(parcel.readStrongBinder());
                    Bundle bundle;
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle = null;
                    }
                    this.onMessageChannelReady(interface1, bundle);
                    parcel2.writeNoException();
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("android.support.customtabs.IPostMessageService");
                    final ICustomTabsCallback interface2 = ICustomTabsCallback.Stub.asInterface(parcel.readStrongBinder());
                    final String string = parcel.readString();
                    Bundle bundle2;
                    if (parcel.readInt() != 0) {
                        bundle2 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle2 = null;
                    }
                    this.onPostMessage(interface2, string, bundle2);
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class Proxy implements IPostMessageService
        {
            private IBinder mRemote;
            
            Proxy(final IBinder mRemote) {
                this.mRemote = mRemote;
            }
            
            public IBinder asBinder() {
                return this.mRemote;
            }
            
            public String getInterfaceDescriptor() {
                return "android.support.customtabs.IPostMessageService";
            }
            
            @Override
            public void onMessageChannelReady(final ICustomTabsCallback customTabsCallback, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.customtabs.IPostMessageService");
                    IBinder binder;
                    if (customTabsCallback != null) {
                        binder = customTabsCallback.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void onPostMessage(final ICustomTabsCallback customTabsCallback, final String s, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.customtabs.IPostMessageService");
                    IBinder binder;
                    if (customTabsCallback != null) {
                        binder = customTabsCallback.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(3, obtain, obtain2, 0);
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
