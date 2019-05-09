package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.webkit.WebSettings;
import java.util.concurrent.Callable;

final class zzamp implements Callable<String> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ Context zzcub;

    zzamp(zzamn zzamn, Context context, Context context2) {
        this.zzcub = context;
        this.val$context = context2;
    }

    public final /* synthetic */ Object call() throws Exception {
        SharedPreferences sharedPreferences;
        int i = 0;
        if (this.zzcub != null) {
            zzakb.m3915v("Attempting to read user agent from Google Play Services.");
            sharedPreferences = this.zzcub.getSharedPreferences("admob_user_agent", 0);
        } else {
            zzakb.m3915v("Attempting to read user agent from local cache.");
            sharedPreferences = this.val$context.getSharedPreferences("admob_user_agent", 0);
            i = 1;
        }
        CharSequence string = sharedPreferences.getString("user_agent", "");
        if (TextUtils.isEmpty(string)) {
            zzakb.m3915v("Reading user agent from WebSettings");
            string = WebSettings.getDefaultUserAgent(this.val$context);
            if (i != 0) {
                sharedPreferences.edit().putString("user_agent", string).apply();
                zzakb.m3915v("Persisting user agent.");
            }
        }
        return string;
    }
}
