// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.configuration;

import com.unity3d.services.core.cache.CacheDirectory;
import android.os.Build$VERSION;
import com.unity3d.services.core.webview.WebView;
import android.annotation.TargetApi;
import com.unity3d.services.core.lifecycle.LifecycleListener;
import android.app.Application$ActivityLifecycleCallbacks;
import com.unity3d.services.core.properties.ClientProperties;
import com.unity3d.services.core.api.Lifecycle;
import com.unity3d.services.core.connectivity.ConnectivityMonitor;
import android.os.ConditionVariable;
import com.unity3d.services.core.connectivity.IConnectivityListener;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;
import com.unity3d.services.core.request.WebRequest;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import com.unity3d.services.core.misc.Utilities;
import java.io.File;
import com.unity3d.services.core.webview.WebViewApp;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.properties.SdkProperties;

public class InitializeThread extends Thread
{
    private static InitializeThread _thread;
    private InitializeState _state;
    private boolean _stopThread;
    
    private InitializeThread(final InitializeState state) {
        this._stopThread = false;
        this._state = state;
    }
    
    public static void initialize(final Configuration configuration) {
        synchronized (InitializeThread.class) {
            if (InitializeThread._thread == null) {
                (InitializeThread._thread = new InitializeThread((InitializeState)new InitializeStateReset(configuration))).setName("UnityAdsInitializeThread");
                InitializeThread._thread.start();
            }
        }
    }
    
    public static void reset() {
        synchronized (InitializeThread.class) {
            if (InitializeThread._thread == null) {
                (InitializeThread._thread = new InitializeThread((InitializeState)new InitializeStateForceReset())).setName("UnityAdsResetThread");
                InitializeThread._thread.start();
            }
        }
    }
    
    public void quit() {
        this._stopThread = true;
    }
    
    @Override
    public void run() {
        while (this._state != null && !(this._state instanceof InitializeStateComplete) && !this._stopThread) {
            this._state = this._state.execute();
        }
        InitializeThread._thread = null;
    }
    
    private abstract static class InitializeState
    {
        public abstract InitializeState execute();
    }
    
    public static class InitializeStateComplete extends InitializeState
    {
        private Configuration _configuration;
        
        public InitializeStateComplete(final Configuration configuration) {
            this._configuration = configuration;
        }
        
        @Override
        public InitializeState execute() {
            final String[] moduleConfigurationList = this._configuration.getModuleConfigurationList();
            for (int length = moduleConfigurationList.length, i = 0; i < length; ++i) {
                final IModuleConfiguration moduleConfiguration = this._configuration.getModuleConfiguration(moduleConfigurationList[i]);
                if (moduleConfiguration != null) {
                    moduleConfiguration.initCompleteState(this._configuration);
                }
            }
            return null;
        }
    }
    
    public static class InitializeStateConfig extends InitializeState
    {
        private Configuration _configuration;
        private int _maxRetries;
        private int _retries;
        private int _retryDelay;
        
        public InitializeStateConfig(final Configuration configuration) {
            this._retries = 0;
            this._maxRetries = 6;
            this._retryDelay = 5;
            this._configuration = configuration;
        }
        
        @Override
        public InitializeState execute() {
            DeviceLog.info("Unity Ads init: load configuration from " + SdkProperties.getConfigUrl());
            try {
                this._configuration.makeRequest();
                return new InitializeStateLoadCache(this._configuration);
            }
            catch (Exception ex) {
                if (this._retries < this._maxRetries) {
                    this._retryDelay *= 2;
                    ++this._retries;
                    return new InitializeStateRetry(this, this._retryDelay);
                }
                return new InitializeStateNetworkError(ex, this, this._configuration);
            }
        }
    }
    
    public static class InitializeStateCreate extends InitializeState
    {
        private Configuration _configuration;
        private String _webViewData;
        
        public InitializeStateCreate(final Configuration configuration, final String webViewData) {
            this._configuration = configuration;
            this._webViewData = webViewData;
        }
        
        @Override
        public InitializeState execute() {
            DeviceLog.debug("Unity Ads init: creating webapp");
            final Configuration configuration = this._configuration;
            configuration.setWebViewData(this._webViewData);
            try {
                if (WebViewApp.create(configuration)) {
                    return new InitializeStateComplete(this._configuration);
                }
            }
            catch (IllegalThreadStateException ex) {
                DeviceLog.exception("Illegal Thread", ex);
                return new InitializeStateError("create webapp", ex, this._configuration);
            }
            DeviceLog.error("Unity Ads WebApp creation failed!");
            return new InitializeStateError("create webapp", new Exception("Creation of WebApp failed!"), this._configuration);
        }
        
