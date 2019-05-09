package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.Api.AnyClientKey;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.ClientSettings.OptionalApiSettings;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.internal.zaj;
import com.google.android.gms.signin.zad;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import javax.annotation.concurrent.GuardedBy;

public final class zaak implements zabd {
    private final Context mContext;
    private final AbstractClientBuilder<? extends zad, SignInOptions> zace;
    private final Lock zaeo;
    private final ClientSettings zaet;
    private final Map<Api<?>, Boolean> zaew;
    private final GoogleApiAvailabilityLight zaey;
    private ConnectionResult zafh;
    private final zabe zaft;
    private int zafw;
    private int zafx = 0;
    private int zafy;
    private final Bundle zafz = new Bundle();
    private final Set<AnyClientKey> zaga = new HashSet();
    private zad zagb;
    private boolean zagc;
    private boolean zagd;
    private boolean zage;
    private IAccountAccessor zagf;
    private boolean zagg;
    private boolean zagh;
    private ArrayList<Future<?>> zagi = new ArrayList();

    public zaak(zabe zabe, ClientSettings clientSettings, Map<Api<?>, Boolean> map, GoogleApiAvailabilityLight googleApiAvailabilityLight, AbstractClientBuilder<? extends zad, SignInOptions> abstractClientBuilder, Lock lock, Context context) {
        this.zaft = zabe;
        this.zaet = clientSettings;
        this.zaew = map;
        this.zaey = googleApiAvailabilityLight;
        this.zace = abstractClientBuilder;
        this.zaeo = lock;
        this.mContext = context;
    }

    @GuardedBy("mLock")
    public final void begin() {
        this.zaft.zahp.clear();
        this.zagd = false;
        this.zafh = null;
        this.zafx = 0;
        this.zagc = true;
        this.zage = false;
        this.zagg = false;
        Map hashMap = new HashMap();
        int i = 0;
        for (Api api : this.zaew.keySet()) {
            Client client = (Client) this.zaft.zagz.get(api.getClientKey());
            int i2 = (api.zah().getPriority() == 1 ? 1 : 0) | i;
            boolean booleanValue = ((Boolean) this.zaew.get(api)).booleanValue();
            if (client.requiresSignIn()) {
                this.zagd = true;
                if (booleanValue) {
                    this.zaga.add(api.getClientKey());
                } else {
                    this.zagc = false;
                }
            }
            hashMap.put(client, new zaam(this, api, booleanValue));
            i = i2;
        }
        if (i != 0) {
            this.zagd = false;
        }
        if (this.zagd) {
            this.zaet.setClientSessionId(Integer.valueOf(System.identityHashCode(this.zaft.zaee)));
            ConnectionCallbacks zaat = new zaat();
            this.zagb = (zad) this.zace.buildClient(this.mContext, this.zaft.zaee.getLooper(), this.zaet, this.zaet.getSignInOptions(), zaat, zaat);
        }
        this.zafy = this.zaft.zagz.size();
        this.zagi.add(zabh.zabb().submit(new zaan(this, hashMap)));
    }

    @GuardedBy("mLock")
    private final boolean zaao() {
        this.zafy--;
        if (this.zafy > 0) {
            return false;
        }
        if (this.zafy < 0) {
            Log.w("GoogleApiClientConnecting", this.zaft.zaee.zaay());
            Log.wtf("GoogleApiClientConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.", new Exception());
            zae(new ConnectionResult(8, null));
            return false;
        } else if (this.zafh == null) {
            return true;
        } else {
            this.zaft.zahs = this.zafw;
            zae(this.zafh);
            return false;
        }
    }

