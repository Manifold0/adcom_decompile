// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.api;

import org.json.JSONObject;
import com.unity3d.services.core.device.StorageManager;
import java.util.List;
import java.util.Collection;
import org.json.JSONArray;
import com.unity3d.services.core.webview.bridge.WebViewExposed;
import com.unity3d.services.core.device.StorageError;
import com.unity3d.services.core.webview.bridge.WebViewCallback;

public class Storage
{
    @WebViewExposed
    public static void clear(final String s, final WebViewCallback webViewCallback) {
        final com.unity3d.services.core.device.Storage storage = getStorage(s);
        if (storage == null) {
            webViewCallback.error(StorageError.COULDNT_GET_STORAGE, s);
            return;
        }
        if (storage.clearStorage()) {
            webViewCallback.invoke(s);
            return;
        }
        webViewCallback.error(StorageError.COULDNT_CLEAR_STORAGE, s);
    }
    
    @WebViewExposed
    public static void delete(final String s, final String s2, final WebViewCallback webViewCallback) {
        final com.unity3d.services.core.device.Storage storage = getStorage(s);
        if (storage == null) {
            webViewCallback.error(StorageError.COULDNT_GET_STORAGE, s);
            return;
        }
        if (storage.delete(s2)) {
            webViewCallback.invoke(s);
            return;
        }
        webViewCallback.error(StorageError.COULDNT_DELETE_VALUE, s);
    }
    
    @WebViewExposed
    public static void get(final String s, final String s2, final WebViewCallback webViewCallback) {
        final com.unity3d.services.core.device.Storage storage = getStorage(s);
        if (storage == null) {
            webViewCallback.error(StorageError.COULDNT_GET_STORAGE, s, s2);
            return;
        }
        final Object value = storage.get(s2);
        if (value == null) {
            webViewCallback.error(StorageError.COULDNT_GET_VALUE, s2);
            return;
        }
        webViewCallback.invoke(value);
    }
    
    @WebViewExposed
    public static void getKeys(final String s, final String s2, final Boolean b, final WebViewCallback webViewCallback) {
        final com.unity3d.services.core.device.Storage storage = getStorage(s);
        if (storage == null) {
            webViewCallback.error(StorageError.COULDNT_GET_STORAGE, s, s2);
            return;
        }
        final List<String> keys = storage.getKeys(s2, b);
        if (keys != null) {
            webViewCallback.invoke(new JSONArray((Collection)keys));
            return;
        }
        webViewCallback.invoke(new JSONArray());
    }
    
    private static com.unity3d.services.core.device.Storage getStorage(final String s) {
        return StorageManager.getStorage(StorageManager.StorageType.valueOf(s));
    }
    
    @WebViewExposed
    public static void read(final String s, final WebViewCallback webViewCallback) {
        final com.unity3d.services.core.device.Storage storage = getStorage(s);
        if (storage != null) {
            storage.readStorage();
            webViewCallback.invoke(s);
            return;
        }
        webViewCallback.error(StorageError.COULDNT_GET_STORAGE, s);
    }
    
    @WebViewExposed
    public static void set(final String s, final String s2, final Boolean b, final WebViewCallback webViewCallback) {
        set(s, s2, (Object)b, webViewCallback);
    }
    
    @WebViewExposed
    public static void set(final String s, final String s2, final Double n, final WebViewCallback webViewCallback) {
        set(s, s2, (Object)n, webViewCallback);
    }
    
    @WebViewExposed
    public static void set(final String s, final String s2, final Integer n, final WebViewCallback webViewCallback) {
        set(s, s2, (Object)n, webViewCallback);
    }
    
    @WebViewExposed
    public static void set(final String s, final String s2, final Long n, final WebViewCallback webViewCallback) {
        set(s, s2, (Object)n, webViewCallback);
    }
    
    private static void set(final String s, final String s2, final Object o, final WebViewCallback webViewCallback) {
        final com.unity3d.services.core.device.Storage storage = getStorage(s);
        if (storage == null) {
            webViewCallback.error(StorageError.COULDNT_GET_STORAGE, s, s2, o);
            return;
        }
        if (storage.set(s2, o)) {
            webViewCallback.invoke(s2, o);
            return;
        }
        webViewCallback.error(StorageError.COULDNT_SET_VALUE, s2, o);
    }
    
    @WebViewExposed
    public static void set(final String s, final String s2, final String s3, final WebViewCallback webViewCallback) {
        set(s, s2, (Object)s3, webViewCallback);
    }
    
    @WebViewExposed
    public static void set(final String s, final String s2, final JSONArray jsonArray, final WebViewCallback webViewCallback) {
        set(s, s2, (Object)jsonArray, webViewCallback);
    }
    
    @WebViewExposed
    public static void set(final String s, final String s2, final JSONObject jsonObject, final WebViewCallback webViewCallback) {
        set(s, s2, (Object)jsonObject, webViewCallback);
    }
    
    @WebViewExposed
    public static void write(final String s, final WebViewCallback webViewCallback) {
        final com.unity3d.services.core.device.Storage storage = getStorage(s);
        if (storage == null) {
            webViewCallback.error(StorageError.COULDNT_GET_STORAGE, s);
            return;
        }
        if (storage.writeStorage()) {
            webViewCallback.invoke(s);
            return;
        }
        webViewCallback.error(StorageError.COULDNT_WRITE_STORAGE_TO_CACHE, s);
    }
}
