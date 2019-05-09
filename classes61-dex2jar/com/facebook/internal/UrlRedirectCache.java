// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.internal;

import java.io.OutputStream;
import com.facebook.LoggingBehavior;
import java.io.Closeable;
import java.io.IOException;
import android.net.Uri;

class UrlRedirectCache
{
    private static final String REDIRECT_CONTENT_TAG;
    static final String TAG;
    private static FileLruCache urlRedirectCache;
    
    static {
        TAG = UrlRedirectCache.class.getSimpleName();
        REDIRECT_CONTENT_TAG = UrlRedirectCache.TAG + "_Redirect";
    }
    
    static void cacheUriRedirect(final Uri uri, final Uri uri2) {
        if (uri == null || uri2 == null) {
            return;
        }
        Closeable openPutStream = null;
        try {
            ((OutputStream)(openPutStream = getCache().openPutStream(uri.toString(), UrlRedirectCache.REDIRECT_CONTENT_TAG))).write(uri2.toString().getBytes());
        }
        catch (IOException ex) {}
        finally {
            Utility.closeQuietly(openPutStream);
        }
    }
    
    static void clearCache() {
        try {
            getCache().clearCache();
        }
        catch (IOException ex) {
            Logger.log(LoggingBehavior.CACHE, 5, UrlRedirectCache.TAG, "clearCache failed " + ex.getMessage());
        }
    }
    
    static FileLruCache getCache() throws IOException {
        synchronized (UrlRedirectCache.class) {
            if (UrlRedirectCache.urlRedirectCache == null) {
                UrlRedirectCache.urlRedirectCache = new FileLruCache(UrlRedirectCache.TAG, new FileLruCache.Limits());
            }
            return UrlRedirectCache.urlRedirectCache;
        }
    }
    
    static Uri getRedirectedUri(final Uri p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ifnonnull       6
        //     4: aconst_null    
        //     5: areturn        
        //     6: aload_0        
        //     7: invokevirtual   android/net/Uri.toString:()Ljava/lang/String;
        //    10: astore          4
        //    12: aconst_null    
        //    13: astore_3       
        //    14: aconst_null    
        //    15: astore_0       
        //    16: invokestatic    com/facebook/internal/UrlRedirectCache.getCache:()Lcom/facebook/internal/FileLruCache;
        //    19: astore          6
        //    21: iconst_0       
        //    22: istore_1       
        //    23: aconst_null    
        //    24: astore_0       
        //    25: aload           4
        //    27: astore_3       
        //    28: aload           6
        //    30: aload_3        
        //    31: getstatic       com/facebook/internal/UrlRedirectCache.REDIRECT_CONTENT_TAG:Ljava/lang/String;
        //    34: invokevirtual   com/facebook/internal/FileLruCache.get:(Ljava/lang/String;Ljava/lang/String;)Ljava/io/InputStream;
        //    37: astore          4
        //    39: aload           4
        //    41: ifnull          166
        //    44: iconst_1       
        //    45: istore_1       
        //    46: new             Ljava/io/InputStreamReader;
        //    49: dup            
        //    50: aload           4
        //    52: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;)V
        //    55: astore          4
        //    57: aload           4
        //    59: astore_0       
        //    60: aload           4
        //    62: astore_3       
        //    63: sipush          128
        //    66: newarray        C
        //    68: astore          5
        //    70: aload           4
        //    72: astore_0       
        //    73: aload           4
        //    75: astore_3       
        //    76: new             Ljava/lang/StringBuilder;
        //    79: dup            
        //    80: invokespecial   java/lang/StringBuilder.<init>:()V
        //    83: astore          7
        //    85: aload           4
        //    87: astore_0       
        //    88: aload           4
        //    90: astore_3       
        //    91: aload           4
        //    93: aload           5
        //    95: iconst_0       
        //    96: aload           5
        //    98: arraylength    
        //    99: invokevirtual   java/io/InputStreamReader.read:([CII)I
        //   102: istore_2       
        //   103: iload_2        
        //   104: ifle            133
        //   107: aload           4
        //   109: astore_0       
        //   110: aload           4
        //   112: astore_3       
        //   113: aload           7
        //   115: aload           5
        //   117: iconst_0       
        //   118: iload_2        
        //   119: invokevirtual   java/lang/StringBuilder.append:([CII)Ljava/lang/StringBuilder;
        //   122: pop            
        //   123: goto            85
        //   126: astore_3       
        //   127: aload_0        
        //   128: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   131: aconst_null    
        //   132: areturn        
        //   133: aload           4
        //   135: astore_0       
        //   136: aload           4
        //   138: astore_3       
        //   139: aload           4
        //   141: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   144: aload           4
        //   146: astore_0       
        //   147: aload           4
        //   149: astore_3       
        //   150: aload           7
        //   152: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   155: astore          5
        //   157: aload           5
        //   159: astore_3       
        //   160: aload           4
        //   162: astore_0       
        //   163: goto            28
        //   166: iload_1        
        //   167: ifeq            181
        //   170: aload_3        
        //   171: invokestatic    android/net/Uri.parse:(Ljava/lang/String;)Landroid/net/Uri;
        //   174: astore_3       
        //   175: aload_0        
        //   176: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   179: aload_3        
        //   180: areturn        
        //   181: aload_0        
        //   182: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   185: aconst_null    
        //   186: areturn        
        //   187: astore_0       
        //   188: aload_3        
        //   189: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   192: aload_0        
        //   193: athrow         
        //   194: astore          4
        //   196: aload_0        
        //   197: astore_3       
        //   198: aload           4
        //   200: astore_0       
        //   201: goto            188
        //   204: astore_3       
        //   205: goto            127
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  16     21     126    127    Ljava/io/IOException;
        //  16     21     187    188    Any
        //  28     39     204    208    Ljava/io/IOException;
        //  28     39     194    204    Any
        //  46     57     204    208    Ljava/io/IOException;
        //  46     57     194    204    Any
        //  63     70     126    127    Ljava/io/IOException;
        //  63     70     187    188    Any
        //  76     85     126    127    Ljava/io/IOException;
        //  76     85     187    188    Any
        //  91     103    126    127    Ljava/io/IOException;
        //  91     103    187    188    Any
        //  113    123    126    127    Ljava/io/IOException;
        //  113    123    187    188    Any
        //  139    144    126    127    Ljava/io/IOException;
        //  139    144    187    188    Any
        //  150    157    126    127    Ljava/io/IOException;
        //  150    157    187    188    Any
        //  170    175    204    208    Ljava/io/IOException;
        //  170    175    194    204    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0028:
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
