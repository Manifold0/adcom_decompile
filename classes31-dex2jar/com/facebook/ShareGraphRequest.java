// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook;

import org.json.JSONException;
import java.util.Locale;
import android.os.Bundle;
import android.net.Uri;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.internal.OpenGraphJSONUtility;
import org.json.JSONObject;
import com.facebook.share.model.ShareOpenGraphObject;

public class ShareGraphRequest
{
    public static GraphRequest createOpenGraphObject(final ShareOpenGraphObject shareOpenGraphObject) throws FacebookException {
        String s;
        if ((s = shareOpenGraphObject.getString("type")) == null) {
            s = shareOpenGraphObject.getString("og:type");
        }
        if (s == null) {
            throw new FacebookException("Open graph object type cannot be null");
        }
        try {
            final JSONObject jsonObject = (JSONObject)OpenGraphJSONUtility.toJSONValue(shareOpenGraphObject, (OpenGraphJSONUtility.PhotoJSONProcessor)new OpenGraphJSONUtility.PhotoJSONProcessor() {
                @Override
                public JSONObject toJSONObject(final SharePhoto sharePhoto) {
                    final Uri imageUrl = sharePhoto.getImageUrl();
                    final JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("url", (Object)imageUrl.toString());
                        return jsonObject;
                    }
                    catch (Exception ex) {
                        throw new FacebookException("Unable to attach images", (Throwable)ex);
                    }
                }
            });
            final Bundle bundle = new Bundle();
            bundle.putString("object", jsonObject.toString());
            return new GraphRequest(AccessToken.getCurrentAccessToken(), String.format(Locale.ROOT, "%s/%s", "me", "objects/" + s), bundle, HttpMethod.POST);
        }
        catch (JSONException ex) {
            throw new FacebookException(ex.getMessage());
        }
    }
}
