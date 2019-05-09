// 
// Decompiled by Procyon v0.5.34
// 

package com.tonyodev.fetch;

import android.support.annotation.Nullable;
import java.io.Serializable;
import android.database.Cursor;
import com.tonyodev.fetch.request.RequestInfo;
import java.util.Iterator;
import android.content.IntentFilter;
import java.io.File;
import com.tonyodev.fetch.exception.EnqueueException;
import android.os.Bundle;
import android.content.Intent;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import android.support.annotation.NonNull;
import android.content.SharedPreferences;
import java.util.List;
import java.util.concurrent.ExecutorService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
import java.util.concurrent.ConcurrentHashMap;

public final class FetchService implements FetchConst
{
    public static final int ACTION_CONCURRENT_DOWNLOADS_LIMIT = 321;
    public static final int ACTION_ENQUEUE = 310;
    public static final int ACTION_LOGGING = 320;
    public static final int ACTION_NETWORK = 314;
    public static final int ACTION_ON_UPDATE_INTERVAL = 323;
    public static final int ACTION_PAUSE = 311;
    public static final int ACTION_PRIORITY = 317;
    public static final int ACTION_PROCESS_PENDING = 315;
    public static final int ACTION_QUERY = 316;
    public static final int ACTION_REMOVE = 313;
    public static final int ACTION_REMOVE_ALL = 319;
    public static final int ACTION_REMOVE_REQUEST = 324;
    public static final int ACTION_REMOVE_REQUEST_ALL = 325;
    public static final int ACTION_RESUME = 312;
    public static final int ACTION_RETRY = 318;
    public static final String ACTION_TYPE = "com.tonyodev.fetch.action_type";
    public static final int ACTION_UPDATE_REQUEST_URL = 322;
    public static final String EVENT_ACTION_ENQUEUED = "com.tonyodev.fetch.event_action_enqueued";
    public static final String EVENT_ACTION_ENQUEUE_FAILED = "com.tonyodev.fetch.event_action_enqueue_failed";
    public static final String EVENT_ACTION_QUERY = "com.tonyodev.fetch.event_action_query";
    public static final String EVENT_ACTION_UPDATE = "com.tonyodev.fetch.event_action_update";
    public static final String EXTRA_CONCURRENT_DOWNLOADS_LIMIT = "com.tonyodev.fetch.extra_concurrent_download_limit";
    public static final String EXTRA_DOWNLOADED_BYTES = "com.tonyodev.fetch.extra_downloaded_bytes";
    public static final String EXTRA_ERROR = "com.tonyodev.fetch.extra_error";
    public static final String EXTRA_FILE_PATH = "com.tonyodev.fetch.extra_file_path";
    public static final String EXTRA_FILE_SIZE = "com.tonyodev.fetch.extra_file_size";
    public static final String EXTRA_HEADERS = "com.tonyodev.fetch.extra_headers";
    public static final String EXTRA_HEADER_NAME = "com.tonyodev.fetch.extra_header_name";
    public static final String EXTRA_HEADER_VALUE = "com.tonyodev.fetch.extra_header_value";
    public static final String EXTRA_ID = "com.tonyodev.fetch.extra_id";
    public static final String EXTRA_LOGGING_ID = "com.tonyodev.fetch.extra_logging_id";
    public static final String EXTRA_NETWORK_ID = "com.tonyodev.fetch.extra_network_id";
    public static final String EXTRA_ON_UPDATE_INTERVAL = "com.tonyodev.fetch.extra_on_update_interval";
    public static final String EXTRA_PRIORITY = "com.tonyodev.fetch.extra_priority";
    public static final String EXTRA_PROGRESS = "com.tonyodev.fetch.extra_progress";
    public static final String EXTRA_QUERY_ID = "com.tonyodev.fetch.extra_query_id";
    public static final String EXTRA_QUERY_RESULT = "com.tonyodev.fetch.extra_query_result";
    public static final String EXTRA_QUERY_TYPE = "com.tonyodev.fetch.extra_query_type";
    public static final String EXTRA_STATUS = "com.tonyodev.fetch.extra_status";
    public static final String EXTRA_URL = "com.tonyodev.fetch.extra_url";
    public static final int QUERY_ALL = 481;
    public static final int QUERY_BY_STATUS = 482;
    public static final int QUERY_SINGLE = 480;
    private static final String SHARED_PREFERENCES = "com.tonyodev.fetch.shared_preferences";
    private static FetchService fetchService;
    private final ConcurrentHashMap<Long, FetchRunnable> activeDownloads;
    private final LocalBroadcastManager broadcastManager;
    private final Context context;
    private final DatabaseHelper databaseHelper;
    private final BroadcastReceiver doneReceiver;
    private int downloadsLimit;
    private final ExecutorService executor;
    private boolean loggingEnabled;
    private long onUpdateInterval;
    private int preferredNetwork;
    private final List<BroadcastReceiver> registeredReceivers;
    private volatile boolean runningTask;
    private final SharedPreferences sharedPreferences;
    private volatile boolean shuttingDown;
    
