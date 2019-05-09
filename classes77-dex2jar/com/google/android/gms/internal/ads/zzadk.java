// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;
import android.support.annotation.NonNull;
import org.json.JSONObject;
import javax.annotation.concurrent.GuardedBy;
import com.google.android.gms.common.util.VisibleForTesting;
import android.content.Context;

@zzadh
public final class zzadk extends zzajx implements zzadx
{
    private final Context mContext;
    @VisibleForTesting
    private zzwy zzbtj;
    @VisibleForTesting
    private zzaef zzbuc;
    @VisibleForTesting
    private zzaej zzbzf;
    private Runnable zzbzg;
    private final Object zzbzh;
    private final zzadj zzccf;
    private final zzaeg zzccg;
    private final zzhs zzcch;
    private final zzhx zzcci;
    @VisibleForTesting
    @GuardedBy("mCancelLock")
    zzalc zzccj;
    
    public zzadk(final Context mContext, final zzaeg zzccg, final zzadj zzccf, final zzhx zzcci) {
        this.zzbzh = new Object();
        this.zzccf = zzccf;
        this.mContext = mContext;
        this.zzccg = zzccg;
        this.zzcci = zzcci;
        (this.zzcch = new zzhs(this.zzcci)).zza(new zzadl(this));
        final zzit zzit = new zzit();
        zzit.zzaot = this.zzccg.zzacr.zzcve;
        zzit.zzaou = this.zzccg.zzacr.zzcvf;
        int n;
        if (this.zzccg.zzacr.zzcvg) {
            n = 0;
        }
        else {
            n = 2;
        }
        zzit.zzaov = n;
        this.zzcch.zza(new zzadm(zzit));
        if (this.zzccg.zzccw != null) {
            this.zzcch.zza(new zzadn(this));
        }
        final zzjn zzacv = this.zzccg.zzacv;
        if (zzacv.zzarc && "interstitial_mb".equals(zzacv.zzarb)) {
            this.zzcch.zza(zzado.zzccm);
        }
        else if (zzacv.zzarc && "reward_mb".equals(zzacv.zzarb)) {
            this.zzcch.zza(zzadp.zzccm);
        }
        else if (!zzacv.zzare && !zzacv.zzarc) {
            this.zzcch.zza(zzadq.zzccm);
        }
        else {
            this.zzcch.zza(zzadr.zzccm);
        }
        this.zzcch.zza(zzhu.zza.zzb.zzakj);
    }
    
    @VisibleForTesting
    private final zzjn zza(final zzaef zzaef) throws zzadu {
        boolean b = true;
        if (this.zzbuc == null || this.zzbuc.zzadn == null || this.zzbuc.zzadn.size() <= 1) {
            b = false;
        }
        if (b && this.zzbtj != null && !this.zzbtj.zzbte) {
            return null;
        }
        if (this.zzbzf.zzarf) {
            final zzjn[] zzard = zzaef.zzacv.zzard;
            for (int length = zzard.length, i = 0; i < length; ++i) {
                final zzjn zzjn = zzard[i];
                if (zzjn.zzarf) {
                    return new zzjn(zzjn, zzaef.zzacv.zzard);
                }
            }
        }
        if (this.zzbzf.zzcet == null) {
            throw new zzadu("The ad response must specify one of the supported ad sizes.", 0);
        }
        final String[] split = this.zzbzf.zzcet.split("x");
        if (split.length != 2) {
            final String value = String.valueOf(this.zzbzf.zzcet);
            String concat;
            if (value.length() != 0) {
                concat = "Invalid ad size format from the ad response: ".concat(value);
            }
            else {
                concat = new String("Invalid ad size format from the ad response: ");
            }
            throw new zzadu(concat, 0);
        }
        while (true) {
            int int1;
            int int2;
            zzjn[] zzard2;
            int length2;
            int n = 0;
            zzjn zzjn2 = null;
            float density;
            int width;
            int height;
            String value2;
            String concat2;
            Label_0303_Outer:Label_0324_Outer:
            while (true) {
            Label_0434:
                while (true) {
                Label_0424:
                    while (true) {
                        try {
                            int1 = Integer.parseInt(split[0]);
                            int2 = Integer.parseInt(split[1]);
                            zzard2 = zzaef.zzacv.zzard;
                            length2 = zzard2.length;
                            n = 0;
                            if (n >= length2) {
                                break;
                            }
                            zzjn2 = zzard2[n];
                            density = this.mContext.getResources().getDisplayMetrics().density;
                            if (zzjn2.width == -1) {
                                width = (int)(zzjn2.widthPixels / density);
                                if (zzjn2.height != -2) {
                                    break Label_0424;
                                }
                                height = (int)(zzjn2.heightPixels / density);
                                if (int1 == width && int2 == height && !zzjn2.zzarf) {
                                    return new zzjn(zzjn2, zzaef.zzacv.zzard);
                                }
                                break Label_0434;
                            }
                        }
                        catch (NumberFormatException ex) {
                            value2 = String.valueOf(this.zzbzf.zzcet);
                            if (value2.length() != 0) {
                                concat2 = "Invalid ad size number from the ad response: ".concat(value2);
                            }
                            else {
                                concat2 = new String("Invalid ad size number from the ad response: ");
                            }
                            throw new zzadu(concat2, 0);
                        }
                        width = zzjn2.width;
                        continue Label_0324_Outer;
                    }
                    height = zzjn2.height;
                    continue;
                }
                ++n;
                continue Label_0303_Outer;
            }
        }
        final String value3 = String.valueOf(this.zzbzf.zzcet);
        String concat3;
        if (value3.length() != 0) {
            concat3 = "The ad size from the ad response was not one of the requested sizes: ".concat(value3);
        }
        else {
            concat3 = new String("The ad size from the ad response was not one of the requested sizes: ");
        }
        throw new zzadu(concat3, 0);
    }
    
