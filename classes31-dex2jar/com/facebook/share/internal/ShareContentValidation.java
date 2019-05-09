// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.internal;

import java.util.Collection;
import com.facebook.share.model.ShareMessengerURLActionButton;
import com.facebook.share.model.ShareMessengerActionButton;
import com.facebook.internal.Validate;
import com.facebook.FacebookSdk;
import android.graphics.Bitmap;
import java.util.Iterator;
import java.util.List;
import com.facebook.share.model.ShareMedia;
import java.util.Locale;
import android.net.Uri;
import com.facebook.internal.Utility;
import com.facebook.FacebookException;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareCameraEffectContent;
import com.facebook.share.model.ShareMediaContent;
import com.facebook.share.model.ShareVideoContent;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareMessengerMediaTemplateContent;
import com.facebook.share.model.ShareMessengerGenericTemplateContent;
import com.facebook.share.model.ShareMessengerOpenGraphMusicTemplateContent;
import com.facebook.share.model.ShareVideo;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.ShareOpenGraphValueContainer;
import com.facebook.share.model.ShareOpenGraphObject;
import com.facebook.share.model.ShareOpenGraphAction;
import com.facebook.share.model.ShareOpenGraphContent;

public class ShareContentValidation
{
    private static Validator ApiValidator;
    private static Validator DefaultValidator;
    private static Validator WebShareValidator;
    
    private static Validator getApiValidator() {
        if (ShareContentValidation.ApiValidator == null) {
            ShareContentValidation.ApiValidator = (Validator)new ApiValidator();
        }
        return ShareContentValidation.ApiValidator;
    }
    
    private static Validator getDefaultValidator() {
        if (ShareContentValidation.DefaultValidator == null) {
            ShareContentValidation.DefaultValidator = new Validator();
        }
        return ShareContentValidation.DefaultValidator;
    }
    
    private static Validator getWebShareValidator() {
        if (ShareContentValidation.WebShareValidator == null) {
            ShareContentValidation.WebShareValidator = (Validator)new WebShareValidator();
        }
        return ShareContentValidation.WebShareValidator;
    }
    
    private static void validate(final ShareContent shareContent, final Validator validator) throws FacebookException {
        if (shareContent == null) {
            throw new FacebookException("Must provide non-null content to share");
        }
        if (shareContent instanceof ShareLinkContent) {
            validator.validate((ShareLinkContent)shareContent);
        }
        else {
            if (shareContent instanceof SharePhotoContent) {
                validator.validate((SharePhotoContent)shareContent);
                return;
            }
            if (shareContent instanceof ShareVideoContent) {
                validator.validate((ShareVideoContent)shareContent);
                return;
            }
            if (shareContent instanceof ShareOpenGraphContent) {
                validator.validate((ShareOpenGraphContent)shareContent);
                return;
            }
            if (shareContent instanceof ShareMediaContent) {
                validator.validate((ShareMediaContent)shareContent);
                return;
            }
            if (shareContent instanceof ShareCameraEffectContent) {
                validator.validate((ShareCameraEffectContent)shareContent);
                return;
            }
            if (shareContent instanceof ShareMessengerOpenGraphMusicTemplateContent) {
                validator.validate((ShareMessengerOpenGraphMusicTemplateContent)shareContent);
                return;
            }
            if (shareContent instanceof ShareMessengerMediaTemplateContent) {
                validator.validate((ShareMessengerMediaTemplateContent)shareContent);
                return;
            }
            if (shareContent instanceof ShareMessengerGenericTemplateContent) {
                validator.validate((ShareMessengerGenericTemplateContent)shareContent);
            }
        }
    }
    
    private static void validateCameraEffectContent(final ShareCameraEffectContent shareCameraEffectContent, final Validator validator) {
        if (Utility.isNullOrEmpty(shareCameraEffectContent.getEffectId())) {
            throw new FacebookException("Must specify a non-empty effectId");
        }
    }
    
