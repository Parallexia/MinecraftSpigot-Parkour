package online.parallexia.mcplugin.parkour.event.game;

import online.parallexia.mcplugin.parkour.game.ParkourRegion;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/*玩家离开跑酷区域时触发的事件*/
public class PlayerLeaveEvent extends Event {
    Player player;
    ParkourRegion region;
    private static final HandlerList handlerList = new HandlerList();
    @NotNull
    public HandlerList getHandlers() {
        return handlerList;
    }
}
