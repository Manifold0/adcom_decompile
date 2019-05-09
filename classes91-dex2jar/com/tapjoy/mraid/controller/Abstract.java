// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.mraid.controller;

import java.lang.reflect.Field;
import com.tapjoy.mraid.util.TransitionStringEnum;
import com.tapjoy.mraid.util.NavigationStringEnum;
import android.os.Parcelable;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.content.Context;
import com.tapjoy.mraid.view.MraidView;

public abstract class Abstract
{
    public static final String EXIT = "exit";
    public static final String FULL_SCREEN = "fullscreen";
    public static final String STYLE_NORMAL = "normal";
    protected MraidView a;
    protected Context b;
    
    public Abstract(final MraidView a, final Context b) {
        this.a = a;
        this.b = b;
    }
    
    public abstract void stopAllListeners();
    
    public static class Dimensions extends ReflectedParcelable
    {
        public static final Parcelable$Creator CREATOR;
        public int height;
        public int width;
        public int x;
        public int y;
        
        static {
            CREATOR = (Parcelable$Creator)new Parcelable$Creator() {};
        }
        
        public Dimensions() {
            this.x = -1;
            this.y = -1;
            this.width = -1;
            this.height = -1;
        }
        
        protected Dimensions(final Parcel parcel) {
            super(parcel);
        }
    }
    
    public static class PlayerProperties extends ReflectedParcelable
    {
        public static final Parcelable$Creator CREATOR;
        public boolean audioMuted;
        public boolean autoPlay;
        public boolean doLoop;
        public boolean inline;
        public boolean showControl;
        public String startStyle;
        public String stopStyle;
        
        static {
            CREATOR = (Parcelable$Creator)new Parcelable$Creator() {};
        }
        
        public PlayerProperties() {
            this.showControl = true;
            this.autoPlay = true;
            this.audioMuted = false;
            this.doLoop = false;
            this.stopStyle = "normal";
            this.startStyle = "normal";
            this.inline = false;
        }
        
        public PlayerProperties(final Parcel parcel) {
            super(parcel);
        }
        
        public boolean doLoop() {
            return this.doLoop;
        }
        
        public boolean doMute() {
            return this.audioMuted;
        }
        
        public boolean exitOnComplete() {
            return this.stopStyle.equalsIgnoreCase("exit");
        }
        
        public boolean isAutoPlay() {
            return this.autoPlay;
        }
        
        public boolean isFullScreen() {
            return this.startStyle.equalsIgnoreCase("fullscreen");
        }
        
        public void muteAudio() {
            this.audioMuted = true;
        }
        
        public void setProperties(final boolean audioMuted, final boolean autoPlay, final boolean showControl, final boolean inline, final boolean doLoop, final String startStyle, final String stopStyle) {
            this.autoPlay = autoPlay;
            this.showControl = showControl;
            this.doLoop = doLoop;
            this.audioMuted = audioMuted;
            this.startStyle = startStyle;
            this.stopStyle = stopStyle;
            this.inline = inline;
        }
        
        public void setStopStyle(final String stopStyle) {
            this.stopStyle = stopStyle;
        }
        
        public boolean showControl() {
            return this.showControl;
        }
    }
    
    public static class Properties extends ReflectedParcelable
    {
        public static final Parcelable$Creator CREATOR;
        public int backgroundColor;
        public float backgroundOpacity;
        public boolean isModal;
        public boolean lockOrientation;
        public boolean useBackground;
        public boolean useCustomClose;
        
        static {
            CREATOR = (Parcelable$Creator)new Parcelable$Creator() {};
        }
        
        public Properties() {
            this.useBackground = false;
            this.backgroundColor = 0;
            this.backgroundOpacity = 0.0f;
            this.isModal = false;
            this.lockOrientation = false;
            this.useCustomClose = false;
        }
        
        protected Properties(final Parcel parcel) {
            super(parcel);
        }
    }
    
    public static class ReflectedParcelable implements Parcelable
    {
        public ReflectedParcelable() {
        }
        
        protected ReflectedParcelable(final Parcel parcel) {
            final Field[] declaredFields = this.getClass().getDeclaredFields();
            int n = 0;
            while (true) {
                try {
                    if (n >= declaredFields.length) {
                        goto Label_0104;
                    }
                    final Field field = declaredFields[n];
                    final Class<?> type = field.getType();
                    if (!type.isEnum()) {
                        goto Label_0105;
                    }
                    final String string = type.toString();
                    if (string.equals("class com.tapjoy.mraid.util.NavigationStringEnum")) {
                        field.set(this, NavigationStringEnum.fromString(parcel.readString()));
                    }
                    else if (string.equals("class com.tapjoy.mraid.util.TransitionStringEnum")) {
                        field.set(this, TransitionStringEnum.fromString(parcel.readString()));
                    }
                }
                catch (IllegalArgumentException ex) {
                    ex.printStackTrace();
                }
                catch (IllegalAccessException ex2) {
                    ex2.printStackTrace();
                    return;
                }
                ++n;
            }
        }
        
        public int describeContents() {
            return 0;
        }
        
        public void writeToParcel(final Parcel parcel, int n) {
            final Field[] declaredFields = this.getClass().getDeclaredFields();
            n = 0;
            while (true) {
                try {
                    if (n >= declaredFields.length) {
                        goto Label_0106;
                    }
                    final Field field = declaredFields[n];
                    final Class<?> type = field.getType();
                    if (!type.isEnum()) {
                        goto Label_0107;
                    }
                    final String string = type.toString();
                    if (string.equals("class com.tapjoy.mraid.util.NavigationStringEnum")) {
                        parcel.writeString(((NavigationStringEnum)field.get(this)).getText());
                    }
                    else if (string.equals("class com.tapjoy.mraid.util.TransitionStringEnum")) {
                        parcel.writeString(((TransitionStringEnum)field.get(this)).getText());
                    }
                }
                catch (IllegalArgumentException ex) {
                    ex.printStackTrace();
                }
                catch (IllegalAccessException ex2) {
                    ex2.printStackTrace();
                    return;
                }
                ++n;
            }
        }
    }
}
