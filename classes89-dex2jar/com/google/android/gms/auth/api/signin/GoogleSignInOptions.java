// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.signin;

import java.util.Arrays;
import com.google.android.gms.common.internal.Preconditions;
import android.support.annotation.NonNull;
import java.util.Set;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.auth.api.signin.internal.HashAccumulator;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.Collections;
import org.json.JSONException;
import org.json.JSONArray;
import java.util.HashSet;
import org.json.JSONObject;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.HashMap;
import android.support.annotation.Nullable;
import java.util.Collection;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.accounts.Account;
import java.util.Map;
import com.google.android.gms.auth.api.signin.internal.GoogleSignInOptionsExtensionParcelable;
import java.util.ArrayList;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.api.Scope;
import java.util.Comparator;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "GoogleSignInOptionsCreator")
public class GoogleSignInOptions extends AbstractSafeParcelable implements Optional, ReflectedParcelable
{
    public static final Parcelable$Creator<GoogleSignInOptions> CREATOR;
    public static final GoogleSignInOptions DEFAULT_GAMES_SIGN_IN;
    public static final GoogleSignInOptions DEFAULT_SIGN_IN;
    private static Comparator<Scope> zaaf;
    @VisibleForTesting
    public static final Scope zar;
    @VisibleForTesting
    public static final Scope zas;
    @VisibleForTesting
    public static final Scope zat;
    @VisibleForTesting
    public static final Scope zau;
    @VisibleForTesting
    public static final Scope zav;
    @SafeParcelable$VersionField(id = 1)
    private final int versionCode;
    @SafeParcelable$Field(getter = "isForceCodeForRefreshToken", id = 6)
    private final boolean zaaa;
    @SafeParcelable$Field(getter = "getServerClientId", id = 7)
    private String zaab;
    @SafeParcelable$Field(getter = "getHostedDomain", id = 8)
    private String zaac;
    @SafeParcelable$Field(getter = "getExtensions", id = 9)
    private ArrayList<GoogleSignInOptionsExtensionParcelable> zaad;
    private Map<Integer, GoogleSignInOptionsExtensionParcelable> zaae;
    @SafeParcelable$Field(getter = "getScopes", id = 2)
    private final ArrayList<Scope> zaw;
    @SafeParcelable$Field(getter = "getAccount", id = 3)
    private Account zax;
    @SafeParcelable$Field(getter = "isIdTokenRequested", id = 4)
    private boolean zay;
    @SafeParcelable$Field(getter = "isServerAuthCodeRequested", id = 5)
    private final boolean zaz;
    
    static {
        zar = new Scope("profile");
        zas = new Scope("email");
        zat = new Scope("openid");
        zau = new Scope("https://www.googleapis.com/auth/games_lite");
        zav = new Scope("https://www.googleapis.com/auth/games");
        DEFAULT_SIGN_IN = new Builder().requestId().requestProfile().build();
        DEFAULT_GAMES_SIGN_IN = new Builder().requestScopes(GoogleSignInOptions.zau, new Scope[0]).build();
        CREATOR = (Parcelable$Creator)new zad();
        GoogleSignInOptions.zaaf = new zac();
    }
    
    @SafeParcelable$Constructor
    GoogleSignInOptions(@SafeParcelable$Param(id = 1) final int n, @SafeParcelable$Param(id = 2) final ArrayList<Scope> list, @SafeParcelable$Param(id = 3) final Account account, @SafeParcelable$Param(id = 4) final boolean b, @SafeParcelable$Param(id = 5) final boolean b2, @SafeParcelable$Param(id = 6) final boolean b3, @SafeParcelable$Param(id = 7) final String s, @SafeParcelable$Param(id = 8) final String s2, @SafeParcelable$Param(id = 9) final ArrayList<GoogleSignInOptionsExtensionParcelable> list2) {
        this(n, list, account, b, b2, b3, s, s2, zaa(list2));
    }
    
    private GoogleSignInOptions(final int versionCode, final ArrayList<Scope> zaw, final Account zax, final boolean zay, final boolean zaz, final boolean zaaa, final String zaab, final String zaac, final Map<Integer, GoogleSignInOptionsExtensionParcelable> zaae) {
        this.versionCode = versionCode;
        this.zaw = zaw;
        this.zax = zax;
        this.zay = zay;
        this.zaz = zaz;
        this.zaaa = zaaa;
        this.zaab = zaab;
        this.zaac = zaac;
        this.zaad = new ArrayList<GoogleSignInOptionsExtensionParcelable>(zaae.values());
        this.zaae = zaae;
    }
    
    private static Map<Integer, GoogleSignInOptionsExtensionParcelable> zaa(@Nullable final List<GoogleSignInOptionsExtensionParcelable> list) {
        final HashMap<Integer, GoogleSignInOptionsExtensionParcelable> hashMap = new HashMap<Integer, GoogleSignInOptionsExtensionParcelable>();
        if (list == null) {
            return hashMap;
        }
        for (final GoogleSignInOptionsExtensionParcelable googleSignInOptionsExtensionParcelable : list) {
            hashMap.put(googleSignInOptionsExtensionParcelable.getType(), googleSignInOptionsExtensionParcelable);
        }
        return hashMap;
    }
    
