package com.google.android.gms.nearby.messages.internal;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.nearby.messages.MessageFilter;
import com.google.android.gms.nearby.messages.Strategy;

public final class zzcd implements Creator<SubscribeRequest> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        IBinder iBinder = null;
        Strategy strategy = null;
        IBinder iBinder2 = null;
        MessageFilter messageFilter = null;
        PendingIntent pendingIntent = null;
        int i2 = 0;
        String str = null;
        String str2 = null;
        byte[] bArr = null;
        boolean z = false;
        IBinder iBinder3 = null;
        boolean z2 = false;
        ClientAppContext clientAppContext = null;
        boolean z3 = false;
        int i3 = 0;
        int i4 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 2:
                    iBinder = SafeParcelReader.readIBinder(parcel, readHeader);
                    break;
                case 3:
                    strategy = (Strategy) SafeParcelReader.createParcelable(parcel, readHeader, Strategy.CREATOR);
                    break;
                case 4:
                    iBinder2 = SafeParcelReader.readIBinder(parcel, readHeader);
                    break;
                case 5:
                    messageFilter = (MessageFilter) SafeParcelReader.createParcelable(parcel, readHeader, MessageFilter.CREATOR);
                    break;
                case 6:
                    pendingIntent = (PendingIntent) SafeParcelReader.createParcelable(parcel, readHeader, PendingIntent.CREATOR);
                    break;
                case 7:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 8:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 9:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 10:
                    bArr = SafeParcelReader.createByteArray(parcel, readHeader);
                    break;
                case 11:
                    z = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 12:
                    iBinder3 = SafeParcelReader.readIBinder(parcel, readHeader);
                    break;
                case 13:
                    z2 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 14:
                    clientAppContext = (ClientAppContext) SafeParcelReader.createParcelable(parcel, readHeader, ClientAppContext.CREATOR);
                    break;
                case 15:
                    z3 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 16:
                    i3 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 17:
                    i4 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new SubscribeRequest(i, iBinder, strategy, iBinder2, messageFilter, pendingIntent, i2, str, str2, bArr, z, iBinder3, z2, clientAppContext, z3, i3, i4);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new SubscribeRequest[i];
    }
}
