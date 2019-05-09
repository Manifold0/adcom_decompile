// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.internal;

import android.content.pm.Signature;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.os.Build;
import android.content.Context;
import java.util.HashSet;

public class FacebookSignatureValidator
{
    private static final String FBF_HASH = "2438bce1ddb7bd026d5ff89f598b3b5e5bb824b3";
    private static final String FBI_HASH = "a4b7452e2ed8f5f191058ca7bbfd26b0d3214bfc";
    private static final String FBL2_HASH = "df6b721c8b4d3b6eb44c861d4415007e5a35fc95";
    private static final String FBL_HASH = "5e8f16062ea3cd2c4a0d547876baa6f38cabf625";
    private static final String FBR2_HASH = "cc2751449a350f668590264ed76692694a80308a";
    private static final String FBR_HASH = "8a3c4b262d721acd49a4bf97d5213199c86fa2b9";
    private static final String MSR_HASH = "9b8f518b086098de3d77736f9458a3d2f6f95a37";
    private static final HashSet<String> validAppSignatureHashes;
    
    static {
        validAppSignatureHashes = buildAppSignatureHashes();
    }
    
    private static HashSet<String> buildAppSignatureHashes() {
        final HashSet<String> set = new HashSet<String>();
        set.add("8a3c4b262d721acd49a4bf97d5213199c86fa2b9");
        set.add("cc2751449a350f668590264ed76692694a80308a");
        set.add("a4b7452e2ed8f5f191058ca7bbfd26b0d3214bfc");
        set.add("5e8f16062ea3cd2c4a0d547876baa6f38cabf625");
        set.add("df6b721c8b4d3b6eb44c861d4415007e5a35fc95");
        set.add("9b8f518b086098de3d77736f9458a3d2f6f95a37");
        set.add("2438bce1ddb7bd026d5ff89f598b3b5e5bb824b3");
        return set;
    }
    
    public static boolean validateSignature(final Context context, String sha1hash) {
        final String brand = Build.BRAND;
        final int flags = context.getApplicationInfo().flags;
        if (!brand.startsWith("generic") || (flags & 0x2) == 0x0) {
            PackageInfo packageInfo;
            try {
                packageInfo = context.getPackageManager().getPackageInfo(sha1hash, 64);
                if (packageInfo.signatures == null || packageInfo.signatures.length <= 0) {
                    return false;
                }
            }
            catch (PackageManager$NameNotFoundException ex) {
                return false;
            }
            final Signature[] signatures = packageInfo.signatures;
            for (int length = signatures.length, i = 0; i < length; ++i) {
                sha1hash = Utility.sha1hash(signatures[i].toByteArray());
                if (!FacebookSignatureValidator.validAppSignatureHashes.contains(sha1hash)) {
                    return false;
                }
            }
        }
        return true;
    }
}
