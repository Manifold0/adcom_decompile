// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads;

import android.support.annotation.WorkerThread;
import com.facebook.ads.internal.n.d;
import android.content.Context;

public final class BidderTokenProvider
{
    @WorkerThread
    public static String getBidderToken(final Context context) {
        return new d(context, true).a();
    }
}
