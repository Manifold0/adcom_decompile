// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.widget;

import java.util.Collection;
import android.graphics.Bitmap;
import android.net.Uri;
import com.facebook.internal.NativeAppCallAttachmentStore;
import com.facebook.internal.NativeAppCallAttachmentStore$Attachment;
import com.facebook.share.model.SharePhoto;
import java.util.UUID;
import com.facebook.internal.Utility;
import com.facebook.share.internal.WebDialogParameters;
import com.facebook.share.internal.ShareFeedContent;
import com.facebook.share.internal.NativeDialogParameters;
import com.facebook.share.internal.LegacyNativeDialogParameters;
import com.facebook.share.internal.ShareContentValidation;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.internal.CallbackManagerImpl;
import java.util.ArrayList;
import java.util.List;
import com.facebook.internal.AppCall;
import android.os.Bundle;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.share.internal.CameraEffectFeature;
import com.facebook.share.model.ShareCameraEffectContent;
import com.facebook.share.model.ShareMediaContent;
import com.facebook.share.internal.OpenGraphActionDialogFeature;
import com.facebook.share.model.ShareVideoContent;
import com.facebook.share.internal.ShareDialogFeature;
import com.facebook.AccessToken;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareLinkContent;
import android.util.Log;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.internal.DialogPresenter;
import com.facebook.internal.DialogFeature;
import android.content.Context;
import com.facebook.internal.FragmentWrapper;
import android.app.Fragment;
import com.facebook.share.internal.ShareInternalUtility;
import android.app.Activity;
import com.facebook.internal.CallbackManagerImpl$RequestCodeOffset;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareContent;
import com.facebook.internal.FacebookDialogBase;

public final class ShareDialog extends FacebookDialogBase<ShareContent, Result> implements Sharer
{
    private static final int DEFAULT_REQUEST_CODE;
    private static final String FEED_DIALOG = "feed";
    private static final String TAG;
    private static final String WEB_OG_SHARE_DIALOG = "share_open_graph";
    public static final String WEB_SHARE_DIALOG = "share";
    private boolean isAutomaticMode;
    private boolean shouldFailOnDataError;
    
    static {
        TAG = ShareDialog.class.getSimpleName();
        DEFAULT_REQUEST_CODE = CallbackManagerImpl$RequestCodeOffset.Share.toRequestCode();
    }
    
    public ShareDialog(final Activity activity) {
        super(activity, ShareDialog.DEFAULT_REQUEST_CODE);
        this.shouldFailOnDataError = false;
        this.isAutomaticMode = true;
        ShareInternalUtility.registerStaticShareCallback(ShareDialog.DEFAULT_REQUEST_CODE);
    }
    
    ShareDialog(final Activity activity, final int n) {
        super(activity, n);
        this.shouldFailOnDataError = false;
        this.isAutomaticMode = true;
        ShareInternalUtility.registerStaticShareCallback(n);
    }
    
    public ShareDialog(final Fragment fragment) {
        this(new FragmentWrapper(fragment));
    }
    
    ShareDialog(final Fragment fragment, final int n) {
        this(new FragmentWrapper(fragment), n);
    }
    
    public ShareDialog(final android.support.v4.app.Fragment fragment) {
        this(new FragmentWrapper(fragment));
    }
    
    ShareDialog(final android.support.v4.app.Fragment fragment, final int n) {
        this(new FragmentWrapper(fragment), n);
    }
    
    private ShareDialog(final FragmentWrapper fragmentWrapper) {
        super(fragmentWrapper, ShareDialog.DEFAULT_REQUEST_CODE);
        this.shouldFailOnDataError = false;
        this.isAutomaticMode = true;
        ShareInternalUtility.registerStaticShareCallback(ShareDialog.DEFAULT_REQUEST_CODE);
    }
    
    private ShareDialog(final FragmentWrapper fragmentWrapper, final int n) {
        super(fragmentWrapper, n);
        this.shouldFailOnDataError = false;
        this.isAutomaticMode = true;
        ShareInternalUtility.registerStaticShareCallback(n);
    }
    
    public static boolean canShow(final Class<? extends ShareContent> clazz) {
        return canShowWebTypeCheck(clazz) || canShowNative(clazz);
    }
    
    private static boolean canShowNative(final Class<? extends ShareContent> clazz) {
        final DialogFeature feature = getFeature(clazz);
        return feature != null && DialogPresenter.canPresentNativeDialogWithFeature(feature);
    }
    
