// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.stats;

import android.content.ComponentName;
import android.util.Log;
import com.google.android.gms.common.util.ClientLibraryUtils;
import android.annotation.SuppressLint;
import android.content.ServiceConnection;
import android.content.Intent;
import android.content.Context;
import java.util.Collections;
import java.util.List;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class ConnectionTracker
{
    private static final Object zzdp;
    private static volatile ConnectionTracker zzfa;
    @VisibleForTesting
    private static boolean zzfb;
    private final List<String> zzfc;
    private final List<String> zzfd;
    private final List<String> zzfe;
    private final List<String> zzff;
    
    static {
        zzdp = new Object();
        ConnectionTracker.zzfb = false;
    }
    
    private ConnectionTracker() {
        this.zzfc = (List<String>)Collections.EMPTY_LIST;
        this.zzfd = (List<String>)Collections.EMPTY_LIST;
        this.zzfe = (List<String>)Collections.EMPTY_LIST;
        this.zzff = (List<String>)Collections.EMPTY_LIST;
    }
    
    @KeepForSdk
    public static ConnectionTracker getInstance() {
        Label_0030: {
            if (ConnectionTracker.zzfa != null) {
                break Label_0030;
            }
            synchronized (ConnectionTracker.zzdp) {
                if (ConnectionTracker.zzfa == null) {
                    ConnectionTracker.zzfa = new ConnectionTracker();
                }
                return ConnectionTracker.zzfa;
            }
        }
    }
    
    @KeepForSdk
    public boolean bindService(final Context context, final Intent intent, final ServiceConnection serviceConnection, final int n) {
        return this.zza(context, context.getClass().getName(), intent, serviceConnection, n);
    }
    
    @SuppressLint({ "UntrackedBindService" })
    @KeepForSdk
    public void unbindService(final Context context, final ServiceConnection serviceConnection) {
        context.unbindService(serviceConnection);
    }
    
    public final boolean zza(final Context context, final String s, final Intent intent, final ServiceConnection serviceConnection, final int n) {
        final ComponentName component = intent.getComponent();
        if (component != null && ClientLibraryUtils.zzc(context, component.getPackageName())) {
            Log.w("ConnectionTracker", "Attempted to bind to a service in a STOPPED package.");
            return false;
        }
        return context.bindService(intent, serviceConnection, n);
    }
}
