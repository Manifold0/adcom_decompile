// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.ads.adunit;

import java.util.Collection;
import java.util.Arrays;
import android.view.WindowManager$LayoutParams;
import android.os.Build$VERSION;
import org.json.JSONArray;
import android.view.KeyEvent;
import java.util.Iterator;
import com.unity3d.services.core.webview.WebViewEventCategory;
import com.unity3d.services.core.api.Intent;
import com.unity3d.services.ads.api.AdUnit;
import android.os.Bundle;
import java.util.HashMap;
import android.widget.FrameLayout$LayoutParams;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import android.content.Context;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import com.unity3d.services.core.misc.ViewUtilities;
import android.view.View;
import com.unity3d.services.core.configuration.IModuleConfiguration;
import com.unity3d.services.core.configuration.Configuration;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.ads.configuration.IAdsModuleConfiguration;
import com.unity3d.services.core.webview.WebViewApp;
import java.util.Map;
import java.util.ArrayList;
import android.app.Activity;

public class AdUnitActivity extends Activity
{
    public static final String EXTRA_ACTIVITY_ID = "activityId";
    public static final String EXTRA_DISPLAY_CUTOUT_MODE = "displayCutoutMode";
    public static final String EXTRA_KEEP_SCREEN_ON = "keepScreenOn";
    public static final String EXTRA_KEY_EVENT_LIST = "keyEvents";
    public static final String EXTRA_ORIENTATION = "orientation";
    public static final String EXTRA_SYSTEM_UI_VISIBILITY = "systemUiVisibility";
    public static final String EXTRA_VIEWS = "views";
    private int _activityId;
    private int _displayCutoutMode;
    boolean _keepScreenOn;
    private ArrayList<Integer> _keyEventList;
    protected AdUnitRelativeLayout _layout;
    private int _orientation;
    private int _systemUiVisibility;
    private Map<String, IAdUnitViewHandler> _viewHandlers;
    private String[] _views;
    
    public AdUnitActivity() {
        this._orientation = -1;
    }
    
    private IAdUnitViewHandler createViewHandler(final String s) {
        if (WebViewApp.getCurrentApp() != null) {
            final Configuration configuration = WebViewApp.getCurrentApp().getConfiguration();
            final String[] moduleConfigurationList = configuration.getModuleConfigurationList();
            for (int length = moduleConfigurationList.length, i = 0; i < length; ++i) {
                final IModuleConfiguration moduleConfiguration = configuration.getModuleConfiguration(moduleConfigurationList[i]);
                if (moduleConfiguration instanceof IAdsModuleConfiguration) {
                    final Map<String, Class> adUnitViewHandlers = ((IAdsModuleConfiguration)moduleConfiguration).getAdUnitViewHandlers();
                    if (adUnitViewHandlers != null && adUnitViewHandlers.containsKey(s)) {
                        try {
                            return ((Class<IAdUnitViewHandler>)adUnitViewHandlers.get(s)).newInstance();
                        }
                        catch (Exception ex) {
                            DeviceLog.error("Error creating view: " + s);
                            return null;
                        }
                    }
                }
            }
        }
        return null;
    }
    
