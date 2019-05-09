// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.signin.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import android.content.Context;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;

final class zzi extends zzo<GoogleSignInResult>
{
    final /* synthetic */ Context val$context;
    final /* synthetic */ GoogleSignInOptions zzbj;
    
    zzi(final GoogleApiClient googleApiClient, final Context val$context, final GoogleSignInOptions zzbj) {
        this.val$context = val$context;
        this.zzbj = zzbj;
        super(googleApiClient);
    }
}
