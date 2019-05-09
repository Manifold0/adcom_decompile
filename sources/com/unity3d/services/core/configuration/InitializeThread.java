package com.unity3d.services.core.configuration;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.os.ConditionVariable;
import com.tapjoy.TapjoyConstants;
import com.unity3d.services.core.api.Lifecycle;
import com.unity3d.services.core.connectivity.ConnectivityMonitor;
import com.unity3d.services.core.connectivity.IConnectivityListener;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.misc.Utilities;
import com.unity3d.services.core.properties.ClientProperties;
import com.unity3d.services.core.properties.SdkProperties;
import com.unity3d.services.core.request.WebRequest;
import com.unity3d.services.core.webview.WebViewApp;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

public class InitializeThread extends Thread {
    private static InitializeThread _thread;
    private InitializeState _state;
    private boolean _stopThread = false;

    private static abstract class InitializeState {
        public abstract InitializeState execute();

        private InitializeState() {
        }
    }

    public static class InitializeStateComplete extends InitializeState {
        private Configuration _configuration;

        public InitializeStateComplete(Configuration configuration) {
            super();
            this._configuration = configuration;
        }

        public InitializeState execute() {
            for (String moduleName : this._configuration.getModuleConfigurationList()) {
                IModuleConfiguration moduleConfiguration = this._configuration.getModuleConfiguration(moduleName);
                if (moduleConfiguration != null) {
                    moduleConfiguration.initCompleteState(this._configuration);
                }
            }
            return null;
        }
    }

    public static class InitializeStateConfig extends InitializeState {
        private Configuration _configuration;
        private int _maxRetries = 6;
        private int _retries = 0;
        private int _retryDelay = 5;

        public InitializeStateConfig(Configuration configuration) {
            super();
            this._configuration = configuration;
        }

        public InitializeState execute() {
            DeviceLog.info("Unity Ads init: load configuration from " + SdkProperties.getConfigUrl());
            try {
                this._configuration.makeRequest();
                return new InitializeStateLoadCache(this._configuration);
            } catch (Exception e) {
                if (this._retries >= this._maxRetries) {
                    return new InitializeStateNetworkError(e, this, this._configuration);
                }
                this._retryDelay *= 2;
                this._retries++;
                return new InitializeStateRetry(this, this._retryDelay);
            }
        }
    }

    public static class InitializeStateCreate extends InitializeState {
        private Configuration _configuration;
        private String _webViewData;

        public InitializeStateCreate(Configuration configuration, String webViewData) {
            super();
            this._configuration = configuration;
            this._webViewData = webViewData;
        }

        public Configuration getConfiguration() {
            return this._configuration;
        }

        public String getWebData() {
            return this._webViewData;
        }

        public InitializeState execute() {
            DeviceLog.debug("Unity Ads init: creating webapp");
            Configuration configuration = this._configuration;
            configuration.setWebViewData(this._webViewData);
            try {
                if (WebViewApp.create(configuration)) {
                    return new InitializeStateComplete(this._configuration);
                }
                DeviceLog.error("Unity Ads WebApp creation failed!");
                return new InitializeStateError("create webapp", new Exception("Creation of WebApp failed!"), this._configuration);
            } catch (IllegalThreadStateException e) {
                DeviceLog.exception("Illegal Thread", e);
                return new InitializeStateError("create webapp", e, this._configuration);
            }
        }
    }

    public static class InitializeStateError extends InitializeState {
        protected Configuration _configuration;
        Exception _exception;
        String _state;

        public InitializeStateError(String state, Exception exception, Configuration configuration) {
            super();
            this._state = state;
            this._exception = exception;
            this._configuration = configuration;
        }

        public InitializeState execute() {
            DeviceLog.error("Unity Ads init: halting init in " + this._state + ": " + this._exception.getMessage());
            for (String moduleName : this._configuration.getModuleConfigurationList()) {
                IModuleConfiguration moduleConfiguration = this._configuration.getModuleConfiguration(moduleName);
                if (moduleConfiguration != null) {
                    moduleConfiguration.initErrorState(this._configuration, this._state, this._exception.getMessage());
                }
            }
            return null;
        }
    }

    public static class InitializeStateReset extends InitializeState {
        private Configuration _configuration;

        public InitializeStateReset(Configuration configuration) {
            super();
            this._configuration = configuration;
        }

