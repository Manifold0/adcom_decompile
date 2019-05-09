package com.kongregate.android.api.receivers;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.ironsource.sdk.constants.Constants.RequestParameters;
import com.kongregate.android.internal.sdk.C0525o;
import com.kongregate.android.internal.util.C0562j;
import com.kongregate.android.internal.util.StringUtils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InstallReceiver extends BroadcastReceiver {
    static final List<String> REFERRER_FIELDS = Arrays.asList(new String[]{C0525o.f616b, C0525o.f617c, C0525o.f618d, C0525o.f619e, C0525o.f620f});

    public static Map<String, Object> getReferrerFields(Context context) {
        HashMap hashMap = new HashMap();
        for (String put : REFERRER_FIELDS) {
            hashMap.put(put, null);
        }
        CharSequence string = getSharedPreferences(context).getString("referrer", "");
        if (StringUtils.m580a(string)) {
            C0562j.m753a("no referrer found");
            return hashMap;
        }
        C0562j.m753a("found referrer: " + string);
        for (String str : string.split(RequestParameters.AMPERSAND)) {
            String[] split = str.split(RequestParameters.EQUAL);
            if (split.length == 2 && REFERRER_FIELDS.contains(split[0])) {
                hashMap.put(split[0], split[1]);
            } else {
                C0562j.m759c("Unrecognized key-value in install referrer: " + str);
            }
        }
        return hashMap;
    }

    public static String getInstallReferrer(Context context) {
        return getSharedPreferences(context).getString("referrer", "");
    }

    public void onReceive(Context context, Intent intent) {
        forwardIntent(context, intent);
        try {
            CharSequence stringExtra = intent.getStringExtra("referrer");
            if (StringUtils.m591d(stringExtra)) {
                C0562j.m753a("InstallReceiver storing referrer in prefs: " + stringExtra);
                getSharedPreferences(context).edit().putString("referrer", stringExtra).apply();
                return;
            }
            C0562j.m753a("InstallReceiver: no referrer");
        } catch (Throwable th) {
            C0562j.m762d("Error while tracking installation", th);
        }
    }

    private void forwardIntent(Context context, Intent intent) {
        try {
            Bundle bundle = context.getPackageManager().getReceiverInfo(new ComponentName(context, "com.kongregate.android.api.receivers.InstallReceiver"), 128).metaData;
            for (String str : bundle.keySet()) {
                CharSequence string = bundle.getString(str);
                if (StringUtils.m580a(string)) {
                    C0562j.m759c("Unrecognized meta-tag in InstallReceiver: " + str);
                } else {
                    try {
                        C0562j.m756b("forwarding to: " + string);
                        getBroadcastReceiver(string).onReceive(context, intent);
                    } catch (Throwable e) {
                        C0562j.m760c("Unable to invoke onReceive for class. Not forwarding install intent: " + string, e);
                    } catch (Throwable e2) {
                        C0562j.m760c("Unable to invoke onReceive for class. Not forwarding install intent: " + string, e2);
                    } catch (Throwable e22) {
                        C0562j.m760c("Unable to invoke onReceive for class. Not forwarding install intent: " + string, e22);
                    } catch (Throwable e222) {
                        C0562j.m760c("Exception  in Receiver: " + string, e222);
                    }
                }
            }
        } catch (Throwable e2222) {
            C0562j.m760c("Unable to retrieve receiver info. Not forwarding install intents: ", e2222);
        }
    }

    public BroadcastReceiver getBroadcastReceiver(String str) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        return (BroadcastReceiver) Class.forName(str).newInstance();
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("kongregate_install", 0);
    }
}
