package com.unity3d.services.core.api;

import com.unity3d.services.core.device.StorageError;
import com.unity3d.services.core.device.StorageManager;
import com.unity3d.services.core.device.StorageManager.StorageType;
import com.unity3d.services.core.webview.bridge.WebViewCallback;
import com.unity3d.services.core.webview.bridge.WebViewExposed;
import org.json.JSONArray;
import org.json.JSONObject;

public class Storage {
    @WebViewExposed
    public static void set(String type, String key, Integer value, WebViewCallback callback) {
        set(type, key, (Object) value, callback);
    }

    @WebViewExposed
    public static void set(String type, String key, Boolean value, WebViewCallback callback) {
        set(type, key, (Object) value, callback);
    }

    @WebViewExposed
    public static void set(String type, String key, Long value, WebViewCallback callback) {
        set(type, key, (Object) value, callback);
    }

    @WebViewExposed
    public static void set(String type, String key, Double value, WebViewCallback callback) {
        set(type, key, (Object) value, callback);
    }

    @WebViewExposed
    public static void set(String type, String key, String value, WebViewCallback callback) {
        set(type, key, (Object) value, callback);
    }

    @WebViewExposed
    public static void set(String type, String key, JSONObject value, WebViewCallback callback) {
        set(type, key, (Object) value, callback);
    }

    @WebViewExposed
    public static void set(String type, String key, JSONArray value, WebViewCallback callback) {
        set(type, key, (Object) value, callback);
    }

    private static void set(String type, String key, Object value, WebViewCallback callback) {
        com.unity3d.services.core.device.Storage s = getStorage(type);
        if (s == null) {
            callback.error(StorageError.COULDNT_GET_STORAGE, type, key, value);
        } else if (s.set(key, value)) {
            callback.invoke(key, value);
        } else {
            callback.error(StorageError.COULDNT_SET_VALUE, key, value);
        }
    }

    @WebViewExposed
    public static void get(String type, String key, WebViewCallback callback) {
        com.unity3d.services.core.device.Storage s = getStorage(type);
        if (s != null) {
            if (s.get(key) == null) {
                callback.error(StorageError.COULDNT_GET_VALUE, key);
                return;
            }
            callback.invoke(s.get(key));
            return;
        }
        callback.error(StorageError.COULDNT_GET_STORAGE, type, key);
    }

    @WebViewExposed
    public static void getKeys(String type, String key, Boolean recursive, WebViewCallback callback) {
        com.unity3d.services.core.device.Storage s = getStorage(type);
        if (s != null) {
            if (s.getKeys(key, recursive.booleanValue()) != null) {
                callback.invoke(new JSONArray(s.getKeys(key, recursive.booleanValue())));
                return;
            }
            callback.invoke(new JSONArray());
            return;
        }
        callback.error(StorageError.COULDNT_GET_STORAGE, type, key);
    }

    @WebViewExposed
    public static void read(String type, WebViewCallback callback) {
        com.unity3d.services.core.device.Storage s = getStorage(type);
        if (s != null) {
            s.readStorage();
            callback.invoke(type);
            return;
        }
        callback.error(StorageError.COULDNT_GET_STORAGE, type);
    }

    @WebViewExposed
    public static void write(String type, WebViewCallback callback) {
        com.unity3d.services.core.device.Storage s = getStorage(type);
        if (s == null) {
            callback.error(StorageError.COULDNT_GET_STORAGE, type);
        } else if (s.writeStorage()) {
            callback.invoke(type);
        } else {
            callback.error(StorageError.COULDNT_WRITE_STORAGE_TO_CACHE, type);
        }
    }

    @WebViewExposed
    public static void clear(String type, WebViewCallback callback) {
        com.unity3d.services.core.device.Storage s = getStorage(type);
        if (s == null) {
            callback.error(StorageError.COULDNT_GET_STORAGE, type);
        } else if (s.clearStorage()) {
            callback.invoke(type);
        } else {
            callback.error(StorageError.COULDNT_CLEAR_STORAGE, type);
        }
    }

    @WebViewExposed
    public static void delete(String type, String key, WebViewCallback callback) {
        com.unity3d.services.core.device.Storage s = getStorage(type);
        if (s == null) {
            callback.error(StorageError.COULDNT_GET_STORAGE, type);
        } else if (s.delete(key)) {
            callback.invoke(type);
        } else {
            callback.error(StorageError.COULDNT_DELETE_VALUE, type);
        }
    }

    private static com.unity3d.services.core.device.Storage getStorage(String type) {
        return StorageManager.getStorage(StorageType.valueOf(type));
    }
}
