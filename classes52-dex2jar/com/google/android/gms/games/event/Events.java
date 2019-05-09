// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.event;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.util.VisibleForTesting;

@Deprecated
@VisibleForTesting
public interface Events
{
    void increment(final GoogleApiClient p0, final String p1, final int p2);
    
    PendingResult<LoadEventsResult> load(final GoogleApiClient p0, final boolean p1);
    
    PendingResult<LoadEventsResult> loadByIds(final GoogleApiClient p0, final boolean p1, final String... p2);
    
    @Deprecated
    public interface LoadEventsResult extends Releasable, Result
    {
        EventBuffer getEvents();
    }
}
