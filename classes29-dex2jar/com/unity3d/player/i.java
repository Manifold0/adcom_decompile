// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.player;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;

public final class i extends Fragment
{
    private final Runnable a;
    
    public i() {
        this.a = null;
    }
    
    public i(final Runnable a) {
        this.a = a;
    }
    
    public final void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (this.a == null) {
            this.getFragmentManager().beginTransaction().remove((Fragment)this).commit();
            return;
        }
        this.requestPermissions(this.getArguments().getStringArray("PermissionNames"), 15881);
    }
    
    public final void onRequestPermissionsResult(int n, final String[] array, final int[] array2) {
        if (n != 15881) {
            return;
        }
        if (array.length == 0) {
            this.requestPermissions(this.getArguments().getStringArray("PermissionNames"), 15881);
            return;
        }
        StringBuilder append;
        String s;
        for (n = 0; n < array.length && n < array2.length; ++n) {
            append = new StringBuilder().append(array[n]);
            if (array2[n] == 0) {
                s = " granted";
            }
            else {
                s = " denied";
            }
            g.Log(4, append.append(s).toString());
        }
        final FragmentTransaction beginTransaction = this.getActivity().getFragmentManager().beginTransaction();
        beginTransaction.remove((Fragment)this);
        beginTransaction.commit();
        this.a.run();
    }
}
