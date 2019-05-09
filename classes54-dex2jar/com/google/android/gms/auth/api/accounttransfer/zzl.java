// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcelable;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import java.util.Map;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import java.util.HashSet;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import java.util.ArrayList;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Indicator;
import java.util.Set;
import com.google.android.gms.common.server.response.FastJsonResponse$Field;
import java.util.HashMap;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.internal.auth.zzaz;

@SafeParcelable$Class(creator = "AccountTransferMsgCreator")
public final class zzl extends zzaz
{
    public static final Parcelable$Creator<zzl> CREATOR;
    private static final HashMap<String, FastJsonResponse$Field<?, ?>> zzaz;
    @SafeParcelable$Indicator
    private final Set<Integer> zzba;
    @SafeParcelable$Field(getter = "getAuthenticatorDatas", id = 2)
    private ArrayList<zzr> zzbb;
    @SafeParcelable$Field(getter = "getRequestType", id = 3)
    private int zzbc;
    @SafeParcelable$Field(getter = "getProgress", id = 4)
    private zzo zzbd;
    @SafeParcelable$VersionField(id = 1)
    private final int zzv;
    
    static {
        CREATOR = (Parcelable$Creator)new zzm();
        (zzaz = new HashMap<String, FastJsonResponse$Field<?, ?>>()).put("authenticatorData", FastJsonResponse$Field.forConcreteTypeArray("authenticatorData", 2, (Class)zzr.class));
        zzl.zzaz.put("progress", (FastJsonResponse$Field<?, ?>)FastJsonResponse$Field.forConcreteType("progress", 4, (Class)zzo.class));
    }
    
    public zzl() {
        this.zzba = new HashSet<Integer>(1);
        this.zzv = 1;
    }
    
    @SafeParcelable$Constructor
    zzl(@SafeParcelable$Indicator final Set<Integer> zzba, @SafeParcelable$Param(id = 1) final int zzv, @SafeParcelable$Param(id = 2) final ArrayList<zzr> zzbb, @SafeParcelable$Param(id = 3) final int zzbc, @SafeParcelable$Param(id = 4) final zzo zzbd) {
        this.zzba = zzba;
        this.zzv = zzv;
        this.zzbb = zzbb;
        this.zzbc = zzbc;
        this.zzbd = zzbd;
    }
    
    public final <T extends FastJsonResponse> void addConcreteTypeArrayInternal(final FastJsonResponse$Field<?, ?> fastJsonResponse$Field, final String s, final ArrayList<T> zzbb) {
        final int safeParcelableFieldId = fastJsonResponse$Field.getSafeParcelableFieldId();
        switch (safeParcelableFieldId) {
            default: {
                throw new IllegalArgumentException(String.format("Field with id=%d is not a known ConcreteTypeArray type. Found %s", safeParcelableFieldId, zzbb.getClass().getCanonicalName()));
            }
            case 2: {
                this.zzbb = (ArrayList<zzr>)zzbb;
                this.zzba.add(safeParcelableFieldId);
            }
        }
    }
    
    public final <T extends FastJsonResponse> void addConcreteTypeInternal(final FastJsonResponse$Field<?, ?> fastJsonResponse$Field, final String s, final T t) {
        final int safeParcelableFieldId = fastJsonResponse$Field.getSafeParcelableFieldId();
        switch (safeParcelableFieldId) {
            default: {
                throw new IllegalArgumentException(String.format("Field with id=%d is not a known custom type. Found %s", safeParcelableFieldId, t.getClass().getCanonicalName()));
            }
            case 4: {
                this.zzbd = (zzo)t;
                this.zzba.add(safeParcelableFieldId);
            }
        }
    }
    
    protected final Object getFieldValue(final FastJsonResponse$Field fastJsonResponse$Field) {
        switch (fastJsonResponse$Field.getSafeParcelableFieldId()) {
            default: {
                throw new IllegalStateException(new StringBuilder(37).append("Unknown SafeParcelable id=").append(fastJsonResponse$Field.getSafeParcelableFieldId()).toString());
            }
            case 1: {
                return this.zzv;
            }
            case 2: {
                return this.zzbb;
            }
            case 4: {
                return this.zzbd;
            }
        }
    }
    
    protected final boolean isFieldSet(final FastJsonResponse$Field fastJsonResponse$Field) {
        return this.zzba.contains(fastJsonResponse$Field.getSafeParcelableFieldId());
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        final Set<Integer> zzba = this.zzba;
        if (zzba.contains(1)) {
            SafeParcelWriter.writeInt(parcel, 1, this.zzv);
        }
        if (zzba.contains(2)) {
            SafeParcelWriter.writeTypedList(parcel, 2, (List)this.zzbb, true);
        }
        if (zzba.contains(3)) {
            SafeParcelWriter.writeInt(parcel, 3, this.zzbc);
        }
        if (zzba.contains(4)) {
            SafeParcelWriter.writeParcelable(parcel, 4, (Parcelable)this.zzbd, n, true);
        }
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
