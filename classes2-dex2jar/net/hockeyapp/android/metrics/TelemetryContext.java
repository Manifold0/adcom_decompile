// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.metrics;

import android.annotation.SuppressLint;
import java.lang.reflect.Method;
import android.view.Display;
import android.graphics.Point;
import android.view.WindowManager;
import java.util.Map;
import android.content.SharedPreferences$Editor;
import android.telephony.TelephonyManager;
import net.hockeyapp.android.Constants;
import java.util.Locale;
import android.os.Build;
import android.os.Build$VERSION;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import net.hockeyapp.android.utils.HockeyLog;
import net.hockeyapp.android.utils.Util;
import net.hockeyapp.android.metrics.model.User;
import android.content.SharedPreferences;
import net.hockeyapp.android.metrics.model.Session;
import net.hockeyapp.android.metrics.model.Internal;
import net.hockeyapp.android.metrics.model.Device;
import android.content.Context;
import net.hockeyapp.android.metrics.model.Application;

class TelemetryContext
{
    private static final String SESSION_IS_FIRST_KEY = "SESSION_IS_FIRST";
    private static final String SHARED_PREFERENCES_KEY = "HOCKEY_APP_TELEMETRY_CONTEXT";
    private static final String TAG = "HockeyApp-Metrics";
    private final Object IKEY_LOCK;
    protected final Application mApplication;
    protected Context mContext;
    protected final Device mDevice;
    private String mInstrumentationKey;
    protected final Internal mInternal;
    private String mPackageName;
    protected final Session mSession;
    private SharedPreferences mSettings;
    protected final User mUser;
    
    private TelemetryContext() {
        this.IKEY_LOCK = new Object();
        this.mDevice = new Device();
        this.mSession = new Session();
        this.mUser = new User();
        this.mApplication = new Application();
        this.mInternal = new Internal();
    }
    
    protected TelemetryContext(final Context mContext, final String s) {
        this();
        this.mSettings = mContext.getSharedPreferences("HOCKEY_APP_TELEMETRY_CONTEXT", 0);
        this.mContext = mContext;
        this.mInstrumentationKey = Util.convertAppIdentifierToGuid(s);
        this.configDeviceContext();
        this.configUserId();
        this.configInternalContext();
        this.configApplicationContext();
    }
    
