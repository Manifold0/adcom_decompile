// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common;

import android.content.ComponentName;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.LinkedBlockingQueue;
import android.os.IBinder;
import java.util.concurrent.BlockingQueue;
import com.google.android.gms.common.annotation.KeepForSdk;
import android.content.ServiceConnection;

@KeepForSdk
public class BlockingServiceConnection implements ServiceConnection
{
    private boolean zze;
    private final BlockingQueue<IBinder> zzf;
    
    public BlockingServiceConnection() {
        this.zze = false;
        this.zzf = new LinkedBlockingQueue<IBinder>();
    }
    
    @KeepForSdk
    public IBinder getService() throws InterruptedException {
        Preconditions.checkNotMainThread("BlockingServiceConnection.getService() called on main thread");
        if (this.zze) {
            throw new IllegalStateException("Cannot call get on this connection more than once");
        }
        this.zze = true;
        return this.zzf.take();
    }
    
    @KeepForSdk
    public IBinder getServiceWithTimeout(final long n, final TimeUnit timeUnit) throws InterruptedException, TimeoutException {
        Preconditions.checkNotMainThread("BlockingServiceConnection.getServiceWithTimeout() called on main thread");
        if (this.zze) {
            throw new IllegalStateException("Cannot call get on this connection more than once");
        }
        this.zze = true;
        final IBinder binder = this.zzf.poll(n, timeUnit);
        if (binder == null) {
            throw new TimeoutException("Timed out waiting for the service connection");
        }
        return binder;
    }
    
    public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
        this.zzf.add(binder);
    }
    
    public void onServiceDisconnected(final ComponentName componentName) {
    }
}
