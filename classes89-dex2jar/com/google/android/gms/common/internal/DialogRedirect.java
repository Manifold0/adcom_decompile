// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import android.content.ActivityNotFoundException;
import android.util.Log;
import android.content.DialogInterface;
import com.google.android.gms.common.api.internal.LifecycleFragment;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.app.Activity;
import android.content.DialogInterface$OnClickListener;

public abstract class DialogRedirect implements DialogInterface$OnClickListener
{
    public static DialogRedirect getInstance(final Activity activity, final Intent intent, final int n) {
        return new zac(intent, activity, n);
    }
    
    public static DialogRedirect getInstance(@NonNull final Fragment fragment, final Intent intent, final int n) {
        return new zad(intent, fragment, n);
    }
    
    public static DialogRedirect getInstance(@NonNull final LifecycleFragment lifecycleFragment, final Intent intent, final int n) {
        return new zae(intent, lifecycleFragment, n);
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        try {
            this.redirect();
        }
        catch (ActivityNotFoundException ex) {
            Log.e("DialogRedirect", "Failed to start resolution intent", (Throwable)ex);
        }
        finally {
            dialogInterface.dismiss();
        }
    }
    
    protected abstract void redirect();
}
