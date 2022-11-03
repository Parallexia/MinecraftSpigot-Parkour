package online.parallexia.mcplugin.parkour.game.eneity;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*跑酷区域*/
public class ParkourRegion {
    //使用工厂类进行实例构建
    public ParkourRegion(@NotNull Location Location1,@NotNull Location Location2,String name){
        this.location1 = Location1;
        this.location2 = Location2;
        this.name = name;
    }
    private Location location1;
    private Location location2;
    private final UUID uuid = UUID.randomUUID();
    private String name;
    //该跑酷区域所支持的最大玩家人数
    private int maxPlayer;
    //runtime
    private final transient List<Player> players = new ArrayList<>();
    //该实例是否被持久化
    public boolean isStored;

    public UUID getUuid(){
        return uuid;
    }
    //name字段方法对
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    //区域大小方法对
    public void setLocation1(@NotNull Location location){
        this.location1 = location;
    }

    public void setLocation2(@NotNull Location location){
        this.location2 = location;
    }

    @NotNull
    public Location getLocation1(){
        return this.location1;
    }

    @NotNull
    public Location getLocation2(){
        return this.location2;
    }
}
