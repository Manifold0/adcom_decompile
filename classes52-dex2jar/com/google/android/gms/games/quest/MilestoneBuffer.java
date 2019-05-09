// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.quest;

import com.google.android.gms.common.data.AbstractDataBuffer;

@Deprecated
public final class MilestoneBuffer extends AbstractDataBuffer<Milestone>
{
    public final Milestone get(final int n) {
        return new zzb(this.mDataHolder, n);
    }
}
