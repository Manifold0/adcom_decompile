// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import com.google.android.gms.internal.common.zza;
import android.os.IBinder;
import com.google.android.gms.internal.common.zzb;
import android.os.RemoteException;
import android.os.IInterface;

public interface ICancelToken extends IInterface
{
    void cancel() throws RemoteException;
    
    public abstract static class Stub extends zzb implements ICancelToken
    {
        public Stub() {
            super("com.google.android.gms.common.internal.ICancelToken");
        }
        
        public static ICancelToken asInterface(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.common.internal.ICancelToken");
            if (queryLocalInterface instanceof ICancelToken) {
                return (ICancelToken)queryLocalInterface;
            }
            return new zza(binder);
        }
        
        public static final class zza extends com.google.android.gms.internal.common.zza implements ICancelToken
        {
            zza(final IBinder binder) {
                super(binder, "com.google.android.gms.common.internal.ICancelToken");
            }
            
            @Override
            public final void cancel() throws RemoteException {
                this.zzc(2, this.zza());
            }
        }
    }
}
