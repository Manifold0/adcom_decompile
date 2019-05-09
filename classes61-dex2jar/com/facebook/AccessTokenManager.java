// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook;

import android.os.Handler;
import android.os.Looper;
import android.content.Context;
import android.app.PendingIntent;
import android.app.AlarmManager;
import android.os.Parcelable;
import android.content.Intent;
import org.json.JSONArray;
import org.json.JSONObject;
import android.util.Log;
import java.util.Locale;
import com.facebook.internal.Utility;
import java.util.Set;
import java.util.HashSet;
import android.os.Bundle;
import com.facebook.internal.Validate;
import java.util.concurrent.atomic.AtomicBoolean;
import android.support.v4.content.LocalBroadcastManager;
import java.util.Date;

public final class AccessTokenManager
{
    public static final String ACTION_CURRENT_ACCESS_TOKEN_CHANGED = "com.facebook.sdk.ACTION_CURRENT_ACCESS_TOKEN_CHANGED";
    public static final String EXTRA_NEW_ACCESS_TOKEN = "com.facebook.sdk.EXTRA_NEW_ACCESS_TOKEN";
    public static final String EXTRA_OLD_ACCESS_TOKEN = "com.facebook.sdk.EXTRA_OLD_ACCESS_TOKEN";
    private static final String ME_PERMISSIONS_GRAPH_PATH = "me/permissions";
    public static final String SHARED_PREFERENCES_NAME = "com.facebook.AccessTokenManager.SharedPreferences";
    public static final String TAG = "AccessTokenManager";
    private static final String TOKEN_EXTEND_GRAPH_PATH = "oauth/access_token";
    private static final int TOKEN_EXTEND_RETRY_SECONDS = 3600;
    private static final int TOKEN_EXTEND_THRESHOLD_SECONDS = 86400;
    private static volatile AccessTokenManager instance;
    private final AccessTokenCache accessTokenCache;
    private AccessToken currentAccessToken;
    private Date lastAttemptedTokenExtendDate;
    private final LocalBroadcastManager localBroadcastManager;
    private AtomicBoolean tokenRefreshInProgress;
    
    AccessTokenManager(final LocalBroadcastManager localBroadcastManager, final AccessTokenCache accessTokenCache) {
        this.tokenRefreshInProgress = new AtomicBoolean(false);
        this.lastAttemptedTokenExtendDate = new Date(0L);
        Validate.notNull(localBroadcastManager, "localBroadcastManager");
        Validate.notNull(accessTokenCache, "accessTokenCache");
        this.localBroadcastManager = localBroadcastManager;
        this.accessTokenCache = accessTokenCache;
    }
    
    private static GraphRequest createExtendAccessTokenRequest(final AccessToken accessToken, final GraphRequest.Callback callback) {
        final Bundle bundle = new Bundle();
        bundle.putString("grant_type", "fb_extend_sso_token");
        return new GraphRequest(accessToken, "oauth/access_token", bundle, HttpMethod.GET, callback);
    }
    
    private static GraphRequest createGrantedPermissionsRequest(final AccessToken accessToken, final GraphRequest.Callback callback) {
        return new GraphRequest(accessToken, "me/permissions", new Bundle(), HttpMethod.GET, callback);
    }
    
    static AccessTokenManager getInstance() {
        Label_0041: {
            if (AccessTokenManager.instance != null) {
                break Label_0041;
            }
            synchronized (AccessTokenManager.class) {
                if (AccessTokenManager.instance == null) {
                    AccessTokenManager.instance = new AccessTokenManager(LocalBroadcastManager.getInstance(FacebookSdk.getApplicationContext()), new AccessTokenCache());
                }
                return AccessTokenManager.instance;
            }
        }
    }
    
