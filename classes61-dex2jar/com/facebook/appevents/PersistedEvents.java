// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.appevents;

import java.util.Set;
import java.util.Collection;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.io.Serializable;

class PersistedEvents implements Serializable
{
    private static final long serialVersionUID = 20160629001L;
    private HashMap<AccessTokenAppIdPair, List<AppEvent>> events;
    
    public PersistedEvents() {
        this.events = new HashMap<AccessTokenAppIdPair, List<AppEvent>>();
    }
    
    public PersistedEvents(final HashMap<AccessTokenAppIdPair, List<AppEvent>> hashMap) {
        (this.events = new HashMap<AccessTokenAppIdPair, List<AppEvent>>()).putAll(hashMap);
    }
    
    private Object writeReplace() {
        return new SerializationProxyV1((HashMap)this.events);
    }
    
    public void addEvents(final AccessTokenAppIdPair accessTokenAppIdPair, final List<AppEvent> list) {
        if (!this.events.containsKey(accessTokenAppIdPair)) {
            this.events.put(accessTokenAppIdPair, list);
            return;
        }
        this.events.get(accessTokenAppIdPair).addAll(list);
    }
    
    public boolean containsKey(final AccessTokenAppIdPair accessTokenAppIdPair) {
        return this.events.containsKey(accessTokenAppIdPair);
    }
    
    public List<AppEvent> get(final AccessTokenAppIdPair accessTokenAppIdPair) {
        return this.events.get(accessTokenAppIdPair);
    }
    
    public Set<AccessTokenAppIdPair> keySet() {
        return this.events.keySet();
    }
    
    static class SerializationProxyV1 implements Serializable
    {
        private static final long serialVersionUID = 20160629001L;
        private final HashMap<AccessTokenAppIdPair, List<AppEvent>> proxyEvents;
        
        private SerializationProxyV1(final HashMap<AccessTokenAppIdPair, List<AppEvent>> proxyEvents) {
            this.proxyEvents = proxyEvents;
        }
        
        private Object readResolve() {
            return new PersistedEvents(this.proxyEvents);
        }
    }
}
