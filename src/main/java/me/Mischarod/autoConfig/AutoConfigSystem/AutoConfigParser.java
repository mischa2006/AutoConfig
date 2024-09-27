package me.Mischarod.autoConfig.AutoConfigSystem;

import java.util.ArrayList;
import java.util.List;

public class AutoConfigParser {
    private static final List<Class<?>> registeredClasses = new ArrayList<>();
    
    public static void registerAutoConfigClass(Class<?> clazz) {
        registeredClasses.add(clazz);
    }
    
    public static List<Class<?>> getRegisteredClasses() {
        return new ArrayList<>(registeredClasses);
    }
}