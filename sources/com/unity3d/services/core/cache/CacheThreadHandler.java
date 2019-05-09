package com.unity3d.services.core.cache;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import com.facebook.share.internal.ShareConstants;
import com.kongregate.android.api.activities.KongregatePanelActivity;
import com.unity3d.services.core.api.Request;
import com.unity3d.services.core.device.Device;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.request.IWebRequestProgressListener;
import com.unity3d.services.core.request.NetworkIOException;
import com.unity3d.services.core.request.WebRequest;
import com.unity3d.services.core.webview.WebViewApp;
import com.unity3d.services.core.webview.WebViewEventCategory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CacheThreadHandler extends Handler {
    private boolean _active = false;
    private boolean _canceled = false;
    private WebRequest _currentRequest = null;

    CacheThreadHandler() {
    }

    public void handleMessage(Message msg) {
        Bundle data = msg.getData();
        String source = data.getString(ShareConstants.FEED_SOURCE_PARAM);
        data.remove(ShareConstants.FEED_SOURCE_PARAM);
        String target = data.getString(KongregatePanelActivity.INTENT_EXTRA_TARGET);
        data.remove(KongregatePanelActivity.INTENT_EXTRA_TARGET);
        int connectTimeout = data.getInt("connectTimeout");
        data.remove("connectTimeout");
        int readTimeout = data.getInt("readTimeout");
        data.remove("readTimeout");
        int progressInterval = data.getInt("progressInterval");
        data.remove("progressInterval");
        boolean append = data.getBoolean("append", false);
        data.remove("append");
        HashMap<String, List<String>> headers = null;
        if (data.size() > 0) {
            DeviceLog.debug("There are headers left in data, reading them");
            headers = new HashMap();
            for (String k : data.keySet()) {
                headers.put(k, Arrays.asList(data.getStringArray(k)));
            }
        }
        File targetFile = new File(target);
        if ((!append || targetFile.exists()) && (append || !targetFile.exists())) {
            switch (msg.what) {
                case 1:
                    downloadFile(source, target, connectTimeout, readTimeout, progressInterval, headers, append);
                    return;
                default:
                    return;
            }
        }
        this._active = false;
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_ERROR, CacheError.FILE_STATE_WRONG, source, target, Boolean.valueOf(append), Boolean.valueOf(targetFile.exists()));
    }

    public void setCancelStatus(boolean canceled) {
        this._canceled = canceled;
        if (canceled && this._currentRequest != null) {
            this._active = false;
            this._currentRequest.cancel();
        }
    }

    public boolean isActive() {
        return this._active;
    }

    private void downloadFile(String source, String target, int connectTimeout, int readTimeout, int progressInterval, HashMap<String, List<String>> headers, boolean append) {
        OutputStream outputStream;
        FileNotFoundException e;
        Throwable th;
        MalformedURLException e2;
        IOException e3;
        IllegalStateException e4;
        NetworkIOException e5;
        if (!this._canceled && source != null && target != null) {
            final File targetFile = new File(target);
            if (append) {
                DeviceLog.debug("Unity Ads cache: resuming download " + source + " to " + target + " at " + targetFile.length() + " bytes");
            } else {
                DeviceLog.debug("Unity Ads cache: start downloading " + source + " to " + target);
            }
            if (Device.isActiveNetworkConnected()) {
                this._active = true;
                long startTime = SystemClock.elapsedRealtime();
                FileOutputStream fileOutput = null;
                try {
                    OutputStream fileOutputStream = new FileOutputStream(targetFile, append);
                    try {
                        this._currentRequest = getWebRequest(source, connectTimeout, readTimeout, headers);
                        final int i = progressInterval;
                        this._currentRequest.setProgressListener(new IWebRequestProgressListener() {
                            private long lastProgressEventTime = System.currentTimeMillis();

                            public void onRequestStart(String url, long total, int responseCode, Map<String, List<String>> headers) {
                                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_STARTED, url, Long.valueOf(targetFile.length()), Long.valueOf(targetFile.length() + total), Integer.valueOf(responseCode), Request.getResponseHeadersMap(headers));
                            }

                            public void onRequestProgress(String url, long bytes, long total) {
                                if (i > 0 && System.currentTimeMillis() - this.lastProgressEventTime > ((long) i)) {
                                    this.lastProgressEventTime = System.currentTimeMillis();
                                    WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_PROGRESS, url, Long.valueOf(bytes), Long.valueOf(total));
                                }
                            }
                        });
                        long total = this._currentRequest.makeStreamRequest(fileOutputStream);
                        this._active = false;
                        postProcessDownload(startTime, source, targetFile, total, this._currentRequest.getContentLength(), this._currentRequest.isCanceled(), this._currentRequest.getResponseCode(), this._currentRequest.getResponseHeaders());
                        this._currentRequest = null;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Exception e6) {
                                DeviceLog.exception("Error closing stream", e6);
                                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_ERROR, CacheError.FILE_IO_ERROR, source, e6.getMessage());
                                outputStream = fileOutputStream;
                                return;
                            }
                        }
                        outputStream = fileOutputStream;
                        return;
                    } catch (FileNotFoundException e7) {
                        e = e7;
                        outputStream = fileOutputStream;
                        try {
                            DeviceLog.exception("Couldn't create target file", e);
                            this._active = false;
                            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_ERROR, CacheError.FILE_IO_ERROR, source, e.getMessage());
                            this._currentRequest = null;
                            if (fileOutput != null) {
                                try {
                                    fileOutput.close();
                                    return;
                                } catch (Exception e62) {
                                    DeviceLog.exception("Error closing stream", e62);
                                    WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_ERROR, CacheError.FILE_IO_ERROR, source, e62.getMessage());
                                    return;
                                }
                            }
                            return;
                        } catch (Throwable th2) {
                            th = th2;
                            this._currentRequest = null;
                            if (fileOutput != null) {
                                try {
                                    fileOutput.close();
                                } catch (Exception e622) {
                                    DeviceLog.exception("Error closing stream", e622);
                                    WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_ERROR, CacheError.FILE_IO_ERROR, source, e622.getMessage());
                                }
                            }
                            throw th;
                        }
                    } catch (MalformedURLException e8) {
                        e2 = e8;
                        outputStream = fileOutputStream;
                        DeviceLog.exception("Malformed URL", e2);
                        this._active = false;
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_ERROR, CacheError.MALFORMED_URL, source, e2.getMessage());
                        this._currentRequest = null;
                        if (fileOutput != null) {
                            try {
                                fileOutput.close();
                                return;
                            } catch (Exception e6222) {
                                DeviceLog.exception("Error closing stream", e6222);
                                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_ERROR, CacheError.FILE_IO_ERROR, source, e6222.getMessage());
                                return;
                            }
                        }
                        return;
                    } catch (IOException e9) {
                        e3 = e9;
                        outputStream = fileOutputStream;
                        DeviceLog.exception("Couldn't request stream", e3);
                        this._active = false;
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_ERROR, CacheError.FILE_IO_ERROR, source, e3.getMessage());
                        this._currentRequest = null;
                        if (fileOutput != null) {
                            try {
                                fileOutput.close();
                                return;
                            } catch (Exception e62222) {
                                DeviceLog.exception("Error closing stream", e62222);
                                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_ERROR, CacheError.FILE_IO_ERROR, source, e62222.getMessage());
                                return;
                            }
                        }
                        return;
                    } catch (IllegalStateException e10) {
                        e4 = e10;
                        outputStream = fileOutputStream;
                        DeviceLog.exception("Illegal state", e4);
                        this._active = false;
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_ERROR, CacheError.ILLEGAL_STATE, source, e4.getMessage());
                        this._currentRequest = null;
                        if (fileOutput != null) {
                            try {
                                fileOutput.close();
                                return;
                            } catch (Exception e622222) {
                                DeviceLog.exception("Error closing stream", e622222);
                                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_ERROR, CacheError.FILE_IO_ERROR, source, e622222.getMessage());
                                return;
                            }
                        }
                        return;
                    } catch (NetworkIOException e11) {
                        e5 = e11;
                        outputStream = fileOutputStream;
                        DeviceLog.exception("Network error", e5);
                        this._active = false;
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_ERROR, CacheError.NETWORK_ERROR, source, e5.getMessage());
                        this._currentRequest = null;
                        if (fileOutput != null) {
                            try {
                                fileOutput.close();
                                return;
                            } catch (Exception e6222222) {
                                DeviceLog.exception("Error closing stream", e6222222);
                                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_ERROR, CacheError.FILE_IO_ERROR, source, e6222222.getMessage());
                                return;
                            }
                        }
                        return;
                    } catch (Throwable th3) {
                        th = th3;
                        outputStream = fileOutputStream;
                        this._currentRequest = null;
                        if (fileOutput != null) {
                            fileOutput.close();
                        }
                        throw th;
                    }
                } catch (FileNotFoundException e12) {
                    e = e12;
                    DeviceLog.exception("Couldn't create target file", e);
                    this._active = false;
                    WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_ERROR, CacheError.FILE_IO_ERROR, source, e.getMessage());
                    this._currentRequest = null;
                    if (fileOutput != null) {
                        fileOutput.close();
                        return;
                    }
                    return;
                } catch (MalformedURLException e13) {
                    e2 = e13;
                    DeviceLog.exception("Malformed URL", e2);
                    this._active = false;
                    WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_ERROR, CacheError.MALFORMED_URL, source, e2.getMessage());
                    this._currentRequest = null;
                    if (fileOutput != null) {
                        fileOutput.close();
                        return;
                    }
                    return;
                } catch (IOException e14) {
                    e3 = e14;
                    DeviceLog.exception("Couldn't request stream", e3);
                    this._active = false;
                    WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_ERROR, CacheError.FILE_IO_ERROR, source, e3.getMessage());
                    this._currentRequest = null;
                    if (fileOutput != null) {
                        fileOutput.close();
                        return;
                    }
                    return;
                } catch (IllegalStateException e15) {
                    e4 = e15;
                    DeviceLog.exception("Illegal state", e4);
                    this._active = false;
                    WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_ERROR, CacheError.ILLEGAL_STATE, source, e4.getMessage());
                    this._currentRequest = null;
                    if (fileOutput != null) {
                        fileOutput.close();
                        return;
                    }
                    return;
                } catch (NetworkIOException e16) {
                    e5 = e16;
                    DeviceLog.exception("Network error", e5);
                    this._active = false;
                    WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_ERROR, CacheError.NETWORK_ERROR, source, e5.getMessage());
                    this._currentRequest = null;
                    if (fileOutput != null) {
                        fileOutput.close();
                        return;
                    }
                    return;
                }
            }
            DeviceLog.debug("Unity Ads cache: download cancelled, no internet connection available");
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_ERROR, CacheError.NO_INTERNET, source);
        }
    }

    private void postProcessDownload(long startTime, String source, File targetFile, long byteCount, long totalBytes, boolean canceled, int responseCode, Map<String, List<String>> responseHeaders) {
        long duration = SystemClock.elapsedRealtime() - startTime;
        if (!targetFile.setReadable(true, false)) {
            DeviceLog.debug("Unity Ads cache: could not set file readable!");
        }
        if (canceled) {
            DeviceLog.debug("Unity Ads cache: downloading of " + source + " stopped");
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_STOPPED, source, Long.valueOf(byteCount), Long.valueOf(totalBytes), Long.valueOf(duration), Integer.valueOf(responseCode), Request.getResponseHeadersMap(responseHeaders));
            return;
        }
        DeviceLog.debug("Unity Ads cache: File " + targetFile.getName() + " of " + byteCount + " bytes downloaded in " + duration + "ms");
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_END, source, Long.valueOf(byteCount), Long.valueOf(totalBytes), Long.valueOf(duration), Integer.valueOf(responseCode), Request.getResponseHeadersMap(responseHeaders));
    }

    private WebRequest getWebRequest(String source, int connectTimeout, int readTimeout, HashMap<String, List<String>> headers) throws MalformedURLException {
        HashMap<String, List<String>> requestHeaders = new HashMap();
        if (headers != null) {
            requestHeaders.putAll(headers);
        }
        return new WebRequest(source, "GET", requestHeaders, connectTimeout, readTimeout);
    }
}