    private void refreshCurrentAccessTokenImpl(final AccessToken.AccessTokenRefreshCallback accessTokenRefreshCallback) {
        final AccessToken currentAccessToken = this.currentAccessToken;
        if (currentAccessToken == null) {
            if (accessTokenRefreshCallback != null) {
                accessTokenRefreshCallback.OnTokenRefreshFailed(new FacebookException("No current access token to refresh"));
            }
        }
        else {
            if (this.tokenRefreshInProgress.compareAndSet(false, true)) {
                this.lastAttemptedTokenExtendDate = new Date();
                final HashSet set = new HashSet();
                final HashSet set2 = new HashSet();
                final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
                final RefreshResult refreshResult = new RefreshResult();
                final GraphRequestBatch graphRequestBatch = new GraphRequestBatch(new GraphRequest[] { createGrantedPermissionsRequest(currentAccessToken, new GraphRequest.Callback() {
                        @Override
                        public void onCompleted(final GraphResponse graphResponse) {
                            final JSONObject jsonObject = graphResponse.getJSONObject();
                            if (jsonObject != null) {
                                final JSONArray optJSONArray = jsonObject.optJSONArray("data");
                                if (optJSONArray != null) {
                                    atomicBoolean.set(true);
                                    for (int i = 0; i < optJSONArray.length(); ++i) {
                                        final JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                                        if (optJSONObject != null) {
                                            final String optString = optJSONObject.optString("permission");
                                            final String optString2 = optJSONObject.optString("status");
                                            if (!Utility.isNullOrEmpty(optString) && !Utility.isNullOrEmpty(optString2)) {
                                                final String lowerCase = optString2.toLowerCase(Locale.US);
                                                if (lowerCase.equals("granted")) {
                                                    set.add(optString);
                                                }
                                                else if (lowerCase.equals("declined")) {
                                                    set2.add(optString);
                                                }
                                                else {
                                                    Log.w("AccessTokenManager", "Unexpected status: " + lowerCase);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }), createExtendAccessTokenRequest(currentAccessToken, new GraphRequest.Callback() {
                        @Override
                        public void onCompleted(final GraphResponse graphResponse) {
                            final JSONObject jsonObject = graphResponse.getJSONObject();
                            if (jsonObject == null) {
                                return;
                            }
                            refreshResult.accessToken = jsonObject.optString("access_token");
                            refreshResult.expiresAt = jsonObject.optInt("expires_at");
                        }
                    }) });
                graphRequestBatch.addCallback((GraphRequestBatch.Callback)new GraphRequestBatch.Callback() {
                    @Override
                    public void onBatchCompleted(final GraphRequestBatch p0) {
                        // 
                        // This method could not be decompiled.
                        // 
                        // Original Bytecode:
                        // 
                        //     3: invokevirtual   com/facebook/AccessTokenManager.getCurrentAccessToken:()Lcom/facebook/AccessToken;
                        //     6: ifnull          28
                        //     9: invokestatic    com/facebook/AccessTokenManager.getInstance:()Lcom/facebook/AccessTokenManager;
                        //    12: invokevirtual   com/facebook/AccessTokenManager.getCurrentAccessToken:()Lcom/facebook/AccessToken;
                        //    15: invokevirtual   com/facebook/AccessToken.getUserId:()Ljava/lang/String;
                        //    18: aload_0        
                        //    19: getfield        com/facebook/AccessTokenManager$4.val$accessToken:Lcom/facebook/AccessToken;
                        //    22: invokevirtual   com/facebook/AccessToken.getUserId:()Ljava/lang/String;
                        //    25: if_acmpeq       86
                        //    28: aload_0        
                        //    29: getfield        com/facebook/AccessTokenManager$4.val$callback:Lcom/facebook/AccessToken$AccessTokenRefreshCallback;
                        //    32: ifnull          53
                        //    35: aload_0        
                        //    36: getfield        com/facebook/AccessTokenManager$4.val$callback:Lcom/facebook/AccessToken$AccessTokenRefreshCallback;
                        //    39: new             Lcom/facebook/FacebookException;
                        //    42: dup            
                        //    43: ldc             "No current access token to refresh"
                        //    45: invokespecial   com/facebook/FacebookException.<init>:(Ljava/lang/String;)V
                        //    48: invokeinterface com/facebook/AccessToken$AccessTokenRefreshCallback.OnTokenRefreshFailed:(Lcom/facebook/FacebookException;)V
                        //    53: aload_0        
                        //    54: getfield        com/facebook/AccessTokenManager$4.this$0:Lcom/facebook/AccessTokenManager;
                        //    57: invokestatic    com/facebook/AccessTokenManager.access$200:(Lcom/facebook/AccessTokenManager;)Ljava/util/concurrent/atomic/AtomicBoolean;
                        //    60: iconst_0       
                        //    61: invokevirtual   java/util/concurrent/atomic/AtomicBoolean.set:(Z)V
                        //    64: aload_0        
                        //    65: getfield        com/facebook/AccessTokenManager$4.val$callback:Lcom/facebook/AccessToken$AccessTokenRefreshCallback;
                        //    68: ifnull          85
                        //    71: iconst_0       
                        //    72: ifeq            85
                        //    75: aload_0        
                        //    76: getfield        com/facebook/AccessTokenManager$4.val$callback:Lcom/facebook/AccessToken$AccessTokenRefreshCallback;
                        //    79: aconst_null    
                        //    80: invokeinterface com/facebook/AccessToken$AccessTokenRefreshCallback.OnTokenRefreshed:(Lcom/facebook/AccessToken;)V
                        //    85: return         
                        //    86: aload_0        
                        //    87: getfield        com/facebook/AccessTokenManager$4.val$permissionsCallSucceeded:Ljava/util/concurrent/atomic/AtomicBoolean;
                        //    90: invokevirtual   java/util/concurrent/atomic/AtomicBoolean.get:()Z
                        //    93: ifne            174
                        //    96: aload_0        
                        //    97: getfield        com/facebook/AccessTokenManager$4.val$refreshResult:Lcom/facebook/AccessTokenManager$RefreshResult;
                        //   100: getfield        com/facebook/AccessTokenManager$RefreshResult.accessToken:Ljava/lang/String;
                        //   103: ifnonnull       174
                        //   106: aload_0        
                        //   107: getfield        com/facebook/AccessTokenManager$4.val$refreshResult:Lcom/facebook/AccessTokenManager$RefreshResult;
                        //   110: getfield        com/facebook/AccessTokenManager$RefreshResult.expiresAt:I
                        //   113: ifne            174
                        //   116: aload_0        
                        //   117: getfield        com/facebook/AccessTokenManager$4.val$callback:Lcom/facebook/AccessToken$AccessTokenRefreshCallback;
                        //   120: ifnull          141
                        //   123: aload_0        
                        //   124: getfield        com/facebook/AccessTokenManager$4.val$callback:Lcom/facebook/AccessToken$AccessTokenRefreshCallback;
                        //   127: new             Lcom/facebook/FacebookException;
                        //   130: dup            
                        //   131: ldc             "Failed to refresh access token"
                        //   133: invokespecial   com/facebook/FacebookException.<init>:(Ljava/lang/String;)V
                        //   136: invokeinterface com/facebook/AccessToken$AccessTokenRefreshCallback.OnTokenRefreshFailed:(Lcom/facebook/FacebookException;)V
                        //   141: aload_0        
                        //   142: getfield        com/facebook/AccessTokenManager$4.this$0:Lcom/facebook/AccessTokenManager;
                        //   145: invokestatic    com/facebook/AccessTokenManager.access$200:(Lcom/facebook/AccessTokenManager;)Ljava/util/concurrent/atomic/AtomicBoolean;
                        //   148: iconst_0       
                        //   149: invokevirtual   java/util/concurrent/atomic/AtomicBoolean.set:(Z)V
                        //   152: aload_0        
                        //   153: getfield        com/facebook/AccessTokenManager$4.val$callback:Lcom/facebook/AccessToken$AccessTokenRefreshCallback;
                        //   156: ifnull          173
                        //   159: iconst_0       
                        //   160: ifeq            173
                        //   163: aload_0        
                        //   164: getfield        com/facebook/AccessTokenManager$4.val$callback:Lcom/facebook/AccessToken$AccessTokenRefreshCallback;
                        //   167: aconst_null    
                        //   168: invokeinterface com/facebook/AccessToken$AccessTokenRefreshCallback.OnTokenRefreshed:(Lcom/facebook/AccessToken;)V
                        //   173: return         
                        //   174: aload_0        
                        //   175: getfield        com/facebook/AccessTokenManager$4.val$refreshResult:Lcom/facebook/AccessTokenManager$RefreshResult;
                        //   178: getfield        com/facebook/AccessTokenManager$RefreshResult.accessToken:Ljava/lang/String;
                        //   181: ifnull          346
                        //   184: aload_0        
                        //   185: getfield        com/facebook/AccessTokenManager$4.val$refreshResult:Lcom/facebook/AccessTokenManager$RefreshResult;
                        //   188: getfield        com/facebook/AccessTokenManager$RefreshResult.accessToken:Ljava/lang/String;
                        //   191: astore_1       
                        //   192: aload_0        
                        //   193: getfield        com/facebook/AccessTokenManager$4.val$accessToken:Lcom/facebook/AccessToken;
                        //   196: invokevirtual   com/facebook/AccessToken.getApplicationId:()Ljava/lang/String;
                        //   199: astore          5
                        //   201: aload_0        
                        //   202: getfield        com/facebook/AccessTokenManager$4.val$accessToken:Lcom/facebook/AccessToken;
                        //   205: invokevirtual   com/facebook/AccessToken.getUserId:()Ljava/lang/String;
                        //   208: astore          6
                        //   210: aload_0        
                        //   211: getfield        com/facebook/AccessTokenManager$4.val$permissionsCallSucceeded:Ljava/util/concurrent/atomic/AtomicBoolean;
                        //   214: invokevirtual   java/util/concurrent/atomic/AtomicBoolean.get:()Z
                        //   217: ifeq            357
                        //   220: aload_0        
                        //   221: getfield        com/facebook/AccessTokenManager$4.val$permissions:Ljava/util/Set;
                        //   224: astore_2       
                        //   225: aload_0        
                        //   226: getfield        com/facebook/AccessTokenManager$4.val$permissionsCallSucceeded:Ljava/util/concurrent/atomic/AtomicBoolean;
                        //   229: invokevirtual   java/util/concurrent/atomic/AtomicBoolean.get:()Z
                        //   232: ifeq            368
                        //   235: aload_0        
                        //   236: getfield        com/facebook/AccessTokenManager$4.val$declinedPermissions:Ljava/util/Set;
                        //   239: astore_3       
                        //   240: aload_0        
                        //   241: getfield        com/facebook/AccessTokenManager$4.val$accessToken:Lcom/facebook/AccessToken;
                        //   244: invokevirtual   com/facebook/AccessToken.getSource:()Lcom/facebook/AccessTokenSource;
                        //   247: astore          7
                        //   249: aload_0        
                        //   250: getfield        com/facebook/AccessTokenManager$4.val$refreshResult:Lcom/facebook/AccessTokenManager$RefreshResult;
                        //   253: getfield        com/facebook/AccessTokenManager$RefreshResult.expiresAt:I
                        //   256: ifeq            379
                        //   259: new             Ljava/util/Date;
                        //   262: dup            
                        //   263: aload_0        
                        //   264: getfield        com/facebook/AccessTokenManager$4.val$refreshResult:Lcom/facebook/AccessTokenManager$RefreshResult;
                        //   267: getfield        com/facebook/AccessTokenManager$RefreshResult.expiresAt:I
                        //   270: i2l            
                        //   271: ldc2_w          1000
                        //   274: lmul           
                        //   275: invokespecial   java/util/Date.<init>:(J)V
                        //   278: astore          4
                        //   280: new             Lcom/facebook/AccessToken;
                        //   283: dup            
                        //   284: aload_1        
                        //   285: aload           5
                        //   287: aload           6
                        //   289: aload_2        
                        //   290: aload_3        
                        //   291: aload           7
                        //   293: aload           4
                        //   295: new             Ljava/util/Date;
                        //   298: dup            
                        //   299: invokespecial   java/util/Date.<init>:()V
                        //   302: invokespecial   com/facebook/AccessToken.<init>:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Collection;Ljava/util/Collection;Lcom/facebook/AccessTokenSource;Ljava/util/Date;Ljava/util/Date;)V
                        //   305: astore_1       
                        //   306: invokestatic    com/facebook/AccessTokenManager.getInstance:()Lcom/facebook/AccessTokenManager;
                        //   309: aload_1        
                        //   310: invokevirtual   com/facebook/AccessTokenManager.setCurrentAccessToken:(Lcom/facebook/AccessToken;)V
                        //   313: aload_0        
                        //   314: getfield        com/facebook/AccessTokenManager$4.this$0:Lcom/facebook/AccessTokenManager;
                        //   317: invokestatic    com/facebook/AccessTokenManager.access$200:(Lcom/facebook/AccessTokenManager;)Ljava/util/concurrent/atomic/AtomicBoolean;
                        //   320: iconst_0       
                        //   321: invokevirtual   java/util/concurrent/atomic/AtomicBoolean.set:(Z)V
                        //   324: aload_0        
                        //   325: getfield        com/facebook/AccessTokenManager$4.val$callback:Lcom/facebook/AccessToken$AccessTokenRefreshCallback;
                        //   328: ifnull          85
                        //   331: aload_1        
                        //   332: ifnull          85
                        //   335: aload_0        
                        //   336: getfield        com/facebook/AccessTokenManager$4.val$callback:Lcom/facebook/AccessToken$AccessTokenRefreshCallback;
                        //   339: aload_1        
                        //   340: invokeinterface com/facebook/AccessToken$AccessTokenRefreshCallback.OnTokenRefreshed:(Lcom/facebook/AccessToken;)V
                        //   345: return         
                        //   346: aload_0        
                        //   347: getfield        com/facebook/AccessTokenManager$4.val$accessToken:Lcom/facebook/AccessToken;
                        //   350: invokevirtual   com/facebook/AccessToken.getToken:()Ljava/lang/String;
                        //   353: astore_1       
                        //   354: goto            192
                        //   357: aload_0        
                        //   358: getfield        com/facebook/AccessTokenManager$4.val$accessToken:Lcom/facebook/AccessToken;
                        //   361: invokevirtual   com/facebook/AccessToken.getPermissions:()Ljava/util/Set;
                        //   364: astore_2       
                        //   365: goto            225
                        //   368: aload_0        
                        //   369: getfield        com/facebook/AccessTokenManager$4.val$accessToken:Lcom/facebook/AccessToken;
                        //   372: invokevirtual   com/facebook/AccessToken.getDeclinedPermissions:()Ljava/util/Set;
                        //   375: astore_3       
                        //   376: goto            240
                        //   379: aload_0        
                        //   380: getfield        com/facebook/AccessTokenManager$4.val$accessToken:Lcom/facebook/AccessToken;
                        //   383: invokevirtual   com/facebook/AccessToken.getExpires:()Ljava/util/Date;
                        //   386: astore          4
                        //   388: goto            280
                        //   391: astore_2       
                        //   392: aconst_null    
                        //   393: astore_1       
                        //   394: aload_0        
                        //   395: getfield        com/facebook/AccessTokenManager$4.this$0:Lcom/facebook/AccessTokenManager;
                        //   398: invokestatic    com/facebook/AccessTokenManager.access$200:(Lcom/facebook/AccessTokenManager;)Ljava/util/concurrent/atomic/AtomicBoolean;
                        //   401: iconst_0       
                        //   402: invokevirtual   java/util/concurrent/atomic/AtomicBoolean.set:(Z)V
                        //   405: aload_0        
                        //   406: getfield        com/facebook/AccessTokenManager$4.val$callback:Lcom/facebook/AccessToken$AccessTokenRefreshCallback;
                        //   409: ifnull          426
                        //   412: aload_1        
                        //   413: ifnull          426
                        //   416: aload_0        
                        //   417: getfield        com/facebook/AccessTokenManager$4.val$callback:Lcom/facebook/AccessToken$AccessTokenRefreshCallback;
                        //   420: aload_1        
                        //   421: invokeinterface com/facebook/AccessToken$AccessTokenRefreshCallback.OnTokenRefreshed:(Lcom/facebook/AccessToken;)V
                        //   426: aload_2        
                        //   427: athrow         
                        //   428: astore_2       
                        //   429: goto            394
                        //    Exceptions:
                        //  Try           Handler
                        //  Start  End    Start  End    Type
                        //  -----  -----  -----  -----  ----
                        //  0      28     391    394    Any
                        //  28     53     391    394    Any
                        //  86     141    391    394    Any
                        //  174    192    391    394    Any
                        //  192    225    391    394    Any
                        //  225    240    391    394    Any
                        //  240    280    391    394    Any
                        //  280    306    391    394    Any
                        //  306    313    428    432    Any
                        //  346    354    391    394    Any
                        //  357    365    391    394    Any
                        //  368    376    391    394    Any
                        //  379    388    391    394    Any
                        // 
                        // The error that occurred was:
                        // 
                        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0346:
                        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
                        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
                        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
                        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
                        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
                        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
                        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
                        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
                        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
                        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1164)
                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1009)
                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:440)
                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:441)
                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
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
                });
                graphRequestBatch.executeAsync();
                return;
            }
            if (accessTokenRefreshCallback != null) {
                accessTokenRefreshCallback.OnTokenRefreshFailed(new FacebookException("Refresh already in progress"));
            }
        }
    }
    
    private void sendCurrentAccessTokenChangedBroadcastIntent(final AccessToken accessToken, final AccessToken accessToken2) {
        final Intent intent = new Intent(FacebookSdk.getApplicationContext(), (Class)CurrentAccessTokenExpirationBroadcastReceiver.class);
        intent.setAction("com.facebook.sdk.ACTION_CURRENT_ACCESS_TOKEN_CHANGED");
        intent.putExtra("com.facebook.sdk.EXTRA_OLD_ACCESS_TOKEN", (Parcelable)accessToken);
        intent.putExtra("com.facebook.sdk.EXTRA_NEW_ACCESS_TOKEN", (Parcelable)accessToken2);
        this.localBroadcastManager.sendBroadcast(intent);
    }
    
    private void setCurrentAccessToken(final AccessToken currentAccessToken, final boolean b) {
        final AccessToken currentAccessToken2 = this.currentAccessToken;
        this.currentAccessToken = currentAccessToken;
        this.tokenRefreshInProgress.set(false);
        this.lastAttemptedTokenExtendDate = new Date(0L);
        if (b) {
            if (currentAccessToken != null) {
                this.accessTokenCache.save(currentAccessToken);
            }
            else {
                this.accessTokenCache.clear();
                Utility.clearFacebookCookies(FacebookSdk.getApplicationContext());
            }
        }
        if (!Utility.areObjectsEqual(currentAccessToken2, currentAccessToken)) {
            this.sendCurrentAccessTokenChangedBroadcastIntent(currentAccessToken2, currentAccessToken);
            this.setTokenExpirationBroadcastAlarm();
        }
    }
    
    private void setTokenExpirationBroadcastAlarm() {
        final Context applicationContext = FacebookSdk.getApplicationContext();
        final AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
        final AlarmManager alarmManager = (AlarmManager)applicationContext.getSystemService("alarm");
        if (!AccessToken.isCurrentAccessTokenActive() || currentAccessToken.getExpires() == null || alarmManager == null) {
            return;
        }
        final Intent intent = new Intent(applicationContext, (Class)CurrentAccessTokenExpirationBroadcastReceiver.class);
        intent.setAction("com.facebook.sdk.ACTION_CURRENT_ACCESS_TOKEN_CHANGED");
        alarmManager.set(1, currentAccessToken.getExpires().getTime(), PendingIntent.getBroadcast(applicationContext, 0, intent, 0));
    }
    
    private boolean shouldExtendAccessToken() {
        if (this.currentAccessToken != null) {
            final Long value = new Date().getTime();
            if (this.currentAccessToken.getSource().canExtendToken() && value - this.lastAttemptedTokenExtendDate.getTime() > 3600000L && value - this.currentAccessToken.getLastRefresh().getTime() > 86400000L) {
                return true;
            }
        }
        return false;
    }
    
    void currentAccessTokenChanged() {
        this.sendCurrentAccessTokenChangedBroadcastIntent(this.currentAccessToken, this.currentAccessToken);
    }
    
    void extendAccessTokenIfNeeded() {
        if (!this.shouldExtendAccessToken()) {
            return;
        }
        this.refreshCurrentAccessToken(null);
    }
    
    AccessToken getCurrentAccessToken() {
        return this.currentAccessToken;
    }
    
    boolean loadCurrentAccessToken() {
        boolean b = false;
        final AccessToken load = this.accessTokenCache.load();
        if (load != null) {
            this.setCurrentAccessToken(load, false);
            b = true;
        }
        return b;
    }
    
    void refreshCurrentAccessToken(final AccessToken.AccessTokenRefreshCallback accessTokenRefreshCallback) {
        if (Looper.getMainLooper().equals(Looper.myLooper())) {
            this.refreshCurrentAccessTokenImpl(accessTokenRefreshCallback);
            return;
        }
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable() {
            @Override
            public void run() {
                AccessTokenManager.this.refreshCurrentAccessTokenImpl(accessTokenRefreshCallback);
            }
        });
    }
    
    void setCurrentAccessToken(final AccessToken accessToken) {
        this.setCurrentAccessToken(accessToken, true);
    }
    
    private static class RefreshResult
    {
        public String accessToken;
        public int expiresAt;
    }
}