    public static void validateForApiShare(final ShareContent shareContent) {
        validate(shareContent, getApiValidator());
    }
    
    public static void validateForMessage(final ShareContent shareContent) {
        validate(shareContent, getDefaultValidator());
    }
    
    public static void validateForNativeShare(final ShareContent shareContent) {
        validate(shareContent, getDefaultValidator());
    }
    
    public static void validateForWebShare(final ShareContent shareContent) {
        validate(shareContent, getWebShareValidator());
    }
    
    private static void validateLinkContent(final ShareLinkContent shareLinkContent, final Validator validator) {
        final Uri imageUrl = shareLinkContent.getImageUrl();
        if (imageUrl != null && !Utility.isWebUri(imageUrl)) {
            throw new FacebookException("Image Url must be an http:// or https:// url");
        }
    }
    
    private static void validateMediaContent(final ShareMediaContent shareMediaContent, final Validator validator) {
        final List<ShareMedia> media = shareMediaContent.getMedia();
        if (media == null || media.isEmpty()) {
            throw new FacebookException("Must specify at least one medium in ShareMediaContent.");
        }
        if (media.size() > 6) {
            throw new FacebookException(String.format(Locale.ROOT, "Cannot add more than %d media.", 6));
        }
        final Iterator<ShareMedia> iterator = media.iterator();
        while (iterator.hasNext()) {
            validator.validate(iterator.next());
        }
    }
    
    public static void validateMedium(final ShareMedia shareMedia, final Validator validator) {
        if (shareMedia instanceof SharePhoto) {
            validator.validate((SharePhoto)shareMedia);
            return;
        }
        if (shareMedia instanceof ShareVideo) {
            validator.validate((ShareVideo)shareMedia);
            return;
        }
        throw new FacebookException(String.format(Locale.ROOT, "Invalid media type: %s", shareMedia.getClass().getSimpleName()));
    }
    
    private static void validateMessengerOpenGraphMusicTemplate(final ShareMessengerOpenGraphMusicTemplateContent shareMessengerOpenGraphMusicTemplateContent) {
        if (Utility.isNullOrEmpty(shareMessengerOpenGraphMusicTemplateContent.getPageId())) {
            throw new FacebookException("Must specify Page Id for ShareMessengerOpenGraphMusicTemplateContent");
        }
        if (shareMessengerOpenGraphMusicTemplateContent.getUrl() == null) {
            throw new FacebookException("Must specify url for ShareMessengerOpenGraphMusicTemplateContent");
        }
        validateShareMessengerActionButton(shareMessengerOpenGraphMusicTemplateContent.getButton());
    }
    
    private static void validateOpenGraphAction(final ShareOpenGraphAction shareOpenGraphAction, final Validator validator) {
        if (shareOpenGraphAction == null) {
            throw new FacebookException("Must specify a non-null ShareOpenGraphAction");
        }
        if (Utility.isNullOrEmpty(shareOpenGraphAction.getActionType())) {
            throw new FacebookException("ShareOpenGraphAction must have a non-empty actionType");
        }
        validator.validate(shareOpenGraphAction, false);
    }
    
    private static void validateOpenGraphContent(final ShareOpenGraphContent shareOpenGraphContent, final Validator validator) {
        validator.validate(shareOpenGraphContent.getAction());
        final String previewPropertyName = shareOpenGraphContent.getPreviewPropertyName();
        if (Utility.isNullOrEmpty(previewPropertyName)) {
            throw new FacebookException("Must specify a previewPropertyName.");
        }
        if (shareOpenGraphContent.getAction().get(previewPropertyName) == null) {
            throw new FacebookException("Property \"" + previewPropertyName + "\" was not found on the action. The name of the preview property must match the name of an action property.");
        }
    }
    
