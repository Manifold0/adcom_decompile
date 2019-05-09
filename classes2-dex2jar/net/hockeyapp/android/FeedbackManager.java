// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android;

import android.view.View;
import android.media.MediaScannerConnection$MediaScannerConnectionClient;
import android.media.MediaScannerConnection;
import android.widget.Toast;
import java.io.IOException;
import net.hockeyapp.android.utils.HockeyLog;
import java.io.OutputStream;
import android.graphics.Bitmap$CompressFormat;
import java.io.FileOutputStream;
import android.graphics.Bitmap;
import java.io.File;
import android.content.IntentFilter;
import android.app.PendingIntent;
import android.os.Parcelable;
import android.content.Intent;
import android.os.Bundle;
import net.hockeyapp.android.utils.Util;
import android.app.NotificationManager;
import android.net.Uri;
import java.util.List;
import net.hockeyapp.android.tasks.SendFeedbackTask;
import android.os.AsyncTask;
import net.hockeyapp.android.utils.AsyncTaskUtils;
import net.hockeyapp.android.tasks.ParseFeedbackTask;
import android.os.Message;
import android.os.Handler;
import net.hockeyapp.android.utils.PrefsUtil;
import android.content.Context;
import net.hockeyapp.android.objects.FeedbackUserDataElement;
import android.content.BroadcastReceiver;
import android.app.Activity;

public class FeedbackManager
{
    private static final String BROADCAST_ACTION = "net.hockeyapp.android.SCREENSHOT";
    private static final int BROADCAST_REQUEST_CODE = 1;
    private static final int SCREENSHOT_NOTIFICATION_ID = 1;
    private static Activity currentActivity;
    private static String identifier;
    private static FeedbackManagerListener lastListener;
    private static boolean notificationActive;
    private static BroadcastReceiver receiver;
    private static FeedbackUserDataElement requireUserEmail;
    private static FeedbackUserDataElement requireUserName;
    private static String urlString;
    private static String userEmail;
    private static String userName;
    
    static {
        FeedbackManager.receiver = null;
        FeedbackManager.notificationActive = false;
        FeedbackManager.identifier = null;
        FeedbackManager.urlString = null;
        FeedbackManager.requireUserName = FeedbackUserDataElement.REQUIRED;
        FeedbackManager.requireUserEmail = FeedbackUserDataElement.REQUIRED;
        FeedbackManager.lastListener = null;
    }
    
    public static void checkForAnswersAndNotify(final Context context) {
        final String feedbackTokenFromPrefs = PrefsUtil.getInstance().getFeedbackTokenFromPrefs(context);
        if (feedbackTokenFromPrefs == null) {
            return;
        }
        final int int1 = context.getSharedPreferences("net.hockeyapp.android.feedback", 0).getInt("idLastMessageSend", -1);
        final SendFeedbackTask sendFeedbackTask = new SendFeedbackTask(context, getURLString(context), null, null, null, null, null, feedbackTokenFromPrefs, new Handler() {
            public void handleMessage(final Message message) {
                final String string = message.getData().getString("feedback_response");
                if (string != null) {
                    final ParseFeedbackTask parseFeedbackTask = new ParseFeedbackTask(context, string, null, "fetch");
                    parseFeedbackTask.setUrlString(getURLString(context));
                    AsyncTaskUtils.execute(parseFeedbackTask);
                }
            }
        }, true);
        sendFeedbackTask.setShowProgressDialog(false);
        sendFeedbackTask.setLastMessageId(int1);
        AsyncTaskUtils.execute(sendFeedbackTask);
    }
    
    private static void endNotification() {
        FeedbackManager.notificationActive = false;
        FeedbackManager.currentActivity.unregisterReceiver(FeedbackManager.receiver);
        ((NotificationManager)FeedbackManager.currentActivity.getSystemService("notification")).cancel(1);
    }
    
    public static FeedbackManagerListener getLastListener() {
        return FeedbackManager.lastListener;
    }
    
    public static FeedbackUserDataElement getRequireUserEmail() {
        return FeedbackManager.requireUserEmail;
    }
    
    public static FeedbackUserDataElement getRequireUserName() {
        return FeedbackManager.requireUserName;
    }
    
    private static String getURLString(final Context context) {
        return FeedbackManager.urlString + "api/2/apps/" + FeedbackManager.identifier + "/feedback/";
    }
    
    public static void register(final Context context) {
        final String appIdentifier = Util.getAppIdentifier(context);
        if (appIdentifier == null || appIdentifier.length() == 0) {
            throw new IllegalArgumentException("HockeyApp app identifier was not configured correctly in manifest or build configuration.");
        }
        register(context, appIdentifier);
    }
    
    public static void register(final Context context, final String s) {
        register(context, s, null);
    }
    
    public static void register(final Context context, final String urlString, final String s, final FeedbackManagerListener lastListener) {
        if (context != null) {
            FeedbackManager.identifier = Util.sanitizeAppIdentifier(s);
            FeedbackManager.urlString = urlString;
            FeedbackManager.lastListener = lastListener;
            Constants.loadFromContext(context);
        }
    }
    
    public static void register(final Context context, final String s, final FeedbackManagerListener feedbackManagerListener) {
        register(context, "https://sdk.hockeyapp.net/", s, feedbackManagerListener);
    }
    
    public static void setActivityForScreenshot(final Activity currentActivity) {
        FeedbackManager.currentActivity = currentActivity;
        if (!FeedbackManager.notificationActive) {
            startNotification();
        }
    }
    
    public static void setRequireUserEmail(final FeedbackUserDataElement requireUserEmail) {
        FeedbackManager.requireUserEmail = requireUserEmail;
    }
    