        public Configuration getConfiguration() {
            return this._configuration;
        }
        
        public String getWebData() {
            return this._webViewData;
        }
    }
    
    public static class InitializeStateError extends InitializeState
    {
        protected Configuration _configuration;
        Exception _exception;
        String _state;
        
        public InitializeStateError(final String state, final Exception exception, final Configuration configuration) {
            this._state = state;
            this._exception = exception;
            this._configuration = configuration;
        }
        
        @Override
        public InitializeState execute() {
            DeviceLog.error("Unity Ads init: halting init in " + this._state + ": " + this._exception.getMessage());
            final String[] moduleConfigurationList = this._configuration.getModuleConfigurationList();
            for (int length = moduleConfigurationList.length, i = 0; i < length; ++i) {
                final IModuleConfiguration moduleConfiguration = this._configuration.getModuleConfiguration(moduleConfigurationList[i]);
                if (moduleConfiguration != null) {
                    moduleConfiguration.initErrorState(this._configuration, this._state, this._exception.getMessage());
                }
            }
            return null;
        }
    }
    
    public static class InitializeStateForceReset extends InitializeStateReset
    {
        public InitializeStateForceReset() {
            super(new Configuration());
        }
        
        @Override
        public InitializeState execute() {
            super.execute();
            return null;
        }
    }
    
    public static class InitializeStateInitModules extends InitializeState
    {
        private Configuration _configuration;
        
        public InitializeStateInitModules(final Configuration configuration) {
            this._configuration = configuration;
        }
        
        @Override
        public InitializeState execute() {
            final String[] moduleConfigurationList = this._configuration.getModuleConfigurationList();
            for (int length = moduleConfigurationList.length, i = 0; i < length; ++i) {
                final IModuleConfiguration moduleConfiguration = this._configuration.getModuleConfiguration(moduleConfigurationList[i]);
                if (moduleConfiguration != null && !moduleConfiguration.initModuleState(this._configuration)) {
                    return null;
                }
            }
            return new InitializeStateConfig(this._configuration);
        }
        
        public Configuration getConfiguration() {
            return this._configuration;
        }
    }
    
    public static class InitializeStateLoadCache extends InitializeState
    {
        private Configuration _configuration;
        
        public InitializeStateLoadCache(final Configuration configuration) {
            this._configuration = configuration;
        }
        
        @Override
        public InitializeState execute() {
            DeviceLog.debug("Unity Ads init: check if webapp can be loaded from local cache");
            byte[] fileBytes;
            try {
                fileBytes = Utilities.readFileBytes(new File(SdkProperties.getLocalWebViewFile()));
                final String sha256 = Utilities.Sha256(fileBytes);
                if (sha256 != null && sha256.equals(this._configuration.getWebViewHash())) {
                    final byte[] array = fileBytes;
                    final String s = "UTF-8";
                    final String s2 = new String(array, s);
                    final String s3 = "Unity Ads init: webapp loaded from local cache";
                    DeviceLog.info(s3);
                    final InitializeStateLoadCache initializeStateLoadCache = this;
                    final Configuration configuration = initializeStateLoadCache._configuration;
                    final String s4 = s2;
                    return new InitializeStateCreate(configuration, s4);
                }
                return new InitializeStateLoadWeb(this._configuration);
            }
            catch (IOException ex) {
                DeviceLog.debug("Unity Ads init: webapp not found in local cache: " + ex.getMessage());
                return new InitializeStateLoadWeb(this._configuration);
            }
            try {
                final byte[] array = fileBytes;
                final String s = "UTF-8";
                final String s2 = new String(array, s);
                final String s3 = "Unity Ads init: webapp loaded from local cache";
                DeviceLog.info(s3);
                final InitializeStateLoadCache initializeStateLoadCache = this;
                final Configuration configuration = initializeStateLoadCache._configuration;
                final String s4 = s2;
                return new InitializeStateCreate(configuration, s4);
            }
            catch (UnsupportedEncodingException ex2) {
                return new InitializeStateError("load cache", ex2, this._configuration);
            }
            return new InitializeStateLoadWeb(this._configuration);
        }
        
