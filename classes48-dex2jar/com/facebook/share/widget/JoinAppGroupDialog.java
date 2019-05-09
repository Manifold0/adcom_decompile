// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.widget;

import com.facebook.internal.DialogPresenter;
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
import com.facebook.internal.FacebookDialogBase;

@Deprecated
public class JoinAppGroupDialog extends FacebookDialogBase<String, Result>
{
    private static final int DEFAULT_REQUEST_CODE;
    private static final String JOIN_GAME_GROUP_DIALOG = "game_group_join";
    
    static {
        DEFAULT_REQUEST_CODE = CallbackManagerImpl$RequestCodeOffset.AppGroupJoin.toRequestCode();
    }
    
    @Deprecated
    public JoinAppGroupDialog(final Activity activity) {
        super(activity, JoinAppGroupDialog.DEFAULT_REQUEST_CODE);
    }
    
    @Deprecated
    public JoinAppGroupDialog(final Fragment fragment) {
        this(new FragmentWrapper(fragment));
    }
    
    @Deprecated
    public JoinAppGroupDialog(final android.support.v4.app.Fragment fragment) {
        this(new FragmentWrapper(fragment));
    }
    
    private JoinAppGroupDialog(final FragmentWrapper fragmentWrapper) {
        super(fragmentWrapper, JoinAppGroupDialog.DEFAULT_REQUEST_CODE);
    }
    
    @Deprecated
    public static boolean canShow() {
        return true;
    }
    
    @Deprecated
    public static void show(final Activity activity, final String s) {
        new JoinAppGroupDialog(activity).show((Object)s);
    }
    
    @Deprecated
    public static void show(final Fragment fragment, final String s) {
        show(new FragmentWrapper(fragment), s);
    }
    
    @Deprecated
    public static void show(final android.support.v4.app.Fragment fragment, final String s) {
        show(new FragmentWrapper(fragment), s);
    }
    
    private static void show(final FragmentWrapper fragmentWrapper, final String s) {
        new JoinAppGroupDialog(fragmentWrapper).show((Object)s);
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
                    facebookCallback.onSuccess((Object)new Result(bundle));
                }
            };
        }
        callbackManagerImpl.registerCallback(this.getRequestCode(), (CallbackManagerImpl$Callback)new CallbackManagerImpl$Callback() {
            public boolean onActivityResult(final int n, final Intent intent) {
                return ShareInternalUtility.handleActivityResult(JoinAppGroupDialog.this.getRequestCode(), n, intent, resultProcessor);
            }
        });
    }
    
    @Deprecated
    public static final class Result
    {
        private final Bundle data;
        
        private Result(final Bundle data) {
            this.data = data;
        }
        
        public Bundle getData() {
            return this.data;
        }
    }
    
    private class WebHandler extends FacebookDialogBase$ModeHandler
    {
        private WebHandler() {
            super((FacebookDialogBase)JoinAppGroupDialog.this);
        }
        
        public boolean canShow(final String s, final boolean b) {
            return true;
        }
        
        public AppCall createAppCall(final String s) {
            final AppCall baseAppCall = JoinAppGroupDialog.this.createBaseAppCall();
            final Bundle bundle = new Bundle();
            bundle.putString("id", s);
            DialogPresenter.setupAppCallForWebDialog(baseAppCall, "game_group_join", bundle);
            return baseAppCall;
        }
    }
}
