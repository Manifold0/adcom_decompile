package com.kongregate.android.internal.sdk;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import com.kongregate.android.api.APIBootstrap;
import com.kongregate.android.api.KongregateButton;
import com.kongregate.android.api.MobileServices;
import com.kongregate.android.internal.util.C0562j;
import com.kongregate.p000o.p006c.C0626d;

/* renamed from: com.kongregate.android.internal.sdk.q */
public class C0528q implements MobileServices {
    /* renamed from: a */
    private final Context f670a;

    public C0528q(Context context) {
        this.f670a = context.getApplicationContext();
    }

    public View getButton(Context context) {
        View kongregateButton = new KongregateButton(context);
        kongregateButton.setLayoutParams(new LayoutParams(-2, -2));
        return kongregateButton;
    }

    public void openKongregateWindow(Context context) {
        openKongregateWindow(context, null);
    }

    public void openKongregateWindow(Context context, String str) {
        openKongregateWindow(context, str, null);
    }

    public void openKongregateWindow(Context context, String str, String str2) {
        try {
            NativeAPI nativeAPI = (NativeAPI) APIBootstrap.getInstance();
            if (nativeAPI != null && NativeAPI.m351g().mo1253c() && nativeAPI.isReady()) {
                nativeAPI.m375a(context, str, str2);
            } else {
                C0562j.m759c("Kongregate API not initialized or panel not supported");
            }
        } catch (Throwable e) {
            C0626d.m966a(e);
            throw e;
        }
    }

    public void closeKongregateWindow(Context context) {
        NativeAPI nativeAPI = (NativeAPI) APIBootstrap.getInstance();
        if (nativeAPI != null) {
            nativeAPI.m374a(context);
        } else {
            C0562j.m759c("Kongregate API not initialized. Unable to close panel.");
        }
    }

    public void trackPurchase(String str) {
        NativeAPI nativeAPI = (NativeAPI) APIBootstrap.getInstance();
        if (nativeAPI != null) {
            nativeAPI.analytics().trackPurchase(str);
        } else {
            C0562j.m759c("Kongregate API not initialized when attempting to track purchase");
        }
    }

    public Uri getOpenURL() {
        NativeAPI nativeAPI = (NativeAPI) APIBootstrap.getInstance();
        if (nativeAPI != null) {
            return nativeAPI.m412w();
        }
        C0562j.m759c("Kongregate API not initialized. Can't get open URL.");
        return null;
    }

    public void trigger(String str) {
        NativeAPI nativeAPI = (NativeAPI) APIBootstrap.getInstance();
        if (nativeAPI != null) {
            nativeAPI.m390d(str);
        } else {
            C0562j.m759c("Kongregate API not initializing. Trigger event not sent: " + str);
        }
    }
}
