package com.google.android.gms.internal.firebase_messaging;

import java.io.PrintStream;

public final class zzc {
    private static final zzd zzb;
    private static final int zzc;

    static final class zza extends zzd {
        zza() {
        }

        public final void zza(Throwable th, Throwable th2) {
        }
    }

    public static void zza(Throwable th, Throwable th2) {
        zzb.zza(th, th2);
    }

    private static Integer zza() {
        try {
            return (Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
        } catch (Exception e) {
            System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
            e.printStackTrace(System.err);
            return null;
        }
    }

    static {
        Integer zza;
        zzd zzh;
        Throwable th;
        PrintStream printStream;
        String name;
        try {
            zza = zza();
            if (zza != null) {
                try {
                    if (zza.intValue() >= 19) {
                        zzh = new zzh();
                        zzb = zzh;
                        zzc = zza != null ? 1 : zza.intValue();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    printStream = System.err;
                    name = zza.class.getName();
                    printStream.println(new StringBuilder(String.valueOf(name).length() + 132).append("An error has occured when initializing the try-with-resources desuguring strategy. The default strategy ").append(name).append("will be used. The error is: ").toString());
                    th.printStackTrace(System.err);
                    zzh = new zza();
                    zzb = zzh;
                    if (zza != null) {
                    }
                    zzc = zza != null ? 1 : zza.intValue();
                }
            }
            if ((!Boolean.getBoolean("com.google.devtools.build.android.desugar.runtime.twr_disable_mimic") ? 1 : null) != null) {
                zzh = new zzg();
            } else {
                zzh = new zza();
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            zza = null;
            th = th4;
            printStream = System.err;
            name = zza.class.getName();
            printStream.println(new StringBuilder(String.valueOf(name).length() + 132).append("An error has occured when initializing the try-with-resources desuguring strategy. The default strategy ").append(name).append("will be used. The error is: ").toString());
            th.printStackTrace(System.err);
            zzh = new zza();
            zzb = zzh;
            if (zza != null) {
            }
            zzc = zza != null ? 1 : zza.intValue();
        }
        zzb = zzh;
        if (zza != null) {
        }
        zzc = zza != null ? 1 : zza.intValue();
    }
}