    private static boolean canShowWebCheck(final ShareContent shareContent) {
        if (!canShowWebTypeCheck(((ShareOpenGraphContent)shareContent).getClass())) {
            return false;
        }
        if (!(shareContent instanceof ShareOpenGraphContent)) {
            return true;
        }
        final ShareOpenGraphContent shareOpenGraphContent = (ShareOpenGraphContent)shareContent;
        try {
            ShareInternalUtility.toJSONObjectForWeb(shareOpenGraphContent);
            return true;
        }
        catch (Exception ex) {
            Log.d(ShareDialog.TAG, "canShow returned false because the content of the Opem Graph object can't be shared via the web dialog", (Throwable)ex);
            return false;
        }
    }
    
    private static boolean canShowWebTypeCheck(final Class<? extends ShareContent> clazz) {
        return ShareLinkContent.class.isAssignableFrom(clazz) || ShareOpenGraphContent.class.isAssignableFrom(clazz) || (SharePhotoContent.class.isAssignableFrom(clazz) && AccessToken.isCurrentAccessTokenActive());
    }
    
    private static DialogFeature getFeature(final Class<? extends ShareContent> clazz) {
        if (ShareLinkContent.class.isAssignableFrom(clazz)) {
            return ShareDialogFeature.SHARE_DIALOG;
        }
        if (SharePhotoContent.class.isAssignableFrom(clazz)) {
            return ShareDialogFeature.PHOTOS;
        }
        if (ShareVideoContent.class.isAssignableFrom(clazz)) {
            return ShareDialogFeature.VIDEO;
        }
        if (ShareOpenGraphContent.class.isAssignableFrom(clazz)) {
            return OpenGraphActionDialogFeature.OG_ACTION_DIALOG;
        }
        if (ShareMediaContent.class.isAssignableFrom(clazz)) {
            return ShareDialogFeature.MULTIMEDIA;
        }
        if (ShareCameraEffectContent.class.isAssignableFrom(clazz)) {
            return CameraEffectFeature.SHARE_CAMERA_EFFECT;
        }
        return null;
    }
    
    private void logDialogShare(final Context context, final ShareContent shareContent, Mode automatic) {
        if (this.isAutomaticMode) {
            automatic = Mode.AUTOMATIC;
        }
        String s = null;
        switch (automatic) {
            default: {
                s = "unknown";
                break;
            }
            case AUTOMATIC: {
                s = "automatic";
                break;
            }
            case WEB: {
                s = "web";
                break;
            }
            case NATIVE: {
                s = "native";
                break;
            }
        }
        final DialogFeature feature = getFeature(shareContent.getClass());
        String s2;
        if (feature == ShareDialogFeature.SHARE_DIALOG) {
            s2 = "status";
        }
        else if (feature == ShareDialogFeature.PHOTOS) {
            s2 = "photo";
        }
        else if (feature == ShareDialogFeature.VIDEO) {
            s2 = "video";
        }
        else if (feature == OpenGraphActionDialogFeature.OG_ACTION_DIALOG) {
            s2 = "open_graph";
        }
        else {
            s2 = "unknown";
        }
        final AppEventsLogger logger = AppEventsLogger.newLogger(context);
        final Bundle bundle = new Bundle();
        bundle.putString("fb_share_dialog_show", s);
        bundle.putString("fb_share_dialog_content_type", s2);
        logger.logSdkEvent("fb_share_dialog_show", (Double)null, bundle);
    }
    
    public static void show(final Activity activity, final ShareContent shareContent) {
        ((FacebookDialogBase<ShareContent, RESULT>)new ShareDialog(activity)).show(shareContent);
    }
    
    public static void show(final Fragment fragment, final ShareContent shareContent) {
        show(new FragmentWrapper(fragment), shareContent);
    }
    
    public static void show(final android.support.v4.app.Fragment fragment, final ShareContent shareContent) {
        show(new FragmentWrapper(fragment), shareContent);
    }
    
    private static void show(final FragmentWrapper fragmentWrapper, final ShareContent shareContent) {
        ((FacebookDialogBase<ShareContent, RESULT>)new ShareDialog(fragmentWrapper)).show(shareContent);
    }
    
    public boolean canShow(final ShareContent shareContent, final Mode mode) {
        Object base_AUTOMATIC_MODE = mode;
        if (mode == Mode.AUTOMATIC) {
            base_AUTOMATIC_MODE = ShareDialog.BASE_AUTOMATIC_MODE;
        }
        return ((FacebookDialogBase<ShareContent, RESULT>)this).canShowImpl(shareContent, base_AUTOMATIC_MODE);
    }
    
    @Override
    protected AppCall createBaseAppCall() {
        return new AppCall(this.getRequestCode());
    }
    
