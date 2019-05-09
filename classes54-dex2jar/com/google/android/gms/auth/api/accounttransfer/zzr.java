// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import java.util.Map;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import java.util.HashSet;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Indicator;
import java.util.Set;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.server.response.FastJsonResponse$Field;
import java.util.HashMap;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.internal.auth.zzaz;

@SafeParcelable$Class(creator = "AuthenticatorAnnotatedDataCreator")
public class zzr extends zzaz
{
    public static final Parcelable$Creator<zzr> CREATOR;
    private static final HashMap<String, FastJsonResponse$Field<?, ?>> zzaz;
    @SafeParcelable$Field(getter = "getPackageName", id = 4)
    private String mPackageName;
    @SafeParcelable$Indicator
    private final Set<Integer> zzba;
    @SafeParcelable$Field(getter = "getInfo", id = 2)
    private zzt zzbk;
    @SafeParcelable$Field(getter = "getSignature", id = 3)
    private String zzbl;
    @SafeParcelable$Field(getter = "getId", id = 5)
    private String zzbm;
    @SafeParcelable$VersionField(id = 1)
    private final int zzv;
    
    static {
        CREATOR = (Parcelable$Creator)new zzs();
        (zzaz = new HashMap<String, FastJsonResponse$Field<?, ?>>()).put("authenticatorInfo", FastJsonResponse$Field.forConcreteType("authenticatorInfo", 2, (Class)zzt.class));
        zzr.zzaz.put("signature", (FastJsonResponse$Field<?, ?>)FastJsonResponse$Field.forString("signature", 3));
        zzr.zzaz.put("package", (FastJsonResponse$Field<?, ?>)FastJsonResponse$Field.forString("package", 4));
    }
    
    public zzr() {
        this.zzba = new HashSet<Integer>(3);
        this.zzv = 1;
    }
    
    @SafeParcelable$Constructor
    zzr(@SafeParcelable$Indicator final Set<Integer> zzba, @SafeParcelable$Param(id = 1) final int zzv, @SafeParcelable$Param(id = 2) final zzt zzbk, @SafeParcelable$Param(id = 3) final String zzbl, @SafeParcelable$Param(id = 4) final String mPackageName, @SafeParcelable$Param(id = 5) final String zzbm) {
        this.zzba = zzba;
        this.zzv = zzv;
        this.zzbk = zzbk;
        this.zzbl = zzbl;
        this.mPackageName = mPackageName;
        this.zzbm = zzbm;
    }
    
    public <T extends FastJsonResponse> void addConcreteTypeInternal(final FastJsonResponse$Field<?, ?> fastJsonResponse$Field, final String s, final T t) {
        final int safeParcelableFieldId = fastJsonResponse$Field.getSafeParcelableFieldId();
        switch (safeParcelableFieldId) {
            default: {
                throw new IllegalArgumentException(String.format("Field with id=%d is not a known custom type. Found %s", safeParcelableFieldId, t.getClass().getCanonicalName()));
            }
            case 2: {
                this.zzbk = (zzt)t;
                this.zzba.add(safeParcelableFieldId);
            }
        }
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
                return this.zzbk;
            }
            case 3: {
                return this.zzbl;
            }
            case 4: {
                return this.mPackageName;
            }
        }
    }
    
    protected boolean isFieldSet(final FastJsonResponse$Field fastJsonResponse$Field) {
        return this.zzba.contains(fastJsonResponse$Field.getSafeParcelableFieldId());
    }
    
    protected void setStringInternal(final FastJsonResponse$Field<?, ?> fastJsonResponse$Field, final String s, final String s2) {
        final int safeParcelableFieldId = fastJsonResponse$Field.getSafeParcelableFieldId();
        switch (safeParcelableFieldId) {
            default: {
                throw new IllegalArgumentException(String.format("Field with id=%d is not known to be a string.", safeParcelableFieldId));
            }
            case 3: {
                this.zzbl = s2;
                break;
            }
            case 4: {
                this.mPackageName = s2;
                break;
            }
        }
        this.zzba.add(safeParcelableFieldId);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        final Set<Integer> zzba = this.zzba;
        if (zzba.contains(1)) {
            SafeParcelWriter.writeInt(parcel, 1, this.zzv);
        }
        if (zzba.contains(2)) {
            SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzbk, n, true);
        }
        if (zzba.contains(3)) {
            SafeParcelWriter.writeString(parcel, 3, this.zzbl, true);
        }
        if (zzba.contains(4)) {
            SafeParcelWriter.writeString(parcel, 4, this.mPackageName, true);
        }
        if (zzba.contains(5)) {
            SafeParcelWriter.writeString(parcel, 5, this.zzbm, true);
        }
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
