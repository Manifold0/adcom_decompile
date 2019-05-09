package com.google.firebase.components;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
final class zzd implements zze<Context> {
    private zzd() {
    }

    public final /* synthetic */ List zza(Object obj) {
        Bundle zza = zza((Context) obj);
        if (zza == null) {
            Log.w("ComponentDiscovery", "Could not retrieve metadata, returning empty list of registrars.");
            return Collections.emptyList();
        }
        List arrayList = new ArrayList();
        for (String str : zza.keySet()) {
            if ("com.google.firebase.components.ComponentRegistrar".equals(zza.get(str)) && str.startsWith("com.google.firebase.components:")) {
                arrayList.add(str.substring(31));
            }
        }
        return arrayList;
    }

    private static Bundle zza(Context context) {
        Bundle bundle = null;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                Log.w("ComponentDiscovery", "Context has no PackageManager.");
            } else {
                ServiceInfo serviceInfo = packageManager.getServiceInfo(new ComponentName(context, ComponentDiscoveryService.class), 128);
                if (serviceInfo == null) {
                    Log.w("ComponentDiscovery", "ComponentDiscoveryService has no service info.");
                } else {
                    bundle = serviceInfo.metaData;
                }
            }
        } catch (NameNotFoundException e) {
            Log.w("ComponentDiscovery", "Application info not found.");
        }
        return bundle;
    }
}