        public Configuration getConfiguration() {
            return this._configuration;
        }
    }
    
    public static class InitializeStateLoadWeb extends InitializeState
    {
        private Configuration _configuration;
        private int _maxRetries;
        private int _retries;
        private int _retryDelay;
        
        public InitializeStateLoadWeb(final Configuration configuration) {
            this._retries = 0;
            this._maxRetries = 6;
            this._retryDelay = 5;
            this._configuration = configuration;
        }
        
        @Override
        public InitializeState execute() {
            DeviceLog.info("Unity Ads init: loading webapp from " + this._configuration.getWebViewUrl());
            String s = null;
            String s3 = null;
            Label_0178: {
                WebRequest webRequest;
                try {
                    final WebRequest webRequest2;
                    webRequest = (webRequest2 = new WebRequest(this._configuration.getWebViewUrl(), "GET", null));
                    s = webRequest2.makeRequest();
                    final InitializeStateLoadWeb initializeStateLoadWeb = this;
                    final Configuration configuration = initializeStateLoadWeb._configuration;
                    final String s2 = configuration.getWebViewHash();
                    final String s4;
                    s3 = (s4 = s2);
                    if (s4 == null) {
                        break Label_0178;
                    }
                    final String s5 = s;
                    final String s6 = Utilities.Sha256(s5);
                    final String s7 = s3;
                    final boolean b = s6.equals(s7);
                    if (!b) {
                        final String s8 = "load web";
                        final String s9 = "Invalid webViewHash";
                        final Exception ex = new Exception(s9);
                        final InitializeStateLoadWeb initializeStateLoadWeb2 = this;
                        final Configuration configuration2 = initializeStateLoadWeb2._configuration;
                        return new InitializeStateError(s8, ex, configuration2);
                    }
                    break Label_0178;
                }
                catch (MalformedURLException ex2) {
                    DeviceLog.exception("Malformed URL", ex2);
                    return new InitializeStateError("make webrequest", ex2, this._configuration);
                }
                try {
                    final WebRequest webRequest2 = webRequest;
                    s = webRequest2.makeRequest();
                    final InitializeStateLoadWeb initializeStateLoadWeb = this;
                    final Configuration configuration = initializeStateLoadWeb._configuration;
                    final String s2 = configuration.getWebViewHash();
                    final String s4;
                    s3 = (s4 = s2);
                    if (s4 != null) {
                        final String s5 = s;
                        final String s6 = Utilities.Sha256(s5);
                        final String s7 = s3;
                        final boolean b = s6.equals(s7);
                        if (!b) {
                            final String s8 = "load web";
                            final String s9 = "Invalid webViewHash";
                            final Exception ex = new Exception(s9);
                            final InitializeStateLoadWeb initializeStateLoadWeb2 = this;
                            final Configuration configuration2 = initializeStateLoadWeb2._configuration;
                            return new InitializeStateError(s8, ex, configuration2);
                        }
                    }
                }
                catch (Exception ex3) {
                    if (this._retries < this._maxRetries) {
                        this._retryDelay *= 2;
                        ++this._retries;
                        return new InitializeStateRetry(this, this._retryDelay);
                    }
                    return new InitializeStateNetworkError(ex3, this, this._configuration);
                }
            }
            if (s3 != null) {
                Utilities.writeFile(new File(SdkProperties.getLocalWebViewFile()), s);
            }
            return new InitializeStateCreate(this._configuration, s);
        }
        
        public Configuration getConfiguration() {
            return this._configuration;
        }
    }
    
    public static class InitializeStateNetworkError extends InitializeStateError implements IConnectivityListener
    {
        protected static final int CONNECTED_EVENT_THRESHOLD_MS = 10000;
        protected static final int MAX_CONNECTED_EVENTS = 500;
        private static long _lastConnectedEventTimeMs;
        private static int _receivedConnectedEvents;
        private ConditionVariable _conditionVariable;
        private InitializeState _erroredState;
        
        static {
            InitializeStateNetworkError._receivedConnectedEvents = 0;
            InitializeStateNetworkError._lastConnectedEventTimeMs = 0L;
        }
        
        public InitializeStateNetworkError(final Exception ex, final InitializeState erroredState, final Configuration configuration) {
            super("network error", ex, configuration);
            this._erroredState = erroredState;
        }
        
