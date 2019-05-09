package com.kongregate.android.api;

public class KongregateEventBundle {
    private String mEventJSON;
    private String mEventName;

    public KongregateEventBundle(String str, String str2) {
        this.mEventName = str;
        this.mEventJSON = str2;
    }

    public String getName() {
        return this.mEventName;
    }

    public String getJSONBundle() {
        return this.mEventJSON;
    }
}
