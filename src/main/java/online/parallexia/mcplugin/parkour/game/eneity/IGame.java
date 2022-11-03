package online.parallexia.mcplugin.parkour.game.eneity;

import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.UUID;

public interface IGame {
    //获取游戏的UID
    UUID getUUID();
    //获取游戏区域的大小
    Vector getSize();
    //开始游戏
    void start();
    //结束游戏
    void end();
    //获取该游戏中的玩家
    List<Player> getPlayer();
    /*@returns 是否在游戏中*/
    boolean inGame();

    void setPlayer(List<Player> player);

}
