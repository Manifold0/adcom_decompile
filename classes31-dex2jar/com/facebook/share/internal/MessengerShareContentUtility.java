// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.internal;

import org.json.JSONArray;
import org.json.JSONObject;
import android.net.Uri;
import com.facebook.share.model.ShareMessengerOpenGraphMusicTemplateContent;
import com.facebook.share.model.ShareMessengerMediaTemplateContent;
import com.facebook.share.model.ShareMessengerGenericTemplateElement;
import com.facebook.internal.Utility;
import com.facebook.share.model.ShareMessengerGenericTemplateContent;
import org.json.JSONException;
import com.facebook.share.model.ShareMessengerURLActionButton;
import com.facebook.share.model.ShareMessengerActionButton;
import android.os.Bundle;
import java.util.regex.Pattern;

public class MessengerShareContentUtility
{
    public static final String ATTACHMENT = "attachment";
    public static final String ATTACHMENT_ID = "attachment_id";
    public static final String ATTACHMENT_PAYLOAD = "payload";
    public static final String ATTACHMENT_TEMPLATE_TYPE = "template";
    public static final String ATTACHMENT_TYPE = "type";
    public static final String BUTTONS = "buttons";
    public static final String BUTTON_TYPE = "type";
    public static final String BUTTON_URL_TYPE = "web_url";
    public static final String DEFAULT_ACTION = "default_action";
    public static final String ELEMENTS = "elements";
    public static final Pattern FACEBOOK_DOMAIN;
    public static final String FALLBACK_URL = "fallback_url";
    public static final String IMAGE_ASPECT_RATIO = "image_aspect_ratio";
    public static final String IMAGE_RATIO_HORIZONTAL = "horizontal";
    public static final String IMAGE_RATIO_SQUARE = "square";
    public static final String IMAGE_URL = "image_url";
    public static final String MEDIA_IMAGE = "image";
    public static final String MEDIA_TYPE = "media_type";
    public static final String MEDIA_VIDEO = "video";
    public static final String MESSENGER_EXTENSIONS = "messenger_extensions";
    public static final String PREVIEW_DEFAULT = "DEFAULT";
    public static final String PREVIEW_OPEN_GRAPH = "OPEN_GRAPH";
    public static final String SHARABLE = "sharable";
    public static final String SHARE_BUTTON_HIDE = "hide";
    public static final String SUBTITLE = "subtitle";
    public static final String TEMPLATE_GENERIC_TYPE = "generic";
    public static final String TEMPLATE_MEDIA_TYPE = "media";
    public static final String TEMPLATE_OPEN_GRAPH_TYPE = "open_graph";
    public static final String TEMPLATE_TYPE = "template_type";
    public static final String TITLE = "title";
    public static final String URL = "url";
    public static final String WEBVIEW_RATIO = "webview_height_ratio";
    public static final String WEBVIEW_RATIO_COMPACT = "compact";
    public static final String WEBVIEW_RATIO_FULL = "full";
    public static final String WEBVIEW_RATIO_TALL = "tall";
    public static final String WEBVIEW_SHARE_BUTTON = "webview_share_button";
    
    static {
        FACEBOOK_DOMAIN = Pattern.compile("^(.+)\\.(facebook\\.com)$");
    }
    
    private static void addActionButton(final Bundle bundle, final ShareMessengerActionButton shareMessengerActionButton, final boolean b) throws JSONException {
        if (shareMessengerActionButton != null && shareMessengerActionButton instanceof ShareMessengerURLActionButton) {
            addURLActionButton(bundle, (ShareMessengerURLActionButton)shareMessengerActionButton, b);
        }
    }
    
    public static void addGenericTemplateContent(final Bundle bundle, final ShareMessengerGenericTemplateContent shareMessengerGenericTemplateContent) throws JSONException {
        addGenericTemplateElementForPreview(bundle, shareMessengerGenericTemplateContent.getGenericTemplateElement());
        Utility.putJSONValueInBundle(bundle, "MESSENGER_PLATFORM_CONTENT", (Object)serializeGenericTemplateContent(shareMessengerGenericTemplateContent));
    }
    
