// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import com.google.android.gms.ads.internal.zzbv;
import javax.annotation.concurrent.GuardedBy;

@zzadh
public final class zzhs
{
    private final zzhx zzakc;
    @GuardedBy("this")
    private final zzii zzakd;
    private final boolean zzake;
    
    private zzhs() {
        this.zzake = false;
        this.zzakc = new zzhx();
        this.zzakd = new zzii();
        this.zzhn();
    }
    
    public zzhs(final zzhx zzakc) {
        this.zzakc = zzakc;
        this.zzake = (boolean)zzkb.zzik().zzd(zznk.zzbeo);
        this.zzakd = new zzii();
        this.zzhn();
    }
    
    private final void zzb(final zzhu.zza.zzb zzb) {
        synchronized (this) {
            this.zzakd.zzanl = zzho();
            this.zzakc.zzd(zzbfi.zzb((zzbfi)this.zzakd)).zzs(zzb.zzhq()).zzbd();
            final String value = String.valueOf(Integer.toString(zzb.zzhq(), 10));
            String concat;
            if (value.length() != 0) {
                concat = "Logging Event with event code : ".concat(value);
            }
            else {
                concat = new String("Logging Event with event code : ");
            }
            zzakb.v(concat);
        }
    }
    
    private final void zzc(final zzhu.zza.zzb p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: monitorenter   
        //     2: invokestatic    android/os/Environment.getExternalStorageDirectory:()Ljava/io/File;
        //     5: astore_2       
        //     6: aload_2        
        //     7: ifnonnull       13
        //    10: aload_0        
        //    11: monitorexit    
        //    12: return         
        //    13: new             Ljava/io/File;
        //    16: dup            
        //    17: aload_2        
        //    18: ldc             "clearcut_events.txt"
        //    20: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //    23: astore_2       
        //    24: new             Ljava/io/FileOutputStream;
        //    27: dup            
        //    28: aload_2        
        //    29: iconst_1       
        //    30: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/File;Z)V
        //    33: astore_2       
        //    34: aload_2        
        //    35: aload_0        
        //    36: aload_1        
        //    37: invokespecial   com/google/android/gms/internal/ads/zzhs.zzd:(Lcom/google/android/gms/internal/ads/zzhu$zza$zzb;)Ljava/lang/String;
        //    40: invokevirtual   java/lang/String.getBytes:()[B
        //    43: invokevirtual   java/io/FileOutputStream.write:([B)V
        //    46: aload_2        
        //    47: bipush          10
        //    49: invokevirtual   java/io/FileOutputStream.write:(I)V
        //    52: aload_2        
        //    53: invokevirtual   java/io/FileOutputStream.close:()V
        //    56: goto            10
        //    59: astore_1       
        //    60: ldc             "Could not close Clearcut output stream."
        //    62: invokestatic    com/google/android/gms/internal/ads/zzakb.v:(Ljava/lang/String;)V
        //    65: goto            10
        //    68: astore_1       
        //    69: ldc             "Could not find file for Clearcut"
        //    71: invokestatic    com/google/android/gms/internal/ads/zzakb.v:(Ljava/lang/String;)V
        //    74: goto            10
        //    77: astore_1       
        //    78: aload_0        
        //    79: monitorexit    
        //    80: aload_1        
        //    81: athrow         
        //    82: astore_1       
        //    83: ldc             "Could not write Clearcut to file."
        //    85: invokestatic    com/google/android/gms/internal/ads/zzakb.v:(Ljava/lang/String;)V
        //    88: aload_2        
        //    89: invokevirtual   java/io/FileOutputStream.close:()V
        //    92: goto            10
        //    95: astore_1       
        //    96: ldc             "Could not close Clearcut output stream."
        //    98: invokestatic    com/google/android/gms/internal/ads/zzakb.v:(Ljava/lang/String;)V
        //   101: goto            10
        //   104: astore_1       
        //   105: aload_2        
        //   106: invokevirtual   java/io/FileOutputStream.close:()V
        //   109: aload_1        
        //   110: athrow         
        //   111: astore_2       
        //   112: ldc             "Could not close Clearcut output stream."
        //   114: invokestatic    com/google/android/gms/internal/ads/zzakb.v:(Ljava/lang/String;)V
        //   117: goto            109
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                           
        //  -----  -----  -----  -----  -------------------------------
        //  2      6      77     82     Any
        //  13     24     77     82     Any
        //  24     34     68     77     Ljava/io/FileNotFoundException;
        //  24     34     77     82     Any
        //  34     52     82     104    Ljava/io/IOException;
        //  34     52     104    120    Any
        //  52     56     59     68     Ljava/io/IOException;
        //  52     56     68     77     Ljava/io/FileNotFoundException;
        //  52     56     77     82     Any
        //  60     65     68     77     Ljava/io/FileNotFoundException;
        //  60     65     77     82     Any
        //  69     74     77     82     Any
        //  83     88     104    120    Any
        //  88     92     95     104    Ljava/io/IOException;
        //  88     92     68     77     Ljava/io/FileNotFoundException;
        //  88     92     77     82     Any
        //  96     101    68     77     Ljava/io/FileNotFoundException;
        //  96     101    77     82     Any
        //  105    109    111    120    Ljava/io/IOException;
        //  105    109    68     77     Ljava/io/FileNotFoundException;
        //  105    109    77     82     Any
        //  109    111    68     77     Ljava/io/FileNotFoundException;
        //  109    111    77     82     Any
        //  112    117    68     77     Ljava/io/FileNotFoundException;
        //  112    117    77     82     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 65, Size: 65
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3569)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
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
    
