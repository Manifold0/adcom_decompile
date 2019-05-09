// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.widget;

import com.facebook.share.internal.NativeDialogParameters;
import com.facebook.share.internal.LegacyNativeDialogParameters;
import com.facebook.internal.DialogPresenter$ParameterProvider;
import com.facebook.share.internal.ShareContentValidation;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.internal.CallbackManagerImpl;
import java.util.ArrayList;
import com.facebook.internal.FacebookDialogBase$ModeHandler;
import java.util.List;
import android.os.Bundle;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.share.model.ShareMessengerMediaTemplateContent;
import com.facebook.share.model.ShareMessengerOpenGraphMusicTemplateContent;
import com.facebook.share.model.ShareMessengerGenericTemplateContent;
import com.facebook.share.internal.OpenGraphMessageDialogFeature;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.ShareVideoContent;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.internal.MessageDialogFeature;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.internal.DialogPresenter;
import com.facebook.internal.DialogFeature;
import com.facebook.internal.AppCall;
import android.content.Context;
import com.facebook.internal.FragmentWrapper;
import android.app.Fragment;
import com.facebook.share.internal.ShareInternalUtility;
import android.app.Activity;
import com.facebook.internal.CallbackManagerImpl$RequestCodeOffset;
import com.facebook.share.Sharer;
import com.facebook.share.Sharer$Result;
import com.facebook.share.model.ShareContent;
import com.facebook.internal.FacebookDialogBase;

public final class MessageDialog extends FacebookDialogBase<ShareContent, Sharer$Result> implements Sharer
{
    private static final int DEFAULT_REQUEST_CODE;
    private boolean shouldFailOnDataError;
    
    static {
        DEFAULT_REQUEST_CODE = CallbackManagerImpl$RequestCodeOffset.Message.toRequestCode();
    }
    
    public MessageDialog(final Activity activity) {
        super(activity, MessageDialog.DEFAULT_REQUEST_CODE);
        this.shouldFailOnDataError = false;
        ShareInternalUtility.registerStaticShareCallback(MessageDialog.DEFAULT_REQUEST_CODE);
    }
    
    MessageDialog(final Activity activity, final int n) {
        super(activity, n);
        this.shouldFailOnDataError = false;
        ShareInternalUtility.registerStaticShareCallback(n);
    }
    
    public MessageDialog(final Fragment fragment) {
        this(new FragmentWrapper(fragment));
    }
    
    MessageDialog(final Fragment fragment, final int n) {
        this(new FragmentWrapper(fragment), n);
    }
    
    public MessageDialog(final android.support.v4.app.Fragment fragment) {
        this(new FragmentWrapper(fragment));
    }
    
    MessageDialog(final android.support.v4.app.Fragment fragment, final int n) {
        this(new FragmentWrapper(fragment), n);
    }
    
    private MessageDialog(final FragmentWrapper fragmentWrapper) {
        super(fragmentWrapper, MessageDialog.DEFAULT_REQUEST_CODE);
        this.shouldFailOnDataError = false;
        ShareInternalUtility.registerStaticShareCallback(MessageDialog.DEFAULT_REQUEST_CODE);
    }
    
    private MessageDialog(final FragmentWrapper fragmentWrapper, final int n) {
        super(fragmentWrapper, n);
        this.shouldFailOnDataError = false;
        ShareInternalUtility.registerStaticShareCallback(n);
    }
    
    static /* synthetic */ Activity access$100(final MessageDialog messageDialog) {
        return messageDialog.getActivityContext();
    }
    
    public static boolean canShow(final Class<? extends ShareContent> clazz) {
        final DialogFeature feature = getFeature(clazz);
        return feature != null && DialogPresenter.canPresentNativeDialogWithFeature(feature);
    }
    
    private static DialogFeature getFeature(final Class<? extends ShareContent> clazz) {
        if (ShareLinkContent.class.isAssignableFrom(clazz)) {
            return (DialogFeature)MessageDialogFeature.MESSAGE_DIALOG;
        }
        if (SharePhotoContent.class.isAssignableFrom(clazz)) {
            return (DialogFeature)MessageDialogFeature.PHOTOS;
        }
        if (ShareVideoContent.class.isAssignableFrom(clazz)) {
            return (DialogFeature)MessageDialogFeature.VIDEO;
        }
        if (ShareOpenGraphContent.class.isAssignableFrom(clazz)) {
            return (DialogFeature)OpenGraphMessageDialogFeature.OG_MESSAGE_DIALOG;
        }
        if (ShareMessengerGenericTemplateContent.class.isAssignableFrom(clazz)) {
            return (DialogFeature)MessageDialogFeature.MESSENGER_GENERIC_TEMPLATE;
        }
        if (ShareMessengerOpenGraphMusicTemplateContent.class.isAssignableFrom(clazz)) {
            return (DialogFeature)MessageDialogFeature.MESSENGER_OPEN_GRAPH_MUSIC_TEMPLATE;
        }
        if (ShareMessengerMediaTemplateContent.class.isAssignableFrom(clazz)) {
            return (DialogFeature)MessageDialogFeature.MESSENGER_MEDIA_TEMPLATE;
        }
        return null;
    }
    
