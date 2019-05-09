package com.google.android.gms.games.internal;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.games.GamesActivityResultCodes;
import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.multiplayer.realtime.RoomEntity;
import com.google.android.gms.games.snapshot.zze;
import com.google.android.gms.internal.games.zza;
import com.google.android.gms.internal.games.zzc;
import com.google.android.gms.nearby.connection.ConnectionsStatusCodes;

public final class zzz extends zza implements zzy {
    zzz(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.games.internal.IGamesService");
    }

    public final Bundle getConnectionHint() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(5004, obtainAndWriteInterfaceToken());
        Bundle bundle = (Bundle) zzc.zza(transactAndReadException, Bundle.CREATOR);
        transactAndReadException.recycle();
        return bundle;
    }

    public final int zza(zzu zzu, byte[] bArr, String str, String str2) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeByteArray(bArr);
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeString(str2);
        obtainAndWriteInterfaceToken = transactAndReadException(5033, obtainAndWriteInterfaceToken);
        int readInt = obtainAndWriteInterfaceToken.readInt();
        obtainAndWriteInterfaceToken.recycle();
        return readInt;
    }

    public final Intent zza(int i, int i2, boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeInt(i);
        obtainAndWriteInterfaceToken.writeInt(i2);
        zzc.zza(obtainAndWriteInterfaceToken, z);
        Parcel transactAndReadException = transactAndReadException(9008, obtainAndWriteInterfaceToken);
        Intent intent = (Intent) zzc.zza(transactAndReadException, Intent.CREATOR);
        transactAndReadException.recycle();
        return intent;
    }

    public final Intent zza(int i, byte[] bArr, int i2, String str) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeInt(i);
        obtainAndWriteInterfaceToken.writeByteArray(bArr);
        obtainAndWriteInterfaceToken.writeInt(i2);
        obtainAndWriteInterfaceToken.writeString(str);
        Parcel transactAndReadException = transactAndReadException(10012, obtainAndWriteInterfaceToken);
        Intent intent = (Intent) zzc.zza(transactAndReadException, Intent.CREATOR);
        transactAndReadException.recycle();
        return intent;
    }

    public final Intent zza(PlayerEntity playerEntity) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) playerEntity);
        Parcel transactAndReadException = transactAndReadException(15503, obtainAndWriteInterfaceToken);
        Intent intent = (Intent) zzc.zza(transactAndReadException, Intent.CREATOR);
        transactAndReadException.recycle();
        return intent;
    }

    public final Intent zza(RoomEntity roomEntity, int i) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) roomEntity);
        obtainAndWriteInterfaceToken.writeInt(i);
        Parcel transactAndReadException = transactAndReadException(9011, obtainAndWriteInterfaceToken);
        Intent intent = (Intent) zzc.zza(transactAndReadException, Intent.CREATOR);
        transactAndReadException.recycle();
        return intent;
    }

    public final Intent zza(String str, boolean z, boolean z2, int i) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        zzc.zza(obtainAndWriteInterfaceToken, z);
        zzc.zza(obtainAndWriteInterfaceToken, z2);
        obtainAndWriteInterfaceToken.writeInt(i);
        Parcel transactAndReadException = transactAndReadException(12001, obtainAndWriteInterfaceToken);
        Intent intent = (Intent) zzc.zza(transactAndReadException, Intent.CREATOR);
        transactAndReadException.recycle();
        return intent;
    }

    public final Intent zza(int[] iArr) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeIntArray(iArr);
        Parcel transactAndReadException = transactAndReadException(12030, obtainAndWriteInterfaceToken);
        Intent intent = (Intent) zzc.zza(transactAndReadException, Intent.CREATOR);
        transactAndReadException.recycle();
        return intent;
    }

    public final void zza(long j) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeLong(j);
        transactAndReadExceptionReturnVoid(5001, obtainAndWriteInterfaceToken);
    }

    public final void zza(IBinder iBinder, Bundle bundle) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeStrongBinder(iBinder);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) bundle);
        transactAndReadExceptionReturnVoid(5005, obtainAndWriteInterfaceToken);
    }

    public final void zza(Contents contents) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) contents);
        transactAndReadExceptionReturnVoid(12019, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzu zzu) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        transactAndReadExceptionReturnVoid(5002, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzu zzu, int i) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeInt(i);
        transactAndReadExceptionReturnVoid(10016, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzu zzu, int i, int i2, int i3) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeInt(i);
        obtainAndWriteInterfaceToken.writeInt(i2);
        obtainAndWriteInterfaceToken.writeInt(i3);
        transactAndReadExceptionReturnVoid(10009, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzu zzu, int i, int i2, String[] strArr, Bundle bundle) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeInt(i);
        obtainAndWriteInterfaceToken.writeInt(i2);
        obtainAndWriteInterfaceToken.writeStringArray(strArr);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) bundle);
        transactAndReadExceptionReturnVoid(ConnectionsStatusCodes.STATUS_CONNECTION_REJECTED, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzu zzu, int i, boolean z, boolean z2) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeInt(i);
        zzc.zza(obtainAndWriteInterfaceToken, z);
        zzc.zza(obtainAndWriteInterfaceToken, z2);
        transactAndReadExceptionReturnVoid(5015, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzu zzu, int i, int[] iArr) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeInt(i);
        obtainAndWriteInterfaceToken.writeIntArray(iArr);
        transactAndReadExceptionReturnVoid(10018, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzu zzu, long j) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeLong(j);
        transactAndReadExceptionReturnVoid(5058, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzu zzu, Bundle bundle, int i, int i2) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) bundle);
        obtainAndWriteInterfaceToken.writeInt(i);
        obtainAndWriteInterfaceToken.writeInt(i2);
        transactAndReadExceptionReturnVoid(5021, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzu zzu, IBinder iBinder, int i, String[] strArr, Bundle bundle, boolean z, long j) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeStrongBinder(iBinder);
        obtainAndWriteInterfaceToken.writeInt(i);
        obtainAndWriteInterfaceToken.writeStringArray(strArr);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) bundle);
        zzc.zza(obtainAndWriteInterfaceToken, false);
        obtainAndWriteInterfaceToken.writeLong(j);
        transactAndReadExceptionReturnVoid(5030, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzu zzu, IBinder iBinder, String str, boolean z, long j) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeStrongBinder(iBinder);
        obtainAndWriteInterfaceToken.writeString(str);
        zzc.zza(obtainAndWriteInterfaceToken, false);
        obtainAndWriteInterfaceToken.writeLong(j);
        transactAndReadExceptionReturnVoid(5031, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzu zzu, String str) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeString(str);
        transactAndReadExceptionReturnVoid(5032, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzu zzu, String str, int i, int i2, int i3, boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeInt(i);
        obtainAndWriteInterfaceToken.writeInt(i2);
        obtainAndWriteInterfaceToken.writeInt(i3);
        zzc.zza(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(5019, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzu zzu, String str, int i, IBinder iBinder, Bundle bundle) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeInt(i);
        obtainAndWriteInterfaceToken.writeStrongBinder(iBinder);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) bundle);
        transactAndReadExceptionReturnVoid(5025, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzu zzu, String str, int i, boolean z, boolean z2) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeInt(i);
        zzc.zza(obtainAndWriteInterfaceToken, z);
        zzc.zza(obtainAndWriteInterfaceToken, z2);
        transactAndReadExceptionReturnVoid(9020, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzu zzu, String str, long j, String str2) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeLong(j);
        obtainAndWriteInterfaceToken.writeString(str2);
        transactAndReadExceptionReturnVoid(7002, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzu zzu, String str, IBinder iBinder, Bundle bundle) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeStrongBinder(iBinder);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) bundle);
        transactAndReadExceptionReturnVoid(5023, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzu zzu, String str, zze zze, Contents contents) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeString(str);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zze);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) contents);
        transactAndReadExceptionReturnVoid(12007, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzu zzu, String str, String str2) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeString(str2);
        transactAndReadExceptionReturnVoid(ConnectionsStatusCodes.STATUS_ENDPOINT_UNKNOWN, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzu zzu, String str, String str2, int i, int i2) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeString(null);
        obtainAndWriteInterfaceToken.writeString(str2);
        obtainAndWriteInterfaceToken.writeInt(i);
        obtainAndWriteInterfaceToken.writeInt(i2);
        transactAndReadExceptionReturnVoid(8001, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzu zzu, String str, String str2, zze zze, Contents contents) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeString(str2);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zze);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) contents);
        transactAndReadExceptionReturnVoid(12033, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzu zzu, String str, boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeString(str);
        zzc.zza(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(GamesStatusCodes.STATUS_MATCH_ERROR_INVALID_MATCH_RESULTS, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzu zzu, String str, boolean z, int i) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeString(str);
        zzc.zza(obtainAndWriteInterfaceToken, z);
        obtainAndWriteInterfaceToken.writeInt(i);
        transactAndReadExceptionReturnVoid(15001, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzu zzu, String str, byte[] bArr, String str2, ParticipantResult[] participantResultArr) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeByteArray(bArr);
        obtainAndWriteInterfaceToken.writeString(str2);
        obtainAndWriteInterfaceToken.writeTypedArray(participantResultArr, 0);
        transactAndReadExceptionReturnVoid(ConnectionsStatusCodes.STATUS_BLUETOOTH_ERROR, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzu zzu, String str, byte[] bArr, ParticipantResult[] participantResultArr) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeByteArray(bArr);
        obtainAndWriteInterfaceToken.writeTypedArray(participantResultArr, 0);
        transactAndReadExceptionReturnVoid(ConnectionsStatusCodes.STATUS_ALREADY_HAVE_ACTIVE_STRATEGY, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzu zzu, boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        zzc.zza(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(6001, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzu zzu, boolean z, String[] strArr) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        zzc.zza(obtainAndWriteInterfaceToken, z);
        obtainAndWriteInterfaceToken.writeStringArray(strArr);
        transactAndReadExceptionReturnVoid(12031, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzu zzu, int[] iArr, int i, boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeIntArray(iArr);
        obtainAndWriteInterfaceToken.writeInt(i);
        zzc.zza(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(12010, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzu zzu, String[] strArr) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeStringArray(strArr);
        transactAndReadExceptionReturnVoid(GamesActivityResultCodes.RESULT_NETWORK_FAILURE, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzu zzu, String[] strArr, boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeStringArray(strArr);
        zzc.zza(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(12029, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzw zzw, long j) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzw);
        obtainAndWriteInterfaceToken.writeLong(j);
        transactAndReadExceptionReturnVoid(15501, obtainAndWriteInterfaceToken);
    }

    public final void zza(String str, int i) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeInt(i);
        transactAndReadExceptionReturnVoid(12017, obtainAndWriteInterfaceToken);
    }

    public final void zza(String str, IBinder iBinder, Bundle bundle) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeStrongBinder(iBinder);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) bundle);
        transactAndReadExceptionReturnVoid(13002, obtainAndWriteInterfaceToken);
    }

    public final void zza(String str, zzu zzu) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        transactAndReadExceptionReturnVoid(20001, obtainAndWriteInterfaceToken);
    }

    public final Intent zzag() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(9010, obtainAndWriteInterfaceToken());
        Intent intent = (Intent) zzc.zza(transactAndReadException, Intent.CREATOR);
        transactAndReadException.recycle();
        return intent;
    }

    public final Intent zzai() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(9012, obtainAndWriteInterfaceToken());
        Intent intent = (Intent) zzc.zza(transactAndReadException, Intent.CREATOR);
        transactAndReadException.recycle();
        return intent;
    }

    public final int zzak() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(9019, obtainAndWriteInterfaceToken());
        int readInt = transactAndReadException.readInt();
        transactAndReadException.recycle();
        return readInt;
    }

    public final String zzam() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(5003, obtainAndWriteInterfaceToken());
        String readString = transactAndReadException.readString();
        transactAndReadException.recycle();
        return readString;
    }

    public final int zzao() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(8024, obtainAndWriteInterfaceToken());
        int readInt = transactAndReadException.readInt();
        transactAndReadException.recycle();
        return readInt;
    }

    public final Intent zzaq() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(10015, obtainAndWriteInterfaceToken());
        Intent intent = (Intent) zzc.zza(transactAndReadException, Intent.CREATOR);
        transactAndReadException.recycle();
        return intent;
    }

    public final int zzar() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(10013, obtainAndWriteInterfaceToken());
        int readInt = transactAndReadException.readInt();
        transactAndReadException.recycle();
        return readInt;
    }

    public final int zzas() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(10023, obtainAndWriteInterfaceToken());
        int readInt = transactAndReadException.readInt();
        transactAndReadException.recycle();
        return readInt;
    }

    public final int zzat() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(12035, obtainAndWriteInterfaceToken());
        int readInt = transactAndReadException.readInt();
        transactAndReadException.recycle();
        return readInt;
    }

    public final int zzav() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(12036, obtainAndWriteInterfaceToken());
        int readInt = transactAndReadException.readInt();
        transactAndReadException.recycle();
        return readInt;
    }

    public final boolean zzaz() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(22030, obtainAndWriteInterfaceToken());
        boolean zza = zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    public final int zzb(byte[] bArr, String str, String[] strArr) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeByteArray(bArr);
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeStringArray(strArr);
        obtainAndWriteInterfaceToken = transactAndReadException(5034, obtainAndWriteInterfaceToken);
        int readInt = obtainAndWriteInterfaceToken.readInt();
        obtainAndWriteInterfaceToken.recycle();
        return readInt;
    }

    public final Intent zzb(String str, int i, int i2) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeInt(i);
        obtainAndWriteInterfaceToken.writeInt(i2);
        Parcel transactAndReadException = transactAndReadException(18001, obtainAndWriteInterfaceToken);
        Intent intent = (Intent) zzc.zza(transactAndReadException, Intent.CREATOR);
        transactAndReadException.recycle();
        return intent;
    }

    public final void zzb(long j) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeLong(j);
        transactAndReadExceptionReturnVoid(5059, obtainAndWriteInterfaceToken);
    }

    public final void zzb(zzu zzu) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        transactAndReadExceptionReturnVoid(5026, obtainAndWriteInterfaceToken);
    }

    public final void zzb(zzu zzu, int i) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeInt(i);
        transactAndReadExceptionReturnVoid(22016, obtainAndWriteInterfaceToken);
    }

    public final void zzb(zzu zzu, long j) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeLong(j);
        transactAndReadExceptionReturnVoid(ConnectionsStatusCodes.STATUS_ENDPOINT_IO_ERROR, obtainAndWriteInterfaceToken);
    }

    public final void zzb(zzu zzu, String str) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeString(str);
        transactAndReadExceptionReturnVoid(ConnectionsStatusCodes.STATUS_NOT_CONNECTED_TO_ENDPOINT, obtainAndWriteInterfaceToken);
    }

    public final void zzb(zzu zzu, String str, int i, int i2, int i3, boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeInt(i);
        obtainAndWriteInterfaceToken.writeInt(i2);
        obtainAndWriteInterfaceToken.writeInt(i3);
        zzc.zza(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(5020, obtainAndWriteInterfaceToken);
    }

    public final void zzb(zzu zzu, String str, int i, IBinder iBinder, Bundle bundle) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeInt(i);
        obtainAndWriteInterfaceToken.writeStrongBinder(iBinder);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) bundle);
        transactAndReadExceptionReturnVoid(7003, obtainAndWriteInterfaceToken);
    }

    public final void zzb(zzu zzu, String str, IBinder iBinder, Bundle bundle) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeStrongBinder(iBinder);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) bundle);
        transactAndReadExceptionReturnVoid(5024, obtainAndWriteInterfaceToken);
    }

    public final void zzb(zzu zzu, String str, String str2) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeString(str2);
        transactAndReadExceptionReturnVoid(12009, obtainAndWriteInterfaceToken);
    }

    public final void zzb(zzu zzu, String str, boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeString(str);
        zzc.zza(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(13006, obtainAndWriteInterfaceToken);
    }

    public final void zzb(zzu zzu, boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        zzc.zza(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(GamesStatusCodes.STATUS_MATCH_ERROR_OUT_OF_DATE_VERSION, obtainAndWriteInterfaceToken);
    }

    public final void zzb(zzu zzu, String[] strArr) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeStringArray(strArr);
        transactAndReadExceptionReturnVoid(GamesActivityResultCodes.RESULT_SEND_REQUEST_FAILED, obtainAndWriteInterfaceToken);
    }

    public final void zzb(String str, int i) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeInt(i);
        transactAndReadExceptionReturnVoid(5029, obtainAndWriteInterfaceToken);
    }

    public final void zzbd() throws RemoteException {
        transactAndReadExceptionReturnVoid(5006, obtainAndWriteInterfaceToken());
    }

    public final String zzbf() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(5012, obtainAndWriteInterfaceToken());
        String readString = transactAndReadException.readString();
        transactAndReadException.recycle();
        return readString;
    }

    public final DataHolder zzbg() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(5013, obtainAndWriteInterfaceToken());
        DataHolder dataHolder = (DataHolder) zzc.zza(transactAndReadException, DataHolder.CREATOR);
        transactAndReadException.recycle();
        return dataHolder;
    }

    public final DataHolder zzbh() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(5502, obtainAndWriteInterfaceToken());
        DataHolder dataHolder = (DataHolder) zzc.zza(transactAndReadException, DataHolder.CREATOR);
        transactAndReadException.recycle();
        return dataHolder;
    }

    public final Intent zzbi() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(19002, obtainAndWriteInterfaceToken());
        Intent intent = (Intent) zzc.zza(transactAndReadException, Intent.CREATOR);
        transactAndReadException.recycle();
        return intent;
    }

    public final Intent zzc(int i, int i2, boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeInt(i);
        obtainAndWriteInterfaceToken.writeInt(i2);
        zzc.zza(obtainAndWriteInterfaceToken, z);
        Parcel transactAndReadException = transactAndReadException(GamesStatusCodes.STATUS_VIDEO_OUT_OF_DISK_SPACE, obtainAndWriteInterfaceToken);
        Intent intent = (Intent) zzc.zza(transactAndReadException, Intent.CREATOR);
        transactAndReadException.recycle();
        return intent;
    }

    public final void zzc(long j) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeLong(j);
        transactAndReadExceptionReturnVoid(ConnectionsStatusCodes.STATUS_PAYLOAD_IO_ERROR, obtainAndWriteInterfaceToken);
    }

    public final void zzc(zzu zzu) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        transactAndReadExceptionReturnVoid(21007, obtainAndWriteInterfaceToken);
    }

    public final void zzc(zzu zzu, long j) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeLong(j);
        transactAndReadExceptionReturnVoid(GamesActivityResultCodes.RESULT_RECONNECT_REQUIRED, obtainAndWriteInterfaceToken);
    }

    public final void zzc(zzu zzu, String str) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeString(str);
        transactAndReadExceptionReturnVoid(8006, obtainAndWriteInterfaceToken);
    }

    public final void zzc(zzu zzu, boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        zzc.zza(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(8027, obtainAndWriteInterfaceToken);
    }

    public final Intent zzd(String str) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        Parcel transactAndReadException = transactAndReadException(12034, obtainAndWriteInterfaceToken);
        Intent intent = (Intent) zzc.zza(transactAndReadException, Intent.CREATOR);
        transactAndReadException.recycle();
        return intent;
    }

    public final void zzd(long j) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeLong(j);
        transactAndReadExceptionReturnVoid(GamesActivityResultCodes.RESULT_SIGN_IN_FAILED, obtainAndWriteInterfaceToken);
    }

    public final void zzd(zzu zzu) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        transactAndReadExceptionReturnVoid(22028, obtainAndWriteInterfaceToken);
    }

    public final void zzd(zzu zzu, long j) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeLong(j);
        transactAndReadExceptionReturnVoid(12011, obtainAndWriteInterfaceToken);
    }

    public final void zzd(zzu zzu, String str) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeString(str);
        transactAndReadExceptionReturnVoid(ConnectionsStatusCodes.STATUS_OUT_OF_ORDER_API_CALL, obtainAndWriteInterfaceToken);
    }

    public final void zzd(zzu zzu, boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        zzc.zza(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(12002, obtainAndWriteInterfaceToken);
    }

    public final void zzd(String str, int i) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeInt(i);
        transactAndReadExceptionReturnVoid(5028, obtainAndWriteInterfaceToken);
    }

    public final void zze(long j) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeLong(j);
        transactAndReadExceptionReturnVoid(12012, obtainAndWriteInterfaceToken);
    }

    public final void zze(zzu zzu, long j) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeLong(j);
        transactAndReadExceptionReturnVoid(22026, obtainAndWriteInterfaceToken);
    }

    public final void zze(zzu zzu, String str) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeString(str);
        transactAndReadExceptionReturnVoid(8010, obtainAndWriteInterfaceToken);
    }

    public final void zze(zzu zzu, boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        zzc.zza(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(12016, obtainAndWriteInterfaceToken);
    }

    public final void zzf(long j) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeLong(j);
        transactAndReadExceptionReturnVoid(22027, obtainAndWriteInterfaceToken);
    }

    public final void zzf(zzu zzu, String str) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeString(str);
        transactAndReadExceptionReturnVoid(8014, obtainAndWriteInterfaceToken);
    }

    public final void zzf(zzu zzu, boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        zzc.zza(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(17001, obtainAndWriteInterfaceToken);
    }

    public final void zzf(String str) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        transactAndReadExceptionReturnVoid(8002, obtainAndWriteInterfaceToken);
    }

    public final void zzg(zzu zzu, String str) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeString(str);
        transactAndReadExceptionReturnVoid(12020, obtainAndWriteInterfaceToken);
    }

    public final void zzh(zzu zzu, String str) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzu);
        obtainAndWriteInterfaceToken.writeString(str);
        transactAndReadExceptionReturnVoid(12008, obtainAndWriteInterfaceToken);
    }

    public final void zzk(int i) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeInt(i);
        transactAndReadExceptionReturnVoid(5036, obtainAndWriteInterfaceToken);
    }

    public final String zzp() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(5007, obtainAndWriteInterfaceToken());
        String readString = transactAndReadException.readString();
        transactAndReadException.recycle();
        return readString;
    }

    public final Intent zzv() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(GamesStatusCodes.STATUS_VIDEO_STORAGE_ERROR, obtainAndWriteInterfaceToken());
        Intent intent = (Intent) zzc.zza(transactAndReadException, Intent.CREATOR);
        transactAndReadException.recycle();
        return intent;
    }

    public final Intent zzx() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(9005, obtainAndWriteInterfaceToken());
        Intent intent = (Intent) zzc.zza(transactAndReadException, Intent.CREATOR);
        transactAndReadException.recycle();
        return intent;
    }

    public final Intent zzy() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(GamesStatusCodes.STATUS_VIDEO_ALREADY_CAPTURING, obtainAndWriteInterfaceToken());
        Intent intent = (Intent) zzc.zza(transactAndReadException, Intent.CREATOR);
        transactAndReadException.recycle();
        return intent;
    }

    public final Intent zzz() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(9007, obtainAndWriteInterfaceToken());
        Intent intent = (Intent) zzc.zza(transactAndReadException, Intent.CREATOR);
        transactAndReadException.recycle();
        return intent;
    }
}
