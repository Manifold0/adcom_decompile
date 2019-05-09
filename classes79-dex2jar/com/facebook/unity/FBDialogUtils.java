// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.unity;

import com.facebook.share.widget.ShareDialog$Mode;
import android.net.Uri;
import com.facebook.share.model.ShareLinkContent$Builder;
import com.facebook.share.internal.ShareFeedContent$Builder;
import android.os.Bundle;

class FBDialogUtils
{
    public static ShareFeedContent$Builder createFeedContentBuilder(final Bundle bundle) {
        final ShareFeedContent$Builder shareFeedContent$Builder = new ShareFeedContent$Builder();
        if (bundle.containsKey("toId")) {
            shareFeedContent$Builder.setToId(bundle.getString("toId"));
        }
        if (bundle.containsKey("link")) {
            shareFeedContent$Builder.setLink(bundle.getString("link"));
        }
        if (bundle.containsKey("linkName")) {
            shareFeedContent$Builder.setLinkName(bundle.getString("linkName"));
        }
        if (bundle.containsKey("linkCaption")) {
            shareFeedContent$Builder.setLinkCaption(bundle.getString("linkCaption"));
        }
        if (bundle.containsKey("linkDescription")) {
            shareFeedContent$Builder.setLinkDescription(bundle.getString("linkDescription"));
        }
        if (bundle.containsKey("picture")) {
            shareFeedContent$Builder.setPicture(bundle.getString("picture"));
        }
        if (bundle.containsKey("mediaSource")) {
            shareFeedContent$Builder.setMediaSource(bundle.getString("mediaSource"));
        }
        return shareFeedContent$Builder;
    }
    
    public static ShareLinkContent$Builder createShareContentBuilder(final Bundle bundle) {
        final ShareLinkContent$Builder shareLinkContent$Builder = new ShareLinkContent$Builder();
        if (bundle.containsKey("content_title")) {
            shareLinkContent$Builder.setContentTitle(bundle.getString("content_title"));
        }
        if (bundle.containsKey("content_description")) {
            shareLinkContent$Builder.setContentDescription(bundle.getString("content_description"));
        }
        if (bundle.containsKey("content_url")) {
            shareLinkContent$Builder.setContentUrl(Uri.parse(bundle.getString("content_url")));
        }
        if (bundle.containsKey("photo_url")) {
            shareLinkContent$Builder.setImageUrl(Uri.parse(bundle.getString("photo_url")));
        }
        return shareLinkContent$Builder;
    }
    
    public static ShareDialog$Mode intToMode(final int n) {
        switch (n) {
            default: {
                return null;
            }
            case 0: {
                return ShareDialog$Mode.AUTOMATIC;
            }
            case 1: {
                return ShareDialog$Mode.NATIVE;
            }
            case 2: {
                return ShareDialog$Mode.WEB;
            }
            case 3: {
                return ShareDialog$Mode.FEED;
            }
        }
    }
}
