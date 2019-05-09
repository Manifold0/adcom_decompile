// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth;

public enum zzay
{
    @Deprecated
    zzcj("ClientLoginDisabled"), 
    @Deprecated
    zzck("DeviceManagementRequiredOrSyncDisabled"), 
    @Deprecated
    zzcl("SocketTimeout"), 
    zzcm("Ok"), 
    zzcn("UNKNOWN_ERR"), 
    zzco("NetworkError"), 
    zzcp("ServiceUnavailable"), 
    zzcq("InternalError"), 
    zzcr("BadAuthentication"), 
    zzcs("EmptyConsumerPackageOrSig"), 
    zzct("InvalidSecondFactor"), 
    zzcu("PostSignInFlowRequired"), 
    zzcv("NeedsBrowser"), 
    zzcw("Unknown"), 
    zzcx("NotVerified"), 
    zzcy("TermsNotAgreed"), 
    zzcz("AccountDisabled"), 
    zzda("CaptchaRequired"), 
    zzdb("AccountDeleted"), 
    zzdc("ServiceDisabled"), 
    zzdd("NeedPermission"), 
    zzde("NeedRemoteConsent"), 
    zzdf("INVALID_SCOPE"), 
    zzdg("UserCancel"), 
    zzdh("PermissionDenied"), 
    zzdi("INVALID_AUDIENCE"), 
    zzdj("UNREGISTERED_ON_API_CONSOLE"), 
    zzdk("ThirdPartyDeviceManagementRequired"), 
    zzdl("DeviceManagementInternalError"), 
    zzdm("DeviceManagementSyncDisabled"), 
    zzdn("DeviceManagementAdminBlocked"), 
    zzdo("DeviceManagementAdminPendingApproval"), 
    zzdp("DeviceManagementStaleSyncRequired"), 
    zzdq("DeviceManagementDeactivated"), 
    zzdr("DeviceManagementScreenlockRequired"), 
    zzds("DeviceManagementRequired"), 
    zzdt("ALREADY_HAS_GMAIL"), 
    zzdu("WeakPassword"), 
    zzdv("BadRequest"), 
    zzdw("BadUsername"), 
    zzdx("DeletedGmail"), 
    zzdy("ExistingUsername"), 
    zzdz("LoginFail"), 
    zzea("NotLoggedIn"), 
    zzeb("NoGmail"), 
    zzec("RequestDenied"), 
    zzed("ServerError"), 
    zzee("UsernameUnavailable"), 
    zzef("GPlusOther"), 
    zzeg("GPlusNickname"), 
    zzeh("GPlusInvalidChar"), 
    zzei("GPlusInterstitial"), 
    zzej("ProfileUpgradeError");
    
    private final String zzek;
    
    private zzay(final String zzek) {
        this.zzek = zzek;
    }
    
    public static boolean zza(final zzay zzay) {
        return zzay.zzcr.equals(zzay) || zzay.zzda.equals(zzay) || zzay.zzdd.equals(zzay) || zzay.zzde.equals(zzay) || zzay.zzcv.equals(zzay) || zzay.zzdg.equals(zzay) || zzay.zzck.equals(zzay) || zzay.zzdl.equals(zzay) || zzay.zzdm.equals(zzay) || zzay.zzdn.equals(zzay) || zzay.zzdo.equals(zzay) || zzay.zzdp.equals(zzay) || zzay.zzdq.equals(zzay) || zzay.zzds.equals(zzay) || zzay.zzdk.equals(zzay) || zzay.zzdr.equals(zzay);
    }
    
    public static final zzay zzc(final String s) {
        zzay zzay = null;
        final zzay[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final zzay zzay2 = values[i];
            if (zzay2.zzek.equals(s)) {
                zzay = zzay2;
            }
        }
        return zzay;
    }
}
