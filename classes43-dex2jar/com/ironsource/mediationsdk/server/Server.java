// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.server;

import android.util.Log;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import org.json.JSONObject;
import com.ironsource.mediationsdk.logger.ThreadExceptionHandler;

public class Server
{
    public static void callAsyncRequestURL(final String s, final boolean b, final int n) {
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                callRequestURL(s, b, n);
            }
        }, "callAsyncRequestURL");
        thread.setUncaughtExceptionHandler((Thread.UncaughtExceptionHandler)new ThreadExceptionHandler());
        thread.start();
    }
    
    private static void callRequestURL(final String s, final boolean b, final int n) {
        try {
            new JSONObject(HttpFunctions.getStringFromURL(ServerURL.getRequestURL(s, b, n)));
            IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.NETWORK, "callRequestURL(reqUrl:" + s + ", " + "hit:" + b + ")", 1);
        }
        catch (Throwable t) {
            final StringBuilder sb = new StringBuilder("callRequestURL(reqUrl:");
            if (s == null) {
                sb.append("null");
            }
            else {
                sb.append(s);
            }
            sb.append(", hit:").append(b).append(")");
            IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.NETWORK, sb.toString() + ", e:" + Log.getStackTraceString(t), 0);
        }
    }
}
