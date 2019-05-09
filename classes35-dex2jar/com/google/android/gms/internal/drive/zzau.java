// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Api$AnyClientKey;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.common.api.Result;

public abstract class zzau<R extends Result> extends BaseImplementation$ApiMethodImpl<R, zzaw>
{
    public zzau(final GoogleApiClient googleApiClient) {
        super((Api$AnyClientKey)Drive.CLIENT_KEY, googleApiClient);
    }
}
