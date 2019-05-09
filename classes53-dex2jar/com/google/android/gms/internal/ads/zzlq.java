// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.IInterface;
import android.os.RemoteException;
import android.os.Parcel;
import android.os.IBinder;

public final class zzlq extends zzej implements zzlo
{
    zzlq(final IBinder binder) {
        super(binder, "com.google.android.gms.ads.internal.client.IVideoController");
    }
    
    public final float getAspectRatio() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(9, this.obtainAndWriteInterfaceToken());
        final float float1 = transactAndReadException.readFloat();
        transactAndReadException.recycle();
        return float1;
    }
    
    public final int getPlaybackState() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(5, this.obtainAndWriteInterfaceToken());
        final int int1 = transactAndReadException.readInt();
        transactAndReadException.recycle();
        return int1;
    }
    
    public final boolean isClickToExpandEnabled() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(12, this.obtainAndWriteInterfaceToken());
        final boolean zza = zzel.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }
    
    public final boolean isCustomControlsEnabled() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(10, this.obtainAndWriteInterfaceToken());
        final boolean zza = zzel.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }
    
    public final boolean isMuted() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(4, this.obtainAndWriteInterfaceToken());
        final boolean zza = zzel.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }
    
    public final void mute(final boolean b) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, b);
        this.transactAndReadExceptionReturnVoid(3, obtainAndWriteInterfaceToken);
    }
    
    public final void pause() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(2, this.obtainAndWriteInterfaceToken());
    }
    
    public final void play() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(1, this.obtainAndWriteInterfaceToken());
    }
    
    public final void zza(final zzlr zzlr) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzlr);
        this.transactAndReadExceptionReturnVoid(8, obtainAndWriteInterfaceToken);
    }
    
    public final float zzim() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(6, this.obtainAndWriteInterfaceToken());
        final float float1 = transactAndReadException.readFloat();
        transactAndReadException.recycle();
        return float1;
    }
    
    public final float zzin() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(7, this.obtainAndWriteInterfaceToken());
        final float float1 = transactAndReadException.readFloat();
        transactAndReadException.recycle();
        return float1;
    }
    
    public final zzlr zzio() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(11, this.obtainAndWriteInterfaceToken());
        final IBinder strongBinder = transactAndReadException.readStrongBinder();
        zzlr zzlr;
        if (strongBinder == null) {
            zzlr = null;
        }
        else {
            final IInterface queryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IVideoLifecycleCallbacks");
            if (queryLocalInterface instanceof zzlr) {
                zzlr = (zzlr)queryLocalInterface;
            }
            else {
                zzlr = new zzlt(strongBinder);
            }
        }
        transactAndReadException.recycle();
        return zzlr;
    }
}
