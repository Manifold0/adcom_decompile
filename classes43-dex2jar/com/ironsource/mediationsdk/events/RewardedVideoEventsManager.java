// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.events;

import com.ironsource.mediationsdk.utils.SessionDepthManager;
import com.ironsource.eventsmodule.EventData;

public class RewardedVideoEventsManager extends BaseEventsManager
{
    private static RewardedVideoEventsManager sInstance;
    private String mCurrentOWPlacment;
    private String mCurrentRVPlacment;
    
    private RewardedVideoEventsManager() {
        this.mFormatterType = "outcome";
        this.mAdUnitType = 3;
        this.mEventType = "RV";
        this.mCurrentRVPlacment = "";
        this.mCurrentOWPlacment = "";
    }
    
    public static RewardedVideoEventsManager getInstance() {
        synchronized (RewardedVideoEventsManager.class) {
            if (RewardedVideoEventsManager.sInstance == null) {
                (RewardedVideoEventsManager.sInstance = new RewardedVideoEventsManager()).initState();
            }
            return RewardedVideoEventsManager.sInstance;
        }
    }
    
    @Override
    protected String getCurrentPlacement(final int n) {
        if (n == 15 || (n >= 300 && n < 400)) {
            return this.mCurrentOWPlacment;
        }
        return this.mCurrentRVPlacment;
    }
    
    @Override
    protected int getSessionDepth(final EventData eventData) {
        final int sessionDepth = SessionDepthManager.getInstance().getSessionDepth(1);
        if (eventData.getEventId() != 15) {
            int sessionDepth2 = sessionDepth;
            if (eventData.getEventId() < 300) {
                return sessionDepth2;
            }
            sessionDepth2 = sessionDepth;
            if (eventData.getEventId() >= 400) {
                return sessionDepth2;
            }
        }
        return SessionDepthManager.getInstance().getSessionDepth(0);
    }
    
    @Override
    protected boolean increaseSessionDepthIfNeeded(final EventData eventData) {
        if (eventData.getEventId() == 6) {
            SessionDepthManager.getInstance().increaseSessionDepth(1);
        }
        else if (eventData.getEventId() == 305) {
            SessionDepthManager.getInstance().increaseSessionDepth(0);
            return false;
        }
        return false;
    }
    
    @Override
    protected boolean isTopPriorityEvent(final EventData eventData) {
        return eventData.getEventId() == 6 || eventData.getEventId() == 5 || eventData.getEventId() == 10 || eventData.getEventId() == 14 || eventData.getEventId() == 305;
    }
    
    @Override
    protected void setCurrentPlacement(final EventData eventData) {
        if (eventData.getEventId() == 15 || (eventData.getEventId() >= 300 && eventData.getEventId() < 400)) {
            this.mCurrentOWPlacment = eventData.getAdditionalDataJSON().optString("placement");
            return;
        }
        this.mCurrentRVPlacment = eventData.getAdditionalDataJSON().optString("placement");
    }
    
    @Override
    protected boolean shouldExtractCurrentPlacement(final EventData eventData) {
        return eventData.getEventId() == 2 || eventData.getEventId() == 10;
    }
    
    @Override
    protected boolean shouldIncludeCurrentPlacement(final EventData eventData) {
        return eventData.getEventId() == 5 || eventData.getEventId() == 6 || eventData.getEventId() == 8 || eventData.getEventId() == 9 || eventData.getEventId() == 19 || eventData.getEventId() == 20 || eventData.getEventId() == 305;
    }
}
