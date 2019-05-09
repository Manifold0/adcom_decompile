// 
// Decompiled by Procyon v0.5.34
// 

package android.support.customtabs;

import android.os.IBinder;
import android.content.ComponentName;
import android.os.RemoteException;
import android.os.Bundle;
import android.content.Intent;
import android.content.Context;
import android.content.ServiceConnection;

public abstract class PostMessageServiceConnection implements ServiceConnection
{
    private final Object mLock;
    private IPostMessageService mService;
    private final ICustomTabsCallback mSessionBinder;
    
    public PostMessageServiceConnection(final CustomTabsSessionToken customTabsSessionToken) {
        this.mLock = new Object();
        this.mSessionBinder = ICustomTabsCallback.Stub.asInterface(customTabsSessionToken.getCallbackBinder());
    }
    
    public boolean bindSessionToPostMessageService(final Context context, final String s) {
        final Intent intent = new Intent();
        intent.setClassName(s, PostMessageService.class.getName());
        return context.bindService(intent, (ServiceConnection)this, 1);
    }
    
    public final boolean notifyMessageChannelReady(final Bundle bundle) {
        if (this.mService == null) {
            return false;
        }
        synchronized (this.mLock) {
            try {
                this.mService.onMessageChannelReady(this.mSessionBinder, bundle);
                return true;
            }
            catch (RemoteException ex) {
                return false;
            }
        }
    }
    
    public void onPostMessageServiceConnected() {
    }
    
    public void onPostMessageServiceDisconnected() {
    }
    
    public final void onServiceConnected(final ComponentName componentName, final IBinder binder) {
        this.mService = IPostMessageService.Stub.asInterface(binder);
        this.onPostMessageServiceConnected();
    }
    
    public final void onServiceDisconnected(final ComponentName componentName) {
        this.mService = null;
        this.onPostMessageServiceDisconnected();
    }
    
    public final boolean postMessage(final String s, final Bundle bundle) {
        if (this.mService == null) {
            return false;
        }
        synchronized (this.mLock) {
            try {
                this.mService.onPostMessage(this.mSessionBinder, s, bundle);
                return true;
            }
            catch (RemoteException ex) {
                return false;
            }
        }
    }
    
    public void unbindFromContext(final Context context) {
        context.unbindService((ServiceConnection)this);
    }
}
