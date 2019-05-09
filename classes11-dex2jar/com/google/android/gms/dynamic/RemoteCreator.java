// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.dynamic;

import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.Preconditions;
import android.content.Context;
import android.os.IBinder;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public abstract class RemoteCreator<T>
{
    private final String zzic;
    private T zzid;
    
    @KeepForSdk
    protected RemoteCreator(final String zzic) {
        this.zzic = zzic;
    }
    
    @KeepForSdk
    protected abstract T getRemoteCreator(final IBinder p0);
    
    @KeepForSdk
    protected final T getRemoteCreatorInstance(Context remoteContext) throws RemoteCreatorException {
        Label_0058: {
            if (this.zzid != null) {
                break Label_0058;
            }
            Preconditions.checkNotNull(remoteContext);
            remoteContext = GooglePlayServicesUtilLight.getRemoteContext(remoteContext);
            if (remoteContext == null) {
                throw new RemoteCreatorException("Could not get remote context.");
            }
            final ClassLoader classLoader = remoteContext.getClassLoader();
            try {
                this.zzid = this.getRemoteCreator((IBinder)classLoader.loadClass(this.zzic).newInstance());
                return this.zzid;
            }
            catch (ClassNotFoundException ex) {
                throw new RemoteCreatorException("Could not load creator class.", ex);
            }
            catch (InstantiationException ex2) {
                throw new RemoteCreatorException("Could not instantiate creator.", ex2);
            }
            catch (IllegalAccessException ex3) {
                throw new RemoteCreatorException("Could not access creator.", ex3);
            }
        }
    }
    
    @KeepForSdk
    public static class RemoteCreatorException extends Exception
    {
        public RemoteCreatorException(final String s) {
            super(s);
        }
        
        public RemoteCreatorException(final String s, final Throwable t) {
            super(s, t);
        }
    }
}
