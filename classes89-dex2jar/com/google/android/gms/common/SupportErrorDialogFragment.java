// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.content.DialogInterface;
import android.content.DialogInterface$OnDismissListener;
import com.google.android.gms.common.internal.Preconditions;
import android.content.DialogInterface$OnCancelListener;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;

public class SupportErrorDialogFragment extends DialogFragment
{
    private Dialog mDialog;
    private DialogInterface$OnCancelListener zaan;
    
    public SupportErrorDialogFragment() {
        this.mDialog = null;
        this.zaan = null;
    }
    
    public static SupportErrorDialogFragment newInstance(final Dialog dialog) {
        return newInstance(dialog, null);
    }
    
    public static SupportErrorDialogFragment newInstance(Dialog mDialog, final DialogInterface$OnCancelListener zaan) {
        final SupportErrorDialogFragment supportErrorDialogFragment = new SupportErrorDialogFragment();
        mDialog = (Dialog)Preconditions.checkNotNull((Object)mDialog, (Object)"Cannot display null dialog");
        mDialog.setOnCancelListener((DialogInterface$OnCancelListener)null);
        mDialog.setOnDismissListener((DialogInterface$OnDismissListener)null);
        supportErrorDialogFragment.mDialog = mDialog;
        if (zaan != null) {
            supportErrorDialogFragment.zaan = zaan;
        }
        return supportErrorDialogFragment;
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