    @Override
    protected List<ModeHandler> getOrderedModeHandlers() {
        final ArrayList<NativeHandler> list = (ArrayList<NativeHandler>)new ArrayList<ModeHandler>();
        list.add(new NativeHandler());
        list.add((NativeHandler)new FeedHandler());
        list.add((NativeHandler)new WebShareHandler());
        list.add((NativeHandler)new CameraEffectHandler());
        return (List<ModeHandler>)list;
    }
    
    @Override
    public boolean getShouldFailOnDataError() {
        return this.shouldFailOnDataError;
    }
    
    @Override
    protected void registerCallbackImpl(final CallbackManagerImpl callbackManagerImpl, final FacebookCallback<Result> facebookCallback) {
        ShareInternalUtility.registerSharerCallback(this.getRequestCode(), (CallbackManager)callbackManagerImpl, facebookCallback);
    }
    
    @Override
    public void setShouldFailOnDataError(final boolean shouldFailOnDataError) {
        this.shouldFailOnDataError = shouldFailOnDataError;
    }
    
    public void show(final ShareContent shareContent, Mode base_AUTOMATIC_MODE) {
        this.isAutomaticMode = (base_AUTOMATIC_MODE == Mode.AUTOMATIC);
        if (this.isAutomaticMode) {
            base_AUTOMATIC_MODE = (Mode)ShareDialog.BASE_AUTOMATIC_MODE;
        }
        ((FacebookDialogBase<ShareContent, RESULT>)this).showImpl(shareContent, base_AUTOMATIC_MODE);
    }
    
    private class CameraEffectHandler extends ModeHandler
    {
        public boolean canShow(final ShareContent shareContent, final boolean b) {
            return shareContent instanceof ShareCameraEffectContent && canShowNative(shareContent.getClass());
        }
        
        public AppCall createAppCall(final ShareContent shareContent) {
            ShareContentValidation.validateForNativeShare(shareContent);
            final AppCall baseAppCall = ShareDialog.this.createBaseAppCall();
            DialogPresenter.setupAppCallForNativeDialog(baseAppCall, (DialogPresenter.ParameterProvider)new DialogPresenter.ParameterProvider() {
                final /* synthetic */ boolean val$shouldFailOnDataError = ShareDialog.this.getShouldFailOnDataError();
                
                @Override
                public Bundle getLegacyParameters() {
                    return LegacyNativeDialogParameters.create(baseAppCall.getCallId(), shareContent, this.val$shouldFailOnDataError);
                }
                
                @Override
                public Bundle getParameters() {
                    return NativeDialogParameters.create(baseAppCall.getCallId(), shareContent, this.val$shouldFailOnDataError);
                }
            }, getFeature(shareContent.getClass()));
            return baseAppCall;
        }
        
        @Override
        public Object getMode() {
            return Mode.NATIVE;
        }
    }
    
    private class FeedHandler extends ModeHandler
    {
        public boolean canShow(final ShareContent shareContent, final boolean b) {
            return shareContent instanceof ShareLinkContent || shareContent instanceof ShareFeedContent;
        }
        
        public AppCall createAppCall(final ShareContent shareContent) {
            ShareDialog.this.logDialogShare((Context)FacebookDialogBase.this.getActivityContext(), shareContent, Mode.FEED);
            final AppCall baseAppCall = ShareDialog.this.createBaseAppCall();
            Bundle bundle;
            if (shareContent instanceof ShareLinkContent) {
                final ShareLinkContent shareLinkContent = (ShareLinkContent)shareContent;
                ShareContentValidation.validateForWebShare(shareLinkContent);
                bundle = WebDialogParameters.createForFeed(shareLinkContent);
            }
            else {
                bundle = WebDialogParameters.createForFeed((ShareFeedContent)shareContent);
            }
            DialogPresenter.setupAppCallForWebDialog(baseAppCall, "feed", bundle);
            return baseAppCall;
        }
        
        @Override
        public Object getMode() {
            return Mode.FEED;
        }
    }
    
    public enum Mode
    {
        AUTOMATIC, 
        FEED, 
        NATIVE, 
        WEB;
    }
    
