// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.ArrayList;
import java.util.Arrays;
import android.net.Uri;
import java.net.MalformedURLException;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.List;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.ByteArrayOutputStream;
import android.content.Context;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinLogger;

public class av
{
    private final String a;
    private final AppLovinSdkImpl b;
    private final AppLovinLogger c;
    private final Object d;
    
    av(final AppLovinSdk appLovinSdk) {
        this.a = "FileManager";
        this.b = (AppLovinSdkImpl)appLovinSdk;
        this.c = appLovinSdk.getLogger();
        this.d = new Object();
    }
    
    private long a(final long n) {
        return n / 1048576L;
    }
    
    private void a(final long n, final Context context) {
        final long n2 = this.c();
        if (n2 == -1L) {
            this.c.d("FileManager", "Cache has no maximum size set; skipping drop...");
            return;
        }
        if (this.a(n) > n2) {
            this.c.d("FileManager", "Cache has exceeded maximum size; dropping...");
            this.g(context);
            this.b.a().a("cache_drop_count");
            return;
        }
        this.c.d("FileManager", "Cache is present but under size limit; not dropping...");
    }
    
    private boolean a() {
        return this.b.get(ea.aY);
    }
    
    private long b() {
        final long longValue = this.b.get(ea.aZ);
        if (longValue >= 0L && this.a()) {
            return longValue;
        }
        return -1L;
    }
    
