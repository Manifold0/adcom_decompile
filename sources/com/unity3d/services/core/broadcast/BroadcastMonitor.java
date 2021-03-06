package com.unity3d.services.core.broadcast;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import com.unity3d.services.core.properties.ClientProperties;
import java.util.HashMap;
import java.util.Map;

public class BroadcastMonitor {
    private static Map<String, BroadcastEventReceiver> _eventReceivers;

    public static void addBroadcastListener(String name, String dataScheme, String[] actions) {
        removeBroadcastListener(name);
        IntentFilter filter = new IntentFilter();
        for (String action : actions) {
            filter.addAction(action);
        }
        if (dataScheme != null) {
            filter.addDataScheme(dataScheme);
        }
        if (_eventReceivers == null) {
            _eventReceivers = new HashMap();
        }
        BroadcastEventReceiver eventReceiver = new BroadcastEventReceiver(name);
        _eventReceivers.put(name, eventReceiver);
        ClientProperties.getApplicationContext().registerReceiver(eventReceiver, filter);
    }

    public static void removeBroadcastListener(String name) {
        if (_eventReceivers != null && _eventReceivers.containsKey(name)) {
            ClientProperties.getApplicationContext().unregisterReceiver((BroadcastReceiver) _eventReceivers.get(name));
            _eventReceivers.remove(name);
        }
    }

    public static void removeAllBroadcastListeners() {
        if (_eventReceivers != null) {
            for (String key : _eventReceivers.keySet()) {
                ClientProperties.getApplicationContext().unregisterReceiver((BroadcastReceiver) _eventReceivers.get(key));
            }
            _eventReceivers = null;
        }
    }
}
