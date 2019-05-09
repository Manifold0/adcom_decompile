// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.login.widget;

import android.content.res.TypedArray;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import com.facebook.Profile;
import android.os.Bundle;
import com.facebook.appevents.AppEventsLogger;
import java.util.Collection;
import java.util.Collections;
import com.facebook.internal.LoginAuthorizationType;
import java.util.Arrays;
import com.facebook.login.LoginResult;
import com.facebook.FacebookCallback;
import com.facebook.CallbackManager;
import android.graphics.Paint$FontMetrics;
import android.graphics.Canvas;
import java.util.List;
import com.facebook.login.LoginBehavior;
import com.facebook.login.R$style;
import com.facebook.internal.CallbackManagerImpl$RequestCodeOffset;
import com.facebook.login.DefaultAudience;
import android.graphics.drawable.Drawable;
import android.support.v7.content.res.AppCompatResources;
import com.facebook.login.R$drawable;
import com.facebook.common.R$color;
import android.view.View$OnClickListener;
import android.content.res.Resources;
import com.facebook.AccessToken;
import com.facebook.login.R$styleable;
import com.facebook.login.R$string;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.Utility;
import com.facebook.FacebookSdk;
import android.view.View;
import android.app.Activity;
import com.facebook.internal.FetchedAppSettings;
import android.util.AttributeSet;
import android.content.Context;
import com.facebook.login.LoginManager;
import com.facebook.AccessTokenTracker;
import com.facebook.FacebookButtonBase;

public class LoginButton extends FacebookButtonBase
{
    private static final String TAG;
    private AccessTokenTracker accessTokenTracker;
    private boolean confirmLogout;
    private String loginLogoutEventName;
    private LoginManager loginManager;
    private String loginText;
    private String logoutText;
    private LoginButtonProperties properties;
    private boolean toolTipChecked;
    private long toolTipDisplayTime;
    private ToolTipMode toolTipMode;
    private ToolTipPopup toolTipPopup;
    private ToolTipPopup.Style toolTipStyle;
    
    static {
        TAG = LoginButton.class.getName();
    }
    
    public LoginButton(final Context context) {
        super(context, (AttributeSet)null, 0, 0, "fb_login_button_create", "fb_login_button_did_tap");
        this.properties = new LoginButtonProperties();
        this.loginLogoutEventName = "fb_login_view_usage";
        this.toolTipStyle = ToolTipPopup.Style.BLUE;
        this.toolTipDisplayTime = 6000L;
    }
    
    public LoginButton(final Context context, final AttributeSet set) {
        super(context, set, 0, 0, "fb_login_button_create", "fb_login_button_did_tap");
        this.properties = new LoginButtonProperties();
        this.loginLogoutEventName = "fb_login_view_usage";
        this.toolTipStyle = ToolTipPopup.Style.BLUE;
        this.toolTipDisplayTime = 6000L;
    }
    
    public LoginButton(final Context context, final AttributeSet set, final int n) {
        super(context, set, n, 0, "fb_login_button_create", "fb_login_button_did_tap");
        this.properties = new LoginButtonProperties();
        this.loginLogoutEventName = "fb_login_view_usage";
        this.toolTipStyle = ToolTipPopup.Style.BLUE;
        this.toolTipDisplayTime = 6000L;
    }
    
    static /* synthetic */ Activity access$100(final LoginButton loginButton) {
        return loginButton.getActivity();
    }
    
    static /* synthetic */ void access$300(final LoginButton loginButton, final View view) {
        loginButton.callExternalOnClickListener(view);
    }
    
    static /* synthetic */ Activity access$800(final LoginButton loginButton) {
        return loginButton.getActivity();
    }
    
    static /* synthetic */ Activity access$900(final LoginButton loginButton) {
        return loginButton.getActivity();
    }
    
