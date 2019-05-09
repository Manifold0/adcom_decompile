// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android;

import android.os.Message;
import java.lang.ref.WeakReference;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import net.hockeyapp.android.utils.AsyncTaskUtils;
import java.util.Map;
import net.hockeyapp.android.tasks.LoginTask;
import java.util.HashMap;
import net.hockeyapp.android.utils.HockeyLog;
import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import net.hockeyapp.android.utils.Util;
import android.content.Context;
import android.os.Handler;

public class LoginManager
{
    static final String LOGIN_EXIT_KEY = "net.hockeyapp.android.EXIT";
    public static final int LOGIN_MODE_ANONYMOUS = 0;
    public static final int LOGIN_MODE_EMAIL_ONLY = 1;
    public static final int LOGIN_MODE_EMAIL_PASSWORD = 2;
    public static final int LOGIN_MODE_VALIDATE = 3;
    private static String identifier;
    static LoginManagerListener listener;
    static Class<?> mainActivity;
    private static int mode;
    private static String secret;
    private static String urlString;
    private static Handler validateHandler;
    
    static {
        LoginManager.identifier = null;
        LoginManager.secret = null;
        LoginManager.validateHandler = null;
        LoginManager.urlString = null;
    }
    
    private static String getURLString(final int n) {
        String s = "";
        if (n == 2) {
            s = "authorize";
        }
        else if (n == 1) {
            s = "check";
        }
        else if (n == 3) {
            s = "validate";
        }
        return LoginManager.urlString + "api/3/apps/" + LoginManager.identifier + "/identity/" + s;
    }
    
    public static void register(final Context context, final String s, final int n) {
        final String appIdentifier = Util.getAppIdentifier(context);
        if (TextUtils.isEmpty((CharSequence)appIdentifier)) {
            throw new IllegalArgumentException("HockeyApp app identifier was not configured correctly in manifest or build configuration.");
        }
        register(context, appIdentifier, s, n, (Class<?>)null);
    }
    
    public static void register(final Context context, final String s, final String s2, final int n, final Class<?> clazz) {
        register(context, s, s2, "https://sdk.hockeyapp.net/", n, clazz);
    }
    
    public static void register(final Context context, final String s, final String s2, final int n, final LoginManagerListener listener) {
        LoginManager.listener = listener;
        register(context, s, s2, n, (Class<?>)null);
    }
    
    public static void register(final Context context, final String s, final String secret, final String urlString, final int mode, final Class<?> mainActivity) {
        if (context != null) {
            LoginManager.identifier = Util.sanitizeAppIdentifier(s);
            LoginManager.secret = secret;
            LoginManager.urlString = urlString;
            LoginManager.mode = mode;
            LoginManager.mainActivity = mainActivity;
            if (LoginManager.validateHandler == null) {
                LoginManager.validateHandler = new LoginHandler(context);
            }
            Constants.loadFromContext(context);
        }
    }
    
    private static void startLoginActivity(final Context context) {
        final Intent intent = new Intent();
        int mode;
        if ((boolean)(LoginManager.mode == 3)) {
            mode = 2;
        }
        else {
            mode = LoginManager.mode;
        }
        intent.setFlags(1342177280);
        intent.setClass(context, (Class)LoginActivity.class);
        intent.putExtra("url", getURLString(mode));
        intent.putExtra("mode", mode);
        intent.putExtra("secret", LoginManager.secret);
        context.startActivity(intent);
    }
    
    public static void verifyLogin(final Activity activity, final Intent intent) {
        if (intent != null && intent.getBooleanExtra("net.hockeyapp.android.EXIT", false)) {
            activity.finish();
        }
        else if (activity != null && LoginManager.mode != 0) {
            final SharedPreferences sharedPreferences = activity.getSharedPreferences("net.hockeyapp.android.login", 0);
            if (sharedPreferences.getInt("mode", -1) != LoginManager.mode) {
                HockeyLog.verbose("HockeyAuth", "Mode has changed, require re-auth.");
                sharedPreferences.edit().remove("auid").remove("iuid").putInt("mode", LoginManager.mode).apply();
            }
            final String string = sharedPreferences.getString("auid", (String)null);
            final String string2 = sharedPreferences.getString("iuid", (String)null);
            boolean b;
            if (string == null && string2 == null) {
                b = true;
            }
            else {
                b = false;
            }
            boolean b2;
            if (string == null && (LoginManager.mode == 2 || LoginManager.mode == 3)) {
                b2 = true;
            }
            else {
                b2 = false;
            }
            boolean b3;
            if (string2 == null && LoginManager.mode == 1) {
                b3 = true;
            }
            else {
                b3 = false;
            }
            if (b || b2 || b3) {
                HockeyLog.verbose("HockeyAuth", "Not authenticated or correct ID missing, re-authenticate.");
                startLoginActivity((Context)activity);
                return;
            }
            if (LoginManager.mode == 3) {
                HockeyLog.verbose("HockeyAuth", "LOGIN_MODE_VALIDATE, Validate the user's info!");
                final HashMap<String, String> hashMap = new HashMap<String, String>();
                if (string != null) {
                    hashMap.put("type", "auid");
                    hashMap.put("id", string);
                }
                else if (string2 != null) {
                    hashMap.put("type", "iuid");
                    hashMap.put("id", string2);
                }
                final LoginTask loginTask = new LoginTask((Context)activity, LoginManager.validateHandler, getURLString(3), 3, hashMap);
                loginTask.setShowProgressDialog(false);
                AsyncTaskUtils.execute(loginTask);
            }
        }
    }
    
    private static class LoginHandler extends Handler
    {
        private final WeakReference<Context> mWeakContext;
        
        public LoginHandler(final Context context) {
            this.mWeakContext = new WeakReference<Context>(context);
        }
        
        public void handleMessage(final Message message) {
            final boolean boolean1 = message.getData().getBoolean("success");
            final Context context = this.mWeakContext.get();
            if (context == null) {
                return;
            }
            if (!boolean1) {
                startLoginActivity(context);
                return;
            }
            HockeyLog.verbose("HockeyAuth", "We authenticated or verified successfully");
        }
    }
}
