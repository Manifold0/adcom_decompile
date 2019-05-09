// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.internal;

import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.SharePhoto;
import com.facebook.internal.Utility$Mapper;
import com.facebook.share.model.SharePhotoContent;
import org.json.JSONObject;
import org.json.JSONException;
import com.facebook.FacebookException;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import java.util.List;
import com.facebook.share.model.GameRequestContent;
import java.util.Locale;
import com.facebook.internal.Utility;
import android.os.Bundle;
import com.facebook.share.model.AppGroupCreationContent;

public class WebDialogParameters
{
    public static Bundle create(final AppGroupCreationContent appGroupCreationContent) {
        final Bundle bundle = new Bundle();
        Utility.putNonEmptyString(bundle, "name", appGroupCreationContent.getName());
        Utility.putNonEmptyString(bundle, "description", appGroupCreationContent.getDescription());
        final AppGroupCreationContent.AppGroupPrivacy appGroupPrivacy = appGroupCreationContent.getAppGroupPrivacy();
        if (appGroupPrivacy != null) {
            Utility.putNonEmptyString(bundle, "privacy", appGroupPrivacy.toString().toLowerCase(Locale.ENGLISH));
        }
        return bundle;
    }
    
    public static Bundle create(final GameRequestContent gameRequestContent) {
        final Bundle bundle = new Bundle();
        Utility.putNonEmptyString(bundle, "message", gameRequestContent.getMessage());
        Utility.putCommaSeparatedStringList(bundle, "to", (List)gameRequestContent.getRecipients());
        Utility.putNonEmptyString(bundle, "title", gameRequestContent.getTitle());
        Utility.putNonEmptyString(bundle, "data", gameRequestContent.getData());
        if (gameRequestContent.getActionType() != null) {
            Utility.putNonEmptyString(bundle, "action_type", gameRequestContent.getActionType().toString().toLowerCase(Locale.ENGLISH));
        }
        Utility.putNonEmptyString(bundle, "object_id", gameRequestContent.getObjectId());
        if (gameRequestContent.getFilters() != null) {
            Utility.putNonEmptyString(bundle, "filters", gameRequestContent.getFilters().toString().toLowerCase(Locale.ENGLISH));
        }
        Utility.putCommaSeparatedStringList(bundle, "suggestions", (List)gameRequestContent.getSuggestions());
        return bundle;
    }
    
    public static Bundle create(final ShareLinkContent shareLinkContent) {
        final Bundle baseParameters = createBaseParameters(shareLinkContent);
        Utility.putUri(baseParameters, "href", shareLinkContent.getContentUrl());
        Utility.putNonEmptyString(baseParameters, "quote", shareLinkContent.getQuote());
        return baseParameters;
    }
    
    public static Bundle create(final ShareOpenGraphContent shareOpenGraphContent) {
        final Bundle baseParameters = createBaseParameters(shareOpenGraphContent);
        Utility.putNonEmptyString(baseParameters, "action_type", shareOpenGraphContent.getAction().getActionType());
        try {
            final JSONObject removeNamespacesFromOGJsonObject = ShareInternalUtility.removeNamespacesFromOGJsonObject(ShareInternalUtility.toJSONObjectForWeb(shareOpenGraphContent), false);
            if (removeNamespacesFromOGJsonObject != null) {
                Utility.putNonEmptyString(baseParameters, "action_properties", removeNamespacesFromOGJsonObject.toString());
            }
            return baseParameters;
        }
        catch (JSONException ex) {
            throw new FacebookException("Unable to serialize the ShareOpenGraphContent to JSON", (Throwable)ex);
        }
    }
    
    public static Bundle create(final SharePhotoContent sharePhotoContent) {
        final Bundle baseParameters = createBaseParameters(sharePhotoContent);
        final String[] array = new String[sharePhotoContent.getPhotos().size()];
        Utility.map((List)sharePhotoContent.getPhotos(), (Utility$Mapper)new Utility$Mapper<SharePhoto, String>() {
            public String apply(final SharePhoto sharePhoto) {
                return sharePhoto.getImageUrl().toString();
            }
        }).toArray(array);
        baseParameters.putStringArray("media", array);
        return baseParameters;
    }
    
    public static Bundle createBaseParameters(final ShareContent shareContent) {
        final Bundle bundle = new Bundle();
        final ShareHashtag shareHashtag = shareContent.getShareHashtag();
        if (shareHashtag != null) {
            Utility.putNonEmptyString(bundle, "hashtag", shareHashtag.getHashtag());
        }
        return bundle;
    }
    
    public static Bundle createForFeed(final ShareFeedContent shareFeedContent) {
        final Bundle bundle = new Bundle();
        Utility.putNonEmptyString(bundle, "to", shareFeedContent.getToId());
        Utility.putNonEmptyString(bundle, "link", shareFeedContent.getLink());
        Utility.putNonEmptyString(bundle, "picture", shareFeedContent.getPicture());
        Utility.putNonEmptyString(bundle, "source", shareFeedContent.getMediaSource());
        Utility.putNonEmptyString(bundle, "name", shareFeedContent.getLinkName());
        Utility.putNonEmptyString(bundle, "caption", shareFeedContent.getLinkCaption());
        Utility.putNonEmptyString(bundle, "description", shareFeedContent.getLinkDescription());
        return bundle;
    }
    
    public static Bundle createForFeed(final ShareLinkContent shareLinkContent) {
        final Bundle bundle = new Bundle();
        Utility.putNonEmptyString(bundle, "name", shareLinkContent.getContentTitle());
        Utility.putNonEmptyString(bundle, "description", shareLinkContent.getContentDescription());
        Utility.putNonEmptyString(bundle, "link", Utility.getUriString(shareLinkContent.getContentUrl()));
        Utility.putNonEmptyString(bundle, "picture", Utility.getUriString(shareLinkContent.getImageUrl()));
        Utility.putNonEmptyString(bundle, "quote", shareLinkContent.getQuote());
        if (shareLinkContent.getShareHashtag() != null) {
            Utility.putNonEmptyString(bundle, "hashtag", shareLinkContent.getShareHashtag().getHashtag());
        }
        return bundle;
    }
}
