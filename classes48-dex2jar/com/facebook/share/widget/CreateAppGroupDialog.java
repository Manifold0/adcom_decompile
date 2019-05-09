// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.widget;

import com.facebook.internal.DialogPresenter;
import com.facebook.share.internal.WebDialogParameters;
import com.facebook.share.internal.ShareInternalUtility;
import android.content.Intent;
import com.facebook.internal.CallbackManagerImpl$Callback;
import android.os.Bundle;
import com.facebook.share.internal.ResultProcessor;
import com.facebook.FacebookCallback;
import com.facebook.internal.CallbackManagerImpl;
import java.util.ArrayList;
import com.facebook.internal.FacebookDialogBase$ModeHandler;
import java.util.List;
import com.facebook.internal.AppCall;
import com.facebook.internal.FragmentWrapper;
import android.app.Fragment;
import android.app.Activity;
import com.facebook.internal.CallbackManagerImpl$RequestCodeOffset;
import com.facebook.share.model.AppGroupCreationContent;
import com.facebook.internal.FacebookDialogBase;

@Deprecated
public class CreateAppGroupDialog extends FacebookDialogBase<AppGroupCreationContent, Result>
{
    private static final int DEFAULT_REQUEST_CODE;
    private static final String GAME_GROUP_CREATION_DIALOG = "game_group_create";
    
    static {
        DEFAULT_REQUEST_CODE = CallbackManagerImpl$RequestCodeOffset.AppGroupCreate.toRequestCode();
    }
    
    @Deprecated
    public CreateAppGroupDialog(final Activity activity) {
        super(activity, CreateAppGroupDialog.DEFAULT_REQUEST_CODE);
    }
    
    @Deprecated
    public CreateAppGroupDialog(final Fragment fragment) {
        this(new FragmentWrapper(fragment));
    }
    
    @Deprecated
    public CreateAppGroupDialog(final android.support.v4.app.Fragment fragment) {
        this(new FragmentWrapper(fragment));
    }
    
    private CreateAppGroupDialog(final FragmentWrapper fragmentWrapper) {
        super(fragmentWrapper, CreateAppGroupDialog.DEFAULT_REQUEST_CODE);
    }
    
    @Deprecated
    public static boolean canShow() {
        return true;
    }
    
    @Deprecated
    public static void show(final Activity activity, final AppGroupCreationContent appGroupCreationContent) {
        new CreateAppGroupDialog(activity).show((Object)appGroupCreationContent);
    }
    
    @Deprecated
    public static void show(final Fragment fragment, final AppGroupCreationContent appGroupCreationContent) {
        show(new FragmentWrapper(fragment), appGroupCreationContent);
    }
    
    @Deprecated
    public static void show(final android.support.v4.app.Fragment fragment, final AppGroupCreationContent appGroupCreationContent) {
        show(new FragmentWrapper(fragment), appGroupCreationContent);
    }
    
    private static void show(final FragmentWrapper fragmentWrapper, final AppGroupCreationContent appGroupCreationContent) {
        new CreateAppGroupDialog(fragmentWrapper).show((Object)appGroupCreationContent);
    }
    
    protected AppCall createBaseAppCall() {
        return new AppCall(this.getRequestCode());
    }
    
    protected List<FacebookDialogBase$ModeHandler> getOrderedModeHandlers() {
        final ArrayList<WebHandler> list = (ArrayList<WebHandler>)new ArrayList<FacebookDialogBase$ModeHandler>();
        list.add(new WebHandler());
        return (List<FacebookDialogBase$ModeHandler>)list;
    }
    
    protected void registerCallbackImpl(final CallbackManagerImpl callbackManagerImpl, final FacebookCallback<Result> facebookCallback) {
        ResultProcessor resultProcessor;
        if (facebookCallback == null) {
            resultProcessor = null;
        }
        else {
            resultProcessor = new ResultProcessor(facebookCallback) {
                public void onSuccess(final AppCall appCall, final Bundle bundle) {
                    facebookCallback.onSuccess((Object)new Result(bundle.getString("id")));
                }
            };
        }
        callbackManagerImpl.registerCallback(this.getRequestCode(), (CallbackManagerImpl$Callback)new CallbackManagerImpl$Callback() {
            public boolean onActivityResult(final int n, final Intent intent) {
                return ShareInternalUtility.handleActivityResult(CreateAppGroupDialog.this.getRequestCode(), n, intent, resultProcessor);
            }
        });
    }
    
    @Deprecated
    public static final class Result
    {
        private final String id;
        
        private Result(final String id) {
            this.id = id;
        }
        
        public String getId() {
            return this.id;
        }
    }
    
    private class WebHandler extends FacebookDialogBase$ModeHandler
    {
        private WebHandler() {
            super((FacebookDialogBase)CreateAppGroupDialog.this);
        }
        
        public boolean canShow(final AppGroupCreationContent appGroupCreationContent, final boolean b) {
            return true;
        }
        
        public AppCall createAppCall(final AppGroupCreationContent appGroupCreationContent) {
            final AppCall baseAppCall = CreateAppGroupDialog.this.createBaseAppCall();
            DialogPresenter.setupAppCallForWebDialog(baseAppCall, "game_group_create", WebDialogParameters.create(appGroupCreationContent));
            return baseAppCall;
        }
    }
}
