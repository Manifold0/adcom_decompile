// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.o.j;

import com.kongregate.o.c.d;
import android.content.ContentValues;
import org.json.JSONObject;
import com.kongregate.o.e.a;
import android.database.Cursor;

public class b extends c
{
    private String f;
    
    public b() {
        this.f = "Guest_Game_Auth_Token";
    }
    
    public b(final Cursor cursor) {
        this.e = cursor.getLong(cursor.getColumnIndex("_id"));
        this.c = cursor.getString(cursor.getColumnIndex("username"));
        this.f = cursor.getString(cursor.getColumnIndex("game_auth_token"));
    }
    
    public static b a(final long n) {
        final b b = null;
        final Cursor query = com.kongregate.o.e.b.a().query("users", (String[])null, "_ID = ?", new String[] { Long.toString(n) }, (String)null, (String)null, (String)null);
        b b2 = b;
        if (query == null) {
            return b2;
        }
        b2 = b;
        try {
            if (query.moveToFirst()) {
                b2 = new b(query);
            }
            return b2;
        }
        finally {
            com.kongregate.o.e.a.a(query);
        }
    }
    
    public String a() {
        return this.f;
    }
    
    public void a(final String f) {
        this.f = f;
    }
    
    public void a(final JSONObject jsonObject, final String f) {
        this.e = jsonObject.optLong("id", 0L);
        this.c = jsonObject.optString("username", "Guest");
        if (f != null && !this.f()) {
            this.f = f;
        }
    }
    
    public void b() {
        final ContentValues contentValues = new ContentValues();
        contentValues.put("_id", Long.valueOf(this.e()));
        contentValues.put("username", this.c());
        if (this.f != null) {
            contentValues.put("game_auth_token", this.f);
        }
        com.kongregate.o.c.d.a(new Runnable() {
            @Override
            public void run() {
                com.kongregate.o.e.a.a(com.kongregate.o.e.b.a(), "users", contentValues);
            }
        });
    }
}