    private static void validateOpenGraphKey(final String s, final boolean b) {
        if (b) {
            final String[] split = s.split(":");
            if (split.length < 2) {
                throw new FacebookException("Open Graph keys must be namespaced: %s", new Object[] { s });
            }
            for (int length = split.length, i = 0; i < length; ++i) {
                if (split[i].isEmpty()) {
                    throw new FacebookException("Invalid key found in Open Graph dictionary: %s", new Object[] { s });
                }
            }
        }
    }
    
    private static void validateOpenGraphObject(final ShareOpenGraphObject shareOpenGraphObject, final Validator validator) {
        if (shareOpenGraphObject == null) {
            throw new FacebookException("Cannot share a null ShareOpenGraphObject");
        }
        validator.validate(shareOpenGraphObject, true);
    }
    
    private static void validateOpenGraphValueContainer(final ShareOpenGraphValueContainer shareOpenGraphValueContainer, final Validator validator, final boolean b) {
        for (final String s : shareOpenGraphValueContainer.keySet()) {
            validateOpenGraphKey(s, b);
            final Object value = shareOpenGraphValueContainer.get(s);
            if (value instanceof List) {
                for (final Object next : (List<Object>)value) {
                    if (next == null) {
                        throw new FacebookException("Cannot put null objects in Lists in ShareOpenGraphObjects and ShareOpenGraphActions");
                    }
                    validateOpenGraphValueContainerObject(next, validator);
                }
            }
            else {
                validateOpenGraphValueContainerObject(value, validator);
            }
        }
    }
    
    private static void validateOpenGraphValueContainerObject(final Object o, final Validator validator) {
        if (o instanceof ShareOpenGraphObject) {
            validator.validate((ShareOpenGraphObject)o);
        }
        else if (o instanceof SharePhoto) {
            validator.validate((SharePhoto)o);
        }
    }
    
    private static void validatePhoto(final SharePhoto sharePhoto) {
        if (sharePhoto == null) {
            throw new FacebookException("Cannot share a null SharePhoto");
        }
        final Bitmap bitmap = sharePhoto.getBitmap();
        final Uri imageUrl = sharePhoto.getImageUrl();
        if (bitmap == null && imageUrl == null) {
            throw new FacebookException("SharePhoto does not have a Bitmap or ImageUrl specified");
        }
    }
    
    private static void validatePhotoContent(final SharePhotoContent sharePhotoContent, final Validator validator) {
        final List<SharePhoto> photos = sharePhotoContent.getPhotos();
        if (photos == null || photos.isEmpty()) {
            throw new FacebookException("Must specify at least one Photo in SharePhotoContent.");
        }
        if (photos.size() > 6) {
            throw new FacebookException(String.format(Locale.ROOT, "Cannot add more than %d photos.", 6));
        }
        final Iterator<SharePhoto> iterator = photos.iterator();
        while (iterator.hasNext()) {
            validator.validate(iterator.next());
        }
    }
    
    private static void validatePhotoForApi(final SharePhoto sharePhoto, final Validator validator) {
        validatePhoto(sharePhoto);
        final Bitmap bitmap = sharePhoto.getBitmap();
        final Uri imageUrl = sharePhoto.getImageUrl();
        if (bitmap == null && Utility.isWebUri(imageUrl) && !validator.isOpenGraphContent()) {
            throw new FacebookException("Cannot set the ImageUrl of a SharePhoto to the Uri of an image on the web when sharing SharePhotoContent");
        }
    }
    
    private static void validatePhotoForNativeDialog(final SharePhoto sharePhoto, final Validator validator) {
        validatePhotoForApi(sharePhoto, validator);
        if (sharePhoto.getBitmap() != null || !Utility.isWebUri(sharePhoto.getImageUrl())) {
            Validate.hasContentProvider(FacebookSdk.getApplicationContext());
        }
    }
    
    private static void validatePhotoForWebDialog(final SharePhoto sharePhoto, final Validator validator) {
        validatePhoto(sharePhoto);
    }
    
