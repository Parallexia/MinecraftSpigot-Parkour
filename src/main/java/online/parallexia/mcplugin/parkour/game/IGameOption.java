package online.parallexia.mcplugin.parkour.game;

import org.bukkit.Material;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@SerializableAs("ParkourOption")
public interface IGameOption{
    /**
     * @return 游戏支持的最远的步数，超过这个步数需要折返*/
    int getMaxStep();
    /**
     * @return 生成的方块的材料
     * @see Material
     */
    List<Material> getBlockPattern();

    /**
     * @return 一步最近的方块数
     * */
    int getMinStepSize();

    /**
     * @return 一步最远的方块数
     */
    int getMaxStepSize();
    /**
     * @return 该配置下的场地大小
     */
    @NotNull
    default Vector getSizeClone(){
        return Game.calcSize(getMaxStep(), getMaxStepSize());
    }

    /***
     * 获取所有方向的最大步数
     * @return 所有方向的最大步数向量的副本
     */
    @NotNull
    Vector getAllStepsClone();

    /**
     * 获取垂直方向上的最大步数
     * 默认跳跃数为1*/
    default int getZLen() {return 1;}
}