    private final String zzd(final zzhu.zza.zzb zzb) {
        synchronized (this) {
            return String.format("id=%s,timestamp=%s,event=%s", this.zzakd.zzanh, zzbv.zzer().elapsedRealtime(), zzb.zzhq());
        }
    }
    
    public static zzhs zzhm() {
        return new zzhs();
    }
    
    private final void zzhn() {
        synchronized (this) {
            this.zzakd.zzanp = new zzib();
            this.zzakd.zzanp.zzalw = new zzie();
            this.zzakd.zzanm = new zzig();
        }
    }
    
    private static long[] zzho() {
        int i = 0;
        final List zzjc = zznk.zzjc();
        final ArrayList<Long> list = new ArrayList<Long>();
        final Iterator<String> iterator = zzjc.iterator();
    Label_0098:
        while (iterator.hasNext()) {
            final String[] split = iterator.next().split(",");
            final int length = split.length;
            int j = 0;
        Label_0080_Outer:
            while (j < length) {
                final String s = split[j];
                while (true) {
                    try {
                        list.add(Long.valueOf(s));
                        ++j;
                        continue Label_0080_Outer;
                    }
                    catch (NumberFormatException ex) {
                        zzakb.v("Experiment ID is not a number");
                        continue;
                    }
                    break;
                }
                break Label_0098;
            }
        }
        final long[] array = new long[list.size()];
        final ArrayList<Long> list2 = list;
        Long value;
        for (int size = list2.size(), n = 0; i < size; ++i, array[n] = value, ++n) {
            value = list2.get(i);
        }
        return array;
    }
    
    public final void zza(final zzht zzht) {
        synchronized (this) {
            if (!this.zzake) {
                return;
            }
            try {
                zzht.zza(this.zzakd);
            }
            catch (NullPointerException ex) {
                zzbv.zzeo().zza(ex, "AdMobClearcutLogger.modify");
            }
        }
    }
    
    public final void zza(final zzhu.zza.zzb zzb) {
        while (true) {
            Label_0047: {
                synchronized (this) {
                    if (this.zzake) {
                        if (!(boolean)zzkb.zzik().zzd(zznk.zzbep)) {
                            break Label_0047;
                        }
                        this.zzc(zzb);
                    }
                    return;
                }
            }
            final zzhu.zza.zzb zzb2;
            this.zzb(zzb2);
        }
    }
}
