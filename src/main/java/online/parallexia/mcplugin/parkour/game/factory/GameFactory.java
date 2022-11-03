package online.parallexia.mcplugin.parkour.game.factory;

import online.parallexia.mcplugin.parkour.game.eneity.Game;
import online.parallexia.mcplugin.parkour.game.eneity.GameOption;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class GameFactory {
    public static Game newGame(Location position, GameOption option, Player player){
        return new Game(position,option,player);
    }
}
