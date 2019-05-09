// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.accounttransfer;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import java.util.ArrayList;
import java.util.Map;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import java.util.List;
import com.google.android.gms.common.server.response.FastJsonResponse$Field;
import android.support.v4.util.ArrayMap;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.internal.auth.zzaz;

@SafeParcelable$Class(creator = "AccountTransferProgressCreator")
public class zzo extends zzaz
{
    public static final Parcelable$Creator<zzo> CREATOR;
    private static final ArrayMap<String, FastJsonResponse$Field<?, ?>> zzbe;
    @SafeParcelable$Field(getter = "getRegisteredAccountTypes", id = 2)
    private List<String> zzbf;
    @SafeParcelable$Field(getter = "getInProgressAccountTypes", id = 3)
    private List<String> zzbg;
    @SafeParcelable$Field(getter = "getSuccessAccountTypes", id = 4)
    private List<String> zzbh;
    @SafeParcelable$Field(getter = "getFailedAccountTypes", id = 5)
    private List<String> zzbi;
    @SafeParcelable$Field(getter = "getEscrowedAccountTypes", id = 6)
    private List<String> zzbj;
    @SafeParcelable$VersionField(id = 1)
    private final int zzv;
    
    static {
        CREATOR = (Parcelable$Creator)new zzp();
        (zzbe = new ArrayMap()).put((Object)"registered", (Object)FastJsonResponse$Field.forStrings("registered", 2));
        zzo.zzbe.put((Object)"in_progress", (Object)FastJsonResponse$Field.forStrings("in_progress", 3));
        zzo.zzbe.put((Object)"success", (Object)FastJsonResponse$Field.forStrings("success", 4));
        zzo.zzbe.put((Object)"failed", (Object)FastJsonResponse$Field.forStrings("failed", 5));
        zzo.zzbe.put((Object)"escrowed", (Object)FastJsonResponse$Field.forStrings("escrowed", 6));
    }
    
    public zzo() {
        this.zzv = 1;
    }
    
    @SafeParcelable$Constructor
    zzo(@SafeParcelable$Param(id = 1) final int zzv, @Nullable @SafeParcelable$Param(id = 2) final List<String> zzbf, @Nullable @SafeParcelable$Param(id = 3) final List<String> zzbg, @Nullable @SafeParcelable$Param(id = 4) final List<String> zzbh, @Nullable @SafeParcelable$Param(id = 5) final List<String> zzbi, @Nullable @SafeParcelable$Param(id = 6) final List<String> zzbj) {
        this.zzv = zzv;
        this.zzbf = zzbf;
        this.zzbg = zzbg;
        this.zzbh = zzbh;
        this.zzbi = zzbi;
        this.zzbj = zzbj;
    }
    
    public Map<String, FastJsonResponse$Field<?, ?>> getFieldMappings() {
        return (Map<String, FastJsonResponse$Field<?, ?>>)zzo.zzbe;
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
                return this.zzbf;
            }
            case 3: {
                return this.zzbg;
            }
            case 4: {
                return this.zzbh;
            }
            case 5: {
                return this.zzbi;
            }
            case 6: {
                return this.zzbj;
            }
        }
    }
    
    protected boolean isFieldSet(final FastJsonResponse$Field fastJsonResponse$Field) {
        return true;
    }
    
    protected void setStringsInternal(final FastJsonResponse$Field<?, ?> fastJsonResponse$Field, final String s, final ArrayList<String> zzbj) {
        final int safeParcelableFieldId = fastJsonResponse$Field.getSafeParcelableFieldId();
        switch (safeParcelableFieldId) {
            default: {
                throw new IllegalArgumentException(String.format("Field with id=%d is not known to be a string list.", safeParcelableFieldId));
            }
            case 2: {
                this.zzbf = zzbj;
            }
            case 3: {
                this.zzbg = zzbj;
            }
            case 4: {
                this.zzbh = zzbj;
            }
            case 5: {
                this.zzbi = zzbj;
            }
            case 6: {
                this.zzbj = zzbj;
            }
        }
    }
    
    public void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzv);
        SafeParcelWriter.writeStringList(parcel, 2, (List)this.zzbf, false);
        SafeParcelWriter.writeStringList(parcel, 3, (List)this.zzbg, false);
        SafeParcelWriter.writeStringList(parcel, 4, (List)this.zzbh, false);
        SafeParcelWriter.writeStringList(parcel, 5, (List)this.zzbi, false);
        SafeParcelWriter.writeStringList(parcel, 6, (List)this.zzbj, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
