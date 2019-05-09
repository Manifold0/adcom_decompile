package com.applovin.impl.sdk;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class bp implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ bo f2158a;

    bp(bo boVar) {
        this.f2158a = boVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.f2158a.f2157a.f2154b.continueVideo();
        this.f2158a.f2157a.f2154b.resumeReportRewardTask();
    }
}