    private boolean b(final ByteArrayOutputStream p0, final File p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/applovin/impl/sdk/av.c:Lcom/applovin/sdk/AppLovinLogger;
        //     4: ldc             "FileManager"
        //     6: new             Ljava/lang/StringBuilder;
        //     9: dup            
        //    10: invokespecial   java/lang/StringBuilder.<init>:()V
        //    13: ldc             "Writing resource to filesystem: "
        //    15: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    18: aload_2        
        //    19: invokevirtual   java/io/File.getName:()Ljava/lang/String;
        //    22: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    25: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    28: invokeinterface com/applovin/sdk/AppLovinLogger.d:(Ljava/lang/String;Ljava/lang/String;)V
        //    33: aconst_null    
        //    34: astore          5
        //    36: aconst_null    
        //    37: astore          6
        //    39: aload_0        
        //    40: getfield        com/applovin/impl/sdk/av.d:Ljava/lang/Object;
        //    43: astore          7
        //    45: aload           7
        //    47: monitorenter   
        //    48: new             Ljava/io/FileOutputStream;
        //    51: dup            
        //    52: aload_2        
        //    53: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/File;)V
        //    56: astore_2       
        //    57: aload_2        
        //    58: astore          5
        //    60: aload_1        
        //    61: aload_2        
        //    62: invokevirtual   java/io/ByteArrayOutputStream.writeTo:(Ljava/io/OutputStream;)V
        //    65: iconst_1       
        //    66: istore          4
        //    68: iload           4
        //    70: istore_3       
        //    71: aload_2        
        //    72: ifnull          82
        //    75: aload_2        
        //    76: invokevirtual   java/io/FileOutputStream.close:()V
        //    79: iload           4
        //    81: istore_3       
        //    82: aload           7
        //    84: monitorexit    
        //    85: iload_3        
        //    86: ireturn        
        //    87: astore_1       
        //    88: aconst_null    
        //    89: astore_2       
        //    90: aload_2        
        //    91: astore          5
        //    93: aload_0        
        //    94: getfield        com/applovin/impl/sdk/av.c:Lcom/applovin/sdk/AppLovinLogger;
        //    97: ldc             "FileManager"
        //    99: ldc             "Unable to write data to file."
        //   101: aload_1        
        //   102: invokeinterface com/applovin/sdk/AppLovinLogger.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   107: aload_2        
        //   108: ifnull          115
        //   111: aload_2        
        //   112: invokevirtual   java/io/FileOutputStream.close:()V
        //   115: iconst_0       
        //   116: istore_3       
        //   117: goto            82
        //   120: astore_1       
        //   121: iconst_0       
        //   122: istore_3       
        //   123: goto            82
        //   126: astore_2       
        //   127: aload           6
        //   129: astore_1       
        //   130: aload_1        
        //   131: astore          5
        //   133: aload_0        
        //   134: getfield        com/applovin/impl/sdk/av.c:Lcom/applovin/sdk/AppLovinLogger;
        //   137: ldc             "FileManager"
        //   139: ldc             "Unknown failure to write file."
        //   141: aload_2        
        //   142: invokeinterface com/applovin/sdk/AppLovinLogger.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   147: aload_1        
        //   148: ifnull          155
        //   151: aload_1        
        //   152: invokevirtual   java/io/FileOutputStream.close:()V
        //   155: iconst_0       
        //   156: istore_3       
        //   157: goto            82
        //   160: astore_1       
        //   161: iconst_0       
        //   162: istore_3       
        //   163: goto            82
        //   166: astore_1       
        //   167: aload           5
        //   169: ifnull          177
        //   172: aload           5
        //   174: invokevirtual   java/io/FileOutputStream.close:()V
        //   177: aload_1        
        //   178: athrow         
        //   179: astore_1       
        //   180: aload           7
        //   182: monitorexit    
        //   183: aload_1        
        //   184: athrow         
        //   185: astore_1       
        //   186: iload           4
        //   188: istore_3       
        //   189: goto            82
        //   192: astore_2       
        //   193: goto            177
        //   196: astore_1       
        //   197: goto            167
        //   200: astore          5
        //   202: aload_2        
        //   203: astore_1       
        //   204: aload           5
        //   206: astore_2       
        //   207: goto            130
        //   210: astore_1       
        //   211: goto            90
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  48     57     87     90     Ljava/io/IOException;
        //  48     57     126    130    Ljava/lang/Throwable;
        //  48     57     166    167    Any
        //  60     65     210    214    Ljava/io/IOException;
        //  60     65     200    210    Ljava/lang/Throwable;
        //  60     65     196    200    Any
        //  75     79     185    192    Ljava/lang/Exception;
        //  75     79     179    185    Any
        //  82     85     179    185    Any
        //  93     107    196    200    Any
        //  111    115    120    126    Ljava/lang/Exception;
        //  111    115    179    185    Any
        //  133    147    166    167    Any
        //  151    155    160    166    Ljava/lang/Exception;
        //  151    155    179    185    Any
        //  172    177    192    196    Ljava/lang/Exception;
        //  172    177    179    185    Any
        //  177    179    179    185    Any
        //  180    183    179    185    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0082:
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
    
    private boolean b(final File file) {
        this.c.d("FileManager", "Removing file " + file.getName() + " from filesystem...");
        synchronized (this.d) {
            try {
                return file.delete();
            }
            catch (Exception ex) {
                this.c.e("FileManager", "Failed to remove file " + file.getName() + " from filesystem!", ex);
                return false;
            }
        }
    }
    
    private int c() {
        final int intValue = this.b.get(ea.ba);
        if (intValue >= 0 && this.a()) {
            return intValue;
        }
        return -1;
    }
    
    private File e(final Context context) {
        if (this.a(context)) {
            return new File(context.getExternalFilesDir((String)null), "al");
        }
        return new File(context.getCacheDir(), "al");
    }
    
    private long f(final Context context) {
        long n = 0L;
        final long b = this.b();
        // monitorexit(o)
        while (true) {
            Label_0178: {
                if (b == -1L) {
                    break Label_0178;
                }
                final int n2 = 1;
                while (true) {
                    final long seconds = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
                    while (true) {
                        Label_0183: {
                            synchronized (this.d) {
                                for (final File file : this.b(context)) {
                                    int n3 = 0;
                                    if (n2 != 0) {
                                        n3 = n3;
                                        if (seconds - TimeUnit.MILLISECONDS.toSeconds(file.lastModified()) > b) {
                                            this.c.d("FileManager", "File " + file.getName() + " has expired, removing...");
                                            this.b(file);
                                            n3 = 1;
                                        }
                                    }
                                    if (n3 == 0) {
                                        break Label_0183;
                                    }
                                    this.b.a().a("cached_files_expired");
                                }
                                break;
                            }
                            break Label_0178;
                        }
                        final File file;
                        n += file.length();
                        continue;
                    }
                }
                return n;
            }
            final int n2 = 0;
            continue;
        }
    }
    
    private void g(final Context context) {
        synchronized (this.d) {
            final Iterator<File> iterator = this.b(context).iterator();
            while (iterator.hasNext()) {
                this.b(iterator.next());
            }
        }
    }
    // monitorexit(o)
    
    ByteArrayOutputStream a(final File p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     4: aconst_null    
        //     5: areturn        
        //     6: aload_0        
        //     7: getfield        com/applovin/impl/sdk/av.c:Lcom/applovin/sdk/AppLovinLogger;
        //    10: ldc             "FileManager"
        //    12: new             Ljava/lang/StringBuilder;
        //    15: dup            
        //    16: invokespecial   java/lang/StringBuilder.<init>:()V
        //    19: ldc             "Reading resource from filesystem: "
        //    21: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    24: aload_1        
        //    25: invokevirtual   java/io/File.getName:()Ljava/lang/String;
        //    28: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    31: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    34: invokeinterface com/applovin/sdk/AppLovinLogger.d:(Ljava/lang/String;Ljava/lang/String;)V
        //    39: aload_0        
        //    40: getfield        com/applovin/impl/sdk/av.d:Ljava/lang/Object;
        //    43: astore          6
        //    45: aload           6
        //    47: monitorenter   
        //    48: new             Ljava/io/FileInputStream;
        //    51: dup            
        //    52: aload_1        
        //    53: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //    56: astore          4
        //    58: aload           4
        //    60: astore_3       
        //    61: new             Ljava/io/ByteArrayOutputStream;
        //    64: dup            
        //    65: invokespecial   java/io/ByteArrayOutputStream.<init>:()V
        //    68: astore          5
        //    70: aload           4
        //    72: astore_3       
        //    73: sipush          1024
        //    76: newarray        B
        //    78: astore          7
        //    80: aload           4
        //    82: astore_3       
        //    83: aload           4
        //    85: aload           7
        //    87: iconst_0       
        //    88: aload           7
        //    90: arraylength    
        //    91: invokevirtual   java/io/FileInputStream.read:([BII)I
        //    94: istore_2       
        //    95: iload_2        
        //    96: iflt            144
        //    99: aload           4
        //   101: astore_3       
        //   102: aload           5
        //   104: aload           7
        //   106: iconst_0       
        //   107: iload_2        
        //   108: invokevirtual   java/io/ByteArrayOutputStream.write:([BII)V
        //   111: goto            80
        //   114: astore_3       
        //   115: aload           4
        //   117: astore_3       
        //   118: aload           5
        //   120: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   123: aload           4
        //   125: ifnull          133
        //   128: aload           4
        //   130: invokevirtual   java/io/FileInputStream.close:()V
        //   133: aload           6
        //   135: monitorexit    
        //   136: aconst_null    
        //   137: areturn        
        //   138: astore_1       
        //   139: aload           6
        //   141: monitorexit    
        //   142: aload_1        
        //   143: athrow         
        //   144: aload           4
        //   146: ifnull          154
        //   149: aload           4
        //   151: invokevirtual   java/io/FileInputStream.close:()V
        //   154: aload           6
        //   156: monitorexit    
        //   157: aload           5
        //   159: areturn        
        //   160: astore_1       
        //   161: aconst_null    
        //   162: astore          4
        //   164: aload           4
        //   166: astore_3       
        //   167: aload_0        
        //   168: getfield        com/applovin/impl/sdk/av.c:Lcom/applovin/sdk/AppLovinLogger;
        //   171: ldc             "FileManager"
        //   173: new             Ljava/lang/StringBuilder;
        //   176: dup            
        //   177: invokespecial   java/lang/StringBuilder.<init>:()V
        //   180: ldc_w           "File not found. "
        //   183: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   186: aload_1        
        //   187: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   190: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   193: invokeinterface com/applovin/sdk/AppLovinLogger.i:(Ljava/lang/String;Ljava/lang/String;)V
        //   198: aload           4
        //   200: ifnull          208
        //   203: aload           4
        //   205: invokevirtual   java/io/FileInputStream.close:()V
        //   208: aload           6
        //   210: monitorexit    
        //   211: aconst_null    
        //   212: areturn        
        //   213: astore          5
        //   215: aconst_null    
        //   216: astore          4
        //   218: aload           4
        //   220: astore_3       
        //   221: aload_0        
        //   222: getfield        com/applovin/impl/sdk/av.c:Lcom/applovin/sdk/AppLovinLogger;
        //   225: ldc             "FileManager"
        //   227: new             Ljava/lang/StringBuilder;
        //   230: dup            
        //   231: invokespecial   java/lang/StringBuilder.<init>:()V
        //   234: ldc_w           "Failed to read file: "
        //   237: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   240: aload_1        
        //   241: invokevirtual   java/io/File.getName:()Ljava/lang/String;
        //   244: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   247: aload           5
        //   249: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   252: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   255: invokeinterface com/applovin/sdk/AppLovinLogger.d:(Ljava/lang/String;Ljava/lang/String;)V
        //   260: aload           4
        //   262: ifnull          270
        //   265: aload           4
        //   267: invokevirtual   java/io/FileInputStream.close:()V
        //   270: aload           6
        //   272: monitorexit    
        //   273: aconst_null    
        //   274: areturn        
        //   275: astore_1       
        //   276: aconst_null    
        //   277: astore          4
        //   279: aload           4
        //   281: astore_3       
        //   282: aload_0        
        //   283: getfield        com/applovin/impl/sdk/av.c:Lcom/applovin/sdk/AppLovinLogger;
        //   286: ldc             "FileManager"
        //   288: ldc_w           "Unknown failure to read file."
        //   291: aload_1        
        //   292: invokeinterface com/applovin/sdk/AppLovinLogger.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   297: aload           4
        //   299: ifnull          307
        //   302: aload           4
        //   304: invokevirtual   java/io/FileInputStream.close:()V
        //   307: aload           6
        //   309: monitorexit    
        //   310: aconst_null    
        //   311: areturn        
        //   312: astore_1       
        //   313: aconst_null    
        //   314: astore_3       
        //   315: aload_3        
        //   316: ifnull          323
        //   319: aload_3        
        //   320: invokevirtual   java/io/FileInputStream.close:()V
        //   323: aload_1        
        //   324: athrow         
        //   325: astore_1       
        //   326: goto            123
        //   329: astore_1       
        //   330: goto            133
        //   333: astore_1       
        //   334: goto            154
        //   337: astore_1       
        //   338: goto            208
        //   341: astore_1       
        //   342: goto            270
        //   345: astore_1       
        //   346: goto            307
        //   349: astore_3       
        //   350: goto            323
        //   353: astore_1       
        //   354: goto            315
        //   357: astore_1       
        //   358: goto            279
        //   361: astore          5
        //   363: goto            218
        //   366: astore_1       
        //   367: goto            164
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                           
        //  -----  -----  -----  -----  -------------------------------
        //  48     58     160    164    Ljava/io/FileNotFoundException;
        //  48     58     213    218    Ljava/io/IOException;
        //  48     58     275    279    Ljava/lang/Throwable;
        //  48     58     312    315    Any
        //  61     70     366    370    Ljava/io/FileNotFoundException;
        //  61     70     361    366    Ljava/io/IOException;
        //  61     70     357    361    Ljava/lang/Throwable;
        //  61     70     353    357    Any
        //  73     80     366    370    Ljava/io/FileNotFoundException;
        //  73     80     361    366    Ljava/io/IOException;
        //  73     80     357    361    Ljava/lang/Throwable;
        //  73     80     353    357    Any
        //  83     95     366    370    Ljava/io/FileNotFoundException;
        //  83     95     361    366    Ljava/io/IOException;
        //  83     95     357    361    Ljava/lang/Throwable;
        //  83     95     353    357    Any
        //  102    111    114    333    Ljava/lang/Exception;
        //  102    111    366    370    Ljava/io/FileNotFoundException;
        //  102    111    361    366    Ljava/io/IOException;
        //  102    111    357    361    Ljava/lang/Throwable;
        //  102    111    353    357    Any
        //  118    123    325    329    Ljava/lang/Exception;
        //  118    123    366    370    Ljava/io/FileNotFoundException;
        //  118    123    361    366    Ljava/io/IOException;
        //  118    123    357    361    Ljava/lang/Throwable;
        //  118    123    353    357    Any
        //  128    133    329    333    Ljava/lang/Exception;
        //  128    133    138    144    Any
        //  133    136    138    144    Any
        //  139    142    138    144    Any
        //  149    154    333    337    Ljava/lang/Exception;
        //  149    154    138    144    Any
        //  154    157    138    144    Any
        //  167    198    353    357    Any
        //  203    208    337    341    Ljava/lang/Exception;
        //  203    208    138    144    Any
        //  208    211    138    144    Any
        //  221    260    353    357    Any
        //  265    270    341    345    Ljava/lang/Exception;
        //  265    270    138    144    Any
        //  270    273    138    144    Any
        //  282    297    353    357    Any
        //  302    307    345    349    Ljava/lang/Exception;
        //  302    307    138    144    Any
        //  307    310    138    144    Any
        //  319    323    349    353    Ljava/lang/Exception;
        //  319    323    138    144    Any
        //  323    325    138    144    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 186, Size: 186
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3435)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3435)
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
    
