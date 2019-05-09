// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook;

import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

public final class CurrentAccessTokenExpirationBroadcastReceiver extends BroadcastReceiver
{
    public void onReceive(final Context context, final Intent intent) {
        if ("com.facebook.sdk.ACTION_CURRENT_ACCESS_TOKEN_CHANGED".equals(intent.getAction())) {
            AccessTokenManager.getInstance().currentAccessTokenChanged();
        }
    }
}
