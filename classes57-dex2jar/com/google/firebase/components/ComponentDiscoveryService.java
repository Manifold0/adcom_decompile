// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.components;

import android.support.annotation.Nullable;
import android.os.IBinder;
import android.content.Intent;
import com.google.android.gms.common.annotation.KeepForSdk;
import android.app.Service;

@KeepForSdk
public class ComponentDiscoveryService extends Service
{
    @Nullable
    public IBinder onBind(final Intent intent) {
        return null;
    }
}
