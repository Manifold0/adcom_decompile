// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.firebase_messaging;

import java.io.PrintStream;

public final class zzc
{
    private static final zzd zzb;
    private static final int zzc;
    
    static {
        while (true) {
            try {
                final Integer zza = zza();
                Label_0040: {
                    if (zza == null) {
                        break Label_0040;
                    }
                Label_0025_Outer:
                    while (true) {
                        while (true) {
                        Label_0025:
                            while (true) {
                            Label_0141:
                                while (true) {
                                    Label_0136: {
                                        zzd zzb2 = null;
                                        try {
                                            if (zza >= 19) {
                                                zzb2 = new zzh();
                                            }
                                            else {
                                                if (Boolean.getBoolean("com.google.devtools.build.android.desugar.runtime.twr_disable_mimic")) {
                                                    break Label_0136;
                                                }
                                                final int n = 1;
                                                if (n == 0) {
                                                    break Label_0141;
                                                }
                                                zzb2 = new zzg();
                                            }
                                            zzb = zzb2;
                                            if (zza == null) {
                                                final int intValue = 1;
                                                zzc = intValue;
                                                return;
                                            }
                                            break Label_0025;
                                        }
                                        catch (Throwable t) {}
                                        final PrintStream err = System.err;
                                        final String name = zza.class.getName();
                                        err.println(new StringBuilder(String.valueOf(name).length() + 132).append("An error has occured when initializing the try-with-resources desuguring strategy. The default strategy ").append(name).append("will be used. The error is: ").toString());
                                        ((Throwable)zzb2).printStackTrace(System.err);
                                        zzb2 = new zza();
                                        continue Label_0025;
                                    }
                                    final int n = 0;
                                    continue Label_0025_Outer;
                                }
                                zzd zzb2 = new zza();
                                continue Label_0025;
                            }
                            final int intValue = zza;
                            continue;
                        }
                    }
                }
            }
            catch (Throwable zzb2) {
                final Integer zza = null;
                continue;
            }
            break;
        }
    }
    
    private static Integer zza() {
        try {
            return (Integer)Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
        }
        catch (Exception ex) {
            System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
            ex.printStackTrace(System.err);
            return null;
        }
    }
    
    public static void zza(final Throwable t, final Throwable t2) {
        com.google.android.gms.internal.firebase_messaging.zzc.zzb.zza(t, t2);
    }
    
    static final class zza extends zzd
    {
        @Override
        public final void zza(final Throwable t, final Throwable t2) {
        }
    }
}
