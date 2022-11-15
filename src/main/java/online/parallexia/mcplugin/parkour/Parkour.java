package online.parallexia.mcplugin.parkour;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Parkour extends JavaPlugin {
private static JavaPlugin instance = null;
private final String pluginName = "Parkour";
private final Logger SLFlogger = LoggerFactory.getLogger("pluginLogger");;
public static JavaPlugin getInstance() {
    return instance;
}
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        SLFlogger.info(pluginName+"插件已加载");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        SLFlogger.info(pluginName+"插件已卸载");
    }

    @NotNull
    public static Logger logger(){
    return ((Parkour) instance).SLFlogger;
    }
}