    ByteArrayOutputStream a(final String p0, final List<String> p1, final boolean p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: iload_3        
        //     4: ifeq            48
        //     7: aload_1        
        //     8: aload_2        
        //     9: invokestatic    com/applovin/impl/sdk/gd.a:(Ljava/lang/String;Ljava/util/List;)Z
        //    12: ifne            48
        //    15: aload_0        
        //    16: getfield        com/applovin/impl/sdk/av.c:Lcom/applovin/sdk/AppLovinLogger;
        //    19: ldc             "FileManager"
        //    21: new             Ljava/lang/StringBuilder;
        //    24: dup            
        //    25: invokespecial   java/lang/StringBuilder.<init>:()V
        //    28: ldc_w           "Domain is not whitelisted, skipping precache for url: "
        //    31: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    34: aload_1        
        //    35: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    38: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    41: invokeinterface com/applovin/sdk/AppLovinLogger.d:(Ljava/lang/String;Ljava/lang/String;)V
        //    46: aconst_null    
        //    47: areturn        
        //    48: aload_1        
        //    49: astore          7
        //    51: aload_0        
        //    52: getfield        com/applovin/impl/sdk/av.b:Lcom/applovin/impl/sdk/AppLovinSdkImpl;
        //    55: getstatic       com/applovin/impl/sdk/ea.bS:Lcom/applovin/impl/sdk/ec;
        //    58: invokevirtual   com/applovin/impl/sdk/AppLovinSdkImpl.get:(Lcom/applovin/impl/sdk/ec;)Ljava/lang/Object;
        //    61: checkcast       Ljava/lang/Boolean;
        //    64: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    67: ifeq            112
        //    70: aload_1        
        //    71: astore          7
        //    73: aload_1        
        //    74: ldc_w           "https://"
        //    77: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //    80: ifne            112
        //    83: aload_0        
        //    84: getfield        com/applovin/impl/sdk/av.b:Lcom/applovin/impl/sdk/AppLovinSdkImpl;
        //    87: invokevirtual   com/applovin/impl/sdk/AppLovinSdkImpl.getLogger:()Lcom/applovin/sdk/AppLovinLogger;
        //    90: ldc             "FileManager"
        //    92: ldc_w           "Plaintext HTTP operation requested; upgrading to HTTPS due to universal SSL setting..."
        //    95: invokeinterface com/applovin/sdk/AppLovinLogger.w:(Ljava/lang/String;Ljava/lang/String;)V
        //   100: aload_1        
        //   101: ldc_w           "http://"
        //   104: ldc_w           "https://"
        //   107: invokevirtual   java/lang/String.replace:(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   110: astore          7
        //   112: aload_0        
        //   113: getfield        com/applovin/impl/sdk/av.c:Lcom/applovin/sdk/AppLovinLogger;
        //   116: ldc             "FileManager"
        //   118: new             Ljava/lang/StringBuilder;
        //   121: dup            
        //   122: invokespecial   java/lang/StringBuilder.<init>:()V
        //   125: ldc_w           "Loading "
        //   128: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   131: aload           7
        //   133: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   136: ldc_w           "..."
        //   139: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   142: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   145: invokeinterface com/applovin/sdk/AppLovinLogger.d:(Ljava/lang/String;Ljava/lang/String;)V
        //   150: new             Ljava/io/ByteArrayOutputStream;
        //   153: dup            
        //   154: invokespecial   java/io/ByteArrayOutputStream.<init>:()V
        //   157: astore          5
        //   159: new             Ljava/net/URL;
        //   162: dup            
        //   163: aload           7
        //   165: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //   168: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
        //   171: checkcast       Ljava/net/HttpURLConnection;
        //   174: astore_1       
        //   175: aload_1        
        //   176: aload_0        
        //   177: getfield        com/applovin/impl/sdk/av.b:Lcom/applovin/impl/sdk/AppLovinSdkImpl;
        //   180: getstatic       com/applovin/impl/sdk/ea.w:Lcom/applovin/impl/sdk/ec;
        //   183: invokevirtual   com/applovin/impl/sdk/AppLovinSdkImpl.get:(Lcom/applovin/impl/sdk/ec;)Ljava/lang/Object;
        //   186: checkcast       Ljava/lang/Integer;
        //   189: invokevirtual   java/lang/Integer.intValue:()I
        //   192: invokevirtual   java/net/HttpURLConnection.setConnectTimeout:(I)V
        //   195: aload_1        
        //   196: aload_0        
        //   197: getfield        com/applovin/impl/sdk/av.b:Lcom/applovin/impl/sdk/AppLovinSdkImpl;
        //   200: getstatic       com/applovin/impl/sdk/ea.y:Lcom/applovin/impl/sdk/ec;
        //   203: invokevirtual   com/applovin/impl/sdk/AppLovinSdkImpl.get:(Lcom/applovin/impl/sdk/ec;)Ljava/lang/Object;
        //   206: checkcast       Ljava/lang/Integer;
        //   209: invokevirtual   java/lang/Integer.intValue:()I
        //   212: invokevirtual   java/net/HttpURLConnection.setReadTimeout:(I)V
        //   215: aload_1        
        //   216: iconst_1       
        //   217: invokevirtual   java/net/HttpURLConnection.setDefaultUseCaches:(Z)V
        //   220: aload_1        
        //   221: iconst_1       
        //   222: invokevirtual   java/net/HttpURLConnection.setUseCaches:(Z)V
        //   225: aload_1        
        //   226: iconst_0       
        //   227: invokevirtual   java/net/HttpURLConnection.setAllowUserInteraction:(Z)V
        //   230: aload_1        
        //   231: iconst_1       
        //   232: invokevirtual   java/net/HttpURLConnection.setInstanceFollowRedirects:(Z)V
        //   235: aload_1        
        //   236: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
        //   239: istore          4
        //   241: iload           4
        //   243: sipush          200
        //   246: if_icmplt       257
        //   249: iload           4
        //   251: sipush          300
        //   254: if_icmplt       289
        //   257: iconst_0       
        //   258: ifeq            269
        //   261: new             Ljava/lang/NullPointerException;
        //   264: dup            
        //   265: invokespecial   java/lang/NullPointerException.<init>:()V
        //   268: athrow         
        //   269: aload           5
        //   271: ifnull          279
        //   274: aload           5
        //   276: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   279: aload_1        
        //   280: ifnull          287
        //   283: aload_1        
        //   284: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   287: aconst_null    
        //   288: areturn        
        //   289: aload_1        
        //   290: invokevirtual   java/net/HttpURLConnection.getInputStream:()Ljava/io/InputStream;
        //   293: astore_2       
        //   294: sipush          1024
        //   297: newarray        B
        //   299: astore          6
        //   301: aload_2        
        //   302: aload           6
        //   304: iconst_0       
        //   305: aload           6
        //   307: arraylength    
        //   308: invokevirtual   java/io/InputStream.read:([BII)I
        //   311: istore          4
        //   313: iload           4
        //   315: iflt            366
        //   318: aload           5
        //   320: aload           6
        //   322: iconst_0       
        //   323: iload           4
        //   325: invokevirtual   java/io/ByteArrayOutputStream.write:([BII)V
        //   328: goto            301
        //   331: astore          6
        //   333: aload           5
        //   335: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   338: aload_2        
        //   339: ifnull          346
        //   342: aload_2        
        //   343: invokevirtual   java/io/InputStream.close:()V
        //   346: aload           5
        //   348: ifnull          356
        //   351: aload           5
        //   353: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   356: aload_1        
        //   357: ifnull          364
        //   360: aload_1        
        //   361: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   364: aconst_null    
        //   365: areturn        
        //   366: aload_0        
        //   367: getfield        com/applovin/impl/sdk/av.c:Lcom/applovin/sdk/AppLovinLogger;
        //   370: ldc             "FileManager"
        //   372: new             Ljava/lang/StringBuilder;
        //   375: dup            
        //   376: invokespecial   java/lang/StringBuilder.<init>:()V
        //   379: ldc_w           "Loaded resource at "
        //   382: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   385: aload           7
        //   387: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   390: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   393: invokeinterface com/applovin/sdk/AppLovinLogger.d:(Ljava/lang/String;Ljava/lang/String;)V
        //   398: aload_2        
        //   399: ifnull          406
        //   402: aload_2        
        //   403: invokevirtual   java/io/InputStream.close:()V
        //   406: aload           5
        //   408: ifnull          416
        //   411: aload           5
        //   413: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   416: aload_1        
        //   417: ifnull          424
        //   420: aload_1        
        //   421: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   424: aload           5
        //   426: areturn        
        //   427: astore          6
        //   429: aconst_null    
        //   430: astore_1       
        //   431: aconst_null    
        //   432: astore_2       
        //   433: aconst_null    
        //   434: astore          5
        //   436: aload_0        
        //   437: getfield        com/applovin/impl/sdk/av.c:Lcom/applovin/sdk/AppLovinLogger;
        //   440: ldc             "FileManager"
        //   442: new             Ljava/lang/StringBuilder;
        //   445: dup            
        //   446: invokespecial   java/lang/StringBuilder.<init>:()V
        //   449: ldc_w           "Error loading "
        //   452: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   455: aload           7
        //   457: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   460: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   463: aload           6
        //   465: invokeinterface com/applovin/sdk/AppLovinLogger.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   470: aload_2        
        //   471: ifnull          478
        //   474: aload_2        
        //   475: invokevirtual   java/io/InputStream.close:()V
        //   478: aload           5
        //   480: ifnull          488
        //   483: aload           5
        //   485: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   488: aload_1        
        //   489: ifnull          496
        //   492: aload_1        
        //   493: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   496: aconst_null    
        //   497: areturn        
        //   498: astore_2       
        //   499: aconst_null    
        //   500: astore_1       
        //   501: aconst_null    
        //   502: astore          5
        //   504: aload           6
        //   506: ifnull          514
        //   509: aload           6
        //   511: invokevirtual   java/io/InputStream.close:()V
        //   514: aload           5
        //   516: ifnull          524
        //   519: aload           5
        //   521: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   524: aload_1        
        //   525: ifnull          532
        //   528: aload_1        
        //   529: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   532: aload_2        
        //   533: athrow         
        //   534: astore_2       
        //   535: goto            269
        //   538: astore_2       
        //   539: goto            279
        //   542: astore_1       
        //   543: goto            287
        //   546: astore          6
        //   548: goto            338
        //   551: astore_2       
        //   552: goto            346
        //   555: astore_2       
        //   556: goto            356
        //   559: astore_1       
        //   560: goto            364
        //   563: astore_2       
        //   564: goto            406
        //   567: astore_2       
        //   568: goto            416
        //   571: astore_1       
        //   572: goto            424
        //   575: astore_2       
        //   576: goto            478
        //   579: astore_2       
        //   580: goto            488
        //   583: astore_1       
        //   584: goto            496
        //   587: astore          6
        //   589: goto            514
        //   592: astore          5
        //   594: goto            524
        //   597: astore_1       
        //   598: goto            532
        //   601: astore_2       
        //   602: aconst_null    
        //   603: astore_1       
        //   604: goto            504
        //   607: astore_2       
        //   608: goto            504
        //   611: astore          7
        //   613: aload_2        
        //   614: astore          6
        //   616: aload           7
        //   618: astore_2       
        //   619: goto            504
        //   622: astore          7
        //   624: aload_2        
        //   625: astore          6
        //   627: aload           7
        //   629: astore_2       
        //   630: goto            504
        //   633: astore          6
        //   635: aconst_null    
        //   636: astore_1       
        //   637: aconst_null    
        //   638: astore_2       
        //   639: goto            436
        //   642: astore          6
        //   644: aconst_null    
        //   645: astore_2       
        //   646: goto            436
        //   649: astore          6
        //   651: goto            436
        //    Signature:
        //  (Ljava/lang/String;Ljava/util/List<Ljava/lang/String;>;Z)Ljava/io/ByteArrayOutputStream;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  150    159    427    436    Ljava/io/IOException;
        //  150    159    498    504    Any
        //  159    175    633    642    Ljava/io/IOException;
        //  159    175    601    607    Any
        //  175    241    642    649    Ljava/io/IOException;
        //  175    241    607    611    Any
        //  261    269    534    538    Ljava/lang/Exception;
        //  274    279    538    542    Ljava/lang/Exception;
        //  283    287    542    546    Ljava/lang/Exception;
        //  289    294    642    649    Ljava/io/IOException;
        //  289    294    607    611    Any
        //  294    301    649    654    Ljava/io/IOException;
        //  294    301    611    622    Any
        //  301    313    649    654    Ljava/io/IOException;
        //  301    313    611    622    Any
        //  318    328    331    563    Ljava/lang/Exception;
        //  318    328    649    654    Ljava/io/IOException;
        //  318    328    611    622    Any
        //  333    338    546    551    Ljava/lang/Exception;
        //  333    338    649    654    Ljava/io/IOException;
        //  333    338    611    622    Any
        //  342    346    551    555    Ljava/lang/Exception;
        //  351    356    555    559    Ljava/lang/Exception;
        //  360    364    559    563    Ljava/lang/Exception;
        //  366    398    649    654    Ljava/io/IOException;
        //  366    398    611    622    Any
        //  402    406    563    567    Ljava/lang/Exception;
        //  411    416    567    571    Ljava/lang/Exception;
        //  420    424    571    575    Ljava/lang/Exception;
        //  436    470    622    633    Any
        //  474    478    575    579    Ljava/lang/Exception;
        //  483    488    579    583    Ljava/lang/Exception;
        //  492    496    583    587    Ljava/lang/Exception;
        //  509    514    587    592    Ljava/lang/Exception;
        //  519    524    592    597    Ljava/lang/Exception;
        //  528    532    597    601    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 305, Size: 305
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3435)
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
    
