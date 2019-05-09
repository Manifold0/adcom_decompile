// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import com.amazon.device.messaging.ADM;
import android.content.Context;

public class PushRegistratorADM implements PushRegistrator
{
    private static boolean callbackSuccessful;
    private static RegisteredHandler registeredCallback;
    
    static {
        PushRegistratorADM.callbackSuccessful = false;
    }
    
    public static void fireCallback(final String s) {
        if (PushRegistratorADM.registeredCallback == null) {
            return;
        }
        PushRegistratorADM.callbackSuccessful = true;
        PushRegistratorADM.registeredCallback.complete(s, 1);
    }
    
    @Override
    public void registerForPush(final Context context, final String s, final RegisteredHandler registeredCallback) {
        PushRegistratorADM.registeredCallback = registeredCallback;
        new Thread(new Runnable() {
            @Override
            public void run() {
                final ADM adm = new ADM(context);
                final String registrationId = adm.getRegistrationId();
                Label_0050: {
                    if (registrationId != null) {
                        break Label_0050;
                    }
                    adm.startRegister();
                    while (true) {
                        try {
                            while (true) {
                                Thread.sleep(30000L);
                                if (!PushRegistratorADM.callbackSuccessful) {
                                    OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "com.onesignal.ADMMessageHandler timed out, please check that your have the receiver, service, and your package name matches(NOTE: Case Sensitive) per the OneSignal instructions.");
                                    PushRegistratorADM.fireCallback(null);
                                }
                                return;
                                OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, "ADM Already registered with ID:" + registrationId);
                                registeredCallback.complete(registrationId, 1);
                                continue;
                            }
                        }
                        catch (InterruptedException ex) {
                            continue;
                        }
                        break;
                    }
                }
            }
        }).start();
    }
}
