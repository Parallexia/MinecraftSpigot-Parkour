package online.parallexia.mcplugin.parkour.game.event;

import online.parallexia.mcplugin.parkour.game.eneity.ParkourRegion;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/*玩家加入一个新的跑酷区域所触发的事件*/
public class PlayerJoinEvent extends Event {
    Player player;
    ParkourRegion region;
    
    private final HandlerList handlerList = new HandlerList();
    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
