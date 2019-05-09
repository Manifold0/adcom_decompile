// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy;

import java.io.OutputStream;
import java.io.BufferedInputStream;
import android.view.ViewGroup$LayoutParams;
import android.webkit.WebView;
import android.os.Handler;
import android.os.Looper;
import org.json.JSONObject;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.io.File;
import android.graphics.Canvas;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.view.View;
import android.content.Context;
import java.util.Iterator;
import android.net.Uri;
import java.util.Map;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import java.security.MessageDigest;
import java.util.HashMap;

public class TapjoyUtil
{
    private static String a;
    private static HashMap b;
    
    static {
        TapjoyUtil.a = null;
        TapjoyUtil.b = new HashMap();
    }
    
    public static String SHA1(final String s) {
        return a("SHA-1", s);
    }
    
    public static String SHA256(final String s) {
        return a("SHA-256", s);
    }
    
    private static String a(final String s, final String s2) {
        final MessageDigest instance = MessageDigest.getInstance(s);
        instance.update(s2.getBytes("iso-8859-1"), 0, s2.length());
        final byte[] digest = instance.digest();
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < digest.length; ++i) {
            int n = digest[i] >>> 4 & 0xF;
            int n2 = 0;
            while (true) {
                if (n >= 0 && n <= 9) {
                    sb.append((char)(n + 48));
                }
                else {
                    sb.append((char)(n - 10 + 97));
                }
                final byte b = digest[i];
                if (n2 > 0) {
                    break;
                }
                ++n2;
                n = (b & 0xF);
            }
        }
        return sb.toString();
    }
    
    public static Document buildDocument(final String s) {
        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(s.getBytes("UTF-8")));
        }
        catch (Exception ex) {
            TapjoyLog.e("TapjoyUtil", "buildDocument exception: " + ex.toString());
            return null;
        }
    }
    
    public static String convertURLParams(final Map map, final boolean b) {
        final Iterator<Map.Entry<String, V>> iterator = map.entrySet().iterator();
        String s = "";
        while (iterator.hasNext()) {
            final Map.Entry<String, V> entry = iterator.next();
            String string = s;
            if (s.length() > 0) {
                string = s + "&";
            }
            if (b) {
                s = string + Uri.encode((String)entry.getKey()) + "=" + Uri.encode((String)entry.getValue());
            }
            else {
                s = string + entry.getKey() + "=" + (String)entry.getValue();
            }
        }
        return s;
    }
    
    public static Map convertURLParams(String decode, final boolean b) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        String s = "";
        String s2 = "";
        int n = 0;
        String s3;
        int n3;
        String s4;
        for (int n2 = 0; n2 < decode.length() && n2 != -1; ++n2, s2 = s4, s = s3, n = n3) {
            final char char1 = decode.charAt(n2);
            if (n == 0) {
                if (char1 == '=') {
                    if (b) {
                        s = Uri.decode(s);
                    }
                    s3 = "";
                    n3 = 1;
                    s4 = s;
                }
                else {
                    s3 = s + char1;
                    s4 = s2;
                    n3 = n;
                }
            }
            else {
                s4 = s2;
                s3 = s;
                if ((n3 = n) == 1) {
                    if (char1 == '&') {
                        if (b) {
                            s = Uri.decode(s);
                        }
                        s3 = "";
                        hashMap.put(s2, s);
                        n3 = 0;
                        s4 = s2;
                    }
                    else {
                        s3 = s + char1;
                        s4 = s2;
                        n3 = n;
                    }
                }
            }
        }
        if (n == 1 && s.length() > 0) {
            decode = s;
            if (b) {
                decode = Uri.decode(s);
            }
            hashMap.put(s2, decode);
        }
        return hashMap;
    }
    
    public static String copyTextFromJarIntoString(final String s) {
        return copyTextFromJarIntoString(s, null);
    }
    
    public static String copyTextFromJarIntoString(final String p0, final Context p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          4
        //     3: sipush          1024
        //     6: newarray        B
        //     8: astore          5
        //    10: new             Ljava/lang/StringBuffer;
        //    13: dup            
        //    14: invokespecial   java/lang/StringBuffer.<init>:()V
        //    17: astore          6
        //    19: ldc             Lcom/tapjoy/TapjoyUtil;.class
        //    21: invokevirtual   java/lang/Class.getClassLoader:()Ljava/lang/ClassLoader;
        //    24: aload_0        
        //    25: invokevirtual   java/lang/ClassLoader.getResource:(Ljava/lang/String;)Ljava/net/URL;
        //    28: astore_3       
        //    29: aload_1        
        //    30: ifnull          133
        //    33: aload_3        
        //    34: ifnonnull       133
        //    37: aload_1        
        //    38: invokevirtual   android/content/Context.getAssets:()Landroid/content/res/AssetManager;
        //    41: aload_0        
        //    42: invokevirtual   android/content/res/AssetManager.open:(Ljava/lang/String;)Ljava/io/InputStream;
        //    45: astore_0       
        //    46: aload_0        
        //    47: astore_1       
        //    48: aload_0        
        //    49: aload           5
        //    51: invokevirtual   java/io/InputStream.read:([B)I
        //    54: istore_2       
        //    55: iload_2        
        //    56: ifle            214
        //    59: aload_0        
        //    60: astore_1       
        //    61: aload           6
        //    63: new             Ljava/lang/String;
        //    66: dup            
        //    67: aload           5
        //    69: invokespecial   java/lang/String.<init>:([B)V
        //    72: iconst_0       
        //    73: iload_2        
        //    74: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //    77: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    80: pop            
        //    81: goto            46
        //    84: astore_3       
        //    85: aload_0        
        //    86: astore_1       
        //    87: ldc             "TapjoyUtil"
        //    89: new             Ljava/lang/StringBuilder;
        //    92: dup            
        //    93: ldc             "file exception: "
        //    95: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //    98: aload_3        
        //    99: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
        //   102: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   105: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   108: invokestatic    com/tapjoy/TapjoyLog.d:(Ljava/lang/String;Ljava/lang/String;)V
        //   111: aload_0        
        //   112: astore_1       
        //   113: aload_3        
        //   114: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   117: aload           4
        //   119: astore_1       
        //   120: aload_0        
        //   121: ifnull          131
        //   124: aload_0        
        //   125: invokevirtual   java/io/InputStream.close:()V
        //   128: aload           4
        //   130: astore_1       
        //   131: aload_1        
        //   132: areturn        
        //   133: aload_3        
        //   134: invokevirtual   java/net/URL.getFile:()Ljava/lang/String;
        //   137: astore_3       
        //   138: aload_3        
        //   139: astore_1       
        //   140: aload_3        
        //   141: ldc             "jar:"
        //   143: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   146: ifeq            155
        //   149: aload_3        
        //   150: iconst_4       
        //   151: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   154: astore_1       
        //   155: aload_1        
        //   156: astore_3       
        //   157: aload_1        
        //   158: ldc             "file:"
        //   160: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   163: ifeq            172
        //   166: aload_1        
        //   167: iconst_5       
        //   168: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   171: astore_3       
        //   172: aload_3        
        //   173: ldc             "!"
        //   175: invokevirtual   java/lang/String.indexOf:(Ljava/lang/String;)I
        //   178: istore_2       
        //   179: aload_3        
        //   180: astore_1       
        //   181: iload_2        
        //   182: ifle            192
        //   185: aload_3        
        //   186: iconst_0       
        //   187: iload_2        
        //   188: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   191: astore_1       
        //   192: new             Ljava/util/jar/JarFile;
        //   195: dup            
        //   196: aload_1        
        //   197: invokespecial   java/util/jar/JarFile.<init>:(Ljava/lang/String;)V
        //   200: astore_1       
        //   201: aload_1        
        //   202: aload_1        
        //   203: aload_0        
        //   204: invokevirtual   java/util/jar/JarFile.getJarEntry:(Ljava/lang/String;)Ljava/util/jar/JarEntry;
        //   207: invokevirtual   java/util/jar/JarFile.getInputStream:(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;
        //   210: astore_0       
        //   211: goto            46
        //   214: aload_0        
        //   215: astore_1       
        //   216: aload           6
        //   218: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   221: astore_3       
        //   222: aload_3        
        //   223: astore_1       
        //   224: aload_0        
        //   225: ifnull          131
        //   228: aload_0        
        //   229: invokevirtual   java/io/InputStream.close:()V
        //   232: aload_3        
        //   233: areturn        
        //   234: astore_0       
        //   235: aload_3        
        //   236: areturn        
        //   237: astore_0       
        //   238: aconst_null    
        //   239: astore_1       
        //   240: aload_1        
        //   241: ifnull          248
        //   244: aload_1        
        //   245: invokevirtual   java/io/InputStream.close:()V
        //   248: aload_0        
        //   249: athrow         
        //   250: astore_0       
        //   251: aconst_null    
        //   252: areturn        
        //   253: astore_1       
        //   254: goto            248
        //   257: astore_0       
        //   258: goto            240
        //   261: astore_3       
        //   262: aconst_null    
        //   263: astore_0       
        //   264: goto            85
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  37     46     261    267    Ljava/lang/Exception;
        //  37     46     237    240    Any
        //  48     55     84     85     Ljava/lang/Exception;
        //  48     55     257    261    Any
        //  61     81     84     85     Ljava/lang/Exception;
        //  61     81     257    261    Any
        //  87     111    257    261    Any
        //  113    117    257    261    Any
        //  124    128    250    253    Ljava/lang/Exception;
        //  133    138    261    267    Ljava/lang/Exception;
        //  133    138    237    240    Any
        //  140    155    261    267    Ljava/lang/Exception;
        //  140    155    237    240    Any
        //  157    172    261    267    Ljava/lang/Exception;
        //  157    172    237    240    Any
        //  172    179    261    267    Ljava/lang/Exception;
        //  172    179    237    240    Any
        //  185    192    261    267    Ljava/lang/Exception;
        //  185    192    237    240    Any
        //  192    211    261    267    Ljava/lang/Exception;
        //  192    211    237    240    Any
        //  216    222    84     85     Ljava/lang/Exception;
        //  216    222    257    261    Any
        //  228    232    234    237    Ljava/lang/Exception;
        //  244    248    253    257    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 155, Size: 155
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
    
    public static Bitmap createBitmapFromView(final View view) {
        final Bitmap bitmap = null;
        Bitmap bitmap3;
        Bitmap bitmap2 = bitmap3 = null;
        if (view == null) {
            return bitmap3;
        }
        bitmap3 = bitmap2;
        if (view.getLayoutParams().width <= 0) {
            return bitmap3;
        }
        bitmap3 = bitmap2;
        if (view.getLayoutParams().height <= 0) {
            return bitmap3;
        }
        bitmap2 = bitmap;
        try {
            bitmap3 = (bitmap2 = Bitmap.createBitmap(view.getLayoutParams().width, view.getLayoutParams().height, Bitmap$Config.ARGB_8888));
            final Canvas canvas = new Canvas(bitmap3);
            bitmap2 = bitmap3;
            view.layout(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            bitmap2 = bitmap3;
            view.draw(canvas);
            return bitmap3;
        }
        catch (Exception ex) {
            TapjoyLog.d("TapjoyUtil", "error creating bitmap: " + ex.toString());
            return bitmap2;
        }
    }
    
    public static void deleteFileOrDirectory(final File file) {
        if (file == null) {
            return;
        }
        if (file.isDirectory()) {
            final File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                for (int length = listFiles.length, i = 0; i < length; ++i) {
                    deleteFileOrDirectory(listFiles[i]);
                }
            }
        }
        TapjoyLog.d("TapjoyUtil", "****************************************");
        TapjoyLog.d("TapjoyUtil", "deleteFileOrDirectory: " + file.getAbsolutePath());
        TapjoyLog.d("TapjoyUtil", "****************************************");
        file.delete();
    }
    
    public static String determineMimeType(String substring) {
        final String s = "";
        String substring2 = substring;
        if (substring.endsWith(".")) {
            substring2 = substring.substring(0, substring.length() - 1);
        }
        substring = s;
        if (substring2.lastIndexOf(46) != -1) {
            substring = substring2.substring(substring2.lastIndexOf(46) + 1);
        }
        if (substring.equals("css")) {
            return "text/css";
        }
        if (substring.equals("js")) {
            return "text/javascript";
        }
        if (substring.equals("html")) {
            return "text/html";
        }
        return "application/octet-stream";
    }
    
    public static long fileOrDirectorySize(final File file) {
        final File[] listFiles = file.listFiles();
        final int length = listFiles.length;
        long n = 0L;
        for (int i = 0; i < length; ++i) {
            final File file2 = listFiles[i];
            if (file2.isFile()) {
                n += file2.length();
            }
            else {
                n += fileOrDirectorySize(file2);
            }
        }
        return n;
    }
    
    public static String getNodeTrimValue(final NodeList list) {
        int i = 0;
        final Element element = (Element)list.item(0);
        if (element == null) {
            return null;
        }
        final NodeList childNodes = element.getChildNodes();
        final int length = childNodes.getLength();
        String s = "";
        while (i < length) {
            final Node item = childNodes.item(i);
            String string = s;
            if (item != null) {
                string = s + item.getNodeValue();
            }
            ++i;
            s = string;
        }
        if (s != null && !s.equals("")) {
            return s.trim();
        }
        return null;
    }
    
    public static String getRedirectDomain(final String s) {
        String substring = "";
        if (s != null) {
            substring = s.substring(s.indexOf("//") + 2, s.lastIndexOf("/"));
        }
        return substring;
    }
    
    public static Object getResource(final String s) {
        return TapjoyUtil.b.get(s);
    }
    
    public static Map jsonToStringMap(final JSONObject jsonObject) {
        Map stringMap = new HashMap();
        if (jsonObject != JSONObject.NULL) {
            stringMap = toStringMap(jsonObject);
        }
        return stringMap;
    }
    
    public static Bitmap loadBitmapFromJar(final String p0, final Context p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          6
        //     3: new             Landroid/graphics/BitmapFactory$Options;
        //     6: dup            
        //     7: invokespecial   android/graphics/BitmapFactory$Options.<init>:()V
        //    10: astore          7
        //    12: aload           7
        //    14: iconst_1       
        //    15: putfield        android/graphics/BitmapFactory$Options.inJustDecodeBounds:Z
        //    18: aload_0        
        //    19: invokestatic    com/tapjoy/TapjoyUtil.getResource:(Ljava/lang/String;)Ljava/lang/Object;
        //    22: checkcast       [B
        //    25: checkcast       [B
        //    28: astore          4
        //    30: aload           4
        //    32: ifnull          402
        //    35: aload           4
        //    37: iconst_0       
        //    38: aload           4
        //    40: arraylength    
        //    41: aload           7
        //    43: invokestatic    android/graphics/BitmapFactory.decodeByteArray:([BIILandroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
        //    46: pop            
        //    47: aload           4
        //    49: iconst_0       
        //    50: aload           4
        //    52: arraylength    
        //    53: invokestatic    android/graphics/BitmapFactory.decodeByteArray:([BII)Landroid/graphics/Bitmap;
        //    56: astore          4
        //    58: aload           4
        //    60: astore          5
        //    62: aload           4
        //    64: ifnonnull       178
        //    67: new             Ljava/lang/StringBuilder;
        //    70: dup            
        //    71: ldc_w           "com/tapjoy/res/"
        //    74: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //    77: aload_0        
        //    78: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    81: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    84: astore          5
        //    86: ldc             Lcom/tapjoy/TapjoyUtil;.class
        //    88: invokevirtual   java/lang/Class.getClassLoader:()Ljava/lang/ClassLoader;
        //    91: aload           5
        //    93: invokevirtual   java/lang/ClassLoader.getResource:(Ljava/lang/String;)Ljava/net/URL;
        //    96: astore_0       
        //    97: aload_0        
        //    98: ifnonnull       217
        //   101: aload_1        
        //   102: invokevirtual   android/content/Context.getAssets:()Landroid/content/res/AssetManager;
        //   105: astore          8
        //   107: aload           8
        //   109: aload           5
        //   111: invokevirtual   android/content/res/AssetManager.open:(Ljava/lang/String;)Ljava/io/InputStream;
        //   114: astore          4
        //   116: aload           4
        //   118: astore_0       
        //   119: aload           4
        //   121: astore_1       
        //   122: aload           4
        //   124: aconst_null    
        //   125: aload           7
        //   127: invokestatic    android/graphics/BitmapFactory.decodeStream:(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
        //   130: pop            
        //   131: aload           4
        //   133: astore_0       
        //   134: aload           4
        //   136: astore_1       
        //   137: aload           8
        //   139: aload           5
        //   141: invokevirtual   android/content/res/AssetManager.open:(Ljava/lang/String;)Ljava/io/InputStream;
        //   144: astore          4
        //   146: aload           4
        //   148: astore_0       
        //   149: aload           4
        //   151: astore_1       
        //   152: aload           4
        //   154: invokestatic    android/graphics/BitmapFactory.decodeStream:(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
        //   157: astore          5
        //   159: aload           5
        //   161: astore_0       
        //   162: aload_0        
        //   163: astore          5
        //   165: aload           4
        //   167: ifnull          178
        //   170: aload           4
        //   172: invokevirtual   java/io/InputStream.close:()V
        //   175: aload_0        
        //   176: astore          5
        //   178: invokestatic    com/tapjoy/TapjoyConnectCore.getDeviceScreenDensityScale:()F
        //   181: fstore_2       
        //   182: aload           5
        //   184: astore_0       
        //   185: aload           5
        //   187: ifnull          215
        //   190: aload           5
        //   192: aload           7
        //   194: getfield        android/graphics/BitmapFactory$Options.outWidth:I
        //   197: i2f            
        //   198: fload_2        
        //   199: fmul           
        //   200: f2i            
        //   201: fload_2        
        //   202: aload           7
        //   204: getfield        android/graphics/BitmapFactory$Options.outHeight:I
        //   207: i2f            
        //   208: fmul           
        //   209: f2i            
        //   210: iconst_1       
        //   211: invokestatic    android/graphics/Bitmap.createScaledBitmap:(Landroid/graphics/Bitmap;IIZ)Landroid/graphics/Bitmap;
        //   214: astore_0       
        //   215: aload_0        
        //   216: areturn        
        //   217: aload_0        
        //   218: invokevirtual   java/net/URL.getFile:()Ljava/lang/String;
        //   221: astore_1       
        //   222: aload_1        
        //   223: astore_0       
        //   224: aload_1        
        //   225: ldc             "jar:"
        //   227: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   230: ifeq            239
        //   233: aload_1        
        //   234: iconst_4       
        //   235: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   238: astore_0       
        //   239: aload_0        
        //   240: astore_1       
        //   241: aload_0        
        //   242: ldc             "file:"
        //   244: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   247: ifeq            256
        //   250: aload_0        
        //   251: iconst_5       
        //   252: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   255: astore_1       
        //   256: aload_1        
        //   257: ldc             "!"
        //   259: invokevirtual   java/lang/String.indexOf:(Ljava/lang/String;)I
        //   262: istore_3       
        //   263: aload_1        
        //   264: astore_0       
        //   265: iload_3        
        //   266: ifle            276
        //   269: aload_1        
        //   270: iconst_0       
        //   271: iload_3        
        //   272: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   275: astore_0       
        //   276: new             Ljava/util/jar/JarFile;
        //   279: dup            
        //   280: aload_0        
        //   281: invokespecial   java/util/jar/JarFile.<init>:(Ljava/lang/String;)V
        //   284: astore          8
        //   286: aload           8
        //   288: aload           5
        //   290: invokevirtual   java/util/jar/JarFile.getJarEntry:(Ljava/lang/String;)Ljava/util/jar/JarEntry;
        //   293: astore          5
        //   295: aload           8
        //   297: aload           5
        //   299: invokevirtual   java/util/jar/JarFile.getInputStream:(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;
        //   302: astore          4
        //   304: aload           4
        //   306: astore_0       
        //   307: aload           4
        //   309: astore_1       
        //   310: aload           4
        //   312: aconst_null    
        //   313: aload           7
        //   315: invokestatic    android/graphics/BitmapFactory.decodeStream:(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
        //   318: pop            
        //   319: aload           4
        //   321: astore_0       
        //   322: aload           4
        //   324: astore_1       
        //   325: aload           8
        //   327: aload           5
        //   329: invokevirtual   java/util/jar/JarFile.getInputStream:(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;
        //   332: astore          4
        //   334: goto            146
        //   337: astore          4
        //   339: aconst_null    
        //   340: astore_1       
        //   341: aload_1        
        //   342: astore_0       
        //   343: aload           4
        //   345: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   348: aload_1        
        //   349: ifnull          356
        //   352: aload_1        
        //   353: invokevirtual   java/io/InputStream.close:()V
        //   356: aconst_null    
        //   357: areturn        
        //   358: astore_0       
        //   359: aload           6
        //   361: astore_1       
        //   362: aload_1        
        //   363: ifnull          370
        //   366: aload_1        
        //   367: invokevirtual   java/io/InputStream.close:()V
        //   370: aload_0        
        //   371: athrow         
        //   372: astore_1       
        //   373: aload_0        
        //   374: astore          5
        //   376: goto            178
        //   379: astore_0       
        //   380: goto            356
        //   383: astore_1       
        //   384: goto            370
        //   387: astore          4
        //   389: aload_0        
        //   390: astore_1       
        //   391: aload           4
        //   393: astore_0       
        //   394: goto            362
        //   397: astore          4
        //   399: goto            341
        //   402: aconst_null    
        //   403: astore          4
        //   405: goto            58
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  86     97     337    341    Ljava/lang/Exception;
        //  86     97     358    362    Any
        //  101    116    337    341    Ljava/lang/Exception;
        //  101    116    358    362    Any
        //  122    131    397    402    Ljava/lang/Exception;
        //  122    131    387    397    Any
        //  137    146    397    402    Ljava/lang/Exception;
        //  137    146    387    397    Any
        //  152    159    397    402    Ljava/lang/Exception;
        //  152    159    387    397    Any
        //  170    175    372    379    Ljava/io/IOException;
        //  217    222    337    341    Ljava/lang/Exception;
        //  217    222    358    362    Any
        //  224    239    337    341    Ljava/lang/Exception;
        //  224    239    358    362    Any
        //  241    256    337    341    Ljava/lang/Exception;
        //  241    256    358    362    Any
        //  256    263    337    341    Ljava/lang/Exception;
        //  256    263    358    362    Any
        //  269    276    337    341    Ljava/lang/Exception;
        //  269    276    358    362    Any
        //  276    304    337    341    Ljava/lang/Exception;
        //  276    304    358    362    Any
        //  310    319    397    402    Ljava/lang/Exception;
        //  310    319    387    397    Any
        //  325    334    397    402    Ljava/lang/Exception;
        //  325    334    387    397    Any
        //  343    348    387    397    Any
        //  352    356    379    383    Ljava/io/IOException;
        //  366    370    383    387    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 221, Size: 221
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
    
    public static void runOnMainThread(final Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
            return;
        }
        new Handler(Looper.getMainLooper()).post(runnable);
    }
    
    public static void safePut(final Map map, final String s, final Number n) {
        if (s != null && s.length() > 0 && n != null) {
            map.put(s, n.toString());
        }
    }
    
    public static void safePut(final Map map, final String s, final String s2, final boolean b) {
        if (s != null && s.length() > 0 && s2 != null && s2.length() > 0) {
            if (!b) {
                map.put(s, s2);
                return;
            }
            map.put(Uri.encode(s), Uri.encode(s2));
        }
    }
    
    public static View scaleDisplayAd(final View view, final int n) {
        final int width = view.getLayoutParams().width;
        final int height = view.getLayoutParams().height;
        TapjoyLog.d("TapjoyUtil", "wxh: " + width + "x" + height);
        if (width > n) {
            final int intValue = (n / (double)width * 100.0).intValue();
            ((WebView)view).getSettings().setSupportZoom(true);
            ((WebView)view).setPadding(0, 0, 0, 0);
            ((WebView)view).setVerticalScrollBarEnabled(false);
            ((WebView)view).setHorizontalScrollBarEnabled(false);
            ((WebView)view).setInitialScale(intValue);
            view.setLayoutParams(new ViewGroup$LayoutParams(n, height * n / width));
        }
        return view;
    }
    
    public static void setResource(final String s, final Object o) {
        TapjoyUtil.b.put(s, o);
    }
    
    public static Map toStringMap(final JSONObject jsonObject) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        final Iterator keys = jsonObject.keys();
        while (keys.hasNext()) {
            final String s = keys.next();
            hashMap.put(s, jsonObject.get(s).toString());
        }
        return hashMap;
    }
    
    public static void writeFileToDevice(final BufferedInputStream bufferedInputStream, final OutputStream outputStream) {
        final byte[] array = new byte[1024];
        while (true) {
            final int read = bufferedInputStream.read(array);
            if (read == -1) {
                break;
            }
            outputStream.write(array, 0, read);
        }
    }
}
