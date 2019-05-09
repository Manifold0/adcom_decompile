package com.adjust.sdk;

import android.content.Context;
import android.net.Uri;
import java.util.ArrayList;
import java.util.List;

public class AdjustInstance {
    private IActivityHandler activityHandler;
    private List<IRunActivityHandler> preLaunchActionsArray;
    private String pushToken;
    private Boolean startEnabled = null;
    private boolean startOffline = false;

    /* renamed from: com.adjust.sdk.AdjustInstance$5 */
    class C00745 implements IRunActivityHandler {
        C00745() {
        }

        public void run(ActivityHandler activityHandler) {
            activityHandler.resetSessionCallbackParametersI();
        }
    }

    /* renamed from: com.adjust.sdk.AdjustInstance$6 */
    class C00756 implements IRunActivityHandler {
        C00756() {
        }

        public void run(ActivityHandler activityHandler) {
            activityHandler.resetSessionPartnerParametersI();
        }
    }

    public void onCreate(AdjustConfig adjustConfig) {
        if (this.activityHandler != null) {
            AdjustFactory.getLogger().error("Adjust already initialized", new Object[0]);
            return;
        }
        adjustConfig.preLaunchActionsArray = this.preLaunchActionsArray;
        adjustConfig.pushToken = this.pushToken;
        adjustConfig.startEnabled = this.startEnabled;
        adjustConfig.startOffline = this.startOffline;
        this.activityHandler = AdjustFactory.getActivityHandler(adjustConfig);
        setSendingReferrersAsNotSent(adjustConfig.context);
    }

    public void trackEvent(AdjustEvent event) {
        if (checkActivityHandler()) {
            this.activityHandler.trackEvent(event);
        }
    }

    public void onResume() {
        if (checkActivityHandler()) {
            this.activityHandler.onResume();
        }
    }

    public void onPause() {
        if (checkActivityHandler()) {
            this.activityHandler.onPause();
        }
    }

    public void setEnabled(boolean enabled) {
        this.startEnabled = Boolean.valueOf(enabled);
        if (checkActivityHandler(enabled, "enabled mode", "disabled mode")) {
            this.activityHandler.setEnabled(enabled);
        }
    }

    public boolean isEnabled() {
        if (checkActivityHandler()) {
            return this.activityHandler.isEnabled();
        }
        return isInstanceEnabled();
    }

    public void appWillOpenUrl(Uri url) {
        if (checkActivityHandler()) {
            this.activityHandler.readOpenUrl(url, System.currentTimeMillis());
        }
    }

    public void sendReferrer(String rawReferrer, Context context) {
        long clickTime = System.currentTimeMillis();
        if (rawReferrer != null && rawReferrer.length() != 0) {
            saveRawReferrer(rawReferrer, clickTime, context);
            if (checkActivityHandler("referrer") && this.activityHandler.isEnabled()) {
                this.activityHandler.sendReftagReferrer();
            }
        }
    }

    public void setOfflineMode(boolean enabled) {
        if (checkActivityHandler(enabled, "offline mode", "online mode")) {
            this.activityHandler.setOfflineMode(enabled);
        } else {
            this.startOffline = enabled;
        }
    }

    public void sendFirstPackages() {
        if (checkActivityHandler()) {
            this.activityHandler.sendFirstPackages();
        }
    }

    public void addSessionCallbackParameter(final String key, final String value) {
        if (checkActivityHandler("adding session callback parameter")) {
            this.activityHandler.addSessionCallbackParameter(key, value);
            return;
        }
        if (this.preLaunchActionsArray == null) {
            this.preLaunchActionsArray = new ArrayList();
        }
        this.preLaunchActionsArray.add(new IRunActivityHandler() {
            public void run(ActivityHandler activityHandler) {
                activityHandler.addSessionCallbackParameterI(key, value);
            }
        });
    }

    public void addSessionPartnerParameter(final String key, final String value) {
        if (checkActivityHandler("adding session partner parameter")) {
            this.activityHandler.addSessionPartnerParameter(key, value);
            return;
        }
        if (this.preLaunchActionsArray == null) {
            this.preLaunchActionsArray = new ArrayList();
        }
        this.preLaunchActionsArray.add(new IRunActivityHandler() {
            public void run(ActivityHandler activityHandler) {
                activityHandler.addSessionPartnerParameterI(key, value);
            }
        });
    }

