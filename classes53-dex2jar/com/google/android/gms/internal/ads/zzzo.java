// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;

@zzadh
public class zzzo
{
    public static zzzj zzbs(final String s) throws RemoteException {
        try {
            return new zzzp((zzatg)Class.forName(s, false, zzzo.class.getClassLoader()).getDeclaredConstructor((Class<?>[])new Class[0]).newInstance(new Object[0]));
        }
        catch (Throwable t) {
            throw new RemoteException();
        }
    }
}
