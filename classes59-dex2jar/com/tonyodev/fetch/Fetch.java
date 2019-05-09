// 
// Decompiled by Procyon v0.5.34
// 

package com.tonyodev.fetch;

import com.tonyodev.fetch.callback.FetchTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.tonyodev.fetch.request.RequestInfo;
import java.io.File;
import com.tonyodev.fetch.request.Header;
import android.net.Uri;
import com.tonyodev.fetch.exception.EnqueueException;
import com.tonyodev.fetch.callback.FetchCall;
import android.support.annotation.NonNull;
import android.content.IntentFilter;
import java.util.Iterator;
import android.content.Intent;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import android.os.Looper;
import android.content.BroadcastReceiver;
import com.tonyodev.fetch.listener.FetchListener;
import java.util.List;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
import android.os.Handler;
import com.tonyodev.fetch.request.Request;
import java.util.concurrent.ConcurrentMap;

public final class Fetch implements FetchConst
{
    private static final FetchCallRunnable.Callback callsCallback;
    private static final ConcurrentMap<Request, FetchCallRunnable> callsMap;
    private static final Handler mainHandler;
    private final LocalBroadcastManager broadcastManager;
    private final Context context;
    private final DatabaseHelper dbHelper;
    private volatile boolean isReleased;
    private final List<FetchListener> listeners;
    private final BroadcastReceiver networkReceiver;
    private final BroadcastReceiver updateReceiver;
    
    static {
        mainHandler = new Handler(Looper.getMainLooper());
        callsMap = new ConcurrentHashMap<Request, FetchCallRunnable>();
        callsCallback = new FetchCallRunnable.Callback() {
            @Override
            public void onDone(final Request request) {
                Fetch.callsMap.remove(request);
            }
        };
    }
    
