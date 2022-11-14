package online.parallexia.mcplugin.parkour.game.strategy;

import org.bukkit.util.Vector;

/**
 * <h>水平方向的移动</h>
 * <p>移动不会发生突变，只能一次改变90°</p>
 *
 * @author Parallexia
 * @since 2022/11/6 15:57
 */
public enum HorizontalMoveDirection {
    PositiveX(1,0),
    PositiveY(0,1),
    NegativeX(-1,0),
    NegativeY(0,-1);
    public final int nx;
    public final int ny;

    HorizontalMoveDirection(int nx,int ny){
        this.nx = nx;
        this.ny = ny;
    }

    /**
     * <p>改变下一跳的水平方向</p>
     * @param current 当前的方向
     * @param allSteps 当前所在的跳数
     * @param maxSteps 最大允许的跳数
     * @return 新的方向,且夹角为90°
     * @throws RuntimeException 无法寻找到新的方向*/
    public static HorizontalMoveDirection change(HorizontalMoveDirection current, Vector allSteps, Vector maxSteps) throws RuntimeException{
        int tryStepX = allSteps.getBlockX(),tryStepY = allSteps.getBlockY();

        int distance;
        for (HorizontalMoveDirection direction: HorizontalMoveDirection.values()){
            distance = Math.abs(current.nx - direction.nx) + Math.abs(current.ny - direction.ny);
            if (distance != 1){
                continue;
            }
            tryStepX += direction.nx;
            tryStepY += direction.ny;
            if (0 < tryStepX && tryStepX < maxSteps.getBlockX()
             && 0 < tryStepY && tryStepY < maxSteps.getBlockY()) {
                return direction;
            }
        }
        throw new RuntimeException("无法改变方向");
    }
}
