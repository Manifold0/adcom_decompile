// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.unity;

import com.facebook.FacebookException;
import com.facebook.share.widget.JoinAppGroupDialog$Result;
import com.facebook.FacebookCallback;
import android.app.Activity;
import com.facebook.share.widget.JoinAppGroupDialog;
import java.io.Serializable;
import android.os.Bundle;

public class FBUnityJoinGameGroupActivity extends BaseActivity
{
    public static String JOIN_GAME_GROUP_PARAMS;
    
    static {
        FBUnityJoinGameGroupActivity.JOIN_GAME_GROUP_PARAMS = "join_game_group_params";
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        final Bundle bundleExtra = this.getIntent().getBundleExtra(FBUnityJoinGameGroupActivity.JOIN_GAME_GROUP_PARAMS);
        final UnityMessage unityMessage = new UnityMessage("OnJoinGroupComplete");
        if (bundleExtra.containsKey("callback_id")) {
            unityMessage.put("callback_id", bundleExtra.getString("callback_id"));
        }
        String string = "";
        if (bundleExtra.containsKey("id")) {
            string = bundleExtra.getString("id");
        }
        final JoinAppGroupDialog joinAppGroupDialog = new JoinAppGroupDialog((Activity)this);
        joinAppGroupDialog.registerCallback(this.mCallbackManager, (FacebookCallback)new FacebookCallback<JoinAppGroupDialog$Result>() {
            public void onCancel() {
                unityMessage.putCancelled();
                unityMessage.send();
            }
            
            public void onError(final FacebookException ex) {
                unityMessage.sendError(ex.getLocalizedMessage());
            }
            
            public void onSuccess(final JoinAppGroupDialog$Result joinAppGroupDialog$Result) {
                unityMessage.put("didComplete", true);
                unityMessage.send();
            }
        });
        joinAppGroupDialog.show((Object)string);
    }
}
