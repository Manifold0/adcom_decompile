// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common;

import android.os.Parcelable;
import java.io.Serializable;
import com.google.android.gms.common.internal.Preconditions;
import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;
import android.accounts.Account;

public final class AccountPicker
{
    private AccountPicker() {
    }
    
    public static Intent newChooseAccountIntent(final Account account, final ArrayList<Account> list, final String[] array, final boolean b, final String s, final String s2, final String[] array2, final Bundle bundle) {
        final Intent intent = new Intent();
        Preconditions.checkArgument(true, (Object)"We only support hostedDomain filter for account chip styled account picker");
        intent.setAction("com.google.android.gms.common.account.CHOOSE_ACCOUNT");
        intent.setPackage("com.google.android.gms");
        intent.putExtra("allowableAccounts", (Serializable)list);
        intent.putExtra("allowableAccountTypes", array);
        intent.putExtra("addAccountOptions", bundle);
        intent.putExtra("selectedAccount", (Parcelable)account);
        intent.putExtra("alwaysPromptForAccount", b);
        intent.putExtra("descriptionTextOverride", s);
        intent.putExtra("authTokenType", s2);
        intent.putExtra("addAccountRequiredFeatures", array2);
        intent.putExtra("setGmsCoreAccount", false);
        intent.putExtra("overrideTheme", 0);
        intent.putExtra("overrideCustomTheme", 0);
        intent.putExtra("hostedDomainFilter", (String)null);
        return intent;
    }
}
