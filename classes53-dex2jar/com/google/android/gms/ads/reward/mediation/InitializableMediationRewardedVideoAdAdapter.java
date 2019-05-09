// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.reward.mediation;

import android.os.Bundle;
import java.util.List;
import android.content.Context;

public interface InitializableMediationRewardedVideoAdAdapter extends MediationRewardedVideoAdAdapter
{
    void initialize(final Context p0, final MediationRewardedVideoAdListener p1, final List<Bundle> p2);
}
