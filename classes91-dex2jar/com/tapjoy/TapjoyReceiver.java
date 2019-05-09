// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy;

import com.tapjoy.internal.dy;
import com.tapjoy.internal.gb;
import java.util.List;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.tapjoy.internal.fz;
import com.tapjoy.internal.gn;
import com.tapjoy.internal.ef;
import com.tapjoy.internal.eb;
import com.tapjoy.internal.gc;
import android.content.pm.ResolveInfo;
import com.tapjoy.internal.ge;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

public class TapjoyReceiver extends BroadcastReceiver
{
    public void onReceive(final Context context, Intent intent) {
        ge.b(context);
        if ("com.tapjoy.PUSH_CLICK".equals(intent.getAction())) {
            final String stringExtra = intent.getStringExtra("com.tapjoy.PUSH_ID");
            final String stringExtra2 = intent.getStringExtra("com.tapjoy.PUSH_PAYLOAD");
            final String stringExtra3 = intent.getStringExtra("com.tapjoy.PUSH_PLACEMENT");
            if (stringExtra != null && stringExtra.length() != 0) {
                final PackageManager packageManager = context.getPackageManager();
                final String packageName = context.getPackageName();
                intent = new Intent("android.intent.action.MAIN");
                intent.setPackage(packageName);
                intent.addCategory("android.intent.category.LAUNCHER");
                final List queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
                if (queryIntentActivities == null || queryIntentActivities.size() <= 0) {
                    intent = null;
                }
                else {
                    intent = new Intent(intent);
                    intent.setFlags(268435456);
                    intent.setClassName(queryIntentActivities.get(0).activityInfo.packageName, queryIntentActivities.get(0).activityInfo.name);
                }
                if (intent != null) {
                    if (stringExtra2 != null) {
                        intent.putExtra("com.tapjoy.PUSH_PAYLOAD", stringExtra2);
                    }
                    final gc a = gc.a(context);
                    if (a.f.c(stringExtra)) {
                        final gb g = a.g;
                        final dy.a a2 = g.a(eb.APP, "push_click");
                        a2.s = new ef(null, null, stringExtra);
                        g.a(a2);
                    }
                    if (stringExtra3 != null) {
                        gn.a(stringExtra, stringExtra3);
                    }
                    context.startActivity(intent);
                }
                else {
                    fz.b("No intent that can be used to launch the main activity.");
                }
            }
        }
        if (this.isOrderedBroadcast()) {
            this.setResult(-1, (String)null, (Bundle)null);
        }
    }
}
