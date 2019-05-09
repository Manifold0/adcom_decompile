// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

public interface BundleCompat<T>
{
    boolean containsKey(final String p0);
    
    boolean getBoolean(final String p0);
    
    boolean getBoolean(final String p0, final boolean p1);
    
    T getBundle();
    
    Integer getInt(final String p0);
    
    Long getLong(final String p0);
    
    String getString(final String p0);
    
    void putBoolean(final String p0, final Boolean p1);
    
    void putInt(final String p0, final Integer p1);
    
    void putLong(final String p0, final Long p1);
    
    void putString(final String p0, final String p1);
}
