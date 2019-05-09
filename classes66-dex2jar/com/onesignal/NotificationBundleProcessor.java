// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import android.content.Intent;
import android.os.Build$VERSION;
import java.util.ArrayList;
import android.database.Cursor;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import java.util.Iterator;
import org.json.JSONArray;
import android.os.Bundle;
import org.json.JSONException;
import android.content.Context;
import org.json.JSONObject;

class NotificationBundleProcessor
{
    static final String DEFAULT_ACTION = "__DEFAULT__";
    
    static OSNotificationPayload OSNotificationPayloadFrom(final JSONObject p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: invokespecial   com/onesignal/OSNotificationPayload.<init>:()V
        //     7: astore_1       
        //     8: new             Lorg/json/JSONObject;
        //    11: dup            
        //    12: aload_0        
        //    13: ldc             "custom"
        //    15: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;)Ljava/lang/String;
        //    18: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //    21: astore_2       
        //    22: aload_1        
        //    23: aload_2        
        //    24: ldc             "i"
        //    26: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;)Ljava/lang/String;
        //    29: putfield        com/onesignal/OSNotificationPayload.notificationID:Ljava/lang/String;
        //    32: aload_1        
        //    33: aload_2        
        //    34: ldc             "ti"
        //    36: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;)Ljava/lang/String;
        //    39: putfield        com/onesignal/OSNotificationPayload.templateId:Ljava/lang/String;
        //    42: aload_1        
        //    43: aload_2        
        //    44: ldc             "tn"
        //    46: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;)Ljava/lang/String;
        //    49: putfield        com/onesignal/OSNotificationPayload.templateName:Ljava/lang/String;
        //    52: aload_1        
        //    53: aload_0        
        //    54: invokevirtual   org/json/JSONObject.toString:()Ljava/lang/String;
        //    57: putfield        com/onesignal/OSNotificationPayload.rawPayload:Ljava/lang/String;
        //    60: aload_1        
        //    61: aload_2        
        //    62: ldc             "a"
        //    64: invokevirtual   org/json/JSONObject.optJSONObject:(Ljava/lang/String;)Lorg/json/JSONObject;
        //    67: putfield        com/onesignal/OSNotificationPayload.additionalData:Lorg/json/JSONObject;
        //    70: aload_1        
        //    71: aload_2        
        //    72: ldc             "u"
        //    74: aconst_null    
        //    75: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    78: putfield        com/onesignal/OSNotificationPayload.launchURL:Ljava/lang/String;
        //    81: aload_1        
        //    82: aload_0        
        //    83: ldc             "alert"
        //    85: aconst_null    
        //    86: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    89: putfield        com/onesignal/OSNotificationPayload.body:Ljava/lang/String;
        //    92: aload_1        
        //    93: aload_0        
        //    94: ldc             "title"
        //    96: aconst_null    
        //    97: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   100: putfield        com/onesignal/OSNotificationPayload.title:Ljava/lang/String;
        //   103: aload_1        
        //   104: aload_0        
        //   105: ldc             "sicon"
        //   107: aconst_null    
        //   108: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   111: putfield        com/onesignal/OSNotificationPayload.smallIcon:Ljava/lang/String;
        //   114: aload_1        
        //   115: aload_0        
        //   116: ldc             "bicon"
        //   118: aconst_null    
        //   119: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   122: putfield        com/onesignal/OSNotificationPayload.bigPicture:Ljava/lang/String;
        //   125: aload_1        
        //   126: aload_0        
        //   127: ldc             "licon"
        //   129: aconst_null    
        //   130: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   133: putfield        com/onesignal/OSNotificationPayload.largeIcon:Ljava/lang/String;
        //   136: aload_1        
        //   137: aload_0        
        //   138: ldc             "sound"
        //   140: aconst_null    
        //   141: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   144: putfield        com/onesignal/OSNotificationPayload.sound:Ljava/lang/String;
        //   147: aload_1        
        //   148: aload_0        
        //   149: ldc             "grp"
        //   151: aconst_null    
        //   152: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   155: putfield        com/onesignal/OSNotificationPayload.groupKey:Ljava/lang/String;
        //   158: aload_1        
        //   159: aload_0        
        //   160: ldc             "grp_msg"
        //   162: aconst_null    
        //   163: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   166: putfield        com/onesignal/OSNotificationPayload.groupMessage:Ljava/lang/String;
        //   169: aload_1        
        //   170: aload_0        
        //   171: ldc             "bgac"
        //   173: aconst_null    
        //   174: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   177: putfield        com/onesignal/OSNotificationPayload.smallIconAccentColor:Ljava/lang/String;
        //   180: aload_1        
        //   181: aload_0        
        //   182: ldc             "ledc"
        //   184: aconst_null    
        //   185: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   188: putfield        com/onesignal/OSNotificationPayload.ledColor:Ljava/lang/String;
        //   191: aload_0        
        //   192: ldc             "vis"
        //   194: aconst_null    
        //   195: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   198: astore_2       
        //   199: aload_2        
        //   200: ifnull          211
        //   203: aload_1        
        //   204: aload_2        
        //   205: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //   208: putfield        com/onesignal/OSNotificationPayload.lockScreenVisibility:I
        //   211: aload_1        
        //   212: aload_0        
        //   213: ldc             "from"
        //   215: aconst_null    
        //   216: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   219: putfield        com/onesignal/OSNotificationPayload.fromProjectNumber:Ljava/lang/String;
        //   222: aload_1        
        //   223: aload_0        
        //   224: ldc             "pri"
        //   226: iconst_0       
        //   227: invokevirtual   org/json/JSONObject.optInt:(Ljava/lang/String;I)I
        //   230: putfield        com/onesignal/OSNotificationPayload.priority:I
        //   233: aload_0        
        //   234: ldc             "collapse_key"
        //   236: aconst_null    
        //   237: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   240: astore_2       
        //   241: ldc             "do_not_collapse"
        //   243: aload_2        
        //   244: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   247: ifne            255
        //   250: aload_1        
        //   251: aload_2        
        //   252: putfield        com/onesignal/OSNotificationPayload.collapseId:Ljava/lang/String;
        //   255: aload_1        
        //   256: invokestatic    com/onesignal/NotificationBundleProcessor.setActionButtons:(Lcom/onesignal/OSNotificationPayload;)V
        //   259: aload_1        
        //   260: aload_0        
        //   261: invokestatic    com/onesignal/NotificationBundleProcessor.setBackgroundImageLayout:(Lcom/onesignal/OSNotificationPayload;Lorg/json/JSONObject;)V
        //   264: aload_1        
        //   265: areturn        
        //   266: astore_2       
        //   267: getstatic       com/onesignal/OneSignal$LOG_LEVEL.ERROR:Lcom/onesignal/OneSignal$LOG_LEVEL;
        //   270: ldc             "Error assigning OSNotificationPayload.actionButtons values!"
        //   272: aload_2        
        //   273: invokestatic    com/onesignal/OneSignal.Log:(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   276: goto            259
        //   279: astore_0       
        //   280: getstatic       com/onesignal/OneSignal$LOG_LEVEL.ERROR:Lcom/onesignal/OneSignal$LOG_LEVEL;
        //   283: ldc             "Error assigning OSNotificationPayload values!"
        //   285: aload_0        
        //   286: invokestatic    com/onesignal/OneSignal.Log:(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   289: aload_1        
        //   290: areturn        
        //   291: astore_0       
        //   292: getstatic       com/onesignal/OneSignal$LOG_LEVEL.ERROR:Lcom/onesignal/OneSignal$LOG_LEVEL;
        //   295: ldc             "Error assigning OSNotificationPayload.backgroundImageLayout values!"
        //   297: aload_0        
        //   298: invokestatic    com/onesignal/OneSignal.Log:(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   301: aload_1        
        //   302: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  8      199    279    291    Ljava/lang/Throwable;
        //  203    211    279    291    Ljava/lang/Throwable;
        //  211    255    279    291    Ljava/lang/Throwable;
        //  255    259    266    279    Ljava/lang/Throwable;
        //  259    264    291    303    Ljava/lang/Throwable;
        //  267    276    279    291    Ljava/lang/Throwable;
        //  292    301    279    291    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0259:
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
    
    static void ProcessFromGCMIntentService(final Context appContext, final BundleCompat bundleCompat, final NotificationExtenderService.OverrideSettings overrideSettings) {
        OneSignal.setAppContext(appContext);
        try {
            final String string = bundleCompat.getString("json_payload");
            if (string == null) {
                OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "json_payload key is nonexistent from mBundle passed to ProcessFromGCMIntentService: " + bundleCompat);
                return;
            }
            final NotificationGenerationJob notificationGenerationJob = new NotificationGenerationJob(appContext);
            notificationGenerationJob.restoring = bundleCompat.getBoolean("restoring", false);
            notificationGenerationJob.shownTimeStamp = bundleCompat.getLong("timestamp");
            notificationGenerationJob.jsonPayload = new JSONObject(string);
            if (notificationGenerationJob.restoring || !OneSignal.notValidOrDuplicated(appContext, notificationGenerationJob.jsonPayload)) {
                NotificationExtenderService.OverrideSettings overrideSettings2 = overrideSettings;
                if (bundleCompat.containsKey("android_notif_id")) {
                    if ((overrideSettings2 = overrideSettings) == null) {
                        overrideSettings2 = new NotificationExtenderService.OverrideSettings();
                    }
                    overrideSettings2.androidNotificationId = bundleCompat.getInt("android_notif_id");
                }
                notificationGenerationJob.overrideSettings = overrideSettings2;
                ProcessJobForDisplay(notificationGenerationJob);
                if (notificationGenerationJob.restoring) {
                    OSUtils.sleep(100);
                }
            }
        }
        catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
    
    static int ProcessJobForDisplay(final NotificationGenerationJob notificationGenerationJob) {
        Label_0114: {
            if (!OneSignal.getInAppAlertNotificationEnabled() || !OneSignal.isAppActive()) {
                break Label_0114;
            }
            boolean showAsAlert = true;
        Label_0047_Outer:
            while (true) {
                notificationGenerationJob.showAsAlert = showAsAlert;
                processCollapseKey(notificationGenerationJob);
                Label_0119: {
                    if (!notificationGenerationJob.hasExtender() && !shouldDisplay(notificationGenerationJob.jsonPayload.optString("alert"))) {
                        break Label_0119;
                    }
                    int n = 1;
                    while (true) {
                        if (n != 0) {
                            GenerateNotification.fromJsonPayload(notificationGenerationJob);
                        }
                        Label_0106: {
                            if (notificationGenerationJob.restoring) {
                                break Label_0106;
                            }
                            saveNotification(notificationGenerationJob, false);
                            try {
                                final JSONObject jsonObject = new JSONObject(notificationGenerationJob.jsonPayload.toString());
                                jsonObject.put("notificationId", (Object)notificationGenerationJob.getAndroidId());
                                OneSignal.handleNotificationReceived(newJsonArray(jsonObject), true, notificationGenerationJob.showAsAlert);
                                return notificationGenerationJob.getAndroidId();
                                showAsAlert = false;
                                continue Label_0047_Outer;
                                n = 0;
                                continue;
                            }
                            catch (Throwable t) {
                                return notificationGenerationJob.getAndroidId();
                            }
                        }
                        break;
                    }
                }
                break;
            }
        }
    }
    
    static JSONObject bundleAsJSONObject(final Bundle bundle) {
        final JSONObject jsonObject = new JSONObject();
        for (final String s : bundle.keySet()) {
            try {
                jsonObject.put(s, bundle.get(s));
            }
            catch (JSONException ex) {
                OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "bundleAsJSONObject error for key: " + s, (Throwable)ex);
            }
        }
        return jsonObject;
    }
    
