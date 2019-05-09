// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.eventsmodule;

import java.util.List;
import java.util.ArrayList;

public interface IEventsStorageHelper
{
    void clearEvents(final String p0);
    
    ArrayList<EventData> loadEvents(final String p0);
    
    void saveEvents(final List<EventData> p0, final String p1);
}
