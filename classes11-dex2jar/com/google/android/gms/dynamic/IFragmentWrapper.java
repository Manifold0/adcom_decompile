// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.dynamic;

import com.google.android.gms.internal.common.zza;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.internal.common.zzc;
import android.os.Parcel;
import android.os.IBinder;
import com.google.android.gms.internal.common.zzb;
import android.content.Intent;
import android.os.RemoteException;
import android.os.Bundle;
import android.os.IInterface;

public interface IFragmentWrapper extends IInterface
{
    Bundle getArguments() throws RemoteException;
    
    int getId() throws RemoteException;
    
    boolean getRetainInstance() throws RemoteException;
    
    String getTag() throws RemoteException;
    
    int getTargetRequestCode() throws RemoteException;
    
    boolean getUserVisibleHint() throws RemoteException;
    
    boolean isAdded() throws RemoteException;
    
    boolean isDetached() throws RemoteException;
    
    boolean isHidden() throws RemoteException;
    
    boolean isInLayout() throws RemoteException;
    
    boolean isRemoving() throws RemoteException;
    
    boolean isResumed() throws RemoteException;
    
    boolean isVisible() throws RemoteException;
    
    void setHasOptionsMenu(final boolean p0) throws RemoteException;
    
    void setMenuVisibility(final boolean p0) throws RemoteException;
    
    void setRetainInstance(final boolean p0) throws RemoteException;
    
    void setUserVisibleHint(final boolean p0) throws RemoteException;
    
    void startActivity(final Intent p0) throws RemoteException;
    
    void startActivityForResult(final Intent p0, final int p1) throws RemoteException;
    
    void zza(final IObjectWrapper p0) throws RemoteException;
    
    IObjectWrapper zzae() throws RemoteException;
    
    IFragmentWrapper zzaf() throws RemoteException;
    
    IObjectWrapper zzag() throws RemoteException;
    
    IFragmentWrapper zzah() throws RemoteException;
    
    IObjectWrapper zzai() throws RemoteException;
    
    void zzb(final IObjectWrapper p0) throws RemoteException;
    
    public abstract static class Stub extends zzb implements IFragmentWrapper
    {
        public Stub() {
            super("com.google.android.gms.dynamic.IFragmentWrapper");
        }
        
        public static IFragmentWrapper asInterface(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.dynamic.IFragmentWrapper");
            if (queryLocalInterface instanceof IFragmentWrapper) {
                return (IFragmentWrapper)queryLocalInterface;
            }
            return new zza(binder);
        }
        
