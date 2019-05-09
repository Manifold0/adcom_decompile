// 
// Decompiled by Procyon v0.5.34
// 

package com.adjust.sdk;

import android.net.Uri;
import java.util.ArrayList;
import android.content.Context;
import java.util.List;

public class AdjustInstance
{
    private IActivityHandler activityHandler;
    private List<IRunActivityHandler> preLaunchActionsArray;
    private String pushToken;
    private Boolean startEnabled;
    private boolean startOffline;
    
    public AdjustInstance() {
        this.startEnabled = null;
        this.startOffline = false;
    }
    
    private boolean checkActivityHandler() {
        return this.checkActivityHandler(null);
    }
    
    private boolean checkActivityHandler(final String s) {
        if (this.activityHandler != null) {
            return true;
        }
        if (s != null) {
            AdjustFactory.getLogger().warn("Adjust not initialized, but %s saved for launch", s);
            return false;
        }
        AdjustFactory.getLogger().error("Adjust not initialized correctly", new Object[0]);
        return false;
    }
    
    private boolean checkActivityHandler(final boolean b, final String s, final String s2) {
        if (b) {
            return this.checkActivityHandler(s);
        }
        return this.checkActivityHandler(s2);
    }
    
    private boolean isInstanceEnabled() {
        return this.startEnabled == null || this.startEnabled;
    }
    
    private void savePushToken(final String s, final Context context) {
        Util.runInBackground(new Runnable() {
            @Override
            public void run() {
                new SharedPreferencesManager(context).savePushToken(s);
            }
        });
    }
    
    private void saveRawReferrer(final String s, final long n, final Context context) {
        Util.runInBackground(new Runnable() {
            @Override
            public void run() {
                new SharedPreferencesManager(context).saveRawReferrer(s, n);
            }
        });
    }
    
    private void setSendingReferrersAsNotSent(final Context context) {
        Util.runInBackground(new Runnable() {
            @Override
            public void run() {
                new SharedPreferencesManager(context).setSendingReferrersAsNotSent();
            }
        });
    }
    
    public void addSessionCallbackParameter(final String s, final String s2) {
        if (this.checkActivityHandler("adding session callback parameter")) {
            this.activityHandler.addSessionCallbackParameter(s, s2);
            return;
        }
        if (this.preLaunchActionsArray == null) {
            this.preLaunchActionsArray = new ArrayList<IRunActivityHandler>();
        }
        this.preLaunchActionsArray.add(new IRunActivityHandler() {
            @Override
            public void run(final ActivityHandler activityHandler) {
                activityHandler.addSessionCallbackParameterI(s, s2);
            }
        });
    }
    
    public void addSessionPartnerParameter(final String s, final String s2) {
        if (this.checkActivityHandler("adding session partner parameter")) {
            this.activityHandler.addSessionPartnerParameter(s, s2);
            return;
        }
        if (this.preLaunchActionsArray == null) {
            this.preLaunchActionsArray = new ArrayList<IRunActivityHandler>();
        }
        this.preLaunchActionsArray.add(new IRunActivityHandler() {
            @Override
            public void run(final ActivityHandler activityHandler) {
                activityHandler.addSessionPartnerParameterI(s, s2);
            }
        });
    }
    
    public void appWillOpenUrl(final Uri uri) {
        if (!this.checkActivityHandler()) {
            return;
        }
        this.activityHandler.readOpenUrl(uri, System.currentTimeMillis());
    }
    
    public String getAdid() {
        if (!this.checkActivityHandler()) {
            return null;
        }
        return this.activityHandler.getAdid();
    }
    
    public AdjustAttribution getAttribution() {
        if (!this.checkActivityHandler()) {
            return null;
        }
        return this.activityHandler.getAttribution();
    }
    
    public boolean isEnabled() {
        if (!this.checkActivityHandler()) {
            return this.isInstanceEnabled();
        }
        return this.activityHandler.isEnabled();
    }
    
    public void onCreate(final AdjustConfig adjustConfig) {
        if (this.activityHandler != null) {
            AdjustFactory.getLogger().error("Adjust already initialized", new Object[0]);
            return;
        }
        adjustConfig.preLaunchActionsArray = this.preLaunchActionsArray;
        adjustConfig.pushToken = this.pushToken;
        adjustConfig.startEnabled = this.startEnabled;
        adjustConfig.startOffline = this.startOffline;
        this.activityHandler = AdjustFactory.getActivityHandler(adjustConfig);
        this.setSendingReferrersAsNotSent(adjustConfig.context);
    }
    
