// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.snapshot.zze;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.games.multiplayer.realtime.RoomEntity;
import android.os.Parcelable;
import com.google.android.gms.games.PlayerEntity;
import android.content.Intent;
import android.os.IInterface;
import android.os.RemoteException;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.internal.games.zzc;
import android.os.Bundle;
import android.os.IBinder;
import com.google.android.gms.internal.games.zza;

public final class zzz extends zza implements zzy
{
    zzz(final IBinder binder) {
        super(binder, "com.google.android.gms.games.internal.IGamesService");
    }
    
    @Override
    public final Bundle getConnectionHint() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(5004, this.obtainAndWriteInterfaceToken());
        final Bundle bundle = zzc.zza(transactAndReadException, (android.os.Parcelable$Creator<Bundle>)Bundle.CREATOR);
        transactAndReadException.recycle();
        return bundle;
    }
    
    @Override
    public final int zza(final zzu zzu, final byte[] array, final String s, final String s2) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeByteArray(array);
        obtainAndWriteInterfaceToken.writeString(s);
        obtainAndWriteInterfaceToken.writeString(s2);
        final Parcel transactAndReadException = this.transactAndReadException(5033, obtainAndWriteInterfaceToken);
        final int int1 = transactAndReadException.readInt();
        transactAndReadException.recycle();
        return int1;
    }
    
    @Override
    public final Intent zza(final int n, final int n2, final boolean b) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeInt(n);
        obtainAndWriteInterfaceToken.writeInt(n2);
        zzc.zza(obtainAndWriteInterfaceToken, b);
        final Parcel transactAndReadException = this.transactAndReadException(9008, obtainAndWriteInterfaceToken);
        final Intent intent = zzc.zza(transactAndReadException, (android.os.Parcelable$Creator<Intent>)Intent.CREATOR);
        transactAndReadException.recycle();
        return intent;
    }
    
    @Override
    public final Intent zza(final int n, final byte[] array, final int n2, final String s) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeInt(n);
        obtainAndWriteInterfaceToken.writeByteArray(array);
        obtainAndWriteInterfaceToken.writeInt(n2);
        obtainAndWriteInterfaceToken.writeString(s);
        final Parcel transactAndReadException = this.transactAndReadException(10012, obtainAndWriteInterfaceToken);
        final Intent intent = zzc.zza(transactAndReadException, (android.os.Parcelable$Creator<Intent>)Intent.CREATOR);
        transactAndReadException.recycle();
        return intent;
    }
    
    @Override
    public final Intent zza(final PlayerEntity playerEntity) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)playerEntity);
        final Parcel transactAndReadException = this.transactAndReadException(15503, obtainAndWriteInterfaceToken);
        final Intent intent = zzc.zza(transactAndReadException, (android.os.Parcelable$Creator<Intent>)Intent.CREATOR);
        transactAndReadException.recycle();
        return intent;
    }
    
    @Override
    public final Intent zza(final RoomEntity roomEntity, final int n) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)roomEntity);
        obtainAndWriteInterfaceToken.writeInt(n);
        final Parcel transactAndReadException = this.transactAndReadException(9011, obtainAndWriteInterfaceToken);
        final Intent intent = zzc.zza(transactAndReadException, (android.os.Parcelable$Creator<Intent>)Intent.CREATOR);
        transactAndReadException.recycle();
        return intent;
    }
    
    @Override
    public final Intent zza(final String s, final boolean b, final boolean b2, final int n) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(s);
        zzc.zza(obtainAndWriteInterfaceToken, b);
        zzc.zza(obtainAndWriteInterfaceToken, b2);
        obtainAndWriteInterfaceToken.writeInt(n);
        final Parcel transactAndReadException = this.transactAndReadException(12001, obtainAndWriteInterfaceToken);
        final Intent intent = zzc.zza(transactAndReadException, (android.os.Parcelable$Creator<Intent>)Intent.CREATOR);
        transactAndReadException.recycle();
        return intent;
    }
    
    @Override
    public final Intent zza(final int[] array) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeIntArray(array);
        final Parcel transactAndReadException = this.transactAndReadException(12030, obtainAndWriteInterfaceToken);
        final Intent intent = zzc.zza(transactAndReadException, (android.os.Parcelable$Creator<Intent>)Intent.CREATOR);
        transactAndReadException.recycle();
        return intent;
    }
    
    @Override
    public final void zza(final long n) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeLong(n);
        this.transactAndReadExceptionReturnVoid(5001, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final IBinder binder, final Bundle bundle) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeStrongBinder(binder);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)bundle);
        this.transactAndReadExceptionReturnVoid(5005, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final Contents contents) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)contents);
        this.transactAndReadExceptionReturnVoid(12019, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzu zzu) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        this.transactAndReadExceptionReturnVoid(5002, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzu zzu, final int n) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeInt(n);
        this.transactAndReadExceptionReturnVoid(10016, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzu zzu, final int n, final int n2, final int n3) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeInt(n);
        obtainAndWriteInterfaceToken.writeInt(n2);
        obtainAndWriteInterfaceToken.writeInt(n3);
        this.transactAndReadExceptionReturnVoid(10009, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzu zzu, final int n, final int n2, final String[] array, final Bundle bundle) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeInt(n);
        obtainAndWriteInterfaceToken.writeInt(n2);
        obtainAndWriteInterfaceToken.writeStringArray(array);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)bundle);
        this.transactAndReadExceptionReturnVoid(8004, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzu zzu, final int n, final boolean b, final boolean b2) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeInt(n);
        zzc.zza(obtainAndWriteInterfaceToken, b);
        zzc.zza(obtainAndWriteInterfaceToken, b2);
        this.transactAndReadExceptionReturnVoid(5015, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzu zzu, final int n, final int[] array) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeInt(n);
        obtainAndWriteInterfaceToken.writeIntArray(array);
        this.transactAndReadExceptionReturnVoid(10018, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzu zzu, final long n) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeLong(n);
        this.transactAndReadExceptionReturnVoid(5058, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzu zzu, final Bundle bundle, final int n, final int n2) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)bundle);
        obtainAndWriteInterfaceToken.writeInt(n);
        obtainAndWriteInterfaceToken.writeInt(n2);
        this.transactAndReadExceptionReturnVoid(5021, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzu zzu, final IBinder binder, final int n, final String[] array, final Bundle bundle, final boolean b, final long n2) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeStrongBinder(binder);
        obtainAndWriteInterfaceToken.writeInt(n);
        obtainAndWriteInterfaceToken.writeStringArray(array);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)bundle);
        zzc.zza(obtainAndWriteInterfaceToken, false);
        obtainAndWriteInterfaceToken.writeLong(n2);
        this.transactAndReadExceptionReturnVoid(5030, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzu zzu, final IBinder binder, final String s, final boolean b, final long n) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeStrongBinder(binder);
        obtainAndWriteInterfaceToken.writeString(s);
        zzc.zza(obtainAndWriteInterfaceToken, false);
        obtainAndWriteInterfaceToken.writeLong(n);
        this.transactAndReadExceptionReturnVoid(5031, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzu zzu, final String s) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeString(s);
        this.transactAndReadExceptionReturnVoid(5032, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzu zzu, final String s, final int n, final int n2, final int n3, final boolean b) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeString(s);
        obtainAndWriteInterfaceToken.writeInt(n);
        obtainAndWriteInterfaceToken.writeInt(n2);
        obtainAndWriteInterfaceToken.writeInt(n3);
        zzc.zza(obtainAndWriteInterfaceToken, b);
        this.transactAndReadExceptionReturnVoid(5019, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzu zzu, final String s, final int n, final IBinder binder, final Bundle bundle) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeString(s);
        obtainAndWriteInterfaceToken.writeInt(n);
        obtainAndWriteInterfaceToken.writeStrongBinder(binder);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)bundle);
        this.transactAndReadExceptionReturnVoid(5025, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzu zzu, final String s, final int n, final boolean b, final boolean b2) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeString(s);
        obtainAndWriteInterfaceToken.writeInt(n);
        zzc.zza(obtainAndWriteInterfaceToken, b);
        zzc.zza(obtainAndWriteInterfaceToken, b2);
        this.transactAndReadExceptionReturnVoid(9020, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzu zzu, final String s, final long n, final String s2) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeString(s);
        obtainAndWriteInterfaceToken.writeLong(n);
        obtainAndWriteInterfaceToken.writeString(s2);
        this.transactAndReadExceptionReturnVoid(7002, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzu zzu, final String s, final IBinder binder, final Bundle bundle) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeString(s);
        obtainAndWriteInterfaceToken.writeStrongBinder(binder);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)bundle);
        this.transactAndReadExceptionReturnVoid(5023, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzu zzu, final String s, final zze zze, final Contents contents) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeString(s);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zze);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)contents);
        this.transactAndReadExceptionReturnVoid(12007, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzu zzu, final String s, final String s2) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeString(s);
        obtainAndWriteInterfaceToken.writeString(s2);
        this.transactAndReadExceptionReturnVoid(8011, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzu zzu, final String s, final String s2, final int n, final int n2) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeString((String)null);
        obtainAndWriteInterfaceToken.writeString(s2);
        obtainAndWriteInterfaceToken.writeInt(n);
        obtainAndWriteInterfaceToken.writeInt(n2);
        this.transactAndReadExceptionReturnVoid(8001, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzu zzu, final String s, final String s2, final zze zze, final Contents contents) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeString(s);
        obtainAndWriteInterfaceToken.writeString(s2);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zze);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)contents);
        this.transactAndReadExceptionReturnVoid(12033, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzu zzu, final String s, final boolean b) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeString(s);
        zzc.zza(obtainAndWriteInterfaceToken, b);
        this.transactAndReadExceptionReturnVoid(6504, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzu zzu, final String s, final boolean b, final int n) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeString(s);
        zzc.zza(obtainAndWriteInterfaceToken, b);
        obtainAndWriteInterfaceToken.writeInt(n);
        this.transactAndReadExceptionReturnVoid(15001, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzu zzu, final String s, final byte[] array, final String s2, final ParticipantResult[] array2) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeString(s);
        obtainAndWriteInterfaceToken.writeByteArray(array);
        obtainAndWriteInterfaceToken.writeString(s2);
        obtainAndWriteInterfaceToken.writeTypedArray((Parcelable[])array2, 0);
        this.transactAndReadExceptionReturnVoid(8007, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzu zzu, final String s, final byte[] array, final ParticipantResult[] array2) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeString(s);
        obtainAndWriteInterfaceToken.writeByteArray(array);
        obtainAndWriteInterfaceToken.writeTypedArray((Parcelable[])array2, 0);
        this.transactAndReadExceptionReturnVoid(8008, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzu zzu, final boolean b) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        zzc.zza(obtainAndWriteInterfaceToken, b);
        this.transactAndReadExceptionReturnVoid(6001, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzu zzu, final boolean b, final String[] array) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        zzc.zza(obtainAndWriteInterfaceToken, b);
        obtainAndWriteInterfaceToken.writeStringArray(array);
        this.transactAndReadExceptionReturnVoid(12031, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzu zzu, final int[] array, final int n, final boolean b) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeIntArray(array);
        obtainAndWriteInterfaceToken.writeInt(n);
        zzc.zza(obtainAndWriteInterfaceToken, b);
        this.transactAndReadExceptionReturnVoid(12010, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzu zzu, final String[] array) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeStringArray(array);
        this.transactAndReadExceptionReturnVoid(10006, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzu zzu, final String[] array, final boolean b) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeStringArray(array);
        zzc.zza(obtainAndWriteInterfaceToken, b);
        this.transactAndReadExceptionReturnVoid(12029, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzw zzw, final long n) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzw);
        obtainAndWriteInterfaceToken.writeLong(n);
        this.transactAndReadExceptionReturnVoid(15501, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final String s, final int n) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(s);
        obtainAndWriteInterfaceToken.writeInt(n);
        this.transactAndReadExceptionReturnVoid(12017, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final String s, final IBinder binder, final Bundle bundle) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(s);
        obtainAndWriteInterfaceToken.writeStrongBinder(binder);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)bundle);
        this.transactAndReadExceptionReturnVoid(13002, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final String s, final zzu zzu) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(s);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        this.transactAndReadExceptionReturnVoid(20001, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final Intent zzag() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(9010, this.obtainAndWriteInterfaceToken());
        final Intent intent = zzc.zza(transactAndReadException, (android.os.Parcelable$Creator<Intent>)Intent.CREATOR);
        transactAndReadException.recycle();
        return intent;
    }
    
    @Override
    public final Intent zzai() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(9012, this.obtainAndWriteInterfaceToken());
        final Intent intent = zzc.zza(transactAndReadException, (android.os.Parcelable$Creator<Intent>)Intent.CREATOR);
        transactAndReadException.recycle();
        return intent;
    }
    
    @Override
    public final int zzak() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(9019, this.obtainAndWriteInterfaceToken());
        final int int1 = transactAndReadException.readInt();
        transactAndReadException.recycle();
        return int1;
    }
    
    @Override
    public final String zzam() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(5003, this.obtainAndWriteInterfaceToken());
        final String string = transactAndReadException.readString();
        transactAndReadException.recycle();
        return string;
    }
    
    @Override
    public final int zzao() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(8024, this.obtainAndWriteInterfaceToken());
        final int int1 = transactAndReadException.readInt();
        transactAndReadException.recycle();
        return int1;
    }
    
    @Override
    public final Intent zzaq() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(10015, this.obtainAndWriteInterfaceToken());
        final Intent intent = zzc.zza(transactAndReadException, (android.os.Parcelable$Creator<Intent>)Intent.CREATOR);
        transactAndReadException.recycle();
        return intent;
    }
    
    @Override
    public final int zzar() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(10013, this.obtainAndWriteInterfaceToken());
        final int int1 = transactAndReadException.readInt();
        transactAndReadException.recycle();
        return int1;
    }
    
    @Override
    public final int zzas() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(10023, this.obtainAndWriteInterfaceToken());
        final int int1 = transactAndReadException.readInt();
        transactAndReadException.recycle();
        return int1;
    }
    
    @Override
    public final int zzat() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(12035, this.obtainAndWriteInterfaceToken());
        final int int1 = transactAndReadException.readInt();
        transactAndReadException.recycle();
        return int1;
    }
    
    @Override
    public final int zzav() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(12036, this.obtainAndWriteInterfaceToken());
        final int int1 = transactAndReadException.readInt();
        transactAndReadException.recycle();
        return int1;
    }
    
    @Override
    public final boolean zzaz() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(22030, this.obtainAndWriteInterfaceToken());
        final boolean zza = zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }
    
    @Override
    public final int zzb(final byte[] array, final String s, final String[] array2) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeByteArray(array);
        obtainAndWriteInterfaceToken.writeString(s);
        obtainAndWriteInterfaceToken.writeStringArray(array2);
        final Parcel transactAndReadException = this.transactAndReadException(5034, obtainAndWriteInterfaceToken);
        final int int1 = transactAndReadException.readInt();
        transactAndReadException.recycle();
        return int1;
    }
    
    @Override
    public final Intent zzb(final String s, final int n, final int n2) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(s);
        obtainAndWriteInterfaceToken.writeInt(n);
        obtainAndWriteInterfaceToken.writeInt(n2);
        final Parcel transactAndReadException = this.transactAndReadException(18001, obtainAndWriteInterfaceToken);
        final Intent intent = zzc.zza(transactAndReadException, (android.os.Parcelable$Creator<Intent>)Intent.CREATOR);
        transactAndReadException.recycle();
        return intent;
    }
    
    @Override
    public final void zzb(final long n) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeLong(n);
        this.transactAndReadExceptionReturnVoid(5059, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zzb(final zzu zzu) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        this.transactAndReadExceptionReturnVoid(5026, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zzb(final zzu zzu, final int n) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeInt(n);
        this.transactAndReadExceptionReturnVoid(22016, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zzb(final zzu zzu, final long n) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeLong(n);
        this.transactAndReadExceptionReturnVoid(8012, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zzb(final zzu zzu, final String s) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeString(s);
        this.transactAndReadExceptionReturnVoid(8005, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zzb(final zzu zzu, final String s, final int n, final int n2, final int n3, final boolean b) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeString(s);
        obtainAndWriteInterfaceToken.writeInt(n);
        obtainAndWriteInterfaceToken.writeInt(n2);
        obtainAndWriteInterfaceToken.writeInt(n3);
        zzc.zza(obtainAndWriteInterfaceToken, b);
        this.transactAndReadExceptionReturnVoid(5020, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zzb(final zzu zzu, final String s, final int n, final IBinder binder, final Bundle bundle) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeString(s);
        obtainAndWriteInterfaceToken.writeInt(n);
        obtainAndWriteInterfaceToken.writeStrongBinder(binder);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)bundle);
        this.transactAndReadExceptionReturnVoid(7003, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zzb(final zzu zzu, final String s, final IBinder binder, final Bundle bundle) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeString(s);
        obtainAndWriteInterfaceToken.writeStrongBinder(binder);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)bundle);
        this.transactAndReadExceptionReturnVoid(5024, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zzb(final zzu zzu, final String s, final String s2) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeString(s);
        obtainAndWriteInterfaceToken.writeString(s2);
        this.transactAndReadExceptionReturnVoid(12009, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zzb(final zzu zzu, final String s, final boolean b) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeString(s);
        zzc.zza(obtainAndWriteInterfaceToken, b);
        this.transactAndReadExceptionReturnVoid(13006, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zzb(final zzu zzu, final boolean b) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        zzc.zza(obtainAndWriteInterfaceToken, b);
        this.transactAndReadExceptionReturnVoid(6503, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zzb(final zzu zzu, final String[] array) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeStringArray(array);
        this.transactAndReadExceptionReturnVoid(10007, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zzb(final String s, final int n) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(s);
        obtainAndWriteInterfaceToken.writeInt(n);
        this.transactAndReadExceptionReturnVoid(5029, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zzbd() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(5006, this.obtainAndWriteInterfaceToken());
    }
    
    @Override
    public final String zzbf() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(5012, this.obtainAndWriteInterfaceToken());
        final String string = transactAndReadException.readString();
        transactAndReadException.recycle();
        return string;
    }
    
    @Override
    public final DataHolder zzbg() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(5013, this.obtainAndWriteInterfaceToken());
        final DataHolder dataHolder = zzc.zza(transactAndReadException, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR);
        transactAndReadException.recycle();
        return dataHolder;
    }
    
    @Override
    public final DataHolder zzbh() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(5502, this.obtainAndWriteInterfaceToken());
        final DataHolder dataHolder = zzc.zza(transactAndReadException, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR);
        transactAndReadException.recycle();
        return dataHolder;
    }
    
    @Override
    public final Intent zzbi() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(19002, this.obtainAndWriteInterfaceToken());
        final Intent intent = zzc.zza(transactAndReadException, (android.os.Parcelable$Creator<Intent>)Intent.CREATOR);
        transactAndReadException.recycle();
        return intent;
    }
    
    @Override
    public final Intent zzc(final int n, final int n2, final boolean b) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeInt(n);
        obtainAndWriteInterfaceToken.writeInt(n2);
        zzc.zza(obtainAndWriteInterfaceToken, b);
        final Parcel transactAndReadException = this.transactAndReadException(9009, obtainAndWriteInterfaceToken);
        final Intent intent = zzc.zza(transactAndReadException, (android.os.Parcelable$Creator<Intent>)Intent.CREATOR);
        transactAndReadException.recycle();
        return intent;
    }
    
    @Override
    public final void zzc(final long n) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeLong(n);
        this.transactAndReadExceptionReturnVoid(8013, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zzc(final zzu zzu) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        this.transactAndReadExceptionReturnVoid(21007, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zzc(final zzu zzu, final long n) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeLong(n);
        this.transactAndReadExceptionReturnVoid(10001, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zzc(final zzu zzu, final String s) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeString(s);
        this.transactAndReadExceptionReturnVoid(8006, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zzc(final zzu zzu, final boolean b) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        zzc.zza(obtainAndWriteInterfaceToken, b);
        this.transactAndReadExceptionReturnVoid(8027, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final Intent zzd(final String s) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(s);
        final Parcel transactAndReadException = this.transactAndReadException(12034, obtainAndWriteInterfaceToken);
        final Intent intent = zzc.zza(transactAndReadException, (android.os.Parcelable$Creator<Intent>)Intent.CREATOR);
        transactAndReadException.recycle();
        return intent;
    }
    
    @Override
    public final void zzd(final long n) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeLong(n);
        this.transactAndReadExceptionReturnVoid(10002, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zzd(final zzu zzu) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        this.transactAndReadExceptionReturnVoid(22028, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zzd(final zzu zzu, final long n) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeLong(n);
        this.transactAndReadExceptionReturnVoid(12011, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zzd(final zzu zzu, final String s) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeString(s);
        this.transactAndReadExceptionReturnVoid(8009, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zzd(final zzu zzu, final boolean b) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        zzc.zza(obtainAndWriteInterfaceToken, b);
        this.transactAndReadExceptionReturnVoid(12002, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zzd(final String s, final int n) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(s);
        obtainAndWriteInterfaceToken.writeInt(n);
        this.transactAndReadExceptionReturnVoid(5028, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zze(final long n) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeLong(n);
        this.transactAndReadExceptionReturnVoid(12012, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zze(final zzu zzu, final long n) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeLong(n);
        this.transactAndReadExceptionReturnVoid(22026, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zze(final zzu zzu, final String s) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeString(s);
        this.transactAndReadExceptionReturnVoid(8010, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zze(final zzu zzu, final boolean b) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        zzc.zza(obtainAndWriteInterfaceToken, b);
        this.transactAndReadExceptionReturnVoid(12016, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zzf(final long n) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeLong(n);
        this.transactAndReadExceptionReturnVoid(22027, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zzf(final zzu zzu, final String s) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeString(s);
        this.transactAndReadExceptionReturnVoid(8014, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zzf(final zzu zzu, final boolean b) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        zzc.zza(obtainAndWriteInterfaceToken, b);
        this.transactAndReadExceptionReturnVoid(17001, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zzf(final String s) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(s);
        this.transactAndReadExceptionReturnVoid(8002, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zzg(final zzu zzu, final String s) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeString(s);
        this.transactAndReadExceptionReturnVoid(12020, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zzh(final zzu zzu, final String s) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzu);
        obtainAndWriteInterfaceToken.writeString(s);
        this.transactAndReadExceptionReturnVoid(12008, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zzk(final int n) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeInt(n);
        this.transactAndReadExceptionReturnVoid(5036, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final String zzp() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(5007, this.obtainAndWriteInterfaceToken());
        final String string = transactAndReadException.readString();
        transactAndReadException.recycle();
        return string;
    }
    
    @Override
    public final Intent zzv() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(9003, this.obtainAndWriteInterfaceToken());
        final Intent intent = zzc.zza(transactAndReadException, (android.os.Parcelable$Creator<Intent>)Intent.CREATOR);
        transactAndReadException.recycle();
        return intent;
    }
    
    @Override
    public final Intent zzx() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(9005, this.obtainAndWriteInterfaceToken());
        final Intent intent = zzc.zza(transactAndReadException, (android.os.Parcelable$Creator<Intent>)Intent.CREATOR);
        transactAndReadException.recycle();
        return intent;
    }
    
    @Override
    public final Intent zzy() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(9006, this.obtainAndWriteInterfaceToken());
        final Intent intent = zzc.zza(transactAndReadException, (android.os.Parcelable$Creator<Intent>)Intent.CREATOR);
        transactAndReadException.recycle();
        return intent;
    }
    
    @Override
    public final Intent zzz() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(9007, this.obtainAndWriteInterfaceToken());
        final Intent intent = zzc.zza(transactAndReadException, (android.os.Parcelable$Creator<Intent>)Intent.CREATOR);
        transactAndReadException.recycle();
        return intent;
    }
}
