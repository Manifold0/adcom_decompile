// 
// Decompiled by Procyon v0.5.34
// 

package android.support.customtabs;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;
import android.content.Intent;
import java.util.NoSuchElementException;
import android.os.RemoteException;
import java.util.List;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.os.IBinder$DeathRecipient;
import android.os.IBinder;
import java.util.Map;
import android.app.Service;

public abstract class CustomTabsService extends Service
{
    public static final String ACTION_CUSTOM_TABS_CONNECTION = "android.support.customtabs.action.CustomTabsService";
    public static final String KEY_URL = "android.support.customtabs.otherurls.URL";
    public static final int RESULT_FAILURE_DISALLOWED = -1;
    public static final int RESULT_FAILURE_MESSAGING_ERROR = -3;
    public static final int RESULT_FAILURE_REMOTE_ERROR = -2;
    public static final int RESULT_SUCCESS = 0;
    private ICustomTabsService.Stub mBinder;
    private final Map<IBinder, IBinder$DeathRecipient> mDeathRecipientMap;
    
    public CustomTabsService() {
        this.mDeathRecipientMap = (Map<IBinder, IBinder$DeathRecipient>)new ArrayMap();
        this.mBinder = new ICustomTabsService.Stub() {
            public Bundle extraCommand(final String s, final Bundle bundle) {
                return CustomTabsService.this.extraCommand(s, bundle);
            }
            
            public boolean mayLaunchUrl(final ICustomTabsCallback customTabsCallback, final Uri uri, final Bundle bundle, final List<Bundle> list) {
                return CustomTabsService.this.mayLaunchUrl(new CustomTabsSessionToken(customTabsCallback), uri, bundle, list);
            }
            
            public boolean newSession(final ICustomTabsCallback customTabsCallback) {
                final CustomTabsSessionToken customTabsSessionToken = new CustomTabsSessionToken(customTabsCallback);
                try {
                    final IBinder$DeathRecipient binder$DeathRecipient = (IBinder$DeathRecipient)new IBinder$DeathRecipient() {
                        public void binderDied() {
                            CustomTabsService.this.cleanUpSession(customTabsSessionToken);
                        }
                    };
                    synchronized (CustomTabsService.this.mDeathRecipientMap) {
                        customTabsCallback.asBinder().linkToDeath((IBinder$DeathRecipient)binder$DeathRecipient, 0);
                        CustomTabsService.this.mDeathRecipientMap.put(customTabsCallback.asBinder(), binder$DeathRecipient);
                        // monitorexit(CustomTabsService.access$000(this.this$0))
                        return CustomTabsService.this.newSession(customTabsSessionToken);
                    }
                }
                catch (RemoteException ex) {
                    return false;
                }
            }
            
            public int postMessage(final ICustomTabsCallback customTabsCallback, final String s, final Bundle bundle) {
                return CustomTabsService.this.postMessage(new CustomTabsSessionToken(customTabsCallback), s, bundle);
            }
            
            public boolean requestPostMessageChannel(final ICustomTabsCallback customTabsCallback, final Uri uri) {
                return CustomTabsService.this.requestPostMessageChannel(new CustomTabsSessionToken(customTabsCallback), uri);
            }
            
            public boolean updateVisuals(final ICustomTabsCallback customTabsCallback, final Bundle bundle) {
                return CustomTabsService.this.updateVisuals(new CustomTabsSessionToken(customTabsCallback), bundle);
            }
            
            public boolean warmup(final long n) {
                return CustomTabsService.this.warmup(n);
            }
        };
    }
    
    protected boolean cleanUpSession(final CustomTabsSessionToken customTabsSessionToken) {
        try {
            synchronized (this.mDeathRecipientMap) {
                final IBinder callbackBinder = customTabsSessionToken.getCallbackBinder();
                callbackBinder.unlinkToDeath((IBinder$DeathRecipient)this.mDeathRecipientMap.get(callbackBinder), 0);
                this.mDeathRecipientMap.remove(callbackBinder);
                return true;
            }
        }
        catch (NoSuchElementException ex) {
            return false;
        }
    }
    
    protected abstract Bundle extraCommand(final String p0, final Bundle p1);
    
    protected abstract boolean mayLaunchUrl(final CustomTabsSessionToken p0, final Uri p1, final Bundle p2, final List<Bundle> p3);
    
    protected abstract boolean newSession(final CustomTabsSessionToken p0);
    
    public IBinder onBind(final Intent intent) {
        return (IBinder)this.mBinder;
    }
    
    protected abstract int postMessage(final CustomTabsSessionToken p0, final String p1, final Bundle p2);
    
    protected abstract boolean requestPostMessageChannel(final CustomTabsSessionToken p0, final Uri p1);
    
    protected abstract boolean updateVisuals(final CustomTabsSessionToken p0, final Bundle p1);
    
    protected abstract boolean warmup(final long p0);
    
    @Retention(RetentionPolicy.SOURCE)
    public @interface Result {
    }
}