    File a(final String s, final Context context, final boolean b) {
        if (!AppLovinSdkUtils.isValidString(s)) {
            this.b.getLogger().d("FileManager", "Nothing to look up, skipping...");
        }
        else {
            this.c.d("FileManager", "Looking up cached resource: " + s);
            if (this.a(context) || b) {
                String replace = s;
                if (s.contains("icon")) {
                    replace = s.replace("/", "_").replace(".", "_");
                }
                synchronized (this.d) {
                    final File e = this.e(context);
                    final File file = new File(e, replace);
                    try {
                        e.mkdirs();
                        return file;
                    }
                    catch (Exception ex) {
                        return null;
                    }
                }
            }
        }
        return null;
    }
    
    String a(final Context context, final String s, final String s2, final List<String> list, final boolean b, final z z) throws MalformedURLException {
        return this.a(context, s, s2, list, b, false, z);
    }
    
    String a(final Context context, final String s, String string, final List<String> list, final boolean b, final boolean b2, final z z) throws MalformedURLException {
        if (!AppLovinSdkUtils.isValidString(s)) {
            this.b.getLogger().d("FileManager", "Nothing to cache, skipping...");
            string = null;
        }
        else {
            final String lastPathSegment = Uri.parse(s).getLastPathSegment();
            if (AppLovinSdkUtils.isValidString(lastPathSegment) && AppLovinSdkUtils.isValidString(string)) {
                string += lastPathSegment;
            }
            else {
                string = lastPathSegment;
            }
            final File a = this.a(string, context, false);
            if (!this.a(a, s, list, b, z)) {
                return null;
            }
            this.c.d("FileManager", "Caching succeeded for file " + string);
            if (b2) {
                return Uri.fromFile(a).toString();
            }
        }
        return string;
    }
    
