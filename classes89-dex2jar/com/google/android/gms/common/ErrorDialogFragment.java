// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common;

import android.app.FragmentManager;
import android.os.Bundle;
import android.content.DialogInterface;
import android.content.DialogInterface$OnDismissListener;
import com.google.android.gms.common.internal.Preconditions;
import android.content.DialogInterface$OnCancelListener;
import android.app.Dialog;
import android.app.DialogFragment;

public class ErrorDialogFragment extends DialogFragment
{
    private Dialog mDialog;
    private DialogInterface$OnCancelListener zaan;
    
    public ErrorDialogFragment() {
        this.mDialog = null;
        this.zaan = null;
    }
    
    public static ErrorDialogFragment newInstance(final Dialog dialog) {
        return newInstance(dialog, null);
    }
    
    public static ErrorDialogFragment newInstance(Dialog mDialog, final DialogInterface$OnCancelListener zaan) {
        final ErrorDialogFragment errorDialogFragment = new ErrorDialogFragment();
        mDialog = (Dialog)Preconditions.checkNotNull((Object)mDialog, (Object)"Cannot display null dialog");
        mDialog.setOnCancelListener((DialogInterface$OnCancelListener)null);
        mDialog.setOnDismissListener((DialogInterface$OnDismissListener)null);
        errorDialogFragment.mDialog = mDialog;
        if (zaan != null) {
            errorDialogFragment.zaan = zaan;
        }
        return errorDialogFragment;
    }
    
    public void onCancel(final DialogInterface dialogInterface) {
        if (this.zaan != null) {
            this.zaan.onCancel(dialogInterface);
        }
    }
    
    public Dialog onCreateDialog(final Bundle bundle) {
        if (this.mDialog == null) {
            this.setShowsDialog(false);
        }
        return this.mDialog;
    }
    
    public void show(final FragmentManager fragmentManager, final String s) {
        super.show(fragmentManager, s);
    }
}