    private static void addGenericTemplateElementForPreview(final Bundle bundle, final ShareMessengerGenericTemplateElement shareMessengerGenericTemplateElement) throws JSONException {
        if (shareMessengerGenericTemplateElement.getButton() != null) {
            addActionButton(bundle, shareMessengerGenericTemplateElement.getButton(), false);
        }
        else if (shareMessengerGenericTemplateElement.getDefaultAction() != null) {
            addActionButton(bundle, shareMessengerGenericTemplateElement.getDefaultAction(), true);
        }
        Utility.putUri(bundle, "IMAGE", shareMessengerGenericTemplateElement.getImageUrl());
        Utility.putNonEmptyString(bundle, "PREVIEW_TYPE", "DEFAULT");
        Utility.putNonEmptyString(bundle, "TITLE", shareMessengerGenericTemplateElement.getTitle());
        Utility.putNonEmptyString(bundle, "SUBTITLE", shareMessengerGenericTemplateElement.getSubtitle());
    }
    
    public static void addMediaTemplateContent(final Bundle bundle, final ShareMessengerMediaTemplateContent shareMessengerMediaTemplateContent) throws JSONException {
        addMediaTemplateContentForPreview(bundle, shareMessengerMediaTemplateContent);
        Utility.putJSONValueInBundle(bundle, "MESSENGER_PLATFORM_CONTENT", (Object)serializeMediaTemplateContent(shareMessengerMediaTemplateContent));
    }
    
    private static void addMediaTemplateContentForPreview(final Bundle bundle, final ShareMessengerMediaTemplateContent shareMessengerMediaTemplateContent) throws JSONException {
        addActionButton(bundle, shareMessengerMediaTemplateContent.getButton(), false);
        Utility.putNonEmptyString(bundle, "PREVIEW_TYPE", "DEFAULT");
        Utility.putNonEmptyString(bundle, "ATTACHMENT_ID", shareMessengerMediaTemplateContent.getAttachmentId());
        if (shareMessengerMediaTemplateContent.getMediaUrl() != null) {
            Utility.putUri(bundle, getMediaUrlKey(shareMessengerMediaTemplateContent.getMediaUrl()), shareMessengerMediaTemplateContent.getMediaUrl());
        }
        Utility.putNonEmptyString(bundle, "type", getMediaType(shareMessengerMediaTemplateContent.getMediaType()));
    }
    
    public static void addOpenGraphMusicTemplateContent(final Bundle bundle, final ShareMessengerOpenGraphMusicTemplateContent shareMessengerOpenGraphMusicTemplateContent) throws JSONException {
        addOpenGraphMusicTemplateContentForPreview(bundle, shareMessengerOpenGraphMusicTemplateContent);
        Utility.putJSONValueInBundle(bundle, "MESSENGER_PLATFORM_CONTENT", (Object)serializeOpenGraphMusicTemplateContent(shareMessengerOpenGraphMusicTemplateContent));
    }
    
    private static void addOpenGraphMusicTemplateContentForPreview(final Bundle bundle, final ShareMessengerOpenGraphMusicTemplateContent shareMessengerOpenGraphMusicTemplateContent) throws JSONException {
        addActionButton(bundle, shareMessengerOpenGraphMusicTemplateContent.getButton(), false);
        Utility.putNonEmptyString(bundle, "PREVIEW_TYPE", "OPEN_GRAPH");
        Utility.putUri(bundle, "OPEN_GRAPH_URL", shareMessengerOpenGraphMusicTemplateContent.getUrl());
    }
    
