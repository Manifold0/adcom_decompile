// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.DeadObjectException;
import com.google.android.gms.common.util.VisibleForTesting;
import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.common.internal.BaseGmsClient$BaseOnConnectionFailedListener;
import com.google.android.gms.common.internal.BaseGmsClient$BaseConnectionCallbacks;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.internal.BaseGmsClient;

@zzadh
public final class zzaee extends BaseGmsClient<zzaen>
{
    public zzaee(Context applicationContext, final Looper looper, final BaseGmsClient$BaseConnectionCallbacks baseGmsClient$BaseConnectionCallbacks, final BaseGmsClient$BaseOnConnectionFailedListener baseGmsClient$BaseOnConnectionFailedListener) {
        if (applicationContext.getApplicationContext() != null) {
            applicationContext = applicationContext.getApplicationContext();
        }
        super(applicationContext, looper, 8, baseGmsClient$BaseConnectionCallbacks, baseGmsClient$BaseOnConnectionFailedListener, (String)null);
    }
    
    @VisibleForTesting
    protected final String getServiceDescriptor() {
        return "com.google.android.gms.ads.internal.request.IAdRequestService";
    }
    
    @VisibleForTesting
    protected final String getStartServiceAction() {
        return "com.google.android.gms.ads.service.START";
    }
    
    public final zzaen zzob() throws DeadObjectException {
        return (zzaen)super.getService();
    }
}
