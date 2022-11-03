package online.parallexia.mcplugin.parkour;

import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Parkour extends JavaPlugin {
private JavaPlugin instance = null;
private String pluginName = "Parkour";
private final Logger logger = LoggerFactory.getLogger("pluginLogger");;
public JavaPlugin getInstance() {
    return instance;
}

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        logger.info(pluginName+"插件已加载");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        logger.info(pluginName+"插件已卸载");
    }
}