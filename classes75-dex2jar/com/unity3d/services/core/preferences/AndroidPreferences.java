// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.preferences;

import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.properties.ClientProperties;

public class AndroidPreferences
{
    public static Boolean getBoolean(final String s, final String s2) {
        final Boolean b = null;
        final SharedPreferences sharedPreferences = ClientProperties.getApplicationContext().getSharedPreferences(s, 0);
        Boolean value = b;
        if (sharedPreferences == null) {
            return value;
        }
        value = b;
        if (!sharedPreferences.contains(s2)) {
            return value;
        }
        try {
            value = sharedPreferences.getBoolean(s2, false);
            return value;
        }
        catch (ClassCastException ex) {
            DeviceLog.error("Unity Ads failed to cast " + s2 + ": " + ex.getMessage());
            return null;
        }
    }
    
    public static Float getFloat(final String s, final String s2) {
        final Float n = null;
        final SharedPreferences sharedPreferences = ClientProperties.getApplicationContext().getSharedPreferences(s, 0);
        Float value = n;
        if (sharedPreferences == null) {
            return value;
        }
        value = n;
        if (!sharedPreferences.contains(s2)) {
            return value;
        }
        try {
            value = sharedPreferences.getFloat(s2, Float.NaN);
            return value;
        }
        catch (ClassCastException ex) {
            DeviceLog.error("Unity Ads failed to cast " + s2 + ": " + ex.getMessage());
            return null;
        }
    }
    
    public static Integer getInteger(final String s, final String s2) {
        final Integer n = null;
        final SharedPreferences sharedPreferences = ClientProperties.getApplicationContext().getSharedPreferences(s, 0);
        Integer value = n;
        if (sharedPreferences == null) {
            return value;
        }
        value = n;
        if (!sharedPreferences.contains(s2)) {
            return value;
        }
        try {
            value = sharedPreferences.getInt(s2, -1);
            return value;
        }
        catch (ClassCastException ex) {
            DeviceLog.error("Unity Ads failed to cast " + s2 + ": " + ex.getMessage());
            return null;
        }
    }
    
    public static Long getLong(final String s, final String s2) {
        final Long n = null;
        final SharedPreferences sharedPreferences = ClientProperties.getApplicationContext().getSharedPreferences(s, 0);
        Long value = n;
        if (sharedPreferences == null) {
            return value;
        }
        value = n;
        if (!sharedPreferences.contains(s2)) {
            return value;
        }
        try {
            value = sharedPreferences.getLong(s2, -1L);
            return value;
        }
        catch (ClassCastException ex) {
            DeviceLog.error("Unity Ads failed to cast " + s2 + ": " + ex.getMessage());
            return null;
        }
    }
    
    public static String getString(String string, final String s) {
        final String s2 = null;
        final SharedPreferences sharedPreferences = ClientProperties.getApplicationContext().getSharedPreferences(string, 0);
        string = s2;
        if (sharedPreferences == null) {
            return string;
        }
        string = s2;
        if (!sharedPreferences.contains(s)) {
            return string;
        }
        try {
            string = sharedPreferences.getString(s, "");
            return string;
        }
        catch (ClassCastException ex) {
            DeviceLog.error("Unity Ads failed to cast " + s + ": " + ex.getMessage());
            return null;
        }
    }
    
    public static boolean hasKey(final String s, final String s2) {
        final boolean b = false;
        final SharedPreferences sharedPreferences = ClientProperties.getApplicationContext().getSharedPreferences(s, 0);
        boolean b2 = b;
        if (sharedPreferences != null) {
            b2 = b;
            if (sharedPreferences.contains(s2)) {
                b2 = true;
            }
        }
        return b2;
    }
    
    public static void removeKey(final String s, final String s2) {
        final SharedPreferences sharedPreferences = ClientProperties.getApplicationContext().getSharedPreferences(s, 0);
        if (sharedPreferences != null) {
            final SharedPreferences$Editor edit = sharedPreferences.edit();
            edit.remove(s2);
            edit.commit();
        }
    }
    
    public static void setBoolean(final String s, final String s2, final Boolean b) {
        final SharedPreferences sharedPreferences = ClientProperties.getApplicationContext().getSharedPreferences(s, 0);
        if (sharedPreferences != null) {
            final SharedPreferences$Editor edit = sharedPreferences.edit();
            edit.putBoolean(s2, (boolean)b);
            edit.commit();
        }
    }
    
    public static void setFloat(final String s, final String s2, final Double n) {
        final SharedPreferences sharedPreferences = ClientProperties.getApplicationContext().getSharedPreferences(s, 0);
        if (sharedPreferences != null) {
            final SharedPreferences$Editor edit = sharedPreferences.edit();
            edit.putFloat(s2, n.floatValue());
            edit.commit();
        }
    }
    
    public static void setInteger(final String s, final String s2, final Integer n) {
        final SharedPreferences sharedPreferences = ClientProperties.getApplicationContext().getSharedPreferences(s, 0);
        if (sharedPreferences != null) {
            final SharedPreferences$Editor edit = sharedPreferences.edit();
            edit.putInt(s2, (int)n);
            edit.commit();
        }
    }
    
    public static void setLong(final String s, final String s2, final Long n) {
        final SharedPreferences sharedPreferences = ClientProperties.getApplicationContext().getSharedPreferences(s, 0);
        if (sharedPreferences != null) {
            final SharedPreferences$Editor edit = sharedPreferences.edit();
            edit.putLong(s2, (long)n);
            edit.commit();
        }
    }
    
    public static void setString(final String s, final String s2, final String s3) {
        final SharedPreferences sharedPreferences = ClientProperties.getApplicationContext().getSharedPreferences(s, 0);
        if (sharedPreferences != null) {
            final SharedPreferences$Editor edit = sharedPreferences.edit();
            edit.putString(s2, s3);
            edit.commit();
        }
    }
}
