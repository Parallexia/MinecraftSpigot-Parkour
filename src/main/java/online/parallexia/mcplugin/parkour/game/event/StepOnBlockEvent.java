package online.parallexia.mcplugin.parkour.game.event;

import online.parallexia.mcplugin.parkour.game.eneity.IGame;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/*
玩家踩下方块的事件
当玩家踩下方块后，触发该事件，并传递踩下的方块与玩家*/
public class StepOnBlockEvent extends Event {

    private static final HandlerList handlerList = new HandlerList();

    public IGame game;
    /*玩家所踩下的方块*/
    public Block block;
    /*踩下方块的玩家*/
    public Player player;
    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
