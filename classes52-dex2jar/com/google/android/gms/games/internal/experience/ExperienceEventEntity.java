// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.internal.experience;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.games.Game;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.net.Uri;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.games.internal.zzd;

@SafeParcelable$Class(creator = "ExperienceEventEntityCreator")
@SafeParcelable$Reserved({ 1000 })
public final class ExperienceEventEntity extends zzd implements ExperienceEvent
{
    public static final Parcelable$Creator<ExperienceEventEntity> CREATOR;
    @SafeParcelable$Field(getter = "getType", id = 10)
    private final int type;
    @SafeParcelable$Field(getter = "getIconImageUrl", id = 5)
    private final String zzac;
    @SafeParcelable$Field(getter = "getExperienceId", id = 1)
    private final String zzkx;
    @SafeParcelable$Field(getter = "getGame", id = 2)
    private final GameEntity zzky;
    @SafeParcelable$Field(getter = "getDisplayTitle", id = 3)
    private final String zzkz;
    @SafeParcelable$Field(getter = "getDisplayDescription", id = 4)
    private final String zzla;
    @SafeParcelable$Field(getter = "getCreatedTimestamp", id = 7)
    private final long zzlb;
    @SafeParcelable$Field(getter = "getXpEarned", id = 8)
    private final long zzlc;
    @SafeParcelable$Field(getter = "getCurrentXp", id = 9)
    private final long zzld;
    @SafeParcelable$Field(getter = "getNewLevel", id = 11)
    private final int zzle;
    @SafeParcelable$Field(getter = "getIconImageUri", id = 6)
    private final Uri zzr;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    @SafeParcelable$Constructor
    ExperienceEventEntity(@SafeParcelable$Param(id = 1) final String zzkx, @SafeParcelable$Param(id = 2) final GameEntity zzky, @SafeParcelable$Param(id = 3) final String zzkz, @SafeParcelable$Param(id = 4) final String zzla, @SafeParcelable$Param(id = 5) final String zzac, @SafeParcelable$Param(id = 6) final Uri zzr, @SafeParcelable$Param(id = 7) final long zzlb, @SafeParcelable$Param(id = 8) final long zzlc, @SafeParcelable$Param(id = 9) final long zzld, @SafeParcelable$Param(id = 10) final int type, @SafeParcelable$Param(id = 11) final int zzle) {
        this.zzkx = zzkx;
        this.zzky = zzky;
        this.zzkz = zzkz;
        this.zzla = zzla;
        this.zzac = zzac;
        this.zzr = zzr;
        this.zzlb = zzlb;
        this.zzlc = zzlc;
        this.zzld = zzld;
        this.type = type;
        this.zzle = zzle;
    }
    
    public final boolean equals(final Object o) {
        if (o instanceof ExperienceEvent) {
            if (this != o) {
                final ExperienceEvent experienceEvent = (ExperienceEvent)o;
                if (!Objects.equal((Object)experienceEvent.zzbm(), (Object)this.zzbm()) || !Objects.equal((Object)experienceEvent.getGame(), (Object)this.getGame()) || !Objects.equal((Object)experienceEvent.zzbn(), (Object)this.zzbn()) || !Objects.equal((Object)experienceEvent.zzbo(), (Object)this.zzbo()) || !Objects.equal((Object)experienceEvent.getIconImageUrl(), (Object)this.getIconImageUrl()) || !Objects.equal((Object)experienceEvent.getIconImageUri(), (Object)this.getIconImageUri()) || !Objects.equal((Object)experienceEvent.zzbp(), (Object)this.zzbp()) || !Objects.equal((Object)experienceEvent.zzbq(), (Object)this.zzbq()) || !Objects.equal((Object)experienceEvent.zzbr(), (Object)this.zzbr()) || !Objects.equal((Object)experienceEvent.getType(), (Object)this.getType()) || !Objects.equal((Object)experienceEvent.zzbs(), (Object)this.zzbs())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    @Override
    public final Game getGame() {
        return this.zzky;
    }
    
    @Override
    public final Uri getIconImageUri() {
        return this.zzr;
    }
    
    @Override
    public final String getIconImageUrl() {
        return this.zzac;
    }
    
    @Override
    public final int getType() {
        return this.type;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzbm(), this.getGame(), this.zzbn(), this.zzbo(), this.getIconImageUrl(), this.getIconImageUri(), this.zzbp(), this.zzbq(), this.zzbr(), this.getType(), this.zzbs() });
    }
    
    public final boolean isDataValid() {
        return true;
    }
    
    public final String toString() {
        return Objects.toStringHelper((Object)this).add("ExperienceId", (Object)this.zzbm()).add("Game", (Object)this.getGame()).add("DisplayTitle", (Object)this.zzbn()).add("DisplayDescription", (Object)this.zzbo()).add("IconImageUrl", (Object)this.getIconImageUrl()).add("IconImageUri", (Object)this.getIconImageUri()).add("CreatedTimestamp", (Object)this.zzbp()).add("XpEarned", (Object)this.zzbq()).add("CurrentXp", (Object)this.zzbr()).add("Type", (Object)this.getType()).add("NewLevel", (Object)this.zzbs()).toString();
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzkx, false);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzky, n, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzkz, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzla, false);
        SafeParcelWriter.writeString(parcel, 5, this.getIconImageUrl(), false);
        SafeParcelWriter.writeParcelable(parcel, 6, (Parcelable)this.zzr, n, false);
        SafeParcelWriter.writeLong(parcel, 7, this.zzlb);
        SafeParcelWriter.writeLong(parcel, 8, this.zzlc);
        SafeParcelWriter.writeLong(parcel, 9, this.zzld);
        SafeParcelWriter.writeInt(parcel, 10, this.type);
        SafeParcelWriter.writeInt(parcel, 11, this.zzle);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    @Override
    public final String zzbm() {
        return this.zzkx;
    }
    
    @Override
    public final String zzbn() {
        return this.zzkz;
    }
    
    @Override
    public final String zzbo() {
        return this.zzla;
    }
    
    @Override
    public final long zzbp() {
        return this.zzlb;
    }
    
    @Override
    public final long zzbq() {
        return this.zzlc;
    }
    
    @Override
    public final long zzbr() {
        return this.zzld;
    }
    
    @Override
    public final int zzbs() {
        return this.zzle;
    }
}
