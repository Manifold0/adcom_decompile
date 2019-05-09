// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.Libraries;

import java.util.Iterator;
import org.json.JSONObject;
import org.json.JSONArray;
import com.chartboost.sdk.impl.bh;
import com.chartboost.sdk.Tracking.a;
import com.chartboost.sdk.i;
import android.content.Context;
import com.chartboost.sdk.impl.s;
import com.chartboost.sdk.Model.e;
import java.util.concurrent.atomic.AtomicReference;
import java.io.File;

public class f
{
    public final File a;
    public final File b;
    private final AtomicReference<e> c;
    private final g d;
    private final AtomicReference<g> e;
    private s f;
    
    public f(final s p0, final Context p1, final AtomicReference<e> p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokespecial   java/lang/Object.<init>:()V
        //     4: aload_0        
        //     5: aload_1        
        //     6: putfield        com/chartboost/sdk/Libraries/f.f:Lcom/chartboost/sdk/impl/s;
        //     9: aload_0        
        //    10: new             Lcom/chartboost/sdk/Libraries/g;
        //    13: dup            
        //    14: aload_2        
        //    15: invokevirtual   android/content/Context.getCacheDir:()Ljava/io/File;
        //    18: invokespecial   com/chartboost/sdk/Libraries/g.<init>:(Ljava/io/File;)V
        //    21: putfield        com/chartboost/sdk/Libraries/f.d:Lcom/chartboost/sdk/Libraries/g;
        //    24: aload_0        
        //    25: new             Ljava/util/concurrent/atomic/AtomicReference;
        //    28: dup            
        //    29: invokespecial   java/util/concurrent/atomic/AtomicReference.<init>:()V
        //    32: putfield        com/chartboost/sdk/Libraries/f.e:Ljava/util/concurrent/atomic/AtomicReference;
        //    35: aload_0        
        //    36: aload_3        
        //    37: putfield        com/chartboost/sdk/Libraries/f.c:Ljava/util/concurrent/atomic/AtomicReference;
        //    40: aload_1        
        //    41: invokevirtual   com/chartboost/sdk/impl/s.b:()Ljava/io/File;
        //    44: astore_1       
        //    45: aload_1        
        //    46: ifnull          64
        //    49: aload_0        
        //    50: getfield        com/chartboost/sdk/Libraries/f.e:Ljava/util/concurrent/atomic/AtomicReference;
        //    53: new             Lcom/chartboost/sdk/Libraries/g;
        //    56: dup            
        //    57: aload_1        
        //    58: invokespecial   com/chartboost/sdk/Libraries/g.<init>:(Ljava/io/File;)V
        //    61: invokevirtual   java/util/concurrent/atomic/AtomicReference.set:(Ljava/lang/Object;)V
        //    64: aload_0        
        //    65: new             Ljava/io/File;
        //    68: dup            
        //    69: aload_0        
        //    70: getfield        com/chartboost/sdk/Libraries/f.d:Lcom/chartboost/sdk/Libraries/g;
        //    73: getfield        com/chartboost/sdk/Libraries/g.a:Ljava/io/File;
        //    76: ldc             "track"
        //    78: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //    81: putfield        com/chartboost/sdk/Libraries/f.b:Ljava/io/File;
        //    84: aload_0        
        //    85: new             Ljava/io/File;
        //    88: dup            
        //    89: aload_0        
        //    90: getfield        com/chartboost/sdk/Libraries/f.d:Lcom/chartboost/sdk/Libraries/g;
        //    93: getfield        com/chartboost/sdk/Libraries/g.a:Ljava/io/File;
        //    96: ldc             "session"
        //    98: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   101: putfield        com/chartboost/sdk/Libraries/f.a:Ljava/io/File;
        //   104: iconst_2       
        //   105: anewarray       Lcom/chartboost/sdk/Libraries/g;
        //   108: astore_1       
        //   109: aload_1        
        //   110: iconst_0       
        //   111: aload_0        
        //   112: getfield        com/chartboost/sdk/Libraries/f.d:Lcom/chartboost/sdk/Libraries/g;
        //   115: aastore        
        //   116: aload_1        
        //   117: iconst_1       
        //   118: aload_0        
        //   119: getfield        com/chartboost/sdk/Libraries/f.e:Ljava/util/concurrent/atomic/AtomicReference;
        //   122: invokevirtual   java/util/concurrent/atomic/AtomicReference.get:()Ljava/lang/Object;
        //   125: checkcast       Lcom/chartboost/sdk/Libraries/g;
        //   128: aastore        
        //   129: aload_1        
        //   130: arraylength    
        //   131: istore          8
        //   133: iconst_0       
        //   134: istore          4
        //   136: iload           4
        //   138: iload           8
        //   140: if_icmpge       555
        //   143: aload_1        
        //   144: iload           4
        //   146: aaload         
        //   147: astore_2       
        //   148: aload_2        
        //   149: aload_0        
        //   150: getfield        com/chartboost/sdk/Libraries/f.d:Lcom/chartboost/sdk/Libraries/g;
        //   153: if_acmpne       366
        //   156: iconst_1       
        //   157: istore          5
        //   159: aload_2        
        //   160: ifnull          504
        //   163: iload           5
        //   165: ifne            175
        //   168: aload_0        
        //   169: invokevirtual   com/chartboost/sdk/Libraries/f.a:()Z
        //   172: ifeq            504
        //   175: invokestatic    java/lang/System.currentTimeMillis:()J
        //   178: getstatic       java/util/concurrent/TimeUnit.DAYS:Ljava/util/concurrent/TimeUnit;
        //   181: aload_3        
        //   182: invokevirtual   java/util/concurrent/atomic/AtomicReference.get:()Ljava/lang/Object;
        //   185: checkcast       Lcom/chartboost/sdk/Model/e;
        //   188: getfield        com/chartboost/sdk/Model/e.w:I
        //   191: i2l            
        //   192: invokevirtual   java/util/concurrent/TimeUnit.toMillis:(J)J
        //   195: lsub           
        //   196: lstore          11
        //   198: new             Ljava/io/File;
        //   201: dup            
        //   202: aload_2        
        //   203: getfield        com/chartboost/sdk/Libraries/g.a:Ljava/io/File;
        //   206: ldc             "templates"
        //   208: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   211: astore          13
        //   213: aload           13
        //   215: invokevirtual   java/io/File.exists:()Z
        //   218: ifeq            429
        //   221: aload           13
        //   223: invokevirtual   java/io/File.listFiles:()[Ljava/io/File;
        //   226: astore          13
        //   228: aload           13
        //   230: ifnull          429
        //   233: aload           13
        //   235: arraylength    
        //   236: istore          9
        //   238: iconst_0       
        //   239: istore          6
        //   241: iload           6
        //   243: iload           9
        //   245: if_icmpge       429
        //   248: aload           13
        //   250: iload           6
        //   252: aaload         
        //   253: astore          14
        //   255: aload           14
        //   257: invokevirtual   java/io/File.isDirectory:()Z
        //   260: ifeq            556
        //   263: aload           14
        //   265: invokevirtual   java/io/File.listFiles:()[Ljava/io/File;
        //   268: astore          15
        //   270: aload           15
        //   272: ifnull          372
        //   275: aload           15
        //   277: arraylength    
        //   278: istore          10
        //   280: iconst_0       
        //   281: istore          7
        //   283: iload           7
        //   285: iload           10
        //   287: if_icmpge       372
        //   290: aload           15
        //   292: iload           7
        //   294: aaload         
        //   295: astore          16
        //   297: iload           5
        //   299: ifne            313
        //   302: aload           16
        //   304: invokevirtual   java/io/File.lastModified:()J
        //   307: lload           11
        //   309: lcmp           
        //   310: ifge            349
        //   313: aload           16
        //   315: invokevirtual   java/io/File.delete:()Z
        //   318: ifne            349
        //   321: ldc             "FileCache"
        //   323: new             Ljava/lang/StringBuilder;
        //   326: dup            
        //   327: invokespecial   java/lang/StringBuilder.<init>:()V
        //   330: ldc             "Unable to delete "
        //   332: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   335: aload           16
        //   337: invokevirtual   java/io/File.getPath:()Ljava/lang/String;
        //   340: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   343: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   346: invokestatic    com/chartboost/sdk/Libraries/CBLogging.b:(Ljava/lang/String;Ljava/lang/String;)V
        //   349: iload           7
        //   351: iconst_1       
        //   352: iadd           
        //   353: istore          7
        //   355: goto            283
        //   358: astore_1       
        //   359: aload_1        
        //   360: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   363: goto            64
        //   366: iconst_0       
        //   367: istore          5
        //   369: goto            159
        //   372: aload           14
        //   374: invokevirtual   java/io/File.listFiles:()[Ljava/io/File;
        //   377: astore          15
        //   379: aload           15
        //   381: ifnull          556
        //   384: aload           15
        //   386: arraylength    
        //   387: ifne            556
        //   390: aload           14
        //   392: invokevirtual   java/io/File.delete:()Z
        //   395: ifne            556
        //   398: ldc             "FileCache"
        //   400: new             Ljava/lang/StringBuilder;
        //   403: dup            
        //   404: invokespecial   java/lang/StringBuilder.<init>:()V
        //   407: ldc             "Unable to delete "
        //   409: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   412: aload           14
        //   414: invokevirtual   java/io/File.getPath:()Ljava/lang/String;
        //   417: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   420: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   423: invokestatic    com/chartboost/sdk/Libraries/CBLogging.b:(Ljava/lang/String;Ljava/lang/String;)V
        //   426: goto            556
        //   429: new             Ljava/io/File;
        //   432: dup            
        //   433: aload_2        
        //   434: getfield        com/chartboost/sdk/Libraries/g.a:Ljava/io/File;
        //   437: ldc             ".adId"
        //   439: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   442: astore          13
        //   444: aload           13
        //   446: invokevirtual   java/io/File.exists:()Z
        //   449: ifeq            504
        //   452: iload           5
        //   454: ifne            468
        //   457: aload           13
        //   459: invokevirtual   java/io/File.lastModified:()J
        //   462: lload           11
        //   464: lcmp           
        //   465: ifge            504
        //   468: aload           13
        //   470: invokevirtual   java/io/File.delete:()Z
        //   473: ifne            504
        //   476: ldc             "FileCache"
        //   478: new             Ljava/lang/StringBuilder;
        //   481: dup            
        //   482: invokespecial   java/lang/StringBuilder.<init>:()V
        //   485: ldc             "Unable to delete "
        //   487: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   490: aload           13
        //   492: invokevirtual   java/io/File.getPath:()Ljava/lang/String;
        //   495: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   498: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   501: invokestatic    com/chartboost/sdk/Libraries/CBLogging.b:(Ljava/lang/String;Ljava/lang/String;)V
        //   504: iload           4
        //   506: iconst_1       
        //   507: iadd           
        //   508: istore          4
        //   510: goto            136
        //   513: astore          13
        //   515: ldc             "FileCache"
        //   517: new             Ljava/lang/StringBuilder;
        //   520: dup            
        //   521: invokespecial   java/lang/StringBuilder.<init>:()V
        //   524: ldc             "Exception while cleaning up templates directory at "
        //   526: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   529: aload_2        
        //   530: getfield        com/chartboost/sdk/Libraries/g.f:Ljava/io/File;
        //   533: invokevirtual   java/io/File.getPath:()Ljava/lang/String;
        //   536: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   539: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   542: aload           13
        //   544: invokestatic    com/chartboost/sdk/Libraries/CBLogging.a:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   547: aload           13
        //   549: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   552: goto            504
        //   555: return         
        //   556: iload           6
        //   558: iconst_1       
        //   559: iadd           
        //   560: istore          6
        //   562: goto            241
        //    Signature:
        //  (Lcom/chartboost/sdk/impl/s;Landroid/content/Context;Ljava/util/concurrent/atomic/AtomicReference<Lcom/chartboost/sdk/Model/e;>;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  40     45     358    366    Ljava/lang/Exception;
        //  49     64     358    366    Ljava/lang/Exception;
        //  148    156    513    555    Ljava/lang/Exception;
        //  168    175    513    555    Ljava/lang/Exception;
        //  175    228    513    555    Ljava/lang/Exception;
        //  233    238    513    555    Ljava/lang/Exception;
        //  255    270    513    555    Ljava/lang/Exception;
        //  275    280    513    555    Ljava/lang/Exception;
        //  302    313    513    555    Ljava/lang/Exception;
        //  313    349    513    555    Ljava/lang/Exception;
        //  372    379    513    555    Ljava/lang/Exception;
        //  384    426    513    555    Ljava/lang/Exception;
        //  429    452    513    555    Ljava/lang/Exception;
        //  457    468    513    555    Ljava/lang/Exception;
        //  468    504    513    555    Ljava/lang/Exception;
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
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:713)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:549)
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
    
    public String a(final String s) {
        final File file = new File(this.d().g, s);
        if (file.exists()) {
            return file.getPath();
        }
        return null;
    }
    
    public boolean a() {
        try {
            final String c = this.f.c();
            if (c != null && c.equals("mounted") && !i.n) {
                return true;
            }
        }
        catch (Exception ex) {
            com.chartboost.sdk.Tracking.a.a(this.getClass(), "isExternalStorageAvailable", ex);
        }
        CBLogging.e("FileCache", "External Storage unavailable");
        return false;
    }
    
    public byte[] a(final File file) {
        final byte[] array = null;
        // monitorenter(this)
        byte[] array2 = null;
        if (file == null) {
            array2 = array;
        }
        else {
            try {
                bh.b(file);
            }
            catch (Exception ex) {
                CBLogging.a("FileCache", "Error loading cache from disk", ex);
                com.chartboost.sdk.Tracking.a.a(this.getClass(), "readByteArrayFromDisk", ex);
            }
            finally {
            }
            // monitorexit(this)
        }
        // monitorexit(this)
        return array2;
    }
    
    public long b(final File file) {
        long n = 0L;
        Label_0080: {
            if (file == null) {
                break Label_0080;
            }
            long length = n;
            try {
                if (file.isDirectory()) {
                    length = n;
                    final File[] listFiles = file.listFiles();
                    length = n;
                    if (listFiles != null) {
                        length = n;
                        final int length2 = listFiles.length;
                        int n2 = 0;
                        while (true) {
                            length = n;
                            if (n2 >= length2) {
                                break;
                            }
                            length = n;
                            final long b = this.b(listFiles[n2]);
                            ++n2;
                            n += b;
                        }
                    }
                }
                else {
                    length = n;
                    if (file != null) {
                        length = n;
                        length = file.length();
                    }
                }
                return length;
            }
            catch (Exception ex) {
                com.chartboost.sdk.Tracking.a.a(f.class, "getFolderSize", ex);
                return length;
            }
        }
    }
    
    public JSONArray b() {
        final JSONArray jsonArray = new JSONArray();
        final String[] list = this.d().g.list();
        if (list != null) {
            for (int length = list.length, i = 0; i < length; ++i) {
                final String s = list[i];
                if (!s.equals(".nomedia") && !s.endsWith(".tmp")) {
                    jsonArray.put((Object)s);
                }
            }
        }
        return jsonArray;
    }
    
    public boolean b(final String s) {
        return this.d().d != null && s != null && new File(this.d().d, s).exists();
    }
    
    public JSONObject c() {
        JSONObject jsonObject;
        while (true) {
            jsonObject = new JSONObject();
            while (true) {
                int n = 0;
                Label_0186: {
                    try {
                        final File a = this.d().a;
                        JSONArray jsonArray = null;
                        String[] list = null;
                        Block_7: {
                            for (final String s : this.c.get().x) {
                                if (!s.equals("templates")) {
                                    final File file = new File(a, s);
                                    jsonArray = new JSONArray();
                                    if (file.exists()) {
                                        list = file.list();
                                        if (list != null) {
                                            final int length = list.length;
                                            n = 0;
                                            if (n < length) {
                                                break Block_7;
                                            }
                                        }
                                    }
                                    com.chartboost.sdk.Libraries.e.a(jsonObject, s, jsonArray);
                                }
                            }
                            break;
                        }
                        final String s2 = list[n];
                        if (!s2.equals(".nomedia") && !s2.endsWith(".tmp")) {
                            jsonArray.put((Object)s2);
                        }
                        break Label_0186;
                    }
                    catch (Exception ex) {
                        com.chartboost.sdk.Tracking.a.a(this.getClass(), "getWebViewCacheAssets", ex);
                    }
                    break;
                }
                ++n;
                continue;
            }
        }
        return jsonObject;
    }
    
    public void c(final File p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: aload_1        
        //     5: ldc_w           "rw"
        //     8: invokespecial   java/io/RandomAccessFile.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //    11: astore_3       
        //    12: aload_3        
        //    13: astore_1       
        //    14: aload_3        
        //    15: lconst_0       
        //    16: invokevirtual   java/io/RandomAccessFile.seek:(J)V
        //    19: aload_3        
        //    20: astore_1       
        //    21: aload_3        
        //    22: invokevirtual   java/io/RandomAccessFile.read:()I
        //    25: istore_2       
        //    26: aload_3        
        //    27: astore_1       
        //    28: aload_3        
        //    29: lconst_0       
        //    30: invokevirtual   java/io/RandomAccessFile.seek:(J)V
        //    33: aload_3        
        //    34: astore_1       
        //    35: aload_3        
        //    36: iload_2        
        //    37: invokevirtual   java/io/RandomAccessFile.write:(I)V
        //    40: aload_3        
        //    41: ifnull          48
        //    44: aload_3        
        //    45: invokevirtual   java/io/RandomAccessFile.close:()V
        //    48: return         
        //    49: astore          4
        //    51: aconst_null    
        //    52: astore_3       
        //    53: aload_3        
        //    54: astore_1       
        //    55: ldc             "FileCache"
        //    57: ldc_w           "File not found when attempting to touch"
        //    60: aload           4
        //    62: invokestatic    com/chartboost/sdk/Libraries/CBLogging.a:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //    65: aload_3        
        //    66: ifnull          48
        //    69: aload_3        
        //    70: invokevirtual   java/io/RandomAccessFile.close:()V
        //    73: return         
        //    74: astore_1       
        //    75: return         
        //    76: astore          4
        //    78: aconst_null    
        //    79: astore_3       
        //    80: aload_3        
        //    81: astore_1       
        //    82: ldc             "FileCache"
        //    84: ldc_w           "IOException when attempting to touch file"
        //    87: aload           4
        //    89: invokestatic    com/chartboost/sdk/Libraries/CBLogging.a:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //    92: aload_3        
        //    93: ifnull          48
        //    96: aload_3        
        //    97: invokevirtual   java/io/RandomAccessFile.close:()V
        //   100: return         
        //   101: astore_1       
        //   102: return         
        //   103: astore_3       
        //   104: aconst_null    
        //   105: astore_1       
        //   106: aload_1        
        //   107: ifnull          114
        //   110: aload_1        
        //   111: invokevirtual   java/io/RandomAccessFile.close:()V
        //   114: aload_3        
        //   115: athrow         
        //   116: astore_1       
        //   117: return         
        //   118: astore_1       
        //   119: goto            114
        //   122: astore_3       
        //   123: goto            106
        //   126: astore          4
        //   128: goto            80
        //   131: astore          4
        //   133: goto            53
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                           
        //  -----  -----  -----  -----  -------------------------------
        //  0      12     49     53     Ljava/io/FileNotFoundException;
        //  0      12     76     80     Ljava/io/IOException;
        //  0      12     103    106    Any
        //  14     19     131    136    Ljava/io/FileNotFoundException;
        //  14     19     126    131    Ljava/io/IOException;
        //  14     19     122    126    Any
        //  21     26     131    136    Ljava/io/FileNotFoundException;
        //  21     26     126    131    Ljava/io/IOException;
        //  21     26     122    126    Any
        //  28     33     131    136    Ljava/io/FileNotFoundException;
        //  28     33     126    131    Ljava/io/IOException;
        //  28     33     122    126    Any
        //  35     40     131    136    Ljava/io/FileNotFoundException;
        //  35     40     126    131    Ljava/io/IOException;
        //  35     40     122    126    Any
        //  44     48     116    118    Ljava/io/IOException;
        //  55     65     122    126    Any
        //  69     73     74     76     Ljava/io/IOException;
        //  82     92     122    126    Any
        //  96     100    101    103    Ljava/io/IOException;
        //  110    114    118    122    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0048:
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
    
    public g d() {
        if (this.a()) {
            final g g = this.e.get();
            while (true) {
                g g2;
                if ((g2 = g) != null) {
                    break Label_0066;
                }
                try {
                    final File b = this.f.b();
                    g2 = g;
                    if (b != null) {
                        this.e.compareAndSet(null, new g(b));
                        g2 = this.e.get();
                    }
                    if (g2 != null) {
                        return g2;
                    }
                }
                catch (Exception ex) {
                    com.chartboost.sdk.Tracking.a.a(this.getClass(), "currentLocations", ex);
                    g2 = g;
                    continue;
                }
                break;
            }
        }
        return this.d;
    }
    
    public JSONObject e() {
        final JSONObject jsonObject = new JSONObject();
        final g g = this.e.get();
        if (g != null) {
            com.chartboost.sdk.Libraries.e.a(jsonObject, ".chartboost-external-folder-size", this.b(g.a));
        }
        com.chartboost.sdk.Libraries.e.a(jsonObject, ".chartboost-internal-folder-size", this.b(this.d.a));
        final File a = this.d().a;
        final String[] list = a.list();
        if (list != null && list.length > 0) {
            for (int length = list.length, i = 0; i < length; ++i) {
                final File file = new File(a, list[i]);
                final JSONObject jsonObject2 = new JSONObject();
                com.chartboost.sdk.Libraries.e.a(jsonObject2, file.getName() + "-size", this.b(file));
                final String[] list2 = file.list();
                if (list2 != null) {
                    com.chartboost.sdk.Libraries.e.a(jsonObject2, "count", list2.length);
                }
                com.chartboost.sdk.Libraries.e.a(jsonObject, file.getName(), jsonObject2);
            }
        }
        return jsonObject;
    }
}
