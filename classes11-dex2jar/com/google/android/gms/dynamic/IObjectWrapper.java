// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.dynamic;

import com.google.android.gms.internal.common.zza;
import android.os.IBinder;
import com.google.android.gms.internal.common.zzb;
import android.os.IInterface;

public interface IObjectWrapper extends IInterface
{
    public static class Stub extends zzb implements IObjectWrapper
    {
        public Stub() {
            super("com.google.android.gms.dynamic.IObjectWrapper");
        }
        
        public static IObjectWrapper asInterface(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.dynamic.IObjectWrapper");
            if (queryLocalInterface instanceof IObjectWrapper) {
                return (IObjectWrapper)queryLocalInterface;
            }
            return new zza(binder);
        }
        
        public static final class zza extends com.google.android.gms.internal.common.zza implements IObjectWrapper
        {
            zza(final IBinder binder) {
                super(binder, "com.google.android.gms.dynamic.IObjectWrapper");
            }
        }
    }
}
