// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.internal;

import com.facebook.share.model.ShareHashtag;
import com.facebook.internal.Validate;
import java.util.UUID;
import com.facebook.share.model.ShareVideoContent;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.ShareMessengerOpenGraphMusicTemplateContent;
import com.facebook.share.model.ShareMessengerMediaTemplateContent;
import com.facebook.share.model.ShareMessengerGenericTemplateContent;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import com.facebook.share.model.ShareMediaContent;
import com.facebook.share.model.ShareLinkContent;
import org.json.JSONObject;
import org.json.JSONException;
import com.facebook.FacebookException;
import com.facebook.internal.Utility;
import com.facebook.share.model.ShareContent;
import android.os.Bundle;
import com.facebook.share.model.ShareCameraEffectContent;

public class NativeDialogParameters
{
    private static Bundle create(final ShareCameraEffectContent shareCameraEffectContent, final Bundle bundle, final boolean b) {
        final Bundle baseParameters = createBaseParameters(shareCameraEffectContent, b);
        Utility.putNonEmptyString(baseParameters, "effect_id", shareCameraEffectContent.getEffectId());
        if (bundle != null) {
            baseParameters.putBundle("effect_textures", bundle);
        }
        try {
            final JSONObject convertToJSON = CameraEffectJSONUtility.convertToJSON(shareCameraEffectContent.getArguments());
            if (convertToJSON != null) {
                Utility.putNonEmptyString(baseParameters, "effect_arguments", convertToJSON.toString());
            }
            return baseParameters;
        }
        catch (JSONException ex) {
            throw new FacebookException("Unable to create a JSON Object from the provided CameraEffectArguments: " + ex.getMessage());
        }
    }
    
    private static Bundle create(final ShareLinkContent shareLinkContent, final boolean b) {
        final Bundle baseParameters = createBaseParameters(shareLinkContent, b);
        Utility.putNonEmptyString(baseParameters, "TITLE", shareLinkContent.getContentTitle());
        Utility.putNonEmptyString(baseParameters, "DESCRIPTION", shareLinkContent.getContentDescription());
        Utility.putUri(baseParameters, "IMAGE", shareLinkContent.getImageUrl());
        Utility.putNonEmptyString(baseParameters, "QUOTE", shareLinkContent.getQuote());
        Utility.putUri(baseParameters, "MESSENGER_LINK", shareLinkContent.getContentUrl());
        Utility.putUri(baseParameters, "TARGET_DISPLAY", shareLinkContent.getContentUrl());
        return baseParameters;
    }
    
    private static Bundle create(final ShareMediaContent shareMediaContent, final List<Bundle> list, final boolean b) {
        final Bundle baseParameters = createBaseParameters(shareMediaContent, b);
        baseParameters.putParcelableArrayList("MEDIA", new ArrayList((Collection<? extends E>)list));
        return baseParameters;
    }
    
    private static Bundle create(final ShareMessengerGenericTemplateContent shareMessengerGenericTemplateContent, final boolean b) {
        final Bundle baseParameters = createBaseParameters(shareMessengerGenericTemplateContent, b);
        try {
            MessengerShareContentUtility.addGenericTemplateContent(baseParameters, shareMessengerGenericTemplateContent);
            return baseParameters;
        }
        catch (JSONException ex) {
            throw new FacebookException("Unable to create a JSON Object from the provided ShareMessengerGenericTemplateContent: " + ex.getMessage());
        }
    }
    
    private static Bundle create(final ShareMessengerMediaTemplateContent shareMessengerMediaTemplateContent, final boolean b) {
        final Bundle baseParameters = createBaseParameters(shareMessengerMediaTemplateContent, b);
        try {
            MessengerShareContentUtility.addMediaTemplateContent(baseParameters, shareMessengerMediaTemplateContent);
            return baseParameters;
        }
        catch (JSONException ex) {
            throw new FacebookException("Unable to create a JSON Object from the provided ShareMessengerMediaTemplateContent: " + ex.getMessage());
        }
    }
    
    private static Bundle create(final ShareMessengerOpenGraphMusicTemplateContent shareMessengerOpenGraphMusicTemplateContent, final boolean b) {
        final Bundle baseParameters = createBaseParameters(shareMessengerOpenGraphMusicTemplateContent, b);
        try {
            MessengerShareContentUtility.addOpenGraphMusicTemplateContent(baseParameters, shareMessengerOpenGraphMusicTemplateContent);
            return baseParameters;
        }
        catch (JSONException ex) {
            throw new FacebookException("Unable to create a JSON Object from the provided ShareMessengerOpenGraphMusicTemplateContent: " + ex.getMessage());
        }
    }
    
