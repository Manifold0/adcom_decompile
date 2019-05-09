// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.events;

import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;

class EventsFormatterFactory
{
    static final int AD_UNIT_INTERSTITIAL = 2;
    static final int AD_UNIT_REWARDED_VIDEO = 3;
    static final String TYPE_IRONBEAST = "ironbeast";
    static final String TYPE_OUTCOME = "outcome";
    
    static AbstractEventsFormatter getFormatter(final String s, final int n) {
        if ("ironbeast".equals(s)) {
            return new IronbeastEventsFormatter(n);
        }
        if ("outcome".equals(s)) {
            return new OutcomeEventsFormatter(n);
        }
        if (n == 2) {
            return new IronbeastEventsFormatter(n);
        }
        if (n == 3) {
            return new OutcomeEventsFormatter(n);
        }
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.NATIVE, "EventsFormatterFactory failed to instantiate a formatter (type: " + s + ", adUnit: " + n + ")", 2);
        return null;
    }
}
