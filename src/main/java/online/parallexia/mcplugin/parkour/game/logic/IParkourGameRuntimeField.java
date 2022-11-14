package online.parallexia.mcplugin.parkour.game.logic;

import org.bukkit.block.Block;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.NoSuchElementException;

/**
 * <h>游戏运行时字段</h>
 * <p>有时其他类进心计算，需要操作游戏运行时的某些字段
 * 该接口的方法可用获取相关的字段</p>
 *
 * @author Parallexia
 * @since 2022/11/10 10:00
 */
public interface IParkourGameRuntimeField {
    /**
     * 获取游戏当前的步骤
     * @return 当前所处步骤向量的副本*/
    Vector getAllStepsClone();

    /**
     * 获取游戏的目标方块
     * @return 游戏的目标方块
     * @throws NoSuchElementException 该方块不存在时抛出
     * */
    Block getTargetBlock() throws NoSuchElementException;

    /**
     * 获取游戏的当前站立方块
     * @return 游戏的当前站立方块
     * @throws NoSuchElementException 该方块不存在时抛出*/
    Block getStandBlock() throws NoSuchElementException;
}
