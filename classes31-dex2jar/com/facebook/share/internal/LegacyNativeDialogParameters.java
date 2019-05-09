// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.internal;

import org.json.JSONException;
import com.facebook.FacebookException;
import com.facebook.internal.Validate;
import java.util.UUID;
import com.facebook.share.model.ShareVideoContent;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import com.facebook.share.model.SharePhotoContent;
import org.json.JSONObject;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.internal.Utility;
import com.facebook.share.model.ShareContent;
import android.os.Bundle;
import com.facebook.share.model.ShareLinkContent;

public class LegacyNativeDialogParameters
{
    private static Bundle create(final ShareLinkContent shareLinkContent, final boolean b) {
        final Bundle baseParameters = createBaseParameters(shareLinkContent, b);
        Utility.putNonEmptyString(baseParameters, "com.facebook.platform.extra.TITLE", shareLinkContent.getContentTitle());
        Utility.putNonEmptyString(baseParameters, "com.facebook.platform.extra.DESCRIPTION", shareLinkContent.getContentDescription());
        Utility.putUri(baseParameters, "com.facebook.platform.extra.IMAGE", shareLinkContent.getImageUrl());
        return baseParameters;
    }
    
    private static Bundle create(final ShareOpenGraphContent shareOpenGraphContent, final JSONObject jsonObject, final boolean b) {
        final Bundle baseParameters = createBaseParameters(shareOpenGraphContent, b);
        Utility.putNonEmptyString(baseParameters, "com.facebook.platform.extra.PREVIEW_PROPERTY_NAME", shareOpenGraphContent.getPreviewPropertyName());
        Utility.putNonEmptyString(baseParameters, "com.facebook.platform.extra.ACTION_TYPE", shareOpenGraphContent.getAction().getActionType());
        Utility.putNonEmptyString(baseParameters, "com.facebook.platform.extra.ACTION", jsonObject.toString());
        return baseParameters;
    }
    
    private static Bundle create(final SharePhotoContent sharePhotoContent, final List<String> list, final boolean b) {
        final Bundle baseParameters = createBaseParameters(sharePhotoContent, b);
        baseParameters.putStringArrayList("com.facebook.platform.extra.PHOTOS", new ArrayList((Collection<? extends E>)list));
        return baseParameters;
    }
    
    private static Bundle create(final ShareVideoContent shareVideoContent, final boolean b) {
        return null;
    }
    
    public static Bundle create(final UUID uuid, final ShareContent shareContent, final boolean b) {
        Validate.notNull((Object)shareContent, "shareContent");
        Validate.notNull((Object)uuid, "callId");
        Bundle create = null;
        if (shareContent instanceof ShareLinkContent) {
            create = create((ShareLinkContent)shareContent, b);
        }
        else {
            if (shareContent instanceof SharePhotoContent) {
                final SharePhotoContent sharePhotoContent = (SharePhotoContent)shareContent;
                return create(sharePhotoContent, ShareInternalUtility.getPhotoUrls(sharePhotoContent, uuid), b);
            }
            if (shareContent instanceof ShareVideoContent) {
                return create((ShareVideoContent)shareContent, b);
            }
            if (shareContent instanceof ShareOpenGraphContent) {
                final ShareOpenGraphContent shareOpenGraphContent = (ShareOpenGraphContent)shareContent;
                try {
                    return create(shareOpenGraphContent, ShareInternalUtility.toJSONObjectForCall(uuid, shareOpenGraphContent), b);
                }
                catch (JSONException ex) {
                    throw new FacebookException("Unable to create a JSON Object from the provided ShareOpenGraphContent: " + ex.getMessage());
                }
            }
        }
        return create;
    }
    
    private static Bundle createBaseParameters(final ShareContent shareContent, final boolean b) {
        final Bundle bundle = new Bundle();
        Utility.putUri(bundle, "com.facebook.platform.extra.LINK", shareContent.getContentUrl());
        Utility.putNonEmptyString(bundle, "com.facebook.platform.extra.PLACE", shareContent.getPlaceId());
        Utility.putNonEmptyString(bundle, "com.facebook.platform.extra.REF", shareContent.getRef());
        bundle.putBoolean("com.facebook.platform.extra.DATA_FAILURES_FATAL", b);
        final List peopleIds = shareContent.getPeopleIds();
        if (!Utility.isNullOrEmpty((Collection)peopleIds)) {
            bundle.putStringArrayList("com.facebook.platform.extra.FRIENDS", new ArrayList(peopleIds));
        }
        return bundle;
    }
}
