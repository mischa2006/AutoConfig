package me.Mischarod.autoConfig.AutoConfigSystem;


import me.Mischarod.autoConfig.AutoConfigSystem.Anotations.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ConfigSystem {
    
    
    
    public static void LoadConfig(JavaPlugin plugin) {
        List<Class<? extends Annotation>> annotations = List.of(
                IntConfigValue.class,
                DoubleConfigValue.class,
                StringConfigValue.class,
                BooleanConfigValue.class,
                FloatConfigValue.class,
                LongConfigValue.class,
                StringConfigValue.class
                
        );
        
        for (Class<?> clazz : AutoConfigParser.getRegisteredClasses()) {
            // Check if the class has the AutoConfig annotation
            if (clazz.isAnnotationPresent(AutoConfig.class)) {
                AutoConfig autoConfigAnno = clazz.getAnnotation(AutoConfig.class);
                String path = autoConfigAnno.config();
                File file = new File(plugin.getDataFolder(), path);
                
                // Ensure the file and its parent directories exist
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                
                YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
                Field[] fields = clazz.getDeclaredFields();
                
                for (Field field : fields) {
                    // Check for each annotation
                    for (Class<? extends Annotation> annotation : annotations) {
                        Annotation fieldAnnotation = field.getAnnotation(annotation);
                        if (fieldAnnotation != null) {
                            try {
                                // Dynamically get the path and default value using reflection
                                String pathKey = (String) annotation.getMethod("path").invoke(fieldAnnotation);
                                Object defaultValue = annotation.getMethod("defaultValue").invoke(fieldAnnotation);
                                
                                // Load the configuration for the field
                                loadConfigField(config, field, pathKey, defaultValue);
                            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
                
                try {
                    config.save(file);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    
    private static void loadConfigField(YamlConfiguration config, Field field, String path, Object defaultValue) {
        if (!config.contains(path)) {
            config.set(path, defaultValue);
        }
        try {
            field.setAccessible(true);
            field.set(null, config.get(path));
            
            System.out.println(field.getName() + ": " + field.get(null));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    
    
    
    
}
