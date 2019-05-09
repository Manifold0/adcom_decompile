package com.google.android.gms.common.api.internal;

import android.support.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.internal.BaseGmsClient.ConnectionProgressReportCallbacks;
import com.google.android.gms.common.internal.GoogleApiAvailabilityCache;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;

final class zaan extends zaau {
    final /* synthetic */ zaak zagj;
    private final Map<Client, zaam> zagl;

    public zaan(zaak zaak, Map<Client, zaam> map) {
        this.zagj = zaak;
        super(zaak);
        this.zagl = map;
    }

    @WorkerThread
    @GuardedBy("mLock")
    public final void zaan() {
        int i = 0;
        GoogleApiAvailabilityCache googleApiAvailabilityCache = new GoogleApiAvailabilityCache(this.zagj.zaey);
        List arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        for (Client client : this.zagl.keySet()) {
            if (!client.requiresGooglePlayServices() || ((zaam) this.zagl.get(client)).zaec) {
                arrayList2.add(client);
            } else {
                arrayList.add(client);
            }
        }
        int i2 = -1;
        ArrayList arrayList3;
        int i3;
        if (!arrayList.isEmpty()) {
            arrayList3 = (ArrayList) arrayList;
            int size = arrayList3.size();
            i3 = 0;
            while (i3 < size) {
                Object obj = arrayList3.get(i3);
                i3++;
                i2 = googleApiAvailabilityCache.getClientAvailability(this.zagj.mContext, (Client) obj);
                if (i2 != 0) {
                    break;
                }
            }
        }
        arrayList3 = (ArrayList) arrayList2;
        i3 = arrayList3.size();
        while (i < i3) {
            obj = arrayList3.get(i);
            i++;
            i2 = googleApiAvailabilityCache.getClientAvailability(this.zagj.mContext, (Client) obj);
            if (i2 == 0) {
                break;
            }
        }
        int i4 = i2;
        if (i4 != 0) {
            this.zagj.zaft.zaa(new zaao(this, this.zagj, new ConnectionResult(i4, null)));
            return;
        }
        if (this.zagj.zagd && this.zagj.zagb != null) {
            this.zagj.zagb.connect();
        }
        for (Client client2 : this.zagl.keySet()) {
            ConnectionProgressReportCallbacks connectionProgressReportCallbacks = (ConnectionProgressReportCallbacks) this.zagl.get(client2);
            if (!client2.requiresGooglePlayServices() || googleApiAvailabilityCache.getClientAvailability(this.zagj.mContext, client2) == 0) {
                client2.connect(connectionProgressReportCallbacks);
            } else {
                this.zagj.zaft.zaa(new zaap(this, this.zagj, connectionProgressReportCallbacks));
            }
        }
    }
}
