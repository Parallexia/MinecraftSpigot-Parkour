package online.parallexia.mcplugin.parkour.game.strategy;

import org.bukkit.util.Vector;

/**
 * <h>垂直方向的移动</h>
 * <p>移动不会发生突变，只能一次改变1</p>
 *
 * @author Parallexia
 * @since 2022/11/6 16:11
 */
public enum VerticalMoveDirection {
    Up(1),
    Level(0),
    Down(-1);
    public final int nz;
    VerticalMoveDirection(int nz){
        this.nz= nz;
    }

    /**
     * <p>改变下一跳的垂直方向</p>
     * @param current 当前跳的垂直方向
     * @param allMaxSteps 当前跳所在的跳数
     * @param maxStep 最大所允许的跳数
     * @return 改变之后的跳数
     * @throws RuntimeException 找不到可用的新方向时抛出
     * */
    public static VerticalMoveDirection change(VerticalMoveDirection current, Vector allMaxSteps,Vector maxStep) throws RuntimeException{
        int distance;
        int tryStepZ;
        for(VerticalMoveDirection direction:VerticalMoveDirection.values()){
            distance = Math.abs(current.nz - direction.nz);
            if (distance != 1){
                continue;
            }
            tryStepZ = allMaxSteps.getBlockZ() + direction.nz;
            if (tryStepZ < maxStep.getBlockZ() && tryStepZ > 0){
                return direction;
            }
        }
        throw new RuntimeException("无法找到可用的新方向");
    }
}
