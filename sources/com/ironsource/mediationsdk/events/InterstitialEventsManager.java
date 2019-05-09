package com.ironsource.mediationsdk.events;

import com.google.android.gms.games.GamesStatusCodes;
import com.ironsource.eventsmodule.EventData;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.ironsource.mediationsdk.utils.SessionDepthManager;
import com.vungle.warren.ui.VungleActivity;

public class InterstitialEventsManager extends BaseEventsManager {
    private static InterstitialEventsManager sInstance;
    private String mCurrentISPlacement;

    private InterstitialEventsManager() {
        this.mFormatterType = "ironbeast";
        this.mAdUnitType = 2;
        this.mEventType = IronSourceConstants.INTERSTITIAL_EVENT_TYPE;
        this.mCurrentISPlacement = "";
    }

    public static synchronized InterstitialEventsManager getInstance() {
        InterstitialEventsManager interstitialEventsManager;
        synchronized (InterstitialEventsManager.class) {
            if (sInstance == null) {
                sInstance = new InterstitialEventsManager();
                sInstance.initState();
            }
            interstitialEventsManager = sInstance;
        }
        return interstitialEventsManager;
    }

    protected boolean shouldExtractCurrentPlacement(EventData event) {
        return event.getEventId() == 23 || event.getEventId() == 3001;
    }

    protected boolean shouldIncludeCurrentPlacement(EventData event) {
        return event.getEventId() == 25 || event.getEventId() == 26 || event.getEventId() == 28 || event.getEventId() == 29 || event.getEventId() == 34;
    }

    protected boolean isTopPriorityEvent(EventData currentEvent) {
        return currentEvent.getEventId() == 26 || currentEvent.getEventId() == 25 || currentEvent.getEventId() == 3005 || currentEvent.getEventId() == IronSourceConstants.BN_INSTANCE_RELOAD_SUCCESS;
    }

    protected int getSessionDepth(EventData event) {
        boolean isBanner = event.getEventId() >= 3000 && event.getEventId() < GamesStatusCodes.STATUS_SNAPSHOT_NOT_FOUND;
        return SessionDepthManager.getInstance().getSessionDepth(isBanner ? 3 : 2);
    }

    protected boolean increaseSessionDepthIfNeeded(EventData event) {
        if (event.getEventId() == 26) {
            SessionDepthManager.getInstance().increaseSessionDepth(2);
        } else if (event.getEventId() == IronSourceConstants.BN_INSTANCE_DESTROY) {
            SessionDepthManager.getInstance().increaseSessionDepth(3);
        }
        return false;
    }

    protected void setCurrentPlacement(EventData event) {
        this.mCurrentISPlacement = event.getAdditionalDataJSON().optString(VungleActivity.PLACEMENT_EXTRA);
    }

    protected String getCurrentPlacement(int eventId) {
        return this.mCurrentISPlacement;
    }
}
