// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.signin;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import java.util.Collections;
import java.util.Comparator;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collection;
import com.google.android.gms.common.internal.Preconditions;
import android.support.annotation.NonNull;
import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;
import android.text.TextUtils;
import android.support.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import android.accounts.Account;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import java.util.HashSet;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.util.DefaultClock;
import java.util.Set;
import com.google.android.gms.common.api.Scope;
import java.util.List;
import android.net.Uri;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.util.Clock;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "GoogleSignInAccountCreator")
public class GoogleSignInAccount extends AbstractSafeParcelable implements ReflectedParcelable
{
    public static final Parcelable$Creator<GoogleSignInAccount> CREATOR;
    @VisibleForTesting
    private static Clock zae;
    @SafeParcelable$Field(getter = "getId", id = 2)
    private String mId;
    @SafeParcelable$VersionField(id = 1)
    private final int versionCode;
    @SafeParcelable$Field(getter = "getIdToken", id = 3)
    private String zaf;
    @SafeParcelable$Field(getter = "getEmail", id = 4)
    private String zag;
    @SafeParcelable$Field(getter = "getDisplayName", id = 5)
    private String zah;
    @SafeParcelable$Field(getter = "getPhotoUrl", id = 6)
    private Uri zai;
    @SafeParcelable$Field(getter = "getServerAuthCode", id = 7)
    private String zaj;
    @SafeParcelable$Field(getter = "getExpirationTimeSecs", id = 8)
    private long zak;
    @SafeParcelable$Field(getter = "getObfuscatedIdentifier", id = 9)
    private String zal;
    @SafeParcelable$Field(id = 10)
    private List<Scope> zam;
    @SafeParcelable$Field(getter = "getGivenName", id = 11)
    private String zan;
    @SafeParcelable$Field(getter = "getFamilyName", id = 12)
    private String zao;
    private Set<Scope> zap;
    
    static {
        CREATOR = (Parcelable$Creator)new zab();
        GoogleSignInAccount.zae = DefaultClock.getInstance();
    }
    
    @SafeParcelable$Constructor
    GoogleSignInAccount(@SafeParcelable$Param(id = 1) final int versionCode, @SafeParcelable$Param(id = 2) final String mId, @SafeParcelable$Param(id = 3) final String zaf, @SafeParcelable$Param(id = 4) final String zag, @SafeParcelable$Param(id = 5) final String zah, @SafeParcelable$Param(id = 6) final Uri zai, @SafeParcelable$Param(id = 7) final String zaj, @SafeParcelable$Param(id = 8) final long zak, @SafeParcelable$Param(id = 9) final String zal, @SafeParcelable$Param(id = 10) final List<Scope> zam, @SafeParcelable$Param(id = 11) final String zan, @SafeParcelable$Param(id = 12) final String zao) {
        this.zap = new HashSet<Scope>();
        this.versionCode = versionCode;
        this.mId = mId;
        this.zaf = zaf;
        this.zag = zag;
        this.zah = zah;
        this.zai = zai;
        this.zaj = zaj;
        this.zak = zak;
        this.zal = zal;
        this.zam = zam;
        this.zan = zan;
        this.zao = zao;
    }
    
    @KeepForSdk
    public static GoogleSignInAccount createDefault() {
        final Account account = new Account("<<default account>>", "com.google");
        return zaa(null, null, account.name, null, null, null, null, 0L, account.name, new HashSet<Scope>());
    }
    
    @Nullable
    public static GoogleSignInAccount zaa(@Nullable String optString) throws JSONException {
        if (TextUtils.isEmpty((CharSequence)optString)) {
            return null;
        }
        final JSONObject jsonObject = new JSONObject(optString);
        optString = jsonObject.optString("photoUrl", (String)null);
        Uri parse;
        if (!TextUtils.isEmpty((CharSequence)optString)) {
            parse = Uri.parse(optString);
        }
        else {
            parse = null;
        }
        final long long1 = Long.parseLong(jsonObject.getString("expirationTime"));
        final HashSet<Scope> set = new HashSet<Scope>();
        final JSONArray jsonArray = jsonObject.getJSONArray("grantedScopes");
        for (int length = jsonArray.length(), i = 0; i < length; ++i) {
            set.add(new Scope(jsonArray.getString(i)));
        }
        final GoogleSignInAccount zaa = zaa(jsonObject.optString("id"), jsonObject.optString("tokenId", (String)null), jsonObject.optString("email", (String)null), jsonObject.optString("displayName", (String)null), jsonObject.optString("givenName", (String)null), jsonObject.optString("familyName", (String)null), parse, long1, jsonObject.getString("obfuscatedIdentifier"), set);
        zaa.zaj = jsonObject.optString("serverAuthCode", (String)null);
        return zaa;
    }
    
    private static GoogleSignInAccount zaa(@Nullable final String s, @Nullable final String s2, @Nullable final String s3, @Nullable final String s4, @Nullable final String s5, @Nullable final String s6, @Nullable final Uri uri, @Nullable final Long n, @NonNull final String s7, @NonNull final Set<Scope> set) {
        Long value = n;
        if (n == null) {
            value = GoogleSignInAccount.zae.currentTimeMillis() / 1000L;
        }
        return new GoogleSignInAccount(3, s, s2, s3, s4, uri, null, value, Preconditions.checkNotEmpty(s7), new ArrayList<Scope>((Collection<? extends Scope>)Preconditions.checkNotNull((Object)set)), s5, s6);
    }
    
