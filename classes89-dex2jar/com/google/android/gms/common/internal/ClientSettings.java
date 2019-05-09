// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import android.support.v4.util.ArraySet;
import javax.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient;
import android.content.Context;
import java.util.Iterator;
import java.util.Collection;
import java.util.HashSet;
import java.util.Collections;
import android.accounts.Account;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.common.api.Api;
import java.util.Map;
import android.view.View;
import com.google.android.gms.common.api.Scope;
import java.util.Set;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
@VisibleForTesting
public final class ClientSettings
{
    public static final String KEY_CLIENT_SESSION_ID = "com.google.android.gms.common.internal.ClientSettings.sessionId";
    private final Set<Scope> zabr;
    private final int zabt;
    private final View zabu;
    private final String zabv;
    private final String zabw;
    private final boolean zaby;
    private final Set<Scope> zaob;
    private final Map<Api<?>, OptionalApiSettings> zaoc;
    private final SignInOptions zaod;
    private Integer zaoe;
    private final Account zax;
    
    @KeepForSdk
    public ClientSettings(final Account account, final Set<Scope> set, final Map<Api<?>, OptionalApiSettings> map, final int n, final View view, final String s, final String s2, final SignInOptions signInOptions) {
        this(account, set, map, n, view, s, s2, signInOptions, false);
    }
    
    public ClientSettings(final Account zax, final Set<Scope> set, final Map<Api<?>, OptionalApiSettings> map, final int zabt, final View zabu, final String zabv, final String zabw, final SignInOptions zaod, final boolean zaby) {
        this.zax = zax;
        Set<Scope> zabr;
        if (set == null) {
            zabr = (Set<Scope>)Collections.EMPTY_SET;
        }
        else {
            zabr = Collections.unmodifiableSet((Set<? extends Scope>)set);
        }
        this.zabr = zabr;
        Map<Api<?>, OptionalApiSettings> empty_MAP = map;
        if (map == null) {
            empty_MAP = (Map<Api<?>, OptionalApiSettings>)Collections.EMPTY_MAP;
        }
        this.zaoc = empty_MAP;
        this.zabu = zabu;
        this.zabt = zabt;
        this.zabv = zabv;
        this.zabw = zabw;
        this.zaod = zaod;
        this.zaby = zaby;
        final HashSet<Scope> set2 = new HashSet<Scope>(this.zabr);
        final Iterator<OptionalApiSettings> iterator = this.zaoc.values().iterator();
        while (iterator.hasNext()) {
            set2.addAll((Collection<?>)iterator.next().mScopes);
        }
        this.zaob = (Set<Scope>)Collections.unmodifiableSet((Set<?>)set2);
    }
    
    @KeepForSdk
    public static ClientSettings createDefault(final Context context) {
        return new GoogleApiClient.Builder(context).buildClientSettings();
    }
    
    @Nullable
    @KeepForSdk
    public final Account getAccount() {
        return this.zax;
    }
    
    @Deprecated
    @Nullable
    @KeepForSdk
    public final String getAccountName() {
        if (this.zax != null) {
            return this.zax.name;
        }
        return null;
    }
    
    @KeepForSdk
    public final Account getAccountOrDefault() {
        if (this.zax != null) {
            return this.zax;
        }
        return new Account("<<default account>>", "com.google");
    }
    
    @KeepForSdk
    public final Set<Scope> getAllRequestedScopes() {
        return this.zaob;
    }
    
    @KeepForSdk
    public final Set<Scope> getApplicableScopes(final Api<?> api) {
        final OptionalApiSettings optionalApiSettings = this.zaoc.get(api);
        if (optionalApiSettings == null || optionalApiSettings.mScopes.isEmpty()) {
            return this.zabr;
        }
        final HashSet<Object> set = (HashSet<Object>)new HashSet<Scope>(this.zabr);
        set.addAll(optionalApiSettings.mScopes);
        return (Set<Scope>)set;
    }
    
    @Nullable
    public final Integer getClientSessionId() {
        return this.zaoe;
    }
    
    @KeepForSdk
    public final int getGravityForPopups() {
        return this.zabt;
    }
    
    public final Map<Api<?>, OptionalApiSettings> getOptionalApiSettings() {
        return this.zaoc;
    }
    
    @Nullable
    public final String getRealClientClassName() {
        return this.zabw;
    }
    
    @Nullable
    @KeepForSdk
    public final String getRealClientPackageName() {
        return this.zabv;
    }
    
    @KeepForSdk
    public final Set<Scope> getRequiredScopes() {
        return this.zabr;
    }
    
    @Nullable
    public final SignInOptions getSignInOptions() {
        return this.zaod;
    }
    
    @Nullable
    @KeepForSdk
    public final View getViewForPopups() {
        return this.zabu;
    }
    
    public final boolean isSignInClientDisconnectFixEnabled() {
        return this.zaby;
    }
    
    public final void setClientSessionId(final Integer zaoe) {
        this.zaoe = zaoe;
    }
    
    @KeepForSdk
    public static final class Builder
    {
        private int zabt;
        private View zabu;
        private String zabv;
        private String zabw;
        private boolean zaby;
        private Map<Api<?>, OptionalApiSettings> zaoc;
        private SignInOptions zaod;
        private ArraySet<Scope> zaof;
        private Account zax;
        
        public Builder() {
            this.zabt = 0;
            this.zaod = SignInOptions.DEFAULT;
        }
        
        public final Builder addAllRequiredScopes(final Collection<Scope> collection) {
            if (this.zaof == null) {
                this.zaof = (ArraySet<Scope>)new ArraySet();
            }
            this.zaof.addAll((Collection)collection);
            return this;
        }
        
        public final Builder addRequiredScope(final Scope scope) {
            if (this.zaof == null) {
                this.zaof = (ArraySet<Scope>)new ArraySet();
            }
            this.zaof.add((Object)scope);
            return this;
        }
        
        @KeepForSdk
        public final ClientSettings build() {
            return new ClientSettings(this.zax, (Set<Scope>)this.zaof, this.zaoc, this.zabt, this.zabu, this.zabv, this.zabw, this.zaod, this.zaby);
        }
        
        public final Builder enableSignInClientDisconnectFix() {
            this.zaby = true;
            return this;
        }
        
        public final Builder setAccount(final Account zax) {
            this.zax = zax;
            return this;
        }
        
        public final Builder setGravityForPopups(final int zabt) {
            this.zabt = zabt;
            return this;
        }
        
        public final Builder setOptionalApiSettingsMap(final Map<Api<?>, OptionalApiSettings> zaoc) {
            this.zaoc = zaoc;
            return this;
        }
        
        public final Builder setRealClientClassName(final String zabw) {
            this.zabw = zabw;
            return this;
        }
        
        @KeepForSdk
        public final Builder setRealClientPackageName(final String zabv) {
            this.zabv = zabv;
            return this;
        }
        
        public final Builder setSignInOptions(final SignInOptions zaod) {
            this.zaod = zaod;
            return this;
        }
        
        public final Builder setViewForPopups(final View zabu) {
            this.zabu = zabu;
            return this;
        }
    }
    
    public static final class OptionalApiSettings
    {
        public final Set<Scope> mScopes;
        
        public OptionalApiSettings(final Set<Scope> set) {
            Preconditions.checkNotNull((Object)set);
            this.mScopes = Collections.unmodifiableSet((Set<? extends Scope>)set);
        }
    }
}