    private static void addURLActionButton(final Bundle bundle, final ShareMessengerURLActionButton shareMessengerURLActionButton, final boolean b) throws JSONException {
        String s;
        if (b) {
            s = Utility.getUriString(shareMessengerURLActionButton.getUrl());
        }
        else {
            s = shareMessengerURLActionButton.getTitle() + " - " + Utility.getUriString(shareMessengerURLActionButton.getUrl());
        }
        Utility.putNonEmptyString(bundle, "TARGET_DISPLAY", s);
        Utility.putUri(bundle, "ITEM_URL", shareMessengerURLActionButton.getUrl());
    }
    
    private static String getImageRatioString(final ShareMessengerGenericTemplateContent.ImageAspectRatio imageAspectRatio) {
        if (imageAspectRatio == null) {
            return "horizontal";
        }
        switch (imageAspectRatio) {
            default: {
                return "horizontal";
            }
            case SQUARE: {
                return "square";
            }
        }
    }
    
    private static String getMediaType(final ShareMessengerMediaTemplateContent.MediaType mediaType) {
        if (mediaType == null) {
            return "image";
        }
        switch (mediaType) {
            default: {
                return "image";
            }
            case VIDEO: {
                return "video";
            }
        }
    }
    
    private static String getMediaUrlKey(final Uri uri) {
        final String host = uri.getHost();
        if (!Utility.isNullOrEmpty(host) && MessengerShareContentUtility.FACEBOOK_DOMAIN.matcher(host).matches()) {
            return "uri";
        }
        return "IMAGE";
    }
    
    private static String getShouldHideShareButton(final ShareMessengerURLActionButton shareMessengerURLActionButton) {
        if (shareMessengerURLActionButton.getShouldHideWebviewShareButton()) {
            return "hide";
        }
        return null;
    }
    
    private static String getWebviewHeightRatioString(final ShareMessengerURLActionButton.WebviewHeightRatio webviewHeightRatio) {
        if (webviewHeightRatio == null) {
            return "full";
        }
        switch (webviewHeightRatio) {
            default: {
                return "full";
            }
            case WebviewHeightRatioCompact: {
                return "compact";
            }
            case WebviewHeightRatioTall: {
                return "tall";
            }
        }
    }
    
    private static JSONObject serializeActionButton(final ShareMessengerActionButton shareMessengerActionButton) throws JSONException {
        return serializeActionButton(shareMessengerActionButton, false);
    }
    
    private static JSONObject serializeActionButton(final ShareMessengerActionButton shareMessengerActionButton, final boolean b) throws JSONException {
        if (shareMessengerActionButton instanceof ShareMessengerURLActionButton) {
            return serializeURLActionButton((ShareMessengerURLActionButton)shareMessengerActionButton, b);
        }
        return null;
    }
    
    private static JSONObject serializeGenericTemplateContent(final ShareMessengerGenericTemplateContent shareMessengerGenericTemplateContent) throws JSONException {
        return new JSONObject().put("attachment", (Object)new JSONObject().put("type", (Object)"template").put("payload", (Object)new JSONObject().put("template_type", (Object)"generic").put("sharable", shareMessengerGenericTemplateContent.getIsSharable()).put("image_aspect_ratio", (Object)getImageRatioString(shareMessengerGenericTemplateContent.getImageAspectRatio())).put("elements", (Object)new JSONArray().put((Object)serializeGenericTemplateElement(shareMessengerGenericTemplateContent.getGenericTemplateElement())))));
    }
    
    private static JSONObject serializeGenericTemplateElement(final ShareMessengerGenericTemplateElement shareMessengerGenericTemplateElement) throws JSONException {
        final JSONObject put = new JSONObject().put("title", (Object)shareMessengerGenericTemplateElement.getTitle()).put("subtitle", (Object)shareMessengerGenericTemplateElement.getSubtitle()).put("image_url", (Object)Utility.getUriString(shareMessengerGenericTemplateElement.getImageUrl()));
        if (shareMessengerGenericTemplateElement.getButton() != null) {
            final JSONArray jsonArray = new JSONArray();
            jsonArray.put((Object)serializeActionButton(shareMessengerGenericTemplateElement.getButton()));
            put.put("buttons", (Object)jsonArray);
        }
        if (shareMessengerGenericTemplateElement.getDefaultAction() != null) {
            put.put("default_action", (Object)serializeActionButton(shareMessengerGenericTemplateElement.getDefaultAction(), true));
        }
        return put;
    }
    
