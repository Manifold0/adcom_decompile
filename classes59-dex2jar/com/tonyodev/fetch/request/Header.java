// 
// Decompiled by Procyon v0.5.34
// 

package com.tonyodev.fetch.request;

import android.support.annotation.Nullable;
import android.support.annotation.NonNull;

public final class Header
{
    private final String header;
    private final String value;
    
    public Header(@NonNull final String header, @Nullable final String s) {
        if (header == null) {
            throw new NullPointerException("header cannot be null");
        }
        if (header.contains(":")) {
            throw new IllegalArgumentException("header may not contain ':'");
        }
        String value;
        if ((value = s) == null) {
            value = "";
        }
        this.header = header;
        this.value = value;
    }
    
    @NonNull
    public String getHeader() {
        return this.header;
    }
    
    @NonNull
    public String getValue() {
        return this.value;
    }
    
    @Override
    public String toString() {
        return this.header + ":" + this.value;
    }
}
