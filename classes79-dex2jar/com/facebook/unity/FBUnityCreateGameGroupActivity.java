// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.unity;

import com.facebook.FacebookException;
import com.facebook.share.widget.CreateAppGroupDialog$Result;
import com.facebook.FacebookCallback;
import android.app.Activity;
import com.facebook.share.widget.CreateAppGroupDialog;
import java.util.Locale;
import com.facebook.share.model.AppGroupCreationContent$AppGroupPrivacy;
import java.io.Serializable;
import com.facebook.share.model.AppGroupCreationContent$Builder;
import android.os.Bundle;

public class FBUnityCreateGameGroupActivity extends BaseActivity
{
    public static String CREATE_GAME_GROUP_PARAMS;
    
    static {
        FBUnityCreateGameGroupActivity.CREATE_GAME_GROUP_PARAMS = "create_game_group_params";
    }
    
    @Override
    protected void onCreate(Bundle bundleExtra) {
        super.onCreate(bundleExtra);
        final AppGroupCreationContent$Builder appGroupCreationContent$Builder = new AppGroupCreationContent$Builder();
        bundleExtra = this.getIntent().getBundleExtra(FBUnityCreateGameGroupActivity.CREATE_GAME_GROUP_PARAMS);
        final UnityMessage unityMessage = new UnityMessage("OnGroupCreateComplete");
        if (bundleExtra.containsKey("callback_id")) {
            unityMessage.put("callback_id", bundleExtra.getString("callback_id"));
        }
        if (bundleExtra.containsKey("name")) {
            appGroupCreationContent$Builder.setName(bundleExtra.getString("name"));
        }
        if (bundleExtra.containsKey("description")) {
            appGroupCreationContent$Builder.setDescription(bundleExtra.getString("name"));
        }
        if (bundleExtra.containsKey("privacy")) {
            final String string = bundleExtra.getString("privacy");
            AppGroupCreationContent$AppGroupPrivacy appGroupPrivacy = AppGroupCreationContent$AppGroupPrivacy.Closed;
            if (string.equalsIgnoreCase("closed")) {
                appGroupPrivacy = AppGroupCreationContent$AppGroupPrivacy.Closed;
            }
            else if (string.equalsIgnoreCase("open")) {
                appGroupPrivacy = AppGroupCreationContent$AppGroupPrivacy.Open;
            }
            else {
                unityMessage.sendError(String.format(Locale.ROOT, "Unknown privacy setting for group creation: %s", string));
                this.finish();
            }
            appGroupCreationContent$Builder.setAppGroupPrivacy(appGroupPrivacy);
        }
        final CreateAppGroupDialog createAppGroupDialog = new CreateAppGroupDialog((Activity)this);
        createAppGroupDialog.registerCallback(this.mCallbackManager, (FacebookCallback)new FacebookCallback<CreateAppGroupDialog$Result>() {
            public void onCancel() {
                unityMessage.putCancelled();
                unityMessage.send();
            }
            
            public void onError(final FacebookException ex) {
                unityMessage.sendError(ex.getLocalizedMessage());
            }
            
            public void onSuccess(final CreateAppGroupDialog$Result createAppGroupDialog$Result) {
                unityMessage.put("id", createAppGroupDialog$Result.getId());
                unityMessage.send();
            }
        });
        createAppGroupDialog.show((Object)appGroupCreationContent$Builder.build());
    }
}
