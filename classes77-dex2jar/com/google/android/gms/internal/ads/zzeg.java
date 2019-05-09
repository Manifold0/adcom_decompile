// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.concurrent.TimeUnit;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.CountDownLatch;
import java.lang.reflect.Method;

public class zzeg
{
    private static final String TAG;
    private final String className;
    private final zzcz zzps;
    private final String zztx;
    private final int zzty;
    private volatile Method zztz;
    private final Class<?>[] zzua;
    private CountDownLatch zzub;
    
    static {
        TAG = zzeg.class.getSimpleName();
    }
    
    public zzeg(final zzcz zzps, final String className, final String zztx, final Class<?>... zzua) {
        this.zzty = 2;
        this.zztz = null;
        this.zzub = new CountDownLatch(1);
        this.zzps = zzps;
        this.className = className;
        this.zztx = zztx;
        this.zzua = zzua;
        this.zzps.zzab().submit(new zzeh(this));
    }
    
    private final void zzav() {
        try {
            final Class loadClass = this.zzps.zzac().loadClass(this.zzb(this.zzps.zzae(), this.className));
            if (loadClass == null) {
                return;
            }
            this.zztz = loadClass.getMethod(this.zzb(this.zzps.zzae(), this.zztx), (Class[])this.zzua);
            if (this.zztz == null) {
                return;
            }
        }
        catch (zzcl zzcl) {}
        catch (UnsupportedEncodingException ex) {}
        catch (ClassNotFoundException ex2) {}
        catch (NoSuchMethodException ex3) {}
        catch (NullPointerException ex4) {}
        finally {
            this.zzub.countDown();
        }
    }
    
    private final String zzb(final byte[] array, final String s) throws zzcl, UnsupportedEncodingException {
        return new String(this.zzps.zzad().zza(array, s), "UTF-8");
    }
    
    public final Method zzaw() {
        Method zztz = null;
        if (this.zztz != null) {
            zztz = this.zztz;
        }
        else {
            try {
                if (this.zzub.await(2L, TimeUnit.SECONDS)) {
                    return this.zztz;
                }
            }
            catch (InterruptedException ex) {
                return null;
            }
        }
        return zztz;
    }
}