    private static JSONArray bundleAsJsonArray(final Bundle bundle) {
        final JSONArray jsonArray = new JSONArray();
        jsonArray.put((Object)bundleAsJSONObject(bundle));
        return jsonArray;
    }
    
    static void deleteOldNotifications(final SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.delete("notification", "created_time < " + (System.currentTimeMillis() / 1000L - 604800L), (String[])null);
    }
    
    static boolean hasRemoteResource(final Bundle bundle) {
        return isBuildKeyRemote(bundle, "licon") || isBuildKeyRemote(bundle, "bicon") || bundle.getString("bg_img", (String)null) != null;
    }
    
    private static boolean isBuildKeyRemote(final Bundle bundle, final String s) {
        final String trim = bundle.getString(s, "").trim();
        return trim.startsWith("http://") || trim.startsWith("https://");
    }
    
    static void markRestoredNotificationAsDismissed(final NotificationGenerationJob notificationGenerationJob) {
        if (notificationGenerationJob.getAndroidIdWithoutCreate() != -1) {
            final String string = "android_notification_id = " + notificationGenerationJob.getAndroidIdWithoutCreate();
            final OneSignalDbHelper instance = OneSignalDbHelper.getInstance(notificationGenerationJob.context);
            SQLiteDatabase sqLiteDatabase = null;
            SQLiteDatabase writableDbWithRetries = null;
            try {
                final SQLiteDatabase sqLiteDatabase2 = sqLiteDatabase = (writableDbWithRetries = instance.getWritableDbWithRetries());
                sqLiteDatabase2.beginTransaction();
                writableDbWithRetries = sqLiteDatabase2;
                sqLiteDatabase = sqLiteDatabase2;
                final ContentValues contentValues = new ContentValues();
                writableDbWithRetries = sqLiteDatabase2;
                sqLiteDatabase = sqLiteDatabase2;
                contentValues.put("dismissed", Integer.valueOf(1));
                writableDbWithRetries = sqLiteDatabase2;
                sqLiteDatabase = sqLiteDatabase2;
                sqLiteDatabase2.update("notification", contentValues, string, (String[])null);
                writableDbWithRetries = sqLiteDatabase2;
                sqLiteDatabase = sqLiteDatabase2;
                BadgeCountUpdater.update(sqLiteDatabase2, notificationGenerationJob.context);
                writableDbWithRetries = sqLiteDatabase2;
                sqLiteDatabase = sqLiteDatabase2;
                sqLiteDatabase2.setTransactionSuccessful();
                if (sqLiteDatabase2 != null) {
                    try {
                        sqLiteDatabase2.endTransaction();
                    }
                    catch (Throwable t) {
                        OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error closing transaction! ", t);
                    }
                }
            }
            catch (Exception ex) {
                sqLiteDatabase = writableDbWithRetries;
                OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error saving notification record! ", ex);
                if (writableDbWithRetries != null) {
                    try {
                        writableDbWithRetries.endTransaction();
                    }
                    catch (Throwable t2) {
                        OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error closing transaction! ", t2);
                    }
                }
            }
            finally {
                Label_0189: {
                    if (sqLiteDatabase == null) {
                        break Label_0189;
                    }
                    try {
                        sqLiteDatabase.endTransaction();
                    }
                    catch (Throwable t3) {
                        OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error closing transaction! ", t3);
                    }
                }
            }
        }
    }
    
