// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Iterator;
import org.json.JSONObject;
import android.text.TextUtils;
import android.support.annotation.Nullable;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import android.content.Context;

@zzadh
public final class zzahx extends zzajx implements zzahw
{
    private final Context mContext;
    private final Object mLock;
    private final zzaji zzbze;
    private final long zzclp;
    private final ArrayList<zzahn> zzcmd;
    private final List<zzahq> zzcme;
    private final HashSet<String> zzcmf;
    private final zzago zzcmg;
    
    public zzahx(final Context context, final zzaji zzaji, final zzago zzago) {
        this(context, zzaji, zzago, (long)zzkb.zzik().zzd(zznk.zzaye));
    }
    
    @VisibleForTesting
    private zzahx(final Context mContext, final zzaji zzbze, final zzago zzcmg, final long zzclp) {
        this.zzcmd = new ArrayList<zzahn>();
        this.zzcme = new ArrayList<zzahq>();
        this.zzcmf = new HashSet<String>();
        this.mLock = new Object();
        this.mContext = mContext;
        this.zzbze = zzbze;
        this.zzcmg = zzcmg;
        this.zzclp = zzclp;
    }
    
    private final zzajh zza(final int n, @Nullable final String s, @Nullable final zzwx zzwx) {
        final zzjj zzccv = this.zzbze.zzcgs.zzccv;
        final List<String> zzbsn = this.zzbze.zzcos.zzbsn;
        final List<String> zzbso = this.zzbze.zzcos.zzbso;
        final List<String> zzces = this.zzbze.zzcos.zzces;
        final int orientation = this.zzbze.zzcos.orientation;
        final long zzbsu = this.zzbze.zzcos.zzbsu;
        final String zzccy = this.zzbze.zzcgs.zzccy;
        final boolean zzceq = this.zzbze.zzcos.zzceq;
        final zzwy zzcod = this.zzbze.zzcod;
        final long zzcer = this.zzbze.zzcos.zzcer;
        final zzjn zzacv = this.zzbze.zzacv;
        final long zzcep = this.zzbze.zzcos.zzcep;
        final long zzcoh = this.zzbze.zzcoh;
        final long zzceu = this.zzbze.zzcos.zzceu;
        final String zzcev = this.zzbze.zzcos.zzcev;
        final JSONObject zzcob = this.zzbze.zzcob;
        final zzaig zzcfe = this.zzbze.zzcos.zzcfe;
        final List<String> zzcff = this.zzbze.zzcos.zzcff;
        final List<String> zzcfg = this.zzbze.zzcos.zzcfg;
        final boolean zzcfh = this.zzbze.zzcos.zzcfh;
        final zzael zzcfi = this.zzbze.zzcos.zzcfi;
        final StringBuilder sb = new StringBuilder("");
        String s2;
        if (this.zzcme == null) {
            s2 = sb.toString();
        }
        else {
            for (final zzahq zzahq : this.zzcme) {
                if (zzahq != null && !TextUtils.isEmpty((CharSequence)zzahq.zzbru)) {
                    final String zzbru = zzahq.zzbru;
                    int n2 = 0;
                    switch (zzahq.errorCode) {
                        default: {
                            n2 = 6;
                            break;
                        }
                        case 6: {
                            n2 = 0;
                            break;
                        }
                        case 3: {
                            n2 = 1;
                            break;
                        }
                        case 4: {
                            n2 = 2;
                            break;
                        }
                        case 7: {
                            n2 = 3;
                            break;
                        }
                        case 5: {
                            n2 = 4;
                            break;
                        }
                    }
                    sb.append(String.valueOf(new StringBuilder(String.valueOf(zzbru).length() + 33).append(zzbru).append(".").append(n2).append(".").append(zzahq.zzbub).toString()).concat("_"));
                }
            }
            s2 = sb.substring(0, Math.max(0, sb.length() - 1));
        }
        return new zzajh(zzccv, null, zzbsn, n, zzbso, zzces, orientation, zzbsu, zzccy, zzceq, zzwx, null, s, zzcod, null, zzcer, zzacv, zzcep, zzcoh, zzceu, zzcev, zzcob, null, zzcfe, zzcff, zzcfg, zzcfh, zzcfi, s2, this.zzbze.zzcos.zzbsr, this.zzbze.zzcos.zzcfl, this.zzbze.zzcoq, this.zzbze.zzcos.zzzl, this.zzbze.zzcor, this.zzbze.zzcos.zzcfp, this.zzbze.zzcos.zzbsp, this.zzbze.zzcos.zzzm, this.zzbze.zzcos.zzcfq);
    }
    
