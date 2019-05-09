// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.api;

import com.unity3d.services.core.webview.bridge.WebViewExposed;
import com.unity3d.services.core.preferences.PreferencesError;
import com.unity3d.services.core.preferences.AndroidPreferences;
import com.unity3d.services.core.webview.bridge.WebViewCallback;

public class Preferences
{
    @WebViewExposed
    public static void getBoolean(final String s, final String s2, final WebViewCallback webViewCallback) {
        final Boolean boolean1 = AndroidPreferences.getBoolean(s, s2);
        if (boolean1 != null) {
            webViewCallback.invoke(boolean1);
            return;
        }
        webViewCallback.error(PreferencesError.COULDNT_GET_VALUE, s, s2);
    }
    
    @WebViewExposed
    public static void getFloat(final String s, final String s2, final WebViewCallback webViewCallback) {
        final Float float1 = AndroidPreferences.getFloat(s, s2);
        if (float1 != null) {
            webViewCallback.invoke(float1);
            return;
        }
        webViewCallback.error(PreferencesError.COULDNT_GET_VALUE, s, s2);
    }
    
    @WebViewExposed
    public static void getInt(final String s, final String s2, final WebViewCallback webViewCallback) {
        final Integer integer = AndroidPreferences.getInteger(s, s2);
        if (integer != null) {
            webViewCallback.invoke(integer);
            return;
        }
        webViewCallback.error(PreferencesError.COULDNT_GET_VALUE, s, s2);
    }
    
    @WebViewExposed
    public static void getLong(final String s, final String s2, final WebViewCallback webViewCallback) {
        final Long long1 = AndroidPreferences.getLong(s, s2);
        if (long1 != null) {
            webViewCallback.invoke(long1);
            return;
        }
        webViewCallback.error(PreferencesError.COULDNT_GET_VALUE, s, s2);
    }
    
    @WebViewExposed
    public static void getString(final String s, final String s2, final WebViewCallback webViewCallback) {
        final String string = AndroidPreferences.getString(s, s2);
        if (string != null) {
            webViewCallback.invoke(string);
            return;
        }
        webViewCallback.error(PreferencesError.COULDNT_GET_VALUE, s, s2);
    }
    
    @WebViewExposed
    public static void hasKey(final String s, final String s2, final WebViewCallback webViewCallback) {
        webViewCallback.invoke(AndroidPreferences.hasKey(s, s2));
    }
    
    @WebViewExposed
    public static void removeKey(final String s, final String s2, final WebViewCallback webViewCallback) {
        AndroidPreferences.removeKey(s, s2);
        webViewCallback.invoke(new Object[0]);
    }
    
    @WebViewExposed
    public static void setBoolean(final String s, final String s2, final Boolean b, final WebViewCallback webViewCallback) {
        AndroidPreferences.setBoolean(s, s2, b);
        webViewCallback.invoke(new Object[0]);
    }
    
    @WebViewExposed
    public static void setFloat(final String s, final String s2, final Double n, final WebViewCallback webViewCallback) {
        AndroidPreferences.setFloat(s, s2, n);
        webViewCallback.invoke(new Object[0]);
    }
    
    @WebViewExposed
    public static void setInt(final String s, final String s2, final Integer n, final WebViewCallback webViewCallback) {
        AndroidPreferences.setInteger(s, s2, n);
        webViewCallback.invoke(new Object[0]);
    }
    
    @WebViewExposed
    public static void setLong(final String s, final String s2, final Long n, final WebViewCallback webViewCallback) {
        AndroidPreferences.setLong(s, s2, n);
        webViewCallback.invoke(new Object[0]);
    }
    
    @WebViewExposed
    public static void setString(final String s, final String s2, final String s3, final WebViewCallback webViewCallback) {
        AndroidPreferences.setString(s, s2, s3);
        webViewCallback.invoke(new Object[0]);
    }
}