    @GuardedBy("mLock")
    private final void zaa(zaj zaj) {
        if (zac(0)) {
            ConnectionResult connectionResult = zaj.getConnectionResult();
            if (connectionResult.isSuccess()) {
                ResolveAccountResponse zacx = zaj.zacx();
                ConnectionResult connectionResult2 = zacx.getConnectionResult();
                if (connectionResult2.isSuccess()) {
                    this.zage = true;
                    this.zagf = zacx.getAccountAccessor();
                    this.zagg = zacx.getSaveDefaultAccount();
                    this.zagh = zacx.isFromCrossClientAuth();
                    zaap();
                    return;
                }
                String valueOf = String.valueOf(connectionResult2);
                Log.wtf("GoogleApiClientConnecting", new StringBuilder(String.valueOf(valueOf).length() + 48).append("Sign-in succeeded with resolve account failure: ").append(valueOf).toString(), new Exception());
                zae(connectionResult2);
            } else if (zad(connectionResult)) {
                zaar();
                zaap();
            } else {
                zae(connectionResult);
            }
        }
    }

    @GuardedBy("mLock")
    private final void zaap() {
        if (this.zafy == 0) {
            if (!this.zagd || this.zage) {
                ArrayList arrayList = new ArrayList();
                this.zafx = 1;
                this.zafy = this.zaft.zagz.size();
                for (AnyClientKey anyClientKey : this.zaft.zagz.keySet()) {
                    if (!this.zaft.zahp.containsKey(anyClientKey)) {
                        arrayList.add((Client) this.zaft.zagz.get(anyClientKey));
                    } else if (zaao()) {
                        zaaq();
                    }
                }
                if (!arrayList.isEmpty()) {
                    this.zagi.add(zabh.zabb().submit(new zaaq(this, arrayList)));
                }
            }
        }
    }

    @GuardedBy("mLock")
    public final void onConnected(Bundle bundle) {
        if (zac(1)) {
            if (bundle != null) {
                this.zafz.putAll(bundle);
            }
            if (zaao()) {
                zaaq();
            }
        }
    }

    @GuardedBy("mLock")
    public final void zaa(ConnectionResult connectionResult, Api<?> api, boolean z) {
        if (zac(1)) {
            zab(connectionResult, api, z);
            if (zaao()) {
                zaaq();
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
            zab(false);
        }
        for (AnyClientKey anyClientKey : this.zaft.zahp.keySet()) {
            ((Client) this.zaft.zagz.get(anyClientKey)).disconnect();
        }
        this.zaft.zaht.zab(this.zafz.isEmpty() ? null : this.zafz);
    }

    public final <A extends AnyClient, R extends Result, T extends ApiMethodImpl<R, A>> T enqueue(T t) {
        this.zaft.zaee.zafc.add(t);
        return t;
    }

    public final <A extends AnyClient, T extends ApiMethodImpl<? extends Result, A>> T execute(T t) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }

    public final void connect() {
    }

    @GuardedBy("mLock")
    public final boolean disconnect() {
        zaas();
        zab(true);
        this.zaft.zaf(null);
        return true;
    }

