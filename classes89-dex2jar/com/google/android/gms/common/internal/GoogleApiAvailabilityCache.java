// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.Api;
import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import android.util.SparseIntArray;

public class GoogleApiAvailabilityCache
{
    private final SparseIntArray zaos;
    private GoogleApiAvailabilityLight zaot;
    
    public GoogleApiAvailabilityCache() {
        this(GoogleApiAvailability.getInstance());
    }
    
    public GoogleApiAvailabilityCache(@NonNull final GoogleApiAvailabilityLight zaot) {
        this.zaos = new SparseIntArray();
        Preconditions.checkNotNull((Object)zaot);
        this.zaot = zaot;
    }
    
    public void flush() {
        this.zaos.clear();
    }
    
    public int getClientAvailability(@NonNull final Context context, @NonNull final Api.Client client) {
        Preconditions.checkNotNull((Object)context);
        Preconditions.checkNotNull((Object)client);
        if (!client.requiresGooglePlayServices()) {
            return 0;
        }
        final int minApkVersion = client.getMinApkVersion();
        final int value = this.zaos.get(minApkVersion, -1);
        if (value != -1) {
            return value;
        }
        while (true) {
            for (int i = 0; i < this.zaos.size(); ++i) {
                final int key = this.zaos.keyAt(i);
                if (key > minApkVersion && this.zaos.get(key) == 0) {
                    final int n = 0;
                    int googlePlayServicesAvailable = n;
                    if (n == -1) {
                        googlePlayServicesAvailable = this.zaot.isGooglePlayServicesAvailable(context, minApkVersion);
                    }
                    this.zaos.put(minApkVersion, googlePlayServicesAvailable);
                    return googlePlayServicesAvailable;
                }
            }
            final int n = value;
            continue;
        }
    }
}
