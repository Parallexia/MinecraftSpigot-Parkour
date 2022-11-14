package online.parallexia.mcplugin.parkour.factory.game;

import online.parallexia.mcplugin.parkour.game.Game;
import online.parallexia.mcplugin.parkour.game.IGameOption;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class GameFactory {
    public static Game newGame(Location position, Vector lineVector, @NotNull IGameOption option, Player player){
        return new Game(position,lineVector,option,player);
    }
}