    private static void validateShareMessengerActionButton(final ShareMessengerActionButton shareMessengerActionButton) {
        if (shareMessengerActionButton != null) {
            if (Utility.isNullOrEmpty(shareMessengerActionButton.getTitle())) {
                throw new FacebookException("Must specify title for ShareMessengerActionButton");
            }
            if (shareMessengerActionButton instanceof ShareMessengerURLActionButton) {
                validateShareMessengerURLActionButton((ShareMessengerURLActionButton)shareMessengerActionButton);
            }
        }
    }
    
    private static void validateShareMessengerGenericTemplateContent(final ShareMessengerGenericTemplateContent shareMessengerGenericTemplateContent) {
        if (Utility.isNullOrEmpty(shareMessengerGenericTemplateContent.getPageId())) {
            throw new FacebookException("Must specify Page Id for ShareMessengerGenericTemplateContent");
        }
        if (shareMessengerGenericTemplateContent.getGenericTemplateElement() == null) {
            throw new FacebookException("Must specify element for ShareMessengerGenericTemplateContent");
        }
        if (Utility.isNullOrEmpty(shareMessengerGenericTemplateContent.getGenericTemplateElement().getTitle())) {
            throw new FacebookException("Must specify title for ShareMessengerGenericTemplateElement");
        }
        validateShareMessengerActionButton(shareMessengerGenericTemplateContent.getGenericTemplateElement().getButton());
    }
    
    private static void validateShareMessengerMediaTemplateContent(final ShareMessengerMediaTemplateContent shareMessengerMediaTemplateContent) {
        if (Utility.isNullOrEmpty(shareMessengerMediaTemplateContent.getPageId())) {
            throw new FacebookException("Must specify Page Id for ShareMessengerMediaTemplateContent");
        }
        if (shareMessengerMediaTemplateContent.getMediaUrl() == null && Utility.isNullOrEmpty(shareMessengerMediaTemplateContent.getAttachmentId())) {
            throw new FacebookException("Must specify either attachmentId or mediaURL for ShareMessengerMediaTemplateContent");
        }
        validateShareMessengerActionButton(shareMessengerMediaTemplateContent.getButton());
    }
    
    private static void validateShareMessengerURLActionButton(final ShareMessengerURLActionButton shareMessengerURLActionButton) {
        if (shareMessengerURLActionButton.getUrl() == null) {
            throw new FacebookException("Must specify url for ShareMessengerURLActionButton");
        }
    }
    
    private static void validateVideo(final ShareVideo shareVideo, final Validator validator) {
        if (shareVideo == null) {
            throw new FacebookException("Cannot share a null ShareVideo");
        }
        final Uri localUrl = shareVideo.getLocalUrl();
        if (localUrl == null) {
            throw new FacebookException("ShareVideo does not have a LocalUrl specified");
        }
        if (!Utility.isContentUri(localUrl) && !Utility.isFileUri(localUrl)) {
            throw new FacebookException("ShareVideo must reference a video that is on the device");
        }
    }
    
    private static void validateVideoContent(final ShareVideoContent shareVideoContent, final Validator validator) {
        validator.validate(shareVideoContent.getVideo());
        final SharePhoto previewPhoto = shareVideoContent.getPreviewPhoto();
        if (previewPhoto != null) {
            validator.validate(previewPhoto);
        }
    }
    
    private static class ApiValidator extends Validator
    {
        @Override
        public void validate(final ShareLinkContent shareLinkContent) {
            if (!Utility.isNullOrEmpty(shareLinkContent.getQuote())) {
                throw new FacebookException("Cannot share link content with quote using the share api");
            }
        }
        
        @Override
        public void validate(final ShareMediaContent shareMediaContent) {
            throw new FacebookException("Cannot share ShareMediaContent using the share api");
        }
        
        @Override
        public void validate(final SharePhoto sharePhoto) {
            validatePhotoForApi(sharePhoto, (Validator)this);
        }
        
