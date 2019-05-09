// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;

public class bk extends Writer implements Serializable
{
    private final StringBuilder a;
    
    public bk() {
        this.a = new StringBuilder();
    }
    
    public bk(final int n) {
        this.a = new StringBuilder(n);
    }
    
    @Override
    public Writer append(final char c) {
        this.a.append(c);
        return this;
    }
    
    @Override
    public Writer append(final CharSequence charSequence) {
        this.a.append(charSequence);
        return this;
    }
    
    @Override
    public Writer append(final CharSequence charSequence, final int n, final int n2) {
        this.a.append(charSequence, n, n2);
        return this;
    }
    
    @Override
    public void close() {
    }
    
    @Override
    public void flush() {
    }
    
    @Override
    public String toString() {
        return this.a.toString();
    }
    
    @Override
    public void write(final String s) {
        if (s != null) {
            this.a.append(s);
        }
    }
    
    @Override
    public void write(final char[] array, final int n, final int n2) {
        if (array != null) {
            this.a.append(array, n, n2);
        }
    }
}
