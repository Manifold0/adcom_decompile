package com.applovin.impl.sdk;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class bq implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ bo f2159a;

    bq(bo boVar) {
        this.f2159a = boVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.f2159a.f2157a.f2154b.skipVideo();
        this.f2159a.f2157a.f2154b.resumeReportRewardTask();
    }
}