    private Fetch(final Context context) {
        this.listeners = new ArrayList<FetchListener>();
        this.isReleased = false;
        this.updateReceiver = new BroadcastReceiver() {
            private long downloadedBytes;
            private int error;
            private long fileSize;
            private long id;
            private int progress;
            private int status;
            
            public void onReceive(final Context context, final Intent intent) {
                if (intent != null) {
                    this.id = intent.getLongExtra("com.tonyodev.fetch.extra_id", -1L);
                    this.status = intent.getIntExtra("com.tonyodev.fetch.extra_status", -1);
                    this.progress = intent.getIntExtra("com.tonyodev.fetch.extra_progress", -1);
                    this.downloadedBytes = intent.getLongExtra("com.tonyodev.fetch.extra_downloaded_bytes", -1L);
                    this.fileSize = intent.getLongExtra("com.tonyodev.fetch.extra_file_size", -1L);
                    this.error = intent.getIntExtra("com.tonyodev.fetch.extra_error", -1);
                    try {
                        final Iterator access$200 = Fetch.this.getListenerIterator();
                        while (access$200.hasNext()) {
                            access$200.next().onUpdate(this.id, this.status, this.progress, this.downloadedBytes, this.fileSize, this.error);
                        }
                    }
                    catch (Exception ex) {
                        if (Fetch.this.isLoggingEnabled()) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        };
        this.networkReceiver = new BroadcastReceiver() {
            public void onReceive(final Context context, final Intent intent) {
                FetchService.processPendingRequests(context);
            }
        };
        this.context = context.getApplicationContext();
        this.broadcastManager = LocalBroadcastManager.getInstance(this.context);
        (this.dbHelper = DatabaseHelper.getInstance(this.context)).setLoggingEnabled(this.isLoggingEnabled());
        this.broadcastManager.registerReceiver(this.updateReceiver, FetchService.getEventUpdateFilter());
        this.context.registerReceiver(this.networkReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        startService(this.context);
    }
    
    public static void call(@NonNull final Request request, @NonNull final FetchCall<String> fetchCall) {
        if (request == null) {
            throw new NullPointerException("Request cannot be null");
        }
        if (fetchCall == null) {
            throw new NullPointerException("FetchCall cannot be null");
        }
        if (Fetch.callsMap.containsKey(request)) {
            return;
        }
        final FetchCallRunnable fetchCallRunnable = new FetchCallRunnable(request, fetchCall, Fetch.callsCallback);
        Fetch.callsMap.put(request, fetchCallRunnable);
        new Thread(fetchCallRunnable).start();
    }
    
    public static void cancelCall(@NonNull final Request request) {
        if (request != null && Fetch.callsMap.containsKey(request)) {
            final FetchCallRunnable fetchCallRunnable = Fetch.callsMap.get(request);
            if (fetchCallRunnable != null) {
                fetchCallRunnable.interrupt();
            }
        }
    }
    
    public static Fetch getInstance(@NonNull final Context context) {
        return newInstance(context);
    }
    
    private Iterator<FetchListener> getListenerIterator() {
        return this.listeners.iterator();
    }
    
    private boolean isLoggingEnabled() {
        return FetchService.isLoggingEnabled(this.context);
    }
    
    public static Fetch newInstance(@NonNull final Context context) {
        if (context == null) {
            throw new NullPointerException("Context cannot be null");
        }
        return new Fetch(context);
    }
    
    private void setReleased(final boolean isReleased) {
        this.isReleased = isReleased;
    }
    
    public static void startService(@NonNull final Context context) {
        FetchService.processPendingRequests(context);
    }
    
    public long addCompletedDownload(@NonNull final String s) {
        Utils.throwIfNotUsable(this);
        if (s == null) {
            throw new NullPointerException("File path cannot be null");
        }
        Label_0072: {
            long n;
            try {
                if (!Utils.fileExist(s)) {
                    throw new EnqueueException("File does not exist at filePath: " + s, -102);
                }
                break Label_0072;
            }
            catch (EnqueueException ex) {
                if (this.isLoggingEnabled()) {
                    ex.printStackTrace();
                }
                n = -1L;
            }
            return n;
        }
        final long generateRequestId = Utils.generateRequestId();
        final File file = Utils.getFile(s);
        final String string = Uri.fromFile(file).toString();
        final String headerListToString = Utils.headerListToString(null, this.isLoggingEnabled());
        final long length = file.length();
        long n = generateRequestId;
        if (!this.dbHelper.insert(generateRequestId, string, s, 903, headerListToString, length, length, 600, -1)) {
            throw new EnqueueException("could not insert request:" + s, -117);
        }
        return n;
    }
    
    @NonNull
    public List<Long> addCompletedDownloads(@NonNull final List<String> list) {
        Utils.throwIfNotUsable(this);
        if (list == null) {
            throw new NullPointerException("Request list cannot be null");
        }
        ArrayList<Long> list2 = null;
        Label_0039: {
            if (list.size() < 1) {
                list2 = new ArrayList<Long>(0);
            }
            else {
                while (true) {
                    final ArrayList<Long> list3 = new ArrayList<Long>(list.size());
                    new ArrayList();
                    while (true) {
                        long generateRequestId = 0L;
                        Label_0327: {
                            StringBuilder sb = null;
                            String s = null;
                            File file = null;
                            Label_0259: {
                                try {
                                    sb = new StringBuilder();
                                    sb.append(this.dbHelper.getInsertStatementOpen());
                                    final Iterator<String> iterator = list.iterator();
                                    if (iterator.hasNext()) {
                                        s = iterator.next();
                                        generateRequestId = -1L;
                                        if (s == null) {
                                            break Label_0327;
                                        }
                                        file = Utils.getFile(s);
                                        if (file.exists()) {
                                            break Label_0259;
                                        }
                                    }
                                    sb.delete(sb.length() - 2, sb.length()).append(this.dbHelper.getInsertStatementClose());
                                    list2 = list3;
                                    if (!this.dbHelper.insert(sb.toString())) {
                                        throw new EnqueueException("could not insert requests", -117);
                                    }
                                    break;
                                }
                                catch (EnqueueException ex) {
                                    if (this.isLoggingEnabled()) {
                                        ex.printStackTrace();
                                    }
                                    list3.clear();
                                    int n = 0;
                                    while (true) {
                                        list2 = list3;
                                        if (n >= list.size()) {
                                            break Label_0039;
                                        }
                                        list3.add(-1L);
                                        ++n;
                                    }
                                }
                            }
                            generateRequestId = Utils.generateRequestId();
                            final String string = Uri.fromFile(file).toString();
                            final String headerListToString = Utils.headerListToString(null, this.isLoggingEnabled());
                            final long length = file.length();
                            sb.append(this.dbHelper.getRowInsertStatement(generateRequestId, string, s, 903, headerListToString, length, length, 600, -1)).append(",");
                        }
                        list3.add(generateRequestId);
                        continue;
                    }
                }
            }
        }
        return list2;
    }
    
    public void addFetchListener(@NonNull final FetchListener fetchListener) {
        Utils.throwIfNotUsable(this);
        if (fetchListener == null) {
            throw new NullPointerException("fetchListener cannot be null");
        }
        if (this.listeners.contains(fetchListener)) {
            return;
        }
        this.listeners.add(fetchListener);
    }
    
    public boolean contains(@NonNull final Request request) {
        synchronized (this) {
            Utils.throwIfNotUsable(this);
            if (request == null) {
                throw new NullPointerException("Request cannot be null.");
            }
        }
        final Request request2;
        // monitorexit(this)
        return Utils.containsRequest(this.dbHelper.getByUrlAndFilePath(request2.getUrl(), request2.getFilePath()), true);
    }
    
    public void enableLogging(final boolean b) {
        Utils.throwIfNotUsable(this);
        new Settings(this.context).enableLogging(b).apply();
    }
    
    public long enqueue(@NonNull final Request request) {
        Utils.throwIfNotUsable(this);
        if (request == null) {
            throw new NullPointerException("Request cannot be null");
        }
        final long generateRequestId = Utils.generateRequestId();
        try {
            final String url = request.getUrl();
            final String filePath = request.getFilePath();
            final int priority = request.getPriority();
            final String headerListToString = Utils.headerListToString(request.getHeaders(), this.isLoggingEnabled());
            long length = 0L;
            final File file = Utils.getFile(filePath);
            if (file.exists()) {
                length = file.length();
            }
            if (!this.dbHelper.insert(generateRequestId, url, filePath, 900, headerListToString, length, 0L, priority, -1)) {
                throw new EnqueueException("could not insert request", -117);
            }
        }
        catch (EnqueueException ex) {
            if (this.isLoggingEnabled()) {
                ex.printStackTrace();
            }
            return -1L;
        }
        startService(this.context);
        return generateRequestId;
    }
    
    @NonNull
    public List<Long> enqueue(@NonNull final List<Request> list) {
        Utils.throwIfNotUsable(this);
        if (list == null) {
            throw new NullPointerException("Request list cannot be null");
        }
        ArrayList<Long> list2;
        if (list.size() < 1) {
            list2 = new ArrayList<Long>(0);
        }
        else {
            final ArrayList<Long> list3 = new ArrayList<Long>(list.size());
            new ArrayList();
            StringBuilder sb;
            try {
                sb = new StringBuilder();
                sb.append(this.dbHelper.getInsertStatementOpen());
                for (final Request request : list) {
                    long n = -1L;
                    if (request != null) {
                        final long generateRequestId = Utils.generateRequestId();
                        final String url = request.getUrl();
                        final String filePath = request.getFilePath();
                        final String headerListToString = Utils.headerListToString(request.getHeaders(), this.isLoggingEnabled());
                        final int priority = request.getPriority();
                        long length = 0L;
                        final File file = Utils.getFile(filePath);
                        if (file.exists()) {
                            length = file.length();
                        }
                        sb.append(this.dbHelper.getRowInsertStatement(generateRequestId, url, filePath, 900, headerListToString, length, 0L, priority, -1)).append(", ");
                        n = generateRequestId;
                    }
                    list3.add(n);
                }
            }
            catch (EnqueueException ex) {
                if (this.isLoggingEnabled()) {
                    ex.printStackTrace();
                }
                list3.clear();
                int n2 = 0;
                while (true) {
                    list2 = list3;
                    if (n2 >= list.size()) {
                        return list2;
                    }
                    list3.add(-1L);
                    ++n2;
                }
            }
            sb.delete(sb.length() - 2, sb.length()).append(this.dbHelper.getInsertStatementClose());
            if (!this.dbHelper.insert(sb.toString())) {
                throw new EnqueueException("could not insert requests", -117);
            }
            startService(this.context);
            return list3;
        }
        return list2;
    }
    
    @Nullable
    public RequestInfo get(final long n) {
        synchronized (this) {
            Utils.throwIfNotUsable(this);
            return Utils.cursorToRequestInfo(this.dbHelper.get(n), true, this.isLoggingEnabled());
        }
    }
    
    @Nullable
    public RequestInfo get(@NonNull final Request request) {
        synchronized (this) {
            Utils.throwIfNotUsable(this);
            if (request == null) {
                throw new NullPointerException("Request cannot be null.");
            }
        }
        final Request request2;
        // monitorexit(this)
        return Utils.cursorToRequestInfo(this.dbHelper.getByUrlAndFilePath(request2.getUrl(), request2.getFilePath()), true, this.isLoggingEnabled());
    }
    
    @NonNull
    public List<RequestInfo> get() {
        synchronized (this) {
            Utils.throwIfNotUsable(this);
            return Utils.cursorToRequestInfoList(this.dbHelper.get(), true, this.isLoggingEnabled());
        }
    }
    
    @NonNull
    public List<RequestInfo> get(final long... array) {
        synchronized (this) {
            Utils.throwIfNotUsable(this);
            List<RequestInfo> cursorToRequestInfoList;
            if (array == null) {
                cursorToRequestInfoList = new ArrayList<RequestInfo>();
            }
            else {
                cursorToRequestInfoList = Utils.cursorToRequestInfoList(this.dbHelper.get(array), true, this.isLoggingEnabled());
            }
            return cursorToRequestInfoList;
        }
    }
    
    @NonNull
    public List<RequestInfo> getByStatus(final int n) {
        synchronized (this) {
            Utils.throwIfNotUsable(this);
            Utils.throwIfInvalidStatus(n);
            return Utils.cursorToRequestInfoList(this.dbHelper.getByStatus(n), true, this.isLoggingEnabled());
        }
    }
    
    @Nullable
    public File getDownloadedFile(final long n) {
        synchronized (this) {
            Utils.throwIfNotUsable(this);
            final RequestInfo cursorToRequestInfo = Utils.cursorToRequestInfo(this.dbHelper.get(n), true, this.isLoggingEnabled());
            File file;
            if (cursorToRequestInfo == null || cursorToRequestInfo.getStatus() != 903) {
                file = null;
            }
            else {
                file = Utils.getFile(cursorToRequestInfo.getFilePath());
                if (!file.exists()) {
                    file = null;
                }
            }
            return file;
        }
    }
    
    @Nullable
    public String getFilePath(final long n) {
        synchronized (this) {
            Utils.throwIfNotUsable(this);
            final RequestInfo cursorToRequestInfo = Utils.cursorToRequestInfo(this.dbHelper.get(n), true, this.isLoggingEnabled());
            String filePath;
            if (cursorToRequestInfo == null) {
                filePath = null;
            }
            else {
                filePath = cursorToRequestInfo.getFilePath();
            }
            return filePath;
        }
    }
    
    boolean isReleased() {
        return this.isReleased;
    }
    
    public boolean isValid() {
        return !this.isReleased();
    }
    
    public void pause(final long n) {
        Utils.throwIfNotUsable(this);
        final Bundle bundle = new Bundle();
        bundle.putInt("com.tonyodev.fetch.action_type", 311);
        bundle.putLong("com.tonyodev.fetch.extra_id", n);
        FetchService.sendToService(this.context, bundle);
    }
    
    public void release() {
        if (!this.isReleased()) {
            this.setReleased(true);
            this.listeners.clear();
            this.broadcastManager.unregisterReceiver(this.updateReceiver);
            this.context.unregisterReceiver(this.networkReceiver);
        }
    }
    
    public void remove(final long n) {
        Utils.throwIfNotUsable(this);
        final Bundle bundle = new Bundle();
        bundle.putInt("com.tonyodev.fetch.action_type", 313);
        bundle.putLong("com.tonyodev.fetch.extra_id", n);
        FetchService.sendToService(this.context, bundle);
    }
    
    public void removeAll() {
        Utils.throwIfNotUsable(this);
        final Bundle bundle = new Bundle();
        bundle.putInt("com.tonyodev.fetch.action_type", 319);
        FetchService.sendToService(this.context, bundle);
    }
    
    public void removeFetchListener(@NonNull final FetchListener fetchListener) {
        Utils.throwIfNotUsable(this);
        if (fetchListener == null) {
            return;
        }
        this.listeners.remove(fetchListener);
    }
    
    public void removeFetchListeners() {
        Utils.throwIfNotUsable(this);
        this.listeners.clear();
    }
    
    public void removeRequest(final long n) {
        Utils.throwIfNotUsable(this);
        final Bundle bundle = new Bundle();
        bundle.putInt("com.tonyodev.fetch.action_type", 324);
        bundle.putLong("com.tonyodev.fetch.extra_id", n);
        FetchService.sendToService(this.context, bundle);
    }
    
    public void removeRequests() {
        Utils.throwIfNotUsable(this);
        final Bundle bundle = new Bundle();
        bundle.putInt("com.tonyodev.fetch.action_type", 325);
        FetchService.sendToService(this.context, bundle);
    }
    
    public void resume(final long n) {
        Utils.throwIfNotUsable(this);
        final Bundle bundle = new Bundle();
        bundle.putInt("com.tonyodev.fetch.action_type", 312);
        bundle.putLong("com.tonyodev.fetch.extra_id", n);
        FetchService.sendToService(this.context, bundle);
    }
    
    public void retry(final long n) {
        Utils.throwIfNotUsable(this);
        final Bundle bundle = new Bundle();
        bundle.putInt("com.tonyodev.fetch.action_type", 318);
        bundle.putLong("com.tonyodev.fetch.extra_id", n);
        FetchService.sendToService(this.context, bundle);
    }
    
    public void runOnBackgroundThread(@NonNull final FetchTask fetchTask) {
        Utils.throwIfNotUsable(this);
        Utils.throwIfFetchTaskNull(fetchTask);
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Fetch instance = Fetch.newInstance(Fetch.this.context);
                fetchTask.onProcess(instance);
                instance.release();
            }
        }).start();
    }
    
    public void runOnMainThread(@NonNull final FetchTask fetchTask) {
        Utils.throwIfNotUsable(this);
        Utils.throwIfFetchTaskNull(fetchTask);
        Fetch.mainHandler.post((Runnable)new Runnable() {
            @Override
            public void run() {
                final Fetch instance = Fetch.newInstance(Fetch.this.context);
                fetchTask.onProcess(instance);
                instance.release();
            }
        });
    }
    
    public void setAllowedNetwork(final int allowedNetwork) {
        Utils.throwIfNotUsable(this);
        new Settings(this.context).setAllowedNetwork(allowedNetwork).apply();
    }
    
    public void setConcurrentDownloadsLimit(final int concurrentDownloadsLimit) {
        Utils.throwIfNotUsable(this);
        new Settings(this.context).setConcurrentDownloadsLimit(concurrentDownloadsLimit).apply();
    }
    
    public void setOnUpdateInterval(final long onUpdateInterval) {
        Utils.throwIfNotUsable(this);
        new Settings(this.context).setOnUpdateInterval(onUpdateInterval).apply();
    }
    
    public void setPriority(final long n, final int n2) {
        Utils.throwIfNotUsable(this);
        int n3 = 600;
        if (n2 == 601) {
            n3 = 601;
        }
        final Bundle bundle = new Bundle();
        bundle.putInt("com.tonyodev.fetch.action_type", 317);
        bundle.putLong("com.tonyodev.fetch.extra_id", n);
        bundle.putInt("com.tonyodev.fetch.extra_priority", n3);
        FetchService.sendToService(this.context, bundle);
    }
    
    public void updateUrlForRequest(final long n, @Nullable final String s) {
        Utils.throwIfNotUsable(this);
        if (s == null) {
            throw new NullPointerException("Url cannot be null");
        }
        Utils.throwIfInvalidUrl(s);
        final Bundle bundle = new Bundle();
        bundle.putInt("com.tonyodev.fetch.action_type", 322);
        bundle.putLong("com.tonyodev.fetch.extra_id", n);
        bundle.putString("com.tonyodev.fetch.extra_url", s);
        FetchService.sendToService(this.context, bundle);
    }
    
    public static class Settings
    {
        private final Context context;
        private final List<Bundle> settings;
        
        public Settings(@NonNull final Context context) {
            this.settings = new ArrayList<Bundle>();
            if (context == null) {
                throw new NullPointerException("Context cannot be null");
            }
            this.context = context;
        }
        
        public void apply() {
            final Iterator<Bundle> iterator = this.settings.iterator();
            while (iterator.hasNext()) {
                FetchService.sendToService(this.context, iterator.next());
            }
        }
        
        public Settings enableLogging(final boolean b) {
            final Bundle bundle = new Bundle();
            bundle.putInt("com.tonyodev.fetch.action_type", 320);
            bundle.putBoolean("com.tonyodev.fetch.extra_logging_id", b);
            this.settings.add(bundle);
            return this;
        }
        
        public Settings setAllowedNetwork(final int n) {
            int n2 = 200;
            if (n == 201) {
                n2 = 201;
            }
            final Bundle bundle = new Bundle();
            bundle.putInt("com.tonyodev.fetch.action_type", 314);
            bundle.putInt("com.tonyodev.fetch.extra_network_id", n2);
            this.settings.add(bundle);
            return this;
        }
        
        public Settings setConcurrentDownloadsLimit(final int n) {
            final Bundle bundle = new Bundle();
            bundle.putInt("com.tonyodev.fetch.action_type", 321);
            bundle.putInt("com.tonyodev.fetch.extra_concurrent_download_limit", n);
            this.settings.add(bundle);
            return this;
        }
        
        public Settings setOnUpdateInterval(final long n) {
            final Bundle bundle = new Bundle();
            bundle.putInt("com.tonyodev.fetch.action_type", 323);
            bundle.putLong("com.tonyodev.fetch.extra_on_update_interval", n);
            this.settings.add(bundle);
            return this;
        }
    }
}