    private static Bundle create(final ShareOpenGraphContent shareOpenGraphContent, final JSONObject jsonObject, final boolean b) {
        final Bundle baseParameters = createBaseParameters(shareOpenGraphContent, b);
        Utility.putNonEmptyString(baseParameters, "PREVIEW_PROPERTY_NAME", (String)ShareInternalUtility.getFieldNameAndNamespaceFromFullName(shareOpenGraphContent.getPreviewPropertyName()).second);
        Utility.putNonEmptyString(baseParameters, "ACTION_TYPE", shareOpenGraphContent.getAction().getActionType());
        Utility.putNonEmptyString(baseParameters, "ACTION", jsonObject.toString());
        return baseParameters;
    }
    
    private static Bundle create(final SharePhotoContent sharePhotoContent, final List<String> list, final boolean b) {
        final Bundle baseParameters = createBaseParameters(sharePhotoContent, b);
        baseParameters.putStringArrayList("PHOTOS", new ArrayList((Collection<? extends E>)list));
        return baseParameters;
    }
    
    private static Bundle create(final ShareVideoContent shareVideoContent, final String s, final boolean b) {
        final Bundle baseParameters = createBaseParameters(shareVideoContent, b);
        Utility.putNonEmptyString(baseParameters, "TITLE", shareVideoContent.getContentTitle());
        Utility.putNonEmptyString(baseParameters, "DESCRIPTION", shareVideoContent.getContentDescription());
        Utility.putNonEmptyString(baseParameters, "VIDEO", s);
        return baseParameters;
    }
    
    public static Bundle create(final UUID uuid, final ShareContent shareContent, final boolean b) {
        Validate.notNull((Object)shareContent, "shareContent");
        Validate.notNull((Object)uuid, "callId");
        final Bundle bundle = null;
        Bundle create;
        if (shareContent instanceof ShareLinkContent) {
            create = create((ShareLinkContent)shareContent, b);
        }
        else {
            if (shareContent instanceof SharePhotoContent) {
                final SharePhotoContent sharePhotoContent = (SharePhotoContent)shareContent;
                return create(sharePhotoContent, ShareInternalUtility.getPhotoUrls(sharePhotoContent, uuid), b);
            }
            if (shareContent instanceof ShareVideoContent) {
                final ShareVideoContent shareVideoContent = (ShareVideoContent)shareContent;
                return create(shareVideoContent, ShareInternalUtility.getVideoUrl(shareVideoContent, uuid), b);
            }
            if (shareContent instanceof ShareOpenGraphContent) {
                final ShareOpenGraphContent shareOpenGraphContent = (ShareOpenGraphContent)shareContent;
                try {
                    return create(shareOpenGraphContent, ShareInternalUtility.removeNamespacesFromOGJsonObject(ShareInternalUtility.toJSONObjectForCall(uuid, shareOpenGraphContent), false), b);
                }
                catch (JSONException ex) {
                    throw new FacebookException("Unable to create a JSON Object from the provided ShareOpenGraphContent: " + ex.getMessage());
                }
            }
            if (shareContent instanceof ShareMediaContent) {
                final ShareMediaContent shareMediaContent = (ShareMediaContent)shareContent;
                return create(shareMediaContent, ShareInternalUtility.getMediaInfos(shareMediaContent, uuid), b);
            }
            if (shareContent instanceof ShareCameraEffectContent) {
                final ShareCameraEffectContent shareCameraEffectContent = (ShareCameraEffectContent)shareContent;
                return create(shareCameraEffectContent, ShareInternalUtility.getTextureUrlBundle(shareCameraEffectContent, uuid), b);
            }
            if (shareContent instanceof ShareMessengerGenericTemplateContent) {
                return create((ShareMessengerGenericTemplateContent)shareContent, b);
            }
            if (shareContent instanceof ShareMessengerOpenGraphMusicTemplateContent) {
                return create((ShareMessengerOpenGraphMusicTemplateContent)shareContent, b);
            }
            create = bundle;
            if (shareContent instanceof ShareMessengerMediaTemplateContent) {
                return create((ShareMessengerMediaTemplateContent)shareContent, b);
            }
        }
        return create;
    }
    
    private static Bundle createBaseParameters(final ShareContent shareContent, final boolean b) {
        final Bundle bundle = new Bundle();
        Utility.putUri(bundle, "LINK", shareContent.getContentUrl());
        Utility.putNonEmptyString(bundle, "PLACE", shareContent.getPlaceId());
        Utility.putNonEmptyString(bundle, "PAGE", shareContent.getPageId());
        Utility.putNonEmptyString(bundle, "REF", shareContent.getRef());
        bundle.putBoolean("DATA_FAILURES_FATAL", b);
        final List peopleIds = shareContent.getPeopleIds();
        if (!Utility.isNullOrEmpty((Collection)peopleIds)) {
            bundle.putStringArrayList("FRIENDS", new ArrayList(peopleIds));
        }
        final ShareHashtag shareHashtag = shareContent.getShareHashtag();
        if (shareHashtag != null) {
            Utility.putNonEmptyString(bundle, "HASHTAG", shareHashtag.getHashtag());
        }
        return bundle;
    }
}
