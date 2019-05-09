// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.internal.sdk;

import android.os.Bundle;
import android.database.Cursor;

class j
{
    private String a;
    private long b;
    private long c;
    private long d;
    private long e;
    
    j(final Cursor cursor) {
        this.b = 0L;
        this.c = Long.MAX_VALUE;
        this.d = 0L;
        this.e = 0L;
        this.a(cursor);
    }
    
    private void a(final Cursor cursor) {
        this.a = cursor.getString(cursor.getColumnIndex("name"));
        this.c = cursor.getLong(cursor.getColumnIndex("min_value"));
        this.b = cursor.getLong(cursor.getColumnIndex("max_value"));
        this.d = cursor.getLong(cursor.getColumnIndex("add_value"));
        this.e = cursor.getLong(cursor.getColumnIndex("replace_value"));
    }
    
    public Bundle a() {
        final Bundle bundle = new Bundle(2);
        bundle.putString("name", this.a);
        final StringBuilder sb = new StringBuilder();
        sb.append(this.c).append(",");
        sb.append(this.b).append(",");
        sb.append(this.d).append(",");
        sb.append(this.e);
        bundle.putString("content", sb.toString());
        return bundle;
    }
}
