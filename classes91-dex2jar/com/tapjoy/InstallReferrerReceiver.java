// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import com.tapjoy.internal.gc;
import android.content.Intent;
import android.content.Context;
import com.tapjoy.internal.l;

public class InstallReferrerReceiver extends l
{
    @Override
    public void onReceive(final Context context, final Intent intent) {
        final String a = gc.a(context, intent);
        final int a2 = this.a(context, intent);
        if (!intent.getBooleanExtra("fiverocks:verify", false) || !this.isOrderedBroadcast()) {
            return;
        }
        this.setResultCode(a2 + 1);
        if (a == null) {
            return;
        }
        try {
            this.setResultData("http://play.google.com/store/apps/details?id=" + context.getPackageName() + "&referrer=" + URLEncoder.encode(a, "UTF-8"));
        }
        catch (UnsupportedEncodingException ex) {}
    }
}