    private void checkToolTipSettings() {
        switch (this.toolTipMode) {
            default: {}
            case AUTOMATIC: {
                FacebookSdk.getExecutor().execute(new Runnable() {
                    final /* synthetic */ String val$appId = Utility.getMetadataApplicationId(LoginButton.this.getContext());
                    
                    @Override
                    public void run() {
                        LoginButton.access$100(LoginButton.this).runOnUiThread((Runnable)new Runnable() {
                            final /* synthetic */ FetchedAppSettings val$settings = FetchedAppSettingsManager.queryAppSettings(LoginButton$1.this.val$appId, false);
                            
                            @Override
                            public void run() {
                                LoginButton.this.showToolTipPerSettings(this.val$settings);
                            }
                        });
                    }
                });
            }
            case DISPLAY_ALWAYS: {
                this.displayToolTip(this.getResources().getString(R$string.com_facebook_tooltip_default));
            }
        }
    }
    
    private void displayToolTip(final String s) {
        (this.toolTipPopup = new ToolTipPopup(s, (View)this)).setStyle(this.toolTipStyle);
        this.toolTipPopup.setNuxDisplayTime(this.toolTipDisplayTime);
        this.toolTipPopup.show();
    }
    
    private int measureButtonWidth(final String s) {
        return this.getCompoundPaddingLeft() + this.getCompoundDrawablePadding() + this.measureTextWidth(s) + this.getCompoundPaddingRight();
    }
    
    private void parseLoginButtonAttributes(Context obtainStyledAttributes, final AttributeSet set, final int n, final int n2) {
        this.toolTipMode = ToolTipMode.DEFAULT;
        obtainStyledAttributes = (Context)obtainStyledAttributes.getTheme().obtainStyledAttributes(set, R$styleable.com_facebook_login_view, n, n2);
        try {
            this.confirmLogout = ((TypedArray)obtainStyledAttributes).getBoolean(R$styleable.com_facebook_login_view_com_facebook_confirm_logout, true);
            this.loginText = ((TypedArray)obtainStyledAttributes).getString(R$styleable.com_facebook_login_view_com_facebook_login_text);
            this.logoutText = ((TypedArray)obtainStyledAttributes).getString(R$styleable.com_facebook_login_view_com_facebook_logout_text);
            this.toolTipMode = ToolTipMode.fromInt(((TypedArray)obtainStyledAttributes).getInt(R$styleable.com_facebook_login_view_com_facebook_tooltip_mode, ToolTipMode.DEFAULT.getValue()));
        }
        finally {
            ((TypedArray)obtainStyledAttributes).recycle();
        }
    }
    
    private void setButtonText() {
        final Resources resources = this.getResources();
        if (!this.isInEditMode() && AccessToken.isCurrentAccessTokenActive()) {
            String text;
            if (this.logoutText != null) {
                text = this.logoutText;
            }
            else {
                text = resources.getString(R$string.com_facebook_loginview_log_out_button);
            }
            this.setText((CharSequence)text);
            return;
        }
        if (this.loginText != null) {
            this.setText((CharSequence)this.loginText);
            return;
        }
        final String string = resources.getString(R$string.com_facebook_loginview_log_in_button_continue);
        final int width = this.getWidth();
        String string2 = string;
        if (width != 0) {
            string2 = string;
            if (this.measureButtonWidth(string) > width) {
                string2 = resources.getString(R$string.com_facebook_loginview_log_in_button);
            }
        }
        this.setText((CharSequence)string2);
    }
    
    private void showToolTipPerSettings(final FetchedAppSettings fetchedAppSettings) {
        if (fetchedAppSettings != null && fetchedAppSettings.getNuxEnabled() && this.getVisibility() == 0) {
            this.displayToolTip(fetchedAppSettings.getNuxContent());
        }
    }
    
    public void clearPermissions() {
        this.properties.clearPermissions();
    }
    
    protected void configureButton(final Context context, final AttributeSet set, final int n, final int n2) {
        super.configureButton(context, set, n, n2);
        this.setInternalOnClickListener((View$OnClickListener)this.getNewLoginClickListener());
        this.parseLoginButtonAttributes(context, set, n, n2);
        if (this.isInEditMode()) {
            this.setBackgroundColor(this.getResources().getColor(R$color.com_facebook_blue));
            this.loginText = "Continue with Facebook";
        }
        else {
            this.accessTokenTracker = new AccessTokenTracker() {
                protected void onCurrentAccessTokenChanged(final AccessToken accessToken, final AccessToken accessToken2) {
                    LoginButton.this.setButtonText();
                }
            };
        }
        this.setButtonText();
        this.setCompoundDrawablesWithIntrinsicBounds(AppCompatResources.getDrawable(this.getContext(), R$drawable.com_facebook_button_login_logo), (Drawable)null, (Drawable)null, (Drawable)null);
    }
    
