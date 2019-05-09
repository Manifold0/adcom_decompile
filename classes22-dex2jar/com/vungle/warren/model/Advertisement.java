// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren.model;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import android.text.TextUtils;
import android.support.annotation.Nullable;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import com.vungle.warren.persistence.MemoryUtils;
import java.nio.ByteBuffer;
import com.google.gson.JsonArray;
import java.util.Iterator;
import java.util.Locale;
import java.util.List;
import java.util.Collections;
import java.util.HashMap;
import com.google.gson.JsonElement;
import android.support.annotation.NonNull;
import com.google.gson.JsonObject;
import java.util.Map;
import java.util.ArrayList;
import com.vungle.warren.AdConfig;
import com.vungle.warren.persistence.Memorable;

public class Advertisement implements Memorable, Cloneable
{
    public static final int DONE = 3;
    public static final int ERROR = 4;
    public static final String KEY_APP_ICON = "appicon";
    public static final String KEY_POSTROLL = "postroll";
    public static final String KEY_POWERED_BY_VUNGLE = "powervungle";
    public static final String KEY_TEMPLATE = "template";
    public static final String KEY_VIDEO = "video";
    public static final int LANDSCAPE = 1;
    public static final int NEW = 0;
    public static final int PORTRAIT = 0;
    public static final String POSTROLL_UNZIP = "postrollUnzip";
    public static final int READY = 1;
    public static final int ROTATE = 2;
    private static final String TAG = "Advertisement";
    public static final int TYPE_VUNGLE_LOCAL = 0;
    public static final int TYPE_VUNGLE_MRAID = 1;
    public static final int VIEWING = 2;
    private AdConfig adConfig;
    private final String adMarketId;
    private final String adToken;
    @AdType
    private final int adType;
    private final String appID;
    private final String bidToken;
    private final String campaign;
    private final ArrayList<Checkpoint> checkpoints;
    private final String[] clickUrls;
    private final String[] closeUrls;
    private final int countdown;
    private final int ctaClickArea;
    private final String ctaDestinationUrl;
    private final boolean ctaOverlayEnabled;
    private final boolean ctaShowOnClick;
    private final int ctaTimeEnabled;
    private final int ctaTimeShow;
    private final String ctaUrl;
    private final int delay;
    private final boolean enableMoat;
    private final long expireTime;
    private final String identifier;
    private final String md5;
    private final String moatExtraVast;
    private final String[] muteUrls;
    private final String[] postRollClickUrls;
    private final String[] postRollViewUrls;
    private final String postrollBundleUrl;
    private final boolean requiresNonMarketInstall;
    private final int retryCount;
    private final int showCloseDelay;
    private final int showCloseIncentivized;
    private int state;
    private final String templateId;
    private final Map<String, String> templateSettings;
    private final String templateType;
    private final String templateUrl;
    private final String[] unmuteUrls;
    private final int videoHeight;
    private final String videoIdentifier;
    private final String videoUrl;
    private final int videoWidth;
    
