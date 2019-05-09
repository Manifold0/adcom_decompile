// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public final class PrivateEventManager
{
    public static final int EVENT_TYPE_UNCAUGHT_EXCEPTION = 1;
    private static List<HockeyEventListener> sEventListeners;
    
    static {
        PrivateEventManager.sEventListeners = new LinkedList<HockeyEventListener>();
    }
    
    public static void addEventListener(final HockeyEventListener hockeyEventListener) {
        PrivateEventManager.sEventListeners.add(hockeyEventListener);
    }
    
    static void postEvent(final Event event) {
        final Iterator<HockeyEventListener> iterator = PrivateEventManager.sEventListeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().onHockeyEvent(event);
        }
    }
    
    public static final class Event
    {
        private final int mType;
        
        protected Event(final int mType) {
            this.mType = mType;
        }
        
        public int getType() {
            return this.mType;
        }
    }
    
    public interface HockeyEventListener
    {
        void onHockeyEvent(final Event p0);
    }
}
