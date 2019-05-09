// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.d;

import java.io.Serializable;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import android.view.View;
import android.os.Bundle;
import com.facebook.ads.internal.w.b.r;

public final class a implements r<Bundle>
{
    private final View a;
    private final List<d> b;
    private c c;
    
    public a(final View a, final List<b> list) {
        this.a = a;
        this.b = new ArrayList<d>(list.size());
        final Iterator<b> iterator = list.iterator();
        while (iterator.hasNext()) {
            this.b.add(new d(iterator.next()));
        }
        this.c = new c();
    }
    
    public a(final View a, final List<b> list, final Bundle bundle) {
        this.a = a;
        this.b = new ArrayList<d>(list.size());
        final ArrayList parcelableArrayList = bundle.getParcelableArrayList("TESTS");
        for (int i = 0; i < list.size(); ++i) {
            this.b.add(new d(list.get(i), parcelableArrayList.get(i)));
        }
        this.c = (c)bundle.getSerializable("STATISTICS");
    }
    
    public void a() {
        this.c.a();
    }
    
    public void a(final double n, double n2) {
        if (n2 >= 0.0) {
            this.c.b(n, n2);
        }
        n2 = com.facebook.ads.internal.x.a.a(this.a, 0).c();
        this.c.a(n, n2);
        final Iterator<d> iterator = this.b.iterator();
        while (iterator.hasNext()) {
            iterator.next().a(n, n2);
        }
    }
    
    public void b() {
        this.c.b();
        final Iterator<d> iterator = this.b.iterator();
        while (iterator.hasNext()) {
            iterator.next().a();
        }
    }
    
    public c c() {
        return this.c;
    }
    
    @Override
    public Bundle g() {
        final Bundle bundle = new Bundle();
        bundle.putSerializable("STATISTICS", (Serializable)this.c);
        final ArrayList<Bundle> list = new ArrayList<Bundle>(this.b.size());
        final Iterator<d> iterator = this.b.iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next().g());
        }
        bundle.putParcelableArrayList("TESTS", (ArrayList)list);
        return bundle;
    }
}