    private final JSONObject zad() {
        final JSONObject jsonObject = new JSONObject();
        try {
            if (this.getId() != null) {
                jsonObject.put("id", (Object)this.getId());
            }
            if (this.getIdToken() != null) {
                jsonObject.put("tokenId", (Object)this.getIdToken());
            }
            if (this.getEmail() != null) {
                jsonObject.put("email", (Object)this.getEmail());
            }
            if (this.getDisplayName() != null) {
                jsonObject.put("displayName", (Object)this.getDisplayName());
            }
            if (this.getGivenName() != null) {
                jsonObject.put("givenName", (Object)this.getGivenName());
            }
            if (this.getFamilyName() != null) {
                jsonObject.put("familyName", (Object)this.getFamilyName());
            }
            if (this.getPhotoUrl() != null) {
                jsonObject.put("photoUrl", (Object)this.getPhotoUrl().toString());
            }
            if (this.getServerAuthCode() != null) {
                jsonObject.put("serverAuthCode", (Object)this.getServerAuthCode());
            }
            jsonObject.put("expirationTime", this.zak);
            jsonObject.put("obfuscatedIdentifier", (Object)this.zal);
            final JSONArray jsonArray = new JSONArray();
            final Scope[] array = this.zam.toArray(new Scope[this.zam.size()]);
            Arrays.sort(array, zaa.zaq);
            for (int length = array.length, i = 0; i < length; ++i) {
                jsonArray.put((Object)array[i].getScopeUri());
            }
            jsonObject.put("grantedScopes", (Object)jsonArray);
            return jsonObject;
        }
        catch (JSONException ex) {
            throw new RuntimeException((Throwable)ex);
        }
    }
    
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof GoogleSignInAccount)) {
                return false;
            }
            final GoogleSignInAccount googleSignInAccount = (GoogleSignInAccount)o;
            if (!googleSignInAccount.zal.equals(this.zal) || !googleSignInAccount.getRequestedScopes().equals(this.getRequestedScopes())) {
                return false;
            }
        }
        return true;
    }
    
    @Nullable
    public Account getAccount() {
        if (this.zag == null) {
            return null;
        }
        return new Account(this.zag, "com.google");
    }
    
    @Nullable
    public String getDisplayName() {
        return this.zah;
    }
    
    @Nullable
    public String getEmail() {
        return this.zag;
    }
    
    @Nullable
    public String getFamilyName() {
        return this.zao;
    }
    
    @Nullable
    public String getGivenName() {
        return this.zan;
    }
    
    @NonNull
    public Set<Scope> getGrantedScopes() {
        return new HashSet<Scope>(this.zam);
    }
    
    @Nullable
    public String getId() {
        return this.mId;
    }
    
    @Nullable
    public String getIdToken() {
        return this.zaf;
    }
    
    @Nullable
    public Uri getPhotoUrl() {
        return this.zai;
    }
    
    @NonNull
    @KeepForSdk
    public Set<Scope> getRequestedScopes() {
        final HashSet<Object> set = (HashSet<Object>)new HashSet<Scope>(this.zam);
        set.addAll(this.zap);
        return (Set<Scope>)set;
    }
    
    @Nullable
    public String getServerAuthCode() {
        return this.zaj;
    }
    
    public int hashCode() {
        return (this.zal.hashCode() + 527) * 31 + this.getRequestedScopes().hashCode();
    }
    
    @KeepForSdk
    public boolean isExpired() {
        return GoogleSignInAccount.zae.currentTimeMillis() / 1000L >= this.zak - 300L;
    }
    
    @KeepForSdk
    public GoogleSignInAccount requestExtraScopes(final Scope... array) {
        if (array != null) {
            Collections.addAll(this.zap, array);
        }
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeString(parcel, 2, this.getId(), false);
        SafeParcelWriter.writeString(parcel, 3, this.getIdToken(), false);
        SafeParcelWriter.writeString(parcel, 4, this.getEmail(), false);
        SafeParcelWriter.writeString(parcel, 5, this.getDisplayName(), false);
        SafeParcelWriter.writeParcelable(parcel, 6, (Parcelable)this.getPhotoUrl(), n, false);
        SafeParcelWriter.writeString(parcel, 7, this.getServerAuthCode(), false);
        SafeParcelWriter.writeLong(parcel, 8, this.zak);
        SafeParcelWriter.writeString(parcel, 9, this.zal, false);
        SafeParcelWriter.writeTypedList(parcel, 10, (List)this.zam, false);
        SafeParcelWriter.writeString(parcel, 11, this.getGivenName(), false);
        SafeParcelWriter.writeString(parcel, 12, this.getFamilyName(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    @NonNull
    public final String zab() {
        return this.zal;
    }
    
    public final String zac() {
        final JSONObject zad = this.zad();
        zad.remove("serverAuthCode");
        return zad.toString();
    }
}
