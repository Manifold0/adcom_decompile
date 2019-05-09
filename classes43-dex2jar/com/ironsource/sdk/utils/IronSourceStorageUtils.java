// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.sdk.utils;

import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import com.ironsource.sdk.data.SSAEnums;
import org.json.JSONArray;
import com.ironsource.sdk.data.SSAFile;
import com.ironsource.environment.DeviceStatus;
import org.json.JSONException;
import java.io.File;
import android.content.Context;
import org.json.JSONObject;

public class IronSourceStorageUtils
{
    private static final String SSA_DIRECTORY_NAME = "supersonicads";
    
    private static JSONObject buildFilesMap(final String p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: aload_0        
        //     5: aload_1        
        //     6: invokespecial   java/io/File.<init>:(Ljava/lang/String;Ljava/lang/String;)V
        //     9: astore_1       
        //    10: new             Lorg/json/JSONObject;
        //    13: dup            
        //    14: invokespecial   org/json/JSONObject.<init>:()V
        //    17: astore_0       
        //    18: aload_1        
        //    19: invokevirtual   java/io/File.listFiles:()[Ljava/io/File;
        //    22: astore_1       
        //    23: aload_1        
        //    24: ifnull          154
        //    27: aload_1        
        //    28: arraylength    
        //    29: istore_3       
        //    30: iconst_0       
        //    31: istore_2       
        //    32: iload_2        
        //    33: iload_3        
        //    34: if_icmpge       154
        //    37: aload_1        
        //    38: iload_2        
        //    39: aaload         
        //    40: astore          4
        //    42: aload           4
        //    44: invokestatic    com/ironsource/sdk/utils/IronSourceStorageUtils.looping:(Ljava/io/File;)Ljava/lang/Object;
        //    47: astore          5
        //    49: aload           5
        //    51: instanceof      Lorg/json/JSONArray;
        //    54: ifeq            72
        //    57: aload_0        
        //    58: ldc             "files"
        //    60: aload           4
        //    62: invokestatic    com/ironsource/sdk/utils/IronSourceStorageUtils.looping:(Ljava/io/File;)Ljava/lang/Object;
        //    65: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    68: pop            
        //    69: goto            156
        //    72: aload           5
        //    74: instanceof      Lorg/json/JSONObject;
        //    77: ifeq            156
        //    80: aload_0        
        //    81: aload           4
        //    83: invokevirtual   java/io/File.getName:()Ljava/lang/String;
        //    86: aload           4
        //    88: invokestatic    com/ironsource/sdk/utils/IronSourceStorageUtils.looping:(Ljava/io/File;)Ljava/lang/Object;
        //    91: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    94: pop            
        //    95: goto            156
        //    98: astore          4
        //   100: aload           4
        //   102: invokevirtual   org/json/JSONException.printStackTrace:()V
        //   105: new             Lcom/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask;
        //   108: dup            
        //   109: invokespecial   com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask.<init>:()V
        //   112: iconst_1       
        //   113: anewarray       Ljava/lang/String;
        //   116: dup            
        //   117: iconst_0       
        //   118: new             Ljava/lang/StringBuilder;
        //   121: dup            
        //   122: invokespecial   java/lang/StringBuilder.<init>:()V
        //   125: ldc             "https://www.supersonicads.com/mobile/sdk5/log?method="
        //   127: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   130: aload           4
        //   132: invokevirtual   org/json/JSONException.getStackTrace:()[Ljava/lang/StackTraceElement;
        //   135: iconst_0       
        //   136: aaload         
        //   137: invokevirtual   java/lang/StackTraceElement.getMethodName:()Ljava/lang/String;
        //   140: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   143: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   146: aastore        
        //   147: invokevirtual   com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask.execute:([Ljava/lang/Object;)Landroid/os/AsyncTask;
        //   150: pop            
        //   151: goto            156
        //   154: aload_0        
        //   155: areturn        
        //   156: iload_2        
        //   157: iconst_1       
        //   158: iadd           
        //   159: istore_2       
        //   160: goto            32
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  42     69     98     154    Lorg/json/JSONException;
        //  72     95     98     154    Lorg/json/JSONException;
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
    
    private static String createRootDirectory(final Context context) {
        final File diskCacheDir = getDiskCacheDir(context, "supersonicads");
        if (!diskCacheDir.exists()) {
            diskCacheDir.mkdir();
        }
        return diskCacheDir.getPath();
    }
    
    private static void deleteAllFiles(final String s) {
        final File[] listFiles = new File(s).listFiles();
        if (listFiles != null) {
            for (int length = listFiles.length, i = 0; i < length; ++i) {
                final File file = listFiles[i];
                if (file.isDirectory()) {
                    deleteAllFiles(file.getAbsolutePath());
                    file.delete();
                }
                else {
                    file.delete();
                }
            }
        }
    }
    
    public static boolean deleteFile(final String s, final String s2, final String s3) {
        final boolean b = false;
        synchronized (IronSourceStorageUtils.class) {
            final File file = new File(s, s2);
            boolean delete;
            if (!file.exists()) {
                delete = b;
            }
            else {
                final File[] listFiles = file.listFiles();
                delete = b;
                if (listFiles != null) {
                    final int length = listFiles.length;
                    int n = 0;
                    File file2;
                    while (true) {
                        delete = b;
                        if (n >= length) {
                            return delete;
                        }
                        file2 = listFiles[n];
                        if (file2.isFile() && file2.getName().equalsIgnoreCase(s3)) {
                            break;
                        }
                        ++n;
                    }
                    delete = file2.delete();
                }
            }
            return delete;
        }
    }
    
    public static boolean deleteFolder(final String s, final String s2) {
        synchronized (IronSourceStorageUtils.class) {
            final File file = new File(s, s2);
            return deleteFolderContentRecursive(file) && file.delete();
        }
    }
    
    private static boolean deleteFolderContentRecursive(final File file) {
        final File[] listFiles = file.listFiles();
        int n = 1;
        int n2 = 1;
        if (listFiles != null) {
            final int length = listFiles.length;
            int n3 = 0;
            while (true) {
                n = n2;
                if (n3 >= length) {
                    break;
                }
                final File file2 = listFiles[n3];
                boolean b = n2 != 0;
                if (file2.isDirectory()) {
                    b = ((n2 & (deleteFolderContentRecursive(file2) ? 1 : 0)) != 0x0);
                }
                n2 = (b ? 1 : 0);
                if (!file2.delete()) {
                    n2 = (false ? 1 : 0);
                }
                ++n3;
            }
        }
        return n != 0;
    }
    
    public static String getCachedFilesMap(String buildFilesMap, final String s) {
        buildFilesMap = (String)buildFilesMap(buildFilesMap, s);
        try {
            ((JSONObject)buildFilesMap).put("path", (Object)s);
            return ((JSONObject)buildFilesMap).toString();
        }
        catch (JSONException ex) {
            ex.printStackTrace();
            return ((JSONObject)buildFilesMap).toString();
        }
    }
    
    private static File getDiskCacheDir(final Context context, final String s) {
        if (!SDKUtils.isExternalStorageAvailable()) {
            return new File(DeviceStatus.getInternalCacheDirPath(context) + File.separator + s);
        }
        final File externalCacheDir = DeviceStatus.getExternalCacheDir(context);
        if (externalCacheDir != null && externalCacheDir.canWrite()) {
            return new File(externalCacheDir.getPath() + File.separator + s);
        }
        return new File(DeviceStatus.getInternalCacheDirPath(context) + File.separator + s);
    }
    
    public static String initializeCacheDirectory(final Context context) {
        createRootDirectory(context);
        return refreshRootDirectory(context);
    }
    
    public static boolean isFileCached(final String s, final SSAFile ssaFile) {
        final boolean b = false;
        synchronized (IronSourceStorageUtils.class) {
            final File file = new File(s, ssaFile.getPath());
            boolean b2 = b;
            if (file.listFiles() != null) {
                final File[] listFiles = file.listFiles();
                final int length = listFiles.length;
                int n = 0;
                while (true) {
                    b2 = b;
                    if (n >= length) {
                        break;
                    }
                    final File file2 = listFiles[n];
                    if (file2.isFile() && file2.getName().equalsIgnoreCase(SDKUtils.getFileName(ssaFile.getFile()))) {
                        b2 = true;
                        break;
                    }
                    ++n;
                }
            }
            return b2;
        }
    }
    
    public static boolean isPathExist(final String s, final String s2) {
        return new File(s, s2).exists();
    }
    
    private static Object looping(final File file) {
        JSONObject jsonObject = null;
        Label_0161: {
            while (true) {
                jsonObject = new JSONObject();
                final JSONArray jsonArray = new JSONArray();
                while (true) {
                    int n = 0;
                    Label_0321: {
                        try {
                            if (file.isFile()) {
                                jsonArray.put((Object)file.getName());
                                return jsonArray;
                            }
                            final File[] listFiles = file.listFiles();
                            final int length = listFiles.length;
                            n = 0;
                            if (n >= length) {
                                break Label_0161;
                            }
                            final File file2 = listFiles[n];
                            if (file2.isDirectory()) {
                                jsonObject.put(file2.getName(), looping(file2));
                                break Label_0321;
                            }
                            jsonArray.put((Object)file2.getName());
                            jsonObject.put("files", (Object)jsonArray);
                            break Label_0321;
                        }
                        catch (JSONException ex) {
                            ex.printStackTrace();
                            new IronSourceAsyncHttpRequestTask().execute((Object[])new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=" + ex.getStackTrace()[0].getMethodName() });
                        }
                        break;
                    }
                    ++n;
                    continue;
                }
            }
            return jsonObject;
        }
        if (file.isDirectory()) {
            final String campaignLastUpdate = IronSourceSharedPrefHelper.getSupersonicPrefHelper().getCampaignLastUpdate(file.getName());
            if (campaignLastUpdate != null) {
                jsonObject.put("lastUpdateTime", (Object)campaignLastUpdate);
            }
        }
        final String lowerCase = file.getName().toLowerCase();
        Enum<SSAEnums.ProductType> enum1 = null;
        if (lowerCase.startsWith(SSAEnums.ProductType.RewardedVideo.toString().toLowerCase())) {
            enum1 = SSAEnums.ProductType.RewardedVideo;
        }
        else if (lowerCase.startsWith(SSAEnums.ProductType.OfferWall.toString().toLowerCase())) {
            enum1 = SSAEnums.ProductType.OfferWall;
        }
        else if (lowerCase.startsWith(SSAEnums.ProductType.Interstitial.toString().toLowerCase())) {
            enum1 = SSAEnums.ProductType.Interstitial;
        }
        if (enum1 != null) {
            jsonObject.put(SDKUtils.encodeString("applicationUserId"), (Object)SDKUtils.encodeString(IronSourceSharedPrefHelper.getSupersonicPrefHelper().getUniqueId((SSAEnums.ProductType)enum1)));
            jsonObject.put(SDKUtils.encodeString("applicationKey"), (Object)SDKUtils.encodeString(IronSourceSharedPrefHelper.getSupersonicPrefHelper().getApplicationKey((SSAEnums.ProductType)enum1)));
            return jsonObject;
        }
        return jsonObject;
    }
    
    public static String makeDir(final String s, final String s2) {
        final File file = new File(s, s2);
        if (!file.exists() && !file.mkdirs()) {
            return null;
        }
        return file.getPath();
    }
    
    private static String refreshRootDirectory(final Context context) {
        final String currentSDKVersion = IronSourceSharedPrefHelper.getSupersonicPrefHelper().getCurrentSDKVersion();
        final String supersonicSdkVersion = DeviceProperties.getSupersonicSdkVersion();
        if (!currentSDKVersion.equalsIgnoreCase(supersonicSdkVersion)) {
            IronSourceSharedPrefHelper.getSupersonicPrefHelper().setCurrentSDKVersion(supersonicSdkVersion);
            final File externalCacheDir = DeviceStatus.getExternalCacheDir(context);
            if (externalCacheDir != null) {
                deleteAllFiles(externalCacheDir.getAbsolutePath() + File.separator + "supersonicads" + File.separator);
            }
            deleteAllFiles(DeviceStatus.getInternalCacheDirPath(context) + File.separator + "supersonicads" + File.separator);
            return createRootDirectory(context);
        }
        return getDiskCacheDir(context, "supersonicads").getPath();
    }
    
    public static boolean renameFile(final String s, final String s2) throws Exception {
        return new File(s).renameTo(new File(s2));
    }
    
    public static int saveFile(byte[] array, String s) throws Exception {
        int n = 0;
        s = (String)new FileOutputStream(new File(s));
        array = (byte[])(Object)new ByteArrayInputStream(array);
        try {
            final byte[] array2 = new byte[102400];
            while (true) {
                final int read = ((InputStream)(Object)array).read(array2);
                if (read == -1) {
                    break;
                }
                ((FileOutputStream)s).write(array2, 0, read);
                n += read;
            }
            return n;
        }
        finally {
            ((FileOutputStream)s).close();
            ((ByteArrayInputStream)(Object)array).close();
        }
    }
}
