// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook;

import com.facebook.internal.Logger;
import java.util.Iterator;
import java.util.List;
import android.content.SharedPreferences$Editor;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONArray;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONObject;
import android.os.Bundle;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import android.content.Context;
import android.content.SharedPreferences;

final class LegacyTokenHelper
{
    public static final String APPLICATION_ID_KEY = "com.facebook.TokenCachingStrategy.ApplicationId";
    public static final String DECLINED_PERMISSIONS_KEY = "com.facebook.TokenCachingStrategy.DeclinedPermissions";
    public static final String DEFAULT_CACHE_KEY = "com.facebook.SharedPreferencesTokenCachingStrategy.DEFAULT_KEY";
    public static final String EXPIRATION_DATE_KEY = "com.facebook.TokenCachingStrategy.ExpirationDate";
    private static final long INVALID_BUNDLE_MILLISECONDS = Long.MIN_VALUE;
    private static final String IS_SSO_KEY = "com.facebook.TokenCachingStrategy.IsSSO";
    private static final String JSON_VALUE = "value";
    private static final String JSON_VALUE_ENUM_TYPE = "enumType";
    private static final String JSON_VALUE_TYPE = "valueType";
    public static final String LAST_REFRESH_DATE_KEY = "com.facebook.TokenCachingStrategy.LastRefreshDate";
    public static final String PERMISSIONS_KEY = "com.facebook.TokenCachingStrategy.Permissions";
    private static final String TAG;
    public static final String TOKEN_KEY = "com.facebook.TokenCachingStrategy.Token";
    public static final String TOKEN_SOURCE_KEY = "com.facebook.TokenCachingStrategy.AccessTokenSource";
    private static final String TYPE_BOOLEAN = "bool";
    private static final String TYPE_BOOLEAN_ARRAY = "bool[]";
    private static final String TYPE_BYTE = "byte";
    private static final String TYPE_BYTE_ARRAY = "byte[]";
    private static final String TYPE_CHAR = "char";
    private static final String TYPE_CHAR_ARRAY = "char[]";
    private static final String TYPE_DOUBLE = "double";
    private static final String TYPE_DOUBLE_ARRAY = "double[]";
    private static final String TYPE_ENUM = "enum";
    private static final String TYPE_FLOAT = "float";
    private static final String TYPE_FLOAT_ARRAY = "float[]";
    private static final String TYPE_INTEGER = "int";
    private static final String TYPE_INTEGER_ARRAY = "int[]";
    private static final String TYPE_LONG = "long";
    private static final String TYPE_LONG_ARRAY = "long[]";
    private static final String TYPE_SHORT = "short";
    private static final String TYPE_SHORT_ARRAY = "short[]";
    private static final String TYPE_STRING = "string";
    private static final String TYPE_STRING_LIST = "stringList";
    private SharedPreferences cache;
    private String cacheKey;
    
    static {
        TAG = LegacyTokenHelper.class.getSimpleName();
    }
    
    public LegacyTokenHelper(final Context context) {
        this(context, null);
    }
    