    @Nullable
    public static GoogleSignInOptions zab(@Nullable String optString) throws JSONException {
        if (TextUtils.isEmpty((CharSequence)optString)) {
            return null;
        }
        final JSONObject jsonObject = new JSONObject(optString);
        final HashSet<Scope> set = new HashSet<Scope>();
        final JSONArray jsonArray = jsonObject.getJSONArray("scopes");
        for (int length = jsonArray.length(), i = 0; i < length; ++i) {
            set.add(new Scope(jsonArray.getString(i)));
        }
        optString = jsonObject.optString("accountName", (String)null);
        Account account;
        if (!TextUtils.isEmpty((CharSequence)optString)) {
            account = new Account(optString, "com.google");
        }
        else {
            account = null;
        }
        return new GoogleSignInOptions(3, new ArrayList<Scope>(set), account, jsonObject.getBoolean("idTokenRequested"), jsonObject.getBoolean("serverAuthRequested"), jsonObject.getBoolean("forceCodeForRefreshToken"), jsonObject.optString("serverClientId", (String)null), jsonObject.optString("hostedDomain", (String)null), new HashMap<Integer, GoogleSignInOptionsExtensionParcelable>());
    }
    
    private final JSONObject zad() {
        final JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray;
        try {
            jsonArray = new JSONArray();
            Collections.sort(this.zaw, GoogleSignInOptions.zaaf);
            final ArrayList<Scope> list = this.zaw;
            final int size = list.size();
            int i = 0;
            while (i < size) {
                final Scope value = list.get(i);
                ++i;
                jsonArray.put((Object)value.getScopeUri());
            }
        }
        catch (JSONException ex) {
            throw new RuntimeException((Throwable)ex);
        }
        jsonObject.put("scopes", (Object)jsonArray);
        if (this.zax != null) {
            jsonObject.put("accountName", (Object)this.zax.name);
        }
        jsonObject.put("idTokenRequested", this.zay);
        jsonObject.put("forceCodeForRefreshToken", this.zaaa);
        jsonObject.put("serverAuthRequested", this.zaz);
        if (!TextUtils.isEmpty((CharSequence)this.zaab)) {
            jsonObject.put("serverClientId", (Object)this.zaab);
        }
        if (!TextUtils.isEmpty((CharSequence)this.zaac)) {
            jsonObject.put("hostedDomain", (Object)this.zaac);
        }
        return jsonObject;
    }
    
    public boolean equals(final Object o) {
        if (o != null) {
            try {
                final GoogleSignInOptions googleSignInOptions = (GoogleSignInOptions)o;
                if (this.zaad.size() <= 0 && googleSignInOptions.zaad.size() <= 0 && this.zaw.size() == googleSignInOptions.getScopes().size() && this.zaw.containsAll(googleSignInOptions.getScopes())) {
                    if (this.zax == null) {
                        if (googleSignInOptions.getAccount() != null) {
                            return false;
                        }
                    }
                    else if (!this.zax.equals((Object)googleSignInOptions.getAccount())) {
                        return false;
                    }
                    if (TextUtils.isEmpty((CharSequence)this.zaab)) {
                        if (!TextUtils.isEmpty((CharSequence)googleSignInOptions.getServerClientId())) {
                            return false;
                        }
                    }
                    else if (!this.zaab.equals(googleSignInOptions.getServerClientId())) {
                        return false;
                    }
                    if (this.zaaa == googleSignInOptions.isForceCodeForRefreshToken() && this.zay == googleSignInOptions.isIdTokenRequested() && this.zaz == googleSignInOptions.isServerAuthCodeRequested()) {
                        return true;
                    }
                }
            }
            catch (ClassCastException ex) {
                return false;
            }
        }
        return false;
    }
    
    @KeepForSdk
    public Account getAccount() {
        return this.zax;
    }
    
    @KeepForSdk
    public ArrayList<GoogleSignInOptionsExtensionParcelable> getExtensions() {
        return this.zaad;
    }
    
    public Scope[] getScopeArray() {
        return this.zaw.toArray(new Scope[this.zaw.size()]);
    }
    
    @KeepForSdk
    public ArrayList<Scope> getScopes() {
        return new ArrayList<Scope>(this.zaw);
    }
    
    @KeepForSdk
    public String getServerClientId() {
        return this.zaab;
    }
    
    public int hashCode() {
        final ArrayList<Comparable> list = new ArrayList<Comparable>();
        final ArrayList<Scope> list2 = this.zaw;
        final int size = list2.size();
        int i = 0;
        while (i < size) {
            final Scope value = list2.get(i);
            ++i;
            list.add(value.getScopeUri());
        }
        Collections.sort(list);
        return new HashAccumulator().addObject(list).addObject(this.zax).addObject(this.zaab).zaa(this.zaaa).zaa(this.zay).zaa(this.zaz).hash();
    }
    