    public void removeSessionCallbackParameter(final String key) {
        if (checkActivityHandler("removing session callback parameter")) {
            this.activityHandler.removeSessionCallbackParameter(key);
            return;
        }
        if (this.preLaunchActionsArray == null) {
            this.preLaunchActionsArray = new ArrayList();
        }
        this.preLaunchActionsArray.add(new IRunActivityHandler() {
            public void run(ActivityHandler activityHandler) {
                activityHandler.removeSessionCallbackParameterI(key);
            }
        });
    }

    public void removeSessionPartnerParameter(final String key) {
        if (checkActivityHandler("removing session partner parameter")) {
            this.activityHandler.removeSessionPartnerParameter(key);
            return;
        }
        if (this.preLaunchActionsArray == null) {
            this.preLaunchActionsArray = new ArrayList();
        }
        this.preLaunchActionsArray.add(new IRunActivityHandler() {
            public void run(ActivityHandler activityHandler) {
                activityHandler.removeSessionPartnerParameterI(key);
            }
        });
    }

    public void resetSessionCallbackParameters() {
        if (checkActivityHandler("resetting session callback parameters")) {
            this.activityHandler.resetSessionCallbackParameters();
            return;
        }
        if (this.preLaunchActionsArray == null) {
            this.preLaunchActionsArray = new ArrayList();
        }
        this.preLaunchActionsArray.add(new C00745());
    }

    public void resetSessionPartnerParameters() {
        if (checkActivityHandler("resetting session partner parameters")) {
            this.activityHandler.resetSessionPartnerParameters();
            return;
        }
        if (this.preLaunchActionsArray == null) {
            this.preLaunchActionsArray = new ArrayList();
        }
        this.preLaunchActionsArray.add(new C00756());
    }

    public void teardown(boolean deleteState) {
        if (checkActivityHandler()) {
            this.activityHandler.teardown(deleteState);
            this.activityHandler = null;
        }
    }

    public void setPushToken(String token) {
        if (checkActivityHandler("push token")) {
            this.activityHandler.setPushToken(token, false);
        } else {
            this.pushToken = token;
        }
    }

    public void setPushToken(String token, Context context) {
        savePushToken(token, context);
        if (checkActivityHandler("push token") && this.activityHandler.isEnabled()) {
            this.activityHandler.setPushToken(token, true);
        }
    }

    public String getAdid() {
        if (checkActivityHandler()) {
            return this.activityHandler.getAdid();
        }
        return null;
    }

    public AdjustAttribution getAttribution() {
        if (checkActivityHandler()) {
            return this.activityHandler.getAttribution();
        }
        return null;
    }

    private boolean checkActivityHandler() {
        return checkActivityHandler(null);
    }

    private boolean checkActivityHandler(boolean status, String trueMessage, String falseMessage) {
        if (status) {
            return checkActivityHandler(trueMessage);
        }
        return checkActivityHandler(falseMessage);
    }

    private boolean checkActivityHandler(String savedForLaunchWarningSuffixMessage) {
        if (this.activityHandler != null) {
            return true;
        }
        if (savedForLaunchWarningSuffixMessage != null) {
            AdjustFactory.getLogger().warn("Adjust not initialized, but %s saved for launch", savedForLaunchWarningSuffixMessage);
            return false;
        }
        AdjustFactory.getLogger().error("Adjust not initialized correctly", new Object[0]);
        return false;
    }

    private void saveRawReferrer(String rawReferrer, long clickTime, Context context) {
        final Context context2 = context;
        final String str = rawReferrer;
        final long j = clickTime;
        Util.runInBackground(new Runnable() {
            public void run() {
                new SharedPreferencesManager(context2).saveRawReferrer(str, j);
            }
        });
    }

    private void savePushToken(final String pushToken, final Context context) {
        Util.runInBackground(new Runnable() {
            public void run() {
                new SharedPreferencesManager(context).savePushToken(pushToken);
            }
        });
    }

    private void setSendingReferrersAsNotSent(final Context context) {
        Util.runInBackground(new Runnable() {
            public void run() {
                new SharedPreferencesManager(context).setSendingReferrersAsNotSent();
            }
        });
    }

    private boolean isInstanceEnabled() {
        return this.startEnabled == null || this.startEnabled.booleanValue();
    }
}
