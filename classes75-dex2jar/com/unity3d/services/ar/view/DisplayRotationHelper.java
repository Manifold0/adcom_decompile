// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.ar.view;

import com.google.ar.core.Session;
import android.os.Handler;
import android.hardware.display.DisplayManager;
import android.view.WindowManager;
import android.view.Display;
import android.content.Context;
import android.annotation.TargetApi;
import android.hardware.display.DisplayManager$DisplayListener;

@TargetApi(23)
public class DisplayRotationHelper implements DisplayManager$DisplayListener
{
    private final Context context;
    private final Display display;
    private boolean viewportChanged;
    private int viewportHeight;
    private int viewportWidth;
    
    DisplayRotationHelper(final Context context) {
        this.context = context;
        this.display = ((WindowManager)context.getSystemService((Class)WindowManager.class)).getDefaultDisplay();
    }
    
    public int getRotation() {
        return this.display.getRotation();
    }
    
    public void onDisplayAdded(final int n) {
    }
    
    public void onDisplayChanged(final int n) {
        this.viewportChanged = true;
    }
    
    public void onDisplayRemoved(final int n) {
    }
    
    void onPause() {
        ((DisplayManager)this.context.getSystemService((Class)DisplayManager.class)).unregisterDisplayListener((DisplayManager$DisplayListener)this);
    }
    
    void onResume() {
        ((DisplayManager)this.context.getSystemService((Class)DisplayManager.class)).registerDisplayListener((DisplayManager$DisplayListener)this, (Handler)null);
    }
    
    void onSurfaceChanged(final int viewportWidth, final int viewportHeight) {
        this.viewportWidth = viewportWidth;
        this.viewportHeight = viewportHeight;
        this.viewportChanged = true;
    }
    
    void updateSessionIfNeeded(final Session session) {
        if (this.viewportChanged) {
            session.setDisplayGeometry(this.display.getRotation(), this.viewportWidth, this.viewportHeight);
            this.viewportChanged = false;
        }
    }
}
