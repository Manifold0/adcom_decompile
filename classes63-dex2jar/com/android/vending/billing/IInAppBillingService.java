// 
// Decompiled by Procyon v0.5.34
// 

package com.android.vending.billing;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import java.util.List;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.IInterface;

public interface IInAppBillingService extends IInterface
{
    int consumePurchase(final int p0, final String p1, final String p2) throws RemoteException;
    
    int consumePurchaseExtraParams(final int p0, final String p1, final String p2, final Bundle p3) throws RemoteException;
    
    Bundle getBuyIntent(final int p0, final String p1, final String p2, final String p3, final String p4) throws RemoteException;
    
    Bundle getBuyIntentExtraParams(final int p0, final String p1, final String p2, final String p3, final String p4, final Bundle p5) throws RemoteException;
    
    Bundle getBuyIntentToReplaceSkus(final int p0, final String p1, final List<String> p2, final String p3, final String p4, final String p5) throws RemoteException;
    
    Bundle getPurchaseHistory(final int p0, final String p1, final String p2, final String p3, final Bundle p4) throws RemoteException;
    
    Bundle getPurchases(final int p0, final String p1, final String p2, final String p3) throws RemoteException;
    
    Bundle getPurchasesExtraParams(final int p0, final String p1, final String p2, final String p3, final Bundle p4) throws RemoteException;
    
    Bundle getSkuDetails(final int p0, final String p1, final String p2, final Bundle p3) throws RemoteException;
    
    int isBillingSupported(final int p0, final String p1, final String p2) throws RemoteException;
    
    int isBillingSupportedExtraParams(final int p0, final String p1, final String p2, final Bundle p3) throws RemoteException;
    
    int isPromoEligible(final int p0, final String p1, final String p2) throws RemoteException;
    
    public abstract static class Stub extends Binder implements IInAppBillingService
    {
        private static final String DESCRIPTOR = "com.android.vending.billing.IInAppBillingService";
        static final int TRANSACTION_consumePurchase = 5;
        static final int TRANSACTION_consumePurchaseExtraParams = 12;
        static final int TRANSACTION_getBuyIntent = 3;
        static final int TRANSACTION_getBuyIntentExtraParams = 8;
        static final int TRANSACTION_getBuyIntentToReplaceSkus = 7;
        static final int TRANSACTION_getPurchaseHistory = 9;
        static final int TRANSACTION_getPurchases = 4;
        static final int TRANSACTION_getPurchasesExtraParams = 11;
        static final int TRANSACTION_getSkuDetails = 2;
        static final int TRANSACTION_isBillingSupported = 1;
        static final int TRANSACTION_isBillingSupportedExtraParams = 10;
        static final int TRANSACTION_isPromoEligible = 6;
        
        public Stub() {
            this.attachInterface((IInterface)this, "com.android.vending.billing.IInAppBillingService");
        }
        
