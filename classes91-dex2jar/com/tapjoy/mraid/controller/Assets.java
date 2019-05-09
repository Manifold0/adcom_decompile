// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.mraid.controller;

import android.webkit.JavascriptInterface;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import com.tapjoy.internal.da;
import com.tapjoy.internal.em;
import java.net.URL;
import com.tapjoy.TapjoyLog;
import android.os.Environment;
import java.io.FileOutputStream;
import android.os.StatFs;
import java.io.File;
import android.content.Context;
import com.tapjoy.mraid.view.MraidView;

public class Assets extends Abstract
{
    private static final char[] d;
    private int c;
    
    static {
        d = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    }
    
    public Assets(final MraidView mraidView, final Context context) {
        super(mraidView, context);
        this.c = 0;
    }
    
    private File a(final String s) {
        return new File(this.b.getFilesDir().getPath() + File.separator + s);
    }
    
    private String a() {
        return this.b.getFilesDir().getPath();
    }
    
    private static String b(final String s) {
        final int lastIndex = s.lastIndexOf(File.separatorChar);
        String substring = "/";
        if (lastIndex >= 0) {
            substring = s.substring(0, s.lastIndexOf(File.separatorChar));
        }
        return substring;
    }
    
    private static String c(final String s) {
        String substring = s;
        if (s.lastIndexOf(File.separatorChar) >= 0) {
            substring = s.substring(s.lastIndexOf(File.separatorChar) + 1);
        }
        return substring;
    }
    
    public static boolean deleteDirectory(final File file) {
        if (file.exists()) {
            final File[] listFiles = file.listFiles();
            for (int i = 0; i < listFiles.length; ++i) {
                if (listFiles[i].isDirectory()) {
                    deleteDirectory(listFiles[i]);
                }
                else {
                    listFiles[i].delete();
                }
            }
        }
        return file.delete();
    }
    
    public static boolean deleteDirectory(final String s) {
        return s != null && deleteDirectory(new File(s));
    }
    
