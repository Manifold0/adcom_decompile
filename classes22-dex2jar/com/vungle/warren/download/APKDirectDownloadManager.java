// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren.download;

import java.util.Iterator;
import com.vungle.warren.locale.LocaleString;
import android.support.v4.content.PermissionChecker;
import android.webkit.MimeTypeMap;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.net.NetworkRequest$Builder;
import android.net.ConnectivityManager;
import android.support.annotation.RequiresApi;
import android.net.Network;
import android.net.ConnectivityManager$NetworkCallback;
import com.vungle.warren.NetworkStateReceiver;
import android.os.Build$VERSION;
import com.vungle.warren.ui.VungleWebViewActivity;
import android.content.Intent;
import java.io.IOException;
import com.vungle.warren.network.Downloader;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.util.ArrayList;
import android.app.NotificationManager;
import java.util.List;
import android.support.v4.app.NotificationCompat$Builder;
import com.vungle.warren.network.APKDirectDownloader;
import android.content.Context;
import java.lang.ref.WeakReference;

public class APKDirectDownloadManager
{
    private static final String APK_POSTFIX = "apk";
    public static final int DIRECT_DOWNLOAD_FLAG_DISABLED = 0;
    public static final int DIRECT_DOWNLOAD_FLAG_ENABLED = 1;
    public static final int DIRECT_DOWNLOAD_FLAG_NOT_SET = -1;
    private static final String FOLDER_APK = "apk";
    private static final String FOLDER_NAME = "vungle";
    private static final String TAG = "DirectDownloadManager";
    private static APKDirectDownloadManager _instance;
    private int apkDirectDownloadStatus;
    private WeakReference<Context> context;
    private APKDirectDownloader downloader;
    private boolean isNetworkReceiverEnabled;
    private boolean isWifiEnabled;
    private NotificationCompat$Builder mBuilder;
    private List<Integer> notificationList;
    private NotificationManager notifyManager;
    private List<String> pendingDownloadUrl;
    private List<Integer> pendingNotificationList;
    
    static {
        APKDirectDownloadManager._instance = new APKDirectDownloadManager();
    }
    
    public APKDirectDownloadManager() {
        this.pendingDownloadUrl = new ArrayList<String>();
        this.notificationList = new ArrayList<Integer>();
        this.pendingNotificationList = new ArrayList<Integer>();
        this.apkDirectDownloadStatus = -1;
        this.isWifiEnabled = false;
        this.isNetworkReceiverEnabled = false;
    }
    
