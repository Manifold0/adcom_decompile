package com.ironsource.mediationsdk.model;

public class ApplicationEvents {
    private int mBackupThreshold;
    private String mEventsType;
    private int mMaxEventsPerBatch;
    private int mMaxNumberOfEvents;
    private int[] mOptOutEvents;
    private boolean mSendEventsToggle;
    private boolean mSendUltraEvents;
    private String mServerEventsURL;

    public ApplicationEvents(boolean sendUltraEvents, boolean sendEventsToggle, String serverEventsURL, String serverEventsType, int backupThreshold, int maxNumberOfEvents, int maxEventsPerBatch, int[] optOutEvents) {
        this.mSendUltraEvents = sendUltraEvents;
        this.mSendEventsToggle = sendEventsToggle;
        this.mServerEventsURL = serverEventsURL;
        this.mEventsType = serverEventsType;
        this.mBackupThreshold = backupThreshold;
        this.mMaxNumberOfEvents = maxNumberOfEvents;
        this.mMaxEventsPerBatch = maxEventsPerBatch;
        this.mOptOutEvents = optOutEvents;
    }

    public boolean isUltraEventsEnabled() {
        return this.mSendUltraEvents;
    }

    public boolean isEventsEnabled() {
        return this.mSendEventsToggle;
    }

    public String getEventsURL() {
        return this.mServerEventsURL;
    }

    public String getEventsType() {
        return this.mEventsType;
    }

    public int getEventsBackupThreshold() {
        return this.mBackupThreshold;
    }

    public int getMaxNumberOfEvents() {
        return this.mMaxNumberOfEvents;
    }

    public int getMaxEventsPerBatch() {
        return this.mMaxEventsPerBatch;
    }

    public int[] getOptOutEvents() {
        return this.mOptOutEvents;
    }
}
