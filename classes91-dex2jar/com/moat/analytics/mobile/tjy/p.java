// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import android.util.Log;
import android.webkit.ValueCallback;

class p implements ValueCallback
{
    final /* synthetic */ n a;
    
    p(final n a) {
        this.a = a;
    }
    
    public void a(String string) {
        if (string == null || string.equalsIgnoreCase("null") || string.equalsIgnoreCase("false")) {
            if (this.a.d.b()) {
                final StringBuilder sb = new StringBuilder("Received value is:");
                if (string == null) {
                    string = "null";
                }
                else {
                    string = "(String)" + string;
                }
                Log.d("MoatJavaScriptBridge", sb.append(string).toString());
            }
            if (this.a.e == -1 || this.a.e == 50) {
                this.a.g();
            }
            this.a.e++;
        }
        else {
            if (string.equalsIgnoreCase("true")) {
                this.a.e = -1;
                this.a.e();
                return;
            }
            if (this.a.d.b()) {
                Log.d("MoatJavaScriptBridge", "Received unusual value from Javascript:" + string);
            }
        }
    }
}
