// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import java.util.Map;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.support.v4.util.ArraySet;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import android.app.PendingIntent;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Indicator;
import java.util.Set;
import com.google.android.gms.common.server.response.FastJsonResponse$Field;
import java.util.HashMap;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.internal.auth.zzaz;

@SafeParcelable$Class(creator = "AuthenticatorTransferInfoCreator")
public class zzt extends zzaz
{
    public static final Parcelable$Creator<zzt> CREATOR;
    private static final HashMap<String, FastJsonResponse$Field<?, ?>> zzaz;
    @SafeParcelable$Indicator
    private final Set<Integer> zzba;
    @SafeParcelable$Field(getter = "getAccountType", id = 2)
    private String zzbn;
    @SafeParcelable$Field(getter = "getStatus", id = 3)
    private int zzbo;
    @SafeParcelable$Field(getter = "getTransferBytes", id = 4)
    private byte[] zzbp;
    @SafeParcelable$Field(getter = "getPendingIntent", id = 5)
    private PendingIntent zzbq;
    @SafeParcelable$Field(getter = "getDeviceMetaData", id = 6)
    private DeviceMetaData zzbr;
    @SafeParcelable$VersionField(id = 1)
    private final int zzv;
    
    static {
        CREATOR = (Parcelable$Creator)new zzu();
        (zzaz = new HashMap<String, FastJsonResponse$Field<?, ?>>()).put("accountType", FastJsonResponse$Field.forString("accountType", 2));
        zzt.zzaz.put("status", (FastJsonResponse$Field<?, ?>)FastJsonResponse$Field.forInteger("status", 3));
        zzt.zzaz.put("transferBytes", (FastJsonResponse$Field<?, ?>)FastJsonResponse$Field.forBase64("transferBytes", 4));
    }
    
    public zzt() {
        this.zzba = (Set<Integer>)new ArraySet(3);
        this.zzv = 1;
    }
    
    @SafeParcelable$Constructor
    zzt(@SafeParcelable$Indicator final Set<Integer> zzba, @SafeParcelable$Param(id = 1) final int zzv, @SafeParcelable$Param(id = 2) final String zzbn, @SafeParcelable$Param(id = 3) final int zzbo, @SafeParcelable$Param(id = 4) final byte[] zzbp, @SafeParcelable$Param(id = 5) final PendingIntent zzbq, @SafeParcelable$Param(id = 6) final DeviceMetaData zzbr) {
        this.zzba = zzba;
        this.zzv = zzv;
        this.zzbn = zzbn;
        this.zzbo = zzbo;
        this.zzbp = zzbp;
        this.zzbq = zzbq;
        this.zzbr = zzbr;
    }
    
    protected Object getFieldValue(final FastJsonResponse$Field fastJsonResponse$Field) {
        switch (fastJsonResponse$Field.getSafeParcelableFieldId()) {
            default: {
                throw new IllegalStateException(new StringBuilder(37).append("Unknown SafeParcelable id=").append(fastJsonResponse$Field.getSafeParcelableFieldId()).toString());
            }
            case 1: {
                return this.zzv;
            }
            case 2: {
                return this.zzbn;
            }
            case 3: {
                return this.zzbo;
            }
            case 4: {
                return this.zzbp;
            }
        }
    }
    
    protected boolean isFieldSet(final FastJsonResponse$Field fastJsonResponse$Field) {
        return this.zzba.contains(fastJsonResponse$Field.getSafeParcelableFieldId());
    }
    
    protected void setDecodedBytesInternal(final FastJsonResponse$Field<?, ?> fastJsonResponse$Field, final String s, final byte[] zzbp) {
        final int safeParcelableFieldId = fastJsonResponse$Field.getSafeParcelableFieldId();
        switch (safeParcelableFieldId) {
            default: {
                throw new IllegalArgumentException(new StringBuilder(59).append("Field with id=").append(safeParcelableFieldId).append(" is not known to be an byte array.").toString());
            }
            case 4: {
                this.zzbp = zzbp;
                this.zzba.add(safeParcelableFieldId);
            }
        }
    }
    
    protected void setIntegerInternal(final FastJsonResponse$Field<?, ?> fastJsonResponse$Field, final String s, final int zzbo) {
        final int safeParcelableFieldId = fastJsonResponse$Field.getSafeParcelableFieldId();
        switch (safeParcelableFieldId) {
            default: {
                throw new IllegalArgumentException(new StringBuilder(52).append("Field with id=").append(safeParcelableFieldId).append(" is not known to be an int.").toString());
            }
            case 3: {
                this.zzbo = zzbo;
                this.zzba.add(safeParcelableFieldId);
            }
        }
    }
    
    protected void setStringInternal(final FastJsonResponse$Field<?, ?> fastJsonResponse$Field, final String s, final String zzbn) {
        final int safeParcelableFieldId = fastJsonResponse$Field.getSafeParcelableFieldId();
        switch (safeParcelableFieldId) {
            default: {
                throw new IllegalArgumentException(String.format("Field with id=%d is not known to be a string.", safeParcelableFieldId));
            }
            case 2: {
                this.zzbn = zzbn;
                this.zzba.add(safeParcelableFieldId);
            }
        }
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        final Set<Integer> zzba = this.zzba;
        if (zzba.contains(1)) {
            SafeParcelWriter.writeInt(parcel, 1, this.zzv);
        }
        if (zzba.contains(2)) {
            SafeParcelWriter.writeString(parcel, 2, this.zzbn, true);
        }
        if (zzba.contains(3)) {
            SafeParcelWriter.writeInt(parcel, 3, this.zzbo);
        }
        if (zzba.contains(4)) {
            SafeParcelWriter.writeByteArray(parcel, 4, this.zzbp, true);
        }
        if (zzba.contains(5)) {
            SafeParcelWriter.writeParcelable(parcel, 5, (Parcelable)this.zzbq, n, true);
        }
        if (zzba.contains(6)) {
            SafeParcelWriter.writeParcelable(parcel, 6, (Parcelable)this.zzbr, n, true);
        }
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
