// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.widget;

import com.facebook.internal.DialogPresenter;
import com.facebook.share.internal.WebDialogParameters;
import com.facebook.share.internal.GameRequestValidation;
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
import com.facebook.share.model.GameRequestContent;
import com.facebook.internal.FacebookDialogBase;

public class GameRequestDialog extends FacebookDialogBase<GameRequestContent, Result>
{
    private static final int DEFAULT_REQUEST_CODE;
    private static final String GAME_REQUEST_DIALOG = "apprequests";
    
    static {
        DEFAULT_REQUEST_CODE = CallbackManagerImpl$RequestCodeOffset.GameRequest.toRequestCode();
    }
    
    public GameRequestDialog(final Activity activity) {
        super(activity, GameRequestDialog.DEFAULT_REQUEST_CODE);
    }
    
    public GameRequestDialog(final Fragment fragment) {
        this(new FragmentWrapper(fragment));
    }
    
    public GameRequestDialog(final android.support.v4.app.Fragment fragment) {
        this(new FragmentWrapper(fragment));
    }
    
    private GameRequestDialog(final FragmentWrapper fragmentWrapper) {
        super(fragmentWrapper, GameRequestDialog.DEFAULT_REQUEST_CODE);
    }
    
    public static boolean canShow() {
        return true;
    }
    
    public static void show(final Activity activity, final GameRequestContent gameRequestContent) {
        new GameRequestDialog(activity).show((Object)gameRequestContent);
    }
    
    public static void show(final Fragment fragment, final GameRequestContent gameRequestContent) {
        show(new FragmentWrapper(fragment), gameRequestContent);
    }
    
    public static void show(final android.support.v4.app.Fragment fragment, final GameRequestContent gameRequestContent) {
        show(new FragmentWrapper(fragment), gameRequestContent);
    }
    
    private static void show(final FragmentWrapper fragmentWrapper, final GameRequestContent gameRequestContent) {
        new GameRequestDialog(fragmentWrapper).show((Object)gameRequestContent);
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
                    if (bundle != null) {
                        facebookCallback.onSuccess((Object)new Result(bundle));
                        return;
                    }
                    this.onCancel(appCall);
                }
            };
        }
        callbackManagerImpl.registerCallback(this.getRequestCode(), (CallbackManagerImpl$Callback)new CallbackManagerImpl$Callback() {
            public boolean onActivityResult(final int n, final Intent intent) {
                return ShareInternalUtility.handleActivityResult(GameRequestDialog.this.getRequestCode(), n, intent, resultProcessor);
            }
        });
    }
    
    public static final class Result
    {
        String requestId;
        List<String> to;
        
        private Result(final Bundle bundle) {
            this.requestId = bundle.getString("request");
            this.to = new ArrayList<String>();
            while (bundle.containsKey(String.format("to[%d]", this.to.size()))) {
                this.to.add(bundle.getString(String.format("to[%d]", this.to.size())));
            }
        }
        
        public String getRequestId() {
            return this.requestId;
        }
        
        public List<String> getRequestRecipients() {
            return this.to;
        }
    }
    
    private class WebHandler extends FacebookDialogBase$ModeHandler
    {
        private WebHandler() {
            super((FacebookDialogBase)GameRequestDialog.this);
        }
        
        public boolean canShow(final GameRequestContent gameRequestContent, final boolean b) {
            return true;
        }
        
        public AppCall createAppCall(final GameRequestContent gameRequestContent) {
            GameRequestValidation.validate(gameRequestContent);
            final AppCall baseAppCall = GameRequestDialog.this.createBaseAppCall();
            DialogPresenter.setupAppCallForWebDialog(baseAppCall, "apprequests", WebDialogParameters.create(gameRequestContent));
            return baseAppCall;
        }
    }
}
