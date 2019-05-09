// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.broadcast;

import java.util.Iterator;
import android.content.BroadcastReceiver;
import com.unity3d.services.core.properties.ClientProperties;
import java.util.HashMap;
import android.content.IntentFilter;
import java.util.Map;

public class BroadcastMonitor
{
    private static Map<String, BroadcastEventReceiver> _eventReceivers;
    
    public static void addBroadcastListener(final String s, final String s2, final String[] array) {
        removeBroadcastListener(s);
        final IntentFilter intentFilter = new IntentFilter();
        for (int length = array.length, i = 0; i < length; ++i) {
            intentFilter.addAction(array[i]);
        }
        if (s2 != null) {
            intentFilter.addDataScheme(s2);
        }
        if (BroadcastMonitor._eventReceivers == null) {
            BroadcastMonitor._eventReceivers = new HashMap<String, BroadcastEventReceiver>();
        }
        final BroadcastEventReceiver broadcastEventReceiver = new BroadcastEventReceiver(s);
        BroadcastMonitor._eventReceivers.put(s, broadcastEventReceiver);
        ClientProperties.getApplicationContext().registerReceiver((BroadcastReceiver)broadcastEventReceiver, intentFilter);
    }
    
    public static void removeAllBroadcastListeners() {
        if (BroadcastMonitor._eventReceivers != null) {
            final Iterator<String> iterator = BroadcastMonitor._eventReceivers.keySet().iterator();
            while (iterator.hasNext()) {
                ClientProperties.getApplicationContext().unregisterReceiver((BroadcastReceiver)BroadcastMonitor._eventReceivers.get(iterator.next()));
            }
            BroadcastMonitor._eventReceivers = null;
        }
    }
    
    public static void removeBroadcastListener(final String s) {
        if (BroadcastMonitor._eventReceivers != null && BroadcastMonitor._eventReceivers.containsKey(s)) {
            ClientProperties.getApplicationContext().unregisterReceiver((BroadcastReceiver)BroadcastMonitor._eventReceivers.get(s));
            BroadcastMonitor._eventReceivers.remove(s);
        }
    }
}
