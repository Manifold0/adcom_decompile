// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import android.text.TextUtils;

class ft implements af<Object>
{
    final /* synthetic */ String a;
    final /* synthetic */ AppLovinSdkImpl b;
    final /* synthetic */ fs c;
    
    ft(final fs c, final String a, final AppLovinSdkImpl b) {
        this.c = c;
        this.a = a;
        this.b = b;
    }
    
    @Override
    public void a(final int n) {
        boolean b = true;
        boolean b2;
        if (n < 200 || n >= 500) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        if (n == -103) {
            b = false;
        }
        if (!b2 || !b) {
            this.c.a(n);
            return;
        }
        if (this.c.o > 0) {
            this.c.e.w(this.a, "Unable to send request due to server failure (code " + n + "). " + this.c.o + " attempts left, retrying in " + this.c.p / 1000.0 + " seconds...");
            --this.c.o;
            if (this.c.o == 0) {
                this.c.c(this.c.q);
                if (!TextUtils.isEmpty((CharSequence)this.c.k) && this.c.k.length() >= 4) {
                    this.c.j = this.c.k;
                    this.c.e.i(this.c.a(), "Switching to backup endpoint " + this.c.k);
                }
            }
            this.b.getTaskManager().a(this.c, fe.b, this.c.p);
            return;
        }
        if (this.c.k != null && this.c.k.equals(this.c.j)) {
            this.c.c(this.c.r);
        }
        else {
            this.c.c(this.c.q);
        }
        this.c.a(n);
    }
    
    @Override
    public void a(final Object o, final int n) {
        this.c.o = 0;
        this.c.a(o, n);
    }
}
