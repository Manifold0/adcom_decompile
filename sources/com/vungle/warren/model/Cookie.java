package com.vungle.warren.model;

import android.support.annotation.NonNull;
import android.util.Log;
import com.vungle.warren.persistence.Memorable;
import com.vungle.warren.persistence.MemoryUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Cookie implements Memorable {
    public static final String APP_ID = "appId";
    public static final String CONFIG_COOKIE = "configSettings";
    public static final String CONSENT_COOKIE = "consentIsImportantToVungle";
    public static final String GOOGLE_AD_ID_COOKIE = "googleAdId";
    public static final String INCENTIVIZED_TEXT_COOKIE = "incentivizedTextSetByPub";
    private Map<String, Boolean> booleans = new HashMap();
    private String identifier;
    private Map<String, Integer> integers = new HashMap();
    private Map<String, Long> longs = new HashMap();
    private Map<String, String> strings = new HashMap();

    public Cookie(String identifier) {
        this.identifier = identifier;
    }

    public Cookie(byte[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Cannot recreated from empty array!");
        }
        ByteBuffer buffy = ByteBuffer.wrap(array);
        this.identifier = (String) MemoryUtils.extract(buffy, String.class);
        this.strings = MemoryUtils.extractMap(buffy, String.class);
        this.booleans = MemoryUtils.extractMap(buffy, Boolean.class);
        this.integers = MemoryUtils.extractMap(buffy, Integer.class);
        this.longs = MemoryUtils.extractMap(buffy, Long.class);
    }

    public <T> void putValue(String key, T value) {
        if (value instanceof String) {
            this.strings.put(key, (String) value);
        } else if (value instanceof Boolean) {
            this.booleans.put(key, (Boolean) value);
        } else if (value instanceof Integer) {
            this.integers.put(key, (Integer) value);
        } else if (value instanceof Long) {
            this.longs.put(key, (Long) value);
        } else {
            throw new IllegalArgumentException("Value type is not supported!");
        }
    }

    public Integer getInt(String key) {
        return (Integer) this.integers.get(key);
    }

    public String getString(String key) {
        return (String) this.strings.get(key);
    }

    public Boolean getBoolean(String key) {
        return Boolean.valueOf(this.booleans.get(key) != null ? ((Boolean) this.booleans.get(key)).booleanValue() : false);
    }

    public Long getLong(String key) {
        return Long.valueOf(this.longs.get(key) != null ? ((Long) this.longs.get(key)).longValue() : 0);
    }

    public byte[] toByteArray() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            MemoryUtils.write(this.identifier, out);
            MemoryUtils.writeMap(this.strings, out);
            MemoryUtils.writeMap(this.booleans, out);
            MemoryUtils.writeMap(this.integers, out);
            MemoryUtils.writeMap(this.longs, out);
            return out.toByteArray();
        } catch (IOException e) {
            Log.e("Cookie#toByteArray()", "Failed to write " + this + " to a byte array");
            return new byte[0];
        }
    }

    @NonNull
    public String getId() {
        return this.identifier;
    }

    public static Cookie restore(int oldVersion, int newVersion, byte[] data) {
        if (data == null || data.length <= 0) {
            return null;
        }
        return new Cookie(Arrays.copyOfRange(data, 1, data.length));
    }
}