        public InitializeState execute() {
            int i = 0;
            DeviceLog.debug("Unity Ads init: starting init");
            final ConditionVariable cv = new ConditionVariable();
            final WebViewApp currentApp = WebViewApp.getCurrentApp();
            boolean success = true;
            if (currentApp != null) {
                currentApp.setWebAppLoaded(false);
                currentApp.setWebAppInitialized(false);
                if (currentApp.getWebView() != null) {
                    Utilities.runOnUiThread(new Runnable() {
                        public void run() {
                            currentApp.getWebView().destroy();
                            currentApp.setWebView(null);
                            cv.open();
                        }
                    });
                    success = cv.block(TapjoyConstants.TIMER_INCREMENT);
                }
                if (!success) {
                    return new InitializeStateError("reset webapp", new Exception("Reset failed on opening ConditionVariable"), this._configuration);
                }
            }
            if (VERSION.SDK_INT > 13) {
                unregisterLifecycleCallbacks();
            }
            SdkProperties.setCacheDirectory(null);
            if (SdkProperties.getCacheDirectory() == null) {
                return new InitializeStateError("reset webapp", new Exception("Cache directory is NULL"), this._configuration);
            }
            SdkProperties.setInitialized(false);
            this._configuration.setConfigUrl(SdkProperties.getConfigUrl());
            String[] moduleConfigurationList = this._configuration.getModuleConfigurationList();
            int length = moduleConfigurationList.length;
            while (i < length) {
                IModuleConfiguration moduleConfiguration = this._configuration.getModuleConfiguration(moduleConfigurationList[i]);
                if (moduleConfiguration != null) {
                    moduleConfiguration.resetState(this._configuration);
                }
                i++;
            }
            return new InitializeStateInitModules(this._configuration);
        }

        @TargetApi(14)
        private void unregisterLifecycleCallbacks() {
            if (Lifecycle.getLifecycleListener() != null) {
                if (ClientProperties.getApplication() != null) {
                    ClientProperties.getApplication().unregisterActivityLifecycleCallbacks(Lifecycle.getLifecycleListener());
                }
                Lifecycle.setLifecycleListener(null);
            }
        }
    }

    public static class InitializeStateForceReset extends InitializeStateReset {
        public InitializeStateForceReset() {
            super(new Configuration());
        }

        public InitializeState execute() {
            super.execute();
            return null;
        }
    }

    public static class InitializeStateInitModules extends InitializeState {
        private Configuration _configuration;

        public InitializeStateInitModules(Configuration configuration) {
            super();
            this._configuration = configuration;
        }

        public Configuration getConfiguration() {
            return this._configuration;
        }

        public InitializeState execute() {
            for (String moduleName : this._configuration.getModuleConfigurationList()) {
                IModuleConfiguration moduleConfiguration = this._configuration.getModuleConfiguration(moduleName);
                if (moduleConfiguration != null && !moduleConfiguration.initModuleState(this._configuration)) {
                    return null;
                }
            }
            return new InitializeStateConfig(this._configuration);
        }
    }

    public static class InitializeStateLoadCache extends InitializeState {
        private Configuration _configuration;

        public InitializeStateLoadCache(Configuration configuration) {
            super();
            this._configuration = configuration;
        }

        public Configuration getConfiguration() {
            return this._configuration;
        }

        public InitializeState execute() {
            DeviceLog.debug("Unity Ads init: check if webapp can be loaded from local cache");
            try {
                byte[] localWebViewData = Utilities.readFileBytes(new File(SdkProperties.getLocalWebViewFile()));
                String localWebViewHash = Utilities.Sha256(localWebViewData);
                if (localWebViewHash == null || !localWebViewHash.equals(this._configuration.getWebViewHash())) {
                    return new InitializeStateLoadWeb(this._configuration);
                }
                try {
                    String webViewDataString = new String(localWebViewData, "UTF-8");
                    DeviceLog.info("Unity Ads init: webapp loaded from local cache");
                    return new InitializeStateCreate(this._configuration, webViewDataString);
                } catch (UnsupportedEncodingException e) {
                    return new InitializeStateError("load cache", e, this._configuration);
                }
            } catch (IOException e2) {
                DeviceLog.debug("Unity Ads init: webapp not found in local cache: " + e2.getMessage());
                return new InitializeStateLoadWeb(this._configuration);
            }
        }
    }

    public static class InitializeStateLoadWeb extends InitializeState {
        private Configuration _configuration;
        private int _maxRetries = 6;
        private int _retries = 0;
        private int _retryDelay = 5;

        public InitializeStateLoadWeb(Configuration configuration) {
            super();
            this._configuration = configuration;
        }

        public Configuration getConfiguration() {
            return this._configuration;
        }

