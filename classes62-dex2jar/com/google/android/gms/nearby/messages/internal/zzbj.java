// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.common.api.Api$Client;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.internal.ClientSettings;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.nearby.messages.MessagesOptions;
import com.google.android.gms.common.api.Api$AbstractClientBuilder;

final class zzbj extends Api$AbstractClientBuilder<zzah, MessagesOptions>
{
    public final int getPriority() {
        return Integer.MAX_VALUE;
    }
}