    private void clearDownloadApkCache() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final File[] listFiles = APKDirectDownloadManager.this.getCacheDirectory().listFiles();
                if (listFiles != null && listFiles.length != 0) {
                    for (int i = 0; i < listFiles.length; ++i) {
                        if (listFiles[i].isFile() && listFiles[i].exists() && listFiles[i].isFile()) {
                            Log.d("DirectDownloadManager", "Clear cache:" + listFiles[i].getName());
                            listFiles[i].delete();
                        }
                    }
                }
            }
        }).start();
    }
    
    private void dismissNotification(final int n) {
        if (this.notifyManager != null) {
            this.notifyManager.cancel(n);
        }
    }
    
    public static void download(final String s) {
        enableNetworkListener(true, getContext());
        if (TextUtils.isEmpty((CharSequence)s)) {
            return;
        }
        final APKDirectDownloadManager instance = APKDirectDownloadManager._instance;
        if (isApkUrl(s)) {
            int hashCode;
            try {
                final String value = String.valueOf(System.currentTimeMillis());
                hashCode = value.hashCode();
                if (APKDirectDownloadManager._instance.isWifiEnabled()) {
                    APKDirectDownloadManager._instance.notificationList.add(hashCode);
                    final File apkDirectory = APKDirectDownloadManager._instance.getApkDirectory(value);
                    APKDirectDownloadManager._instance.downloader.download(s, apkDirectory, new Downloader.Listener() {
                        @Override
                        public void onComplete(final File file) {
                            Log.d("DirectDownloadManager", "download complete :" + file.getAbsolutePath());
                            APKDirectDownloadManager._instance.installApk(apkDirectory);
                            if (!APKDirectDownloadManager.isDownloadTaskRunning()) {
                                APKDirectDownloadManager.enableNetworkListener(false, getContext());
                            }
                            APKDirectDownloadManager._instance.notificationList.remove((Object)hashCode);
                        }
                        
                        @Override
                        public void onError(final Throwable t) {
                            Log.d("DirectDownloadManager", "download complete :" + t.getMessage());
                            if (APKDirectDownloadManager.isDownloadTaskRunning()) {
                                APKDirectDownloadManager.enableNetworkListener(false, getContext());
                            }
                            APKDirectDownloadManager._instance.dismissNotification(hashCode);
                        }
                        
                        @Override
                        public void onProgress(final int n) {
                            Log.d("DirectDownloadManager", "download progress :" + n);
                            APKDirectDownloadManager._instance.notifyProgress(hashCode, n);
                        }
                    });
                    return;
                }
            }
            catch (IOException ex) {
                ex.printStackTrace();
                return;
            }
            APKDirectDownloadManager._instance.notifyProgress(hashCode, -1);
            APKDirectDownloadManager._instance.pendingNotificationList.add(hashCode);
            APKDirectDownloadManager._instance.pendingDownloadUrl.add(s);
            return;
        }
        final Intent intent = new Intent();
        intent.setClass((Context)APKDirectDownloadManager._instance.context.get(), (Class)VungleWebViewActivity.class);
        intent.putExtra("intent_url", s);
        intent.setFlags(268435456);
        APKDirectDownloadManager._instance.context.get().startActivity(intent);
    }
    
    public static void enableNetworkListener(final boolean isNetworkReceiverEnabled, final Context context) {
        if (APKDirectDownloadManager._instance.isNetworkReceiverEnabled != isNetworkReceiverEnabled && context != null) {
            APKDirectDownloadManager._instance.isNetworkReceiverEnabled = isNetworkReceiverEnabled;
            if (Build$VERSION.SDK_INT < 24) {
                if (isNetworkReceiverEnabled) {
                    NetworkStateReceiver.enableBroadcastReceiver(context, true);
                    return;
                }
                NetworkStateReceiver.enableBroadcastReceiver(context, false);
            }
            else {
                final ConnectivityManager$NetworkCallback connectivityManager$NetworkCallback = new ConnectivityManager$NetworkCallback() {
                    @RequiresApi(api = 24)
                    public void onAvailable(final Network network) {
                        super.onAvailable(network);
                        Log.d("DirectDownloadManager", "onAvailable:" + network);
                        resumeDownload();
                        APKDirectDownloadManager._instance.isWifiEnabled = true;
                    }
                    
                    @RequiresApi(api = 24)
                    public void onLost(final Network network) {
                        super.onLost(network);
                        Log.d("DirectDownloadManager", "onLost:" + network);
                        pauseDownload();
                        APKDirectDownloadManager._instance.isWifiEnabled = false;
                    }
                };
                final ConnectivityManager connectivityManager = (ConnectivityManager)APKDirectDownloadManager._instance.context.get().getSystemService("connectivity");
                if (connectivityManager != null) {
                    if (isNetworkReceiverEnabled) {
                        final NetworkRequest$Builder networkRequest$Builder = new NetworkRequest$Builder();
                        networkRequest$Builder.addTransportType(1).addCapability(12);
                        connectivityManager.registerNetworkCallback(networkRequest$Builder.build(), (ConnectivityManager$NetworkCallback)connectivityManager$NetworkCallback);
                        return;
                    }
                    connectivityManager.unregisterNetworkCallback((ConnectivityManager$NetworkCallback)connectivityManager$NetworkCallback);
                }
            }
        }
    }
    
    private File getApkDirectory(final String s) throws IllegalStateException {
        final File file = new File(this.getCacheDirectory().getPath() + File.separator);
        if (!file.exists()) {
            file.mkdir();
        }
        return new File(this.getCacheDirectory().getPath() + File.separator + s + "." + "apk");
    }
    
    private File getCacheDirectory() throws IllegalStateException {
        if (this.context == null) {
            throw new IllegalStateException("Context has expired, cannot continue.");
        }
        final File file = new File(this.context.get().getCacheDir().getPath() + File.separator + "vungle" + File.separator + "apk");
        if (!file.exists()) {
            file.mkdir();
        }
        return file;
    }
    
    @Nullable
    private static Context getContext() {
        if (APKDirectDownloadManager._instance == null || APKDirectDownloadManager._instance.context == null) {
            return null;
        }
        return APKDirectDownloadManager._instance.context.get();
    }
    
    public static void handleDownload(final Context context) {
        boolean b = true;
        if (context != null) {
            if (APKDirectDownloadManager._instance == null || !isDownloadTaskRunning() || APKDirectDownloadManager._instance.apkDirectDownloadStatus == 0) {
                enableNetworkListener(false, context);
                return;
            }
            final NetworkInfo activeNetworkInfo = ((ConnectivityManager)context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting() || activeNetworkInfo.getType() != 1) {
                b = false;
            }
            if (b && !APKDirectDownloadManager._instance.isWifiEnabled) {
                resumeDownload();
                return;
            }
            if (APKDirectDownloadManager._instance.isWifiEnabled) {
                pauseDownload();
            }
        }
    }
    
    public static void init(final Context context) {
        APKDirectDownloadManager._instance.context = new WeakReference<Context>(context);
        APKDirectDownloadManager._instance.clearDownloadApkCache();
        if (APKDirectDownloadManager._instance.downloader == null) {
            APKDirectDownloadManager._instance.downloader = new APKDirectDownloader(APKDirectDownloadManager._instance.context.get());
        }
    }
    
    private void installApk(final File file) {
        final Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setFlags(268435456);
        if (Build$VERSION.SDK_INT > 23) {
            intent.setDataAndType(FileProvider.getUriForFile((Context)this.context.get(), "com.vungle.download.provider", file), "application/vnd.android.package-archive");
            intent.addFlags(3);
        }
        else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }
        if (this.context.get().getPackageManager().queryIntentActivities(intent, 0).size() > 0) {
            this.context.get().startActivity(intent);
        }
        final String name = file.getName();
        final String substring = name.substring(0, name.length() - 4);
        Log.d("DirectDownloadManager", "identifier is" + substring);
        this.dismissNotification(substring.hashCode());
    }
    
    public static boolean isApkUrl(final String s) {
        return MimeTypeMap.getFileExtensionFromUrl(s).toLowerCase().endsWith("apk");
    }
    
    public static boolean isDirectDownloadEnabled(final boolean b, final boolean b2) {
        boolean b3 = b2;
        switch (APKDirectDownloadManager._instance.apkDirectDownloadStatus) {
            default: {
                b3 = false;
                return b3;
            }
            case 1: {
                return b3;
            }
            case -1: {
                b3 = b2;
                if (!b) {
                    return false;
                }
                return b3;
            }
        }
    }
    
    public static boolean isDownloadTaskRunning() {
        return !APKDirectDownloadManager._instance.pendingDownloadUrl.isEmpty() || APKDirectDownloadManager._instance.downloader.isDownloadTaskRunning();
    }
    
    private boolean isWifiEnabled() {
        boolean isWifiEnabled;
        final boolean b = isWifiEnabled = false;
        if (PermissionChecker.checkCallingOrSelfPermission((Context)this.context.get(), "android.permission.ACCESS_NETWORK_STATE") == 0) {
            final Context context = this.context.get();
            final Context context2 = this.context.get();
            final NetworkInfo activeNetworkInfo = ((ConnectivityManager)context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return false;
            }
            isWifiEnabled = b;
            switch (activeNetworkInfo.getType()) {
                default: {
                    isWifiEnabled = b;
                    break;
                }
                case 1:
                case 6: {
                    isWifiEnabled = true;
                    break;
                }
                case 0:
                case 7:
                case 9: {
                    isWifiEnabled = false;
                }
                case 2:
                case 3:
                case 4:
                case 5:
                case 8: {
                    break;
                }
            }
        }
        return this.isWifiEnabled = isWifiEnabled;
    }
    
    private void notifyProgress(final int n, final int n2) {
        Log.d("DirectDownloadManager", "notify id is :" + n + " progress:" + n2);
        if (this.notifyManager == null) {
            final Context context = this.context.get();
            final Context context2 = this.context.get();
            this.notifyManager = (NotificationManager)context.getSystemService("notification");
            (this.mBuilder = new NotificationCompat$Builder((Context)this.context.get())).setSmallIcon(17301634);
        }
        if (n2 == -1 || !this.isWifiEnabled) {
            this.mBuilder.setContentTitle((CharSequence)LocaleString.getLocaleText(4)).setContentText((CharSequence)LocaleString.getLocaleText(2)).setProgress(0, 0, false);
        }
        else if (n2 >= 0 && n2 < 100) {
            this.mBuilder.setContentTitle((CharSequence)LocaleString.getLocaleText(4)).setContentText((CharSequence)LocaleString.getLocaleText(3));
            this.mBuilder.setProgress(100, n2, false);
        }
        else {
            this.mBuilder.setContentText((CharSequence)LocaleString.getLocaleText(5)).setProgress(0, 0, false);
        }
        this.notifyManager.notify(n, this.mBuilder.build());
    }
    
    private static void pauseDownload() {
        APKDirectDownloadManager._instance.isWifiEnabled = false;
        APKDirectDownloadManager._instance.downloader.pause();
        final Iterator<Integer> iterator = APKDirectDownloadManager._instance.notificationList.iterator();
        while (iterator.hasNext()) {
            APKDirectDownloadManager._instance.notifyProgress(iterator.next(), -1);
        }
    }
    
    private static void resumeDownload() {
        APKDirectDownloadManager._instance.isWifiEnabled = true;
        APKDirectDownloadManager._instance.downloader.resume();
        if (!APKDirectDownloadManager._instance.pendingNotificationList.isEmpty()) {
            final Iterator<Integer> iterator = APKDirectDownloadManager._instance.pendingNotificationList.iterator();
            while (iterator.hasNext()) {
                APKDirectDownloadManager._instance.dismissNotification(iterator.next());
            }
        }
        if (!APKDirectDownloadManager._instance.pendingDownloadUrl.isEmpty()) {
            final Iterator<String> iterator2 = APKDirectDownloadManager._instance.pendingDownloadUrl.iterator();
            while (iterator2.hasNext()) {
                download(iterator2.next());
            }
            APKDirectDownloadManager._instance.pendingDownloadUrl.clear();
        }
    }
    
    public static void setDirectDownloadStatus(final int apkDirectDownloadStatus) {
        APKDirectDownloadManager._instance.apkDirectDownloadStatus = apkDirectDownloadStatus;
    }
}
