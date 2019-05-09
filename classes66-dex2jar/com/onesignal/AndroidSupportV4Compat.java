// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import android.os.Build$VERSION;
import android.util.Log;
import android.os.Process;
import android.content.Context;
import android.annotation.TargetApi;
import android.support.annotation.NonNull;
import android.app.Activity;

class AndroidSupportV4Compat
{
    static class ActivityCompat
    {
        static void requestPermissions(@NonNull final Activity activity, @NonNull final String[] array, final int n) {
            ActivityCompatApi23.requestPermissions(activity, array, n);
        }
    }
    
    @TargetApi(23)
    static class ActivityCompatApi23
    {
        static void requestPermissions(final Activity activity, final String[] array, final int n) {
            if (activity instanceof RequestPermissionsRequestCodeValidator) {
                ((RequestPermissionsRequestCodeValidator)activity).validateRequestPermissionsRequestCode(n);
            }
            activity.requestPermissions(array, n);
        }
    }
    
    static class ContextCompat
    {
        static int checkSelfPermission(@NonNull final Context context, @NonNull final String s) {
            try {
                return context.checkPermission(s, Process.myPid(), Process.myUid());
            }
            catch (Throwable t) {
                Log.e("OneSignal", "checkSelfPermission failed, returning PERMISSION_DENIED");
                return -1;
            }
        }
        
        static int getColor(final Context context, final int n) {
            if (Build$VERSION.SDK_INT > 22) {
                return context.getColor(n);
            }
            return context.getResources().getColor(n);
        }
    }
    
    interface RequestPermissionsRequestCodeValidator
    {
        void validateRequestPermissionsRequestCode(final int p0);
    }
}
