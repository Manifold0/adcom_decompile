// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Parcelable;
import android.os.Parcelable$Creator;
import android.os.Parcel;

public abstract class zzaeo extends zzek implements zzaen
{
    public zzaeo() {
        super("com.google.android.gms.ads.internal.request.IAdRequestService");
    }
    
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        final zzaet zzaet = null;
        final zzaet zzaet2 = null;
        final zzaeq zzaeq = null;
        switch (n) {
            default: {
                return false;
            }
            case 1: {
                final zzaej zzb = this.zzb((zzaef)zzel.zza(parcel, (Parcelable$Creator)zzaef.CREATOR));
                parcel2.writeNoException();
                zzel.zzb(parcel2, (Parcelable)zzb);
                break;
            }
            case 2: {
                final zzaef zzaef = (zzaef)zzel.zza(parcel, (Parcelable$Creator)com.google.android.gms.internal.ads.zzaef.CREATOR);
                final IBinder strongBinder = parcel.readStrongBinder();
                zzaeq zzaeq2;
                if (strongBinder == null) {
                    zzaeq2 = zzaeq;
                }
                else {
                    final IInterface queryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.request.IAdResponseListener");
                    if (queryLocalInterface instanceof zzaeq) {
                        zzaeq2 = (zzaeq)queryLocalInterface;
                    }
                    else {
                        zzaeq2 = new zzaes(strongBinder);
                    }
                }
                this.zza(zzaef, zzaeq2);
                parcel2.writeNoException();
                break;
            }
            case 4: {
                final zzaey zzaey = (zzaey)zzel.zza(parcel, (Parcelable$Creator)com.google.android.gms.internal.ads.zzaey.CREATOR);
                final IBinder strongBinder2 = parcel.readStrongBinder();
                zzaet zzaet3;
                if (strongBinder2 == null) {
                    zzaet3 = zzaet;
                }
                else {
                    final IInterface queryLocalInterface2 = strongBinder2.queryLocalInterface("com.google.android.gms.ads.internal.request.INonagonStreamingResponseListener");
                    if (queryLocalInterface2 instanceof zzaet) {
                        zzaet3 = (zzaet)queryLocalInterface2;
                    }
                    else {
                        zzaet3 = new zzaeu(strongBinder2);
                    }
                }
                this.zza(zzaey, zzaet3);
                parcel2.writeNoException();
                break;
            }
            case 5: {
                final zzaey zzaey2 = (zzaey)zzel.zza(parcel, (Parcelable$Creator)zzaey.CREATOR);
                final IBinder strongBinder3 = parcel.readStrongBinder();
                zzaet zzaet4;
                if (strongBinder3 == null) {
                    zzaet4 = zzaet2;
                }
                else {
                    final IInterface queryLocalInterface3 = strongBinder3.queryLocalInterface("com.google.android.gms.ads.internal.request.INonagonStreamingResponseListener");
                    if (queryLocalInterface3 instanceof zzaet) {
                        zzaet4 = (zzaet)queryLocalInterface3;
                    }
                    else {
                        zzaet4 = new zzaeu(strongBinder3);
                    }
                }
                this.zzb(zzaey2, zzaet4);
                parcel2.writeNoException();
                break;
            }
        }
        return true;
    }
}
