// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.cache;

import android.os.Looper;
import com.unity3d.services.core.log.DeviceLog;
import java.util.Iterator;
import android.os.Message;
import android.os.Bundle;
import java.util.List;
import java.util.HashMap;

public class CacheThread extends Thread
{
    public static final int MSG_DOWNLOAD = 1;
    private static int _connectTimeout;
    private static CacheThreadHandler _handler;
    private static int _progressInterval;
    private static int _readTimeout;
    private static boolean _ready;
    private static final Object _readyLock;
    
    static {
        CacheThread._handler = null;
        CacheThread._ready = false;
        _readyLock = new Object();
        CacheThread._connectTimeout = 30000;
        CacheThread._readTimeout = 30000;
        CacheThread._progressInterval = 0;
    }
    
    public static void cancel() {
        if (!CacheThread._ready) {
            return;
        }
        CacheThread._handler.removeMessages(1);
        CacheThread._handler.setCancelStatus(true);
    }
    
    public static void download(final String s, String s2, final HashMap<String, List<String>> hashMap, final boolean b) {
        final Bundle data;
        synchronized (CacheThread.class) {
            if (!CacheThread._ready) {
                init();
            }
            data = new Bundle();
            data.putString("source", s);
            data.putString("target", s2);
            data.putInt("connectTimeout", CacheThread._connectTimeout);
            data.putInt("readTimeout", CacheThread._readTimeout);
            data.putInt("progressInterval", CacheThread._progressInterval);
            data.putBoolean("append", b);
            if (hashMap != null) {
                final Iterator<String> iterator = hashMap.keySet().iterator();
                while (iterator.hasNext()) {
                    s2 = iterator.next();
                    data.putStringArray(s2, (String[])hashMap.get(s2).toArray(new String[hashMap.get(s2).size()]));
                }
            }
        }
        final Message message = new Message();
        message.what = 1;
        message.setData(data);
        CacheThread._handler.setCancelStatus(false);
        CacheThread._handler.sendMessage(message);
    }
    // monitorexit(CacheThread.class)
    
    public static int getConnectTimeout() {
        return CacheThread._connectTimeout;
    }
    
    public static int getProgressInterval() {
        return CacheThread._progressInterval;
    }
    
    public static int getReadTimeout() {
        return CacheThread._readTimeout;
    }
    
    private static void init() {
        final CacheThread cacheThread = new CacheThread();
        cacheThread.setName("UnityAdsCacheThread");
        cacheThread.start();
        while (!CacheThread._ready) {
            try {
                synchronized (CacheThread._readyLock) {
                    CacheThread._readyLock.wait();
                }
            }
            catch (InterruptedException ex) {
                DeviceLog.debug("Couldn't synchronize thread");
                continue;
            }
            break;
        }
    }
    
    public static boolean isActive() {
        return CacheThread._ready && CacheThread._handler.isActive();
    }
    
    public static void setConnectTimeout(final int connectTimeout) {
        CacheThread._connectTimeout = connectTimeout;
    }
    
    public static void setProgressInterval(final int progressInterval) {
        CacheThread._progressInterval = progressInterval;
    }
    
    public static void setReadTimeout(final int readTimeout) {
        CacheThread._readTimeout = readTimeout;
    }
    
    @Override
    public void run() {
        Looper.prepare();
        CacheThread._handler = new CacheThreadHandler();
        CacheThread._ready = true;
        synchronized (CacheThread._readyLock) {
            CacheThread._readyLock.notify();
            Looper.loop();
        }
    }
}
