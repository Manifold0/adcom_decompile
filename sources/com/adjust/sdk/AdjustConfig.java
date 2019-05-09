package com.adjust.sdk;

import android.content.Context;
import java.util.List;

public class AdjustConfig {
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

    public AdjustConfig(Context context, String appToken, String environment) {
        init(context, appToken, environment, false);
    }

    public AdjustConfig(Context context, String appToken, String environment, boolean allowSuppressLogLevel) {
        init(context, appToken, environment, allowSuppressLogLevel);
    }

    private void init(Context context, String appToken, String environment, boolean allowSuppressLogLevel) {
        this.logger = AdjustFactory.getLogger();
        if (allowSuppressLogLevel && ENVIRONMENT_PRODUCTION.equals(environment)) {
            setLogLevel(LogLevel.SUPRESS, environment);
        } else {
            setLogLevel(LogLevel.INFO, environment);
        }
        if (isValid(context, appToken, environment)) {
            this.context = context.getApplicationContext();
            this.appToken = appToken;
            this.environment = environment;
            this.eventBufferingEnabled = false;
            this.sendInBackground = false;
        }
    }

    public void setEventBufferingEnabled(Boolean eventBufferingEnabled) {
        if (eventBufferingEnabled == null) {
            this.eventBufferingEnabled = false;
        } else {
            this.eventBufferingEnabled = eventBufferingEnabled.booleanValue();
        }
    }

    public void setSendInBackground(boolean sendInBackground) {
        this.sendInBackground = sendInBackground;
    }

    public void setLogLevel(LogLevel logLevel) {
        setLogLevel(logLevel, this.environment);
    }

    public void setSdkPrefix(String sdkPrefix) {
        this.sdkPrefix = sdkPrefix;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public void setDefaultTracker(String defaultTracker) {
        this.defaultTracker = defaultTracker;
    }

    public void setOnAttributionChangedListener(OnAttributionChangedListener onAttributionChangedListener) {
        this.onAttributionChangedListener = onAttributionChangedListener;
    }

    public void setDeviceKnown(boolean deviceKnown) {
        this.deviceKnown = Boolean.valueOf(deviceKnown);
    }

    public void setDeepLinkComponent(Class deepLinkComponent) {
        this.deepLinkComponent = deepLinkComponent;
    }

    public void setOnEventTrackingSucceededListener(OnEventTrackingSucceededListener onEventTrackingSucceededListener) {
        this.onEventTrackingSucceededListener = onEventTrackingSucceededListener;
    }

    public void setOnEventTrackingFailedListener(OnEventTrackingFailedListener onEventTrackingFailedListener) {
        this.onEventTrackingFailedListener = onEventTrackingFailedListener;
    }

    public void setOnSessionTrackingSucceededListener(OnSessionTrackingSucceededListener onSessionTrackingSucceededListener) {
        this.onSessionTrackingSucceededListener = onSessionTrackingSucceededListener;
    }

    public void setOnSessionTrackingFailedListener(OnSessionTrackingFailedListener onSessionTrackingFailedListener) {
        this.onSessionTrackingFailedListener = onSessionTrackingFailedListener;
    }

    public void setOnDeeplinkResponseListener(OnDeeplinkResponseListener onDeeplinkResponseListener) {
        this.onDeeplinkResponseListener = onDeeplinkResponseListener;
    }

    public void setDelayStart(double delayStart) {
        this.delayStart = Double.valueOf(delayStart);
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public void setAppSecret(long secretId, long info1, long info2, long info3, long info4) {
        this.secretId = Util.formatString("%d", Long.valueOf(secretId));
        this.appSecret = Util.formatString("%d%d%d%d", Long.valueOf(info1), Long.valueOf(info2), Long.valueOf(info3), Long.valueOf(info4));
    }

    public void setReadMobileEquipmentIdentity(boolean readMobileEquipmentIdentity) {
        this.readMobileEquipmentIdentity = readMobileEquipmentIdentity;
    }

    public boolean isValid() {
        return this.appToken != null;
    }

    private boolean isValid(Context context, String appToken, String environment) {
        if (checkAppToken(appToken) && checkEnvironment(environment) && checkContext(context)) {
            return true;
        }
        return false;
    }

    private void setLogLevel(LogLevel logLevel, String environment) {
        this.logger.setLogLevel(logLevel, ENVIRONMENT_PRODUCTION.equals(environment));
    }

    private boolean checkContext(Context context) {
        if (context == null) {
            this.logger.error("Missing context", new Object[0]);
            return false;
        } else if (Util.checkPermission(context, "android.permission.INTERNET")) {
            return true;
        } else {
            this.logger.error("Missing permission: INTERNET", new Object[0]);
            return false;
        }
    }

    private boolean checkAppToken(String appToken) {
        if (appToken == null) {
            this.logger.error("Missing App Token", new Object[0]);
            return false;
        } else if (appToken.length() == 12) {
            return true;
        } else {
            this.logger.error("Malformed App Token '%s'", appToken);
            return false;
        }
    }

    private boolean checkEnvironment(String environment) {
        if (environment == null) {
            this.logger.error("Missing environment", new Object[0]);
            return false;
        } else if (environment.equals(ENVIRONMENT_SANDBOX)) {
            this.logger.warnInProduction("SANDBOX: Adjust is running in Sandbox mode. Use this setting for testing. Don't forget to set the environment to `production` before publishing!", new Object[0]);
            return true;
        } else if (environment.equals(ENVIRONMENT_PRODUCTION)) {
            this.logger.warnInProduction("PRODUCTION: Adjust is running in Production mode. Use this setting only for the build that you want to publish. Set the environment to `sandbox` if you want to test your app!", new Object[0]);
            return true;
        } else {
            this.logger.error("Unknown environment '%s'", environment);
            return false;
        }
    }
}
