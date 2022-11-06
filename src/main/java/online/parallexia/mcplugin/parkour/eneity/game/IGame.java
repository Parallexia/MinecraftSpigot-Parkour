package online.parallexia.mcplugin.parkour.eneity.game;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

/**
 * <h>游戏的接口</h>
 * <p>游戏实例应该包含{@link IGameOption},{@link IGameLogic}</p>
 * <p>游戏的其余字段应该是运行时相关字段，固定参数由{@link IGameOption}负责</p>*/
public interface IGame {
    /**
     * <p>获取游戏实例的唯一标识符</p>
     * @return {@link UUID}*/
    UUID getUUID();
    /**
     * <p>获取游戏的设定选项</p>
     * @return {@link IGameOption} 该游戏实例应用的游戏选项*/
    @NotNull
    IGameOption getGameOption();
    //获取游戏区域的起始位置
    /**
     * <p>获取游戏区域的起始点</p>
     * @return {@link  Location}*/
    @NotNull
    Location getLocation();
    //获取游戏区域的对角线
    /**
     * <p>获取从游戏区域起始点开始的对角线向量</p>*/
    @NotNull
    Vector getLineVector();

    /**
     * <p>获取在该游戏实例中的玩家列表</p>
     * @return {@link List<Player>} 值传递，修改该列表不会改变游戏内玩家
     */
    @NotNull
    List<Player> getPlayer();
    /*@returns 是否在游戏中*/
    boolean getStarted();
    void setStarted(boolean started);
    /**
     * @return 游戏的执行逻辑的事件接口{@link IGameLogic}
     * @see IParkourGameLogic
     * @see IGameLogic
     * */
    @NotNull
    IGameLogic getGameLogic();
    /**
     * 设置在该游戏内的玩家
     * 实现方法应该对输入进行校验*/
    void setPlayer(@NotNull List<Player> player);
}