    @KeepForSdk
    public boolean isForceCodeForRefreshToken() {
        return this.zaaa;
    }
    
    @KeepForSdk
    public boolean isIdTokenRequested() {
        return this.zay;
    }
    
    @KeepForSdk
    public boolean isServerAuthCodeRequested() {
        return this.zaz;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeTypedList(parcel, 2, (List)this.getScopes(), false);
        SafeParcelWriter.writeParcelable(parcel, 3, (Parcelable)this.getAccount(), n, false);
        SafeParcelWriter.writeBoolean(parcel, 4, this.isIdTokenRequested());
        SafeParcelWriter.writeBoolean(parcel, 5, this.isServerAuthCodeRequested());
        SafeParcelWriter.writeBoolean(parcel, 6, this.isForceCodeForRefreshToken());
        SafeParcelWriter.writeString(parcel, 7, this.getServerClientId(), false);
        SafeParcelWriter.writeString(parcel, 8, this.zaac, false);
        SafeParcelWriter.writeTypedList(parcel, 9, (List)this.getExtensions(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public final String zae() {
        return this.zad().toString();
    }
    
    public static final class Builder
    {
        private Set<Scope> mScopes;
        private boolean zaaa;
        private String zaab;
        private String zaac;
        private Map<Integer, GoogleSignInOptionsExtensionParcelable> zaag;
        private Account zax;
        private boolean zay;
        private boolean zaz;
        
        public Builder() {
            this.mScopes = new HashSet<Scope>();
            this.zaag = new HashMap<Integer, GoogleSignInOptionsExtensionParcelable>();
        }
        
        public Builder(@NonNull final GoogleSignInOptions googleSignInOptions) {
            this.mScopes = new HashSet<Scope>();
            this.zaag = new HashMap<Integer, GoogleSignInOptionsExtensionParcelable>();
            Preconditions.checkNotNull((Object)googleSignInOptions);
            this.mScopes = new HashSet<Scope>(googleSignInOptions.zaw);
            this.zaz = googleSignInOptions.zaz;
            this.zaaa = googleSignInOptions.zaaa;
            this.zay = googleSignInOptions.zay;
            this.zaab = googleSignInOptions.zaab;
            this.zax = googleSignInOptions.zax;
            this.zaac = googleSignInOptions.zaac;
            this.zaag = zaa(googleSignInOptions.zaad);
        }
        
        private final String zac(final String s) {
            Preconditions.checkNotEmpty(s);
            Preconditions.checkArgument(this.zaab == null || this.zaab.equals(s), (Object)"two different server client ids provided");
            return s;
        }
        
        public final Builder addExtension(final GoogleSignInOptionsExtension googleSignInOptionsExtension) {
            if (this.zaag.containsKey(googleSignInOptionsExtension.getExtensionType())) {
                throw new IllegalStateException("Only one extension per type may be added");
            }
            if (googleSignInOptionsExtension.getImpliedScopes() != null) {
                this.mScopes.addAll(googleSignInOptionsExtension.getImpliedScopes());
            }
            this.zaag.put(googleSignInOptionsExtension.getExtensionType(), new GoogleSignInOptionsExtensionParcelable(googleSignInOptionsExtension));
            return this;
        }
        
        public final GoogleSignInOptions build() {
            if (this.mScopes.contains(GoogleSignInOptions.zav) && this.mScopes.contains(GoogleSignInOptions.zau)) {
                this.mScopes.remove(GoogleSignInOptions.zau);
            }
            if (this.zay && (this.zax == null || !this.mScopes.isEmpty())) {
                this.requestId();
            }
            return new GoogleSignInOptions(3, new ArrayList((Collection<? extends E>)this.mScopes), this.zax, this.zay, this.zaz, this.zaaa, this.zaab, this.zaac, this.zaag, null);
        }
        
        public final Builder requestEmail() {
            this.mScopes.add(GoogleSignInOptions.zas);
            return this;
        }
        
        public final Builder requestId() {
            this.mScopes.add(GoogleSignInOptions.zat);
            return this;
        }
        
        public final Builder requestIdToken(final String s) {
            this.zay = true;
            this.zaab = this.zac(s);
            return this;
        }
        
        public final Builder requestProfile() {
            this.mScopes.add(GoogleSignInOptions.zar);
            return this;
        }
        
        public final Builder requestScopes(final Scope scope, final Scope... array) {
            this.mScopes.add(scope);
            this.mScopes.addAll(Arrays.asList(array));
            return this;
        }
        
        public final Builder requestServerAuthCode(final String s) {
            return this.requestServerAuthCode(s, false);
        }
        
        public final Builder requestServerAuthCode(final String s, final boolean zaaa) {
            this.zaz = true;
            this.zaab = this.zac(s);
            this.zaaa = zaaa;
            return this;
        }
        
        public final Builder setAccountName(final String s) {
            this.zax = new Account(Preconditions.checkNotEmpty(s), "com.google");
            return this;
        }
        
        public final Builder setHostedDomain(final String s) {
            this.zaac = Preconditions.checkNotEmpty(s);
            return this;
        }
    }
}