    private boolean handleViewPlacement(final View view) {
        if (view == null) {
            this.finish();
            DeviceLog.error("Could not place view because it is null, finishing activity");
            return false;
        }
        if (view.getParent() != null && view.getParent().equals(this._layout)) {
            this._layout.bringChildToFront(view);
        }
        else {
            ViewUtilities.removeViewFromParent(view);
            final RelativeLayout$LayoutParams relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(-1, -1);
            relativeLayout$LayoutParams.addRule(13);
            relativeLayout$LayoutParams.setMargins(0, 0, 0, 0);
            view.setPadding(0, 0, 0, 0);
            this._layout.addView(view, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
        }
        return true;
    }
    
    protected void createLayout() {
        if (this._layout != null) {
            return;
        }
        (this._layout = new AdUnitRelativeLayout((Context)this)).setLayoutParams((ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -1));
        ViewUtilities.setBackground((View)this._layout, (Drawable)new ColorDrawable(-16777216));
    }
    
    public AdUnitRelativeLayout getLayout() {
        return this._layout;
    }
    
    public Map<String, Integer> getViewFrame(final String s) {
        final IAdUnitViewHandler viewHandler = this.getViewHandler(s);
        final View view = null;
        if (s.equals("adunit")) {
            final FrameLayout$LayoutParams frameLayout$LayoutParams = (FrameLayout$LayoutParams)this._layout.getLayoutParams();
            final HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
            hashMap.put("x", frameLayout$LayoutParams.leftMargin);
            hashMap.put("y", frameLayout$LayoutParams.topMargin);
            hashMap.put("width", this._layout.getWidth());
            hashMap.put("height", this._layout.getHeight());
            return hashMap;
        }
        View view2 = view;
        if (viewHandler != null) {
            view2 = viewHandler.getView();
        }
        if (view2 != null) {
            final RelativeLayout$LayoutParams relativeLayout$LayoutParams = (RelativeLayout$LayoutParams)view2.getLayoutParams();
            final HashMap<String, Integer> hashMap2 = new HashMap<String, Integer>();
            hashMap2.put("x", relativeLayout$LayoutParams.leftMargin);
            hashMap2.put("y", relativeLayout$LayoutParams.topMargin);
            hashMap2.put("width", view2.getWidth());
            hashMap2.put("height", view2.getHeight());
            return hashMap2;
        }
        return null;
    }
    
    public IAdUnitViewHandler getViewHandler(final String s) {
        IAdUnitViewHandler adUnitViewHandler;
        if (this._viewHandlers != null && this._viewHandlers.containsKey(s)) {
            adUnitViewHandler = this._viewHandlers.get(s);
        }
        else {
            final IAdUnitViewHandler viewHandler = this.createViewHandler(s);
            if ((adUnitViewHandler = viewHandler) != null) {
                if (this._viewHandlers == null) {
                    this._viewHandlers = new HashMap<String, IAdUnitViewHandler>();
                }
                this._viewHandlers.put(s, viewHandler);
                return viewHandler;
            }
        }
        return adUnitViewHandler;
    }
    
    public String[] getViews() {
        return this._views;
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (WebViewApp.getCurrentApp() == null) {
            DeviceLog.error("Unity Ads web app is null, closing Unity Ads activity from onCreate");
            this.finish();
            return;
        }
        AdUnit.setAdUnitActivity(this);
        Intent.setActiveActivity(this);
        this.createLayout();
        ViewUtilities.removeViewFromParent((View)this._layout);
        this.addContentView((View)this._layout, this._layout.getLayoutParams());
        AdUnitEvent adUnitEvent;
        if (bundle == null) {
            this._views = this.getIntent().getStringArrayExtra("views");
            this._keyEventList = (ArrayList<Integer>)this.getIntent().getIntegerArrayListExtra("keyEvents");
            if (this.getIntent().hasExtra("orientation")) {
                this._orientation = this.getIntent().getIntExtra("orientation", -1);
            }
            if (this.getIntent().hasExtra("systemUiVisibility")) {
                this._systemUiVisibility = this.getIntent().getIntExtra("systemUiVisibility", 0);
            }
            if (this.getIntent().hasExtra("activityId")) {
                this._activityId = this.getIntent().getIntExtra("activityId", -1);
            }
            if (this.getIntent().hasExtra("displayCutoutMode")) {
                this._displayCutoutMode = this.getIntent().getIntExtra("displayCutoutMode", 0);
            }
            adUnitEvent = AdUnitEvent.ON_CREATE;
        }
        else {
            this._views = bundle.getStringArray("views");
            this._orientation = bundle.getInt("orientation", -1);
            this._systemUiVisibility = bundle.getInt("systemUiVisibility", 0);
            this._keyEventList = (ArrayList<Integer>)bundle.getIntegerArrayList("keyEvents");
            this._keepScreenOn = bundle.getBoolean("keepScreenOn");
            this._activityId = bundle.getInt("activityId", -1);
            this._displayCutoutMode = bundle.getInt("displayCutoutMode", 0);
            this.setKeepScreenOn(this._keepScreenOn);
            adUnitEvent = AdUnitEvent.ON_RESTORE;
        }
        this.setOrientation(this._orientation);
        this.setSystemUiVisibility(this._systemUiVisibility);
        this.setLayoutInDisplayCutoutMode(this._displayCutoutMode);
        if (this._views != null) {
            final String[] views = this._views;
            for (int length = views.length, i = 0; i < length; ++i) {
                final IAdUnitViewHandler viewHandler = this.getViewHandler(views[i]);
                if (viewHandler != null) {
                    viewHandler.onCreate(this, bundle);
                }
            }
        }
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.ADUNIT, adUnitEvent, this._activityId);
    }
    
