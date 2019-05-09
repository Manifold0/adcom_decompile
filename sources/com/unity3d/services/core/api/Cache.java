package com.unity3d.services.core.api;

import android.annotation.TargetApi;
import android.media.MediaMetadataRetriever;
import android.util.Base64;
import android.util.SparseArray;
import com.unity3d.services.core.cache.CacheDirectory;
import com.unity3d.services.core.cache.CacheError;
import com.unity3d.services.core.cache.CacheThread;
import com.unity3d.services.core.device.Device;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.misc.Utilities;
import com.unity3d.services.core.properties.ClientProperties;
import com.unity3d.services.core.properties.SdkProperties;
import com.unity3d.services.core.request.WebRequestError;
import com.unity3d.services.core.webview.bridge.WebViewCallback;
import com.unity3d.services.core.webview.bridge.WebViewExposed;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Cache {

    /* renamed from: com.unity3d.services.core.api.Cache$1 */
    static class C16101 implements FilenameFilter {
        C16101() {
        }

        public boolean accept(File dir, String filename) {
            return filename.startsWith(SdkProperties.getCacheFilePrefix());
        }
    }

    @WebViewExposed
    public static void download(String url, String fileId, JSONArray headers, Boolean append, WebViewCallback callback) {
        if (CacheThread.isActive()) {
            callback.error(CacheError.FILE_ALREADY_CACHING, new Object[0]);
        } else if (Device.isActiveNetworkConnected()) {
            try {
                CacheThread.download(url, fileIdToFilename(fileId), Request.getHeadersMap(headers), append.booleanValue());
                callback.invoke(new Object[0]);
            } catch (Exception e) {
                DeviceLog.exception("Error mapping headers for the request", e);
                callback.error(WebRequestError.MAPPING_HEADERS_FAILED, url, fileId);
            }
        } else {
            callback.error(CacheError.NO_INTERNET, new Object[0]);
        }
    }

    @WebViewExposed
    public static void stop(WebViewCallback callback) {
        if (CacheThread.isActive()) {
            CacheThread.cancel();
            callback.invoke(new Object[0]);
            return;
        }
        callback.error(CacheError.NOT_CACHING, new Object[0]);
    }

    @WebViewExposed
    public static void isCaching(WebViewCallback callback) {
        callback.invoke(Boolean.valueOf(CacheThread.isActive()));
    }

    @WebViewExposed
    public static void getFileContent(String fileId, String encoding, WebViewCallback callback) {
        File f = new File(fileIdToFilename(fileId));
        if (f.exists()) {
            try {
                byte[] byteData = Utilities.readFileBytes(f);
                if (encoding == null) {
                    callback.error(CacheError.UNSUPPORTED_ENCODING, fileId, fileName, encoding);
                    return;
                }
                String fileContents;
                if (encoding.equals("UTF-8")) {
                    fileContents = Charset.forName("UTF-8").decode(ByteBuffer.wrap(byteData)).toString();
                } else if (encoding.equals("Base64")) {
                    fileContents = Base64.encodeToString(byteData, 2);
                } else {
                    callback.error(CacheError.UNSUPPORTED_ENCODING, fileId, fileName, encoding);
                    return;
                }
                callback.invoke(fileContents);
                return;
            } catch (IOException e) {
                callback.error(CacheError.FILE_IO_ERROR, fileId, fileName, e.getMessage() + ", " + e.getClass().getName());
                return;
            }
        }
        callback.error(CacheError.FILE_NOT_FOUND, fileId, fileName);
    }

    @WebViewExposed
    public static void setFileContent(String fileId, String encoding, String content, WebViewCallback callback) {
        Throwable th;
        String targetFileName = fileIdToFilename(fileId);
        FileOutputStream fileOutput = null;
        boolean success = false;
        try {
            byte[] fileContents = content.getBytes("UTF-8");
            if (encoding != null && encoding.length() > 0) {
                if (encoding.equals("Base64")) {
                    fileContents = Base64.decode(content, 2);
                } else if (!encoding.equals("UTF-8")) {
                    callback.error(CacheError.UNSUPPORTED_ENCODING, fileId, targetFileName, encoding);
                    return;
                }
            }
            try {
                FileOutputStream fileOutput2 = new FileOutputStream(targetFileName);
                try {
                    fileOutput2.write(fileContents);
                    fileOutput2.flush();
                    success = true;
                    if (fileOutput2 != null) {
                        try {
                            fileOutput2.close();
                        } catch (Exception e) {
                            DeviceLog.exception("Error closing FileOutputStream", e);
                            fileOutput = fileOutput2;
                        }
                    }
                    fileOutput = fileOutput2;
                } catch (FileNotFoundException e2) {
                    fileOutput = fileOutput2;
                    try {
                        callback.error(CacheError.FILE_NOT_FOUND, fileId, targetFileName, encoding);
                        if (fileOutput != null) {
                            try {
                                fileOutput.close();
                            } catch (Exception e3) {
                                DeviceLog.exception("Error closing FileOutputStream", e3);
                            }
                        }
                        if (success) {
                            callback.invoke(new Object[0]);
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileOutput != null) {
                            try {
                                fileOutput.close();
                            } catch (Exception e32) {
                                DeviceLog.exception("Error closing FileOutputStream", e32);
                            }
                        }
                        throw th;
                    }
                } catch (IOException e4) {
                    fileOutput = fileOutput2;
                    callback.error(CacheError.FILE_IO_ERROR, fileId, targetFileName, encoding);
                    if (fileOutput != null) {
                        try {
                            fileOutput.close();
                        } catch (Exception e322) {
                            DeviceLog.exception("Error closing FileOutputStream", e322);
                        }
                    }
                    if (success) {
                        callback.invoke(new Object[0]);
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileOutput = fileOutput2;
                    if (fileOutput != null) {
                        fileOutput.close();
                    }
                    throw th;
                }
            } catch (FileNotFoundException e5) {
                callback.error(CacheError.FILE_NOT_FOUND, fileId, targetFileName, encoding);
                if (fileOutput != null) {
                    fileOutput.close();
                }
                if (success) {
                    callback.invoke(new Object[0]);
                }
            } catch (IOException e6) {
                callback.error(CacheError.FILE_IO_ERROR, fileId, targetFileName, encoding);
                if (fileOutput != null) {
                    fileOutput.close();
                }
                if (success) {
                    callback.invoke(new Object[0]);
                }
            }
            if (success) {
                callback.invoke(new Object[0]);
            }
        } catch (UnsupportedEncodingException e7) {
            callback.error(CacheError.UNSUPPORTED_ENCODING, fileId, targetFileName, encoding);
        }
    }

    @WebViewExposed
    public static void getFiles(WebViewCallback callback) {
        File cacheDirectory = SdkProperties.getCacheDirectory();
        if (cacheDirectory != null) {
            DeviceLog.debug("Unity Ads cache: checking app directory for Unity Ads cached files");
            File[] fileList = cacheDirectory.listFiles(new C16101());
            if (fileList == null || fileList.length == 0) {
                callback.invoke(new JSONArray());
            }
            try {
                JSONArray files = new JSONArray();
                for (File f : fileList) {
                    String name = f.getName().substring(SdkProperties.getCacheFilePrefix().length());
                    DeviceLog.debug("Unity Ads cache: found " + name + ", " + f.length() + " bytes");
                    files.put(getFileJson(name));
                }
                callback.invoke(files);
            } catch (JSONException e) {
                DeviceLog.exception("Error creating JSON", e);
                callback.error(CacheError.JSON_ERROR, new Object[0]);
            }
        }
    }

    @WebViewExposed
    public static void getFileInfo(String fileId, WebViewCallback callback) {
        try {
            callback.invoke(getFileJson(fileId));
        } catch (JSONException e) {
            DeviceLog.exception("Error creating JSON", e);
            callback.error(CacheError.JSON_ERROR, new Object[0]);
        }
    }

    @WebViewExposed
    public static void getFilePath(String fileId, WebViewCallback callback) {
        if (new File(fileIdToFilename(fileId)).exists()) {
            callback.invoke(fileIdToFilename(fileId));
            return;
        }
        callback.error(CacheError.FILE_NOT_FOUND, new Object[0]);
    }

    @WebViewExposed
    public static void deleteFile(String fileId, WebViewCallback callback) {
        if (new File(fileIdToFilename(fileId)).delete()) {
            callback.invoke(new Object[0]);
        } else {
            callback.error(CacheError.FILE_IO_ERROR, new Object[0]);
        }
    }

    @WebViewExposed
    public static void getHash(String fileId, WebViewCallback callback) {
        callback.invoke(Utilities.Sha256(fileId));
    }

    @WebViewExposed
    public static void setTimeouts(Integer connectTimeout, Integer readTimeout, WebViewCallback callback) {
        CacheThread.setConnectTimeout(connectTimeout.intValue());
        CacheThread.setReadTimeout(readTimeout.intValue());
        callback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void getTimeouts(WebViewCallback callback) {
        callback.invoke(Integer.valueOf(CacheThread.getConnectTimeout()), Integer.valueOf(CacheThread.getReadTimeout()));
    }

    @WebViewExposed
    public static void setProgressInterval(Integer interval, WebViewCallback callback) {
        CacheThread.setProgressInterval(interval.intValue());
        callback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void getProgressInterval(WebViewCallback callback) {
        callback.invoke(Integer.valueOf(CacheThread.getProgressInterval()));
    }

    @WebViewExposed
    public static void getFreeSpace(WebViewCallback callback) {
        callback.invoke(Long.valueOf(Device.getFreeSpace(SdkProperties.getCacheDirectory())));
    }

    @WebViewExposed
    public static void getTotalSpace(WebViewCallback callback) {
        callback.invoke(Long.valueOf(Device.getTotalSpace(SdkProperties.getCacheDirectory())));
    }

    @WebViewExposed
    public static void getMetaData(String fileId, JSONArray requestedMetaDatas, WebViewCallback callback) {
        try {
            SparseArray<String> returnValues = getMetaData(fileIdToFilename(fileId), requestedMetaDatas);
            JSONArray returnJsonArray = new JSONArray();
            for (int i = 0; i < returnValues.size(); i++) {
                JSONArray entryJsonArray = new JSONArray();
                entryJsonArray.put(returnValues.keyAt(i));
                entryJsonArray.put(returnValues.valueAt(i));
                returnJsonArray.put(entryJsonArray);
            }
            callback.invoke(returnJsonArray);
        } catch (JSONException e) {
            callback.error(CacheError.JSON_ERROR, e.getMessage());
        } catch (RuntimeException e2) {
            callback.error(CacheError.INVALID_ARGUMENT, e2.getMessage());
        } catch (IOException e3) {
            callback.error(CacheError.FILE_IO_ERROR, e3.getMessage());
        }
    }

    @WebViewExposed
    public static void getCacheDirectoryType(WebViewCallback callback) {
        CacheDirectory cacheDir = SdkProperties.getCacheDirectoryObject();
        if (cacheDir == null || cacheDir.getCacheDirectory(ClientProperties.getApplicationContext()) == null) {
            callback.error(CacheError.CACHE_DIRECTORY_NULL, new Object[0]);
        } else if (cacheDir.getCacheDirectory(ClientProperties.getApplicationContext()).exists()) {
            if (cacheDir.getType() == null) {
                callback.error(CacheError.CACHE_DIRECTORY_TYPE_NULL, new Object[0]);
                return;
            }
            callback.invoke(cacheDir.getType().name());
        } else {
            callback.error(CacheError.CACHE_DIRECTORY_DOESNT_EXIST, new Object[0]);
        }
    }

    @WebViewExposed
    public static void getCacheDirectoryExists(WebViewCallback callback) {
        if (SdkProperties.getCacheDirectory() == null) {
            callback.error(CacheError.CACHE_DIRECTORY_NULL, new Object[0]);
            return;
        }
        callback.invoke(Boolean.valueOf(SdkProperties.getCacheDirectory().exists()));
    }

    @WebViewExposed
    public static void recreateCacheDirectory(WebViewCallback callback) {
        if (SdkProperties.getCacheDirectory().exists()) {
            callback.error(CacheError.CACHE_DIRECTORY_EXISTS, new Object[0]);
            return;
        }
        SdkProperties.setCacheDirectory(null);
        if (SdkProperties.getCacheDirectory() == null) {
            callback.error(CacheError.CACHE_DIRECTORY_NULL, new Object[0]);
        } else {
            callback.invoke(new Object[0]);
        }
    }

    @TargetApi(10)
    private static SparseArray<String> getMetaData(String videoFile, JSONArray requestedMetaDatas) throws JSONException, IOException, RuntimeException {
        File f = new File(videoFile);
        SparseArray<String> returnArray = new SparseArray();
        if (f.exists()) {
            MediaMetadataRetriever metadataRetriever = new MediaMetadataRetriever();
            metadataRetriever.setDataSource(f.getAbsolutePath());
            for (int i = 0; i < requestedMetaDatas.length(); i++) {
                int metaDataKey = requestedMetaDatas.getInt(i);
                String metaDataValue = metadataRetriever.extractMetadata(metaDataKey);
                if (metaDataValue != null) {
                    returnArray.put(metaDataKey, metaDataValue);
                }
            }
            return returnArray;
        }
        throw new IOException("File: " + f.getAbsolutePath() + " doesn't exist");
    }

    private static String fileIdToFilename(String fileId) {
        return SdkProperties.getCacheDirectory() + "/" + SdkProperties.getCacheFilePrefix() + fileId;
    }

    private static JSONObject getFileJson(String fileId) throws JSONException {
        JSONObject fileJson = new JSONObject();
        fileJson.put("id", fileId);
        File f = new File(fileIdToFilename(fileId));
        if (f.exists()) {
            fileJson.put("found", true);
            fileJson.put("size", f.length());
            fileJson.put("mtime", f.lastModified());
        } else {
            fileJson.put("found", false);
        }
        return fileJson;
    }
}
