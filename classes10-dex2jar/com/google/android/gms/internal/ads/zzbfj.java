// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzbfj
{
    private static void zza(final String p0, final Object p1, final StringBuffer p2, final StringBuffer p3) throws IllegalAccessException, InvocationTargetException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ifnull          474
        //     4: aload_1        
        //     5: instanceof      Lcom/google/android/gms/internal/ads/zzbfi;
        //     8: ifeq            475
        //    11: aload_2        
        //    12: invokevirtual   java/lang/StringBuffer.length:()I
        //    15: istore          8
        //    17: aload_0        
        //    18: ifnull          46
        //    21: aload_3        
        //    22: aload_2        
        //    23: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/StringBuffer;)Ljava/lang/StringBuffer;
        //    26: aload_0        
        //    27: invokestatic    com/google/android/gms/internal/ads/zzbfj.zzer:(Ljava/lang/String;)Ljava/lang/String;
        //    30: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    33: ldc             " <\n"
        //    35: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    38: pop            
        //    39: aload_2        
        //    40: ldc             "  "
        //    42: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    45: pop            
        //    46: aload_1        
        //    47: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    50: astore          11
        //    52: aload           11
        //    54: invokevirtual   java/lang/Class.getFields:()[Ljava/lang/reflect/Field;
        //    57: astore          10
        //    59: aload           10
        //    61: arraylength    
        //    62: istore          9
        //    64: iconst_0       
        //    65: istore          5
        //    67: iload           5
        //    69: iload           9
        //    71: if_icmpge       246
        //    74: aload           10
        //    76: iload           5
        //    78: aaload         
        //    79: astore          14
        //    81: aload           14
        //    83: invokevirtual   java/lang/reflect/Field.getModifiers:()I
        //    86: istore          6
        //    88: aload           14
        //    90: invokevirtual   java/lang/reflect/Field.getName:()Ljava/lang/String;
        //    93: astore          12
        //    95: ldc             "cachedSize"
        //    97: aload           12
        //    99: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   102: ifne            237
        //   105: iload           6
        //   107: iconst_1       
        //   108: iand           
        //   109: iconst_1       
        //   110: if_icmpne       237
        //   113: iload           6
        //   115: bipush          8
        //   117: iand           
        //   118: bipush          8
        //   120: if_icmpeq       237
        //   123: aload           12
        //   125: ldc             "_"
        //   127: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   130: ifne            237
        //   133: aload           12
        //   135: ldc             "_"
        //   137: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //   140: ifne            237
        //   143: aload           14
        //   145: invokevirtual   java/lang/reflect/Field.getType:()Ljava/lang/Class;
        //   148: astore          13
        //   150: aload           14
        //   152: aload_1        
        //   153: invokevirtual   java/lang/reflect/Field.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   156: astore          14
        //   158: aload           13
        //   160: invokevirtual   java/lang/Class.isArray:()Z
        //   163: ifeq            228
        //   166: aload           13
        //   168: invokevirtual   java/lang/Class.getComponentType:()Ljava/lang/Class;
        //   171: getstatic       java/lang/Byte.TYPE:Ljava/lang/Class;
        //   174: if_acmpeq       228
        //   177: aload           14
        //   179: ifnonnull       218
        //   182: iconst_0       
        //   183: istore          6
        //   185: iconst_0       
        //   186: istore          7
        //   188: iload           7
        //   190: iload           6
        //   192: if_icmpge       237
        //   195: aload           12
        //   197: aload           14
        //   199: iload           7
        //   201: invokestatic    java/lang/reflect/Array.get:(Ljava/lang/Object;I)Ljava/lang/Object;
        //   204: aload_2        
        //   205: aload_3        
        //   206: invokestatic    com/google/android/gms/internal/ads/zzbfj.zza:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/StringBuffer;Ljava/lang/StringBuffer;)V
        //   209: iload           7
        //   211: iconst_1       
        //   212: iadd           
        //   213: istore          7
        //   215: goto            188
        //   218: aload           14
        //   220: invokestatic    java/lang/reflect/Array.getLength:(Ljava/lang/Object;)I
        //   223: istore          6
        //   225: goto            185
        //   228: aload           12
        //   230: aload           14
        //   232: aload_2        
        //   233: aload_3        
        //   234: invokestatic    com/google/android/gms/internal/ads/zzbfj.zza:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/StringBuffer;Ljava/lang/StringBuffer;)V
        //   237: iload           5
        //   239: iconst_1       
        //   240: iadd           
        //   241: istore          5
        //   243: goto            67
        //   246: aload           11
        //   248: invokevirtual   java/lang/Class.getMethods:()[Ljava/lang/reflect/Method;
        //   251: astore          12
        //   253: aload           12
        //   255: arraylength    
        //   256: istore          6
        //   258: iconst_0       
        //   259: istore          5
        //   261: iload           5
        //   263: iload           6
        //   265: if_icmpge       453
        //   268: aload           12
        //   270: iload           5
        //   272: aaload         
        //   273: invokevirtual   java/lang/reflect/Method.getName:()Ljava/lang/String;
        //   276: astore          10
        //   278: aload           10
        //   280: ldc             "set"
        //   282: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   285: ifeq            406
        //   288: aload           10
        //   290: iconst_3       
        //   291: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   294: astore          13
        //   296: aload           13
        //   298: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   301: astore          10
        //   303: aload           10
        //   305: invokevirtual   java/lang/String.length:()I
        //   308: ifeq            415
        //   311: ldc             "has"
        //   313: aload           10
        //   315: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   318: astore          10
        //   320: aload           11
        //   322: aload           10
        //   324: iconst_0       
        //   325: anewarray       Ljava/lang/Class;
        //   328: invokevirtual   java/lang/Class.getMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //   331: astore          10
        //   333: aload           10
        //   335: aload_1        
        //   336: iconst_0       
        //   337: anewarray       Ljava/lang/Object;
        //   340: invokevirtual   java/lang/reflect/Method.invoke:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //   343: checkcast       Ljava/lang/Boolean;
        //   346: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   349: ifeq            406
        //   352: aload           13
        //   354: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   357: astore          10
        //   359: aload           10
        //   361: invokevirtual   java/lang/String.length:()I
        //   364: ifeq            434
        //   367: ldc             "get"
        //   369: aload           10
        //   371: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   374: astore          10
        //   376: aload           11
        //   378: aload           10
        //   380: iconst_0       
        //   381: anewarray       Ljava/lang/Class;
        //   384: invokevirtual   java/lang/Class.getMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //   387: astore          10
        //   389: aload           13
        //   391: aload           10
        //   393: aload_1        
        //   394: iconst_0       
        //   395: anewarray       Ljava/lang/Object;
        //   398: invokevirtual   java/lang/reflect/Method.invoke:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //   401: aload_2        
        //   402: aload_3        
        //   403: invokestatic    com/google/android/gms/internal/ads/zzbfj.zza:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/StringBuffer;Ljava/lang/StringBuffer;)V
        //   406: iload           5
        //   408: iconst_1       
        //   409: iadd           
        //   410: istore          5
        //   412: goto            261
        //   415: new             Ljava/lang/String;
        //   418: dup            
        //   419: ldc             "has"
        //   421: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //   424: astore          10
        //   426: goto            320
        //   429: astore          10
        //   431: goto            406
        //   434: new             Ljava/lang/String;
        //   437: dup            
        //   438: ldc             "get"
        //   440: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //   443: astore          10
        //   445: goto            376
        //   448: astore          10
        //   450: goto            406
        //   453: aload_0        
        //   454: ifnull          474
        //   457: aload_2        
        //   458: iload           8
        //   460: invokevirtual   java/lang/StringBuffer.setLength:(I)V
        //   463: aload_3        
        //   464: aload_2        
        //   465: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/StringBuffer;)Ljava/lang/StringBuffer;
        //   468: ldc             ">\n"
        //   470: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   473: pop            
        //   474: return         
        //   475: aload_0        
        //   476: invokestatic    com/google/android/gms/internal/ads/zzbfj.zzer:(Ljava/lang/String;)Ljava/lang/String;
        //   479: astore_0       
        //   480: aload_3        
        //   481: aload_2        
        //   482: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/StringBuffer;)Ljava/lang/StringBuffer;
        //   485: aload_0        
        //   486: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   489: ldc             ": "
        //   491: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   494: pop            
        //   495: aload_1        
        //   496: instanceof      Ljava/lang/String;
        //   499: ifeq            679
        //   502: aload_1        
        //   503: checkcast       Ljava/lang/String;
        //   506: astore_1       
        //   507: aload_1        
        //   508: astore_0       
        //   509: aload_1        
        //   510: ldc             "http"
        //   512: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   515: ifne            547
        //   518: aload_1        
        //   519: astore_0       
        //   520: aload_1        
        //   521: invokevirtual   java/lang/String.length:()I
        //   524: sipush          200
        //   527: if_icmple       547
        //   530: aload_1        
        //   531: iconst_0       
        //   532: sipush          200
        //   535: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   538: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   541: ldc             "[...]"
        //   543: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   546: astore_0       
        //   547: aload_0        
        //   548: invokevirtual   java/lang/String.length:()I
        //   551: istore          6
        //   553: new             Ljava/lang/StringBuilder;
        //   556: dup            
        //   557: iload           6
        //   559: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //   562: astore_1       
        //   563: iconst_0       
        //   564: istore          5
        //   566: iload           5
        //   568: iload           6
        //   570: if_icmpge       650
        //   573: aload_0        
        //   574: iload           5
        //   576: invokevirtual   java/lang/String.charAt:(I)C
        //   579: istore          4
        //   581: iload           4
        //   583: bipush          32
        //   585: if_icmplt       625
        //   588: iload           4
        //   590: bipush          126
        //   592: if_icmpgt       625
        //   595: iload           4
        //   597: bipush          34
        //   599: if_icmpeq       625
        //   602: iload           4
        //   604: bipush          39
        //   606: if_icmpeq       625
        //   609: aload_1        
        //   610: iload           4
        //   612: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   615: pop            
        //   616: iload           5
        //   618: iconst_1       
        //   619: iadd           
        //   620: istore          5
        //   622: goto            566
        //   625: aload_1        
        //   626: ldc             "\\u%04x"
        //   628: iconst_1       
        //   629: anewarray       Ljava/lang/Object;
        //   632: dup            
        //   633: iconst_0       
        //   634: iload           4
        //   636: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   639: aastore        
        //   640: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   643: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   646: pop            
        //   647: goto            616
        //   650: aload_1        
        //   651: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   654: astore_0       
        //   655: aload_3        
        //   656: ldc             "\""
        //   658: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   661: aload_0        
        //   662: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   665: ldc             "\""
        //   667: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   670: pop            
        //   671: aload_3        
        //   672: ldc             "\n"
        //   674: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   677: pop            
        //   678: return         
        //   679: aload_1        
        //   680: instanceof      [B
        //   683: ifeq            828
        //   686: aload_1        
        //   687: checkcast       [B
        //   690: astore_0       
        //   691: aload_0        
        //   692: ifnonnull       705
        //   695: aload_3        
        //   696: ldc             "\"\""
        //   698: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   701: pop            
        //   702: goto            671
        //   705: aload_3        
        //   706: bipush          34
        //   708: invokevirtual   java/lang/StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //   711: pop            
        //   712: iconst_0       
        //   713: istore          5
        //   715: iload           5
        //   717: aload_0        
        //   718: arraylength    
        //   719: if_icmpge       818
        //   722: aload_0        
        //   723: iload           5
        //   725: baload         
        //   726: sipush          255
        //   729: iand           
        //   730: istore          6
        //   732: iload           6
        //   734: bipush          92
        //   736: if_icmpeq       746
        //   739: iload           6
        //   741: bipush          34
        //   743: if_icmpne       768
        //   746: aload_3        
        //   747: bipush          92
        //   749: invokevirtual   java/lang/StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //   752: iload           6
        //   754: i2c            
        //   755: invokevirtual   java/lang/StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //   758: pop            
        //   759: iload           5
        //   761: iconst_1       
        //   762: iadd           
        //   763: istore          5
        //   765: goto            715
        //   768: iload           6
        //   770: bipush          32
        //   772: if_icmplt       793
        //   775: iload           6
        //   777: bipush          127
        //   779: if_icmpge       793
        //   782: aload_3        
        //   783: iload           6
        //   785: i2c            
        //   786: invokevirtual   java/lang/StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //   789: pop            
        //   790: goto            759
        //   793: aload_3        
        //   794: ldc             "\\%03o"
        //   796: iconst_1       
        //   797: anewarray       Ljava/lang/Object;
        //   800: dup            
        //   801: iconst_0       
        //   802: iload           6
        //   804: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   807: aastore        
        //   808: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   811: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   814: pop            
        //   815: goto            759
        //   818: aload_3        
        //   819: bipush          34
        //   821: invokevirtual   java/lang/StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //   824: pop            
        //   825: goto            671
        //   828: aload_3        
        //   829: aload_1        
        //   830: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/Object;)Ljava/lang/StringBuffer;
        //   833: pop            
        //   834: goto            671
        //    Exceptions:
        //  throws java.lang.IllegalAccessException
        //  throws java.lang.reflect.InvocationTargetException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  296    320    429    434    Ljava/lang/NoSuchMethodException;
        //  320    333    429    434    Ljava/lang/NoSuchMethodException;
        //  352    376    448    453    Ljava/lang/NoSuchMethodException;
        //  376    389    448    453    Ljava/lang/NoSuchMethodException;
        //  415    426    429    434    Ljava/lang/NoSuchMethodException;
        //  434    445    448    453    Ljava/lang/NoSuchMethodException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0376:
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
    
    public static <T extends zzbfi> String zzc(final T t) {
        if (t == null) {
            return "";
        }
        final StringBuffer sb = new StringBuffer();
        try {
            zza(null, t, new StringBuffer(), sb);
            return sb.toString();
        }
        catch (IllegalAccessException ex) {
            final String value = String.valueOf(ex.getMessage());
            if (value.length() != 0) {
                return "Error printing proto: ".concat(value);
            }
            return new String("Error printing proto: ");
        }
        catch (InvocationTargetException ex2) {
            final String value2 = String.valueOf(ex2.getMessage());
            if (value2.length() != 0) {
                return "Error printing proto: ".concat(value2);
            }
            return new String("Error printing proto: ");
        }
    }
    
    private static String zzer(final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (i == 0) {
                sb.append(Character.toLowerCase(char1));
            }
            else if (Character.isUpperCase(char1)) {
                sb.append('_').append(Character.toLowerCase(char1));
            }
            else {
                sb.append(char1);
            }
        }
        return sb.toString();
    }
}
