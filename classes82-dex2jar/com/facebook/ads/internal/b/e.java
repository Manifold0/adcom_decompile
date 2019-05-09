// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.b;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import com.facebook.ads.internal.w.h.b;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.facebook.ads.internal.protocol.AdErrorType;
import java.util.Locale;
import com.facebook.ads.internal.protocol.a;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.AdSettings;
import android.content.Context;

public class e
{
    public static AdSettings.IntegrationErrorMode a(final Context context) {
        Enum<AdSettings.IntegrationErrorMode> integration_ERROR_CRASH_DEBUG_MODE;
        if ((integration_ERROR_CRASH_DEBUG_MODE = (AdSettings.IntegrationErrorMode)AdInternalSettings.a.getSerializable("SRL_INTEGRATION_ERROR_MODE_KEY")) == null) {
            integration_ERROR_CRASH_DEBUG_MODE = AdSettings.IntegrationErrorMode.INTEGRATION_ERROR_CRASH_DEBUG_MODE;
        }
        Enum<AdSettings.IntegrationErrorMode> integration_ERROR_CALLBACK_MODE;
        if ((integration_ERROR_CALLBACK_MODE = integration_ERROR_CRASH_DEBUG_MODE) == AdSettings.IntegrationErrorMode.INTEGRATION_ERROR_CRASH_DEBUG_MODE) {
            int n;
            if ((context.getApplicationInfo().flags & 0x2) != 0x0) {
                n = 1;
            }
            else {
                n = 0;
            }
            integration_ERROR_CALLBACK_MODE = integration_ERROR_CRASH_DEBUG_MODE;
            if (n == 0) {
                integration_ERROR_CALLBACK_MODE = AdSettings.IntegrationErrorMode.INTEGRATION_ERROR_CALLBACK_MODE;
            }
        }
        return (AdSettings.IntegrationErrorMode)integration_ERROR_CALLBACK_MODE;
    }
    
    @Nullable
    public static a a(final Context context, final Integer... array) {
        boolean b = true;
        if (com.facebook.ads.internal.r.a.aa(context)) {
            return null;
        }
        final AdSettings.IntegrationErrorMode a = a(context);
        final String format = String.format(Locale.US, AdErrorType.MISSING_DEPENDENCIES_ERROR.getDefaultErrorMessage(), "FBAudienceNetwork");
        switch (e$1.a[a.ordinal()]) {
            default: {
                Log.e("FBAudienceNetwork", format);
                return null;
            }
            case 1:
            case 2: {
                for (int length = array.length, i = 0; i < length; ++i) {
                    switch (array[i]) {
                        case 0: {
                            final String s = "android.support.v4.content.LocalBroadcastManager";
                            try {
                                Class.forName(LocalBroadcastManager.class.getName());
                            }
                            catch (Throwable t) {
                                Log.e("FBAudienceNetwork", "Dependency not found: " + s);
                                com.facebook.ads.internal.w.h.a.a(context, "api", com.facebook.ads.internal.w.h.b.r, new Exception("Missing dependency class: " + s));
                                b = false;
                            }
                            break;
                        }
                        case 1: {
                            Class.forName(RecyclerView.class.getName());
                            break;
                        }
                    }
                }
                if (b) {
                    return null;
                }
                switch (e$1.a[a.ordinal()]) {
                    default: {
                        return null;
                    }
                    case 1: {
                        throw new RuntimeException(format + ". You can change Integration Error mode by setting AdSettings.setIntegrationErrorMode()");
                    }
                    case 2: {
                        final a a2 = com.facebook.ads.internal.protocol.a.a(AdErrorType.MISSING_DEPENDENCIES_ERROR, format);
                        Log.e("FBAudienceNetwork", format);
                        com.facebook.ads.internal.w.h.a.a(context, "api", com.facebook.ads.internal.w.h.b.r, new Exception(format));
                        return a2;
                    }
                }
                break;
            }
        }
    }
}
