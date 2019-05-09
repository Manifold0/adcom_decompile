// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy;

import java.lang.reflect.Method;
import com.google.android.gms.ads.identifier.AdvertisingIdClient$Info;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import android.content.Context;

public class TapjoyAdIdClient
{
    private Context a;
    private String b;
    private boolean c;
    
    public TapjoyAdIdClient(final Context a) {
        this.a = a;
    }
    
    public String getAdvertisingId() {
        return this.b;
    }
    
    public boolean isAdTrackingEnabled() {
        return this.c;
    }
    
    public boolean setupAdIdInfo() {
        try {
            final AdvertisingIdClient$Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.a);
            this.b = advertisingIdInfo.getId();
            this.c = !advertisingIdInfo.isLimitAdTrackingEnabled();
            return true;
        }
        catch (Exception ex) {
            return false;
        }
        catch (Error error) {
            return false;
        }
    }
    
    public boolean setupAdIdInfoReflection() {
        try {
            final Class<?> forName = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
            final Method method = forName.getMethod("getAdvertisingIdInfo", Context.class);
            TapjoyLog.d("TapjoyAdIdClient", "Found method: " + method);
            final Object invoke = method.invoke(forName, this.a);
            final Method method2 = invoke.getClass().getMethod("isLimitAdTrackingEnabled", (Class<?>[])new Class[0]);
            final Method method3 = invoke.getClass().getMethod("getId", (Class<?>[])new Class[0]);
            this.c = !(boolean)method2.invoke(invoke, new Object[0]);
            this.b = (String)method3.invoke(invoke, new Object[0]);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
}
