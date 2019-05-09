// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.quest;

import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.data.Freezable;
import android.os.Parcelable;

@Deprecated
@VisibleForTesting
public interface Milestone extends Parcelable, Freezable<Milestone>
{
    public static final int STATE_CLAIMED = 4;
    public static final int STATE_COMPLETED_NOT_CLAIMED = 3;
    public static final int STATE_NOT_COMPLETED = 2;
    public static final int STATE_NOT_STARTED = 1;
    
    byte[] getCompletionRewardData();
    
    long getCurrentProgress();
    
    String getEventId();
    
    String getMilestoneId();
    
    int getState();
    
    long getTargetProgress();
}
