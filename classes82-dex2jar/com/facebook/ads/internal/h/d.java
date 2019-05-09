// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.h;

import java.io.File;
import android.util.Log;
import android.graphics.BitmapFactory$Options;
import android.graphics.Rect;
import java.io.InputStream;
import java.io.FileInputStream;
import com.facebook.ads.internal.w.c.c;
import com.facebook.ads.internal.w.h.a;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.w.h.b;
import java.io.IOException;
import android.support.annotation.Nullable;
import java.io.Closeable;
import android.graphics.BitmapFactory;
import com.facebook.ads.internal.v.a.p;
import android.graphics.Bitmap;
import android.content.Context;

public class d
{
    private static final String a;
    private static d b;
    private final Context c;
    
    static {
        a = d.class.getSimpleName();
    }
    
    private d(final Context c) {
        this.c = c;
    }
    
    private Bitmap a(final String s) {
        final byte[] d = com.facebook.ads.internal.w.e.d.a(this.c).a(s, (p)null).d();
        return BitmapFactory.decodeByteArray(d, 0, d.length);
    }
    
    public static d a(Context applicationContext) {
        Label_0034: {
            if (d.b != null) {
                break Label_0034;
            }
            applicationContext = applicationContext.getApplicationContext();
            synchronized (d.class) {
                if (d.b == null) {
                    d.b = new d(applicationContext);
                }
                return d.b;
            }
        }
    }
    
