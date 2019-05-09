// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.tasks;

import android.content.SharedPreferences;
import java.util.Locale;
import android.text.TextUtils;
import android.provider.Settings$Secure;
import java.io.BufferedInputStream;
import net.hockeyapp.android.utils.HockeyLog;
import net.hockeyapp.android.utils.VersionCache;
import java.net.URLConnection;
import java.net.URL;
import org.json.JSONObject;
import org.json.JSONException;
import android.os.Build$VERSION;
import net.hockeyapp.android.utils.VersionHelper;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import net.hockeyapp.android.Constants;
import net.hockeyapp.android.Tracking;
import java.lang.ref.WeakReference;
import net.hockeyapp.android.UpdateManagerListener;
import android.content.Context;
import org.json.JSONArray;
import android.os.AsyncTask;

public class CheckUpdateTask extends AsyncTask<Void, String, JSONArray>
{
    protected static final String APK = "apk";
    private static final int MAX_NUMBER_OF_VERSIONS = 25;
    protected String appIdentifier;
    private Context context;
    protected UpdateManagerListener listener;
    protected Boolean mandatory;
    protected String urlString;
    private long usageTime;
    
    public CheckUpdateTask(final WeakReference<? extends Context> weakReference, final String s) {
        this(weakReference, s, null);
    }
    
    public CheckUpdateTask(final WeakReference<? extends Context> weakReference, final String s, final String s2) {
        this(weakReference, s, s2, null);
    }
    
    public CheckUpdateTask(final WeakReference<? extends Context> weakReference, final String urlString, final String appIdentifier, final UpdateManagerListener listener) {
        this.urlString = null;
        this.appIdentifier = null;
        this.context = null;
        this.mandatory = false;
        this.usageTime = 0L;
        this.appIdentifier = appIdentifier;
        this.urlString = urlString;
        this.listener = listener;
        Context context = null;
        if (weakReference != null) {
            context = weakReference.get();
        }
        if (context != null) {
            this.context = context.getApplicationContext();
            this.usageTime = Tracking.getUsageTime(context);
            Constants.loadFromContext(context);
        }
    }
    
