// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import android.database.Cursor;
import android.content.ContentValues;
import android.support.v4.app.NotificationManagerCompat;
import android.content.Intent;
import android.content.Context;
import org.json.JSONObject;
import android.database.sqlite.SQLiteDatabase;
import org.json.JSONArray;

class NotificationOpenedProcessor
{
    private static void addChildNotifications(final JSONArray jsonArray, final String s, SQLiteDatabase query) {
        query = (SQLiteDatabase)query.query("notification", new String[] { "full_data" }, "group_id = ? AND dismissed = 0 AND opened = 0 AND is_summary = 0", new String[] { s }, (String)null, (String)null, (String)null);
        Label_0081: {
            if (((Cursor)query).getCount() <= 1) {
                break Label_0081;
            }
            ((Cursor)query).moveToFirst();
            while (true) {
                try {
                    do {
                        jsonArray.put((Object)new JSONObject(((Cursor)query).getString(((Cursor)query).getColumnIndex("full_data"))));
                    } while (((Cursor)query).moveToNext());
                    ((Cursor)query).close();
                }
                catch (Throwable t) {
                    OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Could not parse JSON of sub notification in group: " + s);
                    continue;
                }
                break;
            }
        }
    }
    
    private static void handleDismissFromActionButtonPress(final Context context, final Intent intent) {
        if (intent.getBooleanExtra("action_button", false)) {
            NotificationManagerCompat.from(context).cancel(intent.getIntExtra("notificationId", 0));
            context.sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
        }
    }
    
    private static boolean isOneSignalIntent(final Intent intent) {
        return intent.hasExtra("onesignal_data") || intent.hasExtra("summary") || intent.hasExtra("notificationId");
    }
    
    private static void markNotificationsConsumed(final Context context, final Intent intent, final SQLiteDatabase sqLiteDatabase) {
        final String stringExtra = intent.getStringExtra("summary");
        String[] array = null;
        String string;
        if (stringExtra != null) {
            string = "group_id = ?";
            array = new String[] { stringExtra };
        }
        else {
            string = "android_notification_id = " + intent.getIntExtra("notificationId", 0);
        }
        sqLiteDatabase.update("notification", newContentValuesWithConsumed(intent), string, array);
        BadgeCountUpdater.update(sqLiteDatabase, context);
    }
    
    private static ContentValues newContentValuesWithConsumed(final Intent intent) {
        final ContentValues contentValues = new ContentValues();
        if (intent.getBooleanExtra("dismissed", false)) {
            contentValues.put("dismissed", Integer.valueOf(1));
            return contentValues;
        }
        contentValues.put("opened", Integer.valueOf(1));
        return contentValues;
    }
    
    static void processFromContext(final Context appContext, final Intent intent) {
        if (!isOneSignalIntent(intent)) {
            return;
        }
        OneSignal.setAppContext(appContext);
        handleDismissFromActionButtonPress(appContext, intent);
        processIntent(appContext, intent);
    }
    
