// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.achievement;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.AbstractDataBuffer;

public final class AchievementBuffer extends AbstractDataBuffer<Achievement>
{
    public AchievementBuffer(final DataHolder dataHolder) {
        super(dataHolder);
    }
    
    public final Achievement get(final int n) {
        return new AchievementRef(this.mDataHolder, n);
    }
}
