// 
// Decompiled by Procyon v0.5.34
// 

package com.google.games.bridge;

import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes;
import com.google.android.gms.auth.api.Auth;
import android.content.Intent;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import android.support.annotation.NonNull;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.games.Games;
import com.google.android.gms.common.api.PendingResult;
import android.view.View;
import android.content.Context;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension;
import com.google.android.gms.games.Games$GamesOptions;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions$Builder;
import android.util.Log;
import android.app.Activity;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import android.app.Fragment;

public class TokenFragment extends Fragment
{
    private static final String FRAGMENT_TAG = "gpg.AuthTokenSupport";
    private static final int RC_ACCT = 9002;
    private static final String TAG = "TokenFragment";
    private static TokenFragment helperFragment;
    private static final Object lock;
    private static boolean mStartUpSignInCheckPerformed;
    private static TokenRequest pendingTokenRequest;
    private GoogleSignInClient mGoogleSignInClient;
    
    static {
        lock = new Object();
        TokenFragment.mStartUpSignInCheckPerformed = false;
    }
    
    private boolean buildClient(final Activity activity, final TokenRequest tokenRequest) {
        Log.d("TokenFragment", "Building client for: " + tokenRequest);
        final GoogleSignInOptions$Builder googleSignInOptions$Builder = new GoogleSignInOptions$Builder();
        if (tokenRequest.doAuthCode) {
            if (tokenRequest.getWebClientId().isEmpty() || tokenRequest.getWebClientId().equals("__WEB_CLIENTID__")) {
                Log.e("TokenFragment", "Web client ID is needed for Auth Code");
                tokenRequest.setResult(10);
                return false;
            }
            googleSignInOptions$Builder.requestServerAuthCode(tokenRequest.getWebClientId(), tokenRequest.getForceRefresh());
        }
        if (tokenRequest.doEmail) {
            googleSignInOptions$Builder.requestEmail();
        }
        if (tokenRequest.doIdToken) {
            if (tokenRequest.getWebClientId().isEmpty() || tokenRequest.getWebClientId().equals("__WEB_CLIENTID__")) {
                Log.e("TokenFragment", "Web client ID is needed for ID Token");
                tokenRequest.setResult(10);
                return false;
            }
            googleSignInOptions$Builder.requestIdToken(tokenRequest.getWebClientId());
        }
        if (tokenRequest.scopes != null) {
            final Scope[] access$000 = tokenRequest.scopes;
            for (int length = access$000.length, i = 0; i < length; ++i) {
                googleSignInOptions$Builder.requestScopes(access$000[i], new Scope[0]);
            }
        }
        if (tokenRequest.hidePopups) {
            Log.d("TokenFragment", "hiding popup views for games API");
            googleSignInOptions$Builder.addExtension((GoogleSignInOptionsExtension)Games$GamesOptions.builder().setShowConnectingPopup(false).build());
        }
        if (tokenRequest.accountName != null && !tokenRequest.accountName.isEmpty()) {
            googleSignInOptions$Builder.setAccountName(tokenRequest.accountName);
        }
        this.mGoogleSignInClient = GoogleSignIn.getClient(activity, googleSignInOptions$Builder.build());
        return true;
    }
    
    public static boolean checkGooglePlayServicesAvailable() {
        GooglePlayServicesUtil.isGooglePlayServicesAvailable((Context)null);
        return false;
    }
    
    public static View createInvisibleView(final Activity activity) {
        final View view = new View((Context)activity);
        view.setVisibility(4);
        view.setClickable(false);
        return view;
    }
    
