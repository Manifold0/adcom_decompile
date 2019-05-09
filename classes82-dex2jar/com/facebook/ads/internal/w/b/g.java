// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.w.b;

import com.facebook.ads.internal.r.a;
import android.app.AlertDialog$Builder;
import android.content.DialogInterface;
import android.app.Activity;
import android.support.annotation.Nullable;
import android.content.Context;
import android.content.DialogInterface$OnClickListener;

public class g
{
    public static void a(final DialogInterface$OnClickListener dialogInterface$OnClickListener, final DialogInterface$OnClickListener dialogInterface$OnClickListener2, @Nullable final Context context) {
        if (context == null || !(context instanceof Activity)) {
            dialogInterface$OnClickListener.onClick((DialogInterface)null, 0);
            return;
        }
        new AlertDialog$Builder(context).setTitle((CharSequence)a.f(context)).setMessage((CharSequence)a.g(context)).setPositiveButton((CharSequence)a.h(context), (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                dialogInterface$OnClickListener.onClick(dialogInterface, n);
            }
        }).setNegativeButton((CharSequence)a.i(context), (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                dialogInterface$OnClickListener2.onClick(dialogInterface, n);
            }
        }).show();
    }
}
