package com.vungle.warren.download;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.ConnectivityManager.NetworkCallback;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.content.FileProvider;
import android.support.v4.content.PermissionChecker;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;
import com.google.android.gms.drive.DriveFile;
import com.onesignal.OneSignalDbContract.NotificationTable;
import com.vungle.warren.NetworkStateReceiver;
import com.vungle.warren.locale.LocaleString;
import com.vungle.warren.network.APKDirectDownloader;
import com.vungle.warren.network.Downloader.Listener;
import com.vungle.warren.ui.VungleWebViewActivity;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class APKDirectDownloadManager {
    private static final String APK_POSTFIX = "apk";
    public static final int DIRECT_DOWNLOAD_FLAG_DISABLED = 0;
    public static final int DIRECT_DOWNLOAD_FLAG_ENABLED = 1;
    public static final int DIRECT_DOWNLOAD_FLAG_NOT_SET = -1;
    private static final String FOLDER_APK = "apk";
    private static final String FOLDER_NAME = "vungle";
    private static final String TAG = "DirectDownloadManager";
    private static APKDirectDownloadManager _instance = new APKDirectDownloadManager();
    private int apkDirectDownloadStatus = -1;
    private WeakReference<Context> context;
    private APKDirectDownloader downloader;
    private boolean isNetworkReceiverEnabled = false;
    private boolean isWifiEnabled = false;
    private Builder mBuilder;
    private List<Integer> notificationList = new ArrayList();
    private NotificationManager notifyManager;
    private List<String> pendingDownloadUrl = new ArrayList();
    private List<Integer> pendingNotificationList = new ArrayList();

    /* renamed from: com.vungle.warren.download.APKDirectDownloadManager$2 */
    class C01352 implements Runnable {
        C01352() {
        }

        public void run() {
            File[] files = APKDirectDownloadManager.this.getCacheDirectory().listFiles();
            if (files != null && files.length != 0) {
                int i = 0;
                while (i < files.length) {
                    if (files[i].isFile() && files[i].exists() && files[i].isFile()) {
                        Log.d(APKDirectDownloadManager.TAG, "Clear cache:" + files[i].getName());
                        files[i].delete();
                    }
                    i++;
                }
            }
        }
    }

    /* renamed from: com.vungle.warren.download.APKDirectDownloadManager$3 */
    static class C01363 extends NetworkCallback {
        C01363() {
        }

        @RequiresApi(api = 24)
        public void onAvailable(Network network) {
            super.onAvailable(network);
            Log.d(APKDirectDownloadManager.TAG, "onAvailable:" + network);
            APKDirectDownloadManager.resumeDownload();
            APKDirectDownloadManager._instance.isWifiEnabled = true;
        }

        @RequiresApi(api = 24)
        public void onLost(Network network) {
            super.onLost(network);
            Log.d(APKDirectDownloadManager.TAG, "onLost:" + network);
            APKDirectDownloadManager.pauseDownload();
            APKDirectDownloadManager._instance.isWifiEnabled = false;
        }
    }

    public static void init(Context context) {
        _instance.context = new WeakReference(context);
        _instance.clearDownloadApkCache();
        if (_instance.downloader == null) {
            _instance.downloader = new APKDirectDownloader((Context) _instance.context.get());
        }
    }

    public static boolean isDirectDownloadEnabled(boolean clientFlag, boolean requiresSideLoading) {
        switch (_instance.apkDirectDownloadStatus) {
            case -1:
                if (clientFlag) {
                    return requiresSideLoading;
                }
                return false;
            case 1:
                return requiresSideLoading;
            default:
                return false;
        }
    }

    public static void setDirectDownloadStatus(int status) {
        _instance.apkDirectDownloadStatus = status;
    }

    public static void download(String url) {
        enableNetworkListener(true, getContext());
        if (!TextUtils.isEmpty(url)) {
            APKDirectDownloadManager aPKDirectDownloadManager = _instance;
            if (isApkUrl(url)) {
                try {
                    String identifier = String.valueOf(System.currentTimeMillis());
                    final int notificationId = identifier.hashCode();
                    if (_instance.isWifiEnabled()) {
                        _instance.notificationList.add(Integer.valueOf(notificationId));
                        final File destFile = _instance.getApkDirectory(identifier);
                        _instance.downloader.download(url, destFile, new Listener() {
                            public void onComplete(File file) {
                                Log.d(APKDirectDownloadManager.TAG, "download complete :" + file.getAbsolutePath());
                                APKDirectDownloadManager._instance.installApk(destFile);
                                if (!APKDirectDownloadManager.isDownloadTaskRunning()) {
                                    APKDirectDownloadManager.enableNetworkListener(false, APKDirectDownloadManager.getContext());
                                }
                                APKDirectDownloadManager._instance.notificationList.remove(Integer.valueOf(notificationId));
                            }

                            public void onProgress(int progress) {
                                Log.d(APKDirectDownloadManager.TAG, "download progress :" + progress);
                                APKDirectDownloadManager._instance.notifyProgress(notificationId, progress);
                            }

                            public void onError(Throwable throwable) {
                                Log.d(APKDirectDownloadManager.TAG, "download complete :" + throwable.getMessage());
                                if (APKDirectDownloadManager.isDownloadTaskRunning()) {
                                    APKDirectDownloadManager.enableNetworkListener(false, APKDirectDownloadManager.getContext());
                                }
                                APKDirectDownloadManager._instance.dismissNotification(notificationId);
                            }
                        });
                        return;
                    }
                    _instance.notifyProgress(notificationId, -1);
                    _instance.pendingNotificationList.add(Integer.valueOf(notificationId));
                    _instance.pendingDownloadUrl.add(url);
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
            Intent intent = new Intent();
            intent.setClass((Context) _instance.context.get(), VungleWebViewActivity.class);
            intent.putExtra(VungleWebViewActivity.INTENT_URL, url);
            intent.setFlags(DriveFile.MODE_READ_ONLY);
            ((Context) _instance.context.get()).startActivity(intent);
        }
    }

    public static boolean isApkUrl(String url) {
        return MimeTypeMap.getFileExtensionFromUrl(url).toLowerCase().endsWith("apk");
    }

    private void clearDownloadApkCache() {
        new Thread(new C01352()).start();
    }

    private File getApkDirectory(String identifier) throws IllegalStateException {
        File folderDir = new File(getCacheDirectory().getPath() + File.separator);
        if (!folderDir.exists()) {
            folderDir.mkdir();
        }
        return new File(getCacheDirectory().getPath() + File.separator + identifier + "." + "apk");
    }

    private File getCacheDirectory() throws IllegalStateException {
        if (this.context == null) {
            throw new IllegalStateException("Context has expired, cannot continue.");
        }
        File dir = new File(((Context) this.context.get()).getCacheDir().getPath() + File.separator + FOLDER_NAME + File.separator + "apk");
        if (!dir.exists()) {
            dir.mkdir();
        }
        return dir;
    }

    private void installApk(File apkFile) {
        Intent installApkIntent = new Intent();
        installApkIntent.setAction("android.intent.action.VIEW");
        installApkIntent.addCategory("android.intent.category.DEFAULT");
        installApkIntent.setFlags(DriveFile.MODE_READ_ONLY);
        if (VERSION.SDK_INT > 23) {
            installApkIntent.setDataAndType(FileProvider.getUriForFile((Context) this.context.get(), "com.vungle.download.provider", apkFile), "application/vnd.android.package-archive");
            installApkIntent.addFlags(3);
        } else {
            installApkIntent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        }
        if (((Context) this.context.get()).getPackageManager().queryIntentActivities(installApkIntent, 0).size() > 0) {
            ((Context) this.context.get()).startActivity(installApkIntent);
        }
        String name = apkFile.getName();
        String identifier = name.substring(0, name.length() - 4);
        Log.d(TAG, "identifier is" + identifier);
        dismissNotification(identifier.hashCode());
    }

    private void notifyProgress(int notifyId, int progress) {
        Log.d(TAG, "notify id is :" + notifyId + " progress:" + progress);
        if (this.notifyManager == null) {
            Context context = (Context) this.context.get();
            this.notifyManager = (NotificationManager) ((Context) this.context.get()).getSystemService(NotificationTable.TABLE_NAME);
            this.mBuilder = new Builder((Context) this.context.get());
            this.mBuilder.setSmallIcon(17301634);
        }
        if (progress == -1 || !this.isWifiEnabled) {
            this.mBuilder.setContentTitle(LocaleString.getLocaleText(4)).setContentText(LocaleString.getLocaleText(2)).setProgress(0, 0, false);
        } else if (progress < 0 || progress >= 100) {
            this.mBuilder.setContentText(LocaleString.getLocaleText(5)).setProgress(0, 0, false);
        } else {
            this.mBuilder.setContentTitle(LocaleString.getLocaleText(4)).setContentText(LocaleString.getLocaleText(3));
            this.mBuilder.setProgress(100, progress, false);
        }
        this.notifyManager.notify(notifyId, this.mBuilder.build());
    }

    private void dismissNotification(int identifier) {
        if (this.notifyManager != null) {
            this.notifyManager.cancel(identifier);
        }
    }

    private boolean isWifiEnabled() {
        boolean ret = false;
        if (PermissionChecker.checkCallingOrSelfPermission((Context) this.context.get(), "android.permission.ACCESS_NETWORK_STATE") == 0) {
            Context context = (Context) this.context.get();
            NetworkInfo info = ((ConnectivityManager) ((Context) this.context.get()).getSystemService("connectivity")).getActiveNetworkInfo();
            if (info != null) {
                switch (info.getType()) {
                    case 0:
                    case 7:
                    case 9:
                        ret = false;
                        break;
                    case 1:
                    case 6:
                        ret = true;
                        break;
                }
            }
            return false;
        }
        this.isWifiEnabled = ret;
        return ret;
    }

    public static void handleDownload(Context context) {
        boolean isConnected = true;
        if (context != null) {
            if (_instance == null || !isDownloadTaskRunning() || _instance.apkDirectDownloadStatus == 0) {
                enableNetworkListener(false, context);
                return;
            }
            NetworkInfo activeNetwork = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (!(activeNetwork != null && activeNetwork.isConnectedOrConnecting() && activeNetwork.getType() == 1)) {
                isConnected = false;
            }
            if (isConnected && !_instance.isWifiEnabled) {
                resumeDownload();
            } else if (_instance.isWifiEnabled) {
                pauseDownload();
            }
        }
    }

    private static void resumeDownload() {
        _instance.isWifiEnabled = true;
        _instance.downloader.resume();
        if (!_instance.pendingNotificationList.isEmpty()) {
            for (Integer intValue : _instance.pendingNotificationList) {
                _instance.dismissNotification(intValue.intValue());
            }
        }
        if (!_instance.pendingDownloadUrl.isEmpty()) {
            for (String tmpUrl : _instance.pendingDownloadUrl) {
                download(tmpUrl);
            }
            _instance.pendingDownloadUrl.clear();
        }
    }

    private static void pauseDownload() {
        _instance.isWifiEnabled = false;
        _instance.downloader.pause();
        for (Integer intValue : _instance.notificationList) {
            _instance.notifyProgress(intValue.intValue(), -1);
        }
    }

    public static void enableNetworkListener(boolean enabled, Context context) {
        if (_instance.isNetworkReceiverEnabled != enabled && context != null) {
            _instance.isNetworkReceiverEnabled = enabled;
            if (VERSION.SDK_INT >= 24) {
                NetworkCallback networkCallback = new C01363();
                ConnectivityManager connectivityManager = (ConnectivityManager) ((Context) _instance.context.get()).getSystemService("connectivity");
                if (connectivityManager == null) {
                    return;
                }
                if (enabled) {
                    NetworkRequest.Builder builder = new NetworkRequest.Builder();
                    builder.addTransportType(1).addCapability(12);
                    connectivityManager.registerNetworkCallback(builder.build(), networkCallback);
                    return;
                }
                connectivityManager.unregisterNetworkCallback(networkCallback);
            } else if (enabled) {
                NetworkStateReceiver.enableBroadcastReceiver(context, true);
            } else {
                NetworkStateReceiver.enableBroadcastReceiver(context, false);
            }
        }
    }

    @Nullable
    private static Context getContext() {
        return (_instance == null || _instance.context == null) ? null : (Context) _instance.context.get();
    }

    public static boolean isDownloadTaskRunning() {
        return !_instance.pendingDownloadUrl.isEmpty() || _instance.downloader.isDownloadTaskRunning();
    }
}