    public static PendingResult fetchToken(final Activity p0, final boolean p1, final boolean p2, final boolean p3, final boolean p4, final String p5, final boolean p6, final String[] p7, final boolean p8, final String p9) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: iload_1        
        //     5: iload_2        
        //     6: iload_3        
        //     7: iload           4
        //     9: aload           5
        //    11: iload           6
        //    13: aload           7
        //    15: iload           8
        //    17: aload           9
        //    19: invokespecial   com/google/games/bridge/TokenFragment$TokenRequest.<init>:(ZZZZLjava/lang/String;Z[Ljava/lang/String;ZLjava/lang/String;)V
        //    22: astore          5
        //    24: iconst_0       
        //    25: istore          10
        //    27: getstatic       com/google/games/bridge/TokenFragment.lock:Ljava/lang/Object;
        //    30: astore          7
        //    32: aload           7
        //    34: monitorenter   
        //    35: getstatic       com/google/games/bridge/TokenFragment.pendingTokenRequest:Lcom/google/games/bridge/TokenFragment$TokenRequest;
        //    38: ifnonnull       49
        //    41: aload           5
        //    43: putstatic       com/google/games/bridge/TokenFragment.pendingTokenRequest:Lcom/google/games/bridge/TokenFragment$TokenRequest;
        //    46: iconst_1       
        //    47: istore          10
        //    49: aload           7
        //    51: monitorexit    
        //    52: iload           10
        //    54: ifne            129
        //    57: ldc             "TokenFragment"
        //    59: new             Ljava/lang/StringBuilder;
        //    62: dup            
        //    63: invokespecial   java/lang/StringBuilder.<init>:()V
        //    66: ldc             "Already a pending token request (requested == ): "
        //    68: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    71: aload           5
        //    73: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    76: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    79: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //    82: pop            
        //    83: ldc             "TokenFragment"
        //    85: new             Ljava/lang/StringBuilder;
        //    88: dup            
        //    89: invokespecial   java/lang/StringBuilder.<init>:()V
        //    92: ldc             "Already a pending token request: "
        //    94: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    97: getstatic       com/google/games/bridge/TokenFragment.pendingTokenRequest:Lcom/google/games/bridge/TokenFragment$TokenRequest;
        //   100: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   103: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   106: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   109: pop            
        //   110: aload           5
        //   112: bipush          10
        //   114: invokevirtual   com/google/games/bridge/TokenFragment$TokenRequest.setResult:(I)V
        //   117: aload           5
        //   119: invokevirtual   com/google/games/bridge/TokenFragment$TokenRequest.getPendingResponse:()Lcom/google/android/gms/common/api/PendingResult;
        //   122: areturn        
        //   123: astore_0       
        //   124: aload           7
        //   126: monitorexit    
        //   127: aload_0        
        //   128: athrow         
        //   129: aload_0        
        //   130: invokevirtual   android/app/Activity.getFragmentManager:()Landroid/app/FragmentManager;
        //   133: ldc             "gpg.AuthTokenSupport"
        //   135: invokevirtual   android/app/FragmentManager.findFragmentByTag:(Ljava/lang/String;)Landroid/app/Fragment;
        //   138: checkcast       Lcom/google/games/bridge/TokenFragment;
        //   141: astore          7
        //   143: aload           7
        //   145: ifnonnull       253
        //   148: ldc             "TokenFragment"
        //   150: ldc             "Creating fragment"
        //   152: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   155: pop            
        //   156: new             Lcom/google/games/bridge/TokenFragment;
        //   159: dup            
        //   160: invokespecial   com/google/games/bridge/TokenFragment.<init>:()V
        //   163: astore          7
        //   165: aload_0        
        //   166: invokevirtual   android/app/Activity.getFragmentManager:()Landroid/app/FragmentManager;
        //   169: invokevirtual   android/app/FragmentManager.beginTransaction:()Landroid/app/FragmentTransaction;
        //   172: astore_0       
        //   173: aload_0        
        //   174: aload           7
        //   176: ldc             "gpg.AuthTokenSupport"
        //   178: invokevirtual   android/app/FragmentTransaction.add:(Landroid/app/Fragment;Ljava/lang/String;)Landroid/app/FragmentTransaction;
        //   181: pop            
        //   182: aload_0        
        //   183: invokevirtual   android/app/FragmentTransaction.commit:()I
        //   186: pop            
        //   187: aload           5
        //   189: invokevirtual   com/google/games/bridge/TokenFragment$TokenRequest.getPendingResponse:()Lcom/google/android/gms/common/api/PendingResult;
        //   192: areturn        
        //   193: astore_0       
        //   194: ldc             "TokenFragment"
        //   196: new             Ljava/lang/StringBuilder;
        //   199: dup            
        //   200: invokespecial   java/lang/StringBuilder.<init>:()V
        //   203: ldc_w           "Cannot launch token fragment:"
        //   206: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   209: aload_0        
        //   210: invokevirtual   java/lang/Throwable.getMessage:()Ljava/lang/String;
        //   213: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   216: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   219: aload_0        
        //   220: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   223: pop            
        //   224: aload           5
        //   226: bipush          13
        //   228: invokevirtual   com/google/games/bridge/TokenFragment$TokenRequest.setResult:(I)V
        //   231: getstatic       com/google/games/bridge/TokenFragment.lock:Ljava/lang/Object;
        //   234: astore_0       
        //   235: aload_0        
        //   236: monitorenter   
        //   237: aconst_null    
        //   238: putstatic       com/google/games/bridge/TokenFragment.pendingTokenRequest:Lcom/google/games/bridge/TokenFragment$TokenRequest;
        //   241: aload_0        
        //   242: monitorexit    
        //   243: goto            187
        //   246: astore          5
        //   248: aload_0        
        //   249: monitorexit    
        //   250: aload           5
        //   252: athrow         
        //   253: ldc             "TokenFragment"
        //   255: ldc_w           "Fragment exists.. calling processRequests"
        //   258: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   261: pop            
        //   262: aload           7
        //   264: invokespecial   com/google/games/bridge/TokenFragment.processRequest:()V
        //   267: goto            187
        //   270: astore_0       
        //   271: goto            194
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  35     46     123    129    Any
        //  49     52     123    129    Any
        //  124    127    123    129    Any
        //  148    165    193    194    Ljava/lang/Throwable;
        //  165    187    270    274    Ljava/lang/Throwable;
        //  237    243    246    253    Any
        //  248    250    246    253    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0187:
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
    
