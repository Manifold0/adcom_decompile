package com.unity3d.services;

import com.unity3d.services.UnityServices.UnityServicesError;

public interface IUnityServicesListener {
    void onUnityServicesError(UnityServicesError unityServicesError, String str);
}
