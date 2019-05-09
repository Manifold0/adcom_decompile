// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.environment;

import android.content.Intent;
import android.net.NetworkInfo;
import android.content.Context;
import android.net.ConnectivityManager;
import android.content.BroadcastReceiver;

public class NetworkStateReceiver extends BroadcastReceiver
{
    private boolean mConnected;
    private NetworkStateReceiverListener mListener;
    private ConnectivityManager mManager;
    
    public NetworkStateReceiver(final Context context, final NetworkStateReceiverListener mListener) {
        this.mListener = mListener;
        this.mManager = (ConnectivityManager)context.getSystemService("connectivity");
        this.checkAndSetState();
    }
    
    private boolean checkAndSetState() {
        final boolean mConnected = this.mConnected;
        final NetworkInfo activeNetworkInfo = this.mManager.getActiveNetworkInfo();
        this.mConnected = (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting());
        return mConnected != this.mConnected;
    }
    
    private void notifyState() {
        if (this.mListener != null) {
            if (!this.mConnected) {
                this.mListener.onNetworkAvailabilityChanged(false);
                return;
            }
            this.mListener.onNetworkAvailabilityChanged(true);
        }
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (intent != null && intent.getExtras() != null && this.checkAndSetState()) {
            this.notifyState();
        }
    }
    
    public interface NetworkStateReceiverListener
    {
        void onNetworkAvailabilityChanged(final boolean p0);
    }
}
