package com.google.android.gms.games.multiplayer.realtime;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Preconditions;

public final class RealTimeMessage implements Parcelable {
    public static final Creator<RealTimeMessage> CREATOR = new zza();
    public static final int RELIABLE = 1;
    public static final int UNRELIABLE = 0;
    private final String zzoo;
    private final byte[] zzop;
    private final int zzoq;

    private RealTimeMessage(Parcel parcel) {
        this(parcel.readString(), parcel.createByteArray(), parcel.readInt());
    }

    private RealTimeMessage(String str, byte[] bArr, int i) {
        this.zzoo = (String) Preconditions.checkNotNull(str);
        this.zzop = (byte[]) ((byte[]) Preconditions.checkNotNull(bArr)).clone();
        this.zzoq = i;
    }

    public final int describeContents() {
        return 0;
    }

    public final byte[] getMessageData() {
        return this.zzop;
    }

    public final String getSenderParticipantId() {
        return this.zzoo;
    }

    public final boolean isReliable() {
        return this.zzoq == 1;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.zzoo);
        parcel.writeByteArray(this.zzop);
        parcel.writeInt(this.zzoq);
    }
}
