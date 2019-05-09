// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.internal.sdk;

import android.net.Uri;
import com.kongregate.android.api.StatServices;
import com.kongregate.android.api.KongregateServices;
import java.util.List;
import android.os.IBinder;
import java.util.Map;
import android.app.Activity;
import com.kongregate.android.api.MicrotransactionServices;
import com.kongregate.android.api.MobileServices;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Message;
import com.kongregate.android.api.AnalyticsServices;
import com.kongregate.android.api.KongregateEventListener;
import com.kongregate.android.api.KongregateEventBundleListener;
import android.os.Bundle;
import android.content.Context;

public class h extends d
{
    h(final Context context, final long n, final String s) {
        super(context, n, s);
        this.f();
    }
    
    @Override
    protected void b(final Message message) {
    }
    
    @Override
    protected void h() {
        synchronized (this) {
            this.c.startService(this.k());
            super.h();
        }
    }
    
    @Override
    protected Intent k() {
        final Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.kongregate.android.backpack", "com.kongregate.android.backpack.service.KongregateService"));
        return intent;
    }
}
