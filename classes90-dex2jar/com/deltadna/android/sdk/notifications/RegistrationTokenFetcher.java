// 
// Decompiled by Procyon v0.5.34
// 

package com.deltadna.android.sdk.notifications;

import com.deltadna.android.sdk.DDNA;
import java.io.Serializable;
import android.content.Intent;
import java.io.IOException;
import android.content.res.Resources$NotFoundException;
import java.util.Locale;
import com.google.firebase.iid.FirebaseInstanceId;
import android.util.Log;
import android.content.Context;
import android.os.Looper;
import android.os.Handler;

final class RegistrationTokenFetcher
{
    private static final Handler HANDLER;
    private static final String TAG;
    
    static {
        TAG = "deltaDNA " + RegistrationTokenFetcher.class.getSimpleName();
        HANDLER = new Handler(Looper.getMainLooper());
    }
    
    private RegistrationTokenFetcher() {
    }
    
    static void fetch(final Context ex) {
        Log.d(RegistrationTokenFetcher.TAG, "Fetching registration token");
        String string;
        try {
            string = ((Context)ex).getString(MetaData.get((Context)ex).getInt("ddna_sender_id"));
            final FirebaseInstanceId firebaseInstanceId = FirebaseInstanceId.getInstance();
            final String s = string;
            final String s2 = "FCM";
            final String s3 = firebaseInstanceId.getToken(s, s2);
            final String s4 = RegistrationTokenFetcher.TAG;
            final StringBuilder sb = new StringBuilder();
            final String s5 = "Registration token has been retrieved: ";
            final StringBuilder sb2 = sb.append(s5);
            final String s6 = s3;
            final StringBuilder sb3 = sb2.append(s6);
            final String s7 = sb3.toString();
            Log.d(s4, s7);
            final Handler handler = RegistrationTokenFetcher.HANDLER;
            final Resources$NotFoundException ex2 = ex;
            final String s8 = s3;
            final Runnable runnable = new Runnable() {
                final /* synthetic */ Context val$context;
                final /* synthetic */ String val$token;
                
                @Override
                public void run() {
                    notifySuccess(this.val$context, this.val$token);
                }
            };
            handler.post((Runnable)runnable);
            return;
        }
        catch (Resources$NotFoundException ex) {
            Log.w(RegistrationTokenFetcher.TAG, String.format(Locale.US, "Failed to find %s, has it been defined in the manifest?", "ddna_sender_id"), (Throwable)ex);
            return;
        }
        try {
            final FirebaseInstanceId firebaseInstanceId = FirebaseInstanceId.getInstance();
            final String s = string;
            final String s2 = "FCM";
            final String s3 = firebaseInstanceId.getToken(s, s2);
            final String s4 = RegistrationTokenFetcher.TAG;
            final StringBuilder sb = new StringBuilder();
            final String s5 = "Registration token has been retrieved: ";
            final StringBuilder sb2 = sb.append(s5);
            final String s6 = s3;
            final StringBuilder sb3 = sb2.append(s6);
            final String s7 = sb3.toString();
            Log.d(s4, s7);
            final Handler handler = RegistrationTokenFetcher.HANDLER;
            final Resources$NotFoundException ex2 = ex;
            final String s8 = s3;
            final Runnable runnable = new Runnable() {
                final /* synthetic */ Context val$context;
                final /* synthetic */ String val$token;
                
                @Override
                public void run() {
                    notifySuccess(ex2, s8);
                }
            };
            handler.post((Runnable)runnable);
        }
        catch (IOException ex3) {
            Log.w(RegistrationTokenFetcher.TAG, "Failed to fetch registration token", (Throwable)ex3);
            RegistrationTokenFetcher.HANDLER.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    notifyFailure(ex, ex3);
                }
            });
        }
    }
    
    private static void notifyFailure(final Context context, final Throwable t) {
        if (UnityForwarder.isPresent()) {
            UnityForwarder.getInstance().forward("DeltaDNA.AndroidNotifications", "DidFailToRegisterForPushNotifications", t.getMessage());
            return;
        }
        context.sendBroadcast(Utils.wrapWithReceiver(context, new Intent("com.deltadna.android.sdk.notifications.REGISTRATION_FAILED").setPackage(context.getPackageName()).putExtra("registration_failure_reason", (Serializable)t)));
    }
    
    private static void notifySuccess(final Context context, final String registrationId) {
        if (UnityForwarder.isPresent()) {
            UnityForwarder.getInstance().forward("DeltaDNA.AndroidNotifications", "DidRegisterForPushNotifications", registrationId);
            return;
        }
        DDNA.instance().setRegistrationId(registrationId);
        context.sendBroadcast(Utils.wrapWithReceiver(context, new Intent("com.deltadna.android.sdk.notifications.REGISTERED").setPackage(context.getPackageName()).putExtra("registration_token", registrationId)));
    }
}
