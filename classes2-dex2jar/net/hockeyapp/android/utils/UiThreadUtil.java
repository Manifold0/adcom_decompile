// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.utils;

import android.content.Context;
import android.widget.Toast;
import android.app.ProgressDialog;
import android.app.Activity;
import java.lang.ref.WeakReference;

public class UiThreadUtil
{
    private UiThreadUtil() {
    }
    
    public static UiThreadUtil getInstance() {
        return WbUtilHolder.INSTANCE;
    }
    
    public void dismissLoading(final WeakReference<Activity> weakReference, final ProgressDialog progressDialog) {
        if (weakReference != null) {
            final Activity activity = weakReference.get();
            if (activity != null) {
                activity.runOnUiThread((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        if (progressDialog != null && progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                    }
                });
            }
        }
    }
    
    public void dismissLoadingDialogAndDisplayError(final WeakReference<Activity> weakReference, final ProgressDialog progressDialog, final int n) {
        if (weakReference != null) {
            final Activity activity = weakReference.get();
            if (activity != null) {
                activity.runOnUiThread((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        if (progressDialog != null && progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        activity.showDialog(n);
                    }
                });
            }
        }
    }
    
    public void displayToastMessage(final WeakReference<Activity> weakReference, final String s, final int n) {
        if (weakReference != null) {
            final Activity activity = weakReference.get();
            if (activity != null) {
                activity.runOnUiThread((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText((Context)activity, (CharSequence)s, n).show();
                    }
                });
            }
        }
    }
    
    private static class WbUtilHolder
    {
        public static final UiThreadUtil INSTANCE;
        
        static {
            INSTANCE = new UiThreadUtil(null);
        }
    }
}
