package com.vungle.warren.ui;

import android.util.Log;
import android.webkit.JavascriptInterface;

public class JavascriptBridge {
    private ActionHandler handler;

    public interface ActionHandler {
        void handleAction(String str);
    }

    public JavascriptBridge(ActionHandler actionHandler) {
        this.handler = actionHandler;
    }

    @JavascriptInterface
    public void performAction(String action) {
        Log.d("JavascriptBridge", "actionClicked(" + action + ")");
        this.handler.handleAction(action);
    }
}
