// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.eventsmodule;

import android.content.Context;

public interface IEventsManager
{
    void log(final EventData p0);
    
    void setBackupThreshold(final int p0);
    
    void setEventsUrl(final String p0, final Context p1);
    
    void setFormatterType(final String p0, final Context p1);
    
    void setIsEventsEnabled(final boolean p0);
    
    void setMaxEventsPerBatch(final int p0);
    
    void setMaxNumberOfEvents(final int p0);
    
    void setOptOutEvents(final int[] p0, final Context p1);
}
