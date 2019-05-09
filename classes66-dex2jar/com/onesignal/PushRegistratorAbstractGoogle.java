// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import android.content.Context;
import java.io.IOException;

abstract class PushRegistratorAbstractGoogle implements PushRegistrator
{
    private static int REGISTRATION_RETRY_BACKOFF_MS;
    private static int REGISTRATION_RETRY_COUNT;
    private boolean firedCallback;
    private Thread registerThread;
    private RegisteredHandler registeredHandler;
    
    static {
        PushRegistratorAbstractGoogle.REGISTRATION_RETRY_COUNT = 5;
        PushRegistratorAbstractGoogle.REGISTRATION_RETRY_BACKOFF_MS = 10000;
    }
    
    private boolean attemptRegistration(String token, final int n) {
        try {
            token = this.getToken(token);
            OneSignal.Log(OneSignal.LOG_LEVEL.INFO, "Device registered, push token = " + token);
            this.registeredHandler.complete(token, 1);
            return true;
        }
        catch (IOException ex) {
            if ("SERVICE_NOT_AVAILABLE".equals(ex.getMessage())) {
                if (n >= PushRegistratorAbstractGoogle.REGISTRATION_RETRY_COUNT - 1) {
                    OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Retry count of " + PushRegistratorAbstractGoogle.REGISTRATION_RETRY_COUNT + " exceed! Could not get a " + this.getProviderName() + " Token.", ex);
                }
                else {
                    OneSignal.Log(OneSignal.LOG_LEVEL.INFO, "'Google Play services' returned SERVICE_NOT_AVAILABLE error. Current retry count: " + n, ex);
                    if (n == 2) {
                        this.registeredHandler.complete(null, -9);
                        return this.firedCallback = true;
                    }
                }
                return false;
            }
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error Getting " + this.getProviderName() + " Token", ex);
            if (!this.firedCallback) {
                this.registeredHandler.complete(null, -11);
                return true;
            }
            return true;
        }
        catch (Throwable t) {
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Unknown error getting " + this.getProviderName() + " Token", t);
            this.registeredHandler.complete(null, -12);
            return true;
        }
    }
    
    private void internalRegisterForPush(final String s) {
        try {
            if (GooglePlayServicesUpgradePrompt.isGMSInstalledAndEnabled()) {
                this.registerInBackground(s);
                return;
            }
            GooglePlayServicesUpgradePrompt.ShowUpdateGPSDialog();
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "'Google Play services' app not installed or disabled on the device.");
            this.registeredHandler.complete(null, -7);
        }
        catch (Throwable t) {
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Could not register with " + this.getProviderName() + " due to an issue with your AndroidManifest.xml or with 'Google Play services'.", t);
            this.registeredHandler.complete(null, -8);
        }
    }
    
    private boolean isValidProjectNumber(final String s, final RegisteredHandler registeredHandler) {
        while (true) {
            try {
                Float.parseFloat(s);
                final int n = 1;
                if (n == 0) {
                    OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Missing Google Project number!\nPlease enter a Google Project number / Sender ID on under App Settings > Android > Configuration on the OneSignal dashboard.");
                    registeredHandler.complete(null, -6);
                    return false;
                }
            }
            catch (Throwable t) {
                final int n = 0;
                continue;
            }
            break;
        }
        return true;
    }
    
    private void registerInBackground(final String s) {
        synchronized (this) {
            if (this.registerThread == null || !this.registerThread.isAlive()) {
                (this.registerThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int n = 0; n < PushRegistratorAbstractGoogle.REGISTRATION_RETRY_COUNT && !PushRegistratorAbstractGoogle.this.attemptRegistration(s, n); ++n) {
                            OSUtils.sleep(PushRegistratorAbstractGoogle.REGISTRATION_RETRY_BACKOFF_MS * (n + 1));
                        }
                    }
                })).start();
            }
        }
    }
    
    abstract String getProviderName();
    
    abstract String getToken(final String p0) throws Throwable;
    
    @Override
    public void registerForPush(final Context context, final String s, final RegisteredHandler registeredHandler) {
        this.registeredHandler = registeredHandler;
        if (this.isValidProjectNumber(s, registeredHandler)) {
            this.internalRegisterForPush(s);
        }
    }
}
