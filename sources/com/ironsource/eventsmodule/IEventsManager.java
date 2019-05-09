package com.ironsource.eventsmodule;

import android.content.Context;

public interface IEventsManager {
    void log(EventData eventData);

    void setBackupThreshold(int i);

    void setEventsUrl(String str, Context context);

    void setFormatterType(String str, Context context);

    void setIsEventsEnabled(boolean z);

    void setMaxEventsPerBatch(int i);

    void setMaxNumberOfEvents(int i);

    void setOptOutEvents(int[] iArr, Context context);
}
