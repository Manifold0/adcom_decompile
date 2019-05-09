package com.google.android.gms.games.quest;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.games.internal.zzd;

@Class(creator = "MilestoneEntityCreator")
@Reserved({1000})
@Deprecated
public final class MilestoneEntity extends zzd implements Milestone {
    public static final Creator<MilestoneEntity> CREATOR = new zza();
    @Field(getter = "getState", id = 5)
    private final int state;
    @Field(getter = "getEventId", id = 6)
    private final String zzfm;
    @Field(getter = "getMilestoneId", id = 1)
    private final String zzho;
    @Field(getter = "getCurrentProgress", id = 2)
    private final long zzpu;
    @Field(getter = "getTargetProgress", id = 3)
    private final long zzpv;
    @Field(getter = "getCompletionRewardData", id = 4)
    private final byte[] zzpw;

    public MilestoneEntity(Milestone milestone) {
        this.zzho = milestone.getMilestoneId();
        this.zzpu = milestone.getCurrentProgress();
        this.zzpv = milestone.getTargetProgress();
        this.state = milestone.getState();
        this.zzfm = milestone.getEventId();
        Object completionRewardData = milestone.getCompletionRewardData();
        if (completionRewardData == null) {
            this.zzpw = null;
            return;
        }
        this.zzpw = new byte[completionRewardData.length];
        System.arraycopy(completionRewardData, 0, this.zzpw, 0, completionRewardData.length);
    }

    @Constructor
    MilestoneEntity(@Param(id = 1) String str, @Param(id = 2) long j, @Param(id = 3) long j2, @Param(id = 4) byte[] bArr, @Param(id = 5) int i, @Param(id = 6) String str2) {
        this.zzho = str;
        this.zzpu = j;
        this.zzpv = j2;
        this.zzpw = bArr;
        this.state = i;
        this.zzfm = str2;
    }

    static int zza(Milestone milestone) {
        return Objects.hashCode(new Object[]{milestone.getMilestoneId(), Long.valueOf(milestone.getCurrentProgress()), Long.valueOf(milestone.getTargetProgress()), Integer.valueOf(milestone.getState()), milestone.getEventId()});
    }

    static boolean zza(Milestone milestone, Object obj) {
        if (!(obj instanceof Milestone)) {
            return false;
        }
        if (milestone == obj) {
            return true;
        }
        Milestone milestone2 = (Milestone) obj;
        return Objects.equal(milestone2.getMilestoneId(), milestone.getMilestoneId()) && Objects.equal(Long.valueOf(milestone2.getCurrentProgress()), Long.valueOf(milestone.getCurrentProgress())) && Objects.equal(Long.valueOf(milestone2.getTargetProgress()), Long.valueOf(milestone.getTargetProgress())) && Objects.equal(Integer.valueOf(milestone2.getState()), Integer.valueOf(milestone.getState())) && Objects.equal(milestone2.getEventId(), milestone.getEventId());
    }

    static String zzb(Milestone milestone) {
        return Objects.toStringHelper(milestone).add("MilestoneId", milestone.getMilestoneId()).add("CurrentProgress", Long.valueOf(milestone.getCurrentProgress())).add("TargetProgress", Long.valueOf(milestone.getTargetProgress())).add("State", Integer.valueOf(milestone.getState())).add("CompletionRewardData", milestone.getCompletionRewardData()).add("EventId", milestone.getEventId()).toString();
    }

    public final boolean equals(Object obj) {
        return zza(this, obj);
    }

    public final Milestone freeze() {
        return this;
    }

    public final byte[] getCompletionRewardData() {
        return this.zzpw;
    }

    public final long getCurrentProgress() {
        return this.zzpu;
    }

    public final String getEventId() {
        return this.zzfm;
    }

    public final String getMilestoneId() {
        return this.zzho;
    }

    public final int getState() {
        return this.state;
    }

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

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getMilestoneId(), false);
        SafeParcelWriter.writeLong(parcel, 2, getCurrentProgress());
        SafeParcelWriter.writeLong(parcel, 3, getTargetProgress());
        SafeParcelWriter.writeByteArray(parcel, 4, getCompletionRewardData(), false);
        SafeParcelWriter.writeInt(parcel, 5, getState());
        SafeParcelWriter.writeString(parcel, 6, getEventId(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