    public static PendingResult getAnotherAuthCode(final Activity activity, final boolean b, final String s) {
        return fetchToken(activity, !b, true, false, false, s, false, null, true, null);
    }
    
    private void onSignedIn(final int result, final GoogleSignInAccount googleSignInAccount) {
        synchronized (TokenFragment.lock) {
            final TokenRequest pendingTokenRequest = TokenFragment.pendingTokenRequest;
            TokenFragment.pendingTokenRequest = null;
            // monitorexit(TokenFragment.lock)
            if (pendingTokenRequest != null) {
                if (googleSignInAccount != null) {
                    pendingTokenRequest.setAuthCode(googleSignInAccount.getServerAuthCode());
                    pendingTokenRequest.setEmail(googleSignInAccount.getEmail());
                    pendingTokenRequest.setIdToken(googleSignInAccount.getIdToken());
                }
                if (result != 0) {
                    Log.e("TokenFragment", "Setting result error status code to: " + result);
                }
                pendingTokenRequest.setResult(result);
            }
        }
    }
    
    private void processRequest() {
        synchronized (TokenFragment.lock) {
            final TokenRequest pendingTokenRequest = TokenFragment.pendingTokenRequest;
            // monitorexit(TokenFragment.lock)
            if (pendingTokenRequest == null) {
                return;
            }
        }
        final TokenRequest tokenRequest;
        if (this.buildClient(this.getActivity(), tokenRequest)) {
            this.signIn();
        }
        else {
            synchronized (TokenFragment.lock) {
                TokenFragment.pendingTokenRequest = null;
            }
        }
        Log.d("TokenFragment", "Done with processRequest, result is pending.");
    }
    
