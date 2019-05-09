package com.moat.analytics.mobile.tjy;

import android.util.Log;
import java.util.Iterator;

class au implements ax {
    /* renamed from: a */
    final /* synthetic */ as f6662a;

    au(as asVar) {
        this.f6662a = asVar;
    }

    /* renamed from: a */
    public void mo6108a(ar arVar) {
        if (as.f6658c != arVar) {
            synchronized (as.f6657b) {
                if (arVar == ar.ON && as.f6659d) {
                    Log.d("MoatOnOff", "Moat enabled - Version 1.7.10");
                }
                as.f6658c = arVar;
                Iterator it = as.f6657b.iterator();
                while (it.hasNext()) {
                    aq aqVar = (aq) it.next();
                    if (arVar == ar.ON) {
                        aqVar.mo6109a();
                    } else {
                        aqVar.mo6110b();
                    }
                    it.remove();
                }
            }
        }
        this.f6662a.m6857g();
    }
}
