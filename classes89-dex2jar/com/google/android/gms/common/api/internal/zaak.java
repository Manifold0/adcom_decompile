// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.HashMap;
import java.util.Collection;
import com.google.android.gms.common.api.Scope;
import java.util.Collections;
import java.util.Iterator;
import android.app.PendingIntent;
import javax.annotation.concurrent.GuardedBy;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import android.util.Log;
import com.google.android.gms.signin.internal.zaj;
import java.util.HashSet;
import java.util.concurrent.Future;
import java.util.ArrayList;
import com.google.android.gms.common.internal.IAccountAccessor;
import java.util.Set;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import java.util.Map;
import com.google.android.gms.common.internal.ClientSettings;
import java.util.concurrent.locks.Lock;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.zad;
import com.google.android.gms.common.api.Api;
import android.content.Context;

public final class zaak implements zabd
{
    private final Context mContext;
    private final Api.AbstractClientBuilder<? extends zad, SignInOptions> zace;
    private final Lock zaeo;
    private final ClientSettings zaet;
    private final Map<Api<?>, Boolean> zaew;
    private final GoogleApiAvailabilityLight zaey;
    private ConnectionResult zafh;
    private final zabe zaft;
    private int zafw;
    private int zafx;
    private int zafy;
    private final Bundle zafz;
    private final Set<Api.AnyClientKey> zaga;
    private zad zagb;
    private boolean zagc;
    private boolean zagd;
    private boolean zage;
    private IAccountAccessor zagf;
    private boolean zagg;
    private boolean zagh;
    private ArrayList<Future<?>> zagi;
    
    public zaak(final zabe zaft, final ClientSettings zaet, final Map<Api<?>, Boolean> zaew, final GoogleApiAvailabilityLight zaey, final Api.AbstractClientBuilder<? extends zad, SignInOptions> zace, final Lock zaeo, final Context mContext) {
        this.zafx = 0;
        this.zafz = new Bundle();
        this.zaga = new HashSet<Api.AnyClientKey>();
        this.zagi = new ArrayList<Future<?>>();
        this.zaft = zaft;
        this.zaet = zaet;
        this.zaew = zaew;
        this.zaey = zaey;
        this.zace = zace;
        this.zaeo = zaeo;
        this.mContext = mContext;
    }
    
    @GuardedBy("mLock")
    private final void zaa(final zaj zaj) {
        if (!this.zac(0)) {
            return;
        }
        final ConnectionResult connectionResult = zaj.getConnectionResult();
        if (connectionResult.isSuccess()) {
            final ResolveAccountResponse zacx = zaj.zacx();
            final ConnectionResult connectionResult2 = zacx.getConnectionResult();
            if (!connectionResult2.isSuccess()) {
                final String value = String.valueOf(connectionResult2);
                Log.wtf("GoogleApiClientConnecting", new StringBuilder(String.valueOf(value).length() + 48).append("Sign-in succeeded with resolve account failure: ").append(value).toString(), (Throwable)new Exception());
                this.zae(connectionResult2);
                return;
            }
            this.zage = true;
            this.zagf = zacx.getAccountAccessor();
            this.zagg = zacx.getSaveDefaultAccount();
            this.zagh = zacx.isFromCrossClientAuth();
            this.zaap();
        }
        else {
            if (this.zad(connectionResult)) {
                this.zaar();
                this.zaap();
                return;
            }
            this.zae(connectionResult);
        }
    }
    
    @GuardedBy("mLock")
    private final boolean zaao() {
        --this.zafy;
        if (this.zafy > 0) {
            return false;
        }
        if (this.zafy < 0) {
            Log.w("GoogleApiClientConnecting", this.zaft.zaee.zaay());
            Log.wtf("GoogleApiClientConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.", (Throwable)new Exception());
            this.zae(new ConnectionResult(8, (PendingIntent)null));
            return false;
        }
        if (this.zafh != null) {
            this.zaft.zahs = this.zafw;
            this.zae(this.zafh);
            return false;
        }
        return true;
    }
    
