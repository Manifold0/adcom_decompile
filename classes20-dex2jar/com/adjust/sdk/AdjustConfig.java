// 
// Decompiled by Procyon v0.5.34
// 

package com.adjust.sdk;

import java.util.List;
import android.content.Context;

public class AdjustConfig
{
    public static final String ENVIRONMENT_PRODUCTION = "production";
    public static final String ENVIRONMENT_SANDBOX = "sandbox";
    String appSecret;
    String appToken;
    Context context;
    Class deepLinkComponent;
    String defaultTracker;
    Double delayStart;
    Boolean deviceKnown;
    String environment;
    boolean eventBufferingEnabled;
    ILogger logger;
    OnAttributionChangedListener onAttributionChangedListener;
    OnDeeplinkResponseListener onDeeplinkResponseListener;
    OnEventTrackingFailedListener onEventTrackingFailedListener;
    OnEventTrackingSucceededListener onEventTrackingSucceededListener;
    OnSessionTrackingFailedListener onSessionTrackingFailedListener;
    OnSessionTrackingSucceededListener onSessionTrackingSucceededListener;
    List<IRunActivityHandler> preLaunchActionsArray;
    String processName;
    String pushToken;
    boolean readMobileEquipmentIdentity;
    String sdkPrefix;
    String secretId;
    boolean sendInBackground;
    Boolean startEnabled;
    boolean startOffline;
    String userAgent;
    
    public AdjustConfig(final Context context, final String s, final String s2) {
        this.init(context, s, s2, false);
    }
    
    public AdjustConfig(final Context context, final String s, final String s2, final boolean b) {
        this.init(context, s, s2, b);
    }
    
    private boolean checkAppToken(final String s) {
        if (s == null) {
            this.logger.error("Missing App Token", new Object[0]);
            return false;
        }
        if (s.length() != 12) {
            this.logger.error("Malformed App Token '%s'", s);
            return false;
        }
        return true;
    }
    
    private boolean checkContext(final Context context) {
        if (context == null) {
            this.logger.error("Missing context", new Object[0]);
            return false;
        }
        if (!Util.checkPermission(context, "android.permission.INTERNET")) {
            this.logger.error("Missing permission: INTERNET", new Object[0]);
            return false;
        }
        return true;
    }
    
    private boolean checkEnvironment(final String s) {
        if (s == null) {
            this.logger.error("Missing environment", new Object[0]);
            return false;
        }
        if (s.equals("sandbox")) {
            this.logger.warnInProduction("SANDBOX: Adjust is running in Sandbox mode. Use this setting for testing. Don't forget to set the environment to `production` before publishing!", new Object[0]);
            return true;
        }
        if (s.equals("production")) {
            this.logger.warnInProduction("PRODUCTION: Adjust is running in Production mode. Use this setting only for the build that you want to publish. Set the environment to `sandbox` if you want to test your app!", new Object[0]);
            return true;
        }
        this.logger.error("Unknown environment '%s'", s);
        return false;
    }
    
    private void init(final Context context, final String appToken, final String environment, final boolean b) {
        this.logger = AdjustFactory.getLogger();
        if (b && "production".equals(environment)) {
            this.setLogLevel(LogLevel.SUPRESS, environment);
        }
        else {
            this.setLogLevel(LogLevel.INFO, environment);
        }
        if (!this.isValid(context, appToken, environment)) {
            return;
        }
        this.context = context.getApplicationContext();
        this.appToken = appToken;
        this.environment = environment;
        this.eventBufferingEnabled = false;
        this.sendInBackground = false;
    }
    
    private boolean isValid(final Context context, final String s, final String s2) {
        return this.checkAppToken(s) && this.checkEnvironment(s2) && this.checkContext(context);
    }
    
    private void setLogLevel(final LogLevel logLevel, final String s) {
        this.logger.setLogLevel(logLevel, "production".equals(s));
    }
    
    public boolean isValid() {
        return this.appToken != null;
    }
    
    public void setAppSecret(final long n, final long n2, final long n3, final long n4, final long n5) {
        this.secretId = Util.formatString("%d", n);
        this.appSecret = Util.formatString("%d%d%d%d", n2, n3, n4, n5);
    }
    
    public void setDeepLinkComponent(final Class deepLinkComponent) {
        this.deepLinkComponent = deepLinkComponent;
    }
    
    public void setDefaultTracker(final String defaultTracker) {
        this.defaultTracker = defaultTracker;
    }
    
    public void setDelayStart(final double n) {
        this.delayStart = n;
    }
    
    public void setDeviceKnown(final boolean b) {
        this.deviceKnown = b;
    }
    
    public void setEventBufferingEnabled(final Boolean b) {
        if (b == null) {
            this.eventBufferingEnabled = false;
            return;
        }
        this.eventBufferingEnabled = b;
    }
    
    public void setLogLevel(final LogLevel logLevel) {
        this.setLogLevel(logLevel, this.environment);
    }
    
    public void setOnAttributionChangedListener(final OnAttributionChangedListener onAttributionChangedListener) {
        this.onAttributionChangedListener = onAttributionChangedListener;
    }
    
    public void setOnDeeplinkResponseListener(final OnDeeplinkResponseListener onDeeplinkResponseListener) {
        this.onDeeplinkResponseListener = onDeeplinkResponseListener;
    }
    
    public void setOnEventTrackingFailedListener(final OnEventTrackingFailedListener onEventTrackingFailedListener) {
        this.onEventTrackingFailedListener = onEventTrackingFailedListener;
    }
    
    public void setOnEventTrackingSucceededListener(final OnEventTrackingSucceededListener onEventTrackingSucceededListener) {
        this.onEventTrackingSucceededListener = onEventTrackingSucceededListener;
    }
    
    public void setOnSessionTrackingFailedListener(final OnSessionTrackingFailedListener onSessionTrackingFailedListener) {
        this.onSessionTrackingFailedListener = onSessionTrackingFailedListener;
    }
    
    public void setOnSessionTrackingSucceededListener(final OnSessionTrackingSucceededListener onSessionTrackingSucceededListener) {
        this.onSessionTrackingSucceededListener = onSessionTrackingSucceededListener;
    }
    
    public void setProcessName(final String processName) {
        this.processName = processName;
    }
    
    public void setReadMobileEquipmentIdentity(final boolean readMobileEquipmentIdentity) {
        this.readMobileEquipmentIdentity = readMobileEquipmentIdentity;
    }
    
    public void setSdkPrefix(final String sdkPrefix) {
        this.sdkPrefix = sdkPrefix;
    }
    
    public void setSendInBackground(final boolean sendInBackground) {
        this.sendInBackground = sendInBackground;
    }
    
    public void setUserAgent(final String userAgent) {
        this.userAgent = userAgent;
    }
}