        public static IInAppBillingService asInterface(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.android.vending.billing.IInAppBillingService");
            if (queryLocalInterface != null && queryLocalInterface instanceof IInAppBillingService) {
                return (IInAppBillingService)queryLocalInterface;
            }
            return new Proxy(binder);
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
                    n = this.isBillingSupported(parcel.readInt(), parcel.readString(), parcel.readString());
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
                    final Bundle skuDetails = this.getSkuDetails(n, string, string2, bundle);
                    parcel2.writeNoException();
                    if (skuDetails != null) {
                        parcel2.writeInt(1);
                        skuDetails.writeToParcel(parcel2, 1);
                    }
                    else {
                        parcel2.writeInt(0);
                    }
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.android.vending.billing.IInAppBillingService");
                    final Bundle buyIntent = this.getBuyIntent(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    if (buyIntent != null) {
                        parcel2.writeInt(1);
                        buyIntent.writeToParcel(parcel2, 1);
                    }
                    else {
                        parcel2.writeInt(0);
                    }
                    return true;
                }
                case 4: {
                    parcel.enforceInterface("com.android.vending.billing.IInAppBillingService");
                    final Bundle purchases = this.getPurchases(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    if (purchases != null) {
                        parcel2.writeInt(1);
                        purchases.writeToParcel(parcel2, 1);
                    }
                    else {
                        parcel2.writeInt(0);
                    }
                    return true;
                }
                case 5: {
                    parcel.enforceInterface("com.android.vending.billing.IInAppBillingService");
                    n = this.consumePurchase(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(n);
                    return true;
                }
                case 6: {
                    parcel.enforceInterface("com.android.vending.billing.IInAppBillingService");
                    n = this.isPromoEligible(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(n);
                    return true;
                }
                case 7: {
                    parcel.enforceInterface("com.android.vending.billing.IInAppBillingService");
                    final Bundle buyIntentToReplaceSkus = this.getBuyIntentToReplaceSkus(parcel.readInt(), parcel.readString(), parcel.createStringArrayList(), parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    if (buyIntentToReplaceSkus != null) {
                        parcel2.writeInt(1);
                        buyIntentToReplaceSkus.writeToParcel(parcel2, 1);
                    }
                    else {
                        parcel2.writeInt(0);
                    }
                    return true;
                }
                case 8: {
                    parcel.enforceInterface("com.android.vending.billing.IInAppBillingService");
                    n = parcel.readInt();
                    final String string3 = parcel.readString();
                    final String string4 = parcel.readString();
                    final String string5 = parcel.readString();
                    final String string6 = parcel.readString();
                    Bundle bundle2;
                    if (parcel.readInt() != 0) {
                        bundle2 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle2 = null;
                    }
                    final Bundle buyIntentExtraParams = this.getBuyIntentExtraParams(n, string3, string4, string5, string6, bundle2);
                    parcel2.writeNoException();
                    if (buyIntentExtraParams != null) {
                        parcel2.writeInt(1);
                        buyIntentExtraParams.writeToParcel(parcel2, 1);
                    }
                    else {
                        parcel2.writeInt(0);
                    }
                    return true;
                }
                case 9: {
                    parcel.enforceInterface("com.android.vending.billing.IInAppBillingService");
                    n = parcel.readInt();
                    final String string7 = parcel.readString();
                    final String string8 = parcel.readString();
                    final String string9 = parcel.readString();
                    Bundle bundle3;
                    if (parcel.readInt() != 0) {
                        bundle3 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle3 = null;
                    }
                    final Bundle purchaseHistory = this.getPurchaseHistory(n, string7, string8, string9, bundle3);
                    parcel2.writeNoException();
                    if (purchaseHistory != null) {
                        parcel2.writeInt(1);
                        purchaseHistory.writeToParcel(parcel2, 1);
                    }
                    else {
                        parcel2.writeInt(0);
                    }
                    return true;
                }
                case 10: {
                    parcel.enforceInterface("com.android.vending.billing.IInAppBillingService");
                    n = parcel.readInt();
                    final String string10 = parcel.readString();
                    final String string11 = parcel.readString();
                    Bundle bundle4;
                    if (parcel.readInt() != 0) {
                        bundle4 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle4 = null;
                    }
                    n = this.isBillingSupportedExtraParams(n, string10, string11, bundle4);
                    parcel2.writeNoException();
                    parcel2.writeInt(n);
                    return true;
                }
                case 11: {
                    parcel.enforceInterface("com.android.vending.billing.IInAppBillingService");
                    n = parcel.readInt();
                    final String string12 = parcel.readString();
                    final String string13 = parcel.readString();
                    final String string14 = parcel.readString();
                    Bundle bundle5;
                    if (parcel.readInt() != 0) {
                        bundle5 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle5 = null;
                    }
                    final Bundle purchasesExtraParams = this.getPurchasesExtraParams(n, string12, string13, string14, bundle5);
                    parcel2.writeNoException();
                    if (purchasesExtraParams != null) {
                        parcel2.writeInt(1);
                        purchasesExtraParams.writeToParcel(parcel2, 1);
                    }
                    else {
                        parcel2.writeInt(0);
                    }
                    return true;
                }
                case 12: {
                    parcel.enforceInterface("com.android.vending.billing.IInAppBillingService");
                    n = parcel.readInt();
                    final String string15 = parcel.readString();
                    final String string16 = parcel.readString();
                    Bundle bundle6;
                    if (parcel.readInt() != 0) {
                        bundle6 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle6 = null;
                    }
                    n = this.consumePurchaseExtraParams(n, string15, string16, bundle6);
                    parcel2.writeNoException();
                    parcel2.writeInt(n);
                    return true;
                }
            }
        }
        
        private static class Proxy implements IInAppBillingService
        {
            private IBinder mRemote;
            
            Proxy(final IBinder mRemote) {
                this.mRemote = mRemote;
            }
            
            public IBinder asBinder() {
                return this.mRemote;
            }
            
            @Override
            public int consumePurchase(int int1, final String s, final String s2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.android.vending.billing.IInAppBillingService");
                    obtain.writeInt(int1);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    this.mRemote.transact(5, obtain, obtain2, 0);
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
            public int consumePurchaseExtraParams(int int1, final String s, final String s2, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.android.vending.billing.IInAppBillingService");
                    obtain.writeInt(int1);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(12, obtain, obtain2, 0);
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
            public Bundle getBuyIntent(final int n, final String s, final String s2, final String s3, final String s4) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.android.vending.billing.IInAppBillingService");
                    obtain.writeInt(n);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    obtain.writeString(s3);
                    obtain.writeString(s4);
                    this.mRemote.transact(3, obtain, obtain2, 0);
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
            public Bundle getBuyIntentExtraParams(final int n, final String s, final String s2, final String s3, final String s4, final Bundle bundle) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    try {
                        obtain.writeInterfaceToken("com.android.vending.billing.IInAppBillingService");
                        obtain.writeInt(n);
                        obtain.writeString(s);
                        obtain.writeString(s2);
                        obtain.writeString(s3);
                        obtain.writeString(s4);
                        if (bundle != null) {
                            obtain.writeInt(1);
                            bundle.writeToParcel(obtain, 0);
                        }
                        else {
                            obtain.writeInt(0);
                        }
                        this.mRemote.transact(8, obtain, obtain2, 0);
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
            public Bundle getBuyIntentToReplaceSkus(final int n, final String s, final List<String> list, final String s2, final String s3, final String s4) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.android.vending.billing.IInAppBillingService");
                    obtain.writeInt(n);
                    obtain.writeString(s);
                    obtain.writeStringList((List)list);
                    obtain.writeString(s2);
                    obtain.writeString(s3);
                    obtain.writeString(s4);
                    this.mRemote.transact(7, obtain, obtain2, 0);
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
            
            public String getInterfaceDescriptor() {
                return "com.android.vending.billing.IInAppBillingService";
            }
            
            @Override
            public Bundle getPurchaseHistory(final int n, final String s, final String s2, final String s3, final Bundle bundle) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    try {
                        obtain.writeInterfaceToken("com.android.vending.billing.IInAppBillingService");
                        obtain.writeInt(n);
                        obtain.writeString(s);
                        obtain.writeString(s2);
                        obtain.writeString(s3);
                        if (bundle != null) {
                            obtain.writeInt(1);
                            bundle.writeToParcel(obtain, 0);
                        }
                        else {
                            obtain.writeInt(0);
                        }
                        this.mRemote.transact(9, obtain, obtain2, 0);
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
            public Bundle getPurchases(final int n, final String s, final String s2, final String s3) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.android.vending.billing.IInAppBillingService");
                    obtain.writeInt(n);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    obtain.writeString(s3);
                    this.mRemote.transact(4, obtain, obtain2, 0);
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
            public Bundle getPurchasesExtraParams(final int n, final String s, final String s2, final String s3, final Bundle bundle) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    try {
                        obtain.writeInterfaceToken("com.android.vending.billing.IInAppBillingService");
                        obtain.writeInt(n);
                        obtain.writeString(s);
                        obtain.writeString(s2);
                        obtain.writeString(s3);
                        if (bundle != null) {
                            obtain.writeInt(1);
                            bundle.writeToParcel(obtain, 0);
                        }
                        else {
                            obtain.writeInt(0);
                        }
                        this.mRemote.transact(11, obtain, obtain2, 0);
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
            public Bundle getSkuDetails(final int n, final String s, final String s2, final Bundle bundle) throws RemoteException {
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
                        this.mRemote.transact(2, obtain, obtain2, 0);
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
            public int isBillingSupported(int int1, final String s, final String s2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.android.vending.billing.IInAppBillingService");
                    obtain.writeInt(int1);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    this.mRemote.transact(1, obtain, obtain2, 0);
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
            public int isBillingSupportedExtraParams(int int1, final String s, final String s2, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.android.vending.billing.IInAppBillingService");
                    obtain.writeInt(int1);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(10, obtain, obtain2, 0);
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
            public int isPromoEligible(int int1, final String s, final String s2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.android.vending.billing.IInAppBillingService");
                    obtain.writeInt(int1);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    this.mRemote.transact(6, obtain, obtain2, 0);
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
