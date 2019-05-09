// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.content.Context;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.zad;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Api;

public final class zaw<O extends Api.ApiOptions> extends GoogleApi<O>
{
    private final Api.AbstractClientBuilder<? extends zad, SignInOptions> zace;
    private final Api.Client zaer;
    private final zaq zaes;
    private final ClientSettings zaet;
    
    public zaw(@NonNull final Context context, final Api<O> api, final Looper looper, @NonNull final Api.Client zaer, @NonNull final zaq zaes, final ClientSettings zaet, final Api.AbstractClientBuilder<? extends zad, SignInOptions> zace) {
        super(context, api, looper);
        this.zaer = zaer;
        this.zaes = zaes;
        this.zaet = zaet;
        this.zace = zace;
        this.zabm.zaa(this);
    }
    
    @Override
    public final Api.Client zaa(final Looper looper, final GoogleApiManager.zaa<O> zaa) {
        this.zaes.zaa(zaa);
        return this.zaer;
    }
    
    @Override
    public final zace zaa(final Context context, final Handler handler) {
        return new zace(context, handler, this.zaet, this.zace);
    }
    
    public final Api.Client zaab() {
        return this.zaer;
    }
}