    @GuardedBy("mLock")
    private final void zaap() {
        if (this.zafy == 0 && (!this.zagd || this.zage)) {
            final ArrayList<Api.Client> list = new ArrayList<Api.Client>();
            this.zafx = 1;
            this.zafy = this.zaft.zagz.size();
            for (final Api.AnyClientKey anyClientKey : this.zaft.zagz.keySet()) {
                if (this.zaft.zahp.containsKey(anyClientKey)) {
                    if (!this.zaao()) {
                        continue;
                    }
                    this.zaaq();
                }
                else {
                    list.add(this.zaft.zagz.get(anyClientKey));
                }
            }
            if (!list.isEmpty()) {
                this.zagi.add(zabh.zabb().submit(new zaaq(list)));
            }
        }
    }
    
    @GuardedBy("mLock")
    private final void zaaq() {
        this.zaft.zaba();
        zabh.zabb().execute(new zaal(this));
        if (this.zagb != null) {
            if (this.zagg) {
                this.zagb.zaa(this.zagf, this.zagh);
            }
            this.zab(false);
        }
        final Iterator<Api.AnyClientKey<?>> iterator = this.zaft.zahp.keySet().iterator();
        while (iterator.hasNext()) {
            this.zaft.zagz.get((Api.AnyClientKey)iterator.next()).disconnect();
        }
        Bundle zafz;
        if (this.zafz.isEmpty()) {
            zafz = null;
        }
        else {
            zafz = this.zafz;
        }
        this.zaft.zaht.zab(zafz);
    }
    
    @GuardedBy("mLock")
    private final void zaar() {
        this.zagd = false;
        this.zaft.zaee.zaha = Collections.emptySet();
        for (final Api.AnyClientKey anyClientKey : this.zaga) {
            if (!this.zaft.zahp.containsKey(anyClientKey)) {
                this.zaft.zahp.put(anyClientKey, new ConnectionResult(17, (PendingIntent)null));
            }
        }
    }
    
    private final void zaas() {
        final ArrayList<Future<?>> list = this.zagi;
        final int size = list.size();
        int i = 0;
        while (i < size) {
            final Future<?> value = list.get(i);
            ++i;
            value.cancel(true);
        }
        this.zagi.clear();
    }
    
    private final Set<Scope> zaat() {
        if (this.zaet == null) {
            return Collections.emptySet();
        }
        final HashSet<Object> set = (HashSet<Object>)new HashSet<Scope>(this.zaet.getRequiredScopes());
        final Map<Api<?>, ClientSettings.OptionalApiSettings> optionalApiSettings = this.zaet.getOptionalApiSettings();
        for (final Api<?> api : optionalApiSettings.keySet()) {
            if (!this.zaft.zahp.containsKey(api.getClientKey())) {
                set.addAll(((ClientSettings.OptionalApiSettings)optionalApiSettings.get(api)).mScopes);
            }
        }
        return (Set<Scope>)set;
    }
    
    @GuardedBy("mLock")
    private final void zab(final ConnectionResult zafh, final Api<?> api, final boolean b) {
        final boolean b2 = true;
        final int priority = api.zah().getPriority();
        while (true) {
            Label_0116: {
                if (b) {
                    int n;
                    if (zafh.hasResolution()) {
                        n = 1;
                    }
                    else if (this.zaey.getErrorResolutionIntent(zafh.getErrorCode()) != null) {
                        n = 1;
                    }
                    else {
                        n = 0;
                    }
                    if (n == 0) {
                        break Label_0116;
                    }
                }
                int n2 = b2 ? 1 : 0;
                if (this.zafh != null) {
                    if (priority >= this.zafw) {
                        break Label_0116;
                    }
                    n2 = (b2 ? 1 : 0);
                }
                if (n2 != 0) {
                    this.zafh = zafh;
                    this.zafw = priority;
                }
                this.zaft.zahp.put(api.getClientKey(), zafh);
                return;
            }
            int n2 = 0;
            continue;
        }
    }
    
    @GuardedBy("mLock")
    private final void zab(final boolean b) {
        if (this.zagb != null) {
            if (((Api.Client)this.zagb).isConnected() && b) {
                this.zagb.zacw();
            }
            ((Api.Client)this.zagb).disconnect();
            if (this.zaet.isSignInClientDisconnectFixEnabled()) {
                this.zagb = null;
            }
            this.zagf = null;
        }
    }
    
