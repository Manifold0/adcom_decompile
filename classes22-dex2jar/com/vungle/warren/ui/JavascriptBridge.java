// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren.ui;

import android.webkit.JavascriptInterface;
import android.util.Log;

public class JavascriptBridge
{
    private ActionHandler handler;
    
    public JavascriptBridge(final ActionHandler handler) {
        this.handler = handler;
    }
    
    @JavascriptInterface
    public void performAction(final String s) {
        Log.d("JavascriptBridge", "actionClicked(" + s + ")");
        this.handler.handleAction(s);
    }
    
    public interface ActionHandler
    {
        void handleAction(final String p0);
    }
}
