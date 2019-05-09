package com.google.android.gms.auth.api.signin.internal;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes;
import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.ironsource.mediationsdk.utils.IronSourceConstants;

@KeepName
public class SignInHubActivity extends FragmentActivity {
    private static boolean zzbt = false;
    private boolean zzbu = false;
    private SignInConfiguration zzbv;
    private boolean zzbw;
    private int zzbx;
    private Intent zzby;

    private class zzc implements LoaderCallbacks<Void> {
        private final /* synthetic */ SignInHubActivity zzbz;

        private zzc(SignInHubActivity signInHubActivity) {
            this.zzbz = signInHubActivity;
        }

        public final Loader<Void> onCreateLoader(int i, Bundle bundle) {
            return new zze(this.zzbz, GoogleApiClient.getAllClients());
        }

        public final void onLoaderReset(Loader<Void> loader) {
        }

        public final /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
            this.zzbz.setResult(this.zzbz.zzbx, this.zzbz.zzby);
            this.zzbz.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        String action = intent.getAction();
        if ("com.google.android.gms.auth.NO_IMPL".equals(action)) {
            zzc((int) GoogleSignInStatusCodes.SIGN_IN_FAILED);
        } else if (action.equals("com.google.android.gms.auth.GOOGLE_SIGN_IN") || action.equals("com.google.android.gms.auth.APPAUTH_SIGN_IN")) {
            this.zzbv = (SignInConfiguration) intent.getBundleExtra("config").getParcelable("config");
            if (this.zzbv == null) {
                Log.e("AuthSignInClient", "Activity started with invalid configuration.");
                setResult(0);
                finish();
                return;
            }
            boolean z;
            if (bundle == null) {
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                this.zzbw = bundle.getBoolean("signingInGoogleApiClients");
                if (this.zzbw) {
                    this.zzbx = bundle.getInt("signInResultCode");
                    this.zzby = (Intent) bundle.getParcelable("signInResultData");
                    zzn();
                }
            } else if (zzbt) {
                setResult(0);
                zzc((int) GoogleSignInStatusCodes.SIGN_IN_CURRENTLY_IN_PROGRESS);
            } else {
                zzbt = true;
                intent = new Intent(action);
                if (action.equals("com.google.android.gms.auth.GOOGLE_SIGN_IN")) {
                    intent.setPackage("com.google.android.gms");
                } else {
                    intent.setPackage(getPackageName());
                }
                intent.putExtra("config", this.zzbv);
                try {
                    startActivityForResult(intent, 40962);
                } catch (ActivityNotFoundException e) {
                    this.zzbu = true;
                    Log.w("AuthSignInClient", "Could not launch sign in Intent. Google Play Service is probably being updated...");
                    zzc(17);
                }
            }
        } else {
            String str = "AuthSignInClient";
            String str2 = "Unknown action: ";
            String valueOf = String.valueOf(intent.getAction());
            Log.e(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            finish();
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("signingInGoogleApiClients", this.zzbw);
        if (this.zzbw) {
            bundle.putInt("signInResultCode", this.zzbx);
            bundle.putParcelable("signInResultData", this.zzby);
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (!this.zzbu) {
            setResult(0);
            switch (i) {
                case 40962:
                    if (intent != null) {
                        SignInAccount signInAccount = (SignInAccount) intent.getParcelableExtra(GoogleSignInApi.EXTRA_SIGN_IN_ACCOUNT);
                        if (signInAccount != null && signInAccount.getGoogleSignInAccount() != null) {
                            Parcelable googleSignInAccount = signInAccount.getGoogleSignInAccount();
                            zzp.zzd(this).zzc(this.zzbv.zzm(), googleSignInAccount);
                            intent.removeExtra(GoogleSignInApi.EXTRA_SIGN_IN_ACCOUNT);
                            intent.putExtra("googleSignInAccount", googleSignInAccount);
                            this.zzbw = true;
                            this.zzbx = i2;
                            this.zzby = intent;
                            zzn();
                            return;
                        } else if (intent.hasExtra(IronSourceConstants.ERROR_CODE_KEY)) {
                            int intExtra = intent.getIntExtra(IronSourceConstants.ERROR_CODE_KEY, 8);
                            if (intExtra == 13) {
                                intExtra = GoogleSignInStatusCodes.SIGN_IN_CANCELLED;
                            }
                            zzc(intExtra);
                            return;
                        }
                    }
                    zzc(8);
                    return;
                default:
                    return;
            }
        }
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return true;
    }

    private final void zzn() {
        getSupportLoaderManager().initLoader(0, null, new zzc());
        zzbt = false;
    }

    private final void zzc(int i) {
        Parcelable status = new Status(i);
        Intent intent = new Intent();
        intent.putExtra("googleSignInStatus", status);
        setResult(0, intent);
        finish();
        zzbt = false;
    }
}