    private FetchService(@NonNull final Context context) {
        this.executor = Executors.newSingleThreadExecutor();
        this.registeredReceivers = new ArrayList<BroadcastReceiver>();
        this.activeDownloads = new ConcurrentHashMap<Long, FetchRunnable>();
        this.runningTask = false;
        this.shuttingDown = false;
        this.downloadsLimit = 1;
        this.loggingEnabled = true;
        this.onUpdateInterval = 2000L;
        this.preferredNetwork = 200;
        this.doneReceiver = new BroadcastReceiver() {
            public void onReceive(final Context context, final Intent intent) {
                if (intent != null) {
                    final long idFromIntent = FetchRunnable.getIdFromIntent(intent);
                    if (FetchService.this.activeDownloads.containsKey(idFromIntent)) {
                        FetchService.this.activeDownloads.remove(idFromIntent);
                    }
                    FetchService.this.startDownload();
                }
            }
        };
        this.context = context.getApplicationContext();
        this.broadcastManager = LocalBroadcastManager.getInstance(context);
        this.sharedPreferences = this.context.getSharedPreferences("com.tonyodev.fetch.shared_preferences", 0);
        this.databaseHelper = DatabaseHelper.getInstance(context);
        this.broadcastManager.registerReceiver(this.doneReceiver, FetchRunnable.getDoneFilter());
        this.registeredReceivers.add(this.doneReceiver);
        this.downloadsLimit = this.getDownloadsLimit();
        this.preferredNetwork = this.getAllowedNetwork();
        this.loggingEnabled = this.isLoggingEnabled();
        this.onUpdateInterval = this.getOnUpdateInterval();
        this.databaseHelper.setLoggingEnabled(this.loggingEnabled);
        if (!this.executor.isShutdown()) {
            this.executor.execute(new Runnable() {
                @Override
                public void run() {
                    FetchService.this.databaseHelper.clean();
                    FetchService.this.databaseHelper.verifyOK();
                }
            });
        }
    }
    
