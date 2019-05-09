// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.Serializable;
import java.util.Locale;
import com.google.android.gms.ads.internal.zzbv;
import java.math.BigInteger;
import android.text.TextUtils;
import android.content.Context;
import javax.annotation.concurrent.GuardedBy;

@zzadh
public final class zzajw
{
    private static final Object sLock;
    @GuardedBy("sLock")
    private static String zzcqq;
    
    static {
        sLock = new Object();
    }
    
    public static String zzc(final Context context, String setBit, String classLoader) {
        while (true) {
            while (true) {
                int bit = 0;
                Label_0143: {
                    synchronized (zzajw.sLock) {
                        if (zzajw.zzcqq == null && !TextUtils.isEmpty((CharSequence)setBit)) {
                            Serializable s = null;
                            Label_0115: {
                                try {
                                    classLoader = (String)context.createPackageContext(classLoader, 3).getClassLoader();
                                    final Class<?> forName = Class.forName("com.google.ads.mediation.MediationAdapter", false, (ClassLoader)classLoader);
                                    s = new BigInteger(new byte[1]);
                                    final String[] split = setBit.split(",");
                                    bit = 0;
                                    if (bit >= split.length) {
                                        break Label_0115;
                                    }
                                    zzbv.zzek();
                                    setBit = (String)s;
                                    if (zzakk.zza((ClassLoader)classLoader, forName, split[bit])) {
                                        setBit = (String)((BigInteger)s).setBit(bit);
                                    }
                                    break Label_0143;
                                }
                                catch (Throwable t) {
                                    zzajw.zzcqq = "err";
                                }
                                return zzajw.zzcqq;
                            }
                            zzajw.zzcqq = String.format(Locale.US, "%X", s);
                        }
                        return zzajw.zzcqq;
                    }
                }
                ++bit;
                Serializable s = setBit;
                continue;
            }
        }
    }
    
    public static String zzqn() {
        synchronized (zzajw.sLock) {
            return zzajw.zzcqq;
        }
    }
}