    public void onPause() {
        if (!this.checkActivityHandler()) {
            return;
        }
        this.activityHandler.onPause();
    }
    
    public void onResume() {
        if (!this.checkActivityHandler()) {
            return;
        }
        this.activityHandler.onResume();
    }
    
    public void removeSessionCallbackParameter(final String s) {
        if (this.checkActivityHandler("removing session callback parameter")) {
            this.activityHandler.removeSessionCallbackParameter(s);
            return;
        }
        if (this.preLaunchActionsArray == null) {
            this.preLaunchActionsArray = new ArrayList<IRunActivityHandler>();
        }
        this.preLaunchActionsArray.add(new IRunActivityHandler() {
            @Override
            public void run(final ActivityHandler activityHandler) {
                activityHandler.removeSessionCallbackParameterI(s);
            }
        });
    }
    
    public void removeSessionPartnerParameter(final String s) {
        if (this.checkActivityHandler("removing session partner parameter")) {
            this.activityHandler.removeSessionPartnerParameter(s);
            return;
        }
        if (this.preLaunchActionsArray == null) {
            this.preLaunchActionsArray = new ArrayList<IRunActivityHandler>();
        }
        this.preLaunchActionsArray.add(new IRunActivityHandler() {
            @Override
            public void run(final ActivityHandler activityHandler) {
                activityHandler.removeSessionPartnerParameterI(s);
            }
        });
    }
    
    public void resetSessionCallbackParameters() {
        if (this.checkActivityHandler("resetting session callback parameters")) {
            this.activityHandler.resetSessionCallbackParameters();
            return;
        }
        if (this.preLaunchActionsArray == null) {
            this.preLaunchActionsArray = new ArrayList<IRunActivityHandler>();
        }
        this.preLaunchActionsArray.add(new IRunActivityHandler() {
            @Override
            public void run(final ActivityHandler activityHandler) {
                activityHandler.resetSessionCallbackParametersI();
            }
        });
    }
    
    public void resetSessionPartnerParameters() {
        if (this.checkActivityHandler("resetting session partner parameters")) {
            this.activityHandler.resetSessionPartnerParameters();
            return;
        }
        if (this.preLaunchActionsArray == null) {
            this.preLaunchActionsArray = new ArrayList<IRunActivityHandler>();
        }
        this.preLaunchActionsArray.add(new IRunActivityHandler() {
            @Override
            public void run(final ActivityHandler activityHandler) {
                activityHandler.resetSessionPartnerParametersI();
            }
        });
    }
    
    public void sendFirstPackages() {
        if (!this.checkActivityHandler()) {
            return;
        }
        this.activityHandler.sendFirstPackages();
    }
    
    public void sendReferrer(final String s, final Context context) {
        final long currentTimeMillis = System.currentTimeMillis();
        if (s != null && s.length() != 0) {
            this.saveRawReferrer(s, currentTimeMillis, context);
            if (this.checkActivityHandler("referrer") && this.activityHandler.isEnabled()) {
                this.activityHandler.sendReftagReferrer();
            }
        }
    }
    
    public void setEnabled(final boolean enabled) {
        this.startEnabled = enabled;
        if (this.checkActivityHandler(enabled, "enabled mode", "disabled mode")) {
            this.activityHandler.setEnabled(enabled);
        }
    }
    
    public void setOfflineMode(final boolean b) {
        if (!this.checkActivityHandler(b, "offline mode", "online mode")) {
            this.startOffline = b;
            return;
        }
        this.activityHandler.setOfflineMode(b);
    }
    
    public void setPushToken(final String pushToken) {
        if (!this.checkActivityHandler("push token")) {
            this.pushToken = pushToken;
            return;
        }
        this.activityHandler.setPushToken(pushToken, false);
    }
    
    public void setPushToken(final String s, final Context context) {
        this.savePushToken(s, context);
        if (this.checkActivityHandler("push token") && this.activityHandler.isEnabled()) {
            this.activityHandler.setPushToken(s, true);
        }
    }
    
    public void teardown(final boolean b) {
        if (!this.checkActivityHandler()) {
            return;
        }
        this.activityHandler.teardown(b);
        this.activityHandler = null;
    }
    
    public void trackEvent(final AdjustEvent adjustEvent) {
        if (!this.checkActivityHandler()) {
            return;
        }
        this.activityHandler.trackEvent(adjustEvent);
    }
}
