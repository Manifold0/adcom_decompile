package com.ironsource.mediationsdk.logger;

import com.ironsource.mediationsdk.logger.IronSourceLogger.IronSourceTag;

public interface LogListener {
    void onLog(IronSourceTag ironSourceTag, String str, int i);
}