    protected boolean a(final Context context) {
        if (ab.a("android.permission.WRITE_EXTERNAL_STORAGE", context)) {
            return true;
        }
        if (ab.f() && !this.b.get(ea.cc)) {
            return true;
        }
        this.b.getLogger().w("FileManager", "Application lacks required WRITE_EXTERNAL_STORAGE manifest permission.");
        return false;
    }
    
    boolean a(final ByteArrayOutputStream byteArrayOutputStream, final File file) {
        if (file == null) {
            return false;
        }
        this.c.d("FileManager", "Caching " + file.getAbsolutePath() + "...");
        if (byteArrayOutputStream == null || byteArrayOutputStream.size() <= 0) {
            this.c.w("FileManager", "No data for " + file.getAbsolutePath());
            return false;
        }
        if (!this.b(byteArrayOutputStream, file)) {
            this.c.e("FileManager", "Unable to cache " + file.getAbsolutePath());
            return false;
        }
        this.c.d("FileManager", "Caching completed for " + file);
        return true;
    }
    
    boolean a(final File file, final String s, final List<String> list, final z z) {
        return this.a(file, s, list, true, z);
    }
    
    boolean a(final File file, final String s, final List<String> list, final boolean b, final z z) {
        if (file != null && file.exists() && !file.isDirectory()) {
            this.b.getLogger().d("FileManager", "File exists for " + s);
            if (z != null) {
                z.b(file.length());
            }
            return true;
        }
        final ByteArrayOutputStream a = this.a(s, list, b);
        if (z != null) {
            z.a(a.size());
        }
        return this.a(a, file);
    }
    
