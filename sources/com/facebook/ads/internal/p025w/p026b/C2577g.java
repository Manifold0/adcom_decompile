package com.facebook.ads.internal.p025w.p026b;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.p050r.C2078a;

/* renamed from: com.facebook.ads.internal.w.b.g */
public class C2577g {
    /* renamed from: a */
    public static void m6637a(final OnClickListener onClickListener, final OnClickListener onClickListener2, @Nullable Context context) {
        if (context == null || !(context instanceof Activity)) {
            onClickListener.onClick(null, 0);
        } else {
            new Builder(context).setTitle(C2078a.m5096f(context)).setMessage(C2078a.m5097g(context)).setPositiveButton(C2078a.m5098h(context), new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    onClickListener.onClick(dialogInterface, i);
                }
            }).setNegativeButton(C2078a.m5099i(context), new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    onClickListener2.onClick(dialogInterface, i);
                }
            }).show();
        }
    }
}