    public LegacyTokenHelper(Context context, final String s) {
        Validate.notNull(context, "context");
        String cacheKey = s;
        if (Utility.isNullOrEmpty(s)) {
            cacheKey = "com.facebook.SharedPreferencesTokenCachingStrategy.DEFAULT_KEY";
        }
        this.cacheKey = cacheKey;
        final Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            context = applicationContext;
        }
        this.cache = context.getSharedPreferences(this.cacheKey, 0);
    }
    
    private void deserializeKey(final String s, final Bundle bundle) throws JSONException {
        final JSONObject jsonObject = new JSONObject(this.cache.getString(s, "{}"));
        final String string = jsonObject.getString("valueType");
        if (string.equals("bool")) {
            bundle.putBoolean(s, jsonObject.getBoolean("value"));
        }
        else {
            if (string.equals("bool[]")) {
                final JSONArray jsonArray = jsonObject.getJSONArray("value");
                final boolean[] array = new boolean[jsonArray.length()];
                for (int i = 0; i < array.length; ++i) {
                    array[i] = jsonArray.getBoolean(i);
                }
                bundle.putBooleanArray(s, array);
                return;
            }
            if (string.equals("byte")) {
                bundle.putByte(s, (byte)jsonObject.getInt("value"));
                return;
            }
            if (string.equals("byte[]")) {
                final JSONArray jsonArray2 = jsonObject.getJSONArray("value");
                final byte[] array2 = new byte[jsonArray2.length()];
                for (int j = 0; j < array2.length; ++j) {
                    array2[j] = (byte)jsonArray2.getInt(j);
                }
                bundle.putByteArray(s, array2);
                return;
            }
            if (string.equals("short")) {
                bundle.putShort(s, (short)jsonObject.getInt("value"));
                return;
            }
            if (string.equals("short[]")) {
                final JSONArray jsonArray3 = jsonObject.getJSONArray("value");
                final short[] array3 = new short[jsonArray3.length()];
                for (int k = 0; k < array3.length; ++k) {
                    array3[k] = (short)jsonArray3.getInt(k);
                }
                bundle.putShortArray(s, array3);
                return;
            }
            if (string.equals("int")) {
                bundle.putInt(s, jsonObject.getInt("value"));
                return;
            }
            if (string.equals("int[]")) {
                final JSONArray jsonArray4 = jsonObject.getJSONArray("value");
                final int[] array4 = new int[jsonArray4.length()];
                for (int l = 0; l < array4.length; ++l) {
                    array4[l] = jsonArray4.getInt(l);
                }
                bundle.putIntArray(s, array4);
                return;
            }
            if (string.equals("long")) {
                bundle.putLong(s, jsonObject.getLong("value"));
                return;
            }
            if (string.equals("long[]")) {
                final JSONArray jsonArray5 = jsonObject.getJSONArray("value");
                final long[] array5 = new long[jsonArray5.length()];
                for (int n = 0; n < array5.length; ++n) {
                    array5[n] = jsonArray5.getLong(n);
                }
                bundle.putLongArray(s, array5);
                return;
            }
            if (string.equals("float")) {
                bundle.putFloat(s, (float)jsonObject.getDouble("value"));
                return;
            }
            if (string.equals("float[]")) {
                final JSONArray jsonArray6 = jsonObject.getJSONArray("value");
                final float[] array6 = new float[jsonArray6.length()];
                for (int n2 = 0; n2 < array6.length; ++n2) {
                    array6[n2] = (float)jsonArray6.getDouble(n2);
                }
                bundle.putFloatArray(s, array6);
                return;
            }
            if (string.equals("double")) {
                bundle.putDouble(s, jsonObject.getDouble("value"));
                return;
            }
            if (string.equals("double[]")) {
                final JSONArray jsonArray7 = jsonObject.getJSONArray("value");
                final double[] array7 = new double[jsonArray7.length()];
                for (int n3 = 0; n3 < array7.length; ++n3) {
                    array7[n3] = jsonArray7.getDouble(n3);
                }
                bundle.putDoubleArray(s, array7);
                return;
            }
            if (string.equals("char")) {
                final String string2 = jsonObject.getString("value");
                if (string2 != null && string2.length() == 1) {
                    bundle.putChar(s, string2.charAt(0));
                }
            }
            else {
                if (string.equals("char[]")) {
                    final JSONArray jsonArray8 = jsonObject.getJSONArray("value");
                    final char[] array8 = new char[jsonArray8.length()];
                    for (int n4 = 0; n4 < array8.length; ++n4) {
                        final String string3 = jsonArray8.getString(n4);
                        if (string3 != null && string3.length() == 1) {
                            array8[n4] = string3.charAt(0);
                        }
                    }
                    bundle.putCharArray(s, array8);
                    return;
                }
                if (string.equals("string")) {
                    bundle.putString(s, jsonObject.getString("value"));
                    return;
                }
                if (string.equals("stringList")) {
                    final JSONArray jsonArray9 = jsonObject.getJSONArray("value");
                    final int length = jsonArray9.length();
                    final ArrayList list = new ArrayList<String>(length);
                    for (int n5 = 0; n5 < length; ++n5) {
                        final Object value = jsonArray9.get(n5);
                        String s2;
                        if (value == JSONObject.NULL) {
                            s2 = null;
                        }
                        else {
                            s2 = (String)value;
                        }
                        list.add(n5, s2);
                    }
                    bundle.putStringArrayList(s, list);
                    return;
                }
                if (string.equals("enum")) {
                    try {
                        bundle.putSerializable(s, Enum.valueOf(Class.forName(jsonObject.getString("enumType")), jsonObject.getString("value")));
                    }
                    catch (ClassNotFoundException ex) {}
                    catch (IllegalArgumentException ex2) {}
                }
            }
        }
    }
    
    public static String getApplicationId(final Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        return bundle.getString("com.facebook.TokenCachingStrategy.ApplicationId");
    }
    
    static Date getDate(final Bundle bundle, final String s) {
        if (bundle != null) {
            final long long1 = bundle.getLong(s, Long.MIN_VALUE);
            if (long1 != Long.MIN_VALUE) {
                return new Date(long1);
            }
        }
        return null;
    }
    
    public static Date getExpirationDate(final Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        return getDate(bundle, "com.facebook.TokenCachingStrategy.ExpirationDate");
    }
    
    public static long getExpirationMilliseconds(final Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        return bundle.getLong("com.facebook.TokenCachingStrategy.ExpirationDate");
    }
    
    public static Date getLastRefreshDate(final Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        return getDate(bundle, "com.facebook.TokenCachingStrategy.LastRefreshDate");
    }
    
    public static long getLastRefreshMilliseconds(final Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        return bundle.getLong("com.facebook.TokenCachingStrategy.LastRefreshDate");
    }
    
    public static Set<String> getPermissions(final Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        final ArrayList stringArrayList = bundle.getStringArrayList("com.facebook.TokenCachingStrategy.Permissions");
        if (stringArrayList == null) {
            return null;
        }
        return new HashSet<String>(stringArrayList);
    }
    
    public static AccessTokenSource getSource(final Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        if (bundle.containsKey("com.facebook.TokenCachingStrategy.AccessTokenSource")) {
            return (AccessTokenSource)bundle.getSerializable("com.facebook.TokenCachingStrategy.AccessTokenSource");
        }
        if (bundle.getBoolean("com.facebook.TokenCachingStrategy.IsSSO")) {
            return AccessTokenSource.FACEBOOK_APPLICATION_WEB;
        }
        return AccessTokenSource.WEB_VIEW;
    }
    
    public static String getToken(final Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        return bundle.getString("com.facebook.TokenCachingStrategy.Token");
    }
    
    public static boolean hasTokenInformation(final Bundle bundle) {
        if (bundle != null) {
            final String string = bundle.getString("com.facebook.TokenCachingStrategy.Token");
            if (string != null && string.length() != 0 && bundle.getLong("com.facebook.TokenCachingStrategy.ExpirationDate", 0L) != 0L) {
                return true;
            }
        }
        return false;
    }
    
    public static void putApplicationId(final Bundle bundle, final String s) {
        Validate.notNull(bundle, "bundle");
        bundle.putString("com.facebook.TokenCachingStrategy.ApplicationId", s);
    }
    
    static void putDate(final Bundle bundle, final String s, final Date date) {
        bundle.putLong(s, date.getTime());
    }
    
    public static void putDeclinedPermissions(final Bundle bundle, final Collection<String> collection) {
        Validate.notNull(bundle, "bundle");
        Validate.notNull(collection, "value");
        bundle.putStringArrayList("com.facebook.TokenCachingStrategy.DeclinedPermissions", new ArrayList((Collection<? extends E>)collection));
    }
    
    public static void putExpirationDate(final Bundle bundle, final Date date) {
        Validate.notNull(bundle, "bundle");
        Validate.notNull(date, "value");
        putDate(bundle, "com.facebook.TokenCachingStrategy.ExpirationDate", date);
    }
    
    public static void putExpirationMilliseconds(final Bundle bundle, final long n) {
        Validate.notNull(bundle, "bundle");
        bundle.putLong("com.facebook.TokenCachingStrategy.ExpirationDate", n);
    }
    
    public static void putLastRefreshDate(final Bundle bundle, final Date date) {
        Validate.notNull(bundle, "bundle");
        Validate.notNull(date, "value");
        putDate(bundle, "com.facebook.TokenCachingStrategy.LastRefreshDate", date);
    }
    
    public static void putLastRefreshMilliseconds(final Bundle bundle, final long n) {
        Validate.notNull(bundle, "bundle");
        bundle.putLong("com.facebook.TokenCachingStrategy.LastRefreshDate", n);
    }
    
    public static void putPermissions(final Bundle bundle, final Collection<String> collection) {
        Validate.notNull(bundle, "bundle");
        Validate.notNull(collection, "value");
        bundle.putStringArrayList("com.facebook.TokenCachingStrategy.Permissions", new ArrayList((Collection<? extends E>)collection));
    }
    
    public static void putSource(final Bundle bundle, final AccessTokenSource accessTokenSource) {
        Validate.notNull(bundle, "bundle");
        bundle.putSerializable("com.facebook.TokenCachingStrategy.AccessTokenSource", (Serializable)accessTokenSource);
    }
    
    public static void putToken(final Bundle bundle, final String s) {
        Validate.notNull(bundle, "bundle");
        Validate.notNull(s, "value");
        bundle.putString("com.facebook.TokenCachingStrategy.Token", s);
    }
    
    private void serializeKey(final String s, final Bundle bundle, final SharedPreferences$Editor sharedPreferences$Editor) throws JSONException {
        final int n = 0;
        final int n2 = 0;
        final int n3 = 0;
        final int n4 = 0;
        final int n5 = 0;
        final int n6 = 0;
        final int n7 = 0;
        int n8 = 0;
        final Object value = bundle.get(s);
        if (value != null) {
            Object o = null;
            Object o2 = null;
            final JSONObject jsonObject = new JSONObject();
            if (value instanceof Byte) {
                o = "byte";
                jsonObject.put("value", (int)value);
            }
            else if (value instanceof Short) {
                o = "short";
                jsonObject.put("value", (int)value);
            }
            else if (value instanceof Integer) {
                o = "int";
                jsonObject.put("value", (int)value);
            }
            else if (value instanceof Long) {
                o = "long";
                jsonObject.put("value", (long)value);
            }
            else if (value instanceof Float) {
                o = "float";
                jsonObject.put("value", (double)value);
            }
            else if (value instanceof Double) {
                o = "double";
                jsonObject.put("value", (double)value);
            }
            else if (value instanceof Boolean) {
                o = "bool";
                jsonObject.put("value", (boolean)value);
            }
            else if (value instanceof Character) {
                o = "char";
                jsonObject.put("value", (Object)value.toString());
            }
            else if (value instanceof String) {
                o = "string";
                jsonObject.put("value", (Object)value);
            }
            else if (value instanceof Enum) {
                o = "enum";
                jsonObject.put("value", (Object)value.toString());
                jsonObject.put("enumType", (Object)((List<String>)value).getClass().getName());
            }
            else {
                final JSONArray jsonArray = new JSONArray();
                if (value instanceof byte[]) {
                    final String s2 = "byte[]";
                    final byte[] array = (byte[])value;
                    final int length = array.length;
                    while (true) {
                        o2 = jsonArray;
                        o = s2;
                        if (n8 >= length) {
                            break;
                        }
                        jsonArray.put((int)array[n8]);
                        ++n8;
                    }
                }
                else if (value instanceof short[]) {
                    final String s3 = "short[]";
                    final short[] array2 = (short[])value;
                    final int length2 = array2.length;
                    int n9 = n;
                    while (true) {
                        o2 = jsonArray;
                        o = s3;
                        if (n9 >= length2) {
                            break;
                        }
                        jsonArray.put((int)array2[n9]);
                        ++n9;
                    }
                }
                else if (value instanceof int[]) {
                    final String s4 = "int[]";
                    final int[] array3 = (int[])value;
                    final int length3 = array3.length;
                    int n10 = n2;
                    while (true) {
                        o2 = jsonArray;
                        o = s4;
                        if (n10 >= length3) {
                            break;
                        }
                        jsonArray.put(array3[n10]);
                        ++n10;
                    }
                }
                else if (value instanceof long[]) {
                    final String s5 = "long[]";
                    final long[] array4 = (long[])value;
                    final int length4 = array4.length;
                    int n11 = n3;
                    while (true) {
                        o2 = jsonArray;
                        o = s5;
                        if (n11 >= length4) {
                            break;
                        }
                        jsonArray.put(array4[n11]);
                        ++n11;
                    }
                }
                else if (value instanceof float[]) {
                    final String s6 = "float[]";
                    final float[] array5 = (float[])value;
                    final int length5 = array5.length;
                    int n12 = n4;
                    while (true) {
                        o2 = jsonArray;
                        o = s6;
                        if (n12 >= length5) {
                            break;
                        }
                        jsonArray.put((double)array5[n12]);
                        ++n12;
                    }
                }
                else if (value instanceof double[]) {
                    final String s7 = "double[]";
                    final double[] array6 = (double[])value;
                    final int length6 = array6.length;
                    int n13 = n5;
                    while (true) {
                        o2 = jsonArray;
                        o = s7;
                        if (n13 >= length6) {
                            break;
                        }
                        jsonArray.put(array6[n13]);
                        ++n13;
                    }
                }
                else if (value instanceof boolean[]) {
                    final String s8 = "bool[]";
                    final boolean[] array7 = (boolean[])value;
                    final int length7 = array7.length;
                    int n14 = n6;
                    while (true) {
                        o2 = jsonArray;
                        o = s8;
                        if (n14 >= length7) {
                            break;
                        }
                        jsonArray.put(array7[n14]);
                        ++n14;
                    }
                }
                else if (value instanceof char[]) {
                    final String s9 = "char[]";
                    final char[] array8 = (char[])value;
                    final int length8 = array8.length;
                    int n15 = n7;
                    while (true) {
                        o2 = jsonArray;
                        o = s9;
                        if (n15 >= length8) {
                            break;
                        }
                        jsonArray.put((Object)String.valueOf(array8[n15]));
                        ++n15;
                    }
                }
                else if (value instanceof List) {
                    final String s10 = "stringList";
                    final Iterator<String> iterator = ((List<String>)value).iterator();
                    while (true) {
                        o2 = jsonArray;
                        o = s10;
                        if (!iterator.hasNext()) {
                            break;
                        }
                        Object null;
                        if ((null = iterator.next()) == null) {
                            null = JSONObject.NULL;
                        }
                        jsonArray.put(null);
                    }
                }
                else {
                    o2 = null;
                }
            }
            if (o != null) {
                jsonObject.put("valueType", o);
                if (o2 != null) {
                    jsonObject.putOpt("value", o2);
                }
                sharedPreferences$Editor.putString(s, jsonObject.toString());
            }
        }
    }
    
    public void clear() {
        this.cache.edit().clear().apply();
    }
    
    public Bundle load() {
        final Bundle bundle = new Bundle();
        final Iterator<String> iterator = this.cache.getAll().keySet().iterator();
        Bundle bundle2;
        while (true) {
            bundle2 = bundle;
            if (iterator.hasNext()) {
                final String s = iterator.next();
                try {
                    this.deserializeKey(s, bundle);
                    continue;
                }
                catch (JSONException ex) {
                    Logger.log(LoggingBehavior.CACHE, 5, LegacyTokenHelper.TAG, "Error reading cached value for key: '" + s + "' -- " + ex);
                    bundle2 = null;
                }
                break;
            }
            break;
        }
        return bundle2;
    }
    
    public void save(final Bundle bundle) {
        Validate.notNull(bundle, "bundle");
        final SharedPreferences$Editor edit = this.cache.edit();
        for (final String s : bundle.keySet()) {
            try {
                this.serializeKey(s, bundle, edit);
                continue;
            }
            catch (JSONException ex) {
                Logger.log(LoggingBehavior.CACHE, 5, LegacyTokenHelper.TAG, "Error processing value for key: '" + s + "' -- " + ex);
                return;
            }
            break;
        }
        edit.apply();
    }
}
