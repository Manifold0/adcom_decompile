// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import android.content.Intent;
import com.applovin.adview.AppLovinConfirmationActivity;

class bf implements Runnable
{
    final /* synthetic */ be a;
    
    bf(final be a) {
        this.a = a;
    }
    
    @Override
    public void run() {
        final String s = this.a.a.get(ea.Z);
        final String b = this.a.b();
        final String s2 = this.a.a.get(ea.ae);
        if (ab.a(AppLovinConfirmationActivity.class, this.a.c)) {
            try {
                final Intent intent = new Intent(this.a.c, (Class)AppLovinConfirmationActivity.class);
                intent.putExtra("dialog_title", s);
                intent.putExtra("dialog_body", b);
                intent.putExtra("dialog_button_text", s2);
                this.a.c.startActivity(intent);
                return;
            }
            catch (Throwable t) {
                this.a.a(b, t);
                return;
            }
        }
        this.a.a(b, null);
    }
}