    private void reset() {
        if (this.mGoogleSignInClient != null) {
            this.mGoogleSignInClient.signOut();
            this.mGoogleSignInClient = null;
        }
    }
    
    private void signIn() {
        Log.d("TokenFragment", "signIn");
        Object o = TokenFragment.lock;
        Label_0097: {
            synchronized (o) {
                final TokenRequest pendingTokenRequest = TokenFragment.pendingTokenRequest;
                // monitorexit(o)
                if (this.mGoogleSignInClient != null && pendingTokenRequest != null) {
                    if (!pendingTokenRequest.canReuseAccount()) {
                        break Label_0097;
                    }
                    o = GoogleSignIn.getLastSignedInAccount((Context)this.getActivity());
                    if (!GoogleSignIn.hasPermissions((GoogleSignInAccount)o, pendingTokenRequest.scopes)) {
                        break Label_0097;
                    }
                    Log.d("TokenFragment", "Checking the last signed-in account if it can be used.");
                    Games.getGamesClient(this.getActivity(), (GoogleSignInAccount)o).getAppId().addOnCompleteListener((OnCompleteListener)new OnCompleteListener<String>() {
                        public void onComplete(@NonNull final Task<String> task) {
                            if (task.isSuccessful()) {
                                Log.d("TokenFragment", "Signed-in with the last signed-in account.");
                                TokenFragment.this.onSignedIn(0, o);
                                return;
                            }
                            TokenFragment.this.mGoogleSignInClient.signOut().addOnCompleteListener((OnCompleteListener)new OnCompleteListener<Void>() {
                                public void onComplete(@NonNull final Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("TokenFragment", "Can't reuse the last signed-in account. Second attempt after sign out.");
                                        TokenFragment.this.signIn();
                                        return;
                                    }
                                    Log.e("TokenFragment", "Can't reuse the last signed-in account and sign out failed.");
                                    TokenFragment.this.onSignedIn(4, null);
                                }
                            });
                        }
                    });
                }
                return;
            }
        }
        Log.d("TokenFragment", "mGoogleSignInClient.silentSignIn");
        final TokenRequest tokenRequest;
        this.mGoogleSignInClient.silentSignIn().addOnSuccessListener(this.getActivity(), (OnSuccessListener)new OnSuccessListener<GoogleSignInAccount>() {
            public void onSuccess(final GoogleSignInAccount googleSignInAccount) {
                Log.d("TokenFragment", "silentSignIn.onSuccess");
                TokenFragment.this.onSignedIn(0, googleSignInAccount);
            }
        }).addOnFailureListener(this.getActivity(), (OnFailureListener)new OnFailureListener() {
            public void onFailure(final Exception ex) {
                Log.d("TokenFragment", "silentSignIn.onFailure");
                int statusCode = 8;
                if (ex instanceof ApiException) {
                    statusCode = ((ApiException)ex).getStatusCode();
                }
                if (statusCode != 4 && statusCode != 8) {
                    Log.e("TokenFragment", "Sign-in failed with status code: " + statusCode);
                    TokenFragment.this.onSignedIn(statusCode, null);
                    return;
                }
                if (!tokenRequest.getSilent()) {
                    TokenFragment.this.startActivityForResult(TokenFragment.this.mGoogleSignInClient.getSignInIntent(), 9002);
                    return;
                }
                Log.i("TokenFragment", "Sign-in failed. Run in silent mode and UI sign-in required.");
                TokenFragment.this.onSignedIn(4, null);
            }
        });
    }
    
    public static void signOut(final Activity activity) {
        final TokenFragment tokenFragment = (TokenFragment)activity.getFragmentManager().findFragmentByTag("gpg.AuthTokenSupport");
        if (tokenFragment != null) {
            tokenFragment.reset();
        }
        synchronized (TokenFragment.lock) {
            TokenFragment.pendingTokenRequest = null;
        }
    }
    
    public void onActivityResult(final int n, final int n2, final Intent intent) {
        if (n != 9002) {
            super.onActivityResult(n, n2, intent);
            return;
        }
        final GoogleSignInResult signInResultFromIntent = Auth.GoogleSignInApi.getSignInResultFromIntent(intent);
        if (signInResultFromIntent != null && signInResultFromIntent.isSuccess()) {
            this.onSignedIn(signInResultFromIntent.getStatus().getStatusCode(), signInResultFromIntent.getSignInAccount());
            return;
        }
        if (n2 == 0) {
            this.onSignedIn(16, null);
            return;
        }
        if (signInResultFromIntent != null) {
            Log.e("TokenFragment", "GoogleSignInResult error status code: " + signInResultFromIntent.getStatus());
            this.onSignedIn(signInResultFromIntent.getStatus().getStatusCode(), null);
            return;
        }
        Log.e("TokenFragment", "Google SignIn Result is null, resultCode is " + n2 + "(" + GoogleSignInStatusCodes.getStatusCodeString(n2) + ")");
        this.onSignedIn(13, null);
    }
    
    public void onResume() {
        Log.d("TokenFragment", "onResume called");
        super.onResume();
        if (TokenFragment.helperFragment == null) {
            TokenFragment.helperFragment = this;
        }
        this.processRequest();
    }
    
    private static class TokenRequest
    {
        private String accountName;
        private boolean doAuthCode;
        private boolean doEmail;
        private boolean doIdToken;
        private boolean forceRefresh;
        private boolean hidePopups;
        private TokenPendingResult pendingResponse;
        private Scope[] scopes;
        private boolean silent;
        private String webClientId;
        
        public TokenRequest(final boolean silent, final boolean doAuthCode, final boolean doEmail, final boolean doIdToken, final String webClientId, final boolean forceRefresh, final String[] array, final boolean hidePopups, final String accountName) {
            this.pendingResponse = new TokenPendingResult();
            this.silent = silent;
            this.doAuthCode = doAuthCode;
            this.doEmail = doEmail;
            this.doIdToken = doIdToken;
            this.webClientId = webClientId;
            this.forceRefresh = forceRefresh;
            if (array != null && array.length > 0) {
                this.scopes = new Scope[array.length];
                for (int i = 0; i < array.length; ++i) {
                    this.scopes[i] = new Scope(array[i]);
                }
            }
            else {
                this.scopes = null;
            }
            this.hidePopups = hidePopups;
            this.accountName = accountName;
        }
        
        public boolean canReuseAccount() {
            return !this.doAuthCode && !this.doIdToken;
        }
        
        public void cancel() {
            this.pendingResponse.cancel();
        }
        
        public String getAuthCode() {
            return this.pendingResponse.result.getAuthCode();
        }
        
        public String getEmail() {
            return this.pendingResponse.result.getEmail();
        }
        
        public boolean getForceRefresh() {
            return this.forceRefresh;
        }
        
        public String getIdToken() {
            return this.pendingResponse.result.getIdToken();
        }
        
        public PendingResult<TokenResult> getPendingResponse() {
            return this.pendingResponse;
        }
        
        public boolean getSilent() {
            return this.silent;
        }
        
        public String getWebClientId() {
            if (this.webClientId == null) {
                return "";
            }
            return this.webClientId;
        }
        
        public void setAuthCode(final String authCode) {
            this.pendingResponse.setAuthCode(authCode);
        }
        
        public void setEmail(final String email) {
            this.pendingResponse.setEmail(email);
        }
        
        public void setIdToken(final String idToken) {
            this.pendingResponse.setIdToken(idToken);
        }
        
        public void setResult(final int status) {
            this.pendingResponse.setStatus(status);
        }
        
        @Override
        public String toString() {
            return Integer.toHexString(this.hashCode()) + " (a:" + this.doAuthCode + " e:" + this.doEmail + " i:" + this.doIdToken + " wc: " + this.webClientId + " f: " + this.forceRefresh + ")";
        }
    }
}
