// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.internal;

import com.facebook.LoggingBehavior;
import android.content.Intent;
import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.FacebookCallback;
import com.facebook.CallbackManager;
import java.util.Iterator;
import com.facebook.FacebookException;
import java.util.List;
import android.app.Activity;
import com.facebook.FacebookDialog;

public abstract class FacebookDialogBase<CONTENT, RESULT> implements FacebookDialog<CONTENT, RESULT>
{
    protected static final Object BASE_AUTOMATIC_MODE;
    private static final String TAG = "FacebookDialog";
    private final Activity activity;
    private final FragmentWrapper fragmentWrapper;
    private List<ModeHandler> modeHandlers;
    private int requestCode;
    
    static {
        BASE_AUTOMATIC_MODE = new Object();
    }
    
    protected FacebookDialogBase(final Activity activity, final int requestCode) {
        Validate.notNull((Object)activity, "activity");
        this.activity = activity;
        this.fragmentWrapper = null;
        this.requestCode = requestCode;
    }
    
    protected FacebookDialogBase(final FragmentWrapper fragmentWrapper, final int requestCode) {
        Validate.notNull((Object)fragmentWrapper, "fragmentWrapper");
        this.fragmentWrapper = fragmentWrapper;
        this.activity = null;
        this.requestCode = requestCode;
        if (fragmentWrapper.getActivity() == null) {
            throw new IllegalArgumentException("Cannot use a fragment that is not attached to an activity");
        }
    }
    
    private List<ModeHandler> cachedModeHandlers() {
        if (this.modeHandlers == null) {
            this.modeHandlers = this.getOrderedModeHandlers();
        }
        return this.modeHandlers;
    }
    
    private AppCall createAppCallForMode(final CONTENT content, final Object o) {
        Label_0102: {
            if (o != FacebookDialogBase.BASE_AUTOMATIC_MODE) {
                break Label_0102;
            }
            int n = 1;
        Label_0083_Outer:
            while (true) {
                final AppCall appCall = null;
                final Iterator<ModeHandler> iterator = this.cachedModeHandlers().iterator();
            Label_0083:
                while (true) {
                    ModeHandler modeHandler;
                    do {
                        final AppCall appCall2 = appCall;
                        if (!iterator.hasNext()) {
                            break Label_0083;
                        }
                        modeHandler = iterator.next();
                    } while ((n == 0 && !Utility.areObjectsEqual(modeHandler.getMode(), o)) || !modeHandler.canShow(content, true));
                    try {
                        final AppCall appCall2 = modeHandler.createAppCall(content);
                        AppCall baseAppCall;
                        if ((baseAppCall = appCall2) == null) {
                            baseAppCall = this.createBaseAppCall();
                            DialogPresenter.setupAppCallForCannotShowError(baseAppCall);
                        }
                        return baseAppCall;
                        n = 0;
                        continue Label_0083_Outer;
                    }
                    catch (FacebookException ex) {
                        final AppCall appCall2 = this.createBaseAppCall();
                        DialogPresenter.setupAppCallForValidationError(appCall2, ex);
                        continue Label_0083;
                    }
                    break;
                }
                break;
            }
        }
    }
    
    @Override
    public boolean canShow(final CONTENT content) {
        return this.canShowImpl(content, FacebookDialogBase.BASE_AUTOMATIC_MODE);
    }
    
    protected boolean canShowImpl(final CONTENT content, final Object o) {
        boolean b;
        if (o == FacebookDialogBase.BASE_AUTOMATIC_MODE) {
            b = true;
        }
        else {
            b = false;
        }
        for (final ModeHandler modeHandler : this.cachedModeHandlers()) {
            if ((b || Utility.areObjectsEqual(modeHandler.getMode(), o)) && modeHandler.canShow(content, false)) {
                return true;
            }
        }
        return false;
    }
    
    protected abstract AppCall createBaseAppCall();
    
    protected Activity getActivityContext() {
        if (this.activity != null) {
            return this.activity;
        }
        if (this.fragmentWrapper != null) {
            return this.fragmentWrapper.getActivity();
        }
        return null;
    }
    
    protected abstract List<ModeHandler> getOrderedModeHandlers();
    
    public int getRequestCode() {
        return this.requestCode;
    }
    
    @Override
    public final void registerCallback(final CallbackManager callbackManager, final FacebookCallback<RESULT> facebookCallback) {
        if (!(callbackManager instanceof CallbackManagerImpl)) {
            throw new FacebookException("Unexpected CallbackManager, please use the provided Factory.");
        }
        this.registerCallbackImpl((CallbackManagerImpl)callbackManager, facebookCallback);
    }
    
    @Override
    public final void registerCallback(final CallbackManager callbackManager, final FacebookCallback<RESULT> facebookCallback, final int requestCode) {
        this.setRequestCode(requestCode);
        this.registerCallback(callbackManager, facebookCallback);
    }
    
    protected abstract void registerCallbackImpl(final CallbackManagerImpl p0, final FacebookCallback<RESULT> p1);
    
    protected void setRequestCode(final int requestCode) {
        if (FacebookSdk.isFacebookRequestCode(requestCode)) {
            throw new IllegalArgumentException("Request code " + requestCode + " cannot be within the range reserved by the Facebook SDK.");
        }
        this.requestCode = requestCode;
    }
    
    @Override
    public void show(final CONTENT content) {
        this.showImpl(content, FacebookDialogBase.BASE_AUTOMATIC_MODE);
    }
    
    protected void showImpl(final CONTENT content, final Object o) {
        final AppCall appCallForMode = this.createAppCallForMode(content, o);
        if (appCallForMode != null) {
            if (this.fragmentWrapper == null) {
                DialogPresenter.present(appCallForMode, this.activity);
                return;
            }
            DialogPresenter.present(appCallForMode, this.fragmentWrapper);
        }
        else {
            Log.e("FacebookDialog", "No code path should ever result in a null appCall");
            if (FacebookSdk.isDebugEnabled()) {
                throw new IllegalStateException("No code path should ever result in a null appCall");
            }
        }
    }
    
    protected void startActivityForResult(final Intent intent, final int n) {
        final String s = null;
        String s2;
        if (this.activity != null) {
            this.activity.startActivityForResult(intent, n);
            s2 = s;
        }
        else if (this.fragmentWrapper != null) {
            if (this.fragmentWrapper.getNativeFragment() != null) {
                this.fragmentWrapper.getNativeFragment().startActivityForResult(intent, n);
                s2 = s;
            }
            else if (this.fragmentWrapper.getSupportFragment() != null) {
                this.fragmentWrapper.getSupportFragment().startActivityForResult(intent, n);
                s2 = s;
            }
            else {
                s2 = "Failed to find Activity or Fragment to startActivityForResult ";
            }
        }
        else {
            s2 = "Failed to find Activity or Fragment to startActivityForResult ";
        }
        if (s2 != null) {
            Logger.log(LoggingBehavior.DEVELOPER_ERRORS, 6, this.getClass().getName(), s2);
        }
    }
    
    protected abstract class ModeHandler
    {
        public abstract boolean canShow(final CONTENT p0, final boolean p1);
        
        public abstract AppCall createAppCall(final CONTENT p0);
        
        public Object getMode() {
            return FacebookDialogBase.BASE_AUTOMATIC_MODE;
        }
    }
}
