// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import javax.annotation.concurrent.GuardedBy;
import android.support.annotation.WorkerThread;
import java.util.Iterator;
import com.google.android.gms.common.internal.BaseGmsClient$ConnectionProgressReportCallbacks;
import android.app.PendingIntent;
import com.google.android.gms.common.ConnectionResult;
import java.util.ArrayList;
import com.google.android.gms.common.internal.GoogleApiAvailabilityCache;
import com.google.android.gms.common.api.Api;
import java.util.Map;

final class zaan extends zaau
{
    final /* synthetic */ zaak zagj;
    private final Map<Api.Client, zaam> zagl;
    
    public zaan(final zaak zagj, final Map<Api.Client, zaam> zagl) {
        this.zagj = zagj;
        super(zagj, null);
        this.zagl = zagl;
    }
    
    @WorkerThread
    @GuardedBy("mLock")
    public final void zaan() {
        int i = 0;
        final GoogleApiAvailabilityCache googleApiAvailabilityCache = new GoogleApiAvailabilityCache(this.zagj.zaey);
        final ArrayList<Api.Client> list = new ArrayList<Api.Client>();
        final ArrayList<Api.Client> list2 = new ArrayList<Api.Client>();
        for (final Api.Client client : this.zagl.keySet()) {
            if (client.requiresGooglePlayServices() && !this.zagl.get(client).zaec) {
                list.add(client);
            }
            else {
                list2.add(client);
            }
        }
        int n = -1;
        if (list.isEmpty()) {
            final ArrayList<Api.Client> list3 = list2;
            while (i < list3.size()) {
                final Api.Client value = list3.get(i);
                ++i;
                final int clientAvailability = googleApiAvailabilityCache.getClientAvailability(this.zagj.mContext, value);
                if ((n = clientAvailability) == 0) {
                    n = clientAvailability;
                    break;
                }
            }
        }
        else {
            final ArrayList<Api.Client> list4 = list;
            final int size = list4.size();
            int j = 0;
            while (j < size) {
                final Api.Client value2 = list4.get(j);
                ++j;
                final int clientAvailability2 = googleApiAvailabilityCache.getClientAvailability(this.zagj.mContext, value2);
                if ((n = clientAvailability2) != 0) {
                    n = clientAvailability2;
                    break;
                }
            }
        }
        if (n != 0) {
            this.zagj.zaft.zaa(new zaao(this, this.zagj, new ConnectionResult(n, (PendingIntent)null)));
        }
        else {
            if (this.zagj.zagd && this.zagj.zagb != null) {
                this.zagj.zagb.connect();
            }
            for (final Api.Client client2 : this.zagl.keySet()) {
                final BaseGmsClient$ConnectionProgressReportCallbacks baseGmsClient$ConnectionProgressReportCallbacks = (BaseGmsClient$ConnectionProgressReportCallbacks)this.zagl.get(client2);
                if (client2.requiresGooglePlayServices() && googleApiAvailabilityCache.getClientAvailability(this.zagj.mContext, client2) != 0) {
                    this.zagj.zaft.zaa(new zaap(this, this.zagj, baseGmsClient$ConnectionProgressReportCallbacks));
                }
                else {
                    client2.connect(baseGmsClient$ConnectionProgressReportCallbacks);
                }
            }
        }
    }
}
