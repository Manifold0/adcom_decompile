// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android;

import net.hockeyapp.android.utils.Util;
import net.hockeyapp.android.utils.HockeyLog;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import android.app.Dialog;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.View;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager$NameNotFoundException;
import android.webkit.WebView;
import android.widget.Button;
import android.os.AsyncTask;
import net.hockeyapp.android.utils.AsyncTaskUtils;
import net.hockeyapp.android.tasks.GetFileSizeTask;
import net.hockeyapp.android.listeners.DownloadFileListener;
import java.util.Locale;
import android.widget.TextView;
import android.annotation.SuppressLint;
import android.provider.Settings$SettingNotFoundException;
import android.provider.Settings$Secure;
import android.provider.Settings$Global;
import android.os.Build$VERSION;
import net.hockeyapp.android.utils.VersionHelper;
import net.hockeyapp.android.objects.ErrorObject;
import net.hockeyapp.android.tasks.DownloadFileTask;
import android.content.Context;
import android.view.View$OnClickListener;
import android.app.Activity;

public class UpdateActivity extends Activity implements UpdateActivityInterface, UpdateInfoListener, View$OnClickListener
{
    private static final int DIALOG_ERROR_ID = 0;
    public static final String EXTRA_JSON = "json";
    public static final String EXTRA_URL = "url";
    private Context mContext;
    protected DownloadFileTask mDownloadTask;
    private ErrorObject mError;
    protected VersionHelper mVersionHelper;
    
    @SuppressLint({ "InlinedApi" })
    private boolean isUnknownSourcesChecked() {
        try {
            if (Build$VERSION.SDK_INT >= 17 && Build$VERSION.SDK_INT < 21) {
                if (Settings$Global.getInt(this.getContentResolver(), "install_non_market_apps") == 1) {
                    return true;
                }
                return false;
            }
            else if (Settings$Secure.getInt(this.getContentResolver(), "install_non_market_apps") != 1) {
                return false;
            }
        }
        catch (Settings$SettingNotFoundException ex) {}
        return true;
    }
    
    private boolean isWriteExternalStorageSet(final Context context) {
        return context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }
    
    protected void configureView() {
        ((TextView)this.findViewById(R$id.label_title)).setText((CharSequence)this.getAppName());
        final TextView textView = (TextView)this.findViewById(R$id.label_version);
        final String string = "Version " + this.mVersionHelper.getVersionString();
        final String fileDateString = this.mVersionHelper.getFileDateString();
        String string2 = "Unknown size";
        final long fileSizeBytes = this.mVersionHelper.getFileSizeBytes();
        if (fileSizeBytes >= 0L) {
            string2 = String.format(Locale.US, "%.2f", fileSizeBytes / 1048576.0f) + " MB";
        }
        else {
            AsyncTaskUtils.execute(new GetFileSizeTask((Context)this, this.getIntent().getStringExtra("url"), new DownloadFileListener() {
                @Override
                public void downloadSuccessful(final DownloadFileTask downloadFileTask) {
                    if (downloadFileTask instanceof GetFileSizeTask) {
                        textView.setText((CharSequence)UpdateActivity.this.getString(R$string.hockeyapp_update_version_details_label, new Object[] { string, fileDateString, String.format(Locale.US, "%.2f", ((GetFileSizeTask)downloadFileTask).getSize() / 1048576.0f) + " MB" }));
                    }
                }
            }));
        }
        textView.setText((CharSequence)this.getString(R$string.hockeyapp_update_version_details_label, new Object[] { string, fileDateString, string2 }));
        ((Button)this.findViewById(R$id.button_update)).setOnClickListener((View$OnClickListener)this);
        final WebView webView = (WebView)this.findViewById(R$id.web_update_details);
        webView.clearCache(true);
        webView.destroyDrawingCache();
        webView.loadDataWithBaseURL("https://sdk.hockeyapp.net/", this.getReleaseNotes(), "text/html", "utf-8", (String)null);
    }
    
    protected void createDownloadTask(final String s, final DownloadFileListener downloadFileListener) {
        this.mDownloadTask = new DownloadFileTask((Context)this, s, downloadFileListener);
    }
    
    public void enableUpdateButton() {
        this.findViewById(R$id.button_update).setEnabled(true);
    }
    
    public String getAppName() {
        try {
            final PackageManager packageManager = this.getPackageManager();
            return packageManager.getApplicationLabel(packageManager.getApplicationInfo(this.getPackageName(), 0)).toString();
        }
        catch (PackageManager$NameNotFoundException ex) {
            return "";
        }
    }
    
    public int getCurrentVersionCode() {
        try {
            return this.getPackageManager().getPackageInfo(this.getPackageName(), 128).versionCode;
        }
        catch (PackageManager$NameNotFoundException ex) {
            return -1;
        }
    }
    
    @SuppressLint({ "InflateParams" })
    public View getLayoutView() {
        return this.getLayoutInflater().inflate(R$layout.hockeyapp_activity_update, (ViewGroup)null);
    }
    
