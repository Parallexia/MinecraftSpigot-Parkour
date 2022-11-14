package online.parallexia.mcplugin.parkour.event.game;

import online.parallexia.mcplugin.parkour.game.IGame;
import online.parallexia.mcplugin.parkour.game.IParkourGameEventReactor;
import online.parallexia.mcplugin.parkour.exception.PlayerNotFoundException;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public class StepOnBlockEventHandler implements Listener {
    /*
    * 在跑酷时踩下目标方块时触发的事件
    * */
    @EventHandler
    public void onBlockStepped(@NotNull StepOnBlockEvent event) throws PlayerNotFoundException{
        IGame game = event.game;
        Player player = event.player;
        /*判断玩家是否在游戏中或是否在线
        * <p>由于玩家可能退出游戏或下线，所以需要做出判断</p>*/
        if (!player.isOnline() || !event.game.getPlayer().contains(player))
            throw new PlayerNotFoundException(event.player);
        if (!(game.getGameLogic() instanceof IParkourGameEventReactor))
            throw new ClassCastException("该监听器不支持该游戏逻辑");

        /*游戏逻辑部分*/
        IParkourGameEventReactor gameLogic = (IParkourGameEventReactor) game.getGameLogic();
        gameLogic.onPlayerStepOnBlockEventBeHandled(event);
    }
}
