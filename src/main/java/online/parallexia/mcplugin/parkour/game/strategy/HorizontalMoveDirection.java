package online.parallexia.mcplugin.parkour.game.strategy;

import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

/**
 * <h>水平方向的移动</h>
 * <p>移动不会发生突变，只能一次改变90°</p>
 *
 * @author Parallexia
 * @since 2022/11/6 15:57
 */
public enum HorizontalMoveDirection {
    PositiveX(1, 0),
    PositiveY(0, 1),
    NegativeX(-1, 0),
    NegativeY(0, -1);
    public final int nx;
    public final int ny;

    HorizontalMoveDirection(int nx, int ny) {
        this.nx = nx;
        this.ny = ny;
    }

    /**
     * <p>改变下一跳的水平方向</p>
     *
     * @param current  当前的方向
     * @param allSteps 当前所在的跳数
     * @param maxSteps 最大允许的跳数
     * @return 新的方向, 且夹角为90°
     * @throws RuntimeException 无法寻找到新的方向
     */
    public static @NotNull HorizontalMoveDirection change(@NotNull HorizontalMoveDirection current, @NotNull Vector allSteps, @NotNull Vector maxSteps) throws RuntimeException {
        int tryStepX,tryStepY;

        int distanceX,distanceY;
        for (HorizontalMoveDirection direction : HorizontalMoveDirection.values()) {
            tryStepX = allSteps.getBlockX();
            tryStepY = allSteps.getBlockY();

            distanceX = Math.abs(current.nx - direction.nx);
            distanceY = Math.abs(current.ny - direction.ny);
            if (!(distanceX == 1 && distanceY == 1)) {
                continue;
            }
            tryStepX += direction.nx;
            tryStepY += direction.ny;
            if (0 <= tryStepX && tryStepX < maxSteps.getBlockX()
                    && 0 <= tryStepY && tryStepY < maxSteps.getBlockY()) {
                allSteps.setX(tryStepX);
                allSteps.setY(tryStepY);
                return direction;
            }
        }
        throw new RuntimeException("无法改变方向");
    }
}
