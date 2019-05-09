// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.quest;

import android.os.Parcel;
import com.google.android.gms.games.internal.zzh;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.DataBufferRef;

public final class zzb extends DataBufferRef implements Milestone
{
    zzb(final DataHolder dataHolder, final int n) {
        super(dataHolder, n);
    }
    
    public final int describeContents() {
        return 0;
    }
    
    public final boolean equals(final Object o) {
        return MilestoneEntity.zza(this, o);
    }
    
    public final /* synthetic */ Object freeze() {
        return new MilestoneEntity(this);
    }
    
    public final byte[] getCompletionRewardData() {
        return this.getByteArray("completion_reward_data");
    }
    
    public final long getCurrentProgress() {
        final long n = 0L;
        long n2 = 0L;
        switch (this.getState()) {
            default: {
                n2 = 0L;
                break;
            }
            case 3:
            case 4: {
                n2 = this.getTargetProgress();
                break;
            }
            case 1: {
                n2 = 0L;
                break;
            }
            case 2: {
                final long n3 = n2 = this.getLong("current_value");
                if (this.getLong("quest_state") != 6L) {
                    n2 = n3 - this.getLong("initial_value");
                    break;
                }
                break;
            }
        }
        if (n2 < 0L) {
            zzh.e("MilestoneRef", "Current progress should never be negative");
            n2 = n;
        }
        long targetProgress = n2;
        if (n2 > this.getTargetProgress()) {
            zzh.e("MilestoneRef", "Current progress should never exceed target progress");
            targetProgress = this.getTargetProgress();
        }
        return targetProgress;
    }
    
    public final String getEventId() {
        return this.getString("external_event_id");
    }
    
    public final String getMilestoneId() {
        return this.getString("external_milestone_id");
    }
    
    public final int getState() {
        return this.getInteger("milestone_state");
    }
    
    public final long getTargetProgress() {
        return this.getLong("target_value");
    }
    
    public final int hashCode() {
        return MilestoneEntity.zza(this);
    }
    
    public final String toString() {
        return MilestoneEntity.zzb(this);
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        ((MilestoneEntity)this.freeze()).writeToParcel(parcel, n);
    }
}
