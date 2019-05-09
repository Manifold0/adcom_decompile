// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.o.j;

import android.os.Bundle;
import android.database.Cursor;
import android.provider.BaseColumns;

public class c implements BaseColumns
{
    public static final String a = "users";
    public static final String b = "username";
    String c;
    String d;
    long e;
    
    public c() {
        this.c = "Guest";
        this.d = "http://cdn4.kongcdn.com/assets/avatars/defaults/headbot.png";
        this.e = 0L;
    }
    
    public c(final Cursor cursor) {
        this.e = cursor.getLong(cursor.getColumnIndex("user_id"));
        this.c = cursor.getString(cursor.getColumnIndex("username"));
        this.d = cursor.getString(cursor.getColumnIndex("avatar"));
    }
    
    public c(final Bundle bundle) {
        this.c = bundle.getString("username");
        this.d = bundle.getString("avatar");
        this.e = bundle.getLong("user_id");
    }
    
    public String c() {
        return this.c;
    }
    
    public String d() {
        return this.d;
    }
    
    public long e() {
        return this.e;
    }
    
    public boolean f() {
        return this.e == 0L;
    }
}