        @Override
        protected final boolean zza(int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            switch (n) {
                default: {
                    return false;
                }
                case 2: {
                    final IObjectWrapper zzae = this.zzae();
                    parcel2.writeNoException();
                    com.google.android.gms.internal.common.zzc.zza(parcel2, (IInterface)zzae);
                    break;
                }
                case 3: {
                    final Bundle arguments = this.getArguments();
                    parcel2.writeNoException();
                    com.google.android.gms.internal.common.zzc.zzb(parcel2, (Parcelable)arguments);
                    break;
                }
                case 4: {
                    n = this.getId();
                    parcel2.writeNoException();
                    parcel2.writeInt(n);
                    break;
                }
                case 5: {
                    final IFragmentWrapper zzaf = this.zzaf();
                    parcel2.writeNoException();
                    com.google.android.gms.internal.common.zzc.zza(parcel2, (IInterface)zzaf);
                    break;
                }
                case 6: {
                    final IObjectWrapper zzag = this.zzag();
                    parcel2.writeNoException();
                    com.google.android.gms.internal.common.zzc.zza(parcel2, (IInterface)zzag);
                    break;
                }
                case 7: {
                    final boolean retainInstance = this.getRetainInstance();
                    parcel2.writeNoException();
                    com.google.android.gms.internal.common.zzc.writeBoolean(parcel2, retainInstance);
                    break;
                }
                case 8: {
                    final String tag = this.getTag();
                    parcel2.writeNoException();
                    parcel2.writeString(tag);
                    break;
                }
                case 9: {
                    final IFragmentWrapper zzah = this.zzah();
                    parcel2.writeNoException();
                    com.google.android.gms.internal.common.zzc.zza(parcel2, (IInterface)zzah);
                    break;
                }
                case 10: {
                    n = this.getTargetRequestCode();
                    parcel2.writeNoException();
                    parcel2.writeInt(n);
                    break;
                }
                case 11: {
                    final boolean userVisibleHint = this.getUserVisibleHint();
                    parcel2.writeNoException();
                    com.google.android.gms.internal.common.zzc.writeBoolean(parcel2, userVisibleHint);
                    break;
                }
                case 12: {
                    final IObjectWrapper zzai = this.zzai();
                    parcel2.writeNoException();
                    com.google.android.gms.internal.common.zzc.zza(parcel2, (IInterface)zzai);
                    break;
                }
                case 13: {
                    final boolean added = this.isAdded();
                    parcel2.writeNoException();
                    com.google.android.gms.internal.common.zzc.writeBoolean(parcel2, added);
                    break;
                }
                case 14: {
                    final boolean detached = this.isDetached();
                    parcel2.writeNoException();
                    com.google.android.gms.internal.common.zzc.writeBoolean(parcel2, detached);
                    break;
                }
                case 15: {
                    final boolean hidden = this.isHidden();
                    parcel2.writeNoException();
                    com.google.android.gms.internal.common.zzc.writeBoolean(parcel2, hidden);
                    break;
                }
                case 16: {
                    final boolean inLayout = this.isInLayout();
                    parcel2.writeNoException();
                    com.google.android.gms.internal.common.zzc.writeBoolean(parcel2, inLayout);
                    break;
                }
                case 17: {
                    final boolean removing = this.isRemoving();
                    parcel2.writeNoException();
                    com.google.android.gms.internal.common.zzc.writeBoolean(parcel2, removing);
                    break;
                }
                case 18: {
                    final boolean resumed = this.isResumed();
                    parcel2.writeNoException();
                    com.google.android.gms.internal.common.zzc.writeBoolean(parcel2, resumed);
                    break;
                }
                case 19: {
                    final boolean visible = this.isVisible();
                    parcel2.writeNoException();
                    com.google.android.gms.internal.common.zzc.writeBoolean(parcel2, visible);
                    break;
                }
                case 20: {
                    this.zza(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    break;
                }
                case 21: {
                    this.setHasOptionsMenu(com.google.android.gms.internal.common.zzc.zza(parcel));
                    parcel2.writeNoException();
                    break;
                }
                case 22: {
                    this.setMenuVisibility(com.google.android.gms.internal.common.zzc.zza(parcel));
                    parcel2.writeNoException();
                    break;
                }
                case 23: {
                    this.setRetainInstance(com.google.android.gms.internal.common.zzc.zza(parcel));
                    parcel2.writeNoException();
                    break;
                }
                case 24: {
                    this.setUserVisibleHint(com.google.android.gms.internal.common.zzc.zza(parcel));
                    parcel2.writeNoException();
                    break;
                }
                case 25: {
                    this.startActivity(com.google.android.gms.internal.common.zzc.zza(parcel, (android.os.Parcelable$Creator<Intent>)Intent.CREATOR));
                    parcel2.writeNoException();
                    break;
                }
                case 26: {
                    this.startActivityForResult(com.google.android.gms.internal.common.zzc.zza(parcel, (android.os.Parcelable$Creator<Intent>)Intent.CREATOR), parcel.readInt());
                    parcel2.writeNoException();
                    break;
                }
                case 27: {
                    this.zzb(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    break;
                }
            }
            return true;
        }
        
        public static final class zza extends com.google.android.gms.internal.common.zza implements IFragmentWrapper
        {
            zza(final IBinder binder) {
                super(binder, "com.google.android.gms.dynamic.IFragmentWrapper");
            }
            
            @Override
            public final Bundle getArguments() throws RemoteException {
                final Parcel zza = this.zza(3, this.zza());
                final Bundle bundle = com.google.android.gms.internal.common.zzc.zza(zza, (android.os.Parcelable$Creator<Bundle>)Bundle.CREATOR);
                zza.recycle();
                return bundle;
            }
            
            @Override
            public final int getId() throws RemoteException {
                final Parcel zza = this.zza(4, this.zza());
                final int int1 = zza.readInt();
                zza.recycle();
                return int1;
            }
            
            @Override
            public final boolean getRetainInstance() throws RemoteException {
                final Parcel zza = this.zza(7, this.zza());
                final boolean zza2 = com.google.android.gms.internal.common.zzc.zza(zza);
                zza.recycle();
                return zza2;
            }
            
            @Override
            public final String getTag() throws RemoteException {
                final Parcel zza = this.zza(8, this.zza());
                final String string = zza.readString();
                zza.recycle();
                return string;
            }
            
            @Override
            public final int getTargetRequestCode() throws RemoteException {
                final Parcel zza = this.zza(10, this.zza());
                final int int1 = zza.readInt();
                zza.recycle();
                return int1;
            }
            
            @Override
            public final boolean getUserVisibleHint() throws RemoteException {
                final Parcel zza = this.zza(11, this.zza());
                final boolean zza2 = com.google.android.gms.internal.common.zzc.zza(zza);
                zza.recycle();
                return zza2;
            }
            
            @Override
            public final boolean isAdded() throws RemoteException {
                final Parcel zza = this.zza(13, this.zza());
                final boolean zza2 = com.google.android.gms.internal.common.zzc.zza(zza);
                zza.recycle();
                return zza2;
            }
            
            @Override
            public final boolean isDetached() throws RemoteException {
                final Parcel zza = this.zza(14, this.zza());
                final boolean zza2 = com.google.android.gms.internal.common.zzc.zza(zza);
                zza.recycle();
                return zza2;
            }
            
            @Override
            public final boolean isHidden() throws RemoteException {
                final Parcel zza = this.zza(15, this.zza());
                final boolean zza2 = com.google.android.gms.internal.common.zzc.zza(zza);
                zza.recycle();
                return zza2;
            }
            
            @Override
            public final boolean isInLayout() throws RemoteException {
                final Parcel zza = this.zza(16, this.zza());
                final boolean zza2 = com.google.android.gms.internal.common.zzc.zza(zza);
                zza.recycle();
                return zza2;
            }
            
            @Override
            public final boolean isRemoving() throws RemoteException {
                final Parcel zza = this.zza(17, this.zza());
                final boolean zza2 = com.google.android.gms.internal.common.zzc.zza(zza);
                zza.recycle();
                return zza2;
            }
            
            @Override
            public final boolean isResumed() throws RemoteException {
                final Parcel zza = this.zza(18, this.zza());
                final boolean zza2 = com.google.android.gms.internal.common.zzc.zza(zza);
                zza.recycle();
                return zza2;
            }
            
            @Override
            public final boolean isVisible() throws RemoteException {
                final Parcel zza = this.zza(19, this.zza());
                final boolean zza2 = com.google.android.gms.internal.common.zzc.zza(zza);
                zza.recycle();
                return zza2;
            }
            
            @Override
            public final void setHasOptionsMenu(final boolean b) throws RemoteException {
                final Parcel zza = this.zza();
                com.google.android.gms.internal.common.zzc.writeBoolean(zza, b);
                this.zzb(21, zza);
            }
            
            @Override
            public final void setMenuVisibility(final boolean b) throws RemoteException {
                final Parcel zza = this.zza();
                com.google.android.gms.internal.common.zzc.writeBoolean(zza, b);
                this.zzb(22, zza);
            }
            
            @Override
            public final void setRetainInstance(final boolean b) throws RemoteException {
                final Parcel zza = this.zza();
                com.google.android.gms.internal.common.zzc.writeBoolean(zza, b);
                this.zzb(23, zza);
            }
            
            @Override
            public final void setUserVisibleHint(final boolean b) throws RemoteException {
                final Parcel zza = this.zza();
                com.google.android.gms.internal.common.zzc.writeBoolean(zza, b);
                this.zzb(24, zza);
            }
            
            @Override
            public final void startActivity(final Intent intent) throws RemoteException {
                final Parcel zza = this.zza();
                com.google.android.gms.internal.common.zzc.zza(zza, (Parcelable)intent);
                this.zzb(25, zza);
            }
            
            @Override
            public final void startActivityForResult(final Intent intent, final int n) throws RemoteException {
                final Parcel zza = this.zza();
                com.google.android.gms.internal.common.zzc.zza(zza, (Parcelable)intent);
                zza.writeInt(n);
                this.zzb(26, zza);
            }
            
            @Override
            public final void zza(final IObjectWrapper objectWrapper) throws RemoteException {
                final Parcel zza = this.zza();
                com.google.android.gms.internal.common.zzc.zza(zza, (IInterface)objectWrapper);
                this.zzb(20, zza);
            }
            
            @Override
            public final IObjectWrapper zzae() throws RemoteException {
                final Parcel zza = this.zza(2, this.zza());
                final IObjectWrapper interface1 = IObjectWrapper.Stub.asInterface(zza.readStrongBinder());
                zza.recycle();
                return interface1;
            }
            
            @Override
            public final IFragmentWrapper zzaf() throws RemoteException {
                final Parcel zza = this.zza(5, this.zza());
                final IFragmentWrapper interface1 = Stub.asInterface(zza.readStrongBinder());
                zza.recycle();
                return interface1;
            }
            
            @Override
            public final IObjectWrapper zzag() throws RemoteException {
                final Parcel zza = this.zza(6, this.zza());
                final IObjectWrapper interface1 = IObjectWrapper.Stub.asInterface(zza.readStrongBinder());
                zza.recycle();
                return interface1;
            }
            
            @Override
            public final IFragmentWrapper zzah() throws RemoteException {
                final Parcel zza = this.zza(9, this.zza());
                final IFragmentWrapper interface1 = Stub.asInterface(zza.readStrongBinder());
                zza.recycle();
                return interface1;
            }
            
            @Override
            public final IObjectWrapper zzai() throws RemoteException {
                final Parcel zza = this.zza(12, this.zza());
                final IObjectWrapper interface1 = IObjectWrapper.Stub.asInterface(zza.readStrongBinder());
                zza.recycle();
                return interface1;
            }
            
            @Override
            public final void zzb(final IObjectWrapper objectWrapper) throws RemoteException {
                final Parcel zza = this.zza();
                com.google.android.gms.internal.common.zzc.zza(zza, (IInterface)objectWrapper);
                this.zzb(27, zza);
            }
        }
    }
}
