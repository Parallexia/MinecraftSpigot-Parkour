package online.parallexia.mcplugin.parkour.exception;

import online.parallexia.mcplugin.parkour.Parkour;
import org.bukkit.entity.Player;

import java.lang.reflect.Type;

/**
 * @date 2022/11/4 14:48
 */
public class PlayerNotFoundException extends NotFoundException {
    Type type = Player.class;
    public PlayerNotFoundException(Player player) {
        Parkour.getInstance().getLogger().info(player.getName() + '{' + player.getUniqueId() + '}' + "未找到");
    }
}