    private final void zzc(final int n, final String s) {
        if (n == 3 || n == -1) {
            zzakb.zzdj(s);
        }
        else {
            zzakb.zzdk(s);
        }
        if (this.zzbzf == null) {
            this.zzbzf = new zzaej(n);
        }
        else {
            this.zzbzf = new zzaej(n, this.zzbzf.zzbsu);
        }
        zzaef zzbuc;
        if (this.zzbuc != null) {
            zzbuc = this.zzbuc;
        }
        else {
            zzbuc = new zzaef(this.zzccg, -1L, null, null, null);
        }
        this.zzccf.zza(new zzaji(zzbuc, this.zzbzf, this.zzbtj, null, n, -1L, this.zzbzf.zzceu, null, this.zzcch, null));
    }
    
    @Override
    public final void onStop() {
        synchronized (this.zzbzh) {
            if (this.zzccj != null) {
                this.zzccj.cancel();
            }
        }
    }
    
    @VisibleForTesting
    final zzalc zza(final zzang zzang, final zzaol<zzaef> zzaol) {
        final Context mContext = this.mContext;
        if (new zzadw(mContext).zza(zzang)) {
            zzakb.zzck("Fetching ad response from local ad request service.");
            final zzaec zzaec = new zzaec(mContext, zzaol, this);
            zzaec.zznt();
            return zzaec;
        }
        zzakb.zzck("Fetching ad response from remote ad request service.");
        zzkb.zzif();
        if (!zzamu.zzbe(mContext)) {
            zzakb.zzdk("Failed to connect to remote ad request service.");
            return null;
        }
        return new zzaed(mContext, zzang, zzaol, this);
    }
    
