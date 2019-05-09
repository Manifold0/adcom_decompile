// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.unity;

import com.facebook.FacebookException;
import com.facebook.share.widget.AppInviteDialog$Result;
import com.facebook.FacebookCallback;
import android.app.Activity;
import com.facebook.share.widget.AppInviteDialog;
import java.io.Serializable;
import com.facebook.share.model.AppInviteContent$Builder;
import android.os.Bundle;

public class AppInviteDialogActivity extends BaseActivity
{
    public static final String DIALOG_PARAMS = "dialog_params";
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        final UnityMessage unityMessage = new UnityMessage("OnAppInviteComplete");
        final Bundle bundleExtra = this.getIntent().getBundleExtra("dialog_params");
        final AppInviteContent$Builder appInviteContent$Builder = new AppInviteContent$Builder();
        if (bundleExtra.containsKey("callback_id")) {
            unityMessage.put("callback_id", bundleExtra.getString("callback_id"));
        }
        if (bundleExtra.containsKey("appLinkUrl")) {
            appInviteContent$Builder.setApplinkUrl(bundleExtra.getString("appLinkUrl"));
        }
        if (bundleExtra.containsKey("previewImageUrl")) {
            appInviteContent$Builder.setPreviewImageUrl(bundleExtra.getString("previewImageUrl"));
        }
        final AppInviteDialog appInviteDialog = new AppInviteDialog((Activity)this);
        appInviteDialog.registerCallback(this.mCallbackManager, (FacebookCallback)new FacebookCallback<AppInviteDialog$Result>() {
            public void onCancel() {
                unityMessage.putCancelled();
                unityMessage.send();
            }
            
            public void onError(final FacebookException ex) {
                unityMessage.sendError(ex.getMessage());
            }
            
            public void onSuccess(final AppInviteDialog$Result appInviteDialog$Result) {
                unityMessage.put("didComplete", true);
                unityMessage.send();
            }
        });
        appInviteDialog.show(appInviteContent$Builder.build());
    }
}
