package com.tapjoy.mraid.controller;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.tapjoy.mraid.util.NavigationStringEnum;
import com.tapjoy.mraid.util.TransitionStringEnum;
import com.tapjoy.mraid.view.MraidView;
import java.lang.reflect.Field;

public abstract class Abstract {
    public static final String EXIT = "exit";
    public static final String FULL_SCREEN = "fullscreen";
    public static final String STYLE_NORMAL = "normal";
    /* renamed from: a */
    protected MraidView f8253a;
    /* renamed from: b */
    protected Context f8254b;

    public static class ReflectedParcelable implements Parcelable {
        public int describeContents() {
            return 0;
        }

        protected ReflectedParcelable(Parcel in) {
            Field[] declaredFields = getClass().getDeclaredFields();
            int i = 0;
            while (i < declaredFields.length) {
                try {
                    Field field = declaredFields[i];
                    Class type = field.getType();
                    if (type.isEnum()) {
                        String cls = type.toString();
                        if (cls.equals("class com.tapjoy.mraid.util.NavigationStringEnum")) {
                            field.set(this, NavigationStringEnum.fromString(in.readString()));
                        } else if (cls.equals("class com.tapjoy.mraid.util.TransitionStringEnum")) {
                            field.set(this, TransitionStringEnum.fromString(in.readString()));
                        }
                    } else if (!(field.get(this) instanceof Creator)) {
                        field.set(this, in.readValue(null));
                    }
                    i++;
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                    return;
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                    return;
                }
            }
        }

        public void writeToParcel(Parcel out, int flags1) {
            Field[] fields = getClass().getDeclaredFields();
            int i = 0;
            while (i < fields.length) {
                try {
                    Field field = fields[i];
                    Class type = field.getType();
                    if (type.isEnum()) {
                        String cls = type.toString();
                        if (cls.equals("class com.tapjoy.mraid.util.NavigationStringEnum")) {
                            out.writeString(((NavigationStringEnum) field.get(this)).getText());
                        } else if (cls.equals("class com.tapjoy.mraid.util.TransitionStringEnum")) {
                            out.writeString(((TransitionStringEnum) field.get(this)).getText());
                        }
                    } else {
                        Object obj = field.get(this);
                        if (!(obj instanceof Creator)) {
                            out.writeValue(obj);
                        }
                    }
                    i++;
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                    return;
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                    return;
                }
            }
        }
    }

    public static class Dimensions extends ReflectedParcelable {
        public static final Creator CREATOR = new C30031();
        public int height;
        public int width;
        /* renamed from: x */
        public int f8251x;
        /* renamed from: y */
        public int f8252y;

        /* renamed from: com.tapjoy.mraid.controller.Abstract$Dimensions$1 */
        static class C30031 implements Creator {
            C30031() {
            }

            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new Dimensions[i];
            }

            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new Dimensions(parcel);
            }
        }

        public Dimensions() {
            this.f8251x = -1;
            this.f8252y = -1;
            this.width = -1;
            this.height = -1;
        }

        protected Dimensions(Parcel in) {
            super(in);
        }
    }

    public static class PlayerProperties extends ReflectedParcelable {
        public static final Creator CREATOR = new C30041();
        public boolean audioMuted;
        public boolean autoPlay;
        public boolean doLoop;
        public boolean inline;
        public boolean showControl;
        public String startStyle;
        public String stopStyle;

        /* renamed from: com.tapjoy.mraid.controller.Abstract$PlayerProperties$1 */
        static class C30041 implements Creator {
            C30041() {
            }

            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new PlayerProperties[i];
            }

            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new PlayerProperties(parcel);
            }
        }

        public PlayerProperties() {
            this.showControl = true;
            this.autoPlay = true;
            this.audioMuted = false;
            this.doLoop = false;
            String str = "normal";
            this.stopStyle = str;
            this.startStyle = str;
            this.inline = false;
        }

        public PlayerProperties(Parcel in) {
            super(in);
        }

        public void setStopStyle(String style) {
            this.stopStyle = style;
        }

        public void setProperties(boolean audioMuted, boolean autoPlay, boolean controls, boolean inline, boolean loop, String startStyle, String stopStyle) {
            this.autoPlay = autoPlay;
            this.showControl = controls;
            this.doLoop = loop;
            this.audioMuted = audioMuted;
            this.startStyle = startStyle;
            this.stopStyle = stopStyle;
            this.inline = inline;
        }

        public void muteAudio() {
            this.audioMuted = true;
        }

        public boolean isAutoPlay() {
            return this.autoPlay;
        }

        public boolean showControl() {
            return this.showControl;
        }

        public boolean doLoop() {
            return this.doLoop;
        }

        public boolean doMute() {
            return this.audioMuted;
        }

        public boolean exitOnComplete() {
            return this.stopStyle.equalsIgnoreCase(Abstract.EXIT);
        }

        public boolean isFullScreen() {
            return this.startStyle.equalsIgnoreCase(Abstract.FULL_SCREEN);
        }
    }

    public static class Properties extends ReflectedParcelable {
        public static final Creator CREATOR = new C30051();
        public int backgroundColor;
        public float backgroundOpacity;
        public boolean isModal;
        public boolean lockOrientation;
        public boolean useBackground;
        public boolean useCustomClose;

        /* renamed from: com.tapjoy.mraid.controller.Abstract$Properties$1 */
        static class C30051 implements Creator {
            C30051() {
            }

            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new Properties[i];
            }

            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new Properties(parcel);
            }
        }

        protected Properties(Parcel in) {
            super(in);
        }

        public Properties() {
            this.useBackground = false;
            this.backgroundColor = 0;
            this.backgroundOpacity = 0.0f;
            this.isModal = false;
            this.lockOrientation = false;
            this.useCustomClose = false;
        }
    }

    public abstract void stopAllListeners();

    public Abstract(MraidView adView, Context context) {
        this.f8253a = adView;
        this.f8254b = context;
    }
}
