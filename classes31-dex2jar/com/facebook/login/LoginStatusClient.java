// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.login;

import android.os.Bundle;
import android.content.Context;
import com.facebook.internal.PlatformServiceClient;

final class LoginStatusClient extends PlatformServiceClient
{
    static final long DEFAULT_TOAST_DURATION_MS = 5000L;
    private final String graphApiVersion;
    private final String loggerRef;
    private final long toastDurationMs;
    
    LoginStatusClient(final Context context, final String s, final String loggerRef, final String graphApiVersion, final long toastDurationMs) {
        super(context, 65546, 65547, 20170411, s);
        this.loggerRef = loggerRef;
        this.graphApiVersion = graphApiVersion;
        this.toastDurationMs = toastDurationMs;
    }
    
    @Override
    protected void populateRequestBundle(final Bundle bundle) {
        bundle.putString("com.facebook.platform.extra.LOGGER_REF", this.loggerRef);
        bundle.putString("com.facebook.platform.extra.GRAPH_API_VERSION", this.graphApiVersion);
        bundle.putLong("com.facebook.platform.extra.EXTRA_TOAST_DURATION_MS", this.toastDurationMs);
    }
}
