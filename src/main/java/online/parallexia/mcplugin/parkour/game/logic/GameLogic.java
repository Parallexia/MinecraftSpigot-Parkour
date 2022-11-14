package online.parallexia.mcplugin.parkour.game.logic;

import online.parallexia.mcplugin.parkour.event.game.StepOnBlockEvent;
import online.parallexia.mcplugin.parkour.game.Game;
import online.parallexia.mcplugin.parkour.game.IGame;
import online.parallexia.mcplugin.parkour.game.IParkourGameEventReactor;
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

//TODO:实现获取运行时变量接口
/**
     * <h>适配于类{@link Game}的游戏逻辑，存储了游戏所有的运行时变量，并实现了接口方法</h>
     * <p>实现了与事件的交互接口{@link IParkourGameEventReactor}</p>
     * <p>实现了一些运行时变量的获取接口{@link IParkourGameRuntimeField}</p>
     * <p>调用了游戏策略</p>
     */
    public class GameLogic implements IParkourGameEventReactor,IParkourGameRuntimeField {
        //玩家正在站立的方块
        private Block standBlock;
        //目标方块
        private Block targetBlock;

        //TODO:处理空指针
        private HorizontalMoveDirection horizontalMoveDirection;

        private VerticalMoveDirection verticalMoveDirection;

        private ParkourGameStrategy strategy;

    public GameLogic() {
        this.strategy = new ParkourGameStrategy();
    }

    /*判断玩家是否成功的踩到了方块上*/
        private boolean isSuccessfulOnTargetBlock(@NotNull IGame game, @NotNull Player player) {
            return false;
        }

        /*当游戏开始时执行的逻辑*/
        private void gameStart(@NotNull IGame game, @NotNull Player player) {
            Block block = game.getLocation().getBlock();
        }

        /*生成新的方块并删去旧方块,由方块列表提供模式*/
        private void generateNewTarget(@NotNull IGame game, @NotNull Player player) {

            //生成的方块距离原点的个数，此处应该为正值
            int stepX = 0, stepY = 0, stepZ = 0;
            //下一个方块应该位于的位置

            standBlock.setType(Material.AIR);
            standBlock = targetBlock;
            //TODO:测试代码正确性
            targetBlock = strategy.getNewTargetBlockLocation(game,standBlock,horizontalMoveDirection,verticalMoveDirection).getBlock();

        }


        /*玩家能否到达该位置*/
        private boolean isReachable(@NotNull Location result) {
            //TODO:改为更加精细的判断
            return result.subtract(standBlock.getLocation().clone()).toVector().length() < 5;
        }

        private boolean checkStepAvail(int step, int maxStep) {
            return step > 0 && step < maxStep;
        }

        private void start(@NotNull IGame game) throws NullPointerException {
            if (game.getPlayer().isEmpty())
                throw new NullPointerException("玩家不存在");
            game.setStarted(true);
        }

        private void stop(@NotNull IGame game) {
            game.setStarted(false);
        }

        @Override
        public void onPlayerStepOnBlockEventBeHandled(@NotNull StepOnBlockEvent event) {
            boolean isSuccessfulOnBlock = isSuccessfulOnTargetBlock(event.game, event.player);
            if (isSuccessfulOnBlock)
                generateNewTarget(event.game, event.player);
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
