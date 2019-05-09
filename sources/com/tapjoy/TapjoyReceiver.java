package com.tapjoy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import com.google.android.gms.drive.DriveFile;
import com.tapjoy.internal.dy.C2879a;
import com.tapjoy.internal.eb;
import com.tapjoy.internal.ef;
import com.tapjoy.internal.fz;
import com.tapjoy.internal.gb;
import com.tapjoy.internal.gc;
import com.tapjoy.internal.ge;
import com.tapjoy.internal.gn;
import java.util.List;

public class TapjoyReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        ge.m7914b(context);
        if ("com.tapjoy.PUSH_CLICK".equals(intent.getAction())) {
            String stringExtra = intent.getStringExtra("com.tapjoy.PUSH_ID");
            String stringExtra2 = intent.getStringExtra(Tapjoy.INTENT_EXTRA_PUSH_PAYLOAD);
            String stringExtra3 = intent.getStringExtra("com.tapjoy.PUSH_PLACEMENT");
            if (!(stringExtra == null || stringExtra.length() == 0)) {
                Intent intent2;
                PackageManager packageManager = context.getPackageManager();
                String packageName = context.getPackageName();
                Intent intent3 = new Intent("android.intent.action.MAIN");
                intent3.setPackage(packageName);
                intent3.addCategory("android.intent.category.LAUNCHER");
                List queryIntentActivities = packageManager.queryIntentActivities(intent3, 0);
                if (queryIntentActivities == null || queryIntentActivities.size() <= 0) {
                    intent2 = null;
                } else {
                    Intent intent4 = new Intent(intent3);
                    intent4.setFlags(DriveFile.MODE_READ_ONLY);
                    intent4.setClassName(((ResolveInfo) queryIntentActivities.get(0)).activityInfo.packageName, ((ResolveInfo) queryIntentActivities.get(0)).activityInfo.name);
                    intent2 = intent4;
                }
                if (intent2 != null) {
                    if (stringExtra2 != null) {
                        intent2.putExtra(Tapjoy.INTENT_EXTRA_PUSH_PAYLOAD, stringExtra2);
                    }
                    gc a = gc.m7832a(context);
                    if (a.f7854f.m7936c(stringExtra)) {
                        gb gbVar = a.f7855g;
                        C2879a a2 = gbVar.m7823a(eb.APP, "push_click");
                        a2.f7430s = new ef(null, null, stringExtra);
                        gbVar.m7824a(a2);
                    }
                    if (stringExtra3 != null) {
                        gn.m7699a(stringExtra, stringExtra3);
                    }
                    context.startActivity(intent2);
                } else {
                    fz.m7816b("No intent that can be used to launch the main activity.");
                }
            }
        }
        if (isOrderedBroadcast()) {
            setResult(-1, null, null);
        }
    }
}
