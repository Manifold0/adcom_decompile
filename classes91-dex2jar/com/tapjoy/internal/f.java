// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.io.FileOutputStream;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;

public final class f extends BroadcastReceiver
{
    public static String a(final Intent intent) {
        if ("com.android.vending.INSTALL_REFERRER".equals(intent.getAction())) {
            return intent.getStringExtra("referrer");
        }
        return null;
    }
    
    private static boolean a(final Context context, final String s, final String s2) {
        Closeable openFileOutput = null;
        try {
            final FileOutputStream fileOutputStream = (FileOutputStream)(openFileOutput = context.openFileOutput(s, 0));
            bl.a(fileOutputStream, s2);
            openFileOutput = fileOutputStream;
            fileOutputStream.close();
            return true;
        }
        catch (IOException ex) {
            dc.a(openFileOutput);
            context.deleteFile("install_referrer");
            return false;
        }
        catch (FileNotFoundException ex2) {
            return false;
        }
    }
    
    public final void onReceive(final Context context, final Intent intent) {
        final String a = a(intent);
        if (a != null) {
            a(context, "install_referrer", a);
        }
    }
}
