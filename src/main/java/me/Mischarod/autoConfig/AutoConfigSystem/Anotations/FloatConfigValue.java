package me.Mischarod.autoConfig.AutoConfigSystem.Anotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FloatConfigValue {
    float defaultValue() default 0;
    String path();
}
