package com.google.games.bridge;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions.Builder;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Games.GamesOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class TokenFragment extends Fragment {
    private static final String FRAGMENT_TAG = "gpg.AuthTokenSupport";
    private static final int RC_ACCT = 9002;
    private static final String TAG = "TokenFragment";
    private static TokenFragment helperFragment;
    private static final Object lock = new Object();
    private static boolean mStartUpSignInCheckPerformed = false;
    private static TokenRequest pendingTokenRequest;
    private GoogleSignInClient mGoogleSignInClient;

    /* renamed from: com.google.games.bridge.TokenFragment$3 */
    class C08143 implements OnSuccessListener<GoogleSignInAccount> {
        C08143() {
        }

        public void onSuccess(GoogleSignInAccount result) {
            Log.d(TokenFragment.TAG, "silentSignIn.onSuccess");
            TokenFragment.this.onSignedIn(0, result);
        }
    }

    private static class TokenRequest {
        private String accountName;
        private boolean doAuthCode;
        private boolean doEmail;
        private boolean doIdToken;
        private boolean forceRefresh;
        private boolean hidePopups;
        private TokenPendingResult pendingResponse = new TokenPendingResult();
        private Scope[] scopes;
        private boolean silent;
        private String webClientId;

        public TokenRequest(boolean silent, boolean fetchAuthCode, boolean fetchEmail, boolean fetchIdToken, String webClientId, boolean forceRefresh, String[] oAuthScopes, boolean hidePopups, String accountName) {
            this.silent = silent;
            this.doAuthCode = fetchAuthCode;
            this.doEmail = fetchEmail;
            this.doIdToken = fetchIdToken;
            this.webClientId = webClientId;
            this.forceRefresh = forceRefresh;
            if (oAuthScopes == null || oAuthScopes.length <= 0) {
                this.scopes = null;
            } else {
                this.scopes = new Scope[oAuthScopes.length];
                for (int i = 0; i < oAuthScopes.length; i++) {
                    this.scopes[i] = new Scope(oAuthScopes[i]);
                }
            }
            this.hidePopups = hidePopups;
            this.accountName = accountName;
        }

        public boolean canReuseAccount() {
            return (this.doAuthCode || this.doIdToken) ? false : true;
        }

        public PendingResult<TokenResult> getPendingResponse() {
            return this.pendingResponse;
        }

        public boolean getSilent() {
            return this.silent;
        }

        public void setResult(int code) {
            this.pendingResponse.setStatus(code);
        }

        public void setEmail(String email) {
            this.pendingResponse.setEmail(email);
        }

        public void cancel() {
            this.pendingResponse.cancel();
        }

        public void setAuthCode(String authCode) {
            this.pendingResponse.setAuthCode(authCode);
        }

        public void setIdToken(String idToken) {
            this.pendingResponse.setIdToken(idToken);
        }

        public String getEmail() {
            return this.pendingResponse.result.getEmail();
        }

        public String getIdToken() {
            return this.pendingResponse.result.getIdToken();
        }

        public String getAuthCode() {
            return this.pendingResponse.result.getAuthCode();
        }

        public String toString() {
            return Integer.toHexString(hashCode()) + " (a:" + this.doAuthCode + " e:" + this.doEmail + " i:" + this.doIdToken + " wc: " + this.webClientId + " f: " + this.forceRefresh + ")";
        }

        public String getWebClientId() {
            return this.webClientId == null ? "" : this.webClientId;
        }

        public boolean getForceRefresh() {
            return this.forceRefresh;
        }
    }

    public static PendingResult fetchToken(Activity parentActivity, boolean silent, boolean requestAuthCode, boolean requestEmail, boolean requestIdToken, String webClientId, boolean forceRefreshToken, String[] additionalScopes, boolean hidePopups, String accountName) {
        Throwable th;
        TokenRequest request = new TokenRequest(silent, requestAuthCode, requestEmail, requestIdToken, webClientId, forceRefreshToken, additionalScopes, hidePopups, accountName);
        boolean ok = false;
        synchronized (lock) {
            if (pendingTokenRequest == null) {
                pendingTokenRequest = request;
                ok = true;
            }
        }
        if (ok) {
            TokenFragment fragment = (TokenFragment) parentActivity.getFragmentManager().findFragmentByTag(FRAGMENT_TAG);
            if (fragment == null) {
                try {
                    Log.d(TAG, "Creating fragment");
                    TokenFragment fragment2 = new TokenFragment();
                    try {
                        FragmentTransaction trans = parentActivity.getFragmentManager().beginTransaction();
                        trans.add(fragment2, FRAGMENT_TAG);
                        trans.commit();
                        fragment = fragment2;
                    } catch (Throwable th2) {
                        th = th2;
                        fragment = fragment2;
                        Log.e(TAG, "Cannot launch token fragment:" + th.getMessage(), th);
                        request.setResult(13);
                        synchronized (lock) {
                            pendingTokenRequest = null;
                        }
                        return request.getPendingResponse();
                    }
                } catch (Throwable th3) {
                    th = th3;
                    Log.e(TAG, "Cannot launch token fragment:" + th.getMessage(), th);
                    request.setResult(13);
                    synchronized (lock) {
                        pendingTokenRequest = null;
                    }
                    return request.getPendingResponse();
                }
            }
            Log.d(TAG, "Fragment exists.. calling processRequests");
            fragment.processRequest();
            return request.getPendingResponse();
        }
        Log.e(TAG, "Already a pending token request (requested == ): " + request);
        Log.e(TAG, "Already a pending token request: " + pendingTokenRequest);
        request.setResult(10);
        return request.getPendingResponse();
    }

    public static PendingResult getAnotherAuthCode(Activity parentActivity, boolean reauthIfNeeded, String webClientId) {
        return fetchToken(parentActivity, !reauthIfNeeded, true, false, false, webClientId, false, null, true, null);
    }

    public static void signOut(Activity activity) {
        TokenFragment fragment = (TokenFragment) activity.getFragmentManager().findFragmentByTag(FRAGMENT_TAG);
        if (fragment != null) {
            fragment.reset();
        }
        synchronized (lock) {
            pendingTokenRequest = null;
        }
    }

    private void reset() {
        if (this.mGoogleSignInClient != null) {
            this.mGoogleSignInClient.signOut();
            this.mGoogleSignInClient = null;
        }
    }

    private void signIn() {
        TokenRequest request_;
        Log.d(TAG, "signIn");
        synchronized (lock) {
            request_ = pendingTokenRequest;
        }
        final TokenRequest request = request_;
        if (this.mGoogleSignInClient != null && request != null) {
            if (request.canReuseAccount()) {
                final GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getActivity());
                if (GoogleSignIn.hasPermissions(account, request.scopes)) {
                    Log.d(TAG, "Checking the last signed-in account if it can be used.");
                    Games.getGamesClient(getActivity(), account).getAppId().addOnCompleteListener(new OnCompleteListener<String>() {

                        /* renamed from: com.google.games.bridge.TokenFragment$1$1 */
                        class C08111 implements OnCompleteListener<Void> {
                            C08111() {
                            }

                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TokenFragment.TAG, "Can't reuse the last signed-in account. Second attempt after sign out.");
                                    TokenFragment.this.signIn();
                                    return;
                                }
                                Log.e(TokenFragment.TAG, "Can't reuse the last signed-in account and sign out failed.");
                                TokenFragment.this.onSignedIn(4, null);
                            }
                        }

                        public void onComplete(@NonNull Task<String> task) {
                            if (task.isSuccessful()) {
                                Log.d(TokenFragment.TAG, "Signed-in with the last signed-in account.");
                                TokenFragment.this.onSignedIn(0, account);
                                return;
                            }
                            TokenFragment.this.mGoogleSignInClient.signOut().addOnCompleteListener(new C08111());
                        }
                    });
                    return;
                }
            }
            Log.d(TAG, "mGoogleSignInClient.silentSignIn");
            this.mGoogleSignInClient.silentSignIn().addOnSuccessListener(getActivity(), new C08143()).addOnFailureListener(getActivity(), new OnFailureListener() {
                public void onFailure(Exception exception) {
                    Log.d(TokenFragment.TAG, "silentSignIn.onFailure");
                    int statusCode = 8;
                    if (exception instanceof ApiException) {
                        statusCode = ((ApiException) exception).getStatusCode();
                    }
                    if (statusCode != 4 && statusCode != 8) {
                        Log.e(TokenFragment.TAG, "Sign-in failed with status code: " + statusCode);
                        TokenFragment.this.onSignedIn(statusCode, null);
                    } else if (request.getSilent()) {
                        Log.i(TokenFragment.TAG, "Sign-in failed. Run in silent mode and UI sign-in required.");
                        TokenFragment.this.onSignedIn(4, null);
                    } else {
                        TokenFragment.this.startActivityForResult(TokenFragment.this.mGoogleSignInClient.getSignInIntent(), 9002);
                    }
                }
            });
        }
    }

    private void processRequest() {
        synchronized (lock) {
            TokenRequest request = pendingTokenRequest;
        }
        if (request != null) {
            if (buildClient(getActivity(), request)) {
                signIn();
            } else {
                synchronized (lock) {
                    pendingTokenRequest = null;
                }
            }
            Log.d(TAG, "Done with processRequest, result is pending.");
        }
    }

    private boolean buildClient(Activity activity, TokenRequest request) {
        Log.d(TAG, "Building client for: " + request);
        Builder builder = new Builder();
        if (request.doAuthCode) {
            if (request.getWebClientId().isEmpty() || request.getWebClientId().equals("__WEB_CLIENTID__")) {
                Log.e(TAG, "Web client ID is needed for Auth Code");
                request.setResult(10);
                return false;
            }
            builder.requestServerAuthCode(request.getWebClientId(), request.getForceRefresh());
        }
        if (request.doEmail) {
            builder.requestEmail();
        }
        if (request.doIdToken) {
            if (request.getWebClientId().isEmpty() || request.getWebClientId().equals("__WEB_CLIENTID__")) {
                Log.e(TAG, "Web client ID is needed for ID Token");
                request.setResult(10);
                return false;
            }
            builder.requestIdToken(request.getWebClientId());
        }
        if (request.scopes != null) {
            for (Scope s : request.scopes) {
                builder.requestScopes(s, new Scope[0]);
            }
        }
        if (request.hidePopups) {
            Log.d(TAG, "hiding popup views for games API");
            builder.addExtension(GamesOptions.builder().setShowConnectingPopup(false).build());
        }
        if (!(request.accountName == null || request.accountName.isEmpty())) {
            builder.setAccountName(request.accountName);
        }
        this.mGoogleSignInClient = GoogleSignIn.getClient(activity, builder.build());
        return true;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 9002) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result != null && result.isSuccess()) {
                onSignedIn(result.getStatus().getStatusCode(), result.getSignInAccount());
                return;
            } else if (resultCode == 0) {
                onSignedIn(16, null);
                return;
            } else if (result != null) {
                Log.e(TAG, "GoogleSignInResult error status code: " + result.getStatus());
                onSignedIn(result.getStatus().getStatusCode(), null);
                return;
            } else {
                Log.e(TAG, "Google SignIn Result is null, resultCode is " + resultCode + "(" + GoogleSignInStatusCodes.getStatusCodeString(resultCode) + ")");
                onSignedIn(13, null);
                return;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void onSignedIn(int statusCode, GoogleSignInAccount acct) {
        synchronized (lock) {
            TokenRequest request = pendingTokenRequest;
            pendingTokenRequest = null;
        }
        if (request != null) {
            if (acct != null) {
                request.setAuthCode(acct.getServerAuthCode());
                request.setEmail(acct.getEmail());
                request.setIdToken(acct.getIdToken());
            }
            if (statusCode != 0) {
                Log.e(TAG, "Setting result error status code to: " + statusCode);
            }
            request.setResult(statusCode);
        }
    }

    public void onResume() {
        Log.d(TAG, "onResume called");
        super.onResume();
        if (helperFragment == null) {
            helperFragment = this;
        }
        processRequest();
    }

    public static boolean checkGooglePlayServicesAvailable() {
        GooglePlayServicesUtil.isGooglePlayServicesAvailable(null);
        return false;
    }

    public static View createInvisibleView(Activity parentActivity) {
        View view = new View(parentActivity);
        view.setVisibility(4);
        view.setClickable(false);
        return view;
    }
}
