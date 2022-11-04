package online.parallexia.mcplugin.parkour.eneity.game;

import online.parallexia.mcplugin.parkour.event.game.StepOnBlockEvent;

/**
 * 实现逻辑与事件处理器的通信
 * @date 2022/11/4 14:24
 */
public interface IParkourGameLogic extends IGameLogic {
    /*当事件被处理后，调用此方法*/
    /*事件的前置处理由监听器完成*/
    void onPlayerStepOnBlockEventBeHandled(StepOnBlockEvent event);
}
