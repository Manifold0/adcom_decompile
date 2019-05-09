package com.unity3d.services.core.request;

import android.os.ConditionVariable;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.request.WebRequest.RequestType;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class WebRequestThread {
    private static int _corePoolSize = 1;
    private static long _keepAliveTime = 1000;
    private static int _maximumPoolSize = 1;
    private static CancelableThreadPoolExecutor _pool;
    private static LinkedBlockingQueue<Runnable> _queue;
    private static boolean _ready = false;
    private static final Object _readyLock = new Object();

    /* renamed from: com.unity3d.services.core.request.WebRequestThread$1 */
    static class C16251 implements Runnable {
        C16251() {
        }

        public void run() {
            WebRequestThread._ready = true;
            synchronized (WebRequestThread._readyLock) {
                WebRequestThread._readyLock.notify();
            }
        }
    }

    private static synchronized void init() {
        synchronized (WebRequestThread.class) {
            _queue = new LinkedBlockingQueue();
            _pool = new CancelableThreadPoolExecutor(_corePoolSize, _maximumPoolSize, _keepAliveTime, TimeUnit.MILLISECONDS, _queue);
            _pool.prestartAllCoreThreads();
            _queue.add(new C16251());
            while (!_ready) {
                try {
                    synchronized (_readyLock) {
                        _readyLock.wait();
                    }
                } catch (InterruptedException e) {
                    DeviceLog.debug("Couldn't synchronize thread");
                }
            }
        }
        return;
    }

    public static synchronized void reset() {
        synchronized (WebRequestThread.class) {
            cancel();
            if (_pool != null) {
                _pool.shutdown();
                try {
                    _pool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
                } catch (InterruptedException e) {
                }
                _queue.clear();
                _pool = null;
                _queue = null;
                _ready = false;
            }
        }
    }

    public static synchronized void cancel() {
        synchronized (WebRequestThread.class) {
            if (_pool != null) {
                _pool.cancel();
                Iterator it = _queue.iterator();
                while (it.hasNext()) {
                    Runnable runnable = (Runnable) it.next();
                    if (runnable instanceof WebRequestRunnable) {
                        ((WebRequestRunnable) runnable).setCancelStatus(true);
                    }
                }
                _queue.clear();
                _pool.purge();
            }
        }
    }

    public static synchronized void request(String url, RequestType requestType, Map<String, List<String>> headers, Integer connectTimeout, Integer readTimeout, IWebRequestListener listener) {
        synchronized (WebRequestThread.class) {
            request(url, requestType, headers, null, connectTimeout, readTimeout, listener);
        }
    }

    public static synchronized void request(String url, RequestType requestType, Map<String, List<String>> headers, String requestBody, Integer connectTimeout, Integer readTimeout, IWebRequestListener listener) {
        synchronized (WebRequestThread.class) {
            if (!_ready) {
                init();
            }
            if (url == null || url.length() < 3) {
                listener.onFailed(url, "Request is NULL or too short");
            } else {
                _queue.add(new WebRequestRunnable(url, requestType.name(), requestBody, connectTimeout.intValue(), readTimeout.intValue(), headers, listener));
            }
        }
    }

    public static synchronized void setConcurrentRequestCount(int count) {
        synchronized (WebRequestThread.class) {
            _corePoolSize = count;
            _maximumPoolSize = _corePoolSize;
            if (_pool != null) {
                _pool.setCorePoolSize(_corePoolSize);
                _pool.setMaximumPoolSize(_maximumPoolSize);
            }
        }
    }

    public static synchronized void setMaximumPoolSize(int count) {
        synchronized (WebRequestThread.class) {
            _maximumPoolSize = count;
            if (_pool != null) {
                _pool.setMaximumPoolSize(_maximumPoolSize);
            }
        }
    }

    public static synchronized void setKeepAliveTime(long milliseconds) {
        synchronized (WebRequestThread.class) {
            _keepAliveTime = milliseconds;
            if (_pool != null) {
                _pool.setKeepAliveTime(_keepAliveTime, TimeUnit.MILLISECONDS);
            }
        }
    }

    public static synchronized boolean resolve(final String host, final IResolveHostListener listener) {
        boolean z;
        synchronized (WebRequestThread.class) {
            if (host != null) {
                if (host.length() >= 3) {
                    new Thread(new Runnable() {
                        public void run() {
                            Exception e;
                            final ConditionVariable cv = new ConditionVariable();
                            Thread t = null;
                            try {
                                Thread t2 = new Thread(new Runnable() {
                                    public void run() {
                                        try {
                                            listener.onResolve(host, InetAddress.getByName(host).getHostAddress());
                                        } catch (UnknownHostException e) {
                                            DeviceLog.exception("Unknown host", e);
                                            listener.onFailed(host, ResolveHostError.UNKNOWN_HOST, e.getMessage());
                                        }
                                        cv.open();
                                    }
                                });
                                try {
                                    t2.start();
                                    t = t2;
                                } catch (Exception e2) {
                                    e = e2;
                                    t = t2;
                                    DeviceLog.exception("Exception while resolving host", e);
                                    listener.onFailed(host, ResolveHostError.UNEXPECTED_EXCEPTION, e.getMessage());
                                    if (!cv.block(20000)) {
                                        return;
                                    }
                                }
                            } catch (Exception e3) {
                                e = e3;
                                DeviceLog.exception("Exception while resolving host", e);
                                listener.onFailed(host, ResolveHostError.UNEXPECTED_EXCEPTION, e.getMessage());
                                if (!cv.block(20000)) {
                                }
                                return;
                            }
                            if (!cv.block(20000) && t != null) {
                                t.interrupt();
                                listener.onFailed(host, ResolveHostError.TIMEOUT, "Timeout");
                            }
                        }
                    }).start();
                    z = true;
                }
            }
            listener.onFailed(host, ResolveHostError.INVALID_HOST, "Host is NULL");
            z = false;
        }
        return z;
    }
}
