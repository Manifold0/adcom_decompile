// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import com.google.android.gms.auth.api.signin.internal.Storage;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.ResultCallback;

final class zaba implements ResultCallback<Status>
{
    private final /* synthetic */ zaaw zahh;
    private final /* synthetic */ StatusPendingResult zahj;
    private final /* synthetic */ boolean zahk;
    private final /* synthetic */ GoogleApiClient zahl;
    
    zaba(final zaaw zahh, final StatusPendingResult zahj, final boolean zahk, final GoogleApiClient zahl) {
        this.zahh = zahh;
        this.zahj = zahj;
        this.zahk = zahk;
        this.zahl = zahl;
    }
}
