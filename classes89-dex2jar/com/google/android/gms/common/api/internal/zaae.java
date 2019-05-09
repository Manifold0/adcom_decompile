// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.Preconditions;
import android.app.Activity;
import android.support.v4.util.ArraySet;

public class zaae extends zal
{
    private GoogleApiManager zabm;
    private final ArraySet<zai<?>> zafp;
    
    private zaae(final LifecycleFragment lifecycleFragment) {
        super(lifecycleFragment);
        this.zafp = (ArraySet<zai<?>>)new ArraySet();
        this.mLifecycleFragment.addCallback("ConnectionlessLifecycleHelper", (LifecycleCallback)this);
    }
    
    public static void zaa(final Activity activity, final GoogleApiManager zabm, final zai<?> zai) {
        final LifecycleFragment fragment = getFragment(activity);
        zaae zaae;
        if ((zaae = (zaae)fragment.getCallbackOrNull("ConnectionlessLifecycleHelper", (Class)zaae.class)) == null) {
            zaae = new zaae(fragment);
        }
        zaae.zabm = zabm;
        Preconditions.checkNotNull((Object)zai, (Object)"ApiKey cannot be null");
        zaae.zafp.add((Object)zai);
        zabm.zaa(zaae);
    }
    
    private final void zaak() {
        if (!this.zafp.isEmpty()) {
            this.zabm.zaa(this);
        }
    }
    
    public void onResume() {
        super.onResume();
        this.zaak();
    }
    
    @Override
    public void onStart() {
        super.onStart();
        this.zaak();
    }
    
    @Override
    public void onStop() {
        super.onStop();
        this.zabm.zab(this);
    }
    
    @Override
    protected final void zaa(final ConnectionResult connectionResult, final int n) {
        this.zabm.zaa(connectionResult, n);
    }
    
    final ArraySet<zai<?>> zaaj() {
        return this.zafp;
    }
    
    @Override
    protected final void zao() {
        this.zabm.zao();
    }
}