    public Advertisement(@NonNull JsonObject jsonObject) throws IllegalArgumentException {
        this.state = 0;
        if (!JsonUtil.hasNonNull((JsonElement)jsonObject, "ad_markup")) {
            throw new IllegalArgumentException("JSON does not contain ad markup!");
        }
        final JsonObject asJsonObject = jsonObject.getAsJsonObject("ad_markup");
        if (!asJsonObject.has("adType")) {
            throw new IllegalArgumentException("Advertisement did not contain an adType!");
        }
        final String asString = asJsonObject.get("adType").getAsString();
        switch (asString) {
            default: {
                throw new IllegalArgumentException("Unknown Ad Type " + asString + "! Please add this ad type");
            }
            case "vungle_local": {
                this.adType = 0;
                String asString2;
                if (JsonUtil.hasNonNull((JsonElement)asJsonObject, "postBundle")) {
                    asString2 = asJsonObject.get("postBundle").getAsString();
                }
                else {
                    asString2 = "";
                }
                this.postrollBundleUrl = asString2;
                this.templateSettings = new HashMap<String, String>();
                this.templateUrl = "";
                this.templateId = "";
                this.templateType = "";
                break;
            }
            case "vungle_mraid": {
                this.adType = 1;
                this.postrollBundleUrl = "";
                if (!JsonUtil.hasNonNull((JsonElement)asJsonObject, "templateSettings")) {
                    throw new IllegalArgumentException("Missing template adConfig!");
                }
                this.templateSettings = new HashMap<String, String>();
                jsonObject = asJsonObject.getAsJsonObject("templateSettings");
                if (JsonUtil.hasNonNull((JsonElement)jsonObject, "normal_replacements")) {
                    for (final Map.Entry<String, V> entry : jsonObject.getAsJsonObject("normal_replacements").entrySet()) {
                        this.templateSettings.put(entry.getKey(), ((JsonElement)entry.getValue()).getAsString());
                    }
                }
                if (JsonUtil.hasNonNull((JsonElement)jsonObject, "cacheable_replacements")) {
                    for (final Map.Entry<K, JsonElement> entry2 : jsonObject.getAsJsonObject("cacheable_replacements").entrySet()) {
                        if (JsonUtil.hasNonNull(entry2.getValue(), "url")) {
                            this.templateSettings.put((String)entry2.getKey(), entry2.getValue().getAsJsonObject().get("url").getAsString());
                        }
                    }
                }
                if (!JsonUtil.hasNonNull((JsonElement)asJsonObject, "templateId")) {
                    throw new IllegalArgumentException("Missing templateID!");
                }
                this.templateId = asJsonObject.get("templateId").getAsString();
                if (!JsonUtil.hasNonNull((JsonElement)asJsonObject, "template_type")) {
                    throw new IllegalArgumentException("Template Type missing!");
                }
                this.templateType = asJsonObject.get("template_type").getAsString();
                if (JsonUtil.hasNonNull((JsonElement)asJsonObject, "templateURL")) {
                    this.templateUrl = asJsonObject.get("templateURL").getAsString();
                    break;
                }
                throw new IllegalArgumentException("Template URL missing!");
            }
        }
        if (!JsonUtil.hasNonNull((JsonElement)asJsonObject, "id")) {
            throw new IllegalArgumentException("Missing identifier, cannot process advertisement!");
        }
        this.identifier = asJsonObject.get("id").getAsString();
        if (!JsonUtil.hasNonNull((JsonElement)asJsonObject, "campaign")) {
            throw new IllegalArgumentException("Missing campaign information, cannot process advertisement!");
        }
        this.campaign = asJsonObject.get("campaign").getAsString();
        if (!JsonUtil.hasNonNull((JsonElement)asJsonObject, "app_id")) {
            throw new IllegalArgumentException("Missing app Id, cannot process advertisement!");
        }
        this.appID = asJsonObject.get("app_id").getAsString();
        if (JsonUtil.hasNonNull((JsonElement)asJsonObject, "expiry") && !asJsonObject.get("expiry").isJsonNull()) {
            final long asLong = asJsonObject.get("expiry").getAsLong();
            if (asLong > 0L) {
                this.expireTime = asLong;
            }
            else {
                this.expireTime = System.currentTimeMillis() / 1000L;
            }
        }
        else {
            this.expireTime = System.currentTimeMillis() / 1000L;
        }
        if (JsonUtil.hasNonNull((JsonElement)asJsonObject, "tpat")) {
            final JsonObject asJsonObject2 = asJsonObject.getAsJsonObject("tpat");
            this.checkpoints = new ArrayList<Checkpoint>(5);
            switch (this.adType) {
                default: {
                    throw new IllegalArgumentException("Unknown Ad Type!");
                }
                case 0: {
                    if (asJsonObject2.has("play_percentage")) {
                        final JsonArray asJsonArray = asJsonObject2.getAsJsonArray("play_percentage");
                        for (int i = 0; i < asJsonArray.size(); ++i) {
                            this.checkpoints.add(new Checkpoint(asJsonArray.get(i).getAsJsonObject()));
                        }
                        Collections.sort(this.checkpoints);
                        break;
                    }
                    break;
                }
                case 1: {
                    for (int j = 0; j < 5; ++j) {
                        final int n2 = j * 25;
                        final String format = String.format(Locale.ENGLISH, "checkpoint.%d", n2);
                        Checkpoint checkpoint = null;
                        if (asJsonObject2.has(format)) {
                            checkpoint = new Checkpoint(asJsonObject2.getAsJsonArray(format), (byte)n2);
                        }
                        this.checkpoints.add(j, checkpoint);
                    }
                    break;
                }
            }
            if (asJsonObject2.has("clickUrl")) {
                final JsonArray asJsonArray2 = asJsonObject2.getAsJsonArray("clickUrl");
                this.clickUrls = new String[asJsonArray2.size()];
                int n3 = 0;
                final Iterator iterator3 = asJsonArray2.iterator();
                while (iterator3.hasNext()) {
                    this.clickUrls[n3] = iterator3.next().getAsString();
                    ++n3;
                }
            }
            else {
                this.clickUrls = new String[0];
            }
            if (asJsonObject2.has("moat")) {
                jsonObject = asJsonObject2.getAsJsonObject("moat");
                this.enableMoat = jsonObject.get("is_enabled").getAsBoolean();
                this.moatExtraVast = jsonObject.get("extra_vast").getAsString();
            }
            else {
                this.enableMoat = false;
                this.moatExtraVast = "";
            }
            String s = null;
            String s2 = null;
            String s3 = null;
            String s4 = null;
            String s5 = null;
            switch (this.adType) {
                default: {
                    throw new IllegalArgumentException("Unknown AdType!");
                }
                case 0: {
                    s = "mute";
                    s2 = "unmute";
                    s3 = "video_close";
                    s4 = "postroll_click";
                    s5 = "postroll_view";
                    break;
                }
                case 1: {
                    s = "video.mute";
                    s2 = "video.unmute";
                    s3 = "video.close";
                    s4 = "postroll.click";
                    s5 = "postroll.view";
                    break;
                }
            }
            if (JsonUtil.hasNonNull((JsonElement)asJsonObject2, s)) {
                final JsonArray asJsonArray3 = asJsonObject2.getAsJsonArray(s);
                this.muteUrls = new String[asJsonArray3.size()];
                for (int k = 0; k < asJsonArray3.size(); ++k) {
                    if (asJsonArray3.get(k) == null || "null".equalsIgnoreCase(asJsonArray3.get(k).toString())) {
                        this.muteUrls[k] = "";
                    }
                    else {
                        this.muteUrls[k] = asJsonArray3.get(k).getAsString();
                    }
                }
            }
            else {
                this.muteUrls = new String[0];
            }
            if (JsonUtil.hasNonNull((JsonElement)asJsonObject2, s2)) {
                final JsonArray asJsonArray4 = asJsonObject2.getAsJsonArray(s2);
                this.unmuteUrls = new String[asJsonArray4.size()];
                for (int l = 0; l < asJsonArray4.size(); ++l) {
                    if (asJsonArray4.get(l) == null || "null".equalsIgnoreCase(asJsonArray4.get(l).toString())) {
                        this.unmuteUrls[l] = "";
                    }
                    else {
                        this.unmuteUrls[l] = asJsonArray4.get(l).getAsString();
                    }
                }
            }
            else {
                this.unmuteUrls = new String[0];
            }
            if (JsonUtil.hasNonNull((JsonElement)asJsonObject2, s3)) {
                final JsonArray asJsonArray5 = asJsonObject2.getAsJsonArray(s3);
                this.closeUrls = new String[asJsonArray5.size()];
                for (int n4 = 0; n4 < asJsonArray5.size(); ++n4) {
                    if (asJsonArray5.get(n4) == null || "null".equalsIgnoreCase(asJsonArray5.get(n4).toString())) {
                        this.closeUrls[n4] = "";
                    }
                    else {
                        this.closeUrls[n4] = asJsonArray5.get(n4).getAsString();
                    }
                }
            }
            else {
                this.closeUrls = new String[0];
            }
            if (JsonUtil.hasNonNull((JsonElement)asJsonObject2, s4)) {
                final JsonArray asJsonArray6 = asJsonObject2.getAsJsonArray(s4);
                this.postRollClickUrls = new String[asJsonArray6.size()];
                for (int n5 = 0; n5 < asJsonArray6.size(); ++n5) {
                    if (asJsonArray6.get(n5) == null || "null".equalsIgnoreCase(asJsonArray6.get(n5).toString())) {
                        this.postRollClickUrls[n5] = "";
                    }
                    else {
                        this.postRollClickUrls[n5] = asJsonArray6.get(n5).getAsString();
                    }
                }
            }
            else {
                this.postRollClickUrls = new String[0];
            }
            if (JsonUtil.hasNonNull((JsonElement)asJsonObject2, s5)) {
                final JsonArray asJsonArray7 = asJsonObject2.getAsJsonArray(s5);
                this.postRollViewUrls = new String[asJsonArray7.size()];
                for (int n6 = 0; n6 < asJsonArray7.size(); ++n6) {
                    if (asJsonArray7.get(n6) == null || "null".equalsIgnoreCase(asJsonArray7.get(n6).toString())) {
                        this.postRollViewUrls[n6] = "";
                    }
                    else {
                        this.postRollViewUrls[n6] = asJsonArray7.get(n6).getAsString();
                    }
                }
            }
            else {
                this.postRollViewUrls = new String[0];
            }
        }
        else {
            this.checkpoints = new ArrayList<Checkpoint>();
            this.muteUrls = new String[0];
            this.closeUrls = new String[0];
            this.unmuteUrls = new String[0];
            this.postRollViewUrls = new String[0];
            this.postRollClickUrls = new String[0];
            this.clickUrls = new String[0];
            this.enableMoat = false;
            this.moatExtraVast = "";
        }
        if (asJsonObject.has("delay")) {
            this.delay = asJsonObject.get("delay").getAsInt();
        }
        else {
            this.delay = 0;
        }
        if (asJsonObject.has("showClose")) {
            this.showCloseDelay = asJsonObject.get("showClose").getAsInt();
        }
        else {
            this.showCloseDelay = 0;
        }
        if (asJsonObject.has("showCloseIncentivized")) {
            this.showCloseIncentivized = asJsonObject.get("showCloseIncentivized").getAsInt();
        }
        else {
            this.showCloseIncentivized = 0;
        }
        if (asJsonObject.has("countdown")) {
            this.countdown = asJsonObject.get("countdown").getAsInt();
        }
        else {
            this.countdown = 0;
        }
        if (!JsonUtil.hasNonNull((JsonElement)asJsonObject, "url")) {
            throw new IllegalArgumentException("Missing video URL!");
        }
        this.videoUrl = asJsonObject.get("url").getAsString();
        if (!asJsonObject.has("videoWidth")) {
            throw new IllegalArgumentException("Missing video width!");
        }
        this.videoWidth = asJsonObject.get("videoWidth").getAsInt();
        if (!asJsonObject.has("videoHeight")) {
            throw new IllegalArgumentException("Missing video height!");
        }
        this.videoHeight = asJsonObject.get("videoHeight").getAsInt();
        if (asJsonObject.has("md5")) {
            this.md5 = asJsonObject.get("md5").getAsString();
        }
        else {
            this.md5 = "";
        }
        if (asJsonObject.has("cta_overlay")) {
            jsonObject = asJsonObject.getAsJsonObject("cta_overlay");
            if (jsonObject.has("enabled")) {
                this.ctaOverlayEnabled = jsonObject.get("enabled").getAsBoolean();
            }
            else {
                this.ctaOverlayEnabled = false;
            }
            if (jsonObject.has("show_onclick")) {
                this.ctaShowOnClick = jsonObject.get("show_onclick").getAsBoolean();
            }
            else {
                this.ctaShowOnClick = false;
            }
            if (jsonObject.has("time_enabled")) {
                this.ctaTimeEnabled = jsonObject.get("time_enabled").getAsInt();
            }
            else {
                this.ctaTimeEnabled = -1;
            }
            if (jsonObject.has("click_area")) {
                this.ctaClickArea = jsonObject.get("click_area").getAsInt();
            }
            else {
                this.ctaClickArea = -1;
            }
            if (jsonObject.has("time_show")) {
                this.ctaTimeShow = jsonObject.get("time_show").getAsInt();
            }
            else {
                this.ctaTimeShow = -1;
            }
        }
        else {
            this.ctaOverlayEnabled = false;
            this.ctaClickArea = -1;
            this.ctaTimeEnabled = -1;
            this.ctaTimeShow = -1;
            this.ctaShowOnClick = false;
        }
        String asString3;
        if (JsonUtil.hasNonNull((JsonElement)asJsonObject, "callToActionDest")) {
            asString3 = asJsonObject.get("callToActionDest").getAsString();
        }
        else {
            asString3 = null;
        }
        this.ctaDestinationUrl = asString3;
        String asString4;
        if (JsonUtil.hasNonNull((JsonElement)asJsonObject, "callToActionUrl")) {
            asString4 = asJsonObject.get("callToActionUrl").getAsString();
        }
        else {
            asString4 = null;
        }
        this.ctaUrl = asString4;
        if (asJsonObject.has("retryCount")) {
            this.retryCount = asJsonObject.get("retryCount").getAsInt();
        }
        else {
            this.retryCount = 1;
        }
        if (asJsonObject.has("ad_token")) {
            this.adToken = asJsonObject.get("ad_token").getAsString();
            if (asJsonObject.has("video_object_id")) {
                this.videoIdentifier = asJsonObject.get("video_object_id").getAsString();
            }
            else {
                this.videoIdentifier = "";
            }
            if (asJsonObject.has("requires_sideloading")) {
                this.requiresNonMarketInstall = asJsonObject.get("requires_sideloading").getAsBoolean();
            }
            else {
                this.requiresNonMarketInstall = false;
            }
            if (asJsonObject.has("ad_market_id")) {
                this.adMarketId = asJsonObject.get("ad_market_id").getAsString();
            }
            else {
                this.adMarketId = "";
            }
            if (asJsonObject.has("bid_token")) {
                this.bidToken = asJsonObject.get("bid_token").getAsString();
            }
            else {
                this.bidToken = "";
            }
            this.adConfig = new AdConfig();
            return;
        }
        throw new IllegalArgumentException("AdToken missing!");
    }
    
