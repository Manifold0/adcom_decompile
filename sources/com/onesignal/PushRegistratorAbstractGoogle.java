package com.onesignal;

import android.content.Context;
import com.onesignal.OneSignal.LOG_LEVEL;
import com.onesignal.PushRegistrator.RegisteredHandler;
import java.io.IOException;

abstract class PushRegistratorAbstractGoogle implements PushRegistrator {
    private static int REGISTRATION_RETRY_BACKOFF_MS = 10000;
    private static int REGISTRATION_RETRY_COUNT = 5;
    private boolean firedCallback;
    private Thread registerThread;
    private RegisteredHandler registeredHandler;

    abstract String getProviderName();

    abstract String getToken(String str) throws Throwable;

    PushRegistratorAbstractGoogle() {
    }

    public void registerForPush(Context context, String senderId, RegisteredHandler callback) {
        this.registeredHandler = callback;
        if (isValidProjectNumber(senderId, callback)) {
            internalRegisterForPush(senderId);
        }
    }

    private void internalRegisterForPush(String senderId) {
        try {
            if (GooglePlayServicesUpgradePrompt.isGMSInstalledAndEnabled()) {
                registerInBackground(senderId);
                return;
            }
            GooglePlayServicesUpgradePrompt.ShowUpdateGPSDialog();
            OneSignal.Log(LOG_LEVEL.ERROR, "'Google Play services' app not installed or disabled on the device.");
            this.registeredHandler.complete(null, -7);
        } catch (Throwable t) {
            OneSignal.Log(LOG_LEVEL.ERROR, "Could not register with " + getProviderName() + " due to an issue with your AndroidManifest.xml or with 'Google Play services'.", t);
            this.registeredHandler.complete(null, -8);
        }
    }

    private synchronized void registerInBackground(final String senderId) {
        if (this.registerThread == null || !this.registerThread.isAlive()) {
            this.registerThread = new Thread(new Runnable() {
                public void run() {
                    int currentRetry = 0;
                    while (currentRetry < PushRegistratorAbstractGoogle.REGISTRATION_RETRY_COUNT && !PushRegistratorAbstractGoogle.this.attemptRegistration(senderId, currentRetry)) {
                        OSUtils.sleep(PushRegistratorAbstractGoogle.REGISTRATION_RETRY_BACKOFF_MS * (currentRetry + 1));
                        currentRetry++;
                    }
                }
            });
            this.registerThread.start();
        }
    }

    private boolean attemptRegistration(String senderId, int currentRetry) {
        try {
            String registrationId = getToken(senderId);
            OneSignal.Log(LOG_LEVEL.INFO, "Device registered, push token = " + registrationId);
            this.registeredHandler.complete(registrationId, 1);
            return true;
        } catch (IOException e) {
            if ("SERVICE_NOT_AVAILABLE".equals(e.getMessage())) {
                if (currentRetry >= REGISTRATION_RETRY_COUNT - 1) {
                    OneSignal.Log(LOG_LEVEL.ERROR, "Retry count of " + REGISTRATION_RETRY_COUNT + " exceed! Could not get a " + getProviderName() + " Token.", e);
                } else {
                    OneSignal.Log(LOG_LEVEL.INFO, "'Google Play services' returned SERVICE_NOT_AVAILABLE error. Current retry count: " + currentRetry, e);
                    if (currentRetry == 2) {
                        this.registeredHandler.complete(null, -9);
                        this.firedCallback = true;
                        return true;
                    }
                }
                return false;
            }
            OneSignal.Log(LOG_LEVEL.ERROR, "Error Getting " + getProviderName() + " Token", e);
            if (this.firedCallback) {
                return true;
            }
            this.registeredHandler.complete(null, -11);
            return true;
        } catch (Throwable t) {
            OneSignal.Log(LOG_LEVEL.ERROR, "Unknown error getting " + getProviderName() + " Token", t);
            this.registeredHandler.complete(null, -12);
            return true;
        }
    }

    private boolean isValidProjectNumber(String senderId, RegisteredHandler callback) {
        boolean isProjectNumberValidFormat;
        try {
            Float.parseFloat(senderId);
            isProjectNumberValidFormat = true;
        } catch (Throwable th) {
            isProjectNumberValidFormat = false;
        }
        if (isProjectNumberValidFormat) {
            return true;
        }
        OneSignal.Log(LOG_LEVEL.ERROR, "Missing Google Project number!\nPlease enter a Google Project number / Sender ID on under App Settings > Android > Configuration on the OneSignal dashboard.");
        callback.complete(null, -6);
        return false;
    }
}
