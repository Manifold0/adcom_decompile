package com.unity3d.services.core.configuration;

import com.unity3d.services.core.api.Broadcast;
import com.unity3d.services.core.api.Cache;
import com.unity3d.services.core.api.Connectivity;
import com.unity3d.services.core.api.DeviceInfo;
import com.unity3d.services.core.api.Intent;
import com.unity3d.services.core.api.Lifecycle;
import com.unity3d.services.core.api.Permissions;
import com.unity3d.services.core.api.Preferences;
import com.unity3d.services.core.api.Request;
import com.unity3d.services.core.api.Resolve;
import com.unity3d.services.core.api.Sdk;
import com.unity3d.services.core.api.SensorInfo;
import com.unity3d.services.core.api.Storage;
import com.unity3d.services.core.broadcast.BroadcastMonitor;
import com.unity3d.services.core.cache.CacheThread;
import com.unity3d.services.core.connectivity.ConnectivityMonitor;
import com.unity3d.services.core.device.AdvertisingId;
import com.unity3d.services.core.device.StorageManager;
import com.unity3d.services.core.device.VolumeChange;
import com.unity3d.services.core.properties.ClientProperties;
import com.unity3d.services.core.request.WebRequestThread;

public class CoreModuleConfiguration implements IModuleConfiguration {
    public Class[] getWebAppApiClassList() {
        return new Class[]{Broadcast.class, Cache.class, Connectivity.class, DeviceInfo.class, Storage.class, Sdk.class, Request.class, Resolve.class, Intent.class, Lifecycle.class, Preferences.class, SensorInfo.class, Permissions.class};
    }

    public boolean resetState(Configuration configuration) {
        BroadcastMonitor.removeAllBroadcastListeners();
        CacheThread.cancel();
        WebRequestThread.cancel();
        ConnectivityMonitor.stopAll();
        StorageManager.init(ClientProperties.getApplicationContext());
        AdvertisingId.init(ClientProperties.getApplicationContext());
        VolumeChange.clearAllListeners();
        return true;
    }

    public boolean initModuleState(Configuration configuration) {
        return true;
    }

    public boolean initErrorState(Configuration configuration, String state, String errorMessage) {
        return true;
    }

    public boolean initCompleteState(Configuration configuration) {
        return true;
    }
}
