// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import org.json.JSONException;
import java.util.Set;
import org.json.JSONObject;

class UserStatePushSynchronizer extends UserStateSynchronizer
{
    private static boolean serverSuccess;
    
    @Override
    protected void addOnSessionOrCreateExtras(final JSONObject jsonObject) {
    }
    
    @Override
    protected void fireEventsForUpdateFailure(final JSONObject jsonObject) {
        if (jsonObject.has("email")) {
            OneSignal.fireEmailUpdateFailure();
        }
    }
    
    @Override
    protected String getId() {
        return OneSignal.getUserId();
    }
    
    @Override
    boolean getSubscribed() {
        return this.getToSyncUserState().isSubscribed();
    }
    
    @Override
    GetTagsResult getTags(final boolean b) {
        if (b) {
            OneSignalRestClient.getSync("players/" + OneSignal.getUserId() + "?app_id=" + OneSignal.getSavedAppId(), (OneSignalRestClient.ResponseHandler)new OneSignalRestClient.ResponseHandler() {
                @Override
                void onSuccess(final String s) {
                    UserStatePushSynchronizer.serverSuccess = true;
                    try {
                        final JSONObject jsonObject = new JSONObject(s);
                        if (jsonObject.has("tags")) {
                            synchronized (UserStatePushSynchronizer.this.syncLock) {
                                final JSONObject generateJsonDiff = UserStatePushSynchronizer.this.generateJsonDiff(UserStatePushSynchronizer.this.currentUserState.syncValues.optJSONObject("tags"), UserStatePushSynchronizer.this.getToSyncUserState().syncValues.optJSONObject("tags"), null, null);
                                UserStatePushSynchronizer.this.currentUserState.syncValues.put("tags", (Object)jsonObject.optJSONObject("tags"));
                                UserStatePushSynchronizer.this.currentUserState.persistState();
                                UserStatePushSynchronizer.this.getToSyncUserState().mergeTags(jsonObject, generateJsonDiff);
                                UserStatePushSynchronizer.this.getToSyncUserState().persistState();
                            }
                        }
                    }
                    catch (JSONException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
        synchronized (this.syncLock) {
            return new GetTagsResult(UserStatePushSynchronizer.serverSuccess, JSONUtils.getJSONObjectWithoutBlankValues(this.toSyncUserState.syncValues, "tags"));
        }
    }
    
    @Override
    public boolean getUserSubscribePreference() {
        return this.getToSyncUserState().dependValues.optBoolean("userSubscribePref", true);
    }
    
    @Override
    void logoutEmail() {
        try {
            this.getUserStateForModification().dependValues.put("logoutEmail", true);
        }
        catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    protected UserState newUserState(final String s, final boolean b) {
        return new UserStatePush(s, b);
    }
    
    @Override
    protected void onSuccessfulSync(final JSONObject jsonObject) {
        if (jsonObject.has("email")) {
            OneSignal.fireEmailUpdateSuccess();
        }
        if (jsonObject.has("identifier")) {
            OneSignal.fireIdsAvailableCallback();
        }
    }
    
    @Override
    protected void scheduleSyncToServer() {
        this.getNetworkHandlerThread(0).runNewJobDelayed();
    }
    
    void setEmail(final String s, final String s2) {
        try {
            final UserState userStateForModification = this.getUserStateForModification();
            userStateForModification.dependValues.put("email_auth_hash", (Object)s2);
            final JSONObject syncValues = userStateForModification.syncValues;
            this.generateJsonDiff(syncValues, new JSONObject().put("email", (Object)s), syncValues, null);
        }
        catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void setPermission(final boolean b) {
        try {
            this.getUserStateForModification().dependValues.put("androidPermission", b);
        }
        catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    void setSubscription(final boolean b) {
        try {
            this.getUserStateForModification().dependValues.put("userSubscribePref", b);
        }
        catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    void updateIdDependents(final String s) {
        OneSignal.updateUserIdDependents(s);
    }
    
    @Override
    void updateState(final JSONObject p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     4: invokespecial   org/json/JSONObject.<init>:()V
        //     7: astore_2       
        //     8: aload_2        
        //     9: ldc             "identifier"
        //    11: aload_1        
        //    12: ldc             "identifier"
        //    14: aconst_null    
        //    15: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    18: invokevirtual   org/json/JSONObject.putOpt:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    21: pop            
        //    22: aload_1        
        //    23: ldc             "device_type"
        //    25: invokevirtual   org/json/JSONObject.has:(Ljava/lang/String;)Z
        //    28: ifeq            44
        //    31: aload_2        
        //    32: ldc             "device_type"
        //    34: aload_1        
        //    35: ldc             "device_type"
        //    37: invokevirtual   org/json/JSONObject.optInt:(Ljava/lang/String;)I
        //    40: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //    43: pop            
        //    44: aload_2        
        //    45: ldc             "parent_player_id"
        //    47: aload_1        
        //    48: ldc             "parent_player_id"
        //    50: aconst_null    
        //    51: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    54: invokevirtual   org/json/JSONObject.putOpt:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    57: pop            
        //    58: aload_0        
        //    59: invokevirtual   com/onesignal/UserStatePushSynchronizer.getUserStateForModification:()Lcom/onesignal/UserState;
        //    62: getfield        com/onesignal/UserState.syncValues:Lorg/json/JSONObject;
        //    65: astore_3       
        //    66: aload_0        
        //    67: aload_3        
        //    68: aload_2        
        //    69: aload_3        
        //    70: aconst_null    
        //    71: invokevirtual   com/onesignal/UserStatePushSynchronizer.generateJsonDiff:(Lorg/json/JSONObject;Lorg/json/JSONObject;Lorg/json/JSONObject;Ljava/util/Set;)Lorg/json/JSONObject;
        //    74: pop            
        //    75: new             Lorg/json/JSONObject;
        //    78: dup            
        //    79: invokespecial   org/json/JSONObject.<init>:()V
        //    82: astore_2       
        //    83: aload_1        
        //    84: ldc             "subscribableStatus"
        //    86: invokevirtual   org/json/JSONObject.has:(Ljava/lang/String;)Z
        //    89: ifeq            105
        //    92: aload_2        
        //    93: ldc             "subscribableStatus"
        //    95: aload_1        
        //    96: ldc             "subscribableStatus"
        //    98: invokevirtual   org/json/JSONObject.optInt:(Ljava/lang/String;)I
        //   101: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   104: pop            
        //   105: aload_1        
        //   106: ldc             "androidPermission"
        //   108: invokevirtual   org/json/JSONObject.has:(Ljava/lang/String;)Z
        //   111: ifeq            127
        //   114: aload_2        
        //   115: ldc             "androidPermission"
        //   117: aload_1        
        //   118: ldc             "androidPermission"
        //   120: invokevirtual   org/json/JSONObject.optBoolean:(Ljava/lang/String;)Z
        //   123: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Z)Lorg/json/JSONObject;
        //   126: pop            
        //   127: aload_0        
        //   128: invokevirtual   com/onesignal/UserStatePushSynchronizer.getUserStateForModification:()Lcom/onesignal/UserState;
        //   131: getfield        com/onesignal/UserState.dependValues:Lorg/json/JSONObject;
        //   134: astore_1       
        //   135: aload_0        
        //   136: aload_1        
        //   137: aload_2        
        //   138: aload_1        
        //   139: aconst_null    
        //   140: invokevirtual   com/onesignal/UserStatePushSynchronizer.generateJsonDiff:(Lorg/json/JSONObject;Lorg/json/JSONObject;Lorg/json/JSONObject;Ljava/util/Set;)Lorg/json/JSONObject;
        //   143: pop            
        //   144: return         
        //   145: astore_2       
        //   146: aload_2        
        //   147: invokevirtual   org/json/JSONException.printStackTrace:()V
        //   150: goto            75
        //   153: astore_1       
        //   154: aload_1        
        //   155: invokevirtual   org/json/JSONException.printStackTrace:()V
        //   158: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  0      44     145    153    Lorg/json/JSONException;
        //  44     75     145    153    Lorg/json/JSONException;
        //  75     105    153    159    Lorg/json/JSONException;
        //  105    127    153    159    Lorg/json/JSONException;
        //  127    144    153    159    Lorg/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0075:
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
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
