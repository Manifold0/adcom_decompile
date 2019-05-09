package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.SimpleClientAdapter;

public final class zaah implements zabd {
    private final zabe zaft;
    private boolean zafu = false;

    public zaah(zabe zabe) {
        this.zaft = zabe;
    }

    public final void begin() {
    }

    public final <A extends AnyClient, R extends Result, T extends ApiMethodImpl<R, A>> T enqueue(T t) {
        return execute(t);
    }

    public final <A extends AnyClient, T extends ApiMethodImpl<? extends Result, A>> T execute(T t) {
        try {
            this.zaft.zaee.zahf.zab(t);
            zaaw zaaw = this.zaft.zaee;
            AnyClient anyClient = (Client) zaaw.zagz.get(t.getClientKey());
            Preconditions.checkNotNull(anyClient, "Appropriate Api was not requested.");
            if (anyClient.isConnected() || !this.zaft.zahp.containsKey(t.getClientKey())) {
                if (anyClient instanceof SimpleClientAdapter) {
                    anyClient = ((SimpleClientAdapter) anyClient).getClient();
                }
                t.run(anyClient);
                return t;
            }
            t.setFailedResult(new Status(17));
            return t;
        } catch (DeadObjectException e) {
            this.zaft.zaa(new zaai(this, this));
        }
    }

    public final boolean disconnect() {
        if (this.zafu) {
            return false;
        }
        if (this.zaft.zaee.zaax()) {
            this.zafu = true;
            for (zacm zabv : this.zaft.zaee.zahe) {
                zabv.zabv();
            }
            return false;
        }
        this.zaft.zaf(null);
        return true;
    }

    public final void connect() {
        if (this.zafu) {
            this.zafu = false;
            this.zaft.zaa(new zaaj(this, this));
        }
    }

    public final void onConnected(Bundle bundle) {
    }

    public final void zaa(ConnectionResult connectionResult, Api<?> api, boolean z) {
    }

    public final void onConnectionSuspended(int i) {
        this.zaft.zaf(null);
        this.zaft.zaht.zab(i, this.zafu);
    }

    final void zaam() {
        if (this.zafu) {
            this.zafu = false;
            this.zaft.zaee.zahf.release();
            disconnect();
        }
    }
}
