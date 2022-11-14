package online.parallexia.mcplugin.parkour.factory.game;

import online.parallexia.mcplugin.parkour.game.ParkourRegion;
import org.bukkit.Location;

public class ParkourRegionFactory {
    public ParkourRegion newParkourRegion(Location location1, Location location2, String name){
        return new ParkourRegion(location1,location2,name);
    }
}
