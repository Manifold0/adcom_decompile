package com.tapjoy;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.ironsource.sdk.constants.Constants.RequestParameters;
import java.lang.reflect.Method;

public class TapjoyAdIdClient {
    /* renamed from: a */
    private Context f6999a;
    /* renamed from: b */
    private String f7000b;
    /* renamed from: c */
    private boolean f7001c;

    public TapjoyAdIdClient(Context context) {
        this.f6999a = context;
    }

    public boolean setupAdIdInfo() {
        try {
            boolean z;
            Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.f6999a);
            this.f7000b = advertisingIdInfo.getId();
            if (advertisingIdInfo.isLimitAdTrackingEnabled()) {
                z = false;
            } else {
                z = true;
            }
            this.f7001c = z;
            return true;
        } catch (Exception e) {
            return false;
        } catch (Error e2) {
            return false;
        }
    }

    public boolean setupAdIdInfoReflection() {
        try {
            Class cls = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
            Method method = cls.getMethod("getAdvertisingIdInfo", new Class[]{Context.class});
            TapjoyLog.m7126d("TapjoyAdIdClient", "Found method: " + method);
            Object invoke = method.invoke(cls, new Object[]{this.f6999a});
            Method method2 = invoke.getClass().getMethod(RequestParameters.isLAT, new Class[0]);
            Method method3 = invoke.getClass().getMethod("getId", new Class[0]);
            this.f7001c = !((Boolean) method2.invoke(invoke, new Object[0])).booleanValue();
            this.f7000b = (String) method3.invoke(invoke, new Object[0]);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getAdvertisingId() {
        return this.f7000b;
    }

    public boolean isAdTrackingEnabled() {
        return this.f7001c;
    }
}
