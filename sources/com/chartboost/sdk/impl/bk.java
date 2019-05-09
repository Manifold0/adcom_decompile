package com.chartboost.sdk.impl;

import java.io.Serializable;
import java.io.Writer;

public class bk extends Writer implements Serializable {
    /* renamed from: a */
    private final StringBuilder f3204a;

    public bk() {
        this.f3204a = new StringBuilder();
    }

    public bk(int i) {
        this.f3204a = new StringBuilder(i);
    }

    public Writer append(char value) {
        this.f3204a.append(value);
        return this;
    }

    public Writer append(CharSequence value) {
        this.f3204a.append(value);
        return this;
    }

    public Writer append(CharSequence value, int start, int end) {
        this.f3204a.append(value, start, end);
        return this;
    }

    public void close() {
    }

    public void flush() {
    }

    public void write(String value) {
        if (value != null) {
            this.f3204a.append(value);
        }
    }

    public void write(char[] value, int offset, int length) {
        if (value != null) {
            this.f3204a.append(value, offset, length);
        }
    }

    public String toString() {
        return this.f3204a.toString();
    }
}
