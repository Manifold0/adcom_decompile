package com.unity3d.services.ar.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.hardware.display.DisplayManager.DisplayListener;
import android.view.Display;
import android.view.WindowManager;
import com.google.ar.core.Session;

@TargetApi(23)
public class DisplayRotationHelper implements DisplayListener {
    private final Context context;
    private final Display display;
    private boolean viewportChanged;
    private int viewportHeight;
    private int viewportWidth;

    DisplayRotationHelper(Context context) {
        this.context = context;
        this.display = ((WindowManager) context.getSystemService(WindowManager.class)).getDefaultDisplay();
    }

    void onResume() {
        ((DisplayManager) this.context.getSystemService(DisplayManager.class)).registerDisplayListener(this, null);
    }

    void onPause() {
        ((DisplayManager) this.context.getSystemService(DisplayManager.class)).unregisterDisplayListener(this);
    }

    void onSurfaceChanged(int width, int height) {
        this.viewportWidth = width;
        this.viewportHeight = height;
        this.viewportChanged = true;
    }

    void updateSessionIfNeeded(Session session) {
        if (this.viewportChanged) {
            session.setDisplayGeometry(this.display.getRotation(), this.viewportWidth, this.viewportHeight);
            this.viewportChanged = false;
        }
    }

    public int getRotation() {
        return this.display.getRotation();
    }

    public void onDisplayAdded(int displayId) {
    }

    public void onDisplayRemoved(int displayId) {
    }

    public void onDisplayChanged(int displayId) {
        this.viewportChanged = true;
    }
}
