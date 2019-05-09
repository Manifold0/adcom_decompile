package com.facebook.share.internal;

import android.os.Bundle;
import com.facebook.FacebookException;
import com.facebook.internal.Utility;
import com.facebook.internal.Utility.Mapper;
import com.facebook.share.model.AppGroupCreationContent;
import com.facebook.share.model.AppGroupCreationContent.AppGroupPrivacy;
import com.facebook.share.model.GameRequestContent;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class WebDialogParameters {

    /* renamed from: com.facebook.share.internal.WebDialogParameters$1 */
    static class C03721 implements Mapper<SharePhoto, String> {
        C03721() {
        }

        public String apply(SharePhoto item) {
            return item.getImageUrl().toString();
        }
    }

    public static Bundle create(AppGroupCreationContent appGroupCreationContent) {
        Bundle webParams = new Bundle();
        Utility.putNonEmptyString(webParams, "name", appGroupCreationContent.getName());
        Utility.putNonEmptyString(webParams, "description", appGroupCreationContent.getDescription());
        AppGroupPrivacy privacy = appGroupCreationContent.getAppGroupPrivacy();
        if (privacy != null) {
            Utility.putNonEmptyString(webParams, "privacy", privacy.toString().toLowerCase(Locale.ENGLISH));
        }
        return webParams;
    }

    public static Bundle create(GameRequestContent gameRequestContent) {
        Bundle webParams = new Bundle();
        Utility.putNonEmptyString(webParams, "message", gameRequestContent.getMessage());
        Utility.putCommaSeparatedStringList(webParams, "to", gameRequestContent.getRecipients());
        Utility.putNonEmptyString(webParams, "title", gameRequestContent.getTitle());
        Utility.putNonEmptyString(webParams, "data", gameRequestContent.getData());
        if (gameRequestContent.getActionType() != null) {
            Utility.putNonEmptyString(webParams, ShareConstants.WEB_DIALOG_PARAM_ACTION_TYPE, gameRequestContent.getActionType().toString().toLowerCase(Locale.ENGLISH));
        }
        Utility.putNonEmptyString(webParams, "object_id", gameRequestContent.getObjectId());
        if (gameRequestContent.getFilters() != null) {
            Utility.putNonEmptyString(webParams, ShareConstants.WEB_DIALOG_PARAM_FILTERS, gameRequestContent.getFilters().toString().toLowerCase(Locale.ENGLISH));
        }
        Utility.putCommaSeparatedStringList(webParams, ShareConstants.WEB_DIALOG_PARAM_SUGGESTIONS, gameRequestContent.getSuggestions());
        return webParams;
    }

    public static Bundle create(ShareLinkContent shareLinkContent) {
        Bundle params = createBaseParameters(shareLinkContent);
        Utility.putUri(params, ShareConstants.WEB_DIALOG_PARAM_HREF, shareLinkContent.getContentUrl());
        Utility.putNonEmptyString(params, ShareConstants.WEB_DIALOG_PARAM_QUOTE, shareLinkContent.getQuote());
        return params;
    }

    public static Bundle create(ShareOpenGraphContent shareOpenGraphContent) {
        Bundle params = createBaseParameters(shareOpenGraphContent);
        Utility.putNonEmptyString(params, ShareConstants.WEB_DIALOG_PARAM_ACTION_TYPE, shareOpenGraphContent.getAction().getActionType());
        try {
            JSONObject ogJSON = ShareInternalUtility.removeNamespacesFromOGJsonObject(ShareInternalUtility.toJSONObjectForWeb(shareOpenGraphContent), false);
            if (ogJSON != null) {
                Utility.putNonEmptyString(params, ShareConstants.WEB_DIALOG_PARAM_ACTION_PROPERTIES, ogJSON.toString());
            }
            return params;
        } catch (JSONException e) {
            throw new FacebookException("Unable to serialize the ShareOpenGraphContent to JSON", e);
        }
    }

    public static Bundle create(SharePhotoContent sharePhotoContent) {
        Bundle params = createBaseParameters(sharePhotoContent);
        String[] urls = new String[sharePhotoContent.getPhotos().size()];
        Utility.map(sharePhotoContent.getPhotos(), new C03721()).toArray(urls);
        params.putStringArray("media", urls);
        return params;
    }

    public static Bundle createBaseParameters(ShareContent shareContent) {
        Bundle params = new Bundle();
        ShareHashtag shareHashtag = shareContent.getShareHashtag();
        if (shareHashtag != null) {
            Utility.putNonEmptyString(params, ShareConstants.WEB_DIALOG_PARAM_HASHTAG, shareHashtag.getHashtag());
        }
        return params;
    }

    public static Bundle createForFeed(ShareLinkContent shareLinkContent) {
        Bundle webParams = new Bundle();
        Utility.putNonEmptyString(webParams, "name", shareLinkContent.getContentTitle());
        Utility.putNonEmptyString(webParams, "description", shareLinkContent.getContentDescription());
        Utility.putNonEmptyString(webParams, "link", Utility.getUriString(shareLinkContent.getContentUrl()));
        Utility.putNonEmptyString(webParams, "picture", Utility.getUriString(shareLinkContent.getImageUrl()));
        Utility.putNonEmptyString(webParams, ShareConstants.WEB_DIALOG_PARAM_QUOTE, shareLinkContent.getQuote());
        if (shareLinkContent.getShareHashtag() != null) {
            Utility.putNonEmptyString(webParams, ShareConstants.WEB_DIALOG_PARAM_HASHTAG, shareLinkContent.getShareHashtag().getHashtag());
        }
        return webParams;
    }

    public static Bundle createForFeed(ShareFeedContent shareFeedContent) {
        Bundle webParams = new Bundle();
        Utility.putNonEmptyString(webParams, "to", shareFeedContent.getToId());
        Utility.putNonEmptyString(webParams, "link", shareFeedContent.getLink());
        Utility.putNonEmptyString(webParams, "picture", shareFeedContent.getPicture());
        Utility.putNonEmptyString(webParams, ShareConstants.FEED_SOURCE_PARAM, shareFeedContent.getMediaSource());
        Utility.putNonEmptyString(webParams, "name", shareFeedContent.getLinkName());
        Utility.putNonEmptyString(webParams, ShareConstants.FEED_CAPTION_PARAM, shareFeedContent.getLinkCaption());
        Utility.putNonEmptyString(webParams, "description", shareFeedContent.getLinkDescription());
        return webParams;
    }
}
