// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.TimeZone;
import java.util.Locale;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

public final class zzap
{
    public static zzc zzb(final zzp p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: lstore          13
        //     5: aload_0        
        //     6: getfield        com/google/android/gms/internal/ads/zzp.zzab:Ljava/util/Map;
        //     9: astore          15
        //    11: lconst_0       
        //    12: lstore          7
        //    14: lconst_0       
        //    15: lstore          5
        //    17: lconst_0       
        //    18: lstore_3       
        //    19: aload           15
        //    21: ldc             "Date"
        //    23: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    28: checkcast       Ljava/lang/String;
        //    31: astore          16
        //    33: aload           16
        //    35: ifnull          45
        //    38: aload           16
        //    40: invokestatic    com/google/android/gms/internal/ads/zzap.zzf:(Ljava/lang/String;)J
        //    43: lstore          7
        //    45: aload           15
        //    47: ldc             "Cache-Control"
        //    49: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    54: checkcast       Ljava/lang/String;
        //    57: astore          16
        //    59: aload           16
        //    61: ifnull          474
        //    64: aload           16
        //    66: ldc             ","
        //    68: invokevirtual   java/lang/String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //    71: astore          16
        //    73: iconst_0       
        //    74: istore_2       
        //    75: iconst_0       
        //    76: istore_1       
        //    77: lconst_0       
        //    78: lstore_3       
        //    79: lconst_0       
        //    80: lstore          5
        //    82: iload_2        
        //    83: aload           16
        //    85: arraylength    
        //    86: if_icmpge       227
        //    89: aload           16
        //    91: iload_2        
        //    92: aaload         
        //    93: invokevirtual   java/lang/String.trim:()Ljava/lang/String;
        //    96: astore          17
        //    98: aload           17
        //   100: ldc             "no-cache"
        //   102: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   105: ifne            118
        //   108: aload           17
        //   110: ldc             "no-store"
        //   112: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   115: ifeq            120
        //   118: aconst_null    
        //   119: areturn        
        //   120: aload           17
        //   122: ldc             "max-age="
        //   124: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   127: ifeq            159
        //   130: aload           17
        //   132: bipush          8
        //   134: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   137: invokestatic    java/lang/Long.parseLong:(Ljava/lang/String;)J
        //   140: lstore          11
        //   142: lload_3        
        //   143: lstore          9
        //   145: iload_2        
        //   146: iconst_1       
        //   147: iadd           
        //   148: istore_2       
        //   149: lload           9
        //   151: lstore_3       
        //   152: lload           11
        //   154: lstore          5
        //   156: goto            82
        //   159: aload           17
        //   161: ldc             "stale-while-revalidate="
        //   163: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   166: ifeq            188
        //   169: aload           17
        //   171: bipush          23
        //   173: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   176: invokestatic    java/lang/Long.parseLong:(Ljava/lang/String;)J
        //   179: lstore          9
        //   181: lload           5
        //   183: lstore          11
        //   185: goto            145
        //   188: aload           17
        //   190: ldc             "must-revalidate"
        //   192: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   195: ifne            215
        //   198: lload_3        
        //   199: lstore          9
        //   201: lload           5
        //   203: lstore          11
        //   205: aload           17
        //   207: ldc             "proxy-revalidate"
        //   209: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   212: ifeq            145
        //   215: iconst_1       
        //   216: istore_1       
        //   217: lload_3        
        //   218: lstore          9
        //   220: lload           5
        //   222: lstore          11
        //   224: goto            145
        //   227: iconst_1       
        //   228: istore_2       
        //   229: aload           15
        //   231: ldc             "Expires"
        //   233: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   238: checkcast       Ljava/lang/String;
        //   241: astore          16
        //   243: aload           16
        //   245: ifnull          468
        //   248: aload           16
        //   250: invokestatic    com/google/android/gms/internal/ads/zzap.zzf:(Ljava/lang/String;)J
        //   253: lstore          11
        //   255: aload           15
        //   257: ldc             "Last-Modified"
        //   259: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   264: checkcast       Ljava/lang/String;
        //   267: astore          16
        //   269: aload           16
        //   271: ifnull          462
        //   274: aload           16
        //   276: invokestatic    com/google/android/gms/internal/ads/zzap.zzf:(Ljava/lang/String;)J
        //   279: lstore          9
        //   281: aload           15
        //   283: ldc             "ETag"
        //   285: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   290: checkcast       Ljava/lang/String;
        //   293: astore          16
        //   295: iload_2        
        //   296: ifeq            400
        //   299: lload           13
        //   301: ldc2_w          1000
        //   304: lload           5
        //   306: lmul           
        //   307: ladd           
        //   308: lstore          5
        //   310: iload_1        
        //   311: ifeq            388
        //   314: lload           5
        //   316: lstore_3       
        //   317: new             Lcom/google/android/gms/internal/ads/zzc;
        //   320: dup            
        //   321: invokespecial   com/google/android/gms/internal/ads/zzc.<init>:()V
        //   324: astore          17
        //   326: aload           17
        //   328: aload_0        
        //   329: getfield        com/google/android/gms/internal/ads/zzp.data:[B
        //   332: putfield        com/google/android/gms/internal/ads/zzc.data:[B
        //   335: aload           17
        //   337: aload           16
        //   339: putfield        com/google/android/gms/internal/ads/zzc.zza:Ljava/lang/String;
        //   342: aload           17
        //   344: lload           5
        //   346: putfield        com/google/android/gms/internal/ads/zzc.zze:J
        //   349: aload           17
        //   351: lload_3        
        //   352: putfield        com/google/android/gms/internal/ads/zzc.zzd:J
        //   355: aload           17
        //   357: lload           7
        //   359: putfield        com/google/android/gms/internal/ads/zzc.zzb:J
        //   362: aload           17
        //   364: lload           9
        //   366: putfield        com/google/android/gms/internal/ads/zzc.zzc:J
        //   369: aload           17
        //   371: aload           15
        //   373: putfield        com/google/android/gms/internal/ads/zzc.zzf:Ljava/util/Map;
        //   376: aload           17
        //   378: aload_0        
        //   379: getfield        com/google/android/gms/internal/ads/zzp.allHeaders:Ljava/util/List;
        //   382: putfield        com/google/android/gms/internal/ads/zzc.zzg:Ljava/util/List;
        //   385: aload           17
        //   387: areturn        
        //   388: ldc2_w          1000
        //   391: lload_3        
        //   392: lmul           
        //   393: lload           5
        //   395: ladd           
        //   396: lstore_3       
        //   397: goto            317
        //   400: lload           7
        //   402: lconst_0       
        //   403: lcmp           
        //   404: ifle            454
        //   407: lload           11
        //   409: lload           7
        //   411: lcmp           
        //   412: iflt            454
        //   415: lload           11
        //   417: lload           7
        //   419: lsub           
        //   420: lload           13
        //   422: ladd           
        //   423: lstore_3       
        //   424: lload_3        
        //   425: lstore          5
        //   427: goto            317
        //   430: astore          17
        //   432: lload_3        
        //   433: lstore          9
        //   435: lload           5
        //   437: lstore          11
        //   439: goto            145
        //   442: astore          17
        //   444: lload_3        
        //   445: lstore          9
        //   447: lload           5
        //   449: lstore          11
        //   451: goto            145
        //   454: lconst_0       
        //   455: lstore_3       
        //   456: lconst_0       
        //   457: lstore          5
        //   459: goto            317
        //   462: lconst_0       
        //   463: lstore          9
        //   465: goto            281
        //   468: lconst_0       
        //   469: lstore          11
        //   471: goto            255
        //   474: iconst_0       
        //   475: istore_1       
        //   476: iconst_0       
        //   477: istore_2       
        //   478: goto            229
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  130    142    442    454    Ljava/lang/Exception;
        //  169    181    430    442    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.assembler.ir.StackMappingVisitor.push(StackMappingVisitor.java:290)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.execute(StackMappingVisitor.java:833)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.visit(StackMappingVisitor.java:398)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2030)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
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
    
    static String zzb(final long n) {
        return zzp().format(new Date(n));
    }
    
    private static long zzf(final String s) {
        try {
            return zzp().parse(s).getTime();
        }
        catch (ParseException ex) {
            zzaf.zza(ex, "Unable to parse dateStr: %s, falling back to 0", s);
            return 0L;
        }
    }
    
    private static SimpleDateFormat zzp() {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat;
    }
}
