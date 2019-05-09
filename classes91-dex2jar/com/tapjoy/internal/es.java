// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.Observable;
import android.content.ComponentName;
import java.util.Set;
import android.content.Intent;
import android.app.Activity;
import com.tapjoy.TJPlacementManager;
import com.tapjoy.TJPlacement;
import com.tapjoy.TJPlacementListener;
import android.content.Context;
import java.util.HashMap;
import java.util.Map;
import java.util.Observer;

public final class es extends fw implements Observer
{
    private final Map b;
    private final el c;
    private boolean d;
    private final fc e;
    
    static {
        fw.a = new es();
    }
    
    private es() {
        this.b = new HashMap();
        this.c = new el();
        this.e = new fc() {
            @Override
            protected final boolean a() {
                return super.a() && !gn.c();
            }
        };
    }
    
    public static void a() {
    }
    
    protected final void a(final Activity activity) {
        Label_0046: {
            Label_0037: {
                if (activity != null) {
                    final int taskId = activity.getTaskId();
                    int n = 0;
                    Label_0016: {
                        if (taskId == -1) {
                            n = 0;
                        }
                        else {
                            final Intent intent = activity.getIntent();
                            if (intent != null) {
                                final Set categories = intent.getCategories();
                                int n2;
                                if (categories != null && categories.contains("android.intent.category.LAUNCHER") && "android.intent.action.MAIN".equals(intent.getAction())) {
                                    n2 = 1;
                                }
                                else {
                                    n2 = 0;
                                }
                                if (n2 != 0) {
                                    final ComponentName component = intent.getComponent();
                                    if (component == null) {
                                        n = 0;
                                        break Label_0016;
                                    }
                                    final Integer n3 = this.b.put(component.getClassName(), taskId);
                                    if (n3 != null && n3 == taskId) {
                                        n = 0;
                                        break Label_0016;
                                    }
                                    n = 1;
                                    break Label_0016;
                                }
                            }
                            n = 0;
                        }
                    }
                    if (n != 0) {
                        break Label_0037;
                    }
                }
                if (this.d || !this.c.a()) {
                    break Label_0046;
                }
            }
            this.e.c(null);
        }
        this.d = true;
    }
    
    @Override
    public final void update(final Observable observable, final Object o) {
        final ev.a d = ev.d;
    }
}
