// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy;

import android.os.Bundle;
import com.tapjoy.internal.ge;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

public class GCMReceiver extends BroadcastReceiver
{
    public void onReceive(final Context context, final Intent intent) {
        final boolean a = ge.b(context).a(intent);
        if (this.isOrderedBroadcast()) {
            this.setResult(-1, (String)null, (Bundle)null);
            if (a) {
                this.abortBroadcast();
            }
        }
    }
}