    private static void a(@Nullable final Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        }
        catch (IOException ex) {}
    }
    
    private void a(final String p0, final Bitmap p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          7
        //     3: aconst_null    
        //     4: astore          8
        //     6: aconst_null    
        //     7: astore          5
        //     9: aconst_null    
        //    10: astore          9
        //    12: aconst_null    
        //    13: astore          6
        //    15: aload_2        
        //    16: ifnonnull       25
        //    19: aload_0        
        //    20: aconst_null    
        //    21: invokespecial   com/facebook/ads/internal/h/d.a:(Ljava/lang/Throwable;)V
        //    24: return         
        //    25: new             Ljava/io/File;
        //    28: dup            
        //    29: aload_0        
        //    30: getfield        com/facebook/ads/internal/h/d.c:Landroid/content/Context;
        //    33: invokevirtual   android/content/Context.getCacheDir:()Ljava/io/File;
        //    36: new             Ljava/lang/StringBuilder;
        //    39: dup            
        //    40: invokespecial   java/lang/StringBuilder.<init>:()V
        //    43: aload_1        
        //    44: invokevirtual   java/lang/String.hashCode:()I
        //    47: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    50: ldc             ".png"
        //    52: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    55: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    58: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //    61: astore          10
        //    63: new             Ljava/io/ByteArrayOutputStream;
        //    66: dup            
        //    67: invokespecial   java/io/ByteArrayOutputStream.<init>:()V
        //    70: astore_3       
        //    71: aload           9
        //    73: astore          5
        //    75: aload_3        
        //    76: astore          4
        //    78: aload_2        
        //    79: getstatic       android/graphics/Bitmap$CompressFormat.PNG:Landroid/graphics/Bitmap$CompressFormat;
        //    82: bipush          100
        //    84: aload_3        
        //    85: invokevirtual   android/graphics/Bitmap.compress:(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
        //    88: pop            
        //    89: aload           9
        //    91: astore          5
        //    93: aload_3        
        //    94: astore          4
        //    96: aload_3        
        //    97: invokevirtual   java/io/ByteArrayOutputStream.size:()I
        //   100: ldc             3145728
        //   102: if_icmplt       130
        //   105: aload           9
        //   107: astore          5
        //   109: aload_3        
        //   110: astore          4
        //   112: getstatic       com/facebook/ads/internal/h/d.a:Ljava/lang/String;
        //   115: ldc             "Bitmap size exceeds max size for storage"
        //   117: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   120: pop            
        //   121: aload_3        
        //   122: invokestatic    com/facebook/ads/internal/h/d.a:(Ljava/io/Closeable;)V
        //   125: aconst_null    
        //   126: invokestatic    com/facebook/ads/internal/h/d.a:(Ljava/io/Closeable;)V
        //   129: return         
        //   130: aload           9
        //   132: astore          5
        //   134: aload_3        
        //   135: astore          4
        //   137: new             Ljava/io/FileOutputStream;
        //   140: dup            
        //   141: aload           10
        //   143: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/File;)V
        //   146: astore_2       
        //   147: aload_3        
        //   148: aload_2        
        //   149: invokevirtual   java/io/ByteArrayOutputStream.writeTo:(Ljava/io/OutputStream;)V
        //   152: aload_2        
        //   153: invokevirtual   java/io/FileOutputStream.flush:()V
        //   156: aload_3        
        //   157: invokestatic    com/facebook/ads/internal/h/d.a:(Ljava/io/Closeable;)V
        //   160: aload_2        
        //   161: invokestatic    com/facebook/ads/internal/h/d.a:(Ljava/io/Closeable;)V
        //   164: return         
        //   165: astore_2       
        //   166: aconst_null    
        //   167: astore_1       
        //   168: aload           6
        //   170: astore          5
        //   172: getstatic       com/facebook/ads/internal/h/d.a:Ljava/lang/String;
        //   175: new             Ljava/lang/StringBuilder;
        //   178: dup            
        //   179: invokespecial   java/lang/StringBuilder.<init>:()V
        //   182: ldc             "Bad output destination (file="
        //   184: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   187: aload           10
        //   189: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   192: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   195: ldc             ")."
        //   197: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   200: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   203: aload_2        
        //   204: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   207: pop            
        //   208: aload_0        
        //   209: aload_2        
        //   210: invokespecial   com/facebook/ads/internal/h/d.a:(Ljava/lang/Throwable;)V
        //   213: aload_1        
        //   214: invokestatic    com/facebook/ads/internal/h/d.a:(Ljava/io/Closeable;)V
        //   217: aload           5
        //   219: invokestatic    com/facebook/ads/internal/h/d.a:(Ljava/io/Closeable;)V
        //   222: return         
        //   223: astore_2       
        //   224: aconst_null    
        //   225: astore_3       
        //   226: aload           7
        //   228: astore          6
        //   230: aload           6
        //   232: astore          5
        //   234: aload_3        
        //   235: astore          4
        //   237: aload_0        
        //   238: aload_2        
        //   239: invokespecial   com/facebook/ads/internal/h/d.a:(Ljava/lang/Throwable;)V
        //   242: aload           6
        //   244: astore          5
        //   246: aload_3        
        //   247: astore          4
        //   249: getstatic       com/facebook/ads/internal/h/d.a:Ljava/lang/String;
        //   252: new             Ljava/lang/StringBuilder;
        //   255: dup            
        //   256: invokespecial   java/lang/StringBuilder.<init>:()V
        //   259: ldc             "Unable to write bitmap to file (url="
        //   261: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   264: aload_1        
        //   265: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   268: ldc             ")."
        //   270: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   273: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   276: aload_2        
        //   277: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   280: pop            
        //   281: aload_3        
        //   282: invokestatic    com/facebook/ads/internal/h/d.a:(Ljava/io/Closeable;)V
        //   285: aload           6
        //   287: invokestatic    com/facebook/ads/internal/h/d.a:(Ljava/io/Closeable;)V
        //   290: return         
        //   291: astore_1       
        //   292: aconst_null    
        //   293: astore_3       
        //   294: aload           8
        //   296: astore_2       
        //   297: aload_2        
        //   298: astore          5
        //   300: aload_3        
        //   301: astore          4
        //   303: aload_0        
        //   304: aload_1        
        //   305: invokespecial   com/facebook/ads/internal/h/d.a:(Ljava/lang/Throwable;)V
        //   308: aload_2        
        //   309: astore          5
        //   311: aload_3        
        //   312: astore          4
        //   314: getstatic       com/facebook/ads/internal/h/d.a:Ljava/lang/String;
        //   317: ldc             "Unable to write bitmap to output stream"
        //   319: aload_1        
        //   320: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   323: pop            
        //   324: aload_3        
        //   325: invokestatic    com/facebook/ads/internal/h/d.a:(Ljava/io/Closeable;)V
        //   328: aload_2        
        //   329: invokestatic    com/facebook/ads/internal/h/d.a:(Ljava/io/Closeable;)V
        //   332: return         
        //   333: astore_1       
        //   334: aconst_null    
        //   335: astore_3       
        //   336: aload_3        
        //   337: invokestatic    com/facebook/ads/internal/h/d.a:(Ljava/io/Closeable;)V
        //   340: aload           5
        //   342: invokestatic    com/facebook/ads/internal/h/d.a:(Ljava/io/Closeable;)V
        //   345: aload_1        
        //   346: athrow         
        //   347: astore_1       
        //   348: aload           4
        //   350: astore_3       
        //   351: goto            336
        //   354: astore_1       
        //   355: aload_2        
        //   356: astore          5
        //   358: goto            336
        //   361: astore_2       
        //   362: aload_1        
        //   363: astore_3       
        //   364: aload_2        
        //   365: astore_1       
        //   366: goto            336
        //   369: astore_1       
        //   370: aload           8
        //   372: astore_2       
        //   373: goto            297
        //   376: astore_1       
        //   377: goto            297
        //   380: astore_2       
        //   381: aload           7
        //   383: astore          6
        //   385: goto            230
        //   388: astore          4
        //   390: aload_2        
        //   391: astore          6
        //   393: aload           4
        //   395: astore_2       
        //   396: goto            230
        //   399: astore_2       
        //   400: aload_3        
        //   401: astore_1       
        //   402: aload           6
        //   404: astore          5
        //   406: goto            172
        //   409: astore          4
        //   411: aload_2        
        //   412: astore          5
        //   414: aload_3        
        //   415: astore_1       
        //   416: aload           4
        //   418: astore_2       
        //   419: goto            172
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                           
        //  -----  -----  -----  -----  -------------------------------
        //  63     71     165    172    Ljava/io/FileNotFoundException;
        //  63     71     223    230    Ljava/io/IOException;
        //  63     71     291    297    Ljava/lang/OutOfMemoryError;
        //  63     71     333    336    Any
        //  78     89     399    409    Ljava/io/FileNotFoundException;
        //  78     89     380    388    Ljava/io/IOException;
        //  78     89     369    376    Ljava/lang/OutOfMemoryError;
        //  78     89     347    354    Any
        //  96     105    399    409    Ljava/io/FileNotFoundException;
        //  96     105    380    388    Ljava/io/IOException;
        //  96     105    369    376    Ljava/lang/OutOfMemoryError;
        //  96     105    347    354    Any
        //  112    121    399    409    Ljava/io/FileNotFoundException;
        //  112    121    380    388    Ljava/io/IOException;
        //  112    121    369    376    Ljava/lang/OutOfMemoryError;
        //  112    121    347    354    Any
        //  137    147    399    409    Ljava/io/FileNotFoundException;
        //  137    147    380    388    Ljava/io/IOException;
        //  137    147    369    376    Ljava/lang/OutOfMemoryError;
        //  137    147    347    354    Any
        //  147    156    409    422    Ljava/io/FileNotFoundException;
        //  147    156    388    399    Ljava/io/IOException;
        //  147    156    376    380    Ljava/lang/OutOfMemoryError;
        //  147    156    354    361    Any
        //  172    213    361    369    Any
        //  237    242    347    354    Any
        //  249    281    347    354    Any
        //  303    308    347    354    Any
        //  314    324    347    354    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 228, Size: 228
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
    
    private void a(final Throwable t) {
        com.facebook.ads.internal.w.h.a.b(this.c, "image", com.facebook.ads.internal.w.h.b.S, new com.facebook.ads.internal.protocol.b(AdErrorType.IMAGE_CACHE_ERROR, AdErrorType.IMAGE_CACHE_ERROR.getDefaultErrorMessage(), t));
    }
    
    private boolean a(final int n, final int n2) {
        return n > 0 && n2 > 0 && com.facebook.ads.internal.r.a.l(this.c);
    }
    
    @Nullable
    private Bitmap b(final String s, final int n, final int n2) {
        try {
            Bitmap bitmap;
            if (this.a(n, n2)) {
                bitmap = com.facebook.ads.internal.w.c.c.a(s.substring("file://".length()), n, n2);
            }
            else {
                bitmap = BitmapFactory.decodeStream((InputStream)new FileInputStream(s.substring("file://".length())), (Rect)null, (BitmapFactory$Options)null);
            }
            this.a(s, bitmap);
            return bitmap;
        }
        catch (IOException ex) {
            Log.e(d.a, "Failed to copy local image into cache (url=" + s + ").", (Throwable)ex);
            return null;
        }
    }
    
    @Nullable
    private Bitmap c(final String p0, final int p1, final int p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          6
        //     3: aload_1        
        //     4: ldc             "asset:///"
        //     6: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //     9: ifeq            184
        //    12: aload_0        
        //    13: getfield        com/facebook/ads/internal/h/d.c:Landroid/content/Context;
        //    16: invokevirtual   android/content/Context.getAssets:()Landroid/content/res/AssetManager;
        //    19: aload_1        
        //    20: bipush          9
        //    22: aload_1        
        //    23: invokevirtual   java/lang/String.length:()I
        //    26: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //    29: invokevirtual   android/content/res/AssetManager.open:(Ljava/lang/String;)Ljava/io/InputStream;
        //    32: astore          5
        //    34: aload           5
        //    36: astore          4
        //    38: aload_0        
        //    39: iload_2        
        //    40: iload_3        
        //    41: invokespecial   com/facebook/ads/internal/h/d.a:(II)Z
        //    44: ifeq            92
        //    47: aload           5
        //    49: astore          4
        //    51: aload           5
        //    53: iload_2        
        //    54: iload_3        
        //    55: invokestatic    com/facebook/ads/internal/w/c/c.a:(Ljava/io/InputStream;II)Landroid/graphics/Bitmap;
        //    58: astore          7
        //    60: aload           7
        //    62: astore          6
        //    64: aload           6
        //    66: astore          4
        //    68: aload           5
        //    70: ifnull          82
        //    73: aload           5
        //    75: invokestatic    com/facebook/ads/internal/h/d.a:(Ljava/io/Closeable;)V
        //    78: aload           6
        //    80: astore          4
        //    82: aload_0        
        //    83: aload_1        
        //    84: aload           4
        //    86: invokespecial   com/facebook/ads/internal/h/d.a:(Ljava/lang/String;Landroid/graphics/Bitmap;)V
        //    89: aload           4
        //    91: areturn        
        //    92: aload           5
        //    94: astore          4
        //    96: aload           5
        //    98: invokestatic    android/graphics/BitmapFactory.decodeStream:(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
        //   101: astore          7
        //   103: aload           7
        //   105: astore          6
        //   107: goto            64
        //   110: astore_1       
        //   111: aconst_null    
        //   112: astore          5
        //   114: aload           5
        //   116: astore          4
        //   118: aload_0        
        //   119: aload_1        
        //   120: invokespecial   com/facebook/ads/internal/h/d.a:(Ljava/lang/Throwable;)V
        //   123: aload           6
        //   125: astore          4
        //   127: aload           5
        //   129: ifnull          89
        //   132: aload           5
        //   134: invokestatic    com/facebook/ads/internal/h/d.a:(Ljava/io/Closeable;)V
        //   137: aconst_null    
        //   138: areturn        
        //   139: astore_1       
        //   140: aconst_null    
        //   141: astore          5
        //   143: aload           5
        //   145: astore          4
        //   147: aload_0        
        //   148: aload_1        
        //   149: invokespecial   com/facebook/ads/internal/h/d.a:(Ljava/lang/Throwable;)V
        //   152: aload           6
        //   154: astore          4
        //   156: aload           5
        //   158: ifnull          89
        //   161: aload           5
        //   163: invokestatic    com/facebook/ads/internal/h/d.a:(Ljava/io/Closeable;)V
        //   166: aconst_null    
        //   167: areturn        
        //   168: astore_1       
        //   169: aconst_null    
        //   170: astore          4
        //   172: aload           4
        //   174: ifnull          182
        //   177: aload           4
        //   179: invokestatic    com/facebook/ads/internal/h/d.a:(Ljava/io/Closeable;)V
        //   182: aload_1        
        //   183: athrow         
        //   184: aload_0        
        //   185: iload_2        
        //   186: iload_3        
        //   187: invokespecial   com/facebook/ads/internal/h/d.a:(II)Z
        //   190: ifeq            262
        //   193: new             Ljava/net/URL;
        //   196: dup            
        //   197: aload_1        
        //   198: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //   201: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
        //   204: checkcast       Ljava/net/HttpURLConnection;
        //   207: astore          4
        //   209: aload           4
        //   211: iconst_1       
        //   212: invokevirtual   java/net/HttpURLConnection.setDoInput:(Z)V
        //   215: aload           4
        //   217: invokevirtual   java/net/HttpURLConnection.connect:()V
        //   220: aload           4
        //   222: invokevirtual   java/net/HttpURLConnection.getInputStream:()Ljava/io/InputStream;
        //   225: astore          5
        //   227: aload           5
        //   229: iload_2        
        //   230: iload_3        
        //   231: invokestatic    com/facebook/ads/internal/w/c/c.a:(Ljava/io/InputStream;II)Landroid/graphics/Bitmap;
        //   234: astore          4
        //   236: aload           5
        //   238: invokestatic    com/facebook/ads/internal/h/d.a:(Ljava/io/Closeable;)V
        //   241: goto            82
        //   244: astore          4
        //   246: aload_0        
        //   247: aload           4
        //   249: invokespecial   com/facebook/ads/internal/h/d.a:(Ljava/lang/Throwable;)V
        //   252: aload_0        
        //   253: aload_1        
        //   254: invokespecial   com/facebook/ads/internal/h/d.a:(Ljava/lang/String;)Landroid/graphics/Bitmap;
        //   257: astore          4
        //   259: goto            82
        //   262: aload_0        
        //   263: aload_1        
        //   264: invokespecial   com/facebook/ads/internal/h/d.a:(Ljava/lang/String;)Landroid/graphics/Bitmap;
        //   267: astore          4
        //   269: goto            82
        //   272: astore_1       
        //   273: goto            172
        //   276: astore_1       
        //   277: goto            143
        //   280: astore_1       
        //   281: goto            114
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  12     34     110    114    Ljava/io/IOException;
        //  12     34     139    143    Ljava/lang/OutOfMemoryError;
        //  12     34     168    172    Any
        //  38     47     280    284    Ljava/io/IOException;
        //  38     47     276    280    Ljava/lang/OutOfMemoryError;
        //  38     47     272    276    Any
        //  51     60     280    284    Ljava/io/IOException;
        //  51     60     276    280    Ljava/lang/OutOfMemoryError;
        //  51     60     272    276    Any
        //  96     103    280    284    Ljava/io/IOException;
        //  96     103    276    280    Ljava/lang/OutOfMemoryError;
        //  96     103    272    276    Any
        //  118    123    272    276    Any
        //  147    152    272    276    Any
        //  193    241    244    262    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0064:
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
    
    @Nullable
    public Bitmap a(final String s, final int n, final int n2) {
        final File file = new File(this.c.getCacheDir(), s.hashCode() + ".png");
        if (!file.exists()) {
            if (s.startsWith("file://")) {
                return this.b(s, n, n2);
            }
            return this.c(s, n, n2);
        }
        else {
            if (this.a(n, n2)) {
                return com.facebook.ads.internal.w.c.c.a(file.getAbsolutePath(), n, n2);
            }
            return BitmapFactory.decodeFile(file.getAbsolutePath());
        }
    }
}
