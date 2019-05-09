// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.api;

import android.annotation.TargetApi;
import android.media.MediaMetadataRetriever;
import android.util.SparseArray;
import java.io.FilenameFilter;
import org.json.JSONObject;
import org.json.JSONException;
import android.util.Base64;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.io.IOException;
import com.unity3d.services.core.misc.Utilities;
import com.unity3d.services.core.cache.CacheDirectoryType;
import com.unity3d.services.core.cache.CacheDirectory;
import com.unity3d.services.core.properties.ClientProperties;
import com.unity3d.services.core.properties.SdkProperties;
import com.unity3d.services.core.request.WebRequestError;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.device.Device;
import com.unity3d.services.core.cache.CacheThread;
import org.json.JSONArray;
import com.unity3d.services.core.webview.bridge.WebViewExposed;
import com.unity3d.services.core.cache.CacheError;
import java.io.File;
import com.unity3d.services.core.webview.bridge.WebViewCallback;

public class Cache
{
    @WebViewExposed
    public static void deleteFile(final String s, final WebViewCallback webViewCallback) {
        if (new File(fileIdToFilename(s)).delete()) {
            webViewCallback.invoke(new Object[0]);
            return;
        }
        webViewCallback.error(CacheError.FILE_IO_ERROR, new Object[0]);
    }
    
    @WebViewExposed
    public static void download(final String s, final String s2, final JSONArray jsonArray, final Boolean b, final WebViewCallback webViewCallback) {
        if (CacheThread.isActive()) {
            webViewCallback.error(CacheError.FILE_ALREADY_CACHING, new Object[0]);
            return;
        }
        if (!Device.isActiveNetworkConnected()) {
            webViewCallback.error(CacheError.NO_INTERNET, new Object[0]);
            return;
        }
        try {
            CacheThread.download(s, fileIdToFilename(s2), Request.getHeadersMap(jsonArray), b);
            webViewCallback.invoke(new Object[0]);
        }
        catch (Exception ex) {
            DeviceLog.exception("Error mapping headers for the request", ex);
            webViewCallback.error(WebRequestError.MAPPING_HEADERS_FAILED, s, s2);
        }
    }
    
    private static String fileIdToFilename(final String s) {
        return SdkProperties.getCacheDirectory() + "/" + SdkProperties.getCacheFilePrefix() + s;
    }
    
    @WebViewExposed
    public static void getCacheDirectoryExists(final WebViewCallback webViewCallback) {
        final File cacheDirectory = SdkProperties.getCacheDirectory();
        if (cacheDirectory == null) {
            webViewCallback.error(CacheError.CACHE_DIRECTORY_NULL, new Object[0]);
            return;
        }
        webViewCallback.invoke(cacheDirectory.exists());
    }
    
    @WebViewExposed
    public static void getCacheDirectoryType(final WebViewCallback webViewCallback) {
        final CacheDirectory cacheDirectoryObject = SdkProperties.getCacheDirectoryObject();
        if (cacheDirectoryObject == null || cacheDirectoryObject.getCacheDirectory(ClientProperties.getApplicationContext()) == null) {
            webViewCallback.error(CacheError.CACHE_DIRECTORY_NULL, new Object[0]);
            return;
        }
        if (!cacheDirectoryObject.getCacheDirectory(ClientProperties.getApplicationContext()).exists()) {
            webViewCallback.error(CacheError.CACHE_DIRECTORY_DOESNT_EXIST, new Object[0]);
            return;
        }
        final CacheDirectoryType type = cacheDirectoryObject.getType();
        if (type == null) {
            webViewCallback.error(CacheError.CACHE_DIRECTORY_TYPE_NULL, new Object[0]);
            return;
        }
        webViewCallback.invoke(type.name());
    }
    
