// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.j;

import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

@VisibleForTesting
public abstract class f<T>
{
    private a a;
    
    protected void a(final a a) {
        this.a = a;
    }
    
    @Nullable
    abstract T b();
    
    public a c() {
        return this.a;
    }
    
    public enum a
    {
        a(9000, "An unknown error has occurred."), 
        b(3001, "Failed to read from database."), 
        c(3002, "Failed to insert row into database."), 
        d(3003, "Failed to update row in database."), 
        e(3004, "Failed to delete row from database.");
        
        private final int f;
        private final String g;
        
        private a(final int f, final String g) {
            this.f = f;
            this.g = g;
        }
        
        public int a() {
            return this.f;
        }
        
        public String b() {
            return this.g;
        }
    }
}
