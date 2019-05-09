// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import java.util.Map;
import java.io.File;

public class n
{
    public static String a(final File p0, final Map<String, String> p1) throws Exception {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          7
        //     3: new             Ljava/io/FileReader;
        //     6: dup            
        //     7: aload_0        
        //     8: invokespecial   java/io/FileReader.<init>:(Ljava/io/File;)V
        //    11: astore          5
        //    13: new             Ljava/io/BufferedReader;
        //    16: dup            
        //    17: aload           5
        //    19: invokespecial   java/io/BufferedReader.<init>:(Ljava/io/Reader;)V
        //    22: astore          6
        //    24: new             Ljava/util/HashMap;
        //    27: dup            
        //    28: invokespecial   java/util/HashMap.<init>:()V
        //    31: astore          7
        //    33: aload_1        
        //    34: invokeinterface java/util/Map.entrySet:()Ljava/util/Set;
        //    39: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //    44: astore_1       
        //    45: aload_1        
        //    46: invokeinterface java/util/Iterator.hasNext:()Z
        //    51: ifeq            167
        //    54: aload_1        
        //    55: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    60: checkcast       Ljava/util/Map$Entry;
        //    63: astore          8
        //    65: aload           8
        //    67: invokeinterface java/util/Map$Entry.getKey:()Ljava/lang/Object;
        //    72: checkcast       Ljava/lang/String;
        //    75: astore          9
        //    77: aload           9
        //    79: ldc             "{{"
        //    81: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //    84: ifne            97
        //    87: aload           9
        //    89: ldc             "{%"
        //    91: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //    94: ifeq            45
        //    97: aload           7
        //    99: aload           9
        //   101: aload           8
        //   103: invokeinterface java/util/Map$Entry.getValue:()Ljava/lang/Object;
        //   108: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   113: pop            
        //   114: goto            45
        //   117: astore          7
        //   119: aload           6
        //   121: astore_1       
        //   122: aload           5
        //   124: astore_0       
        //   125: aload           7
        //   127: astore          5
        //   129: new             Ljava/lang/Exception;
        //   132: dup            
        //   133: aload           5
        //   135: invokespecial   java/lang/Exception.<init>:(Ljava/lang/Throwable;)V
        //   138: athrow         
        //   139: astore          6
        //   141: aload_0        
        //   142: astore          5
        //   144: aload           6
        //   146: astore_0       
        //   147: aload_1        
        //   148: ifnull          155
        //   151: aload_1        
        //   152: invokevirtual   java/io/BufferedReader.close:()V
        //   155: aload           5
        //   157: ifnull          165
        //   160: aload           5
        //   162: invokevirtual   java/io/FileReader.close:()V
        //   165: aload_0        
        //   166: athrow         
        //   167: aload           7
        //   169: invokeinterface java/util/Map.entrySet:()Ljava/util/Set;
        //   174: astore_1       
        //   175: aload_1        
        //   176: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //   181: astore          7
        //   183: iconst_0       
        //   184: istore_2       
        //   185: aload           7
        //   187: invokeinterface java/util/Iterator.hasNext:()Z
        //   192: ifeq            224
        //   195: aload           7
        //   197: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   202: checkcast       Ljava/util/Map$Entry;
        //   205: invokeinterface java/util/Map$Entry.getValue:()Ljava/lang/Object;
        //   210: checkcast       Ljava/lang/String;
        //   213: invokevirtual   java/lang/String.length:()I
        //   216: iconst_3       
        //   217: imul           
        //   218: iload_2        
        //   219: iadd           
        //   220: istore_2       
        //   221: goto            185
        //   224: new             Ljava/lang/StringBuilder;
        //   227: dup            
        //   228: aload_0        
        //   229: invokevirtual   java/io/File.length:()J
        //   232: l2i            
        //   233: iload_2        
        //   234: iadd           
        //   235: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //   238: astore_0       
        //   239: new             Ljava/lang/StringBuilder;
        //   242: dup            
        //   243: sipush          2048
        //   246: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //   249: astore          7
        //   251: aload           6
        //   253: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //   256: astore          8
        //   258: aload           8
        //   260: ifnull          451
        //   263: aload           8
        //   265: ldc             "{{"
        //   267: invokevirtual   java/lang/String.indexOf:(Ljava/lang/String;)I
        //   270: istore_2       
        //   271: aload           8
        //   273: ldc             "{%"
        //   275: invokevirtual   java/lang/String.indexOf:(Ljava/lang/String;)I
        //   278: istore_3       
        //   279: iload_2        
        //   280: iconst_m1      
        //   281: if_icmpeq       317
        //   284: iload_3        
        //   285: iconst_m1      
        //   286: if_icmpeq       317
        //   289: iload_2        
        //   290: iload_3        
        //   291: invokestatic    java/lang/Math.min:(II)I
        //   294: istore_2       
        //   295: iload_2        
        //   296: iconst_m1      
        //   297: if_icmpne       326
        //   300: aload_0        
        //   301: aload           8
        //   303: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   306: pop            
        //   307: aload_0        
        //   308: ldc             "\n"
        //   310: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   313: pop            
        //   314: goto            251
        //   317: iload_2        
        //   318: iload_3        
        //   319: invokestatic    java/lang/Math.max:(II)I
        //   322: istore_2       
        //   323: goto            295
        //   326: aload           7
        //   328: iconst_0       
        //   329: invokevirtual   java/lang/StringBuilder.setLength:(I)V
        //   332: aload           7
        //   334: aload           8
        //   336: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   339: pop            
        //   340: aload_1        
        //   341: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //   346: astore          8
        //   348: aload           8
        //   350: invokeinterface java/util/Iterator.hasNext:()Z
        //   355: ifeq            441
        //   358: aload           8
        //   360: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   365: checkcast       Ljava/util/Map$Entry;
        //   368: astore          10
        //   370: aload           10
        //   372: invokeinterface java/util/Map$Entry.getKey:()Ljava/lang/Object;
        //   377: checkcast       Ljava/lang/String;
        //   380: astore          9
        //   382: aload           10
        //   384: invokeinterface java/util/Map$Entry.getValue:()Ljava/lang/Object;
        //   389: checkcast       Ljava/lang/String;
        //   392: astore          10
        //   394: aload           9
        //   396: invokevirtual   java/lang/String.length:()I
        //   399: istore          4
        //   401: aload           7
        //   403: aload           9
        //   405: iload_2        
        //   406: invokevirtual   java/lang/StringBuilder.indexOf:(Ljava/lang/String;I)I
        //   409: istore_3       
        //   410: iload_3        
        //   411: istore_2       
        //   412: iconst_m1      
        //   413: iload_3        
        //   414: if_icmpeq       348
        //   417: aload           7
        //   419: iload_3        
        //   420: iload_3        
        //   421: iload           4
        //   423: iadd           
        //   424: aload           10
        //   426: invokevirtual   java/lang/StringBuilder.replace:(IILjava/lang/String;)Ljava/lang/StringBuilder;
        //   429: pop            
        //   430: iload_3        
        //   431: aload           10
        //   433: invokevirtual   java/lang/String.length:()I
        //   436: iadd           
        //   437: istore_2       
        //   438: goto            401
        //   441: aload_0        
        //   442: aload           7
        //   444: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/CharSequence;)Ljava/lang/StringBuilder;
        //   447: pop            
        //   448: goto            307
        //   451: aload_0        
        //   452: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   455: astore_0       
        //   456: aload_0        
        //   457: ldc             "{{"
        //   459: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //   462: ifeq            475
        //   465: new             Ljava/lang/IllegalArgumentException;
        //   468: dup            
        //   469: ldc             "Missing required template parameter"
        //   471: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   474: athrow         
        //   475: aload           6
        //   477: ifnull          485
        //   480: aload           6
        //   482: invokevirtual   java/io/BufferedReader.close:()V
        //   485: aload           5
        //   487: ifnull          495
        //   490: aload           5
        //   492: invokevirtual   java/io/FileReader.close:()V
        //   495: aload_0        
        //   496: areturn        
        //   497: astore_1       
        //   498: goto            485
        //   501: astore_1       
        //   502: aload_0        
        //   503: areturn        
        //   504: astore_1       
        //   505: goto            155
        //   508: astore_1       
        //   509: goto            165
        //   512: astore_0       
        //   513: aconst_null    
        //   514: astore_1       
        //   515: aconst_null    
        //   516: astore          5
        //   518: goto            147
        //   521: astore_0       
        //   522: aconst_null    
        //   523: astore_1       
        //   524: goto            147
        //   527: astore          5
        //   529: aconst_null    
        //   530: astore_0       
        //   531: aload           7
        //   533: astore_1       
        //   534: goto            129
        //   537: astore_1       
        //   538: aload           5
        //   540: astore_0       
        //   541: aload_1        
        //   542: astore          5
        //   544: aload           7
        //   546: astore_1       
        //   547: goto            129
        //   550: astore_0       
        //   551: aload           6
        //   553: astore_1       
        //   554: goto            147
        //    Exceptions:
        //  throws java.lang.Exception
        //    Signature:
        //  (Ljava/io/File;Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;)Ljava/lang/String;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  3      13     527    537    Ljava/lang/OutOfMemoryError;
        //  3      13     512    521    Any
        //  13     24     537    550    Ljava/lang/OutOfMemoryError;
        //  13     24     521    527    Any
        //  24     45     117    129    Ljava/lang/OutOfMemoryError;
        //  24     45     550    557    Any
        //  45     97     117    129    Ljava/lang/OutOfMemoryError;
        //  45     97     550    557    Any
        //  97     114    117    129    Ljava/lang/OutOfMemoryError;
        //  97     114    550    557    Any
        //  129    139    139    147    Any
        //  151    155    504    508    Ljava/io/IOException;
        //  160    165    508    512    Ljava/io/IOException;
        //  167    183    117    129    Ljava/lang/OutOfMemoryError;
        //  167    183    550    557    Any
        //  185    221    117    129    Ljava/lang/OutOfMemoryError;
        //  185    221    550    557    Any
        //  224    251    117    129    Ljava/lang/OutOfMemoryError;
        //  224    251    550    557    Any
        //  251    258    117    129    Ljava/lang/OutOfMemoryError;
        //  251    258    550    557    Any
        //  263    279    117    129    Ljava/lang/OutOfMemoryError;
        //  263    279    550    557    Any
        //  289    295    117    129    Ljava/lang/OutOfMemoryError;
        //  289    295    550    557    Any
        //  300    307    117    129    Ljava/lang/OutOfMemoryError;
        //  300    307    550    557    Any
        //  307    314    117    129    Ljava/lang/OutOfMemoryError;
        //  307    314    550    557    Any
        //  317    323    117    129    Ljava/lang/OutOfMemoryError;
        //  317    323    550    557    Any
        //  326    348    117    129    Ljava/lang/OutOfMemoryError;
        //  326    348    550    557    Any
        //  348    401    117    129    Ljava/lang/OutOfMemoryError;
        //  348    401    550    557    Any
        //  401    410    117    129    Ljava/lang/OutOfMemoryError;
        //  401    410    550    557    Any
        //  417    438    117    129    Ljava/lang/OutOfMemoryError;
        //  417    438    550    557    Any
        //  441    448    117    129    Ljava/lang/OutOfMemoryError;
        //  441    448    550    557    Any
        //  451    475    117    129    Ljava/lang/OutOfMemoryError;
        //  451    475    550    557    Any
        //  480    485    497    501    Ljava/io/IOException;
        //  490    495    501    504    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0495:
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
