package online.parallexia.mcplugin.parkour.event.game;

import online.parallexia.mcplugin.parkour.eneity.game.ParkourRegion;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/*
* 玩家离开跑酷区域时触发的事件*/
public class PlayerOutOfRegionEvent extends Event {
    private static final HandlerList handlerList = new HandlerList();

    public ParkourRegion region;
    public Player player;
    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}