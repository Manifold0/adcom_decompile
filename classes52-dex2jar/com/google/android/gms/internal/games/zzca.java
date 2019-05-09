// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import com.google.android.gms.games.internal.zze;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.games.request.OnRequestReceivedListener;
import android.graphics.Bitmap;
import com.google.android.gms.games.Games;
import android.content.Intent;
import com.google.android.gms.games.request.GameRequest;
import android.os.Bundle;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import java.util.List;
import java.util.ArrayList;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.request.Requests;

public final class zzca implements Requests
{
    @Override
    public final PendingResult<UpdateRequestsResult> acceptRequest(final GoogleApiClient googleApiClient, final String s) {
        final ArrayList<String> list = new ArrayList<String>();
        list.add(s);
        return this.acceptRequests(googleApiClient, list);
    }
    
    @Override
    public final PendingResult<UpdateRequestsResult> acceptRequests(final GoogleApiClient googleApiClient, final List<String> list) {
        String[] array;
        if (list == null) {
            array = null;
        }
        else {
            array = list.toArray(new String[list.size()]);
        }
        return (PendingResult<UpdateRequestsResult>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzcb(this, googleApiClient, array));
    }
    
    @Override
    public final PendingResult<UpdateRequestsResult> dismissRequest(final GoogleApiClient googleApiClient, final String s) {
        final ArrayList<String> list = new ArrayList<String>();
        list.add(s);
        return this.dismissRequests(googleApiClient, list);
    }
    
    @Override
    public final PendingResult<UpdateRequestsResult> dismissRequests(final GoogleApiClient googleApiClient, final List<String> list) {
        String[] array;
        if (list == null) {
            array = null;
        }
        else {
            array = list.toArray(new String[list.size()]);
        }
        return (PendingResult<UpdateRequestsResult>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzcc(this, googleApiClient, array));
    }
    
    @Override
    public final ArrayList<GameRequest> getGameRequestsFromBundle(final Bundle bundle) {
        if (bundle == null || !bundle.containsKey("requests")) {
            return new ArrayList<GameRequest>();
        }
        final ArrayList list = (ArrayList)bundle.get("requests");
        final ArrayList<GameRequest> list2 = new ArrayList<GameRequest>();
        for (int size = list.size(), i = 0; i < size; ++i) {
            list2.add(list.get(i));
        }
        return list2;
    }
    
    @Override
    public final ArrayList<GameRequest> getGameRequestsFromInboxResponse(final Intent intent) {
        if (intent == null) {
            return new ArrayList<GameRequest>();
        }
        return this.getGameRequestsFromBundle(intent.getExtras());
    }
    
    @Override
    public final Intent getInboxIntent(final GoogleApiClient googleApiClient) {
        return Games.zza(googleApiClient).zzaq();
    }
    
    @Override
    public final int getMaxLifetimeDays(final GoogleApiClient googleApiClient) {
        return Games.zza(googleApiClient).zzas();
    }
    
    @Override
    public final int getMaxPayloadSize(final GoogleApiClient googleApiClient) {
        return Games.zza(googleApiClient).zzar();
    }
    
    @Override
    public final Intent getSendIntent(final GoogleApiClient googleApiClient, final int n, final byte[] array, final int n2, final Bitmap bitmap, final String s) {
        return Games.zza(googleApiClient).zza(n, array, n2, bitmap, s);
    }
    
    @Override
    public final PendingResult<LoadRequestsResult> loadRequests(final GoogleApiClient googleApiClient, final int n, final int n2, final int n3) {
        return (PendingResult<LoadRequestsResult>)googleApiClient.enqueue((BaseImplementation$ApiMethodImpl)new zzcd(this, googleApiClient, n, n2, n3));
    }
    
    @Override
    public final void registerRequestListener(final GoogleApiClient googleApiClient, final OnRequestReceivedListener onRequestReceivedListener) {
        final zze zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            zza.zzf((ListenerHolder<OnRequestReceivedListener>)googleApiClient.registerListener((Object)onRequestReceivedListener));
        }
    }
    
    @Override
    public final void unregisterRequestListener(final GoogleApiClient googleApiClient) {
        final zze zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            zza.zzaf();
        }
    }
}
