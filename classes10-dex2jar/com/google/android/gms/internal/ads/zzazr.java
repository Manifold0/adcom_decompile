// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.PrintWriter;
import java.io.PrintStream;

public final class zzazr
{
    private static final zzazs zzdov;
    private static final int zzdow;
    
    static {
        while (true) {
            try {
                final Integer zzaau = zzaau();
                Label_0040: {
                    if (zzaau == null) {
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
                                        zzazs zzdov2 = null;
                                        try {
                                            if (zzaau >= 19) {
                                                zzdov2 = new zzazw();
                                            }
                                            else {
                                                if (Boolean.getBoolean("com.google.devtools.build.android.desugar.runtime.twr_disable_mimic")) {
                                                    break Label_0136;
                                                }
                                                final int n = 1;
                                                if (n == 0) {
                                                    break Label_0141;
                                                }
                                                zzdov2 = new zzazv();
                                            }
                                            zzdov = zzdov2;
                                            if (zzaau == null) {
                                                final int intValue = 1;
                                                zzdow = intValue;
                                                return;
                                            }
                                            break Label_0025;
                                        }
                                        catch (Throwable t) {}
                                        final PrintStream err = System.err;
                                        final String name = zza.class.getName();
                                        err.println(new StringBuilder(String.valueOf(name).length() + 132).append("An error has occured when initializing the try-with-resources desuguring strategy. The default strategy ").append(name).append("will be used. The error is: ").toString());
                                        ((Throwable)zzdov2).printStackTrace(System.err);
                                        zzdov2 = new zza();
                                        continue Label_0025;
                                    }
                                    final int n = 0;
                                    continue Label_0025_Outer;
                                }
                                zzazs zzdov2 = new zza();
                                continue Label_0025;
                            }
                            final int intValue = zzaau;
                            continue;
                        }
                    }
                }
            }
            catch (Throwable zzdov2) {
                final Integer zzaau = null;
                continue;
            }
            break;
        }
    }
    
    public static void zza(final Throwable t, final PrintWriter printWriter) {
        zzazr.zzdov.zza(t, printWriter);
    }
    
    private static Integer zzaau() {
        try {
            return (Integer)Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
        }
        catch (Exception ex) {
            System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
            ex.printStackTrace(System.err);
            return null;
        }
    }
    
    static final class zza extends zzazs
    {
        @Override
        public final void zza(final Throwable t, final PrintWriter printWriter) {
            t.printStackTrace(printWriter);
        }
    }
}
