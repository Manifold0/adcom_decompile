package com.google.android.gms.auth.api.accounttransfer;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.util.ArraySet;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Indicator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.server.response.FastJsonResponse.Field;
import com.google.android.gms.internal.auth.zzaz;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Class(creator = "AuthenticatorTransferInfoCreator")
public class zzt extends zzaz {
    public static final Creator<zzt> CREATOR = new zzu();
    private static final HashMap<String, Field<?, ?>> zzaz;
    @Indicator
    private final Set<Integer> zzba;
    @SafeParcelable.Field(getter = "getAccountType", id = 2)
    private String zzbn;
    @SafeParcelable.Field(getter = "getStatus", id = 3)
    private int zzbo;
    @SafeParcelable.Field(getter = "getTransferBytes", id = 4)
    private byte[] zzbp;
    @SafeParcelable.Field(getter = "getPendingIntent", id = 5)
    private PendingIntent zzbq;
    @SafeParcelable.Field(getter = "getDeviceMetaData", id = 6)
    private DeviceMetaData zzbr;
    @VersionField(id = 1)
    private final int zzv;

    @Constructor
    zzt(@Indicator Set<Integer> set, @Param(id = 1) int i, @Param(id = 2) String str, @Param(id = 3) int i2, @Param(id = 4) byte[] bArr, @Param(id = 5) PendingIntent pendingIntent, @Param(id = 6) DeviceMetaData deviceMetaData) {
        this.zzba = set;
        this.zzv = i;
        this.zzbn = str;
        this.zzbo = i2;
        this.zzbp = bArr;
        this.zzbq = pendingIntent;
        this.zzbr = deviceMetaData;
    }

    public zzt() {
        this.zzba = new ArraySet(3);
        this.zzv = 1;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        Set set = this.zzba;
        if (set.contains(Integer.valueOf(1))) {
            SafeParcelWriter.writeInt(parcel, 1, this.zzv);
        }
        if (set.contains(Integer.valueOf(2))) {
            SafeParcelWriter.writeString(parcel, 2, this.zzbn, true);
        }
        if (set.contains(Integer.valueOf(3))) {
            SafeParcelWriter.writeInt(parcel, 3, this.zzbo);
        }
        if (set.contains(Integer.valueOf(4))) {
            SafeParcelWriter.writeByteArray(parcel, 4, this.zzbp, true);
        }
        if (set.contains(Integer.valueOf(5))) {
            SafeParcelWriter.writeParcelable(parcel, 5, this.zzbq, i, true);
        }
        if (set.contains(Integer.valueOf(6))) {
            SafeParcelWriter.writeParcelable(parcel, 6, this.zzbr, i, true);
        }
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    protected boolean isFieldSet(Field field) {
        return this.zzba.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
    }

    protected Object getFieldValue(Field field) {
        switch (field.getSafeParcelableFieldId()) {
            case 1:
                return Integer.valueOf(this.zzv);
            case 2:
                return this.zzbn;
            case 3:
                return Integer.valueOf(this.zzbo);
            case 4:
                return this.zzbp;
            default:
                throw new IllegalStateException("Unknown SafeParcelable id=" + field.getSafeParcelableFieldId());
        }
    }

    protected void setStringInternal(Field<?, ?> field, String str, String str2) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        switch (safeParcelableFieldId) {
            case 2:
                this.zzbn = str2;
                this.zzba.add(Integer.valueOf(safeParcelableFieldId));
                return;
            default:
                throw new IllegalArgumentException(String.format("Field with id=%d is not known to be a string.", new Object[]{Integer.valueOf(safeParcelableFieldId)}));
        }
    }

    protected void setIntegerInternal(Field<?, ?> field, String str, int i) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        switch (safeParcelableFieldId) {
            case 3:
                this.zzbo = i;
                this.zzba.add(Integer.valueOf(safeParcelableFieldId));
                return;
            default:
                throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be an int.");
        }
    }

    protected void setDecodedBytesInternal(Field<?, ?> field, String str, byte[] bArr) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        switch (safeParcelableFieldId) {
            case 4:
                this.zzbp = bArr;
                this.zzba.add(Integer.valueOf(safeParcelableFieldId));
                return;
            default:
                throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be an byte array.");
        }
    }

    public /* synthetic */ Map getFieldMappings() {
        return zzaz;
    }

    static {
        HashMap hashMap = new HashMap();
        zzaz = hashMap;
        hashMap.put("accountType", Field.forString("accountType", 2));
        zzaz.put("status", Field.forInteger("status", 3));
        zzaz.put("transferBytes", Field.forBase64("transferBytes", 4));
    }
}