    @GuardedBy("mLock")
    public final void onConnectionSuspended(int i) {
        zae(new ConnectionResult(8, null));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @javax.annotation.concurrent.GuardedBy("mLock")
    private final void zab(com.google.android.gms.common.ConnectionResult r6, com.google.android.gms.common.api.Api<?> r7, boolean r8) {
        /*
        r5 = this;
        r1 = 0;
        r0 = 1;
        r2 = r7.zah();
        r3 = r2.getPriority();
        if (r8 == 0) goto L_0x0015;
    L_0x000c:
        r2 = r6.hasResolution();
        if (r2 == 0) goto L_0x002f;
    L_0x0012:
        r2 = r0;
    L_0x0013:
        if (r2 == 0) goto L_0x003f;
    L_0x0015:
        r2 = r5.zafh;
        if (r2 == 0) goto L_0x001d;
    L_0x0019:
        r2 = r5.zafw;
        if (r3 >= r2) goto L_0x003f;
    L_0x001d:
        if (r0 == 0) goto L_0x0023;
    L_0x001f:
        r5.zafh = r6;
        r5.zafw = r3;
    L_0x0023:
        r0 = r5.zaft;
        r0 = r0.zahp;
        r1 = r7.getClientKey();
        r0.put(r1, r6);
        return;
    L_0x002f:
        r2 = r5.zaey;
        r4 = r6.getErrorCode();
        r2 = r2.getErrorResolutionIntent(r4);
        if (r2 == 0) goto L_0x003d;
    L_0x003b:
        r2 = r0;
        goto L_0x0013;
    L_0x003d:
        r2 = r1;
        goto L_0x0013;
    L_0x003f:
        r0 = r1;
        goto L_0x001d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zaak.zab(com.google.android.gms.common.ConnectionResult, com.google.android.gms.common.api.Api, boolean):void");
    }

    @GuardedBy("mLock")
    private final void zaar() {
        this.zagd = false;
        this.zaft.zaee.zaha = Collections.emptySet();
        for (AnyClientKey anyClientKey : this.zaga) {
            if (!this.zaft.zahp.containsKey(anyClientKey)) {
                this.zaft.zahp.put(anyClientKey, new ConnectionResult(17, null));
            }
        }
    }

    @GuardedBy("mLock")
    private final boolean zad(ConnectionResult connectionResult) {
        return this.zagc && !connectionResult.hasResolution();
    }

    @GuardedBy("mLock")
    private final void zae(ConnectionResult connectionResult) {
        zaas();
        zab(!connectionResult.hasResolution());
        this.zaft.zaf(connectionResult);
        this.zaft.zaht.zac(connectionResult);
    }

    @GuardedBy("mLock")
    private final void zab(boolean z) {
        if (this.zagb != null) {
            if (this.zagb.isConnected() && z) {
                this.zagb.zacw();
            }
            this.zagb.disconnect();
            if (this.zaet.isSignInClientDisconnectFixEnabled()) {
                this.zagb = null;
            }
            this.zagf = null;
        }
    }

    private final void zaas() {
        ArrayList arrayList = this.zagi;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            ((Future) obj).cancel(true);
        }
        this.zagi.clear();
    }

    private final Set<Scope> zaat() {
        if (this.zaet == null) {
            return Collections.emptySet();
        }
        Set<Scope> hashSet = new HashSet(this.zaet.getRequiredScopes());
        Map optionalApiSettings = this.zaet.getOptionalApiSettings();
        for (Api api : optionalApiSettings.keySet()) {
            if (!this.zaft.zahp.containsKey(api.getClientKey())) {
                hashSet.addAll(((OptionalApiSettings) optionalApiSettings.get(api)).mScopes);
            }
        }
        return hashSet;
    }

    @GuardedBy("mLock")
    private final boolean zac(int i) {
        if (this.zafx == i) {
            return true;
        }
        Log.w("GoogleApiClientConnecting", this.zaft.zaee.zaay());
        String valueOf = String.valueOf(this);
        Log.w("GoogleApiClientConnecting", new StringBuilder(String.valueOf(valueOf).length() + 23).append("Unexpected callback in ").append(valueOf).toString());
        Log.w("GoogleApiClientConnecting", "mRemainingConnections=" + this.zafy);
        valueOf = zad(this.zafx);
        String zad = zad(i);
        Log.wtf("GoogleApiClientConnecting", new StringBuilder((String.valueOf(valueOf).length() + 70) + String.valueOf(zad).length()).append("GoogleApiClient connecting is in step ").append(valueOf).append(" but received callback for step ").append(zad).toString(), new Exception());
        zae(new ConnectionResult(8, null));
        return false;
    }

    private static String zad(int i) {
        switch (i) {
            case 0:
                return "STEP_SERVICE_BINDINGS_AND_SIGN_IN";
            case 1:
                return "STEP_GETTING_REMOTE_SERVICE";
            default:
                return "UNKNOWN";
        }
    }
}
