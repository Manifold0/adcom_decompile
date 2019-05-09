package com.facebook;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.internal.ImageRequest;
import com.facebook.internal.Utility;
import com.facebook.internal.Utility.GraphMeRequestWithCacheCallback;
import com.facebook.internal.Validate;
import com.ironsource.mediationsdk.logger.IronSourceError;
import org.json.JSONException;
import org.json.JSONObject;

public final class Profile implements Parcelable {
    public static final Creator<Profile> CREATOR = new C11672();
    private static final String FIRST_NAME_KEY = "first_name";
    private static final String ID_KEY = "id";
    private static final String LAST_NAME_KEY = "last_name";
    private static final String LINK_URI_KEY = "link_uri";
    private static final String MIDDLE_NAME_KEY = "middle_name";
    private static final String NAME_KEY = "name";
    private static final String TAG = Profile.class.getSimpleName();
    @Nullable
    private final String firstName;
    @Nullable
    private final String id;
    @Nullable
    private final String lastName;
    @Nullable
    private final Uri linkUri;
    @Nullable
    private final String middleName;
    @Nullable
    private final String name;

    /* renamed from: com.facebook.Profile$1 */
    static class C11661 implements GraphMeRequestWithCacheCallback {
        C11661() {
        }

        public void onSuccess(JSONObject userInfo) {
            String id = userInfo.optString("id");
            if (id != null) {
                String link = userInfo.optString("link");
                Profile.setCurrentProfile(new Profile(id, userInfo.optString(Profile.FIRST_NAME_KEY), userInfo.optString(Profile.MIDDLE_NAME_KEY), userInfo.optString(Profile.LAST_NAME_KEY), userInfo.optString("name"), link != null ? Uri.parse(link) : null));
            }
        }

        public void onFailure(FacebookException error) {
            Log.e(Profile.TAG, "Got unexpected exception: " + error);
        }
    }

    /* renamed from: com.facebook.Profile$2 */
    static class C11672 implements Creator<Profile> {
        C11672() {
        }

        public Profile createFromParcel(Parcel source) {
            return new Profile(source);
        }

        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    }

    public static Profile getCurrentProfile() {
        return ProfileManager.getInstance().getCurrentProfile();
    }

    public static void setCurrentProfile(@Nullable Profile profile) {
        ProfileManager.getInstance().setCurrentProfile(profile);
    }

    public static void fetchProfileForCurrentAccessToken() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (AccessToken.isCurrentAccessTokenActive()) {
            Utility.getGraphMeRequestWithCacheAsync(accessToken.getToken(), new C11661());
        } else {
            setCurrentProfile(null);
        }
    }

    public Profile(String id, @Nullable String firstName, @Nullable String middleName, @Nullable String lastName, @Nullable String name, @Nullable Uri linkUri) {
        Validate.notNullOrEmpty(id, "id");
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.name = name;
        this.linkUri = linkUri;
    }

    public Uri getProfilePictureUri(int width, int height) {
        return ImageRequest.getProfilePictureUri(this.id, width, height);
    }

    public String getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getName() {
        return this.name;
    }

    public Uri getLinkUri() {
        return this.linkUri;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Profile)) {
            return false;
        }
        Profile o = (Profile) other;
        if (this.id.equals(o.id) && this.firstName == null) {
            if (o.firstName != null) {
                return false;
            }
            return true;
        } else if (this.firstName.equals(o.firstName) && this.middleName == null) {
            if (o.middleName != null) {
                return false;
            }
            return true;
        } else if (this.middleName.equals(o.middleName) && this.lastName == null) {
            if (o.lastName != null) {
                return false;
            }
            return true;
        } else if (this.lastName.equals(o.lastName) && this.name == null) {
            if (o.name != null) {
                return false;
            }
            return true;
        } else if (!this.name.equals(o.name) || this.linkUri != null) {
            return this.linkUri.equals(o.linkUri);
        } else {
            if (o.linkUri != null) {
                return false;
            }
            return true;
        }
    }

    public int hashCode() {
        int result = this.id.hashCode() + IronSourceError.ERROR_NON_EXISTENT_INSTANCE;
        if (this.firstName != null) {
            result = (result * 31) + this.firstName.hashCode();
        }
        if (this.middleName != null) {
            result = (result * 31) + this.middleName.hashCode();
        }
        if (this.lastName != null) {
            result = (result * 31) + this.lastName.hashCode();
        }
        if (this.name != null) {
            result = (result * 31) + this.name.hashCode();
        }
        if (this.linkUri != null) {
            return (result * 31) + this.linkUri.hashCode();
        }
        return result;
    }

    JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", this.id);
            jsonObject.put(FIRST_NAME_KEY, this.firstName);
            jsonObject.put(MIDDLE_NAME_KEY, this.middleName);
            jsonObject.put(LAST_NAME_KEY, this.lastName);
            jsonObject.put("name", this.name);
            if (this.linkUri == null) {
                return jsonObject;
            }
            jsonObject.put(LINK_URI_KEY, this.linkUri.toString());
            return jsonObject;
        } catch (JSONException e) {
            return null;
        }
    }

    Profile(JSONObject jsonObject) {
        Uri uri = null;
        this.id = jsonObject.optString("id", null);
        this.firstName = jsonObject.optString(FIRST_NAME_KEY, null);
        this.middleName = jsonObject.optString(MIDDLE_NAME_KEY, null);
        this.lastName = jsonObject.optString(LAST_NAME_KEY, null);
        this.name = jsonObject.optString("name", null);
        String linkUriString = jsonObject.optString(LINK_URI_KEY, null);
        if (linkUriString != null) {
            uri = Uri.parse(linkUriString);
        }
        this.linkUri = uri;
    }

    private Profile(Parcel source) {
        this.id = source.readString();
        this.firstName = source.readString();
        this.middleName = source.readString();
        this.lastName = source.readString();
        this.name = source.readString();
        String linkUriString = source.readString();
        this.linkUri = linkUriString == null ? null : Uri.parse(linkUriString);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.firstName);
        dest.writeString(this.middleName);
        dest.writeString(this.lastName);
        dest.writeString(this.name);
        dest.writeString(this.linkUri == null ? null : this.linkUri.toString());
    }
}
