// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.model;

public class ApplicationEvents
{
    private int mBackupThreshold;
    private String mEventsType;
    private int mMaxEventsPerBatch;
    private int mMaxNumberOfEvents;
    private int[] mOptOutEvents;
    private boolean mSendEventsToggle;
    private boolean mSendUltraEvents;
    private String mServerEventsURL;
    
    public ApplicationEvents() {
    }
    
    public ApplicationEvents(final boolean mSendUltraEvents, final boolean mSendEventsToggle, final String mServerEventsURL, final String mEventsType, final int mBackupThreshold, final int mMaxNumberOfEvents, final int mMaxEventsPerBatch, final int[] mOptOutEvents) {
        this.mSendUltraEvents = mSendUltraEvents;
        this.mSendEventsToggle = mSendEventsToggle;
        this.mServerEventsURL = mServerEventsURL;
        this.mEventsType = mEventsType;
        this.mBackupThreshold = mBackupThreshold;
        this.mMaxNumberOfEvents = mMaxNumberOfEvents;
        this.mMaxEventsPerBatch = mMaxEventsPerBatch;
        this.mOptOutEvents = mOptOutEvents;
    }
    
    public int getEventsBackupThreshold() {
        return this.mBackupThreshold;
    }
    
    public String getEventsType() {
        return this.mEventsType;
    }
    
    public String getEventsURL() {
        return this.mServerEventsURL;
    }
    
    public int getMaxEventsPerBatch() {
        return this.mMaxEventsPerBatch;
    }
    
    public int getMaxNumberOfEvents() {
        return this.mMaxNumberOfEvents;
    }
    
    public int[] getOptOutEvents() {
        return this.mOptOutEvents;
    }
    
    public boolean isEventsEnabled() {
        return this.mSendEventsToggle;
    }
    
    public boolean isUltraEventsEnabled() {
        return this.mSendUltraEvents;
    }
}
