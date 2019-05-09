package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

@zzadh
public final class zzxk implements zzww {
    private final Context mContext;
    private final Object mLock = new Object();
    private final long mStartTime;
    private final boolean zzael;
    private final zzwy zzbtj;
    private final boolean zzbtn;
    private final boolean zzbto;
    private final zzaef zzbuc;
    private final long zzbud;
    private boolean zzbuf = false;
    private final String zzbuh;
    private List<zzxe> zzbui = new ArrayList();
    private zzxb zzbum;
    private final zznx zzvr;
    private final zzxn zzwh;

    public zzxk(Context context, zzaef zzaef, zzxn zzxn, zzwy zzwy, boolean z, boolean z2, String str, long j, long j2, zznx zznx, boolean z3) {
        this.mContext = context;
        this.zzbuc = zzaef;
        this.zzwh = zzxn;
        this.zzbtj = zzwy;
        this.zzael = z;
        this.zzbtn = z2;
        this.zzbuh = str;
        this.mStartTime = j;
        this.zzbud = j2;
        this.zzvr = zznx;
        this.zzbto = z3;
    }

    public final void cancel() {
        synchronized (this.mLock) {
            this.zzbuf = true;
            if (this.zzbum != null) {
                this.zzbum.cancel();
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.ads.zzxe zzh(java.util.List<com.google.android.gms.internal.ads.zzwx> r25) {
        /*
        r24 = this;
        r2 = "Starting mediation.";
        com.google.android.gms.internal.ads.zzakb.zzck(r2);
        r18 = new java.util.ArrayList;
        r18.<init>();
        r0 = r24;
        r2 = r0.zzvr;
        r19 = r2.zzjj();
        r0 = r24;
        r2 = r0.zzbuc;
        r2 = r2.zzacv;
        r3 = 2;
        r3 = new int[r3];
        r4 = r2.zzard;
        if (r4 == 0) goto L_0x019b;
    L_0x001f:
        com.google.android.gms.ads.internal.zzbv.zzfd();
        r0 = r24;
        r4 = r0.zzbuh;
        r4 = com.google.android.gms.internal.ads.zzxg.zza(r4, r3);
        if (r4 == 0) goto L_0x019b;
    L_0x002c:
        r4 = 0;
        r4 = r3[r4];
        r5 = 1;
        r5 = r3[r5];
        r6 = r2.zzard;
        r7 = r6.length;
        r3 = 0;
    L_0x0036:
        if (r3 >= r7) goto L_0x019b;
    L_0x0038:
        r9 = r6[r3];
        r8 = r9.width;
        if (r4 != r8) goto L_0x0096;
    L_0x003e:
        r8 = r9.height;
        if (r5 != r8) goto L_0x0096;
    L_0x0042:
        r20 = r25.iterator();
    L_0x0046:
        r2 = r20.hasNext();
        if (r2 == 0) goto L_0x017c;
    L_0x004c:
        r7 = r20.next();
        r7 = (com.google.android.gms.internal.ads.zzwx) r7;
        r3 = "Trying mediation network: ";
        r2 = r7.zzbrs;
        r2 = java.lang.String.valueOf(r2);
        r4 = r2.length();
        if (r4 == 0) goto L_0x0099;
    L_0x0060:
        r2 = r3.concat(r2);
    L_0x0064:
        com.google.android.gms.internal.ads.zzakb.zzdj(r2);
        r2 = r7.zzbrt;
        r21 = r2.iterator();
    L_0x006d:
        r2 = r21.hasNext();
        if (r2 == 0) goto L_0x0046;
    L_0x0073:
        r4 = r21.next();
        r4 = (java.lang.String) r4;
        r0 = r24;
        r2 = r0.zzvr;
        r22 = r2.zzjj();
        r0 = r24;
        r0 = r0.mLock;
        r23 = r0;
        monitor-enter(r23);
        r0 = r24;
        r2 = r0.zzbuf;	 Catch:{ all -> 0x0151 }
        if (r2 == 0) goto L_0x009f;
    L_0x008e:
        r2 = new com.google.android.gms.internal.ads.zzxe;	 Catch:{ all -> 0x0151 }
        r3 = -1;
        r2.<init>(r3);	 Catch:{ all -> 0x0151 }
        monitor-exit(r23);	 Catch:{ all -> 0x0151 }
    L_0x0095:
        return r2;
    L_0x0096:
        r3 = r3 + 1;
        goto L_0x0036;
    L_0x0099:
        r2 = new java.lang.String;
        r2.<init>(r3);
        goto L_0x0064;
    L_0x009f:
        r2 = new com.google.android.gms.internal.ads.zzxb;	 Catch:{ all -> 0x0151 }
        r0 = r24;
        r3 = r0.mContext;	 Catch:{ all -> 0x0151 }
        r0 = r24;
        r5 = r0.zzwh;	 Catch:{ all -> 0x0151 }
        r0 = r24;
        r6 = r0.zzbtj;	 Catch:{ all -> 0x0151 }
        r0 = r24;
        r8 = r0.zzbuc;	 Catch:{ all -> 0x0151 }
        r8 = r8.zzccv;	 Catch:{ all -> 0x0151 }
        r0 = r24;
        r10 = r0.zzbuc;	 Catch:{ all -> 0x0151 }
        r10 = r10.zzacr;	 Catch:{ all -> 0x0151 }
        r0 = r24;
        r11 = r0.zzael;	 Catch:{ all -> 0x0151 }
        r0 = r24;
        r12 = r0.zzbtn;	 Catch:{ all -> 0x0151 }
        r0 = r24;
        r13 = r0.zzbuc;	 Catch:{ all -> 0x0151 }
        r13 = r13.zzadj;	 Catch:{ all -> 0x0151 }
        r0 = r24;
        r14 = r0.zzbuc;	 Catch:{ all -> 0x0151 }
        r14 = r14.zzads;	 Catch:{ all -> 0x0151 }
        r0 = r24;
        r15 = r0.zzbuc;	 Catch:{ all -> 0x0151 }
        r15 = r15.zzcdk;	 Catch:{ all -> 0x0151 }
        r0 = r24;
        r0 = r0.zzbuc;	 Catch:{ all -> 0x0151 }
        r16 = r0;
        r0 = r16;
        r0 = r0.zzcef;	 Catch:{ all -> 0x0151 }
        r16 = r0;
        r0 = r24;
        r0 = r0.zzbto;	 Catch:{ all -> 0x0151 }
        r17 = r0;
        r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17);	 Catch:{ all -> 0x0151 }
        r0 = r24;
        r0.zzbum = r2;	 Catch:{ all -> 0x0151 }
        monitor-exit(r23);	 Catch:{ all -> 0x0151 }
        r0 = r24;
        r2 = r0.zzbum;
        r0 = r24;
        r10 = r0.mStartTime;
        r0 = r24;
        r12 = r0.zzbud;
        r2 = r2.zza(r10, r12);
        r0 = r24;
        r3 = r0.zzbui;
        r3.add(r2);
        r3 = r2.zzbtv;
        if (r3 != 0) goto L_0x0154;
    L_0x0108:
        r3 = "Adapter succeeded.";
        com.google.android.gms.internal.ads.zzakb.zzck(r3);
        r0 = r24;
        r3 = r0.zzvr;
        r5 = "mediation_network_succeed";
        r3.zze(r5, r4);
        r3 = r18.isEmpty();
        if (r3 != 0) goto L_0x012d;
    L_0x011c:
        r0 = r24;
        r3 = r0.zzvr;
        r4 = "mediation_networks_fail";
        r5 = ",";
        r0 = r18;
        r5 = android.text.TextUtils.join(r5, r0);
        r3.zze(r4, r5);
    L_0x012d:
        r0 = r24;
        r3 = r0.zzvr;
        r4 = 1;
        r4 = new java.lang.String[r4];
        r5 = 0;
        r6 = "mls";
        r4[r5] = r6;
        r0 = r22;
        r3.zza(r0, r4);
        r0 = r24;
        r3 = r0.zzvr;
        r4 = 1;
        r4 = new java.lang.String[r4];
        r5 = 0;
        r6 = "ttm";
        r4[r5] = r6;
        r0 = r19;
        r3.zza(r0, r4);
        goto L_0x0095;
    L_0x0151:
        r2 = move-exception;
        monitor-exit(r23);	 Catch:{ all -> 0x0151 }
        throw r2;
    L_0x0154:
        r0 = r18;
        r0.add(r4);
        r0 = r24;
        r3 = r0.zzvr;
        r4 = 1;
        r4 = new java.lang.String[r4];
        r5 = 0;
        r6 = "mlf";
        r4[r5] = r6;
        r0 = r22;
        r3.zza(r0, r4);
        r3 = r2.zzbtx;
        if (r3 == 0) goto L_0x006d;
    L_0x016e:
        r3 = com.google.android.gms.internal.ads.zzakk.zzcrm;
        r4 = new com.google.android.gms.internal.ads.zzxl;
        r0 = r24;
        r4.<init>(r0, r2);
        r3.post(r4);
        goto L_0x006d;
    L_0x017c:
        r2 = r18.isEmpty();
        if (r2 != 0) goto L_0x0193;
    L_0x0182:
        r0 = r24;
        r2 = r0.zzvr;
        r3 = "mediation_networks_fail";
        r4 = ",";
        r0 = r18;
        r4 = android.text.TextUtils.join(r4, r0);
        r2.zze(r3, r4);
    L_0x0193:
        r2 = new com.google.android.gms.internal.ads.zzxe;
        r3 = 1;
        r2.<init>(r3);
        goto L_0x0095;
    L_0x019b:
        r9 = r2;
        goto L_0x0042;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzxk.zzh(java.util.List):com.google.android.gms.internal.ads.zzxe");
    }

    public final List<zzxe> zzme() {
        return this.zzbui;
    }
}