    public void addAsset(final String p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          4
        //     3: aconst_null    
        //     4: astore_3       
        //     5: aload_2        
        //     6: invokestatic    com/tapjoy/internal/em.a:(Ljava/lang/String;)Ljava/io/InputStream;
        //     9: astore_2       
        //    10: aload_2        
        //    11: astore_3       
        //    12: aload_2        
        //    13: astore          4
        //    15: aload_0        
        //    16: aload_2        
        //    17: aload_1        
        //    18: iconst_0       
        //    19: invokevirtual   com/tapjoy/mraid/controller/Assets.writeToDisk:(Ljava/io/InputStream;Ljava/lang/String;Z)Ljava/lang/String;
        //    22: pop            
        //    23: aload_2        
        //    24: astore_3       
        //    25: aload_2        
        //    26: astore          4
        //    28: new             Ljava/lang/StringBuilder;
        //    31: dup            
        //    32: ldc             "MraidAdController.addedAsset('"
        //    34: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //    37: aload_1        
        //    38: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    41: ldc             "' )"
        //    43: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    46: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    49: astore_1       
        //    50: aload_2        
        //    51: astore_3       
        //    52: aload_2        
        //    53: astore          4
        //    55: aload_0        
        //    56: getfield        com/tapjoy/mraid/controller/Assets.a:Lcom/tapjoy/mraid/view/MraidView;
        //    59: aload_1        
        //    60: invokevirtual   com/tapjoy/mraid/view/MraidView.injectMraidJavaScript:(Ljava/lang/String;)V
        //    63: aload_2        
        //    64: ifnull          71
        //    67: aload_2        
        //    68: invokevirtual   java/io/InputStream.close:()V
        //    71: return         
        //    72: astore_1       
        //    73: aload_3        
        //    74: astore          4
        //    76: aload_1        
        //    77: invokevirtual   java/lang/Exception.printStackTrace:()V
        //    80: aload_3        
        //    81: ifnull          71
        //    84: aload_3        
        //    85: invokevirtual   java/io/InputStream.close:()V
        //    88: return         
        //    89: astore_1       
        //    90: return         
        //    91: astore_1       
        //    92: aload           4
        //    94: ifnull          102
        //    97: aload           4
        //    99: invokevirtual   java/io/InputStream.close:()V
        //   102: aload_1        
        //   103: athrow         
        //   104: astore_1       
        //   105: return         
        //   106: astore_2       
        //   107: goto            102
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  5      10     72     91     Ljava/lang/Exception;
        //  5      10     91     104    Any
        //  15     23     72     91     Ljava/lang/Exception;
        //  15     23     91     104    Any
        //  28     50     72     91     Ljava/lang/Exception;
        //  28     50     91     104    Any
        //  55     63     72     91     Ljava/lang/Exception;
        //  55     63     91     104    Any
        //  67     71     104    106    Ljava/lang/Exception;
        //  76     80     91     104    Any
        //  84     88     89     91     Ljava/lang/Exception;
        //  97     102    106    110    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 67, Size: 67
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
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
    
    public int cacheRemaining() {
        final StatFs statFs = new StatFs(this.b.getFilesDir().getPath());
        return statFs.getFreeBlocks() * statFs.getBlockSize();
    }
    
    public String copyTextFromJarIntoAssetDir(final String p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          6
        //     3: ldc             Lcom/tapjoy/mraid/controller/Assets;.class
        //     5: invokevirtual   java/lang/Class.getClassLoader:()Ljava/lang/ClassLoader;
        //     8: aload_2        
        //     9: invokevirtual   java/lang/ClassLoader.getResource:(Ljava/lang/String;)Ljava/net/URL;
        //    12: astore          4
        //    14: aload           4
        //    16: ifnonnull       59
        //    19: aload_0        
        //    20: getfield        com/tapjoy/mraid/controller/Assets.b:Landroid/content/Context;
        //    23: invokevirtual   android/content/Context.getAssets:()Landroid/content/res/AssetManager;
        //    26: aload_2        
        //    27: invokevirtual   android/content/res/AssetManager.open:(Ljava/lang/String;)Ljava/io/InputStream;
        //    30: astore_2       
        //    31: aload_2        
        //    32: astore          4
        //    34: aload_0        
        //    35: aload_2        
        //    36: aload_1        
        //    37: iconst_0       
        //    38: invokevirtual   com/tapjoy/mraid/controller/Assets.writeToDisk:(Ljava/io/InputStream;Ljava/lang/String;Z)Ljava/lang/String;
        //    41: astore_1       
        //    42: aload_1        
        //    43: astore          4
        //    45: aload_2        
        //    46: ifnull          56
        //    49: aload_2        
        //    50: invokevirtual   java/io/InputStream.close:()V
        //    53: aload_1        
        //    54: astore          4
        //    56: aload           4
        //    58: areturn        
        //    59: aload           4
        //    61: invokevirtual   java/net/URL.getFile:()Ljava/lang/String;
        //    64: astore          5
        //    66: aload           5
        //    68: astore          4
        //    70: aload           5
        //    72: ldc             "jar:"
        //    74: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //    77: ifeq            88
        //    80: aload           5
        //    82: iconst_4       
        //    83: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //    86: astore          4
        //    88: aload           4
        //    90: astore          5
        //    92: aload           4
        //    94: ldc             "file:"
        //    96: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //    99: ifeq            110
        //   102: aload           4
        //   104: iconst_5       
        //   105: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   108: astore          5
        //   110: aload           5
        //   112: ldc             "!"
        //   114: invokevirtual   java/lang/String.indexOf:(Ljava/lang/String;)I
        //   117: istore_3       
        //   118: aload           5
        //   120: astore          4
        //   122: iload_3        
        //   123: ifle            135
        //   126: aload           5
        //   128: iconst_0       
        //   129: iload_3        
        //   130: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   133: astore          4
        //   135: new             Ljava/util/jar/JarFile;
        //   138: dup            
        //   139: aload           4
        //   141: invokespecial   java/util/jar/JarFile.<init>:(Ljava/lang/String;)V
        //   144: astore          4
        //   146: aload           4
        //   148: aload           4
        //   150: aload_2        
        //   151: invokevirtual   java/util/jar/JarFile.getJarEntry:(Ljava/lang/String;)Ljava/util/jar/JarEntry;
        //   154: invokevirtual   java/util/jar/JarFile.getInputStream:(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;
        //   157: astore_2       
        //   158: goto            31
        //   161: astore_1       
        //   162: aconst_null    
        //   163: astore_2       
        //   164: aload_2        
        //   165: astore          4
        //   167: ldc             "MRAID Assets"
        //   169: new             Ljava/lang/StringBuilder;
        //   172: dup            
        //   173: ldc             "copyTextFromJarIntoAssetDir: "
        //   175: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   178: aload_1        
        //   179: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
        //   182: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   185: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   188: invokestatic    com/tapjoy/TapjoyLog.e:(Ljava/lang/String;Ljava/lang/String;)V
        //   191: aload           6
        //   193: astore          4
        //   195: aload_2        
        //   196: ifnull          56
        //   199: aload_2        
        //   200: invokevirtual   java/io/InputStream.close:()V
        //   203: aconst_null    
        //   204: areturn        
        //   205: astore_1       
        //   206: aconst_null    
        //   207: areturn        
        //   208: astore_1       
        //   209: aconst_null    
        //   210: astore          4
        //   212: aload           4
        //   214: ifnull          222
        //   217: aload           4
        //   219: invokevirtual   java/io/InputStream.close:()V
        //   222: aload_1        
        //   223: athrow         
        //   224: astore_2       
        //   225: aload_1        
        //   226: areturn        
        //   227: astore_2       
        //   228: goto            222
        //   231: astore_1       
        //   232: goto            212
        //   235: astore_1       
        //   236: goto            164
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  3      14     161    164    Ljava/lang/Exception;
        //  3      14     208    212    Any
        //  19     31     161    164    Ljava/lang/Exception;
        //  19     31     208    212    Any
        //  34     42     235    239    Ljava/lang/Exception;
        //  34     42     231    235    Any
        //  49     53     224    227    Ljava/lang/Exception;
        //  59     66     161    164    Ljava/lang/Exception;
        //  59     66     208    212    Any
        //  70     88     161    164    Ljava/lang/Exception;
        //  70     88     208    212    Any
        //  92     110    161    164    Ljava/lang/Exception;
        //  92     110    208    212    Any
        //  110    118    161    164    Ljava/lang/Exception;
        //  110    118    208    212    Any
        //  126    135    161    164    Ljava/lang/Exception;
        //  126    135    208    212    Any
        //  135    158    161    164    Ljava/lang/Exception;
        //  135    158    208    212    Any
        //  167    191    231    235    Any
        //  199    203    205    208    Ljava/lang/Exception;
        //  217    222    227    231    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0056:
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
    
    public void deleteOldAds() {
        deleteDirectory(new File(this.a() + File.separator + "ad"));
    }
    
    public FileOutputStream getAssetOutputString(final String s) {
        final File a = this.a(b(s));
        a.mkdirs();
        return new FileOutputStream(new File(a, c(s)));
    }
    
    public String getAssetPath() {
        return "file://" + this.b.getFilesDir() + "/";
    }
    
    public void removeAsset(String string) {
        final File a = this.a(b(string));
        a.mkdirs();
        new File(a, c(string)).delete();
        string = "MraidAdController.assetRemoved('" + string + "' )";
        this.a.injectMraidJavaScript(string);
    }
    
    @Override
    public void stopAllListeners() {
    }
    
    public void storePicture(final String s) {
        TapjoyLog.d("MRAID Assets", "Storing media from " + s + " to device photo album.  Output directory: " + Environment.getExternalStorageDirectory() + " state: " + Environment.getExternalStorageState());
        ++this.c;
        try {
            final URL url = new URL(s);
            final String string = "MraidMedia" + this.c + ".jpg";
            final File file = new File(Environment.getExternalStorageDirectory().toString() + "/" + string);
            final long currentTimeMillis = System.currentTimeMillis();
            TapjoyLog.d("MRAID Assets", "download beginning");
            TapjoyLog.d("MRAID Assets", "download url:" + url);
            TapjoyLog.d("MRAID Assets", "downloaded file name:" + string);
            final InputStream inputStream = em.a(url).getInputStream();
            final FileOutputStream fileOutputStream = new FileOutputStream(file);
            da.a(inputStream, fileOutputStream);
            fileOutputStream.close();
            TapjoyLog.d("MRAID Assets", "download ready in" + (System.currentTimeMillis() - currentTimeMillis) / 1000L + " sec");
        }
        catch (IOException ex) {
            TapjoyLog.d("MRAID Assets", "Error: " + ex);
        }
    }
    
    @JavascriptInterface
    public void storePictureInit(final String s) {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(this.b);
        alertDialog$Builder.setMessage((CharSequence)("Are you sure you want to save file from " + s + " to your SD card?"));
        alertDialog$Builder.setCancelable(false);
        alertDialog$Builder.setPositiveButton((CharSequence)"Yes", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public final void onClick(final DialogInterface dialogInterface, final int n) {
                Assets.this.storePicture(s);
            }
        });
        alertDialog$Builder.setNegativeButton((CharSequence)"No", (DialogInterface$OnClickListener)null);
        alertDialog$Builder.show();
    }
    
    public String writeToDisk(final InputStream p0, final String p1, final boolean p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          8
        //     3: iconst_0       
        //     4: istore          4
        //     6: sipush          1024
        //     9: newarray        B
        //    11: astore          10
        //    13: iload_3        
        //    14: ifeq            106
        //    17: ldc_w           "MD5"
        //    20: invokestatic    java/security/MessageDigest.getInstance:(Ljava/lang/String;)Ljava/security/MessageDigest;
        //    23: astore          7
        //    25: aload_0        
        //    26: aload_2        
        //    27: invokevirtual   com/tapjoy/mraid/controller/Assets.getAssetOutputString:(Ljava/lang/String;)Ljava/io/FileOutputStream;
        //    30: astore          9
        //    32: aload           9
        //    34: astore          8
        //    36: aload_1        
        //    37: aload           10
        //    39: invokevirtual   java/io/InputStream.read:([B)I
        //    42: istore          5
        //    44: iload           5
        //    46: ifle            112
        //    49: iload_3        
        //    50: ifeq            69
        //    53: aload           7
        //    55: ifnull          69
        //    58: aload           9
        //    60: astore          8
        //    62: aload           7
        //    64: aload           10
        //    66: invokevirtual   java/security/MessageDigest.update:([B)V
        //    69: aload           9
        //    71: astore          8
        //    73: aload           9
        //    75: aload           10
        //    77: iconst_0       
        //    78: iload           5
        //    80: invokevirtual   java/io/FileOutputStream.write:([BII)V
        //    83: goto            32
        //    86: astore_1       
        //    87: aload           8
        //    89: ifnull          97
        //    92: aload           8
        //    94: invokevirtual   java/io/FileOutputStream.close:()V
        //    97: aload_1        
        //    98: athrow         
        //    99: astore          7
        //   101: aload           7
        //   103: invokevirtual   java/security/NoSuchAlgorithmException.printStackTrace:()V
        //   106: aconst_null    
        //   107: astore          7
        //   109: goto            25
        //   112: aload           9
        //   114: astore          8
        //   116: aload           9
        //   118: invokevirtual   java/io/FileOutputStream.flush:()V
        //   121: aload           9
        //   123: ifnull          131
        //   126: aload           9
        //   128: invokevirtual   java/io/FileOutputStream.close:()V
        //   131: aload_0        
        //   132: invokespecial   com/tapjoy/mraid/controller/Assets.a:()Ljava/lang/String;
        //   135: astore_1       
        //   136: iload_3        
        //   137: ifeq            428
        //   140: aload           7
        //   142: ifnull          428
        //   145: aload           7
        //   147: invokevirtual   java/security/MessageDigest.digest:()[B
        //   150: astore          7
        //   152: aload           7
        //   154: arraylength    
        //   155: iconst_2       
        //   156: imul           
        //   157: newarray        C
        //   159: astore          8
        //   161: iconst_0       
        //   162: istore          5
        //   164: iload           4
        //   166: aload           7
        //   168: arraylength    
        //   169: if_icmpge       229
        //   172: iload           5
        //   174: iconst_1       
        //   175: iadd           
        //   176: istore          6
        //   178: aload           8
        //   180: iload           5
        //   182: getstatic       com/tapjoy/mraid/controller/Assets.d:[C
        //   185: aload           7
        //   187: iload           4
        //   189: baload         
        //   190: iconst_4       
        //   191: iushr          
        //   192: bipush          15
        //   194: iand           
        //   195: caload         
        //   196: castore        
        //   197: iload           6
        //   199: iconst_1       
        //   200: iadd           
        //   201: istore          5
        //   203: aload           8
        //   205: iload           6
        //   207: getstatic       com/tapjoy/mraid/controller/Assets.d:[C
        //   210: aload           7
        //   212: iload           4
        //   214: baload         
        //   215: bipush          15
        //   217: iand           
        //   218: caload         
        //   219: castore        
        //   220: iload           4
        //   222: iconst_1       
        //   223: iadd           
        //   224: istore          4
        //   226: goto            164
        //   229: new             Ljava/lang/String;
        //   232: dup            
        //   233: aload           8
        //   235: invokespecial   java/lang/String.<init>:([C)V
        //   238: astore          8
        //   240: new             Ljava/io/File;
        //   243: dup            
        //   244: new             Ljava/lang/StringBuilder;
        //   247: dup            
        //   248: invokespecial   java/lang/StringBuilder.<init>:()V
        //   251: aload_1        
        //   252: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   255: getstatic       java/io/File.separator:Ljava/lang/String;
        //   258: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   261: aload_2        
        //   262: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   265: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   268: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //   271: astore          7
        //   273: new             Ljava/io/File;
        //   276: dup            
        //   277: new             Ljava/lang/StringBuilder;
        //   280: dup            
        //   281: invokespecial   java/lang/StringBuilder.<init>:()V
        //   284: aload_1        
        //   285: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   288: getstatic       java/io/File.separator:Ljava/lang/String;
        //   291: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   294: ldc             "ad"
        //   296: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   299: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   302: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //   305: invokevirtual   java/io/File.mkdir:()Z
        //   308: pop            
        //   309: new             Ljava/io/File;
        //   312: dup            
        //   313: new             Ljava/lang/StringBuilder;
        //   316: dup            
        //   317: invokespecial   java/lang/StringBuilder.<init>:()V
        //   320: aload_1        
        //   321: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   324: getstatic       java/io/File.separator:Ljava/lang/String;
        //   327: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   330: ldc             "ad"
        //   332: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   335: getstatic       java/io/File.separator:Ljava/lang/String;
        //   338: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   341: aload           8
        //   343: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   346: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   349: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //   352: astore_1       
        //   353: aload_1        
        //   354: invokevirtual   java/io/File.mkdir:()Z
        //   357: pop            
        //   358: aload           7
        //   360: new             Ljava/io/File;
        //   363: dup            
        //   364: aload_1        
        //   365: aload           7
        //   367: invokevirtual   java/io/File.getName:()Ljava/lang/String;
        //   370: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   373: invokevirtual   java/io/File.renameTo:(Ljava/io/File;)Z
        //   376: pop            
        //   377: new             Ljava/lang/StringBuilder;
        //   380: dup            
        //   381: invokespecial   java/lang/StringBuilder.<init>:()V
        //   384: aload_1        
        //   385: invokevirtual   java/io/File.getPath:()Ljava/lang/String;
        //   388: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   391: getstatic       java/io/File.separator:Ljava/lang/String;
        //   394: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   397: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   400: astore_1       
        //   401: new             Ljava/lang/StringBuilder;
        //   404: dup            
        //   405: invokespecial   java/lang/StringBuilder.<init>:()V
        //   408: aload_1        
        //   409: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   412: aload_2        
        //   413: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   416: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   419: areturn        
        //   420: astore_1       
        //   421: goto            131
        //   424: astore_2       
        //   425: goto            97
        //   428: goto            401
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                    
        //  -----  -----  -----  -----  ----------------------------------------
        //  17     25     99     106    Ljava/security/NoSuchAlgorithmException;
        //  25     32     86     99     Any
        //  36     44     86     99     Any
        //  62     69     86     99     Any
        //  73     83     86     99     Any
        //  92     97     424    428    Ljava/lang/Exception;
        //  116    121    86     99     Any
        //  126    131    420    424    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0097:
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
