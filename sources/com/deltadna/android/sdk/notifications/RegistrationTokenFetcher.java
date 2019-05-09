package com.deltadna.android.sdk.notifications;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.deltadna.android.sdk.DDNA;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import java.io.IOException;
import java.util.Locale;

final class RegistrationTokenFetcher {
    private static final Handler HANDLER = new Handler(Looper.getMainLooper());
    private static final String TAG = ("deltaDNA " + RegistrationTokenFetcher.class.getSimpleName());

    static void fetch(final Context context) {
        Log.d(TAG, "Fetching registration token");
        try {
            try {
                final String token = FirebaseInstanceId.getInstance().getToken(context.getString(MetaData.get(context).getInt("ddna_sender_id")), FirebaseMessaging.INSTANCE_ID_SCOPE);
                Log.d(TAG, "Registration token has been retrieved: " + token);
                HANDLER.post(new Runnable() {
                    public void run() {
                        RegistrationTokenFetcher.notifySuccess(context, token);
                    }
                });
            } catch (final IOException e) {
                Log.w(TAG, "Failed to fetch registration token", e);
                HANDLER.post(new Runnable() {
                    public void run() {
                        RegistrationTokenFetcher.notifyFailure(context, e);
                    }
                });
            }
        } catch (NotFoundException e2) {
            Log.w(TAG, String.format(Locale.US, "Failed to find %s, has it been defined in the manifest?", new Object[]{"ddna_sender_id"}), e2);
        }
    }

    private static void notifySuccess(Context context, String token) {
        if (UnityForwarder.isPresent()) {
            UnityForwarder.getInstance().forward("DeltaDNA.AndroidNotifications", "DidRegisterForPushNotifications", token);
            return;
        }
        DDNA.instance().setRegistrationId(token);
        context.sendBroadcast(Utils.wrapWithReceiver(context, new Intent("com.deltadna.android.sdk.notifications.REGISTERED").setPackage(context.getPackageName()).putExtra("registration_token", token)));
    }

    private static void notifyFailure(Context context, Throwable t) {
        if (UnityForwarder.isPresent()) {
            UnityForwarder.getInstance().forward("DeltaDNA.AndroidNotifications", "DidFailToRegisterForPushNotifications", t.getMessage());
        } else {
            context.sendBroadcast(Utils.wrapWithReceiver(context, new Intent("com.deltadna.android.sdk.notifications.REGISTRATION_FAILED").setPackage(context.getPackageName()).putExtra("registration_failure_reason", t)));
        }
    }

    private RegistrationTokenFetcher() {
    }
}
