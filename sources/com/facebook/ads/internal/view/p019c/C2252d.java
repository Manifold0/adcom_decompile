package com.facebook.ads.internal.view.p019c;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.facebook.ads.internal.p021o.C2058a;
import com.facebook.ads.internal.p021o.C2059b;
import com.facebook.ads.internal.p024h.C2015d;
import com.facebook.ads.internal.p025w.p026b.C2587p;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p025w.p069c.C2608f;
import java.lang.ref.WeakReference;

/* renamed from: com.facebook.ads.internal.view.c.d */
public class C2252d extends AsyncTask<String, Void, Bitmap[]> {
    /* renamed from: b */
    private static final String f5278b = C2252d.class.getSimpleName();
    /* renamed from: a */
    public boolean f5279a = false;
    /* renamed from: c */
    private final WeakReference<Context> f5280c;
    /* renamed from: d */
    private final int f5281d;
    @Nullable
    /* renamed from: e */
    private final WeakReference<ImageView> f5282e;
    @Nullable
    /* renamed from: f */
    private final WeakReference<C2249b> f5283f;
    @Nullable
    /* renamed from: g */
    private final WeakReference<ViewGroup> f5284g;
    /* renamed from: h */
    private C1802e f5285h;
    /* renamed from: i */
    private int f5286i = -1;
    /* renamed from: j */
    private int f5287j = -1;

    public C2252d(ViewGroup viewGroup, int i) {
        this.f5280c = new WeakReference(viewGroup.getContext());
        this.f5283f = null;
        this.f5282e = null;
        this.f5284g = new WeakReference(viewGroup);
        this.f5281d = i;
    }

    public C2252d(ImageView imageView) {
        this.f5280c = new WeakReference(imageView.getContext());
        this.f5283f = null;
        this.f5282e = new WeakReference(imageView);
        this.f5284g = null;
        this.f5281d = 0;
    }

    public C2252d(C2249b c2249b) {
        this.f5280c = new WeakReference(c2249b.getContext());
        this.f5283f = new WeakReference(c2249b);
        this.f5282e = null;
        this.f5284g = null;
        this.f5281d = 0;
    }

    /* renamed from: a */
    public C2252d m5771a() {
        this.f5286i = -1;
        this.f5287j = -1;
        return this;
    }

    /* renamed from: a */
    public C2252d m5772a(int i, int i2) {
        this.f5286i = i;
        this.f5287j = i2;
        return this;
    }

    /* renamed from: a */
    public C2252d m5773a(C1802e c1802e) {
        this.f5285h = c1802e;
        return this;
    }

    /* renamed from: a */
    public C2252d m5774a(boolean z) {
        this.f5279a = z;
        return this;
    }

    /* renamed from: a */
    public void m5775a(String str) {
        if (!TextUtils.isEmpty(str)) {
            executeOnExecutor(C2587p.f6372a, new String[]{str});
        } else if (this.f5285h != null) {
            this.f5285h.mo5349a(false);
        }
    }

    /* renamed from: a */
    protected void m5776a(Bitmap[] bitmapArr) {
        if (this.f5282e != null) {
            ImageView imageView = (ImageView) this.f5282e.get();
            if (imageView != null) {
                imageView.setImageBitmap(bitmapArr[0]);
            }
        }
        if (this.f5283f != null) {
            C2249b c2249b = (C2249b) this.f5283f.get();
            if (c2249b != null) {
                c2249b.m5757a(bitmapArr[0], bitmapArr[1]);
            }
        }
        if (!(this.f5284g == null || this.f5284g.get() == null || bitmapArr[1] == null)) {
            C2600x.m6681a((View) this.f5284g.get(), new BitmapDrawable(((Context) this.f5280c.get()).getResources(), bitmapArr[1]));
        }
        if (this.f5285h != null) {
            this.f5285h.mo5349a(bitmapArr[0] != null);
        }
    }

    /* renamed from: a */
    protected Bitmap[] m5777a(String... strArr) {
        Object obj;
        Throwable th;
        Object obj2;
        String str;
        String str2 = strArr[0];
        Context context = (Context) this.f5280c.get();
        if (context == null) {
            return new Bitmap[]{null, null};
        }
        Bitmap bitmap;
        Bitmap bitmap2;
        try {
            Bitmap a = C2015d.m4857a(context).m4864a(str2, this.f5286i, this.f5287j);
            try {
                int i = (this.f5283f == null || this.f5283f.get() == null) ? 0 : 1;
                int i2 = (this.f5284g == null || this.f5284g.get() == null) ? 0 : 1;
                if ((i == 0 && i2 == 0) || a == null || this.f5279a) {
                    bitmap = null;
                } else {
                    C2608f c2608f = new C2608f(a);
                    c2608f.m6705a(this.f5281d != 0 ? this.f5281d : Math.round(((float) a.getWidth()) / 40.0f));
                    bitmap = c2608f.m6704a();
                }
                bitmap2 = a;
            } catch (Throwable th2) {
                Throwable th3 = th2;
                obj = a;
                th = th3;
                Log.e(f5278b, "Error downloading image: " + str2, th);
                C2059b.m5023a(C2058a.m5020a(th, null));
                obj2 = str;
                obj = null;
                return new Bitmap[]{bitmap2, bitmap};
            }
        } catch (Throwable th22) {
            th = th22;
            str = null;
            Log.e(f5278b, "Error downloading image: " + str2, th);
            C2059b.m5023a(C2058a.m5020a(th, null));
            obj2 = str;
            obj = null;
            return new Bitmap[]{bitmap2, bitmap};
        }
        return new Bitmap[]{bitmap2, bitmap};
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m5777a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m5776a((Bitmap[]) obj);
    }
}
