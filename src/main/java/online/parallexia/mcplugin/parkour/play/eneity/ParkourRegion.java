package online.parallexia.mcplugin.parkour.play.eneity;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*跑酷区域*/
public class ParkourRegion {
    private Location location1;
    private Location location2;
    private final UUID uuid = UUID.randomUUID();
    private String name;

    //runtime
    private final List<Player> players = new ArrayList<>();

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

    public Location getLocation1(){
        return this.location1;
    }

    public Location getLocation2(){
        return this.location2;
    }
}