    private void enqueue(final String s, final String s2, ArrayList<Bundle> list, final int n) {
        long generateRequestId = 0L;
        Label_0089: {
            if (s != null) {
                if (s2 != null) {
                    break Label_0089;
                }
            }
            try {
                throw new EnqueueException("Request was not properly formatted. url:" + s + ", filePath:" + s2, -116);
            }
            catch (EnqueueException list2) {
                try {
                    if (this.loggingEnabled) {
                        ((Throwable)list2).printStackTrace();
                    }
                    this.sendEnqueueEvent("com.tonyodev.fetch.event_action_enqueue_failed", -1L, s, s2, -900, list, n, ((EnqueueException)list2).getErrorCode());
                    return;
                    // iftrue(Label_0105:, list2 = list != null)
                    // iftrue(Label_0211:, this.databaseHelper.insert(generateRequestId, s, s2, 900, bundleListToHeaderString, length, 0L, n, -1))
                    Block_6: {
                        break Block_6;
                        Label_0160: {
                            list = list2;
                        }
                        list = list2;
                        throw new EnqueueException("could not enqueue request", -117);
                    }
                    list2 = new ArrayList<Bundle>();
                    Label_0105: {
                        list = list2;
                    }
                    generateRequestId = Utils.generateRequestId();
                    list = list2;
                    final String bundleListToHeaderString = Utils.bundleListToHeaderString(list2, this.loggingEnabled);
                    long length = 0L;
                    list = list2;
                    final File file = Utils.getFile(s2);
                    list = list2;
                    // iftrue(Label_0160:, !file.exists())
                    list = list2;
                    length = file.length();
                }
                finally {
                    this.startDownload();
                }
            }
        }
        Label_0211: {
            final ArrayList<Bundle> list2;
            final String s3;
            this.sendEnqueueEvent("com.tonyodev.fetch.event_action_enqueued", generateRequestId, s3, s2, 900, list2, n, -1);
        }
        this.startDownload();
    }
    
    private int getAllowedNetwork() {
        return this.sharedPreferences.getInt("com.tonyodev.fetch.extra_network_id", 200);
    }
    
    private int getDownloadsLimit() {
        return this.sharedPreferences.getInt("com.tonyodev.fetch.extra_concurrent_download_limit", 1);
    }
    
    @NonNull
    public static IntentFilter getEventEnqueueFailedFilter() {
        return new IntentFilter("com.tonyodev.fetch.event_action_enqueue_failed");
    }
    
    @NonNull
    public static IntentFilter getEventEnqueuedFilter() {
        return new IntentFilter("com.tonyodev.fetch.event_action_enqueued");
    }
    
    @NonNull
    public static IntentFilter getEventQueryFilter() {
        return new IntentFilter("com.tonyodev.fetch.event_action_query");
    }
    
    @NonNull
    public static IntentFilter getEventUpdateFilter() {
        return new IntentFilter("com.tonyodev.fetch.event_action_update");
    }
    
