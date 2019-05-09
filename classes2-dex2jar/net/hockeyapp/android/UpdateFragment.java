// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android;

import android.os.Build$VERSION;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import net.hockeyapp.android.utils.HockeyLog;
import android.webkit.WebView;
import android.widget.Button;
import net.hockeyapp.android.tasks.GetFileSizeTask;
import java.util.Locale;
import android.widget.TextView;
import org.json.JSONException;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.view.View;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager$NameNotFoundException;
import android.os.AsyncTask;
import net.hockeyapp.android.utils.AsyncTaskUtils;
import android.content.Context;
import net.hockeyapp.android.listeners.DownloadFileListener;
import android.os.Bundle;
import android.app.Activity;
import org.json.JSONArray;
import net.hockeyapp.android.utils.VersionHelper;
import net.hockeyapp.android.tasks.DownloadFileTask;
import android.annotation.TargetApi;
import android.view.View$OnClickListener;
import android.app.DialogFragment;

@TargetApi(11)
public class UpdateFragment extends DialogFragment implements View$OnClickListener, UpdateInfoListener
{
    public static final String FRAGMENT_URL = "url";
    public static final String FRAGMENT_VERSION_INFO = "versionInfo";
    private DownloadFileTask mDownloadTask;
    private String mUrlString;
    private VersionHelper mVersionHelper;
    private JSONArray mVersionInfo;
    
    public static UpdateFragment newInstance(final JSONArray jsonArray, final String s) {
        final Bundle arguments = new Bundle();
        arguments.putString("url", s);
        arguments.putString("versionInfo", jsonArray.toString());
        final UpdateFragment updateFragment = new UpdateFragment();
        updateFragment.setArguments(arguments);
        return updateFragment;
    }
    
    private void startDownloadTask(final Activity activity) {
        AsyncTaskUtils.execute(this.mDownloadTask = new DownloadFileTask((Context)activity, this.mUrlString, new DownloadFileListener() {
            @Override
            public void downloadFailed(final DownloadFileTask downloadFileTask, final Boolean b) {
                if (b) {
                    UpdateFragment.this.startDownloadTask(activity);
                }
            }
            
            @Override
            public void downloadSuccessful(final DownloadFileTask downloadFileTask) {
            }
        }));
    }
    
    public String getAppName() {
        final Activity activity = this.getActivity();
        try {
            final PackageManager packageManager = activity.getPackageManager();
            return packageManager.getApplicationLabel(packageManager.getApplicationInfo(activity.getPackageName(), 0)).toString();
        }
        catch (PackageManager$NameNotFoundException ex) {
            return "";
        }
    }
    
    public int getCurrentVersionCode() {
        try {
            return this.getActivity().getPackageManager().getPackageInfo(this.getActivity().getPackageName(), 128).versionCode;
        }
        catch (NullPointerException ex) {
            return -1;
        }
        catch (PackageManager$NameNotFoundException ex2) {
            return -1;
        }
    }
    
    public View getLayoutView() {
        final LinearLayout linearLayout = new LinearLayout((Context)this.getActivity());
        LayoutInflater.from((Context)this.getActivity()).inflate(R$layout.hockeyapp_fragment_update, (ViewGroup)linearLayout);
        return (View)linearLayout;
    }
    
    public void onClick(final View view) {
        this.prepareDownload();
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        try {
            this.mUrlString = this.getArguments().getString("url");
            this.mVersionInfo = new JSONArray(this.getArguments().getString("versionInfo"));
            this.setStyle(1, 16973939);
        }
        catch (JSONException ex) {
            this.dismiss();
        }
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View layoutView = this.getLayoutView();
        this.mVersionHelper = new VersionHelper((Context)this.getActivity(), this.mVersionInfo.toString(), this);
        ((TextView)layoutView.findViewById(R$id.label_title)).setText((CharSequence)this.getAppName());
        final TextView textView = (TextView)layoutView.findViewById(R$id.label_version);
        final String string = "Version " + this.mVersionHelper.getVersionString();
        final String fileDateString = this.mVersionHelper.getFileDateString();
        String string2 = "Unknown size";
        final long fileSizeBytes = this.mVersionHelper.getFileSizeBytes();
        if (fileSizeBytes >= 0L) {
            string2 = String.format(Locale.US, "%.2f", fileSizeBytes / 1048576.0f) + " MB";
        }
        else {
            AsyncTaskUtils.execute(new GetFileSizeTask((Context)this.getActivity(), this.mUrlString, new DownloadFileListener() {
                @Override
                public void downloadSuccessful(final DownloadFileTask downloadFileTask) {
                    if (downloadFileTask instanceof GetFileSizeTask) {
                        textView.setText((CharSequence)UpdateFragment.this.getString(R$string.hockeyapp_update_version_details_label, new Object[] { string, fileDateString, String.format(Locale.US, "%.2f", ((GetFileSizeTask)downloadFileTask).getSize() / 1048576.0f) + " MB" }));
                    }
                }
            }));
        }
        textView.setText((CharSequence)this.getString(R$string.hockeyapp_update_version_details_label, new Object[] { string, fileDateString, string2 }));
        ((Button)layoutView.findViewById(R$id.button_update)).setOnClickListener((View$OnClickListener)this);
        final WebView webView = (WebView)layoutView.findViewById(R$id.web_update_details);
        webView.clearCache(true);
        webView.destroyDrawingCache();
        webView.loadDataWithBaseURL("https://sdk.hockeyapp.net/", this.mVersionHelper.getReleaseNotes(false), "text/html", "utf-8", (String)null);
        return layoutView;
    }
    
    public void onRequestPermissionsResult(final int n, final String[] array, final int[] array2) {
        if (array.length == 0 || array2.length == 0 || n != 1) {
            return;
        }
        if (array2[0] == 0) {
            this.startDownloadTask(this.getActivity());
            return;
        }
        HockeyLog.warn("User denied write permission, can't continue with updater task.");
        final UpdateManagerListener lastListener = UpdateManager.getLastListener();
        if (lastListener != null) {
            lastListener.onUpdatePermissionsNotGranted();
            return;
        }
        new AlertDialog$Builder((Context)this.getActivity()).setTitle((CharSequence)this.getString(R$string.hockeyapp_permission_update_title)).setMessage((CharSequence)this.getString(R$string.hockeyapp_permission_update_message)).setNegativeButton((CharSequence)this.getString(R$string.hockeyapp_permission_dialog_negative_button), (DialogInterface$OnClickListener)null).setPositiveButton((CharSequence)this.getString(R$string.hockeyapp_permission_dialog_positive_button), (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                UpdateFragment.this.prepareDownload();
            }
        }).create().show();
    }
    
    public void prepareDownload() {
        if (Build$VERSION.SDK_INT >= 23 && this.getActivity().checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            this.requestPermissions(new String[] { "android.permission.WRITE_EXTERNAL_STORAGE" }, 1);
            return;
        }
        this.startDownloadTask(this.getActivity());
        this.dismiss();
    }
}
