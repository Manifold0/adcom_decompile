package com.facebook.ads.internal.p033b;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.facebook.ads.AdSettings.IntegrationErrorMode;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.internal.p025w.p044h.C2625a;
import com.facebook.ads.internal.p025w.p044h.C2626b;
import com.facebook.ads.internal.p050r.C2078a;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.C2065a;
import com.facebook.ads.internal.settings.AdInternalSettings;
import java.util.Locale;

/* renamed from: com.facebook.ads.internal.b.e */
public class C1954e {
    /* renamed from: a */
    public static IntegrationErrorMode m4636a(Context context) {
        IntegrationErrorMode integrationErrorMode = (IntegrationErrorMode) AdInternalSettings.f4776a.getSerializable("SRL_INTEGRATION_ERROR_MODE_KEY");
        if (integrationErrorMode == null) {
            integrationErrorMode = IntegrationErrorMode.INTEGRATION_ERROR_CRASH_DEBUG_MODE;
        }
        if (integrationErrorMode != IntegrationErrorMode.INTEGRATION_ERROR_CRASH_DEBUG_MODE) {
            return integrationErrorMode;
        }
        return ((context.getApplicationInfo().flags & 2) != 0 ? 1 : null) == null ? IntegrationErrorMode.INTEGRATION_ERROR_CALLBACK_MODE : integrationErrorMode;
    }

    @Nullable
    /* renamed from: a */
    public static C2065a m4637a(Context context, Integer... numArr) {
        String str;
        String str2;
        int i = 1;
        if (C2078a.aa(context)) {
            return null;
        }
        IntegrationErrorMode a = C1954e.m4636a(context);
        String format = String.format(Locale.US, AdErrorType.MISSING_DEPENDENCIES_ERROR.getDefaultErrorMessage(), new Object[]{AudienceNetworkAds.TAG});
        switch (a) {
            case INTEGRATION_ERROR_CRASH_DEBUG_MODE:
            case INTEGRATION_ERROR_CALLBACK_MODE:
                for (Integer intValue : numArr) {
                    switch (intValue.intValue()) {
                        case 0:
                            try {
                                str = "android.support.v4.content.LocalBroadcastManager";
                                try {
                                    Class.forName(LocalBroadcastManager.class.getName());
                                } catch (Throwable th) {
                                    str2 = str;
                                    Log.e(AudienceNetworkAds.TAG, "Dependency not found: " + str2);
                                    C2625a.m6738a(context, "api", C2626b.f6553r, new Exception("Missing dependency class: " + str2));
                                    i = 0;
                                }
                            } catch (Throwable th2) {
                                str2 = null;
                                Log.e(AudienceNetworkAds.TAG, "Dependency not found: " + str2);
                                C2625a.m6738a(context, "api", C2626b.f6553r, new Exception("Missing dependency class: " + str2));
                                i = 0;
                            }
                        case 1:
                            str = "android.support.v7.widget.RecyclerView";
                            Class.forName(RecyclerView.class.getName());
                            break;
                        default:
                            break;
                    }
                }
                if (i != 0) {
                    return null;
                }
                switch (a) {
                    case INTEGRATION_ERROR_CRASH_DEBUG_MODE:
                        throw new RuntimeException(format + ". You can change Integration Error mode by setting AdSettings.setIntegrationErrorMode()");
                    case INTEGRATION_ERROR_CALLBACK_MODE:
                        C2065a a2 = C2065a.m5036a(AdErrorType.MISSING_DEPENDENCIES_ERROR, format);
                        Log.e(AudienceNetworkAds.TAG, format);
                        C2625a.m6738a(context, "api", C2626b.f6553r, new Exception(format));
                        return a2;
                    default:
                        return null;
                }
            default:
                Log.e(AudienceNetworkAds.TAG, format);
                return null;
        }
    }
}
