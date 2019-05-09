// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.signin.internal;

import java.util.Set;
import com.google.android.gms.common.api.GoogleApiClient;
import android.support.v4.content.Loader;
import android.content.ActivityNotFoundException;
import android.util.Log;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import android.content.Context;
import com.google.android.gms.auth.api.signin.SignInAccount;
import android.view.accessibility.AccessibilityEvent;
import android.support.v4.app.LoaderManager$LoaderCallbacks;
import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import android.content.Intent;
import com.google.android.gms.common.annotation.KeepName;
import android.support.v4.app.FragmentActivity;

@KeepName
public class SignInHubActivity extends FragmentActivity
{
    private static boolean zzbt;
    private boolean zzbu;
    private SignInConfiguration zzbv;
    private boolean zzbw;
    private int zzbx;
    private Intent zzby;
    
    static {
        SignInHubActivity.zzbt = false;
    }
    
    public SignInHubActivity() {
        this.zzbu = false;
    }
    
    private final void zzc(final int n) {
        final Status status = new Status(n);
        final Intent intent = new Intent();
        intent.putExtra("googleSignInStatus", (Parcelable)status);
        this.setResult(0, intent);
        this.finish();
        SignInHubActivity.zzbt = false;
    }
    
    private final void zzn() {
        this.getSupportLoaderManager().initLoader(0, (Bundle)null, (LoaderManager$LoaderCallbacks)new zzc((zzy)null));
        SignInHubActivity.zzbt = false;
    }
    
    public boolean dispatchPopulateAccessibilityEvent(final AccessibilityEvent accessibilityEvent) {
        return true;
    }
    
    protected void onActivityResult(int n, int intExtra, final Intent zzby) {
        if (this.zzbu) {
            return;
        }
        this.setResult(0);
        switch (n) {
            default: {}
            case 40962: {
                if (zzby != null) {
                    final SignInAccount signInAccount = (SignInAccount)zzby.getParcelableExtra("signInAccount");
                    if (signInAccount != null && signInAccount.getGoogleSignInAccount() != null) {
                        final GoogleSignInAccount googleSignInAccount = signInAccount.getGoogleSignInAccount();
                        zzp.zzd((Context)this).zzc(this.zzbv.zzm(), googleSignInAccount);
                        zzby.removeExtra("signInAccount");
                        zzby.putExtra("googleSignInAccount", (Parcelable)googleSignInAccount);
                        this.zzbw = true;
                        this.zzbx = intExtra;
                        this.zzby = zzby;
                        this.zzn();
                        return;
                    }
                    if (zzby.hasExtra("errorCode")) {
                        intExtra = zzby.getIntExtra("errorCode", 8);
                        if ((n = intExtra) == 13) {
                            n = 12501;
                        }
                        this.zzc(n);
                        return;
                    }
                }
                this.zzc(8);
            }
        }
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        final Intent intent = this.getIntent();
        final String action = intent.getAction();
        if ("com.google.android.gms.auth.NO_IMPL".equals(action)) {
            this.zzc(12500);
        }
        else {
            if (!action.equals("com.google.android.gms.auth.GOOGLE_SIGN_IN") && !action.equals("com.google.android.gms.auth.APPAUTH_SIGN_IN")) {
                final String value = String.valueOf(intent.getAction());
                String concat;
                if (value.length() != 0) {
                    concat = "Unknown action: ".concat(value);
                }
                else {
                    concat = new String("Unknown action: ");
                }
                Log.e("AuthSignInClient", concat);
                this.finish();
                return;
            }
            this.zzbv = (SignInConfiguration)intent.getBundleExtra("config").getParcelable("config");
            if (this.zzbv == null) {
                Log.e("AuthSignInClient", "Activity started with invalid configuration.");
                this.setResult(0);
                this.finish();
                return;
            }
            int n;
            if (bundle == null) {
                n = 1;
            }
            else {
                n = 0;
            }
            if (n != 0) {
                if (SignInHubActivity.zzbt) {
                    this.setResult(0);
                    this.zzc(12502);
                    return;
                }
                SignInHubActivity.zzbt = true;
                final Intent intent2 = new Intent(action);
                while (true) {
                    Label_0247: {
                        if (!action.equals("com.google.android.gms.auth.GOOGLE_SIGN_IN")) {
                            break Label_0247;
                        }
                        intent2.setPackage("com.google.android.gms");
                        intent2.putExtra("config", (Parcelable)this.zzbv);
                        try {
                            this.startActivityForResult(intent2, 40962);
                            return;
                        }
                        catch (ActivityNotFoundException ex) {
                            this.zzbu = true;
                            Log.w("AuthSignInClient", "Could not launch sign in Intent. Google Play Service is probably being updated...");
                            this.zzc(17);
                            return;
                        }
                    }
                    intent2.setPackage(this.getPackageName());
                    continue;
                }
            }
            else {
                this.zzbw = bundle.getBoolean("signingInGoogleApiClients");
                if (this.zzbw) {
                    this.zzbx = bundle.getInt("signInResultCode");
                    this.zzby = (Intent)bundle.getParcelable("signInResultData");
                    this.zzn();
                }
            }
        }
    }
    
    protected void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("signingInGoogleApiClients", this.zzbw);
        if (this.zzbw) {
            bundle.putInt("signInResultCode", this.zzbx);
            bundle.putParcelable("signInResultData", (Parcelable)this.zzby);
        }
    }
    
    private final class zzc implements LoaderManager$LoaderCallbacks<Void>
    {
        public final Loader<Void> onCreateLoader(final int n, final Bundle bundle) {
            return (Loader<Void>)new zze((Context)SignInHubActivity.this, GoogleApiClient.getAllClients());
        }
        
        public final void onLoaderReset(final Loader<Void> loader) {
        }
    }
}
