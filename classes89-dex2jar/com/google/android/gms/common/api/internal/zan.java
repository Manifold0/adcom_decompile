// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.support.annotation.MainThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import android.content.DialogInterface$OnCancelListener;
import android.content.Context;
import com.google.android.gms.common.api.GoogleApiActivity;

final class zan implements Runnable
{
    private final zam zadj;
    final /* synthetic */ zal zadk;
    
    zan(final zal zadk, final zam zadj) {
        this.zadk = zadk;
        this.zadj = zadj;
    }
    
    @MainThread
    @Override
    public final void run() {
        if (!this.zadk.mStarted) {
            return;
        }
        final ConnectionResult connectionResult = this.zadj.getConnectionResult();
        if (connectionResult.hasResolution()) {
            this.zadk.mLifecycleFragment.startActivityForResult(GoogleApiActivity.zaa((Context)this.zadk.getActivity(), connectionResult.getResolution(), this.zadj.zar(), false), 1);
            return;
        }
        if (this.zadk.zacd.isUserResolvableError(connectionResult.getErrorCode())) {
            this.zadk.zacd.zaa(this.zadk.getActivity(), this.zadk.mLifecycleFragment, connectionResult.getErrorCode(), 2, (DialogInterface$OnCancelListener)this.zadk);
            return;
        }
        if (connectionResult.getErrorCode() == 18) {
            this.zadk.zacd.zaa(this.zadk.getActivity().getApplicationContext(), new zao(this, GoogleApiAvailability.zaa(this.zadk.getActivity(), (DialogInterface$OnCancelListener)this.zadk)));
            return;
        }
        this.zadk.zaa(connectionResult, this.zadj.zar());
    }
}
