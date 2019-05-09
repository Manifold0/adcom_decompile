package com.google.android.gms.internal.ads;

import com.facebook.ads.AudienceNetworkActivity;

final class zzvb implements Runnable {
    private final /* synthetic */ String zzbpt;
    private final /* synthetic */ zzuw zzbpu;

    zzvb(zzuw zzuw, String str) {
        this.zzbpu = zzuw;
        this.zzbpt = str;
    }

    public final void run() {
        this.zzbpu.zzbnd.loadData(this.zzbpt, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, "UTF-8");
    }
}
