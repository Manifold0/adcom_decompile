// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.tasks;

import android.content.Intent;
import net.hockeyapp.android.UpdateActivity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.DialogFragment;
import net.hockeyapp.android.UpdateFragment;
import android.annotation.TargetApi;
import android.widget.Toast;
import net.hockeyapp.android.utils.Util;
import android.content.DialogInterface$OnCancelListener;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import net.hockeyapp.android.R$string;
import android.app.AlertDialog$Builder;
import net.hockeyapp.android.utils.VersionCache;
import net.hockeyapp.android.utils.HockeyLog;
import org.json.JSONArray;
import android.content.Context;
import net.hockeyapp.android.UpdateManagerListener;
import java.lang.ref.WeakReference;
import android.app.AlertDialog;
import android.app.Activity;

public class CheckUpdateTaskWithUI extends CheckUpdateTask
{
    private Activity mActivity;
    private AlertDialog mDialog;
    protected boolean mIsDialogRequired;
    
    public CheckUpdateTaskWithUI(final WeakReference<Activity> weakReference, final String s, final String s2, final UpdateManagerListener updateManagerListener, final boolean mIsDialogRequired) {
        super((WeakReference<? extends Context>)weakReference, s, s2, updateManagerListener);
        this.mActivity = null;
        this.mDialog = null;
        this.mIsDialogRequired = false;
        if (weakReference != null) {
            this.mActivity = (Activity)weakReference.get();
        }
        this.mIsDialogRequired = mIsDialogRequired;
    }
    
    @TargetApi(11)
    private void showDialog(final JSONArray jsonArray) {
        if (this.getCachingEnabled()) {
            HockeyLog.verbose("HockeyUpdate", "Caching is enabled. Setting version to cached one.");
            VersionCache.setVersionInfo((Context)this.mActivity, jsonArray.toString());
        }
        if (this.mActivity == null || this.mActivity.isFinishing()) {
            return;
        }
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this.mActivity);
        alertDialog$Builder.setTitle(R$string.hockeyapp_update_dialog_title);
        if (!this.mandatory) {
            alertDialog$Builder.setMessage(R$string.hockeyapp_update_dialog_message);
            alertDialog$Builder.setNegativeButton(R$string.hockeyapp_update_dialog_negative_button, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                public void onClick(final DialogInterface dialogInterface, final int n) {
                    CheckUpdateTaskWithUI.this.cleanUp();
                    if (CheckUpdateTaskWithUI.this.listener != null) {
                        CheckUpdateTaskWithUI.this.listener.onCancel();
                    }
                }
            });
            alertDialog$Builder.setOnCancelListener((DialogInterface$OnCancelListener)new DialogInterface$OnCancelListener() {
                public void onCancel(final DialogInterface dialogInterface) {
                    CheckUpdateTaskWithUI.this.cleanUp();
                    if (CheckUpdateTaskWithUI.this.listener != null) {
                        CheckUpdateTaskWithUI.this.listener.onCancel();
                    }
                }
            });
            alertDialog$Builder.setPositiveButton(R$string.hockeyapp_update_dialog_positive_button, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                public void onClick(final DialogInterface dialogInterface, final int n) {
                    if (CheckUpdateTaskWithUI.this.getCachingEnabled()) {
                        VersionCache.setVersionInfo((Context)CheckUpdateTaskWithUI.this.mActivity, "[]");
                    }
                    final WeakReference<Activity> weakReference = new WeakReference<Activity>(CheckUpdateTaskWithUI.this.mActivity);
                    if (Util.fragmentsSupported() && Util.runsOnTablet(weakReference)) {
                        CheckUpdateTaskWithUI.this.showUpdateFragment(jsonArray);
                        return;
                    }
                    CheckUpdateTaskWithUI.this.startUpdateIntent(jsonArray, false);
                }
            });
            (this.mDialog = alertDialog$Builder.create()).show();
            return;
        }
        Toast.makeText((Context)this.mActivity, (CharSequence)String.format(this.mActivity.getString(R$string.hockeyapp_update_mandatory_toast), Util.getAppName((Context)this.mActivity)), 1).show();
        this.startUpdateIntent(jsonArray, true);
    }
    
    @TargetApi(11)
    private void showUpdateFragment(final JSONArray jsonArray) {
        if (this.mActivity == null) {
            return;
        }
        final FragmentTransaction beginTransaction = this.mActivity.getFragmentManager().beginTransaction();
        beginTransaction.setTransition(4097);
        final Fragment fragmentByTag = this.mActivity.getFragmentManager().findFragmentByTag("hockey_update_dialog");
        if (fragmentByTag != null) {
            beginTransaction.remove(fragmentByTag);
        }
        beginTransaction.addToBackStack((String)null);
        Class<? extends UpdateFragment> updateFragmentClass = UpdateFragment.class;
        if (this.listener != null) {
            updateFragmentClass = this.listener.getUpdateFragmentClass();
        }
        try {
            ((DialogFragment)updateFragmentClass.getMethod("newInstance", JSONArray.class, String.class).invoke(null, jsonArray, this.getURLString("apk"))).show(beginTransaction, "hockey_update_dialog");
        }
        catch (Exception ex) {
            HockeyLog.error("An exception happened while showing the update fragment:");
            ex.printStackTrace();
            HockeyLog.error("Showing update activity instead.");
            this.startUpdateIntent(jsonArray, false);
        }
    }
    
    private void startUpdateIntent(final JSONArray jsonArray, final Boolean b) {
        Class<? extends UpdateActivity> updateActivityClass = null;
        if (this.listener != null) {
            updateActivityClass = this.listener.getUpdateActivityClass();
        }
        Class<? extends UpdateActivity> clazz;
        if ((clazz = updateActivityClass) == null) {
            clazz = UpdateActivity.class;
        }
        if (this.mActivity != null) {
            final Intent intent = new Intent();
            intent.setClass((Context)this.mActivity, (Class)clazz);
            intent.putExtra("json", jsonArray.toString());
            intent.putExtra("url", this.getURLString("apk"));
            this.mActivity.startActivity(intent);
            if (b) {
                this.mActivity.finish();
            }
        }
        this.cleanUp();
    }
    
    @Override
    protected void cleanUp() {
        super.cleanUp();
        this.mActivity = null;
        this.mDialog = null;
    }
    
    @Override
    public void detach() {
        super.detach();
        this.mActivity = null;
        if (this.mDialog != null) {
            this.mDialog.dismiss();
            this.mDialog = null;
        }
    }
    
    @Override
    protected void onPostExecute(final JSONArray jsonArray) {
        super.onPostExecute(jsonArray);
        if (jsonArray != null && this.mIsDialogRequired) {
            this.showDialog(jsonArray);
        }
    }
}
