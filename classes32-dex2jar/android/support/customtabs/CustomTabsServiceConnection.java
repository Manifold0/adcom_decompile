// 
// Decompiled by Procyon v0.5.34
// 

package android.support.customtabs;

import android.os.IBinder;
import android.content.ComponentName;
import android.content.ServiceConnection;

public abstract class CustomTabsServiceConnection implements ServiceConnection
{
    public abstract void onCustomTabsServiceConnected(final ComponentName p0, final CustomTabsClient p1);
    
    public final void onServiceConnected(final ComponentName componentName, final IBinder binder) {
        this.onCustomTabsServiceConnected(componentName, new CustomTabsClient(ICustomTabsService.Stub.asInterface(binder), componentName) {});
    }
}
