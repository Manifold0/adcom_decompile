package com.tapjoy;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import com.google.android.gms.drive.DriveFile;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import com.tapjoy.TJCorePlacement.C28064;
import com.tapjoy.TJCorePlacement.C28075;
import com.tapjoy.TapjoyErrorMessage.ErrorType;
import com.tapjoy.internal.ct;
import com.tapjoy.internal.eq;
import com.tapjoy.internal.et;
import com.tapjoy.internal.ez;
import com.tapjoy.internal.fi;
import com.tapjoy.internal.fi.C2926a;
import com.tapjoy.internal.fy;
import com.tapjoy.internal.gc;
import com.tapjoy.internal.gh;
import com.vungle.warren.ui.VungleActivity;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TJPlacement {
    /* renamed from: a */
    TJPlacementListener f6958a;
    /* renamed from: b */
    private TJCorePlacement f6959b;
    /* renamed from: c */
    private TJPlacementListener f6960c;
    /* renamed from: d */
    private TJPlacementVideoListener f6961d;
    /* renamed from: e */
    private String f6962e;
    public String pushId;

    @Deprecated
    public TJPlacement(Context activityContext, String placementName, TJPlacementListener listener) {
        TJCorePlacement a = TJPlacementManager.m7079a(placementName);
        if (a == null) {
            a = TJPlacementManager.m7080a(placementName, "", "", false);
        }
        a.setContext(activityContext);
        m7077a(a, listener);
    }

    TJPlacement(TJCorePlacement corePlacement, TJPlacementListener listener) {
        m7077a(corePlacement, listener);
    }

    /* renamed from: a */
    private void m7077a(TJCorePlacement tJCorePlacement, TJPlacementListener tJPlacementListener) {
        this.f6959b = tJCorePlacement;
        this.f6962e = UUID.randomUUID().toString();
        this.f6960c = tJPlacementListener;
        this.f6958a = tJPlacementListener != null ? (TJPlacementListener) eq.m7671a(tJPlacementListener, TJPlacementListener.class) : null;
        FiveRocksIntegration.addPlacementCallback(getName(), this);
    }

    public TJPlacementListener getListener() {
        return this.f6960c;
    }

    public void setVideoListener(TJPlacementVideoListener videoListener) {
        this.f6961d = videoListener;
    }

    public TJPlacementVideoListener getVideoListener() {
        return this.f6961d;
    }

    public String getName() {
        return this.f6959b.getPlacementData() != null ? this.f6959b.getPlacementData().getPlacementName() : "";
    }

    public boolean isContentReady() {
        boolean isContentReady = this.f6959b.isContentReady();
        ez ezVar = this.f6959b.f6917f;
        if (isContentReady) {
            ezVar.m7690a(4);
        } else {
            ezVar.m7690a(2);
        }
        return isContentReady;
    }

    public boolean isContentAvailable() {
        this.f6959b.f6917f.m7690a(1);
        return this.f6959b.isContentAvailable();
    }

    public void setMediationId(String mediationId) {
        this.f6959b.f6928q = mediationId;
    }

    public void requestContent() {
        Object name = getName();
        TapjoyLog.m7129i("TJPlacement", "requestContent() called for placement " + name);
        fi.m7743a("TJPlacement.requestContent").m7737a(VungleActivity.PLACEMENT_EXTRA, name).m7737a("placement_type", this.f6959b.f6914c.getPlacementType());
        if (!TapjoyConnectCore.isConnected()) {
            fi.m7750b("TJPlacement.requestContent").m7740b("not connected").m7742c();
            m7078a(new TJError(0, "SDK not connected -- connect must be called first with a successful callback"));
        } else if (this.f6959b.getContext() == null) {
            fi.m7750b("TJPlacement.requestContent").m7740b("no context").m7742c();
            m7078a(new TJError(0, "Context is null -- TJPlacement requires a valid Context."));
        } else if (ct.m7339c(name)) {
            fi.m7750b("TJPlacement.requestContent").m7740b("invalid name").m7742c();
            m7078a(new TJError(0, "Invalid placement name -- TJPlacement requires a valid placement name."));
        } else {
            try {
                TJCorePlacement tJCorePlacement = this.f6959b;
                if (this == null) {
                    tJCorePlacement.m7063a(ErrorType.SDK_ERROR, new TJError(0, "Cannot request content from a NULL placement"));
                } else {
                    tJCorePlacement.m7064a("REQUEST", this);
                    if (tJCorePlacement.f6916e - SystemClock.elapsedRealtime() > 0) {
                        TapjoyLog.m7126d(TJCorePlacement.f6912a, "Content has not expired yet for " + tJCorePlacement.f6914c.getPlacementName());
                        if (tJCorePlacement.f6923l) {
                            fi.m7750b("TJPlacement.requestContent").m7737a("content_type", tJCorePlacement.m7066b()).m7737a("from", (Object) "cache").m7742c();
                            tJCorePlacement.f6922k = false;
                            tJCorePlacement.m7061a(this);
                            tJCorePlacement.m7067c();
                        } else {
                            fi.m7750b("TJPlacement.requestContent").m7737a("content_type", ParametersKeys.ORIENTATION_NONE).m7737a("from", (Object) "cache").m7742c();
                            tJCorePlacement.m7061a(this);
                        }
                    } else {
                        if (tJCorePlacement.f6923l) {
                            fi.m7752c("TJPlacement.requestContent").m7737a("was_available", Boolean.valueOf(true));
                        }
                        if (tJCorePlacement.f6924m) {
                            fi.m7752c("TJPlacement.requestContent").m7737a("was_ready", Boolean.valueOf(true));
                        }
                        if (ct.m7339c(tJCorePlacement.f6927p)) {
                            tJCorePlacement.m7060a();
                        } else {
                            Map hashMap = new HashMap();
                            hashMap.put(TJAdUnitConstants.PARAM_PLACEMENT_MEDIATION_AGENT, tJCorePlacement.f6927p);
                            hashMap.put(TJAdUnitConstants.PARAM_PLACEMENT_MEDIATION_ID, tJCorePlacement.f6928q);
                            tJCorePlacement.m7065a(tJCorePlacement.f6914c.getMediationURL(), hashMap);
                        }
                    }
                }
                fi.m7753d("TJPlacement.requestContent");
            } catch (Throwable th) {
                fi.m7753d("TJPlacement.requestContent");
            }
        }
    }

    public void showContent() {
        int i = 1;
        TapjoyLog.m7129i("TJPlacement", "showContent() called for placement " + getName());
        TJCorePlacement tJCorePlacement = this.f6959b;
        fi.m7743a("TJPlacement.showContent").m7737a(VungleActivity.PLACEMENT_EXTRA, tJCorePlacement.f6914c.getPlacementName()).m7737a("placement_type", tJCorePlacement.f6914c.getPlacementType()).m7737a("content_type", tJCorePlacement.m7066b());
        ez ezVar = tJCorePlacement.f6917f;
        ezVar.m7690a(8);
        et etVar = ezVar.f7707a;
        if (etVar != null) {
            etVar.m7661a();
        }
        if (this.f6959b.isContentAvailable()) {
            try {
                tJCorePlacement = this.f6959b;
                if (this == null) {
                    tJCorePlacement.m7063a(ErrorType.SDK_ERROR, new TJError(0, "Cannot show content from a NULL placement"));
                } else if (TapjoyConnectCore.isFullScreenViewOpen()) {
                    TapjoyLog.m7131w(TJCorePlacement.f6912a, "Only one view can be presented at a time.");
                    fi.m7750b("TJPlacement.showContent").m7740b("another content showing").m7742c();
                } else {
                    if (TapjoyConnectCore.isViewOpen()) {
                        TapjoyLog.m7131w(TJCorePlacement.f6912a, "Will close N2E content.");
                        TJPlacementManager.dismissContentShowing(false);
                    }
                    tJCorePlacement.m7064a("SHOW", this);
                    C2926a d = fi.m7753d("TJPlacement.showContent");
                    if (tJCorePlacement.f6918g.isPrerendered()) {
                        d.m7737a("prerendered", Boolean.valueOf(true));
                    }
                    if (tJCorePlacement.isContentReady()) {
                        d.m7737a("content_ready", Boolean.valueOf(true));
                    }
                    tJCorePlacement.f6917f.f7710d = d;
                    String uuid = UUID.randomUUID().toString();
                    if (tJCorePlacement.f6920i != null) {
                        tJCorePlacement.f6920i.f7807f = uuid;
                        if (tJCorePlacement.f6920i != null) {
                            i = tJCorePlacement.f6920i instanceof fy ? 3 : tJCorePlacement.f6920i instanceof gh ? 2 : 0;
                        }
                        TapjoyConnectCore.viewWillOpen(uuid, i);
                        tJCorePlacement.f6920i.f7806e = new C28064(tJCorePlacement, uuid);
                        gc.m7835a(new C28075(tJCorePlacement));
                    } else {
                        tJCorePlacement.f6914c.setContentViewId(uuid);
                        Intent intent = new Intent(tJCorePlacement.f6913b, TJAdUnitActivity.class);
                        intent.putExtra(TJAdUnitConstants.EXTRA_TJ_PLACEMENT_DATA, tJCorePlacement.f6914c);
                        intent.setFlags(DriveFile.MODE_READ_ONLY);
                        tJCorePlacement.f6913b.startActivity(intent);
                    }
                    tJCorePlacement.f6916e = 0;
                    tJCorePlacement.f6923l = false;
                    tJCorePlacement.f6924m = false;
                }
                fi.m7753d("TJPlacement.showContent");
            } catch (Throwable th) {
                fi.m7753d("TJPlacement.showContent");
            }
        } else {
            TapjoyLog.m7127e("TJPlacement", new TapjoyErrorMessage(ErrorType.INTEGRATION_ERROR, "No placement content available. Can not show content for non-200 placement."));
            fi.m7750b("TJPlacement.showContent").m7740b("no content").m7742c();
        }
    }

    public void setMediationName(String mediationName) {
        TapjoyLog.m7126d("TJPlacement", "setMediationName=" + mediationName);
        if (!ct.m7339c(mediationName)) {
            Context context = this.f6959b != null ? this.f6959b.getContext() : null;
            this.f6959b = TJPlacementManager.m7080a(getName(), mediationName, "", false);
            TJCorePlacement tJCorePlacement = this.f6959b;
            tJCorePlacement.f6927p = mediationName;
            tJCorePlacement.f6925n = mediationName;
            tJCorePlacement.f6914c.setPlacementType(mediationName);
            tJCorePlacement.f6914c.setMediationURL(TapjoyConnectCore.getPlacementURL() + "v1/apps/" + TapjoyConnectCore.getAppID() + "/mediation_content?");
            if (context != null) {
                this.f6959b.setContext(context);
            }
        }
    }

    public void setAdapterVersion(String adapterVersion) {
        this.f6959b.f6926o = adapterVersion;
    }

    public static void dismissContent() {
        boolean z = false;
        if ("true".equals(TapjoyConnectCore.getConnectFlagValue("TJC_OPTION_DISMISS_CONTENT_ALL"))) {
            z = true;
        }
        TJPlacementManager.dismissContentShowing(z);
    }

    public String getGUID() {
        return this.f6962e;
    }

    /* renamed from: a */
    private void m7078a(TJError tJError) {
        this.f6959b.m7062a(this, ErrorType.INTEGRATION_ERROR, tJError);
    }
}