    protected void onDestroy() {
        super.onDestroy();
        if (WebViewApp.getCurrentApp() == null) {
            if (!this.isFinishing()) {
                DeviceLog.error("Unity Ads web app is null, closing Unity Ads activity from onDestroy");
                this.finish();
            }
            return;
        }
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.ADUNIT, AdUnitEvent.ON_DESTROY, this.isFinishing(), this._activityId);
        if (this._viewHandlers != null) {
            for (final Map.Entry<String, IAdUnitViewHandler> entry : this._viewHandlers.entrySet()) {
                if (entry.getValue() != null) {
                    entry.getValue().onDestroy(this);
                }
            }
        }
        if (AdUnit.getCurrentAdUnitActivityId() == this._activityId) {
            AdUnit.setAdUnitActivity(null);
        }
        Intent.removeActiveActivity(this);
    }
    
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        if (this._keyEventList != null && this._keyEventList.contains(n)) {
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.ADUNIT, AdUnitEvent.KEY_DOWN, n, keyEvent.getEventTime(), keyEvent.getDownTime(), keyEvent.getRepeatCount(), this._activityId);
            return true;
        }
        return false;
    }
    
    protected void onPause() {
        super.onPause();
        if (WebViewApp.getCurrentApp() == null) {
            if (!this.isFinishing()) {
                DeviceLog.error("Unity Ads web app is null, closing Unity Ads activity from onPause");
                this.finish();
            }
            return;
        }
        if (this.isFinishing()) {
            ViewUtilities.removeViewFromParent((View)WebViewApp.getCurrentApp().getWebView());
        }
        if (this._viewHandlers != null) {
            for (final Map.Entry<String, IAdUnitViewHandler> entry : this._viewHandlers.entrySet()) {
                if (entry.getValue() != null) {
                    entry.getValue().onPause(this);
                }
            }
        }
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.ADUNIT, AdUnitEvent.ON_PAUSE, this.isFinishing(), this._activityId);
    }
    
    public void onRequestPermissionsResult(final int n, final String[] array, final int[] array2) {
        try {
            final JSONArray jsonArray = new JSONArray();
            final JSONArray jsonArray2 = new JSONArray();
            for (int length = array.length, i = 0; i < length; ++i) {
                jsonArray.put((Object)array[i]);
            }
            for (int length2 = array2.length, j = 0; j < length2; ++j) {
                jsonArray2.put(array2[j]);
            }
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.PERMISSIONS, PermissionsEvent.PERMISSIONS_RESULT, n, jsonArray, jsonArray2);
        }
        catch (Exception ex) {
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.PERMISSIONS, PermissionsEvent.PERMISSIONS_ERROR, ex.getMessage());
        }
    }
    
    protected void onResume() {
        super.onResume();
        if (WebViewApp.getCurrentApp() == null) {
            if (!this.isFinishing()) {
                DeviceLog.error("Unity Ads web app is null, closing Unity Ads activity from onResume");
                this.finish();
            }
            return;
        }
        this.setViews(this._views);
        if (this._viewHandlers != null) {
            for (final Map.Entry<String, IAdUnitViewHandler> entry : this._viewHandlers.entrySet()) {
                if (entry.getValue() != null) {
                    entry.getValue().onResume(this);
                }
            }
        }
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.ADUNIT, AdUnitEvent.ON_RESUME, this._activityId);
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("orientation", this._orientation);
        bundle.putInt("systemUiVisibility", this._systemUiVisibility);
        bundle.putIntegerArrayList("keyEvents", (ArrayList)this._keyEventList);
        bundle.putBoolean("keepScreenOn", this._keepScreenOn);
        bundle.putStringArray("views", this._views);
        bundle.putInt("activityId", this._activityId);
    }
    
    protected void onStart() {
        super.onStart();
        if (WebViewApp.getCurrentApp() == null) {
            if (!this.isFinishing()) {
                DeviceLog.error("Unity Ads web app is null, closing Unity Ads activity from onStart");
                this.finish();
            }
            return;
        }
        if (this._viewHandlers != null) {
            for (final Map.Entry<String, IAdUnitViewHandler> entry : this._viewHandlers.entrySet()) {
                if (entry.getValue() != null) {
                    entry.getValue().onStart(this);
                }
            }
        }
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.ADUNIT, AdUnitEvent.ON_START, this._activityId);
    }
    
    protected void onStop() {
        super.onStop();
        if (WebViewApp.getCurrentApp() == null) {
            if (!this.isFinishing()) {
                DeviceLog.error("Unity Ads web app is null, closing Unity Ads activity from onStop");
                this.finish();
            }
            return;
        }
        if (this._viewHandlers != null) {
            for (final Map.Entry<String, IAdUnitViewHandler> entry : this._viewHandlers.entrySet()) {
                if (entry.getValue() != null) {
                    entry.getValue().onStop(this);
                }
            }
        }
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.ADUNIT, AdUnitEvent.ON_STOP, this._activityId);
    }
    
    public void onWindowFocusChanged(final boolean b) {
        if (b) {
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.ADUNIT, AdUnitEvent.ON_FOCUS_GAINED, this._activityId);
        }
        else {
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.ADUNIT, AdUnitEvent.ON_FOCUS_LOST, this._activityId);
        }
        super.onWindowFocusChanged(b);
    }
    
    public boolean setKeepScreenOn(final boolean keepScreenOn) {
        this._keepScreenOn = keepScreenOn;
        if (this.getWindow() == null) {
            return false;
        }
        if (keepScreenOn) {
            this.getWindow().addFlags(128);
        }
        else {
            this.getWindow().clearFlags(128);
        }
        return true;
    }
    
    public void setKeyEventList(final ArrayList<Integer> keyEventList) {
        this._keyEventList = keyEventList;
    }
    
    public void setLayoutInDisplayCutoutMode(final int displayCutoutMode) {
        this._displayCutoutMode = displayCutoutMode;
        if (Build$VERSION.SDK_INT < 28 || this.getWindow() == null) {
            return;
        }
        final WindowManager$LayoutParams attributes = this.getWindow().getAttributes();
        try {
            attributes.getClass().getField("layoutInDisplayCutoutMode").setInt(attributes, displayCutoutMode);
        }
        catch (IllegalAccessException ex) {
            DeviceLog.debug("Error setting layoutInDisplayCutoutMode", ex);
        }
        catch (NoSuchFieldException ex2) {
            DeviceLog.debug("Error getting layoutInDisplayCutoutMode", ex2);
        }
    }
    
    public void setOrientation(final int orientation) {
        this.setRequestedOrientation(this._orientation = orientation);
    }
    
    public boolean setSystemUiVisibility(final int n) {
        boolean b = false;
        this._systemUiVisibility = n;
        if (Build$VERSION.SDK_INT < 11) {
            return b;
        }
        try {
            this.getWindow().getDecorView().setSystemUiVisibility(n);
            b = true;
            return b;
        }
        catch (Exception ex) {
            DeviceLog.exception("Error while setting SystemUIVisibility", ex);
            return false;
        }
    }
    
    public void setViewFrame(final String s, final int n, final int n2, final int n3, final int n4) {
        final IAdUnitViewHandler viewHandler = this.getViewHandler(s);
        final View view = null;
        View view2;
        if (s.equals("adunit")) {
            final FrameLayout$LayoutParams layoutParams = new FrameLayout$LayoutParams(n3, n4);
            layoutParams.setMargins(n, n2, 0, 0);
            this._layout.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
            view2 = view;
        }
        else {
            view2 = view;
            if (viewHandler != null) {
                view2 = viewHandler.getView();
            }
        }
        if (view2 != null) {
            final RelativeLayout$LayoutParams layoutParams2 = new RelativeLayout$LayoutParams(n3, n4);
            layoutParams2.setMargins(n, n2, 0, 0);
            view2.setLayoutParams((ViewGroup$LayoutParams)layoutParams2);
        }
    }
    
    public void setViews(String[] views) {
        int i = 0;
        if (views == null) {
            views = new String[0];
        }
        final ArrayList<Object> list = new ArrayList<Object>(Arrays.asList(views));
        if (this._views == null) {
            this._views = new String[0];
        }
        final ArrayList<String> list2 = new ArrayList<String>(Arrays.asList(this._views));
        list2.removeAll(list);
        final Iterator<String> iterator = list2.iterator();
        while (iterator.hasNext()) {
            this.getViewHandler(iterator.next()).destroy();
        }
        this._views = views;
        while (i < views.length) {
            final String s = views[i];
            if (s != null) {
                final IAdUnitViewHandler viewHandler = this.getViewHandler(s);
                viewHandler.create(this);
                if (!this.handleViewPlacement(viewHandler.getView())) {
                    break;
                }
            }
            ++i;
        }
    }
}
