// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import android.app.Application$ActivityLifecycleCallbacks;
import android.app.Application;
import android.content.Context;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.List;

public class dy
{
    private static final String[] a;
    private static final String[] b;
    private static final String[] c;
    private static final String[] d;
    private final AppLovinSdkImpl e;
    private final List<String> f;
    private final AtomicBoolean g;
    private final AtomicBoolean h;
    private final AtomicBoolean i;
    private Date j;
    private Date k;
    
    static {
        a = new String[] { "paused", "saved_instance_state" };
        b = new String[] { "paused", "saved_instance_state", "stopped", "started" };
        c = new String[] { "paused", "stopped", "saved_instance_state", "started" };
        d = new String[] { "saved_instance_state", "paused", "stopped", "started" };
    }
    
    dy(final AppLovinSdkImpl e) {
        this.f = new ArrayList<String>();
        this.g = new AtomicBoolean();
        this.h = new AtomicBoolean();
        this.i = new AtomicBoolean();
        this.e = e;
    }
    
    private static boolean a(final List<String> list, final String[] array) {
        final int size = list.size();
        final int length = array.length;
        if (size == 0 || length == 0) {
            return false;
        }
        if (size >= array.length) {
            int i;
            for (int n = i = size - length; i < length; ++i) {
                if (!list.get(i).equals(array[i - n])) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    private void e() {
        this.f.add("paused");
    }
    
    private void f() {
        this.f.add("saved_instance_state");
    }
    
    private void g() {
        if (this.i.get()) {
            return;
        }
        if (this.e.get(ea.dp) && a(this.f, dy.a)) {
            final boolean booleanValue = this.e.get(ea.dm);
            final long millis = TimeUnit.MINUTES.toMillis(this.e.get(ea.do));
            if (this.j == null || System.currentTimeMillis() - this.j.getTime() >= millis) {
                ((EventServiceImpl)this.e.getEventService()).a("paused", false);
                if (booleanValue) {
                    this.j = new Date();
                }
            }
            if (!booleanValue) {
                this.j = new Date();
            }
        }
        this.f.add("stopped");
    }
    
    private void h() {
        if (this.f.isEmpty()) {
            return;
        }
        final String s = this.f.get(this.f.size() - 1);
        if (!"stopped".equals(s) && !"saved_instance_state".equals(s)) {
            this.f.clear();
            return;
        }
        this.f.add("started");
    }
    
    private void i() {
        if (this.i.getAndSet(false)) {
            return;
        }
        if (a(this.f, dy.b) || a(this.f, dy.c) || a(this.f, dy.d)) {
            final boolean booleanValue = this.e.get(ea.dm);
            final long millis = TimeUnit.MINUTES.toMillis(this.e.get(ea.dn));
            if (this.k == null || System.currentTimeMillis() - this.k.getTime() >= millis) {
                ((EventServiceImpl)this.e.getEventService()).a("resumed", false);
                if (booleanValue) {
                    this.k = new Date();
                }
            }
            if (!booleanValue) {
                this.k = new Date();
            }
            this.e.a().a("app_paused_and_resumed");
            this.h.set(true);
        }
        this.f.clear();
    }
    
    private void j() {
        this.f.clear();
    }
    
    public void a() {
        this.i.set(true);
    }
    
    void a(Context applicationContext) {
        if (applicationContext != null && ab.c() && this.e.get(ea.dl) && !this.g.getAndSet(true)) {
            if (!(applicationContext instanceof Application)) {
                applicationContext = applicationContext.getApplicationContext();
            }
            ((Application)applicationContext).registerActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)new dz(this));
        }
    }
    
    public void b() {
        this.i.set(false);
    }
    
    boolean c() {
        return this.g.get();
    }
    
    boolean d() {
        return this.h.getAndSet(false);
    }
}
