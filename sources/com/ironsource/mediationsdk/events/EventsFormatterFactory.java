package com.ironsource.mediationsdk.events;

import com.ironsource.mediationsdk.logger.IronSourceLogger.IronSourceTag;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;

class EventsFormatterFactory {
    static final int AD_UNIT_INTERSTITIAL = 2;
    static final int AD_UNIT_REWARDED_VIDEO = 3;
    static final String TYPE_IRONBEAST = "ironbeast";
    static final String TYPE_OUTCOME = "outcome";

    EventsFormatterFactory() {
    }

    static AbstractEventsFormatter getFormatter(String type, int adUnit) {
        if (TYPE_IRONBEAST.equals(type)) {
            return new IronbeastEventsFormatter(adUnit);
        }
        if (TYPE_OUTCOME.equals(type)) {
            return new OutcomeEventsFormatter(adUnit);
        }
        if (adUnit == 2) {
            return new IronbeastEventsFormatter(adUnit);
        }
        if (adUnit == 3) {
            return new OutcomeEventsFormatter(adUnit);
        }
        IronSourceLoggerManager.getLogger().log(IronSourceTag.NATIVE, "EventsFormatterFactory failed to instantiate a formatter (type: " + type + ", adUnit: " + adUnit + ")", 2);
        return null;
    }
}
