// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import android.app.AlertDialog;
import android.content.DialogInterface$OnCancelListener;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import java.util.ArrayList;
import android.app.AlertDialog$Builder;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.R$drawable;
import android.graphics.BitmapFactory;
import java.net.URL;
import java.math.BigInteger;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.net.Uri;
import java.lang.reflect.Field;
import android.app.Notification;
import android.app.PendingIntent;
import org.json.JSONException;
import org.json.JSONArray;
import android.graphics.Bitmap;
import android.support.v4.app.NotificationCompat$Style;
import android.widget.RemoteViews;
import android.os.Build$VERSION;
import android.support.v4.app.NotificationCompat$Builder;
import android.content.Intent;
import java.util.List;
import org.json.JSONObject;
import android.content.Context;
import android.content.res.Resources;

class GenerateNotification
{
    private static Resources contextResources;
    private static Context currentContext;
    private static Class<?> notificationOpenedClass;
    private static boolean openerIsBroadcast;
    private static String packageName;
    
    static {
        GenerateNotification.currentContext = null;
        GenerateNotification.packageName = null;
        GenerateNotification.contextResources = null;
    }
    
    private static void addAlertButtons(final Context context, final JSONObject jsonObject, final List<String> list, final List<String> list2) {
        while (true) {
            try {
                addCustomAlertButtons(context, jsonObject, list, list2);
                if (list.size() == 0 || list.size() < 3) {
                    list.add(OSUtils.getResourceString(context, "onesignal_in_app_alert_ok_button_text", "Ok"));
                    list2.add("__DEFAULT__");
                }
            }
            catch (Throwable t) {
                OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Failed to parse JSON for custom buttons for alert dialog.", t);
                continue;
            }
            break;
        }
    }
    
    private static void addBackgroundImage(final JSONObject jsonObject, final NotificationCompat$Builder notificationCompat$Builder) throws Throwable {
        if (Build$VERSION.SDK_INT >= 16) {
            Bitmap bitmap = null;
            JSONObject jsonObject2 = null;
            final String optString = jsonObject.optString("bg_img", (String)null);
            if (optString != null) {
                jsonObject2 = new JSONObject(optString);
                bitmap = getBitmap(jsonObject2.optString("img", (String)null));
            }
            Bitmap bitmapFromAssetsOrResourceName;
            if ((bitmapFromAssetsOrResourceName = bitmap) == null) {
                bitmapFromAssetsOrResourceName = getBitmapFromAssetsOrResourceName("onesignal_bgimage_default_image");
            }
            if (bitmapFromAssetsOrResourceName != null) {
                final RemoteViews content = new RemoteViews(GenerateNotification.currentContext.getPackageName(), R$layout.onesignal_bgimage_notif_layout);
                content.setTextViewText(R$id.os_bgimage_notif_title, getTitle(jsonObject));
                content.setTextViewText(R$id.os_bgimage_notif_body, (CharSequence)jsonObject.optString("alert"));
                setTextColor(content, jsonObject2, R$id.os_bgimage_notif_title, "tc", "onesignal_bgimage_notif_title_color");
                setTextColor(content, jsonObject2, R$id.os_bgimage_notif_body, "bc", "onesignal_bgimage_notif_body_color");
                Object o = null;
                if (jsonObject2 != null && jsonObject2.has("img_align")) {
                    o = jsonObject2.getString("img_align");
                }
                else {
                    final int identifier = GenerateNotification.contextResources.getIdentifier("onesignal_bgimage_notif_image_align", "string", GenerateNotification.packageName);
                    if (identifier != 0) {
                        o = GenerateNotification.contextResources.getString(identifier);
                    }
                }
                if ("right".equals(o)) {
                    content.setViewPadding(R$id.os_bgimage_notif_bgimage_align_layout, -5000, 0, 0, 0);
                    content.setImageViewBitmap(R$id.os_bgimage_notif_bgimage_right_aligned, bitmapFromAssetsOrResourceName);
                    content.setViewVisibility(R$id.os_bgimage_notif_bgimage_right_aligned, 0);
                    content.setViewVisibility(R$id.os_bgimage_notif_bgimage, 2);
                }
                else {
                    content.setImageViewBitmap(R$id.os_bgimage_notif_bgimage, bitmapFromAssetsOrResourceName);
                }
                notificationCompat$Builder.setContent(content);
                notificationCompat$Builder.setStyle((NotificationCompat$Style)null);
            }
        }
    }
    
    private static void addCustomAlertButtons(final Context context, JSONObject jsonObject, final List<String> list, final List<String> list2) throws JSONException {
        final JSONObject jsonObject2 = new JSONObject(jsonObject.optString("custom"));
        if (jsonObject2.has("a")) {
            final JSONObject jsonObject3 = jsonObject2.getJSONObject("a");
            if (jsonObject3.has("actionButtons")) {
                final JSONArray optJSONArray = jsonObject3.optJSONArray("actionButtons");
                for (int i = 0; i < optJSONArray.length(); ++i) {
                    jsonObject = optJSONArray.getJSONObject(i);
                    list.add(jsonObject.optString("text"));
                    list2.add(jsonObject.optString("id"));
                }
            }
        }
    }
    