    private static String convertStreamToString(final InputStream ex) {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((InputStream)ex), 1024);
        final StringBuilder sb = new StringBuilder();
        try {
            Label_0077: {
                try {
                    while (true) {
                        final String line = bufferedReader.readLine();
                        if (line == null) {
                            break Label_0077;
                        }
                        sb.append(line + "\n");
                    }
                }
                catch (IOException ex2) {
                    ex2.printStackTrace();
                    final IOException ex3 = ex;
                    ((InputStream)ex3).close();
                }
                try {
                    final IOException ex3 = ex;
                    ((InputStream)ex3).close();
                    return sb.toString();
                    try {
                        ((InputStream)ex).close();
                        return sb.toString();
                    }
                    catch (IOException ex) {
                        ex.printStackTrace();
                        return sb.toString();
                    }
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        finally {
            try {
                ((InputStream)ex).close();
            }
            catch (IOException ex4) {
                ex4.printStackTrace();
            }
        }
    }
    
    private String encodeParam(String encode) {
        try {
            encode = URLEncoder.encode(encode, "UTF-8");
            return encode;
        }
        catch (UnsupportedEncodingException ex) {
            return "";
        }
    }
    
    private boolean findNewVersion(final JSONArray jsonArray, final int n) {
        boolean b = false;
        int n2 = 0;
        boolean b2 = false;
        JSONObject jsonObject;
        boolean b3 = false;
        boolean b4 = false;
        boolean b5;
        boolean b6;
        Block_11_Outer:Label_0094_Outer:Label_0138_Outer:
        while (true) {
            b2 = b;
        Label_0138:
            while (true) {
                while (true) {
                    Label_0174: {
                        try {
                            if (n2 >= jsonArray.length()) {
                                break;
                            }
                            jsonObject = jsonArray.getJSONObject(n2);
                            b3 = (jsonObject.getInt("version") > n);
                            b4 = (jsonObject.getInt("version") == n && VersionHelper.isNewerThanLastUpdateTime(this.context, jsonObject.getLong("timestamp")));
                            if (VersionHelper.compareVersionStrings(jsonObject.getString("minimum_os_version"), VersionHelper.mapGoogleVersion(Build$VERSION.RELEASE)) <= 0) {
                                b5 = true;
                                break Label_0174;
                            }
                            b5 = false;
                            break Label_0174;
                            // iftrue(Label_0138:, b5 == false)
                            // iftrue(Label_0135:, !jsonObject.has("mandatory"))
                            while (true) {
                                this.mandatory |= jsonObject.getBoolean("mandatory");
                                Block_10: {
                                    Label_0135: {
                                        break Label_0135;
                                        b6 = b;
                                        break Block_10;
                                    }
                                    b6 = true;
                                    break Label_0138;
                                }
                                continue Label_0094_Outer;
                            }
                            ++n2;
                            b = b6;
                            continue Block_11_Outer;
                        }
                        catch (JSONException ex) {
                            b2 = false;
                        }
                        break;
                    }
                    if (b3) {
                        continue Label_0138_Outer;
                    }
                    b6 = b;
                    if (b4) {
                        continue Label_0138_Outer;
                    }
                    break;
                }
                continue Label_0138;
            }
        }
        return b2;
    }
    
    private JSONArray limitResponseSize(final JSONArray jsonArray) {
        final JSONArray jsonArray2 = new JSONArray();
        int n = 0;
    Label_0033_Outer:
        while (true) {
            if (n >= Math.min(jsonArray.length(), 25)) {
                return jsonArray2;
            }
            while (true) {
                try {
                    jsonArray2.put(jsonArray.get(n));
                    ++n;
                    continue Label_0033_Outer;
                }
                catch (JSONException ex) {
                    continue;
                }
                break;
            }
        }
    }
    
    public void attach(final WeakReference<? extends Context> weakReference) {
        Context context = null;
        if (weakReference != null) {
            context = weakReference.get();
        }
        if (context != null) {
            this.context = context.getApplicationContext();
            Constants.loadFromContext(context);
        }
    }
    
    protected void cleanUp() {
        this.urlString = null;
        this.appIdentifier = null;
    }
    
    protected URLConnection createConnection(final URL url) throws IOException {
        final URLConnection openConnection = url.openConnection();
        openConnection.addRequestProperty("User-Agent", "HockeySDK/Android 4.1.2");
        if (Build$VERSION.SDK_INT <= 9) {
            openConnection.setRequestProperty("connection", "close");
        }
        return openConnection;
    }
    
    public void detach() {
        this.context = null;
    }
    
    protected JSONArray doInBackground(Void... ex) {
        try {
            final int versionCode = this.getVersionCode();
            ex = (IOException)new JSONArray(VersionCache.getVersionInfo(this.context));
            if (this.getCachingEnabled() && this.findNewVersion((JSONArray)ex, versionCode)) {
                HockeyLog.verbose("HockeyUpdate", "Returning cached JSON");
                return (JSONArray)ex;
            }
            ex = (IOException)this.createConnection(new URL(this.getURLString("json")));
            ((URLConnection)ex).connect();
            ex = (IOException)new BufferedInputStream(((URLConnection)ex).getInputStream());
            final String convertStreamToString = convertStreamToString((InputStream)ex);
            ((InputStream)ex).close();
            ex = (IOException)new JSONArray(convertStreamToString);
            if (this.findNewVersion((JSONArray)ex, versionCode)) {
                ex = (IOException)this.limitResponseSize((JSONArray)ex);
                return (JSONArray)ex;
            }
            goto Label_0122;
        }
        catch (JSONException ex2) {}
        catch (IOException ex) {
            goto Label_0118;
        }
    }
    
    protected boolean getCachingEnabled() {
        return true;
    }
    
    protected String getURLString(String string) {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.urlString);
        sb.append("api/2/apps/");
        String s;
        if (this.appIdentifier != null) {
            s = this.appIdentifier;
        }
        else {
            s = this.context.getPackageName();
        }
        sb.append(s);
        sb.append("?format=" + string);
        if (!TextUtils.isEmpty((CharSequence)Settings$Secure.getString(this.context.getContentResolver(), "android_id"))) {
            sb.append("&udid=" + this.encodeParam(Settings$Secure.getString(this.context.getContentResolver(), "android_id")));
        }
        final SharedPreferences sharedPreferences = this.context.getSharedPreferences("net.hockeyapp.android.login", 0);
        final String string2 = sharedPreferences.getString("auid", (String)null);
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            sb.append("&auid=" + this.encodeParam(string2));
        }
        string = sharedPreferences.getString("iuid", (String)null);
        if (!TextUtils.isEmpty((CharSequence)string)) {
            sb.append("&iuid=" + this.encodeParam(string));
        }
        sb.append("&os=Android");
        sb.append("&os_version=" + this.encodeParam(Constants.ANDROID_VERSION));
        sb.append("&device=" + this.encodeParam(Constants.PHONE_MODEL));
        sb.append("&oem=" + this.encodeParam(Constants.PHONE_MANUFACTURER));
        sb.append("&app_version=" + this.encodeParam(Constants.APP_VERSION));
        sb.append("&sdk=" + this.encodeParam("HockeySDK"));
        sb.append("&sdk_version=" + this.encodeParam("4.1.2"));
        sb.append("&lang=" + this.encodeParam(Locale.getDefault().getLanguage()));
        sb.append("&usage_time=" + this.usageTime);
        return sb.toString();
    }
    
    protected int getVersionCode() {
        return Integer.parseInt(Constants.APP_VERSION);
    }
    
    protected void onPostExecute(final JSONArray jsonArray) {
        if (jsonArray != null) {
            HockeyLog.verbose("HockeyUpdate", "Received Update Info");
            if (this.listener != null) {
                this.listener.onUpdateAvailable(jsonArray, this.getURLString("apk"));
            }
        }
        else {
            HockeyLog.verbose("HockeyUpdate", "No Update Info available");
            if (this.listener != null) {
                this.listener.onNoUpdateAvailable();
            }
        }
    }
}
