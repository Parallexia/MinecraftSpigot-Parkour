package online.parallexia.mcplugin.parkour.game;

import online.parallexia.mcplugin.parkour.factory.game.GameFactory;
import online.parallexia.mcplugin.parkour.game.runtime.GameRuntime;
import online.parallexia.mcplugin.parkour.game.runtime.IParkourGameRuntimeField;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * <h>游戏类</h>
 * <p>在服务器加载时创建，持久化存储，尽量避免重复创建占用服务器资源</p>
 * <p>方块的生成必须确保不会让玩家从一个区域跳到另外一个区域</p>
 */
public class Game implements IGame {

    //TODO:实现对输入的数值进行判断
    //TODO:使用工厂类创建实例
    public Game(Location position, Vector lineVector, IGameOption option, Player player, Type factory) throws IllegalAccessException {
        if (!factory.equals(GameFactory.class))
            throw new IllegalAccessException("该类只能由"+GameFactory.class.getName()+"类实例化");
        this.option = option;
        this.position = position;
        this.player = player;
        this.lineVector = lineVector;
        this.runtime = GameRuntime.newInstance();
    }

    /*运行时添加的字段*/
    //
    private final UUID uuid = UUID.randomUUID();
    //游戏区域的对角线，由游戏区域的跳数自动决定，矩形对角线的连线
    private final Vector lineVector;
    //跑酷区域标记点
    public final Location position;

    private final IGameOption option;

    //在游戏中的玩家
    private Player player;
    //游戏所使用的逻辑
    private final GameRuntime runtime;
    private boolean isStarted = false;

    //计算游戏区域的大小，为正值
    public static Vector calcSize(int step, int maxStepSize) {
        int hor = (step) * maxStepSize;
        int vet = step + 2;
        return new Vector(hor, hor, vet);
    }

    @Override
    @NotNull
    public UUID getUUID() {
        return this.uuid;
    }

    @Override
    @NotNull
    public IGameOption getGameOption() {
        return option;
    }

    @Override
    public @NotNull Location getLocation() {
        return position;
    }

    @Override
    @NotNull
    public Vector getLineVectorClone() {
        return lineVector.clone();
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
    public void getBoundVectorClone(Vector min, Vector max) {
        if (Objects.isNull(min)) {
            min = new Vector();
        }
        if (Objects.isNull(max)) {
            max = new Vector();
        }

        min.setX(Math.min(getLocation().getBlockX(), getLocation().add(getLineVectorClone()).getBlockX()));
        max.setX(Math.max(getLocation().getBlockX(), getLocation().add(getLineVectorClone()).getBlockX()));

        min.setY(Math.min(getLocation().getBlockY(), getLocation().add(getLineVectorClone()).getBlockY()));
        max.setY(Math.max(getLocation().getBlockY(), getLocation().add(getLineVectorClone()).getBlockY()));

        min.setZ(Math.min(getLocation().getBlockZ(), getLocation().add(getLineVectorClone()).getBlockZ()));
        max.setZ(Math.max(getLocation().getBlockZ(), getLocation().add(getLineVectorClone()).getBlockZ()));
    }

    @Override
    public boolean getStarted() {
        return this.isStarted;
    }

    //TODO：完成方法
    /**
     * 变量初始化途径：
     * {@link IGameOption}静态变量
     * {@link GameRuntime}运行时变量*/
    @Override
    public void start() throws ExecutionException {
        if (Objects.isNull(player))
            throw new ExecutionException(new NullPointerException("玩家不存在"));
        runtime.onGameStart(this,player);
        isStarted = true;
    }

    //TODO:完成方法
    @Override
    public void stop() throws RuntimeException {
        runtime.onGameStop(this);
        isStarted = false;
    }

    @Override
    public void setPlayer(@NotNull List<Player> players) throws IllegalArgumentException {
        if (players.size() < 1)
            throw new IllegalArgumentException("人数不匹配");
        this.player = players.get(0);
    }

    @Override
    public @NotNull IParkourGameEventExecutor getGameEventExecutor() {
        return runtime;
    }

    @Override
    public IParkourGameRuntimeField getRuntimeField() throws IllegalStateException {
        return this.runtime;
    }

}

