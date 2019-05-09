// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.api;

public class KongregateEventBundle
{
    private String mEventJSON;
    private String mEventName;
    
    public KongregateEventBundle(final String mEventName, final String mEventJSON) {
        this.mEventName = mEventName;
        this.mEventJSON = mEventJSON;
    }
    
    public String getJSONBundle() {
        return this.mEventJSON;
    }
    
    public String getName() {
        return this.mEventName;
    }
}
