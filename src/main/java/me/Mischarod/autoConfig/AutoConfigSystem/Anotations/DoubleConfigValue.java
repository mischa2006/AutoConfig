package me.Mischarod.autoConfig.AutoConfigSystem.Anotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DoubleConfigValue {
    
    public double defaultValue() default 0.0;
    public String path();
}