        private boolean shouldHandleConnectedEvent() {
            return System.currentTimeMillis() - InitializeStateNetworkError._lastConnectedEventTimeMs >= 10000L && InitializeStateNetworkError._receivedConnectedEvents <= 500;
        }
        
        @Override
        public InitializeState execute() {
            DeviceLog.error("Unity Ads init: network error, waiting for connection events");
            this._conditionVariable = new ConditionVariable();
            ConnectivityMonitor.addListener(this);
            if (this._conditionVariable.block(600000L)) {
                ConnectivityMonitor.removeListener(this);
                return this._erroredState;
            }
            ConnectivityMonitor.removeListener(this);
            return new InitializeStateError("network error", new Exception("No connected events within the timeout!"), this._configuration);
        }
        
        @Override
        public void onConnected() {
            ++InitializeStateNetworkError._receivedConnectedEvents;
            DeviceLog.debug("Unity Ads init got connected event");
            if (this.shouldHandleConnectedEvent()) {
                this._conditionVariable.open();
            }
            if (InitializeStateNetworkError._receivedConnectedEvents > 500) {
                ConnectivityMonitor.removeListener(this);
            }
            InitializeStateNetworkError._lastConnectedEventTimeMs = System.currentTimeMillis();
        }
        
        @Override
        public void onDisconnected() {
            DeviceLog.debug("Unity Ads init got disconnected event");
        }
    }
    
    public static class InitializeStateReset extends InitializeState
    {
        private Configuration _configuration;
        
        public InitializeStateReset(final Configuration configuration) {
            this._configuration = configuration;
        }
        
        @TargetApi(14)
        private void unregisterLifecycleCallbacks() {
            if (Lifecycle.getLifecycleListener() != null) {
                if (ClientProperties.getApplication() != null) {
                    ClientProperties.getApplication().unregisterActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)Lifecycle.getLifecycleListener());
                }
                Lifecycle.setLifecycleListener(null);
            }
        }
        
        @Override
        public InitializeState execute() {
            int i = 0;
            DeviceLog.debug("Unity Ads init: starting init");
            final ConditionVariable conditionVariable = new ConditionVariable();
            final WebViewApp currentApp = WebViewApp.getCurrentApp();
            boolean block = true;
            if (currentApp != null) {
                currentApp.setWebAppLoaded(false);
                currentApp.setWebAppInitialized(false);
                if (currentApp.getWebView() != null) {
                    Utilities.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            currentApp.getWebView().destroy();
                            currentApp.setWebView(null);
                            conditionVariable.open();
                        }
                    });
                    block = conditionVariable.block(10000L);
                }
                if (!block) {
                    return new InitializeStateError("reset webapp", new Exception("Reset failed on opening ConditionVariable"), this._configuration);
                }
            }
            if (Build$VERSION.SDK_INT > 13) {
                this.unregisterLifecycleCallbacks();
            }
            SdkProperties.setCacheDirectory(null);
            if (SdkProperties.getCacheDirectory() == null) {
                return new InitializeStateError("reset webapp", new Exception("Cache directory is NULL"), this._configuration);
            }
            SdkProperties.setInitialized(false);
            this._configuration.setConfigUrl(SdkProperties.getConfigUrl());
            for (String[] moduleConfigurationList = this._configuration.getModuleConfigurationList(); i < moduleConfigurationList.length; ++i) {
                final IModuleConfiguration moduleConfiguration = this._configuration.getModuleConfiguration(moduleConfigurationList[i]);
                if (moduleConfiguration != null) {
                    moduleConfiguration.resetState(this._configuration);
                }
            }
            return new InitializeStateInitModules(this._configuration);
        }
    }
    
    public static class InitializeStateRetry extends InitializeState
    {
        int _delay;
        InitializeState _state;
        
        public InitializeStateRetry(final InitializeState state, final int delay) {
            this._state = state;
            this._delay = delay;
        }
        
        @Override
        public InitializeState execute() {
            DeviceLog.debug("Unity Ads init: retrying in " + this._delay + " seconds");
            try {
                Thread.sleep(this._delay * 1000L);
                return this._state;
            }
            catch (InterruptedException ex) {
                DeviceLog.exception("Init retry interrupted", ex);
                return this._state;
            }
        }
    }
}
