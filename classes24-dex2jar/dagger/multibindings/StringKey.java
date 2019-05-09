// 
// Decompiled by Procyon v0.5.34
// 

package dagger.multibindings;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.Documented;
import dagger.MapKey;
import java.lang.annotation.Annotation;

@MapKey
@Documented
@Target({ ElementType.METHOD })
public @interface StringKey {
    String value();
}