        public InitializeState execute() {
            DeviceLog.info("Unity Ads init: loading webapp from " + this._configuration.getWebViewUrl());
            try {
                try {
                    String webViewData = new WebRequest(this._configuration.getWebViewUrl(), "GET", null).makeRequest();
                    String webViewHash = this._configuration.getWebViewHash();
                    if (webViewHash != null && !Utilities.Sha256(webViewData).equals(webViewHash)) {
                        return new InitializeStateError("load web", new Exception("Invalid webViewHash"), this._configuration);
                    }
                    if (webViewHash != null) {
                        Utilities.writeFile(new File(SdkProperties.getLocalWebViewFile()), webViewData);
                    }
                    return new InitializeStateCreate(this._configuration, webViewData);
                } catch (Exception e) {
                    if (this._retries >= this._maxRetries) {
                        return new InitializeStateNetworkError(e, this, this._configuration);
                    }
                    this._retryDelay *= 2;
                    this._retries++;
                    return new InitializeStateRetry(this, this._retryDelay);
                }
            } catch (MalformedURLException e2) {
                DeviceLog.exception("Malformed URL", e2);
                return new InitializeStateError("make webrequest", e2, this._configuration);
            }
        }
    }

    public static class InitializeStateNetworkError extends InitializeStateError implements IConnectivityListener {
        protected static final int CONNECTED_EVENT_THRESHOLD_MS = 10000;
        protected static final int MAX_CONNECTED_EVENTS = 500;
        private static long _lastConnectedEventTimeMs = 0;
        private static int _receivedConnectedEvents = 0;
        private ConditionVariable _conditionVariable;
        private InitializeState _erroredState;

        public InitializeStateNetworkError(Exception exception, InitializeState erroredState, Configuration configuration) {
            super("network error", exception, configuration);
            this._erroredState = erroredState;
        }

        public InitializeState execute() {
            DeviceLog.error("Unity Ads init: network error, waiting for connection events");
            this._conditionVariable = new ConditionVariable();
            ConnectivityMonitor.addListener(this);
            if (this._conditionVariable.block(600000)) {
                ConnectivityMonitor.removeListener(this);
                return this._erroredState;
            }
            ConnectivityMonitor.removeListener(this);
            return new InitializeStateError("network error", new Exception("No connected events within the timeout!"), this._configuration);
        }

        public void onConnected() {
            _receivedConnectedEvents++;
            DeviceLog.debug("Unity Ads init got connected event");
            if (shouldHandleConnectedEvent()) {
                this._conditionVariable.open();
            }
            if (_receivedConnectedEvents > 500) {
                ConnectivityMonitor.removeListener(this);
            }
            _lastConnectedEventTimeMs = System.currentTimeMillis();
        }

        public void onDisconnected() {
            DeviceLog.debug("Unity Ads init got disconnected event");
        }

        private boolean shouldHandleConnectedEvent() {
            if (System.currentTimeMillis() - _lastConnectedEventTimeMs < TapjoyConstants.TIMER_INCREMENT || _receivedConnectedEvents > 500) {
                return false;
            }
            return true;
        }
    }

    public static class InitializeStateRetry extends InitializeState {
        int _delay;
        InitializeState _state;

        public InitializeStateRetry(InitializeState state, int delay) {
            super();
            this._state = state;
            this._delay = delay;
        }

        public InitializeState execute() {
            DeviceLog.debug("Unity Ads init: retrying in " + this._delay + " seconds");
            try {
                Thread.sleep(((long) this._delay) * 1000);
            } catch (InterruptedException e) {
                DeviceLog.exception("Init retry interrupted", e);
            }
            return this._state;
        }
    }

    private InitializeThread(InitializeState state) {
        this._state = state;
    }

    public void run() {
        while (this._state != null && !(this._state instanceof InitializeStateComplete) && !this._stopThread) {
            this._state = this._state.execute();
        }
        _thread = null;
    }

    public void quit() {
        this._stopThread = true;
    }

    public static synchronized void initialize(Configuration configuration) {
        synchronized (InitializeThread.class) {
            if (_thread == null) {
                _thread = new InitializeThread(new InitializeStateReset(configuration));
                _thread.setName("UnityAdsInitializeThread");
                _thread.start();
            }
        }
    }

    public static synchronized void reset() {
        synchronized (InitializeThread.class) {
            if (_thread == null) {
                _thread = new InitializeThread(new InitializeStateForceReset());
                _thread.setName("UnityAdsResetThread");
                _thread.start();
            }
        }
    }
}
