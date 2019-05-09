// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.signin.internal;

import android.content.Intent;
import android.os.IInterface;
import android.os.IBinder;
import java.util.Iterator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions$Builder;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.internal.ClientSettings;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.internal.GmsClient;

public final class zzg extends GmsClient<zzu>
{
    private final GoogleSignInOptions zzbi;
    
    public zzg(final Context context, final Looper looper, final ClientSettings clientSettings, GoogleSignInOptions build, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        super(context, looper, 91, clientSettings, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener);
        if (build == null) {
            build = new GoogleSignInOptions$Builder().build();
        }
        GoogleSignInOptions build2 = build;
        if (!clientSettings.getAllRequestedScopes().isEmpty()) {
            final GoogleSignInOptions$Builder googleSignInOptions$Builder = new GoogleSignInOptions$Builder(build);
            final Iterator<Scope> iterator = (Iterator<Scope>)clientSettings.getAllRequestedScopes().iterator();
            while (iterator.hasNext()) {
                googleSignInOptions$Builder.requestScopes((Scope)iterator.next(), new Scope[0]);
            }
            build2 = googleSignInOptions$Builder.build();
        }
        this.zzbi = build2;
    }
    
    public final int getMinApkVersion() {
        return 12451000;
    }
    
    protected final String getServiceDescriptor() {
        return "com.google.android.gms.auth.api.signin.internal.ISignInService";
    }
    
    public final Intent getSignInIntent() {
        return zzh.zzc(this.getContext(), this.zzbi);
    }
    
    protected final String getStartServiceAction() {
        return "com.google.android.gms.auth.api.signin.service.START";
    }
    
    public final boolean providesSignIn() {
        return true;
    }
    
    public final GoogleSignInOptions zzg() {
        return this.zzbi;
    }
}