    private static void logDialogShare(final Context context, final ShareContent shareContent, final AppCall appCall) {
        final DialogFeature feature = getFeature(shareContent.getClass());
        String s;
        if (feature == MessageDialogFeature.MESSAGE_DIALOG) {
            s = "status";
        }
        else if (feature == MessageDialogFeature.PHOTOS) {
            s = "photo";
        }
        else if (feature == MessageDialogFeature.VIDEO) {
            s = "video";
        }
        else if (feature == OpenGraphMessageDialogFeature.OG_MESSAGE_DIALOG) {
            s = "open_graph";
        }
        else if (feature == MessageDialogFeature.MESSENGER_GENERIC_TEMPLATE) {
            s = "GenericTemplate";
        }
        else if (feature == MessageDialogFeature.MESSENGER_MEDIA_TEMPLATE) {
            s = "MediaTemplate";
        }
        else if (feature == MessageDialogFeature.MESSENGER_OPEN_GRAPH_MUSIC_TEMPLATE) {
            s = "OpenGraphMusicTemplate";
        }
        else {
            s = "unknown";
        }
        final AppEventsLogger logger = AppEventsLogger.newLogger(context);
        final Bundle bundle = new Bundle();
        bundle.putString("fb_share_dialog_content_type", s);
        bundle.putString("fb_share_dialog_content_uuid", appCall.getCallId().toString());
        bundle.putString("fb_share_dialog_content_page_id", shareContent.getPageId());
        logger.logSdkEvent("fb_messenger_share_dialog_show", (Double)null, bundle);
    }
    
    public static void show(final Activity activity, final ShareContent shareContent) {
        new MessageDialog(activity).show((Object)shareContent);
    }
    
    public static void show(final Fragment fragment, final ShareContent shareContent) {
        show(new FragmentWrapper(fragment), shareContent);
    }
    
    public static void show(final android.support.v4.app.Fragment fragment, final ShareContent shareContent) {
        show(new FragmentWrapper(fragment), shareContent);
    }
    
    private static void show(final FragmentWrapper fragmentWrapper, final ShareContent shareContent) {
        new MessageDialog(fragmentWrapper).show((Object)shareContent);
    }
    
    protected AppCall createBaseAppCall() {
        return new AppCall(this.getRequestCode());
    }
    
    protected List<FacebookDialogBase$ModeHandler> getOrderedModeHandlers() {
        final ArrayList<NativeHandler> list = (ArrayList<NativeHandler>)new ArrayList<FacebookDialogBase$ModeHandler>();
        list.add(new NativeHandler());
        return (List<FacebookDialogBase$ModeHandler>)list;
    }
    
    public boolean getShouldFailOnDataError() {
        return this.shouldFailOnDataError;
    }
    
    protected void registerCallbackImpl(final CallbackManagerImpl callbackManagerImpl, final FacebookCallback<Sharer$Result> facebookCallback) {
        ShareInternalUtility.registerSharerCallback(this.getRequestCode(), (CallbackManager)callbackManagerImpl, (FacebookCallback)facebookCallback);
    }
    
    public void setShouldFailOnDataError(final boolean shouldFailOnDataError) {
        this.shouldFailOnDataError = shouldFailOnDataError;
    }
    
    private class NativeHandler extends FacebookDialogBase$ModeHandler
    {
        private NativeHandler() {
            super((FacebookDialogBase)MessageDialog.this);
        }
        
        public boolean canShow(final ShareContent shareContent, final boolean b) {
            return shareContent != null && MessageDialog.canShow(shareContent.getClass());
        }
        
        public AppCall createAppCall(final ShareContent shareContent) {
            ShareContentValidation.validateForMessage(shareContent);
            final AppCall baseAppCall = MessageDialog.this.createBaseAppCall();
            final boolean shouldFailOnDataError = MessageDialog.this.getShouldFailOnDataError();
            logDialogShare((Context)MessageDialog.access$100(MessageDialog.this), shareContent, baseAppCall);
            DialogPresenter.setupAppCallForNativeDialog(baseAppCall, (DialogPresenter$ParameterProvider)new DialogPresenter$ParameterProvider() {
                public Bundle getLegacyParameters() {
                    return LegacyNativeDialogParameters.create(baseAppCall.getCallId(), shareContent, shouldFailOnDataError);
                }
                
                public Bundle getParameters() {
                    return NativeDialogParameters.create(baseAppCall.getCallId(), shareContent, shouldFailOnDataError);
                }
            }, getFeature(shareContent.getClass()));
            return baseAppCall;
        }
    }
}
