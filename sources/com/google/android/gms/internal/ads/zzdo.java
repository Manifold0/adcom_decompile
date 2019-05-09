package com.google.android.gms.internal.ads;

import java.util.concurrent.ExecutionException;

public final class zzdo extends zzei {
    private static final Object zztn = new Object();
    private static volatile zzbj zzto = null;
    private zzax zztp = null;

    public zzdo(zzcz zzcz, String str, String str2, zzba zzba, int i, int i2, zzax zzax) {
        super(zzcz, str, str2, zzba, i, 27);
        this.zztp = zzax;
    }

    private final String zzas() {
        try {
            if (this.zzps.zzak() != null) {
                this.zzps.zzak().get();
            }
            zzba zzaj = this.zzps.zzaj();
            if (!(zzaj == null || zzaj.zzcx == null)) {
                return zzaj.zzcx;
            }
        } catch (InterruptedException e) {
        } catch (ExecutionException e2) {
        }
        return null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected final void zzar() throws java.lang.IllegalAccessException, java.lang.reflect.InvocationTargetException {
        /*
        r10 = this;
        r3 = 3;
        r4 = 2;
        r1 = 1;
        r2 = 0;
        r0 = zzto;
        if (r0 == 0) goto L_0x002a;
    L_0x0008:
        r0 = zzto;
        r0 = r0.zzcx;
        r0 = com.google.android.gms.internal.ads.zzdg.zzo(r0);
        if (r0 != 0) goto L_0x002a;
    L_0x0012:
        r0 = zzto;
        r0 = r0.zzcx;
        r5 = "E";
        r0 = r0.equals(r5);
        if (r0 != 0) goto L_0x002a;
    L_0x001e:
        r0 = zzto;
        r0 = r0.zzcx;
        r5 = "0000000000000000000000000000000000000000000000000000000000000000";
        r0 = r0.equals(r5);
        if (r0 == 0) goto L_0x00bb;
    L_0x002a:
        r0 = r1;
    L_0x002b:
        if (r0 == 0) goto L_0x0086;
    L_0x002d:
        r5 = zztn;
        monitor-enter(r5);
        r0 = r10.zztp;	 Catch:{ all -> 0x010f }
        r0 = 0;
        r0 = com.google.android.gms.internal.ads.zzdg.zzo(r0);	 Catch:{ all -> 0x010f }
        if (r0 != 0) goto L_0x00be;
    L_0x0039:
        r0 = 4;
        r3 = r0;
    L_0x003b:
        r6 = r10.zztz;	 Catch:{ all -> 0x010f }
        r7 = 0;
        r0 = 3;
        r8 = new java.lang.Object[r0];	 Catch:{ all -> 0x010f }
        r0 = 0;
        r9 = r10.zzps;	 Catch:{ all -> 0x010f }
        r9 = r9.getContext();	 Catch:{ all -> 0x010f }
        r8[r0] = r9;	 Catch:{ all -> 0x010f }
        r9 = 1;
        if (r3 != r4) goto L_0x0103;
    L_0x004d:
        r0 = r1;
    L_0x004e:
        r0 = java.lang.Boolean.valueOf(r0);	 Catch:{ all -> 0x010f }
        r8[r9] = r0;	 Catch:{ all -> 0x010f }
        r0 = 2;
        r1 = com.google.android.gms.internal.ads.zznk.zzbav;	 Catch:{ all -> 0x010f }
        r2 = com.google.android.gms.internal.ads.zzkb.zzik();	 Catch:{ all -> 0x010f }
        r1 = r2.zzd(r1);	 Catch:{ all -> 0x010f }
        r8[r0] = r1;	 Catch:{ all -> 0x010f }
        r0 = r6.invoke(r7, r8);	 Catch:{ all -> 0x010f }
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x010f }
        r1 = new com.google.android.gms.internal.ads.zzbj;	 Catch:{ all -> 0x010f }
        r1.<init>(r0);	 Catch:{ all -> 0x010f }
        zzto = r1;	 Catch:{ all -> 0x010f }
        r0 = r1.zzcx;	 Catch:{ all -> 0x010f }
        r0 = com.google.android.gms.internal.ads.zzdg.zzo(r0);	 Catch:{ all -> 0x010f }
        if (r0 != 0) goto L_0x0082;
    L_0x0076:
        r0 = zzto;	 Catch:{ all -> 0x010f }
        r0 = r0.zzcx;	 Catch:{ all -> 0x010f }
        r1 = "E";
        r0 = r0.equals(r1);	 Catch:{ all -> 0x010f }
        if (r0 == 0) goto L_0x0085;
    L_0x0082:
        switch(r3) {
            case 3: goto L_0x0112;
            case 4: goto L_0x0106;
            default: goto L_0x0085;
        };	 Catch:{ all -> 0x010f }
    L_0x0085:
        monitor-exit(r5);	 Catch:{ all -> 0x010f }
    L_0x0086:
        r1 = r10.zztq;
        monitor-enter(r1);
        r0 = zzto;	 Catch:{ all -> 0x0122 }
        if (r0 == 0) goto L_0x00b9;
    L_0x008d:
        r0 = r10.zztq;	 Catch:{ all -> 0x0122 }
        r2 = zzto;	 Catch:{ all -> 0x0122 }
        r2 = r2.zzcx;	 Catch:{ all -> 0x0122 }
        r0.zzcx = r2;	 Catch:{ all -> 0x0122 }
        r0 = r10.zztq;	 Catch:{ all -> 0x0122 }
        r2 = zzto;	 Catch:{ all -> 0x0122 }
        r2 = r2.zzhx;	 Catch:{ all -> 0x0122 }
        r2 = java.lang.Long.valueOf(r2);	 Catch:{ all -> 0x0122 }
        r0.zzeb = r2;	 Catch:{ all -> 0x0122 }
        r0 = r10.zztq;	 Catch:{ all -> 0x0122 }
        r2 = zzto;	 Catch:{ all -> 0x0122 }
        r2 = r2.zzcz;	 Catch:{ all -> 0x0122 }
        r0.zzcz = r2;	 Catch:{ all -> 0x0122 }
        r0 = r10.zztq;	 Catch:{ all -> 0x0122 }
        r2 = zzto;	 Catch:{ all -> 0x0122 }
        r2 = r2.zzda;	 Catch:{ all -> 0x0122 }
        r0.zzda = r2;	 Catch:{ all -> 0x0122 }
        r0 = r10.zztq;	 Catch:{ all -> 0x0122 }
        r2 = zzto;	 Catch:{ all -> 0x0122 }
        r2 = r2.zzdb;	 Catch:{ all -> 0x0122 }
        r0.zzdb = r2;	 Catch:{ all -> 0x0122 }
    L_0x00b9:
        monitor-exit(r1);	 Catch:{ all -> 0x0122 }
        return;
    L_0x00bb:
        r0 = r2;
        goto L_0x002b;
    L_0x00be:
        r0 = r10.zztp;	 Catch:{ all -> 0x010f }
        r0 = 0;
        com.google.android.gms.internal.ads.zzdg.zzo(r0);	 Catch:{ all -> 0x010f }
        r0 = 0;
        r0 = java.lang.Boolean.valueOf(r0);	 Catch:{ all -> 0x010f }
        r0 = r0.booleanValue();	 Catch:{ all -> 0x010f }
        if (r0 == 0) goto L_0x00fe;
    L_0x00cf:
        r0 = r10.zzps;	 Catch:{ all -> 0x010f }
        r0 = r0.zzah();	 Catch:{ all -> 0x010f }
        if (r0 == 0) goto L_0x0101;
    L_0x00d7:
        r0 = com.google.android.gms.internal.ads.zznk.zzbbb;	 Catch:{ all -> 0x010f }
        r6 = com.google.android.gms.internal.ads.zzkb.zzik();	 Catch:{ all -> 0x010f }
        r0 = r6.zzd(r0);	 Catch:{ all -> 0x010f }
        r0 = (java.lang.Boolean) r0;	 Catch:{ all -> 0x010f }
        r0 = r0.booleanValue();	 Catch:{ all -> 0x010f }
        if (r0 == 0) goto L_0x0101;
    L_0x00e9:
        r0 = com.google.android.gms.internal.ads.zznk.zzbbc;	 Catch:{ all -> 0x010f }
        r6 = com.google.android.gms.internal.ads.zzkb.zzik();	 Catch:{ all -> 0x010f }
        r0 = r6.zzd(r0);	 Catch:{ all -> 0x010f }
        r0 = (java.lang.Boolean) r0;	 Catch:{ all -> 0x010f }
        r0 = r0.booleanValue();	 Catch:{ all -> 0x010f }
        if (r0 == 0) goto L_0x0101;
    L_0x00fb:
        r0 = r1;
    L_0x00fc:
        if (r0 != 0) goto L_0x003b;
    L_0x00fe:
        r3 = r4;
        goto L_0x003b;
    L_0x0101:
        r0 = r2;
        goto L_0x00fc;
    L_0x0103:
        r0 = r2;
        goto L_0x004e;
    L_0x0106:
        r0 = zzto;	 Catch:{ all -> 0x010f }
        r1 = 0;
        r1 = r1.zzcx;	 Catch:{ all -> 0x010f }
        r0.zzcx = r1;	 Catch:{ all -> 0x010f }
        goto L_0x0085;
    L_0x010f:
        r0 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x010f }
        throw r0;
    L_0x0112:
        r0 = r10.zzas();	 Catch:{ all -> 0x010f }
        r1 = com.google.android.gms.internal.ads.zzdg.zzo(r0);	 Catch:{ all -> 0x010f }
        if (r1 != 0) goto L_0x0085;
    L_0x011c:
        r1 = zzto;	 Catch:{ all -> 0x010f }
        r1.zzcx = r0;	 Catch:{ all -> 0x010f }
        goto L_0x0085;
    L_0x0122:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0122 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzdo.zzar():void");
    }
}