    static JSONArray newJsonArray(final JSONObject jsonObject) {
        final JSONArray jsonArray = new JSONArray();
        jsonArray.put((Object)jsonObject);
        return jsonArray;
    }
    
    static ProcessedBundleResult processBundleFromReceiver(final Context context, final Bundle bundle) {
        final ProcessedBundleResult processedBundleResult = new ProcessedBundleResult();
        if (OneSignal.getNotificationIdFromGCMBundle(bundle) != null) {
            processedBundleResult.isOneSignalPayload = true;
            unMinifyBundle(bundle);
            if (!startExtenderService(context, bundle, processedBundleResult)) {
                processedBundleResult.isDup = OneSignal.notValidOrDuplicated(context, bundleAsJSONObject(bundle));
                if (!processedBundleResult.isDup && !shouldDisplay(bundle.getString("alert"))) {
                    saveNotification(context, bundle, true, -1);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            OneSignal.handleNotificationReceived(bundleAsJsonArray(bundle), false, false);
                        }
                    }, "OS_PROC_BUNDLE").start();
                    return processedBundleResult;
                }
            }
        }
        return processedBundleResult;
    }
    
    private static void processCollapseKey(final NotificationGenerationJob notificationGenerationJob) {
        if (notificationGenerationJob.restoring || !notificationGenerationJob.jsonPayload.has("collapse_key") || "do_not_collapse".equals(notificationGenerationJob.jsonPayload.optString("collapse_key"))) {
            return;
        }
        final String optString = notificationGenerationJob.jsonPayload.optString("collapse_key");
        final OneSignalDbHelper instance = OneSignalDbHelper.getInstance(notificationGenerationJob.context);
        Cursor cursor = null;
        Cursor query = null;
        try {
            final Cursor cursor2 = cursor = (query = instance.getReadableDbWithRetries().query("notification", new String[] { "android_notification_id" }, "collapse_id = ? AND dismissed = 0 AND opened = 0 ", new String[] { optString }, (String)null, (String)null, (String)null));
            if (cursor2.moveToFirst()) {
                query = cursor2;
                cursor = cursor2;
                notificationGenerationJob.setAndroidIdWithOutOverriding(cursor2.getInt(cursor2.getColumnIndex("android_notification_id")));
            }
        }
        catch (Throwable t) {
            cursor = query;
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Could not read DB to find existing collapse_key!", t);
        }
        finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
    }
    
    private static void saveNotification(final Context context, final Bundle bundle, final boolean b, final int n) {
        final NotificationGenerationJob notificationGenerationJob = new NotificationGenerationJob(context);
        notificationGenerationJob.jsonPayload = bundleAsJSONObject(bundle);
        notificationGenerationJob.overrideSettings = new NotificationExtenderService.OverrideSettings();
        notificationGenerationJob.overrideSettings.androidNotificationId = n;
        saveNotification(notificationGenerationJob, b);
    }
    
    static void saveNotification(final NotificationGenerationJob p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: istore_2       
        //     2: aload_0        
        //     3: getfield        com/onesignal/NotificationGenerationJob.context:Landroid/content/Context;
        //     6: astore          6
        //     8: aload_0        
        //     9: getfield        com/onesignal/NotificationGenerationJob.jsonPayload:Lorg/json/JSONObject;
        //    12: astore          7
        //    14: new             Lorg/json/JSONObject;
        //    17: dup            
        //    18: aload_0        
        //    19: getfield        com/onesignal/NotificationGenerationJob.jsonPayload:Lorg/json/JSONObject;
        //    22: ldc             "custom"
        //    24: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;)Ljava/lang/String;
        //    27: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //    30: astore          8
        //    32: aload_0        
        //    33: getfield        com/onesignal/NotificationGenerationJob.context:Landroid/content/Context;
        //    36: invokestatic    com/onesignal/OneSignalDbHelper.getInstance:(Landroid/content/Context;)Lcom/onesignal/OneSignalDbHelper;
        //    39: astore          5
        //    41: aconst_null    
        //    42: astore          4
        //    44: aconst_null    
        //    45: astore_3       
        //    46: aload           5
        //    48: invokevirtual   com/onesignal/OneSignalDbHelper.getWritableDbWithRetries:()Landroid/database/sqlite/SQLiteDatabase;
        //    51: astore          5
        //    53: aload           5
        //    55: astore_3       
        //    56: aload           5
        //    58: astore          4
        //    60: aload           5
        //    62: invokevirtual   android/database/sqlite/SQLiteDatabase.beginTransaction:()V
        //    65: aload           5
        //    67: astore_3       
        //    68: aload           5
        //    70: astore          4
        //    72: aload           5
        //    74: invokestatic    com/onesignal/NotificationBundleProcessor.deleteOldNotifications:(Landroid/database/sqlite/SQLiteDatabase;)V
        //    77: aload           5
        //    79: astore_3       
        //    80: aload           5
        //    82: astore          4
        //    84: aload_0        
        //    85: invokevirtual   com/onesignal/NotificationGenerationJob.getAndroidIdWithoutCreate:()I
        //    88: iconst_m1      
        //    89: if_icmpeq       194
        //    92: aload           5
        //    94: astore_3       
        //    95: aload           5
        //    97: astore          4
        //    99: new             Ljava/lang/StringBuilder;
        //   102: dup            
        //   103: invokespecial   java/lang/StringBuilder.<init>:()V
        //   106: ldc_w           "android_notification_id = "
        //   109: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   112: aload_0        
        //   113: invokevirtual   com/onesignal/NotificationGenerationJob.getAndroidIdWithoutCreate:()I
        //   116: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   119: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   122: astore          9
        //   124: aload           5
        //   126: astore_3       
        //   127: aload           5
        //   129: astore          4
        //   131: new             Landroid/content/ContentValues;
        //   134: dup            
        //   135: invokespecial   android/content/ContentValues.<init>:()V
        //   138: astore          10
        //   140: aload           5
        //   142: astore_3       
        //   143: aload           5
        //   145: astore          4
        //   147: aload           10
        //   149: ldc_w           "dismissed"
        //   152: iconst_1       
        //   153: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   156: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/Integer;)V
        //   159: aload           5
        //   161: astore_3       
        //   162: aload           5
        //   164: astore          4
        //   166: aload           5
        //   168: ldc_w           "notification"
        //   171: aload           10
        //   173: aload           9
        //   175: aconst_null    
        //   176: invokevirtual   android/database/sqlite/SQLiteDatabase.update:(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
        //   179: pop            
        //   180: aload           5
        //   182: astore_3       
        //   183: aload           5
        //   185: astore          4
        //   187: aload           5
        //   189: aload           6
        //   191: invokestatic    com/onesignal/BadgeCountUpdater.update:(Landroid/database/sqlite/SQLiteDatabase;Landroid/content/Context;)V
        //   194: aload           5
        //   196: astore_3       
        //   197: aload           5
        //   199: astore          4
        //   201: new             Landroid/content/ContentValues;
        //   204: dup            
        //   205: invokespecial   android/content/ContentValues.<init>:()V
        //   208: astore          9
        //   210: aload           5
        //   212: astore_3       
        //   213: aload           5
        //   215: astore          4
        //   217: aload           9
        //   219: ldc_w           "notification_id"
        //   222: aload           8
        //   224: ldc             "i"
        //   226: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;)Ljava/lang/String;
        //   229: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/String;)V
        //   232: aload           5
        //   234: astore_3       
        //   235: aload           5
        //   237: astore          4
        //   239: aload           7
        //   241: ldc             "grp"
        //   243: invokevirtual   org/json/JSONObject.has:(Ljava/lang/String;)Z
        //   246: ifeq            271
        //   249: aload           5
        //   251: astore_3       
        //   252: aload           5
        //   254: astore          4
        //   256: aload           9
        //   258: ldc_w           "group_id"
        //   261: aload           7
        //   263: ldc             "grp"
        //   265: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;)Ljava/lang/String;
        //   268: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/String;)V
        //   271: aload           5
        //   273: astore_3       
        //   274: aload           5
        //   276: astore          4
        //   278: aload           7
        //   280: ldc             "collapse_key"
        //   282: invokevirtual   org/json/JSONObject.has:(Ljava/lang/String;)Z
        //   285: ifeq            620
        //   288: aload           5
        //   290: astore_3       
        //   291: aload           5
        //   293: astore          4
        //   295: ldc             "do_not_collapse"
        //   297: aload           7
        //   299: ldc             "collapse_key"
        //   301: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;)Ljava/lang/String;
        //   304: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   307: ifne            620
        //   310: aload           5
        //   312: astore_3       
        //   313: aload           5
        //   315: astore          4
        //   317: aload           9
        //   319: ldc_w           "collapse_id"
        //   322: aload           7
        //   324: ldc             "collapse_key"
        //   326: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;)Ljava/lang/String;
        //   329: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/String;)V
        //   332: goto            620
        //   335: aload           5
        //   337: astore_3       
        //   338: aload           5
        //   340: astore          4
        //   342: aload           9
        //   344: ldc_w           "opened"
        //   347: iload_2        
        //   348: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   351: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/Integer;)V
        //   354: iload_1        
        //   355: ifne            380
        //   358: aload           5
        //   360: astore_3       
        //   361: aload           5
        //   363: astore          4
        //   365: aload           9
        //   367: ldc_w           "android_notification_id"
        //   370: aload_0        
        //   371: invokevirtual   com/onesignal/NotificationGenerationJob.getAndroidIdWithoutCreate:()I
        //   374: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   377: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/Integer;)V
        //   380: aload           5
        //   382: astore_3       
        //   383: aload           5
        //   385: astore          4
        //   387: aload_0        
        //   388: invokevirtual   com/onesignal/NotificationGenerationJob.getTitle:()Ljava/lang/CharSequence;
        //   391: ifnull          417
        //   394: aload           5
        //   396: astore_3       
        //   397: aload           5
        //   399: astore          4
        //   401: aload           9
        //   403: ldc             "title"
        //   405: aload_0        
        //   406: invokevirtual   com/onesignal/NotificationGenerationJob.getTitle:()Ljava/lang/CharSequence;
        //   409: invokeinterface java/lang/CharSequence.toString:()Ljava/lang/String;
        //   414: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/String;)V
        //   417: aload           5
        //   419: astore_3       
        //   420: aload           5
        //   422: astore          4
        //   424: aload_0        
        //   425: invokevirtual   com/onesignal/NotificationGenerationJob.getBody:()Ljava/lang/CharSequence;
        //   428: ifnull          455
        //   431: aload           5
        //   433: astore_3       
        //   434: aload           5
        //   436: astore          4
        //   438: aload           9
        //   440: ldc_w           "message"
        //   443: aload_0        
        //   444: invokevirtual   com/onesignal/NotificationGenerationJob.getBody:()Ljava/lang/CharSequence;
        //   447: invokeinterface java/lang/CharSequence.toString:()Ljava/lang/String;
        //   452: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/String;)V
        //   455: aload           5
        //   457: astore_3       
        //   458: aload           5
        //   460: astore          4
        //   462: aload           9
        //   464: ldc_w           "full_data"
        //   467: aload           7
        //   469: invokevirtual   org/json/JSONObject.toString:()Ljava/lang/String;
        //   472: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/String;)V
        //   475: aload           5
        //   477: astore_3       
        //   478: aload           5
        //   480: astore          4
        //   482: aload           5
        //   484: ldc_w           "notification"
        //   487: aconst_null    
        //   488: aload           9
        //   490: invokevirtual   android/database/sqlite/SQLiteDatabase.insertOrThrow:(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
        //   493: pop2           
        //   494: iload_1        
        //   495: ifne            512
        //   498: aload           5
        //   500: astore_3       
        //   501: aload           5
        //   503: astore          4
        //   505: aload           5
        //   507: aload           6
        //   509: invokestatic    com/onesignal/BadgeCountUpdater.update:(Landroid/database/sqlite/SQLiteDatabase;Landroid/content/Context;)V
        //   512: aload           5
        //   514: astore_3       
        //   515: aload           5
        //   517: astore          4
        //   519: aload           5
        //   521: invokevirtual   android/database/sqlite/SQLiteDatabase.setTransactionSuccessful:()V
        //   524: aload           5
        //   526: ifnull          534
        //   529: aload           5
        //   531: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
        //   534: return         
        //   535: iconst_0       
        //   536: istore_2       
        //   537: goto            335
        //   540: astore_0       
        //   541: getstatic       com/onesignal/OneSignal$LOG_LEVEL.ERROR:Lcom/onesignal/OneSignal$LOG_LEVEL;
        //   544: ldc_w           "Error closing transaction! "
        //   547: aload_0        
        //   548: invokestatic    com/onesignal/OneSignal.Log:(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   551: return         
        //   552: astore_0       
        //   553: aload_0        
        //   554: invokevirtual   org/json/JSONException.printStackTrace:()V
        //   557: return         
        //   558: astore_0       
        //   559: aload_3        
        //   560: astore          4
        //   562: getstatic       com/onesignal/OneSignal$LOG_LEVEL.ERROR:Lcom/onesignal/OneSignal$LOG_LEVEL;
        //   565: ldc_w           "Error saving notification record! "
        //   568: aload_0        
        //   569: invokestatic    com/onesignal/OneSignal.Log:(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   572: aload_3        
        //   573: ifnull          534
        //   576: aload_3        
        //   577: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
        //   580: return         
        //   581: astore_0       
        //   582: getstatic       com/onesignal/OneSignal$LOG_LEVEL.ERROR:Lcom/onesignal/OneSignal$LOG_LEVEL;
        //   585: ldc_w           "Error closing transaction! "
        //   588: aload_0        
        //   589: invokestatic    com/onesignal/OneSignal.Log:(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   592: return         
        //   593: astore_0       
        //   594: aload           4
        //   596: ifnull          604
        //   599: aload           4
        //   601: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
        //   604: aload_0        
        //   605: athrow         
        //   606: astore_3       
        //   607: getstatic       com/onesignal/OneSignal$LOG_LEVEL.ERROR:Lcom/onesignal/OneSignal$LOG_LEVEL;
        //   610: ldc_w           "Error closing transaction! "
        //   613: aload_3        
        //   614: invokestatic    com/onesignal/OneSignal.Log:(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   617: goto            604
        //   620: iload_1        
        //   621: ifeq            535
        //   624: goto            335
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  14     41     552    558    Lorg/json/JSONException;
        //  46     53     558    593    Ljava/lang/Exception;
        //  46     53     593    620    Any
        //  60     65     558    593    Ljava/lang/Exception;
        //  60     65     593    620    Any
        //  72     77     558    593    Ljava/lang/Exception;
        //  72     77     593    620    Any
        //  84     92     558    593    Ljava/lang/Exception;
        //  84     92     593    620    Any
        //  99     124    558    593    Ljava/lang/Exception;
        //  99     124    593    620    Any
        //  131    140    558    593    Ljava/lang/Exception;
        //  131    140    593    620    Any
        //  147    159    558    593    Ljava/lang/Exception;
        //  147    159    593    620    Any
        //  166    180    558    593    Ljava/lang/Exception;
        //  166    180    593    620    Any
        //  187    194    558    593    Ljava/lang/Exception;
        //  187    194    593    620    Any
        //  201    210    558    593    Ljava/lang/Exception;
        //  201    210    593    620    Any
        //  217    232    558    593    Ljava/lang/Exception;
        //  217    232    593    620    Any
        //  239    249    558    593    Ljava/lang/Exception;
        //  239    249    593    620    Any
        //  256    271    558    593    Ljava/lang/Exception;
        //  256    271    593    620    Any
        //  278    288    558    593    Ljava/lang/Exception;
        //  278    288    593    620    Any
        //  295    310    558    593    Ljava/lang/Exception;
        //  295    310    593    620    Any
        //  317    332    558    593    Ljava/lang/Exception;
        //  317    332    593    620    Any
        //  342    354    558    593    Ljava/lang/Exception;
        //  342    354    593    620    Any
        //  365    380    558    593    Ljava/lang/Exception;
        //  365    380    593    620    Any
        //  387    394    558    593    Ljava/lang/Exception;
        //  387    394    593    620    Any
        //  401    417    558    593    Ljava/lang/Exception;
        //  401    417    593    620    Any
        //  424    431    558    593    Ljava/lang/Exception;
        //  424    431    593    620    Any
        //  438    455    558    593    Ljava/lang/Exception;
        //  438    455    593    620    Any
        //  462    475    558    593    Ljava/lang/Exception;
        //  462    475    593    620    Any
        //  482    494    558    593    Ljava/lang/Exception;
        //  482    494    593    620    Any
        //  505    512    558    593    Ljava/lang/Exception;
        //  505    512    593    620    Any
        //  519    524    558    593    Ljava/lang/Exception;
        //  519    524    593    620    Any
        //  529    534    540    552    Ljava/lang/Throwable;
        //  529    534    552    558    Lorg/json/JSONException;
        //  541    551    552    558    Lorg/json/JSONException;
        //  562    572    593    620    Any
        //  576    580    581    593    Ljava/lang/Throwable;
        //  576    580    552    558    Lorg/json/JSONException;
        //  582    592    552    558    Lorg/json/JSONException;
        //  599    604    606    620    Ljava/lang/Throwable;
        //  599    604    552    558    Lorg/json/JSONException;
        //  604    606    552    558    Lorg/json/JSONException;
        //  607    617    552    558    Lorg/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 302, Size: 302
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3569)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
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
    
    private static void setActionButtons(final OSNotificationPayload osNotificationPayload) throws Throwable {
        if (osNotificationPayload.additionalData != null && osNotificationPayload.additionalData.has("actionButtons")) {
            final JSONArray jsonArray = osNotificationPayload.additionalData.getJSONArray("actionButtons");
            osNotificationPayload.actionButtons = new ArrayList<OSNotificationPayload.ActionButton>();
            for (int i = 0; i < jsonArray.length(); ++i) {
                final JSONObject jsonObject = jsonArray.getJSONObject(i);
                final OSNotificationPayload.ActionButton actionButton = new OSNotificationPayload.ActionButton();
                actionButton.id = jsonObject.optString("id", (String)null);
                actionButton.text = jsonObject.optString("text", (String)null);
                actionButton.icon = jsonObject.optString("icon", (String)null);
                osNotificationPayload.actionButtons.add(actionButton);
            }
            osNotificationPayload.additionalData.remove("actionSelected");
            osNotificationPayload.additionalData.remove("actionButtons");
        }
    }
    
    private static void setBackgroundImageLayout(final OSNotificationPayload osNotificationPayload, JSONObject jsonObject) throws Throwable {
        final String optString = jsonObject.optString("bg_img", (String)null);
        if (optString != null) {
            jsonObject = new JSONObject(optString);
            osNotificationPayload.backgroundImageLayout = new OSNotificationPayload.BackgroundImageLayout();
            osNotificationPayload.backgroundImageLayout.image = jsonObject.optString("img");
            osNotificationPayload.backgroundImageLayout.titleTextColor = jsonObject.optString("tc");
            osNotificationPayload.backgroundImageLayout.bodyTextColor = jsonObject.optString("bc");
        }
    }
    
    static boolean shouldDisplay(final String s) {
        boolean b;
        if (s != null && !"".equals(s)) {
            b = true;
        }
        else {
            b = false;
        }
        final boolean inAppAlertNotificationEnabled = OneSignal.getInAppAlertNotificationEnabled();
        final boolean appActive = OneSignal.isAppActive();
        return b && (OneSignal.getNotificationsWhenActiveEnabled() || inAppAlertNotificationEnabled || !appActive);
    }
    
    private static boolean startExtenderService(final Context context, final Bundle bundle, final ProcessedBundleResult processedBundleResult) {
        boolean b = false;
        final Intent intent = NotificationExtenderService.getIntent(context);
        if (intent == null) {
            return false;
        }
        intent.putExtra("json_payload", bundleAsJSONObject(bundle).toString());
        intent.putExtra("timestamp", System.currentTimeMillis() / 1000L);
        if (Integer.parseInt(bundle.getString("pri", "0")) > 9) {
            b = true;
        }
        if (Build$VERSION.SDK_INT >= 21) {
            JobIntentService.enqueueWork(context, intent.getComponent(), 2071862121, intent, b);
        }
        else {
            context.startService(intent);
        }
        return processedBundleResult.hasExtenderService = true;
    }
    
    private static void unMinifyBundle(final Bundle bundle) {
        if (!bundle.containsKey("o")) {
            return;
        }
    Label_0131_Outer:
        while (true) {
            while (true) {
                String string = null;
            Label_0267:
                while (true) {
                    int n;
                    try {
                        final JSONObject jsonObject = new JSONObject(bundle.getString("custom"));
                        JSONObject jsonObject2;
                        if (jsonObject.has("a")) {
                            jsonObject2 = jsonObject.getJSONObject("a");
                        }
                        else {
                            jsonObject2 = new JSONObject();
                        }
                        final JSONArray jsonArray = new JSONArray(bundle.getString("o"));
                        bundle.remove("o");
                        n = 0;
                        if (n >= jsonArray.length()) {
                            jsonObject2.put("actionButtons", (Object)jsonArray);
                            jsonObject2.put("actionSelected", (Object)"__DEFAULT__");
                            if (!jsonObject.has("a")) {
                                jsonObject.put("a", (Object)jsonObject2);
                            }
                            bundle.putString("custom", jsonObject.toString());
                            return;
                        }
                        final JSONObject jsonObject3 = jsonArray.getJSONObject(n);
                        string = jsonObject3.getString("n");
                        jsonObject3.remove("n");
                        if (!jsonObject3.has("i")) {
                            break Label_0267;
                        }
                        final String string2 = jsonObject3.getString("i");
                        jsonObject3.remove("i");
                        jsonObject3.put("id", (Object)string2);
                        jsonObject3.put("text", (Object)string);
                        if (jsonObject3.has("p")) {
                            jsonObject3.put("icon", (Object)jsonObject3.getString("p"));
                            jsonObject3.remove("p");
                        }
                    }
                    catch (JSONException ex) {
                        ex.printStackTrace();
                        return;
                    }
                    ++n;
                    continue Label_0131_Outer;
                }
                final String string2 = string;
                continue;
            }
        }
    }
    
    static class ProcessedBundleResult
    {
        boolean hasExtenderService;
        boolean isDup;
        boolean isOneSignalPayload;
        
        boolean processed() {
            return !this.isOneSignalPayload || this.hasExtenderService || this.isDup;
        }
    }
}