    private class NativeHandler extends ModeHandler
    {
        public boolean canShow(final ShareContent shareContent, final boolean b) {
            if (shareContent == null || shareContent instanceof ShareCameraEffectContent) {
                return false;
            }
            boolean b2 = true;
            final boolean b3 = true;
            if (!b) {
                boolean canPresentNativeDialogWithFeature = b3;
                if (shareContent.getShareHashtag() != null) {
                    canPresentNativeDialogWithFeature = DialogPresenter.canPresentNativeDialogWithFeature(ShareDialogFeature.HASHTAG);
                }
                b2 = canPresentNativeDialogWithFeature;
                if (shareContent instanceof ShareLinkContent) {
                    b2 = canPresentNativeDialogWithFeature;
                    if (!Utility.isNullOrEmpty(((ShareLinkContent)shareContent).getQuote())) {
                        b2 = (canPresentNativeDialogWithFeature & DialogPresenter.canPresentNativeDialogWithFeature(ShareDialogFeature.LINK_SHARE_QUOTES));
                    }
                }
            }
            return b2 && canShowNative(((ShareLinkContent)shareContent).getClass());
        }
        
        public AppCall createAppCall(final ShareContent shareContent) {
            ShareDialog.this.logDialogShare((Context)FacebookDialogBase.this.getActivityContext(), shareContent, Mode.NATIVE);
            ShareContentValidation.validateForNativeShare(shareContent);
            final AppCall baseAppCall = ShareDialog.this.createBaseAppCall();
            DialogPresenter.setupAppCallForNativeDialog(baseAppCall, (DialogPresenter.ParameterProvider)new DialogPresenter.ParameterProvider() {
                final /* synthetic */ boolean val$shouldFailOnDataError = ShareDialog.this.getShouldFailOnDataError();
                
                @Override
                public Bundle getLegacyParameters() {
                    return LegacyNativeDialogParameters.create(baseAppCall.getCallId(), shareContent, this.val$shouldFailOnDataError);
                }
                
                @Override
                public Bundle getParameters() {
                    return NativeDialogParameters.create(baseAppCall.getCallId(), shareContent, this.val$shouldFailOnDataError);
                }
            }, getFeature(shareContent.getClass()));
            return baseAppCall;
        }
        
        @Override
        public Object getMode() {
            return Mode.NATIVE;
        }
    }
    
    private class WebShareHandler extends ModeHandler
    {
        private SharePhotoContent createAndMapAttachments(final SharePhotoContent sharePhotoContent, final UUID uuid) {
            final SharePhotoContent.Builder from = new SharePhotoContent.Builder().readFrom(sharePhotoContent);
            final ArrayList<SharePhoto> photos = new ArrayList<SharePhoto>();
            final ArrayList<NativeAppCallAttachmentStore$Attachment> list = new ArrayList<NativeAppCallAttachmentStore$Attachment>();
            for (int i = 0; i < sharePhotoContent.getPhotos().size(); ++i) {
                final SharePhoto sharePhoto = sharePhotoContent.getPhotos().get(i);
                final Bitmap bitmap = sharePhoto.getBitmap();
                SharePhoto build = sharePhoto;
                if (bitmap != null) {
                    final NativeAppCallAttachmentStore$Attachment attachment = NativeAppCallAttachmentStore.createAttachment(uuid, bitmap);
                    build = new SharePhoto.Builder().readFrom(sharePhoto).setImageUrl(Uri.parse(attachment.getAttachmentUrl())).setBitmap(null).build();
                    list.add(attachment);
                }
                photos.add(build);
            }
            from.setPhotos(photos);
            NativeAppCallAttachmentStore.addAttachments((Collection)list);
            return from.build();
        }
        
        private String getActionName(final ShareContent shareContent) {
            if (shareContent instanceof ShareLinkContent || shareContent instanceof SharePhotoContent) {
                return "share";
            }
            if (shareContent instanceof ShareOpenGraphContent) {
                return "share_open_graph";
            }
            return null;
        }
        
        public boolean canShow(final ShareContent shareContent, final boolean b) {
            return shareContent != null && canShowWebCheck(shareContent);
        }
        
        public AppCall createAppCall(final ShareContent shareContent) {
            ShareDialog.this.logDialogShare((Context)FacebookDialogBase.this.getActivityContext(), shareContent, Mode.WEB);
            final AppCall baseAppCall = ShareDialog.this.createBaseAppCall();
            ShareContentValidation.validateForWebShare(shareContent);
            Bundle bundle;
            if (shareContent instanceof ShareLinkContent) {
                bundle = WebDialogParameters.create((ShareLinkContent)shareContent);
            }
            else if (shareContent instanceof SharePhotoContent) {
                bundle = WebDialogParameters.create(this.createAndMapAttachments((SharePhotoContent)shareContent, baseAppCall.getCallId()));
            }
            else {
                bundle = WebDialogParameters.create((ShareOpenGraphContent)shareContent);
            }
            DialogPresenter.setupAppCallForWebDialog(baseAppCall, this.getActionName(shareContent), bundle);
            return baseAppCall;
        }
        
        @Override
        public Object getMode() {
            return Mode.WEB;
        }
    }
}