    @Override
    public final void zza(@NonNull final zzaej p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          8
        //     3: ldc_w           "Received ad response."
        //     6: invokestatic    com/google/android/gms/internal/ads/zzakb.zzck:(Ljava/lang/String;)V
        //     9: aload_0        
        //    10: aload_1        
        //    11: putfield        com/google/android/gms/internal/ads/zzadk.zzbzf:Lcom/google/android/gms/internal/ads/zzaej;
        //    14: invokestatic    com/google/android/gms/ads/internal/zzbv.zzer:()Lcom/google/android/gms/common/util/Clock;
        //    17: invokeinterface com/google/android/gms/common/util/Clock.elapsedRealtime:()J
        //    22: lstore_3       
        //    23: aload_0        
        //    24: getfield        com/google/android/gms/internal/ads/zzadk.zzbzh:Ljava/lang/Object;
        //    27: astore_1       
        //    28: aload_1        
        //    29: monitorenter   
        //    30: aload_0        
        //    31: aconst_null    
        //    32: putfield        com/google/android/gms/internal/ads/zzadk.zzccj:Lcom/google/android/gms/internal/ads/zzalc;
        //    35: aload_1        
        //    36: monitorexit    
        //    37: invokestatic    com/google/android/gms/ads/internal/zzbv.zzeo:()Lcom/google/android/gms/internal/ads/zzajm;
        //    40: invokevirtual   com/google/android/gms/internal/ads/zzajm.zzqh:()Lcom/google/android/gms/internal/ads/zzakd;
        //    43: aload_0        
        //    44: getfield        com/google/android/gms/internal/ads/zzadk.zzbzf:Lcom/google/android/gms/internal/ads/zzaej;
        //    47: getfield        com/google/android/gms/internal/ads/zzaej.zzcdr:Z
        //    50: invokevirtual   com/google/android/gms/internal/ads/zzakd.zzae:(Z)V
        //    53: getstatic       com/google/android/gms/internal/ads/zznk.zzayy:Lcom/google/android/gms/internal/ads/zzna;
        //    56: astore_1       
        //    57: invokestatic    com/google/android/gms/internal/ads/zzkb.zzik:()Lcom/google/android/gms/internal/ads/zzni;
        //    60: aload_1        
        //    61: invokevirtual   com/google/android/gms/internal/ads/zzni.zzd:(Lcom/google/android/gms/internal/ads/zzna;)Ljava/lang/Object;
        //    64: checkcast       Ljava/lang/Boolean;
        //    67: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    70: ifeq            99
        //    73: aload_0        
        //    74: getfield        com/google/android/gms/internal/ads/zzadk.zzbzf:Lcom/google/android/gms/internal/ads/zzaej;
        //    77: getfield        com/google/android/gms/internal/ads/zzaej.zzced:Z
        //    80: ifeq            199
        //    83: invokestatic    com/google/android/gms/ads/internal/zzbv.zzeo:()Lcom/google/android/gms/internal/ads/zzajm;
        //    86: invokevirtual   com/google/android/gms/internal/ads/zzajm.zzqh:()Lcom/google/android/gms/internal/ads/zzakd;
        //    89: aload_0        
        //    90: getfield        com/google/android/gms/internal/ads/zzadk.zzbuc:Lcom/google/android/gms/internal/ads/zzaef;
        //    93: getfield        com/google/android/gms/internal/ads/zzaef.zzacp:Ljava/lang/String;
        //    96: invokevirtual   com/google/android/gms/internal/ads/zzakd.zzcp:(Ljava/lang/String;)V
        //    99: aload_0        
        //   100: getfield        com/google/android/gms/internal/ads/zzadk.zzbzf:Lcom/google/android/gms/internal/ads/zzaej;
        //   103: getfield        com/google/android/gms/internal/ads/zzaej.errorCode:I
        //   106: bipush          -2
        //   108: if_icmpeq       218
        //   111: aload_0        
        //   112: getfield        com/google/android/gms/internal/ads/zzadk.zzbzf:Lcom/google/android/gms/internal/ads/zzaej;
        //   115: getfield        com/google/android/gms/internal/ads/zzaej.errorCode:I
        //   118: bipush          -3
        //   120: if_icmpeq       218
        //   123: aload_0        
        //   124: getfield        com/google/android/gms/internal/ads/zzadk.zzbzf:Lcom/google/android/gms/internal/ads/zzaej;
        //   127: getfield        com/google/android/gms/internal/ads/zzaej.errorCode:I
        //   130: istore_2       
        //   131: new             Lcom/google/android/gms/internal/ads/zzadu;
        //   134: dup            
        //   135: new             Ljava/lang/StringBuilder;
        //   138: dup            
        //   139: bipush          66
        //   141: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //   144: ldc_w           "There was a problem getting an ad response. ErrorCode: "
        //   147: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   150: iload_2        
        //   151: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   154: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   157: aload_0        
        //   158: getfield        com/google/android/gms/internal/ads/zzadk.zzbzf:Lcom/google/android/gms/internal/ads/zzaej;
        //   161: getfield        com/google/android/gms/internal/ads/zzaej.errorCode:I
        //   164: invokespecial   com/google/android/gms/internal/ads/zzadu.<init>:(Ljava/lang/String;I)V
        //   167: athrow         
        //   168: astore_1       
        //   169: aload_0        
        //   170: aload_1        
        //   171: invokevirtual   com/google/android/gms/internal/ads/zzadu.getErrorCode:()I
        //   174: aload_1        
        //   175: invokevirtual   com/google/android/gms/internal/ads/zzadu.getMessage:()Ljava/lang/String;
        //   178: invokespecial   com/google/android/gms/internal/ads/zzadk.zzc:(ILjava/lang/String;)V
        //   181: getstatic       com/google/android/gms/internal/ads/zzakk.zzcrm:Landroid/os/Handler;
        //   184: aload_0        
        //   185: getfield        com/google/android/gms/internal/ads/zzadk.zzbzg:Ljava/lang/Runnable;
        //   188: invokevirtual   android/os/Handler.removeCallbacks:(Ljava/lang/Runnable;)V
        //   191: return         
        //   192: astore          6
        //   194: aload_1        
        //   195: monitorexit    
        //   196: aload           6
        //   198: athrow         
        //   199: invokestatic    com/google/android/gms/ads/internal/zzbv.zzeo:()Lcom/google/android/gms/internal/ads/zzajm;
        //   202: invokevirtual   com/google/android/gms/internal/ads/zzajm.zzqh:()Lcom/google/android/gms/internal/ads/zzakd;
        //   205: aload_0        
        //   206: getfield        com/google/android/gms/internal/ads/zzadk.zzbuc:Lcom/google/android/gms/internal/ads/zzaef;
        //   209: getfield        com/google/android/gms/internal/ads/zzaef.zzacp:Ljava/lang/String;
        //   212: invokevirtual   com/google/android/gms/internal/ads/zzakd.zzcq:(Ljava/lang/String;)V
        //   215: goto            99
        //   218: aload_0        
        //   219: getfield        com/google/android/gms/internal/ads/zzadk.zzbzf:Lcom/google/android/gms/internal/ads/zzaej;
        //   222: getfield        com/google/android/gms/internal/ads/zzaej.errorCode:I
        //   225: bipush          -3
        //   227: if_icmpeq       384
        //   230: aload_0        
        //   231: getfield        com/google/android/gms/internal/ads/zzadk.zzbzf:Lcom/google/android/gms/internal/ads/zzaej;
        //   234: getfield        com/google/android/gms/internal/ads/zzaej.zzceo:Ljava/lang/String;
        //   237: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   240: ifeq            255
        //   243: new             Lcom/google/android/gms/internal/ads/zzadu;
        //   246: dup            
        //   247: ldc_w           "No fill from ad server."
        //   250: iconst_3       
        //   251: invokespecial   com/google/android/gms/internal/ads/zzadu.<init>:(Ljava/lang/String;I)V
        //   254: athrow         
        //   255: invokestatic    com/google/android/gms/ads/internal/zzbv.zzeo:()Lcom/google/android/gms/internal/ads/zzajm;
        //   258: invokevirtual   com/google/android/gms/internal/ads/zzajm.zzqh:()Lcom/google/android/gms/internal/ads/zzakd;
        //   261: aload_0        
        //   262: getfield        com/google/android/gms/internal/ads/zzadk.zzbzf:Lcom/google/android/gms/internal/ads/zzaej;
        //   265: getfield        com/google/android/gms/internal/ads/zzaej.zzcdd:Z
        //   268: invokevirtual   com/google/android/gms/internal/ads/zzakd.zzab:(Z)V
        //   271: aload_0        
        //   272: getfield        com/google/android/gms/internal/ads/zzadk.zzbzf:Lcom/google/android/gms/internal/ads/zzaej;
        //   275: getfield        com/google/android/gms/internal/ads/zzaej.zzceq:Z
        //   278: istore          5
        //   280: iload           5
        //   282: ifeq            697
        //   285: aload_0        
        //   286: new             Lcom/google/android/gms/internal/ads/zzwy;
        //   289: dup            
        //   290: aload_0        
        //   291: getfield        com/google/android/gms/internal/ads/zzadk.zzbzf:Lcom/google/android/gms/internal/ads/zzaej;
        //   294: getfield        com/google/android/gms/internal/ads/zzaej.zzceo:Ljava/lang/String;
        //   297: invokespecial   com/google/android/gms/internal/ads/zzwy.<init>:(Ljava/lang/String;)V
        //   300: putfield        com/google/android/gms/internal/ads/zzadk.zzbtj:Lcom/google/android/gms/internal/ads/zzwy;
        //   303: invokestatic    com/google/android/gms/ads/internal/zzbv.zzeo:()Lcom/google/android/gms/internal/ads/zzajm;
        //   306: aload_0        
        //   307: getfield        com/google/android/gms/internal/ads/zzadk.zzbtj:Lcom/google/android/gms/internal/ads/zzwy;
        //   310: getfield        com/google/android/gms/internal/ads/zzwy.zzbss:Z
        //   313: invokevirtual   com/google/android/gms/internal/ads/zzajm.zzaa:(Z)V
        //   316: aload_0        
        //   317: getfield        com/google/android/gms/internal/ads/zzadk.zzbzf:Lcom/google/android/gms/internal/ads/zzaej;
        //   320: getfield        com/google/android/gms/internal/ads/zzaej.zzcds:Ljava/lang/String;
        //   323: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   326: ifne            384
        //   329: getstatic       com/google/android/gms/internal/ads/zznk.zzbdj:Lcom/google/android/gms/internal/ads/zzna;
        //   332: astore_1       
        //   333: invokestatic    com/google/android/gms/internal/ads/zzkb.zzik:()Lcom/google/android/gms/internal/ads/zzni;
        //   336: aload_1        
        //   337: invokevirtual   com/google/android/gms/internal/ads/zzni.zzd:(Lcom/google/android/gms/internal/ads/zzna;)Ljava/lang/Object;
        //   340: checkcast       Ljava/lang/Boolean;
        //   343: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   346: ifeq            384
        //   349: ldc_w           "Received cookie from server. Setting webview cookie in CookieManager."
        //   352: invokestatic    com/google/android/gms/internal/ads/zzakb.zzck:(Ljava/lang/String;)V
        //   355: invokestatic    com/google/android/gms/ads/internal/zzbv.zzem:()Lcom/google/android/gms/internal/ads/zzakq;
        //   358: aload_0        
        //   359: getfield        com/google/android/gms/internal/ads/zzadk.mContext:Landroid/content/Context;
        //   362: invokevirtual   com/google/android/gms/internal/ads/zzakq.zzax:(Landroid/content/Context;)Landroid/webkit/CookieManager;
        //   365: astore_1       
        //   366: aload_1        
        //   367: ifnull          384
        //   370: aload_1        
        //   371: ldc_w           "googleads.g.doubleclick.net"
        //   374: aload_0        
        //   375: getfield        com/google/android/gms/internal/ads/zzadk.zzbzf:Lcom/google/android/gms/internal/ads/zzaej;
        //   378: getfield        com/google/android/gms/internal/ads/zzaej.zzcds:Ljava/lang/String;
        //   381: invokevirtual   android/webkit/CookieManager.setCookie:(Ljava/lang/String;Ljava/lang/String;)V
        //   384: aload_0        
        //   385: getfield        com/google/android/gms/internal/ads/zzadk.zzbuc:Lcom/google/android/gms/internal/ads/zzaef;
        //   388: getfield        com/google/android/gms/internal/ads/zzaef.zzacv:Lcom/google/android/gms/internal/ads/zzjn;
        //   391: getfield        com/google/android/gms/internal/ads/zzjn.zzard:[Lcom/google/android/gms/internal/ads/zzjn;
        //   394: ifnull          768
        //   397: aload_0        
        //   398: aload_0        
        //   399: getfield        com/google/android/gms/internal/ads/zzadk.zzbuc:Lcom/google/android/gms/internal/ads/zzaef;
        //   402: invokespecial   com/google/android/gms/internal/ads/zzadk.zza:(Lcom/google/android/gms/internal/ads/zzaef;)Lcom/google/android/gms/internal/ads/zzjn;
        //   405: astore          6
        //   407: invokestatic    com/google/android/gms/ads/internal/zzbv.zzeo:()Lcom/google/android/gms/internal/ads/zzajm;
        //   410: invokevirtual   com/google/android/gms/internal/ads/zzajm.zzqh:()Lcom/google/android/gms/internal/ads/zzakd;
        //   413: aload_0        
        //   414: getfield        com/google/android/gms/internal/ads/zzadk.zzbzf:Lcom/google/android/gms/internal/ads/zzaej;
        //   417: getfield        com/google/android/gms/internal/ads/zzaej.zzcfa:Z
        //   420: invokevirtual   com/google/android/gms/internal/ads/zzakd.zzac:(Z)V
        //   423: invokestatic    com/google/android/gms/ads/internal/zzbv.zzeo:()Lcom/google/android/gms/internal/ads/zzajm;
        //   426: invokevirtual   com/google/android/gms/internal/ads/zzajm.zzqh:()Lcom/google/android/gms/internal/ads/zzakd;
        //   429: aload_0        
        //   430: getfield        com/google/android/gms/internal/ads/zzadk.zzbzf:Lcom/google/android/gms/internal/ads/zzaej;
        //   433: getfield        com/google/android/gms/internal/ads/zzaej.zzcfm:Z
        //   436: invokevirtual   com/google/android/gms/internal/ads/zzakd.zzad:(Z)V
        //   439: aload_0        
        //   440: getfield        com/google/android/gms/internal/ads/zzadk.zzbzf:Lcom/google/android/gms/internal/ads/zzaej;
        //   443: getfield        com/google/android/gms/internal/ads/zzaej.zzcey:Ljava/lang/String;
        //   446: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   449: ifne            721
        //   452: new             Lorg/json/JSONObject;
        //   455: dup            
        //   456: aload_0        
        //   457: getfield        com/google/android/gms/internal/ads/zzadk.zzbzf:Lcom/google/android/gms/internal/ads/zzaej;
        //   460: getfield        com/google/android/gms/internal/ads/zzaej.zzcey:Ljava/lang/String;
        //   463: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //   466: astore          7
        //   468: aload           8
        //   470: astore_1       
        //   471: aload_0        
        //   472: getfield        com/google/android/gms/internal/ads/zzadk.zzbzf:Lcom/google/android/gms/internal/ads/zzaej;
        //   475: getfield        com/google/android/gms/internal/ads/zzaej.zzcfo:I
        //   478: iconst_2       
        //   479: if_icmpne       539
        //   482: aload_0        
        //   483: getfield        com/google/android/gms/internal/ads/zzadk.zzbuc:Lcom/google/android/gms/internal/ads/zzaef;
        //   486: getfield        com/google/android/gms/internal/ads/zzaef.zzccv:Lcom/google/android/gms/internal/ads/zzjj;
        //   489: astore_1       
        //   490: aload_1        
        //   491: getfield        com/google/android/gms/internal/ads/zzjj.zzaqg:Landroid/os/Bundle;
        //   494: ifnull          727
        //   497: aload_1        
        //   498: getfield        com/google/android/gms/internal/ads/zzjj.zzaqg:Landroid/os/Bundle;
        //   501: astore_1       
        //   502: aload_1        
        //   503: ldc_w           Lcom/google/ads/mediation/admob/AdMobAdapter;.class
        //   506: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //   509: invokevirtual   android/os/Bundle.getBundle:(Ljava/lang/String;)Landroid/os/Bundle;
        //   512: ifnull          738
        //   515: aload_1        
        //   516: ldc_w           Lcom/google/ads/mediation/admob/AdMobAdapter;.class
        //   519: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //   522: invokevirtual   android/os/Bundle.getBundle:(Ljava/lang/String;)Landroid/os/Bundle;
        //   525: astore_1       
        //   526: aload_1        
        //   527: ldc_w           "render_test_label"
        //   530: iconst_1       
        //   531: invokevirtual   android/os/Bundle.putBoolean:(Ljava/lang/String;Z)V
        //   534: iconst_1       
        //   535: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   538: astore_1       
        //   539: aload_0        
        //   540: getfield        com/google/android/gms/internal/ads/zzadk.zzbzf:Lcom/google/android/gms/internal/ads/zzaej;
        //   543: getfield        com/google/android/gms/internal/ads/zzaej.zzcfo:I
        //   546: iconst_1       
        //   547: if_icmpne       555
        //   550: iconst_0       
        //   551: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   554: astore_1       
        //   555: aload_0        
        //   556: getfield        com/google/android/gms/internal/ads/zzadk.zzbzf:Lcom/google/android/gms/internal/ads/zzaej;
        //   559: getfield        com/google/android/gms/internal/ads/zzaej.zzcfo:I
        //   562: ifne            765
        //   565: aload_0        
        //   566: getfield        com/google/android/gms/internal/ads/zzadk.zzbuc:Lcom/google/android/gms/internal/ads/zzaef;
        //   569: getfield        com/google/android/gms/internal/ads/zzaef.zzccv:Lcom/google/android/gms/internal/ads/zzjj;
        //   572: invokestatic    com/google/android/gms/internal/ads/zzamm.zzo:(Lcom/google/android/gms/internal/ads/zzjj;)Z
        //   575: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   578: astore_1       
        //   579: new             Lcom/google/android/gms/internal/ads/zzaji;
        //   582: dup            
        //   583: aload_0        
        //   584: getfield        com/google/android/gms/internal/ads/zzadk.zzbuc:Lcom/google/android/gms/internal/ads/zzaef;
        //   587: aload_0        
        //   588: getfield        com/google/android/gms/internal/ads/zzadk.zzbzf:Lcom/google/android/gms/internal/ads/zzaej;
        //   591: aload_0        
        //   592: getfield        com/google/android/gms/internal/ads/zzadk.zzbtj:Lcom/google/android/gms/internal/ads/zzwy;
        //   595: aload           6
        //   597: bipush          -2
        //   599: lload_3        
        //   600: aload_0        
        //   601: getfield        com/google/android/gms/internal/ads/zzadk.zzbzf:Lcom/google/android/gms/internal/ads/zzaej;
        //   604: getfield        com/google/android/gms/internal/ads/zzaej.zzceu:J
        //   607: aload           7
        //   609: aload_0        
        //   610: getfield        com/google/android/gms/internal/ads/zzadk.zzcch:Lcom/google/android/gms/internal/ads/zzhs;
        //   613: aload_1        
        //   614: invokespecial   com/google/android/gms/internal/ads/zzaji.<init>:(Lcom/google/android/gms/internal/ads/zzaef;Lcom/google/android/gms/internal/ads/zzaej;Lcom/google/android/gms/internal/ads/zzwy;Lcom/google/android/gms/internal/ads/zzjn;IJJLorg/json/JSONObject;Lcom/google/android/gms/internal/ads/zzhs;Ljava/lang/Boolean;)V
        //   617: astore_1       
        //   618: aload_0        
        //   619: getfield        com/google/android/gms/internal/ads/zzadk.zzccf:Lcom/google/android/gms/internal/ads/zzadj;
        //   622: aload_1        
        //   623: invokeinterface com/google/android/gms/internal/ads/zzadj.zza:(Lcom/google/android/gms/internal/ads/zzaji;)V
        //   628: getstatic       com/google/android/gms/internal/ads/zzakk.zzcrm:Landroid/os/Handler;
        //   631: aload_0        
        //   632: getfield        com/google/android/gms/internal/ads/zzadk.zzbzg:Ljava/lang/Runnable;
        //   635: invokevirtual   android/os/Handler.removeCallbacks:(Ljava/lang/Runnable;)V
        //   638: return         
        //   639: astore_1       
        //   640: ldc_w           "Could not parse mediation config."
        //   643: aload_1        
        //   644: invokestatic    com/google/android/gms/internal/ads/zzakb.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   647: aload_0        
        //   648: getfield        com/google/android/gms/internal/ads/zzadk.zzbzf:Lcom/google/android/gms/internal/ads/zzaej;
        //   651: getfield        com/google/android/gms/internal/ads/zzaej.zzceo:Ljava/lang/String;
        //   654: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   657: astore_1       
        //   658: aload_1        
        //   659: invokevirtual   java/lang/String.length:()I
        //   662: ifeq            683
        //   665: ldc_w           "Could not parse mediation config: "
        //   668: aload_1        
        //   669: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   672: astore_1       
        //   673: new             Lcom/google/android/gms/internal/ads/zzadu;
        //   676: dup            
        //   677: aload_1        
        //   678: iconst_0       
        //   679: invokespecial   com/google/android/gms/internal/ads/zzadu.<init>:(Ljava/lang/String;I)V
        //   682: athrow         
        //   683: new             Ljava/lang/String;
        //   686: dup            
        //   687: ldc_w           "Could not parse mediation config: "
        //   690: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //   693: astore_1       
        //   694: goto            673
        //   697: invokestatic    com/google/android/gms/ads/internal/zzbv.zzeo:()Lcom/google/android/gms/internal/ads/zzajm;
        //   700: aload_0        
        //   701: getfield        com/google/android/gms/internal/ads/zzadk.zzbzf:Lcom/google/android/gms/internal/ads/zzaej;
        //   704: getfield        com/google/android/gms/internal/ads/zzaej.zzbss:Z
        //   707: invokevirtual   com/google/android/gms/internal/ads/zzajm.zzaa:(Z)V
        //   710: goto            316
        //   713: astore_1       
        //   714: ldc_w           "Error parsing the JSON for Active View."
        //   717: aload_1        
        //   718: invokestatic    com/google/android/gms/internal/ads/zzakb.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   721: aconst_null    
        //   722: astore          7
        //   724: goto            468
        //   727: new             Landroid/os/Bundle;
        //   730: dup            
        //   731: invokespecial   android/os/Bundle.<init>:()V
        //   734: astore_1       
        //   735: goto            502
        //   738: new             Landroid/os/Bundle;
        //   741: dup            
        //   742: invokespecial   android/os/Bundle.<init>:()V
        //   745: astore          8
        //   747: aload_1        
        //   748: ldc_w           Lcom/google/ads/mediation/admob/AdMobAdapter;.class
        //   751: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //   754: aload           8
        //   756: invokevirtual   android/os/Bundle.putBundle:(Ljava/lang/String;Landroid/os/Bundle;)V
        //   759: aload           8
        //   761: astore_1       
        //   762: goto            526
        //   765: goto            579
        //   768: aconst_null    
        //   769: astore          6
        //   771: goto            407
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                       
        //  -----  -----  -----  -----  -------------------------------------------
        //  30     37     192    199    Any
        //  99     168    168    192    Lcom/google/android/gms/internal/ads/zzadu;
        //  194    196    192    199    Any
        //  218    255    168    192    Lcom/google/android/gms/internal/ads/zzadu;
        //  255    280    168    192    Lcom/google/android/gms/internal/ads/zzadu;
        //  285    316    639    697    Lorg/json/JSONException;
        //  285    316    168    192    Lcom/google/android/gms/internal/ads/zzadu;
        //  316    366    168    192    Lcom/google/android/gms/internal/ads/zzadu;
        //  370    384    168    192    Lcom/google/android/gms/internal/ads/zzadu;
        //  384    407    168    192    Lcom/google/android/gms/internal/ads/zzadu;
        //  452    468    713    721    Ljava/lang/Exception;
        //  640    673    168    192    Lcom/google/android/gms/internal/ads/zzadu;
        //  673    683    168    192    Lcom/google/android/gms/internal/ads/zzadu;
        //  683    694    168    192    Lcom/google/android/gms/internal/ads/zzadu;
        //  697    710    168    192    Lcom/google/android/gms/internal/ads/zzadu;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0468:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    public final void zzdn() {
        zzakb.zzck("AdLoaderBackgroundTask started.");
        this.zzbzg = new zzads(this);
        zzakk.zzcrm.postDelayed(this.zzbzg, (long)zzkb.zzik().zzd(zznk.zzban));
        final long elapsedRealtime = zzbv.zzer().elapsedRealtime();
        if ((boolean)zzkb.zzik().zzd(zznk.zzbak) && this.zzccg.zzccv.extras != null) {
            final String string = this.zzccg.zzccv.extras.getString("_ad");
            if (string != null) {
                this.zzbuc = new zzaef(this.zzccg, elapsedRealtime, null, null, null);
                this.zza(zzafs.zza(this.mContext, this.zzbuc, string));
                return;
            }
        }
        final zzaop<zzaef> zzaop = new zzaop<zzaef>();
        zzaki.zzb(new zzadt(this, zzaop));
        final String zzz = zzbv.zzfh().zzz(this.mContext);
        final String zzaa = zzbv.zzfh().zzaa(this.mContext);
        final String zzab = zzbv.zzfh().zzab(this.mContext);
        zzbv.zzfh().zzg(this.mContext, zzab);
        zzaop.zzk(this.zzbuc = new zzaef(this.zzccg, elapsedRealtime, zzz, zzaa, zzab));
    }
}