    static void processIntent(final Context p0, final Intent p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ldc             "summary"
        //     3: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //     6: astore          7
        //     8: aload_1        
        //     9: ldc             "dismissed"
        //    11: iconst_0       
        //    12: invokevirtual   android/content/Intent.getBooleanExtra:(Ljava/lang/String;Z)Z
        //    15: istore_2       
        //    16: aconst_null    
        //    17: astore_3       
        //    18: aload_3        
        //    19: astore          5
        //    21: iload_2        
        //    22: ifne            85
        //    25: new             Lorg/json/JSONObject;
        //    28: dup            
        //    29: aload_1        
        //    30: ldc             "onesignal_data"
        //    32: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    35: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //    38: astore          4
        //    40: aload           4
        //    42: ldc             "notificationId"
        //    44: aload_1        
        //    45: ldc             "notificationId"
        //    47: iconst_0       
        //    48: invokevirtual   android/content/Intent.getIntExtra:(Ljava/lang/String;I)I
        //    51: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //    54: pop            
        //    55: aload_1        
        //    56: ldc             "onesignal_data"
        //    58: aload           4
        //    60: invokevirtual   org/json/JSONObject.toString:()Ljava/lang/String;
        //    63: invokevirtual   android/content/Intent.putExtra:(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
        //    66: pop            
        //    67: new             Lorg/json/JSONObject;
        //    70: dup            
        //    71: aload_1        
        //    72: ldc             "onesignal_data"
        //    74: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    77: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //    80: invokestatic    com/onesignal/NotificationBundleProcessor.newJsonArray:(Lorg/json/JSONObject;)Lorg/json/JSONArray;
        //    83: astore          5
        //    85: aload_0        
        //    86: invokestatic    com/onesignal/OneSignalDbHelper.getInstance:(Landroid/content/Context;)Lcom/onesignal/OneSignalDbHelper;
        //    89: astore          6
        //    91: aconst_null    
        //    92: astore          4
        //    94: aconst_null    
        //    95: astore_3       
        //    96: aload           6
        //    98: invokevirtual   com/onesignal/OneSignalDbHelper.getWritableDbWithRetries:()Landroid/database/sqlite/SQLiteDatabase;
        //   101: astore          6
        //   103: aload           6
        //   105: astore_3       
        //   106: aload           6
        //   108: astore          4
        //   110: aload           6
        //   112: invokevirtual   android/database/sqlite/SQLiteDatabase.beginTransaction:()V
        //   115: iload_2        
        //   116: ifne            140
        //   119: aload           7
        //   121: ifnull          140
        //   124: aload           6
        //   126: astore_3       
        //   127: aload           6
        //   129: astore          4
        //   131: aload           5
        //   133: aload           7
        //   135: aload           6
        //   137: invokestatic    com/onesignal/NotificationOpenedProcessor.addChildNotifications:(Lorg/json/JSONArray;Ljava/lang/String;Landroid/database/sqlite/SQLiteDatabase;)V
        //   140: aload           6
        //   142: astore_3       
        //   143: aload           6
        //   145: astore          4
        //   147: aload_0        
        //   148: aload_1        
        //   149: aload           6
        //   151: invokestatic    com/onesignal/NotificationOpenedProcessor.markNotificationsConsumed:(Landroid/content/Context;Landroid/content/Intent;Landroid/database/sqlite/SQLiteDatabase;)V
        //   154: aload           7
        //   156: ifnonnull       195
        //   159: aload           6
        //   161: astore_3       
        //   162: aload           6
        //   164: astore          4
        //   166: aload_1        
        //   167: ldc             "grp"
        //   169: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //   172: astore          7
        //   174: aload           7
        //   176: ifnull          195
        //   179: aload           6
        //   181: astore_3       
        //   182: aload           6
        //   184: astore          4
        //   186: aload_0        
        //   187: aload           6
        //   189: aload           7
        //   191: iload_2        
        //   192: invokestatic    com/onesignal/NotificationSummaryManager.updateSummaryNotificationAfterChildRemoved:(Landroid/content/Context;Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Z)V
        //   195: aload           6
        //   197: astore_3       
        //   198: aload           6
        //   200: astore          4
        //   202: aload           6
        //   204: invokevirtual   android/database/sqlite/SQLiteDatabase.setTransactionSuccessful:()V
        //   207: aload           6
        //   209: ifnull          217
        //   212: aload           6
        //   214: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
        //   217: iload_2        
        //   218: ifne            234
        //   221: aload_0        
        //   222: aload           5
        //   224: aload_1        
        //   225: ldc             "from_alert"
        //   227: iconst_0       
        //   228: invokevirtual   android/content/Intent.getBooleanExtra:(Ljava/lang/String;Z)Z
        //   231: invokestatic    com/onesignal/OneSignal.handleNotificationOpen:(Landroid/content/Context;Lorg/json/JSONArray;Z)V
        //   234: return         
        //   235: astore          4
        //   237: aload           4
        //   239: invokevirtual   java/lang/Throwable.printStackTrace:()V
        //   242: aload_3        
        //   243: astore          5
        //   245: goto            85
        //   248: astore_3       
        //   249: getstatic       com/onesignal/OneSignal$LOG_LEVEL.ERROR:Lcom/onesignal/OneSignal$LOG_LEVEL;
        //   252: ldc             "Error closing transaction! "
        //   254: aload_3        
        //   255: invokestatic    com/onesignal/OneSignal.Log:(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   258: goto            217
        //   261: astore          6
        //   263: aload_3        
        //   264: astore          4
        //   266: getstatic       com/onesignal/OneSignal$LOG_LEVEL.ERROR:Lcom/onesignal/OneSignal$LOG_LEVEL;
        //   269: ldc             "Error processing notification open or dismiss record! "
        //   271: aload           6
        //   273: invokestatic    com/onesignal/OneSignal.Log:(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   276: aload_3        
        //   277: ifnull          217
        //   280: aload_3        
        //   281: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
        //   284: goto            217
        //   287: astore_3       
        //   288: getstatic       com/onesignal/OneSignal$LOG_LEVEL.ERROR:Lcom/onesignal/OneSignal$LOG_LEVEL;
        //   291: ldc             "Error closing transaction! "
        //   293: aload_3        
        //   294: invokestatic    com/onesignal/OneSignal.Log:(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   297: goto            217
        //   300: astore_0       
        //   301: aload           4
        //   303: ifnull          311
        //   306: aload           4
        //   308: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
        //   311: aload_0        
        //   312: athrow         
        //   313: astore_1       
        //   314: getstatic       com/onesignal/OneSignal$LOG_LEVEL.ERROR:Lcom/onesignal/OneSignal$LOG_LEVEL;
        //   317: ldc             "Error closing transaction! "
        //   319: aload_1        
        //   320: invokestatic    com/onesignal/OneSignal.Log:(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   323: goto            311
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  25     85     235    248    Ljava/lang/Throwable;
        //  96     103    261    300    Ljava/lang/Exception;
        //  96     103    300    326    Any
        //  110    115    261    300    Ljava/lang/Exception;
        //  110    115    300    326    Any
        //  131    140    261    300    Ljava/lang/Exception;
        //  131    140    300    326    Any
        //  147    154    261    300    Ljava/lang/Exception;
        //  147    154    300    326    Any
        //  166    174    261    300    Ljava/lang/Exception;
        //  166    174    300    326    Any
        //  186    195    261    300    Ljava/lang/Exception;
        //  186    195    300    326    Any
        //  202    207    261    300    Ljava/lang/Exception;
        //  202    207    300    326    Any
        //  212    217    248    261    Ljava/lang/Throwable;
        //  266    276    300    326    Any
        //  280    284    287    300    Ljava/lang/Throwable;
        //  306    311    313    326    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0140:
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
}