    public static FetchService getInstance(@NonNull final Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context cannot be null");
        }
        if (FetchService.fetchService == null || FetchService.fetchService.shuttingDown) {
            FetchService.fetchService = new FetchService(context);
        }
        return FetchService.fetchService;
    }
    
    private long getOnUpdateInterval() {
        return this.onUpdateInterval = this.sharedPreferences.getLong("com.tonyodev.fetch.extra_on_update_interval", 2000L);
    }
    
    private void interruptActiveDownload(final long n) {
        if (this.activeDownloads.containsKey(n)) {
            final FetchRunnable fetchRunnable = this.activeDownloads.get(n);
            if (fetchRunnable != null) {
                fetchRunnable.interrupt();
            }
        }
    }
    
    private void interruptActiveDownloads() {
        final Iterator<Long> iterator = this.activeDownloads.keySet().iterator();
        while (iterator.hasNext()) {
            final FetchRunnable fetchRunnable = this.activeDownloads.get(iterator.next());
            if (fetchRunnable != null) {
                fetchRunnable.interrupt();
            }
        }
    }
    
    private boolean isLoggingEnabled() {
        return this.sharedPreferences.getBoolean("com.tonyodev.fetch.extra_logging_id", true);
    }
    
    static boolean isLoggingEnabled(final Context context) {
        return context.getSharedPreferences("com.tonyodev.fetch.shared_preferences", 0).getBoolean("com.tonyodev.fetch.extra_logging_id", true);
    }
    
    private void pause(final long n) {
        if (this.activeDownloads.containsKey(n)) {
            this.runningTask = true;
            final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
                public void onReceive(final Context context, final Intent intent) {
                    if (FetchRunnable.getIdFromIntent(intent) == n) {
                        FetchService.this.pauseAction(n);
                        FetchService.this.broadcastManager.unregisterReceiver((BroadcastReceiver)this);
                        FetchService.this.registeredReceivers.remove(this);
                        FetchService.this.runningTask = false;
                        FetchService.this.startDownload();
                    }
                }
            };
            this.registeredReceivers.add(broadcastReceiver);
            this.broadcastManager.registerReceiver((BroadcastReceiver)broadcastReceiver, FetchRunnable.getDoneFilter());
            this.interruptActiveDownload(n);
            return;
        }
        this.pauseAction(n);
        this.startDownload();
    }
    
    private void pauseAction(final long n) {
        if (this.databaseHelper.pause(n)) {
            final RequestInfo cursorToRequestInfo = Utils.cursorToRequestInfo(this.databaseHelper.get(n), true, this.loggingEnabled);
            if (cursorToRequestInfo != null) {
                Utils.sendEventUpdate(this.broadcastManager, cursorToRequestInfo.getId(), cursorToRequestInfo.getStatus(), cursorToRequestInfo.getProgress(), cursorToRequestInfo.getDownloadedBytes(), cursorToRequestInfo.getFileSize(), cursorToRequestInfo.getError());
            }
        }
    }
    
    private void processAction(final Bundle bundle) {
        try {
            if (!this.executor.isShutdown()) {
                this.executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        FetchService.this.databaseHelper.clean();
                        final long long1 = bundle.getLong("com.tonyodev.fetch.extra_id", -1L);
                        switch (bundle.getInt("com.tonyodev.fetch.action_type", -1)) {
                            default: {
                                FetchService.this.startDownload();
                            }
                            case 311: {
                                FetchService.this.pause(long1);
                            }
                            case 313: {
                                FetchService.this.remove(long1);
                            }
                            case 312: {
                                FetchService.this.resume(long1);
                            }
                            case 310: {
                                FetchService.this.enqueue(bundle.getString("com.tonyodev.fetch.extra_url"), bundle.getString("com.tonyodev.fetch.extra_file_path"), bundle.getParcelableArrayList("com.tonyodev.fetch.extra_headers"), bundle.getInt("com.tonyodev.fetch.extra_priority", 600));
                            }
                            case 314: {
                                FetchService.this.setAllowedNetwork(bundle.getInt("com.tonyodev.fetch.extra_network_id", 200));
                            }
                            case 320: {
                                FetchService.this.setLoggingEnabled(bundle.getBoolean("com.tonyodev.fetch.extra_logging_id", true));
                            }
                            case 315: {
                                FetchService.this.startDownload();
                            }
                            case 316: {
                                FetchService.this.query(bundle.getInt("com.tonyodev.fetch.extra_query_type", 481), bundle.getLong("com.tonyodev.fetch.extra_query_id", -1L), long1, bundle.getInt("com.tonyodev.fetch.extra_status", -1));
                            }
                            case 317: {
                                FetchService.this.setRequestPriority(long1, bundle.getInt("com.tonyodev.fetch.extra_priority", 600));
                            }
                            case 318: {
                                FetchService.this.retry(long1);
                            }
                            case 319: {
                                FetchService.this.removeAll();
                            }
                            case 321: {
                                FetchService.this.setDownloadsLimit(bundle.getInt("com.tonyodev.fetch.extra_concurrent_download_limit", 1));
                            }
                            case 323: {
                                FetchService.this.setOnUpdateInterval(bundle.getLong("com.tonyodev.fetch.extra_on_update_interval", 2000L));
                            }
                            case 322: {
                                FetchService.this.updateRequestUrl(long1, bundle.getString("com.tonyodev.fetch.extra_url"));
                            }
                            case 324: {
                                FetchService.this.removeRequest(long1);
                            }
                            case 325: {
                                FetchService.this.removeRequestAll();
                            }
                        }
                    }
                });
            }
        }
        catch (Exception ex) {
            if (this.loggingEnabled) {
                ex.printStackTrace();
            }
        }
    }
    
    public static void processPendingRequests(@NonNull final Context context) {
        if (context == null) {
            throw new NullPointerException("Context cannot be null");
        }
        final Bundle bundle = new Bundle();
        bundle.putInt("com.tonyodev.fetch.action_type", 315);
        getInstance(context).runAction(bundle);
    }
    
    private void query(final int n, final long n2, final long n3, final int n4) {
        Cursor cursor = null;
        switch (n) {
            default: {
                cursor = this.databaseHelper.get();
                break;
            }
            case 480: {
                cursor = this.databaseHelper.get(n3);
                break;
            }
            case 482: {
                cursor = this.databaseHelper.getByStatus(n4);
                break;
            }
        }
        this.sendEventQuery(n2, Utils.cursorToQueryResultList(cursor, true, this.loggingEnabled));
        this.startDownload();
    }
    
    private void remove(final long n) {
        if (this.activeDownloads.containsKey(n)) {
            this.runningTask = true;
            final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
                public void onReceive(final Context context, final Intent intent) {
                    if (FetchRunnable.getIdFromIntent(intent) == n) {
                        FetchService.this.removeAction(n);
                        FetchService.this.broadcastManager.unregisterReceiver((BroadcastReceiver)this);
                        FetchService.this.registeredReceivers.remove(this);
                        FetchService.this.runningTask = false;
                        FetchService.this.startDownload();
                    }
                }
            };
            this.registeredReceivers.add(broadcastReceiver);
            this.broadcastManager.registerReceiver((BroadcastReceiver)broadcastReceiver, FetchRunnable.getDoneFilter());
            this.interruptActiveDownload(n);
            return;
        }
        this.removeAction(n);
        this.startDownload();
    }
    
    private void removeAction(final long n) {
        final RequestInfo cursorToRequestInfo = Utils.cursorToRequestInfo(this.databaseHelper.get(n), true, this.loggingEnabled);
        if (cursorToRequestInfo != null && this.databaseHelper.delete(n)) {
            Utils.deleteFile(cursorToRequestInfo.getFilePath());
            Utils.sendEventUpdate(this.broadcastManager, n, 905, 0, 0L, 0L, -1);
        }
    }
    
    private void removeAll() {
        if (this.activeDownloads.size() > 0) {
            this.runningTask = true;
            final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
                public void onReceive(final Context context, final Intent intent) {
                    if (intent != null) {
                        FetchService.this.removeAction(FetchRunnable.getIdFromIntent(intent));
                    }
                    if (FetchService.this.activeDownloads.size() == 0) {
                        FetchService.this.removeAllAction();
                        FetchService.this.broadcastManager.unregisterReceiver((BroadcastReceiver)this);
                        FetchService.this.registeredReceivers.remove(this);
                        FetchService.this.runningTask = false;
                        FetchService.this.startDownload();
                    }
                }
            };
            this.registeredReceivers.add(broadcastReceiver);
            this.broadcastManager.registerReceiver((BroadcastReceiver)broadcastReceiver, FetchRunnable.getDoneFilter());
            this.interruptActiveDownloads();
            return;
        }
        this.removeAllAction();
        this.startDownload();
    }
    
    private void removeAllAction() {
        final List<RequestInfo> cursorToRequestInfoList = Utils.cursorToRequestInfoList(this.databaseHelper.get(), true, this.loggingEnabled);
        if (cursorToRequestInfoList != null && this.databaseHelper.deleteAll()) {
            for (final RequestInfo requestInfo : cursorToRequestInfoList) {
                Utils.deleteFile(requestInfo.getFilePath());
                Utils.sendEventUpdate(this.broadcastManager, requestInfo.getId(), 905, 0, 0L, 0L, -1);
            }
        }
    }
    
    private void removeRequest(final long n) {
        if (this.activeDownloads.containsKey(n)) {
            this.runningTask = true;
            final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
                public void onReceive(final Context context, final Intent intent) {
                    if (FetchRunnable.getIdFromIntent(intent) == n) {
                        FetchService.this.removeRequestAction(n);
                        FetchService.this.broadcastManager.unregisterReceiver((BroadcastReceiver)this);
                        FetchService.this.registeredReceivers.remove(this);
                        FetchService.this.runningTask = false;
                        FetchService.this.startDownload();
                    }
                }
            };
            this.registeredReceivers.add(broadcastReceiver);
            this.broadcastManager.registerReceiver((BroadcastReceiver)broadcastReceiver, FetchRunnable.getDoneFilter());
            this.interruptActiveDownload(n);
            return;
        }
        this.removeRequestAction(n);
        this.startDownload();
    }
    
    private void removeRequestAction(final long n) {
        final RequestInfo cursorToRequestInfo = Utils.cursorToRequestInfo(this.databaseHelper.get(n), true, this.loggingEnabled);
        if (cursorToRequestInfo != null && this.databaseHelper.delete(n)) {
            Utils.sendEventUpdate(this.broadcastManager, n, 905, cursorToRequestInfo.getProgress(), cursorToRequestInfo.getDownloadedBytes(), cursorToRequestInfo.getFileSize(), -1);
        }
    }
    
    private void removeRequestAll() {
        if (this.activeDownloads.size() > 0) {
            this.runningTask = true;
            final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
                public void onReceive(final Context context, final Intent intent) {
                    if (intent != null) {
                        FetchService.this.removeRequestAction(FetchRunnable.getIdFromIntent(intent));
                    }
                    if (FetchService.this.activeDownloads.size() == 0) {
                        FetchService.this.removeRequestAllAction();
                        FetchService.this.broadcastManager.unregisterReceiver((BroadcastReceiver)this);
                        FetchService.this.registeredReceivers.remove(this);
                        FetchService.this.runningTask = false;
                        FetchService.this.startDownload();
                    }
                }
            };
            this.registeredReceivers.add(broadcastReceiver);
            this.broadcastManager.registerReceiver((BroadcastReceiver)broadcastReceiver, FetchRunnable.getDoneFilter());
            this.interruptActiveDownloads();
            return;
        }
        this.removeRequestAllAction();
        this.startDownload();
    }
    
    private void removeRequestAllAction() {
        final List<RequestInfo> cursorToRequestInfoList = Utils.cursorToRequestInfoList(this.databaseHelper.get(), true, this.loggingEnabled);
        if (cursorToRequestInfoList != null && this.databaseHelper.deleteAll()) {
            for (final RequestInfo requestInfo : cursorToRequestInfoList) {
                Utils.sendEventUpdate(this.broadcastManager, requestInfo.getId(), 905, requestInfo.getProgress(), requestInfo.getDownloadedBytes(), requestInfo.getFileSize(), -1);
            }
        }
    }
    
    private void resume(final long n) {
        if (this.activeDownloads.containsKey(n)) {
            return;
        }
        if (this.databaseHelper.resume(n)) {
            final RequestInfo cursorToRequestInfo = Utils.cursorToRequestInfo(this.databaseHelper.get(n), true, this.loggingEnabled);
            if (cursorToRequestInfo != null) {
                Utils.sendEventUpdate(this.broadcastManager, cursorToRequestInfo.getId(), cursorToRequestInfo.getStatus(), cursorToRequestInfo.getProgress(), cursorToRequestInfo.getDownloadedBytes(), cursorToRequestInfo.getFileSize(), cursorToRequestInfo.getError());
            }
        }
        this.startDownload();
    }
    
    private void retry(final long n) {
        if (this.activeDownloads.containsKey(n)) {
            return;
        }
        if (this.databaseHelper.retry(n)) {
            final RequestInfo cursorToRequestInfo = Utils.cursorToRequestInfo(this.databaseHelper.get(n), true, this.loggingEnabled);
            if (cursorToRequestInfo != null) {
                Utils.sendEventUpdate(this.broadcastManager, cursorToRequestInfo.getId(), cursorToRequestInfo.getStatus(), cursorToRequestInfo.getProgress(), cursorToRequestInfo.getDownloadedBytes(), cursorToRequestInfo.getFileSize(), cursorToRequestInfo.getError());
            }
        }
        this.startDownload();
    }
    
    private void sendEnqueueEvent(final String s, final long n, final String s2, final String s3, final int n2, final ArrayList<Bundle> list, final int n3, final int n4) {
        final Intent intent = new Intent(s);
        intent.putExtra("com.tonyodev.fetch.extra_id", n);
        intent.putExtra("com.tonyodev.fetch.extra_status", n2);
        intent.putExtra("com.tonyodev.fetch.extra_url", s2);
        intent.putExtra("com.tonyodev.fetch.extra_file_path", s3);
        intent.putExtra("com.tonyodev.fetch.extra_headers", (Serializable)list);
        intent.putExtra("com.tonyodev.fetch.extra_progress", 0);
        intent.putExtra("com.tonyodev.fetch.extra_file_size", 0L);
        intent.putExtra("com.tonyodev.fetch.extra_error", n4);
        intent.putExtra("com.tonyodev.fetch.extra_priority", n3);
        this.broadcastManager.sendBroadcast(intent);
    }
    
    private void sendEventQuery(final long n, final ArrayList<Bundle> list) {
        final Intent intent = new Intent("com.tonyodev.fetch.event_action_query");
        intent.putExtra("com.tonyodev.fetch.extra_query_id", n);
        intent.putExtra("com.tonyodev.fetch.extra_query_result", (Serializable)list);
        this.broadcastManager.sendBroadcast(intent);
    }
    
    public static void sendToService(@NonNull final Context context, @Nullable final Bundle bundle) {
        if (context == null) {
            throw new NullPointerException("Context cannot be null");
        }
        Bundle bundle2;
        if ((bundle2 = bundle) == null) {
            bundle2 = new Bundle();
        }
        getInstance(context).runAction(bundle2);
    }
    
    private void setAllowedNetwork(final int preferredNetwork) {
        this.preferredNetwork = preferredNetwork;
        this.sharedPreferences.edit().putInt("com.tonyodev.fetch.extra_network_id", preferredNetwork).apply();
        if (this.activeDownloads.size() > 0) {
            this.interruptActiveDownloads();
        }
        this.startDownload();
    }
    
    private void setDownloadsLimit(final int n) {
        int downloadsLimit = n;
        if (n < 1) {
            downloadsLimit = 1;
        }
        this.downloadsLimit = downloadsLimit;
        this.sharedPreferences.edit().putInt("com.tonyodev.fetch.extra_concurrent_download_limit", downloadsLimit).apply();
        if (this.activeDownloads.size() > 0) {
            this.interruptActiveDownloads();
        }
        this.startDownload();
    }
    
    private void setLoggingEnabled(final boolean loggingEnabled) {
        this.loggingEnabled = loggingEnabled;
        this.sharedPreferences.edit().putBoolean("com.tonyodev.fetch.extra_logging_id", loggingEnabled).apply();
        this.databaseHelper.setLoggingEnabled(this.loggingEnabled);
        this.startDownload();
    }
    
    private void setOnUpdateInterval(final long onUpdateInterval) {
        this.onUpdateInterval = onUpdateInterval;
        this.sharedPreferences.edit().putLong("com.tonyodev.fetch.extra_on_update_interval", onUpdateInterval).apply();
        if (this.activeDownloads.size() > 0) {
            this.interruptActiveDownloads();
        }
        this.startDownload();
    }
    
    private void setRequestPriority(final long n, final int n2) {
        if (this.databaseHelper.setPriority(n, n2) && this.activeDownloads.size() > 0) {
            this.interruptActiveDownloads();
        }
        this.startDownload();
    }
    
    private void startDownload() {
        while (true) {
            boolean b = false;
            Label_0087: {
                synchronized (this) {
                    if (!this.shuttingDown) {
                        b = this.runningTask;
                        if (!b) {
                            b = Utils.isNetworkAvailable(this.context);
                            final boolean onWiFi = Utils.isOnWiFi(this.context);
                            if ((b && (this.preferredNetwork != 201 || onWiFi)) || this.activeDownloads.size() <= 0) {
                                break Label_0087;
                            }
                            this.runningTask = true;
                            this.interruptActiveDownloads();
                            this.runningTask = false;
                        }
                    }
                    return;
                }
            }
            if (b && !this.runningTask && this.activeDownloads.size() < this.downloadsLimit && this.databaseHelper.hasPendingRequests()) {
                this.runningTask = true;
                while (true) {
                    try {
                        final Cursor nextPendingRequest = this.databaseHelper.getNextPendingRequest();
                        if (nextPendingRequest != null && !nextPendingRequest.isClosed() && nextPendingRequest.getCount() > 0) {
                            final RequestInfo cursorToRequestInfo = Utils.cursorToRequestInfo(nextPendingRequest, true, this.loggingEnabled);
                            final FetchRunnable fetchRunnable = new FetchRunnable(this.context, cursorToRequestInfo.getId(), cursorToRequestInfo.getUrl(), cursorToRequestInfo.getFilePath(), cursorToRequestInfo.getHeaders(), cursorToRequestInfo.getFileSize(), this.loggingEnabled, this.onUpdateInterval);
                            this.databaseHelper.updateStatus(cursorToRequestInfo.getId(), 901, -1);
                            this.activeDownloads.put(fetchRunnable.getId(), fetchRunnable);
                            new Thread(fetchRunnable).start();
                        }
                        this.runningTask = false;
                        if (this.activeDownloads.size() < this.downloadsLimit && this.databaseHelper.hasPendingRequests()) {
                            this.startDownload();
                        }
                        return;
                    }
                    catch (Exception ex) {
                        if (this.loggingEnabled) {
                            ex.printStackTrace();
                        }
                        continue;
                    }
                    break;
                }
            }
            if (!this.runningTask && this.activeDownloads.size() == 0 && !this.databaseHelper.hasPendingRequests()) {
                this.shuttingDown = true;
                this.shutdown();
            }
        }
    }
    
    private void updateRequestUrl(final long n, final String s) {
        if (this.activeDownloads.containsKey(n)) {
            this.runningTask = true;
            final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
                public void onReceive(final Context context, final Intent intent) {
                    if (FetchRunnable.getIdFromIntent(intent) == n) {
                        FetchService.this.updateRequestUrlAction(n, s);
                        FetchService.this.broadcastManager.unregisterReceiver((BroadcastReceiver)this);
                        FetchService.this.registeredReceivers.remove(this);
                        FetchService.this.runningTask = false;
                        FetchService.this.startDownload();
                    }
                }
            };
            this.registeredReceivers.add(broadcastReceiver);
            this.broadcastManager.registerReceiver((BroadcastReceiver)broadcastReceiver, FetchRunnable.getDoneFilter());
            this.interruptActiveDownload(n);
            return;
        }
        this.updateRequestUrlAction(n, s);
        this.startDownload();
    }
    
    private void updateRequestUrlAction(final long n, final String s) {
        this.databaseHelper.updateUrl(n, s);
        this.databaseHelper.retry(n);
    }
    
    public void runAction(@NonNull final Bundle bundle) {
        if (bundle == null) {
            throw new IllegalArgumentException("Bundle cannot be null");
        }
        this.processAction(bundle);
    }
    
    public void shutdown() {
        this.shuttingDown = true;
        if (!this.executor.isShutdown()) {
            this.executor.shutdown();
        }
        this.interruptActiveDownloads();
        final Iterator<BroadcastReceiver> iterator = this.registeredReceivers.iterator();
        while (iterator.hasNext()) {
            this.broadcastManager.unregisterReceiver((BroadcastReceiver)iterator.next());
        }
        this.registeredReceivers.clear();
    }
}
