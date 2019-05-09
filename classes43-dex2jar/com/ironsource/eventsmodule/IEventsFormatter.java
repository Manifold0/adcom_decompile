// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.eventsmodule;

import org.json.JSONObject;
import java.util.ArrayList;

public interface IEventsFormatter
{
    String format(final ArrayList<EventData> p0, final JSONObject p1);
}