    protected void configApplicationContext() {
        HockeyLog.debug("HockeyApp-Metrics", "Configuring application context");
        this.mPackageName = "";
        while (true) {
            try {
                final PackageInfo packageInfo = this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0);
                if (packageInfo.packageName != null) {
                    this.mPackageName = packageInfo.packageName;
                }
                this.setAppVersion(String.format("%s (%S)", packageInfo.versionName, Integer.toString(packageInfo.versionCode)));
                this.setSdkVersion("android:" + "4.1.2");
            }
            catch (PackageManager$NameNotFoundException ex) {
                HockeyLog.debug("HockeyApp-Metrics", "Could not get application context");
                this.setAppVersion("unknown");
                continue;
            }
            finally {
                this.setAppVersion("unknown");
            }
            break;
        }
    }
    
    protected void configDeviceContext() {
        HockeyLog.debug("HockeyApp-Metrics", "Configuring device context");
        this.setOsVersion(Build$VERSION.RELEASE);
        this.setOsName("Android");
        this.setDeviceModel(Build.MODEL);
        this.setDeviceOemName(Build.MANUFACTURER);
        this.setOsLocale(Locale.getDefault().toString());
        this.setOsLanguage(Locale.getDefault().getLanguage());
        this.updateScreenResolution();
        this.setDeviceId(Constants.DEVICE_IDENTIFIER);
        if (((TelephonyManager)this.mContext.getSystemService("phone")).getPhoneType() != 0) {
            this.setDeviceType("Phone");
        }
        else {
            this.setDeviceType("Tablet");
        }
        if (Util.isEmulator()) {
            this.setDeviceModel("[Emulator]" + this.mDevice.getModel());
        }
    }
    
    protected void configInternalContext() {
        this.setSdkVersion("android:" + "4.1.2");
    }
    
    protected void configSessionContext(final String sessionId) {
        HockeyLog.debug("HockeyApp-Metrics", "Configuring session context");
        this.setSessionId(sessionId);
        HockeyLog.debug("HockeyApp-Metrics", "Setting the isNew-flag to true, as we only count new sessions");
        this.setIsNewSession("true");
        final SharedPreferences$Editor edit = this.mSettings.edit();
        if (!this.mSettings.getBoolean("SESSION_IS_FIRST", false)) {
            edit.putBoolean("SESSION_IS_FIRST", true);
            edit.apply();
            this.setIsFirstSession("true");
            HockeyLog.debug("HockeyApp-Metrics", "It's our first session, writing true to SharedPreferences.");
            return;
        }
        this.setIsFirstSession("false");
        HockeyLog.debug("HockeyApp-Metrics", "It's not their first session, writing false to SharedPreferences.");
    }
    
    protected void configUserId() {
        HockeyLog.debug("HockeyApp-Metrics", "Configuring user context");
        HockeyLog.debug("Using pre-supplied anonymous device identifier.");
        this.setAnonymousUserId(Constants.CRASH_IDENTIFIER);
    }
    
    public String getAnonymousUserId() {
        synchronized (this.mUser) {
            return this.mUser.getId();
        }
    }
    
    public String getAppVersion() {
        synchronized (this.mApplication) {
            return this.mApplication.getVer();
        }
    }
    
    protected Map<String, String> getContextTags() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: invokespecial   java/util/LinkedHashMap.<init>:()V
        //     7: astore_1       
        //     8: aload_0        
        //     9: getfield        net/hockeyapp/android/metrics/TelemetryContext.mApplication:Lnet/hockeyapp/android/metrics/model/Application;
        //    12: astore_2       
        //    13: aload_2        
        //    14: monitorenter   
        //    15: aload_0        
        //    16: getfield        net/hockeyapp/android/metrics/TelemetryContext.mApplication:Lnet/hockeyapp/android/metrics/model/Application;
        //    19: aload_1        
        //    20: invokevirtual   net/hockeyapp/android/metrics/model/Application.addToHashMap:(Ljava/util/Map;)V
        //    23: aload_2        
        //    24: monitorexit    
        //    25: aload_0        
        //    26: getfield        net/hockeyapp/android/metrics/TelemetryContext.mDevice:Lnet/hockeyapp/android/metrics/model/Device;
        //    29: astore_2       
        //    30: aload_2        
        //    31: monitorenter   
        //    32: aload_0        
        //    33: getfield        net/hockeyapp/android/metrics/TelemetryContext.mDevice:Lnet/hockeyapp/android/metrics/model/Device;
        //    36: aload_1        
        //    37: invokevirtual   net/hockeyapp/android/metrics/model/Device.addToHashMap:(Ljava/util/Map;)V
        //    40: aload_2        
        //    41: monitorexit    
        //    42: aload_0        
        //    43: getfield        net/hockeyapp/android/metrics/TelemetryContext.mSession:Lnet/hockeyapp/android/metrics/model/Session;
        //    46: astore_2       
        //    47: aload_2        
        //    48: monitorenter   
        //    49: aload_0        
        //    50: getfield        net/hockeyapp/android/metrics/TelemetryContext.mSession:Lnet/hockeyapp/android/metrics/model/Session;
        //    53: aload_1        
        //    54: invokevirtual   net/hockeyapp/android/metrics/model/Session.addToHashMap:(Ljava/util/Map;)V
        //    57: aload_2        
        //    58: monitorexit    
        //    59: aload_0        
        //    60: getfield        net/hockeyapp/android/metrics/TelemetryContext.mUser:Lnet/hockeyapp/android/metrics/model/User;
        //    63: astore_2       
        //    64: aload_2        
        //    65: monitorenter   
        //    66: aload_0        
        //    67: getfield        net/hockeyapp/android/metrics/TelemetryContext.mUser:Lnet/hockeyapp/android/metrics/model/User;
        //    70: aload_1        
        //    71: invokevirtual   net/hockeyapp/android/metrics/model/User.addToHashMap:(Ljava/util/Map;)V
        //    74: aload_2        
        //    75: monitorexit    
        //    76: aload_0        
        //    77: getfield        net/hockeyapp/android/metrics/TelemetryContext.mInternal:Lnet/hockeyapp/android/metrics/model/Internal;
        //    80: astore_2       
        //    81: aload_2        
        //    82: monitorenter   
        //    83: aload_0        
        //    84: getfield        net/hockeyapp/android/metrics/TelemetryContext.mInternal:Lnet/hockeyapp/android/metrics/model/Internal;
        //    87: aload_1        
        //    88: invokevirtual   net/hockeyapp/android/metrics/model/Internal.addToHashMap:(Ljava/util/Map;)V
        //    91: aload_2        
        //    92: monitorexit    
        //    93: aload_1        
        //    94: areturn        
        //    95: astore_1       
        //    96: aload_2        
        //    97: monitorexit    
        //    98: aload_1        
        //    99: athrow         
        //   100: astore_1       
        //   101: aload_2        
        //   102: monitorexit    
        //   103: aload_1        
        //   104: athrow         
        //   105: astore_1       
        //   106: aload_2        
        //   107: monitorexit    
        //   108: aload_1        
        //   109: athrow         
        //   110: astore_1       
        //   111: aload_2        
        //   112: monitorexit    
        //   113: aload_1        
        //   114: athrow         
        //   115: astore_1       
        //   116: aload_2        
        //   117: monitorexit    
        //   118: aload_1        
        //   119: athrow         
        //    Signature:
        //  ()Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  15     25     95     100    Any
        //  32     42     100    105    Any
        //  49     59     105    110    Any
        //  66     76     110    115    Any
        //  83     93     115    120    Any
        //  96     98     95     100    Any
        //  101    103    100    105    Any
        //  106    108    105    110    Any
        //  111    113    110    115    Any
        //  116    118    115    120    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 86, Size: 86
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public String getDeviceId() {
        return this.mDevice.getId();
    }
    
    public String getDeviceModel() {
        synchronized (this.mDevice) {
            return this.mDevice.getModel();
        }
    }
    
    public String getDeviceOemName() {
        synchronized (this.mDevice) {
            return this.mDevice.getOemName();
        }
    }
    
    public String getDeviceType() {
        return this.mDevice.getType();
    }
    
    public String getInstrumentationKey() {
        synchronized (this.IKEY_LOCK) {
            return this.mInstrumentationKey;
        }
    }
    
    public String getIsFirstSession() {
        synchronized (this.mSession) {
            return this.mSession.getIsFirst();
        }
    }
    
    public String getIsNewSession() {
        synchronized (this.mSession) {
            return this.mSession.getIsNew();
        }
    }
    
    public String getOSLanguage() {
        synchronized (this.mDevice) {
            return this.mDevice.getLanguage();
        }
    }
    
    public String getOsLocale() {
        synchronized (this.mDevice) {
            return this.mDevice.getLocale();
        }
    }
    
    public String getOsName() {
        synchronized (this.mDevice) {
            return this.mDevice.getOs();
        }
    }
    
    public String getOsVersion() {
        synchronized (this.mDevice) {
            return this.mDevice.getOsVersion();
        }
    }
    
    protected String getPackageName() {
        return this.mPackageName;
    }
    
    public String getScreenResolution() {
        synchronized (this.mDevice) {
            return this.mDevice.getScreenResolution();
        }
    }
    
    public String getSdkVersion() {
        synchronized (this.mInternal) {
            return this.mInternal.getSdkVersion();
        }
    }
    
    public String getSessionId() {
        synchronized (this.mSession) {
            return this.mSession.getId();
        }
    }
    
    protected void renewSessionContext(final String s) {
        this.configSessionContext(s);
    }
    
    public void setAnonymousUserId(final String id) {
        synchronized (this.mUser) {
            this.mUser.setId(id);
        }
    }
    
    public void setAppVersion(final String ver) {
        synchronized (this.mApplication) {
            this.mApplication.setVer(ver);
        }
    }
    
    public void setDeviceId(final String id) {
        synchronized (this.mDevice) {
            this.mDevice.setId(id);
        }
    }
    
    public void setDeviceModel(final String model) {
        synchronized (this.mDevice) {
            this.mDevice.setModel(model);
        }
    }
    
    public void setDeviceOemName(final String oemName) {
        synchronized (this.mDevice) {
            this.mDevice.setOemName(oemName);
        }
    }
    
    public void setDeviceType(final String type) {
        synchronized (this.mDevice) {
            this.mDevice.setType(type);
        }
    }
    
    public void setInstrumentationKey(final String mInstrumentationKey) {
        synchronized (this) {
            synchronized (this.IKEY_LOCK) {
                this.mInstrumentationKey = mInstrumentationKey;
            }
        }
    }
    
    public void setIsFirstSession(final String isFirst) {
        synchronized (this.mSession) {
            this.mSession.setIsFirst(isFirst);
        }
    }
    
    public void setIsNewSession(final String isNew) {
        synchronized (this.mSession) {
            this.mSession.setIsNew(isNew);
        }
    }
    
    public void setOsLanguage(final String language) {
        synchronized (this.mDevice) {
            this.mDevice.setLanguage(language);
        }
    }
    
    public void setOsLocale(final String locale) {
        synchronized (this.mDevice) {
            this.mDevice.setLocale(locale);
        }
    }
    
    public void setOsName(final String os) {
        synchronized (this.mDevice) {
            this.mDevice.setOs(os);
        }
    }
    
    public void setOsVersion(final String osVersion) {
        synchronized (this.mDevice) {
            this.mDevice.setOsVersion(osVersion);
        }
    }
    
    public void setScreenResolution(final String screenResolution) {
        synchronized (this.mDevice) {
            this.mDevice.setScreenResolution(screenResolution);
        }
    }
    
    public void setSdkVersion(final String sdkVersion) {
        synchronized (this.mInternal) {
            this.mInternal.setSdkVersion(sdkVersion);
        }
    }
    
    public void setSessionId(final String id) {
        synchronized (this.mSession) {
            this.mSession.setId(id);
        }
    }
    
    @SuppressLint({ "NewApi", "Deprecation" })
    protected void updateScreenResolution() {
        if (this.mContext != null) {
            final WindowManager windowManager = (WindowManager)this.mContext.getSystemService("window");
            int n = 0;
            int n2 = 0;
            Label_0067: {
                if (Build$VERSION.SDK_INT >= 17) {
                    final Point point = new Point();
                    final Display defaultDisplay = windowManager.getDefaultDisplay();
                    if (defaultDisplay != null) {
                        defaultDisplay.getRealSize(point);
                        n = point.x;
                        n2 = point.y;
                    }
                    else {
                        n = 0;
                        n2 = 0;
                    }
                }
                else {
                    if (Build$VERSION.SDK_INT >= 13) {
                        try {
                            final Method method = Display.class.getMethod("getRawWidth", (Class<?>[])new Class[0]);
                            final Method method2 = Display.class.getMethod("getRawHeight", (Class<?>[])new Class[0]);
                            final Display defaultDisplay2 = windowManager.getDefaultDisplay();
                            n = (int)method.invoke(defaultDisplay2, new Object[0]);
                            n2 = (int)method2.invoke(defaultDisplay2, new Object[0]);
                            break Label_0067;
                        }
                        catch (Exception ex) {
                            final Point point2 = new Point();
                            final Display defaultDisplay3 = windowManager.getDefaultDisplay();
                            if (defaultDisplay3 != null) {
                                defaultDisplay3.getRealSize(point2);
                                n = point2.x;
                                n2 = point2.y;
                            }
                            else {
                                n = 0;
                                n2 = 0;
                            }
                            HockeyLog.debug("HockeyApp-Metrics", "Couldn't determine screen resolution: " + ex.toString());
                            break Label_0067;
                        }
                    }
                    final Display defaultDisplay4 = windowManager.getDefaultDisplay();
                    n = defaultDisplay4.getWidth();
                    n2 = defaultDisplay4.getHeight();
                }
            }
            this.setScreenResolution(String.valueOf(n2) + "x" + String.valueOf(n));
        }
    }
}
