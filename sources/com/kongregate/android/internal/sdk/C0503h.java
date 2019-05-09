package com.kongregate.android.internal.sdk;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import com.kongregate.android.api.AnalyticsServices;
import com.kongregate.android.api.KongregateEventBundleListener;
import com.kongregate.android.api.KongregateEventListener;
import com.kongregate.android.api.KongregateServices;
import com.kongregate.android.api.MicrotransactionServices;
import com.kongregate.android.api.MobileServices;
import com.kongregate.android.api.StatServices;
import java.util.List;

/* renamed from: com.kongregate.android.internal.sdk.h */
public class C0503h extends C0497d {
    /* renamed from: a */
    public /* bridge */ /* synthetic */ void mo1179a(Bundle bundle) {
        super.mo1179a(bundle);
    }

    public /* bridge */ /* synthetic */ void addEventBundleListener(KongregateEventBundleListener kongregateEventBundleListener) {
        super.addEventBundleListener(kongregateEventBundleListener);
    }

    public /* bridge */ /* synthetic */ void addEventListener(KongregateEventListener kongregateEventListener) {
        super.addEventListener(kongregateEventListener);
    }

    public /* bridge */ /* synthetic */ AnalyticsServices analytics() {
        return super.analytics();
    }

    public /* bridge */ /* synthetic */ String getApiKey() {
        return super.getApiKey();
    }

    public /* bridge */ /* synthetic */ Context getApplicationContext() {
        return super.getApplicationContext();
    }

    public /* bridge */ /* synthetic */ long getApplicationId() {
        return super.getApplicationId();
    }

    public /* bridge */ /* synthetic */ boolean isReady() {
        return super.isReady();
    }

    public /* bridge */ /* synthetic */ MobileServices mobile() {
        return super.mobile();
    }

    public /* bridge */ /* synthetic */ MicrotransactionServices mtx() {
        return super.mtx();
    }

    public /* bridge */ /* synthetic */ void onCreate(Activity activity, Bundle bundle) {
        super.onCreate(activity, bundle);
    }

    public /* bridge */ /* synthetic */ void onDestroy(Activity activity) {
        super.onDestroy(activity);
    }

    public /* bridge */ /* synthetic */ void onLowMemory() {
        super.onLowMemory();
    }

    public /* bridge */ /* synthetic */ void onPause(Activity activity) {
        super.onPause(activity);
    }

    public /* bridge */ /* synthetic */ void onResume(Activity activity) {
        super.onResume(activity);
    }

    public /* bridge */ /* synthetic */ void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        super.onServiceConnected(componentName, iBinder);
    }

    public /* bridge */ /* synthetic */ void onServiceDisconnected(ComponentName componentName) {
        super.onServiceDisconnected(componentName);
    }

    public /* bridge */ /* synthetic */ List pollEventBundles() {
        return super.pollEventBundles();
    }

    public /* bridge */ /* synthetic */ List pollEvents() {
        return super.pollEvents();
    }

    public /* bridge */ /* synthetic */ void removeEventBundleListener(KongregateEventBundleListener kongregateEventBundleListener) {
        super.removeEventBundleListener(kongregateEventBundleListener);
    }

    public /* bridge */ /* synthetic */ void removeEventListener(KongregateEventListener kongregateEventListener) {
        super.removeEventListener(kongregateEventListener);
    }

    public /* bridge */ /* synthetic */ KongregateServices services() {
        return super.services();
    }

    public /* bridge */ /* synthetic */ StatServices stats() {
        return super.stats();
    }

    public /* bridge */ /* synthetic */ void willOpenUrl(Uri uri) {
        super.willOpenUrl(uri);
    }

    C0503h(Context context, long j, String str) {
        super(context, j, str);
        m437f();
    }

    /* renamed from: h */
    protected synchronized void mo1182h() {
        this.c.startService(mo1181k());
        super.mo1182h();
    }

    /* renamed from: b */
    protected void mo1180b(Message message) {
    }

    /* renamed from: k */
    protected Intent mo1181k() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.kongregate.android.backpack", "com.kongregate.android.backpack.service.KongregateService"));
        return intent;
    }
}