    public Advertisement(final byte[] array) {
        final boolean b = true;
        this.state = 0;
        if (array.length == 0) {
            throw new IllegalArgumentException("Empty array cannot be used to construct Advertisement");
        }
        final ByteBuffer wrap = ByteBuffer.wrap(array);
        this.adConfig = new AdConfig();
        this.adType = wrap.getInt();
        this.expireTime = wrap.getLong();
        this.delay = wrap.getInt();
        this.showCloseDelay = wrap.getInt();
        this.showCloseIncentivized = wrap.getInt();
        this.countdown = wrap.getInt();
        this.videoWidth = wrap.getInt();
        this.videoHeight = wrap.getInt();
        while (true) {
            Label_0662: {
                if (wrap.get() != 1) {
                    break Label_0662;
                }
                final boolean ctaOverlayEnabled = true;
                this.ctaOverlayEnabled = ctaOverlayEnabled;
                this.ctaShowOnClick = (wrap.get() == 1);
                this.ctaTimeEnabled = wrap.getInt();
                this.ctaTimeShow = wrap.getInt();
                this.ctaClickArea = wrap.getInt();
                this.retryCount = wrap.getInt();
                this.enableMoat = (wrap.get() == 1);
                this.requiresNonMarketInstall = (wrap.get() == 1);
                final int int1 = wrap.getInt();
                final int int2 = wrap.getInt();
                final int int3 = wrap.getInt();
                this.adConfig.setFlexViewCloseTime(int2);
                this.adConfig.setOrdinal(int3);
                this.adConfig.setAutoRotate((int1 & 0x10) == 0x10);
                this.adConfig.setImmersiveMode((int1 & 0x4) == 0x4);
                this.adConfig.setMuted((int1 & 0x1) == 0x1);
                final HashMap<String, Object> extraSettings = new HashMap<String, Object>();
                extraSettings.put("direct_download", (int1 & 0x20) == 0x20);
                this.adConfig.setExtraSettings(extraSettings);
                this.adConfig.setTransitionAnimationEnabled((int1 & 0x8) == 0x8 && b);
                this.identifier = MemoryUtils.extractString(wrap);
                this.appID = MemoryUtils.extractString(wrap);
                this.campaign = MemoryUtils.extractString(wrap);
                this.videoUrl = MemoryUtils.extractString(wrap);
                this.md5 = MemoryUtils.extractString(wrap);
                this.postrollBundleUrl = MemoryUtils.extractString(wrap);
                this.ctaDestinationUrl = MemoryUtils.extractString(wrap);
                this.ctaUrl = MemoryUtils.extractString(wrap);
                this.adToken = MemoryUtils.extractString(wrap);
                this.videoIdentifier = MemoryUtils.extractString(wrap);
                this.muteUrls = MemoryUtils.extractStringArray(wrap);
                this.unmuteUrls = MemoryUtils.extractStringArray(wrap);
                this.closeUrls = MemoryUtils.extractStringArray(wrap);
                this.postRollClickUrls = MemoryUtils.extractStringArray(wrap);
                this.postRollViewUrls = MemoryUtils.extractStringArray(wrap);
                this.clickUrls = MemoryUtils.extractStringArray(wrap);
                this.templateUrl = MemoryUtils.extractString(wrap);
                this.templateId = MemoryUtils.extractString(wrap);
                this.templateType = MemoryUtils.extractString(wrap);
                this.moatExtraVast = MemoryUtils.extractString(wrap);
                this.adMarketId = MemoryUtils.extractString(wrap);
                this.bidToken = MemoryUtils.extractString(wrap);
                final int int4 = wrap.getInt();
                this.checkpoints = new ArrayList<Checkpoint>(int4);
                int n = 0;
            Label_0605_Outer:
                while (true) {
                    while (true) {
                        if (n < int4) {
                            try {
                                this.checkpoints.add(MemoryUtils.extractMemorable(wrap, Checkpoint.class));
                                ++n;
                                continue Label_0605_Outer;
                                this.templateSettings = MemoryUtils.extractStringMap(wrap);
                                this.state = wrap.getInt();
                                return;
                            }
                            catch (NoSuchMethodException ex) {
                                throw new RuntimeException(ex);
                            }
                            catch (IllegalAccessException ex2) {
                                throw new RuntimeException(ex2);
                            }
                            catch (InstantiationException ex3) {
                                throw new RuntimeException(ex3);
                            }
                            catch (InvocationTargetException ex4) {
                                throw new RuntimeException(ex4);
                            }
                            break;
                        }
                        continue;
                    }
                }
            }
            final boolean ctaOverlayEnabled = false;
            continue;
        }
    }
    
