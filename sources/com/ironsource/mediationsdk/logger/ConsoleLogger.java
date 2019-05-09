package com.ironsource.mediationsdk.logger;

import android.util.Log;
import com.ironsource.mediationsdk.logger.IronSourceLogger.IronSourceTag;
import com.ironsource.sdk.constants.Constants.RequestParameters;

public class ConsoleLogger extends IronSourceLogger {
    public static final String NAME = "console";

    private ConsoleLogger() {
        super(NAME);
    }

    public ConsoleLogger(int debugLevel) {
        super(NAME, debugLevel);
    }

    public void log(IronSourceTag tag, String message, int logLevel) {
        switch (logLevel) {
            case 0:
                Log.v("" + tag, message);
                return;
            case 1:
                Log.i("" + tag, message);
                return;
            case 2:
                Log.w("" + tag, message);
                return;
            case 3:
                Log.e("" + tag, message);
                return;
            default:
                return;
        }
    }

    public void logException(IronSourceTag tag, String message, Throwable e) {
        log(tag, message + ":stacktrace[" + Log.getStackTraceString(e) + RequestParameters.RIGHT_BRACKETS, 3);
    }
}
