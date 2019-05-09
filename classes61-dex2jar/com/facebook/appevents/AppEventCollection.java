// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.appevents;

import java.util.Set;
import java.util.Iterator;
import android.content.Context;
import com.facebook.internal.AttributionIdentifiers;
import com.facebook.FacebookSdk;
import java.util.HashMap;

class AppEventCollection
{
    private final HashMap<AccessTokenAppIdPair, SessionEventsState> stateMap;
    
    public AppEventCollection() {
        this.stateMap = new HashMap<AccessTokenAppIdPair, SessionEventsState>();
    }
    
    private SessionEventsState getSessionEventsState(final AccessTokenAppIdPair accessTokenAppIdPair) {
        synchronized (this) {
            SessionEventsState sessionEventsState;
            if ((sessionEventsState = this.stateMap.get(accessTokenAppIdPair)) == null) {
                final Context applicationContext = FacebookSdk.getApplicationContext();
                sessionEventsState = new SessionEventsState(AttributionIdentifiers.getAttributionIdentifiers(applicationContext), AppEventsLogger.getAnonymousAppDeviceGUID(applicationContext));
            }
            this.stateMap.put(accessTokenAppIdPair, sessionEventsState);
            return sessionEventsState;
        }
    }
    
    public void addEvent(final AccessTokenAppIdPair accessTokenAppIdPair, final AppEvent appEvent) {
        synchronized (this) {
            this.getSessionEventsState(accessTokenAppIdPair).addEvent(appEvent);
        }
    }
    
    public void addPersistedEvents(final PersistedEvents persistedEvents) {
        // monitorenter(this)
        if (persistedEvents != null) {
            try {
                for (final AccessTokenAppIdPair accessTokenAppIdPair : persistedEvents.keySet()) {
                    final SessionEventsState sessionEventsState = this.getSessionEventsState(accessTokenAppIdPair);
                    final Iterator<AppEvent> iterator2 = persistedEvents.get(accessTokenAppIdPair).iterator();
                    while (iterator2.hasNext()) {
                        sessionEventsState.addEvent(iterator2.next());
                    }
                }
            }
            finally {
            }
            // monitorexit(this)
        }
    }
    // monitorexit(this)
    
    public SessionEventsState get(final AccessTokenAppIdPair accessTokenAppIdPair) {
        synchronized (this) {
            return this.stateMap.get(accessTokenAppIdPair);
        }
    }
    
    public int getEventCount() {
        // monitorenter(this)
        int n = 0;
        try {
            final Iterator<SessionEventsState> iterator = this.stateMap.values().iterator();
            while (iterator.hasNext()) {
                n += iterator.next().getAccumulatedEventCount();
            }
            return n;
        }
        finally {
        }
        // monitorexit(this)
    }
    
    public Set<AccessTokenAppIdPair> keySet() {
        synchronized (this) {
            return this.stateMap.keySet();
        }
    }
}