    public void configure(final AdConfig adConfig) {
        if (adConfig == null) {
            this.adConfig = new AdConfig();
            return;
        }
        this.adConfig = adConfig;
    }
    
    public Advertisement copy() {
        try {
            return (Advertisement)this.clone();
        }
        catch (CloneNotSupportedException ex) {
            Log.e("Advertisement", Log.getStackTraceString((Throwable)ex));
            return null;
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof Advertisement)) {
            return false;
        }
        final Advertisement advertisement = (Advertisement)o;
        if (this.getId() == null) {
            return false;
        }
        if (advertisement.getId() == null) {
            return false;
        }
        if (!advertisement.getId().equals(this.getId())) {
            return false;
        }
        if (advertisement.adType != this.adType) {
            return false;
        }
        if (advertisement.expireTime != this.expireTime) {
            return false;
        }
        if (advertisement.delay != this.delay) {
            return false;
        }
        if (advertisement.showCloseDelay != this.showCloseDelay) {
            return false;
        }
        if (advertisement.showCloseIncentivized != this.showCloseIncentivized) {
            return false;
        }
        if (advertisement.countdown != this.countdown) {
            return false;
        }
        if (advertisement.videoWidth != this.videoWidth) {
            return false;
        }
        if (advertisement.videoHeight != this.videoHeight) {
            return false;
        }
        if (advertisement.ctaOverlayEnabled != this.ctaOverlayEnabled) {
            return false;
        }
        if (advertisement.ctaShowOnClick != this.ctaShowOnClick) {
            return false;
        }
        if (advertisement.ctaTimeEnabled != this.ctaTimeEnabled) {
            return false;
        }
        if (advertisement.ctaTimeShow != this.ctaTimeShow) {
            return false;
        }
        if (advertisement.ctaClickArea != this.ctaClickArea) {
            return false;
        }
        if (advertisement.retryCount != this.retryCount) {
            return false;
        }
        if (advertisement.enableMoat != this.enableMoat) {
            return false;
        }
        if (advertisement.requiresNonMarketInstall != this.requiresNonMarketInstall) {
            return false;
        }
        if (!advertisement.identifier.equals(this.identifier)) {
            return false;
        }
        if (!advertisement.campaign.equals(this.campaign)) {
            return false;
        }
        if (!advertisement.videoUrl.equals(this.videoUrl)) {
            return false;
        }
        if (!advertisement.md5.equals(this.md5)) {
            return false;
        }
        if (!advertisement.postrollBundleUrl.equals(this.postrollBundleUrl)) {
            return false;
        }
        if (!advertisement.ctaDestinationUrl.equals(this.ctaDestinationUrl)) {
            return false;
        }
        if (!advertisement.ctaUrl.equals(this.ctaUrl)) {
            return false;
        }
        if (!advertisement.adToken.equals(this.adToken)) {
            return false;
        }
        if (!advertisement.videoIdentifier.equals(this.videoIdentifier)) {
            return false;
        }
        if (!advertisement.moatExtraVast.equals(this.moatExtraVast)) {
            return false;
        }
        if (advertisement.state != this.state) {
            return false;
        }
        if (advertisement.muteUrls.length != this.muteUrls.length) {
            return false;
        }
        for (int i = 0; i < this.muteUrls.length; ++i) {
            if (!advertisement.muteUrls[i].equals(this.muteUrls[i])) {
                return false;
            }
        }
        if (advertisement.unmuteUrls.length != this.unmuteUrls.length) {
            return false;
        }
        for (int j = 0; j < this.unmuteUrls.length; ++j) {
            if (!advertisement.unmuteUrls[j].equals(this.unmuteUrls[j])) {
                return false;
            }
        }
        if (advertisement.closeUrls.length != this.closeUrls.length) {
            return false;
        }
        for (int k = 0; k < this.closeUrls.length; ++k) {
            if (!advertisement.closeUrls[k].equals(this.closeUrls[k])) {
                return false;
            }
        }
        if (advertisement.postRollClickUrls.length != this.postRollClickUrls.length) {
            return false;
        }
        for (int l = 0; l < this.postRollClickUrls.length; ++l) {
            if (!advertisement.postRollClickUrls[l].equals(this.postRollClickUrls[l])) {
                return false;
            }
        }
        if (advertisement.postRollViewUrls.length != this.postRollViewUrls.length) {
            return false;
        }
        for (int n = 0; n < this.postRollViewUrls.length; ++n) {
            if (!advertisement.postRollViewUrls[n].equals(this.postRollViewUrls[n])) {
                return false;
            }
        }
        if (advertisement.checkpoints.size() != this.checkpoints.size()) {
            return false;
        }
        for (int n2 = 0; n2 < this.checkpoints.size(); ++n2) {
            if (!advertisement.checkpoints.get(n2).equals(this.checkpoints.get(n2))) {
                return false;
            }
        }
        return advertisement.adMarketId.equals(this.adMarketId) && advertisement.bidToken.equals(this.bidToken);
    }
    
    public AdConfig getAdConfig() {
        return this.adConfig;
    }
    
    public String getAdMarketId() {
        return this.adMarketId;
    }
    
    public String getAdToken() {
        return this.adToken;
    }
    
    @AdType
    public int getAdType() {
        return this.adType;
    }
    
    public String getAppID() {
        return this.appID;
    }
    
    public String getBidToken() {
        return this.bidToken;
    }
    
    @Nullable
    public String getCTAURL(final boolean b) {
        switch (this.adType) {
            default: {
                throw new IllegalArgumentException("Unknown AdType " + this.adType);
            }
            case 0: {
                if (b) {
                    return this.ctaUrl;
                }
                return this.ctaDestinationUrl;
            }
            case 1: {
                return this.ctaUrl;
            }
        }
    }
    
    public String getCampaign() {
        return this.campaign;
    }
    
    public List<Checkpoint> getCheckpoints() {
        return this.checkpoints;
    }
    
    public int getCtaClickArea() {
        int ctaClickArea = 1;
        if (this.ctaClickArea >= 1) {
            ctaClickArea = this.ctaClickArea;
        }
        return ctaClickArea;
    }
    
    public int getCtaTimeEnabled() {
        return this.ctaTimeEnabled * 1000;
    }
    
    public int getCtaTimeShow() {
        return this.ctaTimeShow * 1000;
    }
    
    public Map<String, String> getDownloadableUrls() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        switch (this.adType) {
            default: {
                throw new IllegalStateException("Advertisement created without adType!");
            }
            case 0: {
                hashMap.put("video", this.videoUrl);
                if (!TextUtils.isEmpty((CharSequence)this.postrollBundleUrl)) {
                    hashMap.put("postroll", this.postrollBundleUrl);
                    break;
                }
                break;
            }
            case 1: {
                hashMap.put("template", this.templateUrl);
                if (this.templateSettings.containsKey("MAIN_VIDEO")) {
                    hashMap.put("video", this.templateSettings.get("MAIN_VIDEO"));
                }
                if (this.templateSettings.containsKey("APP_ICON")) {
                    hashMap.put("appicon", this.templateSettings.get("APP_ICON"));
                }
                if (this.templateSettings.containsKey("POWERED_BY_VUNGLE")) {
                    hashMap.put("powervungle", this.templateSettings.get("POWERED_BY_VUNGLE"));
                    return hashMap;
                }
                break;
            }
        }
        return hashMap;
    }
    
    public long getExpireTime() {
        return this.expireTime * 1000L;
    }
    
    @NonNull
    @Override
    public String getId() {
        if (this.identifier == null) {
            return "";
        }
        return this.identifier;
    }
    
    public JsonObject getMRAIDArgs() {
        if (this.templateSettings == null) {
            throw new IllegalArgumentException("Advertisment does not have MRAID Arguments!");
        }
        final JsonObject jsonObject = new JsonObject();
        for (final Map.Entry<String, String> entry : this.templateSettings.entrySet()) {
            jsonObject.addProperty((String)entry.getKey(), (String)entry.getValue());
        }
        return jsonObject;
    }
    
    public boolean getMoatEnabled() {
        return this.enableMoat;
    }
    
    public String getMoatVastExtra() {
        return this.moatExtraVast;
    }
    
    @Orientation
    public int getOrientation() {
        if (this.videoWidth > this.videoHeight) {
            return 1;
        }
        return 0;
    }
    
    public int getShowCloseDelay(final boolean b) {
        if (b) {
            return this.showCloseIncentivized * 1000;
        }
        return this.showCloseDelay * 1000;
    }
    
    @State
    public int getState() {
        return this.state;
    }
    
    String getTemplateId() {
        return this.templateId;
    }
    
    public String getTemplateType() {
        return this.templateType;
    }
    
    public String[] getTpatUrls(@NonNull final String s) {
        int n = -1;
        final int n2 = 0;
        String[] postRollViewUrls = null;
        Label_0267: {
            switch (this.adType) {
                default: {
                    throw new IllegalStateException("Unknown Advertisement Type!");
                }
                case 0: {
                    switch (s.hashCode()) {
                        case 1666667655: {
                            if (s.equals("postroll_view")) {
                                n = 0;
                                break;
                            }
                            break;
                        }
                        case 109635558: {
                            if (s.equals("postroll_click")) {
                                n = 1;
                                break;
                            }
                            break;
                        }
                        case 3363353: {
                            if (s.equals("mute")) {
                                n = 2;
                                break;
                            }
                            break;
                        }
                        case -840405966: {
                            if (s.equals("unmute")) {
                                n = 3;
                                break;
                            }
                            break;
                        }
                        case 1370606900: {
                            if (s.equals("video_close")) {
                                n = 4;
                                break;
                            }
                            break;
                        }
                        case -1964722632: {
                            if (s.equals("click_url")) {
                                n = 5;
                                break;
                            }
                            break;
                        }
                    }
                    switch (n) {
                        default: {
                            throw new IllegalArgumentException("Unknown TPAT Event " + s);
                        }
                        case 0: {
                            postRollViewUrls = this.postRollViewUrls;
                            break Label_0267;
                        }
                        case 1: {
                            return this.postRollClickUrls;
                        }
                        case 2: {
                            return this.muteUrls;
                        }
                        case 3: {
                            return this.unmuteUrls;
                        }
                        case 4: {
                            return this.closeUrls;
                        }
                        case 5: {
                            return this.clickUrls;
                        }
                    }
                    break;
                }
                case 1: {
                    if (s.startsWith("checkpoint")) {
                        final String[] array = new String[0];
                        final Checkpoint checkpoint = this.checkpoints.get(Integer.parseInt(s.split("\\.")[1]) / 25);
                        postRollViewUrls = array;
                        if (checkpoint != null) {
                            return checkpoint.getUrls();
                        }
                        break;
                    }
                    else {
                        int n3 = 0;
                        Label_0418: {
                            switch (s.hashCode()) {
                                case -32221499: {
                                    if (s.equals("video.close")) {
                                        n3 = n2;
                                        break Label_0418;
                                    }
                                    break;
                                }
                                case 1621415126: {
                                    if (s.equals("postroll.view")) {
                                        n3 = 1;
                                        break Label_0418;
                                    }
                                    break;
                                }
                                case -1293192841: {
                                    if (s.equals("postroll.click")) {
                                        n3 = 2;
                                        break Label_0418;
                                    }
                                    break;
                                }
                                case 906443463: {
                                    if (s.equals("clickUrl")) {
                                        n3 = 3;
                                        break Label_0418;
                                    }
                                    break;
                                }
                                case -1663300692: {
                                    if (s.equals("video.mute")) {
                                        n3 = 4;
                                        break Label_0418;
                                    }
                                    break;
                                }
                                case -481751803: {
                                    if (s.equals("video.unmute")) {
                                        n3 = 5;
                                        break Label_0418;
                                    }
                                    break;
                                }
                            }
                            n3 = -1;
                        }
                        switch (n3) {
                            default: {
                                throw new IllegalArgumentException("Unknown TPAT Event " + s);
                            }
                            case 0: {
                                return this.closeUrls;
                            }
                            case 1: {
                                return this.postRollViewUrls;
                            }
                            case 2: {
                                return this.postRollClickUrls;
                            }
                            case 3: {
                                return this.clickUrls;
                            }
                            case 4: {
                                return this.muteUrls;
                            }
                            case 5: {
                                return this.unmuteUrls;
                            }
                        }
                    }
                    break;
                }
            }
        }
        return postRollViewUrls;
    }
    
    String getUrl() {
        return this.videoUrl;
    }
    
    public boolean hasPostroll() {
        return !TextUtils.isEmpty((CharSequence)this.postrollBundleUrl);
    }
    
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    
    public boolean isCtaOverlayEnabled() {
        return this.ctaOverlayEnabled;
    }
    
    public boolean isCtaShowOnClick() {
        return this.ctaShowOnClick;
    }
    
    public boolean isRequiresNonMarketInstall() {
        return this.requiresNonMarketInstall;
    }
    
    public void setMraidAssetDir(File file) {
        final File file2 = new File(file, "video");
        if (file2.exists()) {
            this.templateSettings.put("MAIN_VIDEO", "file://" + file2.getPath());
        }
        final File file3 = new File(file, "appicon");
        if (file3.exists()) {
            this.templateSettings.put("APP_ICON", "file://" + file3.getPath());
        }
        file = new File(file, "powervungle");
        if (file.exists()) {
            this.templateSettings.put("POWERED_BY_VUNGLE", "file://" + file.getPath());
        }
    }
    
    public void setState(@State final int state) {
        this.state = state;
    }
    
    @Override
    public byte[] toByteArray() {
        int n;
        ByteArrayOutputStream byteArrayOutputStream = null;
        int n2;
        int n3;
        int n4;
        int n5;
        Iterator<Checkpoint> iterator;
        Label_0121_Outer:Label_0179_Outer:Label_0193_Outer:
        while (true) {
            n = 1;
            byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
            Label_0519:
                while (true) {
                Label_0514:
                    while (true) {
                    Label_0509:
                        while (true) {
                            try {
                                byteArrayOutputStream.write(MemoryUtils.toBytes(this.adType));
                                byteArrayOutputStream.write(MemoryUtils.toBytes(this.expireTime));
                                byteArrayOutputStream.write(MemoryUtils.toBytes(this.delay));
                                byteArrayOutputStream.write(MemoryUtils.toBytes(this.showCloseDelay));
                                byteArrayOutputStream.write(MemoryUtils.toBytes(this.showCloseIncentivized));
                                byteArrayOutputStream.write(MemoryUtils.toBytes(this.countdown));
                                byteArrayOutputStream.write(MemoryUtils.toBytes(this.videoWidth));
                                byteArrayOutputStream.write(MemoryUtils.toBytes(this.videoHeight));
                                if (this.ctaOverlayEnabled) {
                                    n2 = 1;
                                    byteArrayOutputStream.write(n2);
                                    if (!this.ctaShowOnClick) {
                                        break Label_0509;
                                    }
                                    n3 = 1;
                                    byteArrayOutputStream.write(n3);
                                    byteArrayOutputStream.write(MemoryUtils.toBytes(this.ctaTimeEnabled));
                                    byteArrayOutputStream.write(MemoryUtils.toBytes(this.ctaTimeShow));
                                    byteArrayOutputStream.write(MemoryUtils.toBytes(this.ctaClickArea));
                                    byteArrayOutputStream.write(MemoryUtils.toBytes(this.retryCount));
                                    if (!this.enableMoat) {
                                        break Label_0514;
                                    }
                                    n4 = 1;
                                    byteArrayOutputStream.write(n4);
                                    if (this.requiresNonMarketInstall) {
                                        n5 = n;
                                        byteArrayOutputStream.write(n5);
                                        byteArrayOutputStream.write(MemoryUtils.toBytes(this.adConfig.getSettings()));
                                        byteArrayOutputStream.write(MemoryUtils.toBytes(this.adConfig.getFlexViewCloseTime()));
                                        byteArrayOutputStream.write(MemoryUtils.toBytes(this.adConfig.getOrdinal()));
                                        MemoryUtils.writeString(this.identifier, byteArrayOutputStream);
                                        MemoryUtils.writeString(this.appID, byteArrayOutputStream);
                                        MemoryUtils.writeString(this.campaign, byteArrayOutputStream);
                                        MemoryUtils.writeString(this.videoUrl, byteArrayOutputStream);
                                        MemoryUtils.writeString(this.md5, byteArrayOutputStream);
                                        MemoryUtils.writeString(this.postrollBundleUrl, byteArrayOutputStream);
                                        MemoryUtils.writeString(this.ctaDestinationUrl, byteArrayOutputStream);
                                        MemoryUtils.writeString(this.ctaUrl, byteArrayOutputStream);
                                        MemoryUtils.writeString(this.adToken, byteArrayOutputStream);
                                        MemoryUtils.writeString(this.videoIdentifier, byteArrayOutputStream);
                                        MemoryUtils.writeStringArray(this.muteUrls, byteArrayOutputStream);
                                        MemoryUtils.writeStringArray(this.unmuteUrls, byteArrayOutputStream);
                                        MemoryUtils.writeStringArray(this.closeUrls, byteArrayOutputStream);
                                        MemoryUtils.writeStringArray(this.postRollClickUrls, byteArrayOutputStream);
                                        MemoryUtils.writeStringArray(this.postRollViewUrls, byteArrayOutputStream);
                                        MemoryUtils.writeStringArray(this.clickUrls, byteArrayOutputStream);
                                        MemoryUtils.writeString(this.templateUrl, byteArrayOutputStream);
                                        MemoryUtils.writeString(this.templateId, byteArrayOutputStream);
                                        MemoryUtils.writeString(this.templateType, byteArrayOutputStream);
                                        MemoryUtils.writeString(this.moatExtraVast, byteArrayOutputStream);
                                        MemoryUtils.writeString(this.adMarketId, byteArrayOutputStream);
                                        MemoryUtils.writeString(this.bidToken, byteArrayOutputStream);
                                        byteArrayOutputStream.write(MemoryUtils.toBytes(this.checkpoints.size()));
                                        iterator = this.checkpoints.iterator();
                                        while (iterator.hasNext()) {
                                            MemoryUtils.writeMemorable(iterator.next(), byteArrayOutputStream);
                                        }
                                        break;
                                    }
                                    break Label_0519;
                                }
                            }
                            catch (IOException ex) {
                                Log.e("Advertisement.java", "Failed to write " + this + " to a byte array");
                                return new byte[0];
                            }
                            n2 = 0;
                            continue Label_0121_Outer;
                        }
                        n3 = 0;
                        continue Label_0179_Outer;
                    }
                    n4 = 0;
                    continue Label_0193_Outer;
                }
                n5 = 0;
                continue;
            }
        }
        MemoryUtils.writeStringMap(this.templateSettings, byteArrayOutputStream);
        byteArrayOutputStream.write(MemoryUtils.toBytes(this.state));
        return byteArrayOutputStream.toByteArray();
    }
    
    @Override
    public String toString() {
        return "Advertisement{adType=" + this.adType + ", identifier='" + this.identifier + '\'' + ", appID='" + this.appID + '\'' + ", expireTime=" + this.expireTime + ", checkpoints=" + this.checkpoints + ", muteUrls=" + Arrays.toString(this.muteUrls) + ", unmuteUrls=" + Arrays.toString(this.unmuteUrls) + ", closeUrls=" + Arrays.toString(this.closeUrls) + ", postRollClickUrls=" + Arrays.toString(this.postRollClickUrls) + ", postRollViewUrls=" + Arrays.toString(this.postRollViewUrls) + ", clickUrls=" + Arrays.toString(this.clickUrls) + ", delay=" + this.delay + ", campaign='" + this.campaign + '\'' + ", showCloseDelay=" + this.showCloseDelay + ", showCloseIncentivized=" + this.showCloseIncentivized + ", countdown=" + this.countdown + ", videoUrl='" + this.videoUrl + '\'' + ", videoWidth=" + this.videoWidth + ", videoHeight=" + this.videoHeight + ", md5='" + this.md5 + '\'' + ", postrollBundleUrl='" + this.postrollBundleUrl + '\'' + ", ctaOverlayEnabled=" + this.ctaOverlayEnabled + ", ctaShowOnClick=" + this.ctaShowOnClick + ", ctaTimeEnabled=" + this.ctaTimeEnabled + ", ctaTimeShow=" + this.ctaTimeShow + ", ctaClickArea=" + this.ctaClickArea + ", ctaDestinationUrl='" + this.ctaDestinationUrl + '\'' + ", ctaUrl='" + this.ctaUrl + '\'' + ", adConfig=" + this.adConfig + ", retryCount=" + this.retryCount + ", adToken='" + this.adToken + '\'' + ", videoIdentifier='" + this.videoIdentifier + '\'' + ", templateUrl='" + this.templateUrl + '\'' + ", templateSettings=" + this.templateSettings + ", templateId='" + this.templateId + '\'' + ", templateType='" + this.templateType + '\'' + ", enableMoat=" + this.enableMoat + ", moatExtraVast='" + this.moatExtraVast + '\'' + ", requiresNonMarketInstall=" + this.requiresNonMarketInstall + ", adMarketId='" + this.adMarketId + '\'' + ", bidToken='" + this.bidToken + '\'' + ", state=" + this.state + '}';
    }
    
    public @interface AdType {
    }
    
    public @interface CacheKey {
    }
    
    public static class Checkpoint implements Comparable<Checkpoint>, Memorable
    {
        private final byte percentage;
        private final String[] urls;
        
        public Checkpoint(final JsonArray jsonArray, final byte percentage) {
            if (jsonArray.size() == 0) {
                throw new IllegalArgumentException("Empty URLS!");
            }
            this.urls = new String[jsonArray.size()];
            for (int i = 0; i < jsonArray.size(); ++i) {
                this.urls[i] = jsonArray.get(i).getAsString();
            }
            this.percentage = percentage;
        }
        
        public Checkpoint(final JsonObject jsonObject) throws IllegalArgumentException {
            if (!jsonObject.has("checkpoint")) {
                throw new IllegalArgumentException("Checkpoint missing percentage!");
            }
            this.percentage = (byte)(jsonObject.get("checkpoint").getAsFloat() * 100.0f);
            if (JsonUtil.hasNonNull((JsonElement)jsonObject, "urls")) {
                final JsonArray asJsonArray = jsonObject.getAsJsonArray("urls");
                this.urls = new String[asJsonArray.size()];
                for (int i = 0; i < asJsonArray.size(); ++i) {
                    if (asJsonArray.get(i) == null || "null".equalsIgnoreCase(asJsonArray.get(i).toString())) {
                        this.urls[i] = "";
                    }
                    else {
                        this.urls[i] = asJsonArray.get(i).getAsString();
                    }
                }
                return;
            }
            throw new IllegalArgumentException("Checkpoint missing reporting URL!");
        }
        
        public Checkpoint(final byte[] array) {
            final ByteBuffer wrap = ByteBuffer.wrap(array);
            this.percentage = wrap.get();
            this.urls = MemoryUtils.extractStringArray(wrap);
        }
        
        @Override
        public int compareTo(@NonNull final Checkpoint checkpoint) {
            return Float.compare(this.percentage, checkpoint.percentage);
        }
        
        @Override
        public boolean equals(final Object o) {
            if (o instanceof Checkpoint) {
                final Checkpoint checkpoint = (Checkpoint)o;
                if (checkpoint.percentage == this.percentage && checkpoint.urls.length == this.urls.length) {
                    for (int i = 0; i < this.urls.length; ++i) {
                        if (!checkpoint.urls[i].equals(this.urls[i])) {
                            return false;
                        }
                    }
                    return true;
                }
            }
            return false;
        }
        
        @NonNull
        @Override
        public String getId() {
            return "checkpoint";
        }
        
        public byte getPercentage() {
            return this.percentage;
        }
        
        public String[] getUrls() {
            return this.urls;
        }
        
        @Override
        public int hashCode() {
            return super.hashCode();
        }
        
        @Override
        public byte[] toByteArray() {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                byteArrayOutputStream.write(this.percentage);
                MemoryUtils.writeStringArray(this.urls, byteArrayOutputStream);
                return byteArrayOutputStream.toByteArray();
            }
            catch (IOException ex) {
                Log.e("Advertisement.java", "Failed to write " + this + " to a byte array");
                return new byte[0];
            }
        }
    }
    
    public @interface Orientation {
    }
    
    public @interface State {
    }
}