    public static void setRequireUserName(final FeedbackUserDataElement requireUserName) {
        FeedbackManager.requireUserName = requireUserName;
    }
    
    public static void setUserEmail(final String userEmail) {
        FeedbackManager.userEmail = userEmail;
    }
    
    public static void setUserName(final String userName) {
        FeedbackManager.userName = userName;
    }
    
    public static void showFeedbackActivity(final Context context, final Bundle bundle, final Uri... array) {
        if (context != null) {
            Class<? extends FeedbackActivity> feedbackActivityClass = null;
            if (FeedbackManager.lastListener != null) {
                feedbackActivityClass = FeedbackManager.lastListener.getFeedbackActivityClass();
            }
            Class<? extends FeedbackActivity> clazz;
            if ((clazz = feedbackActivityClass) == null) {
                clazz = FeedbackActivity.class;
            }
            final boolean b = FeedbackManager.lastListener != null && FeedbackManager.lastListener.shouldCreateNewFeedbackThread();
            final Intent intent = new Intent();
            if (bundle != null && !bundle.isEmpty()) {
                intent.putExtras(bundle);
            }
            intent.setFlags(268435456);
            intent.setClass(context, (Class)clazz);
            intent.putExtra("url", getURLString(context));
            intent.putExtra("forceNewThread", b);
            intent.putExtra("initialUserName", FeedbackManager.userName);
            intent.putExtra("initialUserEmail", FeedbackManager.userEmail);
            intent.putExtra("initialAttachments", (Parcelable[])array);
            context.startActivity(intent);
        }
    }
    
    public static void showFeedbackActivity(final Context context, final Uri... array) {
        showFeedbackActivity(context, (Bundle)null, array);
    }
    
    private static void startNotification() {
        FeedbackManager.notificationActive = true;
        final NotificationManager notificationManager = (NotificationManager)FeedbackManager.currentActivity.getSystemService("notification");
        final int identifier = FeedbackManager.currentActivity.getResources().getIdentifier("ic_menu_camera", "drawable", "android");
        final Intent intent = new Intent();
        intent.setAction("net.hockeyapp.android.SCREENSHOT");
        notificationManager.notify(1, Util.createNotification((Context)FeedbackManager.currentActivity, PendingIntent.getBroadcast((Context)FeedbackManager.currentActivity, 1, intent, 1073741824), "HockeyApp Feedback", "Take a screenshot for your feedback.", identifier));
        if (FeedbackManager.receiver == null) {
            FeedbackManager.receiver = new BroadcastReceiver() {
                public void onReceive(final Context context, final Intent intent) {
                    FeedbackManager.takeScreenshot(context);
                }
            };
        }
        FeedbackManager.currentActivity.registerReceiver(FeedbackManager.receiver, new IntentFilter("net.hockeyapp.android.SCREENSHOT"));
    }
    
    public static void takeScreenshot(final Context context) {
        final View decorView = FeedbackManager.currentActivity.getWindow().getDecorView();
        decorView.setDrawingCacheEnabled(true);
        final Bitmap drawingCache = decorView.getDrawingCache();
        final String localClassName = FeedbackManager.currentActivity.getLocalClassName();
        final File hockeyAppStorageDir = Constants.getHockeyAppStorageDir();
        File file = new File(hockeyAppStorageDir, localClassName + ".jpg");
        for (int n = 1; file.exists(); file = new File(hockeyAppStorageDir, localClassName + "_" + n + ".jpg"), ++n) {}
        new AsyncTask<File, Void, Boolean>() {
            protected Boolean doInBackground(final File... array) {
                try {
                    final FileOutputStream fileOutputStream = new FileOutputStream(array[0]);
                    drawingCache.compress(Bitmap$CompressFormat.JPEG, 100, (OutputStream)fileOutputStream);
                    fileOutputStream.close();
                    return true;
                }
                catch (IOException ex) {
                    HockeyLog.error("Could not save screenshot.", ex);
                    return false;
                }
            }
            
            protected void onPostExecute(final Boolean b) {
                if (!b) {
                    Toast.makeText(context, (CharSequence)"Screenshot could not be created. Sorry.", 1).show();
                }
            }
        }.execute((Object[])new File[] { file });
        final MediaScannerClient mediaScannerClient = new MediaScannerClient(file.getAbsolutePath());
        final MediaScannerConnection connection = new MediaScannerConnection((Context)FeedbackManager.currentActivity, (MediaScannerConnection$MediaScannerConnectionClient)mediaScannerClient);
        mediaScannerClient.setConnection(connection);
        connection.connect();
        Toast.makeText(context, (CharSequence)("Screenshot '" + file.getName() + "' is available in gallery."), 1).show();
    }
    
    public static void unregister() {
        FeedbackManager.lastListener = null;
    }
    
    public static void unsetCurrentActivityForScreenshot(final Activity activity) {
        if (FeedbackManager.currentActivity == null || FeedbackManager.currentActivity != activity) {
            return;
        }
        endNotification();
        FeedbackManager.currentActivity = null;
    }
    
    private static class MediaScannerClient implements MediaScannerConnection$MediaScannerConnectionClient
    {
        private MediaScannerConnection connection;
        private String path;
        
        private MediaScannerClient(final String path) {
            this.connection = null;
            this.path = path;
        }
        
        public void onMediaScannerConnected() {
            if (this.connection != null) {
                this.connection.scanFile(this.path, (String)null);
            }
        }
        
        public void onScanCompleted(final String s, final Uri uri) {
            HockeyLog.verbose(String.format("Scanned path %s -> URI = %s", s, uri.toString()));
            this.connection.disconnect();
        }
        
        public void setConnection(final MediaScannerConnection connection) {
            this.connection = connection;
        }
    }
}
