// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.internal.sdk;

import com.kongregate.o.c.d;
import android.net.Uri;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import com.kongregate.android.api.KongregateButton;
import android.view.View;
import com.kongregate.android.internal.util.j;
import com.kongregate.android.api.APIBootstrap;
import android.content.Context;
import com.kongregate.android.api.MobileServices;

public class q implements MobileServices
{
    private final Context a;
    
    public q(final Context context) {
        this.a = context.getApplicationContext();
    }
    
    @Override
    public void closeKongregateWindow(final Context context) {
        final NativeAPI nativeAPI = (NativeAPI)APIBootstrap.getInstance();
        if (nativeAPI != null) {
            nativeAPI.a(context);
            return;
        }
        j.c("Kongregate API not initialized. Unable to close panel.");
    }
    
    @Override
    public View getButton(final Context context) {
        final KongregateButton kongregateButton = new KongregateButton(context);
        kongregateButton.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-2, -2));
        return (View)kongregateButton;
    }
    
    @Override
    public Uri getOpenURL() {
        final NativeAPI nativeAPI = (NativeAPI)APIBootstrap.getInstance();
        if (nativeAPI != null) {
            return nativeAPI.w();
        }
        j.c("Kongregate API not initialized. Can't get open URL.");
        return null;
    }
    
    @Override
    public void openKongregateWindow(final Context context) {
        this.openKongregateWindow(context, null);
    }
    
    @Override
    public void openKongregateWindow(final Context context, final String s) {
        this.openKongregateWindow(context, s, null);
    }
    
    @Override
    public void openKongregateWindow(final Context context, final String s, final String s2) {
        try {
            final NativeAPI nativeAPI = (NativeAPI)APIBootstrap.getInstance();
            if (nativeAPI != null && NativeAPI.g().c() && nativeAPI.isReady()) {
                nativeAPI.a(context, s, s2);
                return;
            }
            j.c("Kongregate API not initialized or panel not supported");
        }
        catch (RuntimeException ex) {
            d.a(ex);
            throw ex;
        }
    }
    
    @Override
    public void trackPurchase(final String s) {
        final NativeAPI nativeAPI = (NativeAPI)APIBootstrap.getInstance();
        if (nativeAPI != null) {
            nativeAPI.analytics().trackPurchase(s);
            return;
        }
        j.c("Kongregate API not initialized when attempting to track purchase");
    }
    
    @Override
    public void trigger(final String s) {
        final NativeAPI nativeAPI = (NativeAPI)APIBootstrap.getInstance();
        if (nativeAPI != null) {
            nativeAPI.d(s);
            return;
        }
        j.c("Kongregate API not initializing. Trigger event not sent: " + s);
    }
}
