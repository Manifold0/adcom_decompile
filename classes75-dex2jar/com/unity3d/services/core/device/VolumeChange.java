// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.device;

import java.util.Iterator;
import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings$System;
import com.unity3d.services.core.properties.ClientProperties;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import android.database.ContentObserver;

public class VolumeChange
{
    private static ContentObserver _contentObserver;
    private static ArrayList<IVolumeChangeListener> _listeners;
    
    public static void clearAllListeners() {
        if (VolumeChange._listeners != null) {
            VolumeChange._listeners.clear();
        }
        stopObservering();
        VolumeChange._listeners = null;
    }
    
    public static void registerListener(final IVolumeChangeListener volumeChangeListener) {
        if (VolumeChange._listeners == null) {
            VolumeChange._listeners = new ArrayList<IVolumeChangeListener>();
        }
        if (!VolumeChange._listeners.contains(volumeChangeListener)) {
            startObserving();
            VolumeChange._listeners.add(volumeChangeListener);
        }
    }
    
    public static void startObserving() {
        if (VolumeChange._contentObserver == null) {
            VolumeChange._contentObserver = new ContentObserver(new Handler(Looper.getMainLooper())) {
                public boolean deliverSelfNotifications() {
                    return false;
                }
                
                public void onChange(final boolean b, final Uri uri) {
                    triggerListeners();
                }
            };
            final Context applicationContext = ClientProperties.getApplicationContext();
            if (applicationContext != null) {
                final ContentResolver contentResolver = applicationContext.getContentResolver();
                if (contentResolver != null) {
                    contentResolver.registerContentObserver(Settings$System.CONTENT_URI, true, VolumeChange._contentObserver);
                }
            }
        }
    }
    
    public static void stopObservering() {
        if (VolumeChange._contentObserver != null) {
            final Context applicationContext = ClientProperties.getApplicationContext();
            if (applicationContext != null) {
                final ContentResolver contentResolver = applicationContext.getContentResolver();
                if (contentResolver != null) {
                    contentResolver.unregisterContentObserver(VolumeChange._contentObserver);
                }
            }
            VolumeChange._contentObserver = null;
        }
    }
    
    private static void triggerListeners() {
        if (VolumeChange._listeners != null) {
            for (final IVolumeChangeListener volumeChangeListener : VolumeChange._listeners) {
                volumeChangeListener.onVolumeChanged(Device.getStreamVolume(volumeChangeListener.getStreamType()));
            }
        }
    }
    
    public static void unregisterListener(final IVolumeChangeListener volumeChangeListener) {
        if (VolumeChange._listeners.contains(volumeChangeListener)) {
            VolumeChange._listeners.remove(volumeChangeListener);
        }
        if (VolumeChange._listeners == null || VolumeChange._listeners.size() == 0) {
            stopObservering();
        }
    }
}
