// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.api.receivers;

import android.content.SharedPreferences;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import android.os.Bundle;
import android.content.pm.PackageManager$NameNotFoundException;
import com.kongregate.android.internal.util.j;
import com.kongregate.android.internal.util.StringUtils;
import android.content.ComponentName;
import android.content.Intent;
import android.content.Context;
import java.util.Arrays;
import java.util.List;
import android.content.BroadcastReceiver;

public class InstallReceiver extends BroadcastReceiver
{
    static final List<String> REFERRER_FIELDS;
    
    static {
        REFERRER_FIELDS = Arrays.asList("utm_source", "utm_medium", "utm_term", "utm_content", "utm_campaign");
    }
    
    private void forwardIntent(final Context context, final Intent intent) {
        while (true) {
            while (true) {
                Label_0109: {
                    try {
                        final Bundle metaData = context.getPackageManager().getReceiverInfo(new ComponentName(context, "com.kongregate.android.api.receivers.InstallReceiver"), 128).metaData;
                        for (final String s : metaData.keySet()) {
                            final String string = metaData.getString(s);
                            if (!StringUtils.a((CharSequence)string)) {
                                break Label_0109;
                            }
                            j.c("Unrecognized meta-tag in InstallReceiver: " + s);
                        }
                    }
                    catch (PackageManager$NameNotFoundException ex) {
                        j.c("Unable to retrieve receiver info. Not forwarding install intents: ", (Throwable)ex);
                    }
                    break;
                    try {
                        final String string;
                        j.b("forwarding to: " + string);
                        this.getBroadcastReceiver(string).onReceive(context, intent);
                        continue;
                    }
                    catch (ClassNotFoundException ex2) {
                        final String string;
                        j.c("Unable to invoke onReceive for class. Not forwarding install intent: " + string, ex2);
                        continue;
                    }
                    catch (IllegalAccessException ex3) {
                        final String string;
                        j.c("Unable to invoke onReceive for class. Not forwarding install intent: " + string, ex3);
                        continue;
                    }
                    catch (InstantiationException ex4) {
                        final String string;
                        j.c("Unable to invoke onReceive for class. Not forwarding install intent: " + string, ex4);
                        continue;
                    }
                    catch (Throwable t) {
                        final String string;
                        j.c("Exception  in Receiver: " + string, t);
                        continue;
                    }
                }
                continue;
            }
        }
    }
    
    public static String getInstallReferrer(final Context context) {
        return getSharedPreferences(context).getString("referrer", "");
    }
    
    public static Map<String, Object> getReferrerFields(final Context context) {
        final HashMap<String, Object> hashMap = new HashMap<String, Object>();
        final Iterator<String> iterator = InstallReceiver.REFERRER_FIELDS.iterator();
        while (iterator.hasNext()) {
            hashMap.put(iterator.next(), null);
        }
        final String string = getSharedPreferences(context).getString("referrer", "");
        if (StringUtils.a((CharSequence)string)) {
            j.a("no referrer found");
            return hashMap;
        }
        j.a("found referrer: " + string);
        final String[] split = string.split("&");
        for (int length = split.length, i = 0; i < length; ++i) {
            final String s = split[i];
            final String[] split2 = s.split("=");
            if (split2.length == 2 && InstallReceiver.REFERRER_FIELDS.contains(split2[0])) {
                hashMap.put(split2[0], split2[1]);
            }
            else {
                j.c("Unrecognized key-value in install referrer: " + s);
            }
        }
        return hashMap;
    }
    
    public static SharedPreferences getSharedPreferences(final Context context) {
        return context.getSharedPreferences("kongregate_install", 0);
    }
    
    public BroadcastReceiver getBroadcastReceiver(final String s) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        return (BroadcastReceiver)Class.forName(s).newInstance();
    }
    
    public void onReceive(final Context context, final Intent intent) {
        this.forwardIntent(context, intent);
        try {
            final String stringExtra = intent.getStringExtra("referrer");
            if (StringUtils.d((CharSequence)stringExtra)) {
                j.a("InstallReceiver storing referrer in prefs: " + stringExtra);
                getSharedPreferences(context).edit().putString("referrer", stringExtra).apply();
                return;
            }
            j.a("InstallReceiver: no referrer");
        }
        catch (Throwable t) {
            j.d("Error while tracking installation", t);
        }
    }
}
