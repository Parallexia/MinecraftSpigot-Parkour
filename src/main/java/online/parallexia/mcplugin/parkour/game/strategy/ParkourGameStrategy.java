package online.parallexia.mcplugin.parkour.game.strategy;

import online.parallexia.mcplugin.parkour.game.IGame;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * <h></h>
 * <p></p>
 *
 * @author Parallexia
 * @since 2022/11/6 16:55
 */
public class ParkourGameStrategy {
    private static final Random random = new Random();

    /**
     * <p>获取一个新的目标方块位置</p>*/
    public @NotNull Location getNewTargetBlockLocation(@NotNull IGame game, @NotNull Block standBlockLocation,
                                                       @NotNull HorizontalMoveDirection hor ,@NotNull VerticalMoveDirection vrt) {
        Vector maxSteps = game.getGameOption().getAllStepsClone();
        Vector currentSteps = game.getRuntimeField().getAllStepsClone();

        while (true){
        boolean keepDirection = false;
        if (currentSteps.getBlockX() + hor.nx > maxSteps.getBlockX()||currentSteps.getBlockY() + hor.ny > maxSteps.getBlockY()
        || currentSteps.getBlockX() + hor.nx < 0 || currentSteps.getBlockY() + hor.ny < 0
        ){
             keepDirection = random.nextBoolean();
        }
        if (currentSteps.getBlockZ() + vrt.nz > maxSteps.getBlockZ() || currentSteps.getBlockZ() + vrt.nz < 0){
            VerticalMoveDirection.change(vrt,currentSteps,maxSteps);
        }
       if (!keepDirection)
           HorizontalMoveDirection.change(hor,currentSteps,maxSteps);

       /*一步的跨度区间*/
       int minLen = game.getGameOption().getMinStepSize(),maxLen = game.getGameOption().getMaxStepSize();
       int zLen =game.getGameOption().getZLen();

       Vector min = new Vector(),max = new Vector();
       /*边界*/
        game.getBoundVectorClone(min,max);

        int boundX = getBound(hor.nx, max.getBlockX(), min.getBlockX(),standBlockLocation.getX()),
                boundY = getBound(hor.ny, max.getBlockY(), min.getBlockY(),standBlockLocation.getY()),
                boundZ = getBound(vrt.nz, max.getBlockZ(), min.getBlockZ(),standBlockLocation.getZ());
        if (boundX < minLen || boundY < minLen || boundZ < zLen){
            continue;
            }
        Vector change = new Vector();
        change.setX(random.nextInt(minLen,Math.min(boundX,maxLen) * hor.nx));
        change.setY(random.nextInt(minLen,Math.min(boundY,maxLen)) * hor.ny);
        change.setZ(random.nextInt(0,zLen) * vrt.nz);
            return standBlockLocation.getLocation().clone().add(change);
        }
    }

    /**
     * 获取该方向上最多能走多少格
     * @param towards 方向（1为正，0为负）
     * @param min 最小的边界
     * @param max 最大的边界
     * @param standBlockValue 目前正在站立的方块在该方向的格数
     * @return 该方向上最大前进的格数*/
    private int getBound(int towards,int min,int max,int standBlockValue){
        if (towards > 0){
            return max - standBlockValue;
        }
        if(towards < 0){
            return standBlockValue - min;
        }
        return Integer.MAX_VALUE;
    }

}
