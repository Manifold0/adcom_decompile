package com.tapjoy;

import android.content.Context;
import android.content.Intent;
import com.tapjoy.internal.C2776l;
import com.tapjoy.internal.gc;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class InstallReferrerReceiver extends C2776l {
    public void onReceive(Context context, Intent intent) {
        String a = gc.m7833a(context, intent);
        int a2 = m6987a(context, intent);
        if (intent.getBooleanExtra("fiverocks:verify", false) && isOrderedBroadcast()) {
            setResultCode(a2 + 1);
            if (a != null) {
                try {
                    setResultData("http://play.google.com/store/apps/details?id=" + context.getPackageName() + "&referrer=" + URLEncoder.encode(a, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                }
            }
        }
    }
}
