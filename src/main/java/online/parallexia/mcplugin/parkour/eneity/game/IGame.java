package online.parallexia.mcplugin.parkour.eneity.game;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.UUID;

public interface IGame {
    //获取游戏的UID
    UUID getUUID();
    //获取游戏的选项
    IGameOption getGameOption();
    //获取游戏区域的起始位置
    Location getLocation();
    //获取游戏区域的对角线
    Vector getLineVector();
    //开始游戏
    void start();
    //结束游戏
    void end();
    //获取该游戏中的玩家
    List<Player> getPlayer();
    /*@returns 是否在游戏中*/
    boolean inGame();

    /*获取游戏逻辑*/
    IGameLogic getGameLogic();

    void setPlayer(List<Player> player);


}
