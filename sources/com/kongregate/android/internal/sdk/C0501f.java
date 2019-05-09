package com.kongregate.android.internal.sdk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import com.kongregate.android.api.KongregateServices;
import com.kongregate.android.api.StatServices;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.kongregate.android.internal.sdk.f */
final class C0501f extends C0497d {
    /* renamed from: w */
    private volatile C0529s f515w;
    /* renamed from: x */
    private Runnable f516x = null;

    /* renamed from: com.kongregate.android.internal.sdk.f$1 */
    class C04991 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C0501f f510a;

        C04991(C0501f c0501f) {
            this.f510a = c0501f;
        }

        public void run() {
        }
    }

    /* renamed from: com.kongregate.android.internal.sdk.f$a */
    public enum C0500a {
        UNINITIALIZED,
        NOT_FOUND,
        CONNECTED
    }

    C0501f(Context context, long j, String str) {
        super(context, j, str);
        Log.i(C0498e.f484a, "initialized - client SDK version (1) build 1");
        this.f515w = null;
        this.f515w = new C0529s(this.c);
        m437f();
    }

    /* renamed from: a */
    protected KongregateServices mo1150a() {
        return null;
    }

    /* renamed from: b */
    protected StatServices mo1151b() {
        return null;
    }

    /* renamed from: k */
    protected Intent mo1181k() {
        return new Intent("com.kongregate.android.arcade.action.KONG_SDK");
    }

    /* renamed from: b */
    protected void mo1180b(Message message) {
        Log.d(C0498e.f484a, "Received Message: " + message.toString() + " thread: " + Thread.currentThread());
        switch (message.what) {
            case 3:
                if (message.obj != null) {
                    Log.i(C0498e.f484a, "current user: " + ((Bundle) message.obj).getString("username"));
                    Bundle bundle = (Bundle) message.obj;
                    this.j.post(new C04991(this));
                    return;
                }
                return;
            case 8:
                if (message.obj != null) {
                    m443a(((Bundle) message.obj).getStringArrayList("name"));
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private void m443a(List<String> list) {
        if (this.f515w != null) {
            this.f515w.m547a(list);
        }
    }

    /* renamed from: a */
    public void mo1179a(Bundle bundle) {
        if (this.f515w != null) {
            ArrayList a = this.f515w.m546a();
            if (!a.isEmpty()) {
                bundle.putParcelableArrayList(C0498e.f495l, a);
            }
        }
    }
}
