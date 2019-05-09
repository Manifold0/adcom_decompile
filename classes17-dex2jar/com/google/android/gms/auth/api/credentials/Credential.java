// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.credentials;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import java.util.Collections;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import java.util.List;
import android.net.Uri;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import javax.annotation.Nonnull;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "CredentialCreator")
@SafeParcelable$Reserved({ 1000 })
public class Credential extends AbstractSafeParcelable implements ReflectedParcelable
{
    public static final Parcelable$Creator<Credential> CREATOR;
    public static final String EXTRA_KEY = "com.google.android.gms.credentials.Credential";
    @Nonnull
    @SafeParcelable$Field(getter = "getId", id = 1)
    private final String mId;
    @Nullable
    @SafeParcelable$Field(getter = "getName", id = 2)
    private final String mName;
    @Nullable
    @SafeParcelable$Field(getter = "getProfilePictureUri", id = 3)
    private final Uri zzo;
    @Nonnull
    @SafeParcelable$Field(getter = "getIdTokens", id = 4)
    private final List<IdToken> zzp;
    @Nullable
    @SafeParcelable$Field(getter = "getPassword", id = 5)
    private final String zzq;
    @Nullable
    @SafeParcelable$Field(getter = "getAccountType", id = 6)
    private final String zzr;
    @Nullable
    @SafeParcelable$Field(getter = "getGivenName", id = 9)
    private final String zzs;
    @Nullable
    @SafeParcelable$Field(getter = "getFamilyName", id = 10)
    private final String zzt;
    
    static {
        CREATOR = (Parcelable$Creator)new zzc();
    }
    
    @SafeParcelable$Constructor
    Credential(@SafeParcelable$Param(id = 1) String mName, @SafeParcelable$Param(id = 2) final String s, @SafeParcelable$Param(id = 3) final Uri zzo, @SafeParcelable$Param(id = 4) final List<IdToken> list, @SafeParcelable$Param(id = 5) final String zzq, @SafeParcelable$Param(id = 6) final String zzr, @SafeParcelable$Param(id = 9) final String zzs, @SafeParcelable$Param(id = 10) final String zzt) {
        final String trim = ((String)Preconditions.checkNotNull((Object)mName, (Object)"credential identifier cannot be null")).trim();
        Preconditions.checkNotEmpty(trim, (Object)"credential identifier cannot be empty");
        if (zzq != null && TextUtils.isEmpty((CharSequence)zzq)) {
            throw new IllegalArgumentException("Password must not be empty if set");
        }
        Label_0162: {
            if (zzr != null) {
                while (true) {
                    Label_0156: {
                        if (TextUtils.isEmpty((CharSequence)zzr)) {
                            break Label_0156;
                        }
                        final Uri parse = Uri.parse(zzr);
                        boolean b;
                        if (!parse.isAbsolute() || !parse.isHierarchical() || TextUtils.isEmpty((CharSequence)parse.getScheme()) || TextUtils.isEmpty((CharSequence)parse.getAuthority())) {
                            b = false;
                        }
                        else {
                            if (!"http".equalsIgnoreCase(parse.getScheme()) && !"https".equalsIgnoreCase(parse.getScheme())) {
                                break Label_0156;
                            }
                            b = true;
                        }
                        if (!(boolean)b) {
                            throw new IllegalArgumentException("Account type must be a valid Http/Https URI");
                        }
                        break Label_0162;
                    }
                    boolean b = false;
                    continue;
                }
            }
        }
        if (!TextUtils.isEmpty((CharSequence)zzr) && !TextUtils.isEmpty((CharSequence)zzq)) {
            throw new IllegalArgumentException("Password and AccountType are mutually exclusive");
        }
        if ((mName = s) != null) {
            mName = s;
            if (TextUtils.isEmpty((CharSequence)s.trim())) {
                mName = null;
            }
        }
        this.mName = mName;
        this.zzo = zzo;
        List<IdToken> zzp;
        if (list == null) {
            zzp = Collections.emptyList();
        }
        else {
            zzp = Collections.unmodifiableList((List<? extends IdToken>)list);
        }
        this.zzp = zzp;
        this.mId = trim;
        this.zzq = zzq;
        this.zzr = zzr;
        this.zzs = zzs;
        this.zzt = zzt;
    }
    
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof Credential)) {
                return false;
            }
            final Credential credential = (Credential)o;
            if (!TextUtils.equals((CharSequence)this.mId, (CharSequence)credential.mId) || !TextUtils.equals((CharSequence)this.mName, (CharSequence)credential.mName) || !Objects.equal((Object)this.zzo, (Object)credential.zzo) || !TextUtils.equals((CharSequence)this.zzq, (CharSequence)credential.zzq) || !TextUtils.equals((CharSequence)this.zzr, (CharSequence)credential.zzr)) {
                return false;
            }
        }
        return true;
    }
    
    @Nullable
    public String getAccountType() {
        return this.zzr;
    }
    
    @Nullable
    public String getFamilyName() {
        return this.zzt;
    }
    
    @Nullable
    public String getGivenName() {
        return this.zzs;
    }
    
    @Nonnull
    public String getId() {
        return this.mId;
    }
    
    @Nonnull
    public List<IdToken> getIdTokens() {
        return this.zzp;
    }
    
    @Nullable
    public String getName() {
        return this.mName;
    }
    
    @Nullable
    public String getPassword() {
        return this.zzq;
    }
    
    @Nullable
    public Uri getProfilePictureUri() {
        return this.zzo;
    }
    
    public int hashCode() {
        return Objects.hashCode(new Object[] { this.mId, this.mName, this.zzo, this.zzq, this.zzr });
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.getId(), false);
        SafeParcelWriter.writeString(parcel, 2, this.getName(), false);
        SafeParcelWriter.writeParcelable(parcel, 3, (Parcelable)this.getProfilePictureUri(), n, false);
        SafeParcelWriter.writeTypedList(parcel, 4, (List)this.getIdTokens(), false);
        SafeParcelWriter.writeString(parcel, 5, this.getPassword(), false);
        SafeParcelWriter.writeString(parcel, 6, this.getAccountType(), false);
        SafeParcelWriter.writeString(parcel, 9, this.getGivenName(), false);
        SafeParcelWriter.writeString(parcel, 10, this.getFamilyName(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public static class Builder
    {
        private final String mId;
        private String mName;
        private Uri zzo;
        private List<IdToken> zzp;
        private String zzq;
        private String zzr;
        private String zzs;
        private String zzt;
        
        public Builder(final Credential credential) {
            this.mId = credential.mId;
            this.mName = credential.mName;
            this.zzo = credential.zzo;
            this.zzp = credential.zzp;
            this.zzq = credential.zzq;
            this.zzr = credential.zzr;
            this.zzs = credential.zzs;
            this.zzt = credential.zzt;
        }
        
        public Builder(final String mId) {
            this.mId = mId;
        }
        
        public Credential build() {
            return new Credential(this.mId, this.mName, this.zzo, this.zzp, this.zzq, this.zzr, this.zzs, this.zzt);
        }
        
        public Builder setAccountType(final String zzr) {
            this.zzr = zzr;
            return this;
        }
        
        public Builder setName(final String mName) {
            this.mName = mName;
            return this;
        }
        
        public Builder setPassword(final String zzq) {
            this.zzq = zzq;
            return this;
        }
        
        public Builder setProfilePictureUri(final Uri zzo) {
            this.zzo = zzo;
            return this;
        }
    }
}