    @Override
    public final void onStop() {
    }
    
    @Override
    public final void zza(final String s, final int n) {
    }
    
    @Override
    public final void zzcb(final String s) {
        synchronized (this.mLock) {
            this.zzcmf.add(s);
        }
    }
    
    @Override
    public final void zzdn() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/internal/ads/zzahx.zzbze:Lcom/google/android/gms/internal/ads/zzaji;
        //     4: getfield        com/google/android/gms/internal/ads/zzaji.zzcod:Lcom/google/android/gms/internal/ads/zzwy;
        //     7: getfield        com/google/android/gms/internal/ads/zzwy.zzbsm:Ljava/util/List;
        //    10: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    15: astore          5
        //    17: aload           5
        //    19: invokeinterface java/util/Iterator.hasNext:()Z
        //    24: ifeq            287
        //    27: aload           5
        //    29: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    34: checkcast       Lcom/google/android/gms/internal/ads/zzwx;
        //    37: astore          6
        //    39: aload           6
        //    41: getfield        com/google/android/gms/internal/ads/zzwx.zzbsb:Ljava/lang/String;
        //    44: astore          7
        //    46: aload           6
        //    48: getfield        com/google/android/gms/internal/ads/zzwx.zzbrt:Ljava/util/List;
        //    51: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    56: astore          8
        //    58: aload           8
        //    60: invokeinterface java/util/Iterator.hasNext:()Z
        //    65: ifeq            17
        //    68: aload           8
        //    70: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    75: checkcast       Ljava/lang/String;
        //    78: astore          4
        //    80: ldc_w           "com.google.android.gms.ads.mediation.customevent.CustomEventAdapter"
        //    83: aload           4
        //    85: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    88: ifne            102
        //    91: ldc_w           "com.google.ads.mediation.customevent.CustomEventAdapter"
        //    94: aload           4
        //    96: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    99: ifeq            731
        //   102: new             Lorg/json/JSONObject;
        //   105: dup            
        //   106: aload           7
        //   108: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //   111: ldc_w           "class_name"
        //   114: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   117: astore          4
        //   119: aload_0        
        //   120: getfield        com/google/android/gms/internal/ads/zzahx.mLock:Ljava/lang/Object;
        //   123: astore          9
        //   125: aload           9
        //   127: monitorenter   
        //   128: aload_0        
        //   129: getfield        com/google/android/gms/internal/ads/zzahx.zzcmg:Lcom/google/android/gms/internal/ads/zzago;
        //   132: aload           4
        //   134: invokevirtual   com/google/android/gms/internal/ads/zzago.zzca:(Ljava/lang/String;)Lcom/google/android/gms/internal/ads/zzaib;
        //   137: astore          10
        //   139: aload           10
        //   141: ifnull          160
        //   144: aload           10
        //   146: invokevirtual   com/google/android/gms/internal/ads/zzaib.zzpf:()Lcom/google/android/gms/internal/ads/zzahv;
        //   149: ifnull          160
        //   152: aload           10
        //   154: invokevirtual   com/google/android/gms/internal/ads/zzaib.zzpe:()Lcom/google/android/gms/internal/ads/zzxq;
        //   157: ifnonnull       229
        //   160: aload_0        
        //   161: getfield        com/google/android/gms/internal/ads/zzahx.zzcme:Ljava/util/List;
        //   164: new             Lcom/google/android/gms/internal/ads/zzahs;
        //   167: dup            
        //   168: invokespecial   com/google/android/gms/internal/ads/zzahs.<init>:()V
        //   171: aload           6
        //   173: getfield        com/google/android/gms/internal/ads/zzwx.zzbru:Ljava/lang/String;
        //   176: invokevirtual   com/google/android/gms/internal/ads/zzahs.zzcd:(Ljava/lang/String;)Lcom/google/android/gms/internal/ads/zzahs;
        //   179: aload           4
        //   181: invokevirtual   com/google/android/gms/internal/ads/zzahs.zzcc:(Ljava/lang/String;)Lcom/google/android/gms/internal/ads/zzahs;
        //   184: lconst_0       
        //   185: invokevirtual   com/google/android/gms/internal/ads/zzahs.zzg:(J)Lcom/google/android/gms/internal/ads/zzahs;
        //   188: bipush          7
        //   190: invokevirtual   com/google/android/gms/internal/ads/zzahs.zzad:(I)Lcom/google/android/gms/internal/ads/zzahs;
        //   193: invokevirtual   com/google/android/gms/internal/ads/zzahs.zzpd:()Lcom/google/android/gms/internal/ads/zzahq;
        //   196: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   201: pop            
        //   202: aload           9
        //   204: monitorexit    
        //   205: goto            58
        //   208: astore          4
        //   210: aload           9
        //   212: monitorexit    
        //   213: aload           4
        //   215: athrow         
        //   216: astore          4
        //   218: ldc_w           "Unable to determine custom event class name, skipping..."
        //   221: aload           4
        //   223: invokestatic    com/google/android/gms/internal/ads/zzakb.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   226: goto            58
        //   229: new             Lcom/google/android/gms/internal/ads/zzahn;
        //   232: dup            
        //   233: aload_0        
        //   234: getfield        com/google/android/gms/internal/ads/zzahx.mContext:Landroid/content/Context;
        //   237: aload           4
        //   239: aload           7
        //   241: aload           6
        //   243: aload_0        
        //   244: getfield        com/google/android/gms/internal/ads/zzahx.zzbze:Lcom/google/android/gms/internal/ads/zzaji;
        //   247: aload           10
        //   249: aload_0        
        //   250: aload_0        
        //   251: getfield        com/google/android/gms/internal/ads/zzahx.zzclp:J
        //   254: invokespecial   com/google/android/gms/internal/ads/zzahn.<init>:(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Lcom/google/android/gms/internal/ads/zzwx;Lcom/google/android/gms/internal/ads/zzaji;Lcom/google/android/gms/internal/ads/zzaib;Lcom/google/android/gms/internal/ads/zzahw;J)V
        //   257: astore          4
        //   259: aload           4
        //   261: aload_0        
        //   262: getfield        com/google/android/gms/internal/ads/zzahx.zzcmg:Lcom/google/android/gms/internal/ads/zzago;
        //   265: invokevirtual   com/google/android/gms/internal/ads/zzago.zzos:()Lcom/google/android/gms/ads/internal/gmsg/zzb;
        //   268: invokevirtual   com/google/android/gms/internal/ads/zzahn.zza:(Lcom/google/android/gms/ads/internal/gmsg/zzb;)V
        //   271: aload_0        
        //   272: getfield        com/google/android/gms/internal/ads/zzahx.zzcmd:Ljava/util/ArrayList;
        //   275: aload           4
        //   277: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   280: pop            
        //   281: aload           9
        //   283: monitorexit    
        //   284: goto            58
        //   287: new             Ljava/util/HashSet;
        //   290: dup            
        //   291: invokespecial   java/util/HashSet.<init>:()V
        //   294: astore          4
        //   296: aload_0        
        //   297: getfield        com/google/android/gms/internal/ads/zzahx.zzcmd:Ljava/util/ArrayList;
        //   300: checkcast       Ljava/util/ArrayList;
        //   303: astore          5
        //   305: aload           5
        //   307: invokevirtual   java/util/ArrayList.size:()I
        //   310: istore_3       
        //   311: iconst_0       
        //   312: istore_1       
        //   313: iload_1        
        //   314: iload_3        
        //   315: if_icmpge       365
        //   318: aload           5
        //   320: iload_1        
        //   321: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //   324: astore          6
        //   326: iload_1        
        //   327: iconst_1       
        //   328: iadd           
        //   329: istore_2       
        //   330: aload           6
        //   332: checkcast       Lcom/google/android/gms/internal/ads/zzahn;
        //   335: astore          6
        //   337: iload_2        
        //   338: istore_1       
        //   339: aload           4
        //   341: aload           6
        //   343: getfield        com/google/android/gms/internal/ads/zzahn.zzbth:Ljava/lang/String;
        //   346: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   351: ifeq            313
        //   354: aload           6
        //   356: invokevirtual   com/google/android/gms/internal/ads/zzahn.zzoz:()Ljava/util/concurrent/Future;
        //   359: pop            
        //   360: iload_2        
        //   361: istore_1       
        //   362: goto            313
        //   365: aload_0        
        //   366: getfield        com/google/android/gms/internal/ads/zzahx.zzcmd:Ljava/util/ArrayList;
        //   369: checkcast       Ljava/util/ArrayList;
        //   372: astore          5
        //   374: aload           5
        //   376: invokevirtual   java/util/ArrayList.size:()I
        //   379: istore_2       
        //   380: iconst_0       
        //   381: istore_1       
        //   382: iload_1        
        //   383: iload_2        
        //   384: if_icmpge       572
        //   387: aload           5
        //   389: iload_1        
        //   390: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //   393: astore          4
        //   395: iload_1        
        //   396: iconst_1       
        //   397: iadd           
        //   398: istore_1       
        //   399: aload           4
        //   401: checkcast       Lcom/google/android/gms/internal/ads/zzahn;
        //   404: astore          4
        //   406: aload           4
        //   408: invokevirtual   com/google/android/gms/internal/ads/zzahn.zzoz:()Ljava/util/concurrent/Future;
        //   411: invokeinterface java/util/concurrent/Future.get:()Ljava/lang/Object;
        //   416: pop            
        //   417: aload_0        
        //   418: getfield        com/google/android/gms/internal/ads/zzahx.mLock:Ljava/lang/Object;
        //   421: astore          6
        //   423: aload           6
        //   425: monitorenter   
        //   426: aload           4
        //   428: getfield        com/google/android/gms/internal/ads/zzahn.zzbth:Ljava/lang/String;
        //   431: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   434: ifne            452
        //   437: aload_0        
        //   438: getfield        com/google/android/gms/internal/ads/zzahx.zzcme:Ljava/util/List;
        //   441: aload           4
        //   443: invokevirtual   com/google/android/gms/internal/ads/zzahn.zzpa:()Lcom/google/android/gms/internal/ads/zzahq;
        //   446: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   451: pop            
        //   452: aload           6
        //   454: monitorexit    
        //   455: aload_0        
        //   456: getfield        com/google/android/gms/internal/ads/zzahx.mLock:Ljava/lang/Object;
        //   459: astore          6
        //   461: aload           6
        //   463: monitorenter   
        //   464: aload_0        
        //   465: getfield        com/google/android/gms/internal/ads/zzahx.zzcmf:Ljava/util/HashSet;
        //   468: aload           4
        //   470: getfield        com/google/android/gms/internal/ads/zzahn.zzbth:Ljava/lang/String;
        //   473: invokevirtual   java/util/HashSet.contains:(Ljava/lang/Object;)Z
        //   476: ifeq            717
        //   479: aload_0        
        //   480: bipush          -2
        //   482: aload           4
        //   484: getfield        com/google/android/gms/internal/ads/zzahn.zzbth:Ljava/lang/String;
        //   487: aload           4
        //   489: invokevirtual   com/google/android/gms/internal/ads/zzahn.zzpb:()Lcom/google/android/gms/internal/ads/zzwx;
        //   492: invokespecial   com/google/android/gms/internal/ads/zzahx.zza:(ILjava/lang/String;Lcom/google/android/gms/internal/ads/zzwx;)Lcom/google/android/gms/internal/ads/zzajh;
        //   495: astore          4
        //   497: getstatic       com/google/android/gms/internal/ads/zzamu.zzsy:Landroid/os/Handler;
        //   500: new             Lcom/google/android/gms/internal/ads/zzahy;
        //   503: dup            
        //   504: aload_0        
        //   505: aload           4
        //   507: invokespecial   com/google/android/gms/internal/ads/zzahy.<init>:(Lcom/google/android/gms/internal/ads/zzahx;Lcom/google/android/gms/internal/ads/zzajh;)V
        //   510: invokevirtual   android/os/Handler.post:(Ljava/lang/Runnable;)Z
        //   513: pop            
        //   514: aload           6
        //   516: monitorexit    
        //   517: return         
        //   518: astore          4
        //   520: aload           6
        //   522: monitorexit    
        //   523: aload           4
        //   525: athrow         
        //   526: astore          5
        //   528: invokestatic    java/lang/Thread.currentThread:()Ljava/lang/Thread;
        //   531: invokevirtual   java/lang/Thread.interrupt:()V
        //   534: aload_0        
        //   535: getfield        com/google/android/gms/internal/ads/zzahx.mLock:Ljava/lang/Object;
        //   538: astore          5
        //   540: aload           5
        //   542: monitorenter   
        //   543: aload           4
        //   545: getfield        com/google/android/gms/internal/ads/zzahn.zzbth:Ljava/lang/String;
        //   548: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   551: ifne            569
        //   554: aload_0        
        //   555: getfield        com/google/android/gms/internal/ads/zzahx.zzcme:Ljava/util/List;
        //   558: aload           4
        //   560: invokevirtual   com/google/android/gms/internal/ads/zzahn.zzpa:()Lcom/google/android/gms/internal/ads/zzahq;
        //   563: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   568: pop            
        //   569: aload           5
        //   571: monitorexit    
        //   572: aload_0        
        //   573: iconst_3       
        //   574: aconst_null    
        //   575: aconst_null    
        //   576: invokespecial   com/google/android/gms/internal/ads/zzahx.zza:(ILjava/lang/String;Lcom/google/android/gms/internal/ads/zzwx;)Lcom/google/android/gms/internal/ads/zzajh;
        //   579: astore          4
        //   581: getstatic       com/google/android/gms/internal/ads/zzamu.zzsy:Landroid/os/Handler;
        //   584: new             Lcom/google/android/gms/internal/ads/zzahz;
        //   587: dup            
        //   588: aload_0        
        //   589: aload           4
        //   591: invokespecial   com/google/android/gms/internal/ads/zzahz.<init>:(Lcom/google/android/gms/internal/ads/zzahx;Lcom/google/android/gms/internal/ads/zzajh;)V
        //   594: invokevirtual   android/os/Handler.post:(Ljava/lang/Runnable;)Z
        //   597: pop            
        //   598: return         
        //   599: astore          4
        //   601: aload           5
        //   603: monitorexit    
        //   604: aload           4
        //   606: athrow         
        //   607: astore          6
        //   609: ldc_w           "Unable to resolve rewarded adapter."
        //   612: aload           6
        //   614: invokestatic    com/google/android/gms/internal/ads/zzakb.zzc:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   617: aload_0        
        //   618: getfield        com/google/android/gms/internal/ads/zzahx.mLock:Ljava/lang/Object;
        //   621: astore          6
        //   623: aload           6
        //   625: monitorenter   
        //   626: aload           4
        //   628: getfield        com/google/android/gms/internal/ads/zzahn.zzbth:Ljava/lang/String;
        //   631: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   634: ifne            652
        //   637: aload_0        
        //   638: getfield        com/google/android/gms/internal/ads/zzahx.zzcme:Ljava/util/List;
        //   641: aload           4
        //   643: invokevirtual   com/google/android/gms/internal/ads/zzahn.zzpa:()Lcom/google/android/gms/internal/ads/zzahq;
        //   646: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   651: pop            
        //   652: aload           6
        //   654: monitorexit    
        //   655: goto            382
        //   658: astore          4
        //   660: aload           6
        //   662: monitorexit    
        //   663: aload           4
        //   665: athrow         
        //   666: astore          6
        //   668: aload_0        
        //   669: getfield        com/google/android/gms/internal/ads/zzahx.mLock:Ljava/lang/Object;
        //   672: astore          5
        //   674: aload           5
        //   676: monitorenter   
        //   677: aload           4
        //   679: getfield        com/google/android/gms/internal/ads/zzahn.zzbth:Ljava/lang/String;
        //   682: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   685: ifne            703
        //   688: aload_0        
        //   689: getfield        com/google/android/gms/internal/ads/zzahx.zzcme:Ljava/util/List;
        //   692: aload           4
        //   694: invokevirtual   com/google/android/gms/internal/ads/zzahn.zzpa:()Lcom/google/android/gms/internal/ads/zzahq;
        //   697: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   702: pop            
        //   703: aload           5
        //   705: monitorexit    
        //   706: aload           6
        //   708: athrow         
        //   709: astore          4
        //   711: aload           5
        //   713: monitorexit    
        //   714: aload           4
        //   716: athrow         
        //   717: aload           6
        //   719: monitorexit    
        //   720: goto            382
        //   723: astore          4
        //   725: aload           6
        //   727: monitorexit    
        //   728: aload           4
        //   730: athrow         
        //   731: goto            119
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  102    119    216    229    Lorg/json/JSONException;
        //  128    139    208    216    Any
        //  144    160    208    216    Any
        //  160    205    208    216    Any
        //  210    213    208    216    Any
        //  229    284    208    216    Any
        //  406    417    526    607    Ljava/lang/InterruptedException;
        //  406    417    607    666    Ljava/lang/Exception;
        //  406    417    666    717    Any
        //  426    452    518    526    Any
        //  452    455    518    526    Any
        //  464    517    723    731    Any
        //  520    523    518    526    Any
        //  528    534    666    717    Any
        //  543    569    599    607    Any
        //  569    572    599    607    Any
        //  601    604    599    607    Any
        //  609    617    666    717    Any
        //  626    652    658    666    Any
        //  652    655    658    666    Any
        //  660    663    658    666    Any
        //  677    703    709    717    Any
        //  703    706    709    717    Any
        //  711    714    709    717    Any
        //  717    720    723    731    Any
        //  725    728    723    731    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0569:
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
}