    public void dismissToolTip() {
        if (this.toolTipPopup != null) {
            this.toolTipPopup.dismiss();
            this.toolTipPopup = null;
        }
    }
    
    public DefaultAudience getDefaultAudience() {
        return this.properties.getDefaultAudience();
    }
    
    protected int getDefaultRequestCode() {
        return CallbackManagerImpl$RequestCodeOffset.Login.toRequestCode();
    }
    
    protected int getDefaultStyleResource() {
        return R$style.com_facebook_loginview_default_style;
    }
    
    public LoginBehavior getLoginBehavior() {
        return this.properties.getLoginBehavior();
    }
    
    LoginManager getLoginManager() {
        if (this.loginManager == null) {
            this.loginManager = LoginManager.getInstance();
        }
        return this.loginManager;
    }
    
    protected LoginClickListener getNewLoginClickListener() {
        return new LoginClickListener();
    }
    
    List<String> getPermissions() {
        return this.properties.getPermissions();
    }
    
    public long getToolTipDisplayTime() {
        return this.toolTipDisplayTime;
    }
    
    public ToolTipMode getToolTipMode() {
        return this.toolTipMode;
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.accessTokenTracker != null && !this.accessTokenTracker.isTracking()) {
            this.accessTokenTracker.startTracking();
            this.setButtonText();
        }
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.accessTokenTracker != null) {
            this.accessTokenTracker.stopTracking();
        }
        this.dismissToolTip();
    }
    
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        if (!this.toolTipChecked && !this.isInEditMode()) {
            this.toolTipChecked = true;
            this.checkToolTipSettings();
        }
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        super.onLayout(b, n, n2, n3, n4);
        this.setButtonText();
    }
    
    protected void onMeasure(final int n, int compoundPaddingTop) {
        final Paint$FontMetrics fontMetrics = this.getPaint().getFontMetrics();
        compoundPaddingTop = this.getCompoundPaddingTop();
        final int n2 = (int)Math.ceil(Math.abs(fontMetrics.top) + Math.abs(fontMetrics.bottom));
        final int compoundPaddingBottom = this.getCompoundPaddingBottom();
        final Resources resources = this.getResources();
        String s;
        if ((s = this.loginText) == null) {
            s = resources.getString(R$string.com_facebook_loginview_log_in_button_continue);
            final int measureButtonWidth = this.measureButtonWidth(s);
            if (resolveSize(measureButtonWidth, n) < measureButtonWidth) {
                s = resources.getString(R$string.com_facebook_loginview_log_in_button);
            }
        }
        final int measureButtonWidth2 = this.measureButtonWidth(s);
        String s2;
        if ((s2 = this.logoutText) == null) {
            s2 = resources.getString(R$string.com_facebook_loginview_log_out_button);
        }
        this.setMeasuredDimension(resolveSize(Math.max(measureButtonWidth2, this.measureButtonWidth(s2)), n), compoundPaddingTop + n2 + compoundPaddingBottom);
    }
    
    protected void onVisibilityChanged(final View view, final int n) {
        super.onVisibilityChanged(view, n);
        if (n != 0) {
            this.dismissToolTip();
        }
    }
    
    public void registerCallback(final CallbackManager callbackManager, final FacebookCallback<LoginResult> facebookCallback) {
        this.getLoginManager().registerCallback(callbackManager, (FacebookCallback)facebookCallback);
    }
    
    public void setDefaultAudience(final DefaultAudience defaultAudience) {
        this.properties.setDefaultAudience(defaultAudience);
    }
    
    public void setLoginBehavior(final LoginBehavior loginBehavior) {
        this.properties.setLoginBehavior(loginBehavior);
    }
    
    void setLoginManager(final LoginManager loginManager) {
        this.loginManager = loginManager;
    }
    
    void setProperties(final LoginButtonProperties properties) {
        this.properties = properties;
    }
    
    public void setPublishPermissions(final List<String> publishPermissions) {
        this.properties.setPublishPermissions(publishPermissions);
    }
    
    public void setPublishPermissions(final String... array) {
        this.properties.setPublishPermissions(Arrays.asList(array));
    }
    
    public void setReadPermissions(final List<String> readPermissions) {
        this.properties.setReadPermissions(readPermissions);
    }
    
    public void setReadPermissions(final String... array) {
        this.properties.setReadPermissions(Arrays.asList(array));
    }
    
    public void setToolTipDisplayTime(final long toolTipDisplayTime) {
        this.toolTipDisplayTime = toolTipDisplayTime;
    }
    
    public void setToolTipMode(final ToolTipMode toolTipMode) {
        this.toolTipMode = toolTipMode;
    }
    
    public void setToolTipStyle(final ToolTipPopup.Style toolTipStyle) {
        this.toolTipStyle = toolTipStyle;
    }
    
    public void unregisterCallback(final CallbackManager callbackManager) {
        this.getLoginManager().unregisterCallback(callbackManager);
    }
    
    static class LoginButtonProperties
    {
        private LoginAuthorizationType authorizationType;
        private DefaultAudience defaultAudience;
        private LoginBehavior loginBehavior;
        private List<String> permissions;
        
        LoginButtonProperties() {
            this.defaultAudience = DefaultAudience.FRIENDS;
            this.permissions = Collections.emptyList();
            this.authorizationType = null;
            this.loginBehavior = LoginBehavior.NATIVE_WITH_FALLBACK;
        }
        
        public void clearPermissions() {
            this.permissions = null;
            this.authorizationType = null;
        }
        
        public DefaultAudience getDefaultAudience() {
            return this.defaultAudience;
        }
        
        public LoginBehavior getLoginBehavior() {
            return this.loginBehavior;
        }
        
        List<String> getPermissions() {
            return this.permissions;
        }
        
        public void setDefaultAudience(final DefaultAudience defaultAudience) {
            this.defaultAudience = defaultAudience;
        }
        
        public void setLoginBehavior(final LoginBehavior loginBehavior) {
            this.loginBehavior = loginBehavior;
        }
        
        public void setPublishPermissions(final List<String> permissions) {
            if (LoginAuthorizationType.READ.equals((Object)this.authorizationType)) {
                throw new UnsupportedOperationException("Cannot call setPublishPermissions after setReadPermissions has been called.");
            }
            if (Utility.isNullOrEmpty((Collection)permissions)) {
                throw new IllegalArgumentException("Permissions for publish actions cannot be null or empty.");
            }
            this.permissions = permissions;
            this.authorizationType = LoginAuthorizationType.PUBLISH;
        }
        
        public void setReadPermissions(final List<String> permissions) {
            if (LoginAuthorizationType.PUBLISH.equals((Object)this.authorizationType)) {
                throw new UnsupportedOperationException("Cannot call setReadPermissions after setPublishPermissions has been called.");
            }
            this.permissions = permissions;
            this.authorizationType = LoginAuthorizationType.READ;
        }
    }
    
    protected class LoginClickListener implements View$OnClickListener
    {
        protected LoginManager getLoginManager() {
            final LoginManager instance = LoginManager.getInstance();
            instance.setDefaultAudience(LoginButton.this.getDefaultAudience());
            instance.setLoginBehavior(LoginButton.this.getLoginBehavior());
            return instance;
        }
        
        public void onClick(final View view) {
            final int n = 1;
            LoginButton.access$300(LoginButton.this, view);
            final AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
            if (AccessToken.isCurrentAccessTokenActive()) {
                this.performLogout(LoginButton.this.getContext());
            }
            else {
                this.performLogin();
            }
            final AppEventsLogger logger = AppEventsLogger.newLogger(LoginButton.this.getContext());
            final Bundle bundle = new Bundle();
            int n2;
            if (currentAccessToken != null) {
                n2 = 0;
            }
            else {
                n2 = 1;
            }
            bundle.putInt("logging_in", n2);
            int n3;
            if (AccessToken.isCurrentAccessTokenActive()) {
                n3 = n;
            }
            else {
                n3 = 0;
            }
            bundle.putInt("access_token_expired", n3);
            logger.logSdkEvent(LoginButton.this.loginLogoutEventName, (Double)null, bundle);
        }
        
        protected void performLogin() {
            final LoginManager loginManager = this.getLoginManager();
            if (LoginAuthorizationType.PUBLISH.equals((Object)LoginButton.this.properties.authorizationType)) {
                if (LoginButton.this.getFragment() != null) {
                    loginManager.logInWithPublishPermissions(LoginButton.this.getFragment(), (Collection)LoginButton.this.properties.permissions);
                    return;
                }
                if (LoginButton.this.getNativeFragment() != null) {
                    loginManager.logInWithPublishPermissions(LoginButton.this.getNativeFragment(), (Collection)LoginButton.this.properties.permissions);
                    return;
                }
                loginManager.logInWithPublishPermissions(LoginButton.access$800(LoginButton.this), (Collection)LoginButton.this.properties.permissions);
            }
            else {
                if (LoginButton.this.getFragment() != null) {
                    loginManager.logInWithReadPermissions(LoginButton.this.getFragment(), (Collection)LoginButton.this.properties.permissions);
                    return;
                }
                if (LoginButton.this.getNativeFragment() != null) {
                    loginManager.logInWithReadPermissions(LoginButton.this.getNativeFragment(), (Collection)LoginButton.this.properties.permissions);
                    return;
                }
                loginManager.logInWithReadPermissions(LoginButton.access$900(LoginButton.this), (Collection)LoginButton.this.properties.permissions);
            }
        }
        
        protected void performLogout(final Context context) {
            final LoginManager loginManager = this.getLoginManager();
            if (LoginButton.this.confirmLogout) {
                final String string = LoginButton.this.getResources().getString(R$string.com_facebook_loginview_log_out_action);
                final String string2 = LoginButton.this.getResources().getString(R$string.com_facebook_loginview_cancel_action);
                final Profile currentProfile = Profile.getCurrentProfile();
                String message;
                if (currentProfile != null && currentProfile.getName() != null) {
                    message = String.format(LoginButton.this.getResources().getString(R$string.com_facebook_loginview_logged_in_as), currentProfile.getName());
                }
                else {
                    message = LoginButton.this.getResources().getString(R$string.com_facebook_loginview_logged_in_using_facebook);
                }
                final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(context);
                alertDialog$Builder.setMessage((CharSequence)message).setCancelable(true).setPositiveButton((CharSequence)string, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                    public void onClick(final DialogInterface dialogInterface, final int n) {
                        loginManager.logOut();
                    }
                }).setNegativeButton((CharSequence)string2, (DialogInterface$OnClickListener)null);
                alertDialog$Builder.create().show();
                return;
            }
            loginManager.logOut();
        }
    }
    
    public enum ToolTipMode
    {
        AUTOMATIC("automatic", 0);
        
        public static ToolTipMode DEFAULT;
        
        DISPLAY_ALWAYS("display_always", 1), 
        NEVER_DISPLAY("never_display", 2);
        
        private int intValue;
        private String stringValue;
        
        static {
            ToolTipMode.DEFAULT = ToolTipMode.AUTOMATIC;
        }
        
        private ToolTipMode(final String stringValue, final int intValue) {
            this.stringValue = stringValue;
            this.intValue = intValue;
        }
        
        public static ToolTipMode fromInt(final int n) {
            final ToolTipMode[] values = values();
            for (int length = values.length, i = 0; i < length; ++i) {
                final ToolTipMode toolTipMode = values[i];
                if (toolTipMode.getValue() == n) {
                    return toolTipMode;
                }
            }
            return null;
        }
        
        public int getValue() {
            return this.intValue;
        }
        
        @Override
        public String toString() {
            return this.stringValue;
        }
    }
}
