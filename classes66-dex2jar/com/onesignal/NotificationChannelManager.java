// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import org.json.JSONArray;
import java.util.Iterator;
import java.util.HashSet;
import android.os.Build$VERSION;
import android.support.annotation.RequiresApi;
import org.json.JSONException;
import android.net.Uri;
import android.media.AudioAttributes;
import java.math.BigInteger;
import android.app.NotificationChannelGroup;
import android.app.NotificationChannel;
import org.json.JSONObject;
import android.app.NotificationManager;
import android.content.Context;

class NotificationChannelManager
{
    private static final String DEFAULT_CHANNEL_ID = "fcm_fallback_notification_channel";
    private static final String RESTORE_CHANNEL_ID = "restored_OS_notifications";
    
    @RequiresApi(api = 26)
    private static String createChannel(final Context context, final NotificationManager notificationManager, final JSONObject jsonObject) throws JSONException {
        final Object opt = jsonObject.opt("chnl");
        JSONObject jsonObject2;
        if (opt instanceof String) {
            jsonObject2 = new JSONObject((String)opt);
        }
        else {
            jsonObject2 = (JSONObject)opt;
        }
        String optString;
        if ((optString = jsonObject2.optString("id", "fcm_fallback_notification_channel")).equals("miscellaneous")) {
            optString = "fcm_fallback_notification_channel";
        }
        JSONObject optJSONObject;
        final JSONObject jsonObject3 = optJSONObject = jsonObject2;
        if (jsonObject2.has("langs")) {
            final JSONObject jsonObject4 = jsonObject2.getJSONObject("langs");
            final String correctedLanguage = OSUtils.getCorrectedLanguage();
            optJSONObject = jsonObject3;
            if (jsonObject4.has(correctedLanguage)) {
                optJSONObject = jsonObject4.optJSONObject(correctedLanguage);
            }
        }
        final NotificationChannel notificationChannel = new NotificationChannel(optString, (CharSequence)optJSONObject.optString("nm", "Miscellaneous"), priorityToImportance(jsonObject.optInt("pri", 6)));
        notificationChannel.setDescription(optJSONObject.optString("dscr", (String)null));
        if (jsonObject2.has("grp_id")) {
            final String optString2 = jsonObject2.optString("grp_id");
            notificationManager.createNotificationChannelGroup(new NotificationChannelGroup(optString2, (CharSequence)optJSONObject.optString("grp_nm")));
            notificationChannel.setGroup(optString2);
        }
        if (jsonObject.has("ledc")) {
            notificationChannel.setLightColor(new BigInteger(jsonObject.optString("ledc"), 16).intValue());
        }
        notificationChannel.enableLights(jsonObject.optInt("led", 1) == 1);
        if (jsonObject.has("vib_pt")) {
            final long[] vibrationPattern = OSUtils.parseVibrationPattern(jsonObject);
            if (vibrationPattern != null) {
                notificationChannel.setVibrationPattern(vibrationPattern);
            }
        }
        notificationChannel.enableVibration(jsonObject.optInt("vib", 1) == 1);
        if (jsonObject.has("sound")) {
            final String optString3 = jsonObject.optString("sound", (String)null);
            final Uri soundUri = OSUtils.getSoundUri(context, optString3);
            if (soundUri != null) {
                notificationChannel.setSound(soundUri, (AudioAttributes)null);
            }
            else if ("null".equals(optString3) || "nil".equals(optString3)) {
                notificationChannel.setSound((Uri)null, (AudioAttributes)null);
            }
        }
        notificationChannel.setLockscreenVisibility(jsonObject.optInt("vis", 0));
        notificationChannel.setShowBadge(jsonObject.optInt("bdg", 1) == 1);
        notificationChannel.setBypassDnd(jsonObject.optInt("bdnd", 0) == 1);
        notificationManager.createNotificationChannel(notificationChannel);
        return optString;
    }
    
    @RequiresApi(api = 26)
    private static String createDefaultChannel(final NotificationManager notificationManager) {
        final NotificationChannel notificationChannel = new NotificationChannel("fcm_fallback_notification_channel", (CharSequence)"Miscellaneous", 3);
        notificationChannel.enableLights(true);
        notificationChannel.enableVibration(true);
        notificationManager.createNotificationChannel(notificationChannel);
        return "fcm_fallback_notification_channel";
    }
    
    static String createNotificationChannel(final NotificationGenerationJob notificationGenerationJob) {
        String optString;
        if (Build$VERSION.SDK_INT < 26) {
            optString = "fcm_fallback_notification_channel";
        }
        else {
            final Context context = notificationGenerationJob.context;
            final JSONObject jsonPayload = notificationGenerationJob.jsonPayload;
            final NotificationManager notificationManager = (NotificationManager)context.getSystemService("notification");
            if (notificationGenerationJob.restoring) {
                return createRestoreChannel(notificationManager);
            }
            if (!jsonPayload.has("oth_chnl") || notificationManager.getNotificationChannel(optString = jsonPayload.optString("oth_chnl")) == null) {
                if (!jsonPayload.has("chnl")) {
                    return createDefaultChannel(notificationManager);
                }
                try {
                    return createChannel(context, notificationManager, jsonPayload);
                }
                catch (JSONException ex) {
                    OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Could not create notification channel due to JSON payload error!", (Throwable)ex);
                    return "fcm_fallback_notification_channel";
                }
            }
        }
        return optString;
    }
    
    @RequiresApi(api = 26)
    private static String createRestoreChannel(final NotificationManager notificationManager) {
        notificationManager.createNotificationChannel(new NotificationChannel("restored_OS_notifications", (CharSequence)"Restored", 2));
        return "restored_OS_notifications";
    }
    
    private static int priorityToImportance(final int n) {
        if (n > 9) {
            return 5;
        }
        if (n > 7) {
            return 4;
        }
        if (n > 5) {
            return 3;
        }
        if (n > 3) {
            return 2;
        }
        if (n > 1) {
            return 1;
        }
        return 0;
    }
    
    static void processChannelList(final Context context, JSONObject optJSONArray) {
        if (Build$VERSION.SDK_INT >= 26 && optJSONArray.has("chnl_lst")) {
            final NotificationManager notificationManager = (NotificationManager)context.getSystemService("notification");
            final HashSet<String> set = new HashSet<String>();
            optJSONArray = (JSONObject)optJSONArray.optJSONArray("chnl_lst");
            final int length = ((JSONArray)optJSONArray).length();
            int i = 0;
        Label_0076_Outer:
            while (i < length) {
                while (true) {
                    try {
                        set.add(createChannel(context, notificationManager, ((JSONArray)optJSONArray).getJSONObject(i)));
                        ++i;
                        continue Label_0076_Outer;
                    }
                    catch (JSONException ex) {
                        OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Could not create notification channel due to JSON payload error!", (Throwable)ex);
                        continue;
                    }
                    break;
                }
                break;
            }
            final Iterator iterator = notificationManager.getNotificationChannels().iterator();
            while (iterator.hasNext()) {
                final String id = iterator.next().getId();
                if (id.startsWith("OS_") && !set.contains(id)) {
                    notificationManager.deleteNotificationChannel(id);
                }
            }
        }
    }
}
