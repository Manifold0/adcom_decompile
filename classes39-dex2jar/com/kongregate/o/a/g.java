// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.o.a;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.IInterface;

interface g extends IInterface
{
    int a(final int p0, final String p1, final String p2) throws RemoteException;
    
    Bundle a(final int p0, final String p1, final String p2, final Bundle p3) throws RemoteException;
    
    Bundle a(final int p0, final String p1, final String p2, final String p3) throws RemoteException;
    
    Bundle a(final int p0, final String p1, final String p2, final String p3, final String p4) throws RemoteException;
    
    int b(final int p0, final String p1, final String p2) throws RemoteException;
    
    public abstract static class a extends Binder implements g
    {
        public a() {
            this.attachInterface((IInterface)this, "com.android.vending.billing.IInAppBillingService");
        }
        
        static g a(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.android.vending.billing.IInAppBillingService");
            if (queryLocalInterface != null && queryLocalInterface instanceof g) {
                return (g)queryLocalInterface;
            }
            return new g.a.a(binder);
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.android.vending.billing.IInAppBillingService");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.android.vending.billing.IInAppBillingService");
                    n = this.a(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(n);
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.android.vending.billing.IInAppBillingService");
                    n = parcel.readInt();
                    final String string = parcel.readString();
                    final String string2 = parcel.readString();
                    Bundle bundle;
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle = null;
                    }
                    final Bundle a = this.a(n, string, string2, bundle);
                    parcel2.writeNoException();
                    if (a != null) {
                        parcel2.writeInt(1);
                        a.writeToParcel(parcel2, 1);
                    }
                    else {
                        parcel2.writeInt(0);
                    }
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.android.vending.billing.IInAppBillingService");
                    final Bundle a2 = this.a(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    if (a2 != null) {
                        parcel2.writeInt(1);
                        a2.writeToParcel(parcel2, 1);
                    }
                    else {
                        parcel2.writeInt(0);
                    }
                    return true;
                }
                case 4: {
                    parcel.enforceInterface("com.android.vending.billing.IInAppBillingService");
                    final Bundle a3 = this.a(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    if (a3 != null) {
                        parcel2.writeInt(1);
                        a3.writeToParcel(parcel2, 1);
                    }
                    else {
                        parcel2.writeInt(0);
                    }
                    return true;
                }
                case 5: {
                    parcel.enforceInterface("com.android.vending.billing.IInAppBillingService");
                    n = this.b(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(n);
                    return true;
                }
            }
        }
        
        private static class a implements g
        {
            private IBinder a;
            
            a(final IBinder a) {
                this.a = a;
            }
            
            @Override
            public int a(int int1, final String s, final String s2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.android.vending.billing.IInAppBillingService");
                    obtain.writeInt(int1);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    this.a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    int1 = obtain2.readInt();
                    return int1;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public Bundle a(final int n, final String s, final String s2, final Bundle bundle) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    try {
                        obtain.writeInterfaceToken("com.android.vending.billing.IInAppBillingService");
                        obtain.writeInt(n);
                        obtain.writeString(s);
                        obtain.writeString(s2);
                        if (bundle != null) {
                            obtain.writeInt(1);
                            bundle.writeToParcel(obtain, 0);
                        }
                        else {
                            obtain.writeInt(0);
                        }
                        this.a.transact(2, obtain, obtain2, 0);
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            return (Bundle)Bundle.CREATOR.createFromParcel(obtain2);
                        }
                    }
                    finally {
                        obtain2.recycle();
                        obtain.recycle();
                    }
                    return null;
                }
            }
            
            @Override
            public Bundle a(final int n, final String s, final String s2, final String s3) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.android.vending.billing.IInAppBillingService");
                    obtain.writeInt(n);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    obtain.writeString(s3);
                    this.a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    Bundle bundle;
                    if (obtain2.readInt() != 0) {
                        bundle = (Bundle)Bundle.CREATOR.createFromParcel(obtain2);
                    }
                    else {
                        bundle = null;
                    }
                    return bundle;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public Bundle a(final int n, final String s, final String s2, final String s3, final String s4) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.android.vending.billing.IInAppBillingService");
                    obtain.writeInt(n);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    obtain.writeString(s3);
                    obtain.writeString(s4);
                    this.a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    Bundle bundle;
                    if (obtain2.readInt() != 0) {
                        bundle = (Bundle)Bundle.CREATOR.createFromParcel(obtain2);
                    }
                    else {
                        bundle = null;
                    }
                    return bundle;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            public IBinder asBinder() {
                return this.a;
            }
            
            @Override
            public int b(int int1, final String s, final String s2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.android.vending.billing.IInAppBillingService");
                    obtain.writeInt(int1);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    this.a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    int1 = obtain2.readInt();
                    return int1;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
