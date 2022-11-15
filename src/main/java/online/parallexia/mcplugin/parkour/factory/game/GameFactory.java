package online.parallexia.mcplugin.parkour.factory.game;

import online.parallexia.mcplugin.parkour.Parkour;
import online.parallexia.mcplugin.parkour.game.Game;
import online.parallexia.mcplugin.parkour.game.IGameOption;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.ExecutionException;

public class GameFactory {
    public static @Nullable Game newGame(Location position, Vector lineVector, @NotNull IGameOption option, Player player) throws ExecutionException {
        try {
        Game game = new Game(position,lineVector,option,player,GameFactory.class);
        Parkour.logger().debug(String.format("游戏{%s}被创建",game.getUUID()));
        } catch (IllegalAccessException e){
            Parkour.logger().warn(String.format("类%s无法创建",Game.class.getName()));
            throw new ExecutionException(e);
        }
        throw new ExecutionException(new IllegalAccessException());
    }
}
