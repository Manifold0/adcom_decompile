// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal.shortcutbadger.util;

import java.util.List;
import android.content.Intent;
import android.content.Context;

public class BroadcastHelper
{
    public static boolean canResolveBroadcast(final Context context, final Intent intent) {
        final boolean b = false;
        final List queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 0);
        boolean b2 = b;
        if (queryBroadcastReceivers != null) {
            b2 = b;
            if (queryBroadcastReceivers.size() > 0) {
                b2 = true;
            }
        }
        return b2;
    }
}
