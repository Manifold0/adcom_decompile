package com.facebook.share.widget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.facebook.FacebookCallback;
import com.facebook.internal.AppCall;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.internal.CallbackManagerImpl.Callback;
import com.facebook.internal.CallbackManagerImpl.RequestCodeOffset;
import com.facebook.internal.DialogPresenter;
import com.facebook.internal.FacebookDialogBase;
import com.facebook.internal.FacebookDialogBase.ModeHandler;
import com.facebook.internal.FragmentWrapper;
import com.facebook.share.internal.ResultProcessor;
import com.facebook.share.internal.ShareInternalUtility;
import com.facebook.share.internal.WebDialogParameters;
import com.facebook.share.model.AppGroupCreationContent;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class CreateAppGroupDialog extends FacebookDialogBase<AppGroupCreationContent, Result> {
    private static final int DEFAULT_REQUEST_CODE = RequestCodeOffset.AppGroupCreate.toRequestCode();
    private static final String GAME_GROUP_CREATION_DIALOG = "game_group_create";

    @Deprecated
    public static final class Result {
        private final String id;

        private Result(String id) {
            this.id = id;
        }

        public String getId() {
            return this.id;
        }
    }

    private class WebHandler extends ModeHandler {
        private WebHandler() {
            super(CreateAppGroupDialog.this);
        }

        public boolean canShow(AppGroupCreationContent content, boolean isBestEffort) {
            return true;
        }

        public AppCall createAppCall(AppGroupCreationContent content) {
            AppCall appCall = CreateAppGroupDialog.this.createBaseAppCall();
            DialogPresenter.setupAppCallForWebDialog(appCall, CreateAppGroupDialog.GAME_GROUP_CREATION_DIALOG, WebDialogParameters.create(content));
            return appCall;
        }
    }

    @Deprecated
    public static boolean canShow() {
        return true;
    }

    @Deprecated
    public static void show(Activity activity, AppGroupCreationContent appGroupCreationContent) {
        new CreateAppGroupDialog(activity).show(appGroupCreationContent);
    }

    @Deprecated
    public static void show(Fragment fragment, AppGroupCreationContent appGroupCreationContent) {
        show(new FragmentWrapper(fragment), appGroupCreationContent);
    }

    @Deprecated
    public static void show(android.app.Fragment fragment, AppGroupCreationContent appGroupCreationContent) {
        show(new FragmentWrapper(fragment), appGroupCreationContent);
    }

    private static void show(FragmentWrapper fragmentWrapper, AppGroupCreationContent appGroupCreationContent) {
        new CreateAppGroupDialog(fragmentWrapper).show(appGroupCreationContent);
    }

    @Deprecated
    public CreateAppGroupDialog(Activity activity) {
        super(activity, DEFAULT_REQUEST_CODE);
    }

    @Deprecated
    public CreateAppGroupDialog(Fragment fragment) {
        this(new FragmentWrapper(fragment));
    }

    @Deprecated
    public CreateAppGroupDialog(android.app.Fragment fragment) {
        this(new FragmentWrapper(fragment));
    }

    private CreateAppGroupDialog(FragmentWrapper fragmentWrapper) {
        super(fragmentWrapper, DEFAULT_REQUEST_CODE);
    }

    protected void registerCallbackImpl(CallbackManagerImpl callbackManager, final FacebookCallback<Result> callback) {
        final ResultProcessor resultProcessor = callback == null ? null : new ResultProcessor(callback) {
            public void onSuccess(AppCall appCall, Bundle results) {
                callback.onSuccess(new Result(results.getString("id")));
            }
        };
        callbackManager.registerCallback(getRequestCode(), new Callback() {
            public boolean onActivityResult(int resultCode, Intent data) {
                return ShareInternalUtility.handleActivityResult(CreateAppGroupDialog.this.getRequestCode(), resultCode, data, resultProcessor);
            }
        });
    }

    protected AppCall createBaseAppCall() {
        return new AppCall(getRequestCode());
    }

    protected List<ModeHandler> getOrderedModeHandlers() {
        ArrayList<ModeHandler> handlers = new ArrayList();
        handlers.add(new WebHandler());
        return handlers;
    }
}
