// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.multiplayer.realtime;

import com.google.android.gms.common.internal.Preconditions;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public final class RealTimeMessage implements Parcelable
{
    public static final Parcelable$Creator<RealTimeMessage> CREATOR;
    public static final int RELIABLE = 1;
    public static final int UNRELIABLE = 0;
    private final String zzoo;
    private final byte[] zzop;
    private final int zzoq;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    private RealTimeMessage(final Parcel parcel) {
        this(parcel.readString(), parcel.createByteArray(), parcel.readInt());
    }
    
    private RealTimeMessage(final String s, final byte[] array, final int zzoq) {
        this.zzoo = (String)Preconditions.checkNotNull((Object)s);
        this.zzop = ((byte[])Preconditions.checkNotNull((Object)array)).clone();
        this.zzoq = zzoq;
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
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeString(this.zzoo);
        parcel.writeByteArray(this.zzop);
        parcel.writeInt(this.zzoq);
    }
}
