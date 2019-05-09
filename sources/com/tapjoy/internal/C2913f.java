package com.tapjoy.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.adjust.sdk.Constants;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

/* renamed from: com.tapjoy.internal.f */
public final class C2913f extends BroadcastReceiver {
    public final void onReceive(Context context, Intent intent) {
        String a = C2913f.m7691a(intent);
        if (a != null) {
            C2913f.m7692a(context, Constants.INSTALL_REFERRER, a);
        }
    }

    /* renamed from: a */
    public static String m7691a(Intent intent) {
        if ("com.android.vending.INSTALL_REFERRER".equals(intent.getAction())) {
            return intent.getStringExtra("referrer");
        }
        return null;
    }

    /* renamed from: a */
    private static boolean m7692a(Context context, String str, String str2) {
        Closeable closeable = null;
        try {
            closeable = context.openFileOutput(str, 0);
            bl.m7202a((OutputStream) closeable, str2);
            closeable.close();
            return true;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e2) {
            dc.m7357a(closeable);
            context.deleteFile(Constants.INSTALL_REFERRER);
            return false;
        }
    }
}
