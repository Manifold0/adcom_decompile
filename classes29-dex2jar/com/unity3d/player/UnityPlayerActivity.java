// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.player;

import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.content.Context;
import android.os.Bundle;
import android.content.res.Configuration;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.app.Activity;

public class UnityPlayerActivity extends Activity
{
    protected UnityPlayer mUnityPlayer;
    
    public boolean dispatchKeyEvent(final KeyEvent keyEvent) {
        if (keyEvent.getAction() == 2) {
            return this.mUnityPlayer.injectEvent((InputEvent)keyEvent);
        }
        return super.dispatchKeyEvent(keyEvent);
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mUnityPlayer.configurationChanged(configuration);
    }
    
    protected void onCreate(final Bundle bundle) {
        this.requestWindowFeature(1);
        super.onCreate(bundle);
        this.setContentView((View)(this.mUnityPlayer = new UnityPlayer((Context)this)));
        this.mUnityPlayer.requestFocus();
    }
    
    protected void onDestroy() {
        this.mUnityPlayer.quit();
        super.onDestroy();
    }
    
    public boolean onGenericMotionEvent(final MotionEvent motionEvent) {
        return this.mUnityPlayer.injectEvent((InputEvent)motionEvent);
    }
    
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        return this.mUnityPlayer.injectEvent((InputEvent)keyEvent);
    }
    
    public boolean onKeyUp(final int n, final KeyEvent keyEvent) {
        return this.mUnityPlayer.injectEvent((InputEvent)keyEvent);
    }
    
    public void onLowMemory() {
        super.onLowMemory();
        this.mUnityPlayer.lowMemory();
    }
    
    protected void onNewIntent(final Intent intent) {
        this.setIntent(intent);
    }
    
    protected void onPause() {
        super.onPause();
        this.mUnityPlayer.pause();
    }
    
    protected void onResume() {
        super.onResume();
        this.mUnityPlayer.resume();
    }
    
    protected void onStart() {
        super.onStart();
        this.mUnityPlayer.start();
    }
    
    protected void onStop() {
        super.onStop();
        this.mUnityPlayer.stop();
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        return this.mUnityPlayer.injectEvent((InputEvent)motionEvent);
    }
    
    public void onTrimMemory(final int n) {
        super.onTrimMemory(n);
        if (n == 15) {
            this.mUnityPlayer.lowMemory();
        }
    }
    
    public void onWindowFocusChanged(final boolean b) {
        super.onWindowFocusChanged(b);
        this.mUnityPlayer.windowFocusChanged(b);
    }
}
