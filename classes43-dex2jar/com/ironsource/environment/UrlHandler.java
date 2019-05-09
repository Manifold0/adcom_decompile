// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.environment;

import android.net.Uri;
import android.content.Intent;
import android.content.Context;

public class UrlHandler
{
    public static void openUrl(final Context context, final String s) throws Exception {
        if (s == null) {
            throw new Exception("url is null");
        }
        context.startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse(s)));
    }
}
