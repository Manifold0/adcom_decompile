package com.facebook;

import android.net.Uri;
import android.os.Bundle;
import com.facebook.share.internal.OpenGraphJSONUtility;
import com.facebook.share.internal.OpenGraphJSONUtility.PhotoJSONProcessor;
import com.facebook.share.model.ShareOpenGraphObject;
import com.facebook.share.model.SharePhoto;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class ShareGraphRequest {

    /* renamed from: com.facebook.ShareGraphRequest$1 */
    static class C03001 implements PhotoJSONProcessor {
        C03001() {
        }

        public JSONObject toJSONObject(SharePhoto photo) {
            Uri photoUri = photo.getImageUrl();
            JSONObject photoJSONObject = new JSONObject();
            try {
                photoJSONObject.put("url", photoUri.toString());
                return photoJSONObject;
            } catch (Exception e) {
                throw new FacebookException("Unable to attach images", e);
            }
        }
    }

    public static GraphRequest createOpenGraphObject(ShareOpenGraphObject openGraphObject) throws FacebookException {
        String type = openGraphObject.getString("type");
        if (type == null) {
            type = openGraphObject.getString("og:type");
        }
        if (type == null) {
            throw new FacebookException("Open graph object type cannot be null");
        }
        try {
            JSONObject stagedObject = (JSONObject) OpenGraphJSONUtility.toJSONValue(openGraphObject, new C03001());
            String ogType = type;
            Bundle parameters = new Bundle();
            parameters.putString("object", stagedObject.toString());
            return new GraphRequest(AccessToken.getCurrentAccessToken(), String.format(Locale.ROOT, "%s/%s", new Object[]{"me", "objects/" + ogType}), parameters, HttpMethod.POST);
        } catch (JSONException e) {
            throw new FacebookException(e.getMessage());
        }
    }
}
