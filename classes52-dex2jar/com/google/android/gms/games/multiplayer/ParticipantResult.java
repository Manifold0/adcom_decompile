// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.multiplayer;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.games.internal.zzd;

@SafeParcelable$Class(creator = "ParticipantResultCreator")
@SafeParcelable$Reserved({ 1000 })
public final class ParticipantResult extends zzd
{
    public static final Parcelable$Creator<ParticipantResult> CREATOR;
    public static final int MATCH_RESULT_DISAGREED = 5;
    public static final int MATCH_RESULT_DISCONNECT = 4;
    public static final int MATCH_RESULT_LOSS = 1;
    public static final int MATCH_RESULT_NONE = 3;
    public static final int MATCH_RESULT_TIE = 2;
    public static final int MATCH_RESULT_UNINITIALIZED = -1;
    public static final int MATCH_RESULT_WIN = 0;
    public static final int PLACING_UNINITIALIZED = -1;
    @SafeParcelable$Field(getter = "getParticipantId", id = 1)
    private final String zzhl;
    @SafeParcelable$Field(getter = "getResult", id = 2)
    private final int zzom;
    @SafeParcelable$Field(getter = "getPlacing", id = 3)
    private final int zzon;
    
    static {
        CREATOR = (Parcelable$Creator)new com.google.android.gms.games.multiplayer.zzd();
    }
    
    @SafeParcelable$Constructor
    public ParticipantResult(@SafeParcelable$Param(id = 1) final String s, @SafeParcelable$Param(id = 2) final int zzom, @SafeParcelable$Param(id = 3) final int zzon) {
        this.zzhl = (String)Preconditions.checkNotNull((Object)s);
        boolean b = false;
        switch (zzom) {
            default: {
                b = false;
                break;
            }
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5: {
                b = true;
                break;
            }
        }
        Preconditions.checkState(b);
        this.zzom = zzom;
        this.zzon = zzon;
    }
    
    public final boolean equals(final Object o) {
        if (o instanceof ParticipantResult) {
            if (this == o) {
                return true;
            }
            final ParticipantResult participantResult = (ParticipantResult)o;
            if (participantResult.getPlacing() == this.getPlacing() && participantResult.getResult() == this.getResult() && Objects.equal((Object)participantResult.getParticipantId(), (Object)this.getParticipantId())) {
                return true;
            }
        }
        return false;
    }
    
    public final String getParticipantId() {
        return this.zzhl;
    }
    
    public final int getPlacing() {
        return this.zzon;
    }
    
    public final int getResult() {
        return this.zzom;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.getPlacing(), this.getResult(), this.getParticipantId() });
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.getParticipantId(), false);
        SafeParcelWriter.writeInt(parcel, 2, this.getResult());
        SafeParcelWriter.writeInt(parcel, 3, this.getPlacing());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
