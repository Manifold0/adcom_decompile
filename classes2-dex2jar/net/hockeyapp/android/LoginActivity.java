// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android;

import android.os.Message;
import java.lang.ref.WeakReference;
import android.content.Intent;
import android.view.KeyEvent;
import android.os.Bundle;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import android.os.AsyncTask;
import net.hockeyapp.android.utils.AsyncTaskUtils;
import java.util.Map;
import android.text.TextUtils;
import java.util.HashMap;
import android.widget.Toast;
import android.content.Context;
import net.hockeyapp.android.utils.Util;
import android.view.View;
import android.view.View$OnClickListener;
import android.widget.TextView;
import android.widget.EditText;
import net.hockeyapp.android.tasks.LoginTask;
import android.os.Handler;
import android.widget.Button;
import android.app.Activity;

public class LoginActivity extends Activity
{
    public static final String EXTRA_MODE = "mode";
    public static final String EXTRA_SECRET = "secret";
    public static final String EXTRA_URL = "url";
    private Button mButtonLogin;
    private Handler mLoginHandler;
    private LoginTask mLoginTask;
    private int mMode;
    private String mSecret;
    private String mUrl;
    
    private void configureView() {
        if (this.mMode == 1) {
            ((EditText)this.findViewById(R$id.input_password)).setVisibility(4);
        }
        final TextView textView = (TextView)this.findViewById(R$id.text_headline);
        int text;
        if (this.mMode == 1) {
            text = R$string.hockeyapp_login_headline_text_email_only;
        }
        else {
            text = R$string.hockeyapp_login_headline_text;
        }
        textView.setText(text);
        (this.mButtonLogin = (Button)this.findViewById(R$id.button_login)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                LoginActivity.this.performAuthentication();
            }
        });
    }
    
    private void initLoginHandler() {
        this.mLoginHandler = new LoginHandler(this);
    }
    
    private void performAuthentication() {
        if (!Util.isConnectedToNetwork((Context)this)) {
            Toast.makeText((Context)this, R$string.hockeyapp_error_no_network_message, 1).show();
            return;
        }
        final String string = ((EditText)this.findViewById(R$id.input_email)).getText().toString();
        final String string2 = ((EditText)this.findViewById(R$id.input_password)).getText().toString();
        int n = 0;
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        if (this.mMode == 1) {
            if (!TextUtils.isEmpty((CharSequence)string)) {
                n = 1;
            }
            else {
                n = 0;
            }
            hashMap.put("email", string);
            hashMap.put("authcode", this.md5(this.mSecret + string));
        }
        else if (this.mMode == 2) {
            if (!TextUtils.isEmpty((CharSequence)string) && !TextUtils.isEmpty((CharSequence)string2)) {
                n = 1;
            }
            else {
                n = 0;
            }
            hashMap.put("email", string);
            hashMap.put("password", string2);
        }
        if (n != 0) {
            AsyncTaskUtils.execute(this.mLoginTask = new LoginTask((Context)this, this.mLoginHandler, this.mUrl, this.mMode, hashMap));
            return;
        }
        Toast.makeText((Context)this, (CharSequence)this.getString(R$string.hockeyapp_login_missing_credentials_toast), 1).show();
    }
    
    public String md5(String s) {
        try {
            final MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(s.getBytes());
            final byte[] digest = instance.digest();
            final StringBuilder sb = new StringBuilder();
            for (int length = digest.length, i = 0; i < length; ++i) {
                for (s = Integer.toHexString(digest[i] & 0xFF); s.length() < 2; s = "0" + s) {}
                sb.append(s);
            }
            s = sb.toString();
            return s;
        }
        catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return "";
        }
    }
    
    protected void onCreate(Bundle extras) {
        super.onCreate(extras);
        this.setContentView(R$layout.hockeyapp_activity_login);
        extras = this.getIntent().getExtras();
        if (extras != null) {
            this.mUrl = extras.getString("url");
            this.mSecret = extras.getString("secret");
            this.mMode = extras.getInt("mode");
        }
        this.configureView();
        this.initLoginHandler();
        final Object lastNonConfigurationInstance = this.getLastNonConfigurationInstance();
        if (lastNonConfigurationInstance != null) {
            (this.mLoginTask = (LoginTask)lastNonConfigurationInstance).attach((Context)this, this.mLoginHandler);
        }
    }
    
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        if (n == 4) {
            if (LoginManager.listener == null) {
                final Intent intent = new Intent((Context)this, (Class)LoginManager.mainActivity);
                intent.setFlags(67108864);
                intent.putExtra("net.hockeyapp.android.EXIT", true);
                this.startActivity(intent);
                return true;
            }
            LoginManager.listener.onBack();
        }
        return super.onKeyDown(n, keyEvent);
    }
    
    public Object onRetainNonConfigurationInstance() {
        if (this.mLoginTask != null) {
            this.mLoginTask.detach();
        }
        return this.mLoginTask;
    }
    
    private static class LoginHandler extends Handler
    {
        private final WeakReference<Activity> mWeakActivity;
        
        public LoginHandler(final Activity activity) {
            this.mWeakActivity = new WeakReference<Activity>(activity);
        }
        
        public void handleMessage(final Message message) {
            final Activity activity = this.mWeakActivity.get();
            if (activity != null) {
                if (!message.getData().getBoolean("success")) {
                    Toast.makeText((Context)activity, (CharSequence)"Login failed. Check your credentials.", 1).show();
                    return;
                }
                activity.finish();
                if (LoginManager.listener != null) {
                    LoginManager.listener.onSuccess();
                }
            }
        }
    }
}
