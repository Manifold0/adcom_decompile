// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.internal.sdk;

import android.content.Intent;
import android.os.Message;
import com.kongregate.android.api.StatServices;
import java.util.ArrayList;
import android.os.Bundle;
import com.kongregate.android.api.KongregateServices;
import java.util.List;
import android.util.Log;
import android.content.Context;

final class f extends d
{
    private volatile s w;
    private Runnable x;
    
    f(final Context context, final long n, final String s) {
        super(context, n, s);
        this.x = null;
        Log.i("KongSDK", "initialized - client SDK version (1) build 1");
        this.w = null;
        this.w = new s(this.c);
        this.f();
    }
    
    private void a(final List<String> list) {
        if (this.w != null) {
            this.w.a(list);
        }
    }
    
    @Override
    protected KongregateServices a() {
        return null;
    }
    
    @Override
    public void a(final Bundle bundle) {
        if (this.w != null) {
            final ArrayList<Bundle> a = this.w.a();
            if (!a.isEmpty()) {
                bundle.putParcelableArrayList("stat_cache", (ArrayList)a);
            }
        }
    }
    
    @Override
    protected StatServices b() {
        return null;
    }
    
    @Override
    protected void b(final Message message) {
        Log.d("KongSDK", "Received Message: " + message.toString() + " thread: " + Thread.currentThread());
        switch (message.what) {
            case 3: {
                if (message.obj != null) {
                    Log.i("KongSDK", "current user: " + ((Bundle)message.obj).getString("username"));
                    final Bundle bundle = (Bundle)message.obj;
                    this.j.post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                        }
                    });
                    return;
                }
                break;
            }
            case 8: {
                if (message.obj != null) {
                    this.a(((Bundle)message.obj).getStringArrayList("name"));
                    return;
                }
                break;
            }
        }
    }
    
    @Override
    protected Intent k() {
        return new Intent("com.kongregate.android.arcade.action.KONG_SDK");
    }
    
    public enum a
    {
        a, 
        b, 
        c;
    }
}
