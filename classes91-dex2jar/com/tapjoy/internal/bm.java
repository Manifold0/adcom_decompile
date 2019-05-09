// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.Map;
import java.util.Collection;
import java.io.IOException;
import java.io.Writer;
import java.io.StringWriter;

public final class bm implements bq
{
    private final StringWriter a;
    private final by b;
    
    public bm() {
        this.a = new StringWriter();
        this.b = new by(this.a);
    }
    
    public static String a(final Object o) {
        return new bm().b(o).toString();
    }
    
    private bm b(final Object o) {
        try {
            this.b.a(o);
            return this;
        }
        catch (IOException ex) {
            throw cu.a(ex);
        }
    }
    
    public final bm a() {
        try {
            this.b.a();
            return this;
        }
        catch (IOException ex) {
            throw cu.a(ex);
        }
    }
    
    public final bm a(final long n) {
        try {
            this.b.a(n);
            return this;
        }
        catch (IOException ex) {
            throw cu.a(ex);
        }
    }
    
    public final bm a(final bq bq) {
        try {
            this.b.a(bq);
            return this;
        }
        catch (IOException ex) {
            throw cu.a(ex);
        }
    }
    
    public final bm a(final Number n) {
        try {
            this.b.a(n);
            return this;
        }
        catch (IOException ex) {
            throw cu.a(ex);
        }
    }
    
    public final bm a(final String s) {
        try {
            this.b.a(s);
            return this;
        }
        catch (IOException ex) {
            throw cu.a(ex);
        }
    }
    
    public final bm a(final Collection collection) {
        try {
            this.b.a(collection);
            return this;
        }
        catch (IOException ex) {
            throw cu.a(ex);
        }
    }
    
    public final bm a(final Map map) {
        try {
            this.b.a(map);
            return this;
        }
        catch (IOException ex) {
            throw cu.a(ex);
        }
    }
    
    @Override
    public final void a(final Writer writer) {
        try {
            this.b.a.flush();
            writer.write(this.a.toString());
        }
        catch (IOException ex) {
            throw cu.a(ex);
        }
    }
    
    public final bm b() {
        try {
            this.b.b();
            return this;
        }
        catch (IOException ex) {
            throw cu.a(ex);
        }
    }
    
    public final bm b(final String s) {
        try {
            this.b.b(s);
            return this;
        }
        catch (IOException ex) {
            throw cu.a(ex);
        }
    }
    
    public final bm c() {
        try {
            this.b.c();
            return this;
        }
        catch (IOException ex) {
            throw cu.a(ex);
        }
    }
    
    public final bm d() {
        try {
            this.b.d();
            return this;
        }
        catch (IOException ex) {
            throw cu.a(ex);
        }
    }
    
    @Override
    public final String toString() {
        try {
            this.b.a.flush();
            return this.a.toString();
        }
        catch (IOException ex) {
            throw cu.a(ex);
        }
    }
}