        @Override
        public void validate(final ShareVideoContent shareVideoContent) {
            if (!Utility.isNullOrEmpty(shareVideoContent.getPlaceId())) {
                throw new FacebookException("Cannot share video content with place IDs using the share api");
            }
            if (!Utility.isNullOrEmpty((Collection)shareVideoContent.getPeopleIds())) {
                throw new FacebookException("Cannot share video content with people IDs using the share api");
            }
            if (!Utility.isNullOrEmpty(shareVideoContent.getRef())) {
                throw new FacebookException("Cannot share video content with referrer URL using the share api");
            }
        }
    }
    
    private static class Validator
    {
        private boolean isOpenGraphContent;
        
        private Validator() {
            this.isOpenGraphContent = false;
        }
        
        public boolean isOpenGraphContent() {
            return this.isOpenGraphContent;
        }
        
        public void validate(final ShareCameraEffectContent shareCameraEffectContent) {
            validateCameraEffectContent(shareCameraEffectContent, this);
        }
        
        public void validate(final ShareLinkContent shareLinkContent) {
            validateLinkContent(shareLinkContent, this);
        }
        
        public void validate(final ShareMedia shareMedia) {
            ShareContentValidation.validateMedium(shareMedia, this);
        }
        
        public void validate(final ShareMediaContent shareMediaContent) {
            validateMediaContent(shareMediaContent, this);
        }
        
        public void validate(final ShareMessengerGenericTemplateContent shareMessengerGenericTemplateContent) {
            validateShareMessengerGenericTemplateContent(shareMessengerGenericTemplateContent);
        }
        
        public void validate(final ShareMessengerMediaTemplateContent shareMessengerMediaTemplateContent) {
            validateShareMessengerMediaTemplateContent(shareMessengerMediaTemplateContent);
        }
        
        public void validate(final ShareMessengerOpenGraphMusicTemplateContent shareMessengerOpenGraphMusicTemplateContent) {
            validateMessengerOpenGraphMusicTemplate(shareMessengerOpenGraphMusicTemplateContent);
        }
        
        public void validate(final ShareOpenGraphAction shareOpenGraphAction) {
            validateOpenGraphAction(shareOpenGraphAction, this);
        }
        
        public void validate(final ShareOpenGraphContent shareOpenGraphContent) {
            this.isOpenGraphContent = true;
            validateOpenGraphContent(shareOpenGraphContent, this);
        }
        
        public void validate(final ShareOpenGraphObject shareOpenGraphObject) {
            validateOpenGraphObject(shareOpenGraphObject, this);
        }
        
        public void validate(final ShareOpenGraphValueContainer shareOpenGraphValueContainer, final boolean b) {
            validateOpenGraphValueContainer(shareOpenGraphValueContainer, this, b);
        }
        
        public void validate(final SharePhoto sharePhoto) {
            validatePhotoForNativeDialog(sharePhoto, this);
        }
        
        public void validate(final SharePhotoContent sharePhotoContent) {
            validatePhotoContent(sharePhotoContent, this);
        }
        
        public void validate(final ShareVideo shareVideo) {
            validateVideo(shareVideo, this);
        }
        
        public void validate(final ShareVideoContent shareVideoContent) {
            validateVideoContent(shareVideoContent, this);
        }
    }
    
    private static class WebShareValidator extends Validator
    {
        @Override
        public void validate(final ShareMediaContent shareMediaContent) {
            throw new FacebookException("Cannot share ShareMediaContent via web sharing dialogs");
        }
        
        @Override
        public void validate(final SharePhoto sharePhoto) {
            validatePhotoForWebDialog(sharePhoto, (Validator)this);
        }
        
        @Override
        public void validate(final ShareVideoContent shareVideoContent) {
            throw new FacebookException("Cannot share ShareVideoContent via web sharing dialogs");
        }
    }
}
