package online.parallexia.mcplugin.parkour.game.eneity;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/*
* 游戏类
* 在服务器加载时创建，持久化存储，尽量避免重复创建占用服务器资源*/
public class Game implements IGame{


    //TODO:实现对输入的数值进行判断
    public Game(Location position,GameOption option,Player player)
    {
        this.blocks = option.blocks;
        this.step = option.step;
        this.position = position;
        this.player = player;
        this.size = calcSize(step);
    }
    private UUID uuid = UUID.randomUUID();
    //游戏区域的大小，由游戏区域的跳数自动决定
    private Vector size;
    //游戏区域的跳数
    private int step;
    //游戏使用的方块
    private List<Material> blocks;
    //跑酷区域标记点
    private Location position;
    //在游戏中的玩家
    private Player player;

    private boolean isStarted = false;

    //计算游戏区域的大小
    public static Vector calcSize(int step){
        int hor = step * 5;
        int vet = step;
        return new Vector(hor,hor,vet);
    }

    @Override
    @NotNull
    public UUID getUUID() {
        return this.uuid;
    }

    //获取游戏区域的大小
    @Override
    @NotNull
    public Vector getSize(){
        return size.clone();
    }

    @Override
    public void start() {
        this.isStarted = true;
    }

    @Override
    public void end() {
        this.isStarted = false;
    }

    @Override
    @NotNull
    public List<Player> getPlayer() {
        List<Player> list = new ArrayList<>();
        if (Objects.nonNull(player))
            list.add(player);
        return list;
    }

    @Override
    public boolean inGame() {
        return this.isStarted;
    }

    @Override
    public void setPlayer(List<Player> players) {
        this.player = players.get(0);
    }
}

