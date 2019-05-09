// 
// Decompiled by Procyon v0.5.34
// 

package android.support.customtabs;

import android.os.IBinder;
import android.content.Intent;
import android.os.RemoteException;
import android.os.Bundle;
import android.app.Service;

public class PostMessageService extends Service
{
    private IPostMessageService.Stub mBinder;
    
    public PostMessageService() {
        this.mBinder = new IPostMessageService.Stub() {
            public void onMessageChannelReady(final ICustomTabsCallback customTabsCallback, final Bundle bundle) throws RemoteException {
                customTabsCallback.onMessageChannelReady(bundle);
            }
            
            public void onPostMessage(final ICustomTabsCallback customTabsCallback, final String s, final Bundle bundle) throws RemoteException {
                customTabsCallback.onPostMessage(s, bundle);
            }
        };
    }
    
    public IBinder onBind(final Intent intent) {
        return (IBinder)this.mBinder;
    }
}
