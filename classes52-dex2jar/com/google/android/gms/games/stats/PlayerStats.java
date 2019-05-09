// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.stats;

import android.os.Bundle;
import com.google.android.gms.common.data.Freezable;
import android.os.Parcelable;

public interface PlayerStats extends Parcelable, Freezable<PlayerStats>
{
    public static final float UNSET_VALUE = -1.0f;
    
    float getAverageSessionLength();
    
    @Deprecated
    float getChurnProbability();
    
    int getDaysSinceLastPlayed();
    
    @Deprecated
    float getHighSpenderProbability();
    
    int getNumberOfPurchases();
    
    int getNumberOfSessions();
    
    float getSessionPercentile();
    
    float getSpendPercentile();
    
    @Deprecated
    float getSpendProbability();
    
    @Deprecated
    float getTotalSpendNext28Days();
    
    Bundle zzcn();
}
