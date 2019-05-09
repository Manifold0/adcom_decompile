package com.applovin.impl.sdk;

import android.content.Intent;
import com.applovin.adview.AppLovinConfirmationActivity;

class bf implements Runnable {
    /* renamed from: a */
    final /* synthetic */ be f2136a;

    bf(be beVar) {
        this.f2136a = beVar;
    }

    public void run() {
        String str = (String) this.f2136a.f2133a.get(ea.f2402Z);
        String b = this.f2136a.m2354b();
        String str2 = (String) this.f2136a.f2133a.get(ea.ae);
        if (ab.m2198a(AppLovinConfirmationActivity.class, this.f2136a.f2135c)) {
            try {
                Intent intent = new Intent(this.f2136a.f2135c, AppLovinConfirmationActivity.class);
                intent.putExtra("dialog_title", str);
                intent.putExtra("dialog_body", b);
                intent.putExtra("dialog_button_text", str2);
                this.f2136a.f2135c.startActivity(intent);
                return;
            } catch (Throwable th) {
                this.f2136a.m2353a(b, th);
                return;
            }
        }
        this.f2136a.m2353a(b, null);
    }
}
