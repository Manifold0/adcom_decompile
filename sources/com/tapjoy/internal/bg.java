package com.tapjoy.internal;

import java.util.concurrent.Callable;

public interface bg extends Callable {
    Object call();
}
