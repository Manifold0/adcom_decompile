// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.BroadcastReceiver;
import android.os.Looper;
import android.os.Handler;
import android.app.Application;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.concurrent.atomic.AtomicReference;
import android.annotation.TargetApi;
import com.google.android.gms.common.api.internal.BackgroundDetector$BackgroundStateChangeListener;
import com.google.android.gms.common.internal.Objects;
import com.google.firebase.events.Event;
import android.support.annotation.UiThread;
import com.google.firebase.internal.InternalTokenResult;
import android.support.annotation.VisibleForTesting;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.GetTokenResult;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.common.api.internal.BackgroundDetector;
import android.support.v4.content.ContextCompat;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager$NameNotFoundException;
import java.util.Iterator;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.Base64Utils;
import java.nio.charset.Charset;
import android.text.TextUtils;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.util.ProcessUtils;
import java.util.Collection;
import java.util.ArrayList;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Component;
import com.google.firebase.internal.zza;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.CopyOnWriteArrayList;
import android.support.v4.util.ArrayMap;
import java.util.Collections;
import java.util.Arrays;
import com.google.firebase.internal.InternalTokenProvider;
import java.util.concurrent.atomic.AtomicBoolean;
import com.google.firebase.events.Publisher;
import android.content.SharedPreferences;
import com.google.firebase.components.zzf;
import android.content.Context;
import java.util.concurrent.Executor;
import java.util.Set;
import java.util.List;
import javax.annotation.concurrent.GuardedBy;
import java.util.Map;
import com.google.firebase.annotations.PublicApi;

@PublicApi
public class FirebaseApp
{
    @GuardedBy("LOCK")
    static final Map<String, FirebaseApp> zza;
    private static final List<String> zzb;
    private static final List<String> zzc;
    private static final List<String> zzd;
    private static final List<String> zze;
    private static final Set<String> zzf;
    private static final Object zzg;
    private static final Executor zzh;
    private final Context zzi;
    private final String zzj;
    private final FirebaseOptions zzk;
    private final zzf zzl;
    private final SharedPreferences zzm;
    private final Publisher zzn;
    private final AtomicBoolean zzo;
    private final AtomicBoolean zzp;
    private final AtomicBoolean zzq;
    private final List<IdTokenListener> zzr;
    private final List<BackgroundStateChangeListener> zzs;
    private final List<FirebaseAppLifecycleListener> zzt;
    private InternalTokenProvider zzu;
    private IdTokenListenersCountChangedListener zzv;
    
    static {
        zzb = Arrays.asList("com.google.firebase.auth.FirebaseAuth", "com.google.firebase.iid.FirebaseInstanceId");
        zzc = Collections.singletonList("com.google.firebase.crash.FirebaseCrash");
        zzd = Arrays.asList("com.google.android.gms.measurement.AppMeasurement");
        zze = Arrays.asList(new String[0]);
        zzf = Collections.emptySet();
        zzg = new Object();
        zzh = new zzb((byte)0);
        zza = (Map)new ArrayMap();
    }
    
    private FirebaseApp(final Context context, final String s, final FirebaseOptions firebaseOptions) {
        this.zzo = new AtomicBoolean(false);
        this.zzp = new AtomicBoolean();
        this.zzr = new CopyOnWriteArrayList<IdTokenListener>();
        this.zzs = new CopyOnWriteArrayList<BackgroundStateChangeListener>();
        this.zzt = new CopyOnWriteArrayList<FirebaseAppLifecycleListener>();
        this.zzi = (Context)Preconditions.checkNotNull((Object)context);
        this.zzj = Preconditions.checkNotEmpty(s);
        this.zzk = (FirebaseOptions)Preconditions.checkNotNull((Object)firebaseOptions);
        this.zzv = (IdTokenListenersCountChangedListener)new com.google.firebase.internal.zza();
        this.zzm = context.getSharedPreferences("com.google.firebase.common.prefs", 0);
        this.zzq = new AtomicBoolean(this.zzb());
        this.zzl = new zzf(FirebaseApp.zzh, Component.Component$1.zza(context).zza(), (Component<?>[])new Component[] { Component.of(context, Context.class, (Class<? super Context>[])new Class[0]), Component.of(this, FirebaseApp.class, (Class<? super FirebaseApp>[])new Class[0]), Component.of(firebaseOptions, FirebaseOptions.class, (Class<? super FirebaseOptions>[])new Class[0]) });
        this.zzn = (Publisher)this.zzl.get(Publisher.class);
    }
    
