// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren.model;

import java.io.IOException;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import android.support.annotation.NonNull;
import java.util.Arrays;
import com.vungle.warren.persistence.MemoryUtils;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import com.vungle.warren.persistence.Memorable;

public class Cookie implements Memorable
{
    public static final String APP_ID = "appId";
    public static final String CONFIG_COOKIE = "configSettings";
    public static final String CONSENT_COOKIE = "consentIsImportantToVungle";
    public static final String GOOGLE_AD_ID_COOKIE = "googleAdId";
    public static final String INCENTIVIZED_TEXT_COOKIE = "incentivizedTextSetByPub";
    private Map<String, Boolean> booleans;
    private String identifier;
    private Map<String, Integer> integers;
    private Map<String, Long> longs;
    private Map<String, String> strings;
    
    public Cookie(final String identifier) {
        this.strings = new HashMap<String, String>();
        this.booleans = new HashMap<String, Boolean>();
        this.integers = new HashMap<String, Integer>();
        this.longs = new HashMap<String, Long>();
        this.identifier = identifier;
    }
    
    public Cookie(final byte[] array) {
        this.strings = new HashMap<String, String>();
        this.booleans = new HashMap<String, Boolean>();
        this.integers = new HashMap<String, Integer>();
        this.longs = new HashMap<String, Long>();
        if (array.length == 0) {
            throw new IllegalArgumentException("Cannot recreated from empty array!");
        }
        final ByteBuffer wrap = ByteBuffer.wrap(array);
        this.identifier = MemoryUtils.extract(wrap, String.class);
        this.strings = MemoryUtils.extractMap(wrap, String.class);
        this.booleans = MemoryUtils.extractMap(wrap, Boolean.class);
        this.integers = MemoryUtils.extractMap(wrap, Integer.class);
        this.longs = MemoryUtils.extractMap(wrap, Long.class);
    }
    
    public static Cookie restore(final int n, final int n2, final byte[] array) {
        if (array == null || array.length <= 0) {
            return null;
        }
        return new Cookie(Arrays.copyOfRange(array, 1, array.length));
    }
    
    public Boolean getBoolean(final String s) {
        return this.booleans.get(s) != null && this.booleans.get(s);
    }
    
    @NonNull
    @Override
    public String getId() {
        return this.identifier;
    }
    
    public Integer getInt(final String s) {
        return this.integers.get(s);
    }
    
    public Long getLong(final String s) {
        long longValue;
        if (this.longs.get(s) != null) {
            longValue = this.longs.get(s);
        }
        else {
            longValue = 0L;
        }
        return longValue;
    }
    
    public String getString(final String s) {
        return this.strings.get(s);
    }
    
    public <T> void putValue(final String s, final T t) {
        if (t instanceof String) {
            this.strings.put(s, (String)t);
            return;
        }
        if (t instanceof Boolean) {
            this.booleans.put(s, (Boolean)t);
            return;
        }
        if (t instanceof Integer) {
            this.integers.put(s, (Integer)t);
            return;
        }
        if (t instanceof Long) {
            this.longs.put(s, (Long)t);
            return;
        }
        throw new IllegalArgumentException("Value type is not supported!");
    }
    
    @Override
    public byte[] toByteArray() {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            MemoryUtils.write(this.identifier, byteArrayOutputStream);
            MemoryUtils.writeMap(this.strings, byteArrayOutputStream);
            MemoryUtils.writeMap(this.booleans, byteArrayOutputStream);
            MemoryUtils.writeMap(this.integers, byteArrayOutputStream);
            MemoryUtils.writeMap(this.longs, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
        catch (IOException ex) {
            Log.e("Cookie#toByteArray()", "Failed to write " + this + " to a byte array");
            return new byte[0];
        }
    }
}