    private static void addNotificationActionButtons(final JSONObject jsonObject, final NotificationCompat$Builder notificationCompat$Builder, final int n, final String s) {
        try {
            final JSONObject jsonObject2 = new JSONObject(jsonObject.optString("custom"));
            if (!jsonObject2.has("a")) {
                return;
            }
            final JSONObject jsonObject3 = jsonObject2.getJSONObject("a");
            if (jsonObject3.has("actionButtons")) {
                final JSONArray jsonArray = jsonObject3.getJSONArray("actionButtons");
                for (int i = 0; i < jsonArray.length(); ++i) {
                    final JSONObject optJSONObject = jsonArray.optJSONObject(i);
                    final JSONObject jsonObject4 = new JSONObject(jsonObject.toString());
                    final Intent newBaseIntent = getNewBaseIntent(n);
                    newBaseIntent.setAction("" + i);
                    newBaseIntent.putExtra("action_button", true);
                    jsonObject4.put("actionSelected", (Object)optJSONObject.optString("id"));
                    newBaseIntent.putExtra("onesignal_data", jsonObject4.toString());
                    if (s != null) {
                        newBaseIntent.putExtra("summary", s);
                    }
                    else if (jsonObject.has("grp")) {
                        newBaseIntent.putExtra("grp", jsonObject.optString("grp"));
                    }
                    final PendingIntent newActionPendingIntent = getNewActionPendingIntent(n, newBaseIntent);
                    int resourceIcon = 0;
                    if (optJSONObject.has("icon")) {
                        resourceIcon = getResourceIcon(optJSONObject.optString("icon"));
                    }
                    notificationCompat$Builder.addAction(resourceIcon, (CharSequence)optJSONObject.optString("text"), newActionPendingIntent);
                }
            }
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    private static void addXiaomiSettings(final OneSignalNotificationBuilder oneSignalNotificationBuilder, final Notification notification) {
        if (!oneSignalNotificationBuilder.hasLargeIcon) {
            return;
        }
        try {
            final Object instance = Class.forName("android.app.MiuiNotification").newInstance();
            final Field declaredField = instance.getClass().getDeclaredField("customizedIcon");
            declaredField.setAccessible(true);
            declaredField.set(instance, true);
            final Field field = notification.getClass().getField("extraNotification");
            field.setAccessible(true);
            field.set(notification, instance);
        }
        catch (Throwable t) {}
    }
    
    private static void applyNotificationExtender(final NotificationGenerationJob notificationGenerationJob, final NotificationCompat$Builder notificationCompat$Builder) {
        if (notificationGenerationJob.overrideSettings != null && notificationGenerationJob.overrideSettings.extender != null) {
            try {
                final Field declaredField = NotificationCompat$Builder.class.getDeclaredField("mNotification");
                declaredField.setAccessible(true);
                final Notification notification = (Notification)declaredField.get(notificationCompat$Builder);
                notificationGenerationJob.orgFlags = notification.flags;
                notificationGenerationJob.orgSound = notification.sound;
                notificationCompat$Builder.extend(notificationGenerationJob.overrideSettings.extender);
                final Notification notification2 = (Notification)declaredField.get(notificationCompat$Builder);
                final Field declaredField2 = NotificationCompat$Builder.class.getDeclaredField("mContentText");
                declaredField2.setAccessible(true);
                final CharSequence overriddenBodyFromExtender = (CharSequence)declaredField2.get(notificationCompat$Builder);
                final Field declaredField3 = NotificationCompat$Builder.class.getDeclaredField("mContentTitle");
                declaredField3.setAccessible(true);
                final CharSequence overriddenTitleFromExtender = (CharSequence)declaredField3.get(notificationCompat$Builder);
                notificationGenerationJob.overriddenBodyFromExtender = overriddenBodyFromExtender;
                notificationGenerationJob.overriddenTitleFromExtender = overriddenTitleFromExtender;
                if (!notificationGenerationJob.restoring) {
                    notificationGenerationJob.overriddenFlags = notification2.flags;
                    notificationGenerationJob.overriddenSound = notification2.sound;
                }
            }
            catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }
    
    private static int convertOSToAndroidPriority(final int n) {
        if (n > 9) {
            return 2;
        }
        if (n > 7) {
            return 1;
        }
        if (n > 4) {
            return 0;
        }
        if (n > 2) {
            return -1;
        }
        return -2;
    }
    
    private static Intent createBaseSummaryIntent(final int n, final JSONObject jsonObject, final String s) {
        return getNewBaseIntent(n).putExtra("onesignal_data", jsonObject.toString()).putExtra("summary", s);
    }
    
    private static Notification createSingleNotificationBeforeSummaryBuilder(final NotificationGenerationJob notificationGenerationJob, final NotificationCompat$Builder notificationCompat$Builder) {
        boolean b;
        if (Build$VERSION.SDK_INT > 17 && Build$VERSION.SDK_INT < 24 && !notificationGenerationJob.restoring) {
            b = true;
        }
        else {
            b = false;
        }
        if (b && notificationGenerationJob.overriddenSound != null && !notificationGenerationJob.overriddenSound.equals((Object)notificationGenerationJob.orgSound)) {
            notificationCompat$Builder.setSound((Uri)null);
        }
        final Notification build = notificationCompat$Builder.build();
        if (b) {
            notificationCompat$Builder.setSound(notificationGenerationJob.overriddenSound);
        }
        return build;
    }
    
    private static void createSummaryIdDatabaseEntry(final OneSignalDbHelper oneSignalDbHelper, final String s, final int n) {
        SQLiteDatabase sqLiteDatabase = null;
        SQLiteDatabase writableDbWithRetries = null;
        try {
            final SQLiteDatabase sqLiteDatabase2 = sqLiteDatabase = (writableDbWithRetries = oneSignalDbHelper.getWritableDbWithRetries());
            sqLiteDatabase2.beginTransaction();
            writableDbWithRetries = sqLiteDatabase2;
            sqLiteDatabase = sqLiteDatabase2;
            final ContentValues contentValues = new ContentValues();
            writableDbWithRetries = sqLiteDatabase2;
            sqLiteDatabase = sqLiteDatabase2;
            contentValues.put("android_notification_id", Integer.valueOf(n));
            writableDbWithRetries = sqLiteDatabase2;
            sqLiteDatabase = sqLiteDatabase2;
            contentValues.put("group_id", s);
            writableDbWithRetries = sqLiteDatabase2;
            sqLiteDatabase = sqLiteDatabase2;
            contentValues.put("is_summary", Integer.valueOf(1));
            writableDbWithRetries = sqLiteDatabase2;
            sqLiteDatabase = sqLiteDatabase2;
            sqLiteDatabase2.insertOrThrow("notification", (String)null, contentValues);
            writableDbWithRetries = sqLiteDatabase2;
            sqLiteDatabase = sqLiteDatabase2;
            sqLiteDatabase2.setTransactionSuccessful();
            if (sqLiteDatabase2 == null) {
                return;
            }
            try {
                sqLiteDatabase2.endTransaction();
            }
            catch (Throwable t) {
                OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error closing transaction! ", t);
            }
        }
        catch (Throwable t2) {
            sqLiteDatabase = writableDbWithRetries;
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error adding summary notification record! ", t2);
            if (writableDbWithRetries == null) {
                return;
            }
            try {
                writableDbWithRetries.endTransaction();
            }
            catch (Throwable t3) {
                OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error closing transaction! ", t3);
            }
        }
        finally {
            Label_0173: {
                if (sqLiteDatabase == null) {
                    break Label_0173;
                }
                try {
                    sqLiteDatabase.endTransaction();
                }
                catch (Throwable t4) {
                    OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error closing transaction! ", t4);
                }
            }
        }
    }
    
    private static void createSummaryNotification(final NotificationGenerationJob p0, final OneSignalNotificationBuilder p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/onesignal/NotificationGenerationJob.restoring:Z
        //     4: istore          4
        //     6: aload_0        
        //     7: getfield        com/onesignal/NotificationGenerationJob.jsonPayload:Lorg/json/JSONObject;
        //    10: astore          12
        //    12: aload           12
        //    14: ldc_w           "grp"
        //    17: aconst_null    
        //    18: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    21: astore          15
        //    23: new             Ljava/security/SecureRandom;
        //    26: dup            
        //    27: invokespecial   java/security/SecureRandom.<init>:()V
        //    30: astore          17
        //    32: aload           17
        //    34: invokevirtual   java/security/SecureRandom.nextInt:()I
        //    37: iconst_0       
        //    38: invokestatic    com/onesignal/GenerateNotification.getNewBaseDeleteIntent:(I)Landroid/content/Intent;
        //    41: ldc_w           "summary"
        //    44: aload           15
        //    46: invokevirtual   android/content/Intent.putExtra:(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
        //    49: invokestatic    com/onesignal/GenerateNotification.getNewActionPendingIntent:(ILandroid/content/Intent;)Landroid/app/PendingIntent;
        //    52: astore          16
        //    54: aconst_null    
        //    55: astore          8
        //    57: aconst_null    
        //    58: astore          13
        //    60: aconst_null    
        //    61: astore          11
        //    63: aconst_null    
        //    64: astore          14
        //    66: getstatic       com/onesignal/GenerateNotification.currentContext:Landroid/content/Context;
        //    69: invokestatic    com/onesignal/OneSignalDbHelper.getInstance:(Landroid/content/Context;)Lcom/onesignal/OneSignalDbHelper;
        //    72: astore          18
        //    74: aconst_null    
        //    75: astore          9
        //    77: aload           9
        //    79: astore          7
        //    81: aload           18
        //    83: invokevirtual   com/onesignal/OneSignalDbHelper.getReadableDbWithRetries:()Landroid/database/sqlite/SQLiteDatabase;
        //    86: astore          10
        //    88: ldc_w           "group_id = ? AND dismissed = 0 AND opened = 0"
        //    91: astore          7
        //    93: aload           7
        //    95: astore          6
        //    97: iload           4
        //    99: ifne            156
        //   102: aload           7
        //   104: astore          6
        //   106: aload           9
        //   108: astore          7
        //   110: aload_0        
        //   111: invokevirtual   com/onesignal/NotificationGenerationJob.getAndroidId:()Ljava/lang/Integer;
        //   114: invokevirtual   java/lang/Integer.intValue:()I
        //   117: iconst_m1      
        //   118: if_icmpeq       156
        //   121: aload           9
        //   123: astore          7
        //   125: new             Ljava/lang/StringBuilder;
        //   128: dup            
        //   129: invokespecial   java/lang/StringBuilder.<init>:()V
        //   132: ldc_w           "group_id = ? AND dismissed = 0 AND opened = 0"
        //   135: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   138: ldc_w           " AND android_notification_id <> "
        //   141: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   144: aload_0        
        //   145: invokevirtual   com/onesignal/NotificationGenerationJob.getAndroidId:()Ljava/lang/Integer;
        //   148: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   151: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   154: astore          6
        //   156: aload           9
        //   158: astore          7
        //   160: aload           10
        //   162: ldc_w           "notification"
        //   165: iconst_5       
        //   166: anewarray       Ljava/lang/String;
        //   169: dup            
        //   170: iconst_0       
        //   171: ldc_w           "android_notification_id"
        //   174: aastore        
        //   175: dup            
        //   176: iconst_1       
        //   177: ldc_w           "full_data"
        //   180: aastore        
        //   181: dup            
        //   182: iconst_2       
        //   183: ldc_w           "is_summary"
        //   186: aastore        
        //   187: dup            
        //   188: iconst_3       
        //   189: ldc_w           "title"
        //   192: aastore        
        //   193: dup            
        //   194: iconst_4       
        //   195: ldc_w           "message"
        //   198: aastore        
        //   199: aload           6
        //   201: iconst_1       
        //   202: anewarray       Ljava/lang/String;
        //   205: dup            
        //   206: iconst_0       
        //   207: aload           15
        //   209: aastore        
        //   210: aconst_null    
        //   211: aconst_null    
        //   212: ldc_w           "_id DESC"
        //   215: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //   218: astore          9
        //   220: aload           12
        //   222: astore          10
        //   224: aload           14
        //   226: astore          6
        //   228: aload           9
        //   230: astore          7
        //   232: aload           9
        //   234: invokeinterface android/database/Cursor.moveToFirst:()Z
        //   239: ifeq            357
        //   242: aload           9
        //   244: astore          7
        //   246: new             Ljava/util/ArrayList;
        //   249: dup            
        //   250: invokespecial   java/util/ArrayList.<init>:()V
        //   253: astore          6
        //   255: aload           13
        //   257: astore          8
        //   259: aload           11
        //   261: astore          10
        //   263: aload           9
        //   265: aload           9
        //   267: ldc_w           "is_summary"
        //   270: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   275: invokeinterface android/database/Cursor.getInt:(I)I
        //   280: iconst_1       
        //   281: if_icmpne       776
        //   284: aload           9
        //   286: aload           9
        //   288: ldc_w           "android_notification_id"
        //   291: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   296: invokeinterface android/database/Cursor.getInt:(I)I
        //   301: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   304: astore          7
        //   306: aload           10
        //   308: astore          11
        //   310: aload           9
        //   312: invokeinterface android/database/Cursor.moveToNext:()Z
        //   317: istore          5
        //   319: aload           11
        //   321: astore          10
        //   323: aload           7
        //   325: astore          8
        //   327: iload           5
        //   329: ifne            263
        //   332: iload           4
        //   334: ifeq            963
        //   337: aload           11
        //   339: ifnull          963
        //   342: new             Lorg/json/JSONObject;
        //   345: dup            
        //   346: aload           11
        //   348: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //   351: astore          10
        //   353: aload           7
        //   355: astore          8
        //   357: aload           9
        //   359: ifnull          379
        //   362: aload           9
        //   364: invokeinterface android/database/Cursor.isClosed:()Z
        //   369: ifne            379
        //   372: aload           9
        //   374: invokeinterface android/database/Cursor.close:()V
        //   379: aload           8
        //   381: astore          7
        //   383: aload           8
        //   385: ifnonnull       410
        //   388: aload           17
        //   390: invokevirtual   java/security/SecureRandom.nextInt:()I
        //   393: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   396: astore          7
        //   398: aload           18
        //   400: aload           15
        //   402: aload           7
        //   404: invokevirtual   java/lang/Integer.intValue:()I
        //   407: invokestatic    com/onesignal/GenerateNotification.createSummaryIdDatabaseEntry:(Lcom/onesignal/OneSignalDbHelper;Ljava/lang/String;I)V
        //   410: aload           17
        //   412: invokevirtual   java/security/SecureRandom.nextInt:()I
        //   415: aload           7
        //   417: invokevirtual   java/lang/Integer.intValue:()I
        //   420: aload           10
        //   422: aload           15
        //   424: invokestatic    com/onesignal/GenerateNotification.createBaseSummaryIntent:(ILorg/json/JSONObject;Ljava/lang/String;)Landroid/content/Intent;
        //   427: invokestatic    com/onesignal/GenerateNotification.getNewActionPendingIntent:(ILandroid/content/Intent;)Landroid/app/PendingIntent;
        //   430: astore          8
        //   432: aload           6
        //   434: ifnull          1138
        //   437: iload           4
        //   439: ifeq            453
        //   442: aload           6
        //   444: invokeinterface java/util/Collection.size:()I
        //   449: iconst_1       
        //   450: if_icmpgt       468
        //   453: iload           4
        //   455: ifne            1138
        //   458: aload           6
        //   460: invokeinterface java/util/Collection.size:()I
        //   465: ifle            1138
        //   468: aload           6
        //   470: invokeinterface java/util/Collection.size:()I
        //   475: istore_3       
        //   476: iload           4
        //   478: ifeq            999
        //   481: iconst_0       
        //   482: istore_2       
        //   483: iload_3        
        //   484: iload_2        
        //   485: iadd           
        //   486: istore_2       
        //   487: aload           10
        //   489: ldc_w           "grp_msg"
        //   492: aconst_null    
        //   493: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   496: astore_1       
        //   497: aload_1        
        //   498: ifnonnull       1004
        //   501: new             Ljava/lang/StringBuilder;
        //   504: dup            
        //   505: invokespecial   java/lang/StringBuilder.<init>:()V
        //   508: iload_2        
        //   509: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   512: ldc_w           " new messages"
        //   515: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   518: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   521: astore_1       
        //   522: aload_0        
        //   523: invokestatic    com/onesignal/GenerateNotification.getBaseOneSignalNotificationBuilder:(Lcom/onesignal/NotificationGenerationJob;)Lcom/onesignal/GenerateNotification$OneSignalNotificationBuilder;
        //   526: getfield        com/onesignal/GenerateNotification$OneSignalNotificationBuilder.compatBuilder:Landroid/support/v4/app/NotificationCompat$Builder;
        //   529: astore          9
        //   531: iload           4
        //   533: ifeq            1035
        //   536: aload           9
        //   538: invokestatic    com/onesignal/GenerateNotification.removeNotifyOptions:(Landroid/support/v4/app/NotificationCompat$Builder;)V
        //   541: aload           9
        //   543: aload           8
        //   545: invokevirtual   android/support/v4/app/NotificationCompat$Builder.setContentIntent:(Landroid/app/PendingIntent;)Landroid/support/v4/app/NotificationCompat$Builder;
        //   548: aload           16
        //   550: invokevirtual   android/support/v4/app/NotificationCompat$Builder.setDeleteIntent:(Landroid/app/PendingIntent;)Landroid/support/v4/app/NotificationCompat$Builder;
        //   553: getstatic       com/onesignal/GenerateNotification.currentContext:Landroid/content/Context;
        //   556: invokevirtual   android/content/Context.getPackageManager:()Landroid/content/pm/PackageManager;
        //   559: getstatic       com/onesignal/GenerateNotification.currentContext:Landroid/content/Context;
        //   562: invokevirtual   android/content/Context.getApplicationInfo:()Landroid/content/pm/ApplicationInfo;
        //   565: invokevirtual   android/content/pm/PackageManager.getApplicationLabel:(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
        //   568: invokevirtual   android/support/v4/app/NotificationCompat$Builder.setContentTitle:(Ljava/lang/CharSequence;)Landroid/support/v4/app/NotificationCompat$Builder;
        //   571: aload_1        
        //   572: invokevirtual   android/support/v4/app/NotificationCompat$Builder.setContentText:(Ljava/lang/CharSequence;)Landroid/support/v4/app/NotificationCompat$Builder;
        //   575: iload_2        
        //   576: invokevirtual   android/support/v4/app/NotificationCompat$Builder.setNumber:(I)Landroid/support/v4/app/NotificationCompat$Builder;
        //   579: invokestatic    com/onesignal/GenerateNotification.getDefaultSmallIconId:()I
        //   582: invokevirtual   android/support/v4/app/NotificationCompat$Builder.setSmallIcon:(I)Landroid/support/v4/app/NotificationCompat$Builder;
        //   585: invokestatic    com/onesignal/GenerateNotification.getDefaultLargeIcon:()Landroid/graphics/Bitmap;
        //   588: invokevirtual   android/support/v4/app/NotificationCompat$Builder.setLargeIcon:(Landroid/graphics/Bitmap;)Landroid/support/v4/app/NotificationCompat$Builder;
        //   591: iload           4
        //   593: invokevirtual   android/support/v4/app/NotificationCompat$Builder.setOnlyAlertOnce:(Z)Landroid/support/v4/app/NotificationCompat$Builder;
        //   596: aload           15
        //   598: invokevirtual   android/support/v4/app/NotificationCompat$Builder.setGroup:(Ljava/lang/String;)Landroid/support/v4/app/NotificationCompat$Builder;
        //   601: iconst_1       
        //   602: invokevirtual   android/support/v4/app/NotificationCompat$Builder.setGroupSummary:(Z)Landroid/support/v4/app/NotificationCompat$Builder;
        //   605: pop            
        //   606: aload           9
        //   608: iconst_1       
        //   609: invokevirtual   android/support/v4/app/NotificationCompat$Builder.setGroupAlertBehavior:(I)Landroid/support/v4/app/NotificationCompat$Builder;
        //   612: pop            
        //   613: iload           4
        //   615: ifne            625
        //   618: aload           9
        //   620: aload_1        
        //   621: invokevirtual   android/support/v4/app/NotificationCompat$Builder.setTicker:(Ljava/lang/CharSequence;)Landroid/support/v4/app/NotificationCompat$Builder;
        //   624: pop            
        //   625: new             Landroid/support/v4/app/NotificationCompat$InboxStyle;
        //   628: dup            
        //   629: invokespecial   android/support/v4/app/NotificationCompat$InboxStyle.<init>:()V
        //   632: astore          10
        //   634: iload           4
        //   636: ifne            741
        //   639: aconst_null    
        //   640: astore          8
        //   642: aload_0        
        //   643: invokevirtual   com/onesignal/NotificationGenerationJob.getTitle:()Ljava/lang/CharSequence;
        //   646: ifnull          660
        //   649: aload_0        
        //   650: invokevirtual   com/onesignal/NotificationGenerationJob.getTitle:()Ljava/lang/CharSequence;
        //   653: invokeinterface java/lang/CharSequence.toString:()Ljava/lang/String;
        //   658: astore          8
        //   660: aload           8
        //   662: ifnonnull       1075
        //   665: ldc_w           ""
        //   668: astore          8
        //   670: aload_0        
        //   671: invokevirtual   com/onesignal/NotificationGenerationJob.getBody:()Ljava/lang/CharSequence;
        //   674: invokeinterface java/lang/CharSequence.toString:()Ljava/lang/String;
        //   679: astore_0       
        //   680: new             Landroid/text/SpannableString;
        //   683: dup            
        //   684: new             Ljava/lang/StringBuilder;
        //   687: dup            
        //   688: invokespecial   java/lang/StringBuilder.<init>:()V
        //   691: aload           8
        //   693: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   696: aload_0        
        //   697: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   700: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   703: invokespecial   android/text/SpannableString.<init>:(Ljava/lang/CharSequence;)V
        //   706: astore_0       
        //   707: aload           8
        //   709: invokevirtual   java/lang/String.length:()I
        //   712: ifle            734
        //   715: aload_0        
        //   716: new             Landroid/text/style/StyleSpan;
        //   719: dup            
        //   720: iconst_1       
        //   721: invokespecial   android/text/style/StyleSpan.<init>:(I)V
        //   724: iconst_0       
        //   725: aload           8
        //   727: invokevirtual   java/lang/String.length:()I
        //   730: iconst_0       
        //   731: invokevirtual   android/text/SpannableString.setSpan:(Ljava/lang/Object;III)V
        //   734: aload           10
        //   736: aload_0        
        //   737: invokevirtual   android/support/v4/app/NotificationCompat$InboxStyle.addLine:(Ljava/lang/CharSequence;)Landroid/support/v4/app/NotificationCompat$InboxStyle;
        //   740: pop            
        //   741: aload           6
        //   743: invokeinterface java/util/Collection.iterator:()Ljava/util/Iterator;
        //   748: astore_0       
        //   749: aload_0        
        //   750: invokeinterface java/util/Iterator.hasNext:()Z
        //   755: ifeq            1101
        //   758: aload           10
        //   760: aload_0        
        //   761: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   766: checkcast       Landroid/text/SpannableString;
        //   769: invokevirtual   android/support/v4/app/NotificationCompat$InboxStyle.addLine:(Ljava/lang/CharSequence;)Landroid/support/v4/app/NotificationCompat$InboxStyle;
        //   772: pop            
        //   773: goto            749
        //   776: aload           9
        //   778: aload           9
        //   780: ldc_w           "title"
        //   783: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   788: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //   793: astore          7
        //   795: aload           7
        //   797: ifnonnull       930
        //   800: ldc_w           ""
        //   803: astore          7
        //   805: aload           9
        //   807: aload           9
        //   809: ldc_w           "message"
        //   812: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   817: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //   822: astore          11
        //   824: new             Landroid/text/SpannableString;
        //   827: dup            
        //   828: new             Ljava/lang/StringBuilder;
        //   831: dup            
        //   832: invokespecial   java/lang/StringBuilder.<init>:()V
        //   835: aload           7
        //   837: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   840: aload           11
        //   842: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   845: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   848: invokespecial   android/text/SpannableString.<init>:(Ljava/lang/CharSequence;)V
        //   851: astore          11
        //   853: aload           7
        //   855: invokevirtual   java/lang/String.length:()I
        //   858: ifle            881
        //   861: aload           11
        //   863: new             Landroid/text/style/StyleSpan;
        //   866: dup            
        //   867: iconst_1       
        //   868: invokespecial   android/text/style/StyleSpan.<init>:(I)V
        //   871: iconst_0       
        //   872: aload           7
        //   874: invokevirtual   java/lang/String.length:()I
        //   877: iconst_0       
        //   878: invokevirtual   android/text/SpannableString.setSpan:(Ljava/lang/Object;III)V
        //   881: aload           6
        //   883: aload           11
        //   885: invokeinterface java/util/Collection.add:(Ljava/lang/Object;)Z
        //   890: pop            
        //   891: aload           10
        //   893: astore          11
        //   895: aload           8
        //   897: astore          7
        //   899: aload           10
        //   901: ifnonnull       310
        //   904: aload           9
        //   906: aload           9
        //   908: ldc_w           "full_data"
        //   911: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   916: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //   921: astore          11
        //   923: aload           8
        //   925: astore          7
        //   927: goto            310
        //   930: new             Ljava/lang/StringBuilder;
        //   933: dup            
        //   934: invokespecial   java/lang/StringBuilder.<init>:()V
        //   937: aload           7
        //   939: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   942: ldc_w           " "
        //   945: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   948: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   951: astore          7
        //   953: goto            805
        //   956: astore          8
        //   958: aload           8
        //   960: invokevirtual   org/json/JSONException.printStackTrace:()V
        //   963: aload           12
        //   965: astore          10
        //   967: aload           7
        //   969: astore          8
        //   971: goto            357
        //   974: astore_0       
        //   975: aload           7
        //   977: ifnull          997
        //   980: aload           7
        //   982: invokeinterface android/database/Cursor.isClosed:()Z
        //   987: ifne            997
        //   990: aload           7
        //   992: invokeinterface android/database/Cursor.close:()V
        //   997: aload_0        
        //   998: athrow         
        //   999: iconst_1       
        //  1000: istore_2       
        //  1001: goto            483
        //  1004: aload_1        
        //  1005: ldc_w           "$[notif_count]"
        //  1008: new             Ljava/lang/StringBuilder;
        //  1011: dup            
        //  1012: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1015: ldc_w           ""
        //  1018: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1021: iload_2        
        //  1022: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //  1025: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1028: invokevirtual   java/lang/String.replace:(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //  1031: astore_1       
        //  1032: goto            522
        //  1035: aload_0        
        //  1036: getfield        com/onesignal/NotificationGenerationJob.overriddenSound:Landroid/net/Uri;
        //  1039: ifnull          1052
        //  1042: aload           9
        //  1044: aload_0        
        //  1045: getfield        com/onesignal/NotificationGenerationJob.overriddenSound:Landroid/net/Uri;
        //  1048: invokevirtual   android/support/v4/app/NotificationCompat$Builder.setSound:(Landroid/net/Uri;)Landroid/support/v4/app/NotificationCompat$Builder;
        //  1051: pop            
        //  1052: aload_0        
        //  1053: getfield        com/onesignal/NotificationGenerationJob.overriddenFlags:Ljava/lang/Integer;
        //  1056: ifnull          541
        //  1059: aload           9
        //  1061: aload_0        
        //  1062: getfield        com/onesignal/NotificationGenerationJob.overriddenFlags:Ljava/lang/Integer;
        //  1065: invokevirtual   java/lang/Integer.intValue:()I
        //  1068: invokevirtual   android/support/v4/app/NotificationCompat$Builder.setDefaults:(I)Landroid/support/v4/app/NotificationCompat$Builder;
        //  1071: pop            
        //  1072: goto            541
        //  1075: new             Ljava/lang/StringBuilder;
        //  1078: dup            
        //  1079: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1082: aload           8
        //  1084: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1087: ldc_w           " "
        //  1090: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1093: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1096: astore          8
        //  1098: goto            670
        //  1101: aload           10
        //  1103: aload_1        
        //  1104: invokevirtual   android/support/v4/app/NotificationCompat$InboxStyle.setBigContentTitle:(Ljava/lang/CharSequence;)Landroid/support/v4/app/NotificationCompat$InboxStyle;
        //  1107: pop            
        //  1108: aload           9
        //  1110: aload           10
        //  1112: invokevirtual   android/support/v4/app/NotificationCompat$Builder.setStyle:(Landroid/support/v4/app/NotificationCompat$Style;)Landroid/support/v4/app/NotificationCompat$Builder;
        //  1115: pop            
        //  1116: aload           9
        //  1118: invokevirtual   android/support/v4/app/NotificationCompat$Builder.build:()Landroid/app/Notification;
        //  1121: astore_0       
        //  1122: getstatic       com/onesignal/GenerateNotification.currentContext:Landroid/content/Context;
        //  1125: invokestatic    android/support/v4/app/NotificationManagerCompat.from:(Landroid/content/Context;)Landroid/support/v4/app/NotificationManagerCompat;
        //  1128: aload           7
        //  1130: invokevirtual   java/lang/Integer.intValue:()I
        //  1133: aload_0        
        //  1134: invokevirtual   android/support/v4/app/NotificationManagerCompat.notify:(ILandroid/app/Notification;)V
        //  1137: return         
        //  1138: aload_1        
        //  1139: getfield        com/onesignal/GenerateNotification$OneSignalNotificationBuilder.compatBuilder:Landroid/support/v4/app/NotificationCompat$Builder;
        //  1142: astore_0       
        //  1143: aload_0        
        //  1144: getfield        android/support/v4/app/NotificationCompat$Builder.mActions:Ljava/util/ArrayList;
        //  1147: invokevirtual   java/util/ArrayList.clear:()V
        //  1150: aload           10
        //  1152: aload_0        
        //  1153: aload           7
        //  1155: invokevirtual   java/lang/Integer.intValue:()I
        //  1158: aload           15
        //  1160: invokestatic    com/onesignal/GenerateNotification.addNotificationActionButtons:(Lorg/json/JSONObject;Landroid/support/v4/app/NotificationCompat$Builder;ILjava/lang/String;)V
        //  1163: aload_0        
        //  1164: aload           8
        //  1166: invokevirtual   android/support/v4/app/NotificationCompat$Builder.setContentIntent:(Landroid/app/PendingIntent;)Landroid/support/v4/app/NotificationCompat$Builder;
        //  1169: aload           16
        //  1171: invokevirtual   android/support/v4/app/NotificationCompat$Builder.setDeleteIntent:(Landroid/app/PendingIntent;)Landroid/support/v4/app/NotificationCompat$Builder;
        //  1174: iload           4
        //  1176: invokevirtual   android/support/v4/app/NotificationCompat$Builder.setOnlyAlertOnce:(Z)Landroid/support/v4/app/NotificationCompat$Builder;
        //  1179: aload           15
        //  1181: invokevirtual   android/support/v4/app/NotificationCompat$Builder.setGroup:(Ljava/lang/String;)Landroid/support/v4/app/NotificationCompat$Builder;
        //  1184: iconst_1       
        //  1185: invokevirtual   android/support/v4/app/NotificationCompat$Builder.setGroupSummary:(Z)Landroid/support/v4/app/NotificationCompat$Builder;
        //  1188: pop            
        //  1189: aload_0        
        //  1190: iconst_1       
        //  1191: invokevirtual   android/support/v4/app/NotificationCompat$Builder.setGroupAlertBehavior:(I)Landroid/support/v4/app/NotificationCompat$Builder;
        //  1194: pop            
        //  1195: aload_0        
        //  1196: invokevirtual   android/support/v4/app/NotificationCompat$Builder.build:()Landroid/app/Notification;
        //  1199: astore_0       
        //  1200: aload_1        
        //  1201: aload_0        
        //  1202: invokestatic    com/onesignal/GenerateNotification.addXiaomiSettings:(Lcom/onesignal/GenerateNotification$OneSignalNotificationBuilder;Landroid/app/Notification;)V
        //  1205: goto            1122
        //  1208: astore          8
        //  1210: goto            613
        //  1213: astore          6
        //  1215: goto            1195
        //  1218: astore_0       
        //  1219: aload           9
        //  1221: astore          7
        //  1223: goto            975
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  81     88     974    975    Any
        //  110    121    974    975    Any
        //  125    156    974    975    Any
        //  160    220    974    975    Any
        //  232    242    974    975    Any
        //  246    255    974    975    Any
        //  263    306    1218   1226   Any
        //  310    319    1218   1226   Any
        //  342    353    956    963    Lorg/json/JSONException;
        //  342    353    1218   1226   Any
        //  606    613    1208   1213   Ljava/lang/Throwable;
        //  776    795    1218   1226   Any
        //  805    881    1218   1226   Any
        //  881    891    1218   1226   Any
        //  904    923    1218   1226   Any
        //  930    953    1218   1226   Any
        //  958    963    1218   1226   Any
        //  1189   1195   1213   1218   Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0263:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    static void fromJsonPayload(final NotificationGenerationJob notificationGenerationJob) {
        setStatics(notificationGenerationJob.context);
        if (!notificationGenerationJob.restoring && notificationGenerationJob.showAsAlert && ActivityLifecycleHandler.curActivity != null) {
            showNotificationAsAlert(notificationGenerationJob.jsonPayload, ActivityLifecycleHandler.curActivity, notificationGenerationJob.getAndroidId());
            return;
        }
        showNotification(notificationGenerationJob);
    }
    
    private static BigInteger getAccentColor(final JSONObject jsonObject) {
        try {
            if (jsonObject.has("bgac")) {
                return new BigInteger(jsonObject.optString("bgac", (String)null), 16);
            }
        }
        catch (Throwable t) {}
        try {
            final String manifestMeta = OSUtils.getManifestMeta(GenerateNotification.currentContext, "com.onesignal.NotificationAccentColor.DEFAULT");
            if (manifestMeta != null) {
                return new BigInteger(manifestMeta, 16);
            }
        }
        catch (Throwable t2) {}
        return null;
    }
    
    private static OneSignalNotificationBuilder getBaseOneSignalNotificationBuilder(final NotificationGenerationJob p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/onesignal/NotificationGenerationJob.jsonPayload:Lorg/json/JSONObject;
        //     4: astore_3       
        //     5: new             Lcom/onesignal/GenerateNotification$OneSignalNotificationBuilder;
        //     8: dup            
        //     9: aconst_null    
        //    10: invokespecial   com/onesignal/GenerateNotification$OneSignalNotificationBuilder.<init>:(Lcom/onesignal/GenerateNotification$1;)V
        //    13: astore          4
        //    15: aload_0        
        //    16: invokestatic    com/onesignal/NotificationChannelManager.createNotificationChannel:(Lcom/onesignal/NotificationGenerationJob;)Ljava/lang/String;
        //    19: astore_2       
        //    20: new             Landroid/support/v4/app/NotificationCompat$Builder;
        //    23: dup            
        //    24: getstatic       com/onesignal/GenerateNotification.currentContext:Landroid/content/Context;
        //    27: aload_2        
        //    28: invokespecial   android/support/v4/app/NotificationCompat$Builder.<init>:(Landroid/content/Context;Ljava/lang/String;)V
        //    31: astore_2       
        //    32: aload_3        
        //    33: ldc             "alert"
        //    35: aconst_null    
        //    36: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    39: astore          5
        //    41: aload_2        
        //    42: iconst_1       
        //    43: invokevirtual   android/support/v4/app/NotificationCompat$Builder.setAutoCancel:(Z)Landroid/support/v4/app/NotificationCompat$Builder;
        //    46: aload_3        
        //    47: invokestatic    com/onesignal/GenerateNotification.getSmallIconId:(Lorg/json/JSONObject;)I
        //    50: invokevirtual   android/support/v4/app/NotificationCompat$Builder.setSmallIcon:(I)Landroid/support/v4/app/NotificationCompat$Builder;
        //    53: new             Landroid/support/v4/app/NotificationCompat$BigTextStyle;
        //    56: dup            
        //    57: invokespecial   android/support/v4/app/NotificationCompat$BigTextStyle.<init>:()V
        //    60: aload           5
        //    62: invokevirtual   android/support/v4/app/NotificationCompat$BigTextStyle.bigText:(Ljava/lang/CharSequence;)Landroid/support/v4/app/NotificationCompat$BigTextStyle;
        //    65: invokevirtual   android/support/v4/app/NotificationCompat$Builder.setStyle:(Landroid/support/v4/app/NotificationCompat$Style;)Landroid/support/v4/app/NotificationCompat$Builder;
        //    68: aload           5
        //    70: invokevirtual   android/support/v4/app/NotificationCompat$Builder.setContentText:(Ljava/lang/CharSequence;)Landroid/support/v4/app/NotificationCompat$Builder;
        //    73: aload           5
        //    75: invokevirtual   android/support/v4/app/NotificationCompat$Builder.setTicker:(Ljava/lang/CharSequence;)Landroid/support/v4/app/NotificationCompat$Builder;
        //    78: pop            
        //    79: getstatic       android/os/Build$VERSION.SDK_INT:I
        //    82: bipush          24
        //    84: if_icmplt       103
        //    87: aload_3        
        //    88: ldc_w           "title"
        //    91: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;)Ljava/lang/String;
        //    94: ldc_w           ""
        //    97: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   100: ifne            112
        //   103: aload_2        
        //   104: aload_3        
        //   105: invokestatic    com/onesignal/GenerateNotification.getTitle:(Lorg/json/JSONObject;)Ljava/lang/CharSequence;
        //   108: invokevirtual   android/support/v4/app/NotificationCompat$Builder.setContentTitle:(Ljava/lang/CharSequence;)Landroid/support/v4/app/NotificationCompat$Builder;
        //   111: pop            
        //   112: aload_3        
        //   113: invokestatic    com/onesignal/GenerateNotification.getAccentColor:(Lorg/json/JSONObject;)Ljava/math/BigInteger;
        //   116: astore          6
        //   118: aload           6
        //   120: ifnull          133
        //   123: aload_2        
        //   124: aload           6
        //   126: invokevirtual   java/math/BigInteger.intValue:()I
        //   129: invokevirtual   android/support/v4/app/NotificationCompat$Builder.setColor:(I)Landroid/support/v4/app/NotificationCompat$Builder;
        //   132: pop            
        //   133: iconst_1       
        //   134: istore_1       
        //   135: aload_3        
        //   136: ldc_w           "vis"
        //   139: invokevirtual   org/json/JSONObject.has:(Ljava/lang/String;)Z
        //   142: ifeq            156
        //   145: aload_3        
        //   146: ldc_w           "vis"
        //   149: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;)Ljava/lang/String;
        //   152: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //   155: istore_1       
        //   156: aload_2        
        //   157: iload_1        
        //   158: invokevirtual   android/support/v4/app/NotificationCompat$Builder.setVisibility:(I)Landroid/support/v4/app/NotificationCompat$Builder;
        //   161: pop            
        //   162: aload_3        
        //   163: invokestatic    com/onesignal/GenerateNotification.getLargeIcon:(Lorg/json/JSONObject;)Landroid/graphics/Bitmap;
        //   166: astore          6
        //   168: aload           6
        //   170: ifnull          186
        //   173: aload           4
        //   175: iconst_1       
        //   176: putfield        com/onesignal/GenerateNotification$OneSignalNotificationBuilder.hasLargeIcon:Z
        //   179: aload_2        
        //   180: aload           6
        //   182: invokevirtual   android/support/v4/app/NotificationCompat$Builder.setLargeIcon:(Landroid/graphics/Bitmap;)Landroid/support/v4/app/NotificationCompat$Builder;
        //   185: pop            
        //   186: aload_3        
        //   187: ldc_w           "bicon"
        //   190: aconst_null    
        //   191: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   194: invokestatic    com/onesignal/GenerateNotification.getBitmap:(Ljava/lang/String;)Landroid/graphics/Bitmap;
        //   197: astore          6
        //   199: aload           6
        //   201: ifnull          226
        //   204: aload_2        
        //   205: new             Landroid/support/v4/app/NotificationCompat$BigPictureStyle;
        //   208: dup            
        //   209: invokespecial   android/support/v4/app/NotificationCompat$BigPictureStyle.<init>:()V
        //   212: aload           6
        //   214: invokevirtual   android/support/v4/app/NotificationCompat$BigPictureStyle.bigPicture:(Landroid/graphics/Bitmap;)Landroid/support/v4/app/NotificationCompat$BigPictureStyle;
        //   217: aload           5
        //   219: invokevirtual   android/support/v4/app/NotificationCompat$BigPictureStyle.setSummaryText:(Ljava/lang/CharSequence;)Landroid/support/v4/app/NotificationCompat$BigPictureStyle;
        //   222: invokevirtual   android/support/v4/app/NotificationCompat$Builder.setStyle:(Landroid/support/v4/app/NotificationCompat$Style;)Landroid/support/v4/app/NotificationCompat$Builder;
        //   225: pop            
        //   226: aload_0        
        //   227: getfield        com/onesignal/NotificationGenerationJob.shownTimeStamp:Ljava/lang/Long;
        //   230: ifnull          249
        //   233: aload_2        
        //   234: aload_0        
        //   235: getfield        com/onesignal/NotificationGenerationJob.shownTimeStamp:Ljava/lang/Long;
        //   238: invokevirtual   java/lang/Long.longValue:()J
        //   241: ldc2_w          1000
        //   244: lmul           
        //   245: invokevirtual   android/support/v4/app/NotificationCompat$Builder.setWhen:(J)Landroid/support/v4/app/NotificationCompat$Builder;
        //   248: pop            
        //   249: aload_3        
        //   250: aload_2        
        //   251: invokestatic    com/onesignal/GenerateNotification.setAlertnessOptions:(Lorg/json/JSONObject;Landroid/support/v4/app/NotificationCompat$Builder;)V
        //   254: aload           4
        //   256: aload_2        
        //   257: putfield        com/onesignal/GenerateNotification$OneSignalNotificationBuilder.compatBuilder:Landroid/support/v4/app/NotificationCompat$Builder;
        //   260: aload           4
        //   262: areturn        
        //   263: astore_2       
        //   264: new             Landroid/support/v4/app/NotificationCompat$Builder;
        //   267: dup            
        //   268: getstatic       com/onesignal/GenerateNotification.currentContext:Landroid/content/Context;
        //   271: invokespecial   android/support/v4/app/NotificationCompat$Builder.<init>:(Landroid/content/Context;)V
        //   274: astore_2       
        //   275: goto            32
        //   278: astore_0       
        //   279: goto            249
        //   282: astore          6
        //   284: goto            162
        //   287: astore          6
        //   289: goto            133
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  15     32     263    278    Ljava/lang/Throwable;
        //  112    118    287    292    Ljava/lang/Throwable;
        //  123    133    287    292    Ljava/lang/Throwable;
        //  135    156    282    287    Ljava/lang/Throwable;
        //  156    162    282    287    Ljava/lang/Throwable;
        //  233    249    278    282    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0112:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private static Bitmap getBitmap(final String s) {
        if (s == null) {
            return null;
        }
        final String trim = s.trim();
        if (trim.startsWith("http://") || trim.startsWith("https://")) {
            return getBitmapFromURL(trim);
        }
        return getBitmapFromAssetsOrResourceName(s);
    }
    
    private static Bitmap getBitmapFromAssetsOrResourceName(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore_2       
        //     2: getstatic       com/onesignal/GenerateNotification.currentContext:Landroid/content/Context;
        //     5: invokevirtual   android/content/Context.getAssets:()Landroid/content/res/AssetManager;
        //     8: aload_0        
        //     9: invokevirtual   android/content/res/AssetManager.open:(Ljava/lang/String;)Ljava/io/InputStream;
        //    12: invokestatic    android/graphics/BitmapFactory.decodeStream:(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
        //    15: astore_3       
        //    16: aload_3        
        //    17: astore_2       
        //    18: aload_2        
        //    19: ifnull          24
        //    22: aload_2        
        //    23: areturn        
        //    24: iconst_5       
        //    25: anewarray       Ljava/lang/String;
        //    28: dup            
        //    29: iconst_0       
        //    30: ldc_w           ".png"
        //    33: aastore        
        //    34: dup            
        //    35: iconst_1       
        //    36: ldc_w           ".webp"
        //    39: aastore        
        //    40: dup            
        //    41: iconst_2       
        //    42: ldc_w           ".jpg"
        //    45: aastore        
        //    46: dup            
        //    47: iconst_3       
        //    48: ldc_w           ".gif"
        //    51: aastore        
        //    52: dup            
        //    53: iconst_4       
        //    54: ldc_w           ".bmp"
        //    57: aastore        
        //    58: invokestatic    java/util/Arrays.asList:([Ljava/lang/Object;)Ljava/util/List;
        //    61: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    66: astore          4
        //    68: aload           4
        //    70: invokeinterface java/util/Iterator.hasNext:()Z
        //    75: ifeq            128
        //    78: aload           4
        //    80: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    85: checkcast       Ljava/lang/String;
        //    88: astore_3       
        //    89: getstatic       com/onesignal/GenerateNotification.currentContext:Landroid/content/Context;
        //    92: invokevirtual   android/content/Context.getAssets:()Landroid/content/res/AssetManager;
        //    95: new             Ljava/lang/StringBuilder;
        //    98: dup            
        //    99: invokespecial   java/lang/StringBuilder.<init>:()V
        //   102: aload_0        
        //   103: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   106: aload_3        
        //   107: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   110: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   113: invokevirtual   android/content/res/AssetManager.open:(Ljava/lang/String;)Ljava/io/InputStream;
        //   116: invokestatic    android/graphics/BitmapFactory.decodeStream:(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
        //   119: astore_3       
        //   120: aload_3        
        //   121: astore_2       
        //   122: aload_3        
        //   123: ifnull          68
        //   126: aload_3        
        //   127: areturn        
        //   128: aload_0        
        //   129: invokestatic    com/onesignal/GenerateNotification.getResourceIcon:(Ljava/lang/String;)I
        //   132: istore_1       
        //   133: iload_1        
        //   134: ifeq            148
        //   137: getstatic       com/onesignal/GenerateNotification.contextResources:Landroid/content/res/Resources;
        //   140: iload_1        
        //   141: invokestatic    android/graphics/BitmapFactory.decodeResource:(Landroid/content/res/Resources;I)Landroid/graphics/Bitmap;
        //   144: astore_0       
        //   145: aload_0        
        //   146: areturn        
        //   147: astore_0       
        //   148: aconst_null    
        //   149: areturn        
        //   150: astore_3       
        //   151: aload_2        
        //   152: astore_3       
        //   153: goto            120
        //   156: astore_3       
        //   157: goto            18
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  2      16     156    160    Ljava/lang/Throwable;
        //  24     68     147    148    Ljava/lang/Throwable;
        //  68     89     147    148    Ljava/lang/Throwable;
        //  89     120    150    156    Ljava/lang/Throwable;
        //  128    133    147    148    Ljava/lang/Throwable;
        //  137    145    147    148    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0120:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private static Bitmap getBitmapFromURL(final String s) {
        try {
            return BitmapFactory.decodeStream(new URL(s).openConnection().getInputStream());
        }
        catch (Throwable t) {
            OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "Could not download image!", t);
            return null;
        }
    }
    
    private static Bitmap getDefaultLargeIcon() {
        return resizeBitmapForLargeIconArea(getBitmapFromAssetsOrResourceName("ic_onesignal_large_icon_default"));
    }
    
    private static int getDefaultSmallIconId() {
        int n = getDrawableId("ic_stat_onesignal_default");
        if (n == 0 && (n = getDrawableId("corona_statusbar_icon_default")) == 0 && (n = getDrawableId("ic_os_notification_fallback_white_24dp")) == 0) {
            return 17301598;
        }
        return n;
    }
    
    private static int getDrawableId(final String s) {
        return GenerateNotification.contextResources.getIdentifier(s, "drawable", GenerateNotification.packageName);
    }
    
    private static Bitmap getLargeIcon(final JSONObject jsonObject) {
        Bitmap bitmap;
        if ((bitmap = getBitmap(jsonObject.optString("licon"))) == null) {
            bitmap = getBitmapFromAssetsOrResourceName("ic_onesignal_large_icon_default");
        }
        if (bitmap == null) {
            return null;
        }
        return resizeBitmapForLargeIconArea(bitmap);
    }
    
    private static PendingIntent getNewActionPendingIntent(final int n, final Intent intent) {
        if (GenerateNotification.openerIsBroadcast) {
            return PendingIntent.getBroadcast(GenerateNotification.currentContext, n, intent, 134217728);
        }
        return PendingIntent.getActivity(GenerateNotification.currentContext, n, intent, 134217728);
    }
    
    private static Intent getNewBaseDeleteIntent(final int n) {
        final Intent putExtra = new Intent(GenerateNotification.currentContext, (Class)GenerateNotification.notificationOpenedClass).putExtra("notificationId", n).putExtra("dismissed", true);
        if (GenerateNotification.openerIsBroadcast) {
            return putExtra;
        }
        return putExtra.addFlags(402718720);
    }
    
    private static Intent getNewBaseIntent(final int n) {
        final Intent putExtra = new Intent(GenerateNotification.currentContext, (Class)GenerateNotification.notificationOpenedClass).putExtra("notificationId", n);
        if (GenerateNotification.openerIsBroadcast) {
            return putExtra;
        }
        return putExtra.addFlags(603979776);
    }
    
    private static int getResourceIcon(final String s) {
        int drawableId;
        if (s == null) {
            drawableId = 0;
        }
        else {
            final String trim = s.trim();
            if (!OSUtils.isValidResourceName(trim)) {
                return 0;
            }
            if ((drawableId = getDrawableId(trim)) == 0) {
                try {
                    return R$drawable.class.getField(s).getInt(null);
                }
                catch (Throwable t) {
                    return 0;
                }
            }
        }
        return drawableId;
    }
    
    private static int getSmallIconId(final JSONObject jsonObject) {
        final int resourceIcon = getResourceIcon(jsonObject.optString("sicon", (String)null));
        if (resourceIcon != 0) {
            return resourceIcon;
        }
        return getDefaultSmallIconId();
    }
    
    private static CharSequence getTitle(final JSONObject jsonObject) {
        final String optString = jsonObject.optString("title", (String)null);
        if (optString != null) {
            return optString;
        }
        return GenerateNotification.currentContext.getPackageManager().getApplicationLabel(GenerateNotification.currentContext.getApplicationInfo());
    }
    
    private static boolean isSoundEnabled(final JSONObject jsonObject) {
        final String optString = jsonObject.optString("sound", (String)null);
        return !"null".equals(optString) && !"nil".equals(optString) && OneSignal.getSoundEnabled(GenerateNotification.currentContext);
    }
    
    private static void removeNotifyOptions(final NotificationCompat$Builder notificationCompat$Builder) {
        notificationCompat$Builder.setOnlyAlertOnce(true).setDefaults(0).setSound((Uri)null).setVibrate((long[])null).setTicker((CharSequence)null);
    }
    
    private static Bitmap resizeBitmapForLargeIconArea(final Bitmap bitmap) {
        Bitmap bitmap2;
        if (bitmap == null) {
            bitmap2 = null;
        }
        else {
            try {
                int n = (int)GenerateNotification.contextResources.getDimension(17104902);
                final int n2 = (int)GenerateNotification.contextResources.getDimension(17104901);
                final int height = bitmap.getHeight();
                final int width = bitmap.getWidth();
                if (width <= n2) {
                    bitmap2 = bitmap;
                    if (height <= n) {
                        return bitmap2;
                    }
                }
                int n3;
                if (height > width) {
                    n3 = (int)(n * (width / (float)height));
                }
                else {
                    n3 = n2;
                    if (width > height) {
                        n = (int)(n2 * (height / (float)width));
                        n3 = n2;
                    }
                }
                return Bitmap.createScaledBitmap(bitmap, n3, n, true);
            }
            catch (Throwable t) {
                return bitmap;
            }
        }
        return bitmap2;
    }
    
    private static Integer safeGetColorFromHex(final JSONObject jsonObject, final String s) {
        if (jsonObject != null) {
            try {
                if (jsonObject.has(s)) {
                    return new BigInteger(jsonObject.optString(s), 16).intValue();
                }
            }
            catch (Throwable t) {}
        }
        return null;
    }
    
    private static void setAlertnessOptions(JSONObject soundUri, final NotificationCompat$Builder notificationCompat$Builder) {
        final int convertOSToAndroidPriority = convertOSToAndroidPriority(soundUri.optInt("pri", 6));
        notificationCompat$Builder.setPriority(convertOSToAndroidPriority);
        int n;
        if (convertOSToAndroidPriority < 0) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (n != 0) {
            return;
        }
        int n2 = 0;
    Label_0089:
        while (true) {
            Label_0198: {
                if (!soundUri.has("ledc") || soundUri.optInt("led", 1) != 1) {
                    break Label_0198;
                }
            Label_0182_Outer:
                while (true) {
                    while (true) {
                        int n3 = 0;
                    Label_0212:
                        while (true) {
                            Label_0205: {
                                try {
                                    notificationCompat$Builder.setLights(new BigInteger(soundUri.optString("ledc"), 16).intValue(), 2000, 5000);
                                    n3 = n2;
                                    if (OneSignal.getVibrate(GenerateNotification.currentContext)) {
                                        n3 = n2;
                                        if (soundUri.optInt("vib", 1) == 1) {
                                            if (!soundUri.has("vib_pt")) {
                                                break Label_0205;
                                            }
                                            final long[] vibrationPattern = OSUtils.parseVibrationPattern(soundUri);
                                            n3 = n2;
                                            if (vibrationPattern != null) {
                                                notificationCompat$Builder.setVibrate(vibrationPattern);
                                                n3 = n2;
                                            }
                                        }
                                    }
                                    int defaults = n3;
                                    if (isSoundEnabled(soundUri)) {
                                        soundUri = (JSONObject)OSUtils.getSoundUri(GenerateNotification.currentContext, soundUri.optString("sound", (String)null));
                                        if (soundUri == null) {
                                            break Label_0212;
                                        }
                                        notificationCompat$Builder.setSound((Uri)soundUri);
                                        defaults = n3;
                                    }
                                    notificationCompat$Builder.setDefaults(defaults);
                                    return;
                                }
                                catch (Throwable t) {
                                    n2 = (0x0 | 0x4);
                                    continue Label_0089;
                                }
                                break;
                            }
                            n3 = (n2 | 0x2);
                            continue Label_0182_Outer;
                        }
                        int defaults = n3 | 0x1;
                        continue;
                    }
                }
            }
            n2 = (0x0 | 0x4);
            continue Label_0089;
        }
    }
    
    private static void setStatics(final Context currentContext) {
        GenerateNotification.currentContext = currentContext;
        GenerateNotification.packageName = GenerateNotification.currentContext.getPackageName();
        GenerateNotification.contextResources = GenerateNotification.currentContext.getResources();
        final PackageManager packageManager = GenerateNotification.currentContext.getPackageManager();
        final Intent intent = new Intent(GenerateNotification.currentContext, (Class)NotificationOpenedReceiver.class);
        intent.setPackage(GenerateNotification.currentContext.getPackageName());
        if (packageManager.queryBroadcastReceivers(intent, 0).size() > 0) {
            GenerateNotification.openerIsBroadcast = true;
            GenerateNotification.notificationOpenedClass = NotificationOpenedReceiver.class;
            return;
        }
        GenerateNotification.notificationOpenedClass = NotificationOpenedActivity.class;
    }
    
    private static void setTextColor(final RemoteViews remoteViews, final JSONObject jsonObject, final int n, final String s, final String s2) {
        final Integer safeGetColorFromHex = safeGetColorFromHex(jsonObject, s);
        if (safeGetColorFromHex != null) {
            remoteViews.setTextColor(n, (int)safeGetColorFromHex);
        }
        else {
            final int identifier = GenerateNotification.contextResources.getIdentifier(s2, "color", GenerateNotification.packageName);
            if (identifier != 0) {
                remoteViews.setTextColor(n, AndroidSupportV4Compat.ContextCompat.getColor(GenerateNotification.currentContext, identifier));
            }
        }
    }
    
    private static void showNotification(final NotificationGenerationJob p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: invokespecial   java/security/SecureRandom.<init>:()V
        //     7: astore          5
        //     9: aload_0        
        //    10: invokevirtual   com/onesignal/NotificationGenerationJob.getAndroidId:()Ljava/lang/Integer;
        //    13: invokevirtual   java/lang/Integer.intValue:()I
        //    16: istore_1       
        //    17: aload_0        
        //    18: getfield        com/onesignal/NotificationGenerationJob.jsonPayload:Lorg/json/JSONObject;
        //    21: astore          6
        //    23: aload           6
        //    25: ldc_w           "grp"
        //    28: aconst_null    
        //    29: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    32: astore_3       
        //    33: aload_0        
        //    34: invokestatic    com/onesignal/GenerateNotification.getBaseOneSignalNotificationBuilder:(Lcom/onesignal/NotificationGenerationJob;)Lcom/onesignal/GenerateNotification$OneSignalNotificationBuilder;
        //    37: astore          4
        //    39: aload           4
        //    41: getfield        com/onesignal/GenerateNotification$OneSignalNotificationBuilder.compatBuilder:Landroid/support/v4/app/NotificationCompat$Builder;
        //    44: astore_2       
        //    45: aload           6
        //    47: aload_2        
        //    48: iload_1        
        //    49: aconst_null    
        //    50: invokestatic    com/onesignal/GenerateNotification.addNotificationActionButtons:(Lorg/json/JSONObject;Landroid/support/v4/app/NotificationCompat$Builder;ILjava/lang/String;)V
        //    53: aload           6
        //    55: aload_2        
        //    56: invokestatic    com/onesignal/GenerateNotification.addBackgroundImage:(Lorg/json/JSONObject;Landroid/support/v4/app/NotificationCompat$Builder;)V
        //    59: aload_0        
        //    60: aload_2        
        //    61: invokestatic    com/onesignal/GenerateNotification.applyNotificationExtender:(Lcom/onesignal/NotificationGenerationJob;Landroid/support/v4/app/NotificationCompat$Builder;)V
        //    64: aload_0        
        //    65: getfield        com/onesignal/NotificationGenerationJob.restoring:Z
        //    68: ifeq            75
        //    71: aload_2        
        //    72: invokestatic    com/onesignal/GenerateNotification.removeNotifyOptions:(Landroid/support/v4/app/NotificationCompat$Builder;)V
        //    75: aload_3        
        //    76: ifnull          210
        //    79: aload_2        
        //    80: aload           5
        //    82: invokevirtual   java/security/SecureRandom.nextInt:()I
        //    85: iload_1        
        //    86: invokestatic    com/onesignal/GenerateNotification.getNewBaseIntent:(I)Landroid/content/Intent;
        //    89: ldc_w           "onesignal_data"
        //    92: aload           6
        //    94: invokevirtual   org/json/JSONObject.toString:()Ljava/lang/String;
        //    97: invokevirtual   android/content/Intent.putExtra:(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
        //   100: ldc_w           "grp"
        //   103: aload_3        
        //   104: invokevirtual   android/content/Intent.putExtra:(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
        //   107: invokestatic    com/onesignal/GenerateNotification.getNewActionPendingIntent:(ILandroid/content/Intent;)Landroid/app/PendingIntent;
        //   110: invokevirtual   android/support/v4/app/NotificationCompat$Builder.setContentIntent:(Landroid/app/PendingIntent;)Landroid/support/v4/app/NotificationCompat$Builder;
        //   113: pop            
        //   114: aload_2        
        //   115: aload           5
        //   117: invokevirtual   java/security/SecureRandom.nextInt:()I
        //   120: iload_1        
        //   121: invokestatic    com/onesignal/GenerateNotification.getNewBaseDeleteIntent:(I)Landroid/content/Intent;
        //   124: ldc_w           "grp"
        //   127: aload_3        
        //   128: invokevirtual   android/content/Intent.putExtra:(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
        //   131: invokestatic    com/onesignal/GenerateNotification.getNewActionPendingIntent:(ILandroid/content/Intent;)Landroid/app/PendingIntent;
        //   134: invokevirtual   android/support/v4/app/NotificationCompat$Builder.setDeleteIntent:(Landroid/app/PendingIntent;)Landroid/support/v4/app/NotificationCompat$Builder;
        //   137: pop            
        //   138: aload_2        
        //   139: aload_3        
        //   140: invokevirtual   android/support/v4/app/NotificationCompat$Builder.setGroup:(Ljava/lang/String;)Landroid/support/v4/app/NotificationCompat$Builder;
        //   143: pop            
        //   144: aload_2        
        //   145: iconst_1       
        //   146: invokevirtual   android/support/v4/app/NotificationCompat$Builder.setGroupAlertBehavior:(I)Landroid/support/v4/app/NotificationCompat$Builder;
        //   149: pop            
        //   150: aload_0        
        //   151: aload_2        
        //   152: invokestatic    com/onesignal/GenerateNotification.createSingleNotificationBeforeSummaryBuilder:(Lcom/onesignal/NotificationGenerationJob;Landroid/support/v4/app/NotificationCompat$Builder;)Landroid/app/Notification;
        //   155: astore_2       
        //   156: aload_0        
        //   157: aload           4
        //   159: invokestatic    com/onesignal/GenerateNotification.createSummaryNotification:(Lcom/onesignal/NotificationGenerationJob;Lcom/onesignal/GenerateNotification$OneSignalNotificationBuilder;)V
        //   162: aload_2        
        //   163: astore_0       
        //   164: aload_3        
        //   165: ifnull          176
        //   168: getstatic       android/os/Build$VERSION.SDK_INT:I
        //   171: bipush          17
        //   173: if_icmple       193
        //   176: aload           4
        //   178: aload_0        
        //   179: invokestatic    com/onesignal/GenerateNotification.addXiaomiSettings:(Lcom/onesignal/GenerateNotification$OneSignalNotificationBuilder;Landroid/app/Notification;)V
        //   182: getstatic       com/onesignal/GenerateNotification.currentContext:Landroid/content/Context;
        //   185: invokestatic    android/support/v4/app/NotificationManagerCompat.from:(Landroid/content/Context;)Landroid/support/v4/app/NotificationManagerCompat;
        //   188: iload_1        
        //   189: aload_0        
        //   190: invokevirtual   android/support/v4/app/NotificationManagerCompat.notify:(ILandroid/app/Notification;)V
        //   193: return         
        //   194: astore          7
        //   196: getstatic       com/onesignal/OneSignal$LOG_LEVEL.ERROR:Lcom/onesignal/OneSignal$LOG_LEVEL;
        //   199: ldc_w           "Could not set background notification image!"
        //   202: aload           7
        //   204: invokestatic    com/onesignal/OneSignal.Log:(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   207: goto            59
        //   210: aload_2        
        //   211: aload           5
        //   213: invokevirtual   java/security/SecureRandom.nextInt:()I
        //   216: iload_1        
        //   217: invokestatic    com/onesignal/GenerateNotification.getNewBaseIntent:(I)Landroid/content/Intent;
        //   220: ldc_w           "onesignal_data"
        //   223: aload           6
        //   225: invokevirtual   org/json/JSONObject.toString:()Ljava/lang/String;
        //   228: invokevirtual   android/content/Intent.putExtra:(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
        //   231: invokestatic    com/onesignal/GenerateNotification.getNewActionPendingIntent:(ILandroid/content/Intent;)Landroid/app/PendingIntent;
        //   234: invokevirtual   android/support/v4/app/NotificationCompat$Builder.setContentIntent:(Landroid/app/PendingIntent;)Landroid/support/v4/app/NotificationCompat$Builder;
        //   237: pop            
        //   238: aload_2        
        //   239: aload           5
        //   241: invokevirtual   java/security/SecureRandom.nextInt:()I
        //   244: iload_1        
        //   245: invokestatic    com/onesignal/GenerateNotification.getNewBaseDeleteIntent:(I)Landroid/content/Intent;
        //   248: invokestatic    com/onesignal/GenerateNotification.getNewActionPendingIntent:(ILandroid/content/Intent;)Landroid/app/PendingIntent;
        //   251: invokevirtual   android/support/v4/app/NotificationCompat$Builder.setDeleteIntent:(Landroid/app/PendingIntent;)Landroid/support/v4/app/NotificationCompat$Builder;
        //   254: pop            
        //   255: aload_2        
        //   256: invokevirtual   android/support/v4/app/NotificationCompat$Builder.build:()Landroid/app/Notification;
        //   259: astore_0       
        //   260: goto            164
        //   263: astore          5
        //   265: goto            150
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  53     59     194    210    Ljava/lang/Throwable;
        //  144    150    263    268    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0150:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private static void showNotificationAsAlert(final JSONObject jsonObject, final Activity activity, final int n) {
        activity.runOnUiThread((Runnable)new Runnable() {
            @Override
            public void run() {
                final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)activity);
                alertDialog$Builder.setTitle(getTitle(jsonObject));
                alertDialog$Builder.setMessage((CharSequence)jsonObject.optString("alert"));
                final ArrayList<CharSequence> list = new ArrayList<CharSequence>();
                final ArrayList list2 = new ArrayList();
                addAlertButtons((Context)activity, jsonObject, list, list2);
                final Intent access$200 = getNewBaseIntent(n);
                access$200.putExtra("action_button", true);
                access$200.putExtra("from_alert", true);
                access$200.putExtra("onesignal_data", jsonObject.toString());
                if (jsonObject.has("grp")) {
                    access$200.putExtra("grp", jsonObject.optString("grp"));
                }
                final DialogInterface$OnClickListener dialogInterface$OnClickListener = (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                    public void onClick(final DialogInterface dialogInterface, final int n) {
                        Label_0079: {
                            if (list2.size() <= 1) {
                                break Label_0079;
                            }
                            try {
                                final JSONObject jsonObject = new JSONObject(jsonObject.toString());
                                jsonObject.put("actionSelected", list2.get(n + 3));
                                access$200.putExtra("onesignal_data", jsonObject.toString());
                                NotificationOpenedProcessor.processIntent((Context)activity, access$200);
                                return;
                                NotificationOpenedProcessor.processIntent((Context)activity, access$200);
                            }
                            catch (Throwable t) {}
                        }
                    }
                };
                alertDialog$Builder.setOnCancelListener((DialogInterface$OnCancelListener)new DialogInterface$OnCancelListener() {
                    public void onCancel(final DialogInterface dialogInterface) {
                        NotificationOpenedProcessor.processIntent((Context)activity, access$200);
                    }
                });
                for (int i = 0; i < list.size(); ++i) {
                    if (i == 0) {
                        alertDialog$Builder.setNeutralButton((CharSequence)list.get(i), (DialogInterface$OnClickListener)dialogInterface$OnClickListener);
                    }
                    else if (i == 1) {
                        alertDialog$Builder.setNegativeButton((CharSequence)list.get(i), (DialogInterface$OnClickListener)dialogInterface$OnClickListener);
                    }
                    else if (i == 2) {
                        alertDialog$Builder.setPositiveButton((CharSequence)list.get(i), (DialogInterface$OnClickListener)dialogInterface$OnClickListener);
                    }
                }
                final AlertDialog create = alertDialog$Builder.create();
                create.setCanceledOnTouchOutside(false);
                create.show();
            }
        });
    }
    
    static void updateSummaryNotification(final NotificationGenerationJob notificationGenerationJob) {
        setStatics(notificationGenerationJob.context);
        createSummaryNotification(notificationGenerationJob, null);
    }
    
    private static class OneSignalNotificationBuilder
    {
        NotificationCompat$Builder compatBuilder;
        boolean hasLargeIcon;
    }
}
