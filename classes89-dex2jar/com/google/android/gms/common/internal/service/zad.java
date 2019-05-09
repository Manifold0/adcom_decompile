// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal.service;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;

public final class zad implements zac
{
    @Override
    public final PendingResult<Status> zaa(final GoogleApiClient googleApiClient) {
        return googleApiClient.execute((PendingResult<Status>)new zae(this, googleApiClient));
    }
}