    protected String getReleaseNotes() {
        return this.mVersionHelper.getReleaseNotes(false);
    }
    
    public void onClick(final View view) {
        this.prepareDownload();
        view.setEnabled(false);
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setTitle((CharSequence)"App Update");
        this.setContentView(this.getLayoutView());
        this.mContext = (Context)this;
        this.mVersionHelper = new VersionHelper((Context)this, this.getIntent().getStringExtra("json"), this);
        this.configureView();
        this.mDownloadTask = (DownloadFileTask)this.getLastNonConfigurationInstance();
        if (this.mDownloadTask != null) {
            this.mDownloadTask.attach((Context)this);
        }
    }
    
    protected Dialog onCreateDialog(final int n) {
        return this.onCreateDialog(n, null);
    }
    
    protected Dialog onCreateDialog(final int n, final Bundle bundle) {
        switch (n) {
            default: {
                return null;
            }
            case 0: {
                return (Dialog)new AlertDialog$Builder((Context)this).setMessage((CharSequence)"An error has occured").setCancelable(false).setTitle((CharSequence)"Error").setIcon(17301543).setPositiveButton((CharSequence)"OK", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                    public void onClick(final DialogInterface dialogInterface, final int n) {
                        UpdateActivity.this.mError = null;
                        dialogInterface.cancel();
                    }
                }).create();
            }
        }
    }
    
    protected void onPrepareDialog(final int n, final Dialog dialog) {
        switch (n) {
            default: {}
            case 0: {
                final AlertDialog alertDialog = (AlertDialog)dialog;
                if (this.mError != null) {
                    alertDialog.setMessage((CharSequence)this.mError.getMessage());
                    return;
                }
                alertDialog.setMessage((CharSequence)"An unknown error has occured.");
            }
        }
    }
    
    public void onRequestPermissionsResult(final int n, final String[] array, final int[] array2) {
        this.enableUpdateButton();
        if (array.length == 0 || array2.length == 0 || n != 1) {
            return;
        }
        if (array2[0] == 0) {
            this.prepareDownload();
            return;
        }
        HockeyLog.warn("User denied write permission, can't continue with updater task.");
        final UpdateManagerListener lastListener = UpdateManager.getLastListener();
        if (lastListener != null) {
            lastListener.onUpdatePermissionsNotGranted();
            return;
        }
        new AlertDialog$Builder(this.mContext).setTitle((CharSequence)this.getString(R$string.hockeyapp_permission_update_title)).setMessage((CharSequence)this.getString(R$string.hockeyapp_permission_update_message)).setNegativeButton((CharSequence)this.getString(R$string.hockeyapp_permission_dialog_negative_button), (DialogInterface$OnClickListener)null).setPositiveButton((CharSequence)this.getString(R$string.hockeyapp_permission_dialog_positive_button), (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                UpdateActivity.this.prepareDownload();
            }
        }).create().show();
    }
    
    public Object onRetainNonConfigurationInstance() {
        if (this.mDownloadTask != null) {
            this.mDownloadTask.detach();
        }
        return this.mDownloadTask;
    }
    
    protected void prepareDownload() {
        if (!Util.isConnectedToNetwork(this.mContext)) {
            (this.mError = new ErrorObject()).setMessage(this.getString(R$string.hockeyapp_error_no_network_message));
            this.runOnUiThread((Runnable)new Runnable() {
                @Override
                public void run() {
                    UpdateActivity.this.showDialog(0);
                }
            });
            return;
        }
        if (!this.isWriteExternalStorageSet(this.mContext)) {
            if (Build$VERSION.SDK_INT >= 23) {
                this.requestPermissions(new String[] { "android.permission.WRITE_EXTERNAL_STORAGE" }, 1);
                return;
            }
            (this.mError = new ErrorObject()).setMessage("The permission to access the external storage permission is not set. Please contact the developer.");
            this.runOnUiThread((Runnable)new Runnable() {
                @Override
                public void run() {
                    UpdateActivity.this.showDialog(0);
                }
            });
        }
        else {
            if (!this.isUnknownSourcesChecked()) {
                (this.mError = new ErrorObject()).setMessage("The installation from unknown sources is not enabled. Please check the device settings.");
                this.runOnUiThread((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        UpdateActivity.this.showDialog(0);
                    }
                });
                return;
            }
            this.startDownloadTask();
        }
    }
    
    protected void startDownloadTask() {
        this.startDownloadTask(this.getIntent().getStringExtra("url"));
    }
    
    protected void startDownloadTask(final String s) {
        this.createDownloadTask(s, new DownloadFileListener() {
            @Override
            public void downloadFailed(final DownloadFileTask downloadFileTask, final Boolean b) {
                if (b) {
                    UpdateActivity.this.startDownloadTask();
                    return;
                }
                UpdateActivity.this.enableUpdateButton();
            }
            
            @Override
            public void downloadSuccessful(final DownloadFileTask downloadFileTask) {
                UpdateActivity.this.enableUpdateButton();
            }
        });
        AsyncTaskUtils.execute(this.mDownloadTask);
    }
}
