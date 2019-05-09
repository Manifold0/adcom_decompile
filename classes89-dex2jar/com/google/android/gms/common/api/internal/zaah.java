// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.common.internal.SimpleClientAdapter;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Api;
import java.util.Iterator;
import com.google.android.gms.common.ConnectionResult;

public final class zaah implements zabd
{
    private final zabe zaft;
    private boolean zafu;
    
    public zaah(final zabe zaft) {
        this.zafu = false;
        this.zaft = zaft;
    }
    
    @Override
    public final void begin() {
    }
    
    @Override
    public final void connect() {
        if (this.zafu) {
            this.zafu = false;
            this.zaft.zaa(new zaaj(this, this));
        }
    }
    
    @Override
    public final boolean disconnect() {
        if (this.zafu) {
            return false;
        }
        if (this.zaft.zaee.zaax()) {
            this.zafu = true;
            final Iterator<zacm> iterator = this.zaft.zaee.zahe.iterator();
            while (iterator.hasNext()) {
                iterator.next().zabv();
            }
            return false;
        }
        this.zaft.zaf(null);
        return true;
    }
    
    @Override
    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(final T t) {
        return this.execute(t);
    }
    
    @Override
    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(final T t) {
        try {
            this.zaft.zaee.zahf.zab(t);
            final Api.Client client = this.zaft.zaee.zagz.get(t.getClientKey());
            Preconditions.checkNotNull((Object)client, (Object)"Appropriate Api was not requested.");
            if (!client.isConnected() && this.zaft.zahp.containsKey(t.getClientKey())) {
                ((BaseImplementation.ApiMethodImpl)t).setFailedResult(new Status(17));
                return t;
            }
            Api.AnyClient client2 = client;
            if (client instanceof SimpleClientAdapter) {
                client2 = ((SimpleClientAdapter)client).getClient();
            }
            ((BaseImplementation.ApiMethodImpl<R, A>)t).run((A)client2);
            return t;
        }
        catch (DeadObjectException ex) {
            this.zaft.zaa(new zaai(this, this));
            return t;
        }
    }
    
    @Override
    public final void onConnected(final Bundle bundle) {
    }
    
    @Override
    public final void onConnectionSuspended(final int n) {
        this.zaft.zaf(null);
        this.zaft.zaht.zab(n, this.zafu);
    }
    
    @Override
    public final void zaa(final ConnectionResult connectionResult, final Api<?> api, final boolean b) {
    }
    
    final void zaam() {
        if (this.zafu) {
            this.zafu = false;
            this.zaft.zaee.zahf.release();
            this.disconnect();
        }
    }
}
