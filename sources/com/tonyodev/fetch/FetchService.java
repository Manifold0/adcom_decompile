package com.tonyodev.fetch;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import com.tonyodev.fetch.exception.EnqueueException;
import com.tonyodev.fetch.request.RequestInfo;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class FetchService implements FetchConst {
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
    private final ConcurrentHashMap<Long, FetchRunnable> activeDownloads = new ConcurrentHashMap();
    private final LocalBroadcastManager broadcastManager;
    private final Context context;
    private final DatabaseHelper databaseHelper;
    private final BroadcastReceiver doneReceiver = new C10949();
    private int downloadsLimit = 1;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private boolean loggingEnabled = true;
    private long onUpdateInterval = FetchConst.DEFAULT_ON_UPDATE_INTERVAL;
    private int preferredNetwork = 200;
    private final List<BroadcastReceiver> registeredReceivers = new ArrayList();
    private volatile boolean runningTask = false;
    private final SharedPreferences sharedPreferences;
    private volatile boolean shuttingDown = false;

    /* renamed from: com.tonyodev.fetch.FetchService$1 */
    class C10861 implements Runnable {
        C10861() {
        }

        public void run() {
            FetchService.this.databaseHelper.clean();
            FetchService.this.databaseHelper.verifyOK();
        }
    }

    /* renamed from: com.tonyodev.fetch.FetchService$5 */
    class C10905 extends BroadcastReceiver {
        C10905() {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                FetchService.this.removeAction(FetchRunnable.getIdFromIntent(intent));
            }
            if (FetchService.this.activeDownloads.size() == 0) {
                FetchService.this.removeAllAction();
                FetchService.this.broadcastManager.unregisterReceiver(this);
                FetchService.this.registeredReceivers.remove(this);
                FetchService.this.runningTask = false;
                FetchService.this.startDownload();
            }
        }
    }

    /* renamed from: com.tonyodev.fetch.FetchService$6 */
    class C10916 extends BroadcastReceiver {
        C10916() {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                FetchService.this.removeRequestAction(FetchRunnable.getIdFromIntent(intent));
            }
            if (FetchService.this.activeDownloads.size() == 0) {
                FetchService.this.removeRequestAllAction();
                FetchService.this.broadcastManager.unregisterReceiver(this);
                FetchService.this.registeredReceivers.remove(this);
                FetchService.this.runningTask = false;
                FetchService.this.startDownload();
            }
        }
    }

    /* renamed from: com.tonyodev.fetch.FetchService$9 */
    class C10949 extends BroadcastReceiver {
        C10949() {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                long id = FetchRunnable.getIdFromIntent(intent);
                if (FetchService.this.activeDownloads.containsKey(Long.valueOf(id))) {
                    FetchService.this.activeDownloads.remove(Long.valueOf(id));
                }
                FetchService.this.startDownload();
            }
        }
    }

    public static void sendToService(@NonNull Context context, @Nullable Bundle extras) {
        if (context == null) {
            throw new NullPointerException("Context cannot be null");
        }
        if (extras == null) {
            extras = new Bundle();
        }
        getInstance(context).runAction(extras);
    }

    public static void processPendingRequests(@NonNull Context context) {
        if (context == null) {
            throw new NullPointerException("Context cannot be null");
        }
        Bundle bundle = new Bundle();
        bundle.putInt(ACTION_TYPE, ACTION_PROCESS_PENDING);
        getInstance(context).runAction(bundle);
    }

    public static FetchService getInstance(@NonNull Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context cannot be null");
        }
        if (fetchService == null || fetchService.shuttingDown) {
            fetchService = new FetchService(context);
        }
        return fetchService;
    }

    @NonNull
    public static IntentFilter getEventEnqueuedFilter() {
        return new IntentFilter(EVENT_ACTION_ENQUEUED);
    }

    @NonNull
    public static IntentFilter getEventEnqueueFailedFilter() {
        return new IntentFilter(EVENT_ACTION_ENQUEUE_FAILED);
    }

    @NonNull
    public static IntentFilter getEventUpdateFilter() {
        return new IntentFilter(EVENT_ACTION_UPDATE);
    }

    @NonNull
    public static IntentFilter getEventQueryFilter() {
        return new IntentFilter(EVENT_ACTION_QUERY);
    }

    private FetchService(@NonNull Context context) {
        this.context = context.getApplicationContext();
        this.broadcastManager = LocalBroadcastManager.getInstance(context);
        this.sharedPreferences = this.context.getSharedPreferences(SHARED_PREFERENCES, 0);
        this.databaseHelper = DatabaseHelper.getInstance(context);
        this.broadcastManager.registerReceiver(this.doneReceiver, FetchRunnable.getDoneFilter());
        this.registeredReceivers.add(this.doneReceiver);
        this.downloadsLimit = getDownloadsLimit();
        this.preferredNetwork = getAllowedNetwork();
        this.loggingEnabled = isLoggingEnabled();
        this.onUpdateInterval = getOnUpdateInterval();
        this.databaseHelper.setLoggingEnabled(this.loggingEnabled);
        if (!this.executor.isShutdown()) {
            this.executor.execute(new C10861());
        }
    }

    public void runAction(@NonNull Bundle bundle) {
        if (bundle == null) {
            throw new IllegalArgumentException("Bundle cannot be null");
        }
        processAction(bundle);
    }

    public void shutdown() {
        this.shuttingDown = true;
        if (!this.executor.isShutdown()) {
            this.executor.shutdown();
        }
        interruptActiveDownloads();
        for (BroadcastReceiver registeredReceiver : this.registeredReceivers) {
            this.broadcastManager.unregisterReceiver(registeredReceiver);
        }
        this.registeredReceivers.clear();
    }

    private void processAction(final Bundle bundle) {
        try {
            if (!this.executor.isShutdown()) {
                this.executor.execute(new Runnable() {
                    public void run() {
                        FetchService.this.databaseHelper.clean();
                        long id = bundle.getLong(FetchService.EXTRA_ID, -1);
                        switch (bundle.getInt(FetchService.ACTION_TYPE, -1)) {
                            case FetchService.ACTION_ENQUEUE /*310*/:
                                FetchService.this.enqueue(bundle.getString(FetchService.EXTRA_URL), bundle.getString(FetchService.EXTRA_FILE_PATH), bundle.getParcelableArrayList(FetchService.EXTRA_HEADERS), bundle.getInt(FetchService.EXTRA_PRIORITY, 600));
                                return;
                            case FetchService.ACTION_PAUSE /*311*/:
                                FetchService.this.pause(id);
                                return;
                            case FetchService.ACTION_RESUME /*312*/:
                                FetchService.this.resume(id);
                                return;
                            case FetchService.ACTION_REMOVE /*313*/:
                                FetchService.this.remove(id);
                                return;
                            case FetchService.ACTION_NETWORK /*314*/:
                                FetchService.this.setAllowedNetwork(bundle.getInt(FetchService.EXTRA_NETWORK_ID, 200));
                                return;
                            case FetchService.ACTION_PROCESS_PENDING /*315*/:
                                FetchService.this.startDownload();
                                return;
                            case FetchService.ACTION_QUERY /*316*/:
                                long queryId = bundle.getLong(FetchService.EXTRA_QUERY_ID, -1);
                                FetchService.this.query(bundle.getInt(FetchService.EXTRA_QUERY_TYPE, FetchService.QUERY_ALL), queryId, id, bundle.getInt(FetchService.EXTRA_STATUS, -1));
                                return;
                            case FetchService.ACTION_PRIORITY /*317*/:
                                FetchService.this.setRequestPriority(id, bundle.getInt(FetchService.EXTRA_PRIORITY, 600));
                                return;
                            case FetchService.ACTION_RETRY /*318*/:
                                FetchService.this.retry(id);
                                return;
                            case FetchService.ACTION_REMOVE_ALL /*319*/:
                                FetchService.this.removeAll();
                                return;
                            case FetchService.ACTION_LOGGING /*320*/:
                                FetchService.this.setLoggingEnabled(bundle.getBoolean(FetchService.EXTRA_LOGGING_ID, true));
                                return;
                            case FetchService.ACTION_CONCURRENT_DOWNLOADS_LIMIT /*321*/:
                                FetchService.this.setDownloadsLimit(bundle.getInt(FetchService.EXTRA_CONCURRENT_DOWNLOADS_LIMIT, 1));
                                return;
                            case FetchService.ACTION_UPDATE_REQUEST_URL /*322*/:
                                FetchService.this.updateRequestUrl(id, bundle.getString(FetchService.EXTRA_URL));
                                return;
                            case FetchService.ACTION_ON_UPDATE_INTERVAL /*323*/:
                                FetchService.this.setOnUpdateInterval(bundle.getLong(FetchService.EXTRA_ON_UPDATE_INTERVAL, FetchConst.DEFAULT_ON_UPDATE_INTERVAL));
                                return;
                            case FetchService.ACTION_REMOVE_REQUEST /*324*/:
                                FetchService.this.removeRequest(id);
                                return;
                            case FetchService.ACTION_REMOVE_REQUEST_ALL /*325*/:
                                FetchService.this.removeRequestAll();
                                return;
                            default:
                                FetchService.this.startDownload();
                                return;
                        }
                    }
                });
            }
        } catch (Exception e) {
            if (this.loggingEnabled) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void startDownload() {
        if (!(this.shuttingDown || this.runningTask)) {
            boolean networkAvailable = Utils.isNetworkAvailable(this.context);
            boolean onWiFi = Utils.isOnWiFi(this.context);
            if ((!networkAvailable || (this.preferredNetwork == FetchConst.NETWORK_WIFI && !onWiFi)) && this.activeDownloads.size() > 0) {
                this.runningTask = true;
                interruptActiveDownloads();
                this.runningTask = false;
            } else {
                if (networkAvailable) {
                    if (!this.runningTask && this.activeDownloads.size() < this.downloadsLimit && this.databaseHelper.hasPendingRequests()) {
                        this.runningTask = true;
                        try {
                            Cursor cursor = this.databaseHelper.getNextPendingRequest();
                            if (!(cursor == null || cursor.isClosed() || cursor.getCount() <= 0)) {
                                RequestInfo requestInfo = Utils.cursorToRequestInfo(cursor, true, this.loggingEnabled);
                                FetchRunnable fetchRunnable = new FetchRunnable(this.context, requestInfo.getId(), requestInfo.getUrl(), requestInfo.getFilePath(), requestInfo.getHeaders(), requestInfo.getFileSize(), this.loggingEnabled, this.onUpdateInterval);
                                this.databaseHelper.updateStatus(requestInfo.getId(), FetchConst.STATUS_DOWNLOADING, -1);
                                this.activeDownloads.put(Long.valueOf(fetchRunnable.getId()), fetchRunnable);
                                new Thread(fetchRunnable).start();
                            }
                        } catch (Exception e) {
                            if (this.loggingEnabled) {
                                e.printStackTrace();
                            }
                        }
                        this.runningTask = false;
                        if (this.activeDownloads.size() < this.downloadsLimit && this.databaseHelper.hasPendingRequests()) {
                            startDownload();
                        }
                    }
                }
                if (!(this.runningTask || this.activeDownloads.size() != 0 || this.databaseHelper.hasPendingRequests())) {
                    this.shuttingDown = true;
                    shutdown();
                }
            }
        }
    }

    private void interruptActiveDownloads() {
        for (Long id : this.activeDownloads.keySet()) {
            FetchRunnable fetchRunnable = (FetchRunnable) this.activeDownloads.get(id);
            if (fetchRunnable != null) {
                fetchRunnable.interrupt();
            }
        }
    }

    private void interruptActiveDownload(long id) {
        if (this.activeDownloads.containsKey(Long.valueOf(id))) {
            FetchRunnable fetchRunnable = (FetchRunnable) this.activeDownloads.get(Long.valueOf(id));
            if (fetchRunnable != null) {
                fetchRunnable.interrupt();
            }
        }
    }

    private void enqueue(String url, String filePath, ArrayList<Bundle> headers, int priority) {
        if (url == null || filePath == null) {
            try {
                throw new EnqueueException("Request was not properly formatted. url:" + url + ", filePath:" + filePath, FetchConst.ERROR_BAD_REQUEST);
            } catch (EnqueueException e) {
                if (this.loggingEnabled) {
                    e.printStackTrace();
                }
                sendEnqueueEvent(EVENT_ACTION_ENQUEUE_FAILED, -1, url, filePath, -900, headers, priority, e.getErrorCode());
            } finally {
                startDownload();
            }
        } else {
            if (headers == null) {
                headers = new ArrayList();
            }
            long id = Utils.generateRequestId();
            String headerString = Utils.bundleListToHeaderString(headers, this.loggingEnabled);
            long downloadedBytes = 0;
            File file = Utils.getFile(filePath);
            if (file.exists()) {
                downloadedBytes = file.length();
            }
            if (this.databaseHelper.insert(id, url, filePath, FetchConst.STATUS_QUEUED, headerString, downloadedBytes, 0, priority, -1)) {
                sendEnqueueEvent(EVENT_ACTION_ENQUEUED, id, url, filePath, FetchConst.STATUS_QUEUED, headers, priority, -1);
                startDownload();
                return;
            }
            throw new EnqueueException("could not enqueue request", FetchConst.ERROR_ENQUEUE_ERROR);
        }
    }

    private void resume(long id) {
        if (!this.activeDownloads.containsKey(Long.valueOf(id))) {
            if (this.databaseHelper.resume(id)) {
                RequestInfo requestInfo = Utils.cursorToRequestInfo(this.databaseHelper.get(id), true, this.loggingEnabled);
                if (requestInfo != null) {
                    Utils.sendEventUpdate(this.broadcastManager, requestInfo.getId(), requestInfo.getStatus(), requestInfo.getProgress(), requestInfo.getDownloadedBytes(), requestInfo.getFileSize(), requestInfo.getError());
                }
            }
            startDownload();
        }
    }

    private void pause(final long id) {
        if (this.activeDownloads.containsKey(Long.valueOf(id))) {
            this.runningTask = true;
            BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (FetchRunnable.getIdFromIntent(intent) == id) {
                        FetchService.this.pauseAction(id);
                        FetchService.this.broadcastManager.unregisterReceiver(this);
                        FetchService.this.registeredReceivers.remove(this);
                        FetchService.this.runningTask = false;
                        FetchService.this.startDownload();
                    }
                }
            };
            this.registeredReceivers.add(broadcastReceiver);
            this.broadcastManager.registerReceiver(broadcastReceiver, FetchRunnable.getDoneFilter());
            interruptActiveDownload(id);
            return;
        }
        pauseAction(id);
        startDownload();
    }

    private void pauseAction(long id) {
        if (this.databaseHelper.pause(id)) {
            RequestInfo requestInfo = Utils.cursorToRequestInfo(this.databaseHelper.get(id), true, this.loggingEnabled);
            if (requestInfo != null) {
                Utils.sendEventUpdate(this.broadcastManager, requestInfo.getId(), requestInfo.getStatus(), requestInfo.getProgress(), requestInfo.getDownloadedBytes(), requestInfo.getFileSize(), requestInfo.getError());
            }
        }
    }

    private void remove(final long id) {
        if (this.activeDownloads.containsKey(Long.valueOf(id))) {
            this.runningTask = true;
            BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (FetchRunnable.getIdFromIntent(intent) == id) {
                        FetchService.this.removeAction(id);
                        FetchService.this.broadcastManager.unregisterReceiver(this);
                        FetchService.this.registeredReceivers.remove(this);
                        FetchService.this.runningTask = false;
                        FetchService.this.startDownload();
                    }
                }
            };
            this.registeredReceivers.add(broadcastReceiver);
            this.broadcastManager.registerReceiver(broadcastReceiver, FetchRunnable.getDoneFilter());
            interruptActiveDownload(id);
            return;
        }
        removeAction(id);
        startDownload();
    }

    private void removeAction(long id) {
        RequestInfo request = Utils.cursorToRequestInfo(this.databaseHelper.get(id), true, this.loggingEnabled);
        if (request != null && this.databaseHelper.delete(id)) {
            Utils.deleteFile(request.getFilePath());
            Utils.sendEventUpdate(this.broadcastManager, id, FetchConst.STATUS_REMOVED, 0, 0, 0, -1);
        }
    }

    private void removeAll() {
        if (this.activeDownloads.size() > 0) {
            this.runningTask = true;
            BroadcastReceiver broadcastReceiver = new C10905();
            this.registeredReceivers.add(broadcastReceiver);
            this.broadcastManager.registerReceiver(broadcastReceiver, FetchRunnable.getDoneFilter());
            interruptActiveDownloads();
            return;
        }
        removeAllAction();
        startDownload();
    }

    private void removeAllAction() {
        List<RequestInfo> requests = Utils.cursorToRequestInfoList(this.databaseHelper.get(), true, this.loggingEnabled);
        if (requests != null && this.databaseHelper.deleteAll()) {
            for (RequestInfo request : requests) {
                Utils.deleteFile(request.getFilePath());
                Utils.sendEventUpdate(this.broadcastManager, request.getId(), FetchConst.STATUS_REMOVED, 0, 0, 0, -1);
            }
        }
    }

    private void removeRequestAll() {
        if (this.activeDownloads.size() > 0) {
            this.runningTask = true;
            BroadcastReceiver broadcastReceiver = new C10916();
            this.registeredReceivers.add(broadcastReceiver);
            this.broadcastManager.registerReceiver(broadcastReceiver, FetchRunnable.getDoneFilter());
            interruptActiveDownloads();
            return;
        }
        removeRequestAllAction();
        startDownload();
    }

    private void removeRequestAllAction() {
        List<RequestInfo> requests = Utils.cursorToRequestInfoList(this.databaseHelper.get(), true, this.loggingEnabled);
        if (requests != null && this.databaseHelper.deleteAll()) {
            for (RequestInfo request : requests) {
                Utils.sendEventUpdate(this.broadcastManager, request.getId(), FetchConst.STATUS_REMOVED, request.getProgress(), request.getDownloadedBytes(), request.getFileSize(), -1);
            }
        }
    }

    private void removeRequest(final long id) {
        if (this.activeDownloads.containsKey(Long.valueOf(id))) {
            this.runningTask = true;
            BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (FetchRunnable.getIdFromIntent(intent) == id) {
                        FetchService.this.removeRequestAction(id);
                        FetchService.this.broadcastManager.unregisterReceiver(this);
                        FetchService.this.registeredReceivers.remove(this);
                        FetchService.this.runningTask = false;
                        FetchService.this.startDownload();
                    }
                }
            };
            this.registeredReceivers.add(broadcastReceiver);
            this.broadcastManager.registerReceiver(broadcastReceiver, FetchRunnable.getDoneFilter());
            interruptActiveDownload(id);
            return;
        }
        removeRequestAction(id);
        startDownload();
    }

    private void removeRequestAction(long id) {
        RequestInfo request = Utils.cursorToRequestInfo(this.databaseHelper.get(id), true, this.loggingEnabled);
        if (request != null && this.databaseHelper.delete(id)) {
            Utils.sendEventUpdate(this.broadcastManager, id, FetchConst.STATUS_REMOVED, request.getProgress(), request.getDownloadedBytes(), request.getFileSize(), -1);
        }
    }

    private void query(int queryType, long queryId, long requestId, int status) {
        Cursor cursor;
        switch (queryType) {
            case QUERY_SINGLE /*480*/:
                cursor = this.databaseHelper.get(requestId);
                break;
            case QUERY_BY_STATUS /*482*/:
                cursor = this.databaseHelper.getByStatus(status);
                break;
            default:
                cursor = this.databaseHelper.get();
                break;
        }
        sendEventQuery(queryId, Utils.cursorToQueryResultList(cursor, true, this.loggingEnabled));
        startDownload();
    }

    private void setRequestPriority(long id, int priority) {
        if (this.databaseHelper.setPriority(id, priority) && this.activeDownloads.size() > 0) {
            interruptActiveDownloads();
        }
        startDownload();
    }

    private void setAllowedNetwork(int networkType) {
        this.preferredNetwork = networkType;
        this.sharedPreferences.edit().putInt(EXTRA_NETWORK_ID, networkType).apply();
        if (this.activeDownloads.size() > 0) {
            interruptActiveDownloads();
        }
        startDownload();
    }

    private void retry(long id) {
        if (!this.activeDownloads.containsKey(Long.valueOf(id))) {
            if (this.databaseHelper.retry(id)) {
                RequestInfo requestInfo = Utils.cursorToRequestInfo(this.databaseHelper.get(id), true, this.loggingEnabled);
                if (requestInfo != null) {
                    Utils.sendEventUpdate(this.broadcastManager, requestInfo.getId(), requestInfo.getStatus(), requestInfo.getProgress(), requestInfo.getDownloadedBytes(), requestInfo.getFileSize(), requestInfo.getError());
                }
            }
            startDownload();
        }
    }

    private void updateRequestUrl(final long id, final String url) {
        if (this.activeDownloads.containsKey(Long.valueOf(id))) {
            this.runningTask = true;
            BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (FetchRunnable.getIdFromIntent(intent) == id) {
                        FetchService.this.updateRequestUrlAction(id, url);
                        FetchService.this.broadcastManager.unregisterReceiver(this);
                        FetchService.this.registeredReceivers.remove(this);
                        FetchService.this.runningTask = false;
                        FetchService.this.startDownload();
                    }
                }
            };
            this.registeredReceivers.add(broadcastReceiver);
            this.broadcastManager.registerReceiver(broadcastReceiver, FetchRunnable.getDoneFilter());
            interruptActiveDownload(id);
            return;
        }
        updateRequestUrlAction(id, url);
        startDownload();
    }

    private void updateRequestUrlAction(long id, String url) {
        this.databaseHelper.updateUrl(id, url);
        this.databaseHelper.retry(id);
    }

    private int getAllowedNetwork() {
        return this.sharedPreferences.getInt(EXTRA_NETWORK_ID, 200);
    }

    private void sendEnqueueEvent(String action, long id, String url, String filePath, int status, ArrayList<Bundle> headers, int priority, int error) {
        Intent intent = new Intent(action);
        intent.putExtra(EXTRA_ID, id);
        intent.putExtra(EXTRA_STATUS, status);
        intent.putExtra(EXTRA_URL, url);
        intent.putExtra(EXTRA_FILE_PATH, filePath);
        intent.putExtra(EXTRA_HEADERS, headers);
        intent.putExtra(EXTRA_PROGRESS, 0);
        intent.putExtra(EXTRA_FILE_SIZE, 0);
        intent.putExtra(EXTRA_ERROR, error);
        intent.putExtra(EXTRA_PRIORITY, priority);
        this.broadcastManager.sendBroadcast(intent);
    }

    private void sendEventQuery(long queryId, ArrayList<Bundle> results) {
        Intent intent = new Intent(EVENT_ACTION_QUERY);
        intent.putExtra(EXTRA_QUERY_ID, queryId);
        intent.putExtra(EXTRA_QUERY_RESULT, results);
        this.broadcastManager.sendBroadcast(intent);
    }

    private int getDownloadsLimit() {
        return this.sharedPreferences.getInt(EXTRA_CONCURRENT_DOWNLOADS_LIMIT, 1);
    }

    private void setDownloadsLimit(int limit) {
        if (limit < 1) {
            limit = 1;
        }
        this.downloadsLimit = limit;
        this.sharedPreferences.edit().putInt(EXTRA_CONCURRENT_DOWNLOADS_LIMIT, limit).apply();
        if (this.activeDownloads.size() > 0) {
            interruptActiveDownloads();
        }
        startDownload();
    }

    private void setLoggingEnabled(boolean enabled) {
        this.loggingEnabled = enabled;
        this.sharedPreferences.edit().putBoolean(EXTRA_LOGGING_ID, enabled).apply();
        this.databaseHelper.setLoggingEnabled(this.loggingEnabled);
        startDownload();
    }

    private boolean isLoggingEnabled() {
        return this.sharedPreferences.getBoolean(EXTRA_LOGGING_ID, true);
    }

    static boolean isLoggingEnabled(Context context) {
        return context.getSharedPreferences(SHARED_PREFERENCES, 0).getBoolean(EXTRA_LOGGING_ID, true);
    }

    private void setOnUpdateInterval(long intervalMs) {
        this.onUpdateInterval = intervalMs;
        this.sharedPreferences.edit().putLong(EXTRA_ON_UPDATE_INTERVAL, intervalMs).apply();
        if (this.activeDownloads.size() > 0) {
            interruptActiveDownloads();
        }
        startDownload();
    }

    private long getOnUpdateInterval() {
        this.onUpdateInterval = this.sharedPreferences.getLong(EXTRA_ON_UPDATE_INTERVAL, FetchConst.DEFAULT_ON_UPDATE_INTERVAL);
        return this.onUpdateInterval;
    }
}
