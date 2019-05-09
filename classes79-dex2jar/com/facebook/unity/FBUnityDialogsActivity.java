// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.unity;

import android.content.Intent;
import com.facebook.share.model.ShareContent;
import com.facebook.share.widget.ShareDialog$Mode;
import com.facebook.FacebookException;
import com.facebook.share.Sharer$Result;
import com.facebook.FacebookCallback;
import java.io.Serializable;
import android.app.Activity;
import com.facebook.share.widget.ShareDialog;
import android.util.Log;
import java.util.Locale;
import android.os.Bundle;

public class FBUnityDialogsActivity extends BaseActivity
{
    public static final String DIALOG_TYPE = "dialog_type";
    public static final String FEED_DIALOG_PARAMS = "feed_dialog_params";
    public static final String SHARE_DIALOG_PARAMS = "share_dialog_params";
    private static String TAG;
    
    static {
        FBUnityDialogsActivity.TAG = FBUnityDialogsActivity.class.getName();
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        final Intent intent = this.getIntent();
        Bundle bundle2;
        Object o;
        if (intent.hasExtra("share_dialog_params")) {
            bundle2 = intent.getBundleExtra("share_dialog_params");
            o = FBDialogUtils.createShareContentBuilder(bundle2).build();
        }
        else {
            if (!intent.hasExtra("feed_dialog_params")) {
                Log.e(FBUnityDialogsActivity.TAG, String.format(Locale.ROOT, "Failed to find extra %s or %s", "share_dialog_params", "feed_dialog_params"));
                this.finish();
                return;
            }
            bundle2 = intent.getBundleExtra("feed_dialog_params");
            o = FBDialogUtils.createFeedContentBuilder(bundle2).build();
        }
        final ShareDialog shareDialog = new ShareDialog((Activity)this);
        final UnityMessage unityMessage = new UnityMessage("OnShareLinkComplete");
        final String string = bundle2.getString("callback_id");
        if (string != null) {
            unityMessage.put("callback_id", string);
        }
        shareDialog.registerCallback(this.mCallbackManager, (FacebookCallback)new FacebookCallback<Sharer$Result>() {
            public void onCancel() {
                unityMessage.putCancelled();
                unityMessage.send();
            }
            
            public void onError(final FacebookException ex) {
                unityMessage.sendError(ex.getMessage());
            }
            
            public void onSuccess(final Sharer$Result sharer$Result) {
                if (sharer$Result.getPostId() != null) {
                    unityMessage.putID(sharer$Result.getPostId());
                }
                unityMessage.put("posted", true);
                unityMessage.send();
            }
        });
        shareDialog.show((ShareContent)o, (ShareDialog$Mode)this.getIntent().getSerializableExtra("dialog_type"));
    }
}
