// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import android.util.Log;
import com.applovin.sdk.AppLovinLogger;

class x implements AppLovinLogger
{
    private final AppLovinSdkImpl a;
    private y b;
    
    x(final AppLovinSdkImpl a) {
        this.a = a;
    }
    
    void a(final y b) {
        this.b = b;
    }
    
    boolean a() {
        return this.a.getSettings().isVerboseLoggingEnabled() || (this.a.getSettingsManager() != null && this.a.getSettingsManager().a(ea.l));
    }
    
    @Override
    public void d(final String s, final String s2) {
        if (this.a()) {
            Log.d("AppLovinSdk", "[" + s + "] " + s2);
        }
        if (this.b != null) {
            this.b.a("DEBUG  [" + s + "] " + s2);
        }
    }
    
    @Override
    public void e(final String s, final String s2) {
        this.e(s, s2, null);
    }
    
    @Override
    public void e(String string, final String s, final Throwable t) {
        if (this.a()) {
            Log.e("AppLovinSdk", "[" + string + "] " + s, t);
        }
        if (this.b != null) {
            final y b = this.b;
            final StringBuilder append = new StringBuilder().append("ERROR  [").append(string).append("] ").append(s);
            if (t != null) {
                string = ": " + t.getMessage();
            }
            else {
                string = "";
            }
            b.a(append.append(string).toString());
        }
    }
    
    @Override
    public void i(final String s, final String s2) {
        if (this.a()) {
            Log.i("AppLovinSdk", "[" + s + "] " + s2);
        }
        if (this.b != null) {
            this.b.a("INFO  [" + s + "] " + s2);
        }
    }
    
    @Override
    public void userError(final String s, final String s2) {
        this.userError(s, s2, null);
    }
    
    @Override
    public void userError(String string, final String s, final Throwable t) {
        Log.e("AppLovinSdk", "[" + string + "] " + s, t);
        if (this.b != null) {
            final y b = this.b;
            final StringBuilder append = new StringBuilder().append("USER  [").append(string).append("] ").append(s);
            if (t != null) {
                string = ": " + t.getMessage();
            }
            else {
                string = "";
            }
            b.a(append.append(string).toString());
        }
    }
    
    @Override
    public void w(final String s, final String s2) {
        this.w(s, s2, null);
    }
    
    @Override
    public void w(final String s, final String s2, final Throwable t) {
        if (this.a()) {
            Log.w("AppLovinSdk", "[" + s + "] " + s2, t);
        }
        if (this.b != null) {
            this.b.a("WARN  [" + s + "] " + s2);
        }
    }
}
