// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper$Stub;
import android.content.Intent;
import android.os.Parcelable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;

public abstract class zzaaq extends zzek implements zzaap
{
    public zzaaq() {
        super("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
    }
    
    public static zzaap zzu(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
        if (queryLocalInterface instanceof zzaap) {
            return (zzaap)queryLocalInterface;
        }
        return new zzaar(binder);
    }
    
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return false;
            }
            case 1: {
                this.onCreate((Bundle)zzel.zza(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                break;
            }
            case 2: {
                this.onRestart();
                parcel2.writeNoException();
                break;
            }
            case 3: {
                this.onStart();
                parcel2.writeNoException();
                break;
            }
            case 4: {
                this.onResume();
                parcel2.writeNoException();
                break;
            }
            case 5: {
                this.onPause();
                parcel2.writeNoException();
                break;
            }
            case 6: {
                final Bundle bundle = (Bundle)zzel.zza(parcel, Bundle.CREATOR);
                this.onSaveInstanceState(bundle);
                parcel2.writeNoException();
                zzel.zzb(parcel2, (Parcelable)bundle);
                break;
            }
            case 7: {
                this.onStop();
                parcel2.writeNoException();
                break;
            }
            case 8: {
                this.onDestroy();
                parcel2.writeNoException();
                break;
            }
            case 9: {
                this.zzax();
                parcel2.writeNoException();
                break;
            }
            case 10: {
                this.onBackPressed();
                parcel2.writeNoException();
                break;
            }
            case 11: {
                final boolean zznj = this.zznj();
                parcel2.writeNoException();
                zzel.zza(parcel2, zznj);
                break;
            }
            case 12: {
                this.onActivityResult(parcel.readInt(), parcel.readInt(), (Intent)zzel.zza(parcel, Intent.CREATOR));
                parcel2.writeNoException();
                break;
            }
            case 13: {
                this.zzo(IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            }
        }
        return true;
    }
}
