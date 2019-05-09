// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads;

import android.view.ViewGroup$LayoutParams;
import android.view.View;
import com.google.android.gms.internal.ads.zzkb;
import android.os.Bundle;
import com.google.android.gms.dynamic.ObjectWrapper;
import android.content.res.Configuration;
import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzaap;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;
import android.app.Activity;

@KeepForSdkWithMembers
public class AdActivity extends Activity
{
    public static final String CLASS_NAME = "com.google.android.gms.ads.AdActivity";
    public static final String SIMPLE_CLASS_NAME = "AdActivity";
    private zzaap zzuj;
    
    private final void zzax() {
        if (this.zzuj == null) {
            return;
        }
        try {
            this.zzuj.zzax();
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    protected void onActivityResult(final int n, final int n2, final Intent intent) {
        while (true) {
            try {
                this.zzuj.onActivityResult(n, n2, intent);
                super.onActivityResult(n, n2, intent);
            }
            catch (Exception ex) {
                zzane.zzd("#007 Could not call remote method.", ex);
                continue;
            }
            break;
        }
    }
    
    public void onBackPressed() {
        int zznj = 1;
        while (true) {
            try {
                if (this.zzuj != null) {
                    zznj = (this.zzuj.zznj() ? 1 : 0);
                }
                if (zznj != 0) {
                    super.onBackPressed();
                }
            }
            catch (RemoteException ex) {
                zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
                zznj = zznj;
                continue;
            }
            break;
        }
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        try {
            this.zzuj.zzo(ObjectWrapper.wrap((Object)configuration));
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.zzuj = zzkb.zzig().zzb(this);
        if (this.zzuj == null) {
            zzane.zzd("#007 Could not call remote method.", null);
            this.finish();
            return;
        }
        try {
            this.zzuj.onCreate(bundle);
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
            this.finish();
        }
    }
    
    protected void onDestroy() {
        while (true) {
            try {
                if (this.zzuj != null) {
                    this.zzuj.onDestroy();
                }
                super.onDestroy();
            }
            catch (RemoteException ex) {
                zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    protected void onPause() {
        while (true) {
            try {
                if (this.zzuj != null) {
                    this.zzuj.onPause();
                }
                super.onPause();
            }
            catch (RemoteException ex) {
                zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
                this.finish();
                continue;
            }
            break;
        }
    }
    
    protected void onRestart() {
        super.onRestart();
        try {
            if (this.zzuj != null) {
                this.zzuj.onRestart();
            }
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
            this.finish();
        }
    }
    
    protected void onResume() {
        super.onResume();
        try {
            if (this.zzuj != null) {
                this.zzuj.onResume();
            }
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
            this.finish();
        }
    }
    
    protected void onSaveInstanceState(final Bundle bundle) {
        while (true) {
            try {
                if (this.zzuj != null) {
                    this.zzuj.onSaveInstanceState(bundle);
                }
                super.onSaveInstanceState(bundle);
            }
            catch (RemoteException ex) {
                zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
                this.finish();
                continue;
            }
            break;
        }
    }
    
    protected void onStart() {
        super.onStart();
        try {
            if (this.zzuj != null) {
                this.zzuj.onStart();
            }
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
            this.finish();
        }
    }
    
    protected void onStop() {
        while (true) {
            try {
                if (this.zzuj != null) {
                    this.zzuj.onStop();
                }
                super.onStop();
            }
            catch (RemoteException ex) {
                zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
                this.finish();
                continue;
            }
            break;
        }
    }
    
    public void setContentView(final int contentView) {
        super.setContentView(contentView);
        this.zzax();
    }
    
    public void setContentView(final View contentView) {
        super.setContentView(contentView);
        this.zzax();
    }
    
    public void setContentView(final View view, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        super.setContentView(view, viewGroup$LayoutParams);
        this.zzax();
    }
}
