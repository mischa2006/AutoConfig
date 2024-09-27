
# AutoConfig

An automatic config system for JavaPlugins
made this because i got alot of requests for it




## Usage/Examples

```java
@AutoConfig(config = "anyDir/anyFile.yml") //sets the path of where to create a config file (if blank it will default to config.yml)
public class AutoConfigExample {
    
    @IntConfigValue(defaultValue = 20, path = "exampleInt")public static int a = 2; //sets the path of the value in the config file
    @StringConfigValue(defaultValue = "example", path = "exampleString")public static String b = "example"; //works for all primitive types and strings
    //if the path is not found in the config file it will create it with the default value
    //the value HAS to be static and public, otherwise this will not work
}
```

to use the system make sure to have this in your main class

```java
AutoConfigParser.registerAutoConfigClass(AutoConfigExample.class);
//regesters the autoConfig class

ConfigSystem.LoadConfig(YourPlugin.instance); //or just this

//will load all the config values

//make sure the Parser is above the loadConifg function

```



