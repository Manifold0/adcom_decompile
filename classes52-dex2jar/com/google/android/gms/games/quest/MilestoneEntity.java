// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.quest;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.games.internal.zzd;

@Deprecated
@SafeParcelable$Class(creator = "MilestoneEntityCreator")
@SafeParcelable$Reserved({ 1000 })
public final class MilestoneEntity extends zzd implements Milestone
{
    public static final Parcelable$Creator<MilestoneEntity> CREATOR;
    @SafeParcelable$Field(getter = "getState", id = 5)
    private final int state;
    @SafeParcelable$Field(getter = "getEventId", id = 6)
    private final String zzfm;
    @SafeParcelable$Field(getter = "getMilestoneId", id = 1)
    private final String zzho;
    @SafeParcelable$Field(getter = "getCurrentProgress", id = 2)
    private final long zzpu;
    @SafeParcelable$Field(getter = "getTargetProgress", id = 3)
    private final long zzpv;
    @SafeParcelable$Field(getter = "getCompletionRewardData", id = 4)
    private final byte[] zzpw;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    public MilestoneEntity(final Milestone milestone) {
        this.zzho = milestone.getMilestoneId();
        this.zzpu = milestone.getCurrentProgress();
        this.zzpv = milestone.getTargetProgress();
        this.state = milestone.getState();
        this.zzfm = milestone.getEventId();
        final byte[] completionRewardData = milestone.getCompletionRewardData();
        if (completionRewardData == null) {
            this.zzpw = null;
            return;
        }
        System.arraycopy(completionRewardData, 0, this.zzpw = new byte[completionRewardData.length], 0, completionRewardData.length);
    }
    
    @SafeParcelable$Constructor
    MilestoneEntity(@SafeParcelable$Param(id = 1) final String zzho, @SafeParcelable$Param(id = 2) final long zzpu, @SafeParcelable$Param(id = 3) final long zzpv, @SafeParcelable$Param(id = 4) final byte[] zzpw, @SafeParcelable$Param(id = 5) final int state, @SafeParcelable$Param(id = 6) final String zzfm) {
        this.zzho = zzho;
        this.zzpu = zzpu;
        this.zzpv = zzpv;
        this.zzpw = zzpw;
        this.state = state;
        this.zzfm = zzfm;
    }
    
    static int zza(final Milestone milestone) {
        return Objects.hashCode(new Object[] { milestone.getMilestoneId(), milestone.getCurrentProgress(), milestone.getTargetProgress(), milestone.getState(), milestone.getEventId() });
    }
    
    static boolean zza(final Milestone milestone, final Object o) {
        if (o instanceof Milestone) {
            if (milestone == o) {
                return true;
            }
            final Milestone milestone2 = (Milestone)o;
            if (Objects.equal((Object)milestone2.getMilestoneId(), (Object)milestone.getMilestoneId()) && Objects.equal((Object)milestone2.getCurrentProgress(), (Object)milestone.getCurrentProgress()) && Objects.equal((Object)milestone2.getTargetProgress(), (Object)milestone.getTargetProgress()) && Objects.equal((Object)milestone2.getState(), (Object)milestone.getState()) && Objects.equal((Object)milestone2.getEventId(), (Object)milestone.getEventId())) {
                return true;
            }
        }
        return false;
    }
    
    static String zzb(final Milestone milestone) {
        return Objects.toStringHelper((Object)milestone).add("MilestoneId", (Object)milestone.getMilestoneId()).add("CurrentProgress", (Object)milestone.getCurrentProgress()).add("TargetProgress", (Object)milestone.getTargetProgress()).add("State", (Object)milestone.getState()).add("CompletionRewardData", (Object)milestone.getCompletionRewardData()).add("EventId", (Object)milestone.getEventId()).toString();
    }
    
    public final boolean equals(final Object o) {
        return zza(this, o);
    }
    
    public final Milestone freeze() {
        return this;
    }
    
    @Override
    public final byte[] getCompletionRewardData() {
        return this.zzpw;
    }
    
    @Override
    public final long getCurrentProgress() {
        return this.zzpu;
    }
    
    @Override
    public final String getEventId() {
        return this.zzfm;
    }
    
    @Override
    public final String getMilestoneId() {
        return this.zzho;
    }
    
    @Override
    public final int getState() {
        return this.state;
    }
    
    @Override
    public final long getTargetProgress() {
        return this.zzpv;
    }
    
    public final int hashCode() {
        return zza(this);
    }
    
    public final boolean isDataValid() {
        return true;
    }
    
    public final String toString() {
        return zzb(this);
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.getMilestoneId(), false);
        SafeParcelWriter.writeLong(parcel, 2, this.getCurrentProgress());
        SafeParcelWriter.writeLong(parcel, 3, this.getTargetProgress());
        SafeParcelWriter.writeByteArray(parcel, 4, this.getCompletionRewardData(), false);
        SafeParcelWriter.writeInt(parcel, 5, this.getState());
        SafeParcelWriter.writeString(parcel, 6, this.getEventId(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
