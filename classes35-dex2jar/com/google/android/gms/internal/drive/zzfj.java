// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import com.google.android.gms.drive.events.DriveEvent;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.drive.events.zzr;
import com.google.android.gms.drive.events.zzv;
import com.google.android.gms.drive.events.zzb;
import com.google.android.gms.drive.events.zzo;
import com.google.android.gms.drive.events.CompletionEvent;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "OnEventResponseCreator")
@SafeParcelable$Reserved({ 1, 4, 8 })
public final class zzfj extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzfj> CREATOR;
    @SafeParcelable$Field(id = 2)
    private final int zzcy;
    @SafeParcelable$Field(id = 3)
    private final ChangeEvent zzhl;
    @SafeParcelable$Field(id = 5)
    private final CompletionEvent zzhm;
    @SafeParcelable$Field(id = 6)
    private final zzo zzhn;
    @SafeParcelable$Field(id = 7)
    private final zzb zzho;
    @SafeParcelable$Field(id = 9)
    private final zzv zzhp;
    @SafeParcelable$Field(id = 10)
    private final zzr zzhq;
    
    static {
        CREATOR = (Parcelable$Creator)new zzfk();
    }
    
    @SafeParcelable$Constructor
    zzfj(@SafeParcelable$Param(id = 2) final int zzcy, @SafeParcelable$Param(id = 3) final ChangeEvent zzhl, @SafeParcelable$Param(id = 5) final CompletionEvent zzhm, @SafeParcelable$Param(id = 6) final zzo zzhn, @SafeParcelable$Param(id = 7) final zzb zzho, @SafeParcelable$Param(id = 9) final zzv zzhp, @SafeParcelable$Param(id = 10) final zzr zzhq) {
        this.zzcy = zzcy;
        this.zzhl = zzhl;
        this.zzhm = zzhm;
        this.zzhn = zzhn;
        this.zzho = zzho;
        this.zzhp = zzhp;
        this.zzhq = zzhq;
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.zzcy);
        SafeParcelWriter.writeParcelable(parcel, 3, (Parcelable)this.zzhl, n, false);
        SafeParcelWriter.writeParcelable(parcel, 5, (Parcelable)this.zzhm, n, false);
        SafeParcelWriter.writeParcelable(parcel, 6, (Parcelable)this.zzhn, n, false);
        SafeParcelWriter.writeParcelable(parcel, 7, (Parcelable)this.zzho, n, false);
        SafeParcelWriter.writeParcelable(parcel, 9, (Parcelable)this.zzhp, n, false);
        SafeParcelWriter.writeParcelable(parcel, 10, (Parcelable)this.zzhq, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public final DriveEvent zzak() {
        switch (this.zzcy) {
            default: {
                throw new IllegalStateException(new StringBuilder(33).append("Unexpected event type ").append(this.zzcy).toString());
            }
            case 1: {
                return this.zzhl;
            }
            case 2: {
                return this.zzhm;
            }
            case 3: {
                return this.zzhn;
            }
            case 4: {
                return this.zzho;
            }
            case 7: {
                return this.zzhp;
            }
            case 8: {
                return this.zzhq;
            }
        }
    }
}
