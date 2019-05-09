// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.events;

import com.ironsource.mediationsdk.utils.SessionDepthManager;
import com.ironsource.eventsmodule.EventData;

public class InterstitialEventsManager extends BaseEventsManager
{
    private static InterstitialEventsManager sInstance;
    private String mCurrentISPlacement;
    
    private InterstitialEventsManager() {
        this.mFormatterType = "ironbeast";
        this.mAdUnitType = 2;
        this.mEventType = "IS";
        this.mCurrentISPlacement = "";
    }
    
    public static InterstitialEventsManager getInstance() {
        synchronized (InterstitialEventsManager.class) {
            if (InterstitialEventsManager.sInstance == null) {
                (InterstitialEventsManager.sInstance = new InterstitialEventsManager()).initState();
            }
            return InterstitialEventsManager.sInstance;
        }
    }
    
    @Override
    protected String getCurrentPlacement(final int n) {
        return this.mCurrentISPlacement;
    }
    
    @Override
    protected int getSessionDepth(final EventData eventData) {
        int n;
        if (eventData.getEventId() >= 3000 && eventData.getEventId() < 4000) {
            n = 1;
        }
        else {
            n = 0;
        }
        final SessionDepthManager instance = SessionDepthManager.getInstance();
        int n2;
        if (n != 0) {
            n2 = 3;
        }
        else {
            n2 = 2;
        }
        return instance.getSessionDepth(n2);
    }
    
    @Override
    protected boolean increaseSessionDepthIfNeeded(final EventData eventData) {
        if (eventData.getEventId() == 26) {
            SessionDepthManager.getInstance().increaseSessionDepth(2);
        }
        else if (eventData.getEventId() == 3305) {
            SessionDepthManager.getInstance().increaseSessionDepth(3);
        }
        return false;
    }
    
    @Override
    protected boolean isTopPriorityEvent(final EventData eventData) {
        return eventData.getEventId() == 26 || eventData.getEventId() == 25 || eventData.getEventId() == 3005 || eventData.getEventId() == 3015;
    }
    
    @Override
    protected void setCurrentPlacement(final EventData eventData) {
        this.mCurrentISPlacement = eventData.getAdditionalDataJSON().optString("placement");
    }
    
    @Override
    protected boolean shouldExtractCurrentPlacement(final EventData eventData) {
        return eventData.getEventId() == 23 || eventData.getEventId() == 3001;
    }
    
    @Override
    protected boolean shouldIncludeCurrentPlacement(final EventData eventData) {
        return eventData.getEventId() == 25 || eventData.getEventId() == 26 || eventData.getEventId() == 28 || eventData.getEventId() == 29 || eventData.getEventId() == 34;
    }
}
