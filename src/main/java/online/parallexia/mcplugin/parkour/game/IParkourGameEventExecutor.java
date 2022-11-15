package online.parallexia.mcplugin.parkour.game;

import online.parallexia.mcplugin.parkour.event.game.StepOnBlockEvent;

/**
 * 逻辑与事件处理器的通信接口
 *
 * @see IGameEventExecutor
 * @since 2022/11/4 14:24
 */
public interface IParkourGameEventExecutor extends IGameEventExecutor {
    /**
     * <p>{@link StepOnBlockEvent}触发的游戏逻辑</p>
     */
    void onPlayerStepOnBlockEventBeHandled(StepOnBlockEvent event);
}