    @PublicApi
    public static List<FirebaseApp> getApps(final Context context) {
        synchronized (FirebaseApp.zzg) {
            return new ArrayList<FirebaseApp>(FirebaseApp.zza.values());
        }
    }
    
    @Nullable
    @PublicApi
    public static FirebaseApp getInstance() {
        synchronized (FirebaseApp.zzg) {
            if (FirebaseApp.zza.get("[DEFAULT]") == null) {
                throw new IllegalStateException("Default FirebaseApp is not initialized in this process " + ProcessUtils.getMyProcessName() + ". Make sure to call FirebaseApp.initializeApp(Context) first.");
            }
        }
        // monitorexit(o)
        return;
    }
    
    @PublicApi
    public static FirebaseApp getInstance(@NonNull final String s) {
        while (true) {
            while (true) {
                Object o;
                synchronized (FirebaseApp.zzg) {
                    o = FirebaseApp.zza.get(s.trim());
                    if (o != null) {
                        return (FirebaseApp)o;
                    }
                    o = zzd();
                    if (((List)o).isEmpty()) {
                        o = "";
                        throw new IllegalStateException(String.format("FirebaseApp with name %s doesn't exist. %s", s, o));
                    }
                }
                o = "Available app names: " + TextUtils.join((CharSequence)", ", (Iterable)o);
                continue;
            }
        }
    }
    
    @KeepForSdk
    public static String getPersistenceKey(final String s, final FirebaseOptions firebaseOptions) {
        return Base64Utils.encodeUrlSafeNoPadding(s.getBytes(Charset.defaultCharset())) + "+" + Base64Utils.encodeUrlSafeNoPadding(firebaseOptions.getApplicationId().getBytes(Charset.defaultCharset()));
    }
    
    @Nullable
    @PublicApi
    public static FirebaseApp initializeApp(final Context context) {
        final FirebaseOptions fromResource;
        synchronized (FirebaseApp.zzg) {
            if (FirebaseApp.zza.containsKey("[DEFAULT]")) {
                return getInstance();
            }
            fromResource = FirebaseOptions.fromResource(context);
            if (fromResource == null) {
                Log.d("FirebaseApp", "Default FirebaseApp failed to initialize because no default options were found. This usually means that com.google.gms:google-services was not applied to your gradle project.");
                return null;
            }
        }
        final Context context2;
        // monitorexit(o)
        return initializeApp(context2, fromResource);
    }
    
    @PublicApi
    public static FirebaseApp initializeApp(final Context context, final FirebaseOptions firebaseOptions) {
        return initializeApp(context, firebaseOptions, "[DEFAULT]");
    }
    
    @PublicApi
    public static FirebaseApp initializeApp(Context applicationContext, final FirebaseOptions firebaseOptions, String trim) {
        FirebaseApp.zza.zza((Context)applicationContext);
        trim = trim.trim();
        Label_0104: {
            if (((Context)applicationContext).getApplicationContext() != null) {
                break Label_0104;
            }
            while (true) {
                synchronized (FirebaseApp.zzg) {
                    Preconditions.checkState(!FirebaseApp.zza.containsKey(trim), (Object)("FirebaseApp name " + trim + " already exists!"));
                    Preconditions.checkNotNull(applicationContext, (Object)"Application context cannot be null.");
                    final FirebaseApp firebaseApp = new FirebaseApp((Context)applicationContext, trim, firebaseOptions);
                    FirebaseApp.zza.put(trim, firebaseApp);
                    // monitorexit(FirebaseApp.zzg)
                    firebaseApp.zze();
                    return firebaseApp;
                    applicationContext = ((Context)applicationContext).getApplicationContext();
                }
            }
        }
    }
    
