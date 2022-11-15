package online.parallexia.mcplugin.parkour.game.runtime;

import online.parallexia.mcplugin.parkour.Parkour;
import online.parallexia.mcplugin.parkour.event.game.StepOnBlockEvent;
import online.parallexia.mcplugin.parkour.game.Game;
import online.parallexia.mcplugin.parkour.game.IGame;
import online.parallexia.mcplugin.parkour.game.IParkourGameEventExecutor;
import online.parallexia.mcplugin.parkour.game.strategy.HorizontalMoveDirection;
import online.parallexia.mcplugin.parkour.game.strategy.ParkourGameStrategy;
import online.parallexia.mcplugin.parkour.game.strategy.VerticalMoveDirection;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.NoSuchElementException;
import java.util.concurrent.ExecutionException;

//TODO:实现获取运行时变量接口

/**
 * <h>适配于类{@link Game}的游戏逻辑，存储了游戏所有的运行时变量，并实现了接口方法</h>
 * <p>实现了与事件的交互接口{@link IParkourGameEventExecutor}</p>
 * <p>实现了一些运行时变量的获取接口{@link IParkourGameRuntimeField}</p>
 * <p>调用了游戏策略</p>
 */
public class GameRunTime implements IParkourGameEventExecutor, IParkourGameRuntimeField {
    //玩家正在站立的方块
    private Block standBlock;
    //目标方块
    private Block targetBlock;

    //TODO:处理空指针
    private HorizontalMoveDirection horizontalMoveDirection;

    private VerticalMoveDirection verticalMoveDirection;

    private final ParkourGameStrategy strategy;

    public GameRunTime() {
        this.strategy = new ParkourGameStrategy();
    }

    /**
     * 判断游戏实例是否处于运行时
     *
     * @param game 需要进行判断的游戏实例
     * @see Game
     * @see GameRunTime
     */
    private static void checkRun(@NotNull IGame game) throws IllegalStateException {
        if (!game.getStarted())
            throw new IllegalStateException("游戏未处于运行时");
    }

    /*判断玩家是否成功的踩到了方块上*/
    private boolean isSuccessfulOnTargetBlock(@NotNull IGame game, @NotNull Player player) {
        return false;
    }

    /**
     * 当游戏开始时执行的逻辑
     * <p>初始化站立方块</p>
     * <p>初始化目标方块</p>
     */
    private void onGameStart(@NotNull IGame game, @NotNull Player player) {
        Block block = game.getLocation().getBlock();
        horizontalMoveDirection = HorizontalMoveDirection.PositiveY;
        verticalMoveDirection = VerticalMoveDirection.Level;
    }

    /**
     * 生成新的目标方块
     *
     * @param game   目标方块所指定的游戏
     * @param player 设置该方块为目标的玩家
     * @throws ExecutionException 无法正常的生成目标方块位置时抛出
     */
    private void generateNewTarget(@NotNull IGame game, @NotNull Player player) throws ExecutionException {

        //生成的方块距离原点的个数，此处应该为正值
        int stepX = 0, stepY = 0, stepZ = 0;
        //下一个方块应该位于的位置

        standBlock.setType(Material.AIR);
        standBlock = targetBlock;
        //TODO:测试代码正确性
        targetBlock = strategy.getNewTargetBlockLocation(game, standBlock, horizontalMoveDirection, verticalMoveDirection).getBlock();

    }


    /**
     * 玩家能否到达该位置
     *
     * @param game   位置所位于的游戏
     * @param result 需要判断的位置
     */
    public boolean isReachable(Game game, @NotNull Location result) {
        checkRun(game);
        //TODO:改为更加精细的判断
        return result.clone().subtract(standBlock.getLocation().clone()).toVector().length() < 5;
    }

    @Override
    public void onPlayerStepOnBlockEventBeHandled(@NotNull StepOnBlockEvent event) {
        boolean isSuccessfulOnBlock = isSuccessfulOnTargetBlock(event.game, event.player);
        if (isSuccessfulOnBlock) {
            try {
                generateNewTarget(event.game, event.player);
            } catch (ExecutionException e) {
                Parkour.getInstance().getLogger().warning(
                        "游戏{" + event.game.getUUID().toString() + "}" + "目标方块无法生成"
                );
            }
        }
    }

    /*实现运行时变量的获取*/
    @Override
    public Vector getAllStepsClone() {
        return null;
    }

    @Override
    public Block getTargetBlock() throws NoSuchElementException {
        return null;
    }

    @Override
    public Block getStandBlock() throws NoSuchElementException {
        return null;
    }
}
