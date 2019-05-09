// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.tasks;

import java.net.URLConnection;
import android.os.Bundle;
import android.os.Message;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import net.hockeyapp.android.utils.HttpURLConnectionBuilder;
import java.net.HttpURLConnection;
import android.content.SharedPreferences;
import org.json.JSONException;
import android.text.TextUtils;
import org.json.JSONObject;
import net.hockeyapp.android.Constants;
import android.app.ProgressDialog;
import java.util.Map;
import android.os.Handler;
import android.content.Context;

public class LoginTask extends ConnectionTask<Void, Void, Boolean>
{
    public static final String BUNDLE_SUCCESS = "success";
    private Context mContext;
    private Handler mHandler;
    private final int mMode;
    private final Map<String, String> mParams;
    private ProgressDialog mProgressDialog;
    private boolean mShowProgressDialog;
    private final String mUrlString;
    
    public LoginTask(final Context mContext, final Handler mHandler, final String mUrlString, final int mMode, final Map<String, String> mParams) {
        this.mContext = mContext;
        this.mHandler = mHandler;
        this.mUrlString = mUrlString;
        this.mMode = mMode;
        this.mParams = mParams;
        this.mShowProgressDialog = true;
        if (mContext != null) {
            Constants.loadFromContext(mContext);
        }
    }
    
    private boolean handleResponse(String s) {
        final SharedPreferences sharedPreferences = this.mContext.getSharedPreferences("net.hockeyapp.android.login", 0);
        try {
            final JSONObject jsonObject = new JSONObject(s);
            final String string = jsonObject.getString("status");
            if (TextUtils.isEmpty((CharSequence)string)) {
                return false;
            }
            if (this.mMode == 1) {
                if (!string.equals("identified")) {
                    return false;
                }
                s = jsonObject.getString("iuid");
                if (!TextUtils.isEmpty((CharSequence)s)) {
                    sharedPreferences.edit().putString("iuid", s).apply();
                    return true;
                }
                return false;
            }
            else if (this.mMode == 2) {
                if (!string.equals("authorized")) {
                    return false;
                }
                s = jsonObject.getString("auid");
                if (!TextUtils.isEmpty((CharSequence)s)) {
                    sharedPreferences.edit().putString("auid", s).apply();
                    return true;
                }
                return false;
            }
            else if (this.mMode == 3) {
                if (string.equals("validated")) {
                    return true;
                }
                sharedPreferences.edit().remove("iuid").remove("auid").apply();
                return false;
            }
        }
        catch (JSONException ex) {
            ex.printStackTrace();
            return false;
        }
        throw new IllegalArgumentException("Login mode " + this.mMode + " not supported.");
    }
    
    private HttpURLConnection makeRequest(final int n, final Map<String, String> map) throws IOException {
        if (n == 1) {
            return new HttpURLConnectionBuilder(this.mUrlString).setRequestMethod("POST").writeFormFields(map).build();
        }
        if (n == 2) {
            return new HttpURLConnectionBuilder(this.mUrlString).setRequestMethod("POST").setBasicAuthorization(map.get("email"), map.get("password")).build();
        }
        if (n == 3) {
            return new HttpURLConnectionBuilder(this.mUrlString + "?" + map.get("type") + "=" + map.get("id")).build();
        }
        throw new IllegalArgumentException("Login mode " + n + " not supported.");
    }
    
    public void attach(final Context mContext, final Handler mHandler) {
        this.mContext = mContext;
        this.mHandler = mHandler;
    }
    
    public void detach() {
        this.mContext = null;
        this.mHandler = null;
        this.mProgressDialog = null;
    }
    
    protected Boolean doInBackground(Void... array) {
        Void[] array2 = null;
        array = null;
        Object request = null;
        try {
            final Object o = array = (array2 = (Void[])(request = this.makeRequest(this.mMode, this.mParams)));
            ((URLConnection)o).connect();
            request = o;
            array2 = (Void[])o;
            array = (Void[])o;
            if (((HttpURLConnection)o).getResponseCode() == 200) {
                request = o;
                array2 = (Void[])o;
                array = (Void[])o;
                final String stringFromConnection = ConnectionTask.getStringFromConnection((HttpURLConnection)o);
                request = o;
                array2 = (Void[])o;
                array = (Void[])o;
                if (!TextUtils.isEmpty((CharSequence)stringFromConnection)) {
                    request = o;
                    array2 = (Void[])o;
                    array = (Void[])o;
                    final boolean handleResponse = this.handleResponse(stringFromConnection);
                    if (o != null) {
                        ((HttpURLConnection)o).disconnect();
                    }
                    return handleResponse;
                }
            }
            if (o != null) {
                ((HttpURLConnection)o).disconnect();
            }
            return false;
        }
        catch (UnsupportedEncodingException array2) {
            array = (Void[])request;
            ((Throwable)(Object)array2).printStackTrace();
        }
        catch (IOException request) {
            array = array2;
            ((Throwable)request).printStackTrace();
        }
        finally {
            if (array != null) {
                ((HttpURLConnection)(Object)array).disconnect();
            }
        }
    }
    
    protected void onPostExecute(final Boolean b) {
        while (true) {
            if (this.mProgressDialog == null) {
                break Label_0014;
            }
            try {
                this.mProgressDialog.dismiss();
                if (this.mHandler != null) {
                    final Message message = new Message();
                    final Bundle data = new Bundle();
                    data.putBoolean("success", (boolean)b);
                    message.setData(data);
                    this.mHandler.sendMessage(message);
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
                continue;
            }
            break;
        }
    }
    
    protected void onPreExecute() {
        if ((this.mProgressDialog == null || !this.mProgressDialog.isShowing()) && this.mShowProgressDialog) {
            this.mProgressDialog = ProgressDialog.show(this.mContext, (CharSequence)"", (CharSequence)"Please wait...", true, false);
        }
    }
    
    public void setShowProgressDialog(final boolean mShowProgressDialog) {
        this.mShowProgressDialog = mShowProgressDialog;
    }
}