    private static <T> void zza(final Class<T> clazz, final T t, Iterable<String> iterator, final boolean b) {
        iterator = ((Iterable<String>)iterator).iterator();
    Label_0044_Outer:
        while (iterator.hasNext()) {
            final String s = iterator.next();
            while (true) {
                if (b) {
                    try {
                        if (!FirebaseApp.zze.contains(s)) {
                            continue Label_0044_Outer;
                        }
                        final Method method = Class.forName(s).getMethod("getInstance", clazz);
                        final int modifiers = method.getModifiers();
                        if (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers)) {
                            method.invoke(null, t);
                            continue Label_0044_Outer;
                        }
                        continue Label_0044_Outer;
                    }
                    catch (ClassNotFoundException ex3) {
                        if (FirebaseApp.zzf.contains(s)) {
                            throw new IllegalStateException(s + " is missing, but is required. Check if it has been removed by Proguard.");
                        }
                        Log.d("FirebaseApp", s + " is not linked. Skipping initialization.");
                        continue Label_0044_Outer;
                    }
                    catch (NoSuchMethodException ex4) {
                        throw new IllegalStateException(s + "#getInstance has been removed by Proguard. Add keep rule to prevent it.");
                    }
                    catch (InvocationTargetException ex) {
                        Log.wtf("FirebaseApp", "Firebase API initialization failure.", (Throwable)ex);
                        continue Label_0044_Outer;
                    }
                    catch (IllegalAccessException ex2) {
                        Log.wtf("FirebaseApp", "Failed to initialize " + s, (Throwable)ex2);
                        continue Label_0044_Outer;
                    }
                    break;
                }
                continue;
            }
        }
    }
    
    private void zza(final boolean b) {
        Log.d("FirebaseApp", "Notifying background state change listeners.");
        final Iterator<BackgroundStateChangeListener> iterator = this.zzs.iterator();
        while (iterator.hasNext()) {
            iterator.next().onBackgroundStateChanged(b);
        }
    }
    
    private boolean zzb() {
        final boolean b = true;
        boolean boolean1;
        if (this.zzm.contains("firebase_data_collection_default_enabled")) {
            boolean1 = this.zzm.getBoolean("firebase_data_collection_default_enabled", true);
        }
        else {
            try {
                final PackageManager packageManager = this.zzi.getPackageManager();
                boolean1 = b;
                if (packageManager != null) {
                    final ApplicationInfo applicationInfo = packageManager.getApplicationInfo(this.zzi.getPackageName(), 128);
                    boolean1 = b;
                    if (applicationInfo != null) {
                        boolean1 = b;
                        if (applicationInfo.metaData != null) {
                            boolean1 = b;
                            if (applicationInfo.metaData.containsKey("firebase_data_collection_default_enabled")) {
                                return applicationInfo.metaData.getBoolean("firebase_data_collection_default_enabled");
                            }
                        }
                    }
                }
            }
            catch (PackageManager$NameNotFoundException ex) {
                return true;
            }
        }
        return boolean1;
    }
    
    private void zzc() {
        Preconditions.checkState(!this.zzp.get(), (Object)"FirebaseApp was deleted");
    }
    
    private static List<String> zzd() {
        final ArrayList<String> list = new ArrayList<String>();
        synchronized (FirebaseApp.zzg) {
            final Iterator<FirebaseApp> iterator = FirebaseApp.zza.values().iterator();
            while (iterator.hasNext()) {
                list.add(iterator.next().getName());
            }
        }
        // monitorexit(o)
        final List<Comparable> list2;
        Collections.sort(list2);
        return (List<String>)list2;
    }
    
    private void zze() {
        final boolean deviceProtectedStorage = ContextCompat.isDeviceProtectedStorage(this.zzi);
        if (deviceProtectedStorage) {
            FirebaseApp.zzc.zza(this.zzi);
        }
        else {
            this.zzl.zza(this.isDefaultApp());
        }
        zza(FirebaseApp.class, this, FirebaseApp.zzb, deviceProtectedStorage);
        if (this.isDefaultApp()) {
            zza(FirebaseApp.class, this, FirebaseApp.zzc, deviceProtectedStorage);
            zza(Context.class, this.zzi, FirebaseApp.zzd, deviceProtectedStorage);
        }
    }
    
    @KeepForSdk
    public void addBackgroundStateChangeListener(final BackgroundStateChangeListener backgroundStateChangeListener) {
        this.zzc();
        if (this.zzo.get() && BackgroundDetector.getInstance().isInBackground()) {
            backgroundStateChangeListener.onBackgroundStateChanged(true);
        }
        this.zzs.add(backgroundStateChangeListener);
    }
    
    @Deprecated
    @KeepForSdk
    public void addIdTokenListener(@NonNull final IdTokenListener idTokenListener) {
        this.zzc();
        Preconditions.checkNotNull((Object)idTokenListener);
        this.zzr.add(idTokenListener);
        this.zzv.onListenerCountChanged(this.zzr.size());
    }
    
    @KeepForSdk
    public void addLifecycleEventListener(@NonNull final FirebaseAppLifecycleListener firebaseAppLifecycleListener) {
        this.zzc();
        Preconditions.checkNotNull((Object)firebaseAppLifecycleListener);
        this.zzt.add(firebaseAppLifecycleListener);
    }
    
    @PublicApi
    public void delete() {
        if (this.zzp.compareAndSet(false, true)) {
            Object o = FirebaseApp.zzg;
            synchronized (o) {
                FirebaseApp.zza.remove(this.zzj);
                // monitorexit(o)
                o = this.zzt.iterator();
                while (((Iterator)o).hasNext()) {
                    ((Iterator)o).next();
                }
            }
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof FirebaseApp && this.zzj.equals(((FirebaseApp)o).getName());
    }
    
    @KeepForSdk
    public <T> T get(final Class<T> clazz) {
        this.zzc();
        return (T)this.zzl.get(clazz);
    }
    
    @NonNull
    @PublicApi
    public Context getApplicationContext() {
        this.zzc();
        return this.zzi;
    }
    
    @Deprecated
    @KeepForSdk
    public List<IdTokenListener> getListeners() {
        this.zzc();
        return this.zzr;
    }
    
    @NonNull
    @PublicApi
    public String getName() {
        this.zzc();
        return this.zzj;
    }
    
    @NonNull
    @PublicApi
    public FirebaseOptions getOptions() {
        this.zzc();
        return this.zzk;
    }
    
    @KeepForSdk
    public String getPersistenceKey() {
        return Base64Utils.encodeUrlSafeNoPadding(this.getName().getBytes(Charset.defaultCharset())) + "+" + Base64Utils.encodeUrlSafeNoPadding(this.getOptions().getApplicationId().getBytes(Charset.defaultCharset()));
    }
    
    @Deprecated
    @KeepForSdk
    public Task<GetTokenResult> getToken(final boolean b) {
        this.zzc();
        if (this.zzu == null) {
            return (Task<GetTokenResult>)Tasks.forException((Exception)new FirebaseApiNotAvailableException("firebase-auth is not linked, please fall back to unauthenticated mode."));
        }
        return this.zzu.getAccessToken(b);
    }
    
    @Deprecated
    @Nullable
    @KeepForSdk
    public String getUid() throws FirebaseApiNotAvailableException {
        this.zzc();
        if (this.zzu == null) {
            throw new FirebaseApiNotAvailableException("firebase-auth is not linked, please fall back to unauthenticated mode.");
        }
        return this.zzu.getUid();
    }
    
    @Override
    public int hashCode() {
        return this.zzj.hashCode();
    }
    
    @KeepForSdk
    public boolean isDataCollectionDefaultEnabled() {
        this.zzc();
        return this.zzq.get();
    }
    
    @VisibleForTesting
    @KeepForSdk
    public boolean isDefaultApp() {
        return "[DEFAULT]".equals(this.getName());
    }
    
    @Deprecated
    @UiThread
    @KeepForSdk
    public void notifyIdTokenListeners(@NonNull final InternalTokenResult internalTokenResult) {
        Log.d("FirebaseApp", "Notifying auth state listeners.");
        final Iterator<IdTokenListener> iterator = this.zzr.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            iterator.next().onIdTokenChanged(internalTokenResult);
            ++n;
        }
        Log.d("FirebaseApp", String.format("Notified %d auth state listeners.", n));
    }
    
    @KeepForSdk
    public void removeBackgroundStateChangeListener(final BackgroundStateChangeListener backgroundStateChangeListener) {
        this.zzc();
        this.zzs.remove(backgroundStateChangeListener);
    }
    
    @Deprecated
    @KeepForSdk
    public void removeIdTokenListener(@NonNull final IdTokenListener idTokenListener) {
        this.zzc();
        Preconditions.checkNotNull((Object)idTokenListener);
        this.zzr.remove(idTokenListener);
        this.zzv.onListenerCountChanged(this.zzr.size());
    }
    
    @KeepForSdk
    public void removeLifecycleEventListener(@NonNull final FirebaseAppLifecycleListener firebaseAppLifecycleListener) {
        this.zzc();
        Preconditions.checkNotNull((Object)firebaseAppLifecycleListener);
        this.zzt.remove(firebaseAppLifecycleListener);
    }
    
    @PublicApi
    public void setAutomaticResourceManagementEnabled(final boolean b) {
        this.zzc();
        if (this.zzo.compareAndSet(!b, b)) {
            final boolean inBackground = BackgroundDetector.getInstance().isInBackground();
            if (b && inBackground) {
                this.zza(true);
            }
            else if (!b && inBackground) {
                this.zza(false);
            }
        }
    }
    
    @KeepForSdk
    public void setDataCollectionDefaultEnabled(final boolean b) {
        this.zzc();
        if (this.zzq.compareAndSet(!b, b)) {
            this.zzm.edit().putBoolean("firebase_data_collection_default_enabled", b).commit();
            this.zzn.publish(new Event<Object>(DataCollectionDefaultChange.class, new DataCollectionDefaultChange(b)));
        }
    }
    
    @Deprecated
    @KeepForSdk
    public void setIdTokenListenersCountChangedListener(@NonNull final IdTokenListenersCountChangedListener idTokenListenersCountChangedListener) {
        (this.zzv = (IdTokenListenersCountChangedListener)Preconditions.checkNotNull((Object)idTokenListenersCountChangedListener)).onListenerCountChanged(this.zzr.size());
    }
    
    @Deprecated
    @KeepForSdk
    public void setTokenProvider(@NonNull final InternalTokenProvider internalTokenProvider) {
        this.zzu = (InternalTokenProvider)Preconditions.checkNotNull((Object)internalTokenProvider);
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper((Object)this).add("name", (Object)this.zzj).add("options", (Object)this.zzk).toString();
    }
    
    @KeepForSdk
    public interface BackgroundStateChangeListener
    {
        @KeepForSdk
        void onBackgroundStateChanged(final boolean p0);
    }
    
    @Deprecated
    @KeepForSdk
    public interface IdTokenListener
    {
        @KeepForSdk
        void onIdTokenChanged(@NonNull final InternalTokenResult p0);
    }
    
    @Deprecated
    @KeepForSdk
    public interface IdTokenListenersCountChangedListener
    {
        @KeepForSdk
        void onListenerCountChanged(final int p0);
    }
    
    @TargetApi(14)
    static final class zza implements BackgroundDetector$BackgroundStateChangeListener
    {
        private static AtomicReference<zza> zza;
        
        static {
            FirebaseApp.zza.zza = new AtomicReference<zza>();
        }
        
        private zza() {
        }
        
        static /* synthetic */ void zza(final Context context) {
            if (PlatformVersion.isAtLeastIceCreamSandwich() && context.getApplicationContext() instanceof Application) {
                final Application application = (Application)context.getApplicationContext();
                if (FirebaseApp.zza.zza.get() == null) {
                    final zza zza = new zza();
                    if (FirebaseApp.zza.zza.compareAndSet(null, zza)) {
                        BackgroundDetector.initialize(application);
                        BackgroundDetector.getInstance().addListener((BackgroundDetector$BackgroundStateChangeListener)zza);
                    }
                }
            }
        }
        
        public final void onBackgroundStateChanged(final boolean b) {
            synchronized (FirebaseApp.zzg) {
                for (final FirebaseApp firebaseApp : new ArrayList<FirebaseApp>(FirebaseApp.zza.values())) {
                    if (firebaseApp.zzo.get()) {
                        firebaseApp.zza(b);
                    }
                }
            }
        }
        // monitorexit(o)
    }
    
    static final class zzb implements Executor
    {
        private static final Handler zza;
        
        static {
            zza = new Handler(Looper.getMainLooper());
        }
        
        private zzb() {
        }
        
        @Override
        public final void execute(@NonNull final Runnable runnable) {
            zzb.zza.post(runnable);
        }
    }
    
    @TargetApi(24)
    static final class zzc extends BroadcastReceiver
    {
        private static AtomicReference<zzc> zza;
        private final Context zzb;
        
        static {
            zzc.zza = new AtomicReference<zzc>();
        }
        
        private zzc(final Context zzb) {
            this.zzb = zzb;
        }
        
        static /* synthetic */ void zza(final Context context) {
            if (zzc.zza.get() == null) {
                final zzc zzc = new zzc(context);
                if (FirebaseApp.zzc.zza.compareAndSet(null, zzc)) {
                    context.registerReceiver((BroadcastReceiver)zzc, new IntentFilter("android.intent.action.USER_UNLOCKED"));
                }
            }
        }
        
        public final void onReceive(final Context context, final Intent intent) {
            synchronized (FirebaseApp.zzg) {
                final Iterator<FirebaseApp> iterator = FirebaseApp.zza.values().iterator();
                while (iterator.hasNext()) {
                    iterator.next().zze();
                }
            }
            // monitorexit(context)
            this.zzb.unregisterReceiver((BroadcastReceiver)this);
        }
    }
}
