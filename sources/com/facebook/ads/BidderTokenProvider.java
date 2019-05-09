package com.facebook.ads;

import android.content.Context;
import android.support.annotation.WorkerThread;
import com.facebook.ads.internal.p045n.C2057d;

public final class BidderTokenProvider {
    @WorkerThread
    public static String getBidderToken(Context context) {
        return new C2057d(context, true).m5018a();
    }
}