    @WebViewExposed
    public static void getFileContent(String s, final String s2, final WebViewCallback webViewCallback) {
        final String fileIdToFilename = fileIdToFilename(s);
        final File file = new File(fileIdToFilename);
        if (!file.exists()) {
            webViewCallback.error(CacheError.FILE_NOT_FOUND, s, fileIdToFilename);
            return;
        }
        byte[] fileBytes;
        try {
            fileBytes = Utilities.readFileBytes(file);
            if (s2 == null) {
                webViewCallback.error(CacheError.UNSUPPORTED_ENCODING, s, fileIdToFilename, s2);
                return;
            }
        }
        catch (IOException ex) {
            webViewCallback.error(CacheError.FILE_IO_ERROR, s, fileIdToFilename, ex.getMessage() + ", " + ex.getClass().getName());
            return;
        }
        if (s2.equals("UTF-8")) {
            s = Charset.forName("UTF-8").decode(ByteBuffer.wrap(fileBytes)).toString();
        }
        else {
            if (!s2.equals("Base64")) {
                webViewCallback.error(CacheError.UNSUPPORTED_ENCODING, s, fileIdToFilename, s2);
                return;
            }
            s = Base64.encodeToString(fileBytes, 2);
        }
        webViewCallback.invoke(s);
    }
    
    @WebViewExposed
    public static void getFileInfo(final String s, final WebViewCallback webViewCallback) {
        try {
            webViewCallback.invoke(getFileJson(s));
        }
        catch (JSONException ex) {
            DeviceLog.exception("Error creating JSON", (Exception)ex);
            webViewCallback.error(CacheError.JSON_ERROR, new Object[0]);
        }
    }
    
