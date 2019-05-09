// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api;

import com.google.android.gms.common.api.internal.zace;
import android.os.Handler;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import java.util.Set;
import android.accounts.Account;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import java.util.Collections;
import java.util.Collection;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import android.support.annotation.MainThread;
import com.google.android.gms.common.api.internal.zaae;
import com.google.android.gms.common.api.internal.zabp;
import com.google.android.gms.common.internal.Preconditions;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.app.Activity;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;
import android.os.Looper;
import com.google.android.gms.common.api.internal.zai;
import android.content.Context;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class GoogleApi<O extends Api.ApiOptions>
{
    private final Api<O> mApi;
    private final Context mContext;
    private final int mId;
    private final O zabh;
    private final zai<O> zabi;
    private final Looper zabj;
    private final GoogleApiClient zabk;
    private final StatusExceptionMapper zabl;
    protected final GoogleApiManager zabm;
    
    @MainThread
    @KeepForSdk
    public GoogleApi(@NonNull final Activity activity, final Api<O> mApi, @Nullable final O zabh, final Settings settings) {
        Preconditions.checkNotNull((Object)activity, (Object)"Null activity is not permitted.");
        Preconditions.checkNotNull((Object)mApi, (Object)"Api must not be null.");
        Preconditions.checkNotNull((Object)settings, (Object)"Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
        this.mContext = activity.getApplicationContext();
        this.mApi = mApi;
        this.zabh = zabh;
        this.zabj = settings.zabo;
        this.zabi = zai.zaa(this.mApi, this.zabh);
        this.zabk = new zabp<Object>(this);
        this.zabm = GoogleApiManager.zab(this.mContext);
        this.mId = this.zabm.zabd();
        this.zabl = settings.zabn;
        if (!(activity instanceof GoogleApiActivity)) {
            zaae.zaa(activity, this.zabm, this.zabi);
        }
        this.zabm.zaa(this);
    }
    
    @Deprecated
    @KeepForSdk
    public GoogleApi(@NonNull final Activity activity, final Api<O> api, @Nullable final O o, final StatusExceptionMapper mapper) {
        this(activity, (Api<Api.ApiOptions>)api, o, new Builder().setMapper(mapper).setLooper(activity.getMainLooper()).build());
    }
    
    @KeepForSdk
    protected GoogleApi(@NonNull final Context context, final Api<O> mApi, final Looper zabj) {
        Preconditions.checkNotNull((Object)context, (Object)"Null context is not permitted.");
        Preconditions.checkNotNull((Object)mApi, (Object)"Api must not be null.");
        Preconditions.checkNotNull((Object)zabj, (Object)"Looper must not be null.");
        this.mContext = context.getApplicationContext();
        this.mApi = mApi;
        this.zabh = null;
        this.zabj = zabj;
        this.zabi = zai.zaa(mApi);
        this.zabk = new zabp<Object>(this);
        this.zabm = GoogleApiManager.zab(this.mContext);
        this.mId = this.zabm.zabd();
        this.zabl = (StatusExceptionMapper)new ApiExceptionMapper();
    }
    
    @Deprecated
    @KeepForSdk
    public GoogleApi(@NonNull final Context context, final Api<O> api, @Nullable final O o, final Looper looper, final StatusExceptionMapper mapper) {
        this(context, (Api<Api.ApiOptions>)api, o, new Builder().setLooper(looper).setMapper(mapper).build());
    }
    
    @KeepForSdk
    public GoogleApi(@NonNull final Context context, final Api<O> mApi, @Nullable final O zabh, final Settings settings) {
        Preconditions.checkNotNull((Object)context, (Object)"Null context is not permitted.");
        Preconditions.checkNotNull((Object)mApi, (Object)"Api must not be null.");
        Preconditions.checkNotNull((Object)settings, (Object)"Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
        this.mContext = context.getApplicationContext();
        this.mApi = mApi;
        this.zabh = zabh;
        this.zabj = settings.zabo;
        this.zabi = zai.zaa(this.mApi, this.zabh);
        this.zabk = new zabp<Object>(this);
        this.zabm = GoogleApiManager.zab(this.mContext);
        this.mId = this.zabm.zabd();
        this.zabl = settings.zabn;
        this.zabm.zaa(this);
    }
    
    @Deprecated
    @KeepForSdk
    public GoogleApi(@NonNull final Context context, final Api<O> api, @Nullable final O o, final StatusExceptionMapper mapper) {
        this(context, (Api<Api.ApiOptions>)api, o, new Builder().setMapper(mapper).build());
    }
    
    private final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T zaa(final int n, @NonNull final T t) {
        t.zau();
        this.zabm.zaa((GoogleApi<Api.ApiOptions>)this, n, (BaseImplementation.ApiMethodImpl<? extends Result, Api.AnyClient>)t);
        return t;
    }
    
    private final <TResult, A extends Api.AnyClient> Task<TResult> zaa(final int n, @NonNull final TaskApiCall<A, TResult> taskApiCall) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zabm.zaa((GoogleApi<Api.ApiOptions>)this, n, (TaskApiCall<Api.AnyClient, TResult>)taskApiCall, (com.google.android.gms.tasks.TaskCompletionSource<TResult>)taskCompletionSource, this.zabl);
        return (Task<TResult>)taskCompletionSource.getTask();
    }
    
    @KeepForSdk
    public GoogleApiClient asGoogleApiClient() {
        return this.zabk;
    }
    
    @KeepForSdk
    protected ClientSettings.Builder createClientSettingsBuilder() {
        final ClientSettings.Builder builder = new ClientSettings.Builder();
        while (true) {
            Label_0107: {
                if (!(this.zabh instanceof Api.ApiOptions.HasGoogleSignInAccountOptions)) {
                    break Label_0107;
                }
                final GoogleSignInAccount googleSignInAccount = ((Api.ApiOptions.HasGoogleSignInAccountOptions)this.zabh).getGoogleSignInAccount();
                if (googleSignInAccount == null) {
                    break Label_0107;
                }
                final Account account = googleSignInAccount.getAccount();
                final ClientSettings.Builder setAccount = builder.setAccount(account);
                Label_0138: {
                    if (!(this.zabh instanceof Api.ApiOptions.HasGoogleSignInAccountOptions)) {
                        break Label_0138;
                    }
                    final GoogleSignInAccount googleSignInAccount2 = ((Api.ApiOptions.HasGoogleSignInAccountOptions)this.zabh).getGoogleSignInAccount();
                    if (googleSignInAccount2 == null) {
                        break Label_0138;
                    }
                    final Set<Scope> set = googleSignInAccount2.getRequestedScopes();
                    return setAccount.addAllRequiredScopes(set).setRealClientClassName(this.mContext.getClass().getName()).setRealClientPackageName(this.mContext.getPackageName());
                }
                final Set<Scope> set = Collections.emptySet();
                return setAccount.addAllRequiredScopes(set).setRealClientClassName(this.mContext.getClass().getName()).setRealClientPackageName(this.mContext.getPackageName());
            }
            if (this.zabh instanceof Api.ApiOptions.HasAccountOptions) {
                final Account account = ((Api.ApiOptions.HasAccountOptions)this.zabh).getAccount();
                continue;
            }
            final Account account = null;
            continue;
        }
    }
    
    @KeepForSdk
    protected Task<Boolean> disconnectService() {
        return this.zabm.zac(this);
    }
    
    @KeepForSdk
    public <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T doBestEffortWrite(@NonNull final T t) {
        return this.zaa(2, t);
    }
    
    @KeepForSdk
    public <TResult, A extends Api.AnyClient> Task<TResult> doBestEffortWrite(final TaskApiCall<A, TResult> taskApiCall) {
        return this.zaa(2, taskApiCall);
    }
    
    @KeepForSdk
    public <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T doRead(@NonNull final T t) {
        return this.zaa(0, t);
    }
    
    @KeepForSdk
    public <TResult, A extends Api.AnyClient> Task<TResult> doRead(final TaskApiCall<A, TResult> taskApiCall) {
        return this.zaa(0, taskApiCall);
    }
    
    @Deprecated
    @KeepForSdk
    public <A extends Api.AnyClient, T extends RegisterListenerMethod<A, ?>, U extends UnregisterListenerMethod<A, ?>> Task<Void> doRegisterEventListener(@NonNull final T t, final U u) {
        Preconditions.checkNotNull((Object)t);
        Preconditions.checkNotNull((Object)u);
        Preconditions.checkNotNull((Object)t.getListenerKey(), (Object)"Listener has already been released.");
        Preconditions.checkNotNull((Object)u.getListenerKey(), (Object)"Listener has already been released.");
        Preconditions.checkArgument(((RegisterListenerMethod<A, ?>)t).getListenerKey().equals(u.getListenerKey()), (Object)"Listener registration and unregistration methods must be constructed with the same ListenerHolder.");
        return this.zabm.zaa((GoogleApi<Api.ApiOptions>)this, (RegisterListenerMethod<Api.AnyClient, ?>)t, (UnregisterListenerMethod<Api.AnyClient, ?>)u);
    }
    
    @KeepForSdk
    public <A extends Api.AnyClient> Task<Void> doRegisterEventListener(@NonNull final RegistrationMethods<A, ?> registrationMethods) {
        Preconditions.checkNotNull((Object)registrationMethods);
        Preconditions.checkNotNull((Object)registrationMethods.zajz.getListenerKey(), (Object)"Listener has already been released.");
        Preconditions.checkNotNull((Object)registrationMethods.zaka.getListenerKey(), (Object)"Listener has already been released.");
        return this.zabm.zaa((GoogleApi<Api.ApiOptions>)this, (RegisterListenerMethod<Api.AnyClient, ?>)registrationMethods.zajz, (UnregisterListenerMethod<Api.AnyClient, ?>)registrationMethods.zaka);
    }
    
    @KeepForSdk
    public Task<Boolean> doUnregisterEventListener(@NonNull final ListenerHolder.ListenerKey<?> listenerKey) {
        Preconditions.checkNotNull((Object)listenerKey, (Object)"Listener key cannot be null.");
        return this.zabm.zaa((GoogleApi<Api.ApiOptions>)this, listenerKey);
    }
    
    @KeepForSdk
    public <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T doWrite(@NonNull final T t) {
        return this.zaa(1, t);
    }
    
    @KeepForSdk
    public <TResult, A extends Api.AnyClient> Task<TResult> doWrite(final TaskApiCall<A, TResult> taskApiCall) {
        return this.zaa(1, taskApiCall);
    }
    
    public final Api<O> getApi() {
        return this.mApi;
    }
    
    @KeepForSdk
    public O getApiOptions() {
        return this.zabh;
    }
    
    @KeepForSdk
    public Context getApplicationContext() {
        return this.mContext;
    }
    
    public final int getInstanceId() {
        return this.mId;
    }
    
    @KeepForSdk
    public Looper getLooper() {
        return this.zabj;
    }
    
    @KeepForSdk
    public <L> ListenerHolder<L> registerListener(@NonNull final L l, final String s) {
        return ListenerHolders.createListenerHolder(l, this.zabj, s);
    }
    
    @WorkerThread
    public Api.Client zaa(final Looper looper, final GoogleApiManager.zaa<O> zaa) {
        return (Api.Client)this.mApi.zai().buildClient(this.mContext, looper, this.createClientSettingsBuilder().build(), this.zabh, zaa, zaa);
    }
    
    public zace zaa(final Context context, final Handler handler) {
        return new zace(context, handler, this.createClientSettingsBuilder().build());
    }
    
    public final zai<O> zak() {
        return this.zabi;
    }
    
    @KeepForSdk
    public static class Settings
    {
        @KeepForSdk
        public static final Settings DEFAULT_SETTINGS;
        public final StatusExceptionMapper zabn;
        public final Looper zabo;
        
        static {
            DEFAULT_SETTINGS = new Builder().build();
        }
        
        @KeepForSdk
        private Settings(final StatusExceptionMapper zabn, final Account account, final Looper zabo) {
            this.zabn = zabn;
            this.zabo = zabo;
        }
        
        @KeepForSdk
        public static class Builder
        {
            private Looper zabj;
            private StatusExceptionMapper zabl;
            
            @KeepForSdk
            public Builder() {
            }
            
            @KeepForSdk
            public Settings build() {
                if (this.zabl == null) {
                    this.zabl = (StatusExceptionMapper)new ApiExceptionMapper();
                }
                if (this.zabj == null) {
                    this.zabj = Looper.getMainLooper();
                }
                return new Settings(this.zabl, null, this.zabj, null);
            }
            
            @KeepForSdk
            public Builder setLooper(final Looper zabj) {
                Preconditions.checkNotNull((Object)zabj, (Object)"Looper must not be null.");
                this.zabj = zabj;
                return this;
            }
            
            @KeepForSdk
            public Builder setMapper(final StatusExceptionMapper zabl) {
                Preconditions.checkNotNull((Object)zabl, (Object)"StatusExceptionMapper must not be null.");
                this.zabl = zabl;
                return this;
            }
        }
    }
}
