package com.applovin.adview;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/* renamed from: com.applovin.adview.a */
class C1224a implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ AppLovinConfirmationActivity f1565a;

    C1224a(AppLovinConfirmationActivity appLovinConfirmationActivity) {
        this.f1565a = appLovinConfirmationActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.f1565a.finish();
    }
}