    public boolean a(final String s, final Context context) {
        synchronized (this.d) {
            return this.b(s, context, false);
        }
    }
    
    public List<File> b(final Context context) {
        final File e = this.e(context);
        if (e.isDirectory()) {
            synchronized (this.d) {
                return Arrays.asList(e.listFiles());
            }
        }
        return new ArrayList<File>(0);
    }
    
    boolean b(final String s, final Context context, final boolean b) {
        while (true) {
            synchronized (this.d) {
                final File a = this.a(s, context, b);
                if (a != null && a.exists() && !a.isDirectory()) {
                    return true;
                }
            }
            return false;
        }
    }
    
    boolean c(final Context context) {
        if (this.b.get(ea.cd)) {
            try {
                this.a(".nomedia", context, false);
                final File file = new File(this.e(context), ".nomedia");
                if (file != null) {
                    if (file.exists()) {
                        return true;
                    }
                    this.b.getLogger().d("FileManager", "Dropping .nomedia file at " + file.getAbsolutePath());
                    return file.createNewFile();
                }
            }
            catch (Exception ex) {
                this.b.getLogger().w("FileManager", "Failed to create nomedia file", ex);
            }
        }
        return false;
    }
    
    void d(final Context context) {
        try {
            if (this.a()) {
                if (!this.b.e()) {
                    this.c.e("FileManager", "Cannot empty file cache after SDK has completed initialization and ad loads are in progress!");
                    return;
                }
                this.c.d("FileManager", "Compacting cache...");
                synchronized (this.d) {
                    this.a(this.f(context), context);
                }
            }
        }
        catch (Exception ex) {
            this.c.e("FileManager", "Caught exception while compacting cache!", ex);
            this.b.getSettingsManager().a(ea.aY, false);
            this.b.getSettingsManager().a();
        }
    }
}
