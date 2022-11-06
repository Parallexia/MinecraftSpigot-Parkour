package online.parallexia.mcplugin.parkour.eneity.game;

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
     * @return 一步最远的方块数
     */
    int getMaxStepSize();
    /**
     * @return 该配置下的场地大小
     */
    @NotNull
    default Vector getSize(){
        return Game.calcSize(getMaxStep(), getMaxStepSize());
    }
}

