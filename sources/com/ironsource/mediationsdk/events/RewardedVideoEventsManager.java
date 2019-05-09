package com.ironsource.mediationsdk.events;

import com.google.android.gms.nearby.messages.Strategy;
import com.ironsource.eventsmodule.EventData;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.ironsource.mediationsdk.utils.SessionDepthManager;
import com.vungle.warren.ui.VungleActivity;

public class RewardedVideoEventsManager extends BaseEventsManager {
    private static RewardedVideoEventsManager sInstance;
    private String mCurrentOWPlacment;
    private String mCurrentRVPlacment;

    private RewardedVideoEventsManager() {
        this.mFormatterType = "outcome";
        this.mAdUnitType = 3;
        this.mEventType = IronSourceConstants.REWARDED_VIDEO_EVENT_TYPE;
        this.mCurrentRVPlacment = "";
        this.mCurrentOWPlacment = "";
    }

    public static synchronized RewardedVideoEventsManager getInstance() {
        RewardedVideoEventsManager rewardedVideoEventsManager;
        synchronized (RewardedVideoEventsManager.class) {
            if (sInstance == null) {
                sInstance = new RewardedVideoEventsManager();
                sInstance.initState();
            }
            rewardedVideoEventsManager = sInstance;
        }
        return rewardedVideoEventsManager;
    }

    protected boolean shouldExtractCurrentPlacement(EventData event) {
        return event.getEventId() == 2 || event.getEventId() == 10;
    }

    protected boolean shouldIncludeCurrentPlacement(EventData event) {
        return event.getEventId() == 5 || event.getEventId() == 6 || event.getEventId() == 8 || event.getEventId() == 9 || event.getEventId() == 19 || event.getEventId() == 20 || event.getEventId() == IronSourceConstants.OFFERWALL_OPENED;
    }

    protected boolean isTopPriorityEvent(EventData currentEvent) {
        return currentEvent.getEventId() == 6 || currentEvent.getEventId() == 5 || currentEvent.getEventId() == 10 || currentEvent.getEventId() == 14 || currentEvent.getEventId() == IronSourceConstants.OFFERWALL_OPENED;
    }

    protected int getSessionDepth(EventData event) {
        int sessionDepth = SessionDepthManager.getInstance().getSessionDepth(1);
        if (event.getEventId() == 15 || (event.getEventId() >= Strategy.TTL_SECONDS_DEFAULT && event.getEventId() < 400)) {
            return SessionDepthManager.getInstance().getSessionDepth(0);
        }
        return sessionDepth;
    }

    protected void setCurrentPlacement(EventData event) {
        if (event.getEventId() == 15 || (event.getEventId() >= Strategy.TTL_SECONDS_DEFAULT && event.getEventId() < 400)) {
            this.mCurrentOWPlacment = event.getAdditionalDataJSON().optString(VungleActivity.PLACEMENT_EXTRA);
        } else {
            this.mCurrentRVPlacment = event.getAdditionalDataJSON().optString(VungleActivity.PLACEMENT_EXTRA);
        }
    }

    protected String getCurrentPlacement(int eventId) {
        if (eventId == 15 || (eventId >= Strategy.TTL_SECONDS_DEFAULT && eventId < 400)) {
            return this.mCurrentOWPlacment;
        }
        return this.mCurrentRVPlacment;
    }

    protected boolean increaseSessionDepthIfNeeded(EventData event) {
        if (event.getEventId() == 6) {
            SessionDepthManager.getInstance().increaseSessionDepth(1);
        } else if (event.getEventId() == IronSourceConstants.OFFERWALL_OPENED) {
            SessionDepthManager.getInstance().increaseSessionDepth(0);
        }
        return false;
    }
}