    private static JSONObject getFileJson(final String s) throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", (Object)s);
        final File file = new File(fileIdToFilename(s));
        if (file.exists()) {
            jsonObject.put("found", true);
            jsonObject.put("size", file.length());
            jsonObject.put("mtime", file.lastModified());
            return jsonObject;
        }
        jsonObject.put("found", false);
        return jsonObject;
    }
    
    @WebViewExposed
    public static void getFilePath(final String s, final WebViewCallback webViewCallback) {
        if (new File(fileIdToFilename(s)).exists()) {
            webViewCallback.invoke(fileIdToFilename(s));
            return;
        }
        webViewCallback.error(CacheError.FILE_NOT_FOUND, new Object[0]);
    }
    
    @WebViewExposed
    public static void getFiles(final WebViewCallback webViewCallback) {
        final File cacheDirectory = SdkProperties.getCacheDirectory();
        if (cacheDirectory == null) {
            return;
        }
        DeviceLog.debug("Unity Ads cache: checking app directory for Unity Ads cached files");
        final File[] listFiles = cacheDirectory.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(final File file, final String s) {
                return s.startsWith(SdkProperties.getCacheFilePrefix());
            }
        });
        if (listFiles == null || listFiles.length == 0) {
            webViewCallback.invoke(new JSONArray());
        }
        try {
            final JSONArray jsonArray = new JSONArray();
            for (int length = listFiles.length, i = 0; i < length; ++i) {
                final File file = listFiles[i];
                final String substring = file.getName().substring(SdkProperties.getCacheFilePrefix().length());
                DeviceLog.debug("Unity Ads cache: found " + substring + ", " + file.length() + " bytes");
                jsonArray.put((Object)getFileJson(substring));
            }
            webViewCallback.invoke(jsonArray);
        }
        catch (JSONException ex) {
            DeviceLog.exception("Error creating JSON", (Exception)ex);
            webViewCallback.error(CacheError.JSON_ERROR, new Object[0]);
        }
    }
    
    @WebViewExposed
    public static void getFreeSpace(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getFreeSpace(SdkProperties.getCacheDirectory()));
    }
    
    @WebViewExposed
    public static void getHash(final String s, final WebViewCallback webViewCallback) {
        webViewCallback.invoke(Utilities.Sha256(s));
    }
    
    @TargetApi(10)
    private static SparseArray<String> getMetaData(final String s, final JSONArray jsonArray) throws JSONException, IOException, RuntimeException {
        final File file = new File(s);
        final SparseArray sparseArray = new SparseArray();
        if (file.exists()) {
            final MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(file.getAbsolutePath());
            for (int i = 0; i < jsonArray.length(); ++i) {
                final int int1 = jsonArray.getInt(i);
                final String metadata = mediaMetadataRetriever.extractMetadata(int1);
                if (metadata != null) {
                    sparseArray.put(int1, (Object)metadata);
                }
            }
            return (SparseArray<String>)sparseArray;
        }
        throw new IOException("File: " + file.getAbsolutePath() + " doesn't exist");
    }
    
    @WebViewExposed
    public static void getMetaData(String fileIdToFilename, JSONArray jsonArray, final WebViewCallback webViewCallback) {
        fileIdToFilename = fileIdToFilename(fileIdToFilename);
        try {
            final SparseArray<String> metaData = getMetaData(fileIdToFilename, jsonArray);
            jsonArray = new JSONArray();
            for (int i = 0; i < metaData.size(); ++i) {
                final JSONArray jsonArray2 = new JSONArray();
                jsonArray2.put(metaData.keyAt(i));
                jsonArray2.put(metaData.valueAt(i));
                jsonArray.put((Object)jsonArray2);
            }
        }
        catch (JSONException ex) {
            webViewCallback.error(CacheError.JSON_ERROR, ex.getMessage());
            return;
        }
        catch (RuntimeException ex2) {
            webViewCallback.error(CacheError.INVALID_ARGUMENT, ex2.getMessage());
            return;
        }
        catch (IOException ex3) {
            webViewCallback.error(CacheError.FILE_IO_ERROR, ex3.getMessage());
            return;
        }
        webViewCallback.invoke(jsonArray);
    }
    
    @WebViewExposed
    public static void getProgressInterval(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(CacheThread.getProgressInterval());
    }
    
    @WebViewExposed
    public static void getTimeouts(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(CacheThread.getConnectTimeout(), CacheThread.getReadTimeout());
    }
    
    @WebViewExposed
    public static void getTotalSpace(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getTotalSpace(SdkProperties.getCacheDirectory()));
    }
    
    @WebViewExposed
    public static void isCaching(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(CacheThread.isActive());
    }
    
    @WebViewExposed
    public static void recreateCacheDirectory(final WebViewCallback webViewCallback) {
        if (SdkProperties.getCacheDirectory().exists()) {
            webViewCallback.error(CacheError.CACHE_DIRECTORY_EXISTS, new Object[0]);
            return;
        }
        SdkProperties.setCacheDirectory(null);
        if (SdkProperties.getCacheDirectory() == null) {
            webViewCallback.error(CacheError.CACHE_DIRECTORY_NULL, new Object[0]);
            return;
        }
        webViewCallback.invoke(new Object[0]);
    }
    
    @WebViewExposed
    public static void setFileContent(final String p0, final String p1, final String p2, final WebViewCallback p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokestatic    com/unity3d/services/core/api/Cache.fileIdToFilename:(Ljava/lang/String;)Ljava/lang/String;
        //     4: astore          11
        //     6: aconst_null    
        //     7: astore          9
        //     9: aconst_null    
        //    10: astore          10
        //    12: aconst_null    
        //    13: astore          8
        //    15: iconst_0       
        //    16: istore          5
        //    18: aload_2        
        //    19: ldc             "UTF-8"
        //    21: invokevirtual   java/lang/String.getBytes:(Ljava/lang/String;)[B
        //    24: astore          6
        //    26: aload           6
        //    28: astore          7
        //    30: aload_1        
        //    31: ifnull          61
        //    34: aload           6
        //    36: astore          7
        //    38: aload_1        
        //    39: invokevirtual   java/lang/String.length:()I
        //    42: ifle            61
        //    45: aload_1        
        //    46: ldc             "Base64"
        //    48: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    51: ifeq            140
        //    54: aload_2        
        //    55: iconst_2       
        //    56: invokestatic    android/util/Base64.decode:(Ljava/lang/String;I)[B
        //    59: astore          7
        //    61: aload           10
        //    63: astore_2       
        //    64: new             Ljava/io/FileOutputStream;
        //    67: dup            
        //    68: aload           11
        //    70: invokespecial   java/io/FileOutputStream.<init>:(Ljava/lang/String;)V
        //    73: astore          6
        //    75: aload           6
        //    77: aload           7
        //    79: invokevirtual   java/io/FileOutputStream.write:([B)V
        //    82: aload           6
        //    84: invokevirtual   java/io/FileOutputStream.flush:()V
        //    87: iconst_1       
        //    88: istore          4
        //    90: aload           6
        //    92: ifnull          100
        //    95: aload           6
        //    97: invokevirtual   java/io/FileOutputStream.close:()V
        //   100: iload           4
        //   102: ifeq            113
        //   105: aload_3        
        //   106: iconst_0       
        //   107: anewarray       Ljava/lang/Object;
        //   110: invokevirtual   com/unity3d/services/core/webview/bridge/WebViewCallback.invoke:([Ljava/lang/Object;)V
        //   113: return         
        //   114: astore_2       
        //   115: aload_3        
        //   116: getstatic       com/unity3d/services/core/cache/CacheError.UNSUPPORTED_ENCODING:Lcom/unity3d/services/core/cache/CacheError;
        //   119: iconst_3       
        //   120: anewarray       Ljava/lang/Object;
        //   123: dup            
        //   124: iconst_0       
        //   125: aload_0        
        //   126: aastore        
        //   127: dup            
        //   128: iconst_1       
        //   129: aload           11
        //   131: aastore        
        //   132: dup            
        //   133: iconst_2       
        //   134: aload_1        
        //   135: aastore        
        //   136: invokevirtual   com/unity3d/services/core/webview/bridge/WebViewCallback.error:(Ljava/lang/Enum;[Ljava/lang/Object;)V
        //   139: return         
        //   140: aload           6
        //   142: astore          7
        //   144: aload_1        
        //   145: ldc             "UTF-8"
        //   147: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   150: ifne            61
        //   153: aload_3        
        //   154: getstatic       com/unity3d/services/core/cache/CacheError.UNSUPPORTED_ENCODING:Lcom/unity3d/services/core/cache/CacheError;
        //   157: iconst_3       
        //   158: anewarray       Ljava/lang/Object;
        //   161: dup            
        //   162: iconst_0       
        //   163: aload_0        
        //   164: aastore        
        //   165: dup            
        //   166: iconst_1       
        //   167: aload           11
        //   169: aastore        
        //   170: dup            
        //   171: iconst_2       
        //   172: aload_1        
        //   173: aastore        
        //   174: invokevirtual   com/unity3d/services/core/webview/bridge/WebViewCallback.error:(Ljava/lang/Enum;[Ljava/lang/Object;)V
        //   177: return         
        //   178: astore_0       
        //   179: ldc_w           "Error closing FileOutputStream"
        //   182: aload_0        
        //   183: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   186: goto            100
        //   189: astore_2       
        //   190: aload           8
        //   192: astore          6
        //   194: aload           6
        //   196: astore_2       
        //   197: aload_3        
        //   198: getstatic       com/unity3d/services/core/cache/CacheError.FILE_NOT_FOUND:Lcom/unity3d/services/core/cache/CacheError;
        //   201: iconst_3       
        //   202: anewarray       Ljava/lang/Object;
        //   205: dup            
        //   206: iconst_0       
        //   207: aload_0        
        //   208: aastore        
        //   209: dup            
        //   210: iconst_1       
        //   211: aload           11
        //   213: aastore        
        //   214: dup            
        //   215: iconst_2       
        //   216: aload_1        
        //   217: aastore        
        //   218: invokevirtual   com/unity3d/services/core/webview/bridge/WebViewCallback.error:(Ljava/lang/Enum;[Ljava/lang/Object;)V
        //   221: iload           5
        //   223: istore          4
        //   225: aload           6
        //   227: ifnull          100
        //   230: aload           6
        //   232: invokevirtual   java/io/FileOutputStream.close:()V
        //   235: iload           5
        //   237: istore          4
        //   239: goto            100
        //   242: astore_0       
        //   243: ldc_w           "Error closing FileOutputStream"
        //   246: aload_0        
        //   247: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   250: iload           5
        //   252: istore          4
        //   254: goto            100
        //   257: astore_2       
        //   258: aload           9
        //   260: astore          6
        //   262: aload           6
        //   264: astore_2       
        //   265: aload_3        
        //   266: getstatic       com/unity3d/services/core/cache/CacheError.FILE_IO_ERROR:Lcom/unity3d/services/core/cache/CacheError;
        //   269: iconst_3       
        //   270: anewarray       Ljava/lang/Object;
        //   273: dup            
        //   274: iconst_0       
        //   275: aload_0        
        //   276: aastore        
        //   277: dup            
        //   278: iconst_1       
        //   279: aload           11
        //   281: aastore        
        //   282: dup            
        //   283: iconst_2       
        //   284: aload_1        
        //   285: aastore        
        //   286: invokevirtual   com/unity3d/services/core/webview/bridge/WebViewCallback.error:(Ljava/lang/Enum;[Ljava/lang/Object;)V
        //   289: iload           5
        //   291: istore          4
        //   293: aload           6
        //   295: ifnull          100
        //   298: aload           6
        //   300: invokevirtual   java/io/FileOutputStream.close:()V
        //   303: iload           5
        //   305: istore          4
        //   307: goto            100
        //   310: astore_0       
        //   311: ldc_w           "Error closing FileOutputStream"
        //   314: aload_0        
        //   315: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   318: iload           5
        //   320: istore          4
        //   322: goto            100
        //   325: astore_0       
        //   326: aload_2        
        //   327: ifnull          334
        //   330: aload_2        
        //   331: invokevirtual   java/io/FileOutputStream.close:()V
        //   334: aload_0        
        //   335: athrow         
        //   336: astore_1       
        //   337: ldc_w           "Error closing FileOutputStream"
        //   340: aload_1        
        //   341: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   344: goto            334
        //   347: astore_0       
        //   348: aload           6
        //   350: astore_2       
        //   351: goto            326
        //   354: astore_2       
        //   355: goto            262
        //   358: astore_2       
        //   359: goto            194
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                  
        //  -----  -----  -----  -----  --------------------------------------
        //  18     26     114    140    Ljava/io/UnsupportedEncodingException;
        //  64     75     189    194    Ljava/io/FileNotFoundException;
        //  64     75     257    262    Ljava/io/IOException;
        //  64     75     325    326    Any
        //  75     87     358    362    Ljava/io/FileNotFoundException;
        //  75     87     354    358    Ljava/io/IOException;
        //  75     87     347    354    Any
        //  95     100    178    189    Ljava/lang/Exception;
        //  197    221    325    326    Any
        //  230    235    242    257    Ljava/lang/Exception;
        //  265    289    325    326    Any
        //  298    303    310    325    Ljava/lang/Exception;
        //  330    334    336    347    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 201, Size: 201
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
    
    @WebViewExposed
    public static void setProgressInterval(final Integer n, final WebViewCallback webViewCallback) {
        CacheThread.setProgressInterval(n);
        webViewCallback.invoke(new Object[0]);
    }
    
    @WebViewExposed
    public static void setTimeouts(final Integer n, final Integer n2, final WebViewCallback webViewCallback) {
        CacheThread.setConnectTimeout(n);
        CacheThread.setReadTimeout(n2);
        webViewCallback.invoke(new Object[0]);
    }
    
    @WebViewExposed
    public static void stop(final WebViewCallback webViewCallback) {
        if (!CacheThread.isActive()) {
            webViewCallback.error(CacheError.NOT_CACHING, new Object[0]);
            return;
        }
        CacheThread.cancel();
        webViewCallback.invoke(new Object[0]);
    }
}
