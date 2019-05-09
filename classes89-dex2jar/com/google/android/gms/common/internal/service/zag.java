// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal.service;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.Result;

abstract class zag<R extends Result> extends ApiMethodImpl<R, zai>
{
    public zag(final GoogleApiClient googleApiClient) {
        super(Common.API, googleApiClient);
    }
}