    private static JSONObject serializeMediaTemplateContent(final ShareMessengerMediaTemplateContent shareMessengerMediaTemplateContent) throws JSONException {
        return new JSONObject().put("attachment", (Object)new JSONObject().put("type", (Object)"template").put("payload", (Object)new JSONObject().put("template_type", (Object)"media").put("elements", (Object)new JSONArray().put((Object)serializeMediaTemplateElement(shareMessengerMediaTemplateContent)))));
    }
    
    private static JSONObject serializeMediaTemplateElement(final ShareMessengerMediaTemplateContent shareMessengerMediaTemplateContent) throws JSONException {
        final JSONObject put = new JSONObject().put("attachment_id", (Object)shareMessengerMediaTemplateContent.getAttachmentId()).put("url", (Object)Utility.getUriString(shareMessengerMediaTemplateContent.getMediaUrl())).put("media_type", (Object)getMediaType(shareMessengerMediaTemplateContent.getMediaType()));
        if (shareMessengerMediaTemplateContent.getButton() != null) {
            final JSONArray jsonArray = new JSONArray();
            jsonArray.put((Object)serializeActionButton(shareMessengerMediaTemplateContent.getButton()));
            put.put("buttons", (Object)jsonArray);
        }
        return put;
    }
    
    private static JSONObject serializeOpenGraphMusicTemplateContent(final ShareMessengerOpenGraphMusicTemplateContent shareMessengerOpenGraphMusicTemplateContent) throws JSONException {
        return new JSONObject().put("attachment", (Object)new JSONObject().put("type", (Object)"template").put("payload", (Object)new JSONObject().put("template_type", (Object)"open_graph").put("elements", (Object)new JSONArray().put((Object)serializeOpenGraphMusicTemplateElement(shareMessengerOpenGraphMusicTemplateContent)))));
    }
    
    private static JSONObject serializeOpenGraphMusicTemplateElement(final ShareMessengerOpenGraphMusicTemplateContent shareMessengerOpenGraphMusicTemplateContent) throws JSONException {
        final JSONObject put = new JSONObject().put("url", (Object)Utility.getUriString(shareMessengerOpenGraphMusicTemplateContent.getUrl()));
        if (shareMessengerOpenGraphMusicTemplateContent.getButton() != null) {
            final JSONArray jsonArray = new JSONArray();
            jsonArray.put((Object)serializeActionButton(shareMessengerOpenGraphMusicTemplateContent.getButton()));
            put.put("buttons", (Object)jsonArray);
        }
        return put;
    }
    
    private static JSONObject serializeURLActionButton(final ShareMessengerURLActionButton shareMessengerURLActionButton, final boolean b) throws JSONException {
        final JSONObject put = new JSONObject().put("type", (Object)"web_url");
        Object title;
        if (b) {
            title = null;
        }
        else {
            title = shareMessengerURLActionButton.getTitle();
        }
        return put.put("title", title).put("url", (Object)Utility.getUriString(shareMessengerURLActionButton.getUrl())).put("webview_height_ratio", (Object)getWebviewHeightRatioString(shareMessengerURLActionButton.getWebviewHeightRatio())).put("messenger_extensions", shareMessengerURLActionButton.getIsMessengerExtensionURL()).put("fallback_url", (Object)Utility.getUriString(shareMessengerURLActionButton.getFallbackUrl())).put("webview_share_button", (Object)getShouldHideShareButton(shareMessengerURLActionButton));
    }
}