    @GuardedBy("mLock")
    private final boolean zac(final int n) {
        if (this.zafx != n) {
            Log.w("GoogleApiClientConnecting", this.zaft.zaee.zaay());
            final String value = String.valueOf(this);
            Log.w("GoogleApiClientConnecting", new StringBuilder(String.valueOf(value).length() + 23).append("Unexpected callback in ").append(value).toString());
            Log.w("GoogleApiClientConnecting", new StringBuilder(33).append("mRemainingConnections=").append(this.zafy).toString());
            final String zad = zad(this.zafx);
            final String zad2 = zad(n);
            Log.wtf("GoogleApiClientConnecting", new StringBuilder(String.valueOf(zad).length() + 70 + String.valueOf(zad2).length()).append("GoogleApiClient connecting is in step ").append(zad).append(" but received callback for step ").append(zad2).toString(), (Throwable)new Exception());
            this.zae(new ConnectionResult(8, (PendingIntent)null));
            return false;
        }
        return true;
    }
    
    private static String zad(final int n) {
        switch (n) {
            default: {
                return "UNKNOWN";
            }
            case 0: {
                return "STEP_SERVICE_BINDINGS_AND_SIGN_IN";
            }
            case 1: {
                return "STEP_GETTING_REMOTE_SERVICE";
            }
        }
    }
    
    @GuardedBy("mLock")
    private final boolean zad(final ConnectionResult connectionResult) {
        return this.zagc && !connectionResult.hasResolution();
    }
    
    @GuardedBy("mLock")
    private final void zae(final ConnectionResult connectionResult) {
        this.zaas();
        this.zab(!connectionResult.hasResolution());
        this.zaft.zaf(connectionResult);
        this.zaft.zaht.zac(connectionResult);
    }
    
    @GuardedBy("mLock")
    @Override
    public final void begin() {
        this.zaft.zahp.clear();
        this.zagd = false;
        this.zafh = null;
        this.zafx = 0;
        this.zagc = true;
        this.zage = false;
        this.zagg = false;
        final HashMap<Api.Client, zaam> hashMap = new HashMap<Api.Client, zaam>();
        final Iterator<Api<?>> iterator = this.zaew.keySet().iterator();
        int n = false ? 1 : 0;
        while (iterator.hasNext()) {
            final Api<?> api = iterator.next();
            final Api.Client client = this.zaft.zagz.get(api.getClientKey());
            boolean b;
            if (api.zah().getPriority() == 1) {
                b = true;
            }
            else {
                b = false;
            }
            final boolean booleanValue = this.zaew.get(api);
            if (client.requiresSignIn()) {
                this.zagd = true;
                if (booleanValue) {
                    this.zaga.add((Api.AnyClientKey)api.getClientKey());
                }
                else {
                    this.zagc = false;
                }
            }
            hashMap.put(client, new zaam(this, api, booleanValue));
            n |= (b ? 1 : 0);
        }
        if (n != 0) {
            this.zagd = false;
        }
        if (this.zagd) {
            this.zaet.setClientSessionId(System.identityHashCode(this.zaft.zaee));
            final zaat zaat = new zaat(this, null);
            this.zagb = (zad)this.zace.buildClient(this.mContext, this.zaft.zaee.getLooper(), this.zaet, this.zaet.getSignInOptions(), zaat, zaat);
        }
        this.zafy = this.zaft.zagz.size();
        this.zagi.add(zabh.zabb().submit(new zaan(hashMap)));
    }
    
    @Override
    public final void connect() {
    }
    
    @GuardedBy("mLock")
    @Override
    public final boolean disconnect() {
        this.zaas();
        this.zab(true);
        this.zaft.zaf(null);
        return true;
    }
    
    @Override
    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(final T t) {
        this.zaft.zaee.zafc.add(t);
        return t;
    }
    
    @Override
    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(final T t) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }
    
    @GuardedBy("mLock")
    @Override
    public final void onConnected(final Bundle bundle) {
        if (this.zac(1)) {
            if (bundle != null) {
                this.zafz.putAll(bundle);
            }
            if (this.zaao()) {
                this.zaaq();
            }
        }
    }
    
    @GuardedBy("mLock")
    @Override
    public final void onConnectionSuspended(final int n) {
        this.zae(new ConnectionResult(8, (PendingIntent)null));
    }
    
    @GuardedBy("mLock")
    @Override
    public final void zaa(final ConnectionResult connectionResult, final Api<?> api, final boolean b) {
        if (this.zac(1)) {
            this.zab(connectionResult, api, b);
            if (this.zaao()) {
                this.zaaq();
            }
        }
    }
}
