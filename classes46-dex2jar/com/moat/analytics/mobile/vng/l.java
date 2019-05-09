// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.vng;

import org.json.JSONArray;
import android.os.Build$VERSION;
import org.json.JSONObject;

class l
{
    private boolean a;
    private boolean b;
    private boolean c;
    private int d;
    
    l(final String s) {
        this.a = false;
        this.b = false;
        this.c = false;
        this.d = 200;
        this.a(s);
    }
    
    private void a(final String s) {
        if (s != null) {
            try {
                final JSONObject jsonObject = new JSONObject(s);
                final String string = jsonObject.getString("sa");
                final boolean equals = string.equals("3f2ae9c1894282b5e0222f0d06bbf457191f816f");
                final boolean equals2 = string.equals("8f1d08a2d6496191a5ebae8f0590f513e2619489");
                if ((string.equals("on") || equals || equals2) && !this.a(jsonObject) && !this.b(jsonObject)) {
                    this.a = true;
                    this.b = equals;
                    this.c = equals2;
                    if (this.c) {
                        this.b = true;
                    }
                }
                if (jsonObject.has("in")) {
                    final int int1 = jsonObject.getInt("in");
                    if (int1 >= 100 && int1 <= 1000) {
                        this.d = int1;
                    }
                }
                if (this.c(jsonObject)) {
                    ((k)MoatAnalytics.getInstance()).c = true;
                }
            }
            catch (Exception ex) {
                this.a = false;
                this.b = false;
                this.d = 200;
                m.a(ex);
            }
        }
    }
    
    private boolean a(final JSONObject jsonObject) {
        try {
            if (16 > Build$VERSION.SDK_INT) {
                return true;
            }
            if (jsonObject.has("ob")) {
                final JSONArray jsonArray = jsonObject.getJSONArray("ob");
                for (int length = jsonArray.length(), i = 0; i < length; ++i) {
                    if (jsonArray.getInt(i) == Build$VERSION.SDK_INT) {
                        return true;
                    }
                }
            }
            return false;
        }
        catch (Exception ex) {}
        return true;
    }
    
    private boolean b(final JSONObject jsonObject) {
        boolean b = false;
        try {
            if (jsonObject.has("ap")) {
                final String b2 = new s.a().b();
                final JSONArray jsonArray = jsonObject.getJSONArray("ap");
                final int length = jsonArray.length();
                int n = 0;
                while (true) {
                    b = b;
                    if (n >= length) {
                        break;
                    }
                    if (jsonArray.getString(n).contentEquals(b2)) {
                        b = true;
                        break;
                    }
                    ++n;
                }
            }
            return b;
        }
        catch (Exception ex) {
            m.a(ex);
            return false;
        }
    }
    
    private boolean c(final JSONObject jsonObject) {
        boolean b = false;
        try {
            if (jsonObject.has("al")) {
                final String b2 = new s.a().b();
                final JSONArray jsonArray = jsonObject.getJSONArray("al");
                final int length = jsonArray.length();
                int n = 0;
                while (true) {
                    b = b;
                    if (n >= length) {
                        break;
                    }
                    if (jsonArray.getString(n).contentEquals(b2)) {
                        b = true;
                        break;
                    }
                    ++n;
                }
            }
            return b;
        }
        catch (Exception ex) {
            m.a(ex);
            return false;
        }
    }
    
    boolean a() {
        return this.b;
    }
    
    boolean b() {
        return this.c;
    }
    
    int c() {
        return this.d;
    }
    
    w.d d() {
        if (this.a) {
            return w.d.b;
        }
        return w.d.a;
    }
}
