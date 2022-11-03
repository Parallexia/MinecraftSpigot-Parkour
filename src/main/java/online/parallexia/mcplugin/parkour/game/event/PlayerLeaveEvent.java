package online.parallexia.mcplugin.parkour.game.event;

import online.parallexia.mcplugin.parkour.game.eneity.ParkourRegion;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/*玩家离开跑酷区域时触发的事件*/
public class PlayerLeaveEvent extends Event {
    Player player;
    ParkourRegion region;
    private HandlerList handlerList = new HandlerList();

    public HandlerList getHandlers() {
        return handlerList;
    }
}
