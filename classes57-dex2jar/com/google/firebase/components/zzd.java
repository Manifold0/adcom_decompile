// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.components;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import android.content.pm.ServiceInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.ComponentName;
import android.util.Log;
import android.os.Bundle;
import android.content.Context;

final class zzd implements zze<Context>
{
    private zzd() {
    }
    
    private static Bundle zza(final Context context) {
        ServiceInfo serviceInfo;
        try {
            final PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                Log.w("ComponentDiscovery", "Context has no PackageManager.");
                return null;
            }
            serviceInfo = packageManager.getServiceInfo(new ComponentName(context, (Class)ComponentDiscoveryService.class), 128);
            if (serviceInfo == null) {
                Log.w("ComponentDiscovery", "ComponentDiscoveryService has no service info.");
                return null;
            }
        }
        catch (PackageManager$NameNotFoundException ex) {
            Log.w("ComponentDiscovery", "Application info not found.");
            return null;
        }
        return serviceInfo.metaData;
    }
}
