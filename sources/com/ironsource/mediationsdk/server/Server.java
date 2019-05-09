package com.ironsource.mediationsdk.server;

import android.util.Log;
import com.ironsource.mediationsdk.logger.IronSourceLogger.IronSourceTag;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.logger.ThreadExceptionHandler;
import org.json.JSONObject;

public class Server {
    private static void callRequestURL(String requestUrl, boolean hit, int placementId) {
        try {
            JSONObject obj = new JSONObject(HttpFunctions.getStringFromURL(ServerURL.getRequestURL(requestUrl, hit, placementId)));
            IronSourceLoggerManager.getLogger().log(IronSourceTag.NETWORK, "callRequestURL(reqUrl:" + requestUrl + ", " + "hit:" + hit + ")", 1);
        } catch (Throwable e) {
            StringBuilder builder = new StringBuilder("callRequestURL(reqUrl:");
            if (requestUrl == null) {
                builder.append("null");
            } else {
                builder.append(requestUrl);
            }
            builder.append(", hit:").append(hit).append(")");
            IronSourceLoggerManager.getLogger().log(IronSourceTag.NETWORK, builder.toString() + ", e:" + Log.getStackTraceString(e), 0);
        }
    }

    public static void callAsyncRequestURL(final String requestUrl, final boolean hit, final int placementId) {
        Thread asyncRequestURL = new Thread(new Runnable() {
            public void run() {
                Server.callRequestURL(requestUrl, hit, placementId);
            }
        }, "callAsyncRequestURL");
        asyncRequestURL.setUncaughtExceptionHandler(new ThreadExceptionHandler());
        asyncRequestURL.start();
    }
}
