package com.ironsource.sdk.utils;

import android.content.Context;
import com.google.android.gms.nearby.messages.Message;
import com.ironsource.environment.DeviceStatus;
import com.ironsource.sdk.constants.Constants;
import com.ironsource.sdk.constants.Constants.RequestParameters;
import com.ironsource.sdk.data.SSAEnums.ProductType;
import com.ironsource.sdk.data.SSAFile;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class IronSourceStorageUtils {
    private static final String SSA_DIRECTORY_NAME = "supersonicads";

    public static String initializeCacheDirectory(Context context) {
        createRootDirectory(context);
        return refreshRootDirectory(context);
    }

    private static String refreshRootDirectory(Context context) {
        String storedVerison = IronSourceSharedPrefHelper.getSupersonicPrefHelper().getCurrentSDKVersion();
        String sdkVer = DeviceProperties.getSupersonicSdkVersion();
        if (storedVerison.equalsIgnoreCase(sdkVer)) {
            return getDiskCacheDir(context, SSA_DIRECTORY_NAME).getPath();
        }
        IronSourceSharedPrefHelper.getSupersonicPrefHelper().setCurrentSDKVersion(sdkVer);
        File cacheDir = DeviceStatus.getExternalCacheDir(context);
        if (cacheDir != null) {
            deleteAllFiles(cacheDir.getAbsolutePath() + File.separator + SSA_DIRECTORY_NAME + File.separator);
        }
        deleteAllFiles(DeviceStatus.getInternalCacheDirPath(context) + File.separator + SSA_DIRECTORY_NAME + File.separator);
        return createRootDirectory(context);
    }

    private static void deleteAllFiles(String path) {
        File[] files = new File(path).listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteAllFiles(file.getAbsolutePath());
                    file.delete();
                } else {
                    file.delete();
                }
            }
        }
    }

    private static File getDiskCacheDir(Context context, String cacheDirName) {
        if (SDKUtils.isExternalStorageAvailable()) {
            File externalCacheDir = DeviceStatus.getExternalCacheDir(context);
            if (externalCacheDir == null || !externalCacheDir.canWrite()) {
                return new File(DeviceStatus.getInternalCacheDirPath(context) + File.separator + cacheDirName);
            }
            return new File(externalCacheDir.getPath() + File.separator + cacheDirName);
        }
        return new File(DeviceStatus.getInternalCacheDirPath(context) + File.separator + cacheDirName);
    }

    private static String createRootDirectory(Context context) {
        File rootDirectory = getDiskCacheDir(context, SSA_DIRECTORY_NAME);
        if (!rootDirectory.exists()) {
            rootDirectory.mkdir();
        }
        return rootDirectory.getPath();
    }

    public static String makeDir(String cacheRootDirectory, String directory) {
        File dir = new File(cacheRootDirectory, directory);
        if (dir.exists() || dir.mkdirs()) {
            return dir.getPath();
        }
        return null;
    }

    public static synchronized boolean deleteFile(String rootCacheDir, String filePath, String fileName) {
        boolean z = false;
        synchronized (IronSourceStorageUtils.class) {
            File dir = new File(rootCacheDir, filePath);
            if (dir.exists()) {
                File[] files = dir.listFiles();
                if (files != null) {
                    for (File entry : files) {
                        if (entry.isFile() && entry.getName().equalsIgnoreCase(fileName)) {
                            z = entry.delete();
                            break;
                        }
                    }
                }
            }
        }
        return z;
    }

    public static synchronized boolean isFileCached(String rootDirPath, SSAFile ssaFile) {
        boolean z = false;
        synchronized (IronSourceStorageUtils.class) {
            File dir = new File(rootDirPath, ssaFile.getPath());
            if (dir.listFiles() != null) {
                for (File entry : dir.listFiles()) {
                    if (entry.isFile() && entry.getName().equalsIgnoreCase(SDKUtils.getFileName(ssaFile.getFile()))) {
                        z = true;
                        break;
                    }
                }
            }
        }
        return z;
    }

    public static boolean isPathExist(String cachRootPath, String path) {
        return new File(cachRootPath, path).exists();
    }

    public static synchronized boolean deleteFolder(String cacheRootDir, String path) {
        boolean z;
        synchronized (IronSourceStorageUtils.class) {
            File folder = new File(cacheRootDir, path);
            z = deleteFolderContentRecursive(folder) && folder.delete();
        }
        return z;
    }

    private static boolean deleteFolderContentRecursive(File dir) {
        File[] files = dir.listFiles();
        boolean success = true;
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    success &= deleteFolderContentRecursive(file);
                }
                if (!file.delete()) {
                    success = false;
                }
            }
        }
        return success;
    }

    public static String getCachedFilesMap(String cacheRootPath, String path) {
        JSONObject jsnObj = buildFilesMap(cacheRootPath, path);
        try {
            jsnObj.put("path", path);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsnObj.toString();
    }

    private static JSONObject buildFilesMap(String cacheRootPath, String path) {
        File dir = new File(cacheRootPath, path);
        JSONObject jsnObj = new JSONObject();
        File[] fileList = dir.listFiles();
        if (fileList != null) {
            for (File entry : fileList) {
                try {
                    Object obj = looping(entry);
                    if (obj instanceof JSONArray) {
                        jsnObj.put("files", looping(entry));
                    } else if (obj instanceof JSONObject) {
                        jsnObj.put(entry.getName(), looping(entry));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    new IronSourceAsyncHttpRequestTask().execute(new String[]{Constants.NATIVE_EXCEPTION_BASE_URL + e.getStackTrace()[0].getMethodName()});
                }
            }
        }
        return jsnObj;
    }

    private static Object looping(File file) {
        JSONObject arr = new JSONObject();
        JSONArray tempArr = new JSONArray();
        try {
            if (file.isFile()) {
                tempArr.put(file.getName());
                return tempArr;
            }
            for (File fileEntry : file.listFiles()) {
                if (fileEntry.isDirectory()) {
                    arr.put(fileEntry.getName(), looping(fileEntry));
                } else {
                    tempArr.put(fileEntry.getName());
                    arr.put("files", tempArr);
                }
            }
            if (file.isDirectory()) {
                String lastUpdate = IronSourceSharedPrefHelper.getSupersonicPrefHelper().getCampaignLastUpdate(file.getName());
                if (lastUpdate != null) {
                    arr.put("lastUpdateTime", lastUpdate);
                }
            }
            String product = file.getName().toLowerCase();
            ProductType type = null;
            if (product.startsWith(ProductType.RewardedVideo.toString().toLowerCase())) {
                type = ProductType.RewardedVideo;
            } else if (product.startsWith(ProductType.OfferWall.toString().toLowerCase())) {
                type = ProductType.OfferWall;
            } else if (product.startsWith(ProductType.Interstitial.toString().toLowerCase())) {
                type = ProductType.Interstitial;
            }
            if (type != null) {
                arr.put(SDKUtils.encodeString(RequestParameters.APPLICATION_USER_ID), SDKUtils.encodeString(IronSourceSharedPrefHelper.getSupersonicPrefHelper().getUniqueId(type)));
                arr.put(SDKUtils.encodeString(RequestParameters.APPLICATION_KEY), SDKUtils.encodeString(IronSourceSharedPrefHelper.getSupersonicPrefHelper().getApplicationKey(type)));
            }
            return arr;
        } catch (JSONException e) {
            e.printStackTrace();
            new IronSourceAsyncHttpRequestTask().execute(new String[]{Constants.NATIVE_EXCEPTION_BASE_URL + e.getStackTrace()[0].getMethodName()});
        }
    }

    public static boolean renameFile(String fromName, String toName) throws Exception {
        return new File(fromName).renameTo(new File(toName));
    }

    public static int saveFile(byte[] data, String destFileName) throws Exception {
        int totalBytesRead = 0;
        FileOutputStream fos = new FileOutputStream(new File(destFileName));
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        try {
            byte[] buffer = new byte[Message.MAX_CONTENT_SIZE_BYTES];
            while (true) {
                int bytesRead = bais.read(buffer);
                if (bytesRead == -1) {
                    break;
                }
                fos.write(buffer, 0, bytesRead);
                totalBytesRead += bytesRead;
            }
            return totalBytesRead;
        } finally {
            fos.close();
            bais.close();
        }
    }
}
