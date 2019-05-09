package com.ironsource.mediationsdk.sdk;

import android.app.Activity;

public interface BaseApi {
    void onPause(Activity activity);

    void onResume(Activity activity);

    void setAge(int i);

    void setGender(String str);

    void setMediationSegment(String str);
}
